import { useEffect, useState } from "react";
import MainLayout from "../../components/MainLayout";
import DashboardCards from "../../components/DashboardCards/DashboardCards";
import SpendingPieChart from "../../components/Charts/SpendingPieChart";
import MonthlyTrendChart from "../../components/Charts/MonthlyTrendChart";
import { MONTHS } from "../../utils/constants";
import { formatCurrency } from "../../utils/formatters";
import "../../components/Charts/Charts.css";
import "./Dashboard.css";
import {
  getDashboardSummary,
  getMonthlyTrend,
  getCategoryWiseExpense
} from "../../services/dashboardService";


const currentDate = new Date();
const currentYear = currentDate.getFullYear();
const YEARS = Array.from({ length: 5 }, (_, i) => currentYear - i);
const COLORS = ["#4f46e5", "#ef4444", "#f59e0b", "#10b981", "#3b82f6", "#8b5cf6", "#ec4899"];

export default function Dashboard() {
  const [summary, setSummary] = useState(null);
  const [categoryData, setCategoryData] = useState([]);
  const [trendData, setTrendData] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState("");

  const [month, setMonth] = useState(currentDate.getMonth() + 1);
  const [year, setYear] = useState(currentYear);

  const user = JSON.parse(localStorage.getItem("user"));

  useEffect(() => {
    const load = async () => {
      setLoading(true);
      setError("");
      try {
        const [dashboardRes, categoryRes, trendRes] = await Promise.all([
          getDashboardSummary(user.id, month, year),
          getCategoryWiseExpense(user.id, month, year),
          getMonthlyTrend(user.id, year)
        ]);

        setSummary(dashboardRes.data);
        setCategoryData(categoryRes.data);
        setTrendData(trendRes.data);
      } catch (err) {
        console.error("Failed to load dashboard data", err);
        setError("Failed to load dashboard data. Please try again.");
      } finally {
        setLoading(false);
      }
    };
    load();
  }, [month, year]);

    const totalTrendAmount = trendData.reduce(
    (sum, item) => sum + item.totalAmount,
    0
  );

  return (
    <MainLayout>
      <div style={{ display: "flex", justifyContent: "space-between", alignItems: "center", marginBottom: 20 }}>
        <h2>Dashboard</h2>
        <div className="dashboard-filters">
          <select
            className="dashboard-filter-select"
            value={month}
            onChange={(e) => setMonth(Number(e.target.value))}
          >
            {[1,2,3,4,5,6,7,8,9,10,11,12].map(m => (
              <option key={m} value={m}>
                {new Date(0, m-1).toLocaleString("default",{month:"long"})}
              </option>
            ))}
          </select>
          <select
            className="dashboard-filter-select"
            value={year}
            onChange={(e) => setYear(Number(e.target.value))}
          >
            {YEARS.map((y) => (
              <option key={y} value={y}>
                {y}
              </option>
            ))}
          </select>
        </div>
      </div>

      {loading ? (
        <p>Loading dashboard...</p>
      ) : error ? (
        <p style={{ color: "#ef4444" }}>{error}</p>
      ) : (
        <>
          <DashboardCards summary={summary} />
          <div style={{ display: "grid", gridTemplateColumns: "1fr 1fr", gap: 20 }}>
            <div className="chart-card">
              <h3>Spending by Category</h3>
              <SpendingPieChart data={categoryData} />
              <div className="chart-legend-list">
                {categoryData.map((item, index) => (
                  <div className="chart-legend-item" key={item.categoryName}>
                    <span className="chart-legend-item-name">
                      <span
                        className="chart-legend-dot"
                        style={{ background: COLORS[index % COLORS.length] }}
                      />
                      {item.categoryName}
                    </span>
                    <span className="chart-legend-item-value">
                      {formatCurrency(item.totalAmount)}
                    </span>
                  </div>
                ))}
              </div>
            </div>

            <div className="chart-card">
              <h3>Monthly Trend</h3>
              <MonthlyTrendChart data={trendData} />
              <div className="chart-legend-list">
                {trendData.map((item) => (
                  <div className="chart-legend-item" key={item.month}>
                    <span className="chart-legend-item-name">
                      <span className="chart-legend-dot" style={{ background: "#4f46e5" }} />
                      {MONTHS.find((m) => m.value === item.month)?.label ?? item.month}
                    </span>
                    <span className="chart-legend-item-value">
                      {formatCurrency(item.totalAmount)}
                    </span>
                  </div>
                ))}
              </div>
              <div className="chart-total">
                <span>Total</span>
                <span>{formatCurrency(totalTrendAmount)}</span>
              </div>
            </div>
          </div>
        </>
      )}
    </MainLayout>
  );
}
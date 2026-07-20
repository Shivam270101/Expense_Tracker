import { useEffect, useState } from "react";
import MainLayout from "../../components/MainLayout";
import SpendingPieChart from "../../components/Charts/SpendingPieChart";
import MonthlyTrendChart from "../../components/Charts/MonthlyTrendChart";
import { getSpendingByCategory, getMonthlyTrend } from "../../services/dashboardService";

export default function Reports() {
  const [categoryData, setCategoryData] = useState([]);
  const [trendData, setTrendData] = useState([]);

  useEffect(() => {
    const load = async () => {
      const [catRes, trendRes] = await Promise.all([
        getSpendingByCategory(),
        getMonthlyTrend(),
      ]);
      setCategoryData(catRes.data);
      setTrendData(trendRes.data);
    };
    load();
  }, []);

  return (
    <MainLayout>
      <h2>Reports</h2>
      <div style={{ display: "grid", gridTemplateColumns: "1fr 1fr", gap: 20 }}>
        <div>
          <h3>Spending by Category</h3>
          <SpendingPieChart data={categoryData} />
        </div>
        <div>
          <h3>Monthly Trend</h3>
          <MonthlyTrendChart data={trendData} />
        </div>
      </div>
    </MainLayout>
  );
}

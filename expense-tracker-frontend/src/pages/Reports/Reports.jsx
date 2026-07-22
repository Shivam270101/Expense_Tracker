import { useEffect, useState } from "react";
import MainLayout from "../../components/MainLayout";
import { PAYMENT_MODES } from "../../utils/constants";
import { generateReport } from "../../services/reportService";
import { getCategories } from "../../services/categoryService";
import "./Reports.css";

export default function Reports() {

    const today = new Date().toISOString().split("T")[0];

    const [filters, setFilters] = useState({
        fromDate: today,
        toDate: today,
        categoryId: "",
        paymentMode: ""
    });

    const [categories, setCategories] = useState([]);

    const [report, setReport] = useState(null);

    const [loading, setLoading] = useState(false);

    useEffect(() => {
        const loadCategories = async () => {
            try {
                const { data } = await getCategories();
                setCategories(data);
            } catch (error) {
                console.error("Failed to load categories", error);
            }
        };
        loadCategories();
    }, []);

    const handleChange = (e) => {

        setFilters({
            ...filters,
            [e.target.name]: e.target.value
        });

    };

    const handleGenerateReport = async () => {

        try {

            setLoading(true);

            const payload = {
                fromDate: filters.fromDate,
                toDate: filters.toDate,
                categoryId: filters.categoryId || null,
                paymentMode: filters.paymentMode || null
            };

            const response = await generateReport(payload);

            setReport(response);

        } catch (error) {

            console.error(error);

            alert("Failed to generate report");

        } finally {

            setLoading(false);

        }

    };

    return (

        <MainLayout>

            <div className="reports-page">

                <h2>Expense Reports</h2>

                <div className="report-filters">

                    <div>

                        <label>From Date</label>

                        <input
                            type="date"
                            name="fromDate"
                            value={filters.fromDate}
                            onChange={handleChange}
                        />

                    </div>

                    <div>

                        <label>To Date</label>

                        <input
                            type="date"
                            name="toDate"
                            value={filters.toDate}
                            onChange={handleChange}
                        />

                    </div>

                    <div>

                        <label>Category</label>

                        <select
                            name="categoryId"
                            value={filters.categoryId}
                            onChange={handleChange}
                        >

                            <option value="">All</option>
                            {categories.map((cat) => (
                                <option key={cat.id} value={cat.id}>
                                    {cat.name}
                                </option>
                            ))}

                        </select>

                    </div>

                    <div>

                        <label>Payment mode</label>


                        <select
                            name="paymentMode"
                            value={filters.paymentMode}
                            onChange={handleChange}
                        >
                            <option value="">All Payment Modes</option>

                            {PAYMENT_MODES.map((mode) => (
                                <option key={mode.value} value={mode.value}>
                                    {mode.label}
                                </option>
                            ))}
                        </select>

                    </div>

                    <button
                        className="generate-btn"
                        onClick={handleGenerateReport}
                        disabled={loading}
                    >
                        {loading ? "Generating..." : "Generate Report"}
                    </button>

                </div>

                {
                    !report && !loading && (
                        <div className="no-report">
                            <div className="no-report-icon">📊</div>
                            <p>Select your filters and click "Generate Report" to see results.</p>
                        </div>
                    )
                }

                {
                    report && (

                        <>

                            <div className="report-summary">

                                <div className="summary-card">
                                    <h4>Total Budget</h4>
                                    <p>₹ {report.totalBudget}</p>
                                </div>

                                <div className="summary-card">
                                    <h4>Total Expense</h4>
                                    <p>₹ {report.totalExpense}</p>
                                </div>

                                <div className="summary-card">
                                    <h4>Remaining Budget</h4>
                                    <p>₹ {report.remainingBudget}</p>
                                </div>

                                <div className="summary-card">
                                    <h4>Transactions</h4>
                                    <p>{report.totalTransactions}</p>
                                </div>

                            </div>

                            <table className="report-table">

                                <thead>

                                    <tr>

                                        <th>Date</th>
                                        <th>Title</th>
                                        <th>Category</th>
                                        <th>Payment</th>
                                        <th>Amount</th>

                                    </tr>

                                </thead>

                                <tbody>

                                    {
                                        report.expenses.map(expense => (

                                            <tr key={expense.expenseId}>

                                                <td>{expense.expenseDate}</td>

                                                <td>{expense.title}</td>

                                                <td>{expense.categoryName}</td>

                                                <td>{expense.paymentMode}</td>

                                                <td>₹ {expense.amount}</td>

                                            </tr>

                                        ))
                                    }

                                </tbody>

                            </table>

                        </>

                    )
                }

            </div>

        </MainLayout>

    );

}
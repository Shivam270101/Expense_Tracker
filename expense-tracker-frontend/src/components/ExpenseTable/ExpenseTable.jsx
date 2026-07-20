import { formatCurrency, formatDate } from "../../utils/formatters";
import "./ExpenseTable.css";

const PAYMENT_MODE_COLORS = {
  CASH: "#10b981",
  CARD: "#4f46e5",
  UPI: "#f59e0b",
  NET_BANKING: "#3b82f6",
  OTHER: "#6b7280",
};

export default function ExpenseTable({ expenses = [], onEdit, onDelete }) {
  if (!expenses.length) {
    return <p className="expense-table-empty">No expenses recorded yet.</p>;
  }

  return (
    <div className="expense-table-wrapper">
      <table className="expense-table">
        <thead>
          <tr>
            <th>Title</th>
            <th>Category</th>
            <th>Amount</th>
            <th>Payment Mode</th>
            <th>Date</th>
            <th>Actions</th>
          </tr>
        </thead>
        <tbody>
          {expenses.map((expense) => (
            <tr key={expense.id}>
              <td className="expense-table-title">{expense.title}</td>
              <td>{expense.categoryName}</td>
              <td className="expense-table-amount">{formatCurrency(expense.amount)}</td>
              <td>
                <span
                  className="expense-table-badge"
                  style={{
                    background: `${PAYMENT_MODE_COLORS[expense.paymentMode] || "#6b7280"}1a`,
                    color: PAYMENT_MODE_COLORS[expense.paymentMode] || "#6b7280",
                  }}
                >
                  {expense.paymentMode}
                </span>
              </td>
              <td className="expense-table-date">{formatDate(expense.expenseDate)}</td>
              <td>
                <button onClick={() => onEdit?.(expense)}>Edit</button>
                <button onClick={() => onDelete?.(expense.id)} className="danger">
                  Delete
                </button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}
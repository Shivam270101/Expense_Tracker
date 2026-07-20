import { formatCurrency } from "../../utils/formatters";
import { MONTHS } from "../../utils/constants";
import "./BudgetCard.css";

export default function BudgetCard({ budget, onEdit, onDelete }) {
  const monthLabel = MONTHS.find((m) => m.value === budget.month)?.label ?? budget.month;
  const spent = budget.spentAmount ?? 0;
  const remaining = budget.remainingAmount ?? budget.budgetAmount - spent;
  const percentUsed = Math.min(100, Math.round((spent / budget.budgetAmount) * 100) || 0);

  const getStatusColor = () => {
    if (percentUsed >= 90) return "#ef4444";
    if (percentUsed >= 70) return "#f59e0b";
    return "#10b981";
  };

  return (
    <div className="budget-card">
      <div className="budget-card-header">
        <h4>
          {monthLabel} {budget.year}
        </h4>
        <div className="budget-card-actions">
          <button onClick={() => onEdit?.(budget)}>Edit</button>
          <button className="danger" onClick={() => onDelete?.(budget.id)}>
            Delete
          </button>
        </div>
      </div>

      <div className="budget-card-bar">
        <div
          className="budget-card-bar-fill"
          style={{ width: `${percentUsed}%`, background: getStatusColor() }}
        />
      </div>
      <div className="budget-card-percent" style={{ color: getStatusColor() }}>
        {percentUsed}% used
      </div>

      <div className="budget-card-stats">
        <div className="budget-card-stat">
          <span className="budget-card-stat-label">Budget</span>
          <span className="budget-card-stat-value">{formatCurrency(budget.budgetAmount)}</span>
        </div>
        <div className="budget-card-stat">
          <span className="budget-card-stat-label">Spent</span>
          <span className="budget-card-stat-value">{formatCurrency(spent)}</span>
        </div>
        <div className="budget-card-stat">
          <span className="budget-card-stat-label">Remaining</span>
          <span className="budget-card-stat-value">{formatCurrency(remaining)}</span>
        </div>
      </div>
    </div>
  );
}
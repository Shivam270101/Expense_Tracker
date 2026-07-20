import { formatCurrency } from "../../utils/formatters";
import "./DashboardCards.css";

export default function DashboardCards({ summary }) {
  const cards = [
    {
      label: "Total Spent (This Month)",
      value: summary?.totalSpent ?? 0,
      icon: "💸",
      color: "#ef4444",
    },
    {
      label: "Total Budget",
      value: summary?.totalBudget ?? 0,
      icon: "🎯",
      color: "#4f46e5",
    },
    {
      label: "Remaining Budget",
      value: summary?.remainingBudget ?? 0,
      icon: "💰",
      color: "#10b981",
    },
    {
      label: "Total Categories",
      value: summary?.totalCategories ?? 0,
      icon: "🗂️",
      color: "#f59e0b",
      isCount: true,
    },
  ];

  return (
    <div className="dashboard-cards">
      {cards.map((card) => (
        <div
          className="dashboard-card"
          key={card.label}
          style={{ "--accent-color": card.color }}
        >
          <div className="dashboard-card-icon" style={{ background: card.color }}>
            {card.icon}
          </div>
          <div className="dashboard-card-label">{card.label}</div>
          <div className="dashboard-card-value">
            {card.isCount ? card.value : formatCurrency(card.value)}
          </div>
        </div>
      ))}
    </div>
  );
}
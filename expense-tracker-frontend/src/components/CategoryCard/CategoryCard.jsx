import "./CategoryCard.css";

const COLORS = ["#4f46e5", "#ef4444", "#f59e0b", "#10b981", "#3b82f6", "#8b5cf6", "#ec4899"];

function getColorForName(name) {
  const charCode = name?.charCodeAt(0) || 0;
  return COLORS[charCode % COLORS.length];
}

export default function CategoryCard({ category, onEdit, onDelete }) {
  return (
    <div className="category-card">
      <div className="category-card-info">
        <span
          className="category-card-icon"
          style={{ background: getColorForName(category.name) }}
        >
          {category.name?.charAt(0).toUpperCase()}
        </span>
        <div>
          <h4>{category.name}</h4>
          <p>{category.description || "No description"}</p>
        </div>
      </div>
      <div className="category-card-actions">
        <button onClick={() => onEdit?.(category)}>Edit</button>
        <button className="danger" onClick={() => onDelete?.(category.id)}>
          Delete
        </button>
      </div>
    </div>
  );
}
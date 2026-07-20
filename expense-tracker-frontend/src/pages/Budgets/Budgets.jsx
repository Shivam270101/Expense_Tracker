import { useEffect, useState } from "react";
import MainLayout from "../../components/MainLayout";
import BudgetCard from "../../components/BudgetCard/BudgetCard";
import BudgetFormInline from "./BudgetFormInline";
import useAuth from "../../hooks/useAuth";
import {
  getBudgets,
  createBudget,
  updateBudget,
  deleteBudget,
} from "../../services/budgetService";

export default function Budgets() {
  const [budgets, setBudgets] = useState([]);
  const [editing, setEditing] = useState(null);
  const [showForm, setShowForm] = useState(false);
  const { user } = useAuth();

  const loadData = async () => {
    if (!user?.id) return;

    const { data } = await getBudgets(user.id);
    setBudgets(data);
  };

  useEffect(() => {
    if (user?.id) {
    loadData();
  }
  }, [user]);

  const handleSubmit = async (data) => {
  const user = JSON.parse(localStorage.getItem("user"));

  const budgetData = {
    ...data,
    userId: user.id,
  };

  if (editing) {
    await updateBudget(editing.id, budgetData);
  } else {
    await createBudget(budgetData);
  }

  await loadData();

  setShowForm(false);
  setEditing(null);
};

  const handleDelete = async (id) => {
    if (!confirm("Delete this budget?")) return;
    await deleteBudget(id);
    loadData();
  };

  return (
    <MainLayout>
      <div style={{ display: "flex", justifyContent: "space-between", marginBottom: 16 }}>
        <h2>Budgets</h2>
        <button
          onClick={() => {
            setEditing(null);
            setShowForm(true);
          }}
        >
          + Add Budget
        </button>
      </div>

      {showForm && (
        <BudgetFormInline
          initialData={editing}
          onSubmit={handleSubmit}
          onCancel={() => {
            setShowForm(false);
            setEditing(null);
          }}
        />
      )}

      {!budgets.length ? (
        <p className="budget-empty">No budgets recorded yet.</p>
      ) : (
        budgets.map((budget) => (
          <BudgetCard
            key={budget.id}
            budget={budget}
            onEdit={(b) => {
              setEditing(b);
              setShowForm(true);
            }}
            onDelete={handleDelete}
          />
        ))
      )}
    </MainLayout>
  );
}
import { useEffect, useState } from "react";
import MainLayout from "../../components/MainLayout";
import ExpenseTable from "../../components/ExpenseTable/ExpenseTable";
import ExpenseForm from "../../components/ExpenseForm/ExpenseForm";
import {
  getExpenses,
  createExpense,
  updateExpense,
  deleteExpense,
} from "../../services/expenseService";
import { getCategories } from "../../services/categoryService";
import useAuth from "../../hooks/useAuth";

export default function Expenses() {
  const [expenses, setExpenses] = useState([]);
  const [categories, setCategories] = useState([]);
  const [editing, setEditing] = useState(null);
  const [showForm, setShowForm] = useState(false);
  const { user } = useAuth();

  const loadData = async () => {

  if (!user?.id) return;

  const [expRes, catRes] = await Promise.all([
    getExpenses(user.id),
    getCategories(user.id),
  ]);

  setExpenses(expRes.data);
  setCategories(catRes.data);
};

  useEffect(() => {
    if (user?.id) {
      loadData();
    }
  }, [user]);

  const handleSubmit = async (data) => {

    // const user = JSON.parse(localStorage.getItem("user"));

    const expenseData = {
      ...data,
      userId: user.id,
    };

      if (editing) {
      await updateExpense(editing.id, expenseData);
    } else {
      await createExpense(expenseData);
    }

    await loadData();

    setShowForm(false);
    setEditing(null);
  };

  const handleDelete = async (id) => {
    if (!confirm("Delete this expense?")) return;
    await deleteExpense(id);
    loadData();
  };

  return (
    <MainLayout>
      <div style={{ display: "flex", justifyContent: "space-between", marginBottom: 16 }}>
        <h2>Expenses</h2>
        <button
          className="add-btn"
          onClick={() => {
            setEditing(null);
            setShowForm(true);
          }}
        >
          + Add Expense
        </button>
      </div>

      {showForm && (
        <ExpenseForm
          categories={categories}
          initialData={editing}
          onSubmit={handleSubmit}
          onCancel={() => {
            setShowForm(false);
            setEditing(null);
          }}
        />
      )}

      <div style={{ marginTop: 20, borderRadius: 10 }}>
        <ExpenseTable
          expenses={expenses}
          onEdit={(exp) => {
            setEditing(exp);
            setShowForm(true);
          }}
          onDelete={handleDelete}
        />
      </div>
    </MainLayout>
  );
}

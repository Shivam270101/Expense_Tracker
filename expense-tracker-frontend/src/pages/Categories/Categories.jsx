import { useEffect, useState } from "react";
import MainLayout from "../../components/MainLayout";
import CategoryCard from "../../components/CategoryCard/CategoryCard";
import CategoryFormInline from "./CategoryFormInline";
import {
  getCategories,
  createCategory,
  updateCategory,
  deleteCategory,
} from "../../services/categoryService";
import useAuth from "../../hooks/useAuth";

export default function Categories() {
  const [categories, setCategories] = useState([]);
  const [editing, setEditing] = useState(null);
  const [showForm, setShowForm] = useState(false);
  const { user } = useAuth();

  const loadData = async () => {
    if (!user?.id) return;

    const { data } = await getCategories(user.id);
    setCategories(data);
  };

  useEffect(() => {
     if (user?.id) {
    loadData();
  }
  }, [user]);

const handleSubmit = async (data) => {
  const user = JSON.parse(localStorage.getItem("user"));

  const categoryData = {
    ...data,
    userId: user.id,
  };

  if (editing) {
    await updateCategory(editing.id, categoryData);
  } else {
    await createCategory(categoryData);
  }

  setShowForm(false);
  setEditing(null);
  loadData();
};

  const handleDelete = async (id) => {
    if (!confirm("Delete this category?")) return;
    await deleteCategory(id);
    loadData();
  };

  return (
    <MainLayout>
      <div style={{ display: "flex", justifyContent: "space-between", marginBottom: 16 }}>
        <h2>Categories</h2>
        <button
          className="add-btn"
          onClick={() => {
            setEditing(null);
            setShowForm(true);
          }}
        >
          + Add Category
        </button>
      </div>

      {showForm && (
        <CategoryFormInline
          initialData={editing}
          onSubmit={handleSubmit}
          onCancel={() => {
            setShowForm(false);
            setEditing(null);
          }}
        />
      )}

      {!categories.length ? (
        <p className="category-empty">No categories recorded yet.</p>
      ) : (
        categories.map((cat) => (
          <CategoryCard
            key={cat.id}
            category={cat}
            onEdit={(c) => {
              setEditing(c);
              setShowForm(true);
            }}
            onDelete={handleDelete}
          />
        ))
      )}
    </MainLayout>
  );
}
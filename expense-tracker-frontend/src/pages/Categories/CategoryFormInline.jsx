import { useState, useEffect } from "react";
import "./CategoryFormInline.css";

const empty = { name: "", description: "" };

export default function CategoryFormInline({ initialData, onSubmit, onCancel }) {
  const [form, setForm] = useState(initialData || empty);

  useEffect(() => {
    if (initialData) setForm(initialData);
  }, [initialData]);

  const handleChange = (e) => setForm((f) => ({ ...f, [e.target.name]: e.target.value }));

  const handleSubmit = (e) => {
    e.preventDefault();
    onSubmit(form);
  };

  return (
    <form className="category-form" onSubmit={handleSubmit}>
      <h3 className="category-form-title">
        {initialData ? "Edit Category" : "Add Category"}
      </h3>

      <label>
        Category Name
        <input name="name" value={form.name} onChange={handleChange} required />
      </label>

      <label>
        Description
        <textarea name="description" value={form.description} onChange={handleChange} rows={3} />
      </label>

      <div className="category-form-actions">
        <button type="submit">Save</button>
        {onCancel && (
          <button type="button" onClick={onCancel}>
            Cancel
          </button>
        )}
      </div>
    </form>
  );
}
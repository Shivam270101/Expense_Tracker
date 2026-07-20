import { useState, useEffect } from "react";
import { PAYMENT_MODES } from "../../utils/constants";
import "./ExpenseForm.css";

const emptyForm = {
  title: "",
  amount: "",
  description: "",
  expenseDate: "",
  paymentMode: PAYMENT_MODES[0].value,
  categoryId: "",
};

export default function ExpenseForm({ categories = [], initialData, onSubmit, onCancel }) {
  const [form, setForm] = useState(initialData || emptyForm);

  useEffect(() => {
    if (initialData) setForm(initialData);
  }, [initialData]);

  const handleChange = (e) => {
    const { name, value } = e.target;
    setForm((prev) => ({ ...prev, [name]: value }));
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    onSubmit?.({
      ...form,
      amount: parseFloat(form.amount),
      categoryId: form.categoryId ? Number(form.categoryId) : null,
    });
  };

  return (
    <form className="expense-form" onSubmit={handleSubmit}>
      <h3 className="expense-form-title">
        {initialData ? "Edit Expense" : "Add Expense"}
      </h3>

      <label>
        Expense Title
        <input name="title" value={form.title} onChange={handleChange} required />
      </label>

      <div className="expense-form-row">
        <label>
          Amount
          <input
            type="number"
            step="0.01"
            name="amount"
            value={form.amount}
            onChange={handleChange}
            required
          />
        </label>

        <label>
          Expense Date
          <input
            type="date"
            name="expenseDate"
            value={form.expenseDate}
            onChange={handleChange}
            required
          />
        </label>
      </div>

      <label>
        Description
        <textarea
          name="description"
          value={form.description}
          onChange={handleChange}
          rows={3}
        />
      </label>

      <div className="expense-form-row">
        <label>
          Payment Mode
          <select name="paymentMode" value={form.paymentMode} onChange={handleChange}>
            {PAYMENT_MODES.map((mode) => (
              <option key={mode.value} value={mode.value}>
                {mode.label}
              </option>
            ))}
          </select>
        </label>

        <label>
          Category
          <select name="categoryId" value={form.categoryId} onChange={handleChange} required>
            <option value="">Select category</option>
            {categories.map((cat) => (
              <option key={cat.id} value={cat.id}>
                {cat.name}
              </option>
            ))}
          </select>
        </label>
      </div>

      <div className="expense-form-actions">
        <button type="submit">Save</button>
        {onCancel && (
          <button type="button" className="secondary" onClick={onCancel}>
            Cancel
          </button>
        )}
      </div>
    </form>
  );
}
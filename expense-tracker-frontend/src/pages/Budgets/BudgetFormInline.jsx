import { useState, useEffect } from "react";
import { MONTHS } from "../../utils/constants";
import "./BudgetFormInline.css";

const currentYear = new Date().getFullYear();
const empty = { month: MONTHS[0].value, year: currentYear, budgetAmount: "" };

export default function BudgetFormInline({ initialData, onSubmit, onCancel }) {
  const [form, setForm] = useState(initialData || empty);

  useEffect(() => {
    if (initialData) {
      setForm(initialData);
    } else {
      setForm(empty);
    }
  }, [initialData]);

  const handleChange = (e) => {
    setForm((prev) => ({
      ...prev,
      [e.target.name]: e.target.value,
    }));
  };

  const handleFormSubmit = (e) => {
    e.preventDefault();
    onSubmit(form);
  };

  return (
    <form className="budget-form" onSubmit={handleFormSubmit}>
      <h3 className="budget-form-title">
        {initialData ? "Edit Budget" : "Add Budget"}
      </h3>

      <label>
        Month
        <select name="month" value={form.month} onChange={handleChange}>
          {MONTHS.map((m) => (
            <option key={m.value} value={m.value}>
              {m.label}
            </option>
          ))}
        </select>
      </label>

      <label>
        Year
        <input type="number" name="year" value={form.year} onChange={handleChange} required />
      </label>

      <label>
        Budget Amount
        <input
          type="number"
          step="0.01"
          name="budgetAmount"
          value={form.budgetAmount}
          onChange={handleChange}
          required
        />
      </label>

      <div className="budget-form-actions">
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
import api from "./api";

// Matches ExpenseRequestDTO:
// { title, amount, description, expenseDate, paymentMode, categoryId }
export const getExpenses = (userId) => api.get(`/expenses/user/${userId}`);
export const getExpenseById = (id) => api.get(`/expenses/${id}`);
export const createExpense = (data) => api.post("/expenses", data);
export const updateExpense = (id, data) => api.put(`/expenses/${id}`, data);
export const deleteExpense = (id) => api.delete(`/expenses/${id}`);

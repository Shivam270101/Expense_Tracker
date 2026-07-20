import api from "./api";

// Matches BudgetRequestDTO: { month, year, budgetAmount }
// spentAmount / remainingAmount are typically computed server-side
// export const getBudgets = (params) => api.get("/budgets", { params });
export const getBudgets = (userId) => api.get(`/budgets/user/${userId}`);
export const getBudgetById = (id) => api.get(`/budgets/${id}`);
export const createBudget = (data) => api.post("/budgets", data);
export const updateBudget = (id, data) => api.put(`/budgets/${id}`, data);
export const deleteBudget = (id) => api.delete(`/budgets/${id}`);

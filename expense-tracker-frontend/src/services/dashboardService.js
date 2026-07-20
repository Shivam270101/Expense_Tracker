import api from "./api";

export const getDashboardSummary = (userId, month, year) =>
    api.get(`/dashboard/${userId}`, {
        params: { month, year }
    });

export const getSpendingByCategory = (userId) =>
  api.get(`/dashboard/category/${userId}`);

export const getMonthlyTrend = (userId, year) =>
  api.get(`/dashboard/monthly/${userId}`,{
    params: {year}
  });

export const getDashboard = (userId) =>
  api.get(`/dashboard/${userId}`);

export const getCategoryWiseExpense = (userId, month, year) =>
    api.get(`/dashboard/category/${userId}`, {
        params: { month, year }
    });

export const getMonthlyExpense = (userId, month, year) =>
  api.get(`/dashboard/monthly-expense/${userId}`, {
    params: { month, year },
  });
import api from "./api";

// Matches CategoryRequestDTO: { name, description }
export const getCategories = (userId) =>
    api.get(`/category/user/${userId}`);
export const getCategoryById = (id) => api.get(`/category/${id}`);
export const createCategory = (data) => api.post("/category", data);
export const updateCategory = (id, data) => api.put(`/category/${id}`, data);
export const deleteCategory = (id) => api.delete(`/category/${id}`);

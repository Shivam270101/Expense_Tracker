import api from "./api";

// Matches RegisterRequestDTO: { fullName, email, password }
export const register = (data) => api.post("/auth/register", data);

// Matches LoginRequestDTO: { email, password }
export const login = (data) => api.post("/auth/login", data);

export const logout = () => {
  localStorage.removeItem("token");
  localStorage.removeItem("user");
};

export const getCurrentUser = () => {
  const user = localStorage.getItem("user");
  return user ? JSON.parse(user) : null;
};

export const isAuthenticated = () => !!localStorage.getItem("token");

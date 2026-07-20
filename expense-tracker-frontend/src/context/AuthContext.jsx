import { createContext, useState, useEffect } from "react";
import * as authService from "../services/authService";

export const AuthContext = createContext(null);

export function AuthProvider({ children }) {
  const [user, setUser] = useState(authService.getCurrentUser());
  const [loading, setLoading] = useState(false);

  useEffect(() => {
    setUser(authService.getCurrentUser());
  }, []);

  const login = async (credentials) => {
  setLoading(true);
  try {
    const { data } = await authService.login(credentials);

    localStorage.setItem("token", data.token);
    localStorage.setItem("user", JSON.stringify(data));

    setUser(data);

    return data;
  } finally {
    setLoading(false);
  }
};
  const register = async (payload) => {
    setLoading(true);
    try {
      const { data } = await authService.register(payload);
      return data;
    } finally {
      setLoading(false);
    }
  };

  const logout = () => {
    authService.logout();
    setUser(null);
  };

  return (
    <AuthContext.Provider
      value={{ user, loading, login, register, logout, isAuthenticated: !!user }}
    >
      {children}
    </AuthContext.Provider>
  );
}

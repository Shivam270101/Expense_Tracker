import { NavLink, useNavigate } from "react-router-dom";
import useAuth from "../../hooks/useAuth";
import "./Sidebar.css";

const links = [
  { to: "/dashboard", label: "Dashboard" },
  { to: "/expenses", label: "Expenses" },
  { to: "/categories", label: "Categories" },
  { to: "/budgets", label: "Budgets" },
  { to: "/reports", label: "Reports" },
  { to: "/ai-assistant", label: "FinMate AI 🤖" },
  { to: "/profile", label: "Profile" },
];

export default function Sidebar({ isOpen, onClose }) {
  const { logout } = useAuth();
  const navigate = useNavigate();

  const handleLogout = () => {
    logout();
    navigate("/home");
  };

  return (
    <>
      {isOpen && <div className="sidebar-overlay" onClick={onClose} />}
      <aside className={`sidebar ${isOpen ? "open" : ""}`}>
        <nav className="sidebar-nav">
          {links.map((link) => (
            <NavLink
              key={link.to}
              to={link.to}
              onClick={onClose}
              className={({ isActive }) => "sidebar-link" + (isActive ? " active" : "")}
            >
              {link.label}
            </NavLink>
          ))}
        </nav>

        <button className="sidebar-logout" onClick={handleLogout}>
          Log out
        </button>
      </aside>
    </>
  );
}
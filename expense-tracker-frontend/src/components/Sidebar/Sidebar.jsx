import { NavLink } from "react-router-dom";
import useAuth from "../../hooks/useAuth";
import "./Sidebar.css";
import { useNavigate } from "react-router-dom";

const links = [
  { to: "/dashboard", label: "Dashboard" },
  { to: "/categories", label: "Categories" },
  { to: "/expenses", label: "Expenses" },  
  { to: "/budgets", label: "Budgets" },
  { to: "/reports", label: "Reports" },
  { to: "/ai-assistant", label: "AI Assistant" },
  { to: "/profile", label: "Profile" },
];

export default function Sidebar() {
  const { logout } = useAuth();
  const navigate = useNavigate();

  const handleLogout = () => {
    logout();
    navigate("/login");
  };

  return (
    <aside className="sidebar">
      <nav>
        {links.map((link) => (
          <NavLink
            key={link.to}
            to={link.to}
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
  );
}

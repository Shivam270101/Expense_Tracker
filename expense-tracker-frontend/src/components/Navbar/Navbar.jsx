import { useNavigate } from "react-router-dom";
import useAuth from "../../hooks/useAuth";
import "./Navbar.css";

export default function Navbar() {
  const { user, logout } = useAuth();
  const navigate = useNavigate();

  const handleLogout = () => {
    logout();
    navigate("/login");
  };

  return (
    <header className="navbar">
      <div className="navbar-brand">Expense Tracker</div>
      <div className="navbar-actions">
        {user && (
          <>
            <span className="navbar-user">
              {user.fullName || user.email}
            </span>

            <button
              className="navbar-logout"
              onClick={handleLogout}
            >
              Log out
            </button>
          </>
        )}
      </div>
    </header>
  );
}

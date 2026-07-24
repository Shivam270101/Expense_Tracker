import { useNavigate } from "react-router-dom";
import useAuth from "../../hooks/useAuth";
import "./Navbar.css";

export default function Navbar({ onMenuClick }) {
  const { user, logout } = useAuth();
  const navigate = useNavigate();

  const handleLogout = () => {
    logout();
    navigate("/home");
  };

  const displayName = user?.fullName || user?.email || "";
  const initial = displayName.charAt(0).toUpperCase();

  return (
    <header className="navbar">
      <div className="navbar-brand">
        <img src="src\assets\fintrack_logo.png" alt="" className="home-logo-mark" />
        <span className="navbar-brand-name">FinTrack AI</span>
      </div>

      <div className="navbar-actions">
        {user && (
          <>
            <div className="navbar-user">
              <span className="navbar-user-avatar">{initial}</span>
              <span className="navbar-user-name">{displayName}</span>
            </div>
            <button className="navbar-logout" onClick={handleLogout}>
              Log out
            </button>
          </>
        )}
        <button
          type="button"
          className="navbar-menu-btn"
          onClick={onMenuClick}
          aria-label="Toggle menu"
        >
          ☰
        </button>
      </div>
    </header>
  );
}
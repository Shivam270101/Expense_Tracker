import Navbar from "./Navbar/Navbar";
import Sidebar from "./Sidebar/Sidebar";

export default function MainLayout({ children }) {
  return (
    <div>
      <Navbar />
      <div style={{ display: "flex" }}>
        <Sidebar />
        <main style={{ flex: 1, padding: "24px", background: "#f9fafb", minHeight: "calc(100vh - 60px)" }}>
          {children}
        </main>
      </div>
    </div>
  );
}

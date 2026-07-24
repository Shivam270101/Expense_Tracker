import { useState } from "react";
import Navbar from "./Navbar/Navbar";
import Sidebar from "./Sidebar/Sidebar";

export default function MainLayout({ children, fullBleed = false }) {
  const [sidebarOpen, setSidebarOpen] = useState(false);

  return (
    <div>
      <Navbar onMenuClick={() => setSidebarOpen((prev) => !prev)} />
      <div style={{ display: "flex" }}>
        <Sidebar isOpen={sidebarOpen} onClose={() => setSidebarOpen(false)} />
        <main
          style={{
            flex: 1,
            padding: fullBleed ? 0 : "24px",
            background: fullBleed ? "transparent" : "#f9fafb",
            height: "calc(100vh - 64px)",
            overflow: fullBleed ? "hidden" : "auto",
            minWidth: 0,
          }}
        >
          {children}
        </main>
      </div>
    </div>
  );
}
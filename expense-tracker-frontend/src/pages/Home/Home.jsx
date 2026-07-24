import { Link } from "react-router-dom";
import { useEffect, useRef, useState } from "react";
import "./Home.css";

const WHY_ITEMS = [
  { icon: "💰", title: "Track every expense", desc: "Log spending the moment it happens, sorted by category and payment mode." },
  { icon: "🎯", title: "Set monthly budgets", desc: "Cap what you spend per month and see exactly how close you are to the line." },
  { icon: "🤖", title: "AI Financial Assistant", desc: "Ask plain-language questions about your money and get real answers." },
  { icon: "📊", title: "Interactive dashboard", desc: "Spending by category and monthly trends, updated as you go." },
  { icon: "📈", title: "Reports & analytics", desc: "Filter by date, category, or payment mode to see the full picture." },
  { icon: "🔒", title: "Secure JWT authentication", desc: "Your financial data stays behind a token only you hold." },
];

const STEPS = [
  { title: "Create account", desc: "Register with your name, email, and a password." },
  { title: "Add categories", desc: "Set up the categories your spending actually falls into." },
  { title: "Record expenses", desc: "Log each expense with an amount, date, and payment mode." },
  { title: "Set monthly budget", desc: "Decide what you're willing to spend this month." },
  { title: "View dashboard", desc: "Watch your spending and budget update in real time." },
  { title: "Generate reports", desc: "Pull a filtered report and export it when you need it." },
];

const FEATURES = [
  "Expense management", "Budget planner", "Category management", "Dashboard charts",
  "Monthly trend analysis", "AI chat assistant", "Report generation",
  "PDF export", "Excel export", "JWT authentication",
];

const STACK = [
  { label: "Frontend", items: ["React", "Vite", "Axios", "CSS"] },
  { label: "Backend", items: ["Spring Boot", "Spring Security", "JWT", "Spring Data JPA"] },
  { label: "Database", items: ["MySQL"] },
  { label: "AI", items: ["Spring AI", "Ollama"] },
];

// Project-scope stats only — no fabricated usage numbers on a portfolio piece.
const STATS = [
  { value: 6, suffix: "", label: "Core modules" },
  { value: 10, suffix: "+", label: "Features shipped" },
  { value: 9, suffix: "", label: "Technologies used" },
  { value: 100, suffix: "%", label: "JWT-secured routes" },
];

/* ---------- scroll-reveal hook + wrapper ---------- */

function useInView(threshold = 0.2) {
  const ref = useRef(null);
  const [inView, setInView] = useState(false);

  useEffect(() => {
    const el = ref.current;
    if (!el) return;
    const observer = new IntersectionObserver(
      ([entry]) => {
        if (entry.isIntersecting) {
          setInView(true);
          observer.unobserve(el);
        }
      },
      { threshold }
    );
    observer.observe(el);
    return () => observer.disconnect();
  }, [threshold]);

  return [ref, inView];
}

function Reveal({ children, className = "", delay = 0 }) {
  const [ref, inView] = useInView();
  return (
    <div
      ref={ref}
      className={`reveal ${inView ? "in-view" : ""} ${className}`}
      style={{ transitionDelay: `${delay}ms` }}
    >
      {children}
    </div>
  );
}

/* ---------- animated counter ---------- */

function useCountUp(target, duration, start) {
  const [value, setValue] = useState(0);

  useEffect(() => {
    if (!start) return;
    let startTime = null;
    let raf;
    const step = (ts) => {
      if (!startTime) startTime = ts;
      const progress = Math.min((ts - startTime) / duration, 1);
      setValue(Math.floor(progress * target));
      if (progress < 1) raf = requestAnimationFrame(step);
    };
    raf = requestAnimationFrame(step);
    return () => cancelAnimationFrame(raf);
  }, [start, target, duration]);

  return value;
}

function Stat({ value, suffix, label }) {
  const [ref, inView] = useInView(0.5);
  const count = useCountUp(value, 1400, inView);
  return (
    <div className="stat" ref={ref}>
      <span className="stat-value">{count}{suffix}</span>
      <span className="stat-label">{label}</span>
    </div>
  );
}

export default function Home() {
  const [scrolled, setScrolled] = useState(false);
  const [mockTab, setMockTab] = useState("category");
  const cursorRef = useRef(null);
  const pos = useRef({ x: 0, y: 0 });
  const target = useRef({ x: 0, y: 0 });

  useEffect(() => {
    const onScroll = () => setScrolled(window.scrollY > 12);
    window.addEventListener("scroll", onScroll);
    return () => window.removeEventListener("scroll", onScroll);
  }, []);

   // Cursor-follow glow — only on devices with a real mouse, and only if
  // the person hasn't asked for reduced motion.
  useEffect(() => {
    const hasFinePointer = window.matchMedia("(pointer: fine)").matches;
    const reducedMotion = window.matchMedia("(prefers-reduced-motion: reduce)").matches;
    if (!hasFinePointer || reducedMotion || !cursorRef.current) return;

    const handleMove = (e) => {
      target.current = { x: e.clientX, y: e.clientY };
    };
    window.addEventListener("mousemove", handleMove);

    let raf;
    const animate = () => {
      // Lerp toward the real cursor position for a soft trailing feel.
      pos.current.x += (target.current.x - pos.current.x) * 0.12;
      pos.current.y += (target.current.y - pos.current.y) * 0.12;
      if (cursorRef.current) {
        cursorRef.current.style.transform =
          `translate3d(${pos.current.x}px, ${pos.current.y}px, 0) translate(-50%, -50%)`;
      }
      raf = requestAnimationFrame(animate);
    };
    raf = requestAnimationFrame(animate);

    return () => {
      window.removeEventListener("mousemove", handleMove);
      cancelAnimationFrame(raf);
    };
  }, []);

  const handleTilt = (e) => {
    const card = e.currentTarget;
    const rect = card.getBoundingClientRect();
    const x = e.clientX - rect.left;
    const y = e.clientY - rect.top;
    const rotateX = ((y - rect.height / 2) / rect.height) * -8;
    const rotateY = ((x - rect.width / 2) / rect.width) * 8;
    card.style.transform = `perspective(900px) rotateX(${rotateX}deg) rotateY(${rotateY}deg) scale(1.015)`;
  };

  const resetTilt = (e) => {
    e.currentTarget.style.transform = "perspective(900px) rotateX(0) rotateY(0) scale(1)";
  };

  const scrollToSection = (id) => (e) => {
  e.preventDefault();
  document.getElementById(id)?.scrollIntoView({ behavior: "smooth" });
};

  return (
    <div className="home">
        <div className="cursor-glow" ref={cursorRef} aria-hidden="true" />
            {/* Nav */}
            <header className={`home-nav ${scrolled ? "scrolled" : ""}`}>
                <div className="home-nav-inner">
                <div className="home-logo">
                    <img src="src\assets\fintrack_logo.png" alt="" className="home-logo-mark" />
                    FinTrack <span className="home-logo-ai">AI</span>
                </div>
                <nav className="home-nav-links">
                  <a href="#top" onClick={scrollToSection("top")}>Home</a>
                  <a href="#how-it-works" onClick={scrollToSection("how-it-works")}>About</a>
                  <a href="#features" onClick={scrollToSection("features")}>Features</a>
                </nav>
                <div className="home-nav-actions">
                    <Link to="/login" className="btn-ghost">Login</Link>
                    <Link to="/register" className="btn-solid">Register</Link>
                </div>
                </div>
            </header>

            {/* Hero */}
            <section className="hero" id="top">
                <div className="hero-glow" />
                <div className="hero-inner">
                <div className="hero-copy">
                    <span className="eyebrow">Personal finance, with a second pair of eyes</span>
                    <h1>
                    A smart, AI-powered<br /> personal finance tracker
                    </h1>
                    <p>
                    Manage your income, expenses, budgets, and reports — with an AI
                    assistant that actually understands your spending.
                    </p>
                    <div className="hero-ctas">
                    <Link to="/register" className="btn-solid lg">Get Started</Link>
                    <a href="#features" onClick={scrollToSection("features")} className="btn-ghost lg">
                      View Features
                    </a>
                    </div>
                </div>

                <div
                    className="hero-visual"
                    onMouseMove={handleTilt}
                    onMouseLeave={resetTilt}
                >
                    <div className="mock-card">
                    <div className="mock-card-header">
                        <span className="mock-dot red" />
                        <span className="mock-dot amber" />
                        <span className="mock-dot green" />
                        <span className="mock-card-title">Dashboard — July</span>
                    </div>

                    <div className="mock-tabs">
                        <button
                        className={mockTab === "category" ? "active" : ""}
                        onClick={() => setMockTab("category")}
                        >
                        By Category
                        </button>
                        <button
                        className={mockTab === "trend" ? "active" : ""}
                        onClick={() => setMockTab("trend")}
                        >
                        Trend
                        </button>
                    </div>

                    <div className="mock-stat-row">
                        <div className="mock-stat">
                        <span className="mock-stat-label">Spent</span>
                        <span className="mock-stat-value">₹4,500</span>
                        </div>
                        <div className="mock-stat">
                        <span className="mock-stat-label">Remaining</span>
                        <span className="mock-stat-value mint">₹1,10,500</span>
                        </div>
                    </div>

                    {mockTab === "category" ? (
                        <div className="mock-bars" key="bars">
                        <span style={{ "--h": "40%" }} />
                        <span style={{ "--h": "65%" }} />
                        <span style={{ "--h": "35%" }} />
                        <span style={{ "--h": "80%" }} />
                        <span style={{ "--h": "55%" }} />
                        <span style={{ "--h": "70%" }} />
                        <span style={{ "--h": "45%" }} />
                        </div>
                    ) : (
                        <svg className="mock-line" viewBox="0 0 280 90" key="line">
                        <polyline
                            points="0,70 40,55 80,60 120,35 160,42 200,20 240,28 280,10"
                            fill="none"
                            stroke="#818cf8"
                            strokeWidth="3"
                            strokeLinecap="round"
                            strokeLinejoin="round"
                        />
                        </svg>
                    )}
                    </div>

                    <div className="mock-bubble">
                    <span className="mock-bubble-avatar">🤖</span>
                    <span className="mock-bubble-text">
                        You're 12% under budget this month — nice work.
                    </span>
                    </div>
                </div>
                </div>

                {/* Stats strip */}
                <div className="stats-strip">
                {STATS.map((s) => (
                    <Stat key={s.label} {...s} />
                ))}
                </div>
            </section>

            {/* Why FinTrack AI — glass section */}
            <section className="section glass-section" id="why">
                <Reveal><h2 className="section-title light">Why FinTrack AI?</h2></Reveal>
                <div className="why-grid">
                {WHY_ITEMS.map((item, i) => (
                    <Reveal key={item.title} delay={i * 60}>
                    <div className="why-card glass">
                        <span className="why-icon">{item.icon}</span>
                        <h3>{item.title}</h3>
                        <p>{item.desc}</p>
                    </div>
                    </Reveal>
                ))}
                </div>
            </section>

            {/* How it works */}
            <section className="section dots-bg" id="how-it-works">
                <Reveal><h2 className="section-title1">How it works</h2></Reveal>
                <div className="steps">
                {STEPS.map((step, index) => (
                    <Reveal key={step.title} delay={index * 80}>
                    <div className="step">
                        <div className="step-marker">
                        <span>{index + 1}</span>
                        {index < STEPS.length - 1 && <div className="step-line" />}
                        </div>
                        <div className="step-body">
                        <h4>{step.title}</h4>
                        <p>{step.desc}</p>
                        </div>
                    </div>
                    </Reveal>
                ))}
                </div>
            </section>

            {/* Project features */}
            <section className="section features-bg" id="features">
                <Reveal><h2 className="section-title">Project features</h2></Reveal>
                <div className="features-grid">
                {FEATURES.map((f, i) => (
                    <Reveal key={f} delay={i * 30} className="feature-item-wrap">
                    <div className="feature-item">
                        <span className="feature-check">✓</span>
                        {f}
                    </div>
                    </Reveal>
                ))}
                </div>
            </section>

            {/* Tech stack */}
            <section className="section stack-dark" id="stack">
                <Reveal><h2 className="section-title">Technology stack</h2></Reveal>
                <div className="stack-grid">
                {STACK.map((group, i) => (
                    <Reveal key={group.label} delay={i * 80}>
                    <div className="stack-group">
                        <span className="stack-label">{group.label}</span>
                        <div className="stack-chips">
                        {group.items.map((item) => (
                            <span className="stack-chip" key={item}>{item}</span>
                        ))}
                        </div>
                    </div>
                    </Reveal>
                ))}
                </div>
            </section>

            {/* AI Assistant CTA */}
            <section className="ai-cta">
                <Reveal>
                <div className="ai-cta-inner">
                    <h2>Need help planning your budget?</h2>
                    <p>
                    Our AI assistant reads your spending habits and answers your
                    finance questions instantly — no spreadsheets required.
                    </p>
                    <Link to="/register" className="btn-solid lg light">Try 🤖 FinMate AI Assistant</Link>
                </div>
                </Reveal>
            </section>

            {/* Footer */}
            <footer className="home-footer">
                <div className="home-footer-inner">
                <div>
                    <div className="home-logo footer-logo">
                    <span className="home-logo-mark">FT</span>
                    FinTrack <span className="home-logo-ai">AI</span>
                    </div>
                    <p>Made with React, Spring Boot &amp; Spring AI</p>
                </div>
                <div className="footer-links">
                    <a href="https://github.com" target="_blank" rel="noreferrer">GitHub</a>
                    <a href="https://linkedin.com" target="_blank" rel="noreferrer">LinkedIn</a>
                    <a href="mailto:shivamambekar027@gmail.com">Contact</a>
                </div>
                </div>
            </footer>
        
    </div>
    
  );
}
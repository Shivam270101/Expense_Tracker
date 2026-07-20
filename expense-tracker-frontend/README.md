# Expense Tracker Frontend

React + Vite frontend scaffold for the Expense Tracker app, matching the backend DTOs.

## Setup

```bash
npm install
cp .env.example .env   # then set VITE_API_BASE_URL to your backend URL
npm run dev
```

## Structure

- `src/components` — Navbar, Sidebar, DashboardCards, ExpenseTable, ExpenseForm, BudgetCard, CategoryCard, AiChat, Charts, MainLayout, ProtectedRoute
- `src/pages` — Login, Register, Dashboard, Expenses, Categories, Budgets, Reports, Profile, AIAssistant
- `src/services` — authService, expenseService, budgetService, categoryService, dashboardService, aiService (all axios wrappers around `/api/*` matching the backend DTOs)
- `src/context` — AuthContext (JWT + current user, stored in localStorage)
- `src/hooks` — useAuth
- `src/utils` — constants (payment modes, months) and formatters (currency, date)

## Flow

Register → Login → Dashboard → Categories → Budgets → Expenses → Dashboard updates → AI Assistant

Routing is in `src/App.jsx`. All pages except Login/Register are behind `ProtectedRoute`, which checks for a JWT in localStorage and redirects to `/login` if missing.

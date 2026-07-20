# 💰 FinTrack AI – AI-Powered Personal Finance & Expense Assistant

FinTrack AI is a modern Full Stack Personal Finance Management application that helps users efficiently manage their daily expenses, monthly budgets, and financial records. The application integrates an AI-powered Finance Assistant that provides financial guidance, budgeting tips, and answers finance-related queries through a conversational interface.

Built using **Java 21, Spring Boot, React.js, MySQL, Spring AI, Ollama (Llama 3), and JWT Authentication**, the project demonstrates modern full-stack development practices and AI integration.

---

# 🚀 Features

## 👤 User Management
- User Registration
- Secure Login using JWT Authentication
- Role-Based Access Control
- Password Encryption using BCrypt
- Protected Routes

---

## 💸 Expense Management

- Add Expenses
- Update Expenses
- Delete Expenses
- View Expense History
- Search Expenses
- Filter Expenses
- Expense Categories
- Payment Modes
- Expense Description
- Expense Date Management

---

## 📊 Budget Management

- Set Monthly Budget
- Track Monthly Spending
- Calculate Remaining Budget
- Budget Summary
- Budget Monitoring

---

## 📂 Category Management

- Create Categories
- Update Categories
- Delete Categories
- View Categories
- Prevent Duplicate Categories

---

## 🤖 AI Finance Assistant

Integrated using **Spring AI + Ollama (Llama 3)**

The AI Assistant can answer questions such as:

- How can I save more money?
- Give me a monthly budget plan.
- What is SIP?
- Explain Mutual Funds.
- Suggest investment ideas.
- How can I reduce unnecessary expenses?
- What is Emergency Fund?
- Tax saving suggestions.
- Difference between Needs and Wants.
- Financial planning tips.

---

## 🔐 Authentication & Security

- JWT Authentication
- Spring Security
- Password Encryption
- Secure REST APIs
- Authentication Filters
- Unauthorized Access Handling

---

## 📈 Dashboard

- Total Expenses
- Monthly Expenses
- Remaining Budget
- Budget Utilization
- Category-wise Expenses
- Recent Transactions

---

# 🛠️ Tech Stack

## Frontend

- React.js
- JavaScript (ES6)
- HTML5
- CSS3
- Bootstrap
- Axios
- React Router DOM

---

## Backend

- Java 21
- Spring Boot
- Spring Security
- Spring Data JPA
- Hibernate
- REST APIs
- Maven
- JWT Authentication

---

## AI

- Spring AI
- Ollama
- Llama 3

---

## Database

- MySQL

---

## Tools

- Git
- GitHub
- Postman
- IntelliJ IDEA / STS
- VS Code

---

# 📂 Project Structure

```
FinTrack-AI
│
├── Backend
│   ├── Controllers
│   ├── Services
│   ├── Repositories
│   ├── Entities
│   ├── DTOs
│   ├── Config
│   ├── Security
│   ├── Exception
│   └── AI Integration
│
├── Frontend
│   ├── Components
│   ├── Pages
│   ├── Services
│   ├── Context
│   ├── Routes
│   └── Assets
│
└── Database
```

---

# ⚙️ Installation

## Clone Repository

```bash
git clone https://github.com/<your-username>/FinTrack-AI.git
```

---

## Backend Setup

```bash
cd backend
```

Configure **application.properties**

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/expense_tracker

spring.datasource.username=root

spring.datasource.password=yourpassword

spring.jpa.hibernate.ddl-auto=update
```

Run

```bash
mvn clean install

mvn spring-boot:run
```

Backend runs on

```
http://localhost:8080
```

---

## Frontend Setup

```bash
cd frontend

npm install

npm run dev
```

Frontend runs on

```
http://localhost:5173
```

---

## Ollama Setup

Install Ollama

```
https://ollama.com
```

Pull Llama 3

```bash
ollama pull llama3
```

Start Ollama

```bash
ollama serve
```

---

# 🔌 REST APIs

## Authentication

```
POST /api/auth/register

POST /api/auth/login
```

---

## Expenses

```
GET /api/expenses

POST /api/expenses

PUT /api/expenses/{id}

DELETE /api/expenses/{id}
```

---

## Categories

```
GET /api/categories

POST /api/categories

PUT /api/categories/{id}

DELETE /api/categories/{id}
```

---

## Budget

```
GET /api/budgets

POST /api/budgets

PUT /api/budgets/{id}

DELETE /api/budgets/{id}
```

---

## AI Assistant

```
POST /api/ai/chat
```

---

# 🔒 Security

- JWT Authentication
- Spring Security
- BCrypt Password Encryption
- Role-Based Authorization
- Protected APIs
- Exception Handling

---

# 📸 Screenshots

Add screenshots here.

```
Login Page

Dashboard

Expense Management

Budget Management

Category Management

AI Finance Assistant

Reports
```

---

# 🎯 Future Enhancements

- Expense Analytics
- Charts & Graphs
- Email Notifications
- Export to Excel
- Export to PDF
- Voice Assistant
- Multi-language Support
- Google Login
- UPI Expense Import
- OCR Bill Scanner
- AI Expense Prediction
- AI Spending Analysis
- AI Investment Suggestions
- Mobile Application

---

# 📚 Learning Outcomes

This project helped strengthen practical knowledge of:

- Java 21
- Spring Boot
- Spring Security
- JWT Authentication
- REST API Development
- React.js
- MySQL
- Spring AI
- Ollama
- Llama 3
- Full Stack Development
- Git & GitHub
- Layered Architecture
- DTO Pattern
- Exception Handling

---

# 👨‍💻 Author

**Shivam Ambekar**

Java Full Stack Developer

LinkedIn: *(Add your LinkedIn URL)*

GitHub: https://github.com/Shivam270101

---

# ⭐ Support

If you found this project useful, please consider giving it a ⭐ on GitHub.

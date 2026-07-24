# 💰 FinTrack AI – AI-Powered Personal Finance & Expense Assistant

## About FinTrack AI

**FinTrack AI** is an AI-powered Full Stack Personal Finance Management application that enables users to efficiently manage expenses, track monthly budgets, organize financial records, and gain intelligent financial insights. The application combines traditional expense management with an integrated AI Finance Assistant built using Spring AI, allowing users to interact conversationally for budgeting advice, financial planning, and answers to personal finance questions.

### What makes FinTrack AI unique?

Unlike a traditional expense tracker that simply records transactions:

**Traditional Flow:**

* Add Expense → Save → View Records

**FinTrack AI:**

* Add Expense → Track Spending → Interact with 🤖 FinMate - AI Finance Assistant → Receive personalized budgeting suggestions, financial guidance, and answers to finance-related queries through a conversational interface.

By integrating AI with personal finance management, FinTrack AI goes beyond basic CRUD operations to provide users with a smarter, more interactive, and insightful financial management experience.

Built using **Java 17, Spring Boot, React.js, MySQL, Spring AI, Ollama (Llama 3), and JWT Authentication**, the project demonstrates modern full-stack development practices and AI integration.

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

## 🤖 FinMate - AI Finance Assistant

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
git clone https://github.com/Shivam270101/FinTrack-AI.git
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

- Java 17
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

LinkedIn: *[(Visit Profile)](https://www.linkedin.com/in/shivamambekar27/)*

GitHub: https://github.com/Shivam270101

---

# ⭐ Support

If you found this project useful, please consider giving it a ⭐ on GitHub.

# 👨‍💻 UI Review
Dashboard
<img width="1897" height="911" alt="Screenshot 2026-07-23 103141" src="https://github.com/user-attachments/assets/12699d53-c95c-4988-9b25-c0a03739147e" />

Add Categories
<img width="1897" height="910" alt="Screenshot 2026-07-23 103157" src="https://github.com/user-attachments/assets/e0d6b07d-6a03-45b2-876a-f4951fc2cf46" />

Add Expenses
<img width="1900" height="907" alt="Screenshot 2026-07-23 103225" src="https://github.com/user-attachments/assets/efe62d97-8a1e-4672-90d6-dc1315bbd091" />

Allocate the Budget for every month
<img width="1897" height="912" alt="Screenshot 2026-07-23 103248" src="https://github.com/user-attachments/assets/5c69d1fd-fd4f-433b-9451-3b7dd4017740" />

Expense Reports
<img width="1901" height="911" alt="Screenshot 2026-07-23 103118" src="https://github.com/user-attachments/assets/808da31c-5f6d-42cc-95ab-076ec23a1838" />

FinMate AI Finance Assistant
<img width="1900" height="907" alt="image" src="https://github.com/user-attachments/assets/00c75fb7-f198-43f4-a67f-e22e0169c152" />





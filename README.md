# Quiz App Backend (Spring Boot + JWT)

This is a Spring Boot backend for a Quiz App that supports *JWT authentication, **role-based access (User/Admin)*, and full quiz creation, question management, and quiz submission features.

---

## 🔐 Authentication & Roles

- *JWT-based login system*
- *Admin* can:
  - Create quizzes and questions
  - Update/delete quizzes and questions
- *User* can:
  - View and attempt quizzes
  - Submit answers and view scores

---

## 📦 Technologies Used

- Spring Boot
- Spring Security (JWT)
- JPA/Hibernate
- MySQL (or any DB)
- Lombok

---

## 🔧 API Endpoints

### 🔹 Authentication

| Method | Endpoint   | Description       |
|--------|------------|------------------|
| POST   | /register | Register a user  |
| POST   | /login    | Login (get JWT)  |

### 🔹 Admin (Requires ADMIN Role)

| Method | Endpoint                           | Description                         |
|--------|------------------------------------|-------------------------------------|
| POST   | /api/Quizz/admin/createquiz        | Create new quiz                     |
| POST   | /api/Quizz/admin/createquestion    | Add question to a quiz              |
| GET    | /api/Quizz/admin/getallquizes      | View all quizzes                    |
| PUT    | /api/Quizz/admin/updatequiz/{id}   | Update a quiz                       |
| DELETE | /api/Quizz/admin/deletequiz/{id}   | Delete a quiz                       |
| PUT    | /api/Quizz/admin/updateQuestion/{quizid}/{quesid} | Update a question    |
| DELETE | /api/Quizz/admin/deleteQuestion/{quizid}/{quesindex} | Delete a question |

### 🔹 User

| Method | Endpoint                           | Description              |
|--------|------------------------------------|--------------------------|
| GET    | /api/Quizz/user/getallquizes     | View available quizzes   |
| GET    | /api/Quizz/user/attemptquiz/{id} | Get quiz questions       |
| POST   | /api/Quizz/user/submitquiz       | Submit answers           |

---

## 🛠 How to Run Locally

1. Clone the project:
   ```bash
   git clone https://github.com/YOUR_USERNAME/YOUR_REPO_NAME.git
   cd YOUR_REPO_NAME

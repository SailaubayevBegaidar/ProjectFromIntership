# ProjectFromIntership
# Coordinater – Task Management System

This project is a task coordination system developed during an internship. It allows managing users with different roles and organizing task workflows between administrators, masters, and engineers. The system is built using **Spring Boot**, **PostgreSQL**, and **Thymeleaf**, with security and role-based access control.

---

## 🔧 Technologies Used

- **Java 21**
- **Spring Boot 3.5.3**
- **Spring Security**
- **Spring Data JPA**
- **PostgreSQL**
- **Thymeleaf**
- **Maven**

---

## 📌 Roles and Permissions

| Role       | Permissions                                                                 |
|------------|------------------------------------------------------------------------------|
| `ADMIN`    | Full access. Can manage users and view all data.                            |
| `MASTER`   | Can create tasks and assign them.                                            |
| `ENGINEER` | Can view and submit tasks (task gets linked with engineer's ID on submit). |

---

## 💼 Features

- ✅ Authentication and role-based access control via Spring Security
- 🧑‍🔧 User roles: Admin, Master, Engineer
- 🗂️ Task CRUD: Create, Read, Update, Delete
- 📃 Thymeleaf pages for login, task tables, and user actions
- 📊 Database integration with PostgreSQL
- 🔒 Password encryption with BCrypt
- 📁 MVC architecture with DAO, Service, Controller layers

---

## 📁 Project Structure

src/
├── main/
│ ├── java/
│ │ └── com.example.Coordinater/
│ │ ├── controllers/
│ │ ├── dao/
│ │ ├── models/
│ │ ├── repositories/
│ │ ├── security/
│ │ └── services/
│ └── resources/
│ ├── templates/ # Thymeleaf HTML pages
│ └── application.properties

yaml
Copy
Edit

---

## 🚀 Getting Started

### 1. Clone the repository
```bash
git clone https://github.com/SailaubayevBegaidar/ProjectFromIntership.git
cd ProjectFromIntership
2. Configure PostgreSQL
Make sure you have a PostgreSQL database running. Update your application.properties:

properties
Copy
Edit
spring.datasource.url=jdbc:postgresql://localhost:5432/your_db
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
3. Build and run the project
bash
Copy
Edit
mvn clean install
mvn spring-boot:run
Then open http://localhost:8080 in your browser.

🔐 Default Roles
You can manually insert users in the database with roles:

ADMIN

MASTER

ENGINEER

Passwords should be encrypted with BCrypt.
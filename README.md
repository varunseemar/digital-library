# 📚 Digital Library Management System

A **Spring Boot 3.x** RESTful application for managing **books**, **users**, and **memberships** in a digital library.  
Includes role-based security, membership status updates, and proper endpoint handling for CRUD operations.

---

## 🚀 Features

### **1. User Management**
- Add, update, get, list, and delete users.
- Validation with `@Valid` for safer data input.
- Prevents deletion if related memberships violate constraints.

### **2. Book Management**
- Add, update, get by ID, list all, and delete books.
- Returns proper HTTP **201** on creation, **404** on not found.

### **3. Membership Management**
- Add memberships with plan & status.
- Prevents adding membership if active/paused already exists.
- Allows **status update** via PATCH (except directly setting to `EXPIRED`).
- Retrieve membership details by ID.

### **4. Security**
- Spring Security Basic Auth configured via `application.properties`.
- `/csrf` endpoint for fetching CSRF token when CSRF protection is enabled.

### **5. Welcome Endpoint**
- Home route `/` that returns a welcome message with session ID.

---

## 🛠️ Tech Stack

- **Backend:** Java 21, Spring Boot 3.x
- **Security:** Spring Security (Basic Auth, CSRF)
- **Database:** PostgreSQL, Spring Data JPA, Hibernate
- **Testing:** JUnit 5 + Mockito
- **Build Tool:** Maven
- **Lombok** for boilerplate removal

---

## 📂 Project Structure (Key Packages)

src/main/java/org/VarunSeemar/digital_library
│
├── controller/ # REST API controllers  
├── service/ # Business logic services  
├── adapter/ # Adapters between controller and service layers  
├── repository/ # Spring Data JPA Repositories  
├── entities/ # JPA entities (input/output)  
├── model/ # DTOs for API responses  
├── mappers/ # Model ↔ Entity mappers  
├── enums/ # Membership plans & statuses  
├── exceptions/ # Custom exception classes  
└── DigitalLibraryApplication.java  

---

## ⚙️ Installation & Setup

### **1. Clone the repository**
```bash
git clone https://github.com/yourusername/digital-library.git
cd digital-library
```

### **2. Configure Database & Security**
Edit `src/main/resources/application.properties`:
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/digital_library
spring.datasource.username=your_db_user
spring.datasource.password=your_db_pass
spring.jpa.hibernate.ddl-auto=update

# Basic Auth credentials
spring.security.user.name=varun
spring.security.user.password=varun@123
```

### **3. Run the application**
```bash
mvn clean install
mvn spring-boot:run
```
Runs at: [**http://localhost:8080**](http://localhost:8080)

---

## 📡 API Endpoints

### **LibraryController**
| Method | Endpoint   | Description |
|--------|-----------|-------------|
| GET    | `/`       | Welcome message and session ID |
| GET    | `/csrf`   | Returns CSRF token |

---

### **BookController** (`/book`)
| Method | Endpoint            | Description |
|--------|--------------------|-------------|
| POST   | `/book/add`         | Add a new book |
| GET    | `/book/{id}`        | Get book by ID |
| GET    | `/book/getAllBooks` | Get all books |
| PUT    | `/book/update`      | Update book details |
| DELETE | `/book/{id}`        | Delete book by ID |

---

### **UserController** (`/user`)
| Method | Endpoint             | Description |
|--------|---------------------|-------------|
| POST   | `/user/addUser`      | Add new user |
| GET    | `/user/{id}`         | Get user by ID |
| GET    | `/user/getAllUsers`  | List all users |
| PUT    | `/user/updateUser`   | Update user |
| DELETE | `/user/delete/{id}`  | Delete user by ID |

---

### **MembershipController** (`/membership`)
| Method | Endpoint                  | Description |
|--------|---------------------------|-------------|
| POST   | `/membership/add`         | Add a membership (throws error if active/paused membership exists) |
| GET    | `/membership/{id}`        | Get membership by ID |
| PATCH  | `/membership/updateStatus`| Update membership status (can't set directly to `EXPIRED`) |

**Note:** Calls to `/membership/updateStatus` expect JSON:
```json
{
  "userId": 1,
  "status": "PAUSED"
}
```

---

## 🧪 Running Tests

Unit tests:
```bash
mvn test
```
- **Mockito** used to mock repository/data layers.
- Controllers and service methods tested for both happy and exceptional cases.

---

## 🔐 Security Notes

- All endpoints are **protected by default** (Basic Auth).
- CSRF enabled unless disabled in configuration.
- Use `/csrf` to fetch token for non-GET requests in browsers or with Postman if CSRF on.

---

## 🚀 Future Improvements

- Swagger API Docs
- Role-based permissions
- Pagination & filtering
- Docker deployment

---

📌 **How to save this as your GitHub README:**

Create a file called `README.md` in your project root folder.

Paste the above content exactly.

Commit and push:
```bash
git add README.md
git commit -m "Added project README"
git push
```

<img src="https://img.shields.io/static/v1?label=license&message=MIT&color=5965E0&labelColor=121214" alt="License"> <img src="https://img.shields.io/badge/Spring_Boot_4.1-6DB33F?style=for-the-badge&logo=spring-boot&logoColor=white" alt="Spring Boot" /> <img src="https://img.shields.io/badge/Java_25-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white" alt="Java" /> <img src="https://img.shields.io/badge/Maven-C71A36?style=for-the-badge&logo=apache-maven&logoColor=white" alt="Maven" /> <img src="https://img.shields.io/badge/PostgreSQL-336791?style=for-the-badge&logo=postgresql&logoColor=white" alt="PostgreSQL" /> <img src="https://img.shields.io/badge/Spring_AI-54A859?style=for-the-badge&logo=spring&logoColor=white" alt="Spring AI" />

# Bookstore API

The **Bookstore API** is a REST CRUD service built with **Spring Boot 4.1** and **Java 25**, featuring **AI-generated book summaries** powered by **Spring AI** (Google Gemini). Designed as a teaching project for a Spring Boot course, it demonstrates clean layering, explicit JPA entities, and DTO-to-entity mapping without Lombok.

---

## Stack 🚀

**Backend**

- **Java 25**
- **Spring Boot 4.1.1** (Web MVC, Data JPA, Validation)
- **Spring AI 2.0** — `spring-ai-starter-model-google-genai`
- **Maven**
- **PostgreSQL** (Neon) — Hibernate `ddl-auto: update`

**AI Provider**

- **Google Gemini API**

---

## Environment Configuration 🔐

Before running the project, configure your database and AI credentials:

1. Copy `.env.example` and rename it to `.env`.
2. Open `.env` and fill in your values:

   ```env
   DB_URL=jdbc:postgresql://your-host/bookstore-db?sslmode=require
   DB_USER=your_user
   DB_PASSWORD=your_password
   GEMINI_API_KEY=your_gemini_key
   ```

   _(The `.env` file is excluded from Git for security)._

---

## Running Locally ⚡️

**Prerequisites:** JDK 25+, Maven.

1. Clone the repository:

   ```bash
   git clone https://github.com/dev-araujo/bookstore-api.git
   ```

2. Run the application:

   ```bash
   mvn spring-boot:run
   ```

   Or use the Makefile shortcut:

   ```bash
   make run
   ```

⭐ The API will be available at `http://localhost:8080`.

Other commands: `mvn -q verify` (build & test), `make build`.

---

## Architecture & Conventions 🏗️

- **Layering:** controllers → services → repositories → models
- **Naming:** tables `tb_<plural>` (e.g. `tb_books`), entities `<Name>Model`, input DTOs `<Name>RecordDto`
- **No Lombok** — constructors, getters, and setters are handwritten
- **No field injection** — `@Autowired` on fields is blocked by a guardrail hook
- **`BeanUtils.copyProperties`** used for DTO → entity mapping
- **UUID identifiers** for all entities

---

#### Author 👷

<img src="https://avatars.githubusercontent.com/u/97068163?v=4" width=120 />

[Adriano P Araujo](https://www.linkedin.com/in/araujocode/)

# Структура проекта

Bell_Integrator/

├── src/

│   └── main/

│       └── java/

│           └── com.example.stub/

│               └── StubController.java

├── pom.xml

└── README.md

# Bell_Integrator Stub App

REST-заглушка, реализованная на Java с использованием Spring Boot и Maven.

---

## 📦 Возможности

- `GET /status` — возвращает статичный JSON с задержкой 1.5 сек.
- `POST /login` — принимает login и password, возвращает их вместе с текущей датой.

---

## 🚀 Быстрый запуск

### ⚙️ Предварительные требования

- Java 17+ (JDK)
- Maven 3.6+
- Git (если клонируете репозиторий)

### 🔽 Клонировать проект

```bash
git clone https://github.com/lukajin01/Bell_Integrator.git
cd Bell_Integrator
```

Код запуска
```
./mvnw spring-boot:run
```

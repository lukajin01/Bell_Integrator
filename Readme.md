# Структура проекта

Bell_Integrator/

├── src/

│ └── main/

│ └── java/

│ └── com.example.stub_app/

│ ├── StubAppApplication.java

│ ├── controller/

│ │ └── StubController.java

│ └── model/

│ │ └── User.java

├── jolokia-agent-jvm-2.2.9-javaagent.jar

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

Код запуска spring
```
./mvnw spring-boot:run
```
Код запуска spring с jolokia
```
java -javaagent:src/jolokia-agent-jvm-2.2.9-javaagent.jar=port=8778,host=0.0.0.0 -jar target/stub-app-0.0.1-SNAPSHOT.jar
```

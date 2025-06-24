# Sitly

## 🧱 아키텍처 구성

- **언어**: Kotlin
- **프레임워크**: Spring Boot 3.5
- **데이터 저장소**:
    - RDBMS : H2
- **빌드 툴**: Gradle
- **도구**: Docker, GitHub Actions(CI/CD)

## 로컬 임시 환경

## 💾 Database H2

- H2 Console: [http://localhost:8080/h2-console](http://localhost:8080/h2-console)
    - **JDBC URL**: `jdbc:h2:mem:testdb`
    - **Username**: `sa`
    - **Password**: *(비워두기)*

---

## 🛠 API 문서 (Swagger)

- Swagger UI: http://localhost:8080/swagger-ui/index.html
- API 문서 (JSON): http://localhost:8080/v3/api-docs

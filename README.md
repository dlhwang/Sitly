# Sitly

## 🧱 아키텍처 구성

- **언어**: Kotlin
- **프레임워크**: Spring Boot 3.2
- **데이터 저장소**:
    - RDBMS : H2
- **빌드 툴**: Gradle

## 💾 Database H2

- H2 Console: [http://localhost:8080/h2-console](http://localhost:8080/h2-console)
    - **JDBC URL**: `jdbc:h2:mem:testdb`
    - **Username**: `sa`
    - **Password**: *(비워두기)*

---

## 🛠 API 문서 (Swagger)

- Swagger UI: http://localhost:8080/swagger-ui/index.html
- API 문서 (JSON): http://localhost:8080/v3/api-docs

## 🏃‍♂️ 실행 방법

### 1. 자바 & Gradle 환경 준비

- **Java 17 이상**이 설치되어 있어야 합니다.
- 별도로 Gradle을 설치할 필요는 없습니다. 프로젝트에 포함된 **Gradle Wrapper (`./gradlew`)**를 사용합니다.

### 2. 애플리케이션 실행

```bash
./gradlew bootRun
```

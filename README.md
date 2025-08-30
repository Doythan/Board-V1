# Board-V1
Spring 기반 게시판 프로젝트입니다.

---

# Board Project (V1)

간단하지만 확장 가능한 **게시판/유저/댓글** 학습 프로젝트. 스프링의 핵심 메커니즘(디스패처, 리플렉션), 웹 MVC 흐름, JPA/H2, Mustache 템플릿까지 실전 감각으로 익히는 게 목표다. 불필요한 장식은 걷어내고 **CRUD 품질**에 집중한다.

## 학습 체크리스트

1. 뼈대 만들기: **화면/DB 설계**
2. **데이터베이스 테이블** 생성 + 더미 데이터 구축
3. **DB 연결** 및 테스트
4. 게시글 **목록 조회**
5. 게시글 **상세 보기**
6. 게시글 **추가(작성)**
7. 게시글 **삭제**
8. **수정 화면** 구현
9. 게시글 **수정 처리**

---

## 기술 스택

* **Java / Spring Boot**
* **Spring Web, Spring Data JPA**
* **H2 (in-memory)**
* **Mustache** (서버 사이드 템플릿)
* Gradle, Lombok

---

## 🔩 애플리케이션 설정 (application.properties 해설)

```properties
server.port=8080

# UTF-8 강제 적용 (요청/응답)
server.servlet.encoding.charset=UTF-8
server.servlet.encoding.enabled=true
server.servlet.encoding.force=true

# Mustache에서 request/session 속성 접근 허용
spring.mustache.servlet.expose-session-attributes=true
spring.mustache.servlet.expose-request-attributes=true

# H2 (메모리 DB) + MySQL 호환 모드
spring.datasource.driver-class-name=org.h2.Driver
spring.datasource.url=jdbc:h2:mem:test;MODE=MySQL;
spring.h2.console.enabled=true
spring.datasource.username=sa
spring.datasource.password=

# JPA
spring.jpa.hibernate.ddl-auto=create            # 매 실행마다 스키마 재생성 (학습/테스트 용도만!)
spring.jpa.show-sql=true                        # SQL 출력
spring.jpa.properties.hibernate.format_sql=true # SQL 포맷팅

# data.sql 실행 위치 지정 + 실행 순서 보정
spring.sql.init.data-locations=classpath:db/data.sql
spring.jpa.defer-datasource-initialization=true # JPA가 테이블 만든 뒤 data.sql 실행
```

### 핵심 포인트(강조)

* `jdbc:h2:mem:test;MODE=MySQL;`
  → **H2를 MySQL 문법으로** 최대한 맞춰 학습/호환성 이득.
* `ddl-auto=create`
  → **매번 테이블 갈아엎음.** 실서비스 금지. 학습/테스트에서만 사용.
* `defer-datasource-initialization=true`
  → **스키마 생성 후에 `data.sql`** 이 실행되도록 보장. (에러의 8할 방지)

---

## 📁 디렉터리 개요

```
src
 └─ main
    ├─ java
    │   └─ com.mtcoding.boardproject
    │       ├─ BoardProjectApplication.java
    │       └─ board
    │           ├─ Board.java
    │           ├─ BoardRepository.java
    │           ├─ BoardService.java
    │           ├─ BoardController.java
    │           ├─ dto
    │           │   ├─ BoardRequest.java
    │           │   └─ BoardResponse.java
    │           └─ ...
    └─ resources
        ├─ templates/         # Mustache 뷰(.mustache)
        ├─ static/            # 정적리소스(css/js/img)
        ├─ db/data.sql        # 더미 데이터
        └─ application.properties
```

* 앱: [http://localhost:8080](http://localhost:8080)
* H2 콘솔: [http://localhost:8080/h2-console](http://localhost:8080/h2-console)

    * JDBC URL: `jdbc:h2:mem:test`
    * User: `sa` / Password: (빈칸)

---

## 🧪 더미 데이터(data.sql) 예시

```sql
insert into board_tb (title, content) values ('title1', 'Spring Boot Study 1');
insert into board_tb (title, content) values ('title2', 'Spring Boot Study 2');
```
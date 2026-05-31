# Spring Toy Backend

Spring Boot로 인증과 Quest API를 학습하기 위한 백엔드 토이 프로젝트입니다.

프론트엔드는 별도 Vue 레포에서 관리하고, 이 백엔드는 인증 API와 이후 Quest API를 제공하는 역할로 진행합니다.

## 목표

- Spring Boot 기반 REST API 구성 익히기
- 회원가입과 로그인 API 구현
- BCrypt로 비밀번호 암호화하기
- JWT 발급과 검증 흐름 만들기
- Vue 프론트엔드에서 보호 API를 호출할 수 있도록 연결하기
- Quest 엔터티와 CRUD API로 기능 확장하기

## 현재 구현

- 회원가입 API
  - `POST /auth/signup`
  - username, password 입력
  - BCrypt password encoding
- 로그인 API
  - `POST /auth/login`
  - 로그인 성공 시 JWT access token 발급
- JWT 인증
  - `JwtUtil`
  - `JwtFilter`
  - Spring Security stateless 설정
- 보호 API
  - `GET /users/me`
  - JWT 인증 성공 시 현재 사용자 정보 응답
- MyBatis 기반 사용자 저장소
  - `UserMapper`
  - `UserMapper.xml`

## 프론트엔드 연동

프론트엔드는 별도 레포에서 관리합니다.

```text
vue-toy-frontend
```

현재 프론트에서는 로그인 후 access token을 저장하고, Quest Board 화면에서 `GET /users/me`를 호출해 보호 API 연결을 확인합니다.

## 로컬 설정

실제 DB 접속 정보는 Git에 올리지 않습니다.

`src/main/resources/application-example.properties`를 참고해서 로컬에 아래 파일을 생성한 뒤 값을 채워 실행합니다.

```text
src/main/resources/application.properties
```

## 실행

```sh
./mvnw spring-boot:run
```

## 테스트

```sh
./mvnw test
```

## 다음 작업

- JWT secret을 설정 파일 또는 환경 변수 기반으로 분리
- Quest 엔터티 설계
- Quest Repository/Mapper 작성
- Quest Service/Controller 작성
- 사용자별 Quest 조회, 생성, 완료 토글, 삭제 API 구현
- Vue Quest Board를 백엔드 `/quests` API와 연결

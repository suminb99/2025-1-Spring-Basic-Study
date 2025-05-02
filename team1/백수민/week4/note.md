# 섹션 7. 스프링 DB 접근 기술

## 7.1 H2 데이터베이스 설치

1. h2 databse 다운로드 받기
2. 설치 완료 후 실행
<img src="./images/Screenshot 2025-05-02 at 17.23.32.png" height="50px" width="400px"/>
<img src="./images/Screenshot 2025-05-02 at 17.23.16.png" height="300px" width="400px"/>


3. **test.mv.db**가 home에 있어야 함
4. 파일 직접 접근 `jdbc:h2:~/test` X, socket 통해 접근 `jdbc:h2:tcp://localhost/~/test` O
<img src="./images/Screenshot 2025-05-02 at 17.31.43.png" height="300px" width="500px"/>

- 실행 단축키: command + enter

<img src="./images/Screenshot 2025-05-02 at 17.49.07.png" height="300px" width="600px"/>
<br>

## 7.2 순수 JDBC

1. build.gradle 파일에 jdbc, h2 DB 관련 라이브러리 추가
    - `'org.springframework.boot:spring-boot-starter-jdbc'`
    - `'com.h2database:h2'`
2. resources/application.properties에 DB 연결 설정 추가

- MemberRepository: 회원을 저장하는 역할
- MemoryMemberRepository: 구현을 Memory에
- JDBCMemberRepository: 구현을 DB랑 연동해서 JDBC로
<br>

- DB에서 Sequence 값이 뭐가 생성되었는지 가져와야함
- 사용한 자원은 무조건 release 해주어야함
<br>

**우리가 Spring을 쓰는 이유 (객체지향적 설계의 장점):** 
- 다형성 활용, 인터페이스를 기반으로 구현체를 손쉽게 교체할 수 있음
- Spring은 spring container를 지원하여 보다 편리하게 구현체를 교체할 수 있게 함
- dependency injection을 통해 기존 코드를 수정할 필요 없이 설정만으로 구현 클래스 변경 가능
    - 실제 application 코드를 수정할 필요 없음
    - application을 조립하는 assembly 코드만 수정해주면 됨
- 개방.폐쇄 원칙 (OCP, Open-Closed Principle): 확장에는 Open, 수정/변경에는 closed
<br>

## 7.3 스프링 통합 테스트

- 스프링 통합 테스트 시 추가하는 annotation: `@SpringBootTest`, `@Transactional`
- `@SpringBootTest`: 스프링 컨테이너와 테스트를 함께 실행
- `@Transactional`: 테스트 시작 전에 Transaction을 시작하고 테스트 완료 시 항상 data rollback (데이터 반영 X) => 테스트를 반복해서 실행 가능
- 더 좋은 테스트? 단위 테스트 > 통합 테스트 => 스프링 컨테이너 없이 테스트할 수 있도록 훈련해야함
<br>

## 7.4 스프링 JdbcTemplate
- 반복되는 코드를 줄여주지만, SQL을 직접 작성해줌.
<br>

## 7.5 JPA

- JPA를 사용하면 SQL 쿼리도 JPA가 직접 만들어서 실행해줌
- JPA, H2 DB 관련 라이브러리 추가

- 단건 저장, 조회, 업데이트의 경우 SQL 작성할 필요 X => 자동으로 작성됨
- findByName, findAll의 경우 (PK 기반 X) => query 작성해야함
- 데이터를 저장하거나 변경할 땐 항상 @Transactional 추가해야함
<br>

## 7.6 스프링 데이터 JPA

- 스프링 데이터 JPA 사용 시 repository 구현 클래스 없이 인터페이스만으로 개발을 완료할 수 있음

```Java
public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository {
    @Override
    Optional<Member> findByName(String name);
}
```

- JpaRepository를 상속받으면, Spring 데이터 JPA가 구현체(SpringDataJpaMemberRepository)를 자동으로 만들어서 spring bean에 등록해준다!!!!
- 인터페이스를 통한 기본적인 CRUD 기능 제공
- `findByName()` , `findByEmail()` 처럼 메서드 이름 만으로 조회 기능 제공
- 페이징 기능 자동 제공

# 2주차 학습내용 정리
## 4-1 비즈니스 요구사항 정리
- Service : 비즈니스 로직 
- Domain : 회원, 주문, 쿠폰 등의 비즈니스 도메인 객체
- Repository : DB 접근 및 관리

## 4-2 회원 도메인과 리포지토리 만들기
- Optional.ofNullable  
NullPointerException 방지하기 위해 사용   
값이 null이면 빈 Optional 객체를 반환함

## 4-3 회원 리포지토리 테스트 케이스 작성
- JUnit으로 테스트를 실행
- 모든 test 메서드끼리는 순서와 상관없이 따로 동작하도록 설계해야 함
- 클래스 레벨에서 실행할 시 메서드 실행순서 보장 X
- @AfterEach : 메서드 실행 끝나면 콜백 
- @BeforeEach : 메서드 실행 전마다 콜백

## 4-4 회원 서비스 개발
- Service 의 메서드는 비즈니스 용어로 (ex. join)

## 4-5 회원 서비스 테스트
- Ctrl + Shift + T : create new test
- Build 될 때 테스트 코드는 포함되지 않음
- given-when-then Pattern : 테스트 코드 작성하는 표현 방식.  
  - Given - 테스트를 위한 변수, 입력 값 등을 정의
  - When - 실제 액션
  - Then - 액션 후 나온 값 검증
- 정상x 예외 케이스 중심으로 테스트케이스 만들기
- assertThat, assertThrows
- Dependency Injection
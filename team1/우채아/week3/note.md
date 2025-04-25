# 3주차 학습내용 정리
## 5-1 컴포넌트 스캔과 자동 의존관계 설정
- 지금까지 만든 회원 레포지토리 시스템을 화면에 띄워보자! -> Controller, View 필요
- 멤버 서비스를 통해서 회원가입 및 조회 -> 서로 의존관계를 설정해 주어야 함
- build 할 때 @Controller 있으면 이 컨트롤러 객체 하나 생성해서 컨테이너로 관리함
- 어노테이션 사용하여 컨테이너에 등록 : Dependency Injection
- Dependency Injection
  - 생성자 주입 - 생성자 안에서 DI (우리가 사용한 방법)
  - Setter 주입
  - 필드 주입
- @Autowired, @Controller, @Service ...
- @SpringBootApplication 과 같은 패키지에 있는 클래스 전부 어노테이션 있으면 스프링 빈에 자동 등록됨
- 싱글 톤 : 클래스 하나 당 객체 "하나만" 생성해서 스프링 빈에 등록하여 공유

## 5-2 자바 코드로 직접 스프링 빈 등록하기
- @Service, @Repository 지우기
- @SpringBootApplication 과 같은 패키지에 @Configuration 붙여서 클래스 만들기
- 이 클래스 안에서 @Bean 으로 직접 스프링 빈 등록해주면 됨

## 6-1 회원 웹 기능 - 홈 화면 추가
- resources/templates/home.html 추가

## 6-2 회원 웹 기능 - 등록
- templates/members/createMemberForm.html 추가
- html에서 input 태그로 name 받음
- Post로 넘어온 name을 form 객체의 멤버변수 name에 스프링이 자동으로 넣어줌
- MemberController 에서 Member 객체 만들어서 join

## 6-3 회원 웹 기능 - 조회
- templates/members/memberList.html 추가
- thymeleaf 템플릿 언어에 의해 members 안에 있는 member들의 id, name 출력됨
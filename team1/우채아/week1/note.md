# 1주차 학습내용 정리

## 2-1 프로젝트 생성

### Spring Initializer
- 스프링 부트 기반으로 스프링 프로젝트 만듦  
- start.spring.io 
- maven, gradle - 필요한 라이브러리 가져오고 빌드까지 도와주는 tool : 요즘은 대부분 gradle
- Project Metadata 
  - Group - 보통 기업명
  - Artifact - 빌드 된 후 나오는 결과물 (프로젝트명)
- Dependencies - 어떤 라이브러리 땡겨서 쓸건지 선택
  - Spring web 
  - Thymeleaf : html 파일 렌더링해주는 템플릿 엔진

### Project Structure
- .idea - IntelliJ 프로젝트 설정 파일  
- gradle\wrapper - gradle 관련 폴더  
- src\main , src\test - 표준화.  
- main\java\실제 코드들..  
- test\java\테스트용 코드들  

- resources - xml, propertices 등과 같은 .java 제외한 파일들  
- build.gradle - 예전에는 일일히 코딩해야 하는 설정 파일. 요즘은 스프링부트 통해서 제공  
자바 버전, 스프링 버전   
dependencies : 이니셜라이저에서 선택한 dependencies 설정 들어감 + test  
repositories { mavenCentral() } : 라이브러리 다운받을 경로 설정해둔 것.  
- .gitignore - git에서 버전 관리 하지 않을 파일들. 공개되면 안되는 파일들 자동으로 들어가 있음   

### Project 실행
- o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat initialized with port 8080 (http)  
  -> localhost:8080 들어가면 실행 화면 볼 수 있음
- 스프링부트 애플리케이션 실행 -> tomcat 내장되어 있어서 거기에 스프링 애플리케이션이 올라간다!

## 2-2 라이브러리 살펴보기  
- dependencies 선택 2개밖에 안 했는데, 인텔리제이의 External Libraries 들어가면 라이브러리 많은 이유  
-> maven, gradle 은 라이브러리들 버전 관리 다 알아서 해준다
- 톰캣 없었을 때 : 웹서버와 개발 라이브러리가 분리되어 있었음. 지금은 웹서버가 내장되어 있으므로 따로 설정할 필요 X
- slf4j - 인터페이스 느낌, 실제 로깅은 logback-classic이 담당 ... 둘다 logging 땡겨오면 자동으로 땡겨와짐
- JUnit - 테스트 위한 라이브러리

## 2-3 View 환경설정
spring.io 접속 후 projects - Spring boot 들어가면 공식 문서 읽을 수 있다 !
index.html 은 그냥 파일 통째로 서버에 넘겨준 것  
Thymeleaf 공식 문서도 읽어보기  
thymeleaf 공식 사이트: https://www.thymeleaf.org/  
스프링 공식 튜토리얼: https://spring.io/guides/gs/serving-web-content/  
스프링부트 메뉴얼: https://docs.spring.io/spring-boot/docs

- 컨트롤러에서 리턴 값으로 문자를 반환하면 뷰 리졸버( viewResolver )가 화면을 찾아서 처리한다  
- 스프링 부트 템플릿엔진 기본 viewName 매핑  
  resources:templates/ +{ViewName}+ .html

## 2-4 빌드하고 실행하기
프로젝트 폴더에서 cmd 열고 gradlew.bat build -x check  
build\libs 들어가서 java -jar (build된 jar 파일명) 하면 인텔리제이 안에서 실행시키는 것과 같이 실행된다  
나중에 서버에 배포할때도 이 jar 파일만 서버에 올리면 됨

## 3-1 정적 컨텐츠
- 웹 개발 방법 세가지 : 정적 컨텐츠 (index.html처럼 파일 자체를 웹브라우저에 넘겨줌)  
mvc와 템플릿 엔진 (jsp, php 등 템플릿 엔진. html을 동적으로 바꿔서 넘겨준다)  
api (안드로이드, 아이폰 클라이언트랑 개발할 때. json이라는 데이터 포맷으로 프론트에 데이터 전달해줌 / 서버끼리 통신할 때)

### 정적 컨텐츠
- 스프링부트는 기본적으로 \static에서 파일 자동으로 찾아서 정적 컨텐츠 제공해준다
- http://localhost:8080/hello-static.html static 안에 hello-static.html 만들어서 url 들어가면 자동으로 뜸
- hello-static 관련 컨트롤러가 없음 -> resources\static 에 hello-static이라는 파일 있는지 찾음
- ViewResolver 사용 X

## 3-2 MVC와 템플릿 엔진
- 예전에는 model, view 분리 없이 view에서 모든 걸 다 함
- 하지만 역할, 분야가 다르기 때문에 분리하는 것이 더 효율적 -> MVC 패턴 사용하는 이유
  - Controller : 비즈니스 로직과 관련
  - View : 화면 그리는 데만 집중 !!
- 템플릿 엔진: html 내용 바꿀 수 있다
- ViewResolver가 html 변환 후 웹브라우저에 넘겨준다

## 3-3 API
- View 없음! 데이터 그대로 클라이언트에게 전달
- @ResponseBody : json 형태로 데이터 반환 (이 어노테이션 없으면 ViewResolver 로 던진다)
- 참고) JavaBean 규약 - 객체의 멤버변수를 외부에서 접근할 수 없도록 getter, setter 사용하자 (프로퍼티 접근 방식)
- 객체를 return : 스프링이 json으로 만들어서 http body에 반환한다 (JsonConverter 동작)
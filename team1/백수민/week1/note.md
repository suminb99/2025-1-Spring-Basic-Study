## 섹션 2. 프로젝트 환경설정
- maven, **gradle**: 빌드 관리 도구(Build tool), 의존 관계를 관리해줌
- 라이브러리:
  - spring web
  - thymeleaf
- src 폴더: main, test 폴더로 나누어져 있음
- main/resources: 실제 자바코드 파일을 제외한 xml, properties 등과 같은 설정 파일이 들어가 있음
- test: test code와 관련된 소스들이 들어가 있음
- 현재로서는 gradle이 버전 설정과 라이브러리를 가져온다고 이해하면 됨
- 요즘에는 logging을 **logback** (로그 출력 구현체) & **slf4j** (출력 인터페이스) => 이 두가지 조합을 표준으로 운영함
- spring-boot-starter-logging을 땡기면 위 두가지가 자동으로 땡겨짐
- 테스트 관련 라이브러리: JUnit - 자바 진영에서 사용하는 테스트 라이브러리 (기본)
<br>

터미널에서 빌드하고 실행하는 방법:

<img src="https://raw.githubusercontent.com/suminb99/2025-1-Spring-Basic-Study/refs/heads/main/team1/%EB%B0%B1%EC%88%98%EB%AF%BC/week1/images/Screenshot%202025-04-26%20at%2016.26.12.png" height="300x" width="500px"/>

1. `./gradlew build`
2. `cd build/libs`
3. `java -jar hello-spring-0.0.1-SNAPSHOT.jar` 
4. 실행확인

서버에 배포할 때는 **hello-spring-0.0.1-SNAPSHOT.jar** 파일만 복사해서 서버에 넣어주면 된다

<br>

## 섹션 3. 스프링 웹 개발 기초

- **정적 컨텐츠**: 서버에서 별도의 처리 없이 웹 브라우저로 파일을 그대로 내려주는 것 (eg: welcome page)
- **MVC와 템플릿**: JSP, PHP처럼 서버에서 프로그래밍하여 HTML을 동적으로 변환하여 내려주는 것 (eg: hello page)
- **API**: JSON 데이터 형식으로 클라이언트에게 데이터를 전달하는 것
<br>

**1. 정적 컨텐츠**
- 스프링 부트는 정적 컨텐츠 기능을 자동으로 제공함
- 정적 컨텐츠를 **/static**에서 찾아서 웹 브라우저에게 제공
1. 웹 브라우저에서 내장 톰캣 서버에게 요청을 보냄 (hello-static.html 파일)
2. 우선순위가 높은 Controller에서 hello-static.html 파일이 있는지 확인
3. 파일이 없으면 Resource 폴더에서 탐색
4. hello-static.html 파일을 찾으면, 브라우저에 반환.
<br>

**2. MVC와 템플릿 엔진**
- **MVC: Model, View, Controller**
  - 과거에는 Controller와 View가 분리되지 않은 Model 1 방식을 사용
  - **View**: 화면을 그리는데 모든 역량을 집중 (비즈니스 로직과는 분리됨)
  - **Controller**: 비즈니스 로직과 서버 내의 처리를 담당
  - **Model**: 화면에서 필요한 데이터를 담아서 View로 전달
- 템플릿 엔진은 HTML을 동적으로 변환하여 브라우저에 넘겨줌
- **Thymeleaf**의 장점: HTML 파일의 absolute path를 브라우저 주소창에 직접 입력하면 서버 없이도 열어볼 수 있음
- **ViewResolver**: View를 찾아주고 템플릿 엔진을 연결시켜주는 역할을 함
<br>

**3. API**
- API는 화면(View)을 전달하지 않음.
- **@ResponseBody**: HTTP 응답의 Body에 데이터(문자/객체)를 직접 반환.
- 객체는 JSON 형식으로 변환되어 HTTP 응답 본문에 포함된다

`@ResponseBody` 를 사용
- HTTP의 BODY에 문자 내용을 직접 반환
- `viewResolver` 대신에 `HttpMessageConverter` 가 동작
- 기본 문자처리: `StringHttpMessageConverter`
- 기본 객체처리: `MappingJackson2HttpMessageConverter`
- byte 처리 등등 기타 여러 HttpMessageConverter가 기본으로 등록되어 있음

<br>

자동 완성 단축키: command + shift + enter

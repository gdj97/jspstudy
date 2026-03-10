<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- /src/main/webapp/ex12_el/ex01_el.jsp
 === EL : Expression Language
   1. ${속성이름} : 속성값을 출력함  
   2. JSP에서 사용이 가능한 또 다른 언어임. 표현식의 대체 언어
   3. EL의 내장객체가 따로 존재함
      - 파라미터값 : ${param.파라미터이름}  
      - 속성      : ${영역관련내장객체.속성명}. 영역관련 내장객체이름은 생략 가능함.
   4. 일반변수는 EL로 표현 불가
   5. 값이 없는 경우 null이 아닌 빈문자열로 출력됨.
   
 === 속성값 출력
   ${속성이름} : 영역담당 객체 중 등록 된 속성 중 속성의 이름이 같은 속성값을 출력 
   우선순위
     1. page 영역에 등록된 속성이름의 값을 출력
     2. 1이 없으면 request 영역에 등록된 속성 이름의 값을 출력
     3. 1,2이 없으면 session 영역에 등록된 속성 이름의 값을 출력
     4. 1,2,3이 없으면 application 영역에 등록된 속성 이름의 값을 출력
     5. 1~4이 없으면 공란을 출력함
        
   ${영역내장객체.속성이름}
     1. 영역내장객체의 속성이름의 값을 출력
     2. 1이 없으면 공란을 출력함     
--%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
    String tel = "010-1111-2222";
//    pageContext.setAttribute("tel",tel);  //속성 등록
    request.setAttribute("tel",tel);  //속성 등록
	pageContext.setAttribute("test","pageContext의 test 속성"); //페이지영역 test 속성 등록
	request.setAttribute("test","request의 test 속성");         //request영역 test 속성 등록
	session.setAttribute("test","session의 test 속성");         //session영역 test 속성 등록
	application.setAttribute("test","application의 test 속성"); //application 영역 test 속성 등록
	String name = "홍길동";
%>
<h3>JSP의 스크립트를 이용하여 파라미터와 속성값 출력하기 </h3>
	pageContext tel 속성값 : <%=pageContext.getAttribute("tel") %><br>
	pageContext test 속성값 : <%=pageContext.getAttribute("test") %><br>
	request test 속성값 : <%=request.getAttribute("test") %><br>
	session test 속성값 : <%=session.getAttribute("test") %><br>
	application test 속성값 : <%=application.getAttribute("test") %><br>
	name 변수값 : <%=name %><br>
	id 파라미터 : <%=request.getParameter("id") %><br>
	없는 속성 : <%=request.getAttribute("noattr") %><br>
	없는 파라미터 : <%=request.getParameter("noparam") %><br>   
<hr>
<h3>JSP의 EL를 이용하여 파라미터와 속성값 출력하기 </h3>
	pageContext tel 속성값 : ${pageScope.tel}<br>
	pageContext test 속성값 : ${pageScope.test}<br>
	request test 속성값 : ${requestScope.test }<br>
	session test 속성값 : ${sessionScope.test }<br>
	application test 속성값 : ${applicationScope.test}<br>
	name 변수값 : EL 방식으로 출력 못함<br>
	id 파라미터 : ${param.id}<br>
	없는 속성 : ${requestScope.noattr}<br>
	없는 파라미터 : ${param.noparam}<br>  
<hr>
<h3>영역을 표시하여 속성 출력하기</h3>
\${test} : ${test}<br>	 <%-- 속성 중 이름이 test 속성의 값을 출력 --%>
\${pageScope.test} : ${pageScope.test}<br>       <%-- pageContext 객체의 test 속성 값 출력 --%>
\${requestScope.test} : ${requestScope.test}<br>
\${sessionScope.test} : ${sessionScope.test}<br>
\${applicationScope.test} : ${applicationScope.test}<br>
\${tel} : ${tel}<br>	 <%-- 속성 중 이름이 tel 속성의 값을 출력 --%>
\${pageScope.tel} : ${pageScope.tel}<br>       <%-- pageContext 객체의 tel 속성 값 출력 --%>
\${requestScope.tel} : ${requestScope.tel}<br>
\${sessionScope.tel} : ${sessionScope.tel}<br>
\${applicationScope.tel} : ${applicationScope.tel}<br>
</body></html>
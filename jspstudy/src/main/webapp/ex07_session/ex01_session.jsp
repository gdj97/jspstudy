<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- /src/main/webapp/ex07_session/ex01_session.jsp --%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
    //세션유지시간 설정. 초단위.
    //톰캣의 경우 기본적인 세션유지시간 30분
	session.setMaxInactiveInterval(10);
%>
<h3>session 정보 : 브라우저별로 session이 할당됨<br>
주요기능 : 클라이언트의 정보를 저장</h3>
isNew() : <%=session.isNew() %><br> <%-- 새로운 세션?  --%>
생성시간 : <%=session.getCreationTime() %><br> <%-- session 생성시간. 1970년이후부터 현재까지 밀리초 --%>
최종접속시간 : <%=session.getLastAccessedTime() %><br><%-- session 최종접속시간. 1970년이후부터 현재까지 밀리초 --%>
session id:<%=session.getId() %><br><%-- session ID값. session 별로 다른 ID값을 가짐 --%>
</body>
</html>
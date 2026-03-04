<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- /src/main/webapp/ex04_request/ex03_client.jsp --%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h3>클라이언트 정보</h3>
로컬주소:<%=request.getLocalAddr() %><br>
클라이언트주소:<%=request.getRemoteAddr() %><br>
클라이언트주소:<%=request.getRemoteHost() %><br>
요청메서드 : <%=request.getMethod() %><br>
요청URI : <%=request.getRequestURI() %><br>
요청URL : <%=request.getRequestURL() %><br>
웹어플리케이션이름:<%=request.getContextPath() %><br>
서버이름:<%=request.getServerName() %><br>
서버포트:<%=request.getServerPort() %><br>
<%--
   네트워크 IP 주소 표현
   IPv4 : 32비트 표현
   IPv6 : 128비트 표현
 --%>
</body>
</html>
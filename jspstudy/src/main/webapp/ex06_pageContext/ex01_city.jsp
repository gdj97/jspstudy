<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- /src/main/webapp/ex06_pageContext/ex01_city.jsp --%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	String city = request.getParameter("city");//seoul
	pageContext.forward("ex01_"+city+".jsp");//ex01_seoul.jsp
%>
</body>
</html>
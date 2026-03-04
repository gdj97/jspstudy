<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isErrorPage="true" %> <%-- 현재 페이지가 오류페이지임. exception 객체 제공 --%>    
<%-- /src/main/webapp/ex08_other/ex04_errorPage.jsp --%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>입력값을 확인하세요:숫자만 가능합니다.</h1>
<p><%=exception.getMessage() %></p>
<hr>
<p>
<% exception.printStackTrace(response.getWriter()); %>
</p>
</body>
</html>
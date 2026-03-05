<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- /src/main/webapp/ex08_cookie/ex02_read.jsp --%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	String name="";
	String value="";
	Cookie[] cookies = request.getCookies(); //요청시 서버에서 생성된 쿠키를 서버로 전달함.
	for(Cookie c : cookies) {
		if(c.getName().equals("name")) {
			name = c.getName();
			value= c.getValue();
		}
	}
%>
<h2>쿠키이름 = <%=name %></h2>
<h2>쿠키값 = <%=value %></h2>

</body>
</html>
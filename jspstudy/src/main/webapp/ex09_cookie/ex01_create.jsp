<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- /src/main/webapp/ex08_cookie/ex01_create.jsp --%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
   Cookie cookie = new Cookie("name","hongkildong"); //쿠키생성. (이름,값)
   cookie.setMaxAge(600); //유효기간. 60*10 초. 10분으로 등록
   response.addCookie(cookie); //쿠키를 브라우저로 전송
%>
<h2>쿠키이름 : <%=cookie.getName() %><br>
    쿠키값 : <%=cookie.getValue() %><br>
    쿠키유효기간 : <%=cookie.getMaxAge() %><br>
</h2>
<a href="ex02_read.jsp">쿠키값 불러오기</a>
</body>
</html>
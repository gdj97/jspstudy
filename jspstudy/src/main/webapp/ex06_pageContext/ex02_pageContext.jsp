<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- /src/main/webapp/ex06_pageContext/ex02_pageContext.jsp --%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>pageContext</title>
</head>
<body>
<h2>pageContext 객체 : page 영역을 담당하는 객체. 현재 페이지의 자원을 관리한다.</h2>
<%
   if(request == pageContext.getRequest()) { %>
		request 객체와 pageContext.getRequest()의 리턴 객체는 같은 객체임<br>		
<% } %>
<%
   if(response == pageContext.getResponse()) { %>
		response 객체와 pageContext.getResponse()의 리턴 객체는 같은 객체임<br>		
<% } %>
<%
   if(session == pageContext.getSession()) { %>
		session 객체와 pageContext.getSession()의 리턴 객체는 같은 객체임<br>		
<% } %>
<%
   if(application == pageContext.getServletContext()) { %>
		application 객체와 pageContext.getServletContext()의 리턴 객체는 같은 객체임<br>		
<% } %>
<%
   if(out == pageContext.getOut()) { %>
		out 객체와 pageContext.getOut()의 리턴 객체는 같은 객체임<br>		
<% } %>

</body>
</html>
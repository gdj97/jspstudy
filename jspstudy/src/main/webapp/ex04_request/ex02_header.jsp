<%@page import="java.util.Enumeration"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- /src/main/webapp/ex04_request/ex02_header.jsp
     String request.getHeader(헤더이름) : 헤더이름에 해당하는 값 리턴
     Enumeration request.getHeaderNames() : 모든 헤더이름 목록
 --%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h3>요청 헤더 정보 보기</h3>
<table border="1">
  <tr><th>헤더이름</th><th>헤더값</th></tr>
  <%
     Enumeration e = request.getHeaderNames();
     while (e.hasMoreElements()) {
   	   String hname = (String)e.nextElement();
  %>
  <tr><td><%=hname %></td>
    <td><%=request.getHeader(hname) %></td>
  </tr>
  <% } %>
</table>
</body>
</html>
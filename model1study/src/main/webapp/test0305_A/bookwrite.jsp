<%@page import="model.BookDao"%>
<%@page import="model.Book"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>방명록 결과 보기</title>
</head>
<body>
<%
    request.setCharacterEncoding("UTF-8");

    Book book = new Book();
    
    book.setWriter(request.getParameter("writer"));
    book.setTitle(request.getParameter("title"));
    book.setContent(request.getParameter("content"));
    BookDao dao = new BookDao();
    if(dao.insert(book)) {
%>    	
<table>
     <tr><td>이름</td><td><%= book.getWriter() %></td></tr>
     <tr><td>제목</td><td><%= book.getTitle() %></td></tr>
     <tr><td>내용</td><td><%= book.getContent() %></td></tr>
</table>
<% } %>
</body>
</html>
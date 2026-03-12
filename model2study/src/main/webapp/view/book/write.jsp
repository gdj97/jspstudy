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
 		response.sendRedirect("booklist.jsp");   	
    }
 %>
</body>
</html>
<%@page import="model.BookDao"%>
<%@page import="model.Book"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	Book b = new Book();
	request.setCharacterEncoding("UTF-8");
	b.setNo(Integer.parseInt(request.getParameter("no")));
	b.setWriter(request.getParameter("writer"));
	b.setTitle(request.getParameter("title"));
	b.setContent(request.getParameter("content"));
	BookDao dao = new BookDao();
	dao.update(b);
%>
<script>
	alert("수정완료")
	location.href="bookinfo.jsp?no=<%=b.getNo()%>";
</script>
</body>
</html>
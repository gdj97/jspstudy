<%@page import="model.BookDao"%>
<%@page import="model.Book"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>방문 목록 조회</title>
<link rel="stylesheet" href="../css/style.css">

</head>
<body>
	<table>
		<caption>방명록</caption>
		<tr><th>번호</th><th>글쓴이</th><th>제목</th><th>&nbsp;</th>	</tr>
		<%
		List<Book> list = new BookDao().list();
		for (Book b : list) {
		%>
		<tr>
			<td><%=b.getNo()%></td>
			<td><%=b.getWriter()%></td>
			<td><a href="bookinfo.jsp?no=<%=b.getNo()%>"><%=b.getTitle()%></a></td>
			<td><a href="bookupdateform.jsp?no=<%=b.getNo()%>">수정</a>
			    <a href="javascript:confirmDelete('<%=b.getNo()%>')">삭제</a></td>
		</tr>
		<%
		}
		%>
		<tr>
			<td colspan="4" style="text-align: right"><a href="bookform.jsp">방명록쓰기</a></td>
		</tr>
	</table>
	<script>
		function confirmDelete(no) {
			if (confirm("이 게시글을 정말 삭제하시겠습니까?")) {
				location.href = "bookdelete.jsp?no=" + no;
			}
		}
	</script>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>	
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
		<c:forEach var="b" items="${list }">
		<tr>
			<td></td>
			<td>${b.writer}</td>
			<td><a href="info?no=${b.no}">${b.title}</a></td>
			<td><a href="bookupdateform?no=${b.no}">수정</a>
			    <a href="javascript:confirmDelete('${b.no}')">삭제</a></td>
		</tr>
		</c:forEach>
		<tr>
			<td colspan="4" style="text-align: right"><a href="writeform">방명록쓰기</a></td>
		</tr>
	</table>
	<script>
		function confirmDelete(no) {
			if (confirm("이 게시글을 정말 삭제하시겠습니까?")) {
				location.href = "delete?no=" + no;
			}
		}
	</script>
</body>
</html>
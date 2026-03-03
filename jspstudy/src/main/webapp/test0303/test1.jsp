<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>피자 주문 양식</h1>
	<h2>오늘의 날짜</h2>
<%--	<h4><%=todayStr%></h4>  --%>
	<form action="<%=request.getContextPath()%>/order" method="post">
		<fieldset>
			<legend>주문자정보</legend>
			<table>
				<tr>
					<th>이름</th>
					<td><input type="text" name="userName" required></td>
				</tr>
				<tr>
					<th>전화번호</th>
					<td><input type="text" name="phone" required></td>
				</tr>
				<tr>
					<th>주소</th>
					<td><input type="text" name="address" required></td>
				</tr>
				<tr>
					<th>요청사항</th>
					<td><textarea name="message"></textarea></td>
				</tr>
			</table>
		</fieldset>

		<br> <input type="submit" value="주문"> 
		<input type="reset">
	</form>

</body>
</html>
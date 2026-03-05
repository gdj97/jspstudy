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
request.setCharacterEncoding("UTF-8"); 
String pizza = request.getParameter("pizza");
String[] toppings = request.getParameterValues("topping");
String[] sides = request.getParameterValues("side"); 
String payment = request.getParameter("payment");
%>
	<h1>주문 내역</h1>
	<h4>[ 주문 정보 ]</h4>
	<ul>
		<li>피자명 : <%= pizza %></li>
		<li>토핑 : <%= toppings == null ? "선택안함" : String.join(", ", toppings) %></li>
		<li>사이드 : <%= sides == null ? "선택안함" : String.join(", ", sides)  %> </li>
	</ul>
	<br>
	<h3>위와 같이 주문하셨습니다. </h3>
	
	<h2>결제 방식 : <%= payment.equals("card") ? "카드" : "현금" %></h2>
</body>
</html>
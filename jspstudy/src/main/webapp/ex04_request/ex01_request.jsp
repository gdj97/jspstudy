<%@page import="java.util.Enumeration"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- /src/main/webapp/ex04_request/ex01_request.jsp
   String      request.getParameter("파라미터명") : 한개의 파라미터값 리턴  
   String[]    request.getParameterValues("파라미터명") : 여러개의 파라미터값을 배열로 리턴
   Enumeration request.getParameterNames()      : 파라미터이름 목록 리턴
--%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>파라미터값 출력</title>
</head>
<body>
<%
	//파라미터 데이터의 인코딩 설정필요. 처음에 실행 해야함
	//jsp에서는 기본 인코딩을 ISO8859로 인식함.
	request.setCharacterEncoding("UTF-8");
	String name = request.getParameter("name");   //홍길동
	String age = request.getParameter("age");     //20
	String gender= request.getParameter("gender");//1
	String hobby = request.getParameter("hobby"); //여행,독서,운동
	String year = request.getParameter("year");   //1997
%>
이름:<%=name %><br>
나이:<%=age %><br>
성별:<%=gender.equals("1")?"남":"여" %><br><%-- 표현식에서 수식 가능 --%>
취미:<%=hobby %><br>
출생년도:<%=year %><br>
<hr>
<h3>모든 취미 정보 출력하기</h3>
<%
	String[] hobbies = request.getParameterValues("hobby");
	for (String h : hobbies) {
%>
<%=h %>&emsp;&emsp;&emsp; <%-- for 구문 내부 출력 --%>
<% } %>
<hr>
<h3>모든 파라미터 정보 출력하기</h3>
<table><tr><th>파라미터이름</th><th>파라미터값</th></tr>
<%
//Enumeration : 반복자. Iterator 구버전.
	Enumeration e =request.getParameterNames(); //파라미터이름목록
	while(e.hasMoreElements()) {
		String pname = (String)e.nextElement(); //pname : 파라미터이름
		String[] values = request.getParameterValues(pname); //모든 파라미터값
%>
<tr><td><%=pname %></td>
 	<td><% for (String s : values) {%>
 		<%=s %>&emsp;
 		<% } %>
 	</td></tr>		
<% } %></table>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- /src/main/webapp/ex08_other/ex03_out.jsp --%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h3>out 객체는 response 객체의 출력버퍼에 데이터를 출력할 수 있는 출력 스트림 객체임</h3>
<h4>1~10까지의 부분합과 전체 합 출력하기</h4>
<%
	int sum = 0;
	for(int i=1;i<=10;i++) {
		sum += i;
	%>
	1부터 <%=i %>까지의 부분합 : <%=sum %><br> 
<% } %>	
1부터10까지의 전체합: <%=sum %><br>
<hr>
<%
	sum = 0;
	for(int i=1;i<=10;i++) {
		sum += i;
		out.println("1부터" + i+"까지의 부분합 :" + sum + "<br>");
	}
	out.println("1부터10까지의 전체합:" + sum);
%>
</body>
</html>
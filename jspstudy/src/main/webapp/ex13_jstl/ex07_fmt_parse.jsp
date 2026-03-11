<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- /src/main/webapp/ex13_jstl/ex07_fmt_parse.jsp --%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h3>Format 된 날짜를 날짜형으로 변경하기</h3>
<fmt:parseDate value="2026-12-25 12:00:00" pattern="yyyy-MM-dd HH:mm:ss" var="day" />
${day}<br>
<fmt:parseDate value="2026-12-25 12:00:00" pattern="yyyy-MM-dd HH:mm:ss" /><br>
<p>day 변수를 이용하여 2026-12-25 금요일 출력하기 </p>
<fmt:formatDate value="${day}" pattern="yyyy-MM-dd E요일"/>
<p>day 변수를 이용하여 2026-12-26 금요일 출력하기 </p>
<fmt:parseDate value="2026-12-26" pattern="yyyy-MM-dd" var="day" />
<fmt:formatDate value="${day}" pattern="yyyy-MM-dd E요일"/>
</body>
</html>
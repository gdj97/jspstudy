<%@page import="model.MemberDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- /webapp/view/member/pw.jsp--%>
<!DOCTYPE html>
<html><head>
<meta charset="UTF-8">
<title>비밀번호찾기</title>
<link rel="stylesheet" href="../css/main.css">
</head><body>
<table>
  <tr><th>비밀번호</th><td>**${pass}</td></tr>
  <tr><td colspan="2">
     <input type="button" value="닫기" onclick="self.close();">
  </td></tr>
</table></body></html>
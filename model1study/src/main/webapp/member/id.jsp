<%@page import="model.MemberDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- /src/main/webapp/member/id.jsp
  1. 파라미터값 (email,tel) 저장
  2. db에서 두개의 파라미터를 이용하여 id값을 리턴해주는 함수
     String  MemberDao.idSearch(email,tel)
  3. id 존재 : 화면 뒤쪽 2자를 ** 표시하여 화면에 출력하기
              아이디전송 버튼을 클릭하면 opener 창에 id 입력란에 전달, 
              id.jsp 화면을 닫기
     id 없음 : id가 없습니다. 현재화면 idForm.jsp 페이지로 이동             
--%>
<%
   // 1. 파라미터값 (email,tel) 저장
   String email = request.getParameter("email");
   String tel = request.getParameter("tel");
   //2. db에서 두개의 파라미터를 이용하여 id값을 리턴해주는 함수
   String id = new MemberDao().idSearch(email,tel);
   if (id == null) { //조건을 만족하는 id 검색 실패
%>
<script>
   alert("아이디를 찾을 수 없습니다.");
   location.href = "idForm.jsp";
</script>
<% }  else { //아이디 검색 성공 %>
<!DOCTYPE html>
<html><head><meta charset="UTF-8">
<title>아이디 찾기</title>
<link rel="stylesheet" href="../css/main.css">
</head>
<body><table>
  <tr><th>아이디</th><td><%=id.substring(0,id.length()-2)+"**" %></td></tr>
  <tr><td colspan="2">
     <input type="button" value="아이디전송"
         onclick="idsend('<%=id.substring(0,id.length()-2) %>')">
  </td></tr>
</table>
<script type="text/javascript">
  function idsend(id) {
	  opener.document.f.id.value = id;
	  self.close();
  }
</script></body></html><% } %>
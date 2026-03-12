<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- /src/main/webapp/view/member/passwordForm.jsp --%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>비밀번호 변경</title>
<link rel="stylesheet" href="../css/main.css">
</head>
<body>
  <h3 align="center">비밀번호 변경</h3>
  <form action="password" method="post" name="f" onsubmit="return input_check(this)">
  <table class="table">
     <tr><th>현재비밀번호</th><td><input type="password" name="pass"></td></tr>
     <tr><th>변경비밀번호</th><td><input type="password" name="chgpass"></td></tr>
     <tr><th>변경비밀번호재입력</th><td><input type="password" name="chgpass2"></td></tr>
     <tr><td colspan="2" align="center">
         <input type="submit" value="비밀번호변경"><input type="reset" value="초기화"></td></tr>
   </table></form>
  <script>
    function input_check(f) {
       if(f.pass.value == "") {
		   alert("비밀번호를 입력하세요.");
		   f.pass.focus();
		   return false;
       }	
       if(f.chgpass.value == "") {
		   alert("변경 비밀번호를 입력하세요.");
		   f.chgpass.focus();
		   return false;
       }	
 	   if(f.chgpass.value != f.chgpass2.value) {
		   alert("변경비밀번호 와 변경비밀번호재입력이 다릅니다.");
		   f.chgpass2.value="";
		   f.chgpass2.focus();
		   return false;
	   }
 	   return true;
    }
  </script></body></html>
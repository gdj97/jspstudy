<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- /src/main/webapp/member/joinForm.jsp --%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
<link rel="stylesheet" href="../css/main.css">
</head>
<body>
<form action="join.jsp" name="f" method="post" onsubmit="return input_check(this)">
  <input type="hidden" name="picture" value="">
  <table>
    <tr><td rowspan="4" valign="bottom">
       <img src="" width="100" height="120" id="pic"><br>
       <font size="1"><a href="javascript:win_upload()">사진등록</a></font>
    </td><th>아이디</th>
    <td><input type="text" name="id">
    <button type="button" onclick="idchk()">중복검색</button>
    </td></tr>
    <tr><th>비밀번호</th><td><input type="password" name="pass"></td></tr>
    <tr><th>이름</th><td><input type="text" name="name"></td></tr>
    <tr><th>성별</th>
    <td><label for="gender1">남</label>
      <input type="radio" id="gender1" name="gender" value="1" checked>
      <label for="gender2">여</label>
      <input type="radio" id="gender2" name="gender" value="2">
    </td></tr>
    <tr><th>전화번호</th><td colspan="2"><input type="text" name="tel"></td></tr>
    <tr><th>이메일</th><td colspan="2"><input type="text" name="email"></td></tr>
    <tr><td colspan="3"><button>회원가입</button></tr>
  </table></form>
  <script type="text/javascript">
     function input_check(f) {
    	 //f : <form ...>
     	//f.id : form 객체의 하위 태그중 name="id"인 태그
     	//f.id.value : id 태그의 입력된 값. string 객체
    	 if (f.id.value.trim() == "") {
    		 alert("아이디를 입력하세요");
    		 f.id.focus();
    		 return false; //기본 이벤트(submit 이벤트) 취소
    	 }
    	 if (f.pass.value.trim() == "") {
    		 alert("비밀번호를 입력하세요");
    		 f.pass.focus();
    		 return false; 
    	 }
    	 if (f.name.value.trim() == "") {
    		 alert("이름을 입력하세요");
    		 f.name.focus();
    		 return false; 
    	 }
    	 if (!isValidTel(f.tel.value)) {
    		 alert("전화번호 형식이 아닙니다");
    		 f.tel.focus();
    		 return false; 
    	 }
    	 if (!isValidEmail(f.email.value)) {
    		 alert("이메일 형식이 아닙니다");
    		 f.email.focus();
    		 return false; 
    	 }
    	 return true;
     }
   function isValidEmail(email) {
	   const regex = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
	   return regex.test(email);
    }
   function isValidTel(tel) {
	   const regex = /^(02|01[016789])-?\d{3,4}-?\d{4}$/;
	   return regex.test(tel);
    }
   function win_upload() {
	   let op = "width=500,height=500,left=50,top=150";
	   open("pictureForm.jsp","",op);
   }   
  </script>
  </body></html>
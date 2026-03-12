<%@page import="model.MemberDao"%>
<%@page import="model.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- /src/main/webapp/view/member/idchk.jsp --%> 
<!DOCTYPE html>
<html><head><meta charset="UTF-8">
<title>아이디중복검색</title>
<link rel="stylesheet" href="../css/main.css">
<style type="text/css">
   .able { color:green; font-size: 15px;}
   .disable { color:red; font-size: 20px;}
</style>
<body>
<table class="table"> 
  <tr><td>아이디</td><td>${param.id}</td></tr>
  <tr><td colspan="2"><div id="msg" class="${className}">${msg}</div></td></tr>
  <tr><td colspan="2" >
      <button onclick="self.close()" class="btn btn-primary">닫기</button>  
  </td></tr>
</table>    
<script>
//  document.querySelector("#msg").className='${className}'; 
  if('${className}' == 'disable')	  
	 opener.document.f.id.value="";
</script></body></html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%--/src/main/webapp/view/member/picture.jsp--%>    
<script>
   img = opener.document.getElementById("pic");  
   img.src = "../picture/${fname}"; 
   opener.document.f.picture.value = "${fname}"; 
   self.close(); //현재 페이지 close
</script>
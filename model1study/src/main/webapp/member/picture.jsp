<%@page import="com.oreilly.servlet.MultipartRequest"%>
<%@page import="java.io.File"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%--/src/main/webapp/member/picture.jsp
   1. request 객체로 이미지 업로드 불가 => cos.jar  
   2. 이미지 업로드 폴더 : 현재폴더/picture 설정 
   3. opener 화면에 이미지 볼수 있도록 결과 전달 => javascript
   4. 현재 화면 close 하기                  => javascript
--%>    
<%
	//path : 업로드 파일의 위치.
   String path = application.getRealPath("/") + "member/picture/";
   String fname = null;
   File f = new File(path);
   if(!f.exists()) f.mkdirs(); //업로드 폴더 생성
   //업로드 완료. 같은 이름의 이미지가 업로드 시 이름 변경 안됨. 업로드파일의이름 = 원본파일의 이름이 같다. 
   MultipartRequest multi = new MultipartRequest(
		   request,   //이미지 파일 정보 저장
		   path,      //업로드 파일의 위치
		   10*1024*1024, //10M. 업로드 파일의 최대 크기
		   "utf-8"    //인코딩 설정
		   );
   fname = multi.getFilesystemName("picture");  //파일의 이름
%>
<script>
/*
 * opener : 현재 페이지를 popup한 윈도우. joinForm.jsp, updateForm.jsp 페이지 의미
 */
   img = opener.document.getElementById("pic"); // img 태그.  
   img.src = "picture/<%=fname%>";   //현재폴더/picture/이미지이름. opener페이지에 업로드된 이미지가 보여짐
 //opener의 문서중 name="f" 하위에 name="picture" 인 태그의 value 값에 파일이름 저장
 //<input type="hidden" name="picture" value="파일이름"
   opener.document.f.picture.value = "<%=fname%>"; 
   self.close(); //현재 페이지 close
</script>
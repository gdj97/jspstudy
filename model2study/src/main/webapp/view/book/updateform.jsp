<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>방명록수정</title>
<link rel="stylesheet" href="../css/style.css" >
</head>
<body>
<form action="update" method="post"   onsubmit="return inputcheck(this)">
<input type="hidden" name="no" value="${b.no}">
<table><caption>방명록수정</caption>
<tr><td>방문자</td><td><input type="text" name="writer" value="${b.writer}"></td></tr>
<tr><td>제목</td><td><input type="text" name="title" value="${b.title}"></td></tr>
<tr><td>내용</td>
    <td><textarea rows="10" cols="60" name="content">${b.content}</textarea></td></tr>
<tr><td colspan="2" align="center">
     <input type="submit" value="수정하기"></td></tr>
</table></form>

<script type="text/javascript">
   function inputcheck(f) {
       if(f.writer.value == '') {
		   alert("방문자를 입력하세요");
		   f.writer.focus();
		   return false;
       }
       if(f.title.value == '') {
		   alert("제목 입력하세요");
		   f.title.focus();
		   return false;
       }
       if(f.content.value == '') {
		   alert("내용 입력하세요");
		   f.content.focus();
		   return false;
       }
       return true;
   }
</script>
</body>
</html>
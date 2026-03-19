<%@page import="org.jsoup.nodes.Element"%>
<%@page import="org.jsoup.select.Elements"%>
<%@page import="org.jsoup.Jsoup"%>
<%@page import="java.io.IOException"%>
<%@page import="org.jsoup.nodes.Document"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%--
1. 프로젝트 : jsoupstudy
2. /webapp/ex01_exchange.jsp 
3. 3개의 jar 파일을 /WEB-INF/lib/ 폴더에 붙여넣기
4. 브라우저에서 수출입은행 사이트 방문하기 
   : 업무안내 > 환율정보 선택하기
   환율정보 URL : https://www.koreaexim.go.kr/wg/HPHKWG057M01
   
   웹스크래핑(크롤링) : 인터넷에서 제공되는 정보를 가져오기.
 --%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>수출입은행 환율 정보 조회</title>
<style type="text/css">
	table {border-collapse: collapse;}
	table,td,th {border : 2px solid grey; }
</style>
</head>
<body>
<%
	String url = "https://www.koreaexim.go.kr/wg/HPHKWG057M01";
	String line = "";
	Document doc = null;
	try {
		doc = Jsoup.connect(url).get();
		//table 태그들을 검색하여 리턴
		Elements e1 = doc.select("table"); //table태그. .class속성값, #id속성값
		for(Element e2 : e1) {
			//e2 : table 태그 한개
			//e2.html() : html text 내용
			String temp = e2.html();
			System.out.println(temp);
			line += temp;
		}
	}catch(IOException e) {
		e.printStackTrace();
	}
%>
<table><%=line %></table>
</body></html>
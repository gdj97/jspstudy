<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="org.jsoup.nodes.Element"%>
<%@page import="org.jsoup.select.Elements"%>
<%@page import="org.jsoup.Jsoup"%>
<%@page import="org.jsoup.nodes.Document"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- /webapp/ex03_naver.jsp --%> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>네이버 시장지표</title>
</head>
<body>
<%
  String url = "https://finance.naver.com/marketindex/";
  Document doc = null;  
  try {
	   doc = Jsoup.connect(url).get();
	   Elements hlist = doc.select("div.head_info");
	   Elements htitle = doc.select("h3.h_lst");
	   for (int i=0;i<hlist.size();i++) {
		   Element tag = hlist.get(i);
		   Element title = htitle.get(i);
		   System.out.println(tag);
		   System.out.println(title);
		   System.out.println("===========");
		   String name = title.selectFirst("span.blind").html();
		   out.print(name + ":");
		   String value = tag.selectFirst("span.value").html();
		   out.print(value + "&nbsp;&nbsp;");
           String change = tag.selectFirst("span.change").html();
		   out.print(change + "&nbsp;&nbsp;");
		   Elements  blinds = tag.select("span.blind"); 
		   Element blind = blinds.get(blinds.size() - 1);
		   out.print(blind + "<br>");
		   double d=0;
		   System.out.println(blind + "===========");
		   if(blind.toString().trim().contains("하락")) {
			   d = Double.parseDouble(change.replace(",", "")) * -1;
		   } else {
			   d = Double.parseDouble(change.replace(",", ""));
		   }
	   }
   } catch(Exception e) {
	   e.printStackTrace();
   }
%>
</body>
</html>
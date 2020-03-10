<%@page import="bean.BoardVO"%>
<%@page import="DBPKG.DaoBoard"%>
<%@page import="java.util.List"%>
<%@ page language="java" pageEncoding="UTF-8" 
		contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
table {border: 1px solid black;border-collapse: collapse;}
tr,th,td{padding: 20px 50px;font-size: 20px;}
tr:hover{cursor:pointer;}
</style>
<script>
function godetail(num){
	location.href="./BoardDetailAction.bo?num="+num;
}
</script>
</head>
<body>
<%
List list = (List) request.getAttribute("boardList");
int listCnt = (int) request.getAttribute("listCnt");
int nowPage = (int) request.getAttribute("page");
int maxPage = (int) request.getAttribute("maxPage");
int startPage = (int) request.getAttribute("startPage");
int endPage = (int) request.getAttribute("endPage");
%>
<a href="./BoardWrite.bo">글쓰기</a>
<table border=1>
<tr>
<th>번호</th><th>제목</th><th>작성자</th><th>날짜</th><th>조회수</th>
</tr>
<%
if(listCnt>0){
	for(int i=0;i<list.size();i++){
		BoardVO b = (BoardVO)list.get(i);
		out.write("<tr onclick='godetail("+b.getNum()+")'>");
		out.write("<td>"+b.getNum()+"</td>");
		out.write("<td>"+b.getSubject()+"</td>");
		out.write("<td>"+b.getName()+"</td>");
		out.write("<td>"+b.getRegdate()+"</td>");
		out.write("<td>"+b.getReadcount()+"</td>");
		out.write("</tr>");
	}
	out.write("<tr>");
	out.write("<td colspan='5' style='text-align:center;'>");
	if(nowPage <= 1){
		out.write("[이전] ");
	} else {
		out.write("<a href='./BoardList.bo?page="+(nowPage-1)+"'>");
		out.write("[이전]");
		out.write("</a> ");
	}
	for(int i=startPage;i<=endPage;i++){
		if(nowPage==i){
			out.write(String.valueOf(i+" "));
		} else {
			out.write("<a href='./BoardList.bo?page="+i+"'>"+i+"</a> ");
		}
	}
	if(nowPage>=maxPage){
		out.write("[다음] ");
	} else {
		out.write("<a href='./BoardList.bo?page="+(nowPage+1)+"'>");
		out.write("[다음] ");
		out.write("</a>");
	}

	out.write("</td>");
	out.write("</tr>");
} else {
	out.write("<td colspan='5'>등록된 글이 없습니다.</td>");
}
%>
</table>
</body>
</html>
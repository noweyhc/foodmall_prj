<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h2>공지사항 목록</h2>
<a href="insertNotice.do">글 쓰기</a>
<hr>
<table border="1" width="80%">
	<tr>
		<td>글번호</td>
		<td>글제목</td>
		<td>등록일</td>
		<td>조회수</td>
		<td>관리자</td>

	</tr>
			
	 <c:forEach var="n" items="${list }">
		<tr>
			<td>${n.notice_no }</td>
			<td><a href="detailNotice.do?no=${n.notice_no }">${n.notice_title }</a></td>
			<td>${n.notice_regdate }</td>
			<td>${n.notice_hit }</td>
			<td>${n.notice_manager }</td>
		</tr>
	</c:forEach> 
</table>

<c:forEach var="i" begin="1" end="${totalPage }">
	<a href="listNotice.do?pageNUM=${i }">${i }</a>
</c:forEach>

</body>
</html>









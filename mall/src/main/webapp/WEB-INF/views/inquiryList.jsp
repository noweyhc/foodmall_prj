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
	
<h2>반찬가게 CS문의</h2>	
	
	<table border="1" width="80%">
		<tr>
			<th>문의번호</th>
			<th>문의제목</th>
			<th>분류</th>
			<th>소분류</th>
			<th>작성자</th>
			<th>작성일</th>
		</tr>
		
	<c:forEach var="l" items="${list }">
		<tr>
			<td>${l.cs_no }</td>
			<td><a href="inquiryDetail.do?cs_no=${l.cs_no }">${l.cs_title }</a></td>
			<td>${l.cs_category_one }</td>
			<td>${l.cs_category_two }</td>
			<td>${l.cs_mem_id }</td>		
			<td>${l.cs_regdate }</td>
		</tr>
	</c:forEach>
	</table>
	
	<c:forEach var="i" begin="1" end="${totalPage }">
		<a href="inquiryList.do?pageNUM=${i }">${i }</a>
	</c:forEach>
	
	<input type="text" id="keyword">
	<button id="btnSearch">검색</button>
	
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script>
</script>
</body>
</html>
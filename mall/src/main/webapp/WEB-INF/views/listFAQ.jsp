<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>FAQ 목록</h2>
	<a href="insertFAQ.do">글 쓰기</a>
	<hr>
	<table border="1" width="80%">
		<thead>
			<tr>
				<td>글번호</td>
				<td>글제목</td>
				<td>등록일</td>
				<td>조회수</td>
				<td>관리자</td>

			</tr>
		</thead>

		<tbody id="listFAQBody">
		</tbody>
		<c:forEach var="f" items="${list }">
			<tr>
				<td>${f.faq_no }</td>
				<td><a href="detailFAQ.do?no=${f.faq_no }">${f.faq_title }</a></td>
				<td>${f.faq_regdate }</td>
				<td>${f.faq_hit }</td>
				<td>${f.faq_manager }</td>
			</tr>
		</c:forEach>
	</table>

	<c:forEach var="i" begin="1" end="${totalPage }">
		<a href="listFAQ.do?pageNUM=${i }">${i }</a>
	</c:forEach>

	<input type="text" id="keyword" placeholder="검색할 상품명을 입력">
	<button id="searchBtn">검색</button>


</body>
</body>
</html>









<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="header.jsp" %>
<%@ include file="menubar.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">

	
    <link rel="stylesheet" href="static/css/listProductsStyle.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css" rel="stylesheet"
    integrity="sha384-BmbxuPwQa2lc/FVzBcNJ7UAyJxM6wuqIj61tLrc4wSX0szH/Ev+nYRRuWlolflfl" crossorigin="anonymous">  
	<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@400;500;700&display=swap" rel="stylesheet">


<!-- Compiled and minified CSS -->

<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">

<!-- Compiled and minified JavaScript -->
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>
<link rel="stylesheet" href="/static/css/myPage/faq.css">
<!-- css -->
<link rel="stylesheet" href="/static/css/myPage/wrap.css">
<link rel="stylesheet" href="/static/css/myPage/faqList.css">
</head>
<body>
	<div class="wrap">

		<h2>FAQ 목록</h2>
		<hr>
		<table class="highlight">
			<thead>
				<tr>
					<th>글번호</th>
					<th>글제목</th>
					<th>등록일</th>
					<th>조회수</th>
					<th>관리자</th>
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

		<!-- Pagination  -->
		<ul class="pagination" id="page">
			<c:forEach var="i" begin="1" end="${totalPage }">
				<li class="active" class="waves-effect"><a
					href="listFAQ.do?pageNUM=${i }">${i }</a></li>
			</c:forEach>
		</ul>

		<!-- 관리자로 갈 글쓰기
		<div class="row">
			<button class="btn waves-effect waves-light" type="button"
				name="insert" onclick="location.href='insertFAQ.do' ">글쓰기</button>
		</div> -->

	<div>
		<div class="row">
			<div class="input-field col s12">
				<form action="">
					<%-- <input type="hidden" name="boardCode" value="${param.boardCode }"> --%>
					<input type="hidden" name="page" value="1"> 
					<select
						name="searchType">
						<option value="Notice_TITLE">제목</option>
						<option value="Notice_CONTENT">내용</option>
						<option value="all">제목+내용</option>
					</select> 

					<label>Materialize Select</label>
					
					<input type="text" name="searchKeyword" placeholder="검색어 입력"> 
					<input class="btn waves-effect waves-light" type="submit" value="검색">
				</form>
			</div>
		</div>
	</div>

	</div>
	
	<%@ include file="footer.jsp"%>
</body>
</body>
</html>









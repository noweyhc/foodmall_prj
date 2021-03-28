<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
    <!-- Compiled and minified CSS -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">

    <!-- Compiled and minified JavaScript -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>
    <link rel="stylesheet" href="/static/css/myPage/myInquiry.css">
    <!-- css -->
    <link rel="stylesheet" href="/static/css/myPage/wrap.css">
	<link rel="stylesheet" href="/static/css/myPage/inquiryList.css">
</head>
<body>
	<div class="wrap">
		<h2>반찬가게 CS문의</h2>	
			<hr>
			<table class="highlight">
				<thead>
					<tr>
						<th>문의번호</th>
						<th>문의제목</th>
						<th>분류</th>
						<th>소분류</th>
						<th>작성자</th>
						<th>작성일</th>
						<th>답변처리</th>
					</tr>
				</thead>
				<tbody>
						<c:forEach var="l" items="${list }">
							<tr>
								<td>${l.cs_no }</td>
								<td id="cs_title"><a href="inquiryDetail.do?cs_no=${l.cs_no }">${l.cs_title }</a></td>
								<td>[${l.cs_category_one }]</td>
								<td>[${l.cs_category_two }]</td>
								<td>${l.cs_mem_id }</td>		
								<td>${l.cs_regdate }</td>
								<td id="cs_response" >${l.cs_response }</td>
							</tr>
						</c:forEach>
				</tbody>
			</table>
			
			<c:if test="${empty list}">
				<h2 id="none_list">아직 작성 된 문의 글이 없습니다. :( </h2>
			</c:if>	
<%-- 			
			<c:forEach var="i" begin="1" end="${totalPage }">
				<a href="inquiryList.do?pageNUM=${i }">${i }</a>
			</c:forEach> --%>
		    <!-- Pagination  -->
			<ul class="pagination" id="page">
			    <c:forEach var="i" begin="1" end="${totalPage }">
			    	<li class="active" class="waves-effect"><a href="inquiryList.do?pageNUM=${i }">${i }</a></li>
			    </c:forEach>
			</ul>
			
			
			
	</div>

<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

</body>
</html>
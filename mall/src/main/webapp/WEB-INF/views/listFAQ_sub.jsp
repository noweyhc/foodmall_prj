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
<h2>FAQ 목록</h2>
<a href="insertFAQ.do">글 쓰기</a>
<hr>
<table border="1" width="80%">
	<tr>
		<td>글번호</td>
		<td>글제목</td>
		<td>등록일</td>
		<td>조회수</td>
		<td>관리자</td>

	</tr>
			
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

		<form action="">
					<input type="hidden" name="boardCode" value="${boardCode }">
					<input type="hidden" name="page" value="1">
								
					<select name="searchType">
						<option value="FAQ_TITLE">제목</option>
						<option value="FAQ_CONTENT">내용</option>
						<option value="all">제목+내용</option>
					</select>


					
					<input type="text" name="searchKeyword" placeholder="검색어 입력" value="${searchKeyword }">		
					<input class="btn waves-effect waves-light" type="submit" value="검색">
		</form>
		
<!-- 	<div>
		 <input type="text" name="searchKeyword" id="searchKeyword" style="width:600px; height:20px"
		  placeholder="상품명으로 검색" onkeyup="search('searchKeyword');">
	</div> -->
</body>
</html>









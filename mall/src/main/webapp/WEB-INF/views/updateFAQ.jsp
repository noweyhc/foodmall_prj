<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>글 수정</h2>
	<hr>
	<form action="updateFAQ.do" method="post" enctype="multipart/form-data">
		<input type="hidden" name="faq_no" value="${f.faq_no }">
		글제목 : <input type="text" name="faq_title" value="${f.faq_title }"><br>
		글내용 : <br>
		<textarea rows="10" cols="80" name="faq_content">${f.faq_content }</textarea><br>
		관리자 : <input type="text" name="faq_manager" value="${f.faq_manager }"><br>
		<input type="submit" value="수정">
		<input type="reset" value="취소">
		<a href="listFAQ.do">목록보기</a>
	</form>
</body>
</html>









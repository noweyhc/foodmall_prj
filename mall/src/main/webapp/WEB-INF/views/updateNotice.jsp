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
	<form action="updateNotice.do" method="post" enctype="multipart/form-data">
		<input type="hidden" name="notice_no" value="${n.notice_no }">
		글제목 : <input type="text" name="notice_title" value="${n.notice_title }"><br>
		글내용 : <br>
		<textarea rows="10" cols="80" name="notice_content">${n.notice_content }</textarea><br>
		관리자 : <input type="text" name="notice_manager" value="${n.notice_manager }"><br>
		<input type="submit" value="수정">
		<input type="reset" value="취소">
		<a href="listNotice.do">목록보기</a>
	</form>
</body>
</html>









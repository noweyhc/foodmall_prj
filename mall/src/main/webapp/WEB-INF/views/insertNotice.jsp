<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공자사항 글 쓰기</title>
</head>
<body>
	<h2>공지사항 글 쓰기</h2>
	<hr>
	<form action="insertNotice.do" method="post" enctype="multipart/form-data">
		<input type="hidden" name="no" value="${notice_no }">
		글제목 : <input type="text" name="notice_title"><br>
		글내용 : <br>
		<textarea rows="10" cols="80" name="notice_content"></textarea><br>
		관리자 : <input type="text" name="notice_manager"><br>
		<input type="submit" value="등록">
		<input type="reset" value="취소">
	</form>
</body>
</html>


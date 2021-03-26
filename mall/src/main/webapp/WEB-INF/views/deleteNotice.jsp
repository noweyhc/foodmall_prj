<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항 글 삭제</title>
</head>
<body>
<h2>글 삭제</h2>
<hr>
<form action="deleteNotice.do" method="post">
	<input type="hidden" name="no" value="${no }">
	<input type="submit" value="삭제">
	<input type="reset" value="취소">
</form>

</body>
</html>










<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항 글 상세</title>
</head>
<body>
<h2>공지사항 글 상세</h2>
<hr>
글번호 : ${n.notice_no }<br>
글제목 : ${n.notice_title }<br>
글내용 : <br>
<textarea rows="10" cols="80" readonly="readonly">${n.notice_content }</textarea><br>
글 조회수 : ${n.notice_hit }<br>
관리자 : ${n.notice_manager }<br>
<hr> 
<a href="updateNotice.do?no=${n.notice_no }">수정</a>
<a href="deleteNotice.do?no=${n.notice_no }">삭제</a>
<a href="listNotice.do">목록보기</a>
</body>
</html>








<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h2>공지사항 상세</h2>
<hr>
글번호 : ${f.faq_no }<br>
글제목 : ${f.faq_title }<br>
글내용 : <br>
<textarea rows="10" cols="80" readonly="readonly">${f.faq_content }</textarea><br>
첨부파일 : ${f.uploadFile }<br>
관리자 : ${f.faq_manager }<br>
<%-- 등록일 : ${b.regdate }<br>
조회수 : ${b.hit }<br> --%>
<hr> 
<a href="updateFAQ.do?no=${f.faq_no }">수정</a>
<a href="deleteFAQ.do?no=${f.faq_no }">삭제</a>
<a href="listFAQ.do">목록보기</a>
</body>
</html>








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

${userIqVo.cs_no }<br>
${userIqVo.cs_title }<br>
${userIqVo.cs_content }<br>
${userIqVo.cs_regdate }<br>
${userIqVo.cs_category_one }<br>
${userIqVo.cs_category_two }<br>
<img src="inquiry/${userIqVo.cs_fname }"><br>

<button id="btnReply">답변하기</button>

<div id="answer_wrap">

</div>


<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script>
	
</script>
</body>
</html>
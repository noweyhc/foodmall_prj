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
</head>
<body>

	<h2>문의한 목록</h2>
	<hr>
	
      <table class="highlight">
        <thead>
          <tr>
              <th>문의 번호</th>
              <th>문의 제목</th>
              <th>문의 유형</th>
              <th>문의 일자</th>
              <th>답변 여부</th>
          </tr>
        </thead>

        <tbody>
				<c:forEach var="l" items="${list }">
        	<tr>
					<td>${l.cs_no }</td>
					<td id="cs_title"><a href="myInqDetail.do/${l.cs_no }">${l.cs_title }</a></td>
					<td>[${l.cs_category_two }]</td>
					<td>${l.cs_regdate }</td>
					<td>${l.cs_response }</td>
			</tr>
				</c:forEach>
        </tbody>
      </table>
      
      <c:forEach var="i" begin="1" end="${totalPage }">
      		<a href="myInquiry.do?pageNUM=${i }">${i }</a>
      </c:forEach>

</body>
</html>
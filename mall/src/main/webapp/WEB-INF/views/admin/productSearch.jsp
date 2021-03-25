<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script type="text/javascript" src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<title>Insert title here</title>
</head>
<body>
<h3>상품 검색기</h3>
	<div>
		 <input type="text" name="searchKeyword" id="searchKeyword" style="width:600px; height:20px"
		  placeholder="상품명으로 검색" onkeyup="search('searchKeyword');">
	</div>
	
	<div id="searchResults">
		<table id="resultList">
			<tr>
				<td width="100px">상품번호</td>
				<td width="200px">카테고리</td>
				<td width="300px">상품명</td>
				<td width="200px">원가격</td>
			</tr>
		</table>
	</div>
	
<script type="text/javascript" src="/static/js/admin/adminFuntions.js"></script>
</body>
</html>
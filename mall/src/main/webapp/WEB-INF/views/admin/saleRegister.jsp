<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/admin/adminSideMenu.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="/css/adminStyle.css">
<title>할인 등록 페이지</title>
</head>
<body>
<div class="admin-body-wrapper">
	<h2>할인 등록</h2>
	<hr>
	<input type="button" id="searchProduct" value="상품 찾기" onclick="window.open('product-search', '상품 검색', 'width=800, height=600, left=200, top=50');">
	<br>
	<form action="sale-register" id="sale-register" method="post">
		<input type="text" id="productNo" name="productNo" placeholder="상품번호" readonly="readonly">
		<input type="text" id="productTitle" placeholder="상품명"><br>
		원가격 <input type="text" id="productPrice" placeholder="원가격">
		<br>
		시작일 <input type="date" value="2021-01-01" min="2021-01-01" max="2100-01-01" id="startDate" name="startDate">
		<input type="time" value="00:00" id="startTime" name="startTime">
		<br>
		종료일 <input type="date" value="2021-01-01" min="2021-01-01" max="2100-01-01" id="endDate" name="endDate">
		<input type="time" value="00:00" id="endTime" name="endTime">
		<br>
		세일가 <input type="text" id="salePrice" placeholder="세일가" name="salePrice">
		<input type="submit" value="등록">
	</form>
</div>
<script type="text/javascript" src="/static/js/admin/adminFuntions.js"></script>
<script type="text/javascript" src="/static/js/admin/saleRegister.js"></script>
</body>
</html>
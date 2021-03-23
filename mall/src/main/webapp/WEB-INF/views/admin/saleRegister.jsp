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
	<input type="button" id="searchProduct" value="상품 등록" onclick="window.open('product-search', '상품 검색', 'width=800, height=600, left=200, top=50');">
	<br>
	<input type="text" id="productNo" value="흐억">
</div>
<script type="text/javascript" src="/static/js/admin/adminFuntions.js"></script>
</body>
</html>
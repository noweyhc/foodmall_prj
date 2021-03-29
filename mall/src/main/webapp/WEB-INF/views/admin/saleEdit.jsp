<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/admin/adminSideMenu.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="/css/adminStyle.css">
<title>할인 상품 목록</title>
</head>
<body>

<div class="admin-body-wrapper">
	<div class="admin-title">
		<h2>할인 상품 관리</h2>
		<hr>
	</div>
	
	<table width="90%">
		<tr>
			<td>상품번호</td>
			<td>상품명</td>
			<td>시작일</td>
			<td>종료일</td>
			<td>원가격</td>
			<td>할인가</td>
			<td>수정</td>
			<td>삭제</td>
		</tr>
		
		<c:forEach var="s" items="${list }">
			<tr>
				<td id="${s.sale.product_no }">${s.sale.product_no }</td>
				<td>${s.product.product_title }</td>
				<td>${s.sale.timesale_start }</td>
				<td>${s.sale.timesale_end }</td>
				<td>${s.product.product_price}</td>
				<td>${s.sale.timesale_saleprice }</td>
				<td><input type="button" value="수정" onclick="location.href='/admin/sale-edit/${s.sale.product_no }'"></td>
				<td><input type="button" value="삭제" onclick="deleteConfirmSale('${s.sale.product_no }')"></td>
			</tr>
		</c:forEach>
	</table>
</div>

<script type="text/javascript" src="/static/js/admin/adminFuntions.js"></script>
</body>
</html>
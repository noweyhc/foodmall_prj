<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<script type="text/javascript" src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js" integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV" crossorigin="anonymous"></script>
	<link rel="stylesheet" href="/css/adminStyle.css">
</head>
<body>
	<div id="side-menu" class="side-menu">
		<div class="goto-admin-main">
		<a href="/admin/main">메인으로</a>
		</div>
		<div class="list">
		상품 관리<br>
		<a href="/admin/product-register">상품 등록</a><br>
		<a href="/admin/product-edit">상품 수정 및 삭제</a>
		</div>
		<hr class = "divider">
		<div class= "list">
		할인 관리<br>
		<a href="/admin/sale-register">할인 등록</a><br>
		<a href="/admin/sale-edit">할인 수정 및 삭제</a><br>
		</div>
		<hr class = "divider">
		<div class= "list">
		회원 관리
		</div>
		<hr class = "divider">
	</div>
	
</body>
</html>
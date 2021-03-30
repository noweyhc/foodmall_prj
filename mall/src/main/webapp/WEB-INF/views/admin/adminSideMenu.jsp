<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<script type="text/javascript" src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-cookie/1.4.1/jquery.cookie.js"></script>
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js" integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV" crossorigin="anonymous"></script>
	<script src="https://kit.fontawesome.com/02a1ffc4ee.js" crossorigin="anonymous"></script>
	<link rel="preconnect" href="https://fonts.gstatic.com">
	<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@400;500;700&display=swap" rel="stylesheet">
	<link rel="stylesheet" href="/css/adminStyle.css">
</head>
<body>
	<div id="side-menu" class="side-menu">
		<div class="goto-admin-main"  style="cursor: pointer;" onclick="location.href='/admin/main';">
			<i class="fas fa-utensils" style="color: white;"></i>
			<div class="category-title">밥도둑 관리자</div>
		</div>
		<hr class = "divider">
		<div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">
			<div class="menu-list-sub">
				<a id="menu-list-sub-product" class="menu-list-sub-category ${category eq 'product' ? '' : 'collapsed' }" href="#" data-toggle="collapse" data-target="#collapseOne" 
				aria-expanded="${category eq 'product' ? 'true' : 'false' }" aria-controls="collapseOne" data-parent="#accordion">
					<i class="fas fa-apple-alt"></i>	
					<span style="margin-left: 10px;">상품 관리</span>
				</a>
			</div>
			<div id="collapseOne" class="collapse ${category eq 'product' ? 'show' : '' } ">
				<div class="py-2 collapse-inner rounded">
					<div class="item-wrapper">
						<a class="collapse-item ${function eq 'productRegister' ? 'active' : '' }" href="/admin/product-register">상품 등록</a>
					</div>
					<div class="item-wrapper">
						<a class="collapse-item ${function eq 'productEdit' ? 'active' : '' }" href="/admin/product-edit">상품 수정 및 삭제</a>
					</div>
				</div>
			</div>		
			<div class="menu-list-sub">
				<a id="menu-list-sub-sale" class="menu-list-sub-category ${category eq 'sale' ? '' : 'collapsed' }" href="#" data-toggle="collapse" data-target="#collapseTwo" 
				aria-expanded="${category eq 'sale' ? 'true' : 'false' }" aria-controls="collapseTwo" data-parent="#accordion">
					<i class="fas fa-percentage"></i>	
					<span style="margin-left: 10px;">할인 관리</span>
				</a>
			</div>
			<div id="collapseTwo" class="collapse ${category eq 'sale' ? 'show' : '' } ">
				<div class="py-2 collapse-inner rounded">
					<div class="item-wrapper">
						<a class="collapse-item ${function eq 'saleRegister' ? 'active' : '' }" href="/admin/sale-register">할인 등록</a>
					</div>
					<div class="item-wrapper">
						<a class="collapse-item ${function eq 'saleEdit' ? 'active' : '' }" href="/admin/sale-edit">할인 수정 및 삭제</a>
					</div>
				</div>
			</div>
		</div>
			<hr class = "divider">
		</div>

</body>
</html>
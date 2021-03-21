<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/admin/adminSideMenu.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="/static/css/adminStyle.css">
<script type="text/javascript" src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<title>상품 정보 수정</title>
</head>
<body>
<div class="admin-body-wrapper">
<hr>
<h2>상품 수정</h2>
<hr>

<form id="product_editor" method="post" enctype="multipart/form-data">
			상품번호 : <input type="text" name="product_no" id="product_no" style="width:50px" value="${product.product_no }">
			카테고리 : <select name="product_category" id="product_category" style="width:80px" >
				<option value="국/탕" selected="selected">국/탕</option>
				<option value="찌개/전골">찌개/전골</option>
				<option value="밀키트">밀키트</option>
				<option value="밑반찬">밑반찬</option>
				<option value="김치">김치</option>
				<option value="기타">기타</option>
			</select>
			재고 : <input type="text" name="product_stock" id="product_stock" style="width:30px" value="${product.product_stock }">	
			상품명 : <input type="text" name="product_title" id="product_title" style="width:100px" value="${product.product_title }">
			상품부제 : <input type="text" name="product_subtitle" id="product_subtitle" style="width:140px" value="${product.product_subtitle }"><br>
			칼로리 : <input type="text" name="product_caloies" id="product_caloies" value="${product.product_caloies }">
			원산지 : <input type="text" name="product_origin" id="product_origin" value="${product.product_origin }">
			보관방법 : <select name="product_storage" id="product_storage">
				<option value="냉장" selected="selected">냉장</option>
				<option value="냉동">냉동</option>
				<option value="실온">실온</option>
			</select><br>
			상품 세부글 : <input type="text" name="product_detail_text" id="product_detail_text" style="width:650px" value="${product.product_detail_text }"><br>
			재료 : <input type="text" name="product_ingredient" id="product_ingredient" value="${product.product_ingredient }">
			가격 : <input type="text" name="product_price" id="product_price"  style="width:100px" value="${product.product_price }"><br>
			
			메인이미지  <img src="/img/${product.product_main_img }"><br>
			<input type="hidden" name="product_main_img" class="product_main_img">
			변경 할 메인이미지 : <input type="file" id="mainImgFile" name="mainImgFile"><br>
			
			상세이미지1 <img src="/img/${product.product_detail_img1 }"><br>
			<input type="hidden" name="product_detail_img1" class="product_detail_img1">
			상세이미지1 : <input type="file" id="detailImgFile1" name="detailImgFile1"><hr>
			
			상세이미지2 <img src="/img/${product.product_detail_img2 }" alt="이미지 없음"><br>
			상세이미지2는 필수가 아닙니다<br>
			<input type="hidden" name="product_detail_img2" class="product_detail_img2">
			상세이미지2 : <input type="file" id="detailImgFile2" name="detailImgFile2"><br>
			<hr>
			<br>
			<input type="submit" value="등록">
			<input type="reset" value="재작성">
	</form>

</div>

<script type="text/javascript" src="/static/js/adminFuntions.js"></script>
</body>
</html>
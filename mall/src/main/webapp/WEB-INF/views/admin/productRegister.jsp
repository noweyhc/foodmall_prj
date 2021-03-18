<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/admin/adminSideMenu.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<link rel="stylesheet" href="/static/css/adminStyle.css">
	<meta http-equiv="Expires" content="Mon, 06 Jan 1990 00:00:01 GMT">
	<meta http-equiv="Pragma" content="no-cache">
	<meta http-equiv="Cache-Control" content="no-cache">
</head>
<body>
<div class="body-wrapper">

	<input type="button" value="관리자 메인" onclick='location.href = "/admin"'>
	<hr>
	임시 상품 등록 페이지<br><br>
	<input type="button" id="randomBtn" value="랜덤 생성" style="background-color:cyan">
	등록 실패 시 상품번호 중복이니 재생성 후 등록하세요<br>
	<div style="font-weigt:bold">
	${result }<br>
	</div>
	
	<form action="product-register" id="product_register" method="post" enctype="multipart/form-data">
			상품번호 : <input type="text" name="no" id="no" style="width:50px">
			카테고리 : <select name="category" id="category" style="width:80px">
				<option value="국/탕" selected="selected">국/탕</option>
				<option value="찌개/전골">찌개/전골</option>
				<option value="밀키트">밀키트</option>
				<option value="밑반찬">밑반찬</option>
				<option value="김치">김치</option>
				<option value="기타">기타</option>
			</select>
			재고 : <input type="text" name="stock" id="stock" style="width:30px">
			상품명 : <input type="text" name="title" id="title" style="width:100px">
			상품부제 : <input type="text" name="subtitle" id="subtitle" style="width:140px"><br>
			칼로리 : <input type="text" name="caloies" id="caloies">
			원산지 : <input type="text" name="origin" id="origin">
			보관방법 : <select name="storage" id="storage">
				<option value="냉장" selected="selected">냉장</option>
				<option value="냉동">냉동</option>
				<option value="실온">실온</option>
			</select><br>
			상품 세부글 : <input type="text" name="detailText" id="detailText"  style="width:650px"><br>
			재료 : <input type="text" name="ingredient" id="ingredient">
			가격 : <input type="text" name="price" id="price"  style="width:100px"><br>
			
			<input type="hidden" name="mainImg" class="mainImg">
			메인이미지 : <input type="file" id="mainImgFile" name="mainImgFile"><br>
			
			<input type="hidden" name="detailImg1" class="detailImg1">
			상세이미지1 : <input type="file" id="detailImgFile1" name="detailImgFile1"><hr>
			
			상세이미지2는 필수가 아닙니다<br>
			<input type="hidden" name="detailImg2" class="detailImg2">
			상세이미지2 : <input type="file" id="detailImgFile2" name="detailImgFile2"><br>
			<hr>
			<br>
			<input type="submit" value="등록">
			<input type="reset" value="취소">
	</form>

</div>

<script type="text/javascript" src="/static/js/generate_random.js">
</script>
</body>
</html>
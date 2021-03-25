<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/admin/adminSideMenu.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="/css/adminStyle.css">
<script type="text/javascript" src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<title>상품 정보 수정</title>
</head>
<body>
<div class="admin-body-wrapper">
	<h2>세일 정보 수정</h2>
	<hr>

	<form id="sale_editor" method="post" enctype="multipart/form-data">
		상품번호 <input type="text" id="productNo" name="productNo" value="${product.product_no }" readonly="readonly">
		상품명 <input type="text" id="productTitle" value="${product.product_title }" readonly="readonly"><br>
		원가격 <input type="text" id="productPrice" value="${product.product_price }" readonly="readonly"><br>
		<br>
		시작일 <input type="date" value="2021-01-01" min="2021-01-01" max="2100-01-01" id="startDate" name="startDate">
		<input type="time" value="00:00" id="startTime" name="startTime">
		<br>
		종료일 <input type="date" value="2021-01-01" min="2021-01-01" max="2100-01-01" id="endDate" name="endDate">
		<input type="time" value="00:00" id="endTime" name="endTime">
		<br>
		할인가 <input type="text" id="salePrice" name="salePrice" value="${sale.timesale_saleprice }"> 
		<hr>
		<input type="button" id="sale_editor_submit" value="수정">
		<input type="button" value="취소" onclick="location.href='/admin/sale-edit'">
	</form>

</div>

<script type="text/javascript">
	let date = '${sale.timesale_start }';
	let fDate = "${sale.timesale_end }";
	
	let startDate = date.slice(0, 10);
	let startTime = date.slice(11, 16);
	let endDate = fDate.slice(0, 10);
	let endTime = fDate.slice(11, 16);
	
	document.getElementById('startDate').value = startDate;
	document.getElementById('startDate').min = startDate;
	document.getElementById('startTime').value = startTime;
	
	document.getElementById('endDate').value = endDate;
	document.getElementById('endDate').min = startDate;
	document.getElementById('endTime').value = endTime;
	
	let productNo = document.getElementById('productNo').value;
	let ajaxUrl = '/admin/sale-edit/' + productNo;
	
	$('#sale_editor_submit').click(function(){
	let updateData = new FormData(document.getElementById('sale_editor'));	
	$.ajax({
		url:ajaxUrl,
		data:updateData,
		type:'post',
		processData:false,
		contentType:false,
		success:function(){
			alert("세일 정보 수정에 성공하였습니다.");
		}
	});
});
</script>
<script type="text/javascript" src="/static/js/admin/adminFuntions.js"></script>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>detailProducts</title>
	
	<link rel="stylesheet" href="static/css/mainpageStyle.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css" rel="stylesheet"
    integrity="sha384-BmbxuPwQa2lc/FVzBcNJ7UAyJxM6wuqIj61tLrc4wSX0szH/Ev+nYRRuWlolflfl" crossorigin="anonymous"> 
    
    <!-- 아래 세 개는 임시 추가된 태그로, 작업 편의를 위해 캐시를 저장하지 않게 하는 태그입니다 -->
	<meta http-equiv="Expires" content="Mon, 06 Jan 1990 00:00:01 GMT">
	<meta http-equiv="Pragma" content="no-cache">
	<meta http-equiv="Cache-Control" content="no-cache">
	<meta charset="UTF-8">     
</head>
<body>

	<%@ include file="header.jsp" %>
	<%@ include file="menubar.jsp" %>
	<div id="title" align="center">
		<h2>상품상세</h2>
		<hr>
	</div>
	
	<div align="center">
		<img  src="img/${p.product_main_img }"><br>
		상품번호 : ${p.product_no }<br>
		카테고리 : ${p.product_category }<br>
		상품명 : ${p.product_title }<br>
		<p class="cartStock">
			<span>구입 수량</span>
			<button type="button" class="plus">+</button>
			<input type="number" class="numBox" min="1" max="${view.gdsStock}" value="1" readonly="readonly"/>
			<button type="button" class="minus">-</button>
			 
			<script>
				$(".plus").click(function(){
					var num = $(".numBox").val();
					var plusNum = Number(num) + 1;
			   
					if(plusNum >= ${view.gdsStock}) {
						$(".numBox").val(num);
					} else {
			    		$(".numBox").val(plusNum);          
			   		}
			  	});
			  
				$(".minus").click(function(){
					var num = $(".numBox").val();
					var minusNum = Number(num) - 1;
				   
					if(minusNum <= 0) {
						$(".numBox").val(num);
					} else {
						$(".numBox").val(minusNum);          
					}
				});
			</script> 
		</p>
		
		상품가격 : ${p.product_price }<br>
		<hr>
		<a href="#">장바구니</a>
		<a href="#">바로구매</a>
		
		<hr>
		<img src="img/${p.product_detail_img1 }">
		<img src="img/${p.product_detail_img2 }">
	</div>
	
	<%@ include file="footer.jsp" %>
	
	<script type="text/javascript" src="/static/js/eventbanner.js"></script>
	<meta http-equiv="Expires" content="-1">

</body>
</html>
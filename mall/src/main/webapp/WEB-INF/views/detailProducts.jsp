<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="header.jsp" %>
<%@ include file="menubar.jsp" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>detailProducts</title>
	
	<link rel="stylesheet" href="static/css/detailProductsStyle.css">
	<link href="https://fonts.googleapis.com/css?family=Nanum+Gothic:400,700,800&amp;subset=korean" rel="stylesheet">
	
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css" rel="stylesheet"
    integrity="sha384-BmbxuPwQa2lc/FVzBcNJ7UAyJxM6wuqIj61tLrc4wSX0szH/Ev+nYRRuWlolflfl" crossorigin="anonymous"> 
	<meta charset="UTF-8">   
	

</head>
<body>

	<section id="detailProducts"><br>
	<div class="product-detail">		
		<div id="product-detail-wrap" align="center">
			<table border="1">
				<tr>
					<td>
						<img class="product-mainimg" src="img/${p.product_main_img }">
					</td>
					<td>
						<div id="title" align="center">
							<a href="/listProducts.do">상품목록 > </a>
							<a href="/listProducts.do?category_no=${p.category_no }">${p.product_category }</a><br><br>
						</div>
						<table border="0" style="width: 400px; height: 400px;">
							<tr align="center">
								<td style="text-align: left; padding-left: 30px;">상품명</td>
								<td>${p.product_title }</td>
							</tr>
							<tr align="center">
								<td style="text-align: left; padding-left: 30px;">상품 소제목</td>
								<td>${p.product_subtitle }</td>
							</tr>
							<tr align="center">
								<td style="text-align: left; padding-left: 30px;">상품 가격</td>
								<td><fmt:formatNumber value="${p.product_price }" pattern="#,###"/>원</td>
							</tr>
							<tr align="center">
								<td style="text-align: left; padding-left: 30px;">수량 선택</td>
								<td colspan="2">
									<form name="form-cart" method="post" action="/insert.do">
										<input type="hidden" name="product_no" value="${p.product_no }">
										<input type="hidden" name="product_price" id="product_price" value="${p.product_price }">
										<input type="hidden" name="product_title" value="${p.product_title }">
										<input type="hidden" name="product_main_img" value="${p.product_main_img }">
										<select id="product_qty" name="product_qty" onchange="getProductQTY();">
											<c:set var="total_price" value="${p.product_price }"/>
												<c:forEach var="i" begin="1" end="10" >
													<option value="${i }">${i }</option>
												</c:forEach>
										</select>&nbsp;개&nbsp;
										<input type="submit" id="input_cart" value="장바구니에 담기">						
									</form>
								</td>
							</tr>		
							<tr align="center" style="font-weight: bold">
								<td style="text-align: left; padding-left: 30px;">총 가격</td>
								<td id="total_price"><fmt:formatNumber value="${total_price }" pattern="#,###"/>원</td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
		</div>
		
		<div align="center">		
			<hr>
			<a href="#detail"><button>상품 상세설명</button></a>
			<a href="#review"><button>상품 리뷰</button></a>
			<a href="#cs"><button>1:1 문의</button></a><br>

			<a name="detail"></a> 
			<h6>${product_detail_text }</h6>
			<img src="img/${p.product_detail_img1 }">
			<img src="img/${p.product_detail_img2 }">
			<br><br><br><br><br><br><br><br><br><br><br><br>
			
			<a name="review"></a><hr>
			<h6>리뷰 영역</h6>
			<br><br><br><br><br><br><br><br><br><br><br><br>
			
			<a name="cs"></a><hr>
			<h6>1:1문의 영역</h6>
		</div>
	</div>
	</section>
</body>

	<script type="text/javascript" src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
	<script type="text/javascript">
	/*
		$(function(){
			$("#input_cart").click(function(){
				if(confirm("장바구니로 가기")) {
					location.href="/listCart.do";
				}
			});	
		});
	*/
	
	// 상품 수량 선택 이벤트처리
	function getProductQTY(){
		var qty = $("#product_qty option:selected").val();
		var total_price = $("#product_price").val();
		total_price = total_price*qty;
		var result = addComma(total_price) + "원";
		//alert(result);
		
		document.getElementById("total_price").innerHTML = result;
	}
	
	function addComma(num) {
  		var regexp = /\B(?=(\d{3})+(?!\d))/g;
 		 return num.toString().replace(regexp, ',');
	}
	</script>
	<%@ include file="footer.jsp" %>
</html>
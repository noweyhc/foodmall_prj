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
	
	<link rel="stylesheet" href="css/detailProductsStyle.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css" rel="stylesheet"
    integrity="sha384-BmbxuPwQa2lc/FVzBcNJ7UAyJxM6wuqIj61tLrc4wSX0szH/Ev+nYRRuWlolflfl" crossorigin="anonymous"> 
	<meta charset="UTF-8">     
<!-- 	<script type="text/javascript" src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
	<script type="text/javascript">
		$(function(){
			$("#input_cart").click(function(){
				if(confirm("장바구니로 가기")) {
					location.href="/listCart.do";
				}
			});	
		});
		
	</script>  --> 
</head>
<body>

	
	<section id=detailProducts><br>
	<div id="title" align="center">
		<a href="/listProducts.do">상품목록</a> >
		<a href="#">${p.product_category }</a>
	</div>
	
	<div id="product-detail-wrap" align="center">
		<table border="1">
			<tr>
				<td>
					<img class="product-mainimg" src="img/${p.product_main_img }">
				</td>
				<td>
					<table border="1" style="width: 400px; height: 400px;">
						<tr align="center">
							<td>상품명</td>
							<td>${p.product_title }</td>
						</tr>
						<tr align="center">
							<td>상품 소제목</td>
							<td>${p.product_subtitle }</td>
						</tr>
						<tr align="center">
							<td>상품 가격</td>
							<td><fmt:formatNumber value="${p.product_price }" pattern="#,###"/>원</td>
						</tr>
						<tr align="center">
							<td colspan="2">
								<form name="form-cart" method="post" action="/insert.do">
									<input type="hidden" name="product_no" value="${p.product_no}">
									<input type="hidden" name="product_price" value="${p.product_price}">
									<input type="hidden" name="product_title" value="${p.product_title}">
									<input type="hidden" name="product_main_img" value="${p.product_main_img}">
									<select name="product_qty">
										<c:forEach begin="1" end="10" var="i">
											<option value="${i }">${i }</option>
										</c:forEach>
									</select>&nbsp;개
									<input type="submit" id="input_cart" value="장바구니에 담기">						
								</form>
							</td>
						<tr align="center">
							<td>총 가격</td>
							<td><fmt:formatNumber value="${p.product_price }" pattern="#,###"/>원</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</div>
	
	<div align="center">
		<%-- <img  src="img/${p.product_main_img }"><br>
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
		<a href="#">바로구매</a> --%>
		
		<hr>
		<button id="btn_detail">상품 상세설명</button>&nbsp;
		<button id="btn_review">상품 리뷰</button>&nbsp;
		<button id="btn_cs">1:1 문의</button><br>
		<h6>${product_detail_text }</h6>
		<img src="img/${p.product_detail_img1 }">
		<img src="img/${p.product_detail_img2 }">
	</div>
	</section>
	
	<%@ include file="footer.jsp" %>

</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>listCart</title>
	

    <link rel="stylesheet" href="static/css/mainpageStyle.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css" rel="stylesheet"
    integrity="sha384-BmbxuPwQa2lc/FVzBcNJ7UAyJxM6wuqIj61tLrc4wSX0szH/Ev+nYRRuWlolflfl" crossorigin="anonymous"> 
    
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"> 
    
    <!-- 아래 세 개는 임시 추가된 태그로, 작업 편의를 위해 캐시를 저장하지 않게 하는 태그입니다 -->
    <meta http-equiv="Expires" content="Mon, 06 Jan 1990 00:00:01 GMT">
	<meta http-equiv="Pragma" content="no-cache">
	<meta http-equiv="Cache-Control" content="no-cache">
		
</head>
<body>

	<%@ include file="header.jsp" %>
	<%@ include file="menubar.jsp" %>
	
 	<section id=listCart ><br>
			<div id="title" align="center">
				<h2>내 장바구니</h2>	
			</div>
			
			<div id="cart-list" align="center">
				<hr>
				
				<c:choose>
					<c:when test="${map.count==0 }">장바구니가 비어있습니다.</c:when>
					
					<c:otherwise>
						<form name="form-cart" id="form-cart" method="post" action="/update.do">
							<table border="1">
								<tr>
									<th>상품이미지</th>
									<th>상품명</th>
									<th>단가</th>
									<th>수량</th>
									<th>금액</th>
									<th></th>
									
								</tr>
								<c:forEach var="c" items="${map.list}" varStatus="i">
								<tr>
									<td>
										<a href="/detailProducts.do?no=${c.product_no }">
										<img src="img/${c.product_main_img}" width="100" height="100">
										</a>
									</td>
									<td>${c.product_title }</td>
									<td style="width: 100px">
										<fmt:formatNumber value="${c.product_price }" pattern="#,###"/>원
									</td>
									<td>
										<input type="number" style="width: 50px" name="product_qty" value="${c.product_qty }">
										<input type="hidden" name="product_no" value="${c.product_no }">
										<button type="submit" id="btn_update">변경</button>
									</td>
									<td style="width: 100px">
										<fmt:formatNumber value="${c.product_total }" pattern="#,###"/>원
									</td>
									<td>
										<a href="/delete.do?cart_no=${c.cart_no }">삭제</a>
									</td> 
								</tr>
								</c:forEach>
								
								<tr>
									<td colspan="6" align="right"><br>
										상품금액 합계 : 	<fmt:formatNumber value="${map.sumPriceCart }" pattern="###,###,###"/>원<br>
										배송료 : ${map.fee }원<br>
										전체 주문금액 : <fmt:formatNumber value="${map.totalPriceCart }" pattern="###,###,###"/>원
									</td>
								</tr>
							</table>
							<input type="hidden" name="count" value="${map.count }">
						</form>
					</c:otherwise>
				</c:choose>
				<br><br>
				<button type="submit" id="btn_choice">선택상품 결제</button>
				<button type="submit" id="btn_order">전체상품 결제</button><br><br>
				<hr>
				<button type="button" id="btn_list">상품 더보기</button><br><br>
			</div>
	</section>
	
	<%@ include file="footer.jsp" %>
	
</body>

	<script type="text/javascript" src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
	<script type="text/javascript">
		$(function(){
			// 상품 더보기: 상품 목록 페이지로 이동
			$("#btn_list").click(function(){
				location.href="/listProducts.do";
			});	
		});
		
		$(function(){
			// 결제
			$("#btn_order").click(function(){
				location.href="#";
			});	
		});
		
	</script>
</html>
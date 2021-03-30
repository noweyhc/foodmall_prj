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
	<title>listProducts</title>
	
    <link rel="stylesheet" href="static/css/listProductsStyle.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css" rel="stylesheet"
    integrity="sha384-BmbxuPwQa2lc/FVzBcNJ7UAyJxM6wuqIj61tLrc4wSX0szH/Ev+nYRRuWlolflfl" crossorigin="anonymous">  
	<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@400;500;700&display=swap" rel="stylesheet">

</head>
<body>
	<div id="list">
	<section id=listProducts ><br>
		<div class="list-title">
			<h4>상품목록</h4>	
		</div>
		
		<div class="list-wrap">
			<c:forEach var="p" items="${list }">
			<div class="list-item-box">
					<ul class="items">
						<li><a href="/detailProducts.do?no=${p.product_no }"><img class="list-item-image" src="img/${p.product_main_img}"></a></li>	
				    	<li class="list-product-title"><a href="/detailProducts.do?no=${p.product_no }" class="a">${p.product_title }</a></li>
						<li class="list-product-subtitle">${p.product_subtitle }</li>
						<li class="list-price"><fmt:formatNumber value="${p.product_price }" pattern="##,###"/>원</li>
					</ul>
			</div>
			</c:forEach>
		</div>

	<%-- 		
			<!-- 상품 목록형 --> 			
			<table id="list-form" border="1" width="68%">
				<tr>
					<td>상품번호</td>
					<td>메인이미지</td>
					<td>상품명</td>
					<td>서브제목</td>
					<td>상품가격</td>
					<td>상품등록일</td>
				</tr>
				
				<c:forEach var="p" items="${list }">
					<tr>
						<td>${p.product_no }</td>
						<td>
							<a href="/detailProducts.do?no=${p.product_no }">
							<img src="img/${p.product_main_img}" width="100" height="100">
							</a>
						</td>
						<td>
							<a href="/detailProducts.do?no=${p.product_no }">
							${p.product_title }
							</a>
						</td>
						<td>${p.product_subtitle }</td>
						<td>
							<fmt:formatNumber value="${p.product_price }" pattern="#,###"/>원
						</td>
						<td>${p.product_regdate }</td>
					</tr>
				</c:forEach>
			</table>   --%>
			

	</section>
	</div>
	
	<%@ include file="footer.jsp" %>
	
</body>
</html>
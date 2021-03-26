<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/header.jsp"%>
<%@ include file="/WEB-INF/views/menubar.jsp"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<link rel="stylesheet" href="static/css/mainpageStyle.css">
 	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css" rel="stylesheet"
    integrity="sha384-BmbxuPwQa2lc/FVzBcNJ7UAyJxM6wuqIj61tLrc4wSX0szH/Ev+nYRRuWlolflfl" crossorigin="anonymous"> 
<title>Insert title here</title>
</head>
<body>
	<section id=listProducts ><br>
		<div id="title" align="center">
			<h2>세일목록</h2>	
		</div>
		
		<div id="product-list" align="center">
			<hr>
			
			<!-- 상품 앨범형 -->
			<div class="row row-cols-4">
				<c:forEach var="s" items="${list }">
					<ul class="items">
						<li class="item-image"><a href="/detailProducts.do?no=${s.sale.product_no }"><img src="img/${s.product.product_main_img}" width="200" height="200"></a></li>	
				    	<li class="title"><a href="/detailProducts.do?no=${s.sale.product_no }" class="a">${s.product.product_title }</a></li>
						<li class="subtitle">${s.product.product_subtitle }</li>
						<li class="origin-price" style="color: gray;"><del><fmt:formatNumber value="${s.product.product_price }" pattern="#,###"/>원</del></li>
						<li class="sale-price" style="font-weight: bold;"><fmt:formatNumber value="${s.sale.timesale_saleprice }" pattern="#,###"/>원</li>
					</ul>
			    </c:forEach>
			</div>
			
			<hr>

		</div>
	</section>
<%@ include file="/WEB-INF/views/footer.jsp"%>
</body>
</html>
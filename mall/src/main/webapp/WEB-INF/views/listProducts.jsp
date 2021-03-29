<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>listProducts</title>
	
    <link rel="stylesheet" href="static/css/mainpageStyle.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css" rel="stylesheet"
    integrity="sha384-BmbxuPwQa2lc/FVzBcNJ7UAyJxM6wuqIj61tLrc4wSX0szH/Ev+nYRRuWlolflfl" crossorigin="anonymous">  
    
    <!-- 아래 세 개는 임시 추가된 태그로, 작업 편의를 위해 캐시를 저장하지 않게 하는 태그입니다 -->
    <meta http-equiv="Expires" content="Mon, 06 Jan 1990 00:00:01 GMT">
	<meta http-equiv="Pragma" content="no-cache">
	<meta http-equiv="Cache-Control" content="no-cache">
</head>
<body>

	<%@ include file="header.jsp" %>
	<%@ include file="menubar.jsp" %>
	
	<section id=listProducts ><br>
		<div id="title" align="center">
			<h2>상품목록</h2>	
		</div>
		
		<div id="product-list" align="center">
			<hr>
			
			<!-- 상품 앨범형 -->
			<div class="row row-cols-4">
				<c:forEach var="p" items="${list }">
					<ul class="items">
						<li class="item-image"><a href="/detailProducts.do?no=${p.product_no }"><img src="img/${p.product_main_img}" width="250" height="250"></a></li>	
				    	<li class="title"><a href="/detailProducts.do?no=${p.product_no }" class="a">${p.product_title }</a></li>
						<li class="subtitle">${p.product_subtitle }</li>
						<li class="price"><fmt:formatNumber value="${p.product_price }" pattern="#,###"/>원</li>
					</ul>
			    </c:forEach>
			</div>
			
			<hr>
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
			
		</div>
	</section>
	
	<%@ include file="footer.jsp" %>
	
</body>
</html>
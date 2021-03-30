<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ include file="header.jsp" %>
<%@ include file="menubar.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>mainpage</title>
    <link rel="stylesheet" href="static/css/mainpageStyle.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css" rel="stylesheet"
    integrity="sha384-BmbxuPwQa2lc/FVzBcNJ7UAyJxM6wuqIj61tLrc4wSX0szH/Ev+nYRRuWlolflfl" crossorigin="anonymous">  
	<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@400;500;700&display=swap" rel="stylesheet">
	
</head>
<body>

    <!-- 이벤트 배너 영역 시작 -->
    <div id="event-banner" class="event-banner">
		<div class="viewbox">
			<div class="track">
				<div class = "slide active">
					<img class="banner-image" src="static/img/banner01.PNG">
				</div>
				<div class = "slide">
					<img class="banner-image" src="static/img/banner02.PNG">
				</div>
				<div class = "slide">
					<img class="banner-image" src="static/img/banner03.PNG">
				</div>
				<div class = "slide">
					<img class="banner-image" src="static/img/banner04.PNG">
				</div>
			</div>
			
			<div class="dot-indicator">
				<div class="dot active"></div>
				<div class="dot"></div>
				<div class="dot"></div>
				<div class="dot"></div>
			</div>
			
			<div class="arrow">
				<i class="arrow-left">
					<img src = "static/img/arrow_left.png">
				</i>
				<i class="arrow-right">
					<img src = "static/img/arrow_right.png">
				</i>
			</div>
		</div>
	</div>
	<!-- 이벤트 배너 영역 종료 -->
	
	<!-- 베스트 상품 영역 시작 -->
	<div id="new">
		<div class="new-title">
			<h4>따끈따끈 신상품</h4>
		</div>
		<div class="new-wrap">
			<c:forEach var="n" items="${newProducts }">
			<div class="new-item-box">
				<ul class="items">
					<li><a href="/detailProducts.do?no=${n.product_no }"><img class="new-item-image" src="img/${n.product_main_img }"></a></li>
					<li class="new-product-title"><a href="/detailProducts.do?no=${n.product_no }">${n.product_title }</a></li>
					<li class="new-product-subtitle">${n.product_subtitle }</li>
					<li class="new-price"><fmt:formatNumber value="${n.product_price }" pattern="##,###"/>원</li>
				</ul>
			</div>
			</c:forEach>
		</div>
	</div>
	<!-- 베스트 상품 영역 종료 -->
	
	<!-- 푸터 영역 시작 -->
	<footer class="footer">
		<div class="footer-link">
			<ul>
				<li><a href="#">공지사항</a></li>
				<li><a href="#">자주묻는 질문</a></li>
				<li><a href="#">1:1문의</a></li>

				<li><a href="#">개인정보처리방침</a></li>
				<li><a href="#">이용안내</a></li>
			</ul>
		</div>

		<div class="footer-outer">
			<div class="footer-col">
				<strong class="footer-title">고객센터</strong>
				<p class="footer--call">1588-0000</p>
				<p class="footer--cs">평일 | 09:00 ~ 18:00</p>
				<p class="footer--cs">휴일 | 09:00 ~ 14:00</p>
				<p class="footer--cs">점심시간 | 12:00 ~ 13:00</p>
			</div>

			<div class="footer-col">
				<strong class="footer-title">회사정보</strong>
				<p class="footer--company">법인명: 3조 반찬쇼핑몰 | 대표자: OOO</p>
				<p class="footer--company">회사주소: 서울시 마포구 서교동 OO빌딩 OO층</p>
				<p class="footer--company">사업자등록번호: 000-00-0000</p>
				<p class="footer--company">대표전화번호: 02) 000-0000</p>
				<p class="footer--company">관리책임자: 김반찬 | 이메일: banchan@market.com</p>
			</div>
		</div>

		<div class="footer-bottom">
			<p class="footer--copy">&copy; 2021. BanchanMarket All rights reserved.</p>
		</div>
	</footer>
	<!-- 푸터 영역 종료 -->
   
<script type="text/javascript" src="/static/js/eventbanner.js"></script>
<meta http-equiv="Expires" content="-1">
</body>
</html>
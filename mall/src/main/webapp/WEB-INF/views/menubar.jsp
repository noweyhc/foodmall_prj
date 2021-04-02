<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="/css/mainPage/menubarStyle.css">
<script type="text/javascript" src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<body>

	<!-- 메뉴바 영역 시작 -->
	<div class="divide-line">
	<div class="body-wrapper">
	<div id="menubar" class="menubar">
			<div class="dropdown">
				<button type="button" class="btn btn-outline-dark dropbtn">
					<svg xmlns="http://www.w3.org/2000/svg" width="12" height="12" fill="currentColor" class="bi bi-justify" viewBox="0 0 16 16">
						<path fill-rule="evenodd" d="M2 12.5a.5.5 0 0 1 .5-.5h11a.5.5 0 0 1 0 1h-11a.5.5 0 0 1-.5-.5zm0-3a.5.5 0 0 1 .5-.5h11a.5.5 0 0 1 0 1h-11a.5.5 0 0 1-.5-.5zm0-3a.5.5 0 0 1 .5-.5h11a.5.5 0 0 1 0 1h-11a.5.5 0 0 1-.5-.5zm0-3a.5.5 0 0 1 .5-.5h11a.5.5 0 0 1 0 1h-11a.5.5 0 0 1-.5-.5z"/>  
					</svg> 
				</button>
				<div class="dropdown-content">
						<a href="/categoryProducts.do?no=100">국물요리</a>
						<a href="/listProducts.do?no=101">찜·조림</a>
						<a href="/listProducts.do?no=102">육류</a>
						<a href="/listProducts.do?no=103">해물</a>
						<a href="/listProducts.do?no=104">튀김요리</a>
						<a href="/listProducts.do?no=105">면요리</a>
						<a href="/listProducts.do?no=106">밑반찬·김치</a>
						<a href="/listProducts.do?no=107">분식·도시락</a>    
						<a href="/listProducts.do?no=108">덮밥·볶음밥</a>
						<a href="/listProducts.do?no=109">밀키트·기타</a>
				</div>  
		 
			</div>
			<div class=category-list>
				<ul>
					<li><a href='/listProducts.do'>전체상품</a></li>
					<li><a href='#'>정기구독</a></li>
					<li><a href='#'>집밥코디</a></li>
					<li><a href='/timesale'>타임세일</a></li>
					<li><a href='/reviewList'>회원리뷰</a></li>
					<li><a href='#'>이벤트</a></li>
				</ul>
			</div>
			<div class="search-wrap">
				<form action="#">
					<input type="search" class="inp_search" placeholder="오늘 돈카츠ㄱㄱ?">
					<button type="submit" class="btn btn-alert btn-search">검색</button>
				</form>
			</div>
			<div class="cart-wrap">
				<a href="/listCart.do" class="btn-cart"><svg xmlns="http://www.w3.org/2000/svg" width="30" height="30" fill="currentColor" class="bi bi-cart2" viewBox="0 0 16 16">
					<path d="M0 2.5A.5.5 0 0 1 .5 2H2a.5.5 0 0 1 .485.379L2.89 4H14.5a.5.5 0 0 1 .485.621l-1.5 6A.5.5 0 0 1 13 11H4a.5.5 0 0 1-.485-.379L1.61 3H.5a.5.5 0 0 1-.5-.5zM3.14 5l1.25 5h8.22l1.25-5H3.14zM5 13a1 1 0 1 0 0 2 1 1 0 0 0 0-2zm-2 1a2 2 0 1 1 4 0 2 2 0 0 1-4 0zm9-1a1 1 0 1 0 0 2 1 1 0 0 0 0-2zm-2 1a2 2 0 1 1 4 0 2 2 0 0 1-4 0z"/>
				</svg></a>
			</div>
			<script src="https://code.jquery.com/jquery-3.5.1.min.js" integrity="sha256-9/aliU8dGd2tb6OSsuzixeV4y/faTqgFtohetphbbj0=" crossorigin="anonymous"></script>
	</div>
	</div>
	</div>
    <!-- 메뉴바 영역 끝 -->
    
<script type="text/javascript">
	$(function(){
	$(window).scroll(function(){  //스크롤하면 아래 코드 실행
	       var num = $(this).scrollTop();  // 스크롤값
	       if( num > 220 ){  // 스크롤을 36이상 했을 때
	          $(".divide-line").css("top", "0px");
	          $(".divide-line").css("position","fixed");
	       }else{
	           $(".divide-line").css("position","relative");
	       }
	  });
	});
</script>
</body>
</html>
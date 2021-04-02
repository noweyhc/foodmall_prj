<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="/WEB-INF/views/header.jsp"%>
<%@ include file="/WEB-INF/views/menubar.jsp"%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>listSets</title>
	
    <link rel="stylesheet" href="static/css/setProductsStyle.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css" rel="stylesheet"
    integrity="sha384-BmbxuPwQa2lc/FVzBcNJ7UAyJxM6wuqIj61tLrc4wSX0szH/Ev+nYRRuWlolflfl" crossorigin="anonymous">  
	<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@400;500;700&display=swap" rel="stylesheet">
<title>Insert title here</title>
</head>
<body>
	<div id="list">
		<div class="list-title">
			<h4>세트목록</h4>	
		</div>
		
		<div class="list-wrap">
			<c:forEach var="s" items="${setList }">
			<div class="list-set-box">
					<ul class="sets">
						<li><img class="list-set-image" src="img/${s.set_img}"></li>	
				    	<li class="list-set-title">${s.set_title }</li>
						<li class="list-set-subtitle">${s.set_subtitle }</li>
						<li class="list-set-price"><fmt:formatNumber value="${s.set_price }" pattern="##,###"/>원</li>
						
						<c:forEach var="c" items="${compList }">
							<c:if test="${s.set_no == c.component.set_no }">
								<li>${c.product.product_title }</li>
							</c:if>
						</c:forEach>
					</ul>
			</div>
			</c:forEach>
		</div>
	</div>

	<%@ include file="footer.jsp"%>

</body>
</html>

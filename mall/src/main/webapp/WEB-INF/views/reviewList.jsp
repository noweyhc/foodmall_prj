<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>리뷰리스트</title>
	</head>
	<body>
		<input type="text" id="keyword" placeholder="검색할 상품명을 입력">
		<button id="searchBtn">검색</button>
		<table border="1">
			<thead>
				<tr>
					<th>리뷰 번호</th>
					<th>리뷰 제목</th>
					<th>리뷰 작성자</th>
					<th>리뷰 조회수</th>
					<th>리뷰 Date</th>
				</tr>
			</thead>
			<tbody id="reviewListBody">
			</tbody>
		</table>
		<div id="pagiNation">
		</div>
		<!-- jquery -->
		<script src="https://code.jquery.com/jquery-3.5.1.min.js" type="text/javascript"></script>
		<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
		<script src="/static/js/loadReviewList.js"></script>
	</body>
</html>
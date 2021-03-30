<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>[밥도둑] 배송지 변경</title>
   	<!-- Compiled and minified CSS -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">

    <!-- Compiled and minified JavaScript -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    
    <!-- css  -->
    <link rel="stylesheet" href="/static/css/myPage/updateShipping.css">
    <link rel="stylesheet" href="/static/css/myPage/wrap.css">
    
    
</head>
<body>

<div class="wrap">
	<form action="updateShipping.do" method="post">
	
		<h4>현재주소</h4>
		<br>
		<div class="row">
			<!-- 현재 우편번호 -->
	        <div class="input-field col s3">
	          <input disabled value="${spVo.mem_zipcode }" id="disabled" type="text" class="validate">
	          <label for="disabled">우편번호</label>
	        </div>
	        <!-- 현재 주소  -->
	        <div class="input-field col s3">
	          <input disabled value="${spVo.mem_address }" id="disabled" type="text" class="validate">
	          <label for="disabled">주소</label>
	        </div>
	        <!-- 현재 상세주소  -->
	        <div class="input-field col s6">
	          <input disabled value="${spVo.mem_detailaddress }" id="disabled" type="text" class="validate">
	          <label for="disabled">상세주소</label>
	        </div>
		</div>
		
		<hr>
		<h4>변경할 주소</h4>
	        <!-- 변경 할 우편번호  -->
	        <div class="row">
		        <div class="input-field col s3">
		          <input name="mem_zipcode"  id="mem_zipcode" value=" " type="text" class="validate">
		          <label for="mem_zipcode">변경할 우편번호</label>
		        </div>
	        </div>
	        <!-- 변경 할 주소  -->
	        <div class="row">
		        <div class="input-field col s3">
		          <input name="mem_address" id="mem_address" value=" " type="text" class="validate">
		          <label for="mem_address">변경할 주소</label>
		        </div>
	        </div>
	        <!-- 변경 할 상세주소  -->
	        <div class="row">
		        <div class="input-field col s6">
		          <input  name="mem_detailaddress" id="mem_detailaddress" type="text" class="validate">
		          <label for="mem_detailaddress">상세주소</label>
		        </div>
	        </div>        
	        <!-- 우편번호 찾기 버튼  -->
	        <div class="row">
			  <a class="waves-effect waves-light btn-small" onclick="PostCode()"><i class="material-icons left">search</i>우편번호 찾기</a>
	        </div>
	        
	        <!-- 우편번호 수정 버튼 -->
		   <button class="btn waves-effect waves-light" type="submit" name="action">주소 수정
		     <i class="material-icons right">send</i>
		   </button>
	</form>
</div>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
</body>
</html>
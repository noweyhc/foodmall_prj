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
</head>
<body>

<form action="updateShipping" method="post">

	<h4>현재주소</h4>
	
	<div class="row">
		<!-- 현재 우편번호 -->
        <div class="input-field col s1">
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
	        <div class="input-field col s1">
	          <input name="mem_zipcode"  id="mem_zipcode" type="text" class="validate">
	          <label for="mem_zipcode">변경할 우편번호</label>
	        </div>
        </div>
        <!-- 변경 할 주소  -->
        <div class="row">
	        <div class="input-field col s3">
	          <input name="mem_address" id="mem_address" type="text" class="validate">
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
        <div class="row">
		  <a class="waves-effect waves-light btn-small" onclick="PostCode()"><i class="material-icons left">search</i>우편번호 찾기</a>
        </div>
</form>

<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
	<!---------------- 우편번호 ---------------->
	<script>
	function PostCode(){
		daum.postcode.load(function (data) {
		    new daum.Postcode({
		        oncomplete: function(data) {
		        	//주소 변수
		        	let = '';
		        	//클라이언트가 도로명 주소를 클릭 했을 경우
		        	if(data.userSelectedType === 'R'){
		        		addr = data.roadAddress;
		        	}
		        	else {
		        		//지번 주소 클릭했을 경우
		        		addr = data.jibunAddress;
		        	}
					document.getElementById("mem_zipcode").value = data.zonecode;
					document.getElementById("mem_address").value = addr;
		        }
		    }).open();
		});
	}
	</script>
</body>
</html>
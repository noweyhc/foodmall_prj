<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>[밥도둑]1:1문의</title>
    <!-- Compiled and minified CSS -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">

    <!-- Compiled and minified JavaScript -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
	
	<!-- css -->
	<link rel="stylesheet" href="/static/css/myPage/deleteAccount.css">
	<link rel="stylesheet" href="/static/css/myPage/wrap.css">
	
</head>
<body>
	<div class="wrap">
		<h4>[밥도둑]1:1문의</h4>
		<hr>
		<br>
		<br>
		<form method="post" enctype="multipart/form-data" action="inquiry.do" name="inquiry_form">
			<div class="row">

			<input type="hidden" name="mem_name" value="${uv.mem_name }"> 
			
			  <!-- 문의 유형 -->
			  <label>문의 유형</label>
			  <select class="browser-default" onchange="categoryChange(this)" id="value1">
				    <option value="" disabled selected>선택해주세요</option>
				    <option value="product">상품문의</option>
				    <option value="delivery">배송문의</option>
			  </select>		
			</div>
				
			<!-- 세부 문의 유형  -->
			<div class="row">
			  <label>세부 문의 유형</label>
			  <select class="browser-default" id="good">
				    <option value="" disabled selected>선택해주세요</option>
			  </select>		
			</div>		
			<input type="hidden" name="cs_category_one" id="cs_category_one" value="">
			<input type="hidden" name="cs_category_two" id="cs_category_two" value="">
	
			<!-- 고객 아이디 -->
			<div class="row">
		        <div class="input-field col s3">
		          <i class="material-icons prefix">account_box</i>
		          <input disabled value="${uv.mem_id }" id="mem_id" type="text" class="validate">
		          <label for="mem_id">회원 아이디</label>
		        </div>  		
			</div>
			
			<!-- 고객 이름 -->
			<div class="row">
		        <div class="input-field col s3">
		          <i class="material-icons prefix">account_box</i>
		          <input disabled value="${uv.mem_name}" id="mem_name" type="text" class="validate">
		          <label for="mem_name">고객명</label>
		        </div>  		
			</div>
			
			<!-- 문의 제목 -->
			<div class="row">
		        <div class="input-field col s10">
	          	  <i class="material-icons prefix">title</i>
		          <input  id="cs_title" name="cs_title" type="text" class="validate">
		          <label for="cs_title">문의 제목</label>
		        </div>  		
			</div>		
			
			<!-- 문의 내용 -->
	        <div class="row">
		        <div class="input-field col s10">
		          <i class="material-icons prefix">mode_edit</i>
		          <textarea id="icon_prefix2" rows="30" class="materialize-textarea" id="cs_content" name="cs_content" placeholder="문의유형을 선택후 문의내용을 자세하게 작성해주세요.자세한 내용을 함께 보내주시면 더욱 신속히 답변드릴수 있습니다."></textarea>
		          <label for="icon_prefix2">문의 내용</label>
		        </div>
	        </div>
	        
	        <!-- 사진 업로드  -->
		    <div class="file-field input-field">
		      <div class="btn">
		        <span>이미지 업로드</span>
		        <input type="file" name="uploadFile">
		      </div>
		      <div class="file-path-wrapper">
		        <input class="file-path validate" type="text" placeholder="Upload one or more files">
		      </div>
		    </div>        
		    
		    <!-- 접수 되었다는 메일과 핸드폰 정보  -->
	        <!-- 이메일 주소 -->   	    
	        <div class="row">
		        <div class="input-field col s5">
		          <i class="material-icons prefix">email</i>
		          <input type="text" placeholder="예) example@banchan.com" name="cs_email" class="validate">
		          <label for="cs_email">이메일 주소</label>
		          <p>접수내용 안내를 위해 알림받을 때 사용할 이메일 정보입니다.</p>
		        </div>
	        </div>
	
	        <!-- 휴대전화 -->   	    
	        <div class="row">
		        <div class="input-field col s5">
		          <i class="material-icons prefix">local_phone</i>
		          <input type="text" placeholder="01012345678" name="cs_phone" class="validate">
		          <label for="cs_email">핸드폰 번호</label>
		          <p>접수내용 안내를 위해 알림받을 때 사용할 핸드폰 정보입니다.</p>
		        </div>
	        </div>
	        
	        <div class="row">
	          <button class="btn waves-effect waves-light" type="submit" name="action">Submit
    				<i class="material-icons right">send</i>
  			 </button>
			</div>	        
		</form>
	</div>	
<script>
  function categoryChange(e) {
  let _product = ['상품 품질불량', '상품 조회', '상품 행사문의', '상품 기타사항'];
  let _delivery = ['오배송', '교환/환불', '배송누락', '배송 기타사항'];
  let target = document.getElementById("good");

  if(e.value == "product") var d = _product;
  else if(e.value == "delivery") var d = _delivery;
    
  target.options.length = 0;
 
  for (x in d) {
    var opt = document.createElement("option");
    opt.value = d[x];
    opt.innerHTML = d[x];
    target.appendChild(opt);
  } 
  }
    let good;
    let value1;
    
    document.getElementById("value1").onclick = function(){
        value1 = document.getElementById("value1").value;
        document.inquiry_form.cs_category_one.value = value1;
    }
    
    document.getElementById("good").onclick = function(){
         good = document.getElementById("good").value;
         document.inquiry_form.cs_category_two.value = good;
    }
    
</script>
</body>
</html>
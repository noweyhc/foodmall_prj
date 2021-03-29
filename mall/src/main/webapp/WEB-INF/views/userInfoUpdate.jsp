<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
    <link rel="stylesheet" href="/static/css/mypageStyle.css">
    <!-- Compiled and minified CSS -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">

    <!-- Compiled and minified JavaScript -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
	
	<!-- css  -->    
    <link rel="stylesheet" href="/static/css/myPage/userInfoUpdate.css">
    <link rel="stylesheet" href="/static/css/myPage/wrap.css">
</head>

<body>

  <div class="row wrap">
    <div class="col s12">
    <h2>이메일 변경</h2>
      <!-- 사용자 이름  -->
      <input type="hidden" name="mem_id" readonly="readonly" value="${mVo.mem_id }"><br>
      <div class="row">
        <div class="input-field col s10">
          <i class="material-icons prefix">account_box</i>
          <input disabled value="${mVo.mem_id }" id="mem_id" type="text" class="validate">
          <label for="mem_id">사용자 이름</label>
        </div>
      </div>
      
      	<!-- 현재 반찬 계정 이메일  -->
        <div class="row">
	        <div class="input-field col s10">
	          <i class="material-icons prefix">email</i>
	          <input disabled value="${mVo.mem_email }" id="mem_email" type="text" class="validate">
	          <label for="mem_email">반찬 계정 이메일</label>
	          <p>아이디, 비밀번호 찾기 등 본인확인이 필요한 경우 또는 유료 결제 등 반찬사이트로부터 알림을 받을 때 사용할 이메일입니다.</p>
	        </div>
        </div>        
        
        <!-- 이메일 인증 -->
       	<input type="hidden" name="mem_id" value="${mVo.mem_email}">
       	<div class="row">
       	<a class="waves-effect waves-light btn" id="btn-email"><i class="material-icons left">update</i>이메일 수정</a>
        </div>
        <!-- 이메일 변경 폼  -->
        <div id="emailForm">
       		 <!-- 변경할 이메일 -->
	        <div class="row">
				<div class="input-field col s5">
				  <i class="material-icons prefix">email</i>
		          <input id="email" type="text" class="validate" name="mem_email">
		          <label for="last_name">변결할 이메일</label>
		          <!-- 인증번호 버튼  -->
		          <a class="waves-effect waves-light btn-small" id="btnEmailSendCode"><i class="material-icons left">send</i>이메일 인증 번호</a>
		        </div>
			</div>
			<p id="emailValidCheck"></p>
			<!-- 인증번호 입력 -->
			<div class="row">
				<div class="input-field col s5">
				  <i class="material-icons prefix">verified_user</i>
		          <input id="inputEmail" name="validInput"type="text" class="validate">
		          <label for="last_name">인증번호 입력</label>
		        </div>			
			</div>			
			<!-- 수정/수정 취소 버튼  -->	
			<div class="row">
				<a class="waves-effect waves-light btn-small" id="btnEmailCheck"><i class="material-icons left">check</i>수정 완료</a>
	           <a class="waves-effect waves-light btn-small" id="btnEmailCheckCancel"><i class="material-icons left">cancel</i>수정 취소</a>
			</div>
        </div>
        
        <hr>
        <h2>핸드폰 번호 수정</h2>
        <!-- 현재 휴대전화 번호  -->
          <input type="hidden" name="mem_phone" value="${mVo.mem_phone}">
	      <div class="row">
	        <div class="input-field col s10">
	          <i class="material-icons prefix">local_phone</i>
	          <input disabled value="${mVo.mem_phone }" id="mem_phone" type="text" class="validate">
	          <label for="mem_id">밥도둑 핸드폰 번호</label>
		          <p>아이디, 비밀번호 찾기 등 본인확인이 필요한 경우 사용할 핸드폰 번호입니다.</p>
	        </div>
	      </div>        
	      <!-- 핸드폰 번호 수정 버튼 -->
	      <div class="row">
	       		<a class="waves-effect waves-light btn" id="btn-phone"><i class="material-icons left">update</i>핸드폰 번호 수정</a>
	      </div>	      
	      <!-- 핸드폰 번호 수정 폼 -->
         <div id="phoneForm">
         	<!-- 변경할 핸드폰 번호 ipnut창 -->
	        <div class="row">
				<div class="input-field col s5">
				  <i class="material-icons prefix">local_phone</i>
		          <input id="phone" name="phone" type="text" class="validate">
		          <label for="last_name">변경할 휴대전화</label>	          
		          <a class="waves-effect waves-light btn-small" id="btnPhoneSendCode"><i class="material-icons left">send</i>휴대폰 번호 인증 번호</a>
		        </div>
			</div>         
			<!-- 인증번호 입력 -->
			<div class="row">
				<div class="input-field col s5">
				  <i class="material-icons prefix">verified_user</i>
			      <input id="validPhoneInput" name="validInput"type="text" class="validate">
			      <label for="last_name">인증번호 입력</label>
			    </div>			
			</div>	
			<!-- 수정/수정 취소 버튼  -->	
			<div class="row">
				<a class="waves-effect waves-light btn-small" id="btnPhoneCheck"><i class="material-icons left">check</i>수정 완료</a>
	           <a class="waves-effect waves-light btn-small" id="btnPhoneCheckCancel"><i class="material-icons left">cancel</i>수정 취소</a>
			</div>
        </div>
        </div>
        </div>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="/static/js/emailcertification.js"></script>
<script type="text/javascript" src="/static/js/phonecertification.js"></script>
</body>
</html>
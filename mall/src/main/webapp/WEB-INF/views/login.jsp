<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Document</title>
</head>
<body>
<!DOCTYPE html>
	<html lang="en">
	<head>
	    <meta charset="UTF-8">
	    <title>Document</title>
	    <!-- Compiled and minified CSS -->
	    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">
	
	    <!-- Compiled and minified JavaScript -->
	    <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>
	    
	    <!-- icon -->
	    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
	    <link rel="stylesheet" href="/static/css/login.css">
	    <link rel="stylesheet" href="/static/css/myPage/wrap.css">
		<link rel="stylesheet" href="css/detailProductsStyle.css">
	
	    <link rel="stylesheet" href="login.css">
	</head>
	<body>
	<div class="wrap">
	     <!-- 아이디 -->
	    <form action="/login.do" method="post"  class="col s10">
	    <div class="login-container">
	         <div class="login-background">
	          <div class="row">
	           <!-- 아이디 -->
	            <div class="input-field col s10">
	              <i class="material-icons prefix">account_box</i>
	              <input type="text" id="mem_id" name="mem_id" type="text" class="validate">
	              <label for="first_name">아이디</label>
	            </div><!--input-->
	          </div><!--row-->
	          
	          <!-- 비밀번호 -->   
	          <div class="row">
	            <div class="input-field col s10">
	                <i class="material-icons prefix">enhanced_encryption</i>
	              <input id="mem_pwd" name="mem_pwd" type="password"  class="validate">
	              <label for="password">Password</label>
	            </div><!--input-->
	          </div><!--비밀번호-->  
	        <!--로그인 제출 버튼--> 
	        아이디 찾기 비밀번호 찾기 
	        <div class="login-btn">   
	            <button class="btn waves-effect waves-light" type="submit" name="action">로그인
	                <i class="material-icons right">send</i>
	            </button><!--button-->
	        </div>    
	        </div><!--login-container-->       
	    </div><!--login-container--> 
	    </form><!--form-->   
	    </div>
	    	<%@ include file="footer.jsp" %>
/*
<script type="text/javascript" src="http://developers.kakao.com/sdk/js/kakao.js"></script>
<script>
	Kakao.init("72af82160c18ebef80293d4cbccbe1f6");
	function kakaoLogin(){
		Kakao.Auth.login({
			scope:"profile,account_email,gender",
			success:function(authObj) {
				console.log(authObj);
				Kakao.API.request({
					url:"/v2/user/me",
					success:function(res){
					
						let email = res.kakao_account.email;
						location.href="/kakaoLoginOK.do?email="+email;
					
					}
				});
			}
		});
	}
</script>
	<a href="javascript:kakaoLogin()">카카오 로그인</a>
</body>
    <script type="text/javascript">
        function validateCheck() {
            var id = $('#id').val();
            var pwd = $('#pwd').val();

            if (!id) {
                alert("아이디를 입력해 주세요.");
                $('#id').focus();
                return false;
            }

            if (!pwd) {
                alert("비밀번호를 입력해 주세요.");
                $('#pwd').focus();
                return false;
            }
        }
    </script>
<script src="https://apis.google.com/js/platform.js" async defer></script>
<div class="g-signin2" data-onsuccess="onSignIn"></div>


<script>
function onSignIn(googleUser) {
	  var profile = googleUser.getBasicProfile();
	  alert(profile.getEmail());
	  console.log('ID: ' + profile.getId()); // Do not send to your backend! Use an ID token instead.
	  console.log('Name: ' + profile.getName());
	  console.log('Image URL: ' + profile.getImageUrl());
	  console.log('Email: ' + profile.getEmail()); // This is null if the 'email' scope is not present.
	}
</script>
*/
</html>
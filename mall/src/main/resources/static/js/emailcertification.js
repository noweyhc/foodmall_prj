let code; //근데 여기서는 또 잘되요 위에 이메일 인증하는 코드입니다

let emailValid = {

	init : function(){
		let _this = this;
		
		$("#btn-email").on('click',function(){
			_this.showEmailForm();
		});
        $("#btnEmailCheckCancel").click(function(){
			_this.btnChkCan();	
		});
        $("#btnEmailSendCode").click(function(){
			_this.sendEmaCode();		
		});
        $("#btnEmailCheck").click(function(){
			_this.chckEmail();
		});
	},
	
	showEmailForm : function(){
       $("#emailForm").css("visibility",'visible'); 
	},
	
	btnChkCan : function(){
	   $("#emailForm").css("visibility",'hidden');        
	},
	sendEmaCode : function(){
           let email = $("#email").val();
           
            if (email == '') {
            	$('#emailValidCheck').html('인증번호를 입력해주시기바랍니다.').css('color','red');
            	$('#email').val('');
            	$('#email').focus();
            }
            
            let data = {email:email};
            
            $.ajax({
                url :"/sendEmailCode.do",
                data:data
            }).done(function(data){
                code = data;
                alert("인증코드를 발송하였습니다.");
            });
            
	},
	chckEmail : function(){
		    let userCode = $("#inputEmail").val();
        	let email = $('#email').val();
        	
        	let data = {email:email};
        
        	if(code == userCode){
        		$.ajax({
        			url : "/updateEmail.do",
        			data:data,
					type:'GET'
        		}).done(function(data){
					alert('성공적으로 이메일 변경이 완료되었습니다.');
        		}).fail(function(){
					alert("실패");
				});
        	}else{
        		alert("일치하지않습니다.");
				alert(userCode);
				alert(email);
        		$('#validInput').val('');
        	}
	}
}

emailValid.init();
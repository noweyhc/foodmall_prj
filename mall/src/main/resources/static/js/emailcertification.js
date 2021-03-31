let code;

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
           
            if ($('#email').val() == '') {
				toastr.options = {
						  "closeButton": false,
						  "debug": false,
						  "newestOnTop": false,
						  "progressBar": false,
						  "positionClass": "toast-bottom-right",
						  "preventDuplicates": false,
						  "onclick": null,
						  "showDuration": "300",
						  "hideDuration": "1000",
						  "timeOut": "5000",
						  "extendedTimeOut": "1000",
						  "showEasing": "swing",
						  "hideEasing": "linear",
						  "showMethod": "fadeIn",
						  "hideMethod": "fadeOut"
				};
						toastr.error('이메일을 정확히 입력해 주시기바랍니다.');
            		$('#email').focus();
					return false;
            }
            
            let data = {email:email};
            
            $.ajax({
                url :"/sendEmailCode.do",
                data:data
            }).done(function(data){
                code = data;
				toastr.options = {
						  "closeButton": false,
						  "debug": false,
						  "newestOnTop": false,
						  "progressBar": false,
						  "positionClass": "toast-bottom-right",
						  "preventDuplicates": false,
						  "onclick": null,
						  "showDuration": "300",
						  "hideDuration": "1000",
						  "timeOut": "5000",
						  "extendedTimeOut": "1000",
						  "showEasing": "swing",
						  "hideEasing": "linear",
						  "showMethod": "fadeIn",
						  "hideMethod": "fadeOut"
				};
						toastr.success('인증코드를 발송하였습니다.');
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
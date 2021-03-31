let phonecode; 

let phoneValid = {
	
	init : function(){
		let _this = this;
		
		$("#btn-phone").click(function(){
			_this.showPhoneForm();
		});	
		
        $("#btnPhoneCheckCancel").click(function(){
			_this.hiddPhoneChck();
		});
		
        $("#btnPhoneSendCode").click(function(){
			_this.sendPhoneCode();
		});		
		
        $("#btnPhoneCheck").click(function(){
			_this.chkPhone();
		});
	},
	
   	// 핸드폰 수정 클릭 시 visible 
	showPhoneForm : function(){
           $("#phoneForm").css('visibility','visible'); 		
	},
	
	// 핸드폰 수정 취소 클릭 시 visible 
	hiddPhoneChck : function(){
		   $("#phoneForm").css("visibility",'hidden');        
	},
	
	// 핸드폰 인증
	sendPhoneCode : function(){
       let phone = $("#phone").val();
       let data = {phone:phone};
            
       $.ajax({
           url :"/sendPhoneCode.do",
           data:data
       }).done(function(data){
       		phonecode = data;
            alert("인증코드를 발송하였습니다.");
       });		
	},
	
	//핸드폰 인증완료 후 수정완료 클릭 시 핸드폰번호 변
	chkPhone : function(){
        let userCode = $("#validPhoneInput").val();
        let phone = $('#phone').val();
        	
        let data = {phone:phone};
        
        if(phonecode == userCode){
        	$.ajax({
        		url : "/updatePhone.do",
        		data:data
        	}).done(function(){
				alert('성공적으로 변경이 완료 되었습니다.');
        	});
        }else{
        	alert("일치하지않습니다.");
        	$('#validInput').val('');
        }		
	}
}

phoneValid.init();
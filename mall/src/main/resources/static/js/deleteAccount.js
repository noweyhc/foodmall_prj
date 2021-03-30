let account = {
	
	init : function(){
		let _this = this;
		
		$("#deleteCheck").on('click',function(){
			_this.deleteAccount();
		});
	},
	
	deleteAccount : function(){
	    if(!$("#deleteVaild").is(":checked")){
	    	alert("탈퇴를 하려면 동의하기를 누르셔야합니다.");
	    }else{
	    	$("#deleteForm").submit();
	    }		
	}
}

account.init();
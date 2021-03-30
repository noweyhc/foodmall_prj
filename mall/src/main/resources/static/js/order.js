let  valid = {
    init : function (){
        let _this = this;

	    $('#ship1').on('click',function(){
	        $('#ship-wrap1').show();
	        $('#ship-wrap2').hide();
	    });

	    $('#ship2').on('click',function(){
			_this.ship2();
	    })
		$('#payment').on('click',function(){
			_this.payment();		
		})
    },
	ship1 : function (){
        $('#ship-wrap1').show();
        $('#ship-wrap2').hide();
	},
    ship2 : function (){
        $('#ship-wrap2').show();
        $('#ship-wrap1').hide();
    },
	payment : function (){
	if(!$('#order-agree').is(':checked')){
		alert('결제를 하려면 약관 동의를 하셔야합니다.');
		return false;
	}else{
    $(function(){
	        var IMP = window.IMP; // 생략가능
	        IMP.init('imp91911023'); // 'iamport' 대신 부여받은 "가맹점 식별코드"를 사용
	        var msg;
	        
	        IMP.request_pay({
	            pg : 'kakaopay',
	            pay_method : 'card',
	            merchant_uid : 'merchant_' + new Date().getTime(),
	            name : '밥도둑',
	            amount : totAmount,
	            buyer_email : totAmount,
	            buyer_name : '장일규',
	            buyer_tel : '01066827082',
	            buyer_addr : 'adad',
	            buyer_postcode : '456465',
	        }, function(rsp) {
	            if ( rsp.success ) {
	                //[1] 서버단에서 결제정보 조회를 위해 jQuery ajax로 imp_uid 전달하기
	                jQuery.ajax({
	                    url: "/payments/complete", //cross-domain error가 발생하지 않도록 주의해주세요
	                    type: 'POST',
	                    dataType: 'json',
	                    data: {
	                        imp_uid : rsp.imp_uid
	                        //기타 필요한 데이터가 있으면 추가 전달
	                    }
	                }).done(function(data) {
	                    //[2] 서버에서 결제정보확인 및 서비스루틴이 정상적인 경우
	                    if ( everythings_fine ) {
	                        msg = '결제가 완료되었습니다.';
	                        msg += '\n고유ID : ' + rsp.imp_uid;
	                        msg += '\n상점 거래ID : ' + rsp.merchant_uid;
	                        msg += '\결제 금액 : ' + rsp.paid_amount;
	                        msg += '카드 승인번호 : ' + rsp.apply_num;
	                        
	                        alert(msg);
	                        $.ajax(function(){
	                        	
	                        
	                        });
	                    } else {
	                        //[3] 아직 제대로 결제가 되지 않았습니다.
	                        //[4] 결제된 금액이 요청한 금액과 달라 결제를 자동취소처리하였습니다.
	                    }
	                });
	                //성공시 이동할 페이지
	                location.href='<%=request.getContextPath()%>/order/paySuccess?msg='+msg;
	            } else {
	                msg = '결제에 실패하였습니다.';
	                msg += '에러내용 : ' + rsp.error_msg;
	                //실패시 이동할 페이지
	                location.href="<%=request.getContextPath()%>/order.do";
	                alert(msg);
	            }
	        });
	    });
	}		
	}
}     
	/*최종 금액*/
	let totAmount = $('#totAmount').val();
	/**/	
$('#payment').on('click',function(){
	if(!$('#order-agree').is(':checked')){
		alert('결제를 하려면 약관 동의를 하셔야합니다.');
		return false;
	}else{
    $(function(){
	        var IMP = window.IMP; // 생략가능
	        IMP.init('imp91911023'); // 'iamport' 대신 부여받은 "가맹점 식별코드"를 사용
	        var msg;
	        
	        IMP.request_pay({
	            pg : 'kakaopay',
	            pay_method : 'card',
	            merchant_uid : 'merchant_' + new Date().getTime(),
	            name : '밥도둑',
	            amount : totAmount,
	            buyer_email : '',
	            buyer_name : '장일규',
	            buyer_tel : '01066827082',
	            buyer_addr : 'adad',
	            buyer_postcode : '456465',
	        }, function(rsp) {
	            if ( rsp.success ) {
	                //[1] 서버단에서 결제정보 조회를 위해 jQuery ajax로 imp_uid 전달하기
	                jQuery.ajax({
	                    url: "/payments/complete", //cross-domain error가 발생하지 않도록 주의해주세요
	                    type: 'POST',
	                    dataType: 'json',
	                    data: {
	                        imp_uid : rsp.imp_uid
	                        //기타 필요한 데이터가 있으면 추가 전달
	                    }
	                }).done(function(data) {
	                    //[2] 서버에서 결제정보확인 및 서비스루틴이 정상적인 경우
	                    if ( everythings_fine ) {
	                        msg = '결제가 완료되었습니다.';
	                        msg += '\n고유ID : ' + rsp.imp_uid;
	                        msg += '\n상점 거래ID : ' + rsp.merchant_uid;
	                        msg += '\결제 금액 : ' + rsp.paid_amount;
	                        msg += '카드 승인번호 : ' + rsp.apply_num;
	                        
	                        alert(msg);
	                        $.ajax(function(){
	                        	
	                        
	                        });
	                    } else {
	                        //[3] 아직 제대로 결제가 되지 않았습니다.
	                        //[4] 결제된 금액이 요청한 금액과 달라 결제를 자동취소처리하였습니다.
	                    }
	                });
	                //성공시 이동할 페이지
	                location.href='<%=request.getContextPath()%>/order/paySuccess?msg='+msg;
	            } else {
	                msg = '결제에 실패하였습니다.';
	                msg += '에러내용 : ' + rsp.error_msg;
	                //실패시 이동할 페이지
	                location.href="<%=request.getContextPath()%>/order.do";
	                alert(msg);
	            }
	        });
	    });
	}
});

valid.init();
let inquiryDetail = {
	
	init : function(){
		let _this = this;
		
		$('#btnReply').on('click',function(){
			_this.b();
		});
		
		$('#btnsend').on('click',function(){
			_this.c();
		});
	},
	
	b : function(){
		$('#answer_wrap').show(); 
	},
	
	c : function(){
			// 답변여부
			var resp = $('#cs_respcheck').val();
			
        	let ans_content = $("#ans_content").val();
        	let cs_no = $("#cs_no").val();
        	let ans_title = $("#ans_title").val();
        	let cs_mem_id = $("#cs_mem_id").val();
        	
            let data = { "ans_content" : ans_content, 
						 "cs_no" : cs_no, 
						 "ans_title" : ans_title,
					 	 "cs_mem_id" : cs_mem_id
					   };
            $.ajax({
               url : 'sendQnA.do',
               data : data,
               type : 'POST'
            }).done(function(){
	
            });

			if(resp == 1){
				$("#btnReply").hide();
			}
		    
		    /*summber note*/
			$('#ans_content').summernote('code', '${board_data.BOARD_CONTENT}');
		    $('#ans_content').summernote({ 
		    placeholder: '실제로 고객에 마이페이지에 전송되는 텍스트입니다. 수정이 불가능 하오니 신중히 작성 후 안내 부탁드리겠습니다.',
		     height: 1000,
		     tabsize: 2, 
		     lang: "ko-KR"
		    });
	}
}

inquiryDetail.init();

/*
	// 답변여부
	var resp = $('#cs_respcheck').val();
	
	$('#btnReply').on('click',function(){
        $('#answer_wrap').show(); 
        
        $('#btnsend').on('click',function(){
        	let ans_content = $("#ans_content").val();
        	let cs_no = $("#cs_no").val();
        	let ans_title = $("#ans_title").val();
        	let cs_mem_id = $("#cs_mem_id").val();
        	
            let data = {"ans_content" : ans_content, "cs_no" : cs_no, "ans_title" : ans_title, "cs_mem_id" : cs_mem_id};
            $.ajax({
               url : 'sendQnA.do',
               data : data,
               type : 'POST'
            }).done(function(data){
            });
        });
    });

	if(resp == 1){
		$("#btnReply").hide();
	}

    
    // summber note
	$('#ans_content').summernote('code', '${board_data.BOARD_CONTENT}');
    $('#ans_content').summernote({ 
    placeholder: '실제로 고객에 마이페이지에 전송되는 텍스트입니다. 수정이 불가능 하오니 신중히 작성 후 안내 부탁드리겠습니다.',
     height: 1000,
     tabsize: 2, 
     lang: "ko-KR"
    }); */
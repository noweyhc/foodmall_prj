<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="/static/css/inquiry.css">

<link href="https://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.12/summernote-lite.css" rel="stylesheet"> 
</head>
<body>

<input type="hidden" name="ans_no" id="cs_no" value="${userIqVo.cs_no }">
<input type="hidden" name="cs_mem_id" id="cs_mem_id" value="${userIqVo.cs_mem_id }">
${userIqVo.cs_no }<br>
${userIqVo.cs_mem_id}<br>
${userIqVo.cs_title }<br>
${userIqVo.cs_content }<br>
${userIqVo.cs_regdate }<br>
${userIqVo.cs_category_one }<br>
${userIqVo.cs_category_two }<br>
<img src="inquiry/${userIqVo.cs_fname }"><br>

<button id="btnReply">답변하기</button>

<div id="answer_wrap">

	<input type="text" id="ans_title" name="ans_title">
    <textarea id="ans_content"></textarea><br>
    
    <button type="button" id="btnsend">전송하기</button>
</div>

<div id="a"></div>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.12/summernote-lite.js"></script>
<!--  -->

<script>
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
    /*summber note*/
	$('#ans_content').summernote('code', '${board_data.BOARD_CONTENT}');
    $('#ans_content').summernote({ 
    placeholder: '실제로 고객에 마이페이지에 전송되는 텍스트입니다. 수정이 불가능 하오니 신중히 작성 후 안내 부탁드리겠습니다.',
     tabsize: 2, 
     lang: "ko-KR",
     height: 700 
    }); 
    
</script>
</body>
</html>
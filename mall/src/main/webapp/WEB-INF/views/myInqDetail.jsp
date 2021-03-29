<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
   	<!-- Compiled and minified CSS -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">

    <!-- Compiled and minified JavaScript -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
	<link rel="stylesheet" href="static/css/myPage/myInqDetail.css">
</head>
<body>

<h1>문의 내용</h1>
  <div class="row">
    <div class="col s12">
      <div class="row">
        <div class="input-field col s4">
		  <i class="material-icons prefix">thumb_up</i>
          <input disabled value=${detailVo.cs_no } id="cs_no" type="text" class="validate" >
          <label for="cs_no">글번호</label>
        </div>
        <div class="input-field col s3">
          <i class="material-icons prefix">account_box</i>
          <input disabled value=${detailVo.cs_mem_id } id="cs_mem_id" type="text" class="validate" >
          <label for="cs_mem_id">작성 아이디</label>
        </div>
        <div class="input-field col s3">
          <i class="material-icons prefix">hearing</i>
          <input disabled value=${detailVo.cs_response } id="cs_response" type="text" class="validate" >
          <label for="cs_response">답변 여부</label>
        </div>        
      </div>
      <div class="row">
        <div class="input-field col s3">
          <i class="material-icons prefix">looks_one</i>
          <input disabled value=${detailVo.cs_category_one } id="cs_category_one" type="text" class="validate" >
          <label for="cs_category_one">문의 유형</label>
        </div>
        <div class="input-field col s3">
          <i class="material-icons prefix">looks_two</i>
          <input disabled value=${detailVo.cs_category_two } id="cs_category_two" type="text" class="validate" >
          <label for="cs_category_two">세부 문의 유형</label>
        </div> 
        <div class="input-field col s4">
          <i class="material-icons prefix">date_range</i>
          <input disabled value=${detailVo.cs_regdate } id="cs_regdate" type="text" class="validate" >
          <label for="cs_regdate">작성일</label>
        </div>         
      </div>
        <div class="input-field col s10">
          <i class="material-icons prefix">title</i>
          <input disabled value=${detailVo.ans_title } id="ans_title" type="text" class="validate" >
          <label for="ans_title">문의제목</label>
        </div>       
      <div>
      <div class="row">
        <div class="input-field col s10">
          <i class="material-icons prefix">mode_edit</i>
          <textarea id="icon_prefix2" class="materialize-textarea" disabled="disabled">${detailVo.cs_content }</textarea>
          <label for="icon_prefix2">문의 내용</label>
        </div>
      </div>
      </div>
      
	  <div class="row">
	  	<div class="input-field col s5">
	  	<i class="material-icons prefix">image</i>
	  	<br>
	  	<br>
	  	<label for="cs_fname">첨부 이미지</label>
	  		<img src="/inquiry/${detailVo.cs_fname }" id="cs_fname">
        </div>   
	  </div>      
    </div>
  </div>
  
  <hr>
  <h3>답변</h3>
        <div class="row">
        <div class="input-field col s6">
         <div style=""overflow:auto; "><pre>${detailVo.ans_content }</pre></div>
        </div>
      </div>

</body>
</html>
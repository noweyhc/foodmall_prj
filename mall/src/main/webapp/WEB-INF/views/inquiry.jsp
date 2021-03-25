<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Document</title>
</head>
<body>

	<form method="post" enctype="multipart/form-data" name="inquiry_form">

		<label>문의유형</label> 
		<select onchange="categoryChange(this)" id="value1">
			<option>선택해주세요</option>
			<option value="product">상품문의</option>
			<option value="delivery">배송문의</option>
		</select> 
			<input type="hidden" name="cs_category_one" value="">
		
		<select id="good">
			<option>선택해주세요</option>
		</select> <br> 
		<input type="hidden" name="cs_category_two" id="cs_category_two" value="">
		
		<label>고객명</label> : ${uv.mem_name}
		<br> 
		<input type="hidden" name="mem_name" value="${uv.mem_name }"> 
		<label>아이디</label> : ${uv.mem_id }
		<br>
        <br>
        <label>제목</label><br>
         <input type="text" size="68" id="cs_title" name="cs_title"><br>

		<label>내용</label> <br>
		<textarea cols="70" rows="20" maxlength="550" id="cs_content" name="cs_content"
			placeholder="문의유형을 선택후 문의내용을 자세하게 작성해주세요.
			자세한 내용을 함께 보내주시면 더욱 신속히 답변드릴수 있습니다.

		10월 18일부터 산업안전보건법에 고객응대 근로자 보호조치가 시행됩니다(시행 년도 2018년).고객응대근로자에게 폭언, 욕설 등을 하지 말아주세요."></textarea>
		<br> 
		파일 <input type="file" name="uploadFile"><br>
        <label>이메일주소</label> <input type="text" placeholder="예) example@banchan.com" name="cs_email"><br> 
        <label>핸드폰 번호</label> <input type="text" placeholder="01012345678" name="cs_phone"><br>

		<button type="submit" id="submit1">문의 접수</button>
	</form>
<script>
    function categoryChange(e) {
  let _product = ['상품 품질불량', '상품 조회', '상품 행사문의', '상품 기타사항'];
  let _delivery = ['오배송', '교환/환불', '배송누락', '배송 기타사항'];
  let target = document.getElementById("good");

  if(e.value == "product") var d = _product;
  else if(e.value == "delivery") var d = _delivery;
    
  target.options.length = 0;
 
  for (x in d) {
    var opt = document.createElement("option");
    opt.value = d[x];
    opt.innerHTML = d[x];
    target.appendChild(opt);
  } 
  }
    let good;
    let value1;
    
    document.getElementById("value1").onclick = function(){
         value1 = document.getElementById("value1").value;
        console.log(value1);
        document.inquiry_form.cs_category_one.value = value1;
    }
    
    document.getElementById("good").onclick = function(){
         good = document.getElementById("good").value;
         console.log(good);
         document.inquiry_form.cs_category_two.value = good;
    }
</script>
</body>
</html>
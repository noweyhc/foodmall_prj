<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<input type="button" value="관리자 메인" onclick='location.href = "/admin"'>
<hr>
임시 상품 등록 페이지<br><br>
<input type="button" id="randomBtn" value="랜덤 생성" style="background-color:cyan">
등록 실패 시 상품번호 중복이니 재생성 후 등록하세요<br>
<div style="font-weigt:bold">
${result }<br>
</div>
<hr>
<form action="productregister" id="product_register" method="post" enctype="multipart/form-data">
		상품번호 : <input type="text" name="no" id="no" style="width:50px">
		카테고리 : <select name="category" id="category" style="width:80px">
			<option value="국/탕" selected="selected">국/탕</option>
			<option value="찌개/전골">찌개/전골</option>
			<option value="밀키트">밀키트</option>
			<option value="밑반찬">밑반찬</option>
			<option value="김치">김치</option>
			<option value="기타">기타</option>
		</select>
		재고 : <input type="text" name="stock" id="stock" style="width:30px">
		상품명 : <input type="text" name="title" id="title" style="width:100px">
		상품부제 : <input type="text" name="subtitle" id="subtitle" style="width:140px"><br>
		칼로리 : <input type="text" name="caloies" id="caloies">
		원산지 : <input type="text" name="origin" id="origin">
		보관방법 : <select name="storage" id="storage">
			<option value="냉장" selected="selected">냉장</option>
			<option value="냉동">냉동</option>
			<option value="실온">실온</option>
		</select><br>
		상품 세부글 : <input type="text" name="detailText" id="detailText"  style="width:650px"><br>
		재료 : <input type="text" name="ingredient" id="ingredient">
		가격 : <input type="text" name="price" id="price"  style="width:100px"><br>
		
		<input type="hidden" name="mainImg" class="mainImg">
		메인이미지 : <input type="file" id="mainImgFile" name="mainImgFile"><br>
		
		<input type="hidden" name="detailImg1" class="detailImg1">
		상세이미지1 : <input type="file" id="detailImgFile1" name="detailImgFile1"><hr>
		
		상세이미지2는 필수가 아닙니다<br>
		<input type="hidden" name="detailImg2" class="detailImg2">
		상세이미지2 : <input type="file" id="detailImgFile2" name="detailImgFile2"><br>
		<hr>
		<br>
		<input type="submit" value="등록">
		<input type="reset" value="취소">
</form>

<script type="text/javascript">
	
	const randomBtn = document.getElementById('randomBtn');
	
	let arr_category = ['국/탕', '찌개/전골', '밀키트', '밑반찬', '김치', '기타'];
	let arr_name = ['미역국', '김치찌개', '숙주나물무침', '겉절이 김치', '석박지', '카레', '짜장', '비빔밥',
					'볶음밥', '동치미', '돈까스', '냉면', '오이소박이', '배추전', '닭꼬치'];
	let arr_subname = ['짭짤한 ', '매콤 ', '새콤달콤 ', '톡 쏘는 ', '산뜻한 ', '건강 ', '비건 ', '고기듬뿍 ',
						'중화풍 ', '인도풍 ', '한그릇 ', '푸짐 ', '저칼로리 ', '영양만점 '];
	let arr_origin = ['국산', '수입산'];
	let arr_storage = ['냉장', '냉동', '실온'];
	let product_name = '';
	
	function getRandomInt(min, max) {
	  min = Math.ceil(min);
	  max = Math.floor(max);
	  return Math.floor(Math.random() * (max - min + 1)) + min; //최댓값도 포함, 최솟값도 포함
	}
	
	randomBtn.addEventListener('click', () => {
		
		product_name = arr_subname[getRandomInt(0, arr_subname.length-1)] + arr_name[getRandomInt(0, arr_name.length-1)];
		document.getElementById('no').value = getRandomInt(1, 9999);
		document.getElementById('category').value = arr_category[getRandomInt(0, 5)];
		document.getElementById('stock').value = 99;
		document.getElementById('title').value = product_name;
		document.getElementById('subtitle').value = (product_name + '의 소제목');
		document.getElementById('caloies').value = getRandomInt(10, 100) * 10;
		document.getElementById('origin').value = arr_origin[getRandomInt(0, 1)];
		document.getElementById('storage').value = arr_storage[getRandomInt(0, 2)];
		document.getElementById('detailText').value = (product_name +'의 상세글 내용');
		document.getElementById('ingredient').value = (product_name +'의 재료들');
		document.getElementById('price').value = getRandomInt(1, 500) * 100;
	});


</script>
</body>
</html>

const randomBtn = document.getElementById('randomBtn');

let arr_category = ['국/탕', '찌개/전골', '밑반찬', '김치', '기타'];
let arr_name = ['미역국', '김치찌개', '숙주나물무침', '겉절이 김치', '석박지', '카레', '짜장', '비빔밥',
				'볶음밥', '동치미', '돈까스', '냉면', '오이소박이', '배추전', '닭꼬치'];
let arr_subname = ['짭짤한 ', '매콤 ', '새콤달콤 ', '톡 쏘는 ', '산뜻한 ', '건강 ', '비건 ', '고기듬뿍 '];
let arr_origin = ['국산', '수입산'];
let arr_storage = ['냉장', '냉동'];
let product_name = '';

function getRandomInt(min, max) {
  min = Math.ceil(min);
  max = Math.floor(max);
  return Math.floor(Math.random() * (max - min + 1)) + min; //최댓값도 포함, 최솟값도 포함
}

randomBtn.addEventListener('click', () => {
	
	product_name = arr_subname[getRandomInt(0, arr_subname.length-1)] + arr_name[getRandomInt(0, arr_name.length-1)];
	document.getElementById('no').value = getRandomInt(1, 9999);
	document.getElementById('category').value = arr_category[getRandomInt(0, 4)];
	document.getElementById('stock').value = 99;
	document.getElementById('title').value = product_name;
	document.getElementById('subtitle').value = (product_name + '의 소제목');
	document.getElementById('caloies').value = getRandomInt(10, 100) * 10;
	document.getElementById('origin').value = arr_origin[getRandomInt(0, 1)];
	document.getElementById('storage').value = arr_storage[getRandomInt(0, 1)];
	document.getElementById('detailText').value = (product_name +'의 상세글 내용');
	document.getElementById('ingredient').value = (product_name +'의 재료들');
	document.getElementById('price').value = getRandomInt(1, 500) * 100;
});


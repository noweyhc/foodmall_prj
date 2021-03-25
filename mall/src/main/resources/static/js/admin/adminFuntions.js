   
//상품 삭제 시 확인창을 띄우는 함수
function deleteConfirmProduct(no, productName){
	let deleteConfirm = confirm(no +'번 상품 : ' + productName + ' 삭제합니다');
	if(deleteConfirm){
		location.href = "/admin/product-edit/delete?no=" + no;
	}
}

//이미지 업로드 시 미리보기 기능 구현
function setImg(event, imgId) {
	let reader = new FileReader();
	reader.onload = e => {
		let img = document.getElementById(imgId);
		img.src = e.target.result;
	}
	reader.readAsDataURL(event.target.files[0]);				
};

//상품 검색창 띄우기
function popupSearch(){
	window.open('product-search', '상품 검색', 'width=800, height=600, left=200, top=50');
}

//상품명 검색 시 검색 결과를 바로바로 반영하는 기능
//searchBoxId : 검색어가 입력되는 input 칸의 id
function search(searchBoxId){
	//검색 키워드를 입력창에서 키가 입력될 때 마다 받아옴
	let keyword = document.getElementById(searchBoxId).value;
	let data = {'keyword' : keyword};
	if(keyword != ''){
		$.ajax({
			url:'/admin/product-search',
			data: data,
			type:'post',
			success:function(data){
				$("#resultList").empty();
				$("#resultList").append('<tr><td width="100px">상품번호</td><td width="200px">카테고리</td><td width="300px">상품명</td><td width="200px">원가격</td></tr>');
				
				$.each(data, function(index, item){
					let tr = $("<tr></tr>");
					let td1 = $("<td></td>").html(item.product_no);
					let td2 = $("<td></td>").html(item.product_category);
					let td3 = $("<td></td>").html(item.product_title);
					let td4 = $("<td></td>").html(item.product_price);
					
					$(tr).click(function(){
						selectProduct(item.product_no, item.product_title, item.product_price);
					});
					$(tr).append(td1,td2,td3,td4);
					$("#resultList").append(tr);
				})
			}
		})
	}
}

function selectProduct(no, title, price){
	opener.document.getElementById("productNo").value = no;
	opener.document.getElementById("productTitle").value = title;
	opener.document.getElementById("productPrice").value = price;
	window.close();
}


//할인 삭제 시 확인창을 띄우는 함수
function deleteConfirmSale(no){
	let deleteConfirm = confirm(no +'번 상품 할인을 삭제합니다');
	if(deleteConfirm){
		location.href = "/admin/sale-edit/delete?no=" + no;
	}
}



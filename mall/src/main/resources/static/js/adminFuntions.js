
function deleteConfirm(no, productName){
	let deleteConfirm = confirm(no +'번 상품 : ' + productName + ' 삭제합니다');
	if(deleteConfirm){
		location.href = "/admin/product-edit/delete?no=" + no;
	}
}
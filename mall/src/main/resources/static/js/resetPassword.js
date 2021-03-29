// 값

//유효성
let pwd = /^[A-Za-z0-9]{6,12}$/;


$("#changeSubmit").click(function() {
	let currPassword = $("#currPassword").val();
	let newPassword = $("#newPassword").val();
	let newChkPassword = $("#newChkPassword").val();

	if (!currPassword) {
		alert("현재 비밀번호를 입력해주세요.");
		return false;
	}

	if (!newPassword || !newChkPassword) {
		alert("비밀번호를 입력해주세요.");
		return false;
	}

	if (newPassword !== newChkPassword) {
		alert("비밀번호가 일치하지않습니다.");
		return false;
	}


	let data = { currPassword: currPassword };
	$.ajax({
		url: "/getPassword.do",
		type: "POST",
		data: data
	}).done(function(data) {
		if (data != currPassword) {
			alert("현재 비밀번호가 일치하지않습니다.");
			return false;
		}
	});
});

$("#changeSubmit").click(function() {
	let data = { newPassword: newPassword };

	$.ajax({
		url: "/resetPassword.do",
		data: data,
		type: "POST"
	}).done(function(data) {
		alert("성공적으로 비밀번호가 변경되었습니다.");
	}).fail(function() {
		alert("실패");
	});
});

$('#newChkPassword').keyup(function() {
	if ($('#newPassword').val() != $('#newChkPassword').val()) {
		$('#chkpwd').html('비밀번호가 일치하지 않습니다.<br><br>');
		$('#chkpwd').attr('color', '#FF0000');
	} else {
		$('#chkpwd').html('비밀번호 일치합니다.<br><br>');
		$('#chkpwd').attr('color', '#0099FF');
	}
});

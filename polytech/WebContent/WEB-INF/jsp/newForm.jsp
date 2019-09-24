<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*, java.text.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<title>Insert title here</title>
<style>
.common {
	width: 80%;
	margin: auto;
}

.title {
	text-align: center
}
td,tr {
padding: 8px;
}
</style>
<script type="text/javascript">
	function submitForm(mode) { //길이 값이 0인 경우를 잡기 위한 함수	
		if (mode == 'write') {
			const name = document.getElementById('name');
			const old = document.getElementById('old');
			const address = document.getElementById('address');

			if (name.value.trim().length == 0) {
				alert("이름을 입력해주세요");
				return;
			} else if (old.value.trim().length == 0) {
				alert("나이를 입력해주세요");
				return;
			} else if (address.value.trim().length == 0) {
				alert("주소를 입력해주세요");
				return;
			}
			fm.action = "save.html";
		}
		fm.submit();

		alert("등록되었습니다.")
		
		opener.location.reload();
		window.close();
	}
	
	// 입력값 제외 function
	// 입력값의 문자 종류 판별하는 함수 아래 function들에서 활용함.
	function check_key() {
		var char_ASCII = event.keyCode;
		//숫자
		if (char_ASCII >= 48 && char_ASCII <= 57)
			return 1;
		//영어
		else if ((char_ASCII >= 65 && char_ASCII <= 90)
				|| (char_ASCII >= 97 && char_ASCII <= 122))
			return 2;
		//특수기호
		else if ((char_ASCII >= 33 && char_ASCII <= 47)
				|| (char_ASCII >= 58 && char_ASCII <= 64)
				|| (char_ASCII >= 91 && char_ASCII <= 96)
				|| (char_ASCII >= 123 && char_ASCII <= 126))
			return 4;
		//한글
		else if ((char_ASCII >= 12592) || (char_ASCII <= 12687))
			return 3;
		//그 밖에
		else
			return 0;
	}
	//한글 영어만 입력하도록 하는 함수
	function onlyHanEng() {
		if (check_key() != 3 && check_key() != 2) {
			event.returnValue = false;
			alert("한글이나 영문만 입력하세요.");
			return;//리턴으로 아무것도 안 뱉으면 함수가 끝난다
		}
	}

	//특수문자, 따옴표, 홀따옴표 방지
	 function specialChar(){
		  //특수문자 입력방지
		  if ((event.keyCode > 32 && event.keyCode < 48) || (event.keyCode > 57 && event.keyCode < 65) || (event.keyCode > 90 && event.keyCode < 97)){
		   event.returnValue = false;
		  }
		 
		  //따옴표, 홑따옴표
		  if (event.keyCode==34 || event.keyCode==39){
		   event.returnValue = false;
		  }
		 }
</script>

</head>
<body>
	<div class="title common">
		<span style="font-weight: bold; font-size: 2.5em;"><br>
			사용자 등록 </span><br>
		<br>
		<br>
		<br>
	</div>
	<form method="post" id="fm">
		<table align=center>
			<tr>
				<td width=50><p align=center>이름</p></td>
				<td width=50><p align=center>
						<input type="text" name="name" id="name" maxlength="10" style="ime-mode: active" onkeypress='onlyHanEng()'>
					</p></td>
			</tr>
			<tr>
				<td width=50><p align=center>나이</p></td>
				<td width=50><p align=center>
						<input type="text" name="old" id="old" maxlength="3" onKeyup="this.value=this.value.replace(/[^0-9]/g,'');">
					</p></td>
			</tr>
			<tr>
				<td width=50><p align=center>주소</p></td>
				<td width=50><p align=center>
						<input type="text" name="address" id="address" onKeypress="specialChar();">
					</p></td>
			</tr>
			<tr>
				<td height=60></td>
			</tr>
			<tr align=center>
				<td colspan=2 align=center>
					<a href="./0" class="btn btn-warning" onclick="javascript:self.close()">취소</a>
					<input type="button" value="등록" class="btn btn-primary" onclick='submitForm("write")'>
				</td>
			</tr>

		</table>
	</form>
</body>
</html>
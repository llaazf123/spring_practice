<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<title>Insert title here</title>
<style>
.common {
	width:80%;
	margin:auto;
}

.title {
	text-align:center
}
td,tr {
padding: 8px;
}
</style>
<script type="text/javascript">
	function submitForm(mode) { //길이 값이 0인 경우를 잡기 위한 함수	
		if (mode == 'write') {
			const phoneNumber = document.getElementById('phoneNumber');
			const carrier = document.getElementById('carrier');

			if (phoneNumber.value.trim().length == 0) {
				alert("휴대폰 번호를 입력해주세요");
			} 
			fm.action = "phoneCheck.html";
		}
		fm.submit();
	}
</script>
</head>
<body>
<div class="title common">
	<span style = "font-weight: bold; font-size:2.5em;"><br>
		전화번호 수정
	</span><br><br><br><br>
</div>



<form method="post" id="fm">
	<table align=center>
		<input type="hidden" name="phoneId" value = ${editForm2.phoneId}>
		<tr>
			<td align=center width=100>휴대폰번호</td>
			<td><input type="text" name="phoneNumber" id="phoneNumber" value = ${editForm2.phoneNumber} maxlength="15" onKeyup="this.value=this.value.replace(/[^0-9]/g,'');"></td>
		</tr>
		<tr>
			<td align=center>이동통신사</td>
			<c:choose>	<%-- java의 switch문과 비슷 --%>
				<c:when test = "${editForm2.carrier eq 'SKT'}">	<%-- list가 비어있을 시 --%>
					<td>
						<input type="radio" name="carrier" value="SKT" checked="checked" /> SKT
						<input type="radio" name="carrier" value="KT" /> KT
						<input type="radio" name="carrier" value="U+" /> U+
					</td>
				</c:when>
				<c:when test = "${editForm2.carrier eq 'KT'}">	<%-- list가 비어있을 시 --%>
					<td>
						<input type="radio" name="carrier" value="SKT"/> SKT
						<input type="radio" name="carrier" value="KT"  checked="checked" /> KT
						<input type="radio" name="carrier" value="U+" /> U+
					</td>
				</c:when>
			<c:otherwise>
				<td>
					<input type="radio" name="carrier" value="SKT" checked="checked"  /> SKT
						<input type="radio" name="carrier" value="KT" /> KT
						<input type="radio" name="carrier" value="U+"  checked="checked" /> U+
				</td>
			</c:otherwise>
		</c:choose>
	</tr>
		<input type="hidden" name="userEntity.userId"  value = ${editForm2.userEntity.userId}>
		<tr>
			<td height=60></td>
		</tr>
		<tr>
			<td align=center colspan=2><a href = "oneView.html/${editForm2.userEntity.userId}" class="btn btn-warning" onclick="javascript:self.close()">취소</a>
			<input type="button" value="수정완료" onclick='submitForm("write")' class="btn btn-primary"></td>
		</tr>
	</table>
</form>



</body>
</html>
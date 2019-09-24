<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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
				return;
			} 
			fm.action = "../phoneCheck.html";
		}
		fm.submit();
	}


	function popup(frm)
	{
	  var url    ="http://localhost:8070/polytech/editForm2.html";
	  var title  = "testpop";
	  var status = "toolbar=no,directories=no,scrollbars=no,resizable=no,status=no,menubar=no,width=500, height=500, top=0,left=20"; 
	  window.open("", title,status); //window.open(url,title,status); window.open 함수에 url을 앞에와 같이 인수로 넣어도 동작에는 지장이 없으나 form.action에서 적용하므로 생략가능
	  frm.target = title;                    //form.target 이 부분이 빠지면 form값 전송이 되지 않음 
	  frm.action = url;                    //form.action 이 부분이 빠지면 action값을 찾지 못해서 제대로 된 팝업이 뜨지 않음
	  frm.method = "post";
	  frm.submit();     
	  }


	function popup2(frm)
	{
	  var url    ="http://localhost:8070/polytech/editForm.html";
	  var title  = "testpop";
	  var status = "toolbar=no,directories=no,scrollbars=no,resizable=no,status=no,menubar=no,width=500, height=500, top=0,left=20"; 
	  window.open("", title,status); //window.open(url,title,status); window.open 함수에 url을 앞에와 같이 인수로 넣어도 동작에는 지장이 없으나 form.action에서 적용하므로 생략 가능
	  frm.target = title;                    //form.target 이 부분이 빠지면 form값 전송이 되지 않음
	  frm.action = url;                    //form.action 이 부분이 빠지면 action값을 찾지 못해서 제대로 된 팝업이 뜨지 않음
	  frm.method = "post";
	  frm.submit();     
	  }

</script>
</head>
<body>
<div class="title common">
	<span style = "font-weight: bold; font-size:2.5em;"><br>
		사용자 정보
	</span><br><br><br><br>
</div>
	<table cellspacing=1 width=900 align=center>
		<tr bgcolor="#f5f5f5"
			style="font-size: 1.2em; font-weight: 700; height: 9%; border-bottom: 1px solid #E6E6E6; border-top: 1px solid black;">
			<td width=20%><p align=center>이름</p></td>
			<td width=10%><p align=center>나이</p></td>
			<td width=50%><p align=center>주소</p></td>
			<td width=20%><p align=center>등록 및 수정된 날짜</p></td>
		</tr>
		<tr onmouseover="this.style.background='#F2F2F2'" onmouseout="this.style.background='white'" style="border-bottom:1px solid #E6E6E6;">
			<td width=50><p align=center>${oneView.name}</p></td>
			<td width=50><p align=center>${oneView.old}</p></td>
			<td width=50><p align=center>${oneView.address}</p></td>
			<td width=50><p align=center>${oneView.createdDate}</p></td>
		</tr>
		<tr align=right>
			<td colspan=5>
				<form method="get">
					<input type="hidden" value=${oneView.userId} name=userId>
					<%--<input type="submit" value="사용자 수정" formaction="http://localhost:8070/polytech/editForm.html">  --%>
						<input type="button" class="btn btn-info" value="사용자 수정"   onclick="javascript:popup2(this.form);">
					<input type="submit" class="btn btn-danger" value="사용자 삭제" formaction="http://localhost:8070/polytech/delete.html">
				</form>
			</td>
		</tr>
		</table>
		<div style="margin-top:200px;"></div>
		<table cellspacing=1 width=900 align=center>
		<tr bgcolor="#f5f5f5"
			style="font-size: 1.2em; font-weight: 700; height: 9%; border-bottom: 1px solid #E6E6E6; border-top: 1px solid black;">
			<td width=40%><p align=center>휴대폰번호</p></td>
			<td width=40%><p align=center>이동통신사</p></td>
			<td width=20% colspan=2><p align=center>기타</p></td>
		</tr>

		<c:choose>
			<c:when test="${empty oneView.listOfPhone}">
				<form id="fm" method="post">
					<tr align=center onmouseover="this.style.background='#F2F2F2'" onmouseout="this.style.background='white'" style="border-bottom:1px solid #E6E6E6;">
						<td><input type="text" name="phoneNumber" id="phoneNumber" maxlength="15" onKeyup="this.value=this.value.replace(/[^0-9]/g,'');"></td>
						<td>
							<input type="radio" name="carrier" value="SKT" /> SKT 
							<input type="radio" name="carrier" value="KT" /> KT 
							<input type="radio" name="carrier" value="U+" /> U+
						</td>
						<td colspan=2><input type="button" value="저장" onclick='submitForm("write")' class="btn btn-primary"></td>
					</tr>
					<input type="hidden" name="userEntity.userId" value=${oneView.userId}>
				</form>
			</c:when>
			<c:otherwise>
				<c:forEach items="${oneView.listOfPhone}" var="e">
					<tr align=center onmouseover="this.style.background='#F2F2F2'" onmouseout="this.style.background='white'" style="border-bottom:1px solid #E6E6E6;">
						<td width=50><p align=center>${e.phoneNumber}</p></td>
						<c:choose>
							<c:when test="${e.carrier eq 'SKT'}">
								<td width=50><p align=center>SKT</td>
							</c:when>
							<c:when test="${e.carrier eq 'KT'}">
								<td width=50><p align=center>KT</td>
							</c:when>
							<c:otherwise>
								<td width=50><p align=center>U+</td>
							</c:otherwise>
						</c:choose>
						<td colspan=2 align=center>
							<form method="get">
								<input type="hidden" value=${e.phoneId} name=phoneId> 
								<%--<input type="button" value="수정" formaction="http://localhost:8070/polytech/editForm2.html"> --%>
								<input type="button"  class="btn btn-info" value="수정"   onclick="javascript:popup(this.form);">
								<input type="submit" class="btn btn-danger" value="삭제" formaction="http://localhost:8070/polytech/delete2.html">
							</form>

						</td>
					</tr>
				</c:forEach>
				<form id="fm" method="post">
					<tr align=center onmouseover="this.style.background='#F2F2F2'" onmouseout="this.style.background='white'" style="border-bottom:1px solid #E6E6E6;">
						<td><input type="text" name="phoneNumber" id="phoneNumber" maxlength="15" onKeyup="this.value=this.value.replace(/[^0-9]/g,'');"></td>
						<td>
							<input type="radio" name="carrier" value="SKT" /> SKT 
							<input type="radio" name="carrier" value="KT" /> KT 
							<input type="radio" name="carrier" value="U+" /> U+
						</td>
						<td colspan=2><input type="button" value="저장" onclick='submitForm("write")' class="btn btn-primary"></td>
					</tr>
					<input type="hidden" name="userEntity.userId" value=${oneView.userId}>
				</form>
			</c:otherwise>
		</c:choose>
		<tr>
			<td colspan=5 align=right>
					<p align=center>
						<a href="../0" class="btn btn-warning">목록</a>
					</p>
			</td>
		</tr>
	</table>
</body>
</html>
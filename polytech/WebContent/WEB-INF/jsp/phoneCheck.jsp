<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div class="title common">
	<span style = "font-weight: bold; font-size:2.5em;"><br>
		<center>체크체크체크</center>
	</span><br><br><br><br>
</div>
	<c:choose>
		<%-- 수정하는 경우 --%>
		<c:when test="${!empty phoneCheck2.phoneId}">
			<form method="post" action="edit2.html" id="form">
				<input type="hidden" name="phoneId" value=${phoneCheck2.phoneId}>
				<input type="hidden" name="carrier" value=${phoneCheck2.carrier}>
				<input type="hidden" name="phoneNumber" value=${phoneCheck2.phoneNumber}> 
				<input type="hidden" name="userEntity.userId" value=${phoneCheck2.userEntity.userId}>
			</form>
		</c:when>
		<%-- 신규로 등록하는 경우 --%>
		<c:otherwise>
			<c:choose>
				<c:when test="${phoneCheck}">
					<center>번호가 중복되었습니다. 다시한번 체크해주시길 바랍니다.</center>
					<br><br><br><br><br>
					<center><a href="./0" class="btn btn-warning" onclick="javascript:self.close()" class="btn btn-warning">목록</a></center>
				</c:when>
				<c:otherwise>
					<form method="post" action="save2.html" id="form">
						<input type="hidden" name="carrier" value=${phoneCheck2.carrier}>
						<input type="hidden" name="phoneNumber" value=${phoneCheck2.phoneNumber}> 
						<input type="hidden" name="userEntity.userId" value=${phoneCheck2.userEntity.userId}>
					</form>
				</c:otherwise>
			</c:choose>
		</c:otherwise>
	</c:choose>
	<script type="text/javascript">
		this.document.getElementById("form").submit();
		alert("완료되었습니다.");
		window.opener.location.reload();
		window.close();
	</script>
</body>
</html>
</html>
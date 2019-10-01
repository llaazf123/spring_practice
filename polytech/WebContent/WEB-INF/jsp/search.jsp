<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
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
</style>
</head>
<body>
	<table cellspacing=1 width=900 align=center height=500>
		<c:choose>
			<c:when test="${empty findUserEntityByName}">
				<tr bgcolor="#f5f5f5"
					style="font-size: 1.2em; font-weight: 700; height: 15%; border-bottom: 1px solid #E6E6E6; border-top: 1px solid black;">
					<td width=50><p align=center>user_id</p></td>
					<td width=50><p align=center>이름</p></td>
					<td width=50><p align=center>나이</p></td>
					<td width=50><p align=center>주소</p></td>
					<td width=50><p align=center>등록 및 수정된 날짜</p></td>
				</tr>
				<c:choose>
					<c:when test="${empty findPhoneEntityByPhoneNumber.phoneId}">
						<%-- 이름과 휴대폰번호 둘다 없는 경우 --%>
						<div class="title common">
							<span style="font-weight: bold; font-size: 2.5em;"><br>
								사용자 정보 조회 없음 </span><br>
							<br>
							<br>
							<br>
						</div>
						<tr>
							<td colspan=5>값이 없습니다.</td>
						</tr>
					</c:when>
					<c:otherwise>
						<%-- 휴대폰 번호로 검색 --%>
						<div class="title common">
							<span style="font-weight: bold; font-size: 2.5em;"><br>
								사용자 정보 조회 완료(휴대폰 번호) <br>[${keyword}]</span><br>
							<br>
							<br>
							<br>
						</div>
						<tr>
							<td width=50><p align=center>${findPhoneEntityByPhoneNumber.userEntity.userId}</p></td>
							<td width=50><p align=center><a href="oneView.html/${findPhoneEntityByPhoneNumber.userEntity.userId}">${findPhoneEntityByPhoneNumber.userEntity.name}</a></p></td>
							<td width=50><p align=center>${findPhoneEntityByPhoneNumber.userEntity.old}</p></td>
							<td width=50><p align=center>${findPhoneEntityByPhoneNumber.userEntity.address}</p></td>
							<td width=50><p align=center><fmt:formatDate value="${findPhoneEntityByPhoneNumber.userEntity.createdDate}" pattern="yyyy-MM-dd HH:mm:ss"/></p></td>
						</tr>
					</c:otherwise>
				</c:choose>
			</c:when>


			<c:otherwise>
				<%-- 이름으로 검색 --%>
				<div class="title common">
					<span style="font-weight: bold; font-size: 2.5em;"><br>
						사용자 정보 조회 완료 (이름)<br>[${keyword}]</span><br>
					<br>
					<br>
					<br>
				</div>
				<tr bgcolor="#f5f5f5"
					style="font-size: 1.2em; font-weight: 700; height: 9%; border-bottom: 1px solid #E6E6E6; border-top: 1px solid black;">
					<td width=50><p align=center>user_id</p></td>
					<td width=50><p align=center>이름</p></td>
					<td width=50><p align=center>나이</p></td>
					<td width=50><p align=center>주소</p></td>
					<td width=50><p align=center>등록 및 수정된 날짜</p></td>
				</tr>
				<c:forEach items="${findUserEntityByName}" var="e">
					<tr>
						<td width=50><p align=center>${e.userId}</p></td>
						<td width=50><p align=center><a href="oneView.html/${e.userId}">${e.name}</a></p></td>
						<td width=50><p align=center>${e.old}</p></td>
						<td width=50><p align=center>${e.address}</p></td>
						<td width=50><p align=center><fmt:formatDate value="${e.createdDate}" pattern="yyyy-MM-dd HH:mm:ss"/></p></td>
					</tr>
				</c:forEach>
			</c:otherwise>
		</c:choose>
		<tr align=center>
			<td colspan=5>
				<form>
					<input type="submit" class="btn btn-warning" value="목록" formaction="/polytech/0">
				</form>
			</td>
		</tr>
	</table>
</body>
</html>
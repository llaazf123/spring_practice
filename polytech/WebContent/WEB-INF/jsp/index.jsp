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
</head>
<body>
<div class="title common">
	<span style = "font-weight: bold; font-size:2.5em;"><br>
		사용자 리스트
	</span><br><br><br><br>
</div>
	<table cellspacing=1 width=800 align=center>
	  <tr>			
		<td colspan=2 align=center>
			<form method="get">
				<input type="text" name="keyword" placeholder="이름/전화번호로 검색" style="width:200px; height:50px;">
				<input type="submit" value="검색" formaction="search.html" class="btn btn-success" style="width:80px; height:50px;">
			</form>
		</td>
	</tr>
	<tr bgcolor="#f5f5f5" style = "font-size:1.2em; font-weight: 700;height:9%;border-bottom:1px solid #E6E6E6;border-top:1px solid black;">
		<td width=50%><p align=center>id</p></td>	
		<td width=50%><p align=center>이름</p></td>
	</tr>
	<c:choose>	<%-- java의 switch문과 비슷 --%>
		<c:when test = "${empty users}">	<%-- list가 비어있을 시 --%>
			<tr>
				<td colspan=5>
					값이 없습니다.
				</td>
			</tr>
		</c:when>
		<c:otherwise>
			<c:forEach items="${users.content}" var="e">	<%-- forEach는 반복문 의미, items는 list의미, 변수 e 정의 --%>
			<tr onmouseover="this.style.background='#F2F2F2'" onmouseout="this.style.background='white'" style="border-bottom:1px solid #E6E6E6;">
				<td width=50><p align=center>${e.userId}</p></td>
				<td width=50><p align=center><a href="./oneView.html/${e.userId}">${e.name}</a></p></td>
			</tr>
			</c:forEach>
		</c:otherwise>
	</c:choose>
	
	<tr>
		<td colspan=2 align=center>
			<c:choose>
				<c:when test="${!users.first}">
					<button type="button" class="btn btn-warning" onclick="location.href=${users.number}-1"><</button>
				</c:when>
			</c:choose> 
			<c:forEach begin="${startRange}" end="${endRange}" var="e">
				<button type="button" class="btn btn-warning" onclick="location.href='${e}'">${e+1}</button>
			</c:forEach> 
			<c:choose>
				<c:when test="${!users.last}">
					<button type="button" class="btn btn-warning" onclick="location.href=${users.number}+1">></button>
				</c:when>
			</c:choose>
		</td>
    </tr>
      
	<tr align=center>
		<td colspan=5>
		<form method="get">	<%-- 팝업창 띄우기 --%>
		<a href="newForm.html" class="btn btn-primary" onclick="window.open(this.href, '_blank', 'width=500px,height=500px,toolbars=no,scrollbars=no'); return false;">
			사용자 등록
		</a>
		</form>
		</td>
	</tr>
	</table>
</body>
</html>
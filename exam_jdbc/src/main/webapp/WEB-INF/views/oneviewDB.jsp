<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.*,javax.sql.*,java.io.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h3>조회</h3>
	<hr>

	<table cellspacing=1 width=600 border=1>
	<tr>
		<td width=50><p align=center>이름</p></td>
		<td width=50><p align=center>학번</p></td>
		<td width=50><p align=center>국어</p></td>		
		<td width=50><p align=center>영어</p></td>		
		<td width=50><p align=center>수학</p></td>
	</tr>
	<c:choose>	<%-- java의 switch문과 비슷 --%>
		<c:when test="${empty list}">	<%-- list가 비어있을 시 --%>
			<tr>
				<td colspan=3>
					<spring:message code="common.listEmpty"/>	<%-- 메시지 띄우려면 따로 세팅 필요 --%>
				</td>
			</tr>
		</c:when>
		<c:otherwise>
			<tr>
				<td width=50><p align=center>${list.name}</p></td>
				<td width=50><p align=center>${list.studentid}</p></td>
				<td width=50><p align=center>${list.kor}</p></td>
				<td width=50><p align=center>${list.eng}</p></td>
				<td width=50><p align=center>${list.mat}</p></td>
			</tr>
		</c:otherwise>
	</c:choose>
	</table>
</body>
</html>
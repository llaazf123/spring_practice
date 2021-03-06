<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ page import="java.io.*, java.util.*" %>
<%@ page import="kopo.domain.*" %>
<html>
<head>
	<title>Home</title>
</head>
<body>
이름 : ${name}<br>
주소 : ${addr}<br> 
<hr>
	<table cellspacing=1 width=600 border=1>
	<c:choose>		<%-- java의 switch문과 비슷 --%>
		<c:when test="${empty exam}">	<%-- exam이 비어있을 시 --%>
			<tr>
				<td colspan=3>
					exam 없다
				</td>
			</tr>
		</c:when>
		<c:otherwise>	<%-- exam이 존재할 시 --%>
			<tr>
				<td width=50><p align=center>${exam.name}</p></td>
				<td width=50><p align=center>${exam.studentid}</p></td>
				<td width=50><p align=center>${exam.kor}</p></td>
				<td width=50><p align=center>${exam.eng}</p></td>
				<td width=50><p align=center>${exam.mat}</p></td>
			</tr>
		</c:otherwise>
	</c:choose>
	</table>
<hr>
	<table cellspacing=1 width=600 border=1>
		<c:choose>		<%-- java의 switch문과 비슷 --%>
			<c:when test="${empty exams}">	<%-- exam이 비어있을 시 --%>
				<tr>
					<td colspan=3>
						exams 없다
					</td>
				</tr>
			</c:when>
		<c:otherwise>	<%-- exam이 존재할 시 --%>
			<c:forEach items="${exams}" var="e">	<%-- forEach는 반복문 의미, items는 list의미, 변수 e 정의 --%>
			<tr>
				<td width=50><p align=center>${e.name}</p></td>
				<td width=50><p align=center>${e.studentid}</p></td>
				<td width=50><p align=center>${e.kor}</p></td>
				<td width=50><p align=center>${e.eng}</p></td>
				<td width=50><p align=center>${e.mat}</p></td>
			</tr>
			</c:forEach>
		</c:otherwise>
	</c:choose>
	</table>
<hr><hr>
아래는 Scriptlet방식(spring에서는 잘 사용하지 않음)<br>
<hr>
<%
	String name = (String) request.getAttribute("name");	//name과 addr을 받아옴
	String addr = (String) request.getAttribute("addr");
	out.println("이름 :"+name+"<br>");
	out.println("주소 :"+addr+"<br>");
%>
<hr>
<%
	ExamRIO exam = (ExamRIO) request.getAttribute("exam");
%>
	<table cellspacing=1 width=600 border=1>
		<tr>
			<td width=50><p align=center><%=exam.getName()%></p></td>
			<td width=50><p align=center><%=exam.getStudentid()%></p></td>
			<td width=50><p align=center><%=exam.getKor()%></p></td>
			<td width=50><p align=center><%=exam.getEng()%></p></td>
			<td width=50><p align=center><%=exam.getMat()%></p></td>
		</tr>
	</table>
<hr>
<%
	List<ExamRIO> exams = (List<ExamRIO>) request.getAttribute("exams");	//list형태로 exams를 가져옴
%>
<table cellspacing=1 width=600 border=1>
<%
	for(int i=0;i<exams.size();i++){
		out.println("<tr>");
		out.println("<td width=50><p align=center>" + exams.get(i).getName()+"</p></td>");
		out.println("<td width=50><p align=center>" + Integer.toString(exams.get(i).getStudentid())+"</p></td>");
		out.println("<td width=50><p align=center>" + Integer.toString(exams.get(i).getKor())+"</p></td>");
		out.println("<td width=50><p align=center>" + Integer.toString(exams.get(i).getEng())+"</p></td>");
		out.println("<td width=50><p align=center>" + Integer.toString(exams.get(i).getMat())+"</p></td>");
		out.println("<tr>");
	}
%>
</table>
</body>
</html>

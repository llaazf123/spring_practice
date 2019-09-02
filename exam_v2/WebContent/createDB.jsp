<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*,javax.sql.*,java.io.*" %>
<%@ page import="kopo.domain.*,kopo.dao.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h3>테이블 생성</h3>
	<hr>
	<%
	//createDB 메서드 실행
	try {
		ExamRepo.createDB();
	} catch(Exception e) {
        out.println("테이블 생성중 에러발생."+e);
    }
	%>
</body>
</html>
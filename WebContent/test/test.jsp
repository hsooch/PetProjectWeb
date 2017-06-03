<%@page import="com.pet.test.Test"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%
	List<Test> list = (List) request.getAttribute("list");
%>
<!doctype html>
<html lang="utf-8">
<head>
<meta charset="utf-8">
<title>Document</title>
<style>
</style>
<script>
	
</script>
</head>
<body>
	<%
		for (int i = 0; i < list.size(); i++) {
			Test dto = list.get(i);
			out.print(dto.getTest_id() + "\n");
			out.print(dto.getUsername() + "\n");
			out.print(dto.getPetname() + "\n");
			out.print(dto.getRegdate() + "\n");
			out.print(dto.getHit() + "\n");
			out.print("한개완료\n");
		}
	%>
</body>
</html>

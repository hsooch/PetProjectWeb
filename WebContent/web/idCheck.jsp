<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%
	String msg = (String) request.getAttribute("msg");

	StringBuffer sb = new StringBuffer();

	sb.append("{");
	sb.append("\"msg\":\"" + msg + "\"");
	sb.append("}");

	out.print(sb.toString());
%>
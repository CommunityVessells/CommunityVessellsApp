<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page errorPage="error.jsp" %>
<%@ page import="org.ffcc.communityVessells.app.encryption.EncryptMD5" %>

<!DOCTYPE html>
<html lang="en">
<%@ include file="static_resources/head.html" %>

<body id="comVessells" data-spy="scroll" data-target=".navbar" data-offset="60">



<div>
	<%if (EncryptMD5.encrypt(request.getParameter("password")).equals(request.getAttribute("hash"))) { %>
	<h1>Password ok</h1>
	<p><%=(String)request.getAttribute("hash") %></p>
	<%} %>
</div>
</body>
</html>
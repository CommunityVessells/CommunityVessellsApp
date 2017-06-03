<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="org.ffcc.communityVessells.app.models.Organization"%>
<%@ page import="org.ffcc.communityVessells.app.models.Repository"%>
<%@ page import="org.ffcc.communityVessells.app.dao.RepositoryDAO"%>
<%@ page import="java.util.LinkedList"%>
<%@ page errorPage="error.jsp"%>
<%
	if (session.getAttribute("usertype") != null && session.getAttribute("usertype").equals("organization")) {
%>
<!DOCTYPE html>
<html lang="en">
<%@ include file="static_resources/head.html"%>

<body id="org" data-spy="scroll" data-target=".navbar"	data-offset="60">
	<%
		Organization temp = (Organization) session.getAttribute("organization");
	%>
	<%@ include file="static_resources/loginModal.html"%>
	<%@ include file="navbar_org.jsp"%>

	<div class="container-fluid">
	
	<%int repoID=Integer.parseInt(request.getParameter("repoID")); 
		Repository thisRepo=RepositoryDAO.getRepositoryByID(repoID);
	%>
	<p><%=thisRepo.getTitle() %></p>
	
	</div>
		<%@ include file="static_resources/footer.html"%>

	<%@ include file="static_resources/scriptIncludes.html"%>

	<%@ include file="static_resources/initScript.html"%>

	<%@ include file="static_resources/animateScript.html"%>
	<%@ include file="static_resources/scrollspyScriptOrg.html" %>
	<%@ include file="static_resources/onclickCreateScript.html"%>
	<% } %>
	</body>
	</html>
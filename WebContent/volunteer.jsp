<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="org.ffcc.communityVessells.app.models.Volunteer"%>
<%@ page import="org.ffcc.communityVessells.app.dao.VolunteerDAO"%>
<%@ page import="org.ffcc.communityVessells.app.models.Promise"%>
<%@ page import="org.ffcc.communityVessells.app.models.Repository"%>
<%@ page import="org.ffcc.communityVessells.app.dao.RepositoryDAO"%>
<%@ page import="org.ffcc.communityVessells.app.dao.PromiseDAO"%>
<%@ page import="java.util.LinkedList"%>
<%@ page errorPage="error.jsp" %>
<% if(session.getAttribute("usertype")!=null && session.getAttribute("usertype").equals("volunteer")) { %>
<!DOCTYPE html>
<html lang="en">
<%@ include file="static_resources/head.html" %>

<body id="comVessells" data-spy="scroll" data-target=".navbar" data-offset="60">

<%@ include file="static_resources/loginModal.html" %>
	<%
		Volunteer vol = (Volunteer) session.getAttribute("volunteer");
	%>

<%@ include file="navbar_vol.jsp" %>

	<div class="container-fluid">
<!-- Alerts Start -->
		<%
			if (session.getAttribute("successmessage") != null) {
		%>
		<div class="panel panel-success">
			<div class="panel-heading">
				<h3 class="panel-title text-center" id="panelTitle">Success!</h3>
			</div>
			<div class="panel-body">
				<p class="text-center text-success"><%=(String) session.getAttribute("successmessage")%></p>

			</div>
		</div>


		<%
			}
				session.removeAttribute("successmessage");
		%>


		<%
			if (request.getAttribute("errormessage") != null) {
		%>


		<div class="panel panel-danger">
			<div class="panel-heading">
				<h3 class="panel-title text-center" id="panelTitle">Promised failed to save!</h3>
			</div>
			<div class="panel-body">
				<p class="text-center text-danger"><%=(String) request.getAttribute("errormessage")%></p>
			</div>
		</div>





		<%
			}
		%>

<!-- Alerts End -->

<!-- Jumbotron -->
  <div class="jumbotron text-center light-primary-color">
		<div class="container-fluid">
			<div class="row main">
		<div class="col-sm-2 col-md-2 pull-left"><a href="organization.jsp"><img class="img-responsive img-thumbnail" src="<%=vol.getAvatar()%>" alt="avatar"></a></div>
		<div class="col-sm-10 text-center">
    <h1 class="anton">Welcome <span class="text-primary-color"><%=vol.getUsername()%></span> </h1>

    <h3 class="primary-text-color"><%=vol.getEmail()%></h3>
		</div>
  </div>
</div>
</div>


</div>
<%@ include file="static_resources/footer.html" %>

<%@ include file="static_resources/scriptIncludes.html" %>

<%@ include file="static_resources/initScript.html" %>

<%@ include file="static_resources/animateScript.html" %>
</body>
</html>
<% } %>
<% if(session.getAttribute("usertype")==null){ %>
<jsp:forward page="/"></jsp:forward>
<% } %>
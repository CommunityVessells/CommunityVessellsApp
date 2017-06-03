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
				<h3 class="panel-title text-center" id="panelTitle">Create new
					Repository Failed!</h3>
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
		<div class="col-sm-2 col-md-2 pull-left"><a href="organization.jsp"><img class="img-responsive img-thumbnail" src="<%=temp.getAvatar()%>" alt="avatar"></a></div>
		<div class="col-sm-10 text-center">
    <h1 class="anton">Welcome <span class="text-primary-color"><%=temp.getUsername()%></span> </h1>

    <h3 class="primary-text-color"><%=temp.getType() %></h3>
		</div>
  </div>
</div>
</div>

<!--  Organization title -->
<div class="row-content text-center">
	<h3 class=""><small class="text-accent-color maintext" style="font-family:'Anton', sans-serif;">Organization Details</small></h3>
	<p class="maintext"><b>Name: </b><%=temp.getUsername()%></p>
	<p class="maintext"><b>Email: </b><%=temp.getEmail()%></p>
	<p class="maintext"><b>Description: </b><%=temp.getDescription()%></p>
	<p class="maintext"><b>Type: </b><%=temp.getType()%></p>
</div>
<hr class="divider-color">


<!-- Repositories -->
<div class="row-content text-center" id="Repositories">
	<h3 class="anton"><small class="text-accent-color"><%=temp.getUsername()%> Repositories</small></h3>

<!-- Show All -->
					<button class="btn btn-info" data-toggle="collapse"
						data-target="#repos" id="showAll">
						Show All <span class="glyphicon glyphicon-chevron-down" id="icon"></span>
					</button>
	<div class="collapse" id="repos">
							<%
								RepositoryDAO repodao = new RepositoryDAO();
								LinkedList<Repository> repos = repodao.getRepositories();
								int i=0;
								for (Repository r : repos) {
							%>
							<div class="well">
							<h4 class="list-group-item-heading anton">
								<small>Repository <span class="text-info">
										<%=r.getTitle()%>
								</span> Details
								</small>
							</h4>
							<p>
								<b>Title: </b><%=r.getTitle()%></p>
							<p>
								<b>Repository Type: </b><%=r.getRepoType()%></p>
							<p>
								<b>Total Capacity: </b><%=r.getCapacity()%></p>
							<p>
								<b>Available Products: </b><%=r.getAvailableProducts()%></p>
							<button
								class="btn btn-default btn-raised light-primary-color updateRepo">
								<b>Edit </b><span class="glyphicon glyphicon-plus"
									id="symbolupdate"> </span>
							</button>
							
    
							<%-- URI rewrite repoID --%>
							<a class="btn btn-info btn-raised  updateRepo" href="<%= response.encodeURL ("repository.jsp?repoID="+Integer.toString(r.getRepoID()))%>">
								<b>Details </b><span class="glyphicon glyphicon-eye-open"
									id="symbolview"> </span>
							</a>
							

						</div>
						
						<%
							}
						%>
						</div>
						<!-- loop div end -->
	</div>
	<!-- Repositories end -->
	<hr class="divider-color">
	
	<!-- Create new Repo start -->
	<div class="row-content text-center" id="CreateNew">
				<h4 class="anton">
					<small class="text-accent-color">Create New Repository</small>
				</h4>
				<button class="btn btn-default btn-raised light-primary-color"
					id="createRepo">
					<span class="glyphicon glyphicon-plus" id="symbol"></span>
				</button>
				
				<div class="well hide" id="newForm">
					<%@ include file="static_resources/createRepoForm.html"%>
				</div>
	</div>
	<!-- End Create New -->
	
	
	<!-- Requests start -->
	
</div><!-- End container -->
	<%@ include file="static_resources/footer.html"%>

	<%@ include file="static_resources/scriptIncludes.html"%>

	<%@ include file="static_resources/initScript.html"%>

	<%@ include file="static_resources/animateScript.html"%>
	<%@ include file="static_resources/scrollspyScriptOrg.html" %>
	<%@ include file="static_resources/onclickCreateScript.html"%>
</body>
</html>
<%
	}
%>
<%
	if (session.getAttribute("usertype") == null) {
%>
	<jsp:forward page="/"></jsp:forward>
<%
	}
%>
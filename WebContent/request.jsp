<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="org.ffcc.communityVessells.app.models.Volunteer"%>
<%@ page import="org.ffcc.communityVessells.app.dao.VolunteerDAO"%>
<%@ page import="org.ffcc.communityVessells.app.models.Promise"%>
<%@ page import="org.ffcc.communityVessells.app.models.Request"%>
<%@ page import="org.ffcc.communityVessells.app.models.Repository"%>
<%@ page import="org.ffcc.communityVessells.app.dao.RepositoryDAO"%>
<%@ page import="org.ffcc.communityVessells.app.dao.RequestDAO"%>
<%@ page import="org.ffcc.communityVessells.app.dao.PromiseDAO"%>
<%@ page import="java.util.LinkedList"%>
<%@ page errorPage="error.jsp" %>
<% if(session.getAttribute("usertype")!=null && session.getAttribute("usertype").equals("organization")) { %>
<!DOCTYPE html>
<html lang="en">
<%@ include file="static_resources/head.html" %>

<body id="vol" data-spy="scroll" data-target=".navbar" data-offset="60">

<%@ include file="static_resources/loginModal.html" %>
	<%
		RequestDAO reqDAO = new RequestDAO();
		Request thisRequest = reqDAO.getRequestByID(Integer.parseInt(request.getParameter("requestID")));
	%>

<%@ include file="navbar_org.jsp" %>

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



<!--  Request title -->
<div class="row-content text-center">
	<div class="container-fluid">
	<h2 class="lobster text-accent-color"><%=thisRequest.getTitle()%></h2>
						<div class="center-block">
							<img src="<%=thisRequest.getAvatar() %>" alt="Request Image" class="img-reponsive" style="width:60%;height:auto;">
						</div>
	</div>

	<h3 class=""><small class="text-accent-color maintext" style="font-family:'Anton', sans-serif;">Request Details</small></h3>
	<p class="maintext"><b>Title: </b><%=thisRequest.getTitle()%></p>
	<p class="maintext"><b>Start Date: </b><%=thisRequest.getStartdate()%></p>
	<p class="maintext"><b>Close Date: </b><%=thisRequest.getClosedate()%></p>
	<p class="maintext"><b>Address: </b><%=thisRequest.getAddress()%></p>
</div>
<hr class="divider-color">

<!-- Promises start -->
<div class="container-fluid text-center" id="Promises">
		<h1 class="primary-text-color anton"><small>Promises</small></h1>
		<button class="btn btn-info" data-toggle="collapse"
							data-target="#promises" id="showPromises">
							Show All Promises <span class="glyphicon glyphicon-chevron-down" id="iconPR"></span>
		</button>
		
		<div class="collapse table-responsive" id="promises">
				<div id="records">
				<div class="container-fluid">
					<div class="form-group label-floating">
						<label class="control-label" for="focusedInput1">Search</label>
						<input class="form-control search" type="text" id="focusedInput1"/>
					</div>
				</div>
				</div>

					<table class="table table-hover" id="sortingTable">
					<tr class='light-primary-color text-primary-color'>
 						<td><button class="sort btn text-primary-color" data-sort="email">Email
 						</button></td>
 						<td><button class="sort btn text-primary-color" data-sort="count">Quantity
 						</button></td>
						<td><button class="sort btn text-primary-color" data-sort="title">Title
 						</button></td>
 						<td><button class="sort btn text-primary-color" data-sort="type">Type
 						</button></td>
 						<td><button class="sort btn text-primary-color" data-sort="size">Size</button></td>
						<td><button class="sort btn text-primary-color" data-sort="condition">Condition</button></td>
						<td><button class="sort btn text-primary-color" data-sort="date">Expiry Date</button></td>
						<td><button class="btn text-primary-color">Fulfill</button></td>
						
					</tr>
					<tbody id="tableProd" class="list">
					<% 	LinkedList<Promise> volPromises = null;
						
						
						
						LinkedList<Promise> reqPromises;
						reqPromises = PromiseDAO.getPromisesByRequest(thisRequest.getRequestID());
						
						for(Promise p:reqPromises){
							


					%>	<%if(!p.isFulfilled()) {%>
						<tr class='warning'>
						<%} %>
						<%if(p.isFulfilled()) {%>
						<tr class='success'>
						<%} %>
							<td class="email"><%=p.getVolunteerEmail()%></td>
							<td class="count"><%=p.getCount() %></td>
							<td class="title"><%=p.getTitle() %></td>
							<td class="type"><%=p.getProdType() %></td>
							
							<%if(p.getSize()!=null){ %>
							<td class="size"><%=p.getSize() %></td>
							<%} %>
							<%if(p.getSize()==null) { %>
							<td class="size">N/A</td>
							<%} %>
							<%if(p.getCondition()!=null){ %>
							<td class="condition"><%=p.getCondition() %></td>
							<%}%>
							<%if(p.getCondition()==null) {%>
							<td class="condition">N/A</td>
							<%}%>
							
							<%if(p.getExpire()!=null){ %>
							<td class="date"><%=p.getExpire() %></td>
							<%}%>
							<%if(p.getExpire()==null) { %>
							<td class="date">N/A</td>
							<%} %>
							<%if(!p.isFulfilled()) {%>
							<td class="fulfilled"><a class="btn btn-success btn-sm" href="<%= response.encodeURL ("getproductfrompromise?requestID="+Integer.toString(thisRequest.getRequestID())+"&promiseID="+Integer.toString(p.getPromiseID()))+"&repoID="+Integer.toString(thisRequest.getReposID())%>">OK</a></td>
							<%} %>
							<%if(p.isFulfilled()) {%>
							<td class="fulfilled"><span class="glyphicon glyphicon-ok"></span></td>
							<%} %>
						</tr>	
					<%
							
						}
						 
					%>
					</tbody>
					</table>
		</div>
				
		</div>



</div>
<%@ include file="static_resources/footer.html" %>

<%@ include file="static_resources/scriptIncludes.html" %>

<%@ include file="static_resources/initScript.html" %>
<%@ include file="static_resources/onclickCreateScript.html"%>
<%@ include file="static_resources/animateScript.html" %>
<%@ include file="static_resources/initListpromises.html"%>
</body>
</html>
<% } %>
<% if(session.getAttribute("usertype")==null){ %>
<jsp:forward page="/"></jsp:forward>
<% } %>
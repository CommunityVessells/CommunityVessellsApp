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
<% if(session.getAttribute("usertype")!=null && session.getAttribute("usertype").equals("volunteer")) { %>
<!DOCTYPE html>
<html lang="en">
<%@ include file="static_resources/head.html" %>

<body id="vol" data-spy="scroll" data-target=".navbar" data-offset="60">

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

<!--  Volunteer title -->
<div class="row-content text-center">
	<h3 class=""><small class="text-accent-color maintext" style="font-family:'Anton', sans-serif;">Volunteer Details</small></h3>
	<p class="maintext"><b>Username: </b><%=vol.getUsername()%></p>
	<p class="maintext"><b>Email: </b><%=vol.getEmail()%></p>
	<p class="maintext"><b>First Name: </b><%=vol.getFirstName()%></p>
	<p class="maintext"><b>Last Name: </b><%=vol.getLastName()%></p>
</div>
<hr class="divider-color">

<!-- My Promises start -->
<div class="container-fluid text-center" id="Promises">
		<h1 class="primary-text-color anton">My Promises</h1>
		<button class="btn btn-info" data-toggle="collapse"
							data-target="#promises" id="showAllPromises">
							Show My Pending Promises <span class="glyphicon glyphicon-chevron-down" id="iconP"></span>
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

 						<td><button class="sort btn text-primary-color" data-sort="request">Request
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
						
					</tr>
					<tbody id="tableProd" class="list">
					<% 	LinkedList<Promise> volPromises = null;
						PromiseDAO promiseDAO= new PromiseDAO(); 
						RequestDAO reqDAO = new RequestDAO();
						Request req= null;
						
						volPromises = PromiseDAO.getPromisesByVolunteer(vol.getUserID());
						
						for(Promise p:volPromises){
							
							if(!p.isFulfilled()){	
							req = reqDAO.getRequestByID(p.getRequestID()); 

					%>
						<tr class='warning'>
							<td class="request"><%=req.getTitle()%></td>
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
						</tr>	
					<%
							}
						}
						 
					%>
					</tbody>
					</table>
		</div>
</div>
<hr class="divider-color">

<div class="container-fluid text-center" id="PromisesFull">
		<h1 class="primary-text-color anton">My Fulfilled Promises</h1>
		<button class="btn btn-info" data-toggle="collapse"
							data-target="#promisesful" id="showAllFulfilled">
							Fulfilled Promises <span class="glyphicon glyphicon-chevron-down" id="iconF"></span>
		</button>
		
		<div class="collapse table-responsive" id="promisesful">
				
				
				<div id="records">
				<div class="container-fluid">
					<div class="form-group label-floating">
						<label class="control-label" for="focusedInput2">Search</label>
						<input class="form-control search" type="text" id="focusedInput2"/>
					</div>
				</div>
				</div>
					
					<table class="table table-hover" id="sortingTable">
					<tr class='light-primary-color text-primary-color'>

 						<td><button class="sort btn text-primary-color" data-sort="request">Request
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
						
					</tr>
					<tbody id="tableProd" class="list">
					<% 	
						
						
						
						for(Promise p:volPromises){
							
							if(p.isFulfilled()){	
							req = reqDAO.getRequestByID(p.getRequestID()); 

					%>
						<tr class='success'>
							<td class="request"><%=req.getTitle()%></td>
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
						</tr>	
					<%
							}
						}
						 
					%>
					</tbody>
					</table>
		</div>
</div>
<hr class="divider-color">
<!-- Requests start -->
<div class="text-center"  id="Requests">
<h1 id="purpose" class="primary-text-color">Requests</h1>
<h2 class="anton"><small>Closing soon:</small></h2>
							<%
								RequestDAO requestdao = new RequestDAO();
								LinkedList<Request> requestsList = requestdao.getAllRequests();
							
							%>
							<% 
								for (Request r : requestsList) {
									 
									//check for requests closing max 5 days from now
									if(((int)((r.getClosedate().getTime()-(new java.util.Date().getTime())) / (1000 * 60 * 60 * 24))<=5) && (r.getClosedate().after(new java.sql.Date(new java.util.Date().getTime())))) {
							%>
<div class="well text-center slideanim">
							<h4 class="list-group-item-heading anton">
							
								<small><span class="text-info">
										<%=r.getTitle()%>
								</span> Details
								</small>
							</h4>
							
							<div class="container-fluid">
								<div class="center-block">
									<img src="<%=r.getAvatar() %>" alt="Request Image" class="img-reponsive" style="width:60%;height:auto;">
								</div>
							</div>
							<br>
							<h2 class="lobster text-accent-color"><%=r.getTitle()%></h2>
							<p>
								<b>Product Type: </b><%=RepositoryDAO.getRepositoryByID(r.getReposID()).getRepoType() %></p>
							<p>
								<b>Start Date: </b><%=r.getStartdate() %></p>
							
							<p><b>Closes on: </b><%=r.getClosedate() %></p>
							
							<p><b>Location: </b><%=r.getAddress() %></p>

    						
    							
							<%-- URI rewrite  --%>
							<% if(session.getAttribute("usertype")!=null && session.getAttribute("usertype").equals("volunteer")) {%>
							<a class="btn btn-info btn-raised updateRepo" title="Make a Promise" href="<%=response.encodeURL ("promise.jsp?requestID="+Integer.toString(r.getRequestID()))%>">
								<b>Promise </b><span class="glyphicon glyphicon-eye-open"
									id="symbolview"> </span>
							</a>
							
							<% } 
							 else { %>
							<button class="btn btn-info btn-raised updateRepo" title="You need to sign in to promise" data-toggle="modal" data-target="#loginModal">
								<b>Promise </b><span class="glyphicon glyphicon-eye-open"
									id="symbolview"> </span>
							</button>
							<% } %>

</div>
<% 

									}
								}
%>

		<button class="btn btn-info" data-toggle="collapse"
							data-target="#allrequests" id="showAllRequests">
							Show All Requests <span class="glyphicon glyphicon-chevron-down" id="iconR"></span>
		</button>

							<div class="collapse" id="allrequests">
							<% 
								for (Request r : requestsList) {
							%>
<div class="well text-center">
							<h4 class="list-group-item-heading anton">
							
								<small><span class="text-info">
										<%=r.getTitle()%>
								</span> Details
								</small>
							</h4>
							
							<div class="container-fluid">
								<div class="center-block">
									<img src="<%=r.getAvatar() %>" alt="Request Image" class="img-reponsive" style="width:60%;height:auto;">
								</div>
							</div>
							<br>
							<h2 class="lobster text-accent-color"><%=r.getTitle()%></h2>
							<p>
								<b>Product Type: </b><%=RepositoryDAO.getRepositoryByID(r.getReposID()).getRepoType() %></p>
							<p>
								<b>Start Date: </b><%=r.getStartdate() %></p>
							
							<p><b>Closes on: </b><%=r.getClosedate() %></p>
							
							<p><b>Location: </b><%=r.getAddress() %></p>

    						
    							
							<%-- URI rewrite repoID --%>
							<% if(session.getAttribute("usertype")!=null && session.getAttribute("usertype").equals("volunteer")) {%>
							<% if(r.getClosedate().before(new java.sql.Date(new java.util.Date().getTime()))) {%>
							<button class="btn btn-info btn-raised updateRepo" title="Request Closed"  disabled>
								<b>Promise </b><span class="glyphicon glyphicon-eye-open"
									id="symbolview"> </span>
							</button>    						
							<%} %>
							<% if(!r.getClosedate().before(new java.sql.Date(new java.util.Date().getTime()))) {%>
							<a class="btn btn-info btn-raised updateRepo" title="Make a Promise" href="<%=response.encodeURL ("promise.jsp?requestID="+Integer.toString(r.getRequestID()))%>">
								<b>Promise </b><span class="glyphicon glyphicon-eye-open"
									id="symbolview"> </span>
							</a>
							<%} %>
							<% } 
							 else { %>
							<button class="btn btn-info btn-raised updateRepo" title="You need to sign in to promise" data-toggle="modal" data-target="#loginModal">
								<b>Promise </b><span class="glyphicon glyphicon-eye-open"
									id="symbolview"> </span>
							</button>
							<% } %>
							

</div>
<% } %>
							</div>
</div>
<!-- Requests end -->


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
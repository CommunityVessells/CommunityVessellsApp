<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page errorPage="error.jsp" %>
<% if(session.getAttribute("usertype")!=null && session.getAttribute("usertype").equals("organization")) { %>
<!DOCTYPE html>
<html lang="en">
<%@ include file="static_resources/head.html" %>

<body id="comVessells" data-spy="scroll" data-target=".navbar" data-offset="60">
<%@ include file="static_resources/loginModal.html" %>
<%@ include file="navbar_org.jsp" %>
<div class="container-fluid">



		
		<%    if(request.getAttribute("errormessage") != null) {
		%>

			
			<div class="panel panel-danger">
				<div class="panel-heading">
    				<h3 class="panel-title text-center" id="panelTitle">Create new Repository Failed!</h3>
  				</div>
  				<div class="panel-body">
   					<p class="text-center text-danger"><%=(String)request.getAttribute("errormessage") %></p>
  				</div>
			</div>
				
				
			

			
		<%  } %>
<div class="list-group">
  <div class="list-group-item">
    <div class="row-picture">
      <img class="img-thumbnail img-responsive" src="<%=session.getAttribute("avatar") %>" alt="avatar">
    </div>
    <div class="row-content">
      <h4 class="list-group-item-heading anton text-center">Welcome <%=session.getAttribute("name") %></h4>

      
    </div>
  </div>
  <div class="list-group-separator"></div>
 <div class="list-group-item">

    <div class="row-content">
      <h4 class="list-group-item-heading anton"><small>Organization Details</small> </h4>
		<p class="col-sm-offset-1"><b>Name: </b> <%=session.getAttribute("name") %></p>
		<p class="col-sm-offset-1"><b>Email: </b> <%=session.getAttribute("email") %></p>
		<p class="col-sm-offset-1"><b>Description: </b> <%=session.getAttribute("description") %></p>
		<p class="col-sm-offset-1"><b>Type: </b> <%=session.getAttribute("orgtype") %></p>
      
    </div>
  </div>
   <div class="list-group-separator"></div>
   <div class="list-group-item">

    <div class="row-content">
      <h4 class="list-group-item-heading anton"><small><%=session.getAttribute("name") %> Repositories</small> </h4>
  		<div class="list-group-separator"></div>
  		<h4 class="anton"><small>Create New Repository</small></h4><button class="btn btn-default btn-raised light-primary-color" id="createRepo"><span class="glyphicon glyphicon-plus" id="symbol"></span></button>
			
     <div class= "well hide" id="newForm">
   		<%@ include file="static_resources/createRepoForm.html" %>
   	</div>
  <div class="list-group-separator"></div>
   <div class="list-group-item">


    <div class="row-content">
      <h4 class="list-group-item-heading anton"><small><%=session.getAttribute("name") %> Requests</small> </h4>

      
    </div>
  </div>
  </div>
  </div>
  
</div>
</div>
<%@ include file="static_resources/footer.html" %>

<%@ include file="static_resources/scriptIncludes.html" %>

<%@ include file="static_resources/initScript.html" %>

<%@ include file="static_resources/animateScript.html" %>

<%@ include file="static_resources/onclickCreateScript.html" %>
</body>
</html>
<% } %>
<% if(session.getAttribute("usertype")==null){ %>
<jsp:forward page="/"></jsp:forward>
<% } %>
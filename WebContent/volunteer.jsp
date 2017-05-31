<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page errorPage="error.jsp" %>
<% if(session.getAttribute("usertype")!=null && session.getAttribute("usertype").equals("volunteer")) { %>
<!DOCTYPE html>
<html lang="en">
<%@ include file="static_resources/head.html" %>

<body id="comVessells" data-spy="scroll" data-target=".navbar" data-offset="60">
<%@ include file="static_resources/loginModal.html" %>

<%@ include file="navbar_vol.jsp" %>

<div class="container-fluid">

<div class="list-group">
  <div class="list-group-item">
    <div class="row-picture">
      <img class="img-thumbnail img-responsive" src="<%=session.getAttribute("avatar") %>" alt="avatar">
    </div>
    <div class="row-content">
      <h4 class="list-group-item-heading anton text-center">Welcome <%=session.getAttribute("username") %></h4>

      
    </div>
  </div>
  <div class="list-group-separator"></div>
 <div class="list-group-item">

    <div class="row-content">
      <h4 class="list-group-item-heading anton"><small>My Details</small> </h4>
		<p class="col-sm-offset-1"><b>Username: </b> <%=session.getAttribute("username") %></p>
		<p class="col-sm-offset-1"><b>Email: </b> <%=session.getAttribute("email") %></p>
		<p class="col-sm-offset-1"><b>First Name: </b> <%=session.getAttribute("firstname") %></p>
		<p class="col-sm-offset-1"><b>Last Name: </b> <%=session.getAttribute("lastname") %></p>
      
    </div>
  </div>
  <div class="list-group-separator"></div>
   <div class="list-group-item">

    <div class="row-content">
      <h4 class="list-group-item-heading anton"><small>My Requests</small> </h4>

      
    </div>
  </div>
  </div>
  
</div>
<%@ include file="static_resources/footer.html" %>

<%@ include file="static_resources/scriptIncludes.html" %>

<%@ include file="static_resources/initScript.html" %>
<%@ include file="static_resources/scrollspyScript.html" %>
<%@ include file="static_resources/animateScript.html" %>
</body>
</html>
<% } %>
<% if(session.getAttribute("usertype")==null){ %>
<jsp:forward page="/"></jsp:forward>
<% } %>
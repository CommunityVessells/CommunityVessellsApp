<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<%@ include file="static_resources/head.html" %>

<body id="comVessells" data-spy="scroll" data-target=".navbar" data-offset="60">

<%@ include file="static_resources/signupModal.html" %>

<%@ include file="static_resources/loginModal.html" %>

<!-- Navbar start -->
<% if(session.getAttribute("flag")==null) { %>
<%@ include file="static_resources/navbar.html" %>

<% } 
if(session.getAttribute("flag")!=null && session.getAttribute("usertype").equals("volunteer")){ %>
<%@ include file="navbar_vol.jsp" %>
<% } %>


<!-- Navbar end-->

<div class="container-fluid">

		<% if(request.getAttribute("successmessage") != null) { %>
			<div class="panel panel-success">
				<div class="panel-heading">
    				<h3 class="panel-title text-center" id="panelTitle">Success!</h3>
  				</div>
  				<div class="panel-body">
   					<p class="text-center text-success"><%=(String)request.getAttribute("successmessage") %></p>
					<div class="text-center">
					<button class="btn btn-success btn-raised text-center"  type="button" data-toggle="modal" data-target="#loginModal"><b>LOGIN</b></button>
					</div>
  				</div>
			</div>	

			
		<% }
		
		   if(request.getAttribute("errormessage") != null) {
		%>

			
			<div class="panel panel-danger">
				<div class="panel-heading">
    				<h3 class="panel-title text-center" id="panelTitle">Register Error!</h3>
  				</div>
  				<div class="panel-body">
   					<p class="text-center text-danger"><%=(String)request.getAttribute("errormessage") %></p>
					<div class="text-center">
					<button class="btn btn-danger btn-raised text-center"  type="button" data-toggle="modal" data-target="#signUpModal"><b>TRY AGAIN</b></button>
					</div>
  				</div>
			</div>
				
				
			

			
		<%  } 
		
				
		
		    if(request.getAttribute("loginfail") != null) {
		%>

			
			<div class="panel panel-danger">
				<div class="panel-heading">
    				<h3 class="panel-title text-center" id="panelTitle">Login Fail!</h3>
  				</div>
  				<div class="panel-body">
   					<p class="text-center text-danger"><%=(String)request.getAttribute("loginfail") %></p>
					<div class="text-center">
					<button class="btn btn-danger btn-raised text-center"  type="button" data-toggle="modal" data-target="#loginModal"><b>TRY AGAIN</b></button>
					</div>
  				</div>
			</div>
				
				
			

			
		<%  } %>

<!-- Jumbotron -->
  <div class="jumbotron text-center default-primary-color">
		<div class="container-fluid">
			<div class="row main">
		<div class="col-sm-2 col-md-2 pull-left"><a href=""><img class="img-responsive" src="Images/ffcclogo.png" alt="FFCC logo"></a></div>
		<div class="col-sm-10 text-center">
    <h1 id="logo">COMM<span class="text-primary-color">UNITY</span> VES<span class="text-primary-color">SELLS</span></h1>

    <h3 id="logo-small">Creating Volunteer Channels</h3>
		</div>
  </div>
</div>
</div>



<!-- Image Well -->
<div class="container-fluid">
	<div class="row">

		<img class="img-responsive" id="disclaimer" src="Images/Feed_Logo_Color.png" alt="Feed Logo">

	</div>
</div>

<!-- purpose -->
<div class="container-fluid"  id="Purpose">
	<div><i class="fa fa-heartbeat fa-5x pull-right text-accent-color slideanim" aria-hidden="true"></i>
  <h1 id="purpose" class="primary-text-color">Our Mission:</h1>
	<p class="primary-text-color maintext">To create a platform ,for different and diverse organizations, to optimize their logistics for the purpose of gathering and distributing food , pharmaceutical &amp; material aid , allow volunteers to contribute specific amounts of products based on  calculated needs of the organizations and help them achieve their goals on their charity work and humanitarian projects.</p>
	<hr class="divider-color">
	<h1 id="purpose" class="primary-text-color slideanim"><small>How it Works 	<i class="fa fa-cog fa-spin fa-2x fa-fw"></i>
		<span class="sr-only"></span></small></h1>

	<p  class="primary-text-color maintext">The Community Vessells Platform works on a basis of <em>&quot;requests&quot;</em> issued by the participating organizations. Each volunteer can sign-in and declare his/her interest in participating on a specific request.The <strong>contributions system</strong> ,unobtrusively, obliges each volunteer to commit to a number of deliverable items per request as a <em>&quot;promise to keep&quot;.</em></p>
	<p class="primary-text-color maintext">A simple logistics application panel helps each organization to keep track of their current needs and material aid repositories ,issue new requests and estimate <strong>&quot;fullfilment conditions&quot;</strong> per request ,based on the information of <em>&quot;promises&quot;</em> provided by the volunteers and the actual tracking of products stored ,during the collecting process. </p>

</div>
</div>

<!-- join now alert -->
<div class="container main slideanim"  id="Signup">
<button id="lowercse" class="btn btn-raised btn-block text-center accent-color" type="button" data-toggle="modal" data-target="#signUpModal">
	<h2 id="logo-small" class="text-primary-color text-center">Join The Cause</h2></button>
</div>

</div>

<%@ include file="static_resources/footer.html" %>

<%@ include file="static_resources/scriptIncludes.html" %>

<%@ include file="static_resources/initScript.html" %>
<%@ include file="static_resources/scrollspyScript.html" %>
<%@ include file="static_resources/animateScript.html" %>

</body>
</html>
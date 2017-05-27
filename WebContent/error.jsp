<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page errorPage="error.jsp" %>
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html lang="en">
<%@ include file="static_resources/head.html" %>

<body id="comVessells" data-spy="scroll" data-target=".navbar" data-offset="60">
<%@ include file="static_resources/signupModal.html" %>

<%@ include file="static_resources/loginModal.html" %>

<!-- Navbar start -->
<%@ include file="static_resources/navbar_generic.html" %>
<!-- Navbar end-->

<div class="container-fluid">

<div class="jumbotron text-center dark-primary-color">
		<div class="container-fluid">
			<div class="row main">
			
				<h1 class="text-center" id="error">Oops! Something Went Horribly Wrong!</h1>
			
			<div class="row">				
					<img class="img-responsive center-block" style="width:80%;margin-bottom:2em;"  src="Images/fallout.png" alt="Fallout">
			</div>
			</div>
		</div>
</div>

</div>
<%@ include file="static_resources/footer.html" %>

<%@ include file="static_resources/scriptIncludes.html" %>

<%@ include file="static_resources/initScript.html" %>

</body>
</html>
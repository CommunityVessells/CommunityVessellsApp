	<div class="navbar dark-primary-color navbar-fixed-top">
	  <div class="container-fluid">
	    <div class="navbar-header">
	      <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-inverse-collapse">
	        <span class="icon-bar"></span>
	        <span class="icon-bar"></span>
	        <span class="icon-bar"></span>
	      </button>
	      <a class="navbar-brand" href="/CommunityVessellsApp">Home</a>
	    </div>
	    <div class="navbar-collapse collapse navbar-inverse-collapse">
	      <ul class="nav navbar-nav">
	        
	        <li class="hover-ul"><a href="/CommunityVessellsApp#Purpose">Purpose</a></li>
			<li class="hover-ul"><a href="javascript:void(0)">Requests </a></li>					
	      </ul>

	      <ul class="nav navbar-nav navbar-right">
	      	<li class="hover-ul"><a href="organization.jsp"><span><%=session.getAttribute("email") %></span></a></li>
	        <li class="hover-ul"><a href="logout"> <b>Logout</b></a></li>
	      </ul>
	    </div>
	  </div>
	</div>
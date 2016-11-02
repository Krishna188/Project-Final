
<!--Header Begining-->
<div class="row h_yellow">
	<div class="col-sm-4">
		<img src="img/logo.png" alt="Humber Schedule" id="logo">
	</div>
</div>
<div class="row">
	<div class="container-fluid">
		<div class="row">
			<nav class="navbar navbar-default">
				<div class="container-fluid">
					<div class="navbar-header">
						<button type="button" class="navbar-toggle collapsed"
							data-toggle="collapse" data-target="#navbar"
							aria-expanded="false" aria-controls="navbar">
							<span class="sr-only">Toggle navigation</span> <span
								class="icon-bar"></span> <span class="icon-bar"></span> <span
								class="icon-bar"></span>
						</button>
					</div>
					<div id="navbar" class="navbar-collapse collapse">
						<ul class="nav navbar-nav">
							<%
								session.setAttribute("homelocation", session.getAttribute("role").toString().toLowerCase().concat(".jsp"));
							%>
							<li><a href='${homelocation}'>Home - Welcome ${first}</a></li>
							<li><a href="account_settings.jsp">Account Settings</a></li>
						</ul>
						<ul class="nav navbar-nav navbar-right">
							<li><a href="Logout">Logout</a></li>
						</ul>
					</div>
					<!--/.nav-collapse -->
				</div>
				<!--/.container-fluid -->
			</nav>
		</div>
	</div>
</div>
<!--Header end-->
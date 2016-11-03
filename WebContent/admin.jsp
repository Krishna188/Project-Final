<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="includes/pageRedirect.jsp"%>
<%@page import="Classes.Display" %>
<%@page import="Classes.Logic" %>
<%
	if(!session.getAttribute("role").toString().equals("ADMIN"))
	{
		response.sendRedirect(session.getAttribute("role").toString().toLowerCase().concat(".jsp"));
	}

	try {
		session.setAttribute("data2", new Logic().get_teacher_list());
		session.setAttribute("data3", new Logic().get_student_list());
		session.setAttribute("rooms",new Logic().get_rooms());
	
	} catch (Exception ex) {
		session.setAttribute("result", new Display(Display.Type.ERROR).getHtml(ex.getMessage()));
	}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<%@include file="includes/head.html"%>
</head>

<body>
	<% if(session.getAttribute("username") == null ) {
    	response.sendRedirect("index.jsp");    		
    }
   
   %>
	<div class="container-fluid">
		<%@include file="includes/header.jsp"%>

		<div class="row">
			<div class="col-sm-1"></div>
			<div class="col-sm-10">

				<div class="panel-group" id="accordion">
					<div class="panel panel-default">
						<div class="panel-heading">
							<h4 class="panel-title">
								<a data-toggle="collapse" data-parent="#accordion"
									href="#collapse1">Add User</a>
							</h4>
						</div>
						<div id="collapse1" class="panel-collapse collapse in">
							<div class="panel-body"><%@include
									file="includes/add_user.jsp"%></div>
						</div>
					</div>

					<div class="panel panel-default">
						<div class="panel-heading">
							<h4 class="panel-title">
								<a data-toggle="collapse" data-parent="#accordion"
									href="#collapse3">View Teacher</a>
							</h4>
						</div>
						<div id="collapse3" class="panel-collapse collapse out">
							<div class="panel-body">${data2}</div>
						</div>
					</div>
					<div class="panel panel-default">
						<div class="panel-heading">
							<h4 class="panel-title">
								<a data-toggle="collapse" data-parent="#accordion"
									href="#collapse4">View Student</a>
							</h4>
						</div>
						<div id="collapse4" class="panel-collapse collapse out">
							<div class="panel-body">${ data3 }</div>
						</div>
					</div>
					<div class="panel panel-default">
						<div class="panel-heading">
							<h4 class="panel-title">
								<a data-toggle="collapse" data-parent="#accordion"
									href="#collapse5">Add Rooms</a>
							</h4>
						</div>
						<div id="collapse5" class="panel-collapse collapse out">
							<div class="panel-body"><%@include
									file="includes/add_room.jsp"%></div>
						</div>
						
					</div>
					
					<div class="panel panel-default">
						<div class="panel-heading">
							<h4 class="panel-title">
								<a data-toggle="collapse" data-parent="#accordion"
									href="#collapse6">View Rooms</a>
							</h4>
						</div>
						<div id="collapse6" class="panel-collapse collapse out">
							<div class="panel-body">${ rooms }</div>
						</div>
					</div>
					
				</div>
			</div>
			<div class="col-sm-1"></div>
			${result }
			<% session.setAttribute("result", "");%>
		</div>
	</div>
</body>
<%@include file="includes/foot.html"%>
</html>
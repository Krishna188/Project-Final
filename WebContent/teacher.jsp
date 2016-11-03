<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="includes/pageRedirect.jsp"%>
<%@page import="Classes.Logic" %>
<%@page import="Classes.Display" %>
<%
	if(!session.getAttribute("role").toString().equals("TEACHER"))
	{
		response.sendRedirect(session.getAttribute("role").toString().toLowerCase().concat(".jsp"));
	}
	
	try {
		session.setAttribute("scheduled_courses", new Logic().get_student_exams(session.getAttribute("username").toString()));
		
		session.setAttribute("courses", new Logic().get_all_courses(session.getAttribute("username").toString()));
		session.setAttribute("rooms", new Logic().get_all_rooms());
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
	<div class="container-fluid">
		<%@include file="includes/header.jsp"%>
		<div class="row">
			<div class="panel-group" id="accordion">
				<div class="panel panel-default">
					<div class="panel-heading">
						<h4 class="panel-title">
							<a data-toggle="collapse" data-parent="#accordion"
								href="#collapse1"> View Scheduled Exam</a>
						</h4>
					</div>
					<div id="collapse1" class="panel-collapse collapse in">
						<div class="panel-body">
							<!--Input Data here if the data is available i.e. if the exams are scheduled-->
							${scheduled_courses}
						</div>
					</div>
				</div>
				<div class="panel panel-default">
					<div class="panel-heading">
						<h4 class="panel-title">
							<a data-toggle="collapse" data-parent="#accordion"
								href="#collapse2"> Schedule Exam</a>
						</h4>
					</div>
					<div id="collapse2" class="panel-collapse collapse out">
						<div class="panel-body">
							<%@include file="includes/new_exam.jsp"%>
						
						</div>
					</div>
				</div>
			</div>
		</div>

	</div>
		${result}
		<% session.setAttribute("result", ""); %>
</body>
<%@include file="includes/foot.html"%>
</html>
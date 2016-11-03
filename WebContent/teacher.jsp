<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="includes/pageRedirect.jsp"%>
<%@page import="Classes.Logic" %>
<%
	if(!session.getAttribute("role").toString().equals("TEACHER"))
	{
		response.sendRedirect(session.getAttribute("role").toString().toLowerCase().concat(".jsp"));
	}
	session.setAttribute("scheduled_courses", new Logic().get_student_exams(session.getAttribute("username").toString()));
	
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
							<!-- Table -->
							<table class="table table-bordered table-hover table-condensed">
								<tr>
									<th>Course Code</th>
									<th>Room Number</th>
									<th>Date</th>
									<th>Start Time</th>
									<th>End Time</th>
								</tr>
								<!--Input Data here if the data is available i.e. if the exams are scheduled-->
								${scheduled_courses}
							</table>
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
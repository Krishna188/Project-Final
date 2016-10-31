<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
    <head>
        <%@include file="includes/head.html" %>
    </head>
    <body>
        <div class="container-fluid">
              <%@include file="includes/header.jsp" %>
     
            <div class="row">
                <div class="col-sm-12">
                    <div class="page-header">
                        <h1> ${username} <br><small>Timetable for ${username} </small></h1>
                    </div>
                </div>
            </div>

            <div class="row">
                <!-- Student Timetable displayer -->
                        <div class="panel panel-default">
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
                                ${result}
                            </table>
                        </div>
                    <!-- Displayer Above -->
            </div>

        </div>
    </body>  
      <%@include file="includes/foot.html" %>
    
</html>
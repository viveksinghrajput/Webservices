<!DOCTYPE html>
<%@ page isErrorPage = "true" %>
<%@page import="java.util.LinkedHashMap"%>
<%@page import="java.util.Map"%>
<%@page import="com.rural.Model.MenuMaster"%>
<html lang="en">

<head>
<style>
body { padding-bottom: 50px; }


</style>

    <title>Rural Marketing ERP</title>

    <!-- Bootstrap Core CSS -->
    <link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

    <!-- MetisMenu CSS -->
    <link href="vendor/metisMenu/metisMenu.min.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="dist/css/sb-admin-2.css" rel="stylesheet">

    <!-- Morris Charts CSS -->
    <link href="vendor/morrisjs/morris.css" rel="stylesheet">

    <!-- Custom Fonts -->
    <link href="vendor/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
<style>
    .error_message {
  margin-top: 0;
  margin-left: 2000;
  font-size: .70em;
  margin-bottom: 0;
  color: #FF0000;
  text-align: center;
  }
</style>
</head>
<body>

   	<div id="wrapper">

			<div id="page-wrapper">
				<div class="row">
					
                <div class="panel-body">
											<div align="center">
         				<h3><label class="error_message" id="errorMsg">Some Error Occured</label></h3>
         				<% exception.printStackTrace(response.getWriter()); %>
         				</div></div></div></div></div>
         

<!--     
    <footer>
               <div class="container" align="center">
                    <div class="row" style="margin-top: 7px;">
                         <p> &nbsp; &nbsp; &nbsp;  &nbsp; &nbsp; &nbsp; &copy; Copyright Ivan  2016.</p> 
                  </div></div> 
                   
            </footer>  -->
          
             <!-- Footer -->
         
    <!-- jQuery -->
    <script src="vendor/jquery/jquery.min.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="vendor/bootstrap/js/bootstrap.min.js"></script>

    <!-- Metis Menu Plugin JavaScript -->
    <script src="vendor/metisMenu/metisMenu.min.js"></script>

    <!-- Morris Charts JavaScript -->
    <script src="vendor/raphael/raphael.min.js"></script>
    <script src="vendor/morrisjs/morris.min.js"></script>
    <script src="data/morris-data.js"></script>

    <!-- Custom Theme JavaScript -->
    <script src="dist/js/sb-admin-2.js"></script>

</body>

</html>

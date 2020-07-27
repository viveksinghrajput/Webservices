<!DOCTYPE html>
<html lang="en">
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<head>
   
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Static Planning</title>
<jsp:include page="headerStaticPlanning.jsp" />

    <!-- Bootstrap Core CSS -->
    <link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

    <!-- MetisMenu CSS -->
    <link href="vendor/metisMenu/metisMenu.min.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="dist/css/sb-admin-2.css" rel="stylesheet">

    <!-- Custom Fonts -->
    <link href="vendor/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
   <style>
   	a{
   		color:#009900;
   	}
   	a.dropdown-toggle {
    color: #009900;
	}
	.nav>li>a:focus, .nav>li>a:hover {
    color: #009900;
	}
	.a:hover, .nav .open>a:focus {
	background-color: #408c94;
	}
   </style>
</head>	
<body>
	<div class="container">
        <div class="row">
              <form:form name="securityForm" id="securityForm">
		<div id="wrapper">
		<div id="page-wrapper">
		<br>
		
			<div class="text-left text-left border-bottom-underline" style="float: left;border-bottom-width: 13px;" >
				Agency Master
				</div>
				<br>
			
			<!-- Create Agency Form -->
			<div class="row">
				<div class="col-lg-12">
					
						<div class="panel panel-default">
						 <span
												style="float: right;margin-top: 0.44pc;"> <a href="downloadAgencyMaster">  <img src="dist/img/csv-64.png"
												alt="csv" height="30" width="35">
											</a>
											</span>
						<div class="panel-heading" style="padding-top: 13px;">
					<b>Create New Agency</b></div>
					<!-- Status message -->
					<h5 align="center" style="color: #FF0000;" id="status"> <c:out value="${strStatus}"/></h5>
						
						
					<br>
					<br>
				
						<div class="panel-body">
						
              	Welcome to wallpainting!
              	</div></div></div></div></div></div>
                    </form:form>
		
            </div>
    </div>
	<!-- jQuery -->
    <script src="vendor/jquery/jquery.min.js"></script>
	
	<!-- Confirm password js -->
	<!-- <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js"></script> -->
	
    <!-- Bootstrap Core JavaScript -->
    <script src="vendor/bootstrap/js/bootstrap.min.js"></script>

    <!-- Metis Menu Plugin JavaScript -->
    <script src="vendor/metisMenu/metisMenu.min.js"></script>

    <!-- Custom Theme JavaScript -->
    <script src="dist/js/sb-admin-2.js"></script>
    
    <!-- <script src="js/backButton.js"></script>  -->
    
	</body>
	
	
</html>
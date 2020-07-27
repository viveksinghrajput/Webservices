<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Admin DashBoard</title>

<jsp:include page="header.jsp" />


</head>
<body>
	<div id="wrapper">


		<div id="page-wrapper">
			<div class="row">
				<div class="col-lg-12">
					<h1 class="page-header">Admin Home</h1>
				</div>
				<!-- /.col-lg-12 -->
			</div>
			<div class="row">
				<div class="col-lg-12">
					<div class="panel panel-default">
						<div class="panel-body">
							<div class="col-xs-3">
								<div class="col-xs-15">
									<label id="spanError"> &nbsp; &nbsp; &nbsp;</label>
									<div class="form-group">
										<input id="showPlanData" type="button" class="btn btn-primary"
											onclick="showAllUsers()" value="Show All Users">
									</div>
								</div>
							</div>
							<div class="col-xs-3">
								<div class="col-xs-15">
									<label>&nbsp; &nbsp; &nbsp;</label>
									<div class="form-group">
										<input id="createPrePlan" type="button"
											class="btn btn-primary" onclick="submitForm()"
											value="Create New User ">
									</div>
								</div>
							</div>
							<div class="col-xs-3">
								<div class="col-xs-15">
									<label>&nbsp; &nbsp; &nbsp;</label>
									<div class="form-group">
										<input id="createPrePlan" type="button"
											class="btn btn-primary" onclick="submitForm()"
											value="Show InActive Users ">
									</div>
								</div>
							</div>


						</div>
						<!-- /#panel-body -->
					</div>
					<!-- /#panel panel-default -->
				</div>
				<!-- /#col-lg-12 -->
			</div>
			<!-- /#row -->
		</div>
		<!-- /#page-wrapper -->

	</div>
	<!-- /#wrapper -->

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
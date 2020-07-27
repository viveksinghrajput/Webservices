<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@page import="java.io.File"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%><html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>View Request - Finance</title>
<style>    .show-content {
   overflow-wrap:break-word;
}


<style>
.error_message {
	margin-top: 0;
	margin-bottom: 0;
	color: #FF0000;
}

</style>
<jsp:include page="header.jsp" />
<!-- CSS goes in the document HEAD or added to your external stylesheet -->
<%
						
							if(session.getAttribute("mapMenu")==null)
						    {
						        response.sendRedirect("login.jsp");
						    }
%>

<script type="text/javascript">

		
</script>
</head>


<body>
	<form:form name="viewFinForm"
	id="viewFinForm" autocomplete="off" enctype='multipart/form-data' method="post">
	<div id="wrapper">

		<div id="page-wrapper">
			<div class="row">
				<div class="col-lg-12">
					<h3 class="border-bottom-underline">Finance</h3>
					
					
				</div>
			</div>
				<input type="hidden" id="do_list_info" name="do_list_info">
			<div class="panel panel-default">
						
						<div class="panel-heading">

			<a class="glyphicon glyphicon-circle-arrow-left" href="doDetails"
			style="font-size: 24px;color: #337AB5;text-decoration: none;"></a>
												
				<label style="margin-left: 10px; color: #337AB5; vertical-align:bottom;">DO Details</label>	
				 <span style="float: right;margin-top: 0.44pc;"> <a href="exportToCSVForCompletionDoList">  <img src="dist/img/csv-64.png"
												alt="csv" height="30" width="35" style="margin-top: -0.5pc;">
											</a>
											</span>
											</div>	
	<br>
						<div class="row">
					<div class="col-lg-12">
							<div class="panel-body">
<div align="center">
					<label class="error_message">${statusMsg }</label>
				</div>
								<div class="row">
														
						
														<div class="col-xs-2">
															<label>DO Number </label>
															<input class="form-control" id="agencyId" name="agencyId"
																value="${doDetails.doNumber}" type="text"
																readonly>
														</div>
														
														<div class="col-xs-2">
															<label>DO Value</label>
															<input class="form-control text-right change_to_thousand_seprator"value="${doDetails.doValue}" type="text" readonly>
														</div>


														<div class="col-xs-2">
															<label>Utilized Amount </label>
															<input class="form-control text-right change_to_thousand_seprator" value="${doDetails.doValue - doDetails.doBalance}"
																type="text" readonly>
														</div>
																						<!-- Start of Row -->
														<div class="col-xs-2">
															<label>Available Amount </label>
															<input class="form-control text-right change_to_thousand_seprator"  value="${doDetails.doBalance}" type="text" readonly
																maxlength="10">
														</div>
														<div class="col-xs-2">
															<label>Valid From</label> 
															<input class="form-control" value="${doDetails.validFrom}" type="text" readonly>
															</div>		
															<div class="col-xs-2">
															<label>Valid To</label> 
															<input class="form-control" value="${doDetails.validTo}" type="text" readonly>
															</div>		
		</div></div>
				
		
	<table class="table table-striped table-bordered table-hover"
												id="tablefeilds">
												<thead>
													<tr class = "tr_color">
									
							<th class="text-center" >Completion Report</th>
							<th class="text-center" >Agency Name</th>
							<th class="text-center" >Utilized Amount</th>
							</tr>
							</thead>
							<c:forEach var="finance" items="${financeDoList}">
								<tbody>
									<tr>
										<td class="text-center"><c:out value="${finance.reqNum}"></c:out></td>
															<td class="text-center"><c:out value="${finance.agencyName}"></c:out></td>
															<td class="text-right change_to_thousand_seprator"><c:out value="${finance.totalAmount}"></c:out></td>
									</tr>
								</tbody>
							</c:forEach> 
							<tbody>
							</tbody>
												</table>
		
	
							
							 											</div>
											</div>
										</div>
									</div>
							
							</div>
		<!-- /#page-wrapper -->

						
						</form:form>
				
						
</body>
					

	<!-- </div> -->
	
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
	<!-- DataTables JavaScript -->
    <script src="vendor/datatables/js/jquery.dataTables.min.js"></script>
    <script src="vendor/datatables-plugins/dataTables.bootstrap.min.js"></script>
<script src="js/adminMenu.js"></script>

<script type="text/javascript">

$(document).ready(function() {
	
	var change_to_thousand_seprator = $( ".change_to_thousand_seprator" );
	var elem = $();
	for( var i = 0; i < change_to_thousand_seprator.length; i++ ){
		var elem = $( change_to_thousand_seprator[i] );
		if( elem.is( "input" ) ){
			elem.val( parseFloat( parseFloat( elem.val().trim() ).toFixed(2) ).toLocaleString('en-IN' , { minimumFractionDigits: 2 }) );
		} else {
			elem.html( parseFloat( parseFloat( elem.text().trim() ).toFixed(2) ).toLocaleString('en-IN' , { minimumFractionDigits: 2 }) );
		}
	}
	
});


	/* $('#finDoListTable').DataTable({


			scrollCollapse : true,

			paging : true,

		}); */
	</script>
	</html>
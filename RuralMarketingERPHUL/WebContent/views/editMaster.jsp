<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>View/Edit WareHouse</title>
<jsp:include page="header.jsp" />


</head>
<body>

	<div id="wrapper">


		<div id="page-wrapper">
			<div class="row">
				<div class="col-lg-12">
					<h3 class="text-center">Edit <c:out value="${model}"/> Master Data</h3>
				</div>
			</div>
			<!-- /.row -->
			<br>



			<!-- Show My warehouses Here -->

			<h4>
					<c:if test="${not empty strStatus}">
						<c:if test="${strStatus == 'Success'}">
							<div class="alert alert-success alert-dismissable">
							<button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
							${strStatus}
							</div>
						</c:if>
					</c:if>
			</h4>
			<div class="row">
				<div class="col-lg-12">
					<div class="panel panel-default">
						<div class="panel-body">
							<c:if test="${not empty item}">
								
									<div class="row">
										<div class="col-xs-12">
											<div class="form-group">
												
													<c:if test="${model == 'Saturation'}">
														<%-- <c:out value="${model}"/> --%>
														<a class="btn btn-primary btn-circle" href="saturationMas"
													style="width: 30px;" data-toggle="tooltip" data-placement="top" title="Back"><i class="fa fa-arrow-left"></i>
													</a>
														
													<input id="submit" type="button" class="btn btn-primary fa-save"
														onclick="saveSaturation();" value="&nbsp;Save&nbsp;"
														style="width: 100px;" > 
														
													</c:if>
													<c:if test="${model == 'Conversion'}">
														<%-- <c:out value="${model}"/> --%>
														<a class="btn btn-primary btn-circle" href="conversionMas"
													style="width: 30px;" data-toggle="tooltip" data-placement="top" title="Back"><i class="fa fa-arrow-left"></i>
													</a>
													
													<input id="submit" type="button" class="btn btn-primary fa-save"
														onclick="saveConversion();" value="&nbsp;Save&nbsp;"
														style="width: 100px;" > 
														
													</c:if>
												<%--
													 <a class="btn btn-primary btn-circle" href="warehouse"
													style="width: 30px;" data-toggle="tooltip" data-placement="top" title="Back"><i class="fa fa-arrow-left"></i>
													</a>
												 	
													<input id="submit" type="button" class="btn btn-primary fa-save"
														onclick="saveDetails();" value="&nbsp;Save&nbsp;"
														style="width: 100px;" > 
													
													
													<c:if test="${userRole == 'Admin'}">
													
														&nbsp;&nbsp; 
														<a class="btn btn-primary btn-circle " href="javascript:;"
														onclick="editWareHouse();" style="width: 30px;" data-toggle="tooltip" data-placement="top" title="Edit WareHouse Details"><i class="glyphicon glyphicon-edit"></i>
														</a>
														&nbsp;
														
														
														<input id="submit" type="button" class="btn btn-primary fa-save"
														onclick="saveDetails();" value="&nbsp;Save&nbsp;"
														style="width: 100px;" disabled> 
														
														&nbsp;
														<c:if test="${warehouse.status == 'Approved'}">
															<a class="btn btn-success disabled" href="javascript:;"
															onclick="approve();" style="width: 100px;">Approve
															</a>
															&nbsp;
														 	<a class="btn btn-danger disabled" href="javascript:;"
															onclick="reject();" style="width: 100px;">Reject
															</a>
														</c:if> 
														<c:if test="${warehouse.status == 'Submitted'}">
															<a class="btn btn-success" href="javascript:;"
															onclick="approve();" style="width: 100px;">Approve
															</a>
															&nbsp;
														 	<a class="btn btn-danger" href="javascript:;"
															onclick="reject();" style="width: 100px;">Reject
															</a>
														</c:if>
													</c:if>
													
													<c:if test="${userRole == 'Audit'}">
																									
																										
														<c:if test="${warehouse.status == 'Approved'}">
															<a class="btn btn-success disabled" href="javascript:;"
															onclick="approve();" style="width: 100px;">Approve
															</a>
															&nbsp;
															<a class="btn btn-danger disabled" href="javascript:;"
															onclick="reject();" style="width: 100px;">Reject
															</a>
														</c:if> 
														<c:if test="${warehouse.status == 'Submitted'}">
															<a class="btn btn-success" href="javascript:;"
															onclick="approve();" style="width: 100px;">Approve
															</a>
															&nbsp;
														 	<a class="btn btn-danger" href="javascript:;"
															onclick="reject();" style="width: 100px;">Reject
															</a>
														</c:if>
													</c:if> 
													
													--%>
											</div>
										</div>
									</div>
								
								
								<form:form name="itemForm">
									<div class="row">
										<div class="col-lg-12">
											<div class="panel panel-default">
												<div class="panel-body">
													
													<c:if test="${model == 'Saturation'}">
													<input type="hidden" name="id" id="id"
														value="${item.saturation_id}" />
														
													<div class="row">
														<!-- Start of Row -->
														
														<div class="col-xs-3">
															<label>State</label>
														</div>
														<div class="col-xs-3">
															<input class="form-control" id="contact" name="contact"
																value="${item.state}" type="text" disabled maxlength="10">
														</div>

														<div class="col-xs-3">
															<label>City </label>
														</div>
														<div class="col-xs-3">
															<input class="form-control" id="latitude" name="latitude"
																value="${item.city}" type="text"
																readonly>
														</div>
													</div>
													
													<!-- End of Row -->
													<br>
													<!-- Break Space -->
													<div class="row">
														<!-- Start of Row -->
														<div class="col-xs-3">
															<label>Brand </label>
														</div>
														<div class="col-xs-3">
															<input class="form-control" id="brand" name="brand"
																value="${item.brand}" type="text" disabled maxlength="10">
														</div>

														<div class="col-xs-3">
															<label>Saturation </label>
														</div>
														<div class="col-xs-3">
															<input class="form-control" id="satpercent" name="satpercent"
																value="${item.satpercent}" type="text">
										
														</div>
													</div>
													<!-- End of Row -->
													<br>
													<!-- Break Space -->
													</c:if>
													
													
													<c:if test="${model == 'Conversion'}">
													<input type="hidden" name="id" id="id"
														value="${item.conversion_id}" />
														
													<div class="row">
														<!-- Start of Row -->
														
														<div class="col-xs-3">
															<label>Brand</label>
														</div>
														<div class="col-xs-3">
															<input class="form-control" id="brandname" name="brandname"
																value="${item.brandname}" type="text" readonly>
														</div>

														<div class="col-xs-3">
															<label>Conversion</label>
														</div>
														<div class="col-xs-3">
															<input class="form-control" id="conpercent" name="conpercent"
																value="${item.conpercent}" type="text" >
																
														</div>
														<!-- End of Row -->
														<br><br>
														<!-- Break Space -->
														<div class="col-xs-3">
															<label>Average Sales </label>
														</div>
														<div class="col-xs-3">
															<input class="form-control" id="avgsales" name="avgsales"
																value="${item.avgsales}" type="text" >
														</div>
													</div>
													
													
													
													</c:if>
													


													

												</div>
											</div>
										</div>
										
										
									</div>
									<!-- End of Main ROW -->
								</form:form>


							</c:if>
							<c:if test="${empty warehouse}">
								<c:out value="${noWarehouse}" />
							</c:if>
						</div>
					</div>
				</div>

			</div>
			<!-- /.row -->
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

	<!-- Custom Theme JavaScript -->
	<script src="dist/js/sb-admin-2.js"></script>

	<!-- Custom JavaScript For Admin Module-->
	<script src="js/adminMenu.js"></script>


	<script type="text/javascript">
    $(document).ready(function() {
    	
    	document.getElementById("submit").disabled = false;
    	document.getElementById("state").disabled = true;
    	document.getElementById("city").disabled = true;
    	document.getElementById("brand").disabled = true;
    	
	} );
    </script>
	


</body>
</html>
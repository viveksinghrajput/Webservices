<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html lang="en">

<head>
<title>Planning - Post Planning</title>
<jsp:include page="header.jsp" />
<style type="text/css">
.dataTables_wrapper .dataTables_length {
float: left;
}
.dataTables_wrapper .dataTables_filter {
float: right;
text-align: left;
}
</style>
<style type="text/css">
input[type='number'] {
	-moz-appearance: textfield;
}

input::-webkit-outer-spin-button, input::-webkit-inner-spin-button {
	-webkit-appearance: none;
	margin: 0;
}
</style>
<style type="text/css">
.multiSelect {
	display: block;
	width: 100%;
	height: 34px;
	padding: 6px 12px;
	font-size: 14px;
	line-height: 1.42857143;
	color: #555;
	background-color: #fff;
	background-image: none;
	border: 1px solid #ccc;
	border-radius: 4px;
	x
}
</style>

<style type="text/css">
.error_message {
	margin-top: 0;
	margin-left: 2000;
	font-size: .70em;
	margin-bottom: 0;
	color: #FF0000;
	text-align: center;
}
</style>
<link href="vendor/datatables/css/buttons.dataTables.min.css"
	rel="stylesheet">
<link href="vendor/datatables/css/multi.select.css" rel="stylesheet">
<link rel="stylesheet" href="vendor/multiple-select.css" />
<link rel="stylesheet" href="vendor/multi.select.js" />
<link rel="stylesheet" href="vendor/jquery/jquery-1.12.4.min.js" />
</head>
<body>
	<form name="frm1">
		<div id="wrapper">
			<div id="page-wrapper">
				<div class="row">
					<div class="col-lg-12">
						<h3 class="text-center">Planning - Pre Planning</h3>
					</div>
				</div>
				<h4>
					<!-- Pre Planning --> 
					
					&nbsp; &nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp; &nbsp;
					&nbsp;&nbsp; &nbsp; &nbsp;&nbsp; &nbsp; &nbsp;&nbsp; &nbsp;
					&nbsp;&nbsp; &nbsp;&nbsp; &nbsp; &nbsp;&nbsp; &nbsp; &nbsp;&nbsp;
					&nbsp; &nbsp;&nbsp; &nbsp; &nbsp;&nbsp; &nbsp; &nbsp;&nbsp; &nbsp;
					&nbsp;&nbsp; &nbsp; &nbsp; <label class="error_message"
						id="prePlanCreatedMsg">${strStatus}</label>
				</h4>
				<!-- /.row -->
				<div class="row">
					<div class="col-lg-12">
						<div class="panel panel-default">
							<div class="panel-body">
								<form id="createPrePlanForm">

									<div class="row">
										<div class="col-lg-12">
											<div class="col-xs-2">
												<label>Brand&nbsp;*</label>
												<div class="col-xs-14">
													<select id="brand" class="form-control" name="brand"
														onChange="brandSelection()">
														<option disabled selected value="-1">-- select --
														</option>
														<c:forEach var="brand" items="${brandList}">
															<c:choose>
																<c:when
																	test="${not empty selected_brand && selected_brand eq brand.value}">
																	<option value="${selected_brand}" selected>${selected_brand}</option>
																</c:when>
																<c:otherwise>
																	<option value="${brand.value}">${brand.value}</option>
																</c:otherwise>
															</c:choose>
														</c:forEach>
													</select>
												</div>
												<br> <label>State&nbsp;*</label>
												<div class="col-xs-14">
													<select id="state" class="form-control" name="state"
														onChange="stateSelection();">
														<option disabled selected value="-1">-- select --</option>
														<c:forEach var="state" items="${stateList}">
															<c:choose>
																<c:when
																	test="${not empty selected_state && selected_state eq state}">
																	<option value="${selected_state}" selected>${selected_state}</option>
																</c:when>
																<c:otherwise>
																	<option value="${state}">${state}</option>
																</c:otherwise>
															</c:choose>
														</c:forEach>
													</select>
												</div>


											</div>
											<div class="col-xs-2">
												<label>Conversion &#37;</label>
												<c:if test="${empty selected_conversion}">
													<input class="form-control" id="conversion"
														name="conversion" type="text" value="" readonly>
												</c:if>
												<c:if test="${not empty selected_conversion}">
													<input class="form-control" id="conversion"
														name="conversion" type="text"
														value="${selected_conversion }" readonly>
												</c:if>
												<br>
												<label>City&nbsp;*</label>
												<div class="col-xs-14">
													<select id="city" class="form-control" name="city"
														onclick="citySelection()" multiple size="4">

														<c:forEach var="city" items="${listCity}">
															<option value="${city}"
																<c:if test="${  selected_city.contains( city ) }">SELECTED</c:if>><c:out
																	value="${city}" /></option>

														</c:forEach>
													</select>
												</div>


											</div>
											<div class="col-xs-2">
												<label>Avg.Sale/Promoter</label>
												<c:if test="${empty selected_averageSales}">
													<input class="form-control" id="averageSales"
														name="averageSales" type="text" value="" readonly>
												</c:if>
												<c:if test="${not empty selected_averageSales}">
													<input class="form-control" id="averageSales"
														name="averageSales" type="text"
														value="${selected_averageSales }" readonly>
												</c:if>
												<br>
				
												<label>Saturation &#37;</label>
												<c:if test="${empty selected_saturation}">
													<textarea class="form-control" id="saturation" name="saturation"  readonly
														style="height: 81px"></textarea>
													
												</c:if>
												<c:if test="${not empty selected_saturation}">
													<textarea class="form-control" id="saturation" name="saturation" readonly
														style="height: 81px" >${selected_saturation}</textarea>
													
												</c:if>
												

											</div>
											<div class="col-xs-2">
												<label>Span of Activity&nbsp;*</label>
												<div class="form-group">
													<c:if test="${empty selected_span}">
														<input class="form-control" id="span" name="span" value="265"
															type="text" onkeypress='validateNum(event)'>
													</c:if>
													<c:if test="${not empty selected_span}">
														<input class="form-control" id="span" name="span"
															value="${selected_span }" type="text"
															onkeypress='validateNum(event)'>
													</c:if>

												</div>
												<div class="col-xs-15">
													<label id="spanError"> &nbsp; &nbsp; &nbsp;</label>
													<div class="form-group">
														<input id="showPlanData" type="button"
															class="btn btn-primary" onclick="getPrePlanList()"
															value="Create Pre-Plan&nbsp;" style="width: 140px;">
													</div>
												</div>
												<div class="col-xs-15">

													<div class="form-group">
													
													<c:if test="${not empty listPreplanItems}">
														<input id="createPrePlan" type="button"
															class="btn btn-primary" onclick="savePrePlanForm()"
															value="&nbsp;Save Pre-Plan&nbsp;&nbsp;"
															style="width: 140px;">
													</c:if>
													<c:if test="${empty listPreplanItems}">
														<input id="createPrePlan" type="button"
															class="btn btn-primary" onclick="savePrePlanForm()"
															value="&nbsp;Save Pre-Plan&nbsp;&nbsp;"
															style="width: 140px; " disabled>
													</c:if>
													
													</div>
												</div>



											</div>

										</div>
									</div>
								</form>
							</div>
							<!-- End First Panel Body -->
						</div>
						<!-- End First Panel Default -->
					</div>
					<!-- End First Class Body -->
				</div>
				<!-- End First Row Body -->



				<c:if test="${not empty listPreplanItems}">
					<c:set var = "listPreplan" scope = "session" value = "${listPreplanItems}"/>
					<div class="row" id="prePlanTable">
						<div class="col-lg-12">
							<div class="panel panel-default">
								<div class="panel-heading">
									<b>Pre-Plan Details</b>
									<c:if test="${not empty strStatus}">
									<span style="float:right; height='32px';">
									<a href="exportSavedPrePlan"> <img src="dist/img/csv.jpg" alt="csv"
											height="33" width="35"></a>
									</span>
									</c:if>
								</div>
								<div class="panel-body">
									<table width="100%"
										class="table table-striped table-bordered table-hover"
										id="dataTables-example">
										<thead>
											<tr class = "tr_color">
												<th class="text-center">Brand</th>
												<th class="text-center">State</th>
												<th class="text-center">Town</th>
												<th class="text-center">Population</th>
												<th class="text-center">HouseHold</th>
												<th class="text-center">Conversions</th>
												<th class="text-center">Contacts</th>
												<th class="text-center">Avg Sale Per Promorter</th>
												<th class="text-center">Span of Activity</th>
												<th class="text-center">No of Promorters</th>
												<th class="text-center">No of Teams</th>
											</tr>
										</thead>
										<tbody id="preplanContent">
											<%
												int count = 1;
											%>
											<c:forEach var="PlanList" items="${listPreplanItems}">

												<%
													if (count / 2 == 0) {
												%>
												<tr class="even gradeC">

													<td class="text-center"><c:out value="${PlanList.brand}"></c:out></td>
													<td class="text-center"><c:out value="${PlanList.state}"></c:out></td>
													<td class="text-center"><c:out value="${PlanList.city}"></c:out></td>
													<td class="text-right"><c:out value="${PlanList.tot_pop}"></c:out></td>
													<td class="text-right"><c:out value="${PlanList.tot_hh}"></c:out></td>
													<td class="text-right"><c:out value="${PlanList.conversion}"></c:out></td>
													<td class="text-right"><c:out value="${PlanList.contactDone}"></c:out></td>
													<td class="text-right"><c:out value="${PlanList.avgSales}"></c:out></td>
													<td class="text-right"><c:out value="${PlanList.span}"></c:out></td>
													<td class="text-right"><c:out value="${PlanList.promotorNum}"></c:out></td>
													<td class="text-right"><c:out value="${PlanList.teamNum}"></c:out></td>

												</tr>
												<%
													} else {
												%>
												<tr class="odd gradeX">

													<td class="text-center"><c:out value="${PlanList.brand}"></c:out></td>
													<td class="text-center"><c:out value="${PlanList.state}"></c:out></td>
													<td class="text-center"><c:out value="${PlanList.city}"></c:out></td>
													<td class="text-right"><c:out value="${PlanList.tot_pop}"></c:out></td>
													<td class="text-right"><c:out value="${PlanList.tot_hh}"></c:out></td>
													<td class="text-right"><c:out value="${PlanList.conversion}"></c:out></td>
													<td class="text-right"><c:out value="${PlanList.contactDone}"></c:out></td>
													<td class="text-right"><c:out value="${PlanList.avgSales}"></c:out></td>
													<td class="text-right"><c:out value="${PlanList.span}"></c:out></td>
													<td class="text-right"><c:out value="${PlanList.promotorNum}"></c:out></td>
													<td class="text-right"><c:out value="${PlanList.teamNum}"></c:out></td>

												</tr>
												<%
													}
												%>


											</c:forEach>







										</tbody>
									</table>
								</div>
								<!-- End Second Panel Body -->
							</div>
							<!-- End Second Panel Default -->
						</div>
						<!-- End Second Class -->
					</div>
					<!-- End Second Row for Table  -->
				</c:if>


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

		<!-- DataTables JavaScript -->
		<script src="vendor/datatables/js/jquery.dataTables.min.js"></script>
		<script src="vendor/datatables/js/buttons.flash.min.js"></script>
		<script src="vendor/datatables/js/buttons.html5.min.js"></script>
		<script src="vendor/datatables/js/buttons.print.min.js"></script>
		<script src="vendor/datatables/js/dataTables.buttons.min.js"></script>
		<script src="vendor/datatables/js/jszip.min.js"></script>
		<script src="vendor/datatables/js/pdfmake.min.js"></script>
		<script src="vendor/datatables/js/vfs_fonts.js"></script>


		<script src="vendor/datatables-plugins/dataTables.bootstrap.min.js"></script>
		<script src="vendor/datatables-responsive/dataTables.responsive.js"></script>

		<!-- Custom JavaScript For PrePlanning-->
		<script src="js/preplanning.js"></script>
		<!-- Page-Level Demo Scripts - Tables - Use for reference -->
		 <script>
    $(document).ready(function() {
        $('#dataTables-example').DataTable({
            responsive: true,
            });
        /* resetPage(); */
        
    });
    </script>

	</form>

</body>

</html>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>


<html lang="en">

<head>
<title>Planning - Preplanning</title>
<jsp:include page="header.jsp" />
<style type="text/css">
.dataTables_wrapper .dataTables_length {
	float: left;
}

.dataTables_wrapper .dataTables_filter {
	float: right;
	text-align: left;
}

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
				<div class="col-lg-12 border-bottom-underline" style="text-align:left;padding:10px 0px 0px 20px;">
					<h3 class="">Planning - Pre Planning</h3>
				</div>
			</div>
		<!-- 	<div class="panel panel-default">
						<div class="panel-heading"><span style="float:right; height='34px';">
								<a href="exportToCSVForPrePlan"> 
									<img src="dist/img/csv-64.png" alt="csv"
									 height="30" width="38">
								</a> 						
							</span>
								<div class="panel-heading" style="margin-left: -1.5pc;">
<b>Show All Preplans</b></div>		

 -->

<div class="panel panel-default">
						 <span
												style="float: right;margin-top: 0.3pc;"> <a onclick="validateCSV();"> <img src="dist/img/csv-64.png"
												alt="csv" height="30" width="35">
											</a>
											</span>
						<div class="panel-heading" style="padding-top: 11px;">
<b>Show All Preplans</b></div>	
	
			<!-- <h4>Pre Planning</h4> -->
			<!-- /.row -->
			<div class="row">
				<div class="col-lg-12">
					<div class="panel panel-default">
					<%-- 	<h4>
							<label class="error_message" id="prePlanCreatedMsg">${strStatus}</label>
						</h4> --%>
						
						 <c:if test="${not empty strStatus}">
						<h5 align="center" style="color: #FF0000;"> <c:out value="${strStatus}"/></h5>
		</c:if>
						<!--Start Create PrePlan Menus -->
						 
						<form name="frm1">
						<div class="panel-body" style="margin-top: 20px;">
							<div class="col-lg-12" style="margin-left: -30px;">
								<div class="col-xs-3">
									<label>Brand&nbsp;*</label>
									<div class="col-xs-14">
										<select id="brand" class="form-control" name="brand"
											onChange="brandSelection()">
											<option disabled selected value="-1">-- Select --</option>
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
											<option disabled selected value="-1">-- Select --</option>
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
								<div class="col-xs-3">
									<label>Conversion &#37;</label>
									<c:if test="${empty selected_conversion}">
										<input class="form-control" id="conversion" name="conversion"
											type="text" value="" readonly>
									</c:if>
									<c:if test="${not empty selected_conversion}">
										<input class="form-control" id="conversion" name="conversion"
											type="text" value="${selected_conversion }" readonly>
									</c:if>
									<br> <label>City&nbsp;*</label>
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
								<div class="col-xs-3">
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
									<br> <label>Saturation &#37;</label>
									<c:if test="${empty selected_saturation}">
										<textarea class="form-control" id="saturation"
											name="saturation" readonly style="height: 81px;resize: none;"></textarea>

									</c:if>
									<c:if test="${not empty selected_saturation}">
										<textarea class="form-control" id="saturation"
											name="saturation" readonly style="height: 81px">${selected_saturation}</textarea>

									</c:if>


								</div>
								<div class="col-xs-3">
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
													<label> &nbsp; &nbsp; &nbsp;</label>
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
						<!-- End Create PrePlna Menus -->
						<input type="hidden" name="size" id="size" value="${fn:length(showAllprePlan)}"/>
						<div class="row" id="showAllPrePlanTable">
							<div class="col-lg-12">
									<div class="panel-body" style="padding-top: 0px">
										<table width="100%"
											class="table table-striped table-bordered table-hover"
											id="tablefeilds">
											<thead>
												<tr class = "tr_color"> 
													<th class="text-center">Brand</th>

													<th class="text-center">State</th>
													<th class="text-center">Town</th>
													<th class="text-right">POP</th>
													<th class="text-right">HouseHold</th>
													<th class="text-right">Conversions</th>
													<th class="text-right">Contacts</th>
													<th class="text-right">Average Sales Per Promotor</th>
													<th class="text-right">Span of Activity</th>
													<th class="text-right">No of Promotors</th>
													<th class="text-right">No of Teams</th>

												</tr>
											</thead>
											<tbody>
												<%
													int count = 1;
												%>
												<c:forEach var="PlanList" items="${showAllprePlan}">

													<%
															if (count / 2 == 0) {
													%>
													<tr class="even gradeC">

														<td class="text-center"><c:out
																value="${PlanList.brand}"></c:out></td>

														<td class="text-center"><c:out
																value="${PlanList.state}"></c:out></td>
														<td class="text-center"><c:out
																value="${PlanList.citytown}"></c:out></td>
														<td class="text-right"><c:out
																value="${PlanList.tot_pop}"></c:out></td>
														<td class="text-right"><c:out
																value="${PlanList.tot_hh}"></c:out></td>
														<td class="text-right"><c:out
																value="${PlanList.conversion}"></c:out></td>
														<td class="text-right"><c:out
																value="${PlanList.contactDone}"></c:out></td>
														<td class="text-right"><c:out
																value="${PlanList.avgSales}"></c:out></td>
														<td class="text-right"><c:out
																value="${PlanList.spanDays}"></c:out></td>
														<td class="text-right"><c:out
																value="${PlanList.promotorNum}"></c:out></td>
														<td class="text-right"><c:out
																value="${PlanList.teamNum}"></c:out></td>

													</tr>
													<%
														} else {
													%>
													<tr class="odd gradeX">


														<td class="text-center"><c:out
																value="${PlanList.brand}"></c:out></td>

														<td class="text-center"><c:out
																value="${PlanList.getState}"></c:out></td>
														<td class="text-center"><c:out
																value="${PlanList.getCitytown}"></c:out></td>
														<td class="text-right"><c:out
																value="${PlanList.getTot_pop}"></c:out></td>
														<td class="text-right"><c:out
																value="${PlanList.getTot_hh}"></c:out></td>
														<td class="text-right"><c:out
																value="${PlanList.getConversion}"></c:out></td>
														<td class="text-right"><c:out
																value="${PlanList.getContactDone}"></c:out></td>
														<td class="text-right"><c:out
																value="${PlanList.getAvgSales}"></c:out></td>
														<td class="text-right"><c:out
																value="${PlanList.getSpanDays}"></c:out></td>
														<td class="text-right"><c:out
																value="${PlanList.getPromotorNum}"></c:out></td>
														<td class="text-right"><c:out
																value="${PlanList.getTeamNum}"></c:out></td>

													</tr>
													<%
														}
													%>


												</c:forEach>





											</tbody>
										</table>
									</div>
								</div>
							</div>
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
	<!-- DataTables JavaScript -->
	<script src="vendor/datatables/js/jquery.dataTables.min.js"></script>
	<script src="vendor/datatables-plugins/dataTables.bootstrap.min.js"></script>
	<script src="vendor/datatables-responsive/dataTables.responsive.js"></script>
	<!-- Custom JavaScript For PrePlanning-->
	<script src="js/preplanning.js"></script>
	<!-- Page-Level Demo Scripts - Tables - Use for reference -->

</body>
<script type="text/javascript">
                
                function validateCSV(){
                  var size=document.getElementById("size").value;
                  
                  if(size!=0){
                    
                    document.frm1.action = "exportToCSVForPrePlan";
                    document.frm1.method = "GET";
                    document.frm1.submit();   
                    
                  }else{
                    alert("No Content is available for Download... ");
                  }
                }
                </script>
</html>

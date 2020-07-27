<%@page import="com.rural.Model.PrePlanMaster"%>
<%@page import="java.util.*"%>
<%@ page errorPage="error.jsp"%>
<!DOCTYPE html>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<html lang="en">

<head>
<jsp:include page="header.jsp" />
<!-- Bootstrap Core CSS -->
<link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
			 <link href="dist/css/jquery-ui.css" rel="stylesheet">

<title>Rural Marketing Resource Planner</title>
<script type="text/javascript">
function createRoutePlan()
{
	if (document.getElementById("lsm").value == '-1' || document.getElementById("lsm").value == '' || document.getElementById("lsm").value == ' ') {
		alert('Please Select LSM');
		return false;

	}
	if (document.getElementById("target_amount").value == '') {
		alert('Please Enter Target Amount');
		return false;

	}
if ($('input[type="radio"]:checked').val()){
var radioValue=$('input[type="radio"]:checked').val();
	this.document.routePlanFrm.prePlanID.value=radioValue;
	document.routePlanFrm.action = "createRoutePlan";
	document.routePlanFrm.method = "POST";
	document.routePlanFrm.submit();

	}

else{
	alert('Please Select any Preplan list');
	return false;	
}

	

}


function exportToCSV()
{
	document.routePlanFrm.action = "exportToCSV";
	document.routePlanFrm.method = "GET";
	document.routePlanFrm.submit();
}


function validateNum(evt) {
	//alert("Key Pressed");
	  var theEvent = evt || window.event;
	  var key = theEvent.keyCode || theEvent.which;
	  key = String.fromCharCode( key );
	  if (evt.key == "Backspace" || evt.key == "Del") return true;
	  var regex = /[0-9]|\./;
	  if( !regex.test(key) ) {
	    theEvent.returnValue = false;
	    if(theEvent.preventDefault) theEvent.preventDefault();
	  }
	}

     
	</script>

</head>
<body>
	<form:form name="routePlanFrm">
		<div id="wrapper">

			<div id="page-wrapper">
				<div class="row">
					<div class="col-lg-12">
						<h3 class="border-bottom-underline">Planning - Route Planning</h3>
					</div>
					<!-- /.row -->
					<div class="row">
						<div class="col-lg-12">
							<div class="panel panel-default">
								<div class="panel-body">
									 <ul class="nav nav-pills">
								<c:if test="${empty CreateRoutePlanTab && empty GetRoutePlans}">
       <li class="active"><a href="goToCreateRoutePlan">Create New Plan</a></li>
       <li><a href="getRoutePlans">View Route Plans</a></li>
       </c:if>
									 
									 <c:if test="${CreateRoutePlanTab == 'Y'}">
       <li class="active"><a href="goToCreateRoutePlan">Create New Plan</a></li>
       <li><a href="getRoutePlans">View Route Plans</a></li>
       </c:if>
     <c:if test="${GetRoutePlans == 'Y'}">
     <li><a href="goToCreateRoutePlan">Create New Plan</a></li>
    <li class="active"><a href="getRoutePlans">View Route Plans</a></li></c:if>
  </ul>
									<c:if test="${CreateRoutePlanTab == 'Y'}">

											<div class="row">
												<div class="col-lg-12">
													<div class="panel panel-default">
														<div class="panel-body">
															<div class="col-xs-2">
																<label>LSM&nbsp;*</label> <select id="lsm" multiple
																	class="form-control" name="lsm">
																	<option value="-1">-- SELECT --</option>
																	<c:forEach var="lsm" items="${lSMList}">
																		<option value="${lsm.key}">${lsm.value}</option>
																	</c:forEach>
																</select>

															</div>
															<div class="col-xs-2">
																<label>Target Amount&nbsp;*</label>
																<div class="form-group">
																	<c:if test="${empty target_amount}">
																		<input class="form-control" id="target_amount"
																			name="target_amount" type="text"
																			onkeypress='validateNum(event)'>
																	</c:if>
																	<c:if test="${not empty selected_target_amount}">
																		<input class="form-control" id="target_amount"
																			name="target_amount" value="${target_amount }"
																			type="text" onkeypress='validateNum(event)'>
																	</c:if>

																</div>
															</div>
														</div>
													</div>



													<c:if test="${not empty prePlanList}">
														<c:set var="showAllprePlan" scope="session"
															value="${prePlanList}" />
														<div class="row" id="showAllPrePlanTable">
															<div class="col-lg-12">
																<div class="panel panel-default">
																	<div class="panel-heading">
																		<b>Show All Pre-Plans </b> <span style="float: right;">
																			<a href="exportToCSVForPrePlan"> <img
																				src="dist/img/csv-64.png" alt="csv" height="35"
																				width="35"></a>
																	</div>
																	<div class="panel-body">
																		<table width="100%"
																			class="table table-striped table-bordered table-hover"
																			id="dataTables-example">
																			<thead>
																				<tr>
																					<th></th>
																					<th>Brand</th>
																					<th>State</th>
																					<th>Town</th>
																					<th>Population</th>
																					<th>HouseHold</th>
																					<th>Conversions</th>
																					<th>Contacts</th>
																					<th>Avg Sale Per Promorter</th>
																					<th>Span of Activity</th>
																					<th>No of Promorters</th>
																					<th>No of Teams</th>
																					<th>Route Created (Y/N)</th>
																				</tr>
																			</thead>
																			<tbody>
																				<%
																					int count = 1;
																				%>
																				<c:forEach var="PlanList" items="${prePlanList}">

																					<%
																						if (count / 2 == 0) {
																					%>
																					<tr class="even gradeC">

																						<%-- <td><input type="radio" id="radio1"
																	value="${PlanList.prePlan_id}" ></input></td>
																	 --%>
																						<td><input type="radio" name="radio1"
																							id="radio<%=count%>"
																							value="${PlanList.prePlan_id}"></td>

																						<td><c:out value="${PlanList.brand}"></c:out></td>
																						<td><c:out value="${PlanList.state}"></c:out></td>
																						<td><c:out value="${PlanList.citytown}"></c:out></td>
																						<td><c:out value="${PlanList.tot_pop}"></c:out></td>
																						<td><c:out value="${PlanList.tot_hh}"></c:out></td>
																						<td><c:out value="${PlanList.conversion}"></c:out></td>
																						<td><c:out value="${PlanList.contactDone}"></c:out></td>
																						<td><c:out value="${PlanList.avgSales}"></c:out></td>
																						<td><c:out value="${PlanList.spanDays}"></c:out></td>
																						<td><c:out value="${PlanList.promotorNum}"></c:out></td>
																						<td><c:out value="${PlanList.teamNum}"></c:out></td>
																						<td><c:out value="${PlanList.routeCreated}"></c:out></td>
																					</tr>
																					<%
																						} else {
																					%>
																					<tr class="odd gradeX">

																						<%-- <td><input type="radio" id="radio1"
																	value="${PlanList.prePlan_id}" ></input></td>
																	 --%>
																						<td><input type="radio" name="radio1"
																							id="radio<%=count%>"
																							value="${PlanList.prePlan_id}"></td>

																						<td><c:out value="${PlanList.brand}"></c:out></td>
																						<td><c:out value="${PlanList.state}"></c:out></td>
																						<td><c:out value="${PlanList.citytown}"></c:out></td>
																						<td><c:out value="${PlanList.tot_pop}"></c:out></td>
																						<td><c:out value="${PlanList.tot_hh}"></c:out></td>
																						<td><c:out value="${PlanList.conversion}"></c:out></td>
																						<td><c:out value="${PlanList.contactDone}"></c:out></td>
																						<td><c:out value="${PlanList.avgSales}"></c:out></td>
																						<td><c:out value="${PlanList.spanDays}"></c:out></td>
																						<td><c:out value="${PlanList.promotorNum}"></c:out></td>
																						<td><c:out value="${PlanList.teamNum}"></c:out></td>
																						<td><c:out value="${PlanList.routeCreated}"></c:out></td>
																					</tr>
																					<%
																						}
																					%>


																				</c:forEach>





																			</tbody>
																		</table>
																		<input type="hidden" id="prePlanID" name='prePlanID'
																			value='' /> <input type="button"
																			class="btn btn-primary" onclick="createRoutePlan()"
																			value="Create Route Plan"> <br>
																	</div>
																</div>
															</div>
														</div>
													</c:if>
													<c:if test="${not empty routeplanList}">
														<c:set var="listRoutePlan" scope="session"
															value="${routeplanList}" />
														<div class="row">
															<div class="col-lg-12">

																<div class="panel panel-default">
																	<div class="panel-heading">
																		<b>Show All Route Plans </b> <span
																			style="float: right;"> <a href="exportToCSV">
																				<img src="dist/img/csv-64.png" alt="csv" height="35"
																				width="35">
																		</a>
																		</span> <br>
																	</div>
																	<div class="panel-body">
																		<table width="100%"
																			class="table table-striped table-bordered table-hover"
																			id="allRouteCreated">
																			<thead>
																				<tr>
																					<th>City</th>
																					<th>Ward</th>
																					<th>Brand</th>
																					<th>Actual Sales Done</th>
																					<th>Market Potential Sales</th>
																					<th>Market Share</th>
																					<th>Contribution</th>
																					<th>Market Share Hilo</th>
																					<th>Contribution Hilo</th>
																					<th>X_Y</th>
																					<th>Quadrant</th>
																					<th>Total HH</th>

																					<c:if test="${lsm03 eq 'Y'}">
																						<th>LSM 0-3</th>
																					</c:if>
																					<c:if test="${lsm46 eq 'Y'}">
																						<th>LSM 4-6</th>
																					</c:if>
																					<c:if test="${lsm79 eq 'Y'}">
																						<th>LSM 7-9</th>
																					</c:if>
																					<c:if test="${lsm1012 eq 'Y'}">
																						<th>LSM 10-12</th>
																					</c:if>
																					<c:if test="${lsm1315 eq 'Y'}">
																						<th>LSM 13-15</th>
																					</c:if>
																					<c:if test="${lsm1618 eq 'Y'}">
																						<th>LSM 16-18</th>
																					</c:if>

																					<th>TG HH</th>
																					<th>Conversions</th>
																					<th>Sauration</th>
																					<th>Balance TG HH</th>
																					<th>TG HH's @ 30% Saturation</th>
																					<th>Balance TG HH's Potential</th>
																					<th>TG/Total</th>
																				</tr>
																			</thead>
																			<tbody>
																				<%
																					int count = 1;
																				%>
																				<c:forEach var="routePlanList"
																					items="${routeplanList}">

																					<%
																						if (count / 2 == 0) {
																					%>
																					<tr class="even gradeC">


																						<td><c:out value="${routePlanList.city}"></c:out></td>
																						<td><c:out value="${routePlanList.area}"></c:out></td>
																						<td><c:out value="${routePlanList.brand}"></c:out></td>
																						<td align="right"><c:out
																								value="${routePlanList.actual_Sales_Value}"></c:out></td>
																						<td align="right"><c:out
																								value="${routePlanList.market_Potential_Sales}"></c:out></td>
																						<td align="right"><c:out
																								value="${routePlanList.market_Share}"></c:out></td>
																						<td align="right"><c:out
																								value="${routePlanList.contribution}"></c:out></td>
																						<td align="right"><c:out
																								value="${routePlanList.market_Share_HiLo}"></c:out></td>
																						<td align="right"><c:out
																								value="${routePlanList.contribution_HiLo}"></c:out></td>
																						<td align="right"><c:out
																								value="${routePlanList.x_Y}"></c:out></td>
																						<td align="right"><c:out
																								value="${routePlanList.quadrant}"></c:out></td>
																						<td align="right"><c:out
																								value="${routePlanList.total_HH}"></c:out></td>
																						<c:if test="${lsm03 eq 'Y'}">
																							<td align="right"><c:out
																									value="${routePlanList.lSM_0_3}"></c:out></td>
																						</c:if>
																						<c:if test="${lsm46 eq 'Y'}">
																							<td align="right"><c:out
																									value="${routePlanList.lSM_4_6}"></c:out></td>
																						</c:if>
																						<c:if test="${lsm79 eq 'Y'}">
																							<td align="right"><c:out
																									value="${routePlanList.lSM_7_9}"></c:out></td>
																						</c:if>
																						<c:if test="${lsm1012 eq 'Y'}">
																							<td align="right"><c:out
																									value="${routePlanList.lSM_10_12}"></c:out></td>
																						</c:if>
																						<c:if test="${lsm1315 eq 'Y'}">
																							<td align="right"><c:out
																									value="${routePlanList.lSM_13_15}"></c:out></td>
																						</c:if>
																						<c:if test="${lsm1618 eq 'Y'}">
																							<td align="right"><c:out
																									value="${routePlanList.lSM_16_18}"></c:out></td>
																						</c:if>
																						<td align="right"><c:out
																								value="${routePlanList.tG_HH}"></c:out></td>
																						<td align="right"><c:out
																								value="${routePlanList.conversions}"></c:out></td>
																						<td align="right"><c:out
																								value="${routePlanList.saturation}"></c:out></td>
																						<td align="right"><c:out
																								value="${routePlanList.balance_TG_HH}"></c:out></td>
																						<td align="right"><c:out
																								value="${routePlanList.tG_HH_30_percent}"></c:out></td>
																						<td align="right"><c:out
																								value="${routePlanList.balance_TG_HH_Potential}"></c:out></td>
																						<td align="right"><c:out
																								value="${routePlanList.tG_HH_Potental}"></c:out></td>
																					</tr>
																					<%
																						} else {
																					%>
																					<tr class="odd gradeX">
																						<td><c:out value="${routePlanList.city}"></c:out></td>
																						<td><c:out value="${routePlanList.area}"></c:out></td>
																						<td><c:out value="${routePlanList.brand}"></c:out></td>
																						<td align="right"><c:out
																								value="${routePlanList.actual_Sales_Value}"></c:out></td>
																						<td align="right"><c:out
																								value="${routePlanList.market_Potential_Sales}"></c:out></td>
																						<td align="right"><c:out
																								value="${routePlanList.market_Share}"></c:out></td>
																						<td align="right"><c:out
																								value="${routePlanList.contribution}"></c:out></td>
																						<td align="right"><c:out
																								value="${routePlanList.market_Share_HiLo}"></c:out></td>
																						<td align="right"><c:out
																								value="${routePlanList.contribution_HiLo}"></c:out></td>
																						<td align="right"><c:out
																								value="${routePlanList.x_Y}"></c:out></td>
																						<td align="right"><c:out
																								value="${routePlanList.quadrant}"></c:out></td>
																						<td align="right"><c:out
																								value="${routePlanList.total_HH}"></c:out></td>
																						<c:if test="${lsm03 eq 'Y'}">
																							<td align="right"><c:out
																									value="${routePlanList.lSM_0_3}"></c:out></td>
																						</c:if>
																						<c:if test="${lsm46 eq 'Y'}">
																							<td align="right"><c:out
																									value="${routePlanList.lSM_4_6}"></c:out></td>
																						</c:if>
																						<c:if test="${lsm79 eq 'Y'}">
																							<td align="right"><c:out
																									value="${routePlanList.lSM_7_9}"></c:out></td>
																						</c:if>
																						<c:if test="${lsm1012 eq 'Y'}">
																							<td align="right"><c:out
																									value="${routePlanList.lSM_10_12}"></c:out></td>
																						</c:if>
																						<c:if test="${lsm1315 eq 'Y'}">
																							<td align="right"><c:out
																									value="${routePlanList.lSM_13_15}"></c:out></td>
																						</c:if>
																						<c:if test="${lsm1618 eq 'Y'}">
																							<td align="right"><c:out
																									value="${routePlanList.lSM_16_18}"></c:out></td>
																						</c:if>
																						<td align="right"><c:out
																								value="${routePlanList.tG_HH}"></c:out></td>
																						<td align="right"><c:out
																								value="${routePlanList.conversions}"></c:out></td>
																						<td align="right"><c:out
																								value="${routePlanList.saturation}"></c:out></td>
																						<td align="right"><c:out
																								value="${routePlanList.balance_TG_HH}"></c:out></td>
																						<td align="right"><c:out
																								value="${routePlanList.tG_HH_30_percent}"></c:out></td>
																						<td align="right"><c:out
																								value="${routePlanList.balance_TG_HH_Potential}"></c:out></td>
																						<td align="right"><c:out
																								value="${routePlanList.tG_HH_Potental}"></c:out></td>
																					</tr>
																					<%  
                                    } %>


																				</c:forEach>





																			</tbody>
																		</table>

																	</div>
																</div>
															</div>
														</div>
													</c:if>
												</div>
											</div></c:if>
														<c:if test="${GetRoutePlans == 'Y'}">
															<div class="col-lg-12">
						<h3 class="text-center">Plannfsdfaing - Route Planning</h3>
					</div>
														</c:if>
										</div>
								</div>
							</div>
						</div>
					</div>


				</div>
			</div>
		</div>

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
			<script src="dist/js/jquery-ui.js"></script>

		<!-- DataTables JavaScript -->
		<script src="vendor/datatables/js/jquery.dataTables.min.js"></script>
		<script src="vendor/datatables-plugins/dataTables.bootstrap.min.js"></script>
		<script src="vendor/datatables-responsive/dataTables.responsive.js"></script>



	</form:form>
</body>

</html>

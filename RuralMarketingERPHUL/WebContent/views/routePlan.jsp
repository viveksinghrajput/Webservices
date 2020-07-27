<%@page import="com.rural.Model.PrePlanMaster"%>
<%@page import="java.util.*"%>
<%@ page errorPage="error.jsp"%>
<!DOCTYPE html>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html lang="en">

<head>
<title>Rural Marketing ERP</title>
<jsp:include page="header.jsp" />
<!-- common css styles class -->
<link href="dist/css/common.css" rel="stylesheet">
<link rel="stylesheet" href="dist/css/jquery-ui.css">

<style>
.table {
	overflow: scroll;
}

#lsm {
	width: 150px;
}

#lsm option {
	width: 150px;
}

#brand {
	width: 150px;
}

#brand option {
	width: 150px;
}
#target_amount  {
	width: 150px;
}
#tab_content_area_cliked_panel .panel-body, #tab_content_area_cliked_panel .ui-tabs-panel {
	padding-top: 0;
}

</style>






<%
						
							if(session.getAttribute("mapMenu")==null)
						    {
						        response.sendRedirect("login.jsp");
						    }
%>
</head>
<body>
	<form:form name="frm1">

		<div id="wrapper">

			<div id="page-wrapper">
			 <input type="hidden" name="size" id="size" value="${fn:length(prePlanList)}"/> 
				<div class="row">
					<div class="col-lg-12">
						<h3 class="border-bottom-underline">Planning -Route Planning</h3>
					</div>
				</div>
				<div class="row">
					<div class="col-lg-12">
						<div id="tab_content_area_cliked_panel" class="panel panel-default">
								<!-- Start Creating Tabs -->
								<div id="tabs">
									<ul id="tab_content_area_cliked">
										<li><a href="#tabs-1">Create Route Plan</a></li>
										<li><a href="#tabs-2">View Existing Route Plans</a></li>
									</ul>
									<input type="hidden" value="#tabs-1" name="current_tab" id="current_tab">
									<div id="tabs-1">
										<p>
										<div class="panel-body" style="margin-left: -1.8pc;">
										<h5 align="center" style="color: #FF0000;"> <c:out value="${statusMsg}"/></h5>
											<div class="col-xs-2">
												<label>LSM&nbsp;*</label>
												<select id="lsm" multiple
										class="form-control" name="lsm">
										<option value="-1">-- SELECT --</option>
										<c:forEach var="lsm" items="${lSMList}">
											<option value="${lsm.key}">${lsm.value}</option>
										</c:forEach>
												</select>
											</div>
											<div class="col-xs-2">
												<label>Target Contacts&nbsp;*</label>
												<div class="form-group">
													<%-- <c:if test="${empty selected_target_amount}"> --%>
														<input class="form-control" id="target_amount" name="target_amount" type="text"
															onkeypress='validateNum(event)'>
												<%-- 	</c:if>
													<c:if test="${not empty selected_target_amount}">
														<input class="form-control" id="target_amount"
															name="target_amount" value="${selected_target_amount }"
															type="text" onkeypress='validateNum(event)'>
													</c:if> --%>
												</div>
											</div>
											<div class="col-xs-2">
		
											<label>Consideration Set</label> 
												<select id="consideration" onChange="loaddropdown()" class="form-control" name="consideration" style="width:135px;color:#000000;padding-left: 0px;">
												<option disabled selected value="-1">-- Select --</option>
													<option  id="Yes" value="yes">Yes</option>
  													 <option  id="No"  value="no">No</option>
											</select>
												
											</div>
											<div class="col-xs-2" id="quadrant1" style="display: none;margin-left: -0.8pc;">
											<label>Select Quadrant</label>
										<select id=quadrant multiple
										class="form-control" name="quadrant">
										<c:forEach var="quadrant" items="${quadrantList}">
											<option value="${quadrant.value}">${quadrant.value}</option>
										</c:forEach>
										</select>
											
											</div>

														</div>	
																	<c:if test="${not empty prePlanList}">
														<c:set var="showAllprePlan" scope="session"
															value="${prePlanList}" />
														<div class="row" id="showAllPrePlanTable">
															<div class="col-lg-12">
																<!-- <div class="panel panel-default">
																	<div class="panel-heading">
																		<b>Show All Pre-Plans </b> 
																				<span style="float: right;"> <a
														href="exportToCSVForPrePlan"> <img src="dist/img/csv-64.png"
														alt="csv" height="30" width="35">
														</a>
														</span><br>
																	</div>
															 -->		
															 
															 <div class="panel panel-default">
						 <span
												style="float: right;margin-top: 0.44pc;">  <a onclick="validateCSV();"> <img src="dist/img/csv-64.png"
												alt="csv" height="30" width="35">
											</a>
											</span>
						<div class="panel-heading" style="padding-top: 13px;">
								<b>Show All Pre-Plans</b></div>
					<br>
																	<div class="panel-body">
																		<table 
																			class="table table-striped table-bordered table-hover"
																			id="tablefeilds">
																			<thead>
																				<tr class = "tr_color">
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
																	
																	</div>
																	
																</div>
																<br>
																	<input type="hidden" id="prePlanID" name='prePlanID'
																			value='' /> <input type="button"
																			class="btn btn-primary" onclick="createRoutePlan()"
																			value="Create Route Plan"> <br>
															</div>
														</div><br>
													</c:if>
														<c:if test="${not empty routeplanList}">
														<c:set var="listRoutePlan" scope="session"
															value="${routeplanList}" />
															<div class="col-lg-12">

																<div class="panel panel-default">
																
					<div class="panel panel-default">
						 <span
												style="float: right;margin-top: 0.44pc;"> <a href="exportToCSV?name=SavedRoutePlans">  <img src="dist/img/csv-64.png"
												alt="csv" height="30" width="35">
											</a>
											</span>
						<div class="panel-heading" style="padding-top: 13px;">
								<b>Generated Route Plans</b></div>
					<br>
																	<div class="panel-body">
																		<table style="background-color:#FFFFF0" 
																			class="table table-striped table-bordered table-hover"
																			id="allRouteCreated">
																			<thead>
																				<tr class = "tr_color">
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
																				
																				<c:forEach var="routePlanList"
																					items="${routeplanList}">

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
																					

																				</c:forEach>





																			</tbody>
																		</table>

																	</div>
																</div>
															</div>
														</div>
													</c:if>
												
													<c:if test="${not empty targetWardList}">
														<br>
															<div class="col-lg-12">
<br>
																<div class="panel panel-default">
																	
																	
																	<div class="panel panel-default">
						 <span
												style="float: right;margin-top: 0.44pc;"> <a href="exportToCSV?name=TargetWards">  <img src="dist/img/csv-64.png"
												alt="csv" height="30" width="35">
											</a>
											</span>
						<div class="panel-heading" style="padding-top: 13px;">
								<b>Show Target Wards</b></div>
					<br>
																	<div class="panel-body">
																		<table style="background-color:#FFE4E1"
																			class="table table-striped table-bordered table-hover"
																			id="targetWards" name="tablefeilds">
																			<thead>
																				<tr class = "tr_color">
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
																				
																				<c:forEach var="routePlanList"
																					items="${targetWardList}">

																					
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
																				</c:forEach>





																			</tbody>
																		</table>

																	</div>
																</div>
															</div>
														</div>
													</c:if>
										</p>
										
									</div>
									<!-- End of TAB 1 -->
									<div id="tabs-2">
										<p>
										<div class="panel-body">
						
				<h5 align="center" style="color: #FF0000;"> <c:out value="${statusMsgForView}"/></h5>
										<div class="panel-body">
														<div class="col-xs-2">
											<label>Brand&nbsp;*</label>
											<select id="brand" class="form-control" name="brand" onChange="getCities()">
												<option value="-1">-- SELECT --</option>
												<c:forEach var="brand" items="${brandList}">
													<c:choose>
														<c:when
															test="${not empty selected_brand && selected_brand eq brand}">
															<option value="${selected_brand}" selected>${selected_brand}</option>
														</c:when>
														<c:otherwise>
															<option value="${brand}">${brand}</option>
														</c:otherwise>
													</c:choose>
												</c:forEach>
											</select>
											</div>
												<div class="col-xs-2">
									<label>City&nbsp;*</label> <select id="city" class="form-control"
										name="city">
										<option value="-1">-- SELECT --</option>
										<c:forEach var="city" items="${cityList}">
											<c:choose>
												<c:when
													test="${not empty selected_city && selected_city eq city}">
													<option value="${city}" selected>${city}</option>
												</c:when>
												<c:otherwise>
													<option value="${city}">${city}</option>
												</c:otherwise>
											</c:choose>
										</c:forEach>
									</select>

								</div>
								
														<div class="col-xs-2">
																<label>LSM&nbsp;*</label> <select id="viewLsm" multiple
										class="form-control" name="viewLsm">
										<option value="-1">-- SELECT --</option>
										<c:forEach var="lsm" items="${lSMList}">
											<%-- <c:choose>
												<c:when
													test="${not empty selected_lsm && selected_lsm eq lsm.key}">
													<option value="${lsm.key}" selected>${lsm.value}</option>
												</c:when>
												<c:otherwise> --%>
											<option value="${lsm.key}">${lsm.value}</option>
											<%-- </c:otherwise>
											</c:choose> --%>
										</c:forEach>
									</select>


															</div>
															<label>&nbsp;</label>
															<div class="col-xs-6">
									<input type="button" class="btn btn-primary"
										onclick="getRoutePlanList()" value="View Route Plans">
									</div>
									
<c:if test="${not empty showrRoutePlanList}">
				<br><br>	<div class="row">
						<div class="col-lg-12">
							<div class="panel panel-default">
								<div class="panel-heading">
									<b>Show All Route Plans </b> 
<span style="float: right;"> <a
href="exportToCSV?name=ViewRoutePlans">  <img src="dist/img/csv-64.png"
alt="csv" height="30" width="35">
</a>
</span>
								</div>
								<div class="panel-body">
									<table class="table table-striped table-bordered table-hover"
										id="viewRoutePlan" name="tablefeilds">
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
											<%int count=1; %>
											<c:forEach var="routePlanList" items="${showrRoutePlanList}">

												<%
			                                  
			                                    if(count/2==0){ %>
												<tr class="even gradeC">

														<td><c:out value="${routePlanList.city}"></c:out></td>
															<td><c:out value="${routePlanList.area}"></c:out></td>
															<td><c:out value="${routePlanList.brand}"></c:out></td>
															<td align="right"><c:out value="${routePlanList.actual_Sales_Value}"></c:out></td>
															<td align="right"><c:out value="${routePlanList.market_Potential_Sales}"></c:out></td>
															<td align="right"><c:out value="${routePlanList.market_Share}"></c:out></td>
															<td align="right"><c:out value="${routePlanList.contribution}"></c:out></td>
															<td align="right"><c:out value="${routePlanList.market_Share_HiLo}"></c:out></td>
															<td align="right"><c:out value="${routePlanList.contribution_HiLo}"></c:out></td>
															<td align="right"><c:out value="${routePlanList.x_Y}"></c:out></td>
															<td align="right"><c:out value="${routePlanList.quadrant}"></c:out></td>
															<td align="right"><c:out value="${routePlanList.total_HH}"></c:out></td>
															<c:if test="${lsm03 eq 'Y'}">
																<td align="right"><c:out value="${routePlanList.lSM_0_3}"></c:out></td>
															</c:if>
															<c:if test="${lsm46 eq 'Y'}">
																<td align="right"><c:out value="${routePlanList.lSM_4_6}"></c:out></td>
															</c:if>
															<c:if test="${lsm79 eq 'Y'}">
																<td align="right"><c:out value="${routePlanList.lSM_7_9}"></c:out></td>
															</c:if>
															<c:if test="${lsm1012 eq 'Y'}">
																<td align="right"><c:out value="${routePlanList.lSM_10_12}"></c:out></td>
															</c:if>
															<c:if test="${lsm1315 eq 'Y'}">
																<td align="right"><c:out value="${routePlanList.lSM_13_15}"></c:out></td>
															</c:if>
															<c:if test="${lsm1618 eq 'Y'}">
																<td align="right"><c:out value="${routePlanList.lSM_16_18}"></c:out></td>
															</c:if>
															<td align="right"><c:out value="${routePlanList.tG_HH}"></c:out></td>
															<td align="right"><c:out value="${routePlanList.conversions}"></c:out></td>
															<td align="right"><c:out value="${routePlanList.saturation}"></c:out></td>
															<td align="right"><c:out value="${routePlanList.balance_TG_HH}"></c:out></td>
															<td align="right"><c:out value="${routePlanList.tG_HH_30_percent}"></c:out></td>
															<td align="right"><c:out value="${routePlanList.balance_TG_HH_Potential}"></c:out></td>
															<td align="right"><c:out value="${routePlanList.tG_HH_Potental}"></c:out></td>
															</tr>
												<%  }else{ %>
												<tr class="odd gradeX">

															<td><c:out value="${routePlanList.city}"></c:out></td>
															<td><c:out value="${routePlanList.area}"></c:out></td>
															<td><c:out value="${routePlanList.brand}"></c:out></td>
															<td align="right"><c:out value="${routePlanList.actual_Sales_Value}"></c:out></td>
															<td align="right"><c:out value="${routePlanList.market_Potential_Sales}"></c:out></td>
															<td align="right"><c:out value="${routePlanList.market_Share}"></c:out></td>
															<td align="right"><c:out value="${routePlanList.contribution}"></c:out></td>
															<td align="right"><c:out value="${routePlanList.market_Share_HiLo}"></c:out></td>
															<td align="right"><c:out value="${routePlanList.contribution_HiLo}"></c:out></td>
															<td align="right"><c:out value="${routePlanList.x_Y}"></c:out></td>
															<td align="right"><c:out value="${routePlanList.quadrant}"></c:out></td>
															<td align="right"><c:out value="${routePlanList.total_HH}"></c:out></td>
															<c:if test="${lsm03 eq 'Y'}">
																<td align="right"><c:out value="${routePlanList.lSM_0_3}"></c:out></td>
															</c:if>
															<c:if test="${lsm46 eq 'Y'}">
																<td align="right"><c:out value="${routePlanList.lSM_4_6}"></c:out></td>
															</c:if>
															<c:if test="${lsm79 eq 'Y'}">
																<td align="right"><c:out value="${routePlanList.lSM_7_9}"></c:out></td>
															</c:if>
															<c:if test="${lsm1012 eq 'Y'}">
																<td align="right"><c:out value="${routePlanList.lSM_10_12}"></c:out></td>
															</c:if>
															<c:if test="${lsm1315 eq 'Y'}">
																<td align="right"><c:out value="${routePlanList.lSM_13_15}"></c:out></td>
															</c:if>
															<c:if test="${lsm1618 eq 'Y'}">
																<td align="right"><c:out value="${routePlanList.lSM_16_18}"></c:out></td>
															</c:if>
															<td align="right"><c:out value="${routePlanList.tG_HH}"></c:out></td>
															<td align="right"><c:out value="${routePlanList.conversions}"></c:out></td>
															<td align="right"><c:out value="${routePlanList.saturation}"></c:out></td>
															<td align="right"><c:out value="${routePlanList.balance_TG_HH}"></c:out></td>
															<td align="right"><c:out value="${routePlanList.tG_HH_30_percent}"></c:out></td>
															<td align="right"><c:out value="${routePlanList.balance_TG_HH_Potential}"></c:out></td>
															<td align="right"><c:out value="${routePlanList.tG_HH_Potental}"></c:out></td>
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
										</p>
								</div>
								<!-- End Creating Tabs -->

							</div>
							<!-- End of Panel Body -->
						</div>
						<!-- End of Panel Default -->
					</div>
					<!-- End of Col-lg-12 -->
				</div>
				<!-- End of Row -->

				<!-- /.row -->

			</div>
			<!-- /#page-wrapper -->

		</div>
		<!-- /#wrapper -->
	<!-- jQuery -->
		<script src="vendor/jquery/jquery.min.js"></script>
		<script src="vendor/jquery/jquery-ui.js"></script>

		<!-- Bootstrap Core JavaScript -->
		<script src="vendor/bootstrap/js/bootstrap.min.js"></script>

		<!-- Metis Menu Plugin JavaScript -->
		<script src="vendor/metisMenu/metisMenu.min.js"></script>

		<!-- Morris Charts JavaScript -->
		<!--<script src="vendor/raphael/raphael.min.js"></script>
		<script src="vendor/morrisjs/morris.min.js"></script>
		<script src="data/morris-data.js"></script> -->

		<!-- Custom Theme JavaScript -->
		<script src="dist/js/sb-admin-2.js"></script>

		<!-- DataTables JavaScript -->
		<script src="vendor/datatables/js/jquery.dataTables.min.js"></script>
		<script src="vendor/datatables-plugins/dataTables.bootstrap.min.js"></script>
		<script src="vendor/datatables-responsive/dataTables.responsive.js"></script>

		<script>
$(document).ready(function() {
	$( ".sidebar-nav a[href=routePlan]" ).addClass( "active" );
	$( ".sidebar-nav a[href=routePlan]" ).closest( "ul" ).addClass( "in" );
	
	
 $('#allRouteCreated').DataTable({
"scrollX": true,
"paging" : true,
"ordering" : false

}); 
$('#targetWards').DataTable({
"scrollX": true,
"paging" : true,
"ordering" : false,
});

});
$('#viewRoutePlan').DataTable({
	"scrollX": true,
	"paging" : true,
	"ordering" : false	

});
</script>
		
	<script type="text/javascript">
		$(function() {
			$("#tabs").tabs();
		});
		$( document ).ready( function(){
			$(document).on( "click", "#tab_content_area_cliked li", function(){
				$( "#current_tab" ).val( $( this ).find( "a" ).attr( "href" ) );
			});
			
			$("#tab_content_area_cliked").find( "a[href='<%= request.getParameter("current_tab") %>']" ).trigger("click");
		} );
	</script>
	<script type="text/javascript">
		$(function() {
			$("#brand").change(function() {
				var selectedText = $(this).find("option:selected").text();
				var selectedValue = $(this).val();
			});
		});
		function getCities() {
			$.ajax({
				contentType : "application/json",
				type : "POST",
				url : "loadCitiesForRoutePlan?brand=" + $('#brand').val(),
				success : function(data) {
					var cities = new Array();
					cities = data;
					var select = document.getElementById("city");
					$('#city option[value!="-1"]').remove();
					for (var i = 0; i < cities.length; i++) {
						select.options[select.options.length] = new Option(
								cities[i], cities[i]);
					}
				}
			});
		}
		function getPrePlanList()

		{
			if (document.getElementById("brand").value == '-1'
					&& document.getElementById("city").value == '-1') {
				alert('Please Select Brand or City.');
				return false;

			}

			document.frm1.action = "getPreplanlist";
			document.frm1.method = "POST";
			document.frm1.submit();

		}

		function createRoutePlan() {
			
			if (document.getElementById("lsm").value == '-1'
					|| document.getElementById("lsm").value == ''
					|| document.getElementById("lsm").value == ' ') {
				alert('Please Select LSM');
				return false;

			}
			if (document.getElementById("target_amount").value == '') {
				alert('Please Enter Target Amount');
				return false;

			}
			if(document.getElementById("consideration").value == '-1'){
				alert('Please Select Consideration Set');
				return false;
			}
			if(document.getElementById("consideration").value == 'no' && document.getElementById("quadrant").value==''){
				alert('Please Select Quadrant Set');
				return false;
			}
			if ($('input[type="radio"]:checked').val()) {
				var radioValue = $('input[type="radio"]:checked').val();
				this.document.frm1.prePlanID.value = radioValue;
			 	document.frm1.action = "createRoutePlan";
				document.frm1.method = "POST";
				document.frm1.submit();
 
			}

			else {
				alert('Please Select any Preplan list');
				return false;
			}

		}
		function getRoutePlanList() {
			if (document.getElementById("brand").value == '-1') {
				alert('Please Select Brand.');
				return false;

			}
			if (document.getElementById("city").value == '-1') {
				alert('Please Select City.');
				return false;

			}
			if (document.getElementById("viewLsm").value == '-1'
					|| document.getElementById("viewLsm").value == ''
					|| document.getElementById("viewLsm").value == ' ') {
				alert('Please Select LSM.');
				return false;

			}

			document.frm1.action = "Routeplanlist";
			document.frm1.method = "POST";
			document.frm1.submit();
		}

		function exportToCSV() {
			document.frm1.action = "exportToCSV";
			document.frm1.method = "GET";
			document.frm1.submit();
		}
		function validateNum(evt) {
			
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
	 <script type="text/javascript">
		function loaddropdown() {
		 		if($('#consideration').val()=='yes') {
				 	    document.getElementById("quadrant1").style.display = "none";
				 	}	
				else{
				 	    document.getElementById("quadrant1").style.display = "block";
				}
		} 
	 </script>
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
	</form:form>


</body>

</html>

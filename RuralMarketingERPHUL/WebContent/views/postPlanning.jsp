<%@page import="java.text.DecimalFormat"%>
<%@page import="com.rural.Model.SalesMaster"%>
<%@page import="com.rural.Model.PrePlanMaster"%>
<%@page import="java.util.*"%>
<!DOCTYPE html>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page errorPage="error.jsp"%>

<html lang="en">

<head>
<jsp:include page="header.jsp" />
<link rel="stylesheet" href="dist/css/jquery-ui.css">
<style>
.select {
	overflow: scroll;
	border: 1px solid #00425B;
	border: 1px solid #84A2BD;
	width: 180px;
}

.tableContainer {
	color: #0076BF;
	margin: -10px 0px -10px 0px;
	border-spacing: 10px;
	empty-cells: show;
	width: 90%;
	text-align: left;
}

.tableContainer tr td {
	white-space: nowrap;
	text-align: left;
}

#container {
	height: 400px;
	max-width: 800px;
	margin: 0 auto;
}

/* Link the series colors to axis colors */
.highcharts-color-0 {
	fill: #7cb5ec;
	stroke: #7cb5ec;
}

.highcharts-axis.highcharts-color-0 .highcharts-axis-line {
	stroke: #7cb5ec;
}

.highcharts-axis.highcharts-color-0 text {
	fill: #7cb5ec;
}

.highcharts-color-1 {
	fill: #90ed7d;
	stroke: #90ed7d;
}

.highcharts-axis.highcharts-color-1 .highcharts-axis-line {
	stroke: #90ed7d;
}

.highcharts-axis.highcharts-color-1 text {
	fill: #90ed7d;
}

.highcharts-yaxis .highcharts-axis-line {
	stroke-width: 2px;
}

.selected{
	box-shadow:0px 1px 1px 1px blue;
	zoom: 1.4;
}
</style>

<script>
	function loadCalendar() {
		$.ajax({
			contentType : "application/json",
			type : "POST",
			url : "loadCalendar?frequency=" + $('#frequency').val(),
			success : function(data) {
				var calendar = new Array();
				calendar = data;
				var select = document.getElementById("fromQtr");
				$('#fromQtr option[value!="-1"]').remove();
				for (var i = 0; i < calendar.length; i++) {
					select.options[select.options.length] = new Option(
							calendar[i], calendar[i]);
				}
				var select = document.getElementById("toQtr");
				$('#toQtr option[value!="-1"]').remove();
				for (var i = 0; i < calendar.length; i++) {
					select.options[select.options.length] = new Option(
							calendar[i], calendar[i]);
				}
			}
		});
	}
	function generateReports()

	{
		var from = document.getElementById("fromQtr").value;
		var to = document.getElementById("toQtr").value;
		var frequency = document.getElementById("frequency").value;

		var monthFrom = from.substr(0, 2);
		var monthTo = to.substr(0, 2);
		var quarterFrom = from.substr(1, 1);
		var quarterTo = to.substr(1, 1);
		var yearFrom = from.substr(3);
		var yearTo = to.substr(3);

		if (frequency == 'Monthly') {

			if (yearFrom > yearTo) {
				alert('From is greater than To.');
				return false;

			}
			if (monthFrom > monthTo) {
				if (yearFrom > yearTo || yearFrom == yearTo) {
					alert('From is greater than To.');
					return false;
				}

			}
		}
		if (frequency == 'Quarterly') {
			if (quarterFrom > quarterTo || yearFrom > yearTo) {
				alert('From is greater than To.');
				return false;

			}
		}
		if (frequency == 'Yearly') {
			if (from > to) {
				alert('From is greater than To.');
				return false;

			}
		}

		if (document.getElementById("city").value == '-1') {
			alert('Please Select City.');
			return false;

		}
		if (document.getElementById("frequency").value == '-1') {
			alert('Please Select Frequency.');
			return false;

		}
		if (document.getElementById("fromQtr").value == '-1') {
			alert('Please Select From.');
			return false;

		}
		if (document.getElementById("toQtr").value == '-1') {
			alert('Please Select To.');
			return false;

		}

		$("#wait").css("display", "block");

		var div = document.createElement("div");
		div.className += "overlay";
		document.body.appendChild(div);

		document.frm1.action = "generateReport";
		document.frm1.method = "POST";
		document.frm1.submit();

	}
	function disableScreen() {
		alert('disableScreen');
		// creates <div class="overlay"></div> and 
		// adds it to the DOM
		var div = document.createElement("div");
		div.className += "overlay";
		document.body.appendChild(div);
	}

	function exportToCSV() {
		document.frm1.action = "exportToCSVForPostPlanning";
		document.frm1.method = "GET";
		document.frm1.submit();
	}
</script>

<title>Rural Marketing ERP</title>

<style>
.highcharts-xaxis-labels {
	display: none;
}

.error_message {
	margin-top: 0;
	margin-bottom: 0;
	color: #FF0000;
}

.overlay {
	background-color: #EFEFEF;
	position: fixed;
	width: 100%;
	height: 100%;
	z-index: 1000;
	top: 0px;
	left: 0px;
	opacity: .5; /* in FireFox */
	filter: alpha(opacity = 50); /* in IE */
}
</style>
<link rel="stylesheet" href="dist/css/jquery-ui.css">
<link rel="stylesheet"
	href="dist/css/font-awesome.min.css">
</head>
<body>
	<form:form name="frm1" modelAttribute="Secondary_Sales_Master">

		<div id="wrapper">

			<div id="page-wrapper">
				<div class="row">
					<div class="col-lg-12">
						<h3 class="text-left" style="color: #483D8B">Planning - Post
							planning</h3>
					</div>
				</div>
				<div class="panel panel-default" style="height: 138px;">

					<div class="panel-heading" style="padding-top: 13px;">
						<b>Post Planning</b>
					</div>
					<div align="center">
						<label class="error_message" id="prePlanCreatedMsg">${statusMsg }</label>
					</div>
					<div align="center">
						<div id="wait"
							style="display: none; width: 69px; height: 89px; position: absolute; top: 50%; left: 50%; padding: 2px;">
							<img src='dist/img/preload.gif' width="64" height="64" />
						</div>
					</div>

					<div class="col-lg-12">

						<div class="panel-body">
							<div class="col-xs-2">
								<strong>City *</strong> <select id="city" class="form-control"
									name="city">
									<option value="-1">-- SELECT --</option>
									<c:forEach var="city" items="${listCity}">
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
								<strong>Frequency *</strong> <select id="frequency"
									class="form-control" name="frequency" onChange="loadCalendar()">
									<option value="-1">-- SELECT --</option>
									<c:forEach var="frequency" items="${mapFrequency}">
										<c:choose>
											<c:when
												test="${not empty selected_frequency && selected_frequency eq frequency.value}">
												<option value="${frequency.value}" selected>${frequency.value}</option>
											</c:when>
											<c:otherwise>
												<option value="${frequency.value}">${frequency.value}</option>
											</c:otherwise>
										</c:choose>
									</c:forEach>
								</select>

							</div>

							<div class="col-xs-2">
								<strong>From *</strong> <select id="fromQtr"
									class="form-control" name="fromQtr">
									<option value="-1">-- SELECT --</option>
									<c:forEach var="fromQtr" items="${listCalendar}">
										<c:choose>
											<c:when
												test="${not empty selected_fromqtr && selected_fromqtr eq fromQtr}">
												<option value="${fromQtr}" selected>${fromQtr}</option>
											</c:when>
											<c:otherwise>
												<option value="${fromQtr}">${fromQtr}</option>
											</c:otherwise>
										</c:choose>
									</c:forEach>
								</select>

							</div>
							<div class="col-xs-2">
								<strong>To *</strong> <select id="toQtr" class="form-control"
									name="toQtr">
									<option value="-1">-- SELECT --</option>
									<c:forEach var="toQtr" items="${listCalendar}">
										<c:choose>
											<c:when
												test="${not empty selected_toqtr && selected_toqtr eq toQtr}">
												<option value="${toQtr}" selected>${toQtr}</option>
											</c:when>
											<c:otherwise>
												<option value="${toQtr}">${toQtr}</option>
											</c:otherwise>
										</c:choose>
									</c:forEach>
								</select>

							</div>
							<br>
							<div align="center">
								<input type="button" class="btn btn-primary" id="btn1"
									onclick="generateReports()" value="Generate">
							</div>
							<br>
						</div>
					</div>
				</div>

				<c:if test="${not empty salesMasterList}">
					<div class="row" id="showAllPrePlanTable">
						<div class="col-lg-12">
							<div class="panel panel-default">
								<div class="panel-heading">
									<b>Show Post-Plans </b> <span style="float: right;"> <a
										href="exportToCSVForPostPlanning"> <img
											src="dist/img/csv-64.png" alt="csv" height="30" width="35">
									</a>
									</span><br>

								</div>
								<div class="panel-body" style="margin-top: 20px;">
									<table class="table table-striped table-bordered table-hover"
										id="tablefeilds" style="margin-bottom: 0px;">
										<thead>
											<tr class = "tr_color">
												<th>City</th>
												<th>Ward</th>
												<th>Total HH</th>
												<th>HH Nos</th>
												<th>Saturation %</th>
												<%-- <c:if
															test="${selected_frequency == 'Monthly' || selected_frequency == 'Quarterly'}">

															<c:forEach var="calendar" items="${listSelectedCalendar}">
																<th><c:out value="${calendar}"></c:out></th>
															</c:forEach>
														</c:if> --%>
												<th>Done/Not Done</th>
												<th>Slab of saturation</th>
												<c:if test="${not empty _YTD1}">
													<th><c:out value="${_YTD1}"></c:out></th>
												</c:if>
												<c:if test="${not empty _YTD2}">
													<th><c:out value="${_YTD2}"></c:out></th>
												</c:if>
											</tr>
										</thead>
										<tbody>
											<c:forEach var="salesMaster" items="${salesMasterList}">
												<c:set var="ward" value="${salesMaster.ward }"
													scope="session" />


												<tr class="even gradeC">


													<td><c:out value="${salesMaster.city}"></c:out></td>
													<td><c:out value="${salesMaster.ward}"></c:out></td>
													<td align="right"><c:out
															value="${salesMaster.total_HH}"></c:out></td>
													<td align="right"><c:out value="${salesMaster.noHH}"></c:out></td>
													<td align="right"><c:out
															value="${salesMaster.penetration}"></c:out></td>
													<%-- <c:if test="${selected_frequency == 'Monthly'}">


																<c:forEach items="${salesMaster.salesMap}" var="map">
																	<td align="right"><c:out value="${map.value}"></c:out></td>


																</c:forEach>
															</c:if>
															<c:if test="${selected_frequency == 'Quarterly'}">
															
<%Map<String,Map<String,Double>>  map=(Map<String,Map<String,Double>>) request.getAttribute("finalMap"); 
Map<String,Double> tempMap=new LinkedHashMap<String, Double>();
	Map<String,Double> m=map.get(session.getAttribute("ward"));

for(String quarter:m.keySet()){%>
		<td align="right"><c:out value="<%=+m.get(quarter) %>"></c:out></td>
	<% 
	}
	%>
	</c:if>
	 --%>
													<td align="right"><c:out value="${salesMaster.done}"></c:out></td>
													<td align="right"><c:out
															value="${salesMaster.slab_saturation}"></c:out></td>
													<c:forEach items="${salesMaster.totSalesMap}" var="map">
														<td class="change_to_thousand_seprator" align="right"><c:out
																value="${map.value}"></c:out></td>
													</c:forEach>
												</tr>
											</c:forEach>
										</tbody>
									</table>

									<br>
								</div>


								<div class="row ">
									<div class="col-xs-6">
										<div class="panel panel-default"
											style="margin-left: 20px;">
											<div class="panel-heading">
												<b>Summary 1</b>
												&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
												<label class="highlight" id="Abc"><img src="dist/img/tableicon.png"
												 style="height: 20px; width: 24px;"></label>
													&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
												<label class="highlight"><img src="dist/img/charticon.png"
													 style="height: 20px; width: 24px;" class="graphimage"></label>
													<div id="container" style="margin-top: 20px; display: none;height:450px; "></div>
											</div>
											<div class="panel-body" id="displaytable"
												style="margin-top: 20px;">
												
												<table
													class="table table-striped table-bordered table-hover"
													id="nonFixedSample">
													<thead>
														<tr class = "tr_color">
															<th>Done/Not Done</th>
															<c:if test="${not empty _YTD1}">
																<th><c:out value="${_YTD1}"></c:out></th>
															</c:if>
															<c:if test="${not empty _YTD2}">
																<th><c:out value="${_YTD2}"></c:out></th>
																<th>Growth %</th>

															</c:if>
														</tr>

														<%
															String firstYear = (String) request.getAttribute("_YTD1");
																	String secondYear = (String) request.getAttribute("_YTD2");
																	Map<String, Boolean> mapSummaryBoolean = (Map<String, Boolean>) session
																			.getAttribute("mapSummaryBoolean");
																	Map<String, String> mapSummary = (Map<String, String>) session.getAttribute("mapSummary");

																	DecimalFormat decimalFormat = new DecimalFormat("#.##");
																	decimalFormat.setMaximumFractionDigits(2);
																	if (mapSummaryBoolean.get("isDoneFor0") == true) {
														%>
														<tr class="even gradeC">
															<td align="center"><c:out value="<%=0%>"></c:out></td>
															<td class="change_to_thousand_seprator" align="right"><c:out
																	value="<%=mapSummary.get(\"donefor0First\")%>"></c:out></td>
															<%
																if (secondYear != null) {
															%>
															<td class="change_to_thousand_seprator" align="right"><c:out
																	value="<%=mapSummary.get(\"donefor0Second\")%>"></c:out></td>
															<td class="change_to_thousand_seprator" align="right"><c:out
																	value="<%=decimalFormat.format(((Double.parseDouble(mapSummary.get(\"donefor0Second\"))
										/ Double.parseDouble(mapSummary.get(\"donefor0First\"))) - 1) * 100)%>"></c:out>%</td>
															<%
																}
															%>
														</tr>
														<%
															}
																	if (mapSummaryBoolean.get("isDoneFor1") == true) {
														%>
														<tr class="odd gradeX">
															<td align="center"><c:out value="<%=1%>"></c:out></td>
															<td class="change_to_thousand_seprator" align="right"><c:out
																	value="<%=mapSummary.get(\"donefor1First\")%>"></c:out></td>
															<%
																if (secondYear != null) {
															%>
															<td class="change_to_thousand_seprator" align="right"><c:out
																	value="<%=decimalFormat.format(request.getAttribute(\"d1\"))%>"></c:out></td>
															<td class="change_to_thousand_seprator" align="right"><c:out
																	value="<%=decimalFormat.format(((Double.parseDouble(mapSummary.get(\"d1\"))
										/ Double.parseDouble(mapSummary.get(\"donefor1First\"))) - 1) * 100)%>"></c:out>%</td>

															<%
																}
															%>
														</tr>
														<%
															}
														%>




													</thead>
												</table>

											</div>
										</div>

									</div>
									<div class="col-xs-6">
										<div class="panel panel-default" style="width: 482px;">
											<div class="panel-heading">
												<b>Summary 2 </b>
												&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
												<label class="highlight" id="Abcd" onclick="return runshow();">
												 <img src="dist/img/tableicon.png"
													style="height: 20px; width: 24px;"></label>
													&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
													<label class="highlight"><img src="dist/img/charticon.png"
													 onclick="return runMyFunction();"
													style="height: 20px; width: 24px;" class="graphimage1"></label>
												<div id="container1"
													style="display: none; margin-top: 20px;"></div>
												<span style="float: right;"> <a
													href="exportToCSVForSummary"> <img id="CSVImage"
														src="dist/img/csv-64.png" alt="csv" height="30" width="35">
												</a>
												</span><br>
											</div>
											<div class="panel-body" id="displaytable1"style="margin-top: 20px;">
												<table
													class="table table-striped table-bordered table-hover">
													<thead>
														<tr class = "tr_color">
															<th>Done / Not Done</th>
															<th>Slab of Saturation</th>
															<c:if test="${not empty _YTD1}">
																<th><c:out value="${_YTD1}"></c:out></th>
															</c:if>
															<c:if test="${not empty _YTD2}">
																<th><c:out value="${_YTD2}"></c:out></th>
																<th>Growth %</th>

															</c:if>
														</tr>
														<%
															if (mapSummaryBoolean.get("isDoneFor0") == true) {
														%>
														<tr>
															<td align="center"><c:out value="0"></c:out></td>
															<td align="center"><c:out value="<1%"></c:out></td>

															<td class="change_to_thousand_seprator" align="right"><c:out
																	value="<%=mapSummary.get(\"donefor0First\")%>"></c:out></td>

															<%
																if (secondYear != null) {
															%>
															<td class="change_to_thousand_seprator" align="right"><c:out
																	value="<%=mapSummary.get(\"saturationfor1Second\")%>"></c:out></td>
															<td class="change_to_thousand_seprator" align="right"><c:out
																	value="<%=decimalFormat
										.format(((Double.parseDouble(mapSummary.get(\"saturationfor1Second\"))
												/ Double.parseDouble(mapSummary.get(\"saturationfor1First\"))) - 1)
												* 100)%>"></c:out>%</td>
															<%
																}
															%>
														</tr>
														<%
															}
																	if (mapSummaryBoolean.get("isDoneFor1") == true) {
														%>
														<tr>
															<td align="center"><c:out value="1"></c:out></td>
															<%
																if (mapSummaryBoolean.get("issaturationfor5") == true) {
															%>
															<td align="center"><c:out value="1-5%"></c:out></td>
															<td class="change_to_thousand_seprator" align="right"><c:out
																	value="<%=mapSummary.get(\"saturationfor5First\")%>"></c:out></td>
															<%
																if (secondYear != null) {
															%>
															<td class="change_to_thousand_seprator" align="right"><c:out
																	value="<%=mapSummary.get(\"saturationfor5Second\")%>"></c:out></td>
															<td class="change_to_thousand_seprator" align="right"><c:out
																	value="<%=decimalFormat
											.format(((Double.parseDouble(mapSummary.get(\"saturationfor5Second\"))
													/ Double.parseDouble(mapSummary.get(\"saturationfor5First\")) - 1))
													* 100)%>"></c:out>%</td>
															<%
																}
																			}
															%>
														</tr>


														<tr>
															<td align="center"><c:out value="1"></c:out></td>
															<%
																if (mapSummaryBoolean.get("issaturationfor10") == true) {
															%>
															<td align="center"><c:out value="5-10%"></c:out></td>
															<td class="change_to_thousand_seprator" align="right"><c:out
																	value="<%=mapSummary.get(\"saturationfor10First\")%>"></c:out></td>
															<%
																if (secondYear != null) {
															%>
															<td class="change_to_thousand_seprator" align="right"><c:out
																	value="<%=mapSummary.get(\"saturationfor10Second\")%>"></c:out></td>
															<td class="change_to_thousand_seprator" align="right"><c:out
																	value="<%=decimalFormat
											.format(((Double.parseDouble(mapSummary.get(\"saturationfor10Second\"))
													/ Double.parseDouble(mapSummary.get(\"saturationfor10First\"))) - 1)
													* 100)%>"></c:out>%</td>
															<%
																}
																			}
															%>
														</tr>

														<tr>
															<td align="center"><c:out value="1"></c:out></td>
															<%
																if (mapSummaryBoolean.get("issaturationfor15") == true) {
															%>
															<td align="center"><c:out value="10-15%"></c:out></td>
															<td class="change_to_thousand_seprator" align="right"><c:out
																	value="<%=mapSummary.get(\"saturationfor15First\")%>"></c:out></td>
															<%
																if (secondYear != null) {
															%>
															<td class="change_to_thousand_seprator" align="right"><c:out
																	value="<%=mapSummary.get(\"saturationfor15Second\")%>"></c:out></td>
															<td class="change_to_thousand_seprator" align="right"><c:out
																	value="<%=decimalFormat
											.format(((Double.parseDouble(mapSummary.get(\"saturationfor15Second\"))
													/ Double.parseDouble(mapSummary.get(\"saturationfor15First\"))) - 1)
													* 100)%>"></c:out>%</td>
															<%
																}
																			}
															%>
														</tr>

														<tr>
															<td align="center"><c:out value="1"></c:out></td>
															<%
																if (mapSummaryBoolean.get("issaturationfor20") == true) {
															%>
															<td align="center"><c:out value=">15%"></c:out></td>
															<td class="change_to_thousand_seprator" align="right"><c:out
																	value="<%=mapSummary.get(\"saturationfor20First\")%>"></c:out></td>
															<%
																if (secondYear != null) {
															%>
															<td class="change_to_thousand_seprator" align="right"><c:out
																	value="<%=mapSummary.get(\"saturationfor20Second\")%>"></c:out></td>
															<td class="change_to_thousand_seprator" align="right"><c:out
																	value="<%=decimalFormat
											.format(((Double.parseDouble(mapSummary.get(\"saturationfor20Second\"))
													/ Double.parseDouble(mapSummary.get(\"saturationfor20First\"))) - 1)
													* 100)%>"></c:out>%</td>
															<%
																}
																			}
																		}
															%>
														</tr>
													</thead>
												</table>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
					<!-- End of Summary 1 & 2  -->
				</c:if>
				<!-- /#page-wrapper -->
			</div>
		</div>
		<!-- /#wrapper -->

		<!-- jQuery -->
		<script src="dist/js/jquery.min.js"></script>
		<script src="vendor/jquery/jquery-ui.js"></script>
		<script src="vendor/jquery/jquery-1.12.4.js"></script>
		<!-- Common.css -->
		<link href="dist/css/common.css" rel="stylesheet">

		<link href="dist/css/highcharts.css" rel="stylesheet">
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

		<!-- Graph -->
		<script src="js/highcharts.js"></script>
		<script src="js/exporting.js"></script>
		<script src="vendor/jquery/jquery-ui.js"></script>
		
		<!-- Page-Level Demo Scripts - Tables - Use for reference -->

		<script type="text/javascript">
			$(document)
					.ready(
							function() {
								$( ".sidebar-nav a[href=postPlan]" ).addClass( "active" );
								$( ".sidebar-nav a[href=postPlan]" ).closest( "ul" ).addClass( "in" );
								

								var change_to_thousand_seprator = $(".change_to_thousand_seprator");
								var elem = $();
								for (var i = 0; i < change_to_thousand_seprator.length; i++) {
									var elem = $(change_to_thousand_seprator[i]);
									if (elem.is("input")) {
										elem.val(parseFloat(
												parseFloat(elem.val().trim())
														.toFixed(2))
												.toLocaleString('en-IN', {
													minimumFractionDigits : 2
												}));
									} else {
										elem.html(parseFloat(
												parseFloat(elem.text().trim())
														.toFixed(2))
												.toLocaleString('en-IN', {
													minimumFractionDigits : 2
												}));
									}
								}

							});

			$(document).ready(function() {
				$(document).ajaxStart(function() {
					$("#wait").css("display", "block");
				});
				$(document).ajaxComplete(function() {
					$("#wait").css("display", "none");
				});

			});

			$('#dataTables-example').DataTable({
				"ordering" : false,
				scrollX : "1000px",

				scrollX : true,

				scrollCollapse : true,

				paging : true,

			});
		</script>
		<script type="text/javascript">
			$(document).ready(function() {
				$('#Abc').on('click', function() {
					$('#container').hide();
					$( "#nonFixedSample" ).show();
				});
				$('#Abc_chart').on('click', function() {
					$('#container').show();
					$( "#nonFixedSample" ).hide();
				});
				
			});
			$(document).ready(function() {
				$('#Abcd').on('click', function() {
					$('#container1').hide();
					$('#displaytable1').show();
				});
				
			});

			function runMyFunction() {
				$('#CSVImage').hide();

			}
			function runshow() {
				$('#CSVImage').show();

			}
			$('.highlight').click(function(){
				$('.selected').removeClass('selected');
				$(this).addClass('selected');
				});
		</script>
		<script type="text/javascript">
			//console.log();

			var jsonString = '${mapSummaryJson}';
			var jsonString1 = '${mapSummaryBooleanJson}';
			var jsonObj = {};
			if (jsonString != undefined && jsonString.length > 0) {
				jsonObj = JSON.parse(jsonString);
				//console.log(jsonObj);
			}
			if (jsonString1 != undefined && jsonString1.length > 0) {
				var tempObj = JSON.parse(jsonString1);
				if (tempObj != undefined) {
					$.each(tempObj, function(k, v) {
						jsonObj[k] = v;
					});
				}
			}
			console.log(jsonObj);

			var arr = [];
			arr.push(jsonObj['donefor0First']);
			arr.push(jsonObj['donefor0Second']);
			arr.push(jsonObj['donefor1First']);
			arr.push(jsonObj['donefor1Second']);
			arr.push(jsonObj['saturationfor1First']);
			arr.push(jsonObj['saturationfor1Second']);
			arr.push(jsonObj['saturationfor5First']);
			arr.push(jsonObj['saturationfor5Second']);
			arr.push(jsonObj['saturationfor10First']);
			arr.push(jsonObj['saturationfor10Second']);
			arr.push(jsonObj['saturationfor15First']);
			arr.push(jsonObj['saturationfor15Second']);
			arr.push(jsonObj['saturationfor20First']);
			arr.push(jsonObj['saturationfor20Second']);
			arr.push(jsonObj['isDoneFor1']);
			arr.push(jsonObj['isDoneFor0']);
			arr.push(jsonObj['issaturationfor5']);
			arr.push(jsonObj['issaturationfor10']);
			arr.push(jsonObj['issaturationfor15']);
			arr.push(jsonObj['issaturationfor20']);
			arr.push(jsonObj['d1']);

			var growth_one = (parseFloat(jsonObj["donefor0Second"])
					/ parseFloat(jsonObj["donefor0First"]) - 1) * 100, growth_two = (parseFloat(jsonObj["d1"])
					/ parseFloat(jsonObj["donefor1First"]) - 1) * 100;
			console.log(growth_one + "--" + growth_two);
			console.log(jsonObj['d1']);
			$(document).ready(function() {
				$('.graphimage').on('click', function() {
					$('#container').show();
					Highcharts.chart('container', {

						chart : {
							type : 'column'
						},

						title : {
							text : 'Summary 1 chart'
						},

						yAxis : [ {
							className : 'highcharts-color-0',
							title : {
								text : 'Growth'
							}
						}, {
							className : 'highcharts-color-1',
							opposite : true,
							title : {
								text : ''
							}
						} ],

						xAxis : [ {
							className : 'highcharts-color-1',
							title : {
								text : 'Years'
							}
						}, ],

						plotOptions : {
							column : {
								borderRadius : 5
							}
						},

						series : [

						{
							name : "2016",
							data : [ growth_one ]
						}, {
							name : "2017",
							data : [ growth_two ]
						} ]

					});
				});
			});

			/* Second Graph values */
			var saturation_one = (parseFloat(jsonObj["saturationfor1Second"])
					/ parseFloat(jsonObj["saturationfor1First"]) - 1) * 100, saturation_second = (parseFloat(jsonObj["saturationfor5Second"])
					/ parseFloat(jsonObj["saturationfor5First"]) - 1) * 100;
			saturation_three = (parseFloat(jsonObj["saturationfor10Second"])
					/ parseFloat(jsonObj["saturationfor10First"]) - 1) * 100;
			saturation_four = (parseFloat(jsonObj["saturationfor15Second"])
					/ parseFloat(jsonObj["saturationfor15First"]) - 1) * 100;
			saturation_five = (parseFloat(jsonObj["saturationfor20Second"])
					/ parseFloat(jsonObj["saturationfor20First"]) - 1) * 100;
			$(document).ready(function() {
				$('.graphimage1').on('click', function() {
					$('#container1').show();
					Highcharts.chart('container1', {

						chart : {
							type : 'column'
						},

						title : {
							text : 'Summary 2 chart'
						},

						yAxis : [ {
							className : 'highcharts-color-0',
							title : {
								text : 'Growth'
							}
						}, {
							className : 'highcharts-color-1',
							opposite : true,
							title : {
								text : ''
							}
						} ],

						xAxis : [ {
							className : 'highcharts-color-1',
							title : {
								text : 'Slab of Saturation'
							}
						}, ],

						plotOptions : {
							column : {
								borderRadius : 5
							}
						},

						series : [

						{
							name : "<1%",
							data : [ saturation_one ]
						}, {
							name : "1-5%",
							data : [ saturation_second ]
						}, {
							name : "5-10%",
							data : [ saturation_three ]
						}, {
							name : "10-15%",
							data : [ saturation_four ]
						}, {
							name : ">15%",
							data : [ saturation_five ]
						} ]

					});
				});
			});
		</script>
	</form:form>

</body>


</html>

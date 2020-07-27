<%@page import="com.rural.Model.PrePlanMaster"%>
<%@page import="java.util.*"%>
<!DOCTYPE html>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<html lang="en">

<head>
<jsp:include page="header.jsp" />
<style>
.table {
	overflow: scroll;
}
</style>
<title>Rural Marketing ERP</title>
<style>
.error_message {
	margin-top: 0;
	margin-bottom: 0;
	color: #FF0000;
}
</style>
<script>
function back()
{
	document.frm1.action = "back";
	document.frm1.method = "POST";
	document.frm1.submit();
}

</script>

</head>
<body>
	<form:form name="frm1">

		<div id="wrapper">

			<div id="page-wrapper">
				<div class="row">
					<div class="col-lg-12">
						<h3 class="text-center">Planning</h3>
					</div>
				</div>
				&nbsp; &nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp; &nbsp; &nbsp;&nbsp;
				&nbsp; &nbsp;&nbsp; &nbsp; &nbsp;&nbsp; &nbsp; &nbsp;&nbsp;
				&nbsp;&nbsp; &nbsp; &nbsp;&nbsp; &nbsp; &nbsp;&nbsp; &nbsp;
				&nbsp;&nbsp; &nbsp; &nbsp;&nbsp; &nbsp; &nbsp;&nbsp; &nbsp;
				&nbsp;&nbsp; &nbsp; &nbsp;
				<center>
					<label class="error_message" id="prePlanCreatedMsg">${statusMsg }</label>
				</center>
					<c:set var = "routePlanList" scope = "session" value = "${routePlanList}"/>
				<c:if test="${not empty showrRoutePlanList}">
					<h4>Route Planning</h4>

					<div class="row">
						<div class="col-lg-12">


							<div class="panel panel-default">
								<div class="panel-heading">
									<b>Show All Route Plans </b> <span style="float:right; height='32px';">
										<a href="exportToCSV"> <img src="dist/img/csv.jpg" alt="csv"
											height="35" width="35"></a>
									</span> 

								</div>
								<div class="panel-body">
									<table width="100%"
										class="table table-striped table-bordered table-hover"
										id="dataTables-example">
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
				<input type="button" class="btn btn-primary" onclick="back()"
					value="Back"> <br>
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

		<!-- Morris Charts JavaScript -->
		<script src="vendor/raphael/raphael.min.js"></script>
		<script src="vendor/morrisjs/morris.min.js"></script>
		<script src="data/morris-data.js"></script>

		<!-- Custom Theme JavaScript -->
		<script src="dist/js/sb-admin-2.js"></script>

		<!-- DataTables JavaScript -->
		<script src="vendor/datatables/js/jquery.dataTables.min.js"></script>
		<script src="vendor/datatables-plugins/dataTables.bootstrap.min.js"></script>
		<script src="vendor/datatables-responsive/dataTables.responsive.js"></script>

		<!-- Custom JavaScript For PrePlanning-->
		<script src="js/preplanning.js"></script>
		<!-- Page-Level Demo Scripts - Tables - Use for reference -->
		<script>
		 $('#dataTables-example').DataTable({
	         scrollX:        "1000px",

	         scrollX:        true,

	         scrollCollapse: true,

	         paging:         true,

	        
	                 } );
	        
	    });
    </script>
		<input type="hidden" id="brand" name="brand"
			value="<% out.print(request.getParameter("brand")); %>" />
		<input type="hidden" id="brand" name="city"
			value="<% out.print(request.getParameter("city")); %>" />
		<input type="hidden" id="lsmlsm" name="lsm"
			value="<% out.print(request.getParameter("lsm")); %>" />

	</form:form>
</body>

</html>

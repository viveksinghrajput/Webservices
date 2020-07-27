<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">

<head>
<jsp:include page="header.jsp" />
<style>
table, tr, td {
    /* border: 1px solid black; */
    border-collapse: collapse;
    table-layout: fixed
}
th, td ,tr{
    padding: 5px;
    width: 65px;
}
</style>
</head>

<body>

	<div id="wrapper">

		<div id="page-wrapper">
			<div class="row">
				<div class="col-lg-12">
					<h3 class="text-center">Planning</h3>
				</div>
			</div>
			<h4>Pre Planning</h4>
			<!-- /.row -->
			<div class="row">
				<div class="col-lg-12">
					<div class="panel panel-default">
						<div class="panel-body">
							<div class="row">
								<div class="col-lg-6">
									<form role="form">
										<table width=100%>
											<tr>
												<td><div class="col-xs-2">
														<label>Brand</label>
													</div></td>
												<td><div class="col-xs-2">
														<label>State</label></td>
												<td><div class="col-xs-2">
														<label>City</label></td>
												<td><div class="col-xs-2">
														<label>Span </label></td>
											</tr>
											<tr>
												<td>
													<div class="col-xs-14">
														<select id="brand" class="form-control" name="brand">
															<option value="-1">select</option>

														</select>

													</div>
												</td>
												<td>
													<div class="col-xs-14">
														<select id="state" class="form-control" name="state">
															<option value="-1">select</option>

														</select>

													</div>
												</td>
												<td>
													<div class="col-xs-14">
														<select id="city" class="form-control" name="city">
															<option value="-1">select</option>

														</select>

													</div>
												</td>
												<td>
													<div class="col-xs-14">
														<input class="form-control" id="span" name="span" placeholder="Days">
													</div>
												</td>

											</tr>
											<tr>
												<td>
													<div class="form-group">
													<input class="form-control" id="conversion" type="text"
													placeholder="Conversion &#37;" disabled>
													</div>
												</td>
												<td width="900">
													<div class="form-group">
													<input class="form-control" id="averageSales" type="text"
													 placeholder="Avg.Sale/Promoter" disabled>
													</div>
												</td>
												<td>
													<div class="form-group">
													<input class="form-control" id="saturation" type="text"
													placeholder="Saturation &#37;" disabled>
													</div>
												</td>
												<td>
												<div class="form-group">
												<input type="button" class="btn btn-primary"
													onclick="savePrePlan()" value="Show Pre Plan">
											</div>
												</td>
											</tr>

										</table>
									</form>
								</div>
							</div>





							<div class="row">
								<div class="col-lg-12">
									<div class="panel panel-default">
										<div class="panel-heading">
											<b>Pre-Plan Details</b>
										</div>
										<div class="panel-body">
											<table width="100%"
												class="table table-striped table-bordered table-hover"
												id="dataTables-example">
												<thead>
													<tr>
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

													</tr>
												</thead>
												<tbody>
													<%-- 
														<%int coount=1; %>

														<c:forEach var="PlanList" items="${prePlanList}">
															<%
                                    System.out.println("count "+coount+" co/2 = "+coount/2);
                                    if(coount/2==0){ %>
															<tr class="even gradeC">

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
															</tr>
															<%  }else{ %>
															<tr class="odd gradeX">

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
															</tr>
															<%  
                                    } %>

 
<%-- 														</c:forEach>   --%>





												</tbody>
											</table>
										</div>
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

	<!-- Morris Charts JavaScript -->
	<script src="vendor/raphael/raphael.min.js"></script>
	<script src="vendor/morrisjs/morris.min.js"></script>
	<script src="data/morris-data.js"></script>

	<!-- Custom Theme JavaScript -->
	<script src="dist/js/sb-admin-2.js"></script>

</body>

</html>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@page import="java.util.*"%>
<%@page import="com.rural.Model.VendorWareHouseMaster"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Admin WH DashBoard</title>
<jsp:include page="header.jsp" />
<script
	src="https://maps.googleapis.com/maps/api/js?key=AIzaSyAPcnSkZyiiAM25-kSLn9r117qepkeeB08">
</script>
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
.error_message {
	margin-top: 0;
	margin-left: 2000;
	font-size: .70em;
	margin-bottom: 0;
	color: #FF0000;
	text-align: center;
}
</style>
<!-- Bootstrap Core CSS -->
<link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

</head>
<body>
	<div id="wrapper">

		<form:form name="pageForm" onsubmit="checkBoxValidation">

			<div id="page-wrapper">
				<div class="row">
					<div class="col-lg-12">
						<h3 class="text-center">${strWareHouseName}History</h3>
					</div>
				</div>
				<br>
				<!-- Creates the bootstrap modal where the Maps will appear -->
				<div class="modal fade" id="imagemodal" tabindex="-1" role="dialog"
					aria-labelledby="myModalLabel" aria-hidden="true">
					<div class="modal-dialog" style="width: 800px;">
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal">
									<span aria-hidden="true">&times;</span> <span class="sr-only">Close</span>
								</button>
								<h4 class="modal-title" id="myModalLabel"></h4>
							</div>
							<div class="modal-body" id="modalData"
								style="width: 798px; height: 450px;">

								<img src="" id="imagepreview"
									style="width: 565px; height: 350px;">
							</div>


							<div class="modal-footer">
								<button type="button" class="btn btn-default"
									data-dismiss="modal">Close</button>
							</div>
						</div>
					</div>
				</div>
				<!-- End Bootstrap Modal -->

				<!-- /.row -->

				<div class="row">
					<div class="col-lg-12">
						<div class="panel panel-default">
							<div class="panel-heading" style="height: 50">
								<!-- <b>All WareHouses</b> -->

								
								
								<c:if test="${not empty strId}">	
								<a class="btn btn-primary btn-circle"
									href="editwarehouse?id=${strId}" style="width: 30px;"
									data-toggle="tooltip" data-placement="top" title="Back"><i
									class="fa fa-arrow-left"></i> </a>
								</c:if>
								<c:if test="${not empty backButton}">	
								<a class="btn btn-primary btn-circle"
									href="${backButton}" style="width: 30px;"
									data-toggle="tooltip" data-placement="top" title="Back"><i
									class="fa fa-arrow-left"></i> </a>
								</c:if>
							 <span style="float: right;">
								<a href="downloadHistory"> <img src="dist/img/csv.jpg"
										alt="csv" height="33" width="35">
								</a>
							</span>

							</div>

							<div class="panel-body">
								<table width="100%"
									class="table table-striped table-bordered table-hover"
									id="wareHouseTable" style="margin-bottom: 0px">
									<thead>
										<tr>
											<!-- <th class="text-center"><input type="checkbox" onfocus="Select All" id="checkAll"/></th> -->
											<th class="text-center">WareHouse</th>
											<th class="text-center">Status</th>
											<th class="text-center">Vendor</th>
											<th class="text-center">Contact</th>
											<th class="text-center">Email</th>
											<th class="text-center">Latitude</th>
											<th class="text-center">Longitude</th>
											<th class="text-center">Address</th>
											<th class="text-center">Locality</th>
											<th class="text-center">State</th>
											<th class="text-center">City</th>
											<th class="text-center">PinCode</th>
											<th class="text-center">Images</th>
											<th class="text-center">Map</th>
											<th class="text-center">Area</th>

										</tr>
									</thead>
									<tbody>
										<%
											int count = 1;
										%>

										<c:set var="downloadHistory" scope="session" value="${itemList}" />

										<c:forEach var="itemList" items="${itemList}" varStatus="loop">
											
											<%
												if (count / 2 == 0) {
											%>
											<tr class="even gradeC">

												<td class="text-center"><c:out
														value="${itemList.warehouseName}" /></td>
												<td class="text-center"><c:out
														value="${itemList.warehouseStatus}" /></td>
												<td class="text-center"><c:out
														value="${itemList.vendorName}" /></td>
												<td class="text-center"><c:out
														value="${itemList.vendorContact}" /></td>
												<td class="text-center"><c:out
														value="${itemList.vendorEmail}" /></td>

												<td class="text-center"><c:out
														value="${itemList.wareHouseLatitute}" /></td>
												<td class="text-center"><c:out
														value="${itemList.wareHouseLongitute}" /></td>
												<td class="text-center"><c:out
														value="${itemList.wareHouseAddress01}" /></td>
												<td class="text-center"><c:out
														value="${itemList.wareHouseAddress02}" /></td>
												<td class="text-center"><c:out
														value="${itemList.state}" /></td>
												<td class="text-center"><c:out value="${itemList.city}" /></td>
												<td class="text-center"><c:out
														value="${itemList.pinCode}" /></td>
												<td class="text-center"><a href="#myGallery${loop.index}" data-toggle="modal" data-target="#myModal">View</a></td>
												<td class="text-center"><a href="javascript:;"
													onclick="showMap(<c:out value="${itemList.wareHouseLatitute}"/>,<c:out value="${itemList.wareHouseLongitute}"/>);">
														<img src="dist/img/MapMarker.png" alt="View Map Location"
														width="25" height="35" border="0">
												</a></td>
												<td class="text-center"><c:out value="${itemList.wareHouseAreainSqft}"></c:out></td>
												
												<!--Start PlaceHolder for Images Carousel -->
												<!--begin modal window-->
												<div class="modal fade" id="myModal">
													<div class="modal-dialog">
														<div class="modal-content">
															<div class="modal-header">
																<div class="pull-left">Vendor WareHouse Images</div>
																<button type="button" class="close" data-dismiss="modal"
																	title="Close">
																	<span class="glyphicon glyphicon-remove"></span>
																</button>
															</div>
															<div class="modal-body">

																<!--begin carousel-->
																<div id="myGallery${loop.index}" class="carousel slide"
																	data-interval="false">
																	<div class="carousel-inner">
																		<div class="item active">
																			<div class="carousel-caption">

																				<p>Outer Image</p>
																			</div>
																			<img src="${itemList.wareHouseOuterImage}"
																				alt="Outer Image">

																		</div>
																		<div class="item">
																			<div class="carousel-caption">

																				<p>Inner Image 01</p>
																			</div>
																			<img src="${itemList.wareHouseInnerImage01}"
																				alt="Inner Image 01">

																		</div>
																		<div class="item">
																			<div class="carousel-caption">

																				<p>Inner Image 02</p>
																			</div>
																			<img src="${itemList.wareHouseInnerImage02}"
																				alt="Inner Image 02">

																		</div>

																		<!--end carousel-inner-->
																	</div>
																	<!--Begin Previous and Next buttons-->
																	<a class="left carousel-control" href="#myGallery${loop.index}"
																		role="button" data-slide="prev"> <span
																		class="glyphicon glyphicon-chevron-left"></span></a> <a
																		class="right carousel-control" href="#myGallery${loop.index}"
																		role="button" data-slide="next"> <span
																		class="glyphicon glyphicon-chevron-right"></span></a>
																	<!--end carousel-->
																</div>
																<!--end modal-body-->
															</div>
															<div class="modal-footer">

																<button class="btn-sm close" type="button"
																	data-dismiss="modal">Close</button>
																<!--end modal-footer-->
															</div>
															<!--end modal-content-->
														</div>
														<!--end modal-dialoge-->
													</div>


												</div>
												<!--end myModal-->
												<!--End PlaceHolder for Images Carousel -->
											</tr>
											<%
												} else {
											%>
											<tr class="odd gradeX">

												<td class="text-center"><c:out
														value="${itemList.warehouseName}" /></td>
												<td class="text-center"><c:out
														value="${itemList.warehouseStatus}" /></td>
												<td class="text-center"><c:out
														value="${itemList.vendorName}" /></td>
												<td class="text-center"><c:out
														value="${itemList.vendorContact}" /></td>
												<td class="text-center"><c:out
														value="${itemList.vendorEmail}" /></td>

												<td class="text-center"><c:out
														value="${itemList.wareHouseLatitute}" /></td>
												<td class="text-center"><c:out
														value="${itemList.wareHouseLongitute}" /></td>
												<td class="text-center"><c:out
														value="${itemList.wareHouseAddress01}" /></td>
												<td class="text-center"><c:out
														value="${itemList.wareHouseAddress02}" /></td>
												<td class="text-center"><c:out
														value="${itemList.state}" /></td>
												<td class="text-center"><c:out value="${itemList.city}" /></td>
												<td class="text-center"><c:out
														value="${itemList.pinCode}" /></td>
												<td class="text-center"><a href="#myGallery${loop.index}"
													data-toggle="modal" data-target="#myModal">View</a></td>
												<td class="text-center"><a href="javascript:;"
													onclick="showMap(<c:out value="${itemList.wareHouseLatitute}"/>,<c:out value="${itemList.wareHouseLongitute}"/>);">
														<img src="dist/img/MapMarker.png" alt="View Map Location"
														width="25" height="35" border="0">
												</a></td>
												<td class="text-center"><c:out
														value="${itemList.wareHouseAreainSqft}"></c:out></td>

												<!--Start PlaceHolder for Images Carousel -->
												<!--begin modal window-->
												<div class="modal fade" id="myModal">
													<div class="modal-dialog">
														<div class="modal-content">
															<div class="modal-header">
																<div class="pull-left">Vendor WareHouse Images</div>
																<button type="button" class="close" data-dismiss="modal"
																	title="Close">
																	<span class="glyphicon glyphicon-remove"></span>
																</button>
															</div>
															<div class="modal-body">

																<!--begin carousel-->
																<div id="myGallery${loop.index}" class="carousel slide"
																	data-interval="false">
																	<div class="carousel-inner">
																		<div class="item active">
																			<div class="carousel-caption">

																				<p>Outer Image</p>
																			</div>
																			<img src="${itemList.wareHouseOuterImage}"
																				alt="Outer Image">

																		</div>
																		<div class="item">
																			<div class="carousel-caption">

																				<p>Inner Image 01</p>
																			</div>
																			<img src="${itemList.wareHouseInnerImage01}"
																				alt="Inner Image 01">

																		</div>
																		<div class="item">
																			<div class="carousel-caption">

																				<p>Inner Image 02</p>
																			</div>
																			<img src="${itemList.wareHouseInnerImage02}"
																				alt="Inner Image 02">

																		</div>

																		<!--end carousel-inner-->
																	</div>
																	<!--Begin Previous and Next buttons-->
																	<a class="left carousel-control" href="#myGallery${loop.index}"
																		role="button" data-slide="prev"> <span
																		class="glyphicon glyphicon-chevron-left"></span></a> <a
																		class="right carousel-control" href="#myGallery${loop.index}"
																		role="button" data-slide="next"> <span
																		class="glyphicon glyphicon-chevron-right"></span></a>
																	<!--end carousel-->
																</div>
																<!--end modal-body-->
															</div>
															<div class="modal-footer">

																<button class="btn-sm close" type="button"
																	data-dismiss="modal">Close</button>
																<!--end modal-footer-->
															</div>
															<!--end modal-content-->
														</div>
														<!--end modal-dialoge-->
													</div>


												</div>
												<!--end myModal-->
												<!--End PlaceHolder for Images Carousel -->
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


			</div>
			<!-- /#page-wrapper -->
		</form:form>

	</div>
	<!-- /#wrapper -->


	<!-- jQuery -->
	<script src="vendor/jquery/jquery.min.js"></script>

	<!-- Bootstrap Core JavaScript -->
	<script src="vendor/bootstrap/js/bootstrap.min.js"></script>

	<!-- <!-- Metis Menu Plugin JavaScript -->
	<script src="vendor/metisMenu/metisMenu.min.js"></script>
	-->

	<!-- DataTables JavaScript -->
	<script src="vendor/datatables/js/ColReorderWithResize.js"></script>
	<script src="vendor/datatables/js/jquery.dataTables.min.js"></script>
	<script src="vendor/datatables-plugins/dataTables.bootstrap.min.js"></script>
	<script src="vendor/datatables-responsive/dataTables.responsive.js"></script>


	<!-- Custom Theme JavaScript -->
	<script src="dist/js/sb-admin-2.js"></script>

	<!-- Custom JavaScript For Warehouse-->
	<script src="js/warehouse.js"></script>


	<!-- <script>
    $(document).ready(function() {
        $('#wareHouseTable').DataTable({
        	"ordering": false,
            "scrollX": true 
        });
        
    });
    </script> -->
	<script type="text/javascript">
    $(document).ready(function() {
    var table = $('#wareHouseTable').DataTable( {
    	
    	"ordering"		: false,
    	
        scrollX:        true,
       
        paging:         true,
        "autoWidth": false,
        columnDefs: [
            { width: '100%', targets: 0 }
        ],
        fixedColumns: true
        
    	} );
	} );
    
    
    </script>

	<script type="text/javascript">
     $("#checkAll").click(function () {
        //$('input:checkbox').not(this).prop('checked', this.checked);
    	 $('input:checkbox').not("[disabled]").prop('checked', this.checked);
    }); 
    /* $("#checkAll").click(function (){
    		var checked_status = this.checked;

    		$('div#item input[type=checkbox]').not("[disabled]").each(function () {
               this.checked = checked;
    			});
    }); */
    </script>


</body>
</html>
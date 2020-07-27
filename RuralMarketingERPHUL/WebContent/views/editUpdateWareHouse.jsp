<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@page import="java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>View/Edit WareHouse</title>
<jsp:include page="header.jsp" />
<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyAPcnSkZyiiAM25-kSLn9r117qepkeeB08">></script>
<style type="text/css">
.dataTables_wrapper .dataTables_length {
	float: left;
}

.dataTables_wrapper .dataTables_filter {
	float: right;
	text-align: left;
}

.map-wrapper-background {
	width: 100%;
	height: 100%;
	background-color: rgba(16, 16, 16, 0.6);
	position: fixed;
	top: 0;
	left: 0;
	z-index: 99999;
}

.map-container {
	width: 600px;
	height: 400px;
	background-color: #fff;
	margin: auto;
	margin-top: 60px;
}
</style>
</head>
<body>
	<div class="map-wrapper-background" style="display: none;">
		<div class="map-container"><div style="width:100%;height:100%;" id="google-map-shower"></div></div>
	</div>
	<div id="wrapper">


		<div id="page-wrapper">
			<div class="row">
				<div class="col-lg-12">
					<h3 class="border-bottom-underline">Edit<b> ${warehouse.name} </b>&nbsp;Details</h3>
				</div>
				
			</div>
			<div align="center">
								<h5 align="center" style="color: #FF0000;"> <c:out value="${strStatus}"/></h5>
						</div>
			<!-- /.row -->
			



			<!-- Show My warehouses Here -->

			<%-- <h4>
				
				<c:if test="${not empty strStatus}">
					<c:if test="${strStatus == 'Success'}">
						<div class="alert alert-success alert-dismissable">
							<button type="button" class="close" data-dismiss="alert"
								aria-hidden="true">&times;</button>
							${strStatus}
						</div>
					</c:if>
				</c:if>
			</h4> --%>
			<div class="row">
				<div class="col-lg-12">
					<div class="panel panel-default">
						<div class="panel-body">

								<div class="row">
									<div class="col-xs-12">
										<div class="form-group">
&nbsp;&nbsp; 
											<a class="glyphicon glyphicon-circle-arrow-left" href="warehouse"
												 style="font-size: 30px;top:.8pc;color: #337AB5;text-decoration: none;" ></a>
												
												<!-- &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <a href="warehouse"></div>
							<span class="glyphicon glyphicon-circle-arrow-left" style="font-size: 30px;color: #337AB5;"></span></a>
 -->
											<c:if test="${userRole == 'Vendor'}">
													&nbsp;&nbsp; 
													<a class="btn btn-primary btn-circle " href="javascript:;"
													onclick="editWareHouse();" style="width: 30px;"
													data-toggle="tooltip" data-placement="top"
													title="Edit WareHouse Details"><i
													class="glyphicon glyphicon-pencil"></i> </a>
													&nbsp; 
													<input id="submit" type="button"
													class="btn btn-primary fa-save" onclick="saveDetails();"
													value="&nbsp;Save&nbsp;" style="width: 100px;" disabled> 
													
													&nbsp; 
													</c:if>

											<c:if test="${userRole == 'Admin'}">
													
														&nbsp;&nbsp; 
														<a class="btn btn-primary btn-circle " href="javascript:;"
													onclick="editWareHouse();" style="width: 30px;"
													data-toggle="tooltip" data-placement="top"
													title="Edit WareHouse Details"><i
													class="glyphicon glyphicon-pencil"></i> </a>
														&nbsp;
														
														
														<input id="submit" type="button"
													class="btn btn-primary fa-save" onclick="saveDetails();"
													value="&nbsp;Save&nbsp;" style="width: 100px;" disabled> 
														
														&nbsp;
														<c:if test="${warehouse.status == 'Approved'}">
													<a class="btn btn-success disabled" href="javascript:;"
														onclick="approve();" style="width: 100px;">Approve </a>
															&nbsp;
														 	<a class="btn btn-danger disabled" href="javascript:;"
														onclick="reject();" style="width: 100px;">Reject </a>
												</c:if>
												<c:if test="${warehouse.status == 'Submitted'}">
													<a class="btn btn-success" href="javascript:;"
														onclick="approve();" style="width: 100px;">Approve </a>
															&nbsp;
														 	<a class="btn btn-danger" href="javascript:;"
														onclick="reject();" style="width: 100px;">Reject </a>
												</c:if>
											</c:if>

											<c:if test="${userRole == 'Audit'}">


												<c:if test="${warehouse.status == 'Approved'}">
													<a class="btn btn-success disabled" href="javascript:;"
														onclick="approve();" style="width: 100px;">Approve </a>
															&nbsp;
															<a class="btn btn-danger disabled" href="javascript:;"
														onclick="reject();" style="width: 100px;">Reject </a>
												</c:if>
												<c:if test="${warehouse.status == 'Submitted'}">
													<a class="btn btn-success" href="javascript:;"
														onclick="approve();" style="width: 100px;">Approve </a>
															&nbsp;
														 	<a class="btn btn-danger" href="javascript:;"
														onclick="reject();" style="width: 100px;">Reject </a>
												</c:if>
											</c:if>
										</div>
									</div>
								</div>


								<form:form name="wareHouseForm"  modelAttribute="warehouse">
								<input type="hidden" name="size" id="size" value="${fn:length(itemList)}"/> 
									<div class="row">
										<div class="col-lg-12">
											<div class="panel panel-default">
												<div class="panel-body">

													<input type="hidden" name="wareHouseId" id="wareHouseId"
														value="${warehouse.id}" />
														<br>
													<div class="row">
														<!-- Start of Row -->
														<div class="col-xs-3">
															<label>Vendor Contact </label>
														</div>
														<div class="col-xs-3">
															<input class="form-control" id="contact" name="contact" 
																value="${warehouse.vendorContact}" type="text" disabled
																maxlength="10"/>
														</div>
	<div class="col-xs-3">
															<label>WareHouse Images</label>
														</div> <div class="form-group">
														<c:forEach var="photo" items="${photoList}">
														
														
															<c:if test="${not empty photo}">
																
																
																	<!-- <input id="showOuterImage" type="button"
												class="btn btn-primary" onclick="showOuterImage()"
												value="View&nbsp;" style="width: 140px;"> -->

																	<img class="singleImageShower" src="get_image?path=${photo.path}"
														height="25" width="25"></img>
												
															</c:if>	
															<c:if test="${empty photo.path}">
																<div class="col-xs-5">
																	<label>No Image</label>
																</div>
															</c:if>
														</c:forEach>
														<c:if test="${not empty itemList}">
																	<a href="javascript:;"
																	onclick="showMap(['<c:out value="${itemList.get(0).getWareHouseOuterImage() }"/>',<c:out value="${itemList.get(0).getLatOuter() }"/>,
<c:out value="${itemList.get(0).getLonOuter() }"/>,'<c:out value="${itemList.get(0).getWareHouseInnerImage01() }"/>',<c:out value="${itemList.get(0).getLatInner1() }"/>,
<c:out value="${itemList.get(0).getLonInner1() }"/>,'<c:out value="${itemList.get(0).getWareHouseInnerImage02() }"/>',<c:out value="${itemList.get(0).getLatInner2() }"/>,
<c:out value="${itemList.get(0).getLonInner2() }"/>]);">
																		<img src="dist/img/MapMarker.png"
																		alt="View Map Location" width="25" height="35"
																		border="0">
																	</a></c:if>
														</div>
													
													</div>
													<!-- End of Row -->
													<br>
													<!-- Break Space -->


													<div class="row">
														<!-- Start of Row -->
														<div class="col-xs-3">
															<label>Vendor Email </label>
														</div>
														<div class="col-xs-3">
															<input class="form-control" id="email" name="email" 
																value="${warehouse.vendorEmail}" type="text" disabled/>
														</div>
	<div class="col-xs-3">
															<label>Vendor Owner</label>
														</div>
														<div class="col-xs-3" style="padding-left: 0pc;">
															<input class="form-control" id="owner" name="owner"
																value="${owner}" type="text" readonly>
														</div>

													 
													</div>
													<!-- End of Row -->
													<br>
													<!-- Break Space -->


													<div class="row">
														<!-- Start of Row -->

														<div class="col-xs-3">
															<label>Warehouse Address 1</label>
														</div>
														<div class="col-xs-3">
															<input class="form-control" id="address01" 
																name="address01	" value="${warehouse.addr1}"
																type="text" readonly/>
														</div>

<div class="col-xs-3">
															<label>Comment</label>
														</div>
														<c:if test="${not empty warehouse.comment}">
															<div class="col-xs-3" style="padding-left: 0pc;">
																<textarea class="form-control" id="comment"
																	name="comment" style="height: 35px" readonly><c:out
																		value="${warehouse.comment}"></c:out></textarea>
															</div>
														</c:if>
														<c:if test="${empty warehouse.comment}">
															<div class="col-xs-3">
																<textarea class="form-control" id="comment"
																	name="comment" style="height: 35px"></textarea>
															</div>
														</c:if>
													 
													</div>
													<!-- End of Row -->
													<br>
													<!-- Break Space -->


													<div class="row">
														<!-- Start of Row -->
														<div class="col-xs-3">
															<label>Warehouse Address 2 </label>
														</div>
														<div class="col-xs-3">
															<input class="form-control" id="address02"
																name="address01" value="${warehouse.addr1}"
																type="text" readonly>
														</div>

													
																
													</div>
													<!-- End of Row -->
													<br>
													<!-- Break Space -->


													<div class="row">
														<!-- Start of Row -->
														<div class="col-xs-3">
															<label>State</label>
														</div>
														<div class="col-xs-3">
															<input class="form-control" id="state" name="state"
																value="${stateMap[warehouse.stateId]}" type="text" readonly>
														</div>

													

													</div>
													<!-- End of Row -->
													<br>
													<!-- Break Space -->

													<div class="row">
														<!-- Start of Row -->
														<div class="col-xs-3">
															<label>City</label>
														</div>
														<div class="col-xs-3">
															<input class="form-control" id="city" name="city"
																value="${cityMap[warehouse.cityId]}" type="text" readonly>
														</div>

													

													</div>
													<!-- End of Row -->
													<br>
													<!-- Break Space -->
													<div class="row">
														<!-- Start of Row -->
														<div class="col-xs-3">
															<label>PinCode</label>
														</div>
														<div class="col-xs-3">
															<input class="form-control" id="pincode" name="pincode"
																value="${warehouse.pin}" type="text" readonly>
														</div>

														

													</div>
													<!-- End of Row -->
													<br>
													<!-- Break Space -->


													<div class="row">
														<!-- Start of Row -->
														<div class="col-xs-3">
															<label>Area in Sq. Feet</label>
														</div>
														<div class="col-xs-3">
															<input class="form-control" id="area" name="area"
																value="${warehouse.area}" type="text"
																readonly>
														</div>
														<!-- <div class="col-xs-3">
															<label>History</label>
														</div>
														<div class="col-xs-3">
															<input id="history" type="button" class="btn btn-primary"
																onclick="showHistory()" value="View&nbsp;"
																style="width: 140px;">
														</div> -->


													</div>
													<!-- End of Row -->
													<br>
													<!-- Break Space -->

													<div class="row">
														<!-- Start of Row -->

														<input type="hidden" id="warehouseName"
															name="warehouseName" value="${warehouse.name}">
													 	<input type="hidden" id="outerImage" name="outerImage"
															value="${wareHouseOuterImage}"> <input
															type="hidden" id="innerImage01" name="innerImage01"
															value="${wareHouseInnerImage01}"> <input
															type="hidden" id="innerImage02" name="innerImage02"
															value="${wareHouseInnerImage02}">

 
													</div>
													<!-- End of Row -->
												</div>
											</div>
										</div>

							</div></form:form>
									<!-- End of Main ROW -->
							
							
								<!-- Creates the bootstrap modal where the Maps will appear -->
								<!-- <div class="modal fade" id="imagemodal" tabindex="-1"
									role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
									<div class="modal-dialog" style="width: 800px;">
										<div class="modal-content">
											<div class="modal-header">
												<button type="button" class="close" data-dismiss="modal">
													<span aria-hidden="true">&times;</span> <span
														class="sr-only">Close</span>
												</button>
												<h4 class="modal-title" id="myModalLabel"></h4>
											</div>
											<div class="modal-body" id="map-canvas"
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
								</div> -->
								
								<!-- <div class="modal-body" id="map-canvas"
												style="width: 798px; height: 450px;">

												<img src="" id="imagepreview"
													style="width: 565px; height: 350px;">
											</div> -->
								<!-- End Bootstrap Modal -->
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
												<div id="myGallery" class="carousel slide"
													data-interval="false">
													<div class="carousel-inner">
														<div class="item active">
															<img src="${warehouse.name}"
																alt="Outer Image">

															<p>Outer Image</p>
														</div>
														<div class="item">

															<img src="${warehouse.name}"
																alt="Inner Image 01">
															<p>Inner Image 01</p>
														</div>
														<div class="item">
															<img src="${warehouse.name}"
																alt="Inner Image 02">


															<p>Inner Image 02</p>

														</div>


													</div>
													<!--end carousel-inner-->
													<!--Begin Previous and Next buttons-->
													<a class="left carousel-control" href="#myGallery"
														role="button" data-slide="prev"> <span
														class="glyphicon glyphicon-chevron-left"></span></a> <a
														class="right carousel-control" href="#myGallery"
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




							<c:if test="${empty warehouse}">
								<c:out value="${noWarehouse}" />
							</c:if>

						</div>
					<!-- </div>
				</div>

			</div>
			/.row


			Create WareHouse History Panel Here
			<div class="row">
				<div class="col-lg-12"> -->
					<div class="panel panel-default">
					
							<div class="panel-heading" style="height: 50">
								
								<div class="btn-group">

									<select class="form-control"
										onchange="if (this.value) window.location.href=this.value">
										<!-- <option value="">Status</option> -->
										<c:if test="${selectStatus == 'All'}">
											<option value="editwarehouse?id=<c:out value="${id}"/>&sort=All" selected>All</option>
										<%-- 	<option value="editwarehouse?id=<c:out value="${id}"/>&sort=Submitted">Submitted</option> --%>
											<option value="editwarehouse?id=<c:out value="${id}"/>&sort=Approved">Approved</option>
											<option value="editwarehouse?id=<c:out value="${id}"/>&sort=Rejected">Rejected</option>
										</c:if>
										<%-- <c:if test="${selectStatus == 'Submitted'}">
											<option value="editwarehouse?id=<c:out value="${id}"/>&sort=All" >All</option>
											<option value="editwarehouse?id=<c:out value="${id}"/>&sort=Submitted" selected>Submitted</option>
											<option value="editwarehouse?id=<c:out value="${id}"/>&sort=Approved">Approved</option>
											<option value="editwarehouse?id=<c:out value="${id}"/>&sort=Rejected">Rejected</option>
										</c:if> --%>
										<c:if test="${selectStatus == 'Approved'}">
									<option value="editwarehouse?id=<c:out value="${id}"/>&sort=All" >All</option>
											<%-- <option value="editwarehouse?id=<c:out value="${id}"/>&sort=Submitted">Submitted</option> --%>
											<option value="editwarehouse?id=<c:out value="${id}"/>&sort=Approved" selected>Approved</option>
											<option value="editwarehouse?id=<c:out value="${id}"/>&sort=Rejected">Rejected</option>
										</c:if>
										<c:if test="${selectStatus == 'Rejected'}">
											<option value="editwarehouse?id=<c:out value="${id}"/>&sort=All" >All</option>
											<%-- <option value="editwarehouse?id=<c:out value="${id}"/>&sort=Submitted">Submitted</option> --%>
											<option value="editwarehouse?id=<c:out value="${id}"/>&sort=Approved">Approved</option>
											<option value="editwarehouse?id=<c:out value="${id}"/>&sort=Rejected" selected>Rejected</option>
										</c:if>
									</select>

								</div>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <span
									class="text-center">${strWareHouseName}&nbsp;History</span> <span
									style="float: right;"> <a onclick="validateCSV();"> <img
										src="dist/img/csv-64.png" alt="csv" height="33" width="35">
								</a>
								</span>

										<!-- End Second Class -->
			</div>

							
						

								
														
													
															<!-- <input id="history" type="button"
																class="btn btn-primary" onclick="wareHouseTable"
																value="View&nbsp;" style="width: 140px;"> -->
														<br>		
															<c:if test="${not empty itemList}">	
															<div class="panel-body">
																		<table width="100%"
																			class="table table-striped table-bordered table-hover"
																			id="wareHouseTable">
																	<thead>
										<tr class = "tr_color">
											
											<th class="text-center">WareHouse Name</th>
											<th class="text-center">Status</th>
											<th class="text-center">Vendor</th>
											<th class="text-center">Vendor Email</th>
											<th class="text-center">Contact</th>
											<th class="text-center">Address1</th>
											<th class="text-center">Address2</th>
											<th class="text-center">State</th>
											<th class="text-center">City</th>
										</tr>
									</thead>
									<tbody>
									
										<c:set var="downloadHistory" scope="session" value="${itemList}" />
										<c:forEach var="itemList" items="${itemList}" varStatus="loop">
											<tr class="even gradeC">
												<td class="text-center"><c:out
														value="${itemList.warehouseName}" /></td>
												<td class="text-center"><c:out
														value="${itemList.warehouseStatus}" /></td>
												<td class="text-center"><c:out
														value="${vendorMap[itemList.vendorId]}" /></td>
														<td class="text-center"><c:out
														value="${itemList.vendorEmail}" /></td>
												<td class="text-center"><c:out
														value="${itemList.vendorContact}" /></td>
												<td class="text-center"><c:out
														value="${itemList.wareHouseAddress01}" /></td>
												<td class="text-center"><c:out
														value="${itemList.wareHouseAddress02}" /></td>
												<td class="text-center"><c:out
														value="${stateMap[itemList.stateId]}" /></td>
												<td class="text-center"><c:out
														value="${cityMap[itemList.cityId]}" /></td>	
																
										
												
						
												<!--end myModal-->
												<!--End PlaceHolder for Images Carousel -->
											</tr>
											
											</c:forEach></tbody></table></div></c:if>
							<!-- End Second Panel Body -->
						<c:if test="${empty itemList}">	<br>
					<div align="center"><span class="text-center">&nbsp;No History Available</span></div>
</c:if>
					</div>
					<!-- End Second Panel Default -->
				</div>

</div>	

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

	<!-- Custom JavaScript For Warehouse-->
	<script src="js/warehouse.js"></script>
	<script src="js/bootstrap3-typeahead.min.js"></script>

	<script type="text/javascript">
    $(document).ready(function() {
    	
		/* $('#wareHouseTable').DataTable( {
        	
        	scrollX		:	true,
           	paging		:	true,
            "autoWidth"	:	false,
            "scrollCollapse": true,
	        "paging"		: false,
			"ordering" 		: false,
			stateSave		: true,
        	});
    	 */
    	 
    	/*  $('#wareHouseTable').DataTable({
 			scrollX : true,

 			scrollX : true,

 			scrollCollapse : true,

 			paging : true,

 		}); */
    	document.getElementById("submit").disabled = true;
    	document.getElementById("contact").disabled = true;
    	document.getElementById("email").disabled = true;
    	document.getElementById("address01").disabled = true;
    	document.getElementById("address02").disabled = true;
    	document.getElementById("state").disabled = true;
    	document.getElementById("city").disabled = true;
    	document.getElementById("pincode").disabled = true;
    	document.getElementById("area").disabled = true;
    	
    	
		});
    </script>
	<!-- Page-Level Demo Scripts - Notifications  -->
	<script>
    // tooltip demo
    $('.tooltip-demo').tooltip({
        selector: "[data-toggle=tooltip]",
        container: "body"
    })
    // popover demo
    $("[data-toggle=popover]")
        .popover()
    </script>
    <script type="text/javascript">
                
                function validateCSV(){
                  var size=document.getElementById("size").value;
                  //alert(size);
                  if(size!=0){
                    
                    document.wareHouseForm.action = "downloadHistory";
                    document.wareHouseForm.method = "GET";
                    document.wareHouseForm.submit();   
                    
                  }else{
                    alert("No Content is available for Download... ");
                  }
                }
                </script>
    


</body>
</html>
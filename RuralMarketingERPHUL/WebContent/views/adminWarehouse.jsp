<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
			<%@page import="com.rural.Model.Photo"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@page import="java.util.*"%>
<%@page import="com.rural.Model.VendorWareHouseMaster"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>WareHouse DashBoard</title>
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
.error_message {
	margin-top: 0;
	margin-left: 2000;
	font-size: .70em;
	margin-bottom: 0;
	color: #FF0000;
	text-align: center;
}

.dataTables_scrollHeadInner th:first-child, 
.dataTables_scrollHeadInner td:first-child,
.dataTables_scrollBody th:first-child,
.dataTables_scrollBody td:first-child {
	width: 34px !important;
}
</style>
<!-- Bootstrap Core CSS -->
<link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

</head>
<body>
	<div id="wrapper">

		<form:form name="pageForm" onsubmit="checkBoxValidation">
		<input type="hidden" name="size" id="size" value="${fn:length(wareHouseList)}"/> 
	<input type="hidden" name="id" id="id">
			<div id="page-wrapper">
				<div class="row"><br>
					<div class="col-lg-12">
						<div class="text-left text-left border-bottom-underline"  style="margin-left: -1pc;">WareHouse DashBoard</h3>
					</div>
					<div align="center">
								<h5 align="center" style="color: #FF0000;"> <c:out value="${statusMsg}"/></h5>
						</div>
				</div>
				<br><br>


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
							<div class="panel-heading" style="height: 50;padding-top: 0.4pc;">
								<!-- <b>All WareHouses</b> -->

								<div class="btn-group">
									
									<select class="form-control"
										onchange="if (this.value) window.location.href=this.value" id="sort">
										<!-- <option value="">Status</option> -->
										<c:if test="${selectStatus == 'All'}">
										<option value="warehouse" selected>All</option>
										<option value="showPendingWareHouses">Submitted</option>
										<option value="showApprovedWareHouses">Approved</option>
										<option value="showRejectedWareHouses">Rejected</option>
										</c:if>
										<c:if test="${selectStatus == 'Submitted'}">
										<option value="warehouse" >All</option>
										<option value="showPendingWareHouses" selected>Submitted</option>
										<option value="showApprovedWareHouses">Approved</option>
										<option value="showRejectedWareHouses">Rejected</option>
										</c:if>
										<c:if test="${selectStatus == 'Approved'}">
										<option value="warehouse" >All</option>
										<option value="showPendingWareHouses">Submitted</option>
										<option value="showApprovedWareHouses" selected>Approved</option>
										<option value="showRejectedWareHouses">Rejected</option>
										</c:if>
										<c:if test="${selectStatus == 'Rejected'}">
										<option value="warehouse" >All</option>
										<option value="showPendingWareHouses">Submitted</option>
										<option value="showApprovedWareHouses">Approved</option>
										<option value="showRejectedWareHouses" selected>Rejected</option>
										</c:if>
									</select>

								</div>

								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
								
								<c:if test="${userRole == 'Admin'}">
																	
									<a href="javascript:;"
										class="btn btn-success disabled" onclick="massApprove();">&nbsp;&nbsp;
										Approve &nbsp; &nbsp;</a> &nbsp; 
									<a href="javascript:;"
										class="btn btn-danger disabled" onclick="massReject();">&nbsp;&nbsp;
										Reject &nbsp; &nbsp;
									</a>
									
								</c:if>
								<c:if test="${userRole == 'Audit'}">
								
									<a href="javascript:;"
										class="btn btn-primary" onclick="massApprove();">&nbsp;&nbsp;
										Approve &nbsp; &nbsp;</a> &nbsp; 
									<a href="javascript:;"
										class="btn btn-primary" onclick="massReject();">&nbsp;&nbsp;
										Reject &nbsp; &nbsp;
									</a>
									</c:if>
								
							

								<span style="float: right;margin-top: -0pc;margin-right: -0.7pc;"> <a onclick="validateCSV();">
										<img src="dist/img/csv-64.png" alt="csv" height="33" width="35">
								</a>
								</span>

							</div>
							<form:form name="wareHouseListForm" id="wareHouseListForm">
							
							<div class="panel-body">
								<table width="100%" class="table table-striped table-bordered table-hover"
									id="tablefeilds" style="margin-bottom: 0px">
									<thead>
										<tr class = "tr_color">
											<th style="width: 30px !important;"  class="text-center"><input type="checkbox" onfocus="Select All" id="checkAll"/></th>
											<th class="text-center">WareHouse</th>
											<th class="text-center">Status</th>
											<th class="text-center">Vendor</th>
											<th class="text-right">Contact</th>
											<th class="text-center">Email</th>
											<!-- <th class="text-right">Latitude</th>
											<th class="text-right">Longitude</th> -->
											<th class="text-center">Address</th>
											<th class="text-center">Locality</th>
										<!-- 	<th class="text-center">State</th>
											<th class="text-center">City</th> -->
											<th class="text-right">PinCode</th>
											<th class="text-center">Images</th>
										<!-- 	<th class="text-center">Map</th> -->
											<th class="text-right">Area</th>
											<!-- <th class="text-center">History</th> -->
											
											
										</tr>
									</thead>
									<tbody>
										
										<c:set var = "download" scope = "session" value = "${wareHouseList}"/>
										<%-- <c:set var="backButton" value="warehouse" /> --%>
										<input type="hidden" name="backButton" id="backButton" value="warehouse" />
										<c:forEach var="itemList" items="${wareHouseList}">
											
										
											<tr class="even gradeC">
												<%
													
													boolean checkboxDisabled = true; 
													VendorWareHouseMaster strItemList=(VendorWareHouseMaster)pageContext.getAttribute("itemList");
													String status=strItemList.getStatus();
													if( (status.equalsIgnoreCase("Submitted")) || (status.equalsIgnoreCase("Re-Submitted"))){
														checkboxDisabled=false;
													}
													String checkboxState = checkboxDisabled ? "disabled" : "";
													
												%>
												<td style="width: 30px !important;"  class="text-center"><input type="checkbox" name="wareHouse_submit"
													value="<c:out value="${itemList.id}"/>"  <%=checkboxState%> ></td>
												<td class="text-center">
													<%-- <a href="editwarehouse?id=<c:out value="${itemList.id}"/>">
													<c:out value="${itemList.name}" />
													</a> --%>
														<a  href ="#" onClick = "editwarehouse('${itemList.id}');"><c:out
																		value="${itemList.name}"></c:out></a>
													<input type="hidden" name="warehouseName" id="warehouseName" value="${itemList.name}" />
												</td>
												<td class="text-center"><c:out value="${itemList.status}" /></td>
												<td class="text-center"><c:out value="${vendorMap[itemList.vendorId]}" /></td>
												<td class="text-right"><c:out value="${itemList.vendorContact}" /></td>
												<td class="text-center"><c:out value="${itemList.vendorEmail}" /></td>

												<%-- <td class="text-right"><c:out value="${itemList.wareHouseLatitute}" /></td>
												<td class="text-right"><c:out value="${itemList.wareHouseLongitute}" /></td> --%>
												<td class="text-center"><c:out value="${itemList.addr1}" /></td>
												<td class="text-center"><c:out value="${itemList.addr2}" /></td>
											<%-- 	<td class="text-center"><c:out value="${itemList.state}" /></td>
												<td class="text-center"><c:out value="${itemList.city}" /></td> --%>
												<td class="text-right"><c:out value="${itemList.pin}" /></td>
											 	<td class="text-center"><a href="#myGallery" data-toggle="modal" data-target="#myModal">View</a></td>
											<%--	<td class="text-center"><a href="javascript:;"
													onclick="showMap(<c:out value="${itemList.wareHouseLatitute}"/>,<c:out value="${itemList.wareHouseLongitute}"/>);">
														<img src="dist/img/MapMarker.png" alt="View Map Location"
														width="25" height="35" border="0">
												</a></td> --%>
												<td class="text-right"><c:out value="${itemList.area}"></c:out></td>
												<%-- <td class="text-center">
												<a href="showWareHouseHistory?id=<c:out value="${itemList.warehouseName}"/>&backButton=warehouse">View</a>
												<input id="history" type="button" class="btn btn-primary" data-id="${itemList.warehouseName}" onclick="showWareHouseHistory()" value="View&nbsp;" style="width: 55px;">
												</td> --%>
												<!--Start PlaceHolder for Images Carousel -->
												<!--begin modal window-->
												<c:set var="trackId" value="${itemList.trackId }" scope = "session"/>
												<%-- <%Map<String,List<Photo>>  map=(Map<String,List<Photo>>) request.getAttribute("photoList"); 
	List<Photo> photos=(List<Photo>)map.get(session.getAttribute("trackId"));
	%> --%>
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
																			<div class="carousel-inner">
																		<div class="item active">
																			<div class="carousel-caption">

																				<p>Outer Image</p>
																			</div>
																			<%-- <img src="get_image?path=<%= photos.get(0).getPath().substring(0,photos.get(0).getPath().lastIndexOf("/")+1)+photos.get(0).getName() %>"
																				alt="Outer Image"> --%>
																			
																		</div>
																		<div class="item">
																			<div class="carousel-caption">

																				<p>Inner Image 01</p>
																			</div>
																			<%-- <img src="get_image?path=<%= photos.get(1).getPath().substring(0,photos.get(1).getPath().lastIndexOf("/")+1)+photos.get(1).getName() %>"
																				alt="Inner Image 01"> --%>
																			
																		</div>
																		<div class="item">
																			<div class="carousel-caption">

																				<p>Inner Image 02</p>
																			</div>
																			<%-- <img src="get_image?path=<%= photos.get(2).getPath().substring(0,photos.get(2).getPath().lastIndexOf("/")+1)+photos.get(2).getName() %>"
																				alt="Inner Image 02">
																			 --%>
																		</div>

																				<!--end carousel-inner-->
																	</div>
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
											</tr>
											

										</c:forEach>
									</tbody>
								</table>
							</div>
							</form:form>
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
	<script src="vendor/metisMenu/metisMenu.min.js"></script> -->

	<!-- DataTables JavaScript -->
	<script src="vendor/datatables/js/ColReorderWithResize.js"></script>
	<script src="vendor/datatables/js/jquery.dataTables.min.js"></script>
	<script src="vendor/datatables-plugins/dataTables.bootstrap.min.js"></script>
	<script src="vendor/datatables-responsive/dataTables.responsive.js"></script>
	<script src="js/googleapi.js"></script>

	<!-- Custom Theme JavaScript -->
	<script src="dist/js/sb-admin-2.js"></script>

	<!-- Custom JavaScript For Warehouse-->
	<script src="js/warehouse.js"></script>


	
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
    
    function editwarehouse(warehouseId){
    	
     	document.getElementById("id").value=warehouseId;
     	
     	 document.pageForm.action = "editwarehouse";
     	 document.pageForm.method = "POST";
     	 document.pageForm.submit();	  
     	
     }
    
    </script>
    
	<script type="text/javascript">
     $("#checkAll").click(function () {
        //$('input:checkbox').not(this).prop('checked', this.checked);
    	 $('input:checkbox').not("[disabled]").prop('checked', this.checked);
    });    
    </script>
    <script type="text/javascript">
                
                function validateCSV(){
                  var size=document.getElementById("size").value;
                
                  if(size!=0){
                    
                    document.pageForm.action = "downloadWarehouse";
                    document.pageForm.method = "GET";
                    document.pageForm.submit();   
                    
                  }else{
                    alert("No Content is available for Download... ");
                  }
                }
    </script>
	

</body>
</html>
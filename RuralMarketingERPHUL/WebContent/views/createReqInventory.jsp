<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Delivery And Inventory DashBoard</title>
<style>
#diveryForm input {
	border: 0;
}

.modal-dialog {
	width: 90% !important;
}

.labelFont {
	margin-left: 0px;
	margin-top: 0px;
}

.inputFont {
	width: 13%;
	padding: 12px 20px;
	margin: 8px 0;
	display: inline-block;
	border: 1px solid #ccc;
	border-radius: 4px;
	box-sizing: border-box;
}
</style>
<jsp:include page="header.jsp" />
<!-- CSS goes in the document HEAD or added to your external stylesheet -->
<%
	if (session.getAttribute("mapMenu") == null) {
		response.sendRedirect("login.jsp");
	}
%>

<script type="text/javascript">
	function getWarehouses() {
		$.ajax({
			contentType : "application/json",
			type : "POST",
			url : "loadWarehouses?city=" + $('#city').val(),
			success : function(data) {
				var warehouses = new Array();
				warehouses = data;
				var select = document.getElementById("warehouse");
				$('#warehouse option[value!="-1"]').remove();
				for (var i = 0; i < warehouses.length; i++) {
					select.options[select.options.length] = new Option(
							warehouses[i], warehouses[i]);
				}
			}
		});
	}

	function loadCities() {
		$.ajax({
			contentType : "application/json",
			type : "POST",
			url : "loadCities?state=" + $('#state').val(),
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

	function createRequest() {
		if (document.getElementById("brand").value == '-1') {
			alert('Please Select Brand.');
			return false;

		}
		if (document.getElementById("campaign").value == '') {
			alert('Please Enter Cmpaign.');
			return false;

		}
		if (document.getElementById("state").value == '-1') {
			alert('Please Select  state.');
			return false;

		}
		if (document.getElementById("warehouse").value == '-1') {
			alert('Please Select Warehouse.');
			return false;

		}
		if (document.getElementById("no_of_kits").value == '') {
			alert('Please Enter No of Kits.');
			return false;

		}
		document.deliveryForm.action = "createDeliveryAndInventory";
		document.deliveryForm.method = "POST";
		document.deliveryForm.submit();

	}
</script>
</head>


<body>
	<form:form name="deliveryForm">
	<input type="hidden" name="size" id="size" value="${fn:length(deliveryInventoryList)}"/> 
		<div id="wrapper">

			<div id="page-wrapper">
				<div class="row">
					<div class="col-lg-12">
						<h3 class="border-bottom-underline">Delivery And Inventory</h3>
						<div align="center">
							<label class="error_message">${statusMsg }</label>
						</div>
					</div>
				</div>
				<!-- /.row -->
				<div class="row">
					<div class="col-lg-12">
						<div class="panel panel-default">
						 <span
												style="float: right;margin-top: 0.3pc;"> <a onclick="validateCSV();">  <img src="dist/img/csv-64.png"
												alt="csv" height="30" width="35">
											</a>
											</span>
						<div class="panel-heading" style="padding-top: 10px;">
<b>Delivery And Inventory</b></div>								<!-- /.row -->
								<c:if
									test="${userRole == 'Business Team' || userRole == 'Admin'}">
									<div class="row">
										<div class="col-lg-12">
												<div class="panel-body" style="margin-top: 1pc;">

													<div class="row">
														<div class="col-xs-2">
															<label>Brand *</label> <select id="brand"
																class="form-control" name="brand">
																<option value="-1">-- SELECT --</option>
																<c:forEach var="brand" items="${brandMap}">
                           <option value="${brand.value}"><c:out value="${brand.value}"/></option>
                           </c:forEach>
															</select>
															<br>
															<label>Remarks </label>
															<input class="form-control" id="remarks" name="remarks"
																	type="text" value="">
															<%-- <c:if test="${empty selected_remarks}">
																<input class="form-control" id="remarks" name="remarks"
																	type="text" value="">
															</c:if>
															<c:if test="${not empty selected_remarks}">
																<input class="form-control" id="remarks" name="remarks"
																	type="text" value="${selected_remarks }">
															</c:if> --%>
														</div>
														<div class="col-xs-2">
															<label>Campaign *</label>
															<input class="form-control" id="campaign"
																	name="campaign" type="text" value="">
															<%-- <c:if test="${empty selected_compaign}">
																<input class="form-control" id="campaign"
																	name="campaign" type="text" value="">
															</c:if>
															<c:if test="${not empty selected_compaign}">
																<input class="form-control" id="campaign"
																	name="campaign" type="text"
																	value="${selected_compaign }">
															</c:if> --%>
															<br><br>
															<input type="button" class="btn btn-primary"
																onclick="createRequest()" value="Create Request">
														</div>

														<div class="col-xs-2">
															<label>State&nbsp;*</label> <select id="state"
																class="form-control" name="state"
																onChange="loadCities()">
																<option value="-1" selected>-- SELECT --</option>
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
														<div class="col-xs-2">
															<label>City&nbsp;*</label> <select id="city"
																class="form-control" name="city"
																onChange="getWarehouses()">
																<option value="-1">-- SELECT --</option>
																<c:forEach var="city" items="${cityList}">
																<option value="${city}">${city}</option>
																<%-- 	<c:choose>
																		<c:when
																			test="${not empty selected_city && selected_city eq city}">
																			<option value="${selected_city}" selected>${selected_city}</option>
																		</c:when>
																		<c:otherwise>
																			<option value="${city}">${city}</option>
																		</c:otherwise>
																	</c:choose> --%>
																</c:forEach>
															</select>
														</div>
														<div class="col-xs-2">
															<label>Warehouse&nbsp;*</label> <select id="warehouse"
																class="form-control" name="warehouse">
																<option value="-1">-- SELECT --</option>
																<c:forEach var="warehouse" items="${warehouseList}">
																	<option value="${warehouse}">${warehouse}</option>
															<%-- 		<c:choose>
																		<c:when
																			test="${not empty selected_warehouse && selected_warehouse eq warehouse}">
																			<option value="${selected_warehouse}" selected>${selected_warehouse}</option>
																		</c:when>
																		<c:otherwise>
																			<option value="${warehouse}">${warehouse}</option>
																		</c:otherwise>
																	</c:choose> --%>
																</c:forEach>
															</select>
														</div>
														<div class="col-xs-2">
															<label>No of Kits&nbsp;*</label>
															<div class="form-group">
															<input class="form-control" id="no_of_kits"
																		name="no_of_kits" type="text"
																		onkeypress='validateNum(event)'>
																<%-- <c:if test="${empty selected_no_kits}">
																	<input class="form-control" id="no_of_kits"
																		name="no_of_kits" type="text"
																		onkeypress='validateNum(event)'>
																</c:if>
																<c:if test="${not empty selected_no_kits}">
																	<input class="form-control" id="no_of_kits"
																		name="no_of_kits" value="${selected_no_kits }"
																		type="text" onkeypress='validateNum(event)'>
																</c:if> --%>

															</div>
														</div>
														<div class="col-xs-2">
															
														</div>

														<br> 
														<div class="col-xs-2">
															
														</div>
													</div>

												</div>
											</div>
										</div>
								
								</c:if>




							<div class="row" id="showAllPrePlanTable">
								<div class="col-lg-12">
									<input type="hidden" name="req_Num" id="req_Num">
										<div class="panel-body" style="margin-top: 1pc;">
											<table width="100%"
												class="table table-striped table-bordered table-hover"
												id="tablefeilds">
												<thead>
													<tr class = "tr_color">
														<th class="text-center">Req Num</th>
														<th class="text-center">Brand</th>
														<th class="text-center">Campaign</th>
														<th class="text-center">City</th>
														<th class="text-right">Warehouse</th>
														<!-- <th class="text-right">No of Items Requested</th> -->
														<th class="text-center">Date of Request</th>
														<th class="text-center">Status</th>
													</tr>
												</thead>
												<tbody>
													<c:forEach var="deliveryAndInventory"
														items="${deliveryInventoryList}">
														<tr>
															<td class="text-center"><a href = "#"  onClick = "viewInventory('${deliveryAndInventory.req_num}');">
															<c:out value="${deliveryAndInventory.req_num}"></c:out></a></td>
															<td class="text-center"><c:out
																	value="${deliveryAndInventory.brand}"></c:out></td>
															<td class="text-center"><c:out
																	value="${deliveryAndInventory.campaign}"></c:out></td>
															<td class="text-center"><c:out
																	value="${deliveryAndInventory.city}"></c:out></td>
															<td class="text-center"><c:out
																	value="${deliveryAndInventory.warehouse}"></c:out></td>
														<%-- 	<td class="text-right"><c:out
																	value="${deliveryAndInventory.no_Item_Req}"></c:out></td>--%>
															<td class="text-center">
																	<fmt:formatDate value="${deliveryAndInventory.business_Req_Date}" pattern="yyyy-MM-dd HH:mm:ss" />
																	</td>
															<td class="text-center"><c:out
																	value="${deliveryAndInventory.status}"></c:out></td>
													 </c:forEach>



												</tbody>

											</table>

										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>	</div>
				<!-- /.row -->


	</form:form>
</body>


<!-- </div> -->

<!-- /#wrapper -->

<!-- jQuery -->
<!-- <script src="vendor/jquery/jquery.min.js"></script>
 -->
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
<!-- BootBox Custom Bootstrap Modal PopUp-->
<script src="dist/js/bootbox.min.js"></script>
<script>
$('#dataTables-example').DataTable({
	scrollX : "1000px",

	scrollX : true,

	scrollCollapse : true,

	paging : true,

});
</script>
<script>
	$('.showInventory').on(
			'click',
			function() {
				alert('hi');
				// Get the record's ID via attribute
				var id = $(this).attr('data-id');
				//alert("Edit Button Id:" + id);

				$.ajax({

					contentType : "application/json",
					type : "GET",
					url : "viewInventory?req_Num=" + id,
					success : function(response) {
						//alert("Response from DB:" + response);
						// Populate the form fields with the data returned from server
						$('#diveryForm').find('[name="brand"]').val(
								response.brand).end().find('[name="campaign"]')
								.val(response.campaign).end().find(
										'[name="city"]').val(response.city)
								.end().find('[name="warehouse"]').val(
										response.warehouse).end().find(
										'[name="no_of_kits"]').val(
										response.no_of_kits).end().find(
										'[name="business_Remarks"]').val(
										response.business_Remarks).end()

					},

				});

				//Show Popup Modal
				// Show the dialog
				bootbox.dialog({
					title : 'Edit Data',
					message : $('#diveryForm'),
					show : false
				// We will show it manually later
				}).on('shown.bs.modal', function() {
					$('#diveryForm').show() // Show the  form
					.formValidation('resetForm'); // Reset form
				}).on('hide.bs.modal', function(e) {
					// Bootbox will remove the modal (including the body which contains the login form)
					// after hiding the modal
					// Therefor, we need to backup the form
					$('#diveryForm').hide().appendTo('body');
				}).modal('show');

			});
	
	
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
	
	function viewInventory(reqNum){
		document.getElementById("req_Num").value=reqNum;
		 document.deliveryForm.action = "viewInventory";
			document.deliveryForm.method = "POST";
			document.deliveryForm.submit();	  
		
	}
</script>
 <script type="text/javascript">
                
                function validateCSV(){
                  var size=document.getElementById("size").value;
                
                  if(size!=0){
                    
                    document.deliveryForm.action = "exportDeliveryAndInventories";
                    document.deliveryForm.method = "GET";
                    document.deliveryForm.submit();   
                    
                  }else{
                    alert("No Content is available for Download... ");
                  }
                }
                </script>



</html>
<%@page import="com.rural.Model.PrePlanMaster"%>
<%@page import="java.util.*"%>
<%@page import="java.text.*"%>
<%@ page errorPage="error.jsp"%>
<!DOCTYPE html>
<%@ page import="java.util.Map"%>
<%@page import="java.io.File"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html lang="en">

<head>
<title>Rural Marketing ERP</title>
<jsp:include page="header.jsp" />
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

.ui-datepicker-calendar {
	tooltip
	display: none;
}
</style>
<style type="text/css">
.dataTables_wrapper .dataTables_length {
	float: left;
}

.dataTables_wrapper .dataTables_filter {
	float: right;
	text-align: left;
}

.ui-datepicker-trigger {
	position: absolute;
    width: 30px;
    top: 0;
	
}

.error_message {
	margin-top: 0;
	margin-left: 2000;
	font-size: .70em;
	margin-bottom: 0;
	color: #FF0000;
	font-size: 1.0em;
	text-align: center;
}

.ui-widget-content a{
	color:#337ab7
}
</style>
<style type="text/css">
#myImg {
	border-radius: 5px;
	cursor: pointer;
	transition: 0.3s;
}

#myImg:hover {
	opacity: 0.7;
}

/* The Modal (background) */
.modal {
	display: none; /* Hidden by default */
	position: fixed; /* Stay in place */
	z-index: 1; /* Sit on top */
	padding-top: 100px; /* Location of the box */
	left: 0;
	top: 0;
	width: 100%; /* Full width */
	height: 100%; /* Full height */
	overflow: auto; /* Enable scroll if needed */
	background-color: rgb(0, 0, 0); /* Fallback color */
	background-color: rgba(0, 0, 0, 0.9); /* Black w/ opacity */
}


				/* tAB 3 WIDTH OF TAB;E\ */
/* Modal Content (image) */
/* .modal-content {
	margin: auto;
	display: block;
	width: 80%;
	max-width: 700px;
} */

/* Caption of Modal Image */
#caption {
	margin: auto;
	display: block;
	width: 80%;
	max-width: 700px;
	text-align: center;
	color: #ccc;
	padding: 10px 0;
	height: 150px;
}

/* Add Animation */
/* .modal-content, #caption {
	-webkit-animation-name: zoom;
	-webkit-animation-duration: 0.6s;
	animation-name: zoom;
	animation-duration: 0.6s;
}
 */
@
-webkit-keyframes zoom {
	from {-webkit-transform: scale(0)
}

to {
	-webkit-transform: scale(1)
}

}
@
keyframes zoom {
	from {transform: scale(0)
}

to {
	transform: scale(1)
}

}

/* The Close Button */
.close {
	position: absolute;
	top: 15px;
	right: 15px;
	color: #f71010;
	font-size: 40px;
	font-weight: bold;
	transition: 0.3s;
}

.close:hover, .close:focus {
	color: 	#FF0000;
	text-decoration: none;
	cursor: pointer;
}

/* 100% Image Width on Smaller Screens */
/* @media only screen and (max-width: 700px) {
	.modal-content {
		width: 100%;
	}
} */

/* Table header and body table gap */

#stack-adding-table-wrapper .dataTables_scrollHeadInner > table {
	margin-bottom: 0px;
}


</style>
<style type="text/css">
.dataTables_wrapper .dataTables_length {
	float: left;
}

.dataTables_wrapper .dataTables_filter {
	float: right;
	text-align: left;
}

input[type='number'] {
	-moz-appearance: textfield;
}

input::-webkit-outer-spin-button, input::-webkit-inner-spin-button {
	-webkit-appearance: none;
	margin: 0;
}

.multiSelect {
	display: block;
	width: 100%;
	height: 34px;
	padding: 6px 12px;
	font-size: 14px;
	line-height: 1.42857143;
	color: #555;
	background-color: #fff;
	background-image: none;
	border: 1px solid #ccc;
	border-radius: 4px;
	x
}

#room_fileds #more_fields_wrapper .dataTables_scrollHead {
	display: none;
	
}



.error_message {
	margin-top: 0;
	margin-bottom: 0;
	color: #FF0000;
}

#input_container {
	position: relative;
	padding: 0 0 0 0px;
	margin: 0 0px;
	background: #fff;
	direction: rtl;
	width: 175px;
}

#image {
	margin: 0;
	padding-right: 30px;
	width: 100%;
}

#input_img {
	position: absolute;
	bottom: 2px;
	right: 5px;
	width: 24px;
	height: 24px;
}

.pagination {
	margin-left:-110px;
}
.page-cover-wrapper {
position: absolute;
top: 0;
left: 0;
height: 100%;
display: block;
z-index: 99;
background-color: #ccc;
width: 100%;
}
#dataTables-example_filter{
    padding-bottom: 20px;
}
/* .modal-content:hover {
	transform: scale(1.5);
	/* (150% zoom - Note: if the zoom is too large, it will go outside of the viewport) */
}
 */
</style>

<script type="text/javascript">

function addInvoice() {
	/* alert( "bill number:"+document.getElementById("bill_number").value); */
	var isTrue=false;
	if (document.getElementById("bill_number").value == '') {
	isTrue=true;
	alert('Please Enter Bill Number.');
	$("#bill_number").focus();
	return false;

	}
	if (document.getElementById("rs_name").value == '') {
	isTrue=true;
	alert('Please Enter RS name');
	$("#rs_name").focus();
	return false;

	}
	if (document.getElementById("rs_code").value == '') {
	isTrue=true;
	alert('Please Enter RS CODE.');
	$("#rs_code").focus();
	return false;

	}
	if (document.getElementById("state").value == '-1') {
	isTrue=true;
	alert('Please Select State.');
	$("#state").focus();
	return false;

	}
	if (document.getElementById("city").value == '-1') {
	isTrue=true;
	alert('Please Select City.');
	$("#city").focus();
	return false;

	}
	if (document.getElementById("bill_date").value == '') {
	isTrue=true;
	alert('Please Enter Bill_date.');
	$("#bill_date").focus();
	return false;

	}
	if (document.getElementById("total_amount").value == '0') {
	isTrue=true;
	alert('Please Ente Total amount.');
	$("#total_amount").focus();
	return false;

	}
	if(document.getElementById("invoice_image").value==''){
	isTrue=true;
	alert('Please Select Invoice_image');
	$("#invoice_image").focus();
	return false;
	}
	 if(document.getElementById("rs").value=='Y' && document.getElementById("mailbox_image").value==''){
	isTrue=true;
	alert('Please Select Mailbox Image');
	$("#mailbox_image").focus();
	return false;
	} 
	 
	 var fields = $( "#bodyId input" ),
	     is_null_flg = false;
	 for( var i = 0; i < fields.length && !is_null_flg; i++ ){
		 if( $( fields[i] ).val().trim() == "" && !$( fields[i] ).is( ".case" ) && !$( fields[i] ).is( ".unit" ) ){
			 is_null_flg = true;
		 }
	 }
	 var table_trs = $( "#more_fields tbody tr" );
	 for( var i = 0; i < table_trs.length && !is_null_flg; i++ ){
		 if( $( table_trs[i] ).find( ".case" ).val().trim() == "" && $( table_trs[i] ).find( ".unit" ).val().trim() == ""  ){
			 is_null_flg = true;
		 }
	 }
	 
	 if( is_null_flg ){
		 alert( "Please fill all the required fields." );
		 return false;
	 }
	 
	
	if(isTrue==false){
		
		document.frm1.action = "createInvoice?bill="+document.getElementById("bill_number").value;
		document.frm1.method = "POST";
		document.frm1.submit();	
	}
	
}


</script>

<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
	
</head>
<body>
	<form:form name="frm1" autocomplete="off" method="post" enctype="multipart/form-data" modelAttribute="stock">
		<input type="hidden" name="size" id="size" value="${fn:length(itemList)}"/>
		
		<div id="wrapper">
			<div id="load"></div>
			<div id="contents">
			<div id="page-wrapper">
			<div class="page-cover-wrapper"></div>
				<div class="row">
					<div class="col-lg-12">
						<h3 class=" text-Left text-left border-bottom-underline">Stock</h3>
							<div class="panel-body" style="padding-left: 2px; padding-right: 2px;">
								<input type="hidden" name="billNum" id="billNum">
								<!-- Start Creating Tabs -->
								<div id="tabs">

									<ul>
									<c:if test="${userRole == 'Vendor'}">
									
										<li><a href="#tabs-1" onclick="return runMyFunction();"
											id="alpha">Create Invoice</a></li>
											</c:if>
											<c:if test="${userRole == 'Vendor' or userRole == 'Audit' or userRole == 'Business Team'}">
										<li><a href="#tabs-2" id="Beta"
											onclick="return runCSV();">View Invoice</a></li>
											</c:if>
											<c:if test="${userRole == 'Vendor' or userRole == 'Business Team'}">
											<li><a href="#tabs-3" onclick="return showStock();"
											id="gama">Show Stock</a></li></c:if>
										<div class="panel-heading">
											<span style="float: right;margin-top: -0.25pc;" class="CSV_excel"> <a onclick="validateCSV();"> <img id="CSVImage"
													src="dist/img/csv-64.png" alt="csv" height="30" width="35">
											</a>
											</span>
											
											<span style="float: right;margin-top: -0.25pc;" class="CSV_excel"> <a onclick="validateCSV1();"> <img id="downloadStocks"
													src="dist/img/csv-64.png" alt="csv" height="30" width="35">
											</a>
											</span>
											
										</div>
									</ul><c:if test="${userRole == 'Vendor'}">
									<div id="tabs-1">
										<p>
											<!-- Start of TAB 1  -->



											&nbsp; &nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp; &nbsp;
											&nbsp;&nbsp; &nbsp; &nbsp;&nbsp; &nbsp; &nbsp;&nbsp; &nbsp;
											&nbsp;&nbsp;
											<div class="field">	
										<center>
											
											<label class="error_message"><%=request.getAttribute("statusMsg1") == null ? "" : request.getAttribute("statusMsg1")%>
											</label>

										</center>
										</div>
	

										<div class="row" id="showAllPrePlanTable">
										<div class="col-lg-12" style="bottom: 60px;">
												<div class="col-xs-2">
															<!-- <label>Non RS</label> -->
															<br>
															<input type="hidden" id="rs" name="rs">
															<input type="checkbox" value="Non RS" name="NonRS" id="NonRS" style="width: 18px;height: 18px;"><b style="width: 20px;height: 20px;">&nbsp;&nbsp;&nbsp;Non RS</b>
												</div>
											
											<div class="col-lg-12">
												

													<div class="col-lg-12" style="padding-left: 0px;margin-top: 20px;">
														<div class="col-xs-2">
															<label>Bill Number&nbsp;*</label>

															<form:input class="form-control" id="bill_number"
																path="bill_number" name="bill_number"
																data-behaviour="alphanumeric" type="text" value="" />

															<br> <br>
														</div>
														<div class="col-xs-2">
															<label>RS Name&nbsp;*</label>
															<form:input class="form-control" id="rs_name"
																name="rs_name" data-behaviour="alphanumeric" type="text"
																path="rs_name" />
															<br>


														</div>
														<div class="col-xs-2">
															<label>RS Code&nbsp;*</label>
															<form:input class="form-control" id="rs_code"
																name="rs_code" data-behaviour="alphanumeric" type="text"
																path="rs_code" />
															<br>

														</div>
														<div class="col-xs-2">
															<label>State&nbsp;*</label> <select id="state"
																class="form-control" name="state"
																onclick="citySelectionStock()">
																<option disabled selected value="-1">-- Select
																	--</option>
																<c:forEach var="state" items="${stateList}">
																	<c:choose>
																		<c:when
																			test="${not empty selected_state && selected_state eq state.value}">
																			<option value="${selected_state}" selected>${selected_state}</option>
																		</c:when>
																		<c:otherwise>
																			<option value="${state}">${state}</option>
																		</c:otherwise>
																	</c:choose>
																</c:forEach>
															</select> <br>
														</div>

														<div class="col-xs-2">
															<label>City&nbsp;*</label> <select id="city"
																class="form-control" name="city">
																<option value="-1">-- Select --</option>
																<c:forEach var="city" items="${listCity}">
																	<option value="${city}"
																		<c:if test="${  selected_city.contains( city ) }">SELECTED</c:if>><c:out
																			value="${city}" /></option>
																</c:forEach>
															</select> <br>
														</div>
														<div class="col-xs-2">
															<label>Bill Date&nbsp;*</label>
															<div id="input_container">
																<input class="form-control" id="bill_date" 
																
																 style="padding-right: 89px; background-color: #fff" name="bill_date" path="bill_date" readonly="true">
																
																<script type="text/javascript">
																$( document ).ready(function(){
																	$( "#bill_date" ).datepicker({  "maxDate": new Date(),
																	showOn: "both",
																	  buttonImageOnly: true,
																	  buttonText :"",
																	  buttonImage: "dist/img/calendarBlue.png"});
																});
																</script>
															</div>
														</div>
														<br>
													</div>
													<div class="col-lg-12" style="padding-left: 0px;">
								
														<div class="col-xs-2">
															<label>Total Amount</label>
															<form:input class="form-control" id="total_amount"
																name="total_amount" data-behaviour="thousandsep"
																type="text" path="total_amount" readonly="true" />
														</div>
														<div class="col-xs-2">
															<label>Invoice Image&nbsp;*</label>
															<div>
						
																<input type="file" class="image_uploader_with_fname"
																	id="invoice_image" name="invoice_image"
																	accept="image/gif, image/jpeg, image/png" capture
																	style="display: none" /> <img
																	src="dist/img/Upload1.jpg" id="upinvoice_image"
																	style="cursor: pointer; height: 30px;" />

															</div>
														</div>
																		<div class="col-xs-2" id="mailbox" style="display: none;">
														<label>Mail Image&nbsp;*</label>
															<div>
																
																<input type="file" class="image_uploader_with_fname"
																	id="mailbox_image" name="mailbox_image"
																	accept=".msg" capture
																	style="display: none" /> <img
																	src="dist/img/Upload1.jpg" id="upmailbox_image"
																	style="cursor: pointer; height: 30px;" />

															</div>
														</div>
													</div>
													<br>
													<div id="room_fileds">

														<div class="panel-body">

															<div class="row">
																<div class="col-md-6">

																	<div class="col-md-2" style="padding: 0px 0px 0px 0px;">
																		<h4 class="text-center">
																			<b>Item List</b>
																		</h4>
																	</div>

																</div>
															</div>
															<table
																class="table table-striped table-bordered table-hover"
																style="margin-bottom: 0px" id="more_fields"
																style="border-radius: 4px;">


																<thead>
																	<tr class = "tr_color">
																		<th class="text-center" colspan="2" rowspan="2"></th>
																		<th class="text-center" rowspan="2" width="30">Item
																			Description</th>
																		<th class="text-center" colspan="3"><span
																			style="text-align: center;">Quantity</span></th>
																		<th class="text-center" rowspan="2" width="30">MRP</th>
																		<th class="text-center" rowspan="2" width="50">Net
																			Amount&nbsp;(<i class="fa fa-inr">) 
																		</th>
																	</tr>
																	<tr class = "tr_color">
																		<td class="text-center" width="20"><b>Case</b></td>
																		<td class="text-center" width="20"><b>Unit</b></td>
																		<td class="text-center" width="20"><b>Total</b></td>
																	</tr>
																</thead>
																<tbody id="bodyId">

																	<tr id="rowtoAdd"
																		style="text-align: center; padding-top: 10px; border-top: 1px solid #DCDCDC">
																		<td width="20"><img src="dist/img/plus.png"  height="20" width="20" onclick="insRow()"></td>
																		<td width="20"><img src="dist/img/delete.png" height="20" width="20" onclick="deleteRow(this)"></td>
																		<td width="30"><form:select id="itemdesc"
																				class="itemdesc form-control" name="itemId"
																				path="invoices[0].itemId">


																				<option value="-1">-- Select --</option>
																				<c:forEach var="items" items="${mapItems}">
																					<option value="${items.key}">${items.value}</option>
																				</c:forEach>
																			</form:select> <%
 	Map<Integer, String> map = (Map<Integer, String>) request.getAttribute("map");
 		String productListString = "";
 		String cumma = "";
 		for (Integer name : map.keySet()) {
 			String value = map.get(name);
 			productListString += cumma + "\"" + name + "\": \"" + value + "\"";
 			cumma = ",";

 		}
  		String brand = request.getParameter("itemdesc");
 		pageContext.setAttribute("product_info_list", "{" + productListString + "}");
 %> <script> var product_info_list = '${product_info_list}';</script></td>
																		<td><form:input size="10" type="text"
																				maxlength="4" onkeypress="return isNumber(event)"
																				id="cases" class="case" path="invoices[0].cases" /></td>
																		<td><form:input size="10" type="text"
																				maxlength="3" onkeypress="return isNumber(event)"
																				id="units" class="unit" path="invoices[0].units" /></td>
																		<td><form:input size="10" type="text"
																				style="background-color:#eee" id="totalunits"
																				class="total_units" path="invoices[0].totalunits"
																				readonly="true" /></td>
																		<td><form:input size="10" type="text"
																				style="background-color:#eee" id="Priceperunits"
																				class="Priceperunits"
																				path="invoices[0].Priceperunits" readonly="true" /></td>
																		<td><form:input size="10" type="text"
																				style="background-color:#eee" id="net_amount"
																				class="net_amount" path="invoices[0].net_amount"
																				data-behaviour="thousandsep"
																				onkeypress="return validateFloatKeyPress(this,event);"
																				readonly="true" /></td>

																	</tr>
																</tbody>
															</table>

															<br>
															<div class="col-xs-2">
																<a class="btn btn-primary" href="javascript:;"
																	onclick="addInvoice();" style="width: 130px;">
																	<font style="color: #ffffff;">Create Invoice </a>
															</div>
														</div>
													</div>

												
											</div>
										</div>



										<!--  AddTable -->
										<script src="js/addtable.js"></script> 
							
										<script
											src="vendor/datatables-plugins/dataTables.bootstrap.min.js"></script>
										<script
											src="vendor/datatables-responsive/dataTables.responsive.js"></script>


										<!-- Custom JavaScript For PrePlanning-->
										<script src="js/preplanning.js"></script>

<script>
		function isNumber(e) {
    ev = (e) ? e : window.event;
    var charCode = (e.which) ? e.which : e.keyCode;
    if (charCode > 31 && (charCode < 48 || charCode > 57)) {
        return false;
    }
    return true;
}
		/* Allow only Alphanumeric */
		 $(document ).ready( function(){
			$(document ).on( "keydown", "[data-behaviour=alphanumeric]", function(e){
				var k = e.keyCode || e.which;
				var ok = k >= 65 && k <= 90 || // A-Z
					k >= 96 && k <= 105 || // a-z
					k >= 35 && k <= 40 || // arrows
					k == 9 || //tab
					k == 46 || //del
					k == 8 || // backspaces
					(!e.shiftKey && k >= 48 && k <= 57); // only 0-9 (ignore SHIFT options)
	
				if( !ok || ( e.ctrlKey && e.altKey ) ){
					e.preventDefault();
				}
			});
			
			$(document ).on( "keydown", "[data-behaviour=thousandsep]", function(e){
				var val = $( this ).val();
				val = isNaN( val ) ? 0 : parseFloat( val ).toFixed(2).toLocaleString('en-IN');
				$( this ).val( val );
			});
				
			
			$(document ).on( "change", "#more_fields input, #more_fields select", function(e){
				recalculatetottal(  $( this ) );
			});
		 });
		
			
		 var recalculatetottal = function( _target ) {
				var curr_tr = _target.closest( "tr" ),
				curr_prod = curr_tr.find( ".itemdesc" ).val();
				curr_obj = JSON.parse( product_info_list )[curr_prod],
				p_case = isNaN( curr_tr.find( ".case" ).val() ) || curr_tr.find( ".case" ).val().trim() == "" ? 0 : parseInt(curr_tr.find( ".case" ).val()),
				p_unit = isNaN( curr_tr.find( ".unit" ).val() ) || curr_tr.find( ".unit" ).val().trim() == "" ? 0 : parseInt(curr_tr.find( ".unit" ).val());
				
				if( curr_prod != "-1" && curr_prod != "" ){
					var unit_per_case = curr_obj.split( ":" )[0],
					    price_per_unit = curr_obj.split( ":" )[1];
					curr_tr.find( ".Priceperunits" ).val(price_per_unit );
					curr_tr.find( ".total_units" ).val( ((p_case*parseInt( unit_per_case)) + p_unit) );
					
					curr_tr.find( ".net_amount" ).val(parseFloat(( ((p_case*parseInt( unit_per_case)) + p_unit)*parseFloat(price_per_unit)).toFixed(2)).toLocaleString('en-IN', { minimumFractionDigits: 2 }));
					
					
					var totalNetAmtInput = curr_tr.closest( "table" ).find(".net_amount"),
					    TotAmt = 0,
					    singleVal = $();
					    for( var j = 0; j < totalNetAmtInput.length; j++ ){
					    	singleVal = $( totalNetAmtInput[j] ).val().replace(/[,]/g,'');
					    	if( singleVal.trim() != "" && !isNaN( singleVal ) ){
					    		TotAmt += parseFloat( singleVal );
					    	}
					    }
					   
					$( "#total_amount" ).val( parseFloat( TotAmt.toFixed(2) ).toLocaleString('en-IN' , { minimumFractionDigits: 2 }) );
				}	
		}


	 $(document).ready(function() { 
		
		$("#bill_number").val("");
		$("#total_amount").val("");
	}); 
	 
	
	
 	</script>

	<script type="text/javascript">
	function validateFloatKeyPress(el, evt) {
	    var charCode = (evt.which) ? evt.which : event.keyCode;
	    var number = el.value.split('.');
	    if (charCode != 46 && charCode > 31 && (charCode < 48 || charCode > 57)) {
	        return false;
	    }
	    //just one dot
	    if(number.length>1 && charCode == 46){
	         return false;
	    }
	    //get the carat position
	    var caratPos = getSelectionStart(el);
	    var dotPos = el.value.indexOf(".");
	    if( caratPos > dotPos && dotPos>-1 && (number[1].length > 1)){
	        return false;
	    }
	    return true;
	}

	//thanks: http://javascript.nwbox.com/cursor_position/
	function getSelectionStart(o) {
		if (o.createTextRange) {
			var r = document.selection.createRange().duplicate()
			r.moveEnd('character', o.value.length)
			if (r.text == '') return o.value.length
			return o.value.lastIndexOf(r.text)
		} else return o.selectionStart
	}
	</script>

	<script type="text/javascript">
	$(document).ready(function(){
	    $("#bill_number").change(function(){
	        var bill_number = $(this).val();
	        $.ajax({
	            type: "POST",
	            url: "checkStock",
	            data: {"bill_number": bill_number},
	            success: function(msg){

	                  if(msg == 1){
	                alert("Bill Number exists !... try another");
	                  document.getElementById('bill_number').value = "";
	                  }
	            },
	            error:function(){
	            }
	        });

	    });
	});
	</script>

			<!-- Page-Level Demo Scripts - Tables - Use for reference -->
								<script type="text/javascript">
								$( document ).on( "change", ".image_uploader_with_fname", function(){
									if( $( this )[0].files.length != 0 ){
										if( $( this ).next().next().is( '.fileuploadFname' ) ){
											//alert($( this )[0].files[0].name.substr(0, 3)+'...'+$( this )[0].files[0].name.substr($( this )[0].files[0].name.lastIndexOf('.') + 1).toLowerCase() );
											$( this ).next().next().text( $( this )[0].files[0].name.substr(0, 3)+'...'+$( this )[0].files[0].name.substr($( this )[0].files[0].name.lastIndexOf('.') + 1).toLowerCase()  );
										} else {
											$( this ).next().after( '<label class="fileuploadFname">'+$( this )[0].files[0].name.substr(0, 3)+'...'+$( this )[0].files[0].name.substr($( this )[0].files[0].name.lastIndexOf('.') + 1).toLowerCase() +'</label>' );
										}
									} else {
										if( $( this ).next().next().is( ".fileuploadFname" ) ){
											$( this ).next().next().remove();
										}
									}
								});
			</script>

										<script type="text/javascript">
	    $(document).ready(function() {
	    	
			$('#more_fields').DataTable( {
	        	
	        	scrollX		:	true,
	           	paging		:	false,
	            "autoWidth"	:	false,
	            "scrollCollapse": true,
		        "paging"		: false,
				"ordering" 		: false,
				stateSave		: true,
				"searching"	:	false,
				 "bInfo" : false
	        	});
			
			
	    });
		
	$("#upinvoice_image").click(function () {
	    $("#invoice_image").trigger('click');
	});
	
  $("#upmailbox_image").click(function () {
		    $("#mailbox_image").trigger('click');
		});
	
			function citySelectionStock() {
				
				$.ajax({
					contentType : "application/json",
					type : "POST",
					url : "getCitiesForStock?state=" + $('#state').val(),
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
	$(document).ready(function () {
	    $(document).on('click', '.refresher', function () {
	        $.ajax({
	            url: 'ajax.php',
	            method: get,
	            dataType: 'json',
	            success: function(response) {
	                $('#table-to-refresh').html(response);
	            }
	        });
	    });
	});
	
/*	function showCalender() {

		//JS for Calender
		$('.date-picker')
				.datepicker(
						{
							dateFormat : "mm/yy",
								changeMonth : true,
							changeYear : true,
							showButtonPanel : true,
							onClose : function(dateText, inst) {

								function isDonePressed() {
									return ($('#ui-datepicker-div')
											.html()
											.indexOf(
													'ui-datepicker-close ui-state-default ui-priority-primary ui-corner-all ui-state-hover') > -1);
								}

								if (isDonePressed()) {
									var month = $(
											"#ui-datepicker-div .ui-datepicker-month :selected")
											.val();
									var year = $(
											"#ui-datepicker-div .ui-datepicker-year :selected")
											.val();
									$(this).datepicker('setDate',
											new Date(year, month, 1))
											.trigger('change');

									$('.date-picker').focusout()//Added to remove focus from datepicker input box on selecting date
								}
							},
							beforeShow : function(input, inst) {

								inst.dpDiv
										.addClass('month_year_datepicker')

								if ((datestr = $(this).val()).length > 0) {
									year = datestr.substring(
											datestr.length - 4,
											datestr.length);
									month = datestr.substring(0, 2);
									$(this).datepicker('option',
											'defaultDate',
											new Date(year, month - 1, 1));
									$(this).datepicker('setDate',
											new Date(year, month - 1, 1));
									$(".ui-datepicker-calendar").hide();
								}
							}
						})

	}*/
</script>



									</div></div></c:if>
											
									<div id="tabs-2">

										<p>
											<!-- Start of TAB 2  -->
										<div id="stack-adding-table-wrapper" class="panel-body">

											<table
												class="table table-striped table-bordered table-hover" 
												id="dataTables-example">
												<thead>
													<tr class = "tr_color">
														<th class="text-center">Bill Number</th>
														<c:if test="${userRole == 'Audit'}">
														<th class="text-center">Vendor Name</th>
														</c:if>
														<th class="text-center">Non-RS/RS</th>
														<th class="text-center">RS Code</th>
														<th class="text-center">RS Name</th>
														<th class="text-center">State</th>
														<th class="text-center">City</th>
														<th class="text-center">Bill Date</th>
														<th class="text-center">Total Amount</th>
														<th class="text-center">Invoice Images</th>
														<th class="text-center">Status</th>
													</tr>
												</thead>
												<tbody>
													<%
														int count = 1;
													%>
													<c:forEach var="Stocklist" items="${itemList}">

														<%
																	if (count / 2 == 0) {
														%>
														<c:if test="${Stocklist.status=='12'}">
															<tr class="even gradeC" style="background-color:#ffffcc"></c:if>
			<c:if test="${Stocklist.status=='13'}">
															<tr class="even gradeC" style="background-color:#e3fbe3"></c:if>
															<c:if test="${Stocklist.status=='14'}">
															<tr class="even gradeC" style="background-color:#ffe6e6"></c:if>
															
															<td class="text-center">
															<%-- <a style="color: blue;"
																href="viewInvoiceDetails?bill_number=<c:out value="${Stocklist.bill_number}"/>">
																	<c:out value="${Stocklist.bill_number}" />
															</a>  --%>
															
															<a  href = "#" onClick = "viewInvoiceDetails('${Stocklist.bill_number}');" ><c:out
																		value="${Stocklist.bill_number}"></c:out></a>
															
															<input type="hidden" name="bill_number" id="bill_number"
																value="${Stocklist.bill_number}" /></td>

													<c:if test="${userRole == 'Audit'}">
															<td class="text-left"><c:out
																	value="${nameList[Stocklist.venId]}"></c:out></td>
																	</c:if>
																	<td class="text-left"><c:out
																	value="${Stocklist.non_rs}"></c:out></td>
														
															<td class="text-left"><c:out
																	value="${Stocklist.rs_code}"></c:out></td>
															<td class="text-left"><c:out
																	value="${Stocklist.rs_name}"></c:out></td>
															<td class="text-left"><c:out
																	value="${Stocklist.state}"></c:out></td>
															<td class="text-left"><c:out
																	value="${Stocklist.city}"></c:out></td>
															<td class="text-center"><c:out
																	value="${Stocklist.bill_date}"></c:out></td>
																
															<td class="text-right  change_to_thousand_seprator" id="total_amount"><c:out
																	value="${Stocklist.total_amount}"></c:out></td>

															<%-- <td><img src="get_image?path=${Stocklist.invoice_images}"
															height="55" width="55"></img></td> --%>
															<td class="text-center"><img
																class="singleImageShower"
																src="get_image?path=${Stocklist.invoice_path}"
																height="25" width="25"></img>
																<div id="myModal" class="modal">
																	<span class="close">&times;</span> <img
																		class="modal-content" id="img01">
																	<div id="caption"></div>
																</div></td>
															<td class="text-left"><c:out
																	value="${statusMap[Stocklist.status]}"></c:out></td>
																	
														</tr>
														<%
															} else {
														%>
														<tr class="odd gradeX">


															<td class="text-center">
															
																<a  href = "#" onClick = "viewInvoiceDetails('${Stocklist.bill_number}');" ><c:out
																		value="${Stocklist.bill_number}"></c:out></a> <%-- <c:set var = "warehouseName" scope = "session" value="${itemList.warehouseName}"></c:set> --%>


																<input type="hidden" color="#337ab7" name="bill_number"
																id="bill_number" value="${Stocklist.bill_number}" /></td>

															<%-- <td class="text-center"><c:out
																value="${Stocklist.bill_number}"></c:out></td> --%>

														<c:if test="${userRole == 'Audit'}">
															<td class="text-left"><c:out
																	value="${nameList[Stocklist.venId]}"></c:out></td>
																	</c:if>
														<td class="text-center"><c:out
																	value="${Stocklist.non_rs}"></c:out></td>
															<td class="text-center"><c:out
																	value="${Stocklist.rs_code}"></c:out></td>
															<td class="text-right"><c:out
																	value="${Stocklist.rs_name}"></c:out></td>
															<td class="text-right"><c:out
																	value="${Stocklist.state}"></c:out></td>
															<td class="text-right"><c:out
																	value="${Stocklist.city}"></c:out></td>
															<td class="text-right"><c:out
																	value="${Stocklist.bill_date}"></c:out></td>
															<td class="text-right" id="total_amount"><c:out
																	value="${Stocklist.total_amount}"></c:out></td>
															<%-- <td><img src="get_image?path=${Stocklist.invoice_images}"
															height="55" width="55"></img></td> --%>
															<c:set var="img_path" value="${Stocklist.invoice_path}" />

															<%
																if (pageContext.getAttribute("img_path") != null) {
																				String path = (String) pageContext.getAttribute("img_path");
																				File actual1 = new File(path);
																				File file = new File(path);
																				int fileLen = file.listFiles().length;
																				int it1 = 0;
																				String[] logFile = new String[fileLen];
																				for (File f : actual1.listFiles()) {
																					logFile[0] = path + "/" + f.getName();

																					it1++;
																				}

															%>

															<td><img width="30" height="30"
																class="singleImageShower"
																src="get_image?path=<%=logFile[0]%>" alt="Forest">
															</td>
															<td><img
																src="get_image?path=${Stocklist.invoice_path}"
																height="25" width="25"></img>
																<div id="myModal" class="modal">
																	<span class="close">&times;</span> <img
																		class="modal-content" id="img01">
																	<div id="caption"></div>
																</div></td>
																<td class="text-right" id="total_amount"><c:out
																	value="${statusMap[Stocklist.status]}"></c:out></td>
																	<c:if test="${userRole == 'Audit'}">
																<td class="text-left"><c:out
																	value="${nameList[Stocklist.venId]}"></c:out></td>
																	</c:if>
															<%
																} else {
															%>
															<td></td>
															<%
																}
															%>

														</tr>
														<%
															}
														%>


													</c:forEach>





												</tbody>
											</table>
										</div>

									</div>
									<!-- End of Tab 2  -->
									<c:if test="${userRole == 'Vendor' or userRole == 'Business Team'}">
									<div id="tabs-3">

										<p>
											<!-- Start of TAB 3  -->
										<c:if test="${userRole == 'Business Team'}">
												<div class="col-xs-2">	
												<label>User&nbsp;*</label> 
												<select class="form-control" id="user" name="user" style="width: auto;margin-left: -1pc;">
												<option value="-1" selected>-- SELECT --</option>
													<c:forEach var="user" items="${listUsers}">
                      								   <option value="${user}"><c:out value="${nameList[user]}"/></option>
													</c:forEach>
												</select>
												</div></c:if>
												<div class="col-xs-2">
														<label for="startDate" style="margin-left: -1pc;">Date&nbsp;*</label>
   														 <input class="" id="startDate" type="text" 
   														 style="padding-right: 20px; background-color: #fff;margin-left: -1pc;" name="startDate" >
														
															</div>
													<c:if test="${userRole == 'Business Team'}">
												<div class="col-xs-2">
												
											<a class="btn btn-primary" href="javascript:;" id="getUserSKUDetails" 
											style="margin-left: 4pc;margin-top: 24px;">Get SKUs </a>
											</div></c:if>
											
											<c:if test="${userRole == 'Vendor'}">
											
												<div class="col-xs-2">
											<a class="btn btn-primary" href="javascript:;" id="getUserSKUDetailsForVendor" 
											style="margin-left: 4pc;margin-top: 24px;">Get SKUs </a>
											</div></c:if>
											<br><br>
											<input type="hidden" name="size1" id="size1"/> 
											<table class="table table-striped table-bordered table-hover" id="skuTable" style="margin-top: 2pc;">
												<thead>
													<tr class = "tr_color">
														<th class="text-center">SKU</th>
														<th class="text-center">No of Units</th>
														

													</tr>
												</thead>
												<tbody>
												</tbody>
											</table></div>
										</c:if>
													
										
										</div>

									</div>
								</div>
								<!-- End of tabs -->

							</div>
							<!-- End Creating Tabs -->
						
					</div>
					<!-- End of Panel Default -->
				</div>
				</div>
				<!-- End of Col-lg-12 -->
			<!-- End of Row -->

		<!-- /#page-wrapper -->


		<!-- jQuery -->
		<script src="vendor/jquery/jquery.min.js"></script>
		<script src="vendor/jquery/jquery-ui.js"></script>

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
		<!-- Page-Level Demo Scripts - Tables - Use for reference -->
		

	</form:form>

	<script type="text/javascript">
		$(function() {
			$("#tabs").tabs();
		});
		$(document ).ready(function() { 
			
			var change_to_thousand_seprator = $( ".change_to_thousand_seprator" );
			var elem = $();
			for( var i = 0; i < change_to_thousand_seprator.length; i++ ){
				var elem = $( change_to_thousand_seprator[i] );
				
				elem.html( parseFloat( parseFloat( elem.text().trim() ).toFixed(2) ).toLocaleString('en-IN' , { minimumFractionDigits: 2 }) );
			}
			
		});
		// India uses thousands/lakh/crore separators
	
	</script>



	<script type="text/javascript">

$(document ).ready(function() { 
	$( ".ui-tabs-active a" ).click();
	$('#CSVImage').hide();
	
	
});

function runMyFunction()
{
 $('#CSVImage').hide();
 $('#downloadStocks').hide();
}
function showStock()
{
 $('#CSVImage').hide();
 $('#downloadStocks').show();
 
}
function runCSV()
{
 $('#CSVImage').show();
 $('#downloadStocks').hide();
 
}
$(document).ready(function(){
	 $('input[type="checkbox"]').click(function(){
		 var target  = $(this);
	        if(target.prop("checked") == true){
	        	document.getElementById("rs").value='Y';
	        	document.getElementById("mailbox").style.display = "block";
	        }
	 });
});
$(document).ready(function(){
	 $('input[type="checkbox"]').click(function(){
		 var target  = $(this);
	        if(target.prop("checked") == false){
	        	document.getElementById("rs").value='N';
	        	document.getElementById("mailbox").style.display = "none";
	        }
	 });
});

$(document).ready(function(){
    
    
    $('#getUserSKUDetailsForVendor').click(function(){
    	 if(document.getElementById("startDate").value==''){
    		alert("Please Select Calendar.")
    		return false;

    	}
    	else{
    		
    		jQuery.ajax({
    			type : "POST",
    			url: "getUserSKUDetailsForVendor?date="+ $("#startDate").val(),
    			success: function (data) {
    				var sks = new Array();
    				sks = data;
    				
    			/* 	$("#skuTable tr").remove(); */
    			if(sks.length==0)
    				{
    				document.getElementById("size1").value=0;
    				alert('There are no SKUs available for the Selected Combination');
    				}
    			document.getElementById("size1").value=1;
    			$('#skuTable tbody > tr').remove();
    			 	for (var i = 0; i < sks.length; i++) {
    			 		 var ArrNames = sks[i].split(':');
        				 var sku = ArrNames[0];
        				 var units = ArrNames[1];
        				 var row =("<tr><td>"+sku+"</td><td>"+units+"</td></tr>");
        				 $("#skuTable").append(row);
        				 }
    				
    			    	        },
    	        error: function (data) {
    	            alert(data + "error");
    	        }});
    			
    	}
    });
});    


$(document).ready(function(){
    
    
    $('#getUserSKUDetails').click(function(){
    	if(document.getElementById("user").value=='-1'){
    		alert("Please Select User.")
    		return false;

    	}
    	else if(document.getElementById("startDate").value==''){
    		alert("Please Select Calendar.")
    		return false;

    	}
    	
    	else{
    		
    		jQuery.ajax({
    			type : "POST",
    			url: "getUserSKUDetails?user=" + $("#user").val()+"&date="+ $("#startDate").val(),
    			success: function (data) {
    				var sks = new Array();
    				sks = data;
    				if(sks.length==0)
    				{
    					
    				alert('There are no SKUs available for the Selected Combination');
    				}
    				
    			/* 	$("#skuTable tr").remove(); */
    			$('#skuTable tbody > tr').remove();
    			
    			 	for (var i = 0; i < sks.length; i++) {
    			 		 var ArrNames = sks[i].split(':');
        				 var sku = ArrNames[0];
        				 var units = ArrNames[1];
        				 var row =("<tr><td>"+sku+"</td><td>"+units+"</td></tr>");
        				 $("#skuTable").append(row);
        				 }
    				
    			    	        },
    	        error: function (data) {
    	            alert(data + "error");
    	        }});
    			
    	}
    });
});    



</script>
<script type="text/javascript">
document.onreadystatechange = function () {
var state = document.readyState
if (state == 'interactive') {
document.getElementById('contents').style.visibility="hidden";
} else if (state == 'complete') {
setTimeout(function(){
document.getElementById('interactive');
document.getElementById('load').style.visibility="hidden";
document.getElementsByClassName('page-cover-wrapper')[0].style.display = "none";
document.getElementById('contents').style.visibility="visible";
},1000);
}
}
</script>

<script type="text/javascript">
$( document ).ready(function(){
	$('#startDate').datepicker({
		dateFormat: 'MM yy',
        changeMonth: true,
        changeYear: true,
        showButtonPanel: true,
        beforeShow : function(input, inst) {
         	$( ".ui-datepicker-calendar"  ).hide();
        },
        onClose: function(dateText, inst) { 
            var month = $("#ui-datepicker-div .ui-datepicker-month :selected").val();
            var year = $("#ui-datepicker-div .ui-datepicker-year :selected").val();
            $(this).datepicker('setDate', new Date(year, month, 1)).trigger('change');
        }

	});
	
	$('#startDate').focus(function(){ $(".ui-datepicker-calendar").hide();});
});




function viewInvoiceDetails(billNum){
	document.getElementById("billNum").value=billNum;
	 document.frm1.action = "viewInvoiceDetails";
		document.frm1.method = "POST";
		document.frm1.submit();	  
	
}

    </script>
    <script type="text/javascript">
                
                function validateCSV(){
                  var size=document.getElementById("size").value;
    
                  if(size!=0){
                    
                    document.frm1.action = "downloadStock";
                    document.frm1.method = "GET";
                    document.frm1.submit();   
                    
                  }else{
                    alert("No Content is available for Download... ");
                  }
                }
                </script>
                <script type="text/javascript">
                
                function validateCSV1(){
                  var size=document.getElementById("size1").value;
                  if(size!=0){
                    document.frm1.action = "downloadSKUList";
                    document.frm1.method = "POST";
                    document.frm1.submit();   
                    
                  }else{
                    alert("No Content is available for Download... ");
                  }
                }
                </script>
                

</body>


</html>

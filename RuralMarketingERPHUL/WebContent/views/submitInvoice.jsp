<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html lang="en">
<title>Create Invoice</title>
<head>
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
input[type='number'] {
	-moz-appearance: textfield;
}

input::-webkit-outer-spin-button, input::-webkit-inner-spin-button {
	-webkit-appearance: none;
	margin: 0;
}
</style>
<style type="text/css">
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
.error_message {
	margin-top: 0;
	margin-bottom: 0;
	color: #FF0000;
}
</style>
<!-- <style type="text/css">
canvas {
    max-height: 30px;
    max-width: 30px;
}
</style> -->
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
<style> </style>
<script type="text/javascript">

function addInvoice()
{
	
	var isTrue=false;
	if (document.getElementById("bill_number").value == '') {
		isTrue=true;
		
		alert('Please Enter Bill Number.');
		return false;

	}
	if (document.getElementById("rs_name").value == '') {
		isTrue=true;
		alert('Please Enter RS name');
		return false;

	}
	if (document.getElementById("rs_code").value == '') {
		isTrue=true;
		alert('Please Enter RS CODE.');
		return false;

	}
	if (document.getElementById("state").value == '-1') {
		isTrue=true;
		alert('Please Select State.');
		return false;

	}
	if (document.getElementById("city").value == '-1') {
		isTrue=true;
		alert('Please Select City.');
		return false;

	}
	if (document.getElementById("bill_date").value == '') {
		isTrue=true;
		alert('Please Enter Bill_date.');
		return false;

	}
	if (document.getElementById("total_amount").value == '0') {
		isTrue=true;
		alert('Please Ente Total amount.');
		return false;

	}

	if (document.getElementById("mrp").value == '' || document.getElementById("quantity").value=='' || document.getElementById("baserate").value =='' 
			|| document.getElementById("discount").value ==''  || document.getElementById("purchaserate").value =='' 
			|| document.getElementById("tax").value =='' || document.getElementById("netamount").value =='' ||document.getElementById("itemdesc").value =='' ) {
		isTrue=true;
		alert('Please Enter Line Items');
		return false;

	}
	
	 if(document.getElementById("invoice_image").value==''){
		 isTrue=true;
			alert('Please Select File');
			return false;
		}
	if(isTrue==false){
	document.frm1.action = "createInvoice";
	document.frm1.method = "POST";
	document.frm1.submit();	
	}
	
}


</script>

<link href="vendor/datatables/css/buttons.dataTables.min.css"
	rel="stylesheet">
<link href="vendor/datatables/css/multi.select.css" rel="stylesheet">
<link rel="stylesheet" href="vendor/multiple-select.css" />
<link rel="stylesheet" href="vendor/multi.select.js" />
<link rel="stylesheet" href="vendor/jquery/jquery-1.12.4.min.js" />

<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
  <link rel="stylesheet" href="/resources/demos/style.css">
  <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
  <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<!-- <script>
  $( function() {
    $( "#bill_date" ).datepicker();
  } );
  </script> -->
</head>
<body>
	<form:form name="frm1" autocomplete="off"  method="post" enctype="multipart/form-data" modelAttribute="stock">
		<div id="wrapper">
			<div id="page-wrapper">
				<div class="row">
					<div class="col-lg-12">
						<h3 class="text-center">Add Invoice</h3>
					</div>
				</div>
				
				
				<div class="row" id="showAllPrePlanTable">
					<div class="col-lg-12">
						<div class="panel panel-default">
							<div class="panel-body">
							<div align="center">
					<label class="error_message" id="prePlanCreatedMsg">${msg }</label>
				</div>
								<div class="col-lg-12">	
									<div class="col-xs-2">
										<label>Bill Number</label>
									
    									<form:input class="form-control" id="bill_number" path="bill_number" name="bill_number" 
    									type="text" value=" " onkeypress='validateNum(event)' />
    									<%-- <form:errors path="bill_number" cssClass="error" /> --%>
										<br>
										
										
									
									<br> 
									</div>
									<div class="col-xs-2">
										<label>RS Name</label>
											<form:input class="form-control" id="rs_name" name="rs_name" 
											type="text" path="rs_name" />
									<br>
									
										
									</div>
									<div class="col-xs-2">
								 		<label>RS Code</label>
											<form:input class="form-control" id="rs_code" name="rs_code" 
											type="text" path="rs_code" />
									<br>
									
									</div>
									<div class="col-xs-2">
										<label>State&nbsp;*</label>
										<select id="state" class="form-control" name="state" onclick="citySelection()" > 
											<option disabled selected value="-1">-- Select --</option>
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
										</select>
										<br>
									</div>
									
									<div class="col-xs-2">
										<label>City&nbsp;*</label>
										<select id="city" class="form-control" name="city" > 
											<option  value="-1">-- Select --</option>
											<c:forEach var="city" items="${listCity}">
												<option value="${city}"
													<c:if test="${  selected_city.contains( city ) }">SELECTED</c:if>><c:out
														value="${city}" /></option>
											</c:forEach>
										</select>
										<br>
									</div>
									
									<%-- <div class="col-xs-2">
										
										<label>City&nbsp;*</label>
									<div class="col-xs-14">
										<select id="city" class="form-control" name="city" size="6">
											
											<c:forEach var="city" items="${listCity}">
												<option value="${city}"
													<c:if test="${  selected_city.contains( city ) }">SELECTED</c:if>><c:out
														value="${city}" /></option>
											</c:forEach>
										</select>
									</div>
									</div> --%>
									<br>
								</div>
								
								<div class="col-lg-12">	
								<div class="col-xs-2">
								<label>Bill Date</label>
										
											 <%--  <form: input type="date" name="bill_date"/> --%>
										<%-- <form:input class="form-control" id="bill_date" name="bill_date" path="bill_date"
											type="text"  /> --%>
											
										<input class="form-control" id="bill_date" name="bill_date" path="bill_date"
											type="text"  />
											<script type="text/javascript">
											$( "#bill_date" ).datepicker({  "maxDate": new Date() });
											</script>
								</div>
											
								<div class="col-xs-2">
											<label>Total Amount</label>
											<form:input class="form-control" id="total_amount" name="total_amount" 
											type="text" path="total_amount" readonly="true" />
								</div>
									<div class="col-xs-2">
									<label>Invoice Image</label>
										<div>
											 <%-- <form:input type="file" class="hide_file" path=""/> --%>
						 <input type="file" class="image_uploader_with_fname" id="invoice_image" name="invoice_image" accept="image/gif, image/jpeg, image/png" capture style="display:none"/>
<img  src="dist/img/Upload1.jpg" id="upinvoice_image" style="cursor:pointer;height: 30px;" />
											 
										</div>
								</div>	
								</div>		
											
						 <div id="room_fileds">
          					
           						<div class="panel-body">
								<br>
									<div class="row">
										<div class="col-md-6">
											
											<div class="col-md-2" style="padding:0px 0px 0px 0px;">
											<p style="font-size:18px;padding:20px 0px 0px 0px;">Item List:</p>
											</div>
											
										</div>
									</div>
								
								
								<div id="POItablediv">
  								<table width="100%" class="table table-striped table-bordered table-hover"
									 style="margin-bottom: 0px" id="more_fields" style="width:48px;" style= "border-radius: 4px;" >
									
  								<!-- <table id="POITable" border="1"> -->
								 <thead>
								 
								    <tr>
								     		<th></th>
											<th></th>
											<th class="text-center">Item Description</th>
											<th class="text-center">MRP <i class="fa fa-inr"></i></th>
											<th class="text-center">Quantity <i class="fa fa-inr"></i></th>
											<th class="text-center">Base Rate <i class="fa fa-inr"></i></th>
											<th class="text-center">Discount <i class="fa fa-inr"></i></th>
											<th class="text-center">Purchase Rate <i class="fa fa-inr"></i></th>
											<th class="text-center">Tax <i class="fa fa-inr"></i></th>
											<th class="text-center">Net Amount <i class="fa fa-inr"></i></th>
				
								    </tr>
								    </thead>
								    <tbody id="bodyId" >
								    
								    <tr id="rowtoAdd" style="text-align:center;padding-top:10px; border-top: 1px solid #DCDCDC ">							      
								      <td ><img src="dist/img/plus.png" height="20" width="20" onclick="insRow()"></td>
								      <td ><img src="dist/img/delete.png" height="20" width="20" onclick="deleteRow(this)"></td>
									  <td ><form:input size="15"  type="text" id="itemdesc" path="invoices[0].item_description"/></td>
									  <td ><form:input size="5"  type="text" id="mrp" onkeypress='validateNum(event)' path="invoices[0].mrp_rate"/></td>
  									  <td ><form:input size="5" type="text"  id="quantity" onkeypress='validateNum(event)' path="invoices[0].quantity"/></td>
  									  <td ><form:input size="5"  type="text" id="baserate" onkeypress='validateNum(event)' path="invoices[0].base_rate"/></td>
  									  <td ><form:input size="5" type="text"  id="discount" onkeypress='validateNum(event)' path="invoices[0].discount"/></td>
  									  <td ><form:input size="5"  type="text" id="purchaserate" onkeypress='validateNum(event)' path="invoices[0].purchase_rate"/></td>
  									  <td ><form:input size="5" type="text"  id="tax" onkeypress='validateNum(event)' path="invoices[0].tax"/></td>   
  									  <td ><form:input size="5"  type="text" id="mrp-total" path="invoices[0].net_amount" readonly="true" /></td>
  									  
								    </tr>
								    </tbody>
								  </table>
								 
  									</div>
  									<br>
  									<div class="col-xs-2">
								  <a class="btn btn-primary" href="javascript:;"
														onclick="addInvoice();" style="width: 130px;">Create Invoice </a>
<%-- 								  <form:button  class="btn btn-primary"  type = "submit" value = "Submit" onsubmit="addInvoice()">Submit</form:button>
 --%>							</div>
									 </div>
          							</div>
								</div>
							</div>
						</div>
					</div>	
				</div>
			<!-- /#page-wrapper -->
			</div>
		<!-- /#wrapper -->
		
		
		<!--  AddTable -->
		<script src="js/addtable.js"></script>
		<!-- <!--  StockValidator -->
		<script src="js/stockvalidator.js"></script> -->
		
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
		<script src="dist/css/jquery-ui.theme.min.css"></script>
		

		<!-- DataTables JavaScript -->
		<script src="vendor/datatables/js/jquery.dataTables.min.js"></script>
		<script src="vendor/datatables/js/buttons.flash.min.js"></script>
		<script src="vendor/datatables/js/buttons.html5.min.js"></script>
		<script src="vendor/datatables/js/buttons.print.min.js"></script>
		<script src="vendor/datatables/js/dataTables.buttons.min.js"></script>
		<script src="vendor/datatables/js/jszip.min.js"></script>
		<script src="vendor/datatables/js/pdfmake.min.js"></script>
		<script src="vendor/datatables/js/vfs_fonts.js"></script>


		<script src="vendor/datatables-plugins/dataTables.bootstrap.min.js"></script>
		<script src="vendor/datatables-responsive/dataTables.responsive.js"></script>
		

		<!-- Custom JavaScript For PrePlanning-->
		<script src="js/preplanning.js"></script>
<script>
function calculate(_td) {
	var a = null, b = null;
	a = parseInt(_td.val());
    if (a >= 0) {
    	if(_td.attr("id") === "mrp") {
    		b = parseFloat(_td.parent().next().find("input").val());
    	} else {
    		b = parseFloat(_td.parent().prev().find("input").val());
    	}
    } 
    var total = 0.0, 
    	stotal = 0.0,
    	totTd = _td.parent().parent().find("td:last").find("input");
    if(a !== null && b !== null) {
    	if (!isNaN(a * b)) {
    		totTd.val( (a * b).toFixed(2) );
    		
    		$("#more_fields>tbody>tr").each(function() {
    			stotal = parseFloat($(this).find("td:last").find("input").val());
    			if (!isNaN(stotal)) {
    				total = total + stotal;
    			}
    			$("#total_amount").val(total.toFixed(2));
    		});
    		
    	} else {
    		totTd.val("");
    	}    	
    } else {
    	totTd.val("");
    }
}


$(document).ready(function() { 
	
	$("#bill_number").val("");
	$("#total_amount").val("");
	
	$(document).on("blur", "#more_fields>tbody>tr>td:nth-child(4)", function(e) {
		calculate($(this).find("input"));
	});

	$(document).on("blur", "#more_fields>tbody>tr>td:nth-child(5)", function(e) {
		calculate($(this).find("input"));
	});
});

</script>
<!-- <script type="text/javascript">		
 function addInvoice()
{
alert(document.getElementById("total_amount").value);
	var isValueEmpty =false;
	if (document.getElementById("bill_number").value == '0') {
		isValueEmpty =true;
		alert('Please Enter Bill Number.');
		return false;

	}
	if (document.getElementById("rs_name").value == '') {
		isValueEmpty =true;
		alert('Please Enter RS name');
		return false;

	}
	if (document.getElementById("rs_code").value == '') {
		isValueEmpty =true;
		alert('Please Enter RS CODE.');
		return false;

	}
	if (document.getElementById("state").value == '-1') {
		isValueEmpty =true;
		alert('Please Select State.');
		return false;

	}
	if (document.getElementById("city").value == '-1') {
		isValueEmpty =true;
		alert('Please Select City.');
		return false;

	}
	if (document.getElementById("bill_date").value == '') {
		isValueEmpty =true;
		alert('Please Ente Bill Date.');
		return false;

	}
	if (document.getElementById("total_amount").value == '0') {
		isValueEmpty =true;
		alert('Please Ente Total amount.');
		return false;

	}
	document.frm1.action = "createInvoice";
	document.frm1.method = "POST";
	document.frm1.submit();	
	
}
	function addInvoice()
{
alert(document.getElementById("total_amount").value);
	boolean isValueEmpty =false;
	if (document.getElementById("bill_number").value == '0') {
		isValueEmpty =true;
		alert('Please Enter Bill Number.');
		return false;

	}
	if (document.getElementById("rs_name").value == '') {
		isValueEmpty =true;
		alert('Please Enter RS name');
		return false;

	}
	if (document.getElementById("rs_code").value == '') {
		isValueEmpty =true;
		alert('Please Enter RS CODE.');
		return false;

	}
	if (document.getElementById("state").value == '-1') {
		isValueEmpty =true;
		alert('Please Select State.');
		return false;

	}
	if (document.getElementById("city").value == '-1') {
		isValueEmpty =true;
		alert('Please Select City.');
		return false;

	}
	if (document.getElementById("bill_date").value == '') {
		isValueEmpty =true;
		alert('Please Ente Bill Date.');
		return false;

	}
	if (document.getElementById("total_amount").value == '0') {
		isValueEmpty =true;
		alert('Please Ente Total amount.');
		return false;

	}
	document.frm1.action = "createInvoice";
	document.frm1.method = "POST";
	document.frm1.submit();	
	
}
		
</script> -->		
<script type="text/javascript">
$(document).ready(function(){
    $("#bill_number").change(function(){
        var bill_number = $(this).val();
        $.ajax({
            type: "POST",
            url: "checkStock",
            data: {"bill_number": bill_number},
            success: function(msg){

                  if(msg == 0)
                    alert("OK");
                  else
                    alert("Bill Number exists !... try another");
                  document.getElementById('bill_number').value = "";
            },
            error:function(){
</script>		
		
		<!-- Page-Level Demo Scripts - Tables - Use for reference -->
		<script type="text/javascript">
		$( document ).on( "change", ".image_uploader_with_fname", function(){
			if( $( this )[0].files.length != 0 ){
			if( $( this ).next().next().is( '.fileuploadFname' ) ){
			$( this ).next().next().text( $( this )[0].files[0].name );
			} else {
			$( this ).next().after( '<label class="fileuploadFname">'+$( this )[0].files[0].name+'</label>' );
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
		</script>
		<script type="text/javascript">
$("#upinvoice_image").click(function () {
    $("#invoice_image").trigger('click');
});

</script>
		<script type="text/javascript">
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
function citySelection() {
	
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

</script>
	
		<!--  <script>
    $(document).ready(function() {
        $('#dataTables-example').DataTable({
            responsive: true,
            });
        /* resetPage(); */
        
    });
    </script>
 -->
	</form:form>

</body>

</html>

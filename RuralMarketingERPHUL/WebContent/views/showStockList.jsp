<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@page import="java.io.File"%>
	<%@ page import="java.util.Map" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>View Stock Details</title>
<jsp:include page="header.jsp" />

<style type="text/css">
.dataTables_wrapper .dataTables_length {
	float: left;
}

.dataTables_wrapper .dataTables_filter {
	float: right;
	text-align: left;
}
/* 100% Image Width on Smaller Screens */
@media only screen and (max-width: 700px) {
	.modal-content {
		width: 100%;
	}
}
/* Modal Content (image) */
.modal-content {
	margin: auto;
	display: block;
	width: 80%;
	max-width: 600px;
}
/* Add Animation */
.modal-content, #caption {
	-webkit-animation-name: zoom;
	-webkit-animation-duration: 0.6s;
	animation-name: zoom; 
	animation-duration: 0.6s;
}
.error_message {
	margin-top: 0;
	margin-bottom: 0;
	color: #FF0000;
}

.modal-content:hover {
    transform: scale(1.5); /* (150% zoom - Note: if the zoom is too large, it will go outside of the viewport) */
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

.ui-widget-content a{
	color:#337ab7
}
</style>
</head>
<body>
	
	<div id="wrapper">
		<form:form name="pageForm">
		<input type="hidden" name="size" id="size" value="${fn:length(itemsList)}"/> 
		<div id="page-wrapper">
			<!-- /.row -->
				 <div class="row">
					<div class="col-lg-12" >
						<h3 class="text-left"style="color:#483D8B">Stock</h3>
					</div>
				</div>
				
				<div class="panel panel-default">
					<div class="panel-heading" >
					
			<a class="glyphicon glyphicon-circle-arrow-left" href="addstock#tabs-2"
			style="font-size: 30px;top:-0.02pc;color: #337AB5;text-decoration: none;"></a>
			<label style=" margin-left: 10px; vertical-align: text-bottom;">Invoice Details <c:if test="${userRole == 'Audit'}">- ${nameList[stock.venId]}</c:if></label>
			<br><br>
			
			 <div id="statusMsg" style="display: none;" class="error_message"  align="center"></div>
 
									<input type="hidden" id="invoice_id" name='invoice_id'
														value='${stock.invoice_id}' />
									<input type="hidden" id=status name='status'
														value='${stock.status}' />
														
									<div class="col-xs-2">
										<label>Bill Number</label>
						<input class="form-control" id="bill_number"  name="bill_number" value="${stock.bill_number}"
    									type="text" readonly />
										<br>
									</div>
									
									<div class="col-xs-2">
											<label>RS Code</label>
												<input class="form-control" id="rs_code" 
												 name="rs_code" value="${stock.rs_code}" type="text" readonly />
											<br>
									</div>
									<div class="col-xs-2">
								 		<label>RS Name</label>
											<input class="form-control" id="rs_name"  name="rs_name" 
											value="${stock.rs_name}" type="text" readonly />
									<br>
									</div>
									<div class="col-xs-2">
								 		<label>State</label>
											<input class="form-control" id="state"  name="state" 
											value="${stock.state}" type="text" readonly />
									<br>
									</div>
									
								
									<div class="col-xs-2">
										
								 		<label>City</label>
											<input class="form-control" id="city"  name="city" 
											value="${stock.city}" type="text" readonly />
									<br>
									</div>
									<div class="col-lg-2">
										<label>Bill Date</label>
											<input class="form-control" id="bill_date"  name="bill_date" 
											value="${stock.bill_date}" type="text"   readonly />
									
									
									</div>
									<br>
								</div>
								<div class="col-lg-12">	
									
									<div class="col-lg-2">
										<label>Total Amount</label>
											<input class="form-control change_to_thousand_seprator" id="total_amount"  name="total_amount" 
											value="${stock.total_amount}" type="text" readonly />
									</div>
									<div class="col-lg-2">
										<label>Invoice Image</label>
										<br>
										<c:if test="${userRole == 'Audit'}">
											<img class="singleImageShower1" src="get_image?path=${stock.invoice_path}"
															height="25" width="25"/>
										</c:if>
										<c:if test="${userRole != 'Audit'}">
											<img class="singleImageShower" src="get_image?path=${stock.invoice_path}"
															height="25" width="25"/>
											
										</c:if>
														
															<div id="myModal" class="modal">
															  <span class="close">&times;</span>
															  <img class="modal-content" id="img01" color="#FF0000">
															  <div id="caption"></div>
															</div>
									<br>
									<br>
									</div>
									
									
									
										<c:if test="${stock.non_rs =='Non RS' }">
									<div class="col-lg-2">
										<label>Mail Image</label>
										<br>
										<td>
												<a style="" href="download1?path=${stock.mail_path}"> 
												 <img class="singleImageShower12" src="get_image?path=${stock.mail_path}" height="25" width="25" ></img></a>
																					
										</td>					
									</div></c:if>
									<c:if test="${userRole == 'Audit'}">
									<div  id="windo">
									
									<div class="col-lg-2" >
											<label style="margin-left: 7px;"  >Vendor Name</label>
											<input class="form-control" id="venid"name="venid"
											value="${nameList[stock.venId]}" style="margin-left: 7px;" readonly>
									</div>
									<div class="col-lg-2">
											<label style="margin-left: 7px;">Hul outlet Code</label>
											<input class="form-control" id="huloutletcode"name="huloutlet" type="text" 
											 value="${hul_outlet_code}"   style=" margin-left: 7px;" readonly>
										</div>
									<div class="col-lg-2">
											<label style="margin-left: 7px;">GSTN</label>
											<input class="form-control" id="gstn"name="gstn" type="text" 
											value="${gstn_no}"  style="margin-left: 7px;"readonly>
									</div>
										
								</div>
								<div class="col-lg-12" style="right: 14px;">
								<div class="col-lg-2">
											<label style="margin-left: 7px;">Comments</label>
											<c:choose>
										<c:when test="${not empty stock.comments}">						
												<td class="text-center" >
													<input class="form-control"id="comment"  name="comment" value="${stock.comments}" type="text" style="margin-left: 9px;" readonly />
									
										</c:when>
										<c:otherwise>								
											<c:if test="${(userRole == 'Admin' or userRole == 'Audit') and statusMap[stock.status]=='Invoice Submitted to Audit'}">
										
											<input class="form-control" id="comment" name="comment">
											</c:if>
										</c:otherwise>	
											</c:choose>
										</div>
										</div>
									</c:if>
								<div>
									<c:if test="${userRole == 'Vendor'}">
									<div class="col-lg-2">
											<label style="margin-left: 7px;">Hul outlet Code</label>
											<input class="form-control" id="huloutletcode"name="huloutlet" type="text" 
											value="${gstn_no}"  style=" margin-left: 7px;" readonly>
										</div>
										<div class="col-lg-2">
											<label style="margin-left: 7px;">GSTN</label>
											<input class="form-control" id="gstn"name="gstn" type="text" 
											value="${hul_outlet_code}"  style="margin-left: 7px;"readonly>
										</div>
										
											<div class="col-lg-2">
											 <label style="margin-left: 7px;">Comments</label> 
										<c:if test="${not empty stock.comments}">						
												<td class="text-center" >
													<input class="form-control"id="comment"  name="comment" value="${stock.comments}" type="text" style="margin-left: 9px;" readonly />
									
										</c:if>
										
										</div>
										
									</c:if>
									
								</div>
								
								<br>
								<br>
								<br><br>
								<div id="controls" class="col-lg-12" style="padding-left:400px;">
									<c:if test="${(userRole == 'Admin' or userRole == 'Audit') and statusMap[stock.status]=='Invoice Submitted to Audit'}">
																		
										<a href="javascript:;" 
										class="btn btn-success" id= "approve" onclick="approveinvoices()">&nbsp;&nbsp;
										Approve &nbsp; &nbsp;</a> &nbsp; 
										<a href="javascript:;" 
										class="btn btn-danger" id = "reject" onclick="rejectinvoices()">&nbsp;&nbsp;
										Reject &nbsp; &nbsp;
										</a>
									</c:if>
								</div>	
								</div>
								<div id="room_fileds">
								<div class="col-lg-12" style="padding-top: 20px;">
								<div class="panel panel-default">
						<div class="panel-heading" style="padding-top:13px;">
									<b>Item List</b>&nbsp;&nbsp;
									<span style="float: right;">  
						<a onclick="validateCSV();"><img src="dist/img/csv-64.png"
								alt="csv" height="30" width="35">
								</a>
								</span>
								</div>
								</div>
									</div>
           						<div class="panel-body">
								<br>
								
  								<table width="100%" class="table table-striped table-bordered table-hover"
									 style="margin-bottom: 0px" id="more_fields" style="width:48px;" style= "border-radius: 4px;" >
									
  									<thead>
									  <tr class = "tr_color">
										<th width="70" class="text-center" rowspan="2">Item Description</th>
								   	    <th class="text-center" colspan="3"><span style="text-align:center;">Quantity</span></th>
								   	    <th width="70" class="text-center" rowspan="2">MRP</th>
								   	    <th class="text-center" rowspan="2" col width="100" >Net Amount<i class="fa fa-inr"></th>
								      </tr>
								 	 <tr class = "tr_color">
								    	<td class="text-center"  width="100"><b>Case</b></td>
								    	<td class="text-center"  width="100"><b>Unit</b></td>
								    	<td class="text-center"  width="100"><b>Total</b></td> 
								     </tr>
								   </thead>
								   <c:forEach var="items" items="${itemsList}">
								    <tbody id="bodyId" >
									    <tr>						
								   	   		<td class="text-center"><c:out value="${mapItems[items.itemId]}" /></td>	
								   	   		<td class="text-right"><c:out value="${items.cases}" /></td>
								   	   		<td class="text-right"><c:out value="${items.units}" /></td>
								   	   		<td class="text-right"><c:out value="${items.totalunits}" /></td>
								   	   		<td class="text-right"><c:out value="${items.priceperunits}" /></td>	
								   	   		<td class="text-right change_to_thousand_seprator"><c:out value="${items.net_amount}" /></td>
										
	  									  																
									    </tr>
									    </tbody>
									    </c:forEach>
								  </table>
  								
								</div>
								
								
					</div>
				</div>
				</div>
			
			<!-- /.row -->

		</div>
		<!-- /#page-wrapper -->
		</form:form>
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
	<script src="js/stockapprove.js"></script>

	<script>
	var modal = document.getElementById('myModal');

	// Get the image and insert it inside the modal - use its "alt" text as a caption
	var img = document.getElementById('myImg');
	var modalImg = document.getElementById("img01");
	var captionText = document.getElementById("caption");
	img.onclick = function(){
	    modal.style.display = "block";
	    modalImg.src = this.src;
	    captionText.innerHTML = this.alt;
		
	    //for opening new tab
		
	}

	// Get the <span> element that closes the modal
	var span = document.getElementsByClassName("close")[0];

	// When the user clicks on <span> (x), close the modal
	 span.onclick = function() { 
	    modal.style.display = "none";
	    debugger;
	} 
	
	 var modal = document.getElementById('myModal');

	// Get the image and insert it inside the modal - use its "alt" text as a caption
	var img = document.getElementById('myImg');
	var modalImg = document.getElementById("img01");
	var captionText = document.getElementById("caption");
	img.onclick = function(){
		/* debugger;
		window.open(window.loaction.href,'_blank'); */
	    modal.style.display = "block";
	    modalImg.src = this.src;
	    captionText.innerHTML = this.alt;
	}

	// Get the <span> element that closes the modal
	var span = document.getElementsByClassName("close")[1];

	// When the user clicks on <span> (x), close the modal
	span.onclick = function() { 
	    modal.style.display = "none";
	}
 	</script>
	
	<script type="text/javascript">
    $(document).ready(function() {
    	
    	
    	var change_to_thousand_seprator = $( ".change_to_thousand_seprator" );
		var elem = $();
		for( var i = 0; i < change_to_thousand_seprator.length; i++ ){
			var elem = $( change_to_thousand_seprator[i] );
			if( elem.is( "input" ) ){
				elem.val( parseFloat( parseFloat( elem.val().trim() ).toFixed(2) ).toLocaleString('en-IN' , { minimumFractionDigits: 2 }) );
			} else {
				elem.html( parseFloat( parseFloat( elem.text().trim() ).toFixed(2) ).toLocaleString('en-IN' , { minimumFractionDigits: 2 }) );
			}
		}
    	

    	
    	document.getElementById("submit").disabled = true;
    	document.getElementById("contact").disabled = true;
    	document.getElementById("email").disabled = true;
    	document.getElementById("address01").disabled = true;
    	document.getElementById("address02").disabled = true;
    	document.getElementById("state").disabled = true;
    	document.getElementById("city").disabled = true;
    	document.getElementById("pincode").disabled = true;
    	document.getElementById("area").disabled = true;
    	
    	$('.carousel').carousel();
    	
		});
    	
   			
    </script>
   
	<!-- Page-Level Demo Scripts - Notifications  -->
	<!-- <script>
    // tooltip demo
    $('.tooltip-demo').tooltip({
        selector: "[data-toggle=tooltip]",
        container: "body"
    })
    // popover demo
    $("[data-toggle=popover]")
        .popover()
    </script> -->

 <script type="text/javascript">
                
                function validateCSV(){
                  var size=document.getElementById("size").value;
                  if(size!=0){
                    document.pageForm.action = "downloadItemsBybill_number?billNo="+${stock.bill_number};
                    document.pageForm.method = "POST";
                    document.pageForm.submit();   
                    
                  }else{
                    alert("No Content is available for Download... ");
                  }
                }
                </script>

</body>
</html>
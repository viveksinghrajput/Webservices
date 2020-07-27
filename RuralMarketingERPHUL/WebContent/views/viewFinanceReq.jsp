<%@page import="java.text.DecimalFormat"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@page import="java.io.File"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%><html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>View Request - Finance</title>
<style>    .show-content {
   overflow-wrap:break-word;
}


<style>
.error_message {
	margin-top: 0;
	margin-bottom: 0;
	color: #FF0000;
}

</style>
<jsp:include page="header.jsp" />
<!-- CSS goes in the document HEAD or added to your external stylesheet -->
<%
						
							if(session.getAttribute("mapMenu")==null)
						    {
						        response.sendRedirect("login.jsp");
						    }
%>

<script type="text/javascript">

function cancel(){
	document.getElementById("viewFinForm").reset();
}

$(document).ready(function(){
    
    
    $('#updateFinRequest').click(function(){

    	 if(document.getElementById("status").value==6 || document.getElementById("status").value==3){
    		 if(document.getElementById("excel_file").value==''){
    			alert('Please Upload File');
    			return false;
    	 }
    		}

 if(document.getElementById("remarks").value == ''){
		alert('Please Enter Remarks');
		return false;
 }

 if(document.getElementById("status").value == '-1'){
		alert('Please Select the Status');
		return false;
 		 
 	 }

 
 var amountFields  = $( "#doListTable input[name=do_amount]" ),
	editedValList = [];
for( var i = 0 ; i < amountFields.length ; i++){
	 var doAmount= $(amountFields[i] ).closest( "tr" ).find( "input[name=do_amount]" );
	 var doValue=$( amountFields[i] ).closest( "tr" ).find( ":nth-child(2)" ).text();
	  if(parseInt(doAmount.val()) > parseInt(doValue)){
		 alert('Please Enter less than or Equal to '+doValue);
		 doAmount.val(""); 
		 $(amountFields[i] ).closest( "tr" ).find( "input[name=do_amount]" ).focus();
	 }  
	
	if( $( amountFields[i] ).val().trim() != "" ){
		editedValList.push( { "DONUMBER" : $( amountFields[i] ).closest( "tr" ).find( ":nth-child(1)" ).text(),"VALIDFROM" : $( amountFields[i] ).closest( "tr" ).find( ":nth-child(3)" ).text(),"VALIDTO" : $( amountFields[i] ).closest( "tr" ).find( ":nth-child(4)" ).text(), "DOAMOUNT" : doAmount.val()  } );
	}
}

if(document.getElementById("status").value ==8 && document.getElementById("total_amount").value==''){
	alert('Please Assign Amount to atlease one DO Number');
	return false;
}

$( "#do_list_info" ).val( JSON.stringify( editedValList ) );

 	    document.viewFinForm.action = "updateFinRequest";
			document.viewFinForm.method = "POST";
			document.viewFinForm.submit();	   
	     
    });
});    

</script>
<script>
		
</script>
</head>


<body>
	<form:form name="viewFinForm"
	id="viewFinForm" autocomplete="off" enctype='multipart/form-data' method="post">
	<div id="wrapper">

		<div id="page-wrapper">
			<div class="row">
				<div class="col-lg-12">
					<h3 class="border-bottom-underline">Finance</h3>
					
					
				</div>
			</div>
				<input type="hidden" id="do_list_info" name="do_list_info">
			<div class="panel panel-default">
						
						<div class="panel-heading">

			<a class="glyphicon glyphicon-circle-arrow-left" href="completionReport"
			style="font-size: 24px;color: #337AB5;text-decoration: none;"></a>
												
				<label style="margin-left: 10px; color: #337AB5; vertical-align:bottom;">Request Number - ${financeInfo.reqNum}</label>	
											</div>	
	<br>			<div class="row">
					<div class="col-lg-12">
							<div class="panel-body">
<div align="center">
					<label class="error_message">${statusMsg }</label>
				</div>
								<div class="row">
								<%DecimalFormat decimalFormat = new DecimalFormat("#.##");
								decimalFormat.setMaximumFractionDigits(2); %>
														
						
														<div class="col-xs-2">
															<label>Agency </label>
															<input class="form-control" value="${agencyMap[financeInfo.agencyId]}" type="text"
																readonly>
														</div>
														
														<div class="col-xs-2">
															<label>Month</label>
															<input class="form-control"  value="${financeInfo.month}" type="text" readonly>
														</div>


														<div class="col-xs-2">
															<label>Year </label>
															<input class="form-control" value="${financeInfo.year}"
																type="text" readonly>
														</div>
																						<!-- Start of Row -->
														<div class="col-xs-2">
															<label>Completion Report </label>
															<input class="form-control"	value="${financeInfo.completionRptDesc}" type="text" readonly
																maxlength="10">
														</div>
														<div id="doTotalAmt" style="display: none;">		
									<div class="col-xs-2">
															<label>Total Amount</label> 
															<!-- <input class="form-control text-right change_to_thousand_seprator" id="total_amount" name="total_amount"
															 type="text" readonly> -->
															 
															 <input class="form-control text-right" id="total_amount"
																name="total_amount" data-behaviour="thousandsep"
																type="text"  readonly="true" />
															</div>		
															</div>		
															
															<c:if test="${not empty financeInfo.totalAmount}">		
									<div class="col-xs-2">
															<label>Total Amount</label> 
														<%-- 	<input class="form-control text-right change_to_thousand_seprator" id="totalamount" name="totalamount"
																value="${financeInfo.totalAmount}" type="text" readonly>
														
															 --%>
															<input class="form-control change_to_thousand_seprator"  value="${financeInfo.totalAmount}" type="text" readonly />	
												</div>	
															</c:if>		
													
<%
if(request.getParameter("isEdit")!=null)
{
	if(request.getParameter("isEdit").equalsIgnoreCase("Y")){
	%>	
														  <label>File <c:if test="${statusMap[financeInfo.status] =='Invoice Uploaded' || statusMap[financeInfo.status]=='Submitted for Invoicing'}">
														*
														</c:if></label><br>
 <input type="file" class="form-control image_uploader_with_fname"  name="excel_file"  id="excel_file" accept=".xlsx,.xls,.pdf,image/gif, image/jpeg, image/png" capture style="display:none"/>
<img src="dist/img/Upload1.jpg" id="upexcel_file" style="cursor:pointer;height: 30px;" />
</div>
											<%}} %>	
                                        
</div>    
                                        	<div class="row">
                             <%
if(request.getParameter("isEdit")!=null)
{
	if(request.getParameter("isEdit").equalsIgnoreCase("Y")){
	%>
																	 <div class="col-xs-6" style="margin-left: 1.7pc;">
															<label style="margin-left: -0.7pc;">Remarks&nbsp;*</label>
													
							 <textarea  class="form-control"  rows="5" id="remarks" name="remarks" onkeypress="if (this.value.length > 1000) { return false; }" style="resize: none;width: 495px;margin-left: -0.7pc;"></textarea>
															<h6 class="pull-right" id="count_message"></h6>
															</div>
															
															

													<div class="col-xs-2" style="margin-left: -1.52pc;">
															<label>Status&nbsp;*</label> 
															<select
																class="form-control" id="status" name="status" onChange="validateStatus()">
																<option value="-1" selected>-- SELECT --</option>
																<c:forEach var="statusInfo" items="${statusMapByRole}">
	
                                    <option value="${statusInfo.key}"><c:out value="${statusInfo.value}"/></option>
					</c:forEach>
															</select>
														</div>
													<%}} %>	
													 
										
					   </div>
		<div id="doMenu" style="display: none;">
		
		
		
	<table class="table table-striped table-bordered table-hover" style="!important; overflow: scroll;"
												id="doListTable" width="60%">
												<thead>
													<tr class = "tr_color">
									
							<th class="text-center" >Do Number</th>
							<th class="text-center" >Balance</th>
							<th class="text-center" >Valid From</th>
							<th class="text-center" >Valid To</th>
							<th class="text-center" style="width: 12pc;">Enter Amount</th>
							</tr>
							</thead>
							<%-- <c:forEach var="doDetails" items="${doList}">
								<tbody>
									<tr>
										<td class="text-center"><c:out value="${doDetails.doNumber}"></c:out></td>
										<td class="text-center"><c:out value="${doDetails.doValue}"></c:out></td>
										<td class="text-center"><c:out value="${doDetails.validFrom}"></c:out></td>
										<td class="text-center"><c:out value="${doDetails.validTo}"></c:out></td>
										<td><input class="form-control" id="do_amount"
											name="do_amount" type="text" onkeypress='validateNum(event)'></td>
									</tr>
								</tbody>
							</c:forEach> --%>
							<tbody>
							</tbody>
												</table>
												</div><br>
												
				<c:if test="${not empty finDOMappingList}">
				
				
		
	<table class="table table-striped table-bordered table-hover"
												id="tablefeilds">
												<thead>
													<tr class = "tr_color">
									
							<th class="text-center" >Do Number</th>
							<th class="text-center" >Amount Used</th>
							<th class="text-center" >Valid From</th>
							<th class="text-center" >Valid To</th>
							</tr>
							</thead>
							<c:forEach var="doDetails" items="${finDOMappingList}">
								<tbody>
									<tr>
										<td class="text-center"><c:out value="${doDetails.doNumber}"></c:out></td>
										<td class="text-right change_to_thousand_seprator"><c:out value="${doDetails.amount}"></c:out></td>
										<td class="text-center"><c:out value="${doDetails.validFrom}"></c:out></td>
										<td class="text-center"><c:out value="${doDetails.validTo}"></c:out></td>
									</tr>
								</tbody>
							</c:forEach> 
							<tbody>
							</tbody>
												</table>
				</c:if>
		
												
<%
if(request.getParameter("isEdit")!=null)
{
	if(request.getParameter("isEdit").equalsIgnoreCase("Y")){
	%>
		<a class="btn btn-primary" href="javascript:;" id="updateFinRequest" style="width: 100px;margin-left:26pc;">Save </a>
															&nbsp;
														 	<a class="btn btn-primary" href="javascript:;"
														onclick="cancel();" style="width: 100px;">Cancel </a>
<%-- 														<form:hidden path="finId"/>	
 --%>														
														
																			<%} }%>
																			<input type="hidden" id="finId" name='finId' value='${financeInfo.finId}' />
																			<input type="hidden" id="statusId" name="statusId" value="${financeInfo.status}" /><br><br>
															<input type="hidden" id="agencyName" name="agencyName" value="${agencyMap[financeInfo.agencyId]}" /><br><br>
															
															
														
															<table
												class="table table-striped table-bordered table-hover"
												id="viewInventory" style="!important; overflow: scroll;">
												<thead>
													<tr class = "tr_color">
									
							<th class="text-center" >Team</th>
							<th class="text-center" >Remarks</th>
							<th class="text-center" >Submitted Date</th>
							<th class="text-center" >Status</th>
							<th class="text-center" style="width: 310px;">File</th>
							</tr>
							</thead>
												<tbody>
														<c:forEach var="history"
															items="${historyList}">
														<tr>
															<td class="text-center"><c:out
																	value="${history.team}"></c:out></td>
															<%-- <td class="text-center"><c:out
																	value="${history.remarks}"></c:out></td> --%>
																	<td class="text-center"><a href="#myModal" data-toggle="modal" id="${history.remarks}" data-target="#showRemarks">Click Here to see the Remarks</a></td>
<%-- 																<td> <a data-toggle="modal" data-id="${history.remarks}" data-target="#myModal" class="showRemarks">Open Small Modal</td>
 --%>															<td class="text-center"><fmt:formatDate value="${history.lastupdated_date}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
															<td class="text-center"><c:out
																	value="${statusMap[history.status]}"></c:out></td>
															<td>
															
															<c:if test="${not empty history.filePath}">
															
															<%-- ${fn:substringAfter(history.filePath, "/")} --%>
															<a  style="" href="download?path=${history.filePath}"> <img src="dist/img/download.png" 
															alt="download" height="22" width="35"></a></c:if> 
															<c:set value="${fn:split(history.filePath,'/')}" var="separatorPosition" />
  																<c:out value="${separatorPosition[fn:length(separatorPosition)-1]}">
															</c:out>
															</td>

</tr>
</c:forEach>
														</tbody>
							</table>
							
							 											</div>
											</div>
										</div>
									</div>
							
							</div>
		<!-- /#page-wrapper -->

						
						</form:form>
						
<div id="showRemarks" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content" style="width: 45pc;height: 20pc;">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true" style="top: -0.1pc;margin-right: -2pc;">&times;</button>
                </div>
                <div align="center" class="modal-body show-content">
                </div>
            </div>
        </div>
    </div>						

						
</body>
					

	<!-- </div> -->
	
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
<script src="js/adminMenu.js"></script>

<script type="text/javascript">

	


	$('#viewInventory').DataTable({
			scrollX : "1000px",

			scrollX : true,

			scrollCollapse : true,

			paging : true,

		});
	
	</script>
<script>

function validateStatus() {
	var status=$('#status').val();
if(status==8){ 
	$.ajax({
		contentType : "application/json",
		type : "POST",
		url : "loadDOList?agencyName=" + $("#agencyName").val(),
		success : function(data) {
						var doList = new Array();
						doList = data;
	    			  document.getElementById("doMenu").style.display = "block";
	    			  document.getElementById("doTotalAmt").style.display = "block";
	    			$('#doListTable tbody > tr').remove();
	    			 	for (var i = 0; i < doList.length; i++) {
	    			 		 var ArrNames = doList[i];
	        				 var doNumber = ArrNames[0];
	        				 var amount = ArrNames[1];
	        				 var validFrom = ArrNames[2];
	        				 var validTo = ArrNames[3];
	        				 var row =("<tr><td>"+doNumber+"</td><td class='text-right change_to_thousand_seprator'>"+amount+"</td><td>"+validFrom+"</td><td>"+validTo+"</td><td><input class='doAmount form-control text-right' id='do_amount' maxlength='10' placeholder='0' name='do_amount' type='text' onkeypress='validateNum(event)' onChange='checkData()'></td></tr>");
	        				 $("#doListTable").append(row);
	        				 }
	    				
			}
	});

	document.getElementById("doTotalAmt").style.display = "none";
	document.getElementById("doMenu").style.display = "block";
}

	if(status==6 || status==3 || status==11){
		document.getElementById("doMenu").style.display = "none";
		document.getElementById("doTotalAmt").style.display = "none";
	}
 }
$("#upexcel_file").click(function () {
    $("#excel_file").trigger('click');
});

$("#upimg_invoice").click(function () {
    $("#img_invoice").trigger('click');
});

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


$(document ).on( "change", "#doListTable input", function(e){
		recalculatetottal(  $( this ) );
	});

var recalculatetottal = function( _target ) {
		var curr_tr = _target.closest( "tr" ),
		curr_prod = curr_tr.find( ".doAmount" ).val();
			var totalDoAmt = curr_tr.closest( "table" ).find(".doAmount"),
			    TotAmt = 0,
			    singleVal = $();
			    for( var j = 0; j < totalDoAmt.length; j++ ){
			    	singleVal = $( totalDoAmt[j] ).val().replace(/[,]/g,'');
			    	if( singleVal.trim() != "" && !isNaN( singleVal ) ){
			    		TotAmt += parseFloat( singleVal );
			    	}
			    } 
			   
			$( "#total_amount" ).val( parseFloat( TotAmt.toFixed(2) ).toLocaleString('en-IN' , { minimumFractionDigits: 2 }) );
		}	

$('#showRemarks').on('show.bs.modal', function(e) {
    
    var $modal = $(this),
        esseyId = e.relatedTarget.id;
            $modal.find('.show-content').html(esseyId);
    
});

$(document).ready(function() {
	
	
	var change_to_thousand_seprator = $( ".change_to_thousand_seprator" );
	var elem = $();
	for( var i = 0; i < change_to_thousand_seprator.length; i++ ){
		var elem = $( change_to_thousand_seprator[i] );
		if( elem.is( "input" ) ){
			elem.val( parseFloat( parseFloat( elem.val().trim() ).toFixed(2) ).toLocaleString('en-IN' , { minimumFractionDigits: 2 }) );
		}
		else {
			elem.html( parseFloat( parseFloat( elem.text().trim() ).toFixed(2) ).toLocaleString('en-IN' , { minimumFractionDigits: 2 }) );
		}
	}
	

	
});
</script>

</html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html lang="en">

<head>
<title>Conversion Master Dashboard</title>

<jsp:include page="header.jsp" />
<style type="text/css">
.dataTables_wrapper .dataTables_length {
float: left;
}
.dataTables_wrapper .dataTables_filter {
float: right;
text-align: left;
}
.modal-content{
width:100%
}
.col-xs-offset-5{
	margin-left:center;
}
</style>
<!-- common css styles class -->
	<link href="dist/css/common.css" rel="stylesheet" type="text/css">
	
</head>
	<div id="wrapper">
		<div id="page-wrapper">
			<div class="row">
				<div class="col-lg-12">
					<h3 class="text-Left text-left border-bottom-underline" style="bgcolor: #337ab7">Conversion Master</h3>
					${ConvStatus}
					<c:if test="${not empty ConvStatus}">
						<c:if test="${ConvStatus == 'Success'}">
							<div class="alert alert-success alert-dismissable">
							<button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
							New Data Updated Succesfully.
							</div>
						</c:if>
						<c:if test="${ConvStatus == 'Error'}">
							<div class="alert alert-success alert-dismissable">
							<button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
							Some Error Occurred
							</div>							
						</c:if>
					</c:if>
					
				</div>
			</div>
			
			<!-- /.row -->
			<input type="hidden" name="size" id="size" value="${fn:length(allConversion)}"/> 
			<div class="row">
				<div class="col-lg-12">
					<div class="panel panel-default">
					
						<div class="panel panel-default">
						 <span
												style="float: right;margin-top: 0.44pc;"> <a onclick="validateCSV();">  <img src="dist/img/csv-64.png"
												alt="csv" height="30" width="35">
											</a>
											</span>
						<div class="panel-heading" style="padding-top: 13px;">
								<b>Show All Conversions</b></div>
								 <c:if test="${not empty strStatus}">
						<h5 align="center" style="color: #FF0000;"> <c:out value="${strStatus}"/></h5>
		</c:if>
					<br>
						<div class="panel-body">
							<table class="table table-striped table-bordered table-hover" 
							id="tablefeilds" style="margin-bottom: -0.4pc;margin-top: 0pc;">
								<thead>
									<tr class = "tr_color">
														
										<th style="width: 17px;" class="text-center">Edit</th>
									
										<th class="text-center">Brand</th>
										
										<th class="text-center">Conversion &#37;</th>
										
										<th class="text-center">Average Sales Per Promotor</th>
										
										<th class="text-center">Last Updated</th>
										
										<th class="text-center">Updated By</th>
										
										
									
									</tr>
								</thead>
								<tbody>
								<c:forEach var="itemList" items="${allConversion}">
								
								
								<tr class="even gradeC">
																
																<%-- <td><a href="updateMaster?modal=Conversion&id=<c:out value="${itemList.conversion_id}"/>">Edit</a></td> --%>
																<td class="text-center"><button type="button" data-id="${itemList.conversion_id}" class="btn btn-primary editButton"><span class="fa fa-pencil"></span></button></td>
																<%-- <td class="text-center"><button type="button" data-id="${itemList.conversion_id}" class="btn btn-default editButton">Edit</button></td> --%>
																<td class="text-center"><c:out value="${itemList.brandname}"></c:out></td>
																<td class="text-center"><c:out value="${itemList.conpercent}"></c:out></td>
																<td class="text-center"><c:out value="${itemList.avgsales}"></c:out></td> 
																<td class="text-center"><fmt:formatDate value="${itemList.lastUpdated}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
																<td class="text-center"><c:out value="${itemList.ipAddress}"></c:out></td>
																
																
																
															</tr>
															
														</c:forEach>   





												</tbody>
											</table>
										</div>
										
										
							<!-- The form which is used to populate the item data -->
						<form id="convForm" name="convenceForm" autocomplete="off" method="post" class="form-horizontal" style="display: none;">
							<div class="form-group" style="display: none;">
								<label class="col-xs-3 control-label">ID</label>
								<div class="col-xs-3">
									<input type="text" class="form-control" id="id" name="id"
										disabled="disabled" />
								</div>
							</div>

							<div class="form-group">
								<label class="col-xs-5 control-label">Brand</label>
								<div class="col-xs-5">
									<input type="text" class="form-control" id="brand" name="brand" 
									readonly />
								</div>
							</div>

							

							<div class="form-group">
								<label class="col-xs-5 control-label">Average Sales Per Promotor</label>
								<div class="col-xs-5">
									<input type="text" class="form-control" id="avgsales" name="avgsales" readonly />
								</div>
							</div>
							
							<div class="form-group">
								<label class="col-xs-5 control-label">Conversion &#37;</label>
								<div class="col-xs-5">
									<input type="text" class="form-control" id="conv" name="conv" onkeypress='validateNum(event)'/>
								</div>
							</div>
							
							<div class="form-group">
								<div class="col-xs-5 col-xs-offset-5">
									&nbsp; &nbsp; &nbsp;&nbsp;&nbsp;&nbsp; &nbsp; &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;
									
									<button type="button" id="temp" class="btn btn-success btn-circle btn-lg" style="margin-left: -3pc;"><i class="fa fa-check"></i></button>
									<!-- <input type="image" src="dist/img/save-512.png" alt="Submit" width="48" height="48"> -->
									
								</div>
								
							</div>
							
						</form>
								 
						</div>
				</div>
			</div>
			<!-- /.row -->

		</div>
		<!-- /#page-wrapper -->

	</div>
	<!-- /#wrapper -->

	<!-- common css styles class -->
	<link href="dist/css/common.css" rel="stylesheet">

	<!-- Bootstrap Core JavaScript -->
	<script src="vendor/bootstrap/js/bootstrap.min.js"></script>
	<!-- BootBox Custom Bootstrap Modal PopUp-->
	<script src="dist/js/bootbox.min.js"></script>
    
	<!-- Custom Theme JavaScript -->
	<script src="dist/js/sb-admin-2.js"></script>
	
	<!-- DataTables JavaScript -->
    <script src="vendor/datatables/js/jquery.dataTables.min.js"></script>
    <script src="vendor/datatables-plugins/dataTables.bootstrap.min.js"></script>
    <script src="vendor/datatables-responsive/dataTables.responsive.js"></script>
	
	<!-- Custom JavaScript For PrePlanning-->
	<script src="js/adminMenu.js"></script>
	
	<!-- <script src="js/date.js"></script> -->
	
	
    <script>
  
        
        $('.editButton').on(
				'click',
				function() {
					// Get the record's ID via attribute
					var id = $(this).attr('data-id');
					//alert("Edit Button Id:" + id);

					$.ajax({

						contentType : "application/json",
						type : "POST",
						url : "getConvRowData?id=" + id,
						success : function(response) {
							//alert("Response from DB:" + response);
							// Populate the form fields with the data returned from server
							
							$('#convForm').find('[name="id"]').val(response.conversion_id).end()
									.find('[name="brand"]').val(response.brandname).end()
									.find('[name="conv"]').val(response.conpercent).end()
									.find('[name="avgsales"]').val(response.avgsales).end()
							
						},
						
					});
					
					//Show Popup Modal
					// Show the dialog
		            bootbox
		                .dialog({
		                    title: 'Edit Conversion Percentage',
		                    message: $('#convForm'),
		                    show: false // We will show it manually later
		                })
		                .on('shown.bs.modal', function() {
		                    $('#convForm')
		                        .show()                             // Show the  form
		                        .formValidation('resetForm'); 		// Reset form
		                })
		                .on('hide.bs.modal', function(e) {
		                    // Bootbox will remove the modal (including the body which contains the login form)
		                    
		                    $('#convForm').hide().appendTo('body');
		                })
		                .modal('show');

		}); 
        
        $('#temp').click(function(event){
        	
        	event.preventDefault();
        	var $form = $(event.target);
        	
        	conv=$('#conv').val();
        	brand=$('#brand').val();
        	//alert("Conver :"+conv);
        	if(conv=='')
        		{
        		alert('Conversion Cannot Not be Empty');
        		return false;
        		}
        	if(conv>100)
    		{
    		alert('Conversion% Cannot be More than 100');
    		return false;
    		}
        	if(conv<0)
    		{
    		alert('Conversion Cannot be Negative');
    		return false;
    		}
        	id =$('#id').val();
			var result="";
        	
			 $.ajax({
        		contentType : "application/json",
        		type: "POST",
				url : "updateConv?id=" + id+ "&conv=" + conv,
				success : function(response) {
					// Hide the dialog
	                $form.parents('.bootbox').modal('hide');
					
					//Check if Value updated Successfully
					if(response){
						
						//Show Success Message
						bootbox.alert('New Conversion Value Updated Successfully');
						
						//Update the Table Cells
						// Get the cells
		                var $button = $('button[data-id="' + id + '"]'),
		                    $tr     = $button.closest('tr'),
		                    $cells  = $tr.find('td');

		                // Update the cell data
		                $cells
		                .eq(2).html(response.conpercent).end();
		                
		                $cells
		                .eq(4).html(new Date(response.lastUpdated).toISOString().slice(0,10)+ " " + 
								new Date(response.lastUpdated).getHours()+":"+
								new Date(response.lastUpdated).getMinutes()+":"+ 
								new Date(response.lastUpdated).getSeconds()).end();
		                
						$cells
		                .eq(5).html(response.ipAddress).end();
		            }else{
						//Show Error Message
						bootbox.alert('Some Error Occurred, Please try again Later');			
					}
					
				},
				error : function(response){
					
					// Hide the dialog
	                $form.parents('.bootbox').modal('hide');
					
					
					//Show Error Message
					bootbox.alert('Some Error Occurred');
					//result="Some Error Occurred";
				}
							
			}); 
        	//alert(result);
			// this.modal('hide');
        });
    </script>
	
	<script type="text/javascript">
                
                function validateCSV(){
                  var size=document.getElementById("size").value;
                  
                  if(size!=0){
                    
                    document.convenceForm.action = "downloadConversion";
                    document.convenceForm.method = "GET";
                    document.convenceForm.submit();   
                    
                  }else{
                    alert("No Content is available for Download... ");
                  }
                }
                </script>
		

</html>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page import="java.util.List"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html lang="en">

<head>
<title>Saturation Master Dashboard</title>
<jsp:include page="header.jsp" />
<style type="text/css">
	.dataTables_wrapper .dataTables_length {
		float: left;
	}
	.modal-content{
	width:100%
	}
	.dataTables_wrapper .dataTables_filter {
		float: right;
		text-align: left;
	}
</style>
</head>

<body>
	<div id="wrapper">
		<div id="page-wrapper">
			<div class="row">
				<div class="col-lg-12">
					<h3 class=" text-Left text-left border-bottom-underline">Saturation Master</h3>
					<c:if test="${not empty status}">
							<div class="alert alert-success alert-dismissable">
							<button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
							${status}
							</div>
					</c:if>
				</div>
			</div>

			<!-- /.row -->
			<input type="hidden" name="size" id="size" value="${fn:length(allSaturation)}"/> 
			<div class="row">
			  <div class="col-lg-12">
				<div class="panel panel-default">
						 <span style="float: right;margin-top: 0.44pc;"> 
						 
						   <a onclick="validateCSV();"> 
						     <img src="dist/img/csv-64.png" alt="csv" height="30" width="35">
						  </a>
						</span>
						
				<div class="panel-heading" style="padding-top: 13px;">
					<b>Show All Saturations</b></div>
					  <c:if test="${not empty strStatus}">
						<h5 align="center" style="color: #FF0000;"> 
						  <c:out value="${strStatus}"/>
						</h5>
		             </c:if>
		             <br>
						<div class="panel-body">
							<table width="100%" class="table table-striped table-bordered table-hover" id="tablefeilds" style="margin-bottom: -0.4pc;margin-top: 0pc;">
								<thead>
									<tr class = "tr_color"> 
										<th class="text-center">Edit</th>

										<th class="text-center">State</th>
										<th class="text-center">Town</th>
										<th class="text-center">Brand</th>
										<th class="text-center">Saturation &#37;</th>
										<th class="text-center">Last Updated</th>
										<th class="text-center">Updated By</th>

									</tr>
								</thead>
								<tbody >
									<c:forEach var="itemList" items="${allSaturation}">
										<tr class="even gradeC" style="height: 10px;">
											<td class="text-center"><button type="button" data-id="${itemList.saturation_id}" class="btn btn-primary editButton"><span class="fa fa-pencil"></span></button></td>
											<td class="text-center"><c:out value="${itemList.state}"></c:out></td>
											<td class="text-center"><c:out value="${itemList.city}"></c:out></td>
											<td class="text-center"><c:out value="${itemList.brand}"></c:out></td>
											<td class="text-center"><c:out value="${itemList.satpercent}"></c:out></td>
											<td class="text-center"><fmt:formatDate value="${itemList.lastUpdated}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
											<td class="text-center"><c:out value="${itemList.ipAddress}"></c:out></td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
						 <!-- The form which is used to populate the item data -->
						<form id="satForm" name="showSaturation" autocomplete="off" method="post" class="form-horizontal" style="display: none;">
							<h5 align="center" style="color: #FF0000;" id="strMsg"> 
							  <c:out value="${strStatus}"/>
							</h5>
							<div class="form-group" style="display: none;">
								<label class="col-xs-3 control-label">ID</label>
									<div class="col-xs-3">
										<input type="text" class="form-control" id="id" name="id" disabled="disabled" />
									</div>
							</div>

							<div class="form-group" style="margin-right: 1pc;margin-left: 6pc;">
								<label class="col-xs-3 control-label" >State</label>
									<div class="col-xs-5">
										<input type="text" class="form-control" id="state" name="state" disabled="disabled" />
									</div>
							</div>

							<div class="form-group" style="margin-right: 1pc;margin-left: 6pc;">
								<label class="col-xs-3 control-label">Town</label>
									<div class="col-xs-5">
										<input type="text" class="form-control" id="town" name="town"disabled="disabled" />
									</div>
							</div>

							<div class="form-group" style="margin-right: 1pc;margin-left: 6pc;">
								<label class="col-xs-3 control-label">Brand</label>
								 <div class="col-xs-5">
									<input type="text" class="form-control" id="brand" name="brand" disabled="disabled" />
								</div>
							</div>
							<div class="form-group" style="margin-right: 1pc;margin-left: 6pc;">
								<label class="col-xs-3 control-label">Saturation &#37;</label>
								  <div class="col-xs-5">
									<input type="text" class="form-control" id="sat" name="sat" />
								 </div>
							</div>
							

							 <div class="form-group" >
								<div class="col-xs-5 col-xs-offset-3">
									&nbsp; &nbsp; &nbsp;&nbsp;&nbsp;&nbsp; &nbsp; &nbsp;&nbsp;&nbsp;&nbsp; &nbsp; 
									<button  style="margin-left: 2pc;" type="button" id="submitButton" class="btn btn-success btn-circle btn-lg">
										<i class="fa fa-check"></i>
									</button>
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


	<!-- jQuery -->
	<script src="vendor/jquery/jquery.min.js"></script>
	<!-- Bootstrap Core JavaScript -->
	<script src="vendor/bootstrap/js/bootstrap.min.js"></script>
	<!-- Metis Menu Plugin JavaScript -->
	<script src="vendor/metisMenu/metisMenu.min.js"></script>
	<!-- BootBox Custom Bootstrap Modal PopUp-->
	<script src="dist/js/bootbox.min.js"></script>
	<!-- Custom Theme JavaScript -->
	<script src="dist/js/sb-admin-2.js"></script>
	<!-- DataTables JavaScript -->
    <script src="vendor/datatables/js/jquery.dataTables.min.js"></script>
    <script src="vendor/datatables-plugins/dataTables.bootstrap.min.js"></script>
    <script src="vendor/datatables-responsive/dataTables.responsive.js"></script>
	<script src="js/adminMenu.js"></script>
	
	
	<script>
		
			 $('.editButton').on('click',function() {
							// Get the record's ID via attribute
							var id = $(this).attr('data-id');
							//alert("Edit Button Id:" + id);

							$.ajax({

								contentType : "application/json",
								type : "POST",
								url : "getSatRowData?id=" + id,
								success : function(response) {
									//alert("Response from DB:" + response);
									// Populate the form fields with the data returned from server
									//alert(response);
									$('#satForm').find('[name="id"]').val(response.saturation_id).end()
									.find('[name="state"]').val(response.state).end()
									.find('[name="town"]').val(response.city).end()
									.find('[name="brand"]').val(response.brand).end()
									.find('[name="sat"]').val(response.satpercent).end()
									
								},
								error: function (response) {
									 document.getElementById("strMsg").innerHTML=response;
						        }
								
							});
							
							//Show Popup Modal
							// Show the dialog
				            bootbox
				                .dialog({
				                    title: 'Edit Saturation Percentage',
				                    message: $('#satForm'),
				                    show: false // We will show it manually later
				                })
				                .on('shown.bs.modal', function() {
				                    $('#satForm')
				                        .show();                             // Show the login form
				                        /* .formValidation('resetForm');  */		// Reset form
				                })
				                .on('hide.bs.modal', function(e) {
				                    // Bootbox will remove the modal (including the body which contains the login form)
				                    // after hiding the modal
				                    // Therefor, we need to backup the form
				                    $('#satForm').hide().appendTo('body');
				                })
				                .modal('show');

				}); 
		        
		        $('#submitButton').click(function(event){
		        	event.preventDefault();
		        	var $form = $(event.target);
		        	
		        	satVal=$('#sat').val();
		        	if(satVal=='')
		        	{
		        	alert('Please Enter Saturation Value');
		        	return false;
		        	}
		        	if(satVal>100)
	        		{
	        		alert('Saturation Cannot be More than 100%');
	        		return false;
	        		}
		        	if(satVal<0)
	        		{
	        		alert('Saturation Cannot be Negative');
	        		return false;
	        		}
		        	
		        	id =$('#id').val();
					$.ajax({
		        		contentType : "application/json",
		        		type: "POST",
						url : "updateSat?id=" + id+ "&newSat=" + satVal,
						success : function(response) {
							// Hide the dialog
			                $form.parents('.bootbox').modal('hide');
							
			              //Check if Value updated Successfully
							if(response){
								//Show Success Message
								bootbox.alert('New Saturation Value Updated Successfully');
								//Update the Table Cells
								// Get the cells
				                var $button = $('button[data-id="' + id + '"]'),
				                    $tr     = $button.closest('tr'),
				                    $cells  = $tr.find('td');

				                // Update the cell data
				                $cells
				                .eq(4).html(response.satpercent).end();
				                
								$cells
				                .eq(5).html(new Date(response.lastUpdated).toISOString().slice(0,10)+ " " + 
										new Date(response.lastUpdated).getHours()+":"+
										new Date(response.lastUpdated).getMinutes()+":"+ 
										new Date(response.lastUpdated).getSeconds()).end();
				                
								$cells
				                .eq(6).html(response.ipAddress).end();
				                
							}else{
								//Show Error Message
								bootbox.alert('Some Error Occurred, Please try again Later');			
							}
						},
					});
		        	
		        	//alert("Result: "+response);	
		        	   
		        });
	</script>
	<script type="text/javascript">
                
                function validateCSV(){
                  var size=document.getElementById("size").value;
              
                  if(size!=0){
                    
                    document.showSaturation.action = "downloadSaturation";
                    document.showSaturation.method = "GET";
                    document.showSaturation.submit();   
                    
                  }else{
                    alert("No Content is available for Download... ");
                  }
                }
                </script>
	

	

</body>

</html>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html lang="en">

<head>

<title>User Master Dashboard</title>
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
<script>
function save()
	{
	aler('save');
		
	}
	
</script>
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
</head>

<body>

	<div id="wrapper">

		<div id="page-wrapper">
			<br>
			
				<div class="text-left text-left border-bottom-underline"  style="border-bottom-width:10px;" >
				User Master
				</div>
			<!-- User Master Menu's -->
			<div class="row">
				<div class="col-lg-12">
			
						
						<div class="panel panel-default">
						 <span
												style="float: right;margin-top: 0.44pc;"> <a onclick="validateCSV();"> <img src="dist/img/csv-64.png"
												alt="csv" height="30" width="35">
											</a>
											</span>
						<div class="panel-heading" style="padding-top: 13px;">
							<b>View All Users</b></div>
								<h5 align="center" style="color: #FF0000;" id="status"> <c:out value="${strStatus}"/></h5>


					<br>
					<br>
						<div class="panel-body">
							<div class="col-lg-2" style="padding-left: 2px;">
							
								<a href="createUserForm" class="btn btn-primary">Add User</a>
								
							</div>
						</div>	
					<input type="hidden" name="size" id="size" value="${fn:length(userList)}"/> 
					<!-- /.row -->
					
			<form:form name="createuser" modelAttribute="user">
			<div class="row">
				<div class="col-lg-12">
				<input type="hidden" name="id" id="id">
					<!-- <div class="panel panel-default" style="margin-bottom: -1px;"> -->
					
						<div class="panel-body">
							<table  class="table table-striped table-bordered table-hover" id="tablefeilds">								<thead>
									<tr class = "tr_color">
									<th class="text-center">Username</th>
										
										<th class="text-center">First Name</th>

										<th class="text-center">Last Name</th>

										<th class="text-center">Email</th>

										<th class="text-center">Contact</th>

										<th class="text-center">User Type</th>

										<th class="text-center">Access Level</th>

									 	<th class="text-center">Is Active</th>
 								</tr>
							</thead>
						<tbody>
										
								<c:forEach var="itemList" items="${userList}">
								
								<tr class="even gradeC" style="height: 46px;">	
								
								
										<td  class="text-center" style="padding-bottom: 2px;padding-top: 16px;">
																<%-- <a href="edituser?id=<c:out value="${itemList.id}"/>">
																<c:out value="${itemList.username}" ></c:out></a> --%>
																
																<a  href = "#" onClick = "editUser(${itemList.id});" <%-- href="viewRequest?finId=${myAssingedReuest.finId}&isEdit=Y" --%>><c:out
																		value="${itemList.username}"></c:out></a>
																</td>
																<td class="text-left"style="padding-bottom: 2px;padding-top: 16px;"><c:out value="${itemList.firstName}"></c:out></td> 
																<td class="text-left"style="padding-bottom: 2px;padding-top: 16px;"><c:out value="${itemList.lastName}"></c:out></td>
																<td class="text-left"style="padding-bottom: 2px;padding-top: 16px;"><c:out value="${itemList.email}"></c:out></td>
																<td class="text-left"style="padding-bottom: 2px;padding-top: 16px;"><c:out value="${itemList.contactNum}"></c:out></td>
																<td class="text-left"style="padding-bottom: 2px;padding-top: 16px;"><c:out value="${rolesMap[itemList.roleId]}"></c:out></td>
																
																<c:if test="${itemList.mobileAccess == 'Yes' && itemList.roleId ==4}">
																		<td class="text-left"style="padding-bottom: 2px;padding-top: 16px;"><c:out value="Portal & Mobile"></c:out></td>
																</c:if>
																<c:if test="${itemList.mobileAccess == 'No' && itemList.roleId ==4}">
																	<td class="text-left"style="padding-bottom: 2px;padding-top: 16px;"><c:out value="Portal"></c:out></td>
																</c:if>
																<c:if test="${itemList.mobileAccess == 'No' && itemList.roleId !=4}">
																	<td class="text-left"style="padding-bottom: 2px;padding-top: 16px;"><c:out value="NA"></c:out></td>
																</c:if>
																
																
																<c:if test="${itemList.isActive == '0'}">
																	<td class="text-center"><input type="hidden" id="userId" name='userId' 
																	value= "${itemList.id}"/>
																	<input type="button" id="${itemList.id}" class="btn btn-primary"style="width:85px" onclick="unblockUser(${itemList.id},this)"
																	value="&nbsp;Un Block&nbsp"></td>
																</c:if>
																<c:if test="${itemList.isActive == '1'}">
																	<td class="text-center"><input type="button" style="width:85px"
																	class="btn btn-primary"id="${itemList.id}" onclick="unblockUser(${itemList.id},this)"
																	value="&nbsp;Block&nbsp"></td>
																</c:if>
										</tr>
							</c:forEach>   

									</tbody>
							</table>
						</div>
					</div>
				</div>
				</form:form>
				</div>						
			</div>
		</div>
			
			
			<!-- /.row -->
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
	<!-- Custom JavaScript For PrePlanning-->
	<script src="js/adminMenu.js"></script>
	<script src="js/user.js"></script>
	
	
	
	
	
	
	<script>
  /*   function blockUser(value)
    {
    	document.createuser.action = "lockUser?userId="+value;
		document.createuser.method = "POST";
		document.createuser.submit();

    }
    function unblockUser(value)
    {
    	document.createuser.action = "unlockUser?userId="+value;
		document.createuser.method = "POST";
		document.createuser.submit();

    }
   */
   var currentBtn = null;
	function unblockUser(id, btn){
		currentBtn = btn;
		var btnTxt = $(btn).val().trim();
		var value = 0;
		if(btnTxt == "Block"){
			value= "Un Block";
		}else{
			value = "Block";
		}
		
	var url = "";
	if(btnTxt == "Block"){
		url = "lockUser?userId="+id;   
	}else{
		url = "unlockUser?userId="+id;
	}
	jQuery.ajax({
		type : "POST",
		url : url,
		success: function (data){
			
			$(currentBtn).val(value);
			$("#status").text(data);
		},
		error: function (data){
			alert(data + "error");
		}
	});
	}
    
    </script>
    <script>
    $(document).ready(function() {
    	
    /* 	 setTimeout(function() { 
 	        $('#status').hide();
 	    }, 2000);
         
         windows.location.reload(); */
       
        
        $('.editButton').on(
				'click',
				function() {
					// Get the record's ID via attribute
					var id = $(this).attr('data-id');
					//alert("Edit Button Id:" + id);

					$.ajax({

						contentType : "application/json",
						type : "POST",
						url : "getUserRowData?id=" + id,
						success : function(response) {
							//alert("Response from DB:" + response);
							// Populate the form fields with the data returned from server
							$('#userForm').find('[name="id"]').val(response.id).end()
									.find('[name="username"]').val(response.username).end()
									.find('[name="firstname"]').val(response.firstName).end()
									.find('[name="lastname"]').val(response.lastName).end()
									
							
							
						},
						
					});
					
					//Show Popup Modal
					// Show the dialog
		            bootbox
		                .dialog({
		                    title: 'Edit User Details',
		                    message: $('#userForm'),
		                    show: false // We will show it manually later
		                })
		                .on('shown.bs.modal', function() {
		                    $('#userForm')
		                        .show()                             // Show the  form
		                        .formValidation('resetForm'); 		// Reset form
		                })
		                .on('hide.bs.modal', function(e) {
		                    // Bootbox will remove the modal (including the body which contains the login form)
		                    
		                    $('#userForm').hide().appendTo('body');
		                })
		                .modal('show');

		}); 
        
       /*  $('#temp').click(function(event){
        	
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
				url : "updateUser",
				data: $('userForm').serialize(),
				success : function(response) {
					
					//alert("Response :"+response);
					
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
        
        
 */         
    });
    
    
    function editUser(userId){
    	document.getElementById("id").value=userId;
    	 document.createuser.action = "edituser";
    		document.createuser.method = "POST";
    		document.createuser.submit();	  
    	
    }
    </script>
	
	<script type="text/javascript">
                
                function validateCSV(){
                  var size=document.getElementById("size").value;
                  //alert(size);
                  if(size!=0){
                    
                    document.createuser.action = "downloadUserMaster";
                    document.createuser.method = "GET";
                    document.createuser.submit();   
                    
                  }else{
                    alert("No Content is available for Download... ");
                  }
                }
                </script>
		
</body>

</html>

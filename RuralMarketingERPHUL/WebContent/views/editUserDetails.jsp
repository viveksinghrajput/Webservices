<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>View/Edit User</title>
<jsp:include page="header.jsp" />
<script
	src="https://maps.googleapis.com/maps/api/js?key=AIzaSyAPcnSkZyiiAM25-kSLn9r117qepkeeB08&callback=myMap">
</script>
<style type="text/css">
.dataTables_wrapper .dataTables_length {
	float: left;
}

.dataTables_wrapper .dataTables_filter {
	float: right;
	text-align: left;
	
}
.field-icon {
  float: right;
  margin-left: -25px;
  margin-top: -25px;
  position: relative;
  z-index: 2;
}

</style>
</head>
<body>
<form:form name="userUpdateForm"  modelAttribute="user">

	<div id="wrapper">
		<div id="page-wrapper">
		
			<div class="row" style="padding-left: 1pc;">
		<br>
				<%--  style="border-left-width: 2px;padding-left: 0px;padding-right: 800px; padding-top: 17px;">
				<div class="col-lg-12 col-sm-11 col-xs-10 text-left" style="float: left;"></div>
					
			<!-- /.row -->
		<!-- Show My warehouses Here -->
			<div class="col-lg-1 col-sm-1 col-xs-3 pt10"  style="float: left;"><a href="usersmaster"></div>
							<span class="glyphicon glyphicon-circle-arrow-left" style="font-size: 24px;color: #337AB5;"></span></a>
				
			<div class=border-bottom-underline" style="float: left;"></div>
			
				<c class="border-bottom-underline" style="font-size: 20px;"> &nbsp; User Details</c>
		
			</div> --%>
			
			<%-- <div class="row" style="border-left-width: 2px;padding-left: 0px;padding-right: 800px; padding-top: 17px;">
				<div class="col-lg-12 col-sm-11 col-xs-10 text-left" style="float: left;"></div>
					
					<br>
			<!-- /.row -->
		<!-- Show My warehouses Here -->
			<div class="col-lg-1 col-sm-1 col-xs-3 pt10"  style="float: left;"><a href="usersmaster"></div>
							<span class="glyphicon glyphicon-circle-arrow-left" style="font-size: 24px;color: #337AB5;"></span></a>
				
			<div class="col-lg-12 col-sm-11 col-xs-10 text-Left" style="float: left;"></div>
			
				<c class="border-bottom-underline" style="font-size: 20px;"> &nbsp; &nbsp;User Details</c>
			
			</div> --%>
		<div class="text-left border-bottom-underline"  style="border-bottom-width:10px;margin-left: 0pc;" >
				User Master
				</div>
				<c:if test="${not empty strStatus}">
						<%-- <div class="alert alert-success alert-dismissable">
							<button type="button" class="close" data-dismiss="alert"
								aria-hidden="true">&times;</button>
							${strStatus}
						</div> --%>
						<h5 align="center" style="color: #FF0000;" > <c:out value="${strStatus}"/></h5>
				</c:if>
			<div class="row">
				<div class="col-lg-12">
					<div class="panel panel-default">
					<div class="panel-heading" >
							<c:if test="${not empty userDetails}">
							<a class="glyphicon glyphicon-circle-arrow-left" href="usersmaster"
												 style="font-size: 30px;top:-0.02pc;color: #337AB5;text-decoration: none;" data-toggle="tooltip"
												data-placement="top" ></a>&nbsp;
												<b style="padding-top: 13px;">Edit User</b>
						</div>
						
								<br>
								
									<div class="row">
										<div class="col-lg-12">
											
												<div class="panel-body">

													<input type="hidden" name="Id" id="Id"
														value="${userDetails.id}" />
														<input type="hidden" name="access" id="access"
														value="${userDetails.mobileAccess}" />
														<input type="hidden" name="active" id="active"
														value="${userDetails.isActive}" />
													<div class="row">
														<!-- Start of Row -->
														<div class="col-xs-3">
															<label>Username </label>
														</div>
														<div class="col-xs-3">
															<input class="form-control" id="username" name="username"
																value="${userDetails.username}" type="text" readonly
																maxlength="10">
														</div>

														<div class="col-xs-3">
															<label>First Name </label>
														</div>
														<div class="col-xs-3">
															<input class="form-control" id="firstname" name="firstname"
																value="${userDetails.firstName}" type="text"
																readonly>
														</div>
													</div>
													<!-- End of Row -->
													<br>
													<!-- Break Space -->


													<div class="row">
														<!-- Start of Row -->
														
														<div class="col-xs-3">
															<label>Password </label>
														</div>
														
														<div class="col-xs-3">
															<input type="password" class="form-control" id="password" name="password" 
																value="${userDetails.password}" placeholder="Password"
																title="The password length sholud be greater than 8 and less than 11,
																		password must contain at least one numeral,
																		password can not contains illegal characters" required/>
																		 <span id="id"  toggle="#password-field" class="fa fa-fw fa-eye field-icon toggle-password" ></span>
																		
														</div>


														<div class="col-xs-3">
															<label>Last Name </label>
														</div>
														<div class="col-xs-3">
															<input class="form-control" id="lastname"
																name="lastname" value="${userDetails.lastName}"
																type="text" readonly>
														</div>
													</div>
													<br>
													<!-- End of Row -->
													
													<!-- Break Space -->
													<div class="row">
														<!-- Start of Row -->

														<div class="col-xs-3">
															<label>Email Id</label>
														</div>
														<div class="col-xs-3">
															<input class="form-control" id="email" 
																name="email" value="${userDetails.email}"
																type="text" placeholder="Unilever@example.com"/>
														</div>


														<div class="col-xs-3">
															<label>Contact</label>
														</div>
														<div class="col-xs-3">
															<input class="form-control" id="contact" maxlength="10"
																name="contact" value="${userDetails.contactNum}"
																type="text" placeholder="9988776655"/>
														</div>
													</div>
													<!-- End of Row -->
													<br>
													<!-- Break Space -->


													<div class="row">
														<!-- Start of Row -->
														<div class="col-xs-3">
															<label>State </label>
														</div>
														<div class="col-xs-3">
															<input class="form-control" id="state"
																name="state" value="${stateMap[userDetails.state]}"
																type="text" readonly>
														</div>

														<div class="col-xs-3">
															<label>City</label>
														</div>
														<div class="col-xs-3">
															
															<input class="form-control" id="city"
																name="city" value="${cityMap[userDetails.city]}"
																type="text" readonly>
														

														</div>

													</div>
													<!-- End of Row -->
													<br>
													<!-- Break Space -->


													<div class="row">
														<!-- Start of Row -->
														<div class="col-xs-3">
															<label>User Type / Role</label>
														</div>
														<div class="col-xs-3">
														<input type="hidden" id="role" name="role" value="${userDetails.roleId}"/>
															<input class="form-control" id="roleName" name="roleName"
																value="${rolesMap[userDetails.roleId]}" type="text" readonly>
														</div>
														<c:if test="${userDetails.roleId == '5'}">
														<div class="col-xs-3">
															<label>Brand</label>
														</div>
														<div class="col-xs-3">
															<select id="brand" class="form-control" name="brand" >
																<option disabled selected value="-1">-- Select --</option>
																<c:forEach var="brand" items="${brandList}">
																		<c:choose>
																		<c:when
																			test="${not empty userDetails.brand && userDetails.brand eq brand.value}">
																			<option value="${userDetails.brandId}" selected>${userDetails.brandId}</option>
																		</c:when>
																	 <c:otherwise>
																		<option value="${brand.key}">${brand.value}</option>
																	</c:otherwise> 
																	</c:choose>
															</c:forEach>
														</select>
														</div>
														
														</c:if>
												<c:if test="${userDetails.roleId == 4}">
														<div class="col-xs-3">
															<label>Agency</label>
														</div>
														
														<div class="col-xs-3">
															<input class="form-control" id="agency" name="agency"
																value="${agencyMap[userDetails.agencyId]}" type="text" readonly
																maxlength="100">
															
														</div>
														<br><br>
														<div class="col-xs-3">
															<label>Access Level</label> 
														</div>
														<div class="col-xs-3">
																<div class="radio">
																	<form>
																	
																		<c:if test="${userDetails.mobileAccess == 'No'}">
																	
																		<input type="radio" name="mobileaccess" id="mobileaccess" value="No" checked>Portal<br>
																		<input type="radio" name="mobileaccess" id="mobileaccess"  value="Yes">Mobile & Portal					
																		
																		</c:if>
																		<c:if test="${userDetails.mobileAccess == 'Yes'}">
																	
																		<input type="radio" name="mobileaccess" id="mobileaccess" value="No" >Portal<br>
																		<input type="radio" name="mobileaccess" id="mobileaccess"  value="Yes" checked>Mobile & Portal					
																		
																		</c:if>
																	</form>
																</div>
														</div>
														<br>
														 <div class="col-xs-3">
															<label>Vendor</label>
														</div>
														<div class="col-xs-3">
															<input class="form-control" id="vendor" name="vendor"
																value="${vendorMap[userDetails.vendorId]}" type="text" readonly
																maxlength="100">
														</div>
														
															<br><br>
											</c:if> 
	
										</div>
											</div>
									
									</div>
								
									<!-- End of Main ROW -->
								
								
								<!-- Row for Save button -->
									<br><div style="text-align:center; margin-bottom: 14px;">
								
									
									<a class="btn btn-primary" href="javascript:;"
														onclick="updateUser();" style="width: 100px;">Update </a>
														
									</div>
									
							</c:if>
							

						</div>
					</div>
				</div>

			</div>
		
			<!-- /.row -->
			
				<!-- End Second Class -->
			</div>




		</div>
		<!-- /#page-wrapper -->

	
	<!-- /#wrapper -->
	</form:form>

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


<!-- 	<script type="text/javascript">
	
	
	


	 $(document).ready(function() {
	    	
			$('#wareHouseTable').DataTable( {
	        	
	        	scrollX		:	true,
	           	paging		:	true,
	            "autoWidth"	:	false,
	            "scrollCollapse": true,
		        "paging"		: false,
				"ordering" 		: false,
				stateSave		: true,
	        	});
	    	
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
		Page-Level Demo Scripts - Notifications 
		<script>
	    // tooltip demo
	    $('.tooltip-demo').tooltip({
	        selector: "[data-toggle=tooltip]",
	        container: "body"
	    })
	    // popover demo
	    $("[data-toggle=popover]")
	        .popover()
	    </script> -->
	    
	    <script>
	    setTimeout(function() { 
	        $('#status').hide();
	    }, 2000);
	    function updateUser(){
			
 			var password = document.userUpdateForm.password;
			var password1=/[\W_]/;
		    var email = document.userUpdateForm.email;
		    var reg = /^([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/;
		    var contact = document.userUpdateForm.contact;
		    var mob = /^[1-9]{1}[0-9]{9}$/;
		    var vendor=document.userUpdateForm.vendor;
		    if (password.value == '')
		    {
		    		password.style.background = 'Midnight';
		            error = "You didn't enter a password.\n";
		            alert(error);
		            password.focus();
		            return false;
		        
		    } else if ((password.value.length < 8) || (password.value.length > 11)) {
		            
		            password.style.background = 'Midnight';
		            error = "The password length sholud be greater than 4 and less than 11 . \n";
		            alert(error);
		            password.focus();
		            return false;
		     
		        } else if (password1.test(password.value)) {
		            
		            password.style.background = 'Midnight';
		            error = "The password contains illegal characters.\n";
		            alert(error);
		            password.focus();
		            return false;
		     
		        } else if ( (password.value.search(/[a-zA-Z]+/)==-1) || (password.value.search(/[0-9]+/)==-1) ) {
		            
		            password.style.background = 'Midnight';
		            error = "The password must contain at least one numeral.\n";
		            alert(error);
		            password.focus();
		            return false;
		     
		        } 
		    if (email.value == '')
		    {
		        window.alert("Please Enter The Email ::");
		        email.focus();
		        return false;
		    }else if (reg.test(email.value) == false) 
		    {
		            alert('Please Enter the Valid Email ::');
		            email.focus();
		            return false;
		      }
		    if (contact.value == '')
		    {
		        window.alert("Please Enter The contact ::");
		        contact.focus();
		        return false;
		    }
		    else if (mob.test(contact.value) == false) {
		        alert("Enter 10 Digit Mobile Number (like: 9028183586)");
		        contact.focus();
		        return false;
		    }
		    else{
				document.userUpdateForm.action = "UpdateUserDetails";
				document.userUpdateForm.method = "POST";
				document.userUpdateForm.submit();				
			}
		}


	  //loading the vendor with respect to Agency
	  function loadVendorData(){
	  	$.ajax({
	  		contentType : "application/json",
	  		type : "POST",
	  		url : "getVendorofAgency?agency=" + $('#agency').val(),
	  		success : function(data) {
	  			var vendors = new Array();
	  			vendors = data;
	  			var select = document.getElementById("vendor");
	  			$('#vendor option[value!="-1"]').remove();
	  			for (var i = 0; i < vendors.length; i++) {
	  				select.options[select.options.length] = new Option(
	  						vendors[i], vendors[i]);
	  			}
	  		}
	  	});
	  }

	  
	 /*  
	  function myFunction() {
		
		    var x = document.getElementById("password");
		    if (x.type === "password") {
		        x.type = "text";
		    } else {
		        x.type = "password";
		    }
		} */
		$("#id").click(function() {

			  $(this).toggleClass("fa-eye fa-eye-slash");
			  var x = document.getElementById("password");
			    if (x.type === "password") {
			        x.type = "text";
			    } else {
			        x.type = "password";
			    }
			});
	        </script>



	
   
</body>
</html>
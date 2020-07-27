
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Create New User</title>
<jsp:include page="header.jsp" />
<style type="text/css">
.dataTables_wrapper .dataTables_length {
	float: left;
}
.dataTables_wrapper .dataTables_filter {
	float: right;
	text-align: left;
}
/* Style all input fields */
input {
    width: 100%;
    padding: 12px;
    border: 1px solid #ccc;
    border-radius: 4px;
    box-sizing: border-box;
    margin-top: 6px;
    margin-bottom: 16px;
}
/* Style the container for inputs */
.container {
    background-color: #f1f1f1;
    padding: 20px;
}

#message {
    display:none;
    background: #f1f1f1;
    color: #000;
    position: relative;
    padding: 20px;
    margin-top: 10px;
}

#message p {
    padding: 8px 18px;
    font-size: 13px;
}

/* Add a green text color and a checkmark when the requirements are right */
.valid {
    color: green;
}

.valid:before {
    position: relative;
    left: -35px;
    /* content: "✔"; */
}

/* Add a red text color and an "x" when the requirements are wrong */
.invalid {
    color: red;
}

.invalid:before {
    position: relative;
    left: -35px;
    /* content: "✖"; */
}

</style>


</head>
<body>
	<div id="wrapper">
		<div id="page-wrapper">
			<div class="row"><br>
		<div class="text-left border-bottom-underline"  >
				User Master
				</div>
			</div><!-- /.row -->
			<div class="panel panel-default">
			<div class="col-lg-1 col-sm-1 col-xs-2 pt10"  style="float: left;padding-bottom: -1px;"><a href="usersmaster">
						<span class="glyphicon glyphicon-circle-arrow-left" style="font-size: 24px;color: #337ab7;padding-top: 9px;"></span></a>
				</div>
						<div class="panel-heading">
							<h4>Add New User</h4> 
					</div>
			<!-- Show Create Form Here -->
	<div class="row">
		<div class="col-lg-12">
		 <div class="panel panel-default" style="padding-top: 10px;">
		<div class="panel-body">
		 <div class="row"><!-- Menu Buttons -->
		 <c:if test="${not empty strStatus}">
						<h5 align="center" style="color: #FF0000;"> <c:out value="${strStatus}"/></h5>
		</c:if>
			
			</div><!--End Menu Buttons -->
			<br>
	<form:form name="createUser" modelAttribute="user">
	<div class="row">
	<div class="col-lg-15" style="padding-left: 0px;">
	
	<div class="panel-body"style="padding-top:0px;padding-bottom: 0px;">
	<!-- Dropdown Selection Items -->
	
		<div class="col-lg-3">
		
			<label>Select Role</label> 
			<div class="scrollable">
			<form:select id="role" class="form-control" name="role" path="roleId"  onChange="loadOptions();" size="1" style="width: 170px;">
				<option disabled selected value="-1"><br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;--Select Role--</br></option>
				<c:forEach var="roles" items="${roleMap}">
                            <c:choose>
                                <c:when test="${selectedRole == roles.value}">
                                    <option selected="selected" value="${roles.key}"><c:out value="${roles.value}"/></option>       
                                </c:when> 
                                <c:otherwise>
                                    <option value="${roles.key}"><c:out value="${roles.value}"/></option>
                                </c:otherwise>
                            </c:choose>
                </c:forEach>
			</form:select>
			</div>
		</div>
		<div class="col-lg-3" id="brandMenu" style="display: none;margin-left: -0.8pc;">
			<label>Select Brand</label>
			<form:select id="brand" path="brandId" class="form-control" name="brand" onChange="brandSelection();" style="width: 170px;">
				<option disabled selected value="-1">&nbsp;&nbsp;&nbsp;-- Select --</option>
					<c:forEach var="brand" items="${brandList}">
						<c:choose>
							<c:when
								test="${not empty selected_brand && selected_brand eq brand.value}">
									<option value="${selected_brand}" selected>${selected_brand}</option>
							</c:when>
					 <c:otherwise>
						<option value="${brand.key}">${brand.value}</option>
						</c:otherwise> 
						</c:choose>
					</c:forEach>
		</form:select>
		</div>
		<div id="secondaryMenu" style="display: none;">
			<div class="col-lg-3" id="selectAgency" style="display: none;margin-left: -0.8pc;">
				<label>Select Agency</label> 
				<form:select id="agency" class="form-control" name="agency" path="agencyId" onChange="loadVendorData();" style="width: 170px;">
					<option disabled selected value="-1"> &nbsp;&nbsp;&nbsp;&nbsp;--Select Agency--</option>
					<c:forEach var="agency" items="${agencyList}">
						<c:choose>
						<c:when test="${selected_agency == agency.agencyname}">
                                <option selected="selected" value="${agency.id}"><c:out value="${agency.agencyname}"/></option>       
                            </c:when> 
                               	<c:otherwise>
                                   	<option value="${agency.id}"><c:out value="${agency.agencyname}"/></option>
                                </c:otherwise>
                          </c:choose>
					</c:forEach>
				</form:select>
			</div>
	
			<div class="col-lg-3" id="selectVendor" style="display: none;margin-left: -0.8pc;">
				<label>Select Vendor</label>
				 <form:select id="vendor"  class="form-control" name="vendor" path="vendorId" style="width: 170px;">
					<option disabled selected value="-1"> &nbsp;&nbsp;&nbsp;&nbsp;--Select Vendor--</option>
					<c:forEach var="ven" items="${vendorList}">
						<%-- <option value="${vendorList}">${vendorList}</option> --%>
						<c:choose>
						<c:when test="${selected_vendor == ven.vendorName}">
                                <option selected="selected" value="${ven.id}"><c:out value="${ven.vendorName}"/></option>       
                            </c:when> 
                               	<c:otherwise>
                                   	<option value="${ven.id}"><c:out value="${ven.vendorName}"/></option>
                                </c:otherwise>
                          </c:choose>
					</c:forEach>
				</form:select> 
			</div>
			<div class="col-lg-3" id="chooseMobile" style="display: none;padding-right: 0px;padding-left: 2px;margin-left: -0.5pc;">
				<label>Access Level</label> 		
				<div class="radio">
				
					<input type="radio" name="mobileaccess" id="mobileaccess"  value="No" style="margin-left:-5pc; width:250px;" checked/>Portal &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				 	<input type="radio" name="mobileaccess" id="mobileaccess"  value="Yes" style="margin-left:-1pc; width:250px;"/>Mobile & Portal								
				
				</div>				
				
			</div>
			<!-- 
			<div class="col-lg-3" id="huloutlet" style="display: none;">
				<label>Hul Outlet Code</label>
					<input type="text" name="huloutletcode" class="form-control" id="huloutletcode" style="width: 170px;">											
			
			</div>
				<label>GSTN</label>
			
					<input type="text" name="gstn" class="form-control" id="gstn" style="width: 170px;">											
			

		</div> -->
		<br>
		</div><!-- End of Secondary Menu -->
	</div><!-- End of DropDown Selections panel-body-->
	
	<div class="panel-body" style="padding-top:0px;width: 95%;"> 
	<!-- Start Second Panel Body for Field Items -->
	<div class="col-lg-12"style="padding-left: 4px;">
	<!-- First Row -->
	<br>
		<div class="col-lg-3" >
		
			<label>First Name</label>
			<form:input class="form-control" id="firstname" name="firstname" path="firstName"  type="text" placeholder="First name" style="width:170px;"/> 
		</div>
		<div class="col-lg-3">
			<label>Last Name</label> 
			<form:input class="form-control" id="lastname" name="lastname" type="text" path="lastName" placeholder="Last name" style="width:170px;"/> 
		 </div>
		 
			<div class="col-lg-3"> 
				<label>Email Id</label> 
				<form:input class="form-control" id="email" name="email" type="text" path="email"  placeholder="unilever@example.com" style="width:170px;"/>
		</div>
	<div class="col-lg-3">
			<label>Contact Number</label> 
			<form:input class="form-control" id="contact" name="contact" type="text" path="contactNum" placeholder="Contact number" maxlength="10" style="width:170px;"/>
	</div>
		
	</div>
	<div class="col-lg-12" style="padding-left: 4px;">
		<!-- Second Row -->
		 <div class="col-lg-3" >	
			<label>Username</label>
			<form:input class="form-control" id="username" name="username" type="text" path="username"  placeholder="User name" style="width:170px;"/>
		</div>
		<!-- <div class="col-lg-3">
			<label>Password</label>
			
			<input class="form-control" id="password" name="password" type="password" placeholder="Password" 
			pattern="/^(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{6,}$/" 
			title="at least one number, one lowercase and one uppercase letter at least 8 characters that are letters, 
			numbers or the underscore" required>
		<div id="myModal" class="modal">
		<div id="message" style="padding-top: 1px;padding-bottom: 1px;">
			  <p id="letter" class="invalid" style="padding-top: 1px;padding-bottom:1px;margin-bottom:1px;padding-left:1px;"><b>lowercase</b> letter</p>
			  <p id="capital" class="invalid" style="padding-top: 1px;padding-bottom:1px;margin-bottom:1px;padding-left:1px;"><b>uppercase</b> letter</p>
			  <p id="number" class="invalid" style="padding-top: 1px;padding-bottom:1px;margin-bottom:1px;padding-left:1px;"><b>number</b></p>
			  <p id="length" class="invalid"style="padding-top: 1px;padding-bottom:1px;margin-bottom:1px;padding-left:1px;">Minimum <b>8 characters</b></p>
		</div>
			
		</div> -->
		
<!-- <div class="col-lg-3">
<label>Password</label>
<input class="form-control" id="password" name="password" type="password" placeholder="Password" 
pattern="/^(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{6,}$/" 
title="at least one number, one lowercase and one uppercase letter at least 8 characters that are letters, 
numbers or the underscore" required style="width: 170px;" >
<div id="myModal" class="modal">
<div id="message" style="padding-top: 1px;padding-bottom: 1px;">
  <p id="letter" class="invalid" style="padding-top: 1px;padding-bottom:1px;margin-bottom:1px;padding-left:1px;"><b>lowercase</b> letter</p>
  <p id="capital" class="invalid" style="padding-top: 1px;padding-bottom:1px;margin-bottom:1px;padding-left:1px;"><b>uppercase</b> letter</p>
  <p id="number" class="invalid" style="padding-top: 1px;padding-bottom:1px;margin-bottom:1px;padding-left:1px;"><b>number</b></p>
  <p id="length" class="invalid"style="padding-top: 1px;padding-bottom:1px;margin-bottom:1px;padding-left:1px;">Minimum <b>8 characters</b></p>
</div>
</div> -->
	<div class="col-lg-3" >	
			<label>Select State</label>
			<div class="scrollable" style="padding-top: 5px;">
			<%-- <select id="state" class="form-control" name="state"  onChange="loadCities();"style="width: 170px;">
					<option disabled selected value="-1"><br> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;--Select State--</option>
						<c:forEach var="state" items="${stateList}">
							<option value="${state}">${state}</option>
						</c:forEach>
				</select> --%>
		 <form:select class="form-control" id="state" name="state" path="state"  onChange="loadCities();">
																<option value="-1" selected>-- SELECT --</option>
																<c:forEach var="state" items="${stateList}">
																   <c:choose>
                                										<c:when test="${selected_State == state}">
                                   											 <option selected="selected" value="${state.stateId}"><c:out value="${state.states}"/></option>       
                                										</c:when> 
                               									 <c:otherwise>
                                    									<option value="${state.stateId}"><c:out value="${state.states}"/></option>
                                								</c:otherwise>
                            										</c:choose>
																</c:forEach>
							</form:select>  
			</div>
	</div>
		
	<div class="col-lg-3">	
			<label>Select City</label> 
			<div class="scrollable" style="padding-top: 5px">
			<form:select id="city" class="form-control" path="city" name="city" style="width:170px;">
				<option disabled selected value="-1"><br> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;--Select City--</option>
			</form:select> 
			</div>
		</div>
			
	</div><!-- End of Second Panel Body -->
	</div>
	</div>
	</div><!-- End of Row Form -->
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br><div style="text-align:center; margin-bottom: 14px;">
									<input type="Submit" value="Save"  class="btn btn-primary fa-save" id="savingOnclick" style="width:85px">
									</div>
	
	
	</button>
	</form:form>
	</div>
	</div>
	</div>
	</div><!-- /.row -->
	</div><!-- /#page-wrapper -->
	</div><!-- /#wrapper -->


	<!-- jQuery -->
	<script src="vendor/jquery/jquery.min.js"></script>
	
	<!-- common css styles class -->
	<link href="dist/css/common.css" rel="stylesheet">

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
	<script src="js/adminMenu.js"></script>
	 <script src="js/user.js"></script>
 

	<script type="text/javascript">
		// tooltip demo
		$('.tooltip-demo').tooltip({
			selector : "[data-toggle=tooltip]",
			container : "body"
		})
		// popover demo
		$("[data-toggle=popover]").popover()
	</script>

</body>
</html>
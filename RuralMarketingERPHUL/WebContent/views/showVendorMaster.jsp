<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html lang="en">

<head>

<!-- common css styles class -->
<link href="dist/css/common.css" rel="stylesheet">

<title>Vendor Master Dashboard</title>
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
			<div class="text-left text-left border-bottom-underline" style="border-bottom-width:10px;">
				Vendor Master
				</div>
			
			<!-- Create Vendor Form -->
			<div class="row">
				<div class="col-lg-12">
					<!-- <div class="panel panel-default" >
						<div class="panel-heading">
						<b>Create New Vendor</b>
							<span style="float:right; height='32px';">
								<a href="downloadVendorMaster"> 
									<img src="dist/img/csv-64.png" alt="csv"
									 height="30" width="38">
								</a>
							</span>
							 
						</div> -->
						
						<div class="panel panel-default">
						 <span
												style="float: right;margin-top: 0.44pc;"> <a onclick="validateCSV();">  <img src="dist/img/csv-64.png"
												alt="csv" height="30" width="35">
											</a>
											</span>
						<div class="panel-heading" style="padding-top: 13px;">
						<b>Create New vendor</b></div>
						<!-- Status message -->
						<h5 align="center" style="color: #FF0000;" id="userStatus"> <c:out value="${strStatus}"/></h5>
						 
					<br>
					<br>
					<form:form name="createVendor" modelAttribute="vendor">
						<div class="panel-body">
								<div class="col-lg-2" style="padding-left: 8px; width: 194.92px;">
									<label>Vendor Name</label>
									<form:input class="form-control" path="vendorName" id="vendorname" name="vendorname" type="text"/>
								</div>
							<div class="col-lg-2" style="padding-left: 8px;width: 194.92px;">
								<label>Select Agency</label>
								<%-- <select id="agency" class="form-control" name="agency">
													<option disabled selected value="-1">-- Select --</option>
													<c:forEach var="agency" items="${agencyList}">
														<option value="${agency}">${agency}</option>
													</c:forEach>
													
							</select> --%>
							<form:select class="form-control" id="agency" name="agency" path="agencyId">
																<option value="-1" selected>-- SELECT --</option>
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
							
						<div class="col-lg-2" style="padding-left: 8px;width: 194.92px;">
								<label>Hul Outlet Code</label>
									<form:input type="text" path="hul_outlet_code" name="huloutletcode" class="form-control" id="huloutletcode" style="width: 170px;"/>											
			
						</div>
						<div class="col-lg-2" style="padding-left: 8px;width: 194.92px;">
								<label>GSTN</label>
			
								<form:input type="text" path="gstn_no" name="gstn" class="form-control" id="gstn" style="width: 170px;"/>											
						</div>

		
						<div class="col-lg-2"style="padding-left: 8px; width: 194.92px;">
							<label>Select State</label>
							<form:select class="form-control" id="state" name="state" path="state" onChange="loadCities();">
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
							<br>
							</div>
								<div class="col-lg-2" style="padding-left: 8px; width: 194.92px;">
									<label>Select City</label>
									<form:select id="city" path="city"
										class="form-control" name="city">
										<option disabled selected value="-1">-- Select --</option>
									</form:select>
								</div>
								<br>
							<br><br>
							
							<div class="col-lg-2" style="padding-top: 5px;">
									<input type="submit" class="btn btn-primary" id="savingOnclick" 
										value="&nbsp;Add&nbsp;&nbsp;"style="width: 100px; margin-top: 18px;">
							</div>
							
							
						</div>
				
			
			
			<!-- /.row -->
			<input type="hidden" name="size" id="size" value="${fn:length(vendorList)}"/> 
			<div class="row">
				<div class="col-lg-12">
							
						<div class="panel-body">
							<table  class="table table-striped table-bordered table-hover" id="tablefeilds">
								<thead>
									<tr class="tr_color">
										<th class="text-center">Vendor Name</th>
										<th class="text-center">Agency Name</th>
										<th class="text-center">Hul Outlet Code</th>
										<th class="text-center">GSTN NO</th>
										<th class="text-center">State</th>
										<th class="text-center">City</th>
										<th class="text-center">IsActive</th>	
										
									
									</tr>
								</thead>
								<tbody>
								<c:forEach var="itemList" items="${vendorList}">
								
								<tr class="even gradeC" id="vendorId">
																
																
																
																<td class="text-left"><c:out value="${itemList.vendorName}"></c:out></td>
																<td class="text-left"><c:out value="${agencyMap[itemList.agencyId]}"></c:out></td>
																<td class="text-left"><c:out value="${itemList.hul_outlet_code}"></c:out></td>
																<td class="text-left"><c:out value="${itemList.gstn_no}"></c:out></td>
																<td class="text-left"><c:out value="${stateMap[itemList.state]}"></c:out></td>
																<td class="text-left"><c:out value="${cityMap[itemList.city]}"></c:out></td>
											
											<c:if test="${itemList.isActive == '0'}">
												<td class="text-center">
												<input type="hidden" id="venId" name='venId' value= "${itemList.id}"/>
												<input type="button" id="${itemList.id}"style="width:85px" class="btn btn-primary" onclick="blockVendor(${itemList.id}, this)" 
												value="&nbsp;Un Block&nbsp"></td>
											</c:if>
											<c:if test="${itemList.isActive == '1'}">
												<td class="text-center">
												<input type="hidden" id="venId" name='venId' value= "${itemList.id}"/>
												<input type="button" id="${itemList.id}" style="width:85px" class="btn btn-primary" onclick="blockVendor(${itemList.id}, this)" 
												value="&nbsp;Block&nbsp" ></td>
											</c:if>

			
															</tr>
														
														</c:forEach>   
												</tbody>
											</table>
										</div>
									
								</div>
							</div>
						</form:form>
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
	<!-- <script src="js/vendorMaster.js"></script> -->
	
	<script type="text/javascript">
	

	//saving new vendor here
	    $("#savingOnclick").click(function(){
	    	
	    	var vendor=document.createVendor.vendorname;
			var vend=/^[0-9a-zA-Z]+$/;
			var huloutletcode=document.createVendor.huloutletcode;
		    var hulcode=/^[0-9a-zA-Z]+$/;
		    var gstn=document.createVendor.gstn;
		    var gstncode=/^[0-9a-zA-Z]+$/;
		    
		    
	    	if(document.getElementById("vendorname").value==''){
	    		alert('Please Enter Vendor Name. ');
	    		$("#vendorname").focus();
	    		return false;
	    	}else if(vend.test(vendor.value)==false){
	    		alert('Vendor Name Should be Alphanumeric ::');
	    		$("#vendorname").focus();
	    		return false;
	    		
	    	}
	    	if (document.getElementById("agency").value == '-1') {
	    		alert('Please Enter AgencyMaste.');
	    		$("#agency").focus();
	    		return false;
	    	}if(huloutletcode.value==''){
	    		//alert("hiiiiiiiiiiiiiiiiiiiii");
	    		huloutletcode.style.background='Midnight';
	    		error="Please Enter Hul outlet Code ::\n";
	    		alert(error);
	    		huloutletcode.focus();
	    		return false;
	    	}if(gstn.value==''){
	    		gstn.style.background='Midnight';
	    		error="Please Enter GSTN Number ::";
	    		alert(error);
	    		gstn.focus();
	    		return false;
	    	}if (document.getElementById("state").value == '-1') {
	    		alert('Please Select State.');
	    		$("#state").focus();
	    		return false;			
	    	}if (document.getElementById("city").value == '-1') {
	    		alert('Please Select City.');
	    		$("#city").focus();
	    		return false;
	    	}else{
	    		document.createVendor.action = "saveNewVendor";
	    		document.createVendor.method = "POST";
	    		document.createVendor.submit();				
	    	}
	    	
	    	
	    	
	    });

	</script>
	
	
	
	
	
	
  <!--   <script> 
    function blockVendor(value)
    {
    	 document.createVendor.action = "lockVendor?venId="+value;
		document.createVendor.method = "POST";
		document.createVendor.submit();  
		
     	$.ajax({
			 
			 contentType : "application/json",
	    		type: "POST",
				url : "lockVendor1?venId="++value,
				data: JSON,
				
				success : function(response) {
					if(response){
				                
						alert('Vendor Blocking Successfully');
					}
				}
				
		 }); 
    }
    function unblockVendor(value)
    {
    	document.createVendor.action = "unlockVendor?venId="+value;
		document.createVendor.method = "POST";
		document.createVendor.submit();

    } 

    </script> -->
	 <script type="text/javascript">
    $("#vendorname").change(function(){
        var vendorname = $(this).val();
        
        $.ajax({
            type: "POST",
            url: "checkVendor",
            data: {"vendorName": vendorname},
            success: function(msg){

                  if(msg == 1){
                alert("VendorName exists !... try another");
                  document.getElementById('vendorname').value = "";
                  $('#vendorname').focus();
                  }
            },
            error:function(){
            }
        });

    });
    
    
    var currentBtn = null;
	function blockVendor(id, btn){
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
		url = "lockVendor?venId="+id;
	}else{
		url = "unlockVendor?venId="+id;
	}
	jQuery.ajax({
		type : "POST",
		url : url,
		success: function (data){
			
			$(currentBtn).val(value);
			$("#userStatus").text(data);
		},
		error: function (data){
			alert(data + "error");
		}
	});
	}
      </script>
      
      <script type="text/javascript">
      
      function validateCSV(){
    	  var size=document.getElementById("size").value;
    	  
    	  if(size!=0){
    		  document.createVendor.action = "downloadVendorMaster";
	    		document.createVendor.method = "GET";
	    		document.createVendor.submit();		
    		  
    	  }else{
    		  alert("No Content is available for Download... ");
    	  }
      }
      </script>
	
		
</body>

</html>

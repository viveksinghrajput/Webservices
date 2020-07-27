<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix='fn' uri='http://java.sun.com/jsp/jstl/functions' %>

<html lang="en">

<head>

<title>Agency Master Dashboard</title>
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
<form:form id="createAgency" name="createAgency" modelAttribute="agency" > 
	<div id="wrapper">

		<div id="page-wrapper">
		<br>
		
			<div class="text-left text-left border-bottom-underline" style="float: left;border-bottom-width: 13px;" >
				Agency Master
				</div>
				<br>
			
			<!-- Create Agency Form -->
			<div class="row">
				<div class="col-lg-12">
			
					<!-- <div class="panel panel-default">
						
						<div class="panel-heading">
						<span style="float:right; height='34px';">
								<a href="downloadAgencyMaster"> 
									<img src="dist/img/csv-64.png" alt="csv"
									 height="30" width="38">
								</a> &nbsp;						
							</span>
							<b>Create New Agency</b> 
						</div> -->
						<div class="panel panel-default">
						 <span
												style="float: right;margin-top: 0.44pc;"> <a  onclick="validateCSV();">  <img src="dist/img/csv-64.png"
												alt="csv" height="30" width="35">
											</a>
											</span>
						<div class="panel-heading" style="padding-top: 13px;">
					<b>Create New Agency</b></div>
					<!-- Status message -->
					<h5 align="center" style="color: #FF0000;" id="status"> <c:out value="${strStatus}"/></h5>
						
						
					<br>
					<br>
				
						<div class="panel-body">
						<div class="col-lg-2" style="padding-left: 8px; width: 204.92px;">
							<label>Agency Name</label>
							<form:input type="text" class="form-control" path="agencyname" id="agencyname" name="agencyname" />
						</div>
						
						<div class="col-lg-2"style="padding-left: 8px; width: 204.92px;">
							<label>Select State</label>
							<form:select class="form-control" id="state" name="state" path="stateId" onChange="loadCities();">
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
								<div class="col-lg-2"style="padding-left: 8px; width: 204.92px;">
									<label>Select City</label>
									<form:select id="city" class="form-control" name="city" path="cityId">
										<option disabled selected value="-1">-- Select --</option>
									</form:select>
								</div>
								<br>
							
							<div class="col-lg-2" style="padding-top: 5px;">
							<input  type="button" class="btn btn-primary" id="savingOnclick" value="&nbsp;Add&nbsp;&nbsp;"
									style="width: 100px;">
							</div>
						</div>
			
			<!-- /.row -->
			<input type="hidden" name="size" id="size" value="${fn:length(agencyList)}"/> 
			<div class="row">
				<div class="col-lg-12">
						<div class="panel-body">
							<table  class="table table-striped table-bordered table-hover" id="tablefeilds">
								<thead>
									<tr class="tr_color">
											
										<!-- <th class="text-center">Status</th> -->
										<th class="text-center">Agency Name</th>
										<th class="text-center">State</th>
										<th class="text-center">City</th>
										<th class="text-center">IsActive</th>	
										
										
									
									</tr>
								</thead>
								<tbody>
										
								<c:forEach var="itemList" items="${agencyList}">
								
							
								
								<tr class="even gradeC">
																
																
				
																<td class="text-left" rowspan="1" colspan="1" style="width: 272.021px;"><c:out value="${itemList.agencyname}"></c:out></td>
																<td class="text-left"rowspan="1" colspan="1" style="width: 272.021px;"><c:out value="${stateMap[itemList.stateId]}"></c:out></td>
																<td class="text-left"rowspan="1" colspan="1"style="width: 272.021px;"><c:out value="${cityMap[itemList.cityId]}"></c:out></td> 
																
												
											<c:if test="${itemList.isActive == '0'}">
												<td class="text-center">
												<input type="hidden" id="venId" name='agenId' value= "${itemList.id}"/>
												<input type="button" id="${itemList.id}"style="width:85px" class="btn btn-primary" onclick="blockAgency(${itemList.id}, this)" 
												value="&nbsp;Un Block&nbsp"></td>
											</c:if>
											<c:if test="${itemList.isActive == '1'}">
												<td class="text-center">
												<input type="hidden" id="venId" name='agenId' value= "${itemList.id}"/>
												<input type="button" id="${itemList.id}" style="width:85px" class="btn btn-primary" onclick="blockAgency(${itemList.id}, this)" 
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
		
								 
			
 		<!-- /.row -->

		
		<!-- /#page-wrapper -->

	
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
	<!--  <script src="js/agencyMaster.js"></script>  -->
	
	
    <script>
  
    
  /*   function blockAgency(value)
    
    {
    	document.createAgency.action = "lockAgency?agenId="+value;
		document.createAgency.method = "POST";
		document.createAgency.submit();
    } 
    function unblockAgency(value)
    {
    	document.createAgency.action = "unlockAgency?agenId="+value;
		document.createAgency.method = "POST";
		document.createAgency.submit();
	} */

	// Saving new agency here	
	 
				$("#savingOnclick").click(function(){
					
					var agency=document.createAgency.agencyname;
					var agen=/^[0-9a-zA-Z]+$/;
					if (document.getElementById("agencyname").value == '') {
						alert('Please Enter Agency');
						$("#agencyname").focus();
						return false;
					}else if(agen.test(agency.value) == false){
						
						alert("Agency Name Should be Alphanumeric ::");
						$("#agencyname").focus();
						return false;
						
					}else if (document.getElementById("state").value == '-1') {
						alert('Please Select State.');
						$("#state").focus();
						return false;			
					}else if (document.getElementById("city").value == '-1') {
						alert('Please Select City.');
						$("#city").focus();
						return false;
					}else{
						
						document.createAgency.action = "saveNewAgency";
						document.createAgency.method = "POST";
						document.createAgency.submit();
					}
				
				});
				

   
    </script>
    <script type="text/javascript">
    $("#agencyname").change(function(){
    	
        var agencyname1 = $(this).val();
        $.ajax({
            type: "POST",
            url: "checkAgency",
            data: {"agencyname": agencyname1},
            success: function(msg){

                  if(msg == 1){
                alert("AgencyName exists !... try another");
                  document.getElementById('agencyname').value = "";
                  $('#agencyname').focus();
                  }
            },
            error:function(){
            }
        });

    });
    
    var currentBtn = null;
	function blockAgency(id, btn){
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
		url = "lockAgency?agenId="+id;   
	}else{
		url = "unlockAgency?agenId="+id;
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
    <script type="text/javascript">
    function validateCSV(){
    	var size=document.getElementById("size").value;
    	//alert("size of list is...."+size);
    	if(size!=0){
    		document.createAgency.action = "downloadAgencyMaster";
			document.createAgency.method = "GET";
			document.createAgency.submit();
    		
    	}else{
    		alert("No Content is available for Download... ");
    	}
    	
    	
    	
    }
    
    </script>
	
	
		
</body>
</html>

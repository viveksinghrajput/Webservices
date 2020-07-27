<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %><html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Finance DashBoard</title>
<jsp:include page="header.jsp" />
<!-- CSS goes in the document HEAD or added to your external stylesheet -->
<%
	if (session.getAttribute("mapMenu") == null) {
		response.sendRedirect("login.jsp");
	}
%>
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


<script type="text/javascript">
	
	function createRequest() {
		if (document.getElementById("completionRptDesc").value == '') {
			alert('Please Enter completionRptDesc.');
			return false;

		}
		
		document.financeForm.action = "createFinanceRequest";
		document.financeForm.method = "POST";
		document.financeForm.submit();

	}
</script>
</head>


<body>
	<form:form name="financeForm" autocomplete="off" method="post"
		enctype="multipart/form-data" modelAttribute="finance">
		<input type="hidden" name="size" id="size" value="${fn:length(myAssignedList)}"/> 
		<input type="hidden" name="size1" id="size1" value="${fn:length(allRequestsList)}"/> 
		<div id="wrapper">

			<div id="page-wrapper">
				<div class="row">
					<div class="col-lg-12">
						<h3 class="border-bottom-underline">Finance</h3>
					</div>
				</div>
				<!-- /.row -->
				<div class="row">
					<div class="col-lg-12">
						<div class="panel panel-default">
					
						<div class="panel-heading" style="padding-top: 10px;">
<b>Finance</b></div>								<!-- /.row -->
<div class="panel-body">
<input type="hidden" name="finId" id="finId">
<input type="hidden" name="statusId" id="statusId">
<input type="hidden" name="isEdit" id="isEdit">

<c:if
									test="${userRole == 'Audit' || userRole == 'Admin'}">
								
							<div class="col-lg-2" style="padding-left: 2px;">
							
								<a href="createFinRequest" class="btn btn-primary" style="margin-top: 1pc;">Create Request</a>
								
							</div></c:if>
						</div>	
								<div align="center">
								<h5 align="center" style="color: #FF0000;"> <c:out value="${statusMsg}"/></h5>
						</div>
														<c:set var="myRequest" scope="session"
															value="${myAssignedList}" />
														<div class="row">
															<div class="col-lg-12">

																<div class="panel panel-default">
																	<div class="panel-heading">
																		<b>Action Needed</b> 
																		<span style="float: right;"> <a onclick="validateCSV();"> <img src="dist/img/csv-64.png"
alt="csv" height="30" width="35">
</a>
</span><br>
																	</div>



							<div class="row" id="showAllPrePlanTable">
								<div class="col-lg-12">
									
										<div class="panel-body" style="margin-top: 1pc;">
											<table width="100%"
												class="table table-striped table-bordered table-hover"
												id="tablefeilds">
												<thead>
													<tr class = "tr_color">
														<th class="text-center">Req Num</th>
														<th class="text-center">Completion Report</th>
														<th class="text-center">Agency</th>
														<th class="text-center">Month</th>
														<th class="text-right">Year</th>
														<th class="text-center">Date of Request</th>
														<th class="text-center">Status</th>
													</tr>
												</thead>
												<tbody>
													<c:forEach var="myAssingedReuest"
														items="${myAssignedList}">
									<c:if test="${myAssingedReuest.status!='7'}">
															<tr style="background-color:#ffffcc">
															</c:if>
															<c:if test="${myAssingedReuest.status=='7'}">
															<tr style="background-color:#e3fbe3">
															</c:if>			
																	<td class="text-center">
															<input type="hidden" id="financeId" name="financeId" value="${myAssingedReuest.finId}">
															<a id="myList" href = "#" onClick = "myList(${myAssingedReuest.finId},${myAssingedReuest.status});" <%-- href="viewRequest?finId=${myAssingedReuest.finId}&isEdit=Y" --%>><c:out
																		value="${myAssingedReuest.reqNum}"></c:out></a></td>
															<td class="text-center"><c:out
																	value="${myAssingedReuest.completionRptDesc}"></c:out></td>
															<td class="text-center"><c:out
																	value="${agencyMap[myAssingedReuest.agencyId]}"></c:out></td>
															<td class="text-center"><c:out
																	value="${myAssingedReuest.month}"></c:out></td>
															<td class="text-center"><c:out
																	value="${myAssingedReuest.year}"></c:out></td>
														<%-- 	<td class="text-right"><c:out
																	value="${deliveryAndInventory.no_Item_Req}"></c:out></td>--%>
															<td class="text-center"><fmt:formatDate value="${myAssingedReuest.createdDate}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
															<td class="text-center"><c:out
																	value="${statusMap[myAssingedReuest.status]}"></c:out></td>
													 </c:forEach>



												</tbody>

											</table>
</div></div></div>
										</div>
									</div>
								</div>
								
																
														<c:set var="allRequests" scope="session"
															value="${allRequestsList}" />
														
														<div class="row">
															<div class="col-lg-12">

																<div class="panel panel-default">
																	<div class="panel-heading">
																		<b>View All Requests</b> 
																		<span style="float: right;"> 
																		<a onclick="validateCSV1();">
																		<img src="dist/img/csv-64.png"alt="csv" height="30" width="35"></a>
</span><br>
																	</div>


							<div class="row" id="showAllPrePlanTable">
								<div class="col-lg-12">
									
										<div class="panel-body" style="margin-top: 1pc;">
							<table class="table table-striped table-bordered table-hover" 
							id="allDataTable">
												<thead>
													<tr class = "tr_color">
														<th class="text-center">Req Num</th>
														<th class="text-center">Completion Report</th>
														<th class="text-center">Agency</th>
														<th class="text-center">Month</th>
														<th class="text-right">Year</th>
														<th class="text-center">Date of Request</th>
														<th class="text-center">Status</th>
													</tr>
												</thead>
												<tbody>
													<c:forEach var="allRequests"
														items="${allRequestsList}">
												
														<c:if test="${allRequests.status!='7'}">
															<tr style="background-color:#ffffcc">
															</c:if>
															<c:if test="${allRequests.status=='7'}">
															<tr style="background-color:#e3fbe3">
															</c:if>	
														
																<td class="text-center">
															
															<a href = "#" onClick = "allList(${allRequests.finId},${allRequests.status});" <%-- href="viewRequest?finId=${myAssingedReuest.finId}&isEdit=Y" --%>><c:out
																		value="${allRequests.reqNum}"></c:out></a></td>
															<td class="text-center"><c:out
																	value="${allRequests.completionRptDesc}"></c:out></td>
															<td class="text-center"><c:out
																	value="${agencyMap[allRequests.agencyId]}"></c:out></td>
															<td class="text-center"><c:out
																	value="${allRequests.month}"></c:out></td>
															<td class="text-center"><c:out
																	value="${allRequests.year}"></c:out></td>
															<td class="text-center"><fmt:formatDate value="${allRequests.createdDate}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
															<td class="text-center"><c:out
																	value="${statusMap[allRequests.status]}"></c:out></td>
													 </c:forEach>



												</tbody>

											</table>
</div></div></div>
										</div>
									</div>
								</div>
								
								
							</div>
						</div>
					</div>
				</div>	</div>
				<!-- /.row -->


	</form:form>

</body>


<!-- </div> -->

<!-- /#wrapper -->
	<script src="vendor/jquery/jquery.min.js"></script>
	<!-- common css styles class -->
	<link href="dist/css/common.css" rel="stylesheet">

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
	<script>
$(document).ready(function() {
    $('#myDataTable').DataTable({
        "ordering" : false
       
    });
});
$(document).ready(function() {
    $('#allDataTable').DataTable({
        "ordering" : false
       
    });
});
<%-- 
$(document).ready(function(){
        $("myList").click();
        alert('finance Id:ddddddddddddd: '+$('#financeId').val());
        href="viewRequest?finId=${myAssingedReuest.finId}&isEdit=Y"
      /*   $.ajax({
			contentType : "application/json",
			type : "POST",
			url : "viewRequest?finId=" + $('#financeId').val(),
        });	 */
        
        document.deliveryForm.action = "updateInventories";
		document.deliveryForm.method = "POST";
		document.deliveryForm.submit();	   
}); --%>
function myList(finId,statusId){
	document.getElementById("finId").value=finId;
	document.getElementById("statusId").value=statusId;
	document.getElementById("isEdit").value='Y';
	 document.financeForm.action = "viewRequest";
		document.financeForm.method = "POST";
		document.financeForm.submit();	  
	
}

function allList(finId,statusId){
	document.getElementById("finId").value=finId;
	document.getElementById("statusId").value=statusId;
	document.getElementById("isEdit").value='N';
	 document.financeForm.action = "viewRequest";
		document.financeForm.method = "POST";
		document.financeForm.submit();	  
	
}

</script>

 <script type="text/javascript">
                
                function validateCSV(){
                  var size=document.getElementById("size").value;
            
                  if(size!=0){
                    
                    document.financeForm.action = "exportToCSVForFinance?name=myActionItems";
                    document.financeForm.method = "POST";
                    document.financeForm.submit();   
                    
                  }else{
                    alert("No Content is available for Download... ");
                  }
                }
                </script>
                <script type="text/javascript">
                
                function validateCSV1(){
                  var size=document.getElementById("size1").value;
                  
                  if(size!=0){
                	 
                    document.financeForm.action = "exportToCSVForFinance?name=allActionItems";
                    document.financeForm.method = "POST";
                    document.financeForm.submit();   
                    
                  }else{
                    alert("No Content is available for Download... ");
                  }
                }
                </script>

</html>
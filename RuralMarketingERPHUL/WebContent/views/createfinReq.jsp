<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%><html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Create Request-Finance</title>
<jsp:include page="header.jsp" />
<!-- CSS goes in the document HEAD or added to your external stylesheet -->
<%
	if (session.getAttribute("mapMenu") == null) {
		response.sendRedirect("login.jsp");
	}
%>

<script type="text/javascript">
function cancel(){
	document.getElementById("financeForm").reset();
}
	
	function createRequest() {
		
		 var dt = new Date();
		
		/* var months = ["Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"];
		var m = months[dt.getMonth()]; //you get the idea...
		 */
		 var m = dt.getMonth();
		 var y = dt.getFullYear();
		
		if(document.getElementById("agency").value == '-1'){
			alert('Please Select Agency');
			return false;
	    		 
	    	 }
		if(document.getElementById("month").value == '-1'){
			alert('Please Select Month');
			return false;
	    		 
	    	 }
		if(document.getElementById("year").value == '-1'){
			alert('Please Select Year');
			return false;
	    		 
	    	 }
		if( document.getElementById("month").value != '-1' && document.getElementById("year").value != '-1'){
		if(document.getElementById("month").value>m && document.getElementById("year").value==y)
		{
		alert('Please Select Before Month');
		return false;
		}
		}
	
		if (document.getElementById("completionRptDesc").value == '') {
			alert('Please Enter completionRptDesc.');
			return false;

		}		
	 			 if(document.getElementById("excel_file").value==''){
				alert('Please Upload File');
				return false;
			}
		 if(document.getElementById("remarks").value == ''){
				alert('Please Enter Remarks');
				return false;
		 }
		 
		 
		
		document.financeForm.action = "createFinanceRequest";
		document.financeForm.method = "POST";
		document.financeForm.submit();

	}
</script>
</head>


<body>
	<form:form name="financeForm" id="financeForm" autocomplete="off" method="post"
		enctype="multipart/form-data" modelAttribute="finance">
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
							<a class="glyphicon glyphicon-circle-arrow-left" href="completionReport"
							style="font-size: 24px;color: #337AB5;text-decoration: none;"></a>
		
<label style=" margin-left: 10px; vertical-align: bottom;">Finance</label></div>								<!-- /.row -->
									<div class="row">
										<div class="col-lg-12">
												<div class="panel-body" style="margin-top: 1pc;">
<div align="center">
								<h5 align="center" style="color: #FF0000;"> <c:out value="${statusMsg}"/></h5>
						</div>
													<div class="row">
														<div class="col-xs-2">
															<label>Agency&nbsp;*</label> 
															<form:select 
																class="form-control" id="agency" name="agency" path="agencyId">
																<option value="-1" selected>-- SELECT --</option>
																<c:forEach var="agency" items="${agencyMasterList}">
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
										
														<br>
													<%-- 		<div class="col-xs-2">
															<label style="margin-left: -0.7pc;">Remarks&nbsp;*</label>
															<div class="form-group">
							 <form:textarea class="form-control" rows="5" id="remarks" name="remarks" onkeypress="if (this.value.length > 1000) { return false; }" path="history[0].remarks" style="resize: none;width: 595px;margin-left: -0.7pc;"></form:textarea>
															</div> --%>
															 <div class="col-xs-6">
															<label style="margin-left: -0.7pc;">Remarks&nbsp;*</label>
													<div class="form-group">
							 <form:textarea  class="form-control"  rows="5" id="remarks" name="remarks" onkeypress="if (this.value.length > 1000) { return false; }" path="history[0].remarks" style="resize: none;width: 495px;margin-left: -0.7pc;"></form:textarea>
															<h6 class="pull-right" id="count_message"></h6>
															</div>
															
															
														</div>
															
														</div>

																		<div class="col-xs-2">
															<label>Month&nbsp;*</label> 
															<form:select 
																class="form-control" id="month" name="month" path="month">
																<option value="-1">-- SELECT --</option>
																<%-- <c:forEach var="month" items="${monthList}">
																<option value="${month}">${month}</option>
																	<c:choose>
																		<c:when
																			test="${not empty selected_city && selected_city eq city}">
																			<option value="${selected_city}" selected>${selected_city}</option>
																		</c:when>
																		<c:otherwise>
																			<option value="${city}">${city}</option>
																		</c:otherwise>
																	</c:choose>
																</c:forEach> --%>
																
																<c:forEach var="month" items="${monthMap}">
                              
                                    <option value="${month.key}"><c:out value="${month.value}"/></option>
                                								</c:forEach>
															</form:select>
														</div>
														<div class="col-xs-2">
															<label>Year&nbsp;*</label>
															 <form:select 	class="form-control" id="year" name="year" path="year">
																<option value="-1">-- SELECT --</option>
																<c:forEach var="year" items="${yearList}">
																	<option value="${year}">${year}</option>
															<%-- 		<c:choose>
																		<c:when
																			test="${not empty selected_warehouse && selected_warehouse eq warehouse}">
																			<option value="${selected_warehouse}" selected>${selected_warehouse}</option>
																		</c:when>
																		<c:otherwise>
																			<option value="${warehouse}">${warehouse}</option>
																		</c:otherwise>
																	</c:choose> --%>
																</c:forEach>
															</form:select>
														</div>
													<div class="col-xs-2">
															<label>Completion Report *</label>
															<form:input class="form-control"  path="completionRptDesc"
																	name="completionRptDesc" type="text" value=""></form:input>
															</div>
														 <div class="col-xs-2">
														  <label>File *</label><br>
                                            <input type="file" class="form-control image_uploader_with_fname"  name="excel_file"  id="excel_file" accept=".xlsx" capture style="display:none"/>
<img src="dist/img/Upload1.jpg" id="upexcel_file" style="cursor:pointer;height: 30px;" />

                                        </div>
														
													</div><div align="center">
															<input type="button" class="btn btn-primary"
																onclick="createRequest()" value="Create Request">
																
																<input type="button" class="btn btn-primary" style="width: 8pc;"
																onclick="cancel()" value="Cancel">
																</div>

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

<!-- jQuery -->
<script src="vendor/jquery/jquery.min.js"></script>

<!-- Bootstrap Core JavaScript -->
<script src="vendor/bootstrap/js/bootstrap.min.js"></script>

<!-- Metis Menu Plugin JavaScript -->
<script src="vendor/metisMenu/metisMenu.min.js"></script>

<!-- DataTables JavaScript -->
	<script src="vendor/datatables/js/jquery.dataTables.min.js"></script>
	<script src="vendor/datatables-plugins/dataTables.bootstrap.min.js"></script>
	<script src="vendor/datatables-responsive/dataTables.responsive.js"></script>

<!-- Custom Theme JavaScript -->
<script src="dist/js/sb-admin-2.js"></script>
<!-- BootBox Custom Bootstrap Modal PopUp-->
<script src="dist/js/bootbox.min.js"></script>
	<!-- Custom JavaScript For Warehouse-->
	<script src="js/warehouse.js"></script>


<script>
$("#upexcel_file").click(function () {
    $("#excel_file").trigger('click');
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
</script>


</html>
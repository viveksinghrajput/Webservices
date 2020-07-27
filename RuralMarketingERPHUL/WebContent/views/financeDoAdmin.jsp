<%@page import="com.rural.Model.PrePlanMaster"%>
<%@page import="java.util.*"%>
<%@ page errorPage="error.jsp"%>
<!DOCTYPE html>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html lang="en">

<head>
<title>Finance - DO Details</title>
<jsp:include page="header.jsp" />

<link rel="stylesheet" href="dist/css/jquery-ui.css">

<style>
.table {
	overflow: scroll;
}

#lsm {
	width: 150px;
}

#lsm option {
	width: 150px;
}

#brand {
	width: 150px;
}

#brand option {
	width: 150px;
}
#target_amount  {
	width: 150px;
}
#tab_content_area_cliked_panel .panel-body, #tab_content_area_cliked_panel .ui-tabs-panel {
	padding-top: 0;
}
</style>





<%
						
							if(session.getAttribute("mapMenu")==null)
						    {
						        response.sendRedirect("login.jsp");
						    }
%>
</head>
<body>
	<form:form name="financeForm" id="financeForm" autocomplete="off" method="post"
		enctype="multipart/form-data" modelAttribute="finance">
<input type="hidden" name="size" id="size" value="${fn:length(doList)}"/>
		<div id="wrapper">

			<div id="page-wrapper">
				<div class="row">
					<div class="col-lg-12">
						<h3 class="border-bottom-underline">Finance -DO Admin
					</div>
				</div>
				<input type="hidden" name="doNumber" id="doNumber">
				<div class="row">
					<div class="col-lg-12">
						<div id="tab_content_area_cliked_panel" class="panel panel-default">
								<!-- Start Creating Tabs -->
								<div id="tabs">
									<ul id="tab_content_area_cliked">
										<li><a href="#tabs-1">DO List</a></li>
										<li><a href="#tabs-2">DO Upload</a></li>
									</ul>
									<input type="hidden" value="#tabs-1" name="current_tab" id="current_tab">
									<div id="tabs-1">
										<p>
										<div class="panel-body" style="margin-left: -1.8pc;">
									<h5 align="center" style="color: #FF0000;"> <c:out value="${statusMsg}"/></h5>
									
														<div class="row" id="showAllPrePlanTable">
															<div class="col-lg-12">
																<!-- <div class="panel panel-default">
																	<div class="panel-heading">
																		<b>Show All Pre-Plans </b> 
																				<span style="float: right;"> <a
														href="exportToCSVForPrePlan"> <img src="dist/img/csv-64.png"
														alt="csv" height="30" width="35">
														</a>
														</span><br>
																	</div>
															 -->		
															 
															 <div class="panel panel-default">
						 <span
												style="float: right;margin-top: 0.44pc;"> <a onclick="validateCSV();"> <img src="dist/img/csv-64.png"
												alt="csv" height="30" width="35">
											</a>
											</span>
						<div class="panel-heading" style="padding-top: 13px;">
								<b>Show DO List</b></div>
					<br>
						<div class="panel-body">
							<table class="table table-striped table-bordered table-hover"id="tablefeilds">
									<thead>
											<tr class = "tr_color">
														<th class="text-center" >Do Number</th>
														<th class="text-center" >Worth</th>
														<th class="text-center" >Utilized Amount</th>
														<th class="text-center" >Balance Amount</th>
														<th class="text-center" >Valid From</th>
														<th class="text-center" >Valid To</th>
														
											</tr>
									</thead>
																			<tbody>
																		
																				<c:forEach var="doMaster" items="${doList}">
																				
																				<c:choose>
																				<c:when test="${submitDate <now}">
																					<tr style="background-color:#ffffcc">
																					
																				</c:when>
																				<c:otherwise><tr>
																				</c:otherwise>
																				</c:choose>
																					<tr>	
																					<td>
														
															<a id="myList" style = "color: #1c25de;" href = "#" onClick = "viewDOReport('${doMaster.doNumber}');"><c:out
																		value="${doMaster.doNumber}"></c:out></a></td>
										<td class="text-right change_to_thousand_seprator"><c:out value="${doMaster.doValue}"></c:out></td>
										<td class="text-right change_to_thousand_seprator"><c:out value="${doMaster.doValue - doMaster.doBalance}"></c:out></td>
										<td class="text-right change_to_thousand_seprator"><c:out value="${doMaster.doBalance}"></c:out></td>
										<td class="text-center"><c:out value="${doMaster.validFrom}"></c:out></td>
										<td class="text-center"><c:out value="${doMaster.validTo}"></c:out></td>
										
																		</tr>
																			</c:forEach>
																			</tbody>
																		</table>
																	</div>
																</div>
															</div>
														</div><br>
										</p>
									</div></div>
									<!-- End of TAB 1 -->
									<div id="tabs-2">
										<p>
										<div class="panel-body">
											<%-- <div align="center">
					<label class="error_message" id="prePlanCreatedMsg">${statusMsgForView }</label>
				</div> --%>
				<h5 align="center" style="color: #FF0000;"> <c:out value="${status}"/></h5>
										<div class="panel-body">
															<div class="row">
														
														 <div class="col-xs-2">
														  <label>File *</label><br>
                                            <input type="file" class="form-control image_uploader_with_fname"  name="file_upload"  id="file_upload" accept=".csv" capture style="display:none"/>
<img src="dist/img/Upload1.jpg" id="upfile_upload" style="cursor:pointer;height: 30px;" />

                                        </div>
														
													</div><div align="center">
															<input type="button" class="btn btn-primary"
																onclick="uploadDO()" value="Upload">
																
																<input type="button" class="btn btn-primary" style="width: 8pc;"
																onclick="cancel()" value="Cancel">
																</div>

												</div></div>
										</p>
								</div>
								<!-- End Creating Tabs -->

							</div>
							<!-- End of Panel Body -->
						</div>
						<!-- End of Panel Default -->
					</div>
					<!-- End of Col-lg-12 -->
				</div>
				<!-- End of Row -->

				<!-- /.row -->

			</div>
			<!-- /#page-wrapper -->

		</div>
		<!-- /#wrapper -->

		<!-- jQuery -->
		<script src="vendor/jquery/jquery.min.js"></script>
		<script src="vendor/jquery/jquery-ui.js"></script>

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
		
	
		
	<script type="text/javascript">
	$(document).ready(function() {
		
		var change_to_thousand_seprator = $( ".change_to_thousand_seprator" );
		var elem = $();
		for( var i = 0; i < change_to_thousand_seprator.length; i++ ){
			var elem = $( change_to_thousand_seprator[i] );
			if( elem.is( "input" ) ){
				elem.val( parseFloat( parseFloat( elem.val().trim() ).toFixed(2) ).toLocaleString('en-IN' , { minimumFractionDigits: 2 }) );
			} else {
				elem.html( parseFloat( parseFloat( elem.text().trim() ).toFixed(2) ).toLocaleString('en-IN' , { minimumFractionDigits: 2 }) );
			}
		}
		
	});
	function uploadDO() {
		
		 if(document.getElementById("file_upload").value==''){
		alert('Please Upload File');
		return false;
	}
		 /* else{
			alert('enterd');
			jQuery.ajax({
			
				type : "POST",
		        url: "uploadDODetails?file_upload=" + $("#file_upload").val() ,
		        success: function (data) {
		        	alert('hiiii');
		        	window.location.href = "uploadDODetails";
		        	/*  if(data=='Success')
		        		 {
		        		  window.location.href = "uploadDODetails";
		        		 }
		        	 else{
			        	 document.getElementById("statusMsg").innerHTML=data;
		        	 } 
		        },
		        error: function (data) {
		            alert(data + "error");
		        }}); */
document.financeForm.action = "uploadDODetails";
document.financeForm.method = "POST";
document.financeForm.submit();  
		 }
	
	$("#upfile_upload").click(function () {
	    $("#file_upload").trigger('click');
	});
	
		$(function() {
			$("#tabs").tabs();
		});
		$( document ).ready( function(){
			$(document).on( "click", "#tab_content_area_cliked li", function(){
				$( "#current_tab" ).val( $( this ).find( "a" ).attr( "href" ) );
			});
			
			$("#tab_content_area_cliked").find( "a[href='<%= request.getParameter("current_tab") %>']" ).trigger("click");
		} );
		
		function viewDOReport(value){
			document.getElementById("doNumber").value=value;
			 document.financeForm.action = "viewDODetails";
				document.financeForm.method = "POST";
				document.financeForm.submit();	  
			
		}
		
		$(document).ready(function() {
		    $('#dataTables-doList').DataTable({
		    	scrollCollapse : true,

				paging : true,

		       
		    });
		});

	</script>
	<script type="text/javascript">
                
                function validateCSV(){
                  var size=document.getElementById("size").value;
                  alert(size);
                  if(size!=0){
                    
                    document.financeForm.action = "exportToCSVForDoList";
                    document.financeForm.method = "GET";
                    document.financeForm.submit();   
                    
                  }else{
                    alert("No Content is available for Download... ");
                  }
                }
                </script>
	
	</form:form>


</body>

</html>

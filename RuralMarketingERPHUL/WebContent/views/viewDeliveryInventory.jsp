<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@page import="java.io.File"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%><html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>View Delivery And Inventory</title>
<style>
#diveryForm input 
{
border: 0;
} 
.modal-dialog 
{ width: 90% !important; 
}
.labelFont 
{
margin-left: 0px;
margin-top: 0px;
}
.inputFont
{ 
width: 13%;
padding:12px 20px;
margin: 8px 0;
display: inline-block;
border: 1px solid #ccc;
border-radius: 4px;
box-sizing: border-box; 
}


.imagediv {
	float:left;
    margin-top:50px;
}
.imagediv .showonhover {
	background:red;
	padding:20px;
	opacity:0.9;
	color:white;
	width: 100%;
	display:block;	
	text-align:center;
	cursor:pointer;
}

<style>
.error_message {
	margin-top: 0;
	margin-bottom: 0;
	color: #FF0000;
}

</style>
<jsp:include page="header.jsp" />
<!-- CSS goes in the document HEAD or added to your external stylesheet -->
<%
						
							if(session.getAttribute("mapMenu")==null)
						    {
						        response.sendRedirect("login.jsp");
						    }
%>

<script type="text/javascript">
function getWarehouses()
{
		$.ajax({
			contentType : "application/json",
			type : "POST",
			url : "loadWarehouses?city=" + $('#city').val(),
			success : function(data) {
				var warehouses = new Array();
				warehouses = data;
				var select = document.getElementById("warehouse");
				$('#warehouse option[value!="-1"]').remove();
				for (var i = 0; i < warehouses.length; i++) {
					select.options[select.options.length] = new Option(
							warehouses[i], warehouses[i]);
				}
			}
		});
}

function loadCities()
{
		$.ajax({
			contentType : "application/json",
			type : "POST",
			url : "loadCities?state=" + $('#state').val(),
			success : function(data) {
				var cities = new Array();
				cities = data;
				var select = document.getElementById("city");
				$('#city option[value!="-1"]').remove();
				for (var i = 0; i < cities.length; i++) {
					select.options[select.options.length] = new Option(
							cities[i], cities[i]);
				}
			}
		});
}


function createRequest()
{
	if (document.getElementById("brand").value == '-1') {
		alert('Please Select Brand.');
		return false;

	}
	if (document.getElementById("campaign").value == '') {
		alert('Please Enter Cmpaign.');
		return false;

	}
	if (document.getElementById("state").value == '-1') {
		alert('Please Select  state.');
		return false;

	}
	if (document.getElementById("warehouse").value == '-1') {
		alert('Please Select Warehouse.');
		return false;

	}
	if (document.getElementById("no_of_kits").value == '') {
		alert('Please Enter No of Kits.');
		return false;

	}
	document.deliveryForm.action = "createDeliveryAndInventory";
	document.deliveryForm.method = "POST";
	document.deliveryForm.submit();	
	
}
 
/*   $(document).ready(function(){
	 $('input[type="checkbox"]').click(function(){
		 var target  = $(this);
	        if(target.prop("checked") == true){
	       	 var checkedvalue = target.closest("tr").find( "td:nth-child(2)" ).text();

	        }
	 });
 });
   */

 $(document).ready(function(){
	 $('input[type="checkbox"]').click(function(){
		 var target  = $(this);
	        if(target.prop("checked") == true){
		       	 var checkedvalue = target.closest("tr").find( "td:nth-child(2)" ).text();
 document.deliveryForm.action = "updateStatus?reqNo=" + $('#req_num').val()+"&user=" + checkedvalue;
	document.deliveryForm.method = "POST";
	document.deliveryForm.submit();	
	        }
	 });
 });
 
</script>
<script type="text/javascript">


	
$(document).ready(function(){
    $("td").click(function(){
        $("iddd").click();
        $.ajax({
			contentType : "application/json",
			type : "POST",
			url : "getDate?reqNo=" + $('#req_num').val(),
        });			
    });
});

function cancelInventory(){
	
//	$("input[type=text], text").val("");
	document.getElementById("deliveryForm").reset();
}
$(document).ready(function(){
    
    
    $('#saveInventories').click(function(){
    	
        if('${userRole}'=='Business Team'){
             return false;
        }
    	
    	var checkboxes  = $( "#viewInventory input[type=checkbox]" ),
    		checkboxflg = false,
    	 	allTextBoxed =  $( "#viewInventory tr input[type=text]:visible" ),
    		allFieldVal = false,
    		textFieldss  = $( "#viewInventory input[name=no_of_kits_received]" ),
    		editedValList = [];
    	var count='${userCount}';
    	
    		for( var i = 0 ; i < allTextBoxed.length ; i++){
    			if( !allFieldVal && $( allTextBoxed[i] ).val().trim() != "" ){
    				allFieldVal = true;
    			}
    		}
    		/* if( !allFieldVal ){
        		alert("Some of the row all fields are empty.");
        		 return false;
        	} */
    		
    		for( var i = 0 ; i < checkboxes.length ; i++){
    			if( !checkboxflg  ){
	    			if( !$( checkboxes[i] ).prop( "disabled" ) && !$( checkboxes[i] ).prop( "checked" ) ){
	    				checkboxflg = true;
	    			}
    			}
    		}
    	if( checkboxflg || !allFieldVal){
    		alert("Some of the row all fields are empty or Some of the enabled checkbox not checked.");
    		 return false;
    	}
    	
    
    	
    	if( allFieldVal && checkboxflg){
    		 return false;
    	}
    	
    	
    	for( var i = 0 ; i < textFieldss.length ; i++){
    		if( $( textFieldss[i] ).val().trim() != "" ){
    			editedValList.push( { "no_of_kits_received" : $( textFieldss[i] ).val(), "user" : $( textFieldss[i] ).closest( "tr" ).find( ":nth-child(2)" ).text(), "no_of_kits_dispatched_logi" : $( textFieldss[i] ).closest( "tr" ).find( "input[name=no_of_kits_dispatched_logi]" ).val(), "logi_remarks" :  $( textFieldss[i] ).closest( "tr" ).find( "input[name=logi_remarks]" ).val()  } );
    		}
    	}
    	
    	$( "#product_list_info" ).val( JSON.stringify( editedValList ) );

    	var textFields  = $( "#viewInventory input[name=no_of_kits_dispatched_logi]" );
    	
    	for( var i = 0 ; i < textFields.length ; i++){
    		if( $( textFields[i] ).val().trim() != "" ){
    			editedValList.push( {  "user" : $( textFields[i] ).closest( "tr" ).find( ":nth-child(2)" ).text(), "no_of_kits_dispatched_logi" : $( textFields[i] ).closest( "tr" ).find( "input[name=no_of_kits_dispatched_logi]" ).val(), "logi_remarks" :  $( textFields[i] ).closest( "tr" ).find( "input[name=logi_remarks]" ).val()  } );
    		}
    	}
    	
    	$( "#product_list_info" ).val( JSON.stringify( editedValList ) );

    	
  if('${userRole}'=='Logistics'){
		 if(document.getElementById("no_of_kits_dispatched_logi").value != ''){
			 var validation_flg = true,
				entry_field = $( "input.no_of_kits_dispatched_logi" ),
				check_val = "checked_for_first_time";
				for( var i = 0; i < entry_field.length; i++ ){
					if( validation_flg ){
						if( $( entry_field[i] ).val().trim() == ''  ||  ( i != 0 && check_val != $( entry_field[i] ).val().trim() ) || count!=entry_field.length){
							validation_flg = false;
						} else {
							check_val = $( entry_field[i] ).val().trim();
						}
					}
				}

				if( !validation_flg ){
					alert( "Please fill all the fields and should be same." );
					return false;
				}     
				if( validation_flg ){
			 if(document.getElementById("img_dispatch1").value==''){
					alert('Please Upload Logistics POD');
					return false;
				}
				}
		 }
  }
	 if('${deliveryAndInventory.status}'=='Received by Logistics' && '${userRole}'=='Logistics'){
		 
		 
		 
		 
			    var validation_flg = true,
			entry_field = $( "input.no_of_kits_dispatched_logi" ),
			check_val = "checked_for_first_time";
			    
			    
			    
			    
			    
			    
			for( var i = 0; i < entry_field.length; i++ ){
				if( validation_flg ){
					if( $( entry_field[i] ).val().trim() == ''  ||  ( i != 0 && check_val != $( entry_field[i] ).val().trim() ) || count!=entry_field.length){
						validation_flg = false;
					} else {
						check_val = $( entry_field[i] ).val().trim();
					}
				}
			}

			if( !validation_flg ){
				alert( "Please fill all the fields and should be same." );
				return false;
			}     
			
			 if(document.getElementById("img_dispatch1").value==''){
					alert('Please Upload Logistics POD');
					return false;
				}
			 if(document.getElementById("img_dispatch2").value!=''){
				 if(document.getElementById("img_dispatch1").value==''){
					alert('Please Upload Logistics POD1');
					return false;
				}
			 }
			
	 }
	 
	 
	 if('${deliveryAndInventory.status}'=='Acknowledged by vendor' && '${userRole}'=='Vendor'){
		 
		 if(document.getElementById("img_delivery2").value!=''){
 			 if(document.getElementById("img_delivery1").value == ''){
 				alert('Please Upload Vendor POD1');
 				return false;
 			 }
 			}
 		 if(document.getElementById("img_delivery1").value == ''){
 				alert('Please Upload Vendor POD');
 				return false;
 		 }
 		
	 }
		if('${deliveryAndInventory.status}'=='Delivered to Warehouse'){
				alert('Request has been Processed Successfully');
				return false;

	 }
      	
		// document.deliveryForm.action = "updateStatus?user=" + user;
	  document.deliveryForm.action = "updateInventories";
			document.deliveryForm.method = "POST";
			document.deliveryForm.submit();	   
	   
    });
});    

</script>
</head>


<body>
	<form:form name="deliveryForm" enctype='multipart/form-data' id="deliveryForm">
	<input type="hidden" id="product_list_info" name="product_list_info">
	<div id="wrapper">

		<div id="page-wrapper">
			<div class="row">
				<div class="col-lg-12">
					<h3 class="border-bottom-underline">Delivery And Inventory</h3>
					
					
				</div>
			</div>
			<!-- /.row -->	<!-- <a class="btn btn-primary btn-circle" href="delivery and inventory"
					style="width: 30px;" data-toggle="tooltip"
					data-placement="top"> <i
					class="fa fa-arrow-left"></i>
				</a> -->
				
			<div class="panel panel-default">
						
						<div class="panel-heading">

			<a class="glyphicon glyphicon-circle-arrow-left" href="deliveryandinventory"
												 style="font-size: 30px;top:0.3;color: #337AB5;text-decoration: none;"></a>&nbsp;
												
												<label style=" margin-left: 10px; vertical-align: text-bottom;">Request Number - ${deliveryAndInventory.req_num}</label>	
											</div>	
	<br>
			<%-- <c:if test="${userRole == 'Production Team'}">
			
			<b> , Collateral - ${collateral}</b>
			</c:if> --%>
			<br/>
<!-- 			<div class="row">
				<div class="col-lg-12">
					<div class="panel panel-default">
						<div class="panel-body">
						<a class="btn btn-primary btn-circle" href="delivery and inventory"
					style="width: 30px;" data-toggle="tooltip"
					data-placement="top"> <i
					class="fa fa-arrow-left"></i>
				</a>&nbsp;&nbsp;&nbsp;&nbsp;
				
	<a class="btn btn-primary btn-circle" href="delivery and inventory"
												style="width: 50px; height:48px" data-toggle="tooltip"
												data-placement="top" title="Back"> 
												<i class="fa fa-arrow-left"></i> </a>-->
												<!-- <button type="button" id="temp" class="btn btn-success btn-circle "
				data-toggle="tooltip" data-placement="top" title="Save">
				<i class="fa fa-check"></i>
				</button> -->
<!-- 												<button type="button" id="temp" class="btn btn-success btn-circle btn-lg"><i class="fa fa-check"></i></button><br><br>
 -->				<div class="row">
					<div class="col-lg-12">
							<div class="panel-body">
<div align="center">
					<label class="error_message">${statusMsg }</label>
				</div>
								<div class="row">
														
														<!-- Start of Row -->
														<div class="col-xs-2">
															<label>Brand </label>
															<input class="form-control" id="contact" name="contact"
																value="${deliveryAndInventory.brand}" type="text" readonly
																maxlength="10">
														</div>

														<div class="col-xs-2">
															<label>Campaign </label>
															<input class="form-control" id="latitude" name="latitude"
																value="${deliveryAndInventory.campaign}" type="text"
																readonly>
														</div>
														
														<div class="col-xs-2">
															<label>City</label>
															<input class="form-control" id="email" name="email"
																value="${deliveryAndInventory.city}" type="text" readonly>
														</div>


														<div class="col-xs-2">
															<label>Warehouse </label>
															<input class="form-control" id="longitude"
																name="longitude" value="${deliveryAndInventory.warehouse}"
																type="text" readonly>
														</div>
														
												<c:if test="${deliveryAndInventory.status == 'Acknowledged by Logistics' || deliveryAndInventory.status == 'Received by Logistics'}">		
											 <c:if test="${userRole == 'Admin' || userRole == 'Logistics'}">
												<div class="col-xs-4"> <label>Logistics POD &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
                                            <input type="file" class="form-control image_uploader_with_fname" id="img_dispatch1" name="img_dispatch1" accept="image/gif, image/jpeg, image/png" capture style="display:none"/>
<img src="dist/img/Upload1.jpg" id="upimg_dispatch1" style="cursor:pointer;height: 30px;" />


<input type="file" class="form-control image_uploader_with_fname" id="img_dispatch2" name="img_dispatch2" accept="image/gif, image/jpeg, image/png" capture style="display:none"/>
<img src="dist/img/Upload1.jpg" id="upimg_dispatch2" style="cursor:pointer;height: 30px;" />
                                           <!--  <input type="file" name="img_dispatch2" accept="image/gif, image/jpeg, image/png"/> -->
                                        </div></c:if>
                                        </c:if>
                                        	 	<c:if test="${deliveryAndInventory.status == 'Acknowledged by vendor'}"> 
                                            <c:if test="${userRole == 'Admin' || userRole == 'Vendor'}">
												<div class="col-xs-4"> <label>Vendor POD &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
                                            <input type="file" class="form-control image_uploader_with_fname" id="img_delivery1" name="img_delivery1" accept="image/gif, image/jpeg, image/png" capture style="display:none"/>
<img src="dist/img/Upload1.jpg" id="upimg_delivery1" style="cursor:pointer;height: 30px;" />


<input type="file" id="img_delivery2" class="form-control image_uploader_with_fname" name="img_delivery2" accept="image/gif, image/jpeg, image/png" capture style="display:none"/>
<img src="dist/img/Upload1.jpg" id="upimg_delivery2" style="cursor:pointer;height: 30px;" />
                                        </div>
                                        </c:if></c:if>
                                     </div>  
													<!-- Break Space --><br>
												
															<table
												class="table table-striped table-bordered table-hover"
												id="tablefeilds" style="!important; overflow: scroll;">
												<thead>
													<tr class = "tr_color">
									
							<th>Team</th>
							<th>User</th>
							<th>Kits Requested</th>
							<th>Kits Produced</th>
							<th>Kits Received</th>
							<th>Kits Dispatched</th>
							<th>Request Date</th>
							<th>Produced Date</th>
							<th>Received Date</th>
							<th>Dispatched Date</th>
							<th>Remarks</th>
							<th>Ack</th>		
							</tr>
							</thead>
												<tbody>
														<c:forEach var="items"
															items="${deliveryAndInventoryItems}">
														<c:if test="${ userRole != 'Vendor'}">
															<tr>
															
															<c:choose>
																		<c:when test="${ items.team == 'Vendor'}">
																			<td class="text-center" title='${date}'><c:out
																		value="${items.team}"></c:out></td>
																		</c:when>
																		<c:otherwise>
																			<td class="text-center"><c:out
																		value="${items.team}"></c:out></td>
																		</c:otherwise>
																		</c:choose>
															
																		<td class="text-center" title='${items.collateral}'><c:out
																		value="${items.prod_users}"></c:out></td>
																		<td class="text-center"><c:out
																		value="${items.no_Item_Req}"></c:out></td>
																		
																		<!-- For Kits Produced -->
																		<c:choose>
																		<c:when test="${items.team == 'Production' and items.status == 'Production in Progress'}">
																		<c:if test="${items.prod_users==username or  userRole=='Admin'}">
																		<input type="hidden" id="prod_user" name="prod_user" value='${items.prod_users}' />
																		<td><input class="form-control" id="no_of_kits_produced"
																name="no_of_kits_produced" type="text"
																onkeypress='validateNum(event)'></td>
																</c:if>
																	<c:if test="${userRole=='Business Team' or userRole=='Logistics'}">
																			<td class="text-center"><c:out
																		value="-"></c:out></td>
																		</c:if>
																		</c:when>
																		<c:when test="${not empty items.item_Produced}">
																			<td class="text-center">
																<c:out value="${items.item_Produced}"></c:out></td>
																		</c:when>
																		<c:otherwise>
																		<td class="text-center"><c:out
																		value="-"></c:out></td>
																		</c:otherwise>
																		</c:choose>
																		
																		
																		<!-- For Kits Received  Logistics-->
																		<c:choose>
																		<c:when test="${items.team == 'Logistics' and not empty items.item_Received_logi}">
																			<td class="text-center">
																<c:out value="${items.item_Received_logi}"></c:out></td>
																		</c:when>
																			
																		<c:when test="${(items.team == 'Logistics' and items.status == 'Acknowledged by Logistics' and items.reachedLogi == '1') or items.reachedLogi == '2'}">
																		
																		<c:if test="${userRole=='Logistics' or  userRole=='Admin'}">
																			<input type="hidden" id="tempTeam" name="tempTeam" value='${items.team}' />
																			<input type="hidden" id="tempStatus" name="tempStatus" value='${items.status}' />
																			<input type="hidden" id="reachedLogi" name="reachedLogi" value='${items.reachedLogi}' />
																			
																		<input type="hidden" id="prod_user" name="prod_user" value='${items.prod_users}' />
																		<td><input class="form-control" id="no_of_kits_received"
																name="no_of_kits_received" type="text"
																onkeypress='validateNum(event)'></td>
																</c:if>
																	<c:if test="${userRole=='Business Team' or userRole=='Production Team'}">
																			<td class="text-center"><c:out
																		value="-"></c:out></td>
																		</c:if>
																		</c:when>
																		<c:when test="${items.team == 'Vendor' and not empty items.item_Reveived_Vend}">
																		
																			<td class="text-center">
																<c:out value="${items.item_Reveived_Vend}"></c:out></td>
																		</c:when>
																		
																	
																		<c:when test="${items.team == 'Vendor' and items.status == 'Acknowledged by vendor'}">
																	
																	<c:if test="${userRole=='Business Team' or userRole=='Production Team' or userRole=='Logistics'}">
																			<td class="text-center"><c:out
																		value="-"></c:out></td>
																		</c:if>
																		</c:when>
																	
																		<c:otherwise>
																		<td class="text-center"><c:out
																		value="-"></c:out></td>
																		</c:otherwise>
																		</c:choose>
																		
																		<!-- For Kits Dispatched Production -->
																		<c:choose>
																		<c:when test="${items.team == 'Production' and items.status == 'Production in Progress'}">
																		<c:if test="${items.prod_users==username or  userRole=='Admin'}">
																		<input type="hidden" id="prod_user" name="prod_user" value='${items.prod_users}' />
																		<td><input class="form-control" id="no_of_kits_dispached"
																name="no_of_kits_dispached" type="text" onkeypress='validateNum(event)'></td>
																</c:if>
																	<c:if test="${userRole=='Business Team' or userRole=='Logistics'}">
																			<td class="text-center"><c:out
																		value="-"></c:out></td>
																		</c:if>
																		</c:when>
																			<c:when test="${items.team == 'Production' and items.status == 'Production Completed'}">
																		<c:if test="${items.prod_users==username or  userRole=='Admin'}">
																		<input type="hidden" id="prod_user" name="prod_user" value='${items.prod_users}' />
																		<td><input class="form-control" id="no_of_kits_dispached"
																name="no_of_kits_dispached" type="text" onkeypress='validateNum(event)'></td>
																</c:if>
																<c:if test="${userRole=='Logistics' or userRole=='Business Team'}">
																<td class="text-center"><c:out
																		value="-"></c:out></td>
																		</c:if>
																		</c:when>
																		<c:when test="${items.team == 'Production' and not empty items.item_Dispatch_Prod}">
																			<td class="text-center">
																<c:out value="${items.item_Dispatch_Prod}"></c:out></td>
																		</c:when>
																			<c:when test="${items.team == 'Logistics' and not empty items.item_Dispatch_logi}">
																			<td class="text-center">
																<c:out value="${items.item_Dispatch_logi}"></c:out></td>
																		</c:when>
																		<c:when test="${(items.team == 'Logistics' and items.status == 'Acknowledged by Logistics' and items.reachedLogi == '1') or items.reachedLogi == '2' or items.reachedLogi == '3' }">
																		<c:if test="${userRole=='Logistics' or  userRole=='Admin'}">
																		<input type="hidden" id="prod_user" name="prod_user" value='${items.prod_users}' />
																		<td><input class="form-control no_of_kits_dispatched_logi" id="no_of_kits_dispatched_logi"
																name="no_of_kits_dispatched_logi" type="text" onkeypress='validateNum(event)'></td>
																</c:if>
																<c:if test="${userRole=='Business Team' or userRole=='Production Team'}">
																			<td class="text-center"><c:out
																		value="-"></c:out></td>
																		</c:if>
																		</c:when>
																			<c:when test="${items.team == 'Logistics' and items.status == 'Received by Logistics'}">
																		<c:if test="${userRole=='Logistics' or  userRole=='Admin'}">
																		<input type="hidden" id="prod_user" name="prod_user" value='${items.prod_users}' />
																		<td><input class="form-control no_of_kits_dispatched_logi" id="no_of_kits_dispatched_logi"
																name="no_of_kits_dispatched_logi" type="text" onkeypress='validateNum(event)'></td>
																</c:if>
																	<c:if test="${userRole=='Business Team' or userRole=='Production Team'}">
																			<td class="text-center"><c:out
																		value="-"></c:out></td>
																		</c:if>
																		</c:when>
																		
																	
																		<c:otherwise>
																		<td class="text-center"><c:out
																		value="-"></c:out></td>
																		</c:otherwise>
																		</c:choose>
																	<!-- End Kits Dispatched  Logistics-->
																		
																		<!-- For Request Date-->	
																		<td class="text-center"><c:out value="${items.business_Req_Date}"></c:out></td>
																		<!-- End Request Date-->
																		
																		<!-- For Produced Date Production-->	
																	<c:choose>
																		<c:when test="${items.team == 'Production' and not empty items.item_Produce_Date}">
																			<td class="text-center">
																<c:out value="${items.item_Produce_Date}"></c:out></td>
																		</c:when>
																		<c:otherwise>
																		<td class="text-center"><c:out
																		value="-"></c:out></td>
																		</c:otherwise>
																		</c:choose>
																		<!-- End Produced Date-->	
																		
																		<!-- For Received Date Logistics-->	
																	<c:choose>
																		<c:when test="${items.team == 'Logistics' and  not empty items.item_Received_Date_logi}">
																			<td class="text-center">
																<c:out value="${items.item_Received_Date_logi}"></c:out></td>
																		</c:when>
																		<c:when test="${items.team == 'Vendor' and  not empty items.item_Reveived_Vend_Dt}">
																			<td class="text-center">
																<c:out value="${items.item_Reveived_Vend_Dt}"></c:out></td>
																		</c:when>
																		<c:otherwise>
																		<td class="text-center"><c:out
																		value="-"></c:out></td>
																		</c:otherwise>
																		</c:choose>
																		
																		<!-- End Received Date Logistics-->	
																		
																
																	<c:choose>
																		<c:when test="${items.team == 'Production' and not empty items.item_Dispatch_date}">
																			<td class="text-center">
																<c:out value="${items.item_Dispatch_date}"></c:out></td>
																		</c:when>
																	
																		<c:when test="${items.team == 'Logistics' and not empty items.item_Dispatch_Date_logi}">
																			<td class="text-center">
																<c:out value="${items.item_Dispatch_Date_logi}"></c:out></td>
																		</c:when>
																		<c:otherwise>
																		<td class="text-center"><c:out
																		value="-"></c:out></td>
																		</c:otherwise>
																		</c:choose>
																		<!-- End Dispatched Date Logistics-->
																		
																		<!-- For Remarks  Business Team-->
																		<c:if test="${items.team == 'Business'}">
																		
																	<td class="text-center"><c:out value="${items.business_Remarks}"></c:out></td>
																   	</c:if>	
																   	<!-- End Remarks  Business Team-->
													
																		<!-- For Remarks for other Production,Logistics and Vendor Teams -->
																		<c:if test="${items.team != 'Business'}">
																		<c:choose>
																		<c:when test="${items.team == 'Production' and items.status == 'Production in Progress'}">
																		<c:if test="${items.prod_users==username or  userRole=='Admin'}">
																		<td><input class="form-control" id="prod_remarks"
																name="prod_remarks" type="text"></td>
																</c:if>
																	<c:if test="${userRole=='Business Team' or userRole=='Logistics'}">
																			<td class="text-center"><c:out
																		value="-"></c:out></td>
																		</c:if>
																		</c:when>
																			<c:when test="${items.team == 'Production' and items.status == 'Production Completed'}">
																		<c:if test="${items.prod_users==username or  userRole=='Admin'}">
																		<td><input class="form-control" id="prod_remarks"
																name="prod_remarks" type="text"></td>
																</c:if>
																	<c:if test="${userRole=='Business Team'}">
																			<td class="text-center"><c:out
																		value="-"></c:out></td>
																		</c:if>
																<c:if test="${userRole=='Logistics'}">
																<td class="text-center"><c:out
																		value="-"></c:out></td>
																		</c:if>
																		</c:when>
																		
																		<c:when test="${items.team == 'Production' and not empty items.prod_Remarks_dispatched}">
																			<td class="text-center">
																<c:out value="${items.prod_Remarks_dispatched}"></c:out></td>
																		</c:when>
																		
																		<c:when test="${items.team == 'Logistics' and items.status == 'Acknowledged by Logistics'}">
																		<c:if test="${userRole=='Logistics' or  userRole=='Admin'}">
																		<input type="hidden" id="prod_user" name="prod_user" value='${items.prod_users}' />
																		<td><input class="form-control" id="logi_remarks"
																name="logi_remarks" type="text"></td>
																</c:if>
																	<c:if test="${userRole=='Business Team' or userRole=='Production Team'}">
																			<td class="text-center"><c:out
																		value="-"></c:out></td>
																		</c:if>
																		</c:when>
																			<c:when test="${items.team == 'Logistics' and items.status == 'Received by Logistics'}">
																		<c:if test="${userRole=='Logistics' or  userRole=='Admin'}">
																		<input type="hidden" id="prod_user" name="prod_user" value='${items.prod_users}' />
																		<td><input class="form-control" id="logi_remarks"
																name="logi_remarks" type="text"></td>
																</c:if>
																	<c:if test="${userRole=='Business Team' or userRole=='Production Team'}">
																			<td class="text-center"><c:out
																		value="-"></c:out></td>
																		</c:if>
																		</c:when>
																		
																		<c:when test="${items.team == 'Logistics' and not empty items.logist_Remarks_dispatched}">
																			<td class="text-center">
																<c:out value="${items.logist_Remarks_dispatched}"></c:out></td>
																		</c:when>
																		
																		<c:when test="${items.team == 'Vendor' and items.status == 'Received by Logistics'}">
																		
																	<c:if test="${userRole=='Business Team'}">
																			<td class="text-center"><c:out
																		value="-"></c:out></td>
																		</c:if>
																		</c:when>
																		
																		<c:when test="${items.team == 'Vendor' and not empty items.vendor_Remarks}">
																			<td class="text-center">
																<c:out value="${items.vendor_Remarks}"></c:out></td>
																		</c:when>
																		
																		<c:otherwise>
																		<td class="text-center"><c:out
																		value="-"></c:out></td>
																		</c:otherwise>
																		</c:choose>
																		</c:if>
																		<!-- For Ack  Business Team-->
																		<c:if test="${items.team == 'Business'}">
																		
																<td><input type="checkbox" checked value="" disabled></td>	
																   	</c:if>	
																   	<!-- End Remarks  Business Team-->																	<!-- End Remarks for other Production,Logistics and Vendor Teams -->
																		
																			
	<!-- End Ack for other Production,Logistics and Vendor Teams -->
	<c:if test="${items.team != 'Business'}">
	<c:choose>
	<c:when test="${(items.team == 'Production' and items.status == 'Submitted for Production') and (items.prod_users==username or  userRole=='Admin')}">
	<%-- <c:if test="${items.prod_users==username or  userRole=='Admin'}"> --%>
<input type="hidden" id="prod_user" name="prod_user" value='${items.prod_users}' />
			<td><input type="checkbox"  value="" ></td>	
<%-- 		</c:if>
 --%>	</c:when>
	<c:when test="${items.team == 'Production' and items.status != 'Submitted for Production'}">
<%-- 	<c:if test="${ items.status != 'Submitted for Production'}">
 --%>		<td><input type="checkbox" checked value="" disabled></td>	
<%-- 		</c:if>
 --%>	</c:when>
	<%-- <c:otherwise>
		<td><input type="checkbox"  value="" disabled>222</td>	
	</c:otherwise> --%>
	
		<c:when test="${(items.team == 'Logistics' and items.status == 'Dispatched to Logistics' and deliveryAndInventory.status=='Dispatched to Logistics') and (userRole=='Logistics' or  userRole=='Admin') }">
<%-- 	<c:if test="${userRole=='Logistics' or  userRole=='Admin'}">
 --%>	<input type="hidden" id="prod_user" name="prod_user" value='${items.prod_users}' />
			<td><input type="checkbox"  value="" ></td>	
<%-- 		</c:if>
 --%>		
		
	
	</c:when>
	
	<c:when test="${(items.reachedLogi == '1'  and items.team == 'Logistics' and items.status == 'Dispatched to Logistics') and (userRole=='Logistics' or  userRole=='Admin')}">
		

	<input type="hidden" id="tempTeam" name="tempTeam" value='${items.team}' />
	<input type="hidden" id="reachedLogi" name="reachedLogi" value='${items.reachedLogi}' />
	<input type="hidden" id="prod_user" name="prod_user" value='${items.prod_users}' />
	<input type="hidden" id="tempStatus" name="tempStatus" value='Dispatched to Logistics' />
			<td><input type="checkbox"  value="" ></td>	
	</c:when>
	
	
	<c:when test="${(items.team == 'Logistics' and items.status != 'Submitted for Production' and items.status != 'Production in Progress' and items.status != 'Production Completed' and items.status != 'Dispatched to Logistics') or items.reachedLogi == '2' or items.reachedLogi == '3'}">
	
<%-- 	<c:if test="${ items.status != 'Submitted for Production' and items.status != 'Production in Progress' and items.status != 'Production Completed' and items.status != 'Dispatched to Logistics' and items.status != 'Received by Logistics'}">
 --%>		
		<td><input type="checkbox" checked value="" disabled></td>	
<%-- 	</c:if>
 --%>	
	</c:when>
	
		<c:when test="${(items.team == 'Vendor' and items.status == 'Dispatched to Warehouse') and (userRole=='Vendor' or  userRole=='Admin')}">
<%-- 	<c:if test="${userRole=='Vendor' or  userRole=='Admin'}">
 --%><input type="hidden" id="prod_user" name="prod_user" value='${items.prod_users}' />
			<td><input type="checkbox"  value="" ></td>	
<%-- 		</c:if>
 --%>	</c:when>
	<c:when test="${items.team == 'Vendor' and  items.status != 'Submitted for Production' and items.status != 'Production in Progress' and items.status != 'Production Completed' and items.status != 'Dispatched to Logistics' and items.status != 'Acknowledged by Logistics' and items.status != 'Received by Logistics' and items.status != 'Dispatched to Warehouse'}">
<%-- 	<c:if test="${ items.status != 'Submitted for Production' and items.status != 'Production in Progress' and items.status != 'Production Completed' and items.status != 'Dispatched to Logistics' and items.status != 'Acknowledged by Logistics' and items.status != 'Received by Logistics' and items.status != 'Dispatched to Warehouse' and  items.status != 'Acknowledged by vendor'}">
 --%>		<td><input type="checkbox" checked value="" disabled></td>	
<%-- 		</c:if>
 --%>	</c:when>
	
	<c:otherwise>
		<td><input type="checkbox"  value="" disabled></td>	
	</c:otherwise>
	</c:choose>
	
	</c:if>
													
	<!-- End Ack for other Production,Logistics and Vendor Teams -->
																			
																			
															</tr>
															</c:if>
															
															<c:if test="${ userRole == 'Vendor' && items.team == 'Vendor'}">
															<tr>
															
															<c:choose>
																		<c:when test="${ items.team == 'Vendor'}">
																			<td class="text-center" title='${date}'><c:out
																		value="${items.team}"></c:out></td>
																		</c:when>
																		<c:otherwise>
																			<td class="text-center"><c:out
																		value="${items.team}"></c:out></td>
																		</c:otherwise>
																		</c:choose>
															
																		<td class="text-center" title='${items.collateral}'><c:out
																		value="${items.prod_users}"></c:out></td>
																		<td class="text-center"><c:out
																		value="${items.no_Item_Req}"></c:out></td>
																		<td class="text-center"><c:out
																		value="-"></c:out></td>
																		<!-- For Kits Produced -->
																		
																		
																		<!-- For Kits Received  Logistics-->
																		<c:choose>
																		
																		<c:when test="${items.team == 'Vendor' and not empty items.item_Reveived_Vend}">
																		
																			<td class="text-center">
																<c:out value="${items.item_Reveived_Vend}"></c:out></td>
																		</c:when>
																		
																	
																		<c:when test="${items.team == 'Vendor' and items.status == 'Acknowledged by vendor'}">
																		<c:if test="${userRole=='Vendor' or  userRole=='Admin'}">
																		<td><input class="form-control" id="no_of_kits_received_vend"
																name="no_of_kits_received_vend" type="text"
																onkeypress='validateNum(event)'></td>
																</c:if>
																	
																		</c:when>
																	
																		<c:otherwise>
																		<td class="text-center"><c:out
																		value="-"></c:out></td>
																		</c:otherwise>
																		</c:choose>
																	
																		<td class="text-center"><c:out
																		value="-"></c:out></td>
																		
																		<!-- For Request Date-->	
																		<td class="text-center"><c:out value="${items.business_Req_Date}"></c:out></td>
																		<!-- End Request Date-->
																		
																	<td class="text-center"><c:out
																		value="-"></c:out></td>
																		
																		<!-- For Received Date Logistics-->	
																	<c:choose>
																		
																		<c:when test="${items.team == 'Vendor' and  not empty items.item_Reveived_Vend_Dt}">
																			<td class="text-center">
																<c:out value="${items.item_Reveived_Vend_Dt}"></c:out></td>
																		</c:when>
																		<c:otherwise>
																		<td class="text-center"><c:out
																		value="-"></c:out></td>
																		</c:otherwise>
																		</c:choose>
																	
																		<!-- For Dispatched Date Production-->	
																	<td class="text-center"><c:out
																		value="-"></c:out></td>
																		<!-- End Dispatched Date Logistics-->
																		
																		
													
																		<!-- For Remarks for other Production,Logistics and Vendor Teams -->
																		<c:choose>
																		<c:when test="${items.team == 'Vendor' and items.status == 'Received by Logistics'}">
																		<c:if test="${userRole=='Vendor' or  userRole=='Admin'}">
																		<td><input class="form-control" id="vendor_remarks"
																name="vendor_remarks" type="text"></td>
																</c:if>
																		</c:when>
																		
																		<c:when test="${items.team == 'Vendor' and not empty items.vendor_Remarks}">
																			<td class="text-center">
																<c:out value="${items.vendor_Remarks}"></c:out></td>
																		</c:when>
																		
																		<c:otherwise>
																		<td class="text-center"><c:out
																		value="-"></c:out></td>
																		</c:otherwise>
																		</c:choose>
																		<!-- For Ack  Business Team-->
																				
	<!-- End Ack for other Production,Logistics and Vendor Teams -->
	<c:if test="${items.team != 'Business'}">
	<c:choose>
	
		<c:when test="${(items.team == 'Vendor' and items.status == 'Dispatched to Warehouse') and (userRole=='Vendor' or  userRole=='Admin')}">
<%-- 	<c:if test="${userRole=='Vendor' or  userRole=='Admin'}">
 --%><input type="hidden" id="prod_user" name="prod_user" value='${items.prod_users}' />
			<td><input type="checkbox"  value="" ></td>	
<%-- 		</c:if>
 --%>	</c:when>
	<c:when test="${items.team == 'Vendor' and  items.status != 'Submitted for Production' and items.status != 'Production in Progress' and items.status != 'Production Completed' and items.status != 'Dispatched to Logistics' and items.status != 'Acknowledged by Logistics' and items.status != 'Received by Logistics' and items.status != 'Dispatched to Warehouse'}">
<%-- 	<c:if test="${ items.status != 'Submitted for Production' and items.status != 'Production in Progress' and items.status != 'Production Completed' and items.status != 'Dispatched to Logistics' and items.status != 'Acknowledged by Logistics' and items.status != 'Received by Logistics' and items.status != 'Dispatched to Warehouse' and  items.status != 'Acknowledged by vendor'}">
 --%>		<td><input type="checkbox" checked value="" disabled></td>	
<%-- 		</c:if>
 --%>	</c:when>
	
	<c:otherwise>
		<td><input type="checkbox"  value="" disabled></td>	
	</c:otherwise>
	</c:choose>
	
	</c:if>
													
	<!-- End Ack for other Production,Logistics and Vendor Teams -->
																			
																			
															</tr>
															</c:if>
															
															
														</c:forEach>
														</tbody>
							</table>
																	
	<c:if test="${ (deliveryAndInventory.status == 'Acknowledged by vendor' || deliveryAndInventory.status == 'Dispatched to Warehouse' || deliveryAndInventory.status == 'Delivered to Warehouse') && userRole != 'Vendor'}">
	<c:set var="path_dispatch" value="${deliveryAndInventory.path_dispatch}" />
																												<%
															 if(pageContext.getAttribute("path_dispatch")!=null){
				%>
																
															<%
																String path_name1 = (String) pageContext.getAttribute("path_dispatch");
																	File actual1 = new File(path_name1);
																	File file=new File(path_name1);
																	int fileLen=file.listFiles().length;
																	int it1 = 0;
																	String[] logFile=new String[fileLen];
																        for( File f : actual1.listFiles()){
																        	logFile[it1]=path_name1+"/"+f.getName();
																        	
																        	it1++;
																        }
															        	
																        %>
																      <%--   
																        <div class="item <%  if( it1 == 0 ){%> <%= "active" %> <% } %>">
																        <img src="get_image?path=<%= path_name1+"/"+f.getName() %>"
																alt="Outer Image">
																</div>
																            <%
																            it1++;
																        }
															
															%> --%>
															
															
														<!-- Start of Row -->
													
														<%if(fileLen==2){%><label>Logistics POD </label>
  <img width="30" height="30" class="singleImageShower" src="get_image?path=<%= logFile[0]%>" alt="Forest">
  <img width="30" height="30" class="singleImageShower" src="get_image?path=<%= logFile[1]%>" alt="Forest">
	<%} %>												
	<%if(fileLen==1){%><label>Logistics POD </label>
  <img width="30" height="30" class="singleImageShower" src="get_image?path=<%= logFile[0]%>" alt="Forest">
	<%} %>													<br><br>
<% }%></c:if>
<c:if test="${deliveryAndInventory.status == 'Delivered to Warehouse'}">
<c:set var="path_delivery" value="${deliveryAndInventory.path_delivery}" />
																												<%
															 if(pageContext.getAttribute("path_delivery")!=null){
				%>
																
															<%
																String path_name2 = (String) pageContext.getAttribute("path_delivery");
																	File actual2 = new File(path_name2);
																	File file=new File(path_name2);
																	int fileLenVen=file.listFiles().length;
																	int it1 = 0;
																	String[] vendorFile=new String[fileLenVen];
																        for( File f : actual2.listFiles()){
																        	vendorFile[it1]=path_name2+"/"+f.getName();
																        	
																        	it1++;
																        }
															        	
																        %>
																      <%--   
																        <div class="item <%  if( it1 == 0 ){%> <%= "active" %> <% } %>">
																        <img src="get_image?path=<%= path_name1+"/"+f.getName() %>"
																alt="Outer Image">
																</div>
																            <%
																            it1++;
																        }
															
															%> --%>
															
															
														<!-- Start of Row -->
													
														
														<%if(fileLenVen==2){%><label>Vendor POD &nbsp;&nbsp;&nbsp;</label>
<img width="30" height="30" class="singleImageShower" src="get_image?path=<%= vendorFile[0]%>" alt="Forest">
<img width="30" height="30" class="singleImageShower" src="get_image?path=<%= vendorFile[1]%>" alt="Forest">
	<%} %>												
	<%if(fileLenVen==1){%><label>Vendor POD &nbsp;&nbsp;&nbsp;</label>
 <img width="30" height="30" class="singleImageShower" src="get_image?path=<%= vendorFile[0]%>" alt="Forest">
	<%} %>													<br><br>
<% }%></c:if>
						<c:if test="${userRole!='Business Team'}">
		<a class="btn btn-primary" href="javascript:;" id="saveInventories" style="width: 100px;">Save </a>
															&nbsp;
														 	<a class="btn btn-primary" href="javascript:;"
														onclick="cancelInventory();" style="width: 100px;">Cancel </a>
														</c:if>
															<input type="hidden" id="req_Id" name='req_Id'
																			value='${deliveryAndInventory.req_Id}' />
											<input type="hidden" id="req_num" name='req_num'
																			value='${deliveryAndInventory.req_num}' /> 
											<input type="hidden" id="status" name='status'
																			value='${deliveryAndInventory.status}' /> 		
													</div>

												</div>
											</div>
										</div>
									</div>
							
							</div></div></div>
						</div>
	
		<!-- /#page-wrapper -->

						
						</form:form>
						
						
						
						<!-- The Modal -->
						
						
</body>
					

	<!-- </div> -->
	
	<!-- /#wrapper -->

	<!-- jQuery -->
<!-- 	<script src="vendor/jquery/jquery.min.js"></script>
 -->
	<!-- Bootstrap Core JavaScript -->
	<script src="vendor/bootstrap/js/bootstrap.min.js"></script>

	<!-- Metis Menu Plugin JavaScript -->
	<script src="vendor/metisMenu/metisMenu.min.js"></script>

	<!-- Morris Charts JavaScript -->
	<script src="vendor/raphael/raphael.min.js"></script>
	<script src="vendor/morrisjs/morris.min.js"></script>
	<script src="data/morris-data.js"></script>
	<!-- Custom Theme JavaScript -->
	<script src="dist/js/sb-admin-2.js"></script>
	<script src="js/adminMenu.js"></script>


<script type="text/javascript">

	


	$('#viewInventory').DataTable({
			scrollX : "1000px",

			scrollX : true,

			scrollCollapse : true,

			paging : true,

		});
	
	</script>
<script type="text/javascript">
$("#upimg_dispatch1").click(function () {
    $("#img_dispatch1").trigger('click');
});
$("#upimg_dispatch2").click(function () {
    $("#img_dispatch2").trigger('click');
});

$("#upimg_delivery1").click(function () {
    $("#img_delivery1").trigger('click');
});
$("#upimg_delivery2").click(function () {
    $("#img_delivery2").trigger('click');
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
			<script type="text/javascript">
			 function change(){
				var radioValue = $("input[name='optionsRadiosInline']:checked").val();
				if(radioValue=='log'){
					  document.getElementById("myGallery1").style.display="block";
					 document.getElementById("myGallery2").style.display="none"; 
				}
				if(radioValue=='vendor'){
					 document.getElementById("myGallery1").style.display="none";
					 document.getElementById("myGallery2").style.display="block"; 
				}
				
			} 
			 
			</script>	
</html>
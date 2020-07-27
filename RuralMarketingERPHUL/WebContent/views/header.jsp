<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="java.util.LinkedHashMap"%>
<%@page import="java.util.Map"%>
<%@page import="com.rural.Model.MenuMaster"%>

<%-- 
<%
    session=request.getSession(false);
    if(session.getAttribute("mapMenu")==null)
    {
        response.sendRedirect("login.jsp");
    }

%>  --%>
<html lang="en">

<head>
<!-- common css styles class -->
	<link href="dist/css/common.css" rel="stylesheet">
	
<!-- <title>Rural Marketing ERP</title> -->

<!-- Bootstrap Core CSS -->
<link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

<!-- MetisMenu CSS -->
<link href="vendor/metisMenu/metisMenu.min.css" rel="stylesheet">

<!-- Custom CSS -->
<link href="dist/css/sb-admin-2.css" rel="stylesheet">

<!-- common css styles class -->
<link href="dist/css/common.css" rel="stylesheet">

<link href="dist/css/jquery-ui.min.css" rel="stylesheet">

<!-- Morris Charts CSS -->
<link href="vendor/morrisjs/morris.css" rel="stylesheet">

<!-- Custom Fonts -->
<link href="vendor/font-awesome/css/font-awesome.min.css"
	rel="stylesheet" type="text/css">

	

   

    

<style>




#myImg {
    border-radius: 5px;
    cursor: pointer;
    transition: 0.3s;
}

#myImg:hover {opacity: 0.7;}

/* The Modal (background) */
.modal_image_pager {
    display: none; /* Hidden by default */
    position: fixed; /* Stay in place */
    z-index: 99990; /* Sit on top */
    padding-top: 100px; /* Location of the box */
    left: 0;
    top: 0;
    width: 100%; /* Full width */
    height: 100%; /* Full height */
    overflow: auto; /* Enable scroll if needed */
    background-color: rgb(144, 144, 144); /* Fallback color */
    background-color: rgba(144, 144, 144, 0.5); /* Black w/ opacity */
}


/* Modal Content (image) */
.modal-content {
    margin: auto;
    display: block;
    width: 50%;
    max-width: 700px;
}

/* Caption of Modal Image */
#caption {
    margin: auto;
    display: block;
    width: 80%;
    max-width: 700px;
    text-align: center;
    color: #ccc;
    padding: 10px 0;
    height: 150px;
}

.close_image_viewer {
opacity: 0.8;
}

/* Add Animation */
.modal-content, #caption {    
    -webkit-animation-name: zoom;
    -webkit-animation-duration: 0.6s;
    animation-name: zoom;
    animation-duration: 0.6s;
}

@-webkit-keyframes zoom {
    from {-webkit-transform:scale(0)} 
    to {-webkit-transform:scale(1)}
}

@keyframes zoom {
    from {transform:scale(0)} 
    to {transform:scale(1)}
}

/* The Close Button */
.close {
    position: absolute;
    top: 15px;
    right: 35px;
    color: #000;
    font-size: 40px;
    font-weight: bold;
    transition: 0.3s;
}

.close:hover,
.close:focus {
    color: #bbb;
    text-decoration: none;
    cursor: pointer;
}





.navbar-brand-img {
	height: 200;
}
.tr_color{
	background-color: #337ab7;
}
.dataTables_scrollHeadInner .dataTable {
	 margin-bottom: 0;
}
</style>

</head>
<body >
<div class="modal fade" id="mymodel12" role="dialog">
  	  <div class="modal-dialog">
      <!-- Modal content-->
      <div class="modal-content" style="right: 11px;height: 200px;width: 520px;">
        <div class="modal-header" style="background-color: #f5f5f5">
          <h3 class="modal-title" style="text-align:center;"><b>Your session has expired.</b></h3>
        </div>
           <div class="modal-body">
      <p style="text-align: center;"><b>Click Ok...!!!</b></p>
      <br><br>
      
 <input  type="button" value="OK" class="btn btn-success "style=" margin-left: 187px;width: 102px;" id="okbutton"/> 
    </div>
  </div>
</div>
               
   </div>
	<%
		if(session.getAttribute("mapMenu")==null)
	    {
	        response.sendRedirect("login.jsp");
	    }
	%>
	<div id="wrapper">

		<!-- Navigation -->
		<nav class="navbar navbar-default navbar-static-top" role="navigation" style="margin-bottom: 0">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle"  data-target=".navbar-collapse">
					<span class="sr-only">Toggle navigation</span> <span class="icon-bar"></span>
					<span class="icon-bar"></span> <span class="icon-bar"></span>
				</button>
				<img src="dist/img/Home.png" style="margin-left: 432px;"
                  			     alt="Unilever" height="50" width="70">
			</div>
			
			<div class="nav navbar-top-links navbar-left" >
                  			
					<a href="Home" class="navbar-brand" style="font-size: 24px; bgcolor:#337ab7;">Rural Resource Planner</a> 
					
			</div>
			<!-- /.navbar-header -->

			<ul class="nav navbar-top-links navbar-right">
				<li class="dropdown">
					<a class="dropdown-toggle" data-toggle="dropdown" href="#"> Welcome, ${name} |&nbsp;&nbsp; H2H &nbsp;${userRole}
					
					</a>
					
				</li>
			</ul>
			<div class="navbar-default sidebar" role="navigation">
				<div class="sidebar-nav navbar-collapse" padding="100px">
					<ul class="nav" id="side-menu">
						<%-- <c:out value="${mapMenu}"></c:out> --%>
						<c:if test="${empty mapMenu}">
								<%
								response.sendRedirect("/LogOut");
								%>
								
						</c:if>
						
						
						<%
						
							if(session.getAttribute("mapMenu")==null)
						    {
						        response.sendRedirect("login.jsp");
						    }
							Map<String, Map<String, String>> menuMap = (Map<String, Map<String, String>>)session.getAttribute("mapMenu");
							/* if(menuMap==null){
								System.out.println("Session Timed Out....");
								 response.sendRedirect("/LogOut");
								 return;
							}  */
							
							
							MenuMaster menuMaster = new MenuMaster();
							Map<String, String> tempMap = new LinkedHashMap<String, String>();
							for (String strMenu : menuMap.keySet()) 
								{
									tempMap = menuMap.get(strMenu);
									if (tempMap.size() == 1) 
									{
										String strLower = strMenu.toLowerCase();
										%><li><a href="<%=strLower.replaceAll(" ", "")%>"> <%=strMenu%></a></li> <%
							 		}else
							 		{
							 			int count = 0;
							 			%>
											<li><a href="#"> <%=strMenu%><span class="fa arrow"></span></a>
											<ul class="nav nav-second-level" >
					<%
										for (String temp : tempMap.keySet()) 
										{
											count++;

											if (count > 1)
											{
												%>
												
													<li><a href="<%=tempMap.get(temp)%>"><%=temp%></a></li>
												 <!-- /.nav-second-level --> <%
											}
						 				}
						 				%></ul></li>
										<%
									}
								}
										%>
										<li class="divider"></li>
				                        <li>
				                        	<a href="LogOut">Logout
				                        	</a>
				                        </li>
									</ul>
								</div>
								<!-- /.sidebar-collapse -->
							</div>
							<!-- /.navbar-static-side -->
						</nav>
		
	</div><!-- /#wrapper -->

	<!-- jQuery -->
	<script src="vendor/jquery/jquery.min.js"></script>

	<!-- Bootstrap Core JavaScript -->
	<script src="vendor/bootstrap/js/bootstrap.min.js"></script>

	
	<!-- jQuery -->
	<script src="vendor/jquery/jquery.min.js"></script>


	<!-- Bootstrap Core JavaScript -->
	<script src="vendor/bootstrap/js/bootstrap.min.js"></script>



	<!-- BootBox Custom Bootstrap Modal PopUp-->
	<script src="dist/js/bootbox.min.js"></script>
    
	<!-- Custom Theme JavaScript -->
	<script src="dist/js/sb-admin-2.js"></script>
	
	<!-- DataTables JavaScript -->
    <script src="vendor/datatables/js/jquery.dataTables.min.js"></script>
    <script src="vendor/datatables-plugins/dataTables.bootstrap.min.js"></script>
    <script src="vendor/datatables-responsive/dataTables.responsive.js"></script>
	
	
	<div id="imageViewer" class="modal_image_pager">
						  <span class="close close_image_viewer">&times;</span>
						  <img class="modal-content" id="img01">
						  <div id="caption"></div>
						</div>
			<script>
						$(document).ready(function(){							// Get the modal
							var myWindow;
							$(document).on( "click", ".singleImageShower1", function(){
								var modal = $('#imageViewer');
								// Get the image and insert it inside the modal - use its "alt" text as a caption
								var modalImg = $("#img01");
								var captionText = document.getElementById("caption");
								var url = "GSTN: Hul outlet Code:"; 
								var divText = document.getElementById("windo").outerHTML;
								myWindow = window.open(url,'','width=200,height=200');
								var doc = myWindow.document;
								doc.open();
								doc.write(divText);
								doc.close();
								console.log("dsasdasd");
								modal.css( "display", "block" );
								modalImg.attr( "src",  $( this ).attr( "src" ) );
								
							});
							
							$(document).on( "click", ".close", function() { 
								$('#imageViewer').hide();
									closedwindow();
							}); 
							function closedwindow(){
								//debugger;
								if(myWindow){
								myWindow.close();
								}
							}
							
						}); 
					$(document).ready(function(){							// Get the modal
							var myWindow;
							$(document).on( "click", ".singleImageShower", function(){
								var modal = $('#imageViewer');
								// Get the image and insert it inside the modal - use its "alt" text as a caption
								var modalImg = $("#img01");
								var captionText = document.getElementById("caption");
								console.log("dsasdasd");
								modal.css( "display", "block" );
								modalImg.attr( "src",  $( this ).attr( "src" ) );
								
							});
							
							$(document).on( "click", ".close", function() { 
								$('#imageViewer').hide();
									
							}); 
							
						}); 
						$( document ).on( "change", ".image_uploader_with_fname", function(){
							if( $( this )[0].files.length != 0 ){
							if( $( this ).next().next().is( '.fileuploadFname' ) ){
							$( this ).next().next().text( $( this )[0].files[0].name );
							} else {
							$( this ).next().after( '<label class="fileuploadFname">'+$( this )[0].files[0].name+'</label>' );
							}
							} else {
							if( $( this ).next().next().is( ".fileuploadFname" ) ){
							$( this ).next().next().remove();
							}
							}
							});
						
						/* setTimeout(function(){
						callThis();
						},6000);

						function callThis(){
							
							$('#myModal').modal({backdrop: 'static', keyboard: false})
						
						} */
						
						var initial;
						function invocation(){
							//alert("invocat");
							initial=window.setTimeout(function(){
								callThis();
							},600000);
						function callThis(){
							//alert("hi");
							$('#mymodel12').modal({backdrop: 'static', keyboard: false})
							}
						}
						invocation();
						document.body.onclick=function(){
							//alert("reset");
							clearTimeout(initial);
							invocation();
						}
						
						
						
						$(document).ready(function() {

							$('#tablefeilds').DataTable( {

							"scrollX"	:	true,
							"paging"	:	true,
							"autoWidth"	:	false,
							"scrollCollapse": true,
							"ordering" : false,
							"stateSave"	: true,
							"searching"	:	true,
							"bInfo" : false
							});


							});
						</script>
						
						<script type="text/javascript">
						$(document).ready(function(){
							
							$("#okbutton").click(function(){
								debugger;
								$.ajax({
									contentType : "application/json",
									url: "/RuralMarketingERPHUL/LogOut",
									type: "GET",
									success: function (data) {
									location='/RuralMarketingERPHUL/';
									}
									});
							});
							
							 $("#Canclebutton").click(function(){
								// $('#myModal').addClass("modal fade");
						            $("#myModal").dialog('close');

								
							});
							
							
							
							
						});
						
						</script>

</body>

</html>

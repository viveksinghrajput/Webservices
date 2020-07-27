<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>WareHouse DashBoard</title>
<jsp:include page="header.jsp" />
</head>
<body>
<div id="wrapper">

       
        <div id="page-wrapper">
            <div class="row">
				<div class="col-lg-12">
					<h3 class="text-center">WareHouse DashBoard</h3>
				</div>
			</div>
           	<br>
            <div class="row">
				<div class="col-lg-16">
					<div class="panel panel-default">
						<div class="panel-body">
							
							<div class="col-xs-4">
								<div class="form-group">
									<a class="btn btn-primary" href="#">View Pending Approvals</a>
								</div>
							</div>
							<div class="col-xs-4">
								<div class="form-group">
									<a class="btn btn-primary" href="#">View Old Inactive Warehouse</a>
								</div>
							</div>
						</div>
						
						</div>
				</div>
			</div><!-- /.row -->
			
           <!-- Show My warehouses Here -->
           <!-- /.row -->
           
           <div class="row">
           	<div class="col-lg-12">
				<div class="panel panel-default">
					<div class="panel-heading">
					<b>My WareHouses</b>
						<span style="float:right; height='32px';">
							<a href="downloadWarehouse"> <img src="dist/img/csv.jpg" alt="csv" height="33" width="35"></a>
						</span>
									
					</div>
				<div class="panel-body">
					<table width="0%" class="table table-striped table-bordered table-hover" id="wareHouseTable">
						<thead>
							<tr>
								<th>Modify</th>
								<th>Vendor Name</th>
								<th>Contact</th>
								<th>Email</th>
								<th>WareHouse Name</th>
								<th>WareHouse Latitute</th>
								<th>WareHouse Longitude</th>
								<th>WareHouse Address 01</th>
								<th>WareHouse Address 02</th>
								<th>State</th>
								<th>City</th>
								<th>PinCode</th>
								<th>Images</th>
								<th>Map View</th>
								<th>Area In Sq Feet</th>
								<th>Created Date</th>
								<th>LastEdited on</th>
								
																
							</tr>
						</thead>
						<tbody>
							<%
							int count = 1;
							%>
							<c:forEach var="itemList" items="${wareHouseList}">
							<%
							if (count / 2 == 0) {
							%>
							<tr class="even gradeC">
								<td> <a href="editwarehouse?id=<c:out value="${itemList.vendorMaster_id}"/>">Edit</a></td>
								<td><c:out value="${itemList.vendorName}"></c:out></td>
								<td><c:out value="${itemList.vendorContact}"></c:out></td>
								<td><c:out value="${itemList.vendorEmail}"></c:out></td>
								<td><c:out value="${itemList.warehouseName}"></c:out></td>
								<td><c:out value="${itemList.wareHouseLatitute}"></c:out></td>
								<td><c:out value="${itemList.wareHouseLongitute}"></c:out></td>
								<td><c:out value="${itemList.wareHouseAddress01}"></c:out></td>
								<td><c:out value="${itemList.wareHouseAddress02}"></c:out></td>
								<td><c:out value="${itemList.state}"></c:out></td>
								<td><c:out value="${itemList.city}"></c:out></td>
								<td><c:out value="${itemList.pinCode}"></c:out></td>
								<td>Images HyperLink</td>
								<td>Map HyperLink</td>
								<td><c:out value="${itemList.wareHouseAreainSqft}"></c:out></td>
								<td><c:out value="${itemList.createdDate}"></c:out></td>
								<td><c:out value="${itemList.lastEditedDate}"></c:out></td>
								
								
							</tr>
							<%
							} else {
							%>
							<tr class="odd gradeX">
								
								<td> <a href="editwarehouse?id=<c:out value="${itemList.vendorMaster_id}"/>">Edit</a></td>
								<td><c:out value="${itemList.vendorName}"></c:out></td>
								<td><c:out value="${itemList.vendorContact}"></c:out></td>
								<td><c:out value="${itemList.vendorEmail}"></c:out></td>
								<td><c:out value="${itemList.warehouseName}"></c:out></td>
								<td><c:out value="${itemList.wareHouseLatitute}"></c:out></td>
								<td><c:out value="${itemList.wareHouseLongitute}"></c:out></td>
								<td><c:out value="${itemList.wareHouseAddress01}"></c:out></td>
								<td><c:out value="${itemList.wareHouseAddress02}"></c:out></td>
								<td><c:out value="${itemList.state}"></c:out></td>
								<td><c:out value="${itemList.city}"></c:out></td>
								<td><c:out value="${itemList.pinCode}"></c:out></td>
								<td>Images HyperLink</td>
								<td>Map HyperLink</td>
								<td><c:out value="${itemList.wareHouseAreainSqft}"></c:out></td>
								<td><c:out value="${itemList.createdDate}"></c:out></td>
								<td><c:out value="${itemList.lastEditedDate}"></c:out></td>
								
							</tr>
							<%
								}
							%>
		
							</c:forEach>
					</tbody>
				</table>
			</div>
								<!-- End Second Panel Body -->
							</div>
							<!-- End Second Panel Default -->
						</div>
						<!-- End Second Class -->
					</div>
           
        </div><!-- /#page-wrapper -->
        

    </div><!-- /#wrapper -->
    

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
    
    <script>
    $(document).ready(function() {
        $('#wareHouseTable').DataTable({
           
            "scrollX": true
        });
        
    });
    </script>

</body>
</html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Audit WareHouse</title>
<jsp:include page="header.jsp" />
</head>
<body>
<div id="wrapper">

       
        <div id="page-wrapper">
            <div class="row">
				<div class="col-lg-12">
					<h3 class="text-center">Auditor DashBoard</h3>
				</div>
			</div><!-- /.row -->
			
           <!-- Show My warehouses Here -->
           <!-- /.row -->
           <h4>Show Vendor's WareHouses</h4>
           <div class="row">
           <div class="col-lg-16">
           	<div class="col-lg-3 col-md-6">
				<div class="panel panel-primary">
						<div class="panel-heading">
                            <div class="row">
                                <div class="col-xs-3">
                                    <i class="fa fa-tasks fa-5x"></i>
                                </div>
                                <div class="col-xs-9 text-right">
                                   <div >WareHouse Name</div>
                                   <br>
                                   <div >Location</div> 
                                </div>
                            </div>
                        </div>
                        <a href="editwarehouse">
                            <div class="panel-footer">
                                <span class="pull-left">View Details</span>
                                <span class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
                                <div class="clearfix"></div>
                            </div>
                        </a>
                    </div>
             </div>
             
             <div class="col-lg-3 col-md-6">
				<div class="panel panel-primary">
						<div class="panel-heading">
                            <div class="row">
                                <div class="col-xs-3">
                                    <i class="fa fa-tasks fa-5x"></i>
                                </div>
                                <div class="col-xs-9 text-right">
                                   <div >WareHouse Name</div>
                                   <br>
                                   <div >Location</div> 
                                </div>
                            </div>
                        </div>
                        <a href="editwarehouse">
                            <div class="panel-footer">
                                <span class="pull-left">View Details</span>
                                <span class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
                                <div class="clearfix"></div>
                            </div>
                        </a>
                    </div>
             </div>
           
           </div>
           </div>
        </div>
        <!-- /#page-wrapper -->

    </div>
    <!-- /#wrapper -->

    <!-- jQuery -->
    <script src="vendor/jquery/jquery.min.js"></script>

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

</body>
</html>
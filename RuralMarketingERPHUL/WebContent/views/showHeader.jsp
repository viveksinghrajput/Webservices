<!DOCTYPE html>
<%@page import="java.util.LinkedHashMap"%>
<%@page import="java.util.Map"%>
<%@page import="com.rural.Model.MenuMaster"%>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Rural Marketing ERP</title>

    <!-- Bootstrap Core CSS -->
    <link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

    <!-- MetisMenu CSS -->
    <link href="vendor/metisMenu/metisMenu.min.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="dist/css/sb-admin-2.css" rel="stylesheet">

    <!-- Morris Charts CSS -->
    <link href="vendor/morrisjs/morris.css" rel="stylesheet">

    <!-- Custom Fonts -->
    <link href="vendor/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">

   
<style>
.navbar-brand-img {
  height: 200;
</style>
</head>
<body>

    <div id="wrapper">

        <!-- Navigation -->
        <nav class="navbar navbar-default navbar-static-top" role="navigation" style="margin-bottom: 0">
        
            <div class="nav navbar-top-links navbar-left">
                <img src="dist/img/logo.jpg" class="nav navbar-top-links navbar-left" alt="Unilever" height="50" width="90">
            
             <a class="navbar-brand">Rural Marketing ERP</a>         
                </div>
            <!-- /.navbar-header -->

            <ul class="nav navbar-top-links navbar-right">
            <li class="dropdown">
                    <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                       Welcome, ${firstname}
                    </a>
                                    </li>
                </ul><div class="navbar-default sidebar" role="navigation">
                <div class="sidebar-nav navbar-collapse">
                    <ul class="nav" id="side-menu">
                        
                        <%Map<String,Map<String,String>>  menuMap=(Map<String,Map<String,String>>) request.getAttribute("showMenu"); 
                        MenuMaster menuMaster=new MenuMaster();
		Map<String,String> tempMap=new LinkedHashMap<String, String>();
		for(String strMenu:menuMap.keySet()){
			
			tempMap=menuMap.get(strMenu);
			if(tempMap.size()==1){
				String strLower=strMenu.toLowerCase();

			%><li>
			                            <a href="<%=strLower %>"> <%=strMenu %></a>
			<%} else{ int count=0;
			%><li>
                           <a href="#"> <%=strMenu %><span class="fa arrow"></span></a>
                           <%for(String temp:tempMap.keySet()){
                       		count++;

                       		if(count>1){ %>
                            <ul class="nav nav-second-level">
                                <li>
                                    <a href="<%=tempMap.get(temp)%>"><%=temp %></a>
                                </li>
                               
                               
                            </ul>
                            <!-- /.nav-second-level -->
                      
                        <%}} }}%>
                                  </li>   
                                   <li>
                                    <a href="logout">Logout</a>
                                </li>         </ul>
                </div>
                <!-- /.sidebar-collapse -->
            </div>
            <!-- /.navbar-static-side -->
        </nav>

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

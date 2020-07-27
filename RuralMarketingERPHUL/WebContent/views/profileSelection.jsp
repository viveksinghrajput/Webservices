<!DOCTYPE html>
<html lang="en">
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<head>
    <STYLE>
    .error_message {
  margin-top: 0;
  margin-bottom: 0;
  color: #FF0000;
  }
 
}
    </STYLE>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Selection</title>

    <!-- Bootstrap Core CSS -->
    <link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

    <!-- MetisMenu CSS -->
    <link href="vendor/metisMenu/metisMenu.min.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="dist/css/sb-admin-2.css" rel="stylesheet">

    <!-- Custom Fonts -->
    <link href="vendor/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
   
</head>	
<body>
  <!-- Trigger the modal with a button -->
  <!-- Modal -->
  
    <div class="modal-dialog" id="model">
   
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header" style="background-color: #f5f5f5">
          <h4 class="modal-title" style="text-align:center;"><b>Profile Selection</b></h4>
        </div>
        <div class="modal-body">
        <form:form name="profilesel">
                    <div class="panel-body">
                       <fieldset>
		                     <div class="form-group">
								<input type="radio" name="appType" value="h2h" style="margin-left:60px;" checked="checked" ><b> H2H</b>
								<input type="radio" name="appType" value="wal" style="margin-left: 300px;"><b> WallPainting</b>
							</div>
                      	<br>
              </fieldset>
          <div id="buttons" class="col-lg-12" style="padding-left:100px;">
						<button id="submit" onclick="validateprofile();" class="btn btn-lg btn-success "
								style="padding: 8px 30px; margin-left: 102px; margin-right: 170px;">Submit</button>
        </div>
      </div>
      </form:form>
    </div>
  </div>
  </div>
 
	<!-- jQuery -->
    <script src="vendor/jquery/jquery.min.js"></script>
	
	<!-- Confirm password js -->
	<!-- <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js"></script> -->
	
    <!-- Bootstrap Core JavaScript -->
    <script src="vendor/bootstrap/js/bootstrap.min.js"></script>

    <!-- Metis Menu Plugin JavaScript -->
    <script src="vendor/metisMenu/metisMenu.min.js"></script>

    <!-- Custom Theme JavaScript -->
    <script src="dist/js/sb-admin-2.js"></script>
    
	</body>
	<script type="text/javascript">
	
	
	function validateprofile() {
		var appType = $( "input[name=appType]:checked" ).val();
		var action = appType=="h2h" ? "homePage" : "wallpainting";
		$( "#command" ).attr( {"action" : action } ).submit();
	}
	</script>
	
</html>
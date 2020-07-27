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

    </STYLE>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Security Questions</title>

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
<body id = "securityqns">
                 	
		
 		 <!-- Trigger the modal with a button -->
  		<!-- Modal -->
  		<div class="modal fade" id="myModal" role="dialog">
  	  <div class="modal-dialog">
      <!-- Modal content-->
      <div class="modal-content" style="right: 99px;height: 311px;width: 820px;">
        <div class="modal-header" style="background-color: #f5f5f5">
          <button type="button" class="close" onclick="AttachClass();" data-dismiss="modal">&times;</button>
          <h3 class="modal-title" style="text-align:center">Profile Selection</h3>
        </div>
           <div class="modal-body">
        <form:form name="profilesel">
                    <div class="panel-body">
                       <fieldset>
		                     <div class="form-group">
								<input type="radio" name="appType" value="h2h" style="margin-left:60px;" checked="checked" ><b> H2H</b>
								<input type="radio" name="appType" value="wal" style="margin-left: 489px;margin-top: 50px;"><b> WallPainting</b>
							</div>
                      	<br>
              </fieldset>
          <div id="buttons" class="col-lg-12" style="padding-left:100px;">
						<button id="submit" onclick="validateprofile();" class="btn btn-lg btn-success "
								style="padding: 8px 30px; margin-left: 200px; margin-right: 103px;margin-top: 40px;">Submit</button>
        	</div>
      		</div>
      </form:form>
    </div>
  </div>
</div>
               
   </div>

	<div class="container">
        <div class="row">
                <div class="login-panel panel panel-default" style= " margin-top: 3%;margin-left: 186px;margin-right: 276px;width:70%;">
                    <div class="panel-heading">
                     <a class="glyphicon glyphicon-circle-arrow-left" href="LoginUser" style="font-size:20px; color: #337AB5;text-decoration: none;top:24px;"></a>
                        <div align="center"><h3 class="panel-title" style="margin-bottom:14px;"><b> Security Questions</b></h3>
                        <div align="center"><h5 class="error_message" id="status">${statusMsg}</h5></div>
                    </div>  
                    </div>
        <form:form name="securityForm" id="securityForm">
          <div class="panel-body">
               <fieldset> <% int count=1; %>
                   <div class="form-group">
                      <table id="qusfeilds">
                      <c:forEach var="question" items="${qnsList}">
                      			<tr><td><input type="hidden" id="questionId" name="questionId" value="${question.qus_id}" tabindex="1"></td>
							 	<td><b><%=count++ %></b></td>
								<td><b>&nbsp;.&nbsp;<c:out value="${question.qus_name}"></c:out></b></td>
								<br>
                       		    <td><input class="form-control" autocomplete="off" id="ans1" name="ans1" style = "margin-left:14%; margin-bottom:6px;" tabindex="2"></td>
                       			</tr>
                      </c:forEach>
                      </table>
                    </div>
                    <input type="hidden" id="rolename" value="${userRole}"/>
             	<input type="hidden" id="ans-list-info" name ="ans-list-info"/>
                   <div id="buttons" class="col-lg-12" style="padding-left:270px;">
						<button tabindex="3" id="submit" onclick="return validateqns();" class="btn btn-lg btn-success "
									style="padding: 8px 29px; margin-left: 38px;">Submit</button>
							<br>
							<br>
                      	</div>
               </fieldset>
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
   <!--  <script src="js/backButton.js"></script> -->
	</body>
	
	<script type="text/javascript">
	function validateqns() {
		var ansfeilds = $( "#qusfeilds input[name=ans1]" ),ansList = [];
		for( var i = 0 ; i < ansfeilds.length ; i++){
			if( $(ansfeilds[i]).val().trim !=""){
				ansList.push( { "QNO" : $(ansfeilds[i] ).closest( "tr" ).find( "input[name=questionId]" ).val(),
								"ANS" : $(ansfeilds[i] ).closest( "tr" ).find( "input[name=ans1]" ).val()});
			}
			}
		
		
		$( "#ans-list-info" ).val( JSON.stringify( ansList ) );
		var RoleName=document.getElementById("rolename").value;
		var list=document.getElementById("ans-list-info").value;
		if(RoleName=="Admin"){
			debugger;
				jQuery.ajax({
					async:false,
					type : "GET",
					url: "submitquestionsAdmin?myKeyVals="+encodeURI($( "#ans-list-info" ).val()),
					dataType:"json",
			        
			        success: function (data) {
			        	//alert(data);
			        	if(data=="1"){
			        		$('#myModal').modal({backdrop: 'static', keyboard: false})
			        	}
			        	else{
			        		 document.getElementById("status").innerHTML='Questions and Answers does not match';
			        	}
			        
			        	  
			        },
			        error:function(data){
			        	$('#myModal').modal('hide')
			        }
			        }); 
		 
	
		return false;
	}
		else{
			document.securityForm.action = "submitquestions";
			document.securityForm.method = "POST";
			document.securityForm.submit();	
			}
		
	}

	</script>
	<script type="text/javascript">
	function AttachClass(){
		 $('#myModal').addClass("modal fade");
		
		
	}
	
	function validateprofile() {
		var appType = $( "input[name=appType]:checked" ).val();
		var action = appType=="h2h" ? "homePage" : "wallpainting";
		$( "#command" ).attr( {"action" : action } ).submit();
	}
	</script>
</html>
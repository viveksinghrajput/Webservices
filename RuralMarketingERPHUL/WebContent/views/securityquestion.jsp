<!DOCTYPE html>
<html lang="en">
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Reset Password -  Security Questions</title>

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
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
    <STYLE>
    .error_message {
  margin-top: 0;
  margin-bottom: 0;
  color: #FF0000;
}

    </STYLE>
 
</head>

<body id="loginFrm">

    <div class="container">
        <div class="row">
           		
                <div class="login-panel panel panel-default" style= " margin-top: 2%; margin-right:276px; margin-left:82px;width:86%">
                    <div class="panel-heading">
                    <a class="glyphicon glyphicon-circle-arrow-left" href="Login"
			style="font-size: 20px;color: #337AB5;text-decoration: none;top:24px;"></a>
			  <div align="center"><h5 class="error_message">${statusMsg}</h5></div>
                        <div align="center"><h3 class="panel-title"><b>Reset Password - Security Questions</b></h3>
                    </div>
                    <form:form name="registerForm" id="registerForm" autocomplete="false" modelAttribute= "securityAnswers">
                    	<div class="panel-body">
                    		
                    		<h5><b>User Name:</b><input class="form-control" type="text" id="uname"
		                   		 style="width: 20%;display:inline-block;margin-left:455px;" value = "${username}" readonly>
		                    </h5>
                   			
                   			
		                    <h5><b>Old Password&nbsp;*:</b><input class="form-control" autocomplete="off" type="password" id="opsd"
		                   	 style="width: 20%;display:inline-block;margin-left:426px;" tabindex="1"/>
		                    </h5>
		                    
		                    <h5><b>New Password&nbsp;*:</b><input class="form-control" autocomplete="off" type="password" id="password"
		                   	maxlength="16" name = "password" style="width: 20%;display:inline-block;margin-left:420px;" tabindex="2">
		                    </h5>    
		                    <h5><b>Confirm Password&nbsp;*:</b><input class="form-control" autocomplete="off" type="password" id="confirm_password"
		                   	maxlength="16"	name="confirm_password" style="width: 20%;display:inline-block;margin-left:395px; margin-bottom:6px;" tabindex="3">
		                   		<span id='message'style="margin-left:460px;"></span>
		                    </h5>
		                   
		                    	 <p><b>Please fill the below Questions with Answers</b></p> 	
		                    
                      
                      <% int count=1; %>
                      <div class="form-group">
                       <table id="qusfeilds" >
                           <c:forEach var="question" items="${map}">
                          
							 <tr><td><input type="hidden" id="questionId" name="questionId"  value="${question.key}"></td>
							 <td><b><%=count++ %></b></td>
								<td><b>&nbsp;.&nbsp;<c:out value="${question.value}"></c:out></b></td>
                       		    <td><input class="form-control" autocomplete="off" id="ans1" name="ans1"  style = "margin-left:62px; width:186px; margin-bottom:6px;" tabindex="4"></td>
                       		 </tr>
                       			 
							</c:forEach>
							 </table>	         
                      </div>
                      
                      <input type="hidden" id="ans-list-info" name ="ans-list-info"/>
                      
                      	<div id="buttons" class="col-lg-12" style="padding-left:330px;">
							<button tabindex="5" id="submit" onclick=" return addCredentials();" class="btn btn-lg btn-success "
								style="padding: 6px 16px; margin-left: 38px;">Submit</button>
								<button tabindex="6" id="resetbtn" onclick="validateqns();" class="btn btn-lg"
								style="padding: 6px 20px; background-color: #94b1cb; color: white;">Reset</button>		
                      	</div>
						</div>        
                   <!-- Change this to a button or input when using this as a form -->
                   
                  
           </form:form>
            </div>
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
    
    <script src="js/backButton.js"></script> 
    
	
</body>

<script type="text/javascript">
$(function () {
    $("#resetbtn").on("click", function (e) {
        e.preventDefault();
       // $('#theForm')[0].reset(); // Or
        $('#registerForm').trigger("reset");
     
    });
});
$(function(){
	 $("#opsd").on("change", function (e){
	  var OldPwd='${password}';
	  var newPwd=document.getElementById("opsd").value;
	  if(OldPwd!=newPwd)
		  {
		  alert('Please Enter Correct password');
		  document.getElementById("opsd").value='';
		  document.getElementById("opsd").focus();
		  }
	  });
});
function addCredentials() {
	
	var isTrue=false;
	if (document.getElementById("opsd").value == '') {
		isTrue=true;
		
		alert('Please Enter Old Password.');
		return false;

	}
	if (document.getElementById("password").value == '') {
		isTrue=true;
		
		alert('Please Enter New Password.');
		return false;

	}
	else if ((password.value.length < 8) || (password.value.length > 16)) {
        
        password.style.background = 'Midnight';
        error = "The password length should be greater than 8 and less than 16 . \n";
        alert(error);
        password.focus();
        return false;
 
    }
	else if ( (password.value.search(/[a-zA-Z]+/)==-1) || (password.value.search(/[0-9]+/)==-1) ) {
        
        password.style.background = 'Midnight';
        error = "The password must contain at least one numeral.\n";
        alert(error);
        password.focus();
        return false;
 
    } 
	else if (password.value.search(/[A-Z]+/)==-1){
		
		 password.style.background = 'Midnight';
	        error = "The password must contain at least one capital letter.\n";
	        alert(error);
	        password.focus();
	        return false;
	}
	if (document.getElementById("confirm_password").value == '') {
		isTrue=true;
		
		alert('Please Confirm Password.');
		return false;

	}
	if (document.getElementById("ans1").value == '') {
		isTrue=true;
		
		alert('Please Fill Answers.');
		return false;

	}
	
	if( $( "#password" ).val().trim() != $( "#confirm_password" ).val().trim() ){
		alert( "Make sure password and confirm-password should match." );
		return false;
	}
	
	if( $( "#opsd" ).val().trim() == $( "#password" ).val().trim() ){
		alert( "Make sure Old password and New password should be Different." );
		return false;
	}
	
	var ansfeilds = $( "#qusfeilds input[name=ans1]" ),ansList = [];
	for( var i = 0 ; i < ansfeilds.length ; i++){
		if( $(ansfeilds[i]).val().trim !=""){
			ansList.push( { "QNO" : $(ansfeilds[i] ).closest( "tr" ).find( "input[name=questionId]" ).val(),
							"ANS" : $(ansfeilds[i] ).closest( "tr" ).find( "input[name=ans1]" ).val()});
		}
		}
		$( "#ans-list-info" ).val( JSON.stringify( ansList ) );
	if(isTrue==false){
		document.registerForm.action = "submitanswers";
		document.registerForm.method = "POST";
		document.registerForm.submit();	
	}
}
				/* confirm pwd alert msg */
/* $(function () {
    $("#submit").click(function () {
        var Password = $("#password").val();
        var ConfirmPassword = $("#confirm_password").val();
        if (Password != ConfirmPassword) {
            alert("Passwords do not match.");
            return false;
        }
        return true;
    });
}); */

 $('#password, #confirm_password').on('keyup', function () {
	  if ($('#password').val() == $('#confirm_password').val()) {
	    $('#message').html('Matching').css('color', 'green');
	  } else 
	    $('#message').html('Not Matching').css('color', 'red');
		  $('#submit').prop('disabled' , false);
	}); 
	$( "#registerForm" ).on( "submit", function(e){
		var validation_flg = true,
		    err_msg        = "";
		// todo
		/* if( $( "#password" ).val().trim() != ""  ){
			err_msg = "Please enter password";
		} else if( $( "#password" ).val().trim().length > 6 ){
			err_msg = "Password should be above 6 digit";
		} else */ if( $( "#password" ).val().trim() != $( "#confirm_password" ).val().trim() ){
			err_msg = "Make sure password and confirm-password are match.";
		}
		
		if( err_msg != "" ){
			e.preventDefault();
			return false;
		}
	} )
</script>
<script type="text/javascript">
	
</script>
</html>

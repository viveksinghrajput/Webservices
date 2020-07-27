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

    <title>Rural Marketing ERP</title>

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

<body id="loginFrm" >

    <div class="container">
    
        <div class="row">
            <div class="col-md-4 col-md-offset-4">
           	<div class="nav navbar-top-links navbar-left">
				 <a class="navbar-brand" style="font-size: 28px;margin-top: 32px;padding-left: 26px; text-shadow: 2px 1px 0 #1e90ff, 
                2px 1px 0 #1e90ff;">Rural Resource Planner</a>
			</div>
                <div class="login-panel panel panel-default">
                  <div class="panel-heading" >
                  
                  <h3 class="panel-title"><b style="margin-left: 16px;"><font style="color: black;">Sign In</font></b></h3>&nbsp;
                  		 <div class="panel-title"> 
                         <div align="center"><h5 class="error_message">${message}</h5></div>
                    </div> 
                  		<div style="text-align:left;margin-left: 102px;">
                  			<img src="dist/img/Home.png" class="nav navbar-top-links" alt="Unilever" height="90" width="110"><br/>
                  		</div>
                        
                 
                    <form:form name="loginForm" id="loginForm">
                    <div class="panel-body">
                            <fieldset>
                                <div class="form-group">
                                    <input class="form-control" placeholder="User Name" id = "username" name="username" type="text" 
                                    autofocus>
                                </div>
                                <div class="form-group">
                                    <input class="form-control" placeholder="Password" id = "password" name="password" type="password" 
                                    value="">
                                </div>
                                
                             	<div class ="form-group">
						             <input type="text" id="mainCaptcha" oncopy="return false" oncut="return false" onpaste="return false" style="width: 158px; height: 30px; font-style: italic; font-size: larger; font-weight: bold; background-image :url(dist/img/bgimg.jpg)" readonly="readonly"/>
										 <img src = "dist/img/refreshnew.png" style = "height: 24px; " id="refresh" onclick="Captcha();" />
						          <div class ="form-group">
						            <input type="text" class="form-control" placeholder="Captcha"  id="txtInput" name= "txtInput" style="width: 158px; height: 30px; margin-top: 8px; margin-bottom: 8px;"/>
						          </div>
						        
						       </div>
						        
                   <!-- Change this to a button or input when using this as a form -->
      
         <input class="btn btn-lg btn-success btn-block" id="login" type="button" value="Submit">
		 </fieldset>
                    </div>
                    </form:form>
                </div>
            </div>
        </div>
    </div>
	</div>
	<div class="col-lg-18">
	 <div class="nav navbar-top-links navbar-middle" style="text-align: center;padding: 0px 0px 0px 0px;">
                <img src="dist/img/logo.jpg" class="nav navbar-top-links" alt="Unilever" height="80" width="80"><br/>
            	<div class="col-lg-22"><p style="font-size:14px;text-align:center;">Copyright Unilever 2018</p>
                 </div>
      </div>
    </div>
    
    <!-- jQuery -->
    <script src="vendor/jquery/jquery.min.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="vendor/bootstrap/js/bootstrap.min.js"></script>

    <!-- Metis Menu Plugin JavaScript -->
    <script src="vendor/metisMenu/metisMenu.min.js"></script>

    <!-- Custom Theme JavaScript -->
    <script src="dist/js/sb-admin-2.js"></script>
	<!-- login validation .js -->
	<script src = "js/login.js"></script>
	
	 
	 <script type="text/javascript">
                 function Captcha(){
                     var alpha = new Array('A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S',
                    		 'T','U','V','W','X','Y','Z','1','2','3','4','5','6','7','8','9','0');
                     var i;
                     for (i=0;i<6;i++){
                       var a = alpha[Math.floor(Math.random() * alpha.length)];
                       var b = alpha[Math.floor(Math.random() * alpha.length)];
                       var c = alpha[Math.floor(Math.random() * alpha.length)];
                       var d = alpha[Math.floor(Math.random() * alpha.length)];
                       var e = alpha[Math.floor(Math.random() * alpha.length)];
                       var f = alpha[Math.floor(Math.random() * alpha.length)];
                       var g = alpha[Math.floor(Math.random() * alpha.length)];
                      }
                    var code = a +" " + b +" " + c +" " + d +" " +e +" " +f +" " +g;
                    document.getElementById("mainCaptcha").value = code
                  }
                 
                  function removeSpaces(string){
                    return string.split(' ').join('');
                  }
             		
             </script>
             
             <script>
             
             (function (global, $) {

            	 var _hash = "!";
            	 var noBackPlease = function () {
            	 global.location.href += "#";
            	 global.setTimeout(function () {
            	 global.location.href += "!";
            	 }, 5);
            	 };

            	 global.onhashchange = function () {
            	 if (global.location.hash != _hash) {
            	 global.location.hash = _hash;
            	 }
            	 };

            	 global.onload = function () {
            	 noBackPlease();
            	 Captcha();
            	 };

            	 })(window, jQuery || window.jQuery);

             </script>
              
</body>

</html>

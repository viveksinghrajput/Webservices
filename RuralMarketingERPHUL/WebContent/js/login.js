$("#login").click(function(){
var username=document.loginForm.username;
var password=document.loginForm.password;
var txtInput=document.loginForm.txtInput;
if(username.value==''){
username.style.background = 'Midnight';
error = "Please Enter username ";
alert(error);
username.focus();
return false;
}
if(password.value==''){
password.style.background='Midnight';
error="Please Enter the password ";
alert(error);
password.focus();
return false;
}
if(txtInput.value==''){
txtInput.style.background = 'Midnight';
error = "Please Enter captcha";
alert(error);
txtInput.focus();
return false;
}

if(txtInput.value!=''){
var string1 = removeSpaces(document.getElementById('mainCaptcha').value);
var string2 = removeSpaces(document.getElementById('txtInput').value);
if (string1 != string2){
alert('Invalid Captha');
return false;
}
}
document.loginForm.action = "LoginUser";
document.loginForm.method = "POST";
document.loginForm.submit();	
});


$("#savingOnclick").click(function(){

var role=document.createUser.role;
var agency=document.createUser.agency;
var vendor=document.createUser.vendor;
var firstname = document.createUser.firstname;
var lastname = document.createUser.lastname;
var username = document.createUser.username;
var user=/^[0-9a-zA-Z]+$/;
var password = document.createUser.password;
var password1=/[\W_]/;
    var email = document.createUser.email;
    var reg = /^([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/;
    var contact = document.createUser.contact;
    var mob = /^[1-9]{1}[0-9]{9}$/;
    var state = document.createUser.state;
    
    /*var huloutletcode=document.createUser.huloutletcode;
    var hulcode=/^[0-9a-zA-Z]+$/;
    var gstn=document.createUser.gstn;
    var gstncode=/^[0-9a-zA-Z]+$/;*/
    
    if (role.value == '-1')
    {
    role.style.background = 'Midnight';
    error = "Please Select the Role ::\n";
    alert(error);
        role.focus();
        return false;
    }
    if(role.value==5){
    if (brand.value == '-1'){
    brand.style.background = 'Midnight';
        error = "Please Select the Brand ::\n";
        alert(error);
    brand.focus();
    return false;
    } 
    }
    if(role.value==3){
    if (agency.value == '-1'){
    agency.style.background = 'Midnight';
        error = "Please Select the Agency ::\n";
        alert(error);
    agency.focus();
    return false;
    }
    } 
    if(role.value==4){
    if (agency.value == '-1'){
    agency.style.background = 'Midnight';
        error = "Please Select the Agency ::\n";
    alert(error);
    agency.focus();
    return false;
    }if(vendor.value=='-1'){
    vendor.style.background = 'Midnight';
        error = "Please Select the Vendor ::\n";
    alert(error);
    vendor.focus();
    return false;
    }
    }
    if (firstname.value == '')
    {
    firstname.style.background = 'Midnight';
    error = "Please Enter The FirstName ::\n";
alert(error);
        firstname.focus();
        
        return false;
    }
    if (lastname.value == '')
    {
    lastname.style.background = 'Midnight';
    error = "Please Enter The LastName ::\n";
alert(error);
        lastname.focus();
        return false;
    }
    if (email.value == '')
    {
    email.style.background = 'Midnight';
    error="Please Enter The Email ::";
        alert(error);
       
        email.focus();
        return false;
    }else if (reg.test(email.value) == false) 
    { 
    email.style.background = 'Midnight';
error="Invalid Email Address--> Try Again..";
alert(error);
           
            email.focus();
            return false;
      } 
    if (contact.value == '')
    {
    contact.style.background = 'Midnight';
error="Please Enter The contact ::";
alert(error);
       
        contact.focus();
        return false;
    }
    else if (mob.test(contact.value) == false) {
    contact.style.background = 'Midnight';
error="Enter 10 Digit Mobile No start with [7-9]";
alert(error);
        
        contact.focus();
        return false;
    }
    if (username.value == '')
    {
    username.style.background = 'Midnight';
    error="Please Enter The UserName ::";
    alert(error);
        username.focus();
        return false;
    }
    else if (user.test(username.value) == false) 
    { 
    username.style.background = 'Midnight';
error="Enter numbers and alphabets only";
alert(error);
username.focus();
        return false;
      } 
    if (state.value == '-1')
    {
    state.style.background = 'Midnight';
error="Please Select The state ::";
alert(error);
      
        state.focus();
        return false;
    }
    if (city.value == '-1')
    {
    city.style.background = 'Midnight';
error="Please Select The city ::";
alert(error);
       
        city.focus();
        return false;
    }
    else{
document.createUser.action = "saveNewUser";
document.createUser.method = "POST";
document.createUser.submit();
}
});




$("#username").change(function(){
    $.ajax({
        type: "POST",
        url: "checkUsername",
        data: {"username": $(this).val()},
        success: function(msg){

              if(msg == 1){
            alert("UserName exists !... try another");
              document.getElementById('username').value = "";
              $('#username').focus();
              }
        },
        error:function(){
        }
    });

});

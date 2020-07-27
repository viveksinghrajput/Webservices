function showOuterImage(){
	$('#imagepreview').attr('src', $('#outer_img').attr('src')); 
	   $('#imagemodal').modal('show'); 
}

function showFirstInnerImage(){
	$('#imagepreview').attr('src', $('#firstInner_img').attr('src')); 
	$('#imagemodal').modal('show');
}

function showSecondInnerImage(){
	$('#imagepreview').attr('src', $('#secondInner_img').attr('src')); 
	$('#imagemodal').modal('show');
	
}
var markersData = [];

function showMap(_obj){
	$( ".map-wrapper-background" ).show();
	var _lat_long = _obj;
	markersData = [
	          	     {lat : _lat_long[1],lng : _lat_long[2], image: _lat_long[0]},
	          	     {lat: _lat_long[4], lng : _lat_long[5], image: _lat_long[3]},
	          	     {lat : _lat_long[7],  lng : _lat_long[8], image: _lat_long[6]}
	          	];
	initialize();
    // The location of Uluru
//var india = { lat: 21.76, lng: 78.87 };

   
/*
                   var map = new google.maps.Map(document.getElementById('google-map-shower'), {

                     zoom: 11,

                     center: new google.maps.LatLng(21.76, 78.87),

                     mapTypeId: google.maps.MapTypeId.ROADMAP

                   });



                   var infowindow = new google.maps.InfoWindow();



                   var marker, i;



                   for (i = 0; i < locations.length; i++) {

                     marker = new google.maps.Marker({

                       position: new google.maps.LatLng(locations[i][1], locations[i][2]),

                       map: map

                     });



                    google.maps.event.addListener(marker, 'click', (function(marker, i) {

                       return function() {

                         infowindow.setContent(locations[i][0]);

                         infowindow.open(map, marker);

                       }

                     })(marker, i));
}*/
}
/*function initMap(var1,var2,var3,var4,var5,var6,var7,var8,var9){

	$('#imagemodal').modal('show'); 
}
*/
function massApprove(){
	
	var boxChecked=0;
	for(var i=0; i < document.pageForm.wareHouse_submit.length; i++)
	{
		if(document.pageForm.wareHouse_submit[i].checked)
		{
			
			boxChecked++;
		}
	}
	if(boxChecked<=0){
		alert("Please Select Atleast One WareHouse");
		return false;
	
	}else{
		var result=confirm("Are You Sure You want to Mass Approve these WareHouses ?");
		if(result)
		{
			document.pageForm.action = "massApprove";
			document.pageForm.method = "POST";
			document.pageForm.submit();
			return true;
		}else{
			return false;
		}
	}
	
}

/*function massApprove(){
	
	
	
	
	for(var i=0; i < document.pageForm.wareHouse_submit.length; i++)
	{
		if(!document.pageForm.wareHouse_submit[i].checked)
		{
			
			alert("Please Select Atleast One WareHouse");
			return false;
		}else{
			var result=confirm("Are You Sure You want to Mass Approve these WareHouses ?");
			//alert(result);
			if(result)
			{
				document.pageForm.action = "massApprove";
				document.pageForm.method = "POST";
				document.pageForm.submit();
				return true;
			}else{
				return false;
			}
		}
	}
	
}*/
function massReject(){
	var boxChecked=0;
	
	
	for(var i=0; i < document.pageForm.wareHouse_submit.length; i++)
	{
		if(document.pageForm.wareHouse_submit[i].checked)
		{
			
			boxChecked++;
		}
	}
	
	if(boxChecked<=0){
		alert("Please Select Atleast One WareHouse");
		return false;
	
	}else{
		var result=confirm("Are You Sure You want to Mass Reject these WareHouses ?");
		if(result)
		{
			document.pageForm.action = "massReject";
			document.pageForm.method = "POST";
			document.pageForm.submit();
			return true;
		}else{
			return false;
		}
		
	}
	
	
	
}
/*function massReject(){
	
	
	for(var i=0; i < document.pageForm.wareHouse_submit.length; i++)
	{
		if(!document.pageForm.wareHouse_submit[i].checked)
		{
			
			alert("Please Select Atleast One WareHouse");
			return false;
		}else{
			var result=confirm("Are You Sure You want to Mass Reject these WareHouses ?");
			if(result)
			{
				document.pageForm.action = "massReject";
				document.pageForm.method = "POST";
				document.pageForm.submit();
				return true;
			}else{
				return false;
			}
			
		}
	}
	
}*/

function approve(){
	var commentVal=document.getElementById("comment").value;
	// alert("Comment :"+commentVal);
	
	if(commentVal.length<1)
	   {
	        window.alert ("Please Enter Comments.");
	        document.wareHouseForm.comment.focus();
	        return true;
	   }
	   else
	   {
		   //alert("Comment :"+commentVal);
		   
		   /*
			 * var wareHouseId=$('#wareHouseId'); alert("wareHouseId:
			 * "+wareHouseId);
			 */
		   
		   var id=document.getElementById("wareHouseId").value;
		   //alert("WareHouse Id: "+id);
	       approveWarehouse();
	       return false;
	   }
}
function reject(){
	var commentVal=document.getElementById("comment").value;
	// alert("Comment :"+commentVal);
	
	if(commentVal.length<1)
	   {
	        window.alert ("Please Enter Comments.");
	        document.wareHouseForm.comment.focus();
	        return true;
	   }
	   else
	   {
		   // alert("Comment :"+commentVal);
		   
		   var id=document.getElementById("wareHouseId").value;
		  // alert("WareHouse Id: "+id);
	       rejectWarehouse();
	       return false;
	   }
}

function approveWarehouse(){
	alert("Are You Sure You want to Approve this WareHouses ?");
	
	document.wareHouseForm.action = "approveWarehouse";
	document.wareHouseForm.method = "POST";
	document.wareHouseForm.submit();
	
}

function rejectWarehouse(){
	alert("Are You Sure You want to Reject this WareHouses ?");
	
	document.wareHouseForm.action = "rejectWarehouse";
	document.wareHouseForm.method = "POST";
	document.wareHouseForm.submit();
	
}

function showImages(){
	
	alert("Show Images Called   :");
		
}

function showMapWareHouse(lat,long){
	alert("showMapWareHouse Called..")
}

function editWareHouse(){
	
	// Enable Fields for Editing
	enableFieldsForEdit();
}

function enableFieldsForEdit(){
	document.getElementById("submit").disabled = false;
	document.getElementById("contact").disabled = false;
	document.getElementById("email").disabled = false;
	document.getElementById("address01").disabled = false;
	document.getElementById("address02").disabled = false;
	document.getElementById("state").disabled = false;
	document.getElementById("city").disabled = false;
	document.getElementById("pincode").disabled = false;
	document.getElementById("area").disabled = false;
}
function saveDetails(){
	var result=fieldValidation();
	//alert("Result :"+result);
	
	var validation=checkValue(result);
	if(validation)
	{
		document.wareHouseForm.action = "updateVen";
		document.wareHouseForm.method = "POST";
		document.wareHouseForm.submit();
	}else{
		return false;
	}
}

function checkValue(arr){
	var status = true;
	/*var pattern= /error/;
	var exists = pattern.test(arr);
	if(!exists){
		status = false;
	}*/
	
	if( arr.indexOf('error') >= 0){
		status=false;
	}
	
	return status;
}

/*function saveDetails(){

	var fields=fieldValidation();
	//alert("Validation Result: "+fields);
		
	var validation=checkValue(fields,"false");
	
	if(validation)
	{
		
		alert("Form Validated..")
		
		document.wareHouseForm.action = "updateVendor";
		document.wareHouseForm.method = "POST";
		document.wareHouseForm.submit();
		 	
	}else{
		alert("Form Not Validated..")
		return false;
	}
	
	
	
}
*/




function fieldValidation(){
	
	
	
	// Field Contact Validation
	var contact=contactValidation();
	
	// Field Email Validation
	var email=emailValidation();
	
	// Field Address 01 Validation
	var add01=address01Validation();
	
	// Field Address 02 Validation
	var add02=address02Validation();
	
	// Field State Validation
	var state=stateValidation();
	
	// Field City Validation
	var city=cityValidation();
	
	// Field PinCode Validation
	var pin=pincodeValidation();
	
	// Field Area Validation
	var area=areaValidation();
	
	var result=[contact,email,add01,add02,state,city,pin,area];
	// alert("Validation Result: "+result);
	return result;
}

function contactValidation(){
	var contactVal=document.getElementById("contact").value;
	
	var pattern = /^\d{10}$/;
	if (pattern.test(contactVal)) {
        return "";
    }else{
    	alert("Please Enter 10 Digits Contact Number.");
        document.wareHouseForm.contact.focus();
        return "error";

	}
	
		
}

function emailValidation(){
	var emailVal=document.getElementById("email").value;
	// alert("Email :"+emailVal);
	var emailRegex = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
	
	var status=emailRegex.test(emailVal);
	// alert("Email Status :"+status);
	if(status)
	{
		// alert("Email is Okay");
		return "";
	}else{
		alert("Please Enter a Valid Email Id.");
        document.wareHouseForm.email.focus();
        return "error";
	}
}

function address01Validation(){
	var add01Val=document.getElementById("address01").value;
	// alert("Address 01 Value:"+add01Val);
	
	if(add01Val.length<1){
		alert("Enter WareHouse Address 1");
		document.wareHouseForm.address01.focus();
		return "error";
	}else{
		return "";
	}
	
}

function address02Validation(){
	var add02Val=document.getElementById("address02").value;
	// alert("Address 02 Value:"+add02Val);
	
	if(add02Val.length<1){
		alert("Enter WareHouse Address 2");
		document.wareHouseForm.address02.focus();
		return "error"
	}else{
		return ""
	}
	
}

function stateValidation(){
	var stateVal=document.getElementById("state").value;
	// alert("State Value: "+stateVal)
	
	if(stateVal.length<1){
		alert("Enter State");
		document.wareHouseForm.state.focus();
		return "error"
	}else{
		return ""
	}
	
}

function cityValidation(){
	var cityVal=document.getElementById("city").value;
	// alert("State Value: "+cityVal)
	
	if(cityVal.length<1){
		alert("Enter State");
		document.wareHouseForm.city.focus();
		return "error"
	}else{
		return ""
	}
	
}

function pincodeValidation(){
	var pinVal=document.getElementById("pincode").value;
	// alert("State Value: "+cityVal)
	
	var pinCodeRegex=/^\d{6}$/;
	
	if(pinVal.length<1){
		alert("Enter PinCode");
		document.wareHouseForm.pincode.focus();
		return "error"
	}else if(!pinCodeRegex.test(pinVal))
	{
		alert("Pin code should be 6 digits ");
		document.wareHouseForm.pincode.focus();
		return "error";
	
	}else{
		return "";
	}
	
}

function areaValidation(){
	var areaVal=document.getElementById("area").value;
	// alert("State Value: "+cityVal)
	var numbers = /^[0-9]+$/;
	if(areaVal.length<1){
		alert("Enter Area");
		document.wareHouseForm.area.focus();
		return "error";
	}else if(!numbers.test(areaVal)){
		alert("Enter Area in Numbers");
		document.wareHouseForm.area.focus();
		return "error";
	}else{
		return "";
	}
}

function showHistory(){
	
	document.wareHouseForm.action = "wareHouseHistory";
	document.wareHouseForm.method = "POST";
	document.wareHouseForm.submit();
}
function showWareHouseHistory(){
	alert("showWareHouseHistory submitted");
	var id = $(this).attr('data-id');
	alert(id);
	document.pageForm.action = "showWareHouseHistory";
	document.pageForm.method = "POST";
	document.pageForm.submit();
}



///////////////////////////////////////////////////////////////////////////

var map;
var infoWindow;

//markersData variable stores the information necessary to each marker
/*var markersData = [ {
lat: 12.971891,
lng: 77.641154,
name: "Jayasurya Tex",
address1:"Anna strret",
address2: "Chennai",
postalCode: "625789"
},
{
lat: 12.992214,
lng: 77.715900,
name: "Ram Tex",
address1:"65 Kovalan strret",
address2: "Chennai",
postalCode: "625001"
},
{
lat: 12.922637,
lng: 77.617444
} 
];*/



function initialize(_lat_long) {
	
var mapOptions = {
center: new google.maps.LatLng(12.972442,77.580643),
zoom: 10,
mapTypeId: 'roadmap',
};

map = new google.maps.Map(document.getElementById('google-map-shower'), mapOptions);

// a new Info Window is created
infoWindow = new google.maps.InfoWindow();

// Event that closes the Info Window with a click on the map
google.maps.event.addListener(map, 'click', function() {
infoWindow.close();
});

// Finally displayMarkers() function is called to begin the markers creation
displayMarkers();
}
//google.maps.event.addDomListener(window, 'load', initialize);


//This function will iterate over markersData array
//creating markers with createMarker function
function displayMarkers(){

// this variable sets the map bounds according to markers position
var bounds = new google.maps.LatLngBounds();

// for loop traverses markersData array calling createMarker function for each marker 
for (var i = 0; i < markersData.length; i++){

var latlng = new google.maps.LatLng(markersData[i].lat, markersData[i].lng);
var name = markersData[i].name;
var address1 = markersData[i].address1;
var address2 = markersData[i].address2;
var postalCode = markersData[i].postalCode;
var imageTitle = markersData[i].image;
createMarker(latlng, name, address1, address2, postalCode, imageTitle);

// marker position is added to bounds variable
bounds.extend(latlng);  
}

// Finally the bounds variable is used to set the map bounds
// with fitBounds() function
map.fitBounds(bounds);
}

//This function creates each marker and it sets their Info Window content
function createMarker(latlng, name, address1, address2, postalCode, imageTitle){
var marker = new google.maps.Marker({
map: map,
position: latlng,
title: name
});

// This event expects a click on a marker
// When this event is fired the Info Window content is created
// and the Info Window is opened.
google.maps.event.addListener(marker, 'click', function() {

// Creating the content to be inserted in the infowindow
var iwContent = '<div id="iw_container">' +
'<div class="iw_title">' + imageTitle + '</div></div>';

// including content to the Info Window.
infoWindow.setContent(iwContent);

// opening the Info Window in the current map and at the current marker location.
infoWindow.open(map, marker);
});
}


$( document ).ready(function(){
	$( document ).on( "click", ".map-wrapper-background", function(e){
		if($(e.target).is( ".map-wrapper-background" )){
			$( ".map-wrapper-background" ).hide();
		}
	} )
});

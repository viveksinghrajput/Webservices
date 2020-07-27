function saveSaturation(){
	//alert("saveSaturation");
	document.itemForm.action = "updateSat";
	document.itemForm.method = "POST";
	document.itemForm.submit();
}

function saveConversion(){
	//alert("saveConveresion");
	document.itemForm.action = "updateConv";
	document.itemForm.method = "POST";
	document.itemForm.submit();
}

function validateNum(evt) {
	//alert("Key Pressed");
	  var theEvent = evt || window.event;
	  var key = theEvent.keyCode || theEvent.which;
	  key = String.fromCharCode( key );
	  if (evt.key == "Backspace" || evt.key == "Del") return true;
	  var regex = /[0-9]|\./;
	  if( !regex.test(key) ) {
	    theEvent.returnValue = false;
	    if(theEvent.preventDefault) theEvent.preventDefault();
	  }
}


/*
function saveNewVendor(){
	document.createVendor.action = "saveNewVendor";
	document.createVendor.method = "POST";
	document.createVendor.submit();

}
*/
function saveNewUser(){
	document.createUser.action = "saveNewUser";
	document.createUser.method = "POST";
	document.createUser.submit();

}


//loading the vendor with respect to Agency
function loadVendorData(){
	/*$.ajax({
		contentType : "application/json",
		type : "POST",
		url : "getVendorofAgency?agency=" + $('#agency').val(),
		success : function(data) {
			var vendors = new Array();
			vendors = data;
			var select = document.getElementById("vendor");
			$('#vendor option[value!="-1"]').remove();
			for (var i = 0; i < vendors.length; i++) {
				select.options[select.options.length] = new Option(
						vendors[i], vendors[i]);
			}
		}
	});*/
	var select = document.getElementById("vendor");
	var strAgency=$('#agency').val();
	var sendData="&agency="+strAgency;
	var vendors=new Array();

	$.ajax({
	contentType : "application/json",
	type : "POST",
	url : "getVendorofAgency?"+sendData,
	success : function(data) {
	var listItems= "<option value='-1'>--SELECT--</option>";
	$.each(data, function(key, value) {
	listItems+= "<option value='" + key + "'>" + value + "</option>";
	});
	$("#vendor").html(listItems);
	}
	});
	return vendors;
}

function loadOptions() {
	
	var roleVal=$('#role').val();
	//alert("roleVal :"+ roleVal);
	if($('#role').val() == 1 || $('#role').val() == 2 || $('#role').val() == 6 || $('#role').val() == 7 || $('#role').val() == 8 ) {
       //alert("Admin Selected");
		document.getElementById("brandMenu").style.display = "none";
        document.getElementById("secondaryMenu").style.display = "none";
        document.getElementById("selectAgency").style.display = "none";
        document.getElementById("selectVendor").style.display = "none";
        document.getElementById("chooseMobile").style.display = "none";
    } 
/*	
	if($('#role').val() == 2) {
        //alert("Business Selected");
		document.getElementById("brandMenu").style.display = "none";
        document.getElementById("secondaryMenu").style.display = "none";
        document.getElementById("selectAgency").style.display = "none";
        document.getElementById("selectVendor").style.display = "none";
        document.getElementById("chooseMobile").style.display = "none";
    } */
	
	if($('#role').val() == 3) {
      //alert("Agency Selected");
		document.getElementById("brandMenu").style.display = "none";
        document.getElementById("secondaryMenu").style.display = "block";
        document.getElementById("selectAgency").style.display = "block";
        document.getElementById("selectVendor").style.display = "none";
        document.getElementById("chooseMobile").style.display = "none";
    } 

	if($('#role').val() == 4) {
      
      //alert("Vendor Selected");
		document.getElementById("brandMenu").style.display = "none";
        document.getElementById("secondaryMenu").style.display = "block";
        document.getElementById("selectAgency").style.display = "block";
        document.getElementById("selectVendor").style.display = "block";
        document.getElementById("chooseMobile").style.display = "block";
    }
	
	if($('#role').val() == 5) {
		//alert("Brand Selected");
		document.getElementById("brandMenu").style.display = "block";
        document.getElementById("secondaryMenu").style.display = "none";
        document.getElementById("selectAgency").style.display = "none";
        document.getElementById("selectVendor").style.display = "none";
        document.getElementById("chooseMobile").style.display = "none";
    }
	/*
	if($('#role').val() == 6) {
        //alert("Audit Selected");
		document.getElementById("brandMenu").style.display = "none";
        document.getElementById("secondaryMenu").style.display = "none";
        document.getElementById("selectAgency").style.display = "none";
        document.getElementById("selectVendor").style.display = "none";
        document.getElementById("chooseMobile").style.display = "none";
    } 
	
	if($('#role').val() == 7) {
        //alert("Logistics Selected");
		document.getElementById("brandMenu").style.display = "none";
        document.getElementById("secondaryMenu").style.display = "none";
        document.getElementById("selectAgency").style.display = "none";
        document.getElementById("selectVendor").style.display = "none";
        document.getElementById("chooseMobile").style.display = "none";
    } 
	
	if($('#role').val() == 8) {
        //alert("Production Selected");
		document.getElementById("brandMenu").style.display = "none";
        document.getElementById("secondaryMenu").style.display = "none";
        document.getElementById("selectAgency").style.display = "none";
        document.getElementById("selectVendor").style.display = "none";
        document.getElementById("chooseMobile").style.display = "none";
    } 
	
	*/
	
}

function loadCities() {
	var select = document.getElementById("city");
	var strState = $('#state').val();
	var sendData="&state="+strState;
	
	var cities = new Array();
	$.ajax({
				contentType : "application/json",
				type : "POST",
				//url : "getCity?brand=" + $('#state').val(),
				url : "getCityState?"+sendData,
				success : function(data) {
					var listItems= "<option value='-1'>--SELECT--</option>";
					$.each(data, function(key, value) {
					listItems+= "<option value='" + key + "'>" + value + "</option>";
					});
					$("#city").html(listItems);
					
				}
			});
		
		return cities;
}

function load_edit_Cities() {
	var select = document.getElementById("edit_city");
	var strState = $('#edit_state').val();
	
	//alert("strState :"+strState);
	
	var sendData="&state="+strState;
	
	var cities = new Array();
	$.ajax({
				contentType : "application/json",
				type : "POST",
				//url : "getCity?brand=" + $('#state').val(),
				url : "getCityState?"+sendData,
				success : function(data) {
					//alert("Cities Data length"+data.length);
					cities = data;
					if(data.length >0)
					{
						$('#edit_city option[value!="-1"]').remove();
						remove_edit_City_Options();
						for (var i = 0; i < cities.length; i++) {
							select.options[select.options.length] = new Option(cities[i], cities[i]);
						}
					}else{
						$('#edit_city option[value!="-1"]').remove();
						$('#edit_city option[value!=" "]').remove();
						
						var option = document.createElement("option");
						option.text = "-- No Data --";
					    option.value = " ";
						try {
							select.add(option, null); //Standard
					    }catch(error) {
					    	select.add(option); // IE only
					    }
					}
					
					
				}
			});
		
		return cities;
}

function remove_edit_City_Options(){
    var select = document.getElementById("edit_city");
    select.options.length = 0;
}


// India uses thousands/lakh/crore separators

$( document ).on( "change", ".image_uploader_with_fname", function(){
	if( $( this )[0].files.length != 0 ){
		if( $( this ).next().next().is( '.fileuploadFname' ) ){
			//alert($( this )[0].files[0].name.substr(0, 3)+'...'+$( this )[0].files[0].name.substr($( this )[0].files[0].name.lastIndexOf('.') + 1).toLowerCase() );
			$( this ).next().next().text( $( this )[0].files[0].name.substr(0, 3)+'...'+$( this )[0].files[0].name.substr($( this )[0].files[0].name.lastIndexOf('.') + 1).toLowerCase()  );
		} else {
			$( this ).next().after( '<label class="fileuploadFname">'+$( this )[0].files[0].name.substr(0, 3)+'...'+$( this )[0].files[0].name.substr($( this )[0].files[0].name.lastIndexOf('.') + 1).toLowerCase() +'</label>' );
		}
	} else {
		if( $( this ).next().next().is( ".fileuploadFname" ) ){
			$( this ).next().next().remove();
		}
	}
});
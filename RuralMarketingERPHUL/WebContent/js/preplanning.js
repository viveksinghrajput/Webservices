/*document.load(function(){
	reset();
});*/

function showAllPrePlan(){
	var x = document.getElementById("showAllPrePlanTable");
    if (x.style.display === "none") {
        x.style.display = "block";
    } else {
        x.style.display = "none";
    }
}





function loadConvAndSat() {
$.ajax({
			contentType : "application/json",
			type : "POST",
			url : "getConvAndAvgSales?brand=" + $('#brand').val(),
			success : function(data) {
				var mydata = new Array();
				mydata=data;
				//alert("Data : "+mydata);
				var inputConversion = document.getElementById("conversion");
				var inputaverageSales = document.getElementById("averageSales");
				inputConversion.value=mydata[0];
				inputaverageSales.value=mydata[1];
			}
		});
}


function showPrePlanData(){
	
	getPrePlanList();
	//show Save Button
	document.getElementById("createPrePlan").disabled = false;
}

function getPrePlanList()
{
	//Check for Empty Fields 
	var fields=false;
	fields=emptyCheck();
	
	if(fields)
	{
		
		//Check for Span Range
		var spanCheck = false;
		spanCheck=checkSpan();
		if(spanCheck)
		{
			document.frm1.action = "getPreplanlistNew";
			document.frm1.method = "POST";
			document.frm1.submit();
		}
	}
	
}

function loadStates() {
	var select = document.getElementById("state");
	//Clear existing values from State Selection
	resetSelectElement(document.getElementById("state"));
	
	var strBrand = $('#brand').val()
	
	//alert("strBrand :"+strBrand);
	var sendData="brand="+strBrand;
	
	var states = new Array();
	$.ajax({
				contentType : "application/json",
				type : "POST",
				url : "getState?"+sendData,
				success : function(data) {
					//alert("states Data length"+data.length);
					states = data;
					if(data.length >0)
					{
						$("#state").prepend("<option value='' selected='selected'>-- Select --</option>");
						
						for (var i = 0; i < states.length; i++) {
							select.options[select.options.length] = new Option(
									states[i], states[i]);
						}
					}else{
						
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
		
		return states;
	}



function loadCities() {
	var select = document.getElementById("city");
	var strState = $('#state').val();
	var strBrand = $('#brand').val()
	//alert("strState :"+strState);
	//alert("strBrand :"+strBrand);
	var sendData="brand="+strBrand+"&state="+strState;
	
	var cities = new Array();
	$.ajax({
				contentType : "application/json",
				type : "POST",
				//url : "getCity?brand=" + $('#state').val(),
				url : "getCity?"+sendData,
				success : function(data) {
					//alert("Cities Data length"+data.length);
					cities = data;
					if(data.length >0)
					{
						$('#city option[value!="-1"]').remove();
						for (var i = 0; i < cities.length; i++) {
							select.options[select.options.length] = new Option(
									cities[i], cities[i]);
						}
					}else{
						$('#city option[value!="-1"]').remove();
						$('#city option[value!=" "]').remove();
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

function getSaturation(){
	
	var cityVal=($('#city').val());
	var brandVal=($('#brand').val());
	var stateVal=($('#state').val());
	var inputSaturation = document.getElementById("saturation");
	
	var sendURLData="brand="+brandVal+"&state="+stateVal+"&city="+cityVal;
	//alert(cityVal);
	var output="";
		$.ajax({
			contentType : "application/json",
			type : "POST",
			/*url : "getSaturation?city=" + $('#city').val(),*/
			url : "getSaturation?"+sendURLData,
			success : function(data) {
				var mydata = new Array();
				mydata=data;
				inputSaturation.value=data;
				//alert("Data length: "+mydata.length);
				
				
				
				
				
			}
		});
}

function citySelection(){
	
	
	getSaturation();
	
}


function stateSelection() {
	
	//alert("stateSelection called");
	
	//validate Brand Selection
	var strBrand = $('#brand').val()
	if((strBrand==null) || (strBrand='')){
		alert("Please Select Brand First");
		$('#brand').focus();
		return false;
	}
	//Reset Saturation Field
	document.getElementById("saturation").value="";
	
	//Reset Citi Options
	removeCityOptions();
	
	//Load Cities
	var myData=loadCities();
		
}



function showSaveButton(){
	//Disable CreatePre Plan Button
	document.getElementById("createPrePlan").disabled = false;
}

function hideMsgAndButton(){
	//Hide Successful Msg
	document.getElementById("prePlanCreatedMsg").style.display = 'none';
	
	//Disable CreatePre Plan Button
	document.getElementById("createPrePlan").disabled = true;
}



function showDataTable() {
	var x = document.getElementById("prePlanTable");
    x.style.display = "block";
}
function hideDataTable() {
    var x = document.getElementById("prePlanTable");
    x.style.display = "none";
    
}

function resetPage(){
	//reset();
	hideMsgAndButton();
	hideDataTable();
	removeCityOptions();
	resetSelectElement(document.getElementById("brand"));
	resetSelectElement(document.getElementById("state"));
	document.getElementById("span").value="";
	document.getElementById("conversion").value="";
	document.getElementById("saturation").value="";
	document.getElementById("averageSales").value="";
}

function resetSelectElement(selectElement) {
    var options = selectElement.options;

    // Look for a default selected option
    for (var i=0, iLen=options.length; i<iLen; i++) {

        if (options[i].defaultSelected) {
            selectElement.selectedIndex = i;
            return;
        }
    }

    // If no option is the default, select first or none as appropriate
    selectElement.selectedIndex = -1; // or -1 for no option selected
}
function reset(){
	//alert("Reset Called");
	document.getElementById("createPrePlan").disabled = true;
	document.getElementById("prePlanCreatedMsg").style.display = 'none';
	//document.getElementById("createPrePlanForm").reset();
	//document.getElementById("prePlanTable").innerHTML="";
	
	
}



function brandSelection() {
	var brandSelected = document.getElementById("brand");
	var brand = brandSelected.options[brandSelected.selectedIndex].value;
	//alert(brand);
	
	//reset Fields on Brand Selection
	//resetSelectElement(document.getElementById("state"));
	removeStateOptions();
	removeCityOptions();
	document.getElementById("saturation").value="";
	
	//Reload State List on Brand Selection
	myStates=loadStates();
	
	//Load Conversion and Avg Sales of Brand
	loadConvAndSat();	
	
	
}
function setConversionValue(brand){
	var conv=document.getElementById("conversion");
	conv.value=brand;
}


function setAvgSalesValue(state){
	var conv=document.getElementById("averageSales");
	conv.value=state;
}

function emptyCheck(){
  	/*var selects = document.getElementsByTagName("select");
    var numSelects = selects.length;
    for (var i = 0; i < numSelects-1; i++) {
        var val = selects[i].value;		
		if(val==null || val=="")
		{
			return true;
		}
		
    }*/
	
		var brandSel = document.getElementById("brand");
	    var brand = brandSel.value;
	  
		var stateSel = document.getElementById("state");
	    var state = stateSel.value;
	    var citySel = document.getElementById("city");
	    var city = citySel.value;
	    var span = document.getElementById("span");
	    var spanVal = span.value;
	    
	    if(brand==null || brand=="" || brand=="-1"){
				alert("Please Select Brand");
				return false;
		
	    }else if(state==null || state=="" || state=="-1"){
			alert("Please Select State");
				return false;
		
	    }else if(city==null || city=="" || city=="-1"){
			alert("Please Select City");
				return false;
		
	    } else if(spanVal==null || spanVal==""){
			alert("Please Input Span Activity");
				return false;
		
	    }else{
			return true;
		}
		
	    
	}

function removeStateOptions(){
	var select = document.getElementById("state");
    select.options.length = 0;
}


function removeCityOptions(){
    var select = document.getElementById("city");
    select.options.length = 0;
    //select.size=1;
    //Create new Option
    /*
    var option = document.createElement("option");
    option.text = "-- select --";
    option.value = "-1";
	try {
		select.add(option, null); //Standard
    }catch(error) {
    	select.add(option); // IE only
    }
    */
}

function savePrePlanForm()
{	
	//Check for Empty Fields 
	var field=false;
	field=emptyCheck();
	if(field)
	{
		
		//Check Span of Activity Range
		var spanCheck = false;
		spanCheck=checkSpan();
		if(spanCheck){
		
		//Show Pre Plan List
		//getPrePlanList();
			
		//Save Pre Plan Master
		savePrePlanMaster();
		reset();
		
		//Disable save button id=createPrePlan
		//document.getElementById("createPrePlan").disabled = false;
		}
	}
}

function checkSpan(){
	//alert("checkSpan Called...");
	var spanVal = document.getElementById("span").value;
	if((isNaN(spanVal)) || (spanVal < 1 || spanVal > 365))
	    {
	    alert("Enter Span of Activity Between 01-365 Days.");
	    return false;
	    }else{
	    	return true;
	    }
}
function savePrePlanMaster() {
	
	document.frm1.action = "savePrePlanMaster";
	document.frm1.method = "POST";
	document.frm1.submit();
	
}

/*function savePrePlanMaster() {
	
	var brandVal=$('#brand').val();
	var stateVal=$('#state').val();
	var spanVal=$('#span').val();
	var cityVal=$('#city').val();
	
	var listPreplan=$('#listPreplan');
	
	alert("listPreplan: "+listPreplan);
	
	//var sendURLData="brand="+brandVal+"&span="+spanVal+"&state="+stateVal+"&city="+cityVal;
	//var sendURLData="prePlanItemList="+listPreplan;
	$.ajax({
		contentType : "application/json",
		type : "POST",
		//url : "savePrePlanMaster?" + sendURLData,
		url : "savePrePlanMaster",
		data:$('#listPreplan'),
		success : function(data) {
			
			if(data=='1'){
				//alert("Pre-Plan Saved Succesfully");	
				//var lableId=document.getElementById("prePlanCreatedMsg").value;
				//document.getElementById("prePlanCreatedMsg").style.display = 'none';
				document.getElementById("prePlanCreatedMsg").style.display = 'block';
			}else{
				alert("Some Error Occurred");
			}
		},
		error: function (exception) {
			alert("Some Error Occurred");
		}
	});
}*/

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
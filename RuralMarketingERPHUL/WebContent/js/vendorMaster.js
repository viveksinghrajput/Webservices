    $(document).ready(function() {
    	//for hidding the status
    	setTimeout(function() { $("#status").hide(); }, 2000);
    	//for auto refresh after data updating
    	/*$("#temp").click(function(){
    		
    		location.reload();
    	});*/
    	
    	
        $('#dataTables-example').DataTable({
        	"paging"		: false,
            "ordering" : false,
           
        });
        
        $('.editButton').on(
				'click',
				function() {
					// Get the record's ID via attribute
					var id = $(this).attr('data-id');
					//alert("Edit Button Id:" + id);

					$.ajax({

						contentType : "application/json",
						type : "POST",
						url : "getVendorRowData?id=" + id,
						success : function(response) {
							//alert("Response from DB:" + response);
							// Populate the form fields with the data returned from server
							$('#vendorEditForm').find('[name="edit_id"]').val(response.id).end()
									.find('[name="edit_vendorname"]').val(response.vendorName).end()
								//	.find('[name="edit_agencyname"]').val(response.agencyName).end()
								//	.find('[name="edit_state"]').val(response.state).end()
								//	.find('[name="edit_city"]').val(response.city).end()
							
						},
						
					});
					
					//Show Popup Modal
					// Show the dialog
		            bootbox
		                .dialog({
		                    title: 'Edit Agency Details',
		                    message: $('#vendorEditForm'),
		                    show: false // We will show it manually later
		                })
		                .on('shown.bs.modal', function() {
		                    $('#vendorEditForm')
		                        .show()                             // Show the  form
		                        .formValidation('resetForm'); 		// Reset form
		                })
		                .on('hide.bs.modal', function(e) {
		                    // Bootbox will remove the modal (including the body which contains the login form)
		                    
		                    $('#vendorEditForm').hide().appendTo('body');
		                })
		                .modal('show');

		}); 
        
        $('#temp').click(function(event){
        	
        	event.preventDefault();
        	var $form = $(event.target);
        	var formFields=$(vendorEditForm).serialize();
			var result="";
			id=$('#edit_id').val();
			vendorName=$('#edit_vendorname').val();
			agencyName=$('#edit_agencyname').val();
        	state=$('#edit_state').val();
        	city=$('#edit_city').val();
        	//alert(id+" name "+agencyName+" state "+state+" city "+city)
			 $.ajax({
        		contentType : "application/json",
        		type: "POST",
				url : "updateVendor?id=" + id+"&vendorName="+vendorName  +"&agencyName="+agencyName+"&state="+state+"&city="+city,
				data: formFields,
				success : function(response) {
					
					//alert("Response :"+response);
					
					// Hide the dialog
	                $('.bootbox').modal('hide');
					
					//Check if Value updated Successfully
					if(response){
						
						//Show Success Message
						alert('Vendor Delete Successfully');
						
						window.location.reload();
						
						//Update the Table Cells
						// Get the cells
		                var $button = $('button[data-id="' + id + '"]'),
		                    $tr     = $button.closest('tr'),
		                    $cells  = $tr.find('td');

		                // Update the cell data
		                $cells
		                
		                .eq(2).html(response.conpercent).end();
	
					}else{
						//Show Error Message
						alert('Some Error Occurred, Please try again Later');			
					}
					
				},
				error : function(response){
					
					// Hide the dialog
	                $form.parents('.bootbox').modal('hide');
					
					//Show Error Message
					bootbox.alert('Some Error Occurred');
					//result="Some Error Occurred";
				}			
			}); 
        	//alert(result);
			// this.modal('hide');
        });
       
    });


//saving new vendor here
    $("#savingOnclick").click(function(){
    	
    	var vendor=document.createVendor.vendorname;
		var vend=/^[0-9a-zA-Z]+$/;
    	
    	if(document.getElementById("vendorname").value==''){
    		alert('Please Enter Vendor Name. ');
    		$("#vendorname").focus();
    		return false;
    	}else if(vend.test(vendor.value)==false){
    		alert('Vendor Name Should be Alphanumeric ::');
    		$("#vendorname").focus();
    		return false;
    		
    	}
    	if (document.getElementById("agency").value == '-1') {
    		alert('Please Enter AgencyMaste.');
    		$("#agency").focus();
    		return false;
    	}else if (document.getElementById("state").value == '-1') {
    		alert('Please Select State.');
    		$("#state").focus();
    		return false;			
    	}else if (document.getElementById("city").value == '-1') {
    		alert('Please Select City.');
    		$("#city").focus();
    		return false;
    	}else{
    		document.createVendor.action = "saveNewVendor";
    		document.createVendor.method = "POST";
    		document.createVendor.submit();				
    	}
    	
    	
    	
    });

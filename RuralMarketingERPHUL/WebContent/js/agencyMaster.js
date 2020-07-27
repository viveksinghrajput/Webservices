 $(document).ready(function() {
	 
	 		//this function is used for status hiding 
    		setTimeout(function() { 
    	        $('#status').hide();
    	    }, 2000);
    		 //this function is used for the auto re-load after the event completed
    		
    		/*$('#temp').click(function() {
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
						url : "getAgencyRowData?id=" + id,
						success : function(response) {
							//alert("Response from DB:" + response);
							// Populate the form fields with the data returned from server
							$('#agencyEditForm').find('[name="edit_id"]').val(response.id).end()
									.find('[name="edit_agencyname"]').val(response.agencyname).end()
									.find('[name="edit_state"]').val(response.statename).end()
									.find('[name="edit_city"]').val(response.cityname).end()
						},
						
					});
					
					//Show Popup Modal
					// Show the dialog
		            bootbox
		                .dialog({
		                    title: 'fffffffff',
		                    message: $('#agencyEditForm'),
		                    show: false // We will show it manually later
		                })
		                .on('shown.bs.modal', function() {
		                    $('#agencyEditForm')
		                        .show()                             // Show the  form
		                        .formValidation('resetForm'); 		// Reset form
		                })
		                .on('hide.bs.modal', function(e) {
		                    // Bootbox will remove the modal (including the body which contains the login form)
		                    
		                    $('#agencyEditForm').hide().appendTo('body');
		                })
		                .modal('show');

		}); 
        
        $('#temp').click(function(event){
        	
        	event.preventDefault();	
        	var formFields=$(agencyEditForm).serialize();
        	
			var result="";
			id=$('#edit_id').val();
        	agencyName=$('#edit_agencyname').val();
        	state=$('#edit_state').val();
        	city=$('#edit_city').val();
        	//alert(id+" name "+agencyName+" state "+state+" city "+city)
			 $.ajax({
        		contentType : "application/json",
        		type: "POST",
				url : "updateagency?id=" + id+"&agencyName="+agencyName+"&state="+state+"&city="+city,
				data: formFields,
				
			 success : function(response) {
					
                
					// Hide the dialog
	                //$form.parents('.bootbox').modal('hide');
					//if multiple form are available then use this code
					$('.bootbox').modal('hide');					
					//Check if Value updated Successfully
					if(response){
						
						//Show Success Message
						alert('Update Agency Successfully');
						
						//window.location.reload();
						
					//	alert('New Agency Value Updated Successfully');
						
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
						bootbox.alert('Some Error Occurred, Please try again Later');			
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
        });
    });

// Saving new agency here	
 
			$("#savingOnclick").click(function(){
				
				var agency=document.createAgency.agencyname;
				var agen=/^[0-9a-zA-Z]+$/;
				if (document.getElementById("agencyname").value == '') {
					alert('Please Enter Agency');
					$("#agencyname").focus();
					return false;
				}else if(agen.test(agency.value) == false){
					
					alert("Agency Name Should be Alphanumeric ::");
					$("#agencyname").focus();
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
					/*$.ajax({
						url: "saveNewAgency",
						type: "POST",
						data: $("#createAgency").serialize(),
						success: function(data) {
							debugger;
							//$("#bala").html("Success");
						},
						error: function(err) {
							debugger;
						}
					});*/
					document.createAgency.action = "saveNewAgency";
					document.createAgency.method = "POST";
					document.createAgency.submit();
				}
			
			});
			
				
				
			
 


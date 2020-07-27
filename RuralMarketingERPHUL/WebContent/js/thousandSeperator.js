
$(document).ready(function() {
	
	
	var change_to_thousand_seprator = $( ".change_to_thousand_seprator" );
	var elem = $();
	for( var i = 0; i < change_to_thousand_seprator.length; i++ ){
		var elem = $( change_to_thousand_seprator[i] );
		if( elem.is( "input" ) ){
			elem.val( parseFloat( parseFloat( elem.val().trim() ).toFixed(2) ).toLocaleString('en-IN' , { minimumFractionDigits: 2 }) );
		} else if ( elem.is( "input" ) ){
			elem.html( parseFloat( parseFloat( elem.text().trim() ).toFixed(2) ).toLocaleString('en-IN' , { minimumFractionDigits: 2 }) );
		}
		else {
			elem( parseFloat( parseFloat( elem.text().trim() ).toFixed(2) ).toLocaleString('en-IN' , { minimumFractionDigits: 2 }) );
		}
	}
	

	
});
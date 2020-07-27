
function approveinvoices(){
	
	var commentsVal=document.getElementById("comment").value;
	
	if(commentsVal.length<1)
	   {
	        window.alert ("Please Enter Comments.");
	        document.pageForm.comment.focus();
	        return false;
	   }

	else{
	jQuery.ajax({
		type : "POST",
        url: "approveinvoice?invoice_id=" + $("#invoice_id").val() + "&comment=" + $("#comment").val() + "&status=" + $("#status").val()+ "&bill_number=" + $("#bill_number").val(),
        success: function (data) {
        	 document.getElementById("statusMsg").innerHTML=data;
            document.getElementById("statusMsg").style.display = "block";
            document.getElementById("controls").style.display = "none";
            $("#comment").prop("readonly", true);
        },
        error: function (data) {
            alert(data + "error");
        }});
	}
	
}

function rejectinvoices(){
	
var commentsVal=document.getElementById("comment").value;
	if(commentsVal.length<1)
	   {
	        window.alert ("Please Enter Comments.");
	        document.pageForm.comment.focus();
	        return false;
	   }

	else{
	jQuery.ajax({
		type : "POST",
        url: "rejectinvoice?invoice_id=" + $("#invoice_id").val() + "&comment=" + $("#comment").val() + "&status=" + $("#status").val()+ "&bill_number=" + $("#bill_number").val(),
        success: function (data) {
        	document.getElementById("statusMsg").innerHTML=data;
            document.getElementById("statusMsg").style.display = "block";
            document.getElementById("controls").style.display = "none";
            $("#comment").prop("readonly", true);
        },
        error: function (data) {
            alert(data + "error");
        }});
	}

}

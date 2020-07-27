var i = 1;	
function deleteRow(row) {
  var curr_row = $( row ).closest( "tr" ),
      total_trs = curr_row.closest( "tbody" ).find( "tr" ).length;
  if( total_trs != 1 ){
	  curr_row.remove();
  }
  recalculatetottal( $( "#more_fields input:first" ) );
}


function insRow() {
	var select_box = $($( ".itemdesc" )[0]).html();
 $("#more_fields tbody").append('<tr id="rowtoAdd" style="text-align:center;padding-top:10px; border-top: 1px solid #DCDCDC "><td width="20"><img src="dist/img/plus.png" height="20" width="20" onclick="insRow()"></td><td width="20"><img src="dist/img/delete.png" height="20" width="20" onclick="deleteRow(this)"></td><td width="30"><select id="itemdesc" path="invoices[0].itemId" class="itemdesc form-control" name="invoices['+(i)+'].itemId">'+select_box+'</select></td><td><input size="10" type="text" id="case" class="case" maxlength="4" onkeypress="return isNumber(event)" path="invoices[0].cases" name="invoices['+(i)+'].cases"></input></td><td><input size="10" type="text" id="unit" onkeypress="return isNumber(event)" class="unit" maxlength="3" path="invoices[0].units" name="invoices['+(i)+'].units"></input></td><td><input size="10" type="text" style="background-color:#eee"  id= "total_units" class="total_units" path="invoices[0].totalunits" name="invoices['+(i)+'].totalunits" readonly="true"></input></td><td><input size="10" type="text" id="Priceperunits" style="background-color:#eee"  class="Priceperunits" onkeypress="return validateFloatKeyPress(this,event);" name="invoices['+(i)+'].Priceperunits" readonly="true"></td> <td><input size="10" type="text" style="background-color:#eee"  id="net_amount" class="net_amount" onkeypress="return validateFloatKeyPress(this,event);" data-behaviour="thousandsep" name="invoices['+(i)+'].net_amount" readonly="true"></td></tr>');


 
 ++i;
}
 
 
 
 
















 
/* $("#more_fields tbody").append('<tr id="rowtoAdd" style="text-align:center;padding-top:10px; border-top: 1px solid #DCDCDC "><td><img src="dist/img/plus.png" height="20" width="20" onclick="insRow()"></td><td><img src="dist/img/delete.png" height="20" width="20" onclick="deleteRow(this)"></td><td col="" width="70"><select id="itemdesc" class="form-control" name="itemdesc">'+select_box+'</select></td><td><input size="5" type="text" id="case" path="invoices[0].item_description" name="invoices[1].item_case"></input></td><td><input size="5" type="text" id="unit" path="invoices[0].item_description" name="invoices[1].item_units"></input></td><td><input size="5" type="text" id="totalunits" path="invoices[0].item_description" name="invoices[1].totalunits"></input></td> <td><input size="5" type="text" id="netamount" onkeypress="return validateFloatKeyPress(this,event);" name=""></td></tr>')*/
	
 
 
 
 /*$("#more_fields tbody").append('<tr id="rowtoAdd" style="text-align:center;padding-top:10px; border-top: 1px solid #DCDCDC "><td ><img src="dist/img/plus.png" height="20" width="20" onclick="insRow()"></td></td><td ><img src="dist/img/delete.png" height="20" width="20" onclick="deleteRow(this)"></td><td ><select id="itemdesc" class="form-control" name="itemdesc">'+select_box+'</select></td><td ><form:input size="15"  type="text" id="case" path="invoices[0].item_description" name="invoices['+(i)+'].item_case"/></td><td ><form:input size="15"  type="text" id="unit" path="invoices[0].item_description" name="invoices['+(i)+'].item_units"/></td><td ><form:input size="15"  type="text" id="totalunits" path="invoices[0].item_description" name="invoices['+(i)+'].totalunits"/></td><td ><form:input size="15"  type="text" id="mrp" path="invoices[0].item_description" name="invoices['+(i)+'].mrp"/></td><td ><input size="5"  type="text" id="netamount" onkeypress="return validateFloatKeyPress(this,event);" name="invoices['+(i)+'].net_amount"/></td></tr>')*/
	
//$("#more_fields tbody").append('<tr id="rowtoAdd" style="text-align:center;padding-top:10px; border-top: 1px solid #DCDCDC "><td width="50"><img src="dist/img/plus.png" height="20" width="20" onclick="insRow()"></td><td width="50"><img src="dist/img/delete.png" height="20" width="20" onclick="deleteRow(this)"></td><td width="70"><form:select id="itemdesc" class="itemdesc form-control" name="itemdesc" path="invoices[0].item_description">'+select_box+'</form:select></td><td><form:input size="5" type="text" id="cases" class="case" path="invoices[0].cases" name="invoices['+(i)+'].case"/></td><td><from:input size="5" type="text" id="units" class="unit" path="invoices[0].units" name="invoices['+(i)+'].units"/></td><td><from:input size="5" type="text" id= "total_units" class="total_units" path="invoices[0].totalunits" name="invoices['+(i)+'].totalunits"/></td> <td><from:input size="5" type="text" id="net_amount" class="net_amount" path="invoices[0].net_amount" onkeypress="return validateFloatKeyPress(this,event);" name="invoices['+(i)+'].net_amount"/></td></tr>')
 

 
 //<tr id="rowtoAdd" style="text-align:center;padding-top:10px; border-top: 1px solid #DCDCDC "><td ><img src="dist/img/plus.png" height="20" width="20" onclick="insRow()"></td></td><td ><img src="dist/img/delete.png" height="20" width="20" onclick="deleteRow(this)"></td><td col width="70" ><select id="itemdesc" class="form-control"  name="itemdesc"><option  value="-1">-- Select --</option><c:forEach var="itemdesc" items="${itemDesc}"><c:choose><c:when test="${not empty selected_itemdesc && selected_itemdesc eq itemdesc.value}"><option value="${selected_itemdesc}" selected>${selected_itemdesc}</option></c:when><c:otherwise><option value="${itemdesc.itemDescription}">${itemdesc.itemDescription}</option></c:otherwise></c:choose></c:forEach></select></td><td ><form:input size="15"  type="text" id="case" path="invoices[0].item_description" name="invoices['+(i)+'].item_case"/></td><td ><form:input size="15"  type="text" id="unit" path="invoices[0].item_description" name="invoices['+(i)+'].item_units"/></td><td ><form:input size="15"  type="text" id="totalunits" path="invoices[0].item_description" name="invoices['+(i)+'].totalunits"/></td> <td ><input size="5"  type="text" id="netamount" onkeypress="return validateFloatKeyPress(this,event);" name="invoices['+(i)+'].net_amount"/></td></tr>
 














/*<td ><form:input size="15"  type="text" id="mrp" path="invoices[0].item_description" name="invoices['+(i)+'].mrp"/></td>*/



/*<th class="text-center" colspan ="3" rowspan = "2">Item Description</th>*/
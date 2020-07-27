/*
*DeliveryAndInventoryService.java
*Created By		:Bhagya Lakshmi Mula
*Created Date	:Jul 4, 2018
*/
package com.rural.Service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.Part;

import com.rural.Model.CollateralMaster;
import com.rural.Model.DeliveryAndInventory;
import com.rural.Model.DeliveryAndInventoryItems;
import com.rural.Model.DeliveryAndInventoryTemp;
import com.rural.Model.SLAMaster;

public interface DeliveryAndInventoryService {

	public Map<Integer,String> getBrands();

	public List<String> getStateList();
	
	public List<String> getCityList(String strState);
	
	//public List<String> getVendors();

	public List<String> getWarehouseList(String strCity);

	public List<DeliveryAndInventory> createDeliveryAndInventory(
			DeliveryAndInventory deliveryAndInventory,int noOfKits,String strRemarks);

	public List<DeliveryAndInventory> getAllDeliveryAndInventaries(String userRole, String username);

	public DeliveryAndInventory getDeliveryInventoryDetails(String strReq_Num);

	public List<DeliveryAndInventoryItems> updateStatus(String username,String userRole, String strStatus,String strReqNo,String StrProduser,String reachedLogi,String tempStatus);

	public List<DeliveryAndInventoryItems> updateInventories(
			DeliveryAndInventoryItems deliveryAndInventory,String userRole,String tempStatus,String destPath,DeliveryAndInventoryTemp[] temp);

	public void CopyUploadedFiles(String strReqNo, Part partDispatch1, Part partDispatch2, String string);

	public java.sql.Date getEstimatedDeliveryDate(String reqNo);

	/*public String getCollaterals(String username, String strReq_Num);*/

	public List<DeliveryAndInventoryItems> getDeliveryInventoryItems(String username,String userRole,String strReq_Num);
/*
	public List<DeliveryAndInventoryProductionUser> getUsersForLogistics(
			String strReq_Num);*/

	//public List<CollateralMaster> getAllCollaterals();

}

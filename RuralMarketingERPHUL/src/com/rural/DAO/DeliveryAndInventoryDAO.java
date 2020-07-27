/*
*DeliveryAndInventoryDAO.java
*Created By		:Bhagya Lakshmi Mula
*Created Date	:Jul 4, 2018
*/
package com.rural.DAO;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.rural.Model.BrandMaster;
import com.rural.Model.CollateralMaster;
import com.rural.Model.DeliveryAndInventory;
import com.rural.Model.DeliveryAndInventoryItems;
import com.rural.Model.DeliveryAndInventoryTemp;

@Repository
@Component
public interface DeliveryAndInventoryDAO {
	
	public List<BrandMaster> getBrands();
	
	public List<String> getStateList();
	
	public List<String> getCityList(String strState);

	public List<String> getWarehouseList(String strCity);

	public List<DeliveryAndInventory> createDeliveryAndInventory(
			DeliveryAndInventory deliveryAndInventory,int noOfKits,String strRemarks);

	public List<DeliveryAndInventory> getAllDeliveryAndInventaries(String userRole, String userName);

	public DeliveryAndInventory getDeliveryInventoryDetails(
			String strReq_Num);

	public List<DeliveryAndInventoryItems> updateStatus(String username,String userRole,String strReqNo, String strStatus,String StrProduser,String reachedLogi,String tempStatus);

	public List<DeliveryAndInventoryItems> updateInventories(
			DeliveryAndInventoryItems deliveryAndInventory,String userRole,String tempStatus,String destPath, DeliveryAndInventoryTemp[] obj);

	public java.sql.Date getEstimatedDeliveryDate(String reqNo);

	/*public String getCollaterals(String username, String strReq_Num);*/

	public List<DeliveryAndInventoryItems> getDeliveryInventoryItems(String username,String userRole,String strReq_Num);
/*
	public List<DeliveryAndInventoryProductionUser> getUsersForLogistics(
			String strReq_Num);
*/
	//public List<CollateralMaster> getAllCollaterals();

}

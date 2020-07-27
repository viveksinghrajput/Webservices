/*
*WarehouseService.java
*Created By		:Kamal Thapa
*Created Date	:Mar 7, 2018
*/
package com.rural.Service;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.rural.Model.Photo;
import com.rural.Model.UserMaster;
import com.rural.Model.VendorWareHouseMaster;
import com.rural.Model.WareHouseHistory;
import com.rural.webservice.DTO.WarehouseResponse;

@Component
@Service
public interface WarehouseService {
	
	//List all Warehouses
	public List<VendorWareHouseMaster> listAll();
	
	//List All WareHouse of Particular Vendor
	public List<VendorWareHouseMaster> listMyWareHouse(String username);
	
	//List Pendig WareHouse of Particular Vendor
	public List<VendorWareHouseMaster> listMyPendingWareHouse(String username);
	
	//List Rejected WareHouse of Particular Vendor
	public List<VendorWareHouseMaster> listMyRejectedWareHouse(String username);
	
	//List Approved WareHouse of Particular Vendor
	public List<VendorWareHouseMaster> listMyApprovedWareHouse(String username);
	
	//Save Updated WareHouse
	public int updateWareHouseHistory( String wareHouseId, String username);
	
	//Get warehouse per id
	public VendorWareHouseMaster getWarehouse(String id);
	
	//Get Pending Approval warehouses 
	public List<VendorWareHouseMaster> getPendingWarehouse();
	
	//Get Rejected warehouses 
	public List<VendorWareHouseMaster> getRejectedWarehouse();
	
	//Get Rejected warehouses 
	public List<VendorWareHouseMaster> getAprovedWarehouse();
	
	//Get Rejected warehouses 
	public List<VendorWareHouseMaster> getReSubmittedWarehouse();
	
	//Mass Approve passed WareHouse Id / Ids
	public String massApprove(String strSelected);

	//Mass Reject passed WareHouse Id / Ids
	public String massReject(String strSelected);
	
	//Approve WareHouse with Comments
	public String approveWareHouse(String strId,String strComments);

	//Reject WareHouse with Comments
	public String rejectWareHouse(String strId,String strComments);

	//Update Vendor with new Details
	public String updateVendor(VendorWareHouseMaster obj);

	//Get warehouse History per wareHouseName
	public List<WareHouseHistory> getWareHouseHistory(String strWareHouseName, String sorting);

	public List<Photo> getPhotoDetails(String strId);

	public UserMaster getNameById(int parseInt);

	
	
}

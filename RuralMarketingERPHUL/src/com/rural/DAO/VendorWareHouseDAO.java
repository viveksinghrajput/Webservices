/*
*VendorWareHouseDAO.java
*Created By		:Kamal Thapa
*Created Date	:Mar 9, 2018
*/
package com.rural.DAO;

import java.util.List;

import com.rural.Model.Photo;
import com.rural.Model.UserMaster;
import com.rural.Model.VendorWareHouseMaster;
import com.rural.Model.WareHouseHistory;

public interface VendorWareHouseDAO {

	public List<VendorWareHouseMaster> allWareHouse();
	public List<VendorWareHouseMaster> myWareHouse(String username);
	public int updateWarehouse(WareHouseHistory item);
	public VendorWareHouseMaster warehouse(String id);
	public List<VendorWareHouseMaster> viewWareHouses(String status);
	public int approveWareHouse(String id,String strComments);
	public int rejectWareHouse(String id,String strComments);
	public String editWareHouse(VendorWareHouseMaster obj);
	public int persistWareHouseHistory(WareHouseHistory obj);
	public List<WareHouseHistory> getHistory(String wareHouseName, String sorting);
	public List<WareHouseHistory> getVendorHistory(String vendorName);
	public List<Photo> getPhotoDetails(String strId);
	public UserMaster getNameById(int userId);
}

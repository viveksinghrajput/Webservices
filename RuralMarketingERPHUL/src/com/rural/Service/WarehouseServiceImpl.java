/*
*WarehouseServiceImpl.java
*Created By		:Kamal Thapa
*Created Date	:Mar 9, 2018
*/
package com.rural.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.rural.DAO.AdminDAO;
import com.rural.DAO.VendorWareHouseDAO;
import com.rural.Model.Photo;
import com.rural.Model.StateMaster;
import com.rural.Model.UserMaster;
import com.rural.Model.VendorWareHouseMaster;
import com.rural.Model.WareHouseHistory;

@Component
@Service
public class WarehouseServiceImpl implements WarehouseService{

	@Autowired
	VendorWareHouseDAO vendorWareHouseDAO;
	@Override
	public List<VendorWareHouseMaster>listAll() {
		return vendorWareHouseDAO.allWareHouse();
	}

	@Override
	public List<VendorWareHouseMaster> listMyWareHouse(String username) {
		return vendorWareHouseDAO.myWareHouse(username);
	}


	@Override
	public int updateWareHouseHistory(String wareHouseId, String username) {

		int strHistoryUpdate=0;
		//Get All warehouse details from vendorwarehouse DB
		String[] strId=wareHouseId.split(",");
		for(String id:strId)
		{
		VendorWareHouseMaster wareHouse=vendorWareHouseDAO.warehouse(id);
		//Update the same to WareHouse History.
		WareHouseHistory history=new WareHouseHistory();
		history.setTrackId(wareHouse.getTrackId());
		history.setVendorId(wareHouse.getVendorId());
		history.setVendorContact(wareHouse.getVendorContact());
		history.setVendorEmail(wareHouse.getVendorEmail());
		//Get Update Count and Increment it --TO Do
		history.setWarehouseName(wareHouse.getName());
		history.setWarehouseStatus(wareHouse.getStatus());
		/*history.setWareHouseLatitute(wareHouse.getWareHouseLatitute());
		history.setWareHouseLongitute(wareHouse.getWareHouseLongitute());*/
		history.setWareHouseAddress01(wareHouse.getAddr1());
		history.setWareHouseAddress02(wareHouse.getAddr2());
		history.setStateId(wareHouse.getStateId());
		history.setCityId(wareHouse.getCityId());
		history.setPinCode(wareHouse.getPin());
		//history.setWareHouseOuterImage(wareHouse.getWareHouseOuterImage());
		history.setWareHouseInnerImage01(wareHouse.getAddr1());
		history.setWareHouseInnerImage02(wareHouse.getAddr2());
		history.setWareHouseAreainSqft(wareHouse.getArea());
		history.setApprovedBy(username);
		history.setComment(wareHouse.getComment());
		//Send this to DAO to save in DB
		if(history!=null){
			strHistoryUpdate=vendorWareHouseDAO.persistWareHouseHistory(history);
		}
		}
		return strHistoryUpdate;
	}


	@Override
	public VendorWareHouseMaster getWarehouse(String id) {
		
		return vendorWareHouseDAO.warehouse(id);
	}


	@Override
	public List<VendorWareHouseMaster> getPendingWarehouse() {
		
		return vendorWareHouseDAO.viewWareHouses("Submitted");
	}
	
	@Override
	public List<VendorWareHouseMaster> getRejectedWarehouse() {
		
		return vendorWareHouseDAO.viewWareHouses("Rejected");
	}
	
	@Override
	public List<VendorWareHouseMaster> getAprovedWarehouse() {
		
		return vendorWareHouseDAO.viewWareHouses("Approved");
	}
	
	@Override
	public List<VendorWareHouseMaster> getReSubmittedWarehouse() {
		
		return vendorWareHouseDAO.viewWareHouses("ReSubmitted");
	}


	@SuppressWarnings("null")
	@Override
	public List<VendorWareHouseMaster> listMyPendingWareHouse(String username) {
		List<VendorWareHouseMaster> output=new ArrayList<VendorWareHouseMaster>();
		List<VendorWareHouseMaster> myWareHouses=vendorWareHouseDAO.myWareHouse(username);
		if(myWareHouses!=null){
			for(VendorWareHouseMaster item: myWareHouses){
						
				String itemStatus=item.getStatus();
				if((itemStatus!=null) && (itemStatus.equalsIgnoreCase("Submitted"))){
					output.add(item);
				}
			}
		}
		return output;
	}


	@SuppressWarnings({ "null", "unchecked" })
	@Override
	public List<VendorWareHouseMaster> listMyRejectedWareHouse(String username) {
		List<VendorWareHouseMaster> output=new ArrayList<VendorWareHouseMaster>();
		List<VendorWareHouseMaster> myWareHouses=vendorWareHouseDAO.myWareHouse(username);
		if(myWareHouses!=null){
			for(VendorWareHouseMaster item: myWareHouses){
				if(item.getStatus().equalsIgnoreCase("Rejected"))
					output.add(item);
			}
		}
		return output;
	}


	@SuppressWarnings("null")
	@Override
	public List<VendorWareHouseMaster> listMyApprovedWareHouse(String username) {
		List<VendorWareHouseMaster> output=new ArrayList<VendorWareHouseMaster>();
		List<VendorWareHouseMaster> myWareHouses=vendorWareHouseDAO.myWareHouse(username);
		if(myWareHouses!=null){
			for(VendorWareHouseMaster item: myWareHouses){
				if(item.getStatus().equalsIgnoreCase("Approved"))
					output.add(item);
			}
		}
		return output;
	}


	@Override
	public String massApprove(String strSelected) {
		String status="Failed";
		//Convert String to Array
		String[] strId=strSelected.split(",");
		for(String id:strId)
		{
			//Update Status as Approved
			int result=vendorWareHouseDAO.approveWareHouse(id, "Mass Approve");
			if(result>=1)
			{
				status="Success";
			}
		}
		return status;
	}


	@Override
	public String massReject(String strSelected) {
		String status="Failed";
		//Convert String to Array
		String[] strId=strSelected.split(",");
		for(String id:strId)
		{
			//Update Status as Approved
			int result=vendorWareHouseDAO.rejectWareHouse(id,"Mass Reject");
			if(result>=1)
			{
				status="Success";
			}
		}
		return status;
	}


	@Override
	public String approveWareHouse(String strId, String strComments) {
		String status="Failed";
			//Update Status as Approved
			int result=vendorWareHouseDAO.approveWareHouse(strId,strComments);
			if(result>=1)
			{
				status="Success";
			}
		return status;
	}


	@Override
	public String rejectWareHouse(String strId, String strComments) {
		String status="Failed";
		//Update Status as Approved
		int result=vendorWareHouseDAO.rejectWareHouse(strId,strComments);
		if(result>=1)
		{
			status="Success";
		}
		return status;
	}


	@Override
	public String updateVendor(VendorWareHouseMaster obj) {
		return vendorWareHouseDAO.editWareHouse(obj);
	}


	@Override
	public List<WareHouseHistory> getWareHouseHistory(String strWareHouseName,String sorting) {
		return vendorWareHouseDAO.getHistory(strWareHouseName,sorting);
	}


	@Override
	public List<Photo> getPhotoDetails(String strId) {
		return vendorWareHouseDAO.getPhotoDetails(strId);
	}


	@Override
	public UserMaster getNameById(int parseInt) {
		return vendorWareHouseDAO.getNameById(parseInt);
	}

}

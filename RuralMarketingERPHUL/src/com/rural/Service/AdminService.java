/*
*AdminService.java
*Created By		:Kamal Thapa
*Created Date	:Apr 4, 2018
*/
package com.rural.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Component;

import com.rural.Model.AgencyMaster;
import com.rural.Model.BatchTemp;
import com.rural.Model.CityMaster;
import com.rural.Model.DiviceMaster;
import com.rural.Model.LoginResponse;
import com.rural.Model.ConversionMaster;
import com.rural.Model.SaturationMaster;
import com.rural.Model.StateMaster;
/*import com.rural.Model.UserHistory;
*/import com.rural.Model.UserMaster;
import com.rural.Model.VendorMaster;

@Component
public interface AdminService {

	public List<ConversionMaster> getAllConversion();
	public List<SaturationMaster> getAllSaturation();
	
	public ConversionMaster getConvById(int strId);
	public SaturationMaster getSatById(int strId);
	
	public int updateConv(int strId, String strConv, String strAvgSales,String ipAddress);
	public int updateSat(int strId, String strSat,String ipAddress);
	public List<CityMaster> getCityofState(Integer intStateId);
	public String persistAgency(AgencyMaster newAgency) ;
	public String persistVendor(VendorMaster newVendor);
	public List<AgencyMaster> getAgencyNames();
	public int updateVendor(VendorMaster vendor);
	public VendorMaster getVendorById(int strId);
	public AgencyMaster getAgencyById(int strId);
	public AgencyMaster checkAgencyName(String newAgencyName);
	public Set<String> getVendorNames();
	public List<AgencyMaster> getAgencyMaster();
	

	public List<VendorMaster> getVendorMaster();
	
	public List<UserMaster> getUserMaster();
	public Set<String> getRoleNames();
	
	public UserMaster getUserById(int strId);
	//public int updateUser(UserMaster user);
/*	public List<UserHistory> getUserHistory(int strId);
*/	public int updateagency(int strId, String state, String agencyname, String city);
	public int UpdateVendor(int strId, String vendorname, String agencyname, String state, String city);
	
	public int updateUser(int strId, String strpassword, String strEmailId, String strContact);
	public List<String> getStatusList();
	public  Map<Integer,String> getRoleId();
	
	public boolean persistUserRole(UserMaster newUser);
	public int LockVendor(int strId);
	public int unlockVendor(int strId);
	public int LockAgency(int strId);
	public int unlockAgency(int strId);
/*	public List<String> getVendorofAgency(String strAgency);
*/
	public int LockUser(int strId);
	public int unlockUser(int strId);
	public UserMaster getUserByroleId(int strId);
	public Object getVendorNameActives();
		public UserMaster checkUser(String username);
		//For Status
		public int getStatusId(String strStatus, String userRole,String strModule);
		public VendorMaster checkVendorName(String vendorName);
		public List<AgencyMaster> getAgenciesList();
		public List<StateMaster> getStateList();
		public List<VendorMaster> getVendorList(int agencyId);
		public int updateUser(UserMaster user);
		public Map<Integer, String> getBrandList();
		
		
		
	
}

/*
*AdminDAO.java
*Created By		:Kamal Thapa
*Created Date	:Apr 4, 2018
*/
package com.rural.DAO;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.rural.Model.AgencyMaster;
import com.rural.Model.BatchTemp;
import com.rural.Model.CityMaster;
import com.rural.Model.ConversionMaster;
import com.rural.Model.DiviceMaster;
import com.rural.Model.LoginResponse;
import com.rural.Model.RoleMaster;
import com.rural.Model.Roles;
import com.rural.Model.SaturationMaster;
import com.rural.Model.StateMaster;
/*import com.rural.Model.UserHistory;
*/import com.rural.Model.UserMaster;
import com.rural.Model.VendorMaster;


@Component
public interface AdminDAO {

	public List<ConversionMaster> getConvMaster();
	public List<SaturationMaster> getSatMaster();
	public ConversionMaster getConv(int strId);
	public SaturationMaster getSat(int strId);
	public int updateConvValue(int strId, String strConv, String avgSales,String ipAddress);
	public int updateSatValue(int strId, String strSat,String ipAddress);
	public List<CityMaster> listCityOfStates(Integer intStateId);
	public List<AgencyMaster> listAgencyMaster();
	public AgencyMaster getAgency(int strId);
	public AgencyMaster checkAgencyName(String newAgencyName);
	public int saveNewAgency(AgencyMaster newAgency);
	
	
	public List<UserMaster> listUserMaster();
	//public List<UserMaster1> listUserMaster1();
	public List<RoleMaster> listRoles();
	
/*	public void saveHistory(UserHistory history);
*/	public UserMaster getUser(int strId);
	public int updateUser(UserMaster user);
	

/*	public List<UserHistory> getHistory(String username);
*/	public Map<String, String> stateMap();
	public int updateAgencyValue(int strId, String agencyname, String city, String state);
	
	public List<VendorMaster> listVendorMaster();
	//public String checkVendorName(String newVendorName);
	public int saveNewVendor(VendorMaster newVendor);
	public VendorMaster getVendor(int strId);
	public String getVendorAgency(String userVendor);
	public int updateVendorValue(int strId, String vendorname, String agencyname, String state, String city);
	
	public int updateUserValue(int strId, String strpassword, String strEmailId, String strContact);
	
	public List<String> liststatus();
	
	public int saveNewUser(UserMaster newUser);
	public List<Roles> listRoleId();
	
	public UserMaster checkUniqueUserName(String newUserName);
	
	public String checkUniqueRoleId(int newRoleId);
	public int LockVendorValue(int strId);
	public int UnLockVendorValue(int strId);
	public int LockAgencyValue(int strId);
	public int UnLockAgencyValue(int strId);
	public List<String> listVendorofAgency(String strAgency);
	public int LockUserValue(int strId);
	public int UnLockUserValue(int strId);
	public UserMaster getUserroleId(int strId);
	public List<VendorMaster> getVendorNameActives();
	public UserMaster UserName(String username);
	public int getStatusId(String strStatus, String userRole, String strModule);
	public VendorMaster checkVendorName(String vendorName);
	public List<AgencyMaster> getAgenciesList();
	public List<StateMaster> getStateList();
	public List<VendorMaster> getVendorsList(int intAgencyId);
	public Map<Integer, String> brandMap();

	
	
	
	
	
}

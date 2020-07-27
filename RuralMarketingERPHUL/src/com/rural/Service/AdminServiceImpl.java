/*
*AdminServiceImpl.java
*Created By		:Kamal Thapa
*Created Date	:Apr 4, 2018
*/
package com.rural.Service;


import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.rural.DAO.AdminDAO;
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
@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	AdminDAO adminDAO;

	@Override
	public List<ConversionMaster> getAllConversion() {
		
		return adminDAO.getConvMaster();
	}
	@Override
	public List<SaturationMaster> getAllSaturation() {	
		return adminDAO.getSatMaster();
	}
	@Override
	public ConversionMaster getConvById(int strId) {
		
		return adminDAO.getConv(strId);
	}

	@Override
	public SaturationMaster getSatById(int strId) {
		
		return adminDAO.getSat(strId);
	}

	@Override
	public int updateConv(int strId, String strConv, String strAvgSales,String ipAddress) {
		
		return adminDAO.updateConvValue(strId, strConv, strAvgSales,ipAddress);
	}

	@Override
	public int updateSat(int strId, String strSat,String ipAddress) {
		
		return adminDAO.updateSatValue(strId,strSat,ipAddress);
	}

	
	public List<CityMaster> getCityofState(Integer intStateId) {
		
		return adminDAO.listCityOfStates(intStateId);
	}
	
	@Override
	public List<VendorMaster> getVendorMaster() {
		
		return adminDAO.listVendorMaster();
	}
	
	@Override
	public List<AgencyMaster> getAgencyMaster() {
		// TODO Auto-generated method stub
		return adminDAO.listAgencyMaster();
	
	}


	@Override
	public VendorMaster getVendorById(int strId) {
		
		return adminDAO.getVendor(strId);
	}


	@Override
	public Set<String> getRoleNames() {
		Set<String> output=new LinkedHashSet<String>(); 
		List<RoleMaster> rolelist=adminDAO.listRoles();
		for(RoleMaster roles:rolelist){
			String name=roles.getRoleName();
			output.add(name);
		}
		
		return output;
	}
	@Override
	public Map<Integer,String> getRoleId() {
		// TODO Auto-generated method stub
		List<Roles> rolelist=adminDAO.listRoleId();
		Map<Integer,String> roleMap=new LinkedHashMap<Integer,String>();
		for (Roles roleMaster : rolelist) {
			roleMap.put(roleMaster.getRoleId(), roleMaster.getRoleName());
		}
		return roleMap;
	}

	@Override
	public boolean persistUserRole(UserMaster newUser) {
		
		int newRoleId = newUser.getRoleId();
		String present = null;
		boolean isInserted=false;
		if (newRoleId == 1){
		present = adminDAO.checkUniqueRoleId(newRoleId);
		if(present=="Does Not Exists") {
				int result = adminDAO.saveNewUser(newUser);
				if (result == 1) {
					isInserted=true;
	
				} else {
					isInserted=false;
				}
		
			
		}
		else{
			isInserted=false;
		}
	}
		else
		{
			int result = adminDAO.saveNewUser(newUser);
			if (result == 1) {
				isInserted=true;

			}
			else
			{
				isInserted=false;
			}
		}
		return isInserted;
}


	@Override
	public List<UserMaster> getUserMaster() {
		
		return adminDAO.listUserMaster();
	}
	
	@Override
	public UserMaster getUserById(int strId) {
		
		return adminDAO.getUser(strId);
	}
	@Override
	public int updateUser(UserMaster user) {
		
		return adminDAO.updateUser(user);
	}
	@Override
	public Set<String> getVendorNames() {
		Set<String> output=new LinkedHashSet<String>();
		List<VendorMaster> vendorList=adminDAO.listVendorMaster();
		for(VendorMaster vendor:vendorList){
			String name=vendor.getVendorName();
			output.add(name);
		}
		
		return output;
	}
	
	

	
	

	

	/*@Override
	public List<UserHistory> getUserHistory(int strId) {
		List<UserHistory> history=null;
		String username=null;
		//Get Username from id
		UserMaster user=adminDAO.getUser(strId);
		username=user.getUsername();
		if(username!=null){
			history=adminDAO.getHistory(username);
		}
		return history;
	}*/


	@Override
	public String persistAgency(AgencyMaster newAgency) {
		
		//Check if agency already in DB
		String newAgencyName=newAgency.getAgencyname();
		AgencyMaster present=null;
		present=adminDAO.checkAgencyName(newAgencyName);
		if(present==null){
			//AgencyName is not in DB
			//Create the new Agency in DB
				int result=adminDAO.saveNewAgency(newAgency);
				if(result==1){
					return "New Agency Created successfully";
				}else if (result==0) {
					return "Agency : "+ newAgencyName +" already present";
				}
				else{
							return "Problem in Agency Creation";
				}
		
		}else {
			return "Agency : "+ newAgencyName +" already present";
		}
	}
	@Override
	public String persistVendor(VendorMaster newVendor) {
		// TODO Auto-generated method stub
		String newVendorName=newVendor.getVendorName();
		VendorMaster present=null;
		present=adminDAO.checkVendorName(newVendorName);
		if(present==null) {
			
			int result=adminDAO.saveNewVendor(newVendor);
			if(result==1){
				return "New Vendor Created successfully";
			}else if (result==0) {
				return "Vendor : "+ newVendorName +" already present";
			}
			else{
						return "Problem in Agency Creation";
			}
		}else {
			return "Vendor : "+ newVendorName +" already present";
		}
			

		
	}

	@Override
	public List<AgencyMaster> getAgencyNames() {
		// TODO Auto-generated method stub
		return adminDAO.listAgencyMaster();
	}
	
	@Override
	public List<StateMaster> getStateList() {
		// TODO Auto-generated method stub
		return adminDAO.getStateList();
	}
	@Override
	public List<String> getStatusList() {
		// TODO Auto-generated method stub
		return adminDAO.liststatus();
	}

	@Override
	public AgencyMaster getAgencyById(int strId) {
		// TODO Auto-generated method stub
		return adminDAO.getAgency(strId);
	}
	
	
	@Override
	public int updateagency(int strId, String state, String agencyname, String city) {
		// TODO Auto-generated method stub
		return adminDAO.updateAgencyValue(strId,agencyname,city,state);
	}
	
	@Override
	public int UpdateVendor(int strId, String vendorname, String agencyname, String state, String city) {
		// TODO Auto-generated method stub
		return  adminDAO.updateVendorValue(strId, vendorname, agencyname, state, city);
	}
	@Override
	public int LockVendor(int strId) {
		// TODO Auto-generated method stub
		return adminDAO.LockVendorValue(strId);
	}
	@Override
	public int unlockVendor(int strId) {
		// TODO Auto-generated method stub
		return adminDAO.UnLockVendorValue(strId);
	}
	@Override
	public int LockAgency(int strId) {
		// TODO Auto-generated method stub
		return adminDAO.LockAgencyValue(strId);
	}
	@Override
	public int unlockAgency(int strId) {
		// TODO Auto-generated method stub
		return adminDAO.UnLockAgencyValue(strId);
	}
	
	@Override
	public int updateUser(int strId, String strpassword, String strEmailId, String strContact) {
		// TODO Auto-generated method stub
		return adminDAO.updateUserValue(strId,strpassword,strEmailId,strContact);
	}
	@Override
	public int updateVendor(VendorMaster vendor) {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public int LockUser(int strId) {
		// TODO Auto-generated method stub
		return adminDAO.LockUserValue(strId);
	}
	@Override
	public int unlockUser(int strId) {
		// TODO Auto-generated method stub
		return adminDAO.UnLockUserValue(strId);
	}
	@Override
	public UserMaster getUserByroleId(int strId) {
		// TODO Auto-generated method stub
		return adminDAO.getUserroleId(strId);
	}
	
	
	@Override
	public Object getVendorNameActives() {
		// TODO Auto-generated method stub
		return adminDAO.getVendorNameActives();
	}

	
	@Override
	public AgencyMaster checkAgencyName(String newAgencyName) {
		// TODO Auto-generated method stub
		return adminDAO.checkAgencyName(newAgencyName);
	}
	@Override
	public VendorMaster checkVendorName(String vendorName) {
		// TODO Auto-generated method stub
		return adminDAO.checkVendorName(vendorName);
	}
	@Override
	public UserMaster checkUser(String username) {
		// TODO Auto-generated method stub
		return adminDAO.UserName(username);
	}

	@Override
	public int getStatusId(String strStatus, String userRole, String strModule) {
		// TODO Auto-generated method stub
		return adminDAO.getStatusId(strStatus,userRole,strModule);
	}
	@Override
	public List<AgencyMaster> getAgenciesList() {
		// TODO Auto-generated method stub
		return adminDAO.getAgenciesList();
	}
	@Override
	public List<VendorMaster> getVendorList(int intAgencyId) {
		// TODO Auto-generated method stub
		return adminDAO.getVendorsList(intAgencyId);
	}
	@Override
	public Map<Integer, String> getBrandList() {
		// TODO Auto-generated method stub
		return adminDAO.brandMap();
	}
}

	

	
	

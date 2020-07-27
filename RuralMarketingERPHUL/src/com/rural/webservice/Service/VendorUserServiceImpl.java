/*
*AdminServiceImpl.java
*Created By		:Kamal Thapa
*Created Date	:Apr 4, 2018
*/
package com.rural.webservice.Service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import com.rural.Model.BatchTemp;
import com.rural.Model.DiviceMaster;
import com.rural.Model.LoginResponse;
/*import com.rural.Model.UserHistory;
*/import com.rural.Model.UserMaster;
import com.rural.webservice.DAO.VendorUserDAO;


@Component
@Service
public class VendorUserServiceImpl implements VendorUserService {
	@Autowired
	VendorUserDAO userDAO;

	@Override
	public int validateUser(String userName, String password) {
		// TODO Auto-generated method stub
		return userDAO.validateUser(userName,password);
	}

	@Override
	public LoginResponse getDeviceInfo(DiviceMaster diviceMaster) {
		// TODO Auto-generated method stub
		return userDAO.getDeviceInfo(diviceMaster);
	}

	@Override
	public BatchTemp getStates() {
		// TODO Auto-generated method stub
		return userDAO.getStates();
	}

	@Override
	public UserMaster validateUser(int userId) {
		// TODO Auto-generated method stub
		return userDAO.validateUser(userId);
	}
}

	

	
	

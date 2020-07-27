/*
*AdminService.java
*Created By		:Kamal Thapa
*Created Date	:Apr 4, 2018
*/
package com.rural.webservice.Service;

import org.springframework.stereotype.Component;
import com.rural.Model.BatchTemp;
import com.rural.Model.DiviceMaster;
import com.rural.Model.LoginResponse;
import com.rural.Model.UserMaster;

@Component
public interface VendorUserService {
public int validateUser(String userName, String password);
public LoginResponse getDeviceInfo(DiviceMaster diviceMaster);
public BatchTemp getStates();
public UserMaster validateUser(int userId);
}

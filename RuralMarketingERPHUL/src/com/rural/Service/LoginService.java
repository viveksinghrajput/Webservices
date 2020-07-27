package com.rural.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.rural.Model.AgencyMaster;
import com.rural.Model.Roles;
import com.rural.Model.SecurityAnswers;
import com.rural.Model.SecurityQuestions;
import com.rural.Model.StateMaster;
import com.rural.Model.StatusMaster;
import com.rural.Model.UserMaster;
import com.rural.Model.VendorMaster;

@Component
public interface LoginService {

	 public boolean checkLogin(String userName, String userPassword);

	public List<Roles> getRoles();
	public List<StateMaster> getStateMap();
	public List<AgencyMaster> getAgencyMap();

	public List<StatusMaster> getStatusMap();

	public List<UserMaster> getNames(Integer id, String lastName);

	public List<VendorMaster> getVendorMap();

	public List<SecurityQuestions> qList(Integer userId);

	public void saveAnswers(List<SecurityAnswers> ansList, Integer userId, String username, String strpassword);

	public int validateQuestions(Integer userId, List<SecurityAnswers> ansList);

	public int countAns(String username);

	public String getpassword(int userId);

	public  List<String> getMenuListbyRole(int roleId);
}

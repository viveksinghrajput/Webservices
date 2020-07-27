/*
*FinanceServiceImple.java
*Created By		:Bhagya Lakshmi Mula
*Created Date	:Jul 4, 2018
*/
package com.rural.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.rural.DAO.FinanceDAO;
import com.rural.Model.AgencyMaster;
import com.rural.Model.DOMaster;
import com.rural.Model.DOMasterTemp;
import com.rural.Model.FinanceDOBalance;
import com.rural.Model.FinanceDOMapping;
import com.rural.Model.FinanceMaster;
import com.rural.Model.StatusMaster;

@Component
@Service
public class FinanceServiceImple implements FinanceService {

	@Autowired
	FinanceDAO financeDAO;
	
	@Override
	public List<AgencyMaster> getAgencyList() {
		// TODO Auto-generated method stub
		return financeDAO.getAgencyList();
	}

	@Override
	public int createFinRequest(FinanceMaster financeMaster,String userRole,String strPath,String strFileName) {
		// TODO Auto-generated method stub
		return financeDAO.createFinRequest(financeMaster,userRole,strPath,strFileName);
	}

	@Override
	public List<FinanceMaster> getMyAssignedRequests(String username,String userRole) {
		// TODO Auto-generated method stub
		return financeDAO.getMyAssignedRequests(username,userRole);
	}

	@Override
	public List<FinanceMaster> getAllRequests(String username, String userRole) {
		// TODO Auto-generated method stub
		return financeDAO.getAllRequests(username,userRole);
	}


	@Override
	public FinanceMaster getRequestDetailsFinance(
			FinanceMaster financeMaster) {
		// TODO Auto-generated method stub
		return financeDAO.getRequestDetailsFinance(financeMaster);
	}



	@Override
	public int updateFinRequest(int intFinId, int statusId, String strRemarks,
			String userRole, String path, String strFileName) {
		// TODO Auto-generated method stub
		return financeDAO.updateFinRequest(intFinId,statusId,strRemarks,userRole,path,strFileName);
	}

	@Override
	public Map<String, String> getStatusMap(int intFinId, String userRole,
			String strModule) {
		// TODO Auto-generated method stub
		return financeDAO.getStatusMap(intFinId,userRole,strModule);
	}

	@Override
	public List<DOMaster> getDOList(String strAgencyName) {
		// TODO Auto-generated method stub
		return financeDAO.getDOList(strAgencyName);
	}

	@Override
	public int updateFinRequest(int finId, int statusId, String strRemarks,
			String userRole, String strPath, String strFileName,String totalAmount,
			List<FinanceDOMapping> finDOMapping) {
		// TODO Auto-generated method stub
		return financeDAO.updateFinRequest(finId,statusId,strRemarks,userRole,strPath,strFileName,totalAmount,finDOMapping);
	}

	@Override
	public List<DOMasterTemp> insertIntoDoTemp(List<DOMasterTemp> doList) {
		// TODO Auto-generated method stub
		return financeDAO.insertIntoDoTemp(doList);
	}

	@Override
	public List<FinanceDOMapping> getDOMapping(int finId) {
		// TODO Auto-generated method stub
		return financeDAO.getDOMapping(finId);
	}

	@Override
	public List<DOMaster> getDOList(Integer userId,
			String userRole) {
		// TODO Auto-generated method stub
		return financeDAO.getDOList(userId,userRole);
	}

	@Override
	public List<FinanceDOBalance> getFinanceDetailsByDO(String doNumber) {
		// TODO Auto-generated method stub
		return financeDAO.getFinanceDetailsByDO(doNumber);
	}

	@Override
	public DOMaster getDODetails(String doNumber) {
		// TODO Auto-generated method stub
		return financeDAO.getDODetails(doNumber);
	}

	
	}
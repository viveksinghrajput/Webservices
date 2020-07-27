/*
*FinanceService.java
*Created By		:Bhagya Lakshmi Mula
*Created Date	:Jul 4, 2018
*/
package com.rural.Service;

import java.util.List;
import java.util.Map;

import com.rural.Model.AgencyMaster;
import com.rural.Model.DOMaster;
import com.rural.Model.DOMasterTemp;
import com.rural.Model.FinanceDOBalance;
import com.rural.Model.FinanceDOMapping;
import com.rural.Model.FinanceMaster;
import com.rural.Model.StatusMaster;

public interface FinanceService {

	List<AgencyMaster> getAgencyList();

	int createFinRequest(FinanceMaster financeMaster, String userRole,String path,String fileName);

	List<FinanceMaster> getMyAssignedRequests(String username,String userRole);

	List<FinanceMaster> getAllRequests(String username, String userRole);

	FinanceMaster getRequestDetailsFinance(FinanceMaster financeMaster);

	int updateFinRequest(int intFinId, int statusId, String strRemarks,
			String userRole, String strPath, String strFileName);

	Map<String, String> getStatusMap(int intFinId, String userRole, String strModule);

	List<DOMaster> getDOList(String strAgencyName);

	int updateFinRequest(int finId, int statusId, String strRemarks,
			String userRole, String strPath, String strFileName,String totalAmount,
			List<FinanceDOMapping> finDOMapping);

	List<DOMasterTemp> insertIntoDoTemp(List<DOMasterTemp> doList);

	List<FinanceDOMapping> getDOMapping(int finId);

	List<DOMaster> getDOList(Integer userId, String userRole);

	List<FinanceDOBalance> getFinanceDetailsByDO(String doNumber);

	DOMaster getDODetails(String doNumber);


	}

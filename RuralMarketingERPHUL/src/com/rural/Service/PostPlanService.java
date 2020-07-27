/*
*PostPlanService.java
*Created By		:Bhagya Lakshmi Mula
*Created Date	:Apr 4, 2018
*/
package com.rural.Service;

import java.util.List;
import java.util.Map;

import com.rural.Model.SalesMaster;

public interface PostPlanService {
	//public List<String> getStates();
	public List<String> getCities();
	public List<String> getCalendar(String strFrequency);
	public List<SalesMaster> genereateReport(String strCity,String strFrequency,String strFromQtr,String strToQtr);
	public Map<Integer, String> getFrequency();
	public List<String> getSelectedCalendar(String strFromQtr, String strToQtr,
			String strFrequency);
	public List<String> getMOC(String tempQrt, String strFrequency);
	/*public List<String> getFinalContentForPostPlanning(String strCity,
			String strFrom, String strTo, String strFrequency,
			List<SalesMaster> salesMasterList,  List<String> listSelectedCalendar);
	public String downloadCSVFile(String strBasePath, String strFileName,
			List<String> finalContent);
	public List<String> getFinalContentForSummary(String strCity,
			String strFromQtr, String strToQtr, String strFrequency,
			Map<String, String> mapSummary,
			Map<String, Boolean> mapSummaryBoolean,
			List<String> listSelectedCalendar);*/
	
}

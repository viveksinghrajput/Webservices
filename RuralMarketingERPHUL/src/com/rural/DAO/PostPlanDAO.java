/*
*PostPlanDAO.java
*Created By		:Bhagya Lakshmi Mula
*Created Date	:Jul 4, 2018
*/
package com.rural.DAO;

import java.util.List;

import org.springframework.stereotype.Component;

import com.rural.Model.SalesMaster;


@Component
public interface PostPlanDAO {
	//public List<String> getStates();
	public List<String> getCities();
	public List<String> getCalendar(String strFrequency);
	public List<SalesMaster> genereateReport(String strCity,String strFrequency,String strFromQtr,String strToQtr);
	List<String> getSelectedCalendar(String strFrom,String strTo,String strFrequency);
	public List<String> getMOC(String tempQrt, String strFrequency);
	
}

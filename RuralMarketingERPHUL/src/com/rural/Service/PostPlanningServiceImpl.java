/*
*PostPlanningServiceImpl.java
*Created By		:Bhagya Lakshmi Mula
*Created Date	:Apr 4, 2018
*/
package com.rural.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import com.rural.Constant.IConstants;
import com.rural.DAO.PostPlanDAO;
import com.rural.Model.SalesMaster;


@Component
@Service
public class PostPlanningServiceImpl implements PostPlanService {

	@Autowired
	PostPlanDAO postPlanDAO;
	
	/*@Override
	public List<String> getStates() {
		// TODO Auto-generated method stub
		return postPlanDAO.getStates();
	}
*/
	@Override
	public List<String> getCities() {
		return postPlanDAO.getCities();
	}

	@Override
	public List<String> getCalendar(String strFrequency) {
		return postPlanDAO.getCalendar(strFrequency);
	}
	@Override
	public Map<Integer, String> getFrequency() {
		Map<Integer,String> mapFrequency=new LinkedHashMap<Integer,String>();
	//	mapFrequency.put(1, IConstants.MONTH);
		mapFrequency.put(2, IConstants.QUARTER);
		mapFrequency.put(3, IConstants.YEAR);
		return mapFrequency;
	}

	@Override
	public List<SalesMaster> genereateReport(String strCity,
			String strFrequency, String strFromQtr, String strToQtr) {
		 return postPlanDAO.genereateReport(strCity, strFrequency,strFromQtr, strToQtr);
		 
	}

	@Override
	public List<String> getSelectedCalendar(String strFromQtr, String strToQtr,
			String strFrequency) {
		return postPlanDAO.getSelectedCalendar(strFromQtr, strToQtr, strFrequency);
	}

	@Override
	public List<String> getMOC(String tempQrt, String strFrequency) {
		return postPlanDAO.getMOC(tempQrt, strFrequency);
	}

	}
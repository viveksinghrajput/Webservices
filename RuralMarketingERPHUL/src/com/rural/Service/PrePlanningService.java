/*
*PrePlanningService.java
*Created By		:Kamal Thappa
*Created Date	:Apr 4, 2018
*/
package com.rural.Service;

import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import com.rural.Model.PrePlanItemList;
import com.rural.Model.PrePlanMaster;
import com.rural.Model.UserMaster;

@Component
@Service
public interface PrePlanningService {

	
	public Map<String, String> getBrandList();
	public Map<String, String> getStateList();
	public List<String> getCityList();
	public List<String> getCityofState(String strState);
	public List<String> getCityofStateBrand(String strBrand,String strState);
	public List<PrePlanMaster> getAllPrePlan();
	public List<String> getConvAndSat(String strBrand);
	public String getSatData(String[] strCity);
	public String getSatData(String[] strCity, String strBrand, String strState);
	public List<PrePlanItemList> getPrePlanListData(String strBrand, String strState, String[] strCity,String strSpan);
	public List<PrePlanItemList> testPrePlanListData(String strBrand, String strState, String strCity,String strSpan);
	public int savePrePlanMaster( PrePlanItemList itemList, UserMaster user);
	public List<String> getStateOfBrand(String strBrand);
		
}

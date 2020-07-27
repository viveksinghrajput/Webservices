/*
*PrePlanningDAO.java
*Created By		:Kamal Thappa
*Created Date	:Apr 4, 2018
*/
package com.rural.DAO;

import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.rural.Model.CensusMaster;
import com.rural.Model.ConversionMaster;
import com.rural.Model.PrePlanMaster;


@Component
@Repository
public interface PrePlanningDAO {

	
	public Map<String,String> brandMap();
	public Map<String,String> stateMap();
	public List<String> getcityList(String state);
	public List<String> getcityList(String strBrand,String strState);
	public List<PrePlanMaster> showAllPrePlanData();
	public List<String> getBrandConvAndSat(String strBrand);
	public String getCitySaturation(String strCity);
	public String getBrandCitySaturation(String strBrand,String strCity);
	public String getSaturation(String strCity,String strBrand, String strState);
	public List<CensusMaster> getCensusData(String strCity);
	public List<ConversionMaster> getConversionData(String strBrand);
	public int persistPrePlanMaster(PrePlanMaster preplanMaster);
	public List<String> getStateList(String strBrand);
}

/*
*RoutePlanDAO.java
*Created By		:Bhagya Lakshmi Mula
*Created Date	:Jul 4, 2018
*/
package com.rural.DAO;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.rural.Model.PrePlanMaster;
import com.rural.Model.RoutePlanMaster;

@Component
public interface RoutePlanDAO {
	public List<String> getCities(String strBrand); 
	public List<String> getBrands(); 
	List<PrePlanMaster> getPrePlanList();
	List<RoutePlanMaster> getRouteplanlist(String strBrand, String strCity, String strLsm);
	List<RoutePlanMaster>  insertRoutePlan(String strBrand, String strCity, String strLsm,String strSessionId,
			String string);
	public PrePlanMaster getPlanDetails(int prePlanID);
	List<RoutePlanMaster> getTargetWards(String strBrand, String strCity, double targetAmount, String strconsideration,String[] strQuadrant);
}

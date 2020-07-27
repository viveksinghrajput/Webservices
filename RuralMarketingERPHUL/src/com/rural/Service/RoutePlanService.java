/*
*RoutePlanService.java
*Created By		:Bhagya Lakshmi Mula
*Created Date	:Apr 4, 2018
*/
package com.rural.Service;

import java.util.List;
import java.util.Map;

import com.rural.Model.PrePlanMaster;
import com.rural.Model.RoutePlanMaster;


public interface RoutePlanService {
	List<String> getCities(String strBrand);
	List<String> getBrands();
	List<PrePlanMaster> getPrePlanList();
	Map<Integer,String> getLSmList();
	List<RoutePlanMaster> getRouteplanlist(String strBrand, String strCity, String strLsm);
	List<RoutePlanMaster>  insertRoutePlan(String strBrand, String strCity, String strLsm,String strSessionId,
			String strUser);
	PrePlanMaster getPlanDetails(int prePlanID);
	List<RoutePlanMaster> getTargetWards(String strBrand, String strCity, double targetAmount, String strConsiderationset, String[] strQuadrant);
	Map<Integer, String> getQuadrantList();
}

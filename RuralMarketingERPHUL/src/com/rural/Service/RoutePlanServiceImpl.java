/*
*RoutePlanServiceImpl.java
*Created By		:Bhagya Lakshmi Mula
*Created Date	:Apr 4, 2018
*/
package com.rural.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Service;

import com.rural.Constant.IConstants;
import com.rural.DAO.RoutePlanDAO;
import com.rural.Model.PrePlanMaster;
import com.rural.Model.RoutePlanMaster;

@Service
@Configurable
public class RoutePlanServiceImpl implements RoutePlanService{
	
	@Autowired
	RoutePlanDAO routePlanDAO;
	
	@Override
	public List<String> getCities(String strBrand) {
	
		return routePlanDAO.getCities(strBrand);
	}
	@Override
	public List<String> getBrands() {
		
		return routePlanDAO.getBrands();
	}


	@Override
	public List<PrePlanMaster> getPrePlanList() {
		// TODO Auto-generated method stub
		return routePlanDAO.getPrePlanList();
	}


	@Override
	public Map<Integer,String> getLSmList() {
		// TODO Auto-generated method stub
		Map<Integer,String> mapLSM=new LinkedHashMap<Integer,String>();
		mapLSM.put(1, IConstants.LSM13);
		mapLSM.put(2, IConstants.LSM46);
		mapLSM.put(3, IConstants.LSM79);
		mapLSM.put(4, IConstants.LSM1012);
		mapLSM.put(5, IConstants.LSM1315);
		mapLSM.put(6, IConstants.LSM1618);
	return mapLSM;
	}
	
	@Override
	public Map<Integer,String> getQuadrantList() {
		// TODO Auto-generated method stub
		Map<Integer,String> mapQuadrant=new LinkedHashMap<Integer,String>();
		mapQuadrant.put(1, IConstants.Quadrant4);
		mapQuadrant.put(2, IConstants.Quadrant3);
		mapQuadrant.put(3, IConstants.Quadrant2);
		mapQuadrant.put(4, IConstants.Quadrant1);
	return mapQuadrant;
	}

	@Override
	public List<RoutePlanMaster> getRouteplanlist(String strBrand, String strCity,
			String strLsm) {
		// TODO Auto-generated method stub
		return routePlanDAO.getRouteplanlist(strBrand, strCity, strLsm);
	}

	@Override
	public List<RoutePlanMaster>  insertRoutePlan(String strBrand, String strCity,
			String strLsm, String strSessionId,String string) {
		// TODO Auto-generated method stub
		List<RoutePlanMaster>  routePlanMaster=routePlanDAO.insertRoutePlan(strBrand, strCity, strLsm, strSessionId,string);
		
		return routePlanMaster;
	}

	@Override
	public PrePlanMaster getPlanDetails(int prePlanID) {
		// TODO Auto-generated method stub
		return routePlanDAO.getPlanDetails(prePlanID);
	}
	@Override
	public List<RoutePlanMaster> getTargetWards(String strBrand,
			String strCity, double targetAmount, String strconsiderationset, String[] strQuadrant) {
		// TODO Auto-generated method stub
		return routePlanDAO.getTargetWards(strBrand,strCity,targetAmount, strconsiderationset, strQuadrant);
	}}

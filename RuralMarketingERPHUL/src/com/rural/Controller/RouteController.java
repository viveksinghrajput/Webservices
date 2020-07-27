/*
*RouteController.java
*Created By		:Bhagya Lakshmi Mula
*Created Date	:Jul 4, 2018
*/
package com.rural.Controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.rural.Model.PrePlanMaster;
import com.rural.Model.RoutePlanMaster;
import com.rural.Service.MenuService;
import com.rural.Service.RoutePlanService;
import com.rural.exceptions.ERPException;

@Controller
public class RouteController {

	@Autowired
	private MenuService menuService;

	@Autowired
	private RoutePlanService routePlanService;
	
	static Logger logger =Logger.getLogger(RouteController.class);

	@RequestMapping("/routePlan")
	public ModelAndView createRoutePlan(HttpServletRequest req,
			HttpServletResponse resp, Model model) {
		HttpSession session = req.getSession();
		ModelAndView mav = null;
		String statusMsg = null;
		Map<Integer, String> mapLSM = new LinkedHashMap<Integer, String>();
		// Quadrant written by Sai
		Map<Integer, String> mapQuadrant = new LinkedHashMap<Integer, String>();
		List<String> listBrand=null;
		List<PrePlanMaster> prePlanList=null;
		try {
			session.removeAttribute("routeplanList");
			session.removeAttribute("targetWardList");
			session.removeAttribute("showrRoutePlanList");
			//name = session.getAttribute("name").toString();
			listBrand = new ArrayList<String>();
			listBrand = routePlanService.getBrands();
			mapLSM = routePlanService.getLSmList();
			// Quadrant written by Sai
			try{
			mapQuadrant = routePlanService.getQuadrantList();
			prePlanList = routePlanService.getPrePlanList();
			} catch (ERPException erp) {
				statusMsg = "Problem Occured while loading Preplan list. Please try Again";
			} catch (Exception e) {
				statusMsg = "Problem Occured while loading Preplan list. Please try Again";
			}
			mav = new ModelAndView("routePlan");
		
			if (listBrand.size() == 0) {
				statusMsg = "No Pre Plan Data is available . No Data is mapped with Camtool Master.";
			}
		} catch (ERPException erp) {
			statusMsg = "Problem Occured. Please try Again";
		} catch (Exception e) {
			statusMsg = "Problem Occured. Please try Again";
		}
		//mav.addObject("name", name);
		mav.addObject("brandList", listBrand);
		mav.addObject("lSMList", mapLSM);
		// Quadrant written by Sai
		mav.addObject("quadrantList", mapQuadrant);
		mav.addObject("prePlanList", prePlanList);
		mav.addObject("statusMsg", statusMsg);
		return mav;
	}

	@RequestMapping(value = "/loadCitiesForRoutePlan", method = RequestMethod.POST)
	public @ResponseBody List<String> getCity(
			@RequestParam("brand") String strBrand, HttpServletRequest req,
			Model model) {
		List<String> listCity = new ArrayList<String>();
		try {
			listCity = routePlanService.getCities(strBrand);
		} catch (ERPException erp) {
		} catch (Exception e) {
		}
		return listCity;

	}


	
	@RequestMapping(value = "/createRoutePlan", method = RequestMethod.POST)
	public ModelAndView createRoutePlan(
			@RequestParam("lsm") String strLsm,
			@RequestParam("target_amount") String strTargetAmount,
			@RequestParam("consideration") String strconsiderationset,
			@RequestParam("prePlanID") int strprePlanID,
			HttpServletRequest req, Model model) {
		String statusMsg = null;
		ModelAndView modelandview = new ModelAndView("routePlan");
		Map<Integer, String> mapQuadrant = new LinkedHashMap<Integer, String>();
			HttpSession session = req.getSession();
			String sessionId = session.getId();
			String username = session.getAttribute("username").toString();
			PrePlanMaster prePlanMaster = routePlanService
					.getPlanDetails(strprePlanID);	
			session.removeAttribute("isGetRoutePlanList");
			session.removeAttribute("lsmTemp");
			session.removeAttribute("routeplanList");
			session.removeAttribute("showrRoutePlanList");
			session.removeAttribute("targetWardList");
			session.setAttribute("isGetRoutePlanList", "Y");
			session.setAttribute("lsmTemp",strLsm);
			Map<Integer, String> mapLSM = new LinkedHashMap<Integer, String>();
			List<String> listBrand=null;
			List<PrePlanMaster> prePlanList=null;
			List<RoutePlanMaster> routeplanList =null;
			List<RoutePlanMaster> targetWardList =null;
			try {
			int i03;
			int i46;
			int i79;
			int i1012;
			int i1315;
			int i1618;
			i03 = strLsm.indexOf("1");
			i46 = strLsm.indexOf("2");
			i79 = strLsm.indexOf("3");
			i1012 = strLsm.indexOf("4");
			i1315 = strLsm.indexOf("5");
			i1618 = strLsm.indexOf("6");
			if (i03 != -1) {
				modelandview.addObject("lsm03", "Y");
			}

			if (i46 != -1) {
				modelandview.addObject("lsm46", "Y");
			}

			if (i79 != -1) {
				modelandview.addObject("lsm79", "Y");
			}
			if (i1012 != -1) {
				modelandview.addObject("lsm1012", "Y");
			}

			if (i1315 != -1) {
				modelandview.addObject("lsm1315", "Y");
			}

			if (i1618 != -1) {
				modelandview.addObject("lsm1618", "Y");
			}
			
			try{
			 routeplanList = routePlanService
					.insertRoutePlan(prePlanMaster.getBrand(),
							prePlanMaster.getCitytown(), strLsm, sessionId,
							username);
			} catch (Exception e) {
				 statusMsg = "Error while creationg Route Plan";
			}
			 String strQuadrant[]=new String[4];
				if(strconsiderationset.equalsIgnoreCase("no")) {
					strQuadrant=req.getParameterValues("quadrant");
				}
				try{
				 targetWardList = routePlanService
							.getTargetWards(prePlanMaster.getBrand(),
									prePlanMaster.getCitytown(),Double.parseDouble(strTargetAmount),strconsiderationset,strQuadrant );
				} catch (Exception e) {
					 statusMsg = "Error while loading Target Wards";
				}	
			 if (routeplanList.size() != 0) {
				statusMsg = "Route Plan Created Successfully";
			} else {
				statusMsg = "No mapping Data is Available.";
			}

			modelandview.addObject("statusMsg", statusMsg);
			listBrand = new ArrayList<String>();
			listBrand = routePlanService.getBrands();
			mapLSM = routePlanService.getLSmList();
			prePlanList = routePlanService.getPrePlanList();
			mapQuadrant = routePlanService.getQuadrantList();
		} catch (ERPException erp) {
			statusMsg = "Problem Occured. Please try Again";
		} catch (Exception e) {
		}
			List<String> lsmTemp = Arrays.asList(strLsm);
		modelandview.addObject("lSMList", mapLSM);
		modelandview.addObject("lsmTemp", lsmTemp);
		modelandview.addObject("selected_lsm", strLsm);
		modelandview.addObject("prePlanList", prePlanList);
		modelandview.addObject("brandList", listBrand);
		modelandview.addObject("selected_target_amount", strTargetAmount);
		modelandview.addObject("CreateRoutePlanTab", "Y");
		session.setAttribute("routeplanList", routeplanList);
		session.setAttribute("targetWardList", targetWardList);
		modelandview.addObject("selected_target_amount", strTargetAmount);
		modelandview.addObject("statusMsg", statusMsg);
		modelandview.addObject("quadrantList", mapQuadrant);
		return modelandview;
	}

	
	@RequestMapping(value = "/getPreplanlist", method = RequestMethod.POST)
	public ModelAndView getPreplanlist(@RequestParam("brand") String strBrand,
			@RequestParam("city") String strCity, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		ModelAndView modelandview = new ModelAndView("createRoutePlan");
		List<String> listBrand = new ArrayList<String>();
		Map<Integer, String> mapLSM = new LinkedHashMap<Integer, String>();
		List<String> listCity = new ArrayList<String>();
		String statusMsg = null;
		List<PrePlanMaster> prePlanList =null;
		try {
			try{
			listBrand = routePlanService.getBrands();
			} catch (ERPException erp) {
				statusMsg = "Problem Occured while loading Brand List. Please try Again";
			} catch (Exception e) {
				statusMsg = "Problem Occured while loading Brand list. Please try Again";
			}
			try{
			listCity = routePlanService.getCities(strBrand);
			} catch (ERPException erp) {
				statusMsg = "Problem Occured while loading City list. Please try Again";
			} catch (Exception e) {
				statusMsg = "Problem Occured while loading City list. Please try Again";
			}
			mapLSM = routePlanService.getLSmList();
			try{
			 prePlanList = routePlanService.getPrePlanList();
			} catch (ERPException erp) {
				statusMsg = "Problem Occured while loading Preplan list. Please try Again";
			} catch (Exception e) {
				statusMsg = "Problem Occured while loading Preplan list. Please try Again";
			}

		} catch (ERPException erp) {
			statusMsg = "Problem Occured. Please try Again";
			erp.printStackTrace();
		} catch (Exception e) {
			statusMsg = "Problem Occured. Please try Again";
			e.printStackTrace();
		}
		modelandview.addObject("statusMsg", statusMsg);
		modelandview.addObject("brandList", listBrand);
		modelandview.addObject("lSMList", mapLSM);
		modelandview.addObject("cityList", listCity);
		modelandview.addObject("selected_brand", strBrand);
		modelandview.addObject("selected_city", strCity);
		modelandview.addObject("prePlanList", prePlanList);
		return modelandview;
	}

	@RequestMapping(value = "/Routeplanlist", method = RequestMethod.POST)
	public ModelAndView getRouteplanlist(
			@RequestParam("brand") String strBrand,
			@RequestParam("city") String strCity,
			@RequestParam("viewLsm") String strLsm, HttpServletRequest req,
			HttpServletResponse response, Model model) {
		String statusMsg = null;
		ModelAndView modelandview = new ModelAndView("routePlan");
		List<PrePlanMaster> prePlanList = null;
		List<RoutePlanMaster> routePlanList=null;
		List<String> listBrand = new ArrayList<String>();
		Map<Integer, String> mapLSM = new LinkedHashMap<Integer, String>();
		List<String> listCity = new ArrayList<String>();
		try {
			HttpSession session = req.getSession();
			session.removeAttribute("isGetRoutePlanList");
			session.removeAttribute("lsmTemp");
			session.removeAttribute("showrRoutePlanList");
			session.setAttribute("isGetRoutePlanList", "Y");
			session.setAttribute("strBrand", strBrand);
			session.setAttribute("strCity", strCity);
			session.setAttribute("lsmTemp", strLsm);
			listBrand = routePlanService.getBrands();
			listCity = routePlanService.getCities(strBrand);
			mapLSM = routePlanService.getLSmList();
			try{
			routePlanList = routePlanService
					.getRouteplanlist(strBrand, strCity, strLsm);
			} catch (ERPException erp) {
				statusMsg = "Problem Occured while loading Route Plans. Please try Again";
			} catch (Exception e) {
				statusMsg = "Problem Occured while loading Route Plans. Please try Again";
			}
			session.setAttribute("showrRoutePlanList", routePlanList);

			if (routePlanList.size() == 0) {
				statusMsg = "There are no Route Plans for the Selected Combination.";
			} else {
				int i03;
				int i46;
				int i79;
				int i1012;
				int i1315;
				int i1618;
				i03 = strLsm.indexOf("1");
				i46 = strLsm.indexOf("2");
				i79 = strLsm.indexOf("3");
				i1012 = strLsm.indexOf("4");
				i1315 = strLsm.indexOf("5");
				i1618 = strLsm.indexOf("6");
			if (i03 != -1) {
					modelandview.addObject("lsm03", "Y");
				}

				if (i46 != -1) {
					modelandview.addObject("lsm46", "Y");
				}

				if (i79 != -1) {
					modelandview.addObject("lsm79", "Y");
				}
				if (i1012 != -1) {
					modelandview.addObject("lsm1012", "Y");
				}

				if (i1315 != -1) {
					modelandview.addObject("lsm1315", "Y");
				}

				if (i1618 != -1) {
					modelandview.addObject("lsm1618", "Y");
				}

				prePlanList = routePlanService
						.getPrePlanList();

			}
		} catch (ERPException erp) {
			statusMsg = "Problem Occured. Please try Again";
			erp.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		modelandview.addObject("brandList", listBrand);
		modelandview.addObject("lSMList", mapLSM);
		modelandview.addObject("cityList", listCity);
		modelandview.addObject("selected_brand", strBrand);
		modelandview.addObject("selected_city", strCity);
		modelandview.addObject("selected_lsm", strLsm);
		modelandview.addObject("statusMsgForView", statusMsg);
		modelandview.addObject("prePlanList", prePlanList);
		modelandview.addObject("GetRoutePlans", "Y");
		return modelandview;
	}

	@RequestMapping(value = "/back", method = RequestMethod.POST)
	public ModelAndView back(@RequestParam("brand") String strBrand,
			@RequestParam("city") String strCity,
			@RequestParam("lsm") String strLsm, Model model) {
		ModelAndView modelandview = new ModelAndView("createRoutePlan");
		Map<Integer, String> mapLSM = new LinkedHashMap<Integer, String>();
		List<String> listBrand = new ArrayList<String>();
		List<String> listCity = new ArrayList<String>();
		listBrand = routePlanService.getBrands();
		listCity = routePlanService.getCities(strBrand);
		mapLSM = routePlanService.getLSmList();
		List<PrePlanMaster> prePlanList = routePlanService.getPrePlanList();
		modelandview.addObject("brandList", listBrand);
		modelandview.addObject("cityList", listCity);
		modelandview.addObject("lSMList", mapLSM);
		modelandview.addObject("selected_brand", strBrand);
		modelandview.addObject("selected_city", strCity);
		modelandview.addObject("selected_lsm", strLsm);
		modelandview.addObject("prePlanList", prePlanList);
		return modelandview;
	}

	@RequestMapping(value = "/exportToCSV", method = RequestMethod.GET)
	public ModelAndView exportToCSV(@RequestParam("name") String name,HttpServletRequest req,
			HttpServletResponse response, Model model) {
		ModelAndView modelandview = new ModelAndView("routePlan");
		String strLsm = null;
		List<String> listBrand = new ArrayList<String>();
		Map<Integer, String> mapLSM = new LinkedHashMap<Integer, String>();
		String statusMsg=null;
		List<RoutePlanMaster> routePlanList=null;
		List<PrePlanMaster> prePlanList=null;
		HttpSession session = req.getSession();
		String strBrand = (String) session.getAttribute("strBrand");
		String strCity = (String) session.getAttribute("strCity");
		if (session.getAttribute("isGetRoutePlanList").equals("Y")) {
			strLsm = (String) session.getAttribute("lsmTemp");
		}
		List<String> listCity = new ArrayList<String>();
		try{
		listBrand = routePlanService.getBrands();
		listCity = routePlanService.getCities(strBrand);
		mapLSM = routePlanService.getLSmList();
		String strFile=null;
		if(name.endsWith("TargetWards")){
			strFile="TargetWards.csv";
			 routePlanList =(List<RoutePlanMaster>) session.getAttribute("targetWardList");
		}else if(name.endsWith("SavedRoutePlans"))
		{			
			strFile="GeneratedRoutePlan.csv";
			 routePlanList =(List<RoutePlanMaster>) session.getAttribute("routeplanList");
		}
		else if(name.endsWith("ViewRoutePlans"))
		{	
			strFile="RoutePlan.csv";
			routePlanList =(List<RoutePlanMaster>) session.getAttribute("showrRoutePlanList");
		}
		 prePlanList = routePlanService.getPrePlanList();
		downloadCSV(response,strFile, strLsm, routePlanList);
		
} catch (ERPException erp) {
	statusMsg = "Problem Occured while downloading Route Plans. Please try Again";
	erp.printStackTrace();
} catch (Exception e) {
	e.printStackTrace();
}
		modelandview.addObject("brandList", listBrand);
		modelandview.addObject("cityList", listCity);
		modelandview.addObject("lSMList", mapLSM);
		modelandview.addObject("selected_brand", strBrand);
		modelandview.addObject("selected_city", strCity);
		modelandview.addObject("selected_lsm", strLsm);
		modelandview.addObject("prePlanList", prePlanList);
modelandview.addObject("statusMsg", statusMsg);

		return modelandview;
	}

	public void downloadCSV(HttpServletResponse response,String name/*, String strBrand,
			String strCity*/, String strLsm, List<RoutePlanMaster> routePlanList) {
		ServletOutputStream outStream = null;
		try {

			ArrayList<String> rows = new ArrayList<String>();
			rows.add("City,Ward,Brand,Retailers,Actual Sales Done,Market Potential Sales,Market Share,Contribution,Market Share Hilo,Contribution Hilo,X_Y,Quadrant,Total HH,");
			int i03;
			int i46;
			int i79;
			int i1012;
			int i1315;
			int i1618;
			i03 = strLsm.indexOf("1");
			i46 = strLsm.indexOf("2");
			i79 = strLsm.indexOf("3");
			i1012 = strLsm.indexOf("4");
			i1315 = strLsm.indexOf("5");
			i1618 = strLsm.indexOf("6");
			String strLsmName = null;

			if (i03 != -1) {
				strLsmName = "LSM 0-3";
				rows.add("LSM 0-3,");
			}

			if (i46 != -1) {
				strLsmName = strLsmName + "LSM 4-6";

				rows.add("LSM 4-6,");
			}

			if (i79 != -1) {
				strLsmName = strLsmName + "LSM 7-9";

				rows.add("LSM 7-9,");
			}
			if (i1012 != -1) {
				strLsmName = strLsmName + "LSM 10-12";

				rows.add("LSM 10-12,");
			}

			if (i1315 != -1) {
				strLsmName = strLsmName + "LSM 13-15";

				rows.add("LSM 13-15,");
			}

			if (i1618 != -1) {
				strLsmName = strLsmName + "LSM 16-18";

				rows.add("LSM 16-18,");
			}
			rows.add("TG HH,Conversions,Sauration,");
			//String strFile = strBrand + strCity  + ".csv";

if(name.equals("TargetWards.csv")){
	rows.add("Planned Contacts,TG HH's @ 30% Saturation,Balance TG HH's Potential,TG/Total");
}
else{
	rows.add("Balance TG HH,TG HH's @ 30% Saturation,Balance TG HH's Potential,TG/Total");
}
			rows.add("\n");

			Iterator<RoutePlanMaster> iter = routePlanList.iterator();
			while (iter.hasNext()) {
				RoutePlanMaster routePlanMaster = (RoutePlanMaster) iter.next();
				rows.add(routePlanMaster.getCity() + ","
						+ routePlanMaster.getArea() + ","
						+ routePlanMaster.getBrand() + ","
						+ routePlanMaster.getNoof_Retailors() + ","
						+ routePlanMaster.getActual_Sales_Value() + ","
						+ routePlanMaster.getMarket_Potential_Sales() + ","
						+ routePlanMaster.getMarket_Share() + ","
						+ routePlanMaster.getContribution() + ","
						+routePlanMaster.getMarket_Share_HiLo() + ","
						+routePlanMaster.getContribution_HiLo() + ","
						+"\""+routePlanMaster.getX_Y()+ "\""+","+ ""
						+routePlanMaster.getQuadrant() + ","
						+routePlanMaster.getTotal_HH() + ",");

				if (i03 != -1) {
					rows.add(routePlanMaster.getlSM_0_3() + ",");
				}

				if (i46 != -1) {
					rows.add(routePlanMaster.getlSM_4_6() + ",");
				}

				if (i79 != -1) {
					rows.add(routePlanMaster.getlSM_7_9() + ",");
				}
				if (i1012 != -1) {
					rows.add(routePlanMaster.getlSM_10_12() + ",");
				}

				if (i1315 != -1) {
					rows.add(routePlanMaster.getlSM_13_15() + ",");
				}

				if (i1618 != -1) {
					rows.add(routePlanMaster.getlSM_16_18() + ",");
				}

				rows.add(routePlanMaster.gettG_HH() + ","
						+ routePlanMaster.getConversions()+ ","
						+ routePlanMaster.getSaturation() + ","
						+ routePlanMaster.getBalance_TG_HH() + ","
						+ routePlanMaster.gettG_HH_30_percent() + ","
						+ routePlanMaster.getBalance_TG_HH_Potential() + ","
						+ routePlanMaster.gettG_HH_Potental()+ "," );
				rows.add("\n");
			}
			outStream = response.getOutputStream();
			response.setContentType("text/csv");
			response.setHeader("Content-disposition", "attachment;filename="
					+ name);
			Iterator<String> itr = rows.iterator();
			while (itr.hasNext()) {
				String outputString = (String) itr.next();
				outStream.print(outputString);
			}

		} catch (IOException ie) {
			ie.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(outStream!=null)
				{
				outStream.flush();
				outStream.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}

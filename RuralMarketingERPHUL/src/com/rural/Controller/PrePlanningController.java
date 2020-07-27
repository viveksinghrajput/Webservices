/*
*PrePlanningController.java
*Created By		:Kamal Thappa
*Created Date	:Jul 4, 2018
*/
package com.rural.Controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.rural.DAO.PrePlanningDAOImpl;
import com.rural.Model.PrePlanItemList;
import com.rural.Model.PrePlanMaster;
import com.rural.Model.UserMaster;
import com.rural.Service.PrePlanningService;
import com.rural.exceptions.ERPException;

@Controller
public class PrePlanningController {

	@Autowired
	PrePlanningService prePlanningService;
	
	static Logger logger =Logger.getLogger(PrePlanningController.class);

	@RequestMapping(value = "/prePlan")
	public String showPrePlanHome(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		HttpSession session = request.getSession(true);
		String statusMsg=null;
		
		//Role wise Restricting the user to access the url
		int role=(Integer) request.getSession().getAttribute("userRole1");
		if(role==1 || role==2) {
		try{
		session.setAttribute("showAllprePlan", prePlanningService.getAllPrePlan());
		} catch (ERPException erp) {
			statusMsg = "Problem in Loading the Preplans list";
		} catch (Exception e) {
			statusMsg = "Problem in Loading the Preplans list";
		}
		
		try{
			session.setAttribute("brandList", prePlanningService.getBrandList());
			} catch (ERPException erp) {
				statusMsg = "Problen in Loading the Brands";
			} catch (Exception e) {
				statusMsg = "Problen in Loading the Brands";
			}
		request.setAttribute("strStatus", statusMsg);
		}else {
			try {
			request.getRequestDispatcher("/views/login.jsp").forward(request, response);
			}catch (Exception e) {
			}
		}
		return ("showPrePlanning");
	}

	@RequestMapping(value = "/createPrePlan")
	public String showCreateNewPrePlan(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		HttpSession session = request.getSession(true);
		String statusMsg=null;

		//Role wise Restricting the user to access the url
		int role=(Integer) request.getSession().getAttribute("userRole1");
		if(role==1 || role==2) {
		try{
		session.setAttribute("brandList", prePlanningService.getBrandList());
		} catch (ERPException erp) {
			statusMsg = "Problen in Loading the Brands";
		} catch (Exception e) {
			statusMsg = "Problen in Loading the Brands";
		}
		request.setAttribute("strStatus", statusMsg);
	}else {
		try {
		request.getRequestDispatcher("/views/login.jsp").forward(request, response);
		}catch (Exception e) {
		}
	}
		return ("preplanning");
	}
	
	@RequestMapping(value = "/getState", method = RequestMethod.POST)
	public @ResponseBody List<String> getState(@RequestParam("brand") String strBrand, HttpServletResponse response, Model model,HttpServletRequest request) {
		List<String> listState = new ArrayList<String>();

		//Role wise Restricting the user to access the url
		int role=(Integer) request.getSession().getAttribute("userRole1");
		if(role==1 || role==2) {
		try{
		listState = prePlanningService.getStateOfBrand(strBrand);
		} catch (ERPException erp) {
			/*logger.error("Exception occured while Loading the States :"+ erp.getMessage());*/
		} catch (Exception e) {
			/*logger.error("Exception occured while Loading the States :"+ e.getMessage());*/
		}
		}else {
			try {
			request.getRequestDispatcher("/views/login.jsp").forward(request, response);
			}catch (Exception e) {
			}
		}
		return listState;

	}

	@RequestMapping(value = "/getCity", method = RequestMethod.POST)
	public @ResponseBody List<String> getCity(@RequestParam("brand") String strBrand,@RequestParam("state") String strstate, HttpServletRequest req,
			HttpServletResponse response,Model model) {
		List<String> listCity = new ArrayList<String>();

		//Role wise Restricting the user to access the url
		int role=(Integer) req.getSession().getAttribute("userRole1");
		if(role==1 || role==2) {
		try{
		listCity = prePlanningService.getCityofStateBrand(strBrand,strstate);
			} catch (ERPException erp) {
				/*logger.error("Exception occured while Loading the cities :"+ erp.getMessage());*/
			} catch (Exception e) {
				/*logger.error("Exception occured while Loading the cities :"+ e.getMessage());*/
			}
	}else {
		try {
		req.getRequestDispatcher("/views/login.jsp").forward(req, response);
		}catch (Exception e) {
		}
	}
		return listCity;

	}

	@RequestMapping(value = "/getConvAndAvgSales", method = RequestMethod.POST)
	public @ResponseBody List<String> getConvAndSat(@RequestParam("brand") String strBrand, HttpServletRequest req,
			HttpServletResponse response,Model model) {
		List<String> convAndSat = new ArrayList<String>();

		//Role wise Restricting the user to access the url
		int role=(Integer) req.getSession().getAttribute("userRole1");
		if(role==1 || role==2) {
		try{
		convAndSat = prePlanningService.getConvAndSat(strBrand);
		} catch (ERPException erp) {
			/*logger.error("Exception occured while Loading the Conversion and Avg Sales :"+ erp.getMessage());*/
		} catch (Exception e) {
			/*logger.error("Exception occured while Loading the Conversion and Avg Sales :"+ e.getMessage());*/
		}
		
		}else {
			try {
			req.getRequestDispatcher("/views/login.jsp").forward(req, response);
			}catch (Exception e) {
			}
		}
		return convAndSat;

	}

	@RequestMapping(value = "/getSaturation", method = RequestMethod.POST)
	public @ResponseBody String getSaturation(@RequestParam("brand") String strBrand,@RequestParam("state") String strState,@RequestParam("city") String[] strCity, HttpServletRequest req,
			HttpServletResponse response,Model model) {
		String citySat=null;
		//Role wise Restricting the user to access the url
				int role=(Integer) req.getSession().getAttribute("userRole1");
				if(role==1 || role==2) {
		try{
		 citySat = prePlanningService.getSatData(strCity,strBrand,strState);
		} catch (ERPException erp) {
			/*logger.error("Exception occured while Loading Saturation % :"+ erp.getMessage());*/
		} catch (Exception e) {
			/*logger.error("Exception occured whileLoading Saturation % :"+ e.getMessage());*/
		}
		
	}else {
		try {
		req.getRequestDispatcher("/views/login.jsp").forward(req, response);
		}catch (Exception e) {
		}
	}
		return citySat;
	}
	@RequestMapping(value = "/getPreplanlistNew", method = RequestMethod.POST)
	public ModelAndView getPreplanlistNew(@RequestParam("brand") String strBrand,
			@RequestParam("state") String strState, @RequestParam("city") String[] strCity,
			@RequestParam("span") String strSpan, @RequestParam("saturation") String strSaturation,
			@RequestParam("conversion") String strConversion, @RequestParam("averageSales") String strAverageSales,
			HttpServletRequest request, ModelAndView mav,HttpServletResponse response) {
		List<String> listCityTemp = Arrays.asList(strCity);
		List<PrePlanItemList> listPreplanItems = new ArrayList<PrePlanItemList>();
		ModelAndView modelandview = new ModelAndView("preplanning");
		List<String> listCity = new ArrayList<String>();
		String statusMsg=null;
		//Role wise Restricting the user to access the url
		int role=(Integer) request.getSession().getAttribute("userRole1");
		if(role==1 || role==2) {
		try{
		listCity = prePlanningService.getCityofStateBrand(strBrand, strState);
		listPreplanItems = prePlanningService.getPrePlanListData(strBrand, strState, strCity, strSpan);
		List<String> listState = new ArrayList<String>();
		listState = prePlanningService.getStateOfBrand(strBrand);
		modelandview.addObject("listCity", listCity);
		modelandview.addObject("stateList",listState );
		modelandview.addObject("listPreplanItems", listPreplanItems);
		modelandview.addObject("selected_brand", strBrand);
		modelandview.addObject("selected_state", strState);
		modelandview.addObject("selected_city", listCityTemp);
		modelandview.addObject("selected_span", strSpan);
		modelandview.addObject("selected_saturation", strSaturation);
		modelandview.addObject("selected_conversion", strConversion);
		modelandview.addObject("selected_averageSales", strAverageSales);
	} catch (ERPException erp) {
		statusMsg = " Problem Occured when Creating the Preplan";
	} catch (Exception e) {
		statusMsg = "Problem Occured when Creating the Preplan";
	}
		request.setAttribute("strStatus", statusMsg);
		}else {
			try {
			request.getRequestDispatcher("/views/login.jsp").forward(request, response);
			}catch (Exception e) {
			}
		}
		return modelandview;

	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/savePrePlanMaster")
	public ModelAndView createPrePlanMaster(HttpServletRequest request, ModelAndView modelAndView,HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("showPrePlanning");
		int result = 0;
		String statusMsg = null;
		HttpSession session = request.getSession(true);
		
		UserMaster user = new UserMaster();
		List<PrePlanItemList> itemList =new ArrayList<PrePlanItemList>();
		String username=null;
		//Role wise Restricting the user to access the url
		int role=(Integer) request.getSession().getAttribute("userRole1");
		if(role==1 || role==2) {
		try{
		username= (String) session.getAttribute("username");
		itemList= (List<PrePlanItemList>)session.getAttribute("listPreplan");
		user.setUsername(username);
		for (PrePlanItemList temp : itemList) {
			result = prePlanningService.savePrePlanMaster(temp, user);
		}

		if (result == 1) {
			statusMsg = "Pre Plan Saved Successfully.";

		}
		mav.addObject("listPreplanItems", itemList);
		request.setAttribute("strStatus", statusMsg);
		session.setAttribute("showAllprePlan", prePlanningService.getAllPrePlan());
		session.setAttribute("brandList", prePlanningService.getBrandList());
	} catch (ERPException erp) {
		statusMsg = "Problem Occured while Saving the Preplan";
	} catch (Exception e) {
		statusMsg = "Problem Occured while Saving the Preplan";
	}
		request.setAttribute("strStatus", statusMsg);
		}else {
			try {
			request.getRequestDispatcher("/views/login.jsp").forward(request, response);
			}catch (Exception e) {
			}
		}
		
		return mav;
	}
	
	@RequestMapping(value = "/exportSavedPrePlan", method = RequestMethod.GET)
	public void exportSavedPrePlan(HttpServletRequest request,HttpServletResponse response, Model model) {
		String strFile ="MasterPrePlan.csv";
		
		HttpSession session=request.getSession(true);
		String statusMsg=null;
		//Role wise Restricting the user to access the url
		int role=(Integer) request.getSession().getAttribute("userRole1");
		if(role==1 || role==2) {
		try{
		List<PrePlanItemList> listPreplanItems=(List<PrePlanItemList>)session.getAttribute("listPreplan");
		if(listPreplanItems!=null){
		downloadCSVSavedPrePlan(response, listPreplanItems,strFile);
		}
		else{
			statusMsg = "No Preplan Data is available to download";
		}
		} catch (ERPException erp) {
			statusMsg = "Problem Occured while downloading the Preplans";
		} catch (Exception e) {
			statusMsg = "Problem Occured while downloading the Preplans";
		}
		request.setAttribute("strStatus", statusMsg);
		
		}else {
			try {
			request.getRequestDispatcher("/views/login.jsp").forward(request, response);
			}catch (Exception e) {
			}
		}
	}
	
	public void downloadCSVSavedPrePlan(HttpServletResponse response, List<PrePlanItemList> prePlanMasterList,String strFile) {
		ServletOutputStream outStream = null;
		try {
			
			ArrayList<String> rows = new ArrayList<String>();
			rows.add("Brand,State,Town,Population,Household,Conversions ,Contacts,Average Sales,"
					+ "Span of Activity,No. of Promotors,No. of Teams");
			rows.add("\n");

			Iterator<PrePlanItemList> iter = prePlanMasterList.iterator();
			while (iter.hasNext()) {
				PrePlanItemList prePlanMaster = (PrePlanItemList) iter.next();
				rows.add(prePlanMaster.getBrand() + ","+prePlanMaster.getState()+","+prePlanMaster.getCity()+","+
				prePlanMaster.getTot_pop()+","+prePlanMaster.getTot_hh()+","+prePlanMaster.getConversion()+","+
				prePlanMaster.getContactDone()+","+prePlanMaster.getAvgSales()+","+prePlanMaster.getSpan()
				+","+prePlanMaster.getPromotorNum()+","+prePlanMaster.getTeamNum());

				rows.add("\n");
			}
			outStream = response.getOutputStream();
			response.setContentType("text/csv");
			response.setHeader("Content-disposition", "attachment;filename="
					+ strFile);
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
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	@RequestMapping(value = "/exportToCSVForPrePlan", method = RequestMethod.GET)
	public void exportToCSVForPrePlan(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		HttpSession session=request.getSession(true);
		List<PrePlanMaster> prePlanMaster=(List<PrePlanMaster>)session.getAttribute("showAllprePlan");
		String strFile ="AllPrePlanMasterDetails.csv";
		//Role wise Restricting the user to access the url
		int role=(Integer) request.getSession().getAttribute("userRole1");
		if(role==1 || role==2) {
		downloadCSV(response, prePlanMaster,strFile);
		
		}else {
		try {
		request.getRequestDispatcher("/views/login.jsp").forward(request, response);
		}catch (Exception e) {
		}
	}
		

	//	return modelandview;
	}
	
	public void downloadCSV(HttpServletResponse response, List<PrePlanMaster> prePlanMasterList,String strFile) {
		ServletOutputStream outStream = null;
		try {
			
			ArrayList<String> rows = new ArrayList<String>();
			rows.add("Brand,State,Town,Population,Household,Conversions ,Contacts,Average Sales,"
					+ "Span of Activity,No. of Promotors,No. of Teams,Created Date, Created By");
			rows.add("\n");

			Iterator<PrePlanMaster> iter = prePlanMasterList.iterator();
			while (iter.hasNext()) {
				PrePlanMaster prePlanMaster = (PrePlanMaster) iter.next();
				rows.add(prePlanMaster.getBrand() + ","+prePlanMaster.getState()+","+prePlanMaster.getCitytown()+","+
				prePlanMaster.getTot_pop()+","+prePlanMaster.getTot_hh()+","+prePlanMaster.getConversion()+","+
				prePlanMaster.getContactDone()+","+prePlanMaster.getAvgSales()+","+prePlanMaster.getSpanDays()
				+","+prePlanMaster.getPromotorNum()+","+prePlanMaster.getTeamNum()+","+prePlanMaster.getCreatedDate()
				+","+prePlanMaster.getCreatedBy());

				rows.add("\n");
			}
			outStream = response.getOutputStream();
			response.setContentType("text/csv");
			response.setHeader("Content-disposition", "attachment;filename="
					+ strFile);
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
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
}

/*
*PostPlanningController.java
*Created By		:Bhagya Lakshmi Mula
*Created Date	:Jul 4, 2018
*/
package com.rural.Controller;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
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

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rural.Constant.IConstants;
import com.rural.Model.SalesMaster;
import com.rural.Service.PostPlanService;
import com.rural.exceptions.ERPException;
import com.rural.utility.CommonUtility;

@Controller
public class PostPlanningController {
	static Logger logger =Logger.getLogger(PostPlanningController.class);
	
	@Autowired
	private PostPlanService postPlanService;

	@RequestMapping(value = "/postPlan")
	public ModelAndView getCity(HttpServletRequest req, Model model,HttpServletResponse response) {
		ModelAndView mav = null;
		mav = new ModelAndView("postPlanning");
		List<String> listCity = new ArrayList<String>();
		// List<String> listQuarters= new ArrayList<String>();
		Map<Integer, String> mapFrequency = new LinkedHashMap<Integer, String>();

		String statusMsg = null;

		//Role wise Restricting the user to access the url
		int role=(Integer) req.getSession().getAttribute("userRole1");
		if(role==1 || role==2) {
		try {
			req.getSession().removeAttribute("salesMasterList");
			req.getSession().removeAttribute("mapSummary");
			req.getSession().removeAttribute("mapSummaryBoolean");
			
			listCity = postPlanService.getCities();
			mapFrequency = postPlanService.getFrequency();
		} catch (ERPException erp) {
			statusMsg = "Problem  occured while loading cities. Please try Again";
		} catch (Exception e) {
			statusMsg = "Problem  occured while loading cities. Please try Again";
		}

		mav.addObject("listCity", listCity);
		mav.addObject("mapFrequency", mapFrequency);
		mav.addObject("statusMsg", statusMsg);
		}else {
			try {
			req.getRequestDispatcher("/views/login.jsp").forward(req, response);
			}catch (Exception e) {
			}
		}
		return mav;
	}

	@RequestMapping(value = "/loadCalendar", method = RequestMethod.POST)
	public @ResponseBody List<String> loadCalendar(
			@RequestParam("frequency") String strFrequency,
			HttpServletRequest req, Model model,HttpServletResponse response) {
		String statusMsg = null;
		List<String> listCalendar = null;
		//Role wise Restricting the user to access the url
		int role=(Integer) req.getSession().getAttribute("userRole1");
		if(role==1 || role==2) {
		try {
			listCalendar = new ArrayList<String>();

			listCalendar = postPlanService.getCalendar(strFrequency);
		} catch (ERPException erp) {
			statusMsg = "Problem  occured while loading Calendar. Please try Again";
		} catch (Exception e) {
			statusMsg = "Problem  occured while loading Calendar. Please try Again";
		}
		req.setAttribute("statusMsg", statusMsg);
	}else {
		try {
		req.getRequestDispatcher("/views/login.jsp").forward(req, response);
		}catch (Exception e) {
		}
	}
		return listCalendar;

	}

	@RequestMapping(value = "/generateReport", method = RequestMethod.POST)
	public ModelAndView generateReport(@RequestParam("city") String strCity,
			@RequestParam("frequency") String strFrequency,
			@RequestParam("fromQtr") String strFromQtr,
			@RequestParam("toQtr") String strToQtr, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		logger.debug("Show generateReport ");

		ModelAndView modelandview = new ModelAndView("postPlanning");
		//Map<String, Map<String, Double>> finalMap = new LinkedHashMap<String, Map<String, Double>>();
		/*Map<String, Double> map = null;
		SalesMaster salesMaster = null;
		String strWard = null;*/
		HttpSession session = request.getSession();
		session.removeAttribute("salesMasterList");
		session.removeAttribute("mapSummary");
		session.removeAttribute("mapSummaryBoolean");

		List<String> listCalendar = new ArrayList<String>();
		List<String> listSelectedCalendar = null;
		String strMsg = null;
		List<SalesMaster> summaryReport = null;
		List<SalesMaster> salesMasterList = null;
		Map<Integer, String> mapFrequency = null;
	
		Double donefor0First = 0.00;
		Double donefor1First = 0.00;
		Double donefor0Second = 0.00;
		Double donefor1Second = 0.00;
		Double saturationfor1First= 0.00;
		Double saturationfor1Second= 0.00;
		Double saturationfor5First= 0.00;
		Double saturationfor5Second= 0.00;
		Double saturationfor10First= 0.00;
		Double saturationfor10Second= 0.00;
		Double saturationfor15First= 0.00;
		Double saturationfor15Second= 0.00;
		Double saturationfor20First= 0.00;
		Double saturationfor20Second= 0.00;

		 boolean isDoneFor0=false;
		 boolean isDoneFor1=false;
		 int penetration=0;
		 boolean issaturationfor5=false;
		 boolean issaturationfor10=false;
		 boolean issaturationfor15=false;
		 boolean issaturationfor20=false;
		 Map<String,String>  mapSummary=new  HashMap<String,String>();
		 Map<String,Boolean>  mapSummaryBoolean=new  HashMap<String,Boolean>();
			String firstYear=null;
			String secondYear=null;
		DecimalFormat decimalFormat = new DecimalFormat("#.##");
		if (/*strFrequency.equals(IConstants.MONTH)
				||*/ strFrequency.equals(IConstants.QUARTER)) {
			firstYear=strFromQtr.substring(3);
		 secondYear=strToQtr.substring(3);
		}
		else{
			firstYear=strFromQtr;
			 secondYear=strToQtr;
		}

		try {
			listSelectedCalendar = postPlanService.getSelectedCalendar(
					strFromQtr, strToQtr, strFrequency);
			
			salesMasterList = postPlanService.genereateReport(strCity,
					strFrequency, strFromQtr, strToQtr);

		
			// Calculate Quarter wise sales
			
			//Summary Report Calculation

for(SalesMaster salesMasterTemp : (List<SalesMaster>) salesMasterList) { 
			String tempTotalSales=salesMasterTemp.getTotal_sales();
			penetration=salesMasterTemp.getPenetration();
			String strTotalSales = null;
			StringTokenizer tokenizerTotalSales = new StringTokenizer(
					tempTotalSales, ",");

			if(salesMasterTemp.getDone().equals("0")){
				isDoneFor0=true;
			while (tokenizerTotalSales.hasMoreTokens()) {
				strTotalSales = tokenizerTotalSales.nextToken();

					if (strTotalSales.substring(0, 4).equals(firstYear)) {
						donefor0First = donefor0First + Double.parseDouble(strTotalSales.substring(5));
						saturationfor1First=saturationfor1First + Double.parseDouble(strTotalSales.substring(5));

					}else{

						donefor0Second = donefor0Second + Double.parseDouble(strTotalSales.substring(5));
						saturationfor1Second=saturationfor1Second + Double.parseDouble(strTotalSales.substring(5));

					}
			}
			}	else{
				isDoneFor1=true;
				if(penetration>1 && penetration<=5){
					issaturationfor5=true;
				while (tokenizerTotalSales.hasMoreTokens()) {
					strTotalSales = tokenizerTotalSales.nextToken();
						if (strTotalSales.substring(0, 4).equals(firstYear)) {
							donefor1First = donefor1First + Double.parseDouble(strTotalSales.substring(5));
							saturationfor5First= saturationfor5First + Double.parseDouble(strTotalSales.substring(5));

						}else{
							donefor1Second = donefor1Second + Double.parseDouble(strTotalSales.substring(5));
							saturationfor5Second= saturationfor5Second + Double.parseDouble(strTotalSales.substring(5));
						}
				
				}
				}
				if(penetration>5 && penetration<=10){
					issaturationfor10=true;
					while (tokenizerTotalSales.hasMoreTokens()) {
						strTotalSales = tokenizerTotalSales.nextToken();
							if (strTotalSales.substring(0, 4).equals(firstYear)) {
								donefor1First = donefor1First + Double.parseDouble(strTotalSales.substring(5));
								saturationfor10First= saturationfor10First + Double.parseDouble(strTotalSales.substring(5));

							}else{
								donefor1Second = donefor1Second + Double.parseDouble(strTotalSales.substring(5));
								saturationfor10Second= saturationfor10Second + Double.parseDouble(strTotalSales.substring(5));
							}
					}
					}
				if(penetration>10 && penetration<=15){
					issaturationfor15=true;
					while (tokenizerTotalSales.hasMoreTokens()) {
						strTotalSales = tokenizerTotalSales.nextToken();
							if (strTotalSales.substring(0, 4).equals(firstYear)) {
								donefor1First = donefor1First + Double.parseDouble(strTotalSales.substring(5));
								saturationfor15First= saturationfor15First + Double.parseDouble(strTotalSales.substring(5));

							}else{
								donefor1Second =   + Double.parseDouble(strTotalSales.substring(5));
								saturationfor15Second= saturationfor15Second + Double.parseDouble(strTotalSales.substring(5));
							}
					}
					}
				if(penetration>15){
					issaturationfor20=true;
					while (tokenizerTotalSales.hasMoreTokens()) {
						strTotalSales = tokenizerTotalSales.nextToken();
							if (strTotalSales.substring(0, 4).equals(firstYear)) {
								donefor1First = donefor1First + Double.parseDouble(strTotalSales.substring(5));
								saturationfor20First= saturationfor20First + Double.parseDouble(strTotalSales.substring(5));

							}else{
								donefor1Second = donefor1Second + Double.parseDouble(strTotalSales.substring(5));
								saturationfor20Second= saturationfor20Second + Double.parseDouble(strTotalSales.substring(5));
							}
					}
					}
			}
}

				mapSummary.put("donefor0First",decimalFormat.format(donefor0First));
				mapSummary.put("donefor1First",decimalFormat.format(donefor1First));
				mapSummary.put("donefor0Second",decimalFormat.format(donefor0Second));
				mapSummary.put("donefor1Second",decimalFormat.format(donefor1Second));
				mapSummary.put("saturationfor1First",decimalFormat.format(saturationfor1First));
				mapSummary.put("saturationfor1Second",decimalFormat.format(saturationfor1Second));
				mapSummary.put("saturationfor5First",decimalFormat.format(saturationfor5First));
				mapSummary.put("saturationfor5Second",decimalFormat.format(saturationfor5Second));
				mapSummary.put("saturationfor10First",decimalFormat.format(saturationfor10First));
				mapSummary.put("saturationfor10Second",decimalFormat.format(saturationfor10Second));
				mapSummary.put("saturationfor15First",decimalFormat.format(saturationfor15First));
				mapSummary.put("saturationfor15Second",decimalFormat.format(saturationfor15Second));
				mapSummary.put("saturationfor20First",decimalFormat.format(saturationfor20First));
				mapSummary.put("saturationfor20Second",decimalFormat.format(saturationfor20Second));
				mapSummaryBoolean.put("isDoneFor0",isDoneFor0);
				mapSummaryBoolean.put("isDoneFor1",isDoneFor1);
				mapSummaryBoolean.put("issaturationfor5",issaturationfor5);
				mapSummaryBoolean.put("issaturationfor10",issaturationfor10);
				mapSummaryBoolean.put("issaturationfor15",issaturationfor15);
				mapSummaryBoolean.put("issaturationfor20",issaturationfor20);

			//Summary Report Calculation Ended

		
			List<String> listCity = new ArrayList<String>();
			mapFrequency = new LinkedHashMap<Integer, String>();
			listCity = postPlanService.getCities();
			listCalendar = postPlanService.getCalendar(strFrequency);
			mapFrequency = postPlanService.getFrequency();
			modelandview.addObject("listCity", listCity);

			if (/*strFrequency.equals(IConstants.MONTH)
					||*/ strFrequency.equals(IConstants.QUARTER)) {
				modelandview.addObject("_YTD1", strFromQtr.substring(3));
				if (!strFromQtr.substring(3).equals(strToQtr.substring(3))) {
					modelandview.addObject("_YTD2", strToQtr.substring(3));
				}
			} else if (strFrequency.equals(IConstants.YEAR)) {

				modelandview.addObject("_YTD1", "YTD_" + strFromQtr);

				if (!strFromQtr.equals(strToQtr)) {
					modelandview.addObject("_YTD2", "YTD_" + strToQtr);
				}

			}
		} catch (ERPException erp) {
			strMsg = "Some error occured . Plese try Again";
			erp.printStackTrace();

		} catch (Exception e) {
			strMsg = "Some error occured . Plese try Again";
			e.printStackTrace();

		}
		
		
		modelandview.addObject("listCalendar", listCalendar);
		modelandview.addObject("mapFrequency", mapFrequency);
		modelandview.addObject("selected_city", strCity);
		modelandview.addObject("selected_frequency", strFrequency);
		modelandview.addObject("selected_fromqtr", strFromQtr);
	//	modelandview.addObject("finalMap", finalMap);
		session.setAttribute("salesMasterList", salesMasterList);
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			session.setAttribute("mapSummary", mapSummary);
			session.setAttribute("mapSummaryBoolean", mapSummaryBoolean);
			String jsonString = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(mapSummary);
			jsonString = jsonString.replaceAll("\r\n", "");
			session.setAttribute("mapSummaryJson", jsonString);
			jsonString = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(mapSummaryBoolean);
			jsonString = jsonString.replaceAll("\r\n", "");
			session.setAttribute("mapSummaryBooleanJson", jsonString);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		request.setAttribute("listSelectedCalendar", listSelectedCalendar);
		modelandview.addObject("selected_toqtr", strToQtr);
		modelandview.addObject("strMsg", strMsg);
		request.setAttribute("summaryReport", summaryReport);
		
		
		Double d1=saturationfor5Second+saturationfor10Second+saturationfor15Second+saturationfor20Second;
		request.setAttribute("d1", d1);
		mapSummary.put("d1",d1.toString());
		try {
			session.setAttribute("mapSummary", mapSummary);
			String jsonString = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(mapSummary);
			jsonString = jsonString.replaceAll("\r\n", "");
			session.setAttribute("mapSummaryJson", jsonString);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		session.setAttribute("city", strCity);
		session.setAttribute("from", strFromQtr);
		session.setAttribute("to", strToQtr);
		session.setAttribute("frequency", strFrequency);
		//session.setAttribute("finalMap", finalMap);
		
		return modelandview;
	}

	@RequestMapping(value = "/exportToCSVForPostPlanning", method = RequestMethod.GET)
	public void exportToCSVForPostPlanning(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		logger.debug("Show downloadCSV ");

		/*ModelAndView modelandview = new ModelAndView("postPlanning");*/
		/*Map<String, Map<String, Double>> finalMap = new LinkedHashMap<String, Map<String, Double>>();*/
		HttpSession session = request.getSession();
		/*String strMsg = null;
		List<String> listCity = new ArrayList<String>();
		Map<Integer, String> mapFrequency = new LinkedHashMap<Integer, String>();
		List<String> listCalendar = new ArrayList<String>();*/
		List<String> listSelectedCalendar = null;
		List<SalesMaster> salesMasterList = null;
		String strCity = null;
		String strFromQtr = null;
		String strToQtr = null;
		String strFrequency = null;
		//Role wise Restricting the user to access the url
		int role=(Integer) request.getSession().getAttribute("userRole1");
		if(role==1 || role==2) {
		try {
			strCity = (String) session.getAttribute("city");
			strFromQtr = (String) session.getAttribute("from");
			strToQtr = (String) session.getAttribute("to");
			strFrequency = (String) session.getAttribute("frequency");
			listSelectedCalendar = postPlanService.getSelectedCalendar(
					strFromQtr, strToQtr, strFrequency);
			/*salesMasterList = postPlanService.genereateReport(strCity,
					strFrequency, strFromQtr, strToQtr);*/
			salesMasterList=(List<SalesMaster>) session.getAttribute("salesMasterList");

			// Summary Report
			downloadCSV(response, strCity, strFromQtr, strToQtr, strFrequency,
					salesMasterList, listSelectedCalendar);
/*
			listCity = postPlanService.getCities();
			listCalendar = postPlanService.getCalendar(strFrequency);
			mapFrequency = postPlanService.getFrequency();

			if (strFrequency.equals(IConstants.MONTH)
					|| strFrequency.equals(IConstants.QUARTER)) {
				modelandview.addObject("_YTD1", strFromQtr.substring(3));
				if (!strFromQtr.substring(3).equals(strToQtr.substring(3))) {
					modelandview.addObject("_YTD2", strToQtr.substring(3));
				}
			} else if (strFrequency.equals(IConstants.YEAR)) {

				modelandview.addObject("_YTD1", "YTD_" + strFromQtr);

				if (!strFromQtr.equals(strToQtr)) {
					modelandview.addObject("_YTD2", "YTD_" + strToQtr);
				}

			}*/
		} catch (ERPException erp) {
			logger.error("Problem occuured while Downloading the Sales"+erp.getMessage());

		} catch (Exception e) {
			logger.error("Problem occuured while Downloading the Sales"+e.getMessage());

		}
		/*modelandview.addObject("listCity", listCity);
		modelandview.addObject("listCalendar", listCalendar);
		modelandview.addObject("mapFrequency", mapFrequency);
		modelandview.addObject("selected_city", strCity);
		modelandview.addObject("selected_frequency", strFrequency);
		modelandview.addObject("selected_fromqtr", strFromQtr);
	//	modelandview.addObject("finalMap", finalMap);
		modelandview.addObject("salesMasterList", salesMasterList);
		request.setAttribute("listSelectedCalendar", listSelectedCalendar);
		modelandview.addObject("selected_toqtr", strToQtr);
		modelandview.addObject("statusMsg", strMsg);

		return modelandview;*/

		}else {
			try {
			request.getRequestDispatcher("/views/login.jsp").forward(request, response);
			}catch (Exception e) {
			}
		}
	}

	@RequestMapping(value = "/exportToCSVForSummary", method = RequestMethod.GET)
	public void exportToCSVForSummary(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		logger.debug("Show downloadCSV ");

/*		ModelAndView modelandview = new ModelAndView("postPlanning");
*/	//	Map<String, Map<String, Double>> finalMap = new LinkedHashMap<String, Map<String, Double>>();
		HttpSession session = request.getSession();
		/*String strMsg = null;
		List<String> listCity = new ArrayList<String>();
		Map<Integer, String> mapFrequency = new LinkedHashMap<Integer, String>();
		List<String> listCalendar = new ArrayList<String>();
		List<String> listSelectedCalendar = new ArrayList<String>();*/
		List<SalesMaster> salesMasterList = null;
		String strCity = null;
		String strFromQtr = null;
		String strToQtr = null;
		String strFrequency = null;
		 Map<String,String>  mapSummary=new  HashMap<String,String>();
		 Map<String,Boolean>  mapSummaryBoolean=new  HashMap<String,Boolean>();
		//Role wise Restricting the user to access the url
			int role=(Integer) request.getSession().getAttribute("userRole1");
			if(role==1 || role==2) {
		try {
			strCity = (String) session.getAttribute("city");
			strFromQtr = (String) session.getAttribute("from");
			strToQtr = (String) session.getAttribute("to");
			strFrequency = (String) session.getAttribute("frequency");
			/*listSelectedCalendar = postPlanService.getSelectedCalendar(
					strFromQtr, strToQtr, strFrequency);*/
			/*salesMasterList = postPlanService.genereateReport(strCity,
					strFrequency, strFromQtr, strToQtr);*/
			/*session.setAttribute("mapSummary", mapSummary);
			session.setAttribute("mapSummaryBoolean", mapSummaryBoolean);*/
			salesMasterList=(List<SalesMaster>) session.getAttribute("salesMasterList");
			mapSummary= (Map<String, String>) session.getAttribute("mapSummary");
			mapSummaryBoolean=  (Map<String, Boolean>) session.getAttribute("mapSummaryBoolean");
			// Summary Report
			downloadCSV(response, strCity, strFromQtr, strToQtr, strFrequency,
					salesMasterList, mapSummary,mapSummaryBoolean);

			/*listCity = postPlanService.getCities();
			listCalendar = postPlanService.getCalendar(strFrequency);
			mapFrequency = postPlanService.getFrequency();*/

			/*if (strFrequency.equals(IConstants.MONTH)
					|| strFrequency.equals(IConstants.QUARTER)) {
				modelandview.addObject("_YTD1", strFromQtr.substring(3));
				if (!strFromQtr.substring(3).equals(strToQtr.substring(3))) {
					modelandview.addObject("_YTD2", strToQtr.substring(3));
				}
			} else if (strFrequency.equals(IConstants.YEAR)) {

				modelandview.addObject("_YTD1", "YTD_" + strFromQtr);

				if (!strFromQtr.equals(strToQtr)) {
					modelandview.addObject("_YTD2", "YTD_" + strToQtr);
				}

			}*/
		} catch (ERPException erp) {
			logger.error("Problem occuured while Downloading the Summary Report"+erp.getMessage());

		} catch (Exception e) {
			logger.error("Problem occuured while Downloading the Summary Report"+e.getMessage());

		}
		/*modelandview.addObject("listCity", listCity);
		modelandview.addObject("listCalendar", listCalendar);
		modelandview.addObject("mapFrequency", mapFrequency);
		modelandview.addObject("selected_city", strCity);
		modelandview.addObject("selected_frequency", strFrequency);
		modelandview.addObject("selected_fromqtr", strFromQtr);
		//modelandview.addObject("finalMap", finalMap);
		modelandview.addObject("salesMasterList", salesMasterList);
		request.setAttribute("listSelectedCalendar", listSelectedCalendar);
		modelandview.addObject("selected_toqtr", strToQtr);
		modelandview.addObject("statusMsg", strMsg);
		modelandview.addObject("mapSummary", mapSummary);
		modelandview.addObject("mapSummaryBoolean", mapSummaryBoolean);*/

		//return modelandview;
			}else {
				try {
				request.getRequestDispatcher("/views/login.jsp").forward(request, response);
				}catch (Exception e) {
				}
			}
	}
	
	
	@SuppressWarnings("null")
	private void downloadCSV(HttpServletResponse response, String strCity,
			String strFromQtr, String strToQtr, String strFrequency,
			List<SalesMaster> salesMasterList, Map<String, String> mapSummary,
			Map<String, Boolean> mapSummaryBoolean) {
		ServletOutputStream outStream = null;
		String strFile=null;
		String firstYear=null;
		String secondYear=null;
		try {
			ArrayList<String> rows = new ArrayList<String>();
		 firstYear=strFromQtr.substring(3);
		 secondYear=strToQtr.substring(3);
 strFile="SummaryInfo.csv";
		rows.add("Summary 1,,,,,,,,,,Summary 2");
		rows.add("\n");
		rows.add("Done / Not Done,sum of "+firstYear+",");
		if((secondYear!=null || !secondYear.equals("null")) && !secondYear.equals(firstYear)){
			rows.add("sum of "+secondYear+", Growth %");
		}else
		{
			rows.add(",");
		}
		rows.add(",,,,,,,Done / Not Done,Slab of Saturation,sum of "+firstYear+",");
		if((secondYear!=null || !secondYear.equals("null")) && !secondYear.equals(firstYear)){
			rows.add("sum of "+secondYear+", Growth %");
		}else
		{
			rows.add(",");
		}
		rows.add("\n");
		//For Done/Not Done 0
		if(mapSummaryBoolean.get("isDoneFor0")==true){
		rows.add("0,"+mapSummary.get("donefor0First"));
		if((secondYear!=null || !secondYear.equals("null")) && !secondYear.equals(firstYear)){
			rows.add(","+mapSummary.get("donefor0Second")+","+Double.toString(CommonUtility.round((((Double.parseDouble(mapSummary.get("donefor0Second"))/Double.parseDouble(mapSummary.get("donefor0First")))-1)*100),2)));
		}else
		{
			rows.add(",,");
		}
		/*if(mapSummaryBoolean.get("isDoneFor1")==true) {
			rows.add("1,"+mapSummary.get("donefor1First"));
		}
		
		if(secondYear!=null || !secondYear.equals("null")){
			rows.add(","+mapSummary.get("d1")+","+((mapSummary.get("d1")/mapSummary.get("donefor1First"))-1)*100);
		}*/
		rows.add(",,,,,,,");
		//For Saturation 	1%
	//	if(mapSummaryBoolean.get("isDoneFor0")==true) {
			rows.add("0,"+"<1%,"+mapSummary.get("donefor0First"));
		//}
			if((secondYear!=null || !secondYear.equals("null")) && !secondYear.equals(firstYear)){
			rows.add(","+mapSummary.get("saturationfor1Second")+","+Double.toString(CommonUtility.round((((Double.parseDouble(mapSummary.get("saturationfor1Second"))/Double.parseDouble((mapSummary.get("saturationfor1First"))))-1)*100),2)));
		}else
		{
			rows.add(",,");
		}
		}
		rows.add("\n");
		//For Done/Not Done 1
		if(mapSummaryBoolean.get("isDoneFor1")==true){
		
		//For Saturation 	1-5%
		if(mapSummaryBoolean.get("issaturationfor5")==true){
		rows.add("1,"+mapSummary.get("donefor1First"));
		if((secondYear!=null || !secondYear.equals("null")) && !secondYear.equals(firstYear)){
			rows.add(","+mapSummary.get("d1")+","+Double.toString(CommonUtility.round((((Double.parseDouble(mapSummary.get("d1"))/Double.parseDouble(mapSummary.get("donefor1First")))-1)*100),2)));
		}else
		{
			rows.add(",,");
		}
		rows.add(",,,,,,,1,"+"1-5%,"+mapSummary.get("saturationfor5First"));
		if((secondYear!=null || !secondYear.equals("null")) && !secondYear.equals(firstYear)){
			rows.add(","+mapSummary.get("saturationfor5Second")+","+Double.toString(CommonUtility.round((((Double.parseDouble(mapSummary.get("saturationfor5Second"))/Double.parseDouble(mapSummary.get("saturationfor5First")))-1)*100),2)));
		}else
		{
			rows.add(",,");
		}
		}
		rows.add("\n");
		
		//For Saturation 	5-10%
		if(mapSummaryBoolean.get("issaturationfor10")==true){
		rows.add(",,,,,,,,,,1,"+"5-10%,"+mapSummary.get("saturationfor10First"));
		if((secondYear!=null || !secondYear.equals("null")) && !secondYear.equals(firstYear)){
			rows.add(","+mapSummary.get("saturationfor10Second")+","+Double.toString(CommonUtility.round((((Double.parseDouble(mapSummary.get("saturationfor10Second"))/Double.parseDouble(mapSummary.get("saturationfor10First")))-1)*100),2)));
		}else
		{
			rows.add(",,");
		}
		}
		rows.add("\n");
		//For Saturation 	10-15%
		if(mapSummaryBoolean.get("issaturationfor10")==true){
			rows.add(",,,,,,,,,,1,"+"10-15%,"+mapSummary.get("saturationfor15First"));
			if((secondYear!=null || !secondYear.equals("null")) && !secondYear.equals(firstYear)){
				rows.add(","+mapSummary.get("saturationfor15Second")+","+Double.toString(CommonUtility.round((((Double.parseDouble(mapSummary.get("saturationfor15Second"))/Double.parseDouble(mapSummary.get("saturationfor15First")))-1)*100),2)));
			}else
			{
				rows.add(",,");
			}
		}
		rows.add("\n");		
				
		//For Saturation  >15%
		if(mapSummaryBoolean.get("issaturationfor20")==true){
			rows.add(",,,,,,,,,,1,"+">15%,"+mapSummary.get("saturationfor20First"));
			if((secondYear!=null || !secondYear.equals("null")) && !secondYear.equals(firstYear)){
				rows.add(","+mapSummary.get("saturationfor20Second")+","+Double.toString(CommonUtility.round((((Double.parseDouble(mapSummary.get("saturationfor20Second"))/Double.parseDouble(mapSummary.get("saturationfor20First")))-1)*100),2)));
			}else
			{
				rows.add(",,");
			}
		}
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
			logger.error("Some Problem occured while downloading the Summary Report "+ie.getMessage());
		} catch (Exception e) {
			logger.error("Some Problem occured while downloading the Summary Report "+e.getMessage());
		} finally {
			try {
				if (outStream != null) {
					outStream.flush();
					outStream.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	
		
		
	}

	public void downloadCSV(HttpServletResponse response, String strCity,
			String strFrom, String strTo, String strFrequency,
			List<SalesMaster> salesMasterList, List<String> listSelectedCalendar) {
		ServletOutputStream outStream = null;
		try {
			String strFile = null;
			String firstYear=strFrom.substring(3);
			String secondYear=strTo.substring(3);

			String monthFrom=CommonUtility.getMonth().get(strFrom.substring(0, 2));
			String monthTo=CommonUtility.getMonth().get(strTo.substring(0, 2));

			if (strFrequency.equals(IConstants.MONTH)){
			if (strFrom.substring(0, 2).equals(strTo.substring(0, 2))) {
				strFile = "" + strCity + "CitySalesFor" + monthFrom+""+firstYear + ".csv";
			} else {
				strFile = strCity + "CitySalesFor "
						+ monthFrom +firstYear+ "-" + monthTo+secondYear+ ".csv";

			}
			}
			else{
				if (strFrom.equals(strTo)) {
					 strFile=strCity+"CitySalesfor"+strFrom + ".csv";
				}
				else
				{
					 strFile=strCity+"CitySalesfor "+strFrom+""+strTo + ".csv";

				}
			}
			ArrayList<String> rows = new ArrayList<String>();
			rows.add("City,Ward,Total HH,HH Nos,Saturation %,Done / Not Done,Slab of Saturation,");

		/*	if (strFrequency.equals(IConstants.MONTH)
					|| strFrequency.equals(IConstants.QUARTER)) {
				strSelectedCalendar = CommonUtility
						.convertListToStringArray(listSelectedCalendar);
				for (int i = 0; i < strSelectedCalendar.length; i++) {
					rows.add(strSelectedCalendar[i] + ",");
				}
			}*/
			if (/*strFrequency.equals(IConstants.MONTH)
					||*/ strFrequency.equals(IConstants.QUARTER)) {
				if (firstYear.equals(secondYear)) {

					rows.add("YTD_" + firstYear);

				}
				if (!firstYear.equals(secondYear)) {
					rows.add("YTD_" + firstYear + ",");
					rows.add("YTD_" + secondYear);
				}
			} else if (strFrequency.equals(IConstants.YEAR)) {

				if (strFrom.equals(strTo)) {
					rows.add("YTD_" + strFrom);
				}
				if (!strFrom.equals(strTo)) {
					rows.add("YTD_" + strFrom + ",");
					rows.add(strTo);
				}

			}

			rows.add("\n");
			Iterator<SalesMaster> iter = salesMasterList.iterator();
			while (iter.hasNext()) {
				SalesMaster salesMaster = (SalesMaster) iter.next();
				rows.add(salesMaster.getCity() + "," + salesMaster.getWard()
						+ "," + salesMaster.getTotal_HH() + ","
						+ salesMaster.getNoHH() + ","
						+ salesMaster.getPenetration() + ",");
				Map<String, Double> totSales = salesMaster.getTotSalesMap();
				/*	Double d = 0.0;
					List<String> mocList = null;
				if (strFrequency.equals(IConstants.MONTH)) {
					strSelectedCalendar = CommonUtility
							.convertListToStringArray(listSelectedCalendar);
					for (int i = 0; i < strSelectedCalendar.length; i++) {
						rows.add(salesMaster.getSalesMap().get(
								strSelectedCalendar[i])
								+ ",");
					}
				}
				if (strFrequency.equals(IConstants.QUARTER)) {
					for (String tempQrt : listSelectedCalendar) {
						d = 0.0;
						mocList = postPlanService.getMOC(tempQrt, strFrequency);
						for (String temp : mocList) {
							d = d + salesMaster.getSalesMap().get(temp);
						}
						rows.add(d + ",");

					}
				}*/
				rows.add(salesMaster.getDone() + ","
						+ salesMaster.getSlab_saturation() + ",");

				for (String strYear : totSales.keySet()) {
					rows.add(totSales.get(strYear) + ",");
				}
				rows.add("\n");

			}
			
			response.setContentType("text/csv");
			response.setHeader("Content-disposition", "attachment;filename="
					+ strFile);
			outStream = response.getOutputStream();
			Iterator<String> itr = rows.iterator();
			while (itr.hasNext()) {
				String outputString = (String) itr.next();
				outStream.print(outputString);
			}

		} catch (IOException ie) {
			logger.error("Some Problem occured while downloading the sales information "+ie.getMessage());
			ie.printStackTrace();
		} catch (Exception e) {
			logger.error("Some Problem occured while downloading the sales information "+e.getMessage());
			e.printStackTrace();
		} finally {
			try {
				if (outStream != null) {
					outStream.flush();
					outStream.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}

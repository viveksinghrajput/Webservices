/*
*FinanceController.java
*Created By		:Bhagya Lakshmi Mula
*Created Date	:Jul 4, 2018
*/
package com.rural.Controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rural.Constant.IConstants;
import com.rural.Model.AgencyMaster;
import com.rural.Model.DOMaster;
import com.rural.Model.DOMasterTemp;
import com.rural.Model.FinanceDOBalance;
import com.rural.Model.FinanceDOMapping;
import com.rural.Model.FinanceDOTemp;
import com.rural.Model.FinanceHistory;
import com.rural.Model.FinanceMaster;
import com.rural.Model.UserMaster;
import com.rural.Service.FinanceService;
import com.rural.Service.UserService;
import com.rural.exceptions.ERPException;
import com.rural.utility.CommonUtility;

@Controller
public class FinanceController {
	@Autowired
	FinanceService financeService;

	@Autowired
	UserService userService;
	
	static Logger logger =Logger.getLogger(FinanceController.class);
	
	@RequestMapping(value="/completionReport")
	public ModelAndView FinanceHome(ModelMap model,HttpServletRequest request) {
		logger.info("Show FinanceHome Page");
		ModelAndView mav=new ModelAndView("financeAdmin");
		model.addAttribute("finance",new FinanceMaster());
		String statusMsg=null;
		HttpSession session=request.getSession(true);
		String username=null;
		String userRole=null;
		String password=null;
		UserMaster user=null;
		List<FinanceMaster> myAssignedList=null;
		List<FinanceMaster> allRequestsList=null;
		session.removeAttribute("myAssignedList");
		session.removeAttribute("allRequestsList");
		try{
			if(session.getAttribute("username")==null || session.getAttribute("username").equals("null")){
				username=(String) request.getAttribute("username");
				password=(String) request.getAttribute("password");
				user=userService.validateUser(username, password);
				Map<String, String> rolesMap=(Map<String, String>)session.getAttribute("rolesMap");
				userRole=rolesMap.get(user.getRoleId());
			}
			else{
			//get User Roles
				userRole=(String) session.getAttribute("userRole");
			 username=(String) session.getAttribute("username");
			}
			try{
			 myAssignedList=financeService.getMyAssignedRequests(username,userRole);
			} catch (ERPException erp) {
				statusMsg = "Problem Occured While loading Action Needed Items";
			} catch (Exception e) {
				statusMsg = "Problem Occured While loading Action Needed Items";
				}
			try{
			 allRequestsList=financeService.getAllRequests(username,userRole);
			} catch (ERPException erp) {
				statusMsg = "Problem Occured While loading All Finance Requests";
			} catch (Exception e) {
				statusMsg = "Problem Occured While loading All Finance Requests";
				}
		} catch (ERPException erp) {
			statusMsg = "Problem Occured. Please try Again";
		} catch (Exception e) {
			statusMsg = "Problem Occured. Please try Again";
			}
		mav.addObject("statusMsg", statusMsg);
		session.setAttribute("myAssignedList", myAssignedList);
		session.setAttribute("allRequestsList", allRequestsList);
		return mav;
	}

	@RequestMapping(value="/createFinRequest")
	public ModelAndView createFinRequest(ModelMap model,HttpServletRequest request) {
		ModelAndView mav=new ModelAndView("createfinReq");
		model.addAttribute("finance",new FinanceMaster());
		Map<Integer,String> monthMap=null;
		List<Integer> yearList=null;
		String statusMsg=null;
		List<AgencyMaster> agencyMasterList=null;
		HttpSession session=request.getSession(true);
		String username=null;
		String userRole=null;
		String password=null;
		UserMaster user=null;
		try{
			if(session.getAttribute("username")==null || session.getAttribute("username").equals("null")){
				username=(String) request.getAttribute("username");
				password=(String) request.getAttribute("password");
				user=userService.validateUser(username, password);
				Map<String, String> rolesMap=(Map<String, String>)session.getAttribute("rolesMap");
				userRole=rolesMap.get(user.getRoleId());
			}
			else{
			//get User Roles
				userRole=(String) session.getAttribute("userRole");
			 username=(String) session.getAttribute("username");
			}
			monthMap=CommonUtility.getMonthMap();
			yearList=CommonUtility.getYearList();
		 agencyMasterList=financeService.getAgencyList();
		} catch (ERPException erp) {
			statusMsg = "Problem Occured while loading the Agency List";
		} catch (Exception e) {
			statusMsg = "Problem Occured while loading the Agency List";
		}
		mav.addObject("statusMsg", statusMsg);
		mav.addObject("monthMap", monthMap);
		mav.addObject("yearList", yearList);
		mav.addObject("agencyMasterList", agencyMasterList);
		return mav;
	}

	
	@RequestMapping(value= "/createFinanceRequest", method = RequestMethod.POST)
	public ModelAndView createInvoices(@ModelAttribute("finance")FinanceMaster financeMaster,
			HttpServletRequest request, Model model,   BindingResult validationResult) throws IOException, ServletException {
		HttpSession session=null;
		ModelAndView mav=null;
		String statusMsg=null;
		 String DESTINATION_DIR_PATH=null;
		 String userRole=null;
		 Map<Integer,String> monthMap=null;
			List<Integer> yearList=null;
			 List<AgencyMaster> agencyMasterList=null;
		try{
			session=request.getSession();
			userRole=(String) session.getAttribute("userRole");
			mav=new ModelAndView("createfinReq");
		monthMap=CommonUtility.getMonthMap();
		 yearList=CommonUtility.getYearList();
		String monthemp=monthMap.get(Integer.parseInt(financeMaster.getMonth()));
		financeMaster.setMonth(monthemp);
		Part file=request.getPart("excel_file");
		String path=IConstants.FINANCE_WINDOW;
		 agencyMasterList=financeService.getAgencyList();
		 String strFileName=extractFileName(file);
		int historyId= financeService.createFinRequest(financeMaster,userRole,path,strFileName);
		if(historyId!=0)
		{
		statusMsg = "Request created successfully";
			DESTINATION_DIR_PATH = path+historyId+"/";
		 CopyUploadedFiles(file,DESTINATION_DIR_PATH,strFileName,request);
		}
		} catch (ERPException erp) {
			statusMsg = "Problem Occured while Creating the request.";
		} catch (Exception e) {
			statusMsg = "Problem Occured while Creating the request.";
		}
		model.addAttribute("finance",new FinanceMaster());
		mav.addObject("statusMsg", statusMsg);
		mav.addObject("monthMap", monthMap);
		mav.addObject("yearList", yearList);
		mav.addObject("agencyMasterList", agencyMasterList);
		return mav;
	}
	@RequestMapping(value= "/updateFinRequest", method = RequestMethod.POST)
	public ModelAndView updateFinRequest(@RequestParam("finId") int finId,@RequestParam("status") int statusId,@RequestParam("remarks") String strRemarks,
			Model model,HttpServletRequest request) throws IOException, ServletException {
		HttpSession session=null;
		ModelAndView mav=null;
		String statusMsg=null;
		 String DESTINATION_DIR_PATH=null;
			String userRole=null;
			FinanceMaster financeMaster=null;
			List<FinanceHistory> historyList=null;
			 int historyId=0;
			 Part file=null;
			 FinanceMaster finance=null;
		try{
			session=request.getSession();
			 userRole=(String) session.getAttribute("userRole");
			mav=new ModelAndView("viewFinanceReq");
			 file=request.getPart("excel_file");
		String strPath=IConstants.FINANCE_WINDOW;
		
		 String strFileName=extractFileName(file);
		 if(statusId==8){
			 List<FinanceDOMapping> finDOMapping=new ArrayList<FinanceDOMapping>();
			 FinanceDOTemp[] obj=null;
			 FinanceDOMapping doMapping=null;
			 String newList = (String) request.getParameter("do_list_info");
			 String totalAmount = (String) request.getParameter("total_amount");
		//101[{"no_of_kits_received":"2","user":"testprod1","no_of_kits_dispatched_logi":"3","logi_remarks":"23"}]
			 ObjectMapper mapper = new ObjectMapper();
			//JSON from String to Object
			    mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

			     obj =  mapper.readValue(newList, FinanceDOTemp[].class);
			    for(int i=0;i<obj.length;i++){
			    	if(!obj[i].getDoAmount().equals("0")){
			    	doMapping=new FinanceDOMapping();
			    	doMapping.setFinId(finId);
			    	doMapping.setDoNumber(obj[i].getDoNumber());
			    	doMapping.setAmount(obj[i].getDoAmount());
			    	doMapping.setValidFrom(obj[i].getValidFrom());
			    	doMapping.setValidTo(obj[i].getValidTo());
			    	finDOMapping.add(doMapping);
			    	}
			    }
			    historyId= financeService.updateFinRequest(finId,statusId,strRemarks,userRole,strPath,strFileName,totalAmount,finDOMapping);
		 }
		 else
		 {
		 historyId= financeService.updateFinRequest(finId,statusId,strRemarks,userRole,strPath,strFileName);
		 }
		if(historyId!=0)
		{
			statusMsg = "Request updated successfully"; 
			DESTINATION_DIR_PATH = strPath+historyId+"/";
		 CopyUploadedFiles(file,DESTINATION_DIR_PATH,strFileName,request);
		}
		else
		{
			statusMsg = "Problem Occured while updating the Request";
			DESTINATION_DIR_PATH = strPath+historyId+"/";
		 CopyUploadedFiles(file,DESTINATION_DIR_PATH,strFileName,request);
		}
		} catch (ERPException erp) {
			statusMsg = "Problem Occured while updating the Request";
		} catch (Exception e) {
			statusMsg = "Problem Occured while updating the Request";
		}
		
		 financeMaster=new FinanceMaster();
		financeMaster.setFinId(finId);
		try{
			finance=new FinanceMaster();
		 finance=financeService.getRequestDetailsFinance(financeMaster);
		} catch (ERPException erp) {
			statusMsg = "Problem Occured while loading the Request";
		} catch (Exception e) {
			statusMsg = "Problem Occured while loading the Request";
		}
		if(finance != null && finance.getHistory().size() >0){
			historyList=new ArrayList<FinanceHistory>();
			try{
			historyList = finance.getHistory();
			} catch (ERPException erp) {
				statusMsg = "Problem Occured while loading Request History";
			} catch (Exception e) {
				statusMsg = "Problem Occured while loading Request History";
			}
		
	}
		if(statusId==3 || statusId==6 ||  statusId==7 || statusId==8 ||  statusId==11){
			List<FinanceDOMapping> finDOMapping=new ArrayList<FinanceDOMapping>();
			try{
			finDOMapping=financeService.getDOMapping(finId);
			mav.addObject("finDOMappingList",finDOMapping);
			} catch (ERPException erp) {
				statusMsg = "Problem Occured while loading the DO List";
			} catch (Exception e) {
				statusMsg = "Problem Occured while loading the DO List";
			}
			}
		mav.addObject("financeInfo",finance);
		mav.addObject("historyList",historyList);
		mav.addObject("statusMsg", statusMsg);
		return mav;
	}

	public int CopyUploadedFiles(Part partFile,String DESTINATION_DIR_PATH,String strFileName,HttpServletRequest req) {
		 File file = new File(DESTINATION_DIR_PATH);
		 int len=0;
		    if (!file.exists()) {
		    	file.mkdirs();
		    }
		 try {
			 partFile.write(DESTINATION_DIR_PATH + File.separator + strFileName);
		 } catch (IOException io) {
				logger.error("Problem Occured while copying the file"+io.getMessage());
			}
		 catch (Exception e) {
			 logger.error("Problem Occured while copying the file"+e.getMessage());
		}
		 return len;
	}


private String extractFileName(Part part) {
    String contentDisp = part.getHeader("content-disposition");
    String[] items = contentDisp.split(";");
    for (String s : items) {
        if (s.trim().startsWith("filename")) {
            return s.substring(s.indexOf("=") + 2, s.length()-1);
        }
    }
    return "";
	}


@RequestMapping(value="/viewRequest", method = RequestMethod.POST)
public ModelAndView  viewRequestFinance(HttpServletRequest request, HttpServletResponse response){
	FinanceMaster financeMaster=null;
	List<FinanceHistory> historyList=null;
	ModelAndView mav=null;
	String statusMsg=null;
	int finId=(Integer.parseInt(request.getParameter("finId")));
		HttpSession session= request.getSession();
		String userRole=(String) session.getAttribute("userRole");
		mav=new ModelAndView("viewFinanceReq");
		financeMaster=new FinanceMaster();
		financeMaster.setFinId(finId);
		historyList=new ArrayList<FinanceHistory>();
		FinanceMaster finance=null;
		try {
		 finance=financeService.getRequestDetailsFinance(financeMaster);
		if(finance != null && finance.getHistory().size() >0){
			try{
			historyList = finance.getHistory();
			} catch (ERPException erp) {
				statusMsg = "Problem Occured while loading the Request History";
			} catch (Exception e) {
				statusMsg = "Problem Occured while loading the Request History";
			}
		
	}
	}catch (ERPException erp) {
			statusMsg = "Problem Occured while loading the Request";
		} catch (Exception e) {
			statusMsg = "Problem Occured while loading the Request";
		}
	try{
		int statusId=request.getParameter("statusId")==null?0:Integer.parseInt(request.getParameter("statusId"));
		String  isEdit=request.getParameter("isEdit")==null?"":request.getParameter("isEdit");
		if(isEdit.equals("Y")){
			Map<String, String> statusMap=new HashMap<String, String>();
			statusMap=financeService.getStatusMap(finId,userRole,"Finance");
			mav.addObject("statusMapByRole",statusMap);
			}
		
		if(statusId==3 || statusId==6 ||  statusId==7 || statusId==8 ||  statusId==11){
			List<FinanceDOMapping> finDOMapping=new ArrayList<FinanceDOMapping>();
			try{
			finDOMapping=financeService.getDOMapping(finId);
			mav.addObject("finDOMappingList",finDOMapping);
			}catch (ERPException erp) {
				statusMsg = "Problem Occured while loading  DO Details";
			} catch (Exception e) {
				statusMsg = "Problem Occured while loading  DO Details";
			}
			}
		mav.addObject("financeInfo",finance);
		mav.addObject("historyList",historyList);

		
	}
	catch (ERPException erp) {
		erp.printStackTrace();
	} catch (Exception e) {
		e.printStackTrace();
	}
	mav.addObject("statusMsg",statusMsg);
	return mav;
}
@RequestMapping(value = "/download", method = RequestMethod.GET)
public void download(@RequestParam("path") String strPath,HttpServletRequest request, HttpServletResponse response,ModelMap model){
	model.addAttribute("finance",new FinanceMaster());
    File file = new File(strPath);
    response.setContentType("text/csv");
    response.setHeader("Content-Disposition", "attachment;filename=" + file.getName());
    BufferedInputStream inStrem=null;
    BufferedOutputStream outStream=null;

	//Role wise Restricting the user to access the url 1
	int role=(Integer) request.getSession().getAttribute("userRole1");
	if(role!=7 && role!=8 && role!=4) {
	try {
		inStrem = new BufferedInputStream(new FileInputStream(file));
	
     outStream = new BufferedOutputStream(response.getOutputStream());
    
    byte[] buffer = new byte[1024];
    int bytesRead = 0;
    while ((bytesRead = inStrem.read(buffer)) != -1) {
      outStream.write(buffer, 0, bytesRead);
    }
    } catch (FileNotFoundException e) {
		e.printStackTrace();
	}
	catch (Exception e) {
		e.printStackTrace();
	}
	finally{
		try {
			outStream.flush();
			inStrem.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	    
	}
	}else {
		try {
		request.getRequestDispatcher("/views/login.jsp").forward(request, response);
		}catch (Exception e) {
		}
	}
	
    
 }
@RequestMapping(value = "/loadDOList", method = RequestMethod.POST)
public @ResponseBody List<DOMaster> getDOList(@RequestParam("agencyName") String strAgencyName,HttpServletRequest req) {
	List<DOMaster> doList = new ArrayList<DOMaster>();
	try {
		doList = financeService.getDOList(strAgencyName);
	} catch (ERPException erp) {
	} catch (Exception e) {
	}
	return doList;

}
@RequestMapping(value = "/exportToCSVForFinance", method = RequestMethod.POST)
public void exportToCSVForFinance(@RequestParam("name") String name,HttpServletRequest req,
		HttpServletResponse response, Model model) {
	HttpSession session = req.getSession();
	String strFile=null;
	List<FinanceMaster> finalList=null;
	//Role wise Restricting the user to access the url 1
			int role=(Integer) req.getSession().getAttribute("userRole1");
			if(role!=7 && role!=8 && role!=4) {
	try{
	if(name.endsWith("myActionItems")){
		strFile="MyActionItems.csv";
		finalList =(List<FinanceMaster>) session.getAttribute("myAssignedList");
	}else if(name.endsWith("allActionItems"))
	{			
		strFile="AllActionItems.csv";

		finalList =(List<FinanceMaster>) session.getAttribute("allRequestsList");
	}
	downloadCSV(req,response,strFile, finalList);
	
	} catch (ERPException erp) {
	} catch (Exception e) {
	}

			}else {
				try {
				req.getRequestDispatcher("/views/login.jsp").forward(req, response);
				}catch (Exception e) {
				}
			}
			
}



public void downloadCSV(HttpServletRequest req,HttpServletResponse response,String name,List<FinanceMaster> finalList) {
	ServletOutputStream outStream = null;
	try {
	HttpSession session=req.getSession();
		ArrayList<String> rows = new ArrayList<String>();
		Map<Integer, String> agencyMap=new HashMap<Integer, String>();
		Map<Integer, String> statusMap=new HashMap<Integer, String>();
		agencyMap=(Map<Integer, String>)session.getAttribute("agencyMap");
		statusMap=(Map<Integer, String>)session.getAttribute("statusMap");
		rows.add("Req Number,Completion Report,Agency,Month,Year,Date of Request,Status");
				rows.add("\n");

		Iterator<FinanceMaster> iter = finalList.iterator();
		while (iter.hasNext()) {
			FinanceMaster financeMaster = (FinanceMaster) iter.next();
			rows.add(financeMaster.getReqNum() + ","
					+ financeMaster.getCompletionRptDesc() + ","
					+ agencyMap.get(financeMaster.getAgencyId())+ ","
					+ financeMaster.getMonth() + ","
					+ financeMaster.getYear() + ","
					+ financeMaster.getCreatedDate() + ","
					+ statusMap.get(financeMaster.getStatus()));
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
			outStream.flush();
			outStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}



@RequestMapping(value = "/exportToCSVForDoList", method = RequestMethod.GET)
public void exportToCSVForDoList(HttpServletRequest req,
		HttpServletResponse response, Model model) {
	
	HttpSession session = req.getSession();
	List<DOMaster> doList=null;
	//Role wise Restricting the user to access the url 1
			int role=(Integer) req.getSession().getAttribute("userRole1");
			if(role!=7 && role!=8 && role!=4) {

	try{
		doList =(List<DOMaster>) session.getAttribute("doList");
	downloadCSV(req,response, doList);
	
	} catch (ERPException erp) {
	logger.error("Error while Downloading the List of DO Numbers "+erp.getMessage());
	erp.printStackTrace();
	} catch (Exception e) {
	logger.error("Error while Downloading the List of DO Numbers "+e.getMessage());
	e.printStackTrace();
	}
			}else {
				try {
				req.getRequestDispatcher("/views/login.jsp").forward(req, response);
				}catch (Exception e) {
				}
			}
}


public void downloadCSV(HttpServletRequest req,HttpServletResponse response,List<DOMaster> doList) {
	ServletOutputStream outStream = null;
	try {
		ArrayList<String> rows = new ArrayList<String>();
		rows.add("DO Number,Worth,Utilized Amount,Balance Amount,Valid From,Valid To");
				rows.add("\n");

		Iterator<DOMaster> iter = doList.iterator();
		while (iter.hasNext()) {
			DOMaster doMaster = (DOMaster) iter.next();
			rows.add(doMaster.getDoNumber() + ","
					+ doMaster.getDoValue() + ","
					+ (Float.parseFloat(doMaster.getDoValue())-Float.parseFloat(doMaster.getDoBalance()))+ ","
					+ doMaster.getDoBalance() + ","
					+ doMaster.getValidFrom() + ","
					+ doMaster.getValidTo());
			rows.add("\n");
		}
		outStream = response.getOutputStream();
		response.setContentType("text/csv");
		response.setHeader("Content-disposition", "attachment;filename="
				+ "DOList.csv");
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
			outStream.flush();
			outStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}



@RequestMapping(value = "/exportToCSVForCompletionDoList", method = RequestMethod.GET)
public void exportToCSVForCompletionDoList(HttpServletRequest req,
		HttpServletResponse response, Model model) {
	
	HttpSession session = req.getSession();
	//Role wise Restricting the user to access the url 1
	int role=(Integer) req.getSession().getAttribute("userRole1");
	if(role!=7 && role!=8 && role!=4) {
	try{
		@SuppressWarnings("unchecked")
		List<FinanceDOBalance> financeDoList =(List<FinanceDOBalance>) session.getAttribute("financeDoList");
		DOMaster doDetails=(DOMaster)session.getAttribute("doDetails");
		exportToCSVForCompletionDoList(req,response, doDetails,financeDoList);
	
	} catch (ERPException erp) {
	logger.error("Error while Downloading the List of DO Numbers "+erp.getMessage());
	erp.printStackTrace();
	} catch (Exception e) {
	logger.error("Error while Downloading the List of DO Numbers "+e.getMessage());
	e.printStackTrace();
	}

	}else {
		try {
		req.getRequestDispatcher("/views/login.jsp").forward(req, response);
		}catch (Exception e) {
		}
	}
	
}


public void exportToCSVForCompletionDoList(HttpServletRequest req,HttpServletResponse response,DOMaster doDetails,List<FinanceDOBalance> financeDoList) {
	ServletOutputStream outStream = null;
	try {
		ArrayList<String> rows = new ArrayList<String>();
		rows.add("DO Number,DO Value,Utilized Amount,Available Amount,Valid From,Valid To");
				rows.add("\n");
				
				rows.add(doDetails.getDoNumber()+ ","
						+doDetails.getDoValue()+ ","
						+ (Float.parseFloat(doDetails.getDoValue())-Float.parseFloat(doDetails.getDoBalance()))+ ","
						+doDetails.getDoBalance()+","
						+doDetails.getValidFrom()+","
						+doDetails.getValidTo());
				rows.add("\n");
				rows.add("\n");
				rows.add("Completion Report,Agency Name,Utilized Amount");
				rows.add("\n");
		Iterator<FinanceDOBalance> iter = financeDoList.iterator();
		while (iter.hasNext()) {
			FinanceDOBalance doMaster = (FinanceDOBalance) iter.next();
			rows.add(doMaster.getReqNum() + ","
					+ doMaster.getAgencyName() + ","
					+ doMaster.getTotalAmount());
			rows.add("\n");
		}
		outStream = response.getOutputStream();
		response.setContentType("text/csv");
		response.setHeader("Content-disposition", "attachment;filename="
				+ "DOList.csv");
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
			outStream.flush();
			outStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}



@RequestMapping(value="/doDetails")
public ModelAndView  getdoAdminPage(HttpServletRequest request,HttpServletResponse response){
	ModelAndView mav=null;
	String userRole=null;
	Integer userId=null;
	String statusMsg=null;
	//Role wise Restricting the user to access the url 1
	int role=(Integer) request.getSession().getAttribute("userRole1");
	if(role!=7 && role!=8 && role!=4) {
	try{
	mav=new ModelAndView("financeDoAdmin");
	HttpSession session=request.getSession();
	userId=(Integer) session.getAttribute("userId");
	userRole=(String) session.getAttribute("userRole");
	 session.removeAttribute("doList");
	List<DOMaster> doList=financeService.getDOList(userId,userRole);
	 session.setAttribute("doList",doList);
	}catch (ERPException erp) {
		statusMsg = "Problem Occured while loading  DO Details";
	} catch (Exception e) {
		statusMsg = "Problem Occured while loading  DO Details";
	}
	mav.addObject("statusMsg",statusMsg);
	}else {
		try {
		request.getRequestDispatcher("/views/login.jsp").forward(request, response);
		}catch (Exception e) {
		}
	}
	return mav;
}


@RequestMapping(value="/viewDODetails", method = RequestMethod.POST)
public ModelAndView  viewDODetails(@RequestParam("doNumber") String doNumber,HttpServletRequest request, HttpServletResponse response){
	ModelAndView mav=null;
	String statusMsg=null;
	
		HttpSession session= request.getSession();
		String userRole=(String) session.getAttribute("userRole");
		session.getAttribute("financeDoList");
		session.getAttribute("doDetails");
		mav=new ModelAndView("viewDoDetails");
		try {
		List<FinanceDOBalance> financeDoList=financeService.getFinanceDetailsByDO(doNumber);
		session.setAttribute("financeDoList",financeDoList);
	
		}catch (ERPException erp) {
			statusMsg = "Problem Occured while loading  Finance Request";
		} catch (Exception e) {
			statusMsg = "Problem Occured while loading  Finance Request";
		}
		try{
		DOMaster doDetails=financeService.getDODetails(doNumber);
		session.setAttribute("doDetails",doDetails);
		}catch (ERPException erp) {
			statusMsg = "Problem Occured while loading  DO Details";
		} catch (Exception e) {
			statusMsg = "Problem Occured while loading  DO Details";
		}
	
	mav.addObject("statusMsg",statusMsg);
	return mav;
}

@RequestMapping(value= "/uploadDODetails", method = RequestMethod.POST)
public ModelAndView  uploadDODetails(HttpServletRequest request,HttpServletResponse response){
	ModelAndView mav=null;
	String strFileName=null;
	List<DOMasterTemp> doList = new ArrayList<DOMasterTemp>();
	String userRole=null;
	Integer userId=null;
	try{
		HttpSession session= request.getSession();
	mav=new ModelAndView("financeDoAdmin");
	Part file=request.getPart("file_upload");
	strFileName=extractFileName(file);
	String path=IConstants.FINANCE_DO_WINDOW;
	CopyUploadedFiles(file,path,strFileName,request);
	
 convertCSVToList(path+"/"+strFileName,request);
 String status=request.getAttribute("status")==null?"":(String)request.getAttribute("status");
	if(status.equalsIgnoreCase("success")){
		doList=(List<DOMasterTemp>) request.getAttribute("doList");
		List<DOMasterTemp> finaldoList=financeService.insertIntoDoTemp(doList);
		downloadErrorCSV(request,response,finaldoList);
		mav.addObject("status", "All/Few DO Numbers uploaded successfully");
	}
	else{
		mav.addObject("status", status);
	}
	
	userId=(Integer) session.getAttribute("userId");
	userRole=(String) session.getAttribute("userRole");
	//List<FinanceMaster> financeDoList=financeService.getCompletionReportList(userId,userRole);
	List<DOMaster> doMasterList=financeService.getDOList(userId,userRole);
	mav.addObject("doList",doMasterList);
	}catch (ERPException erp) {
		erp.printStackTrace();
	} catch (Exception e) {
		e.printStackTrace();
	}
	
return mav;
}

private void convertCSVToList(String path, HttpServletRequest request) {
	List<DOMasterTemp> doList = new ArrayList<DOMasterTemp>();
	DOMasterTemp doTemp=null;
    String line = null;

    // Read all lines in from CSV file and add to studentList
    FileReader fileReader;
    BufferedReader bufferedReader=null;
    int fixedLen=0;
    String[] headersFixed=new String[10];
    String[] headers=new String[10];
    try {
		fileReader = new FileReader(path);
	
     bufferedReader = new BufferedReader(fileReader);
    
    	 line = bufferedReader.readLine();
    	 headersFixed=new String[]{"DO Numebr","AgencyName","Amount","Valid From","Valid To"};
    	 fixedLen=headersFixed.length;
    	headers=line.split(",");
    	
	} catch (Exception e1) {
		e1.printStackTrace();
	}
     if(!Arrays.equals(headersFixed, headers)){
		 request.setAttribute("status", "Headers are not in Correct format"); 
	 }
     else{

    try {
		while ((line = bufferedReader.readLine()) != null) {
		    String[] temp = line.split(",");
		    if (fixedLen!=temp.length){
		    	 request.setAttribute("status", "Data is not in Correct format"); 
		    	 break;
		    }
		    else if(!CommonUtility.isValidFormat("yyyy-MM-dd", temp[3], Locale.ENGLISH)){
		    	 request.setAttribute("status", "Valid From Date should be in yyyy-MM-dd Format"); 
		    	 break;
		    }
		    else if(!CommonUtility.isValidFormat("yyyy-MM-dd", temp[4], Locale.ENGLISH)){
		    	 request.setAttribute("status","Valid To Date should be in yyyy-MM-dd Format"); 
		    	 break;
		    }
		    else{
		    	request.setAttribute("status", "Success");
		    doTemp=new DOMasterTemp();
		    doTemp.setDoNumber(temp[0]);
		    doTemp.setAgencyName(temp[1]);
		    doTemp.setDoValue(temp[2]);
		    doTemp.setValidFrom(temp[3]);
		    doTemp.setValidTo(temp[4]);
		    doList.add(doTemp);
		    
		    }
		    request.setAttribute("doList", doList);
		}
	
	} catch (NumberFormatException e) {
		e.printStackTrace();
	} catch (IOException e) {
		e.printStackTrace();
	} 
	finally{
    try {
		bufferedReader.close();
	} catch (IOException e) {
		e.printStackTrace();
	}	
	}
     }}

public void downloadErrorCSV(HttpServletRequest req,HttpServletResponse response,List<DOMasterTemp> finaldoList) {
	ServletOutputStream outStream = null;
	String strFileName=null;
	try {
		strFileName="Template.csv";
		ArrayList<String> rows = new ArrayList<String>();
		rows.add("DO Number,Agency Name,Amount,Valid From,Valid To,Status");
				rows.add("\n");

		Iterator<DOMasterTemp> iter = finaldoList.iterator();
		while (iter.hasNext()) {
			DOMasterTemp doTemp = (DOMasterTemp) iter.next();
			rows.add(doTemp.getDoNumber() + ","
					+ doTemp.getAgencyName() + ","
					+ doTemp.getDoValue()+ ","
					+ doTemp.getValidFrom() + ","
					+ doTemp.getValidTo() + ","
					+ doTemp.getError());
			rows.add("\n");
		}
		outStream = response.getOutputStream();
		response.setContentType("text/csv");
		response.setHeader("Content-disposition", "attachment;filename="
				+ strFileName);
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
			outStream.flush();
			outStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}



}
/*
*DeliveryAndInventoryController.java
*Created By		:Bhagya Lakshmi Mula
*Created Date	:Apr 4, 2018
*/
package com.rural.Controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rural.Constant.IConstants;
import com.rural.Model.DeliveryAndInventory;
import com.rural.Model.DeliveryAndInventoryItems;
import com.rural.Model.DeliveryAndInventoryTemp;
import com.rural.Model.UserMaster;
import com.rural.Service.DeliveryAndInventoryService;
import com.rural.Service.UserService;
import com.rural.exceptions.ERPException;

@Controller
public class DeliveryAndInventoryController {
	@Autowired
	DeliveryAndInventoryService deliveryAndInventoryService;
	
	@Autowired
	UserService userService;
	
	static Logger logger =Logger.getLogger(DeliveryAndInventoryController.class);
	
	@RequestMapping(value="/deliveryandinventory")
	public String showAdminMenu(HttpServletRequest request, HttpServletResponse response,ModelMap model){
		logger.info("show delivery and inventory Page");
		HttpSession session=request.getSession(true);
		String username=null;
		String userRole=null;
		String password=null;
		UserMaster user=null;
		String statusMsg=null;
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
		session.removeAttribute("deliveryInventoryList");
		session.removeAttribute("brandList");
		session.removeAttribute("stateList");
		session.removeAttribute("cityList");
		session.removeAttribute("warehouseList");
		session.setAttribute("userRole",userRole);
		List<DeliveryAndInventory> deliveryInventoryList=new ArrayList<DeliveryAndInventory>();
		try{
			 deliveryInventoryList=deliveryAndInventoryService.getAllDeliveryAndInventaries(userRole,username);
		session.setAttribute("deliveryInventoryList", deliveryInventoryList);
		}catch (Exception e) {
			if(deliveryInventoryList.size()!=0){
				statusMsg="Error Occured while loading Delivery And Inventories";
			}
			
		}
		
	if(( ((userRole.equalsIgnoreCase("Admin"))) ||  userRole.equalsIgnoreCase("Business Team")) ){
		
		try{
			session.setAttribute("brandMap",deliveryAndInventoryService.getBrands());
			}catch (Exception e) {
				statusMsg="Error Occured while loading Brands";
			}
		try{
			session.setAttribute("stateList",deliveryAndInventoryService.getStateList());
			}catch (Exception e) {
				statusMsg="Error Occured while loading States";
			}
		
	}
	request.setAttribute("statusMsg", statusMsg);
		return ("createReqInventory");
		
    }
	@RequestMapping(value="/viewInventory")
	public String  viewInventory(HttpServletRequest request, HttpServletResponse response,ModelMap model){
		String statusMsg=null;
		//Role wise Restricting the user to access the url 1
		String strReq_Num=request.getParameter("req_Num");
		int role=(Integer) request.getSession().getAttribute("userRole1");
		if(role!=3 && role!=6 && role!=9) {
		try {
			HttpSession session= request.getSession();
			String userRole=(String) session.getAttribute("userRole");
			String username=(String) session.getAttribute("username");
			/*String collateral=deliveryAndInventoryService.getCollaterals(username,strReq_Num);*/
			session.removeAttribute("userCount");
			List<DeliveryAndInventoryItems> items=new LinkedList<DeliveryAndInventoryItems>();
			/*session.setAttribute("collateral",collateral);*/
			try{
			 items=deliveryAndInventoryService.getDeliveryInventoryItems(username,userRole,strReq_Num);
				request.setAttribute("deliveryAndInventoryItems",items);
		}catch (Exception e) {
			statusMsg="Error Occured while loading Delivery And Inventory Items";
		}
	
			Iterator<DeliveryAndInventoryItems> loginUsers=items.iterator();
			int userCount=0;
			while(loginUsers.hasNext()){
				DeliveryAndInventoryItems del=(DeliveryAndInventoryItems)loginUsers.next();
				if(del.getTeam().equals("Logistics")){
					userCount++;
				}
				
			}
			session.setAttribute("userCount",userCount);
			try{
			request.setAttribute("deliveryAndInventory",deliveryAndInventoryService.getDeliveryInventoryDetails(strReq_Num));
	
			}catch (Exception e) {
				statusMsg="Error Occured while loading Delivery And Inventory Headers";
			}
			/*if(userRole.equals("Admin") ||  userRole.equals("Logistics")){
				List<DeliveryAndInventoryProductionUser> prodUsersList=new ArrayList<DeliveryAndInventoryProductionUser>();
				prodUsersList=deliveryAndInventoryService.getUsersForLogistics(strReq_Num);
				System.out.println("prodUsersList size "+prodUsersList.size());
				request.setAttribute("prodUsersList",prodUsersList);
			}*/
		}
		catch (ERPException erp) {
		} catch (Exception e) {
		}
		request.setAttribute("statusMsg", statusMsg);
		}else {
			try {
			request.getRequestDispatcher("/views/login.jsp").forward(request, response);
			}catch (Exception e) {
			}
		}
		return "viewDeliveryInventory";
    }
@RequestMapping(value = "/loadCities", method = RequestMethod.POST)
public @ResponseBody List<String> loadCities(
		@RequestParam("state") String strState, HttpServletRequest req,
		Model model) {
	List<String> cityList = new ArrayList<String>();
	try {
		cityList = deliveryAndInventoryService.getCityList(strState);
		req.getSession().setAttribute("cityList",cityList);
		//req.setAttribute("selected_state", strState);
		if (req.getParameter("brand") != null) {
			req.setAttribute("selected_brand", req.getParameter("brand"));

		}
	} catch (ERPException erp) {
	} catch (Exception e) {
	}
	return cityList;

}

@RequestMapping(value = "/getDate", method = RequestMethod.POST)
public @ResponseBody void getDate(@RequestParam("reqNo") String reqNo, HttpServletRequest req,HttpServletResponse response,
		Model model) {
	//Role wise Restricting the user to access the url 1
	int role=(Integer) req.getSession().getAttribute("userRole1");
	if(role!=3 && role!=6 && role!=9) {
	try {
		HttpSession session=req.getSession();
		session.removeAttribute("date");
		logger.debug(" date::: "+ deliveryAndInventoryService.getEstimatedDeliveryDate(reqNo));
		req.getSession().setAttribute("date", deliveryAndInventoryService.getEstimatedDeliveryDate(reqNo));
	} catch (ERPException erp) {
	} catch (Exception e) {
	}
	
	}else {
		try {
		req.getRequestDispatcher("/views/login.jsp").forward(req, response);
		}catch (Exception e) {
		}
	}
	return ;

}
	@RequestMapping(value = "/loadWarehouses", method = RequestMethod.POST)
	public @ResponseBody List<String> getCity(@RequestParam("city") String strCity, HttpServletRequest req,HttpServletResponse response,
			Model model) {
		List<String> listWarehouse = new ArrayList<String>();
		//Role wise Restricting the user to access the url 1
		int role=(Integer) req.getSession().getAttribute("userRole1");
		if(role!=3 && role!=6 && role!=9) {
		try {
			listWarehouse = deliveryAndInventoryService.getWarehouseList(strCity);
			req.getSession().setAttribute("warehouseList", listWarehouse);
			req.setAttribute("selected_city", strCity);
			if (req.getParameter("brand") != null) {
				req.setAttribute("selected_brand", req.getParameter("brand"));

			}
		} catch (ERPException erp) {
		} catch (Exception e) {
		}
		}else {
			try {
			req.getRequestDispatcher("/views/login.jsp").forward(req, response);
			}catch (Exception e) {
			}
		}
		return listWarehouse;

	} 
	@RequestMapping(value = "/createDeliveryAndInventory", method = RequestMethod.POST)
	public  String createDeliveryAndInventory(
			@RequestParam("brand") String strBrand, @RequestParam("campaign") String strCampaign,@RequestParam("state") String strState,
			@RequestParam("city") String strCity,@RequestParam("warehouse") String strWarehouse,@RequestParam("no_of_kits") int no_of_kits,
			@RequestParam("remarks") String strRemarks,HttpServletRequest req,HttpServletResponse response,
			Model model) {
		List<DeliveryAndInventory> deliveryInventoryList = null;
		DeliveryAndInventory deliveryAndInventory=null;
		String statusMsg = null;
		HttpSession session=req.getSession();
		//Role wise Restricting the user to access the url 1
		int role=(Integer) req.getSession().getAttribute("userRole1");
		if(role!=3 && role!=6 && role!=9) {
		try {
			deliveryAndInventory=new DeliveryAndInventory();
			deliveryAndInventory.setBrand(strBrand);
			deliveryAndInventory.setCampaign(strCampaign);
			deliveryAndInventory.setState(strState);
			deliveryAndInventory.setCity(strCity);
			deliveryAndInventory.setWarehouse(strWarehouse);
		/*	deliveryAndInventor.setNo_Item_Req(no_of_kits);
			deliveryAndInventor.setBusiness_Remarks(strRemarks);*/
			deliveryInventoryList=new ArrayList<DeliveryAndInventory>();
			deliveryInventoryList = deliveryAndInventoryService.createDeliveryAndInventory(deliveryAndInventory,no_of_kits,strRemarks);
			
			if (deliveryInventoryList.size() != 0) {
				statusMsg = "Request Created Successfully";
			} else {
				statusMsg = "Problem in Creating Request.";
			}
			session.removeAttribute("deliveryInventoryList");
			session.setAttribute("deliveryInventoryList",deliveryInventoryList);
			req.setAttribute("selected_brand", deliveryAndInventory.getBrand());
			req.setAttribute("selected_compaign", deliveryAndInventory.getCampaign());
			//req.setAttribute("selected_state", deliveryAndInventory.getState());
			req.setAttribute("selected_city", deliveryAndInventory.getCity());
			req.setAttribute("selected_warehouse", deliveryAndInventory.getWarehouse());
		/*	req.setAttribute("selected_no_kits", deliveryAndInventor.getNo_Item_Req());
			req.setAttribute("selected_remarks", deliveryAndInventor.getBusiness_Remarks());*/
		} catch (ERPException erp) {
			statusMsg = "Problem Occured. Please try Again";
		} catch (Exception e) {
		}
		req.setAttribute("statusMsg", statusMsg);
		}else {
			try {
			req.getRequestDispatcher("/views/login.jsp").forward(req, response);
			}catch (Exception e) {
			}
		}
		
		return "createReqInventory";

	} 
	
	@RequestMapping(value = "/updateStatus", method = RequestMethod.POST)
	public String  updateStatus(
			@RequestParam("reqNo") String strReqNo,@RequestParam("status") String strStatus, @RequestParam("user") String strUser,HttpServletRequest req,
			HttpServletResponse response,Model model) {
		String statusMsg = null;
		HttpSession session=null;
		//Role wise Restricting the user to access the url 1
		int role=(Integer) req.getSession().getAttribute("userRole1");
		if(role!=3 && role!=6 && role!=9) {
		try {
			session=req.getSession();
			String userRole=(String)session.getAttribute("userRole");
			String username=(String) session.getAttribute("username");
			//String StrProduser=req.getParameter("prod_user")==null?"":req.getParameter("prod_user");
			String tempStatus=null;
			String reachedLogi=null;

			if((userRole.equals("Admin") ||  userRole.equals("Logistics"))  && (strStatus.equals(IConstants.BUSINESS_SUBMITTED) || strStatus.equals(IConstants.PRODUCTION_ACK) || strStatus.equals(IConstants.PRODUCTION_COMPLETED))){
				 tempStatus=(String)req.getParameter("tempStatus")==null?"":req.getParameter("tempStatus");
				 reachedLogi=req.getParameter("reachedLogi")==null?"":req.getParameter("reachedLogi");
				// StrProduser=req.getParameter("prod_user")==null?"":req.getParameter("prod_user");
/*				if(ProductionStatus.equals(IConstants.))
*/			}
			
			List<DeliveryAndInventoryItems> items = deliveryAndInventoryService.updateStatus(username,userRole,strStatus,strReqNo,strUser,reachedLogi,tempStatus);
			req.setAttribute("deliveryAndInventoryItems", items);
			statusMsg = "Request Acknowledged successfully";
			} catch (ERPException erp) {
			statusMsg = "Problem Occured while Acknowledging the request. Please try Again";
		} catch (Exception e) {
			statusMsg = "Problem Occured while Acknowledging the request. Please try Again";
		}
		req.setAttribute("deliveryAndInventory",deliveryAndInventoryService.getDeliveryInventoryDetails(strReqNo));
		req.setAttribute("statusMsg", statusMsg);

		}else {
			try {
			req.getRequestDispatcher("/views/login.jsp").forward(req, response);
			}catch (Exception e) {
			}
		}
		return "viewDeliveryInventory";

	} 
	
	@RequestMapping(value = "/cancelInventories", method = RequestMethod.GET)
	public String  cancelInventories() {
		return "viewDeliveryInventory";
	} 
	
	@RequestMapping(value = "/updateInventories", method = RequestMethod.POST)
	public String  updateInventories(@RequestParam("req_Id") String strReqId,@RequestParam("req_num") String strReqNo,@RequestParam("status") String strStatus,HttpServletRequest req) 
	{
		DeliveryAndInventoryItems deliveryAndInventoryItems = null;
		String statusMsg = null;
		HttpSession session=req.getSession();
		String tempStatus=null;
		String reachedLogi=null;
		String tempTeam=null;
		 String DESTINATION_DIR_PATH=null;
		 DeliveryAndInventoryTemp[] obj=null;
		try {
			String userRole=(String)session.getAttribute("userRole");
			String username=(String) session.getAttribute("username");
			if(userRole.equals("Logistics") || userRole.equals("Admin")){
			String newList = (String) req.getParameter("product_list_info");
		//da  aaaaa 101[{"no_of_kits_received":"2","user":"testprod1","no_of_kits_dispatched_logi":"3","logi_remarks":"23"}]hello
			 ObjectMapper mapper = new ObjectMapper();
			//JSON from String to Object
			    mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

			     obj =  mapper.readValue(newList, DeliveryAndInventoryTemp[].class);
			   /* for(int i=0;i<obj.length;i++){
			    	System.out.println(" getNo_of_kits_received "+obj[i].getNo_of_kits_received());
			    	System.out.println(" user "+obj[i].getUser());
			    	System.out.println(" refc "+obj[i].getLogi_remarks());

			    }*/
			}
			//JSONObject obj=new JSONObject(newList);
			deliveryAndInventoryItems = new DeliveryAndInventoryItems();
			deliveryAndInventoryItems.setReq_Id(strReqId);
			deliveryAndInventoryItems.setStatus(strStatus);
			tempTeam=req.getParameter("tempTeam")==null?"":req.getParameter("tempTeam");
			 tempStatus=(String)req.getParameter("tempStatus")==null?"":req.getParameter("tempStatus");

			if((userRole.equals("Admin") ||  userRole.equals("Logistics")) &&  tempTeam.equals("Logistics") && (strStatus.equals(IConstants.BUSINESS_SUBMITTED) || strStatus.equals(IConstants.PRODUCTION_ACK) || strStatus.equals(IConstants.PRODUCTION_COMPLETED))){
				String strUser=req.getParameter("prod_user")==null?"0":req.getParameter("prod_user");
				 reachedLogi=req.getParameter("reachedLogi")==null?"0":req.getParameter("reachedLogi");
					deliveryAndInventoryItems.setItem_Received_logi(req.getParameter("no_of_kits_received"));
					if(req.getParameter("no_of_kits_dispatched_logi")==null || req.getParameter("no_of_kits_dispatched_logi").equals("null") || req.getParameter("no_of_kits_dispatched_logi").equals("") ){
						deliveryAndInventoryItems.setItem_Dispatch_logi("");
						
					}
					else{
						deliveryAndInventoryItems.setItem_Dispatch_logi(req.getParameter("no_of_kits_dispatched_logi"));
					}
					if(req.getParameter("logi_remarks")==null || req.getParameter("logi_remarks").equals("null") || req.getParameter("logi_remarks").equals("") ){
						deliveryAndInventoryItems.setLogist_Remarks_dispatched("");
						
					}
					else{
						deliveryAndInventoryItems.setLogist_Remarks_dispatched(req.getParameter("logi_remarks"));
					}

				 //deliveryAndInventoryItems.setTeam(team);
				deliveryAndInventoryItems.setReachedLogi(reachedLogi);
				deliveryAndInventoryItems.setProd_users(strUser);
				
/*				if(ProductionStatus.equals(IConstants.))
*/			}
			else{
				deliveryAndInventoryItems.setReachedLogi("0");

			}
			
			if((strStatus.equals(IConstants.PRODUCTION_ACK) || strStatus.equals(IConstants.BUSINESS_SUBMITTED)) &&  !tempTeam.equals("Logistics")){
				if(req.getParameter("no_of_kits_produced")==null || req.getParameter("no_of_kits_produced").equals("null") || req.getParameter("no_of_kits_produced").equals("")){
					
					deliveryAndInventoryItems.setItem_Produced("");
					}else{
				deliveryAndInventoryItems.setItem_Produced(req.getParameter("no_of_kits_produced"));
					}
				deliveryAndInventoryItems.setProd_Remarks_produced(req.getParameter("prod_remarks"));
				if(req.getParameter("no_of_kits_dispached")==null || req.getParameter("no_of_kits_dispached").equals("null") || req.getParameter("no_of_kits_dispached").equals("")){
					deliveryAndInventoryItems.setItem_Dispatch_Prod("");
				}
				else{
					deliveryAndInventoryItems.setItem_Dispatch_Prod(req.getParameter("no_of_kits_dispached"));
				}
				
			}
			if(strStatus.equals(IConstants.PRODUCTION_COMPLETED)){
				deliveryAndInventoryItems.setProd_Remarks_dispatched(req.getParameter("prod_remarks"));
				
				deliveryAndInventoryItems.setItem_Dispatch_Prod(req.getParameter("no_of_kits_dispached"));
			}
			if(strStatus.equals(IConstants.LOGISTICS_ACK) || strStatus.equals(IConstants.PRODUCTION_SUBMITTED)){
				deliveryAndInventoryItems.setItem_Received_logi(req.getParameter("no_of_kits_received"));
				deliveryAndInventoryItems.setLogist_Remarks_received(req.getParameter("logi_remarks"));
				if(req.getParameter("no_of_kits_dispatched_logi")==null || req.getParameter("no_of_kits_dispatched_logi").equals("null") || req.getParameter("no_of_kits_dispatched_logi").equals("") ){
					deliveryAndInventoryItems.setItem_Dispatch_logi("");
					
				}
				else{
					Part partDispatch1=req.getPart("img_dispatch1");
					Part partDispatch2=req.getPart("img_dispatch2");
					String path=IConstants.DELIVERYINVENTORY_WINDOW;
					//copyPartToFile(strReqNo,partDispatch1,path);
					  DESTINATION_DIR_PATH = path+strReqNo+"/"+"Logistics";
				//deliveryAndInventoryService.CopyUploadedFiles(strReqNo,partDispatch1,partDispatch2,DESTINATION_DIR_PATH);
					int lenLogFiles= CopyUploadedFiles(strReqNo,partDispatch1,partDispatch2,DESTINATION_DIR_PATH,req);
					session.removeAttribute("lenLogFiles");
					session.setAttribute("lenLogFiles",lenLogFiles);
					deliveryAndInventoryItems.setItem_Dispatch_logi(req.getParameter("no_of_kits_dispatched_logi"));
					//deliveryAndInventoryItems.setPath_dispatch(DESTINATION_DIR_PATH);
				}
				
			}
			if(strStatus.equals(IConstants.LOGISTICS_COMPLETED)){
				deliveryAndInventoryItems.setLogist_Remarks_dispatched(req.getParameter("logi_remarks"));
				//MultipartFile file= req.getParameter("file");
								Part partDispatch1=req.getPart("img_dispatch1");
				Part partDispatch2=req.getPart("img_dispatch2");
				String path=IConstants.DELIVERYINVENTORY_WINDOW;
				
				  DESTINATION_DIR_PATH = path+strReqNo+"/"+"Logistics";
				//deliveryAndInventoryService.CopyUploadedFiles(strReqNo,partDispatch1,partDispatch2,DESTINATION_DIR_PATH);
				 int lenLogFiles=CopyUploadedFiles(strReqNo,partDispatch1,partDispatch2,DESTINATION_DIR_PATH,req);
				 session.removeAttribute("lenLogFiles");
					session.setAttribute("lenLogFiles",lenLogFiles);
					deliveryAndInventoryItems.setItem_Dispatch_logi(req.getParameter("no_of_kits_dispatched_logi"));
					
					//deliveryAndInventoryItems.setPath_dispatch(DESTINATION_DIR_PATH);
				
			}
			if(strStatus.equals(IConstants.VENDOR_ACK)){
				deliveryAndInventoryItems.setVendor_Remarks(req.getParameter("vendor_remarks"));
					Part partDispatch1=req.getPart("img_delivery1");
					Part partDispatch2=req.getPart("img_delivery2");
					String path=IConstants.DELIVERYINVENTORY_WINDOW;
					  DESTINATION_DIR_PATH = path+strReqNo+"/"+"Vendor";
				//	deliveryAndInventoryService.CopyUploadedFiles(strReqNo,partDispatch1,partDispatch2,DESTINATION_DIR_PATH);
					int lenVendorFiles= CopyUploadedFiles(strReqNo,partDispatch1,partDispatch2,DESTINATION_DIR_PATH,req);
					 session.removeAttribute("lenvenFiles");
						session.setAttribute("lenvenFiles",lenVendorFiles);
					 deliveryAndInventoryItems.setItem_Reveived_Vend(req.getParameter("no_of_kits_received_vend"));
					// deliveryAndInventoryItems.setPath_delivery(DESTINATION_DIR_PATH);
				
			}
			/*if(strStatus.equals(IConstants.LOGISTICS_ACK) || strStatus.equals(IConstants.LOGISTICS_COMPLETED) || strStatus.equals(IConstants.LOGISTICS_SUBMITTED) ){
				String strUser=req.getParameter("user")==null?"":req.getParameter("user");
							String StrProduser=req.getParameter("prod_user")==null?"":req.getParameter("prod_user");

				deliveryAndInventoryItems.setProd_users(strUser);
			}else{
			deliveryAndInventoryItems.setProd_users(username);
			}*/
			
			if(!strStatus.equals(IConstants.VENDOR_ACK)){
			String strUser=req.getParameter("prod_user")==null?"":req.getParameter("prod_user");
			deliveryAndInventoryItems.setProd_users(strUser);
			}
			List<DeliveryAndInventoryItems> items = deliveryAndInventoryService.updateInventories(deliveryAndInventoryItems,userRole,tempStatus,DESTINATION_DIR_PATH,obj);
			
			req.setAttribute("deliveryAndInventoryItems", items);
			req.setAttribute("deliveryAndInventory",deliveryAndInventoryService.getDeliveryInventoryDetails(strReqNo));
			statusMsg = "Entered Values saved successfully";
			} catch (ERPException erp) {
			statusMsg = "Problem Occured while updating the values. Please try Again";
		} catch (Exception e) {
		}
		req.setAttribute("statusMsg", statusMsg);
		return "viewDeliveryInventory";
		} 
	public int CopyUploadedFiles(String strReqNo, Part partDispatch1,
			Part partDispatch2,String DESTINATION_DIR_PATH,HttpServletRequest req) {
		// TODO Auto-generated method stub
		 File file = new File(DESTINATION_DIR_PATH);
		 int len=0;
		    boolean folderCreate = false;
		    if (!file.exists()) {
		    	folderCreate = file.mkdirs();
		    }
		    /*if (folderCreate){
		      System.out.println("Directory successfully created");
		    }
		    else{
		      System.out.println("Directoy directory");
		    }*/
		
		 String fileName1=extractFileName(partDispatch1);
		 try {
			partDispatch1.write(DESTINATION_DIR_PATH + File.separator + fileName1);
			
		 String fileName2=extractFileName(partDispatch2);
		
		 partDispatch2.write(DESTINATION_DIR_PATH + File.separator + fileName2);
		 len=file.listFiles().length;
		 } catch (IOException e) {
				e.printStackTrace();
			}
		/* finally{
			 System.out.println("file ************part2******************** "+file.length());
		 }*/
		 return len;
	}
	
	
	// file name of the upload file is included in content-disposition header like this:
			//form-data; name="dataFile"; filename="PHOTO.JPG"
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

	@RequestMapping(value = "/exportDeliveryAndInventories", method = RequestMethod.GET)
	public void exportSavedPrePlan(HttpServletRequest request,HttpServletResponse response, Model model) {
		//ModelAndView modelandview = new ModelAndView("showPrePlanning");
		String strFile ="DeliveryAndInventrories.csv";
		//Role wise Restricting the user to access the url 1
		int role=(Integer) request.getSession().getAttribute("userRole1");
		if(role!=3 && role!=6 && role!=9) {
		
		HttpSession session=request.getSession(true);
		String userRole=(String) session.getAttribute("userRole");
		List<DeliveryAndInventory> listDeliveryAndInventory=(List<DeliveryAndInventory>)session.getAttribute("deliveryInventoryList");
		downloadCSVSavedPrePlan(response, listDeliveryAndInventory,strFile);
		//return modelandview;
		}else {
			try {
			request.getRequestDispatcher("/views/login.jsp").forward(request, response);
			}catch (Exception e) {
			}
		}
	}
	
	public void downloadCSVSavedPrePlan(HttpServletResponse response, List<DeliveryAndInventory> listDeliveryAndInventory,String strFile) {
		ServletOutputStream outStream = null;
		try {
			
			ArrayList<String> rows = new ArrayList<String>();
			rows.add("Req_Num,Brand,Campaign,City,Warehouse,Date of Request,Status");
			rows.add("\n");

			Iterator<DeliveryAndInventory> iter = listDeliveryAndInventory.iterator();
			while (iter.hasNext()) {
				DeliveryAndInventory deliveryAndInventory = (DeliveryAndInventory) iter.next();
				rows.add(deliveryAndInventory.getReq_num() + ","+deliveryAndInventory.getBrand()+","+deliveryAndInventory.getCampaign()+","+
						deliveryAndInventory.getCity()+","+deliveryAndInventory.getWarehouse()+","+
						deliveryAndInventory.getBusiness_Req_Date()+","+deliveryAndInventory.getStatus());

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

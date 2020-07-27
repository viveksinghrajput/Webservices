/*
*WareHouseController.java
*Created By		:Kamal Thappa
*Created Date	:Jul 4, 2018
*/
package com.rural.Controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
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
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.rural.Model.Photo;
import com.rural.Model.UserMaster;
import com.rural.Model.VendorWareHouseMaster;
import com.rural.Model.WareHouseHistory;
import com.rural.Service.WarehouseService;

@Controller
public class WareHouseController {
	
	@Autowired
	WarehouseService wareHouseService;

	static Logger logger =Logger.getLogger(WareHouseController.class);
	@RequestMapping(value="/warehouse")
	public ModelAndView showWareHouseMain(HttpServletRequest request, HttpServletResponse response,ModelMap model){
		
		HttpSession session=request.getSession(true);
		ModelAndView mav;
		String statusMsg=null;
		//get User Roles
		String userRole=(String) session.getAttribute("userRole");
		String username=(String) session.getAttribute("username");
		statusMsg=(String)session.getAttribute("status");
		session.removeAttribute("status");
		if( ((userRole.equalsIgnoreCase("Admin"))) || ((userRole.equalsIgnoreCase("Audit"))) || ((userRole.equalsIgnoreCase("Business Team"))) || ((userRole.equalsIgnoreCase("Agency"))))
		{
			List<VendorWareHouseMaster> itemList=new ArrayList<VendorWareHouseMaster>();
			Map<String,List<Photo>> photos=new HashMap<String,List<Photo>>();
			mav=new ModelAndView("adminWarehouse");
			//getAllWareHouses
			try{
			 itemList=wareHouseService.listAll();
			Iterator<VendorWareHouseMaster> itr=itemList.iterator();
			List<Photo> photoList=null;
			VendorWareHouseMaster warehouse=null;
			while(itr.hasNext())
			{
				photoList=new ArrayList<Photo>();
				warehouse=(VendorWareHouseMaster)itr.next();
				photoList=warehouse.getPhotos();
				photos.put(warehouse.getTrackId(),photoList);
			}
			}catch (Exception e) {
				statusMsg="Error Occured while loading Warehouselist";
			}
			mav.addObject("wareHouseList", itemList);
			request.setAttribute("photoList", photos);
			//Setting select Option
			mav.addObject("selectStatus", "All");
			mav.addObject("statusMsg", statusMsg);
			return mav;
			
		}else if((userRole.equalsIgnoreCase("Vendor")) ){
			mav=new ModelAndView("adminWarehouse");
			//Get Vendor Specific Warehouse List
			String userId=Integer.toString((Integer)session.getAttribute("userId"));
			try{
			List<VendorWareHouseMaster> itemList=wareHouseService.listMyWareHouse(userId);
			Iterator<VendorWareHouseMaster> itr=itemList.iterator();
			List<Photo> photoList=null;
			Map<String,List<Photo>> photos=new HashMap<String,List<Photo>>();
			VendorWareHouseMaster warehouse=null;
			while(itr.hasNext())
			{
				photoList=new ArrayList<Photo>();
				warehouse=(VendorWareHouseMaster)itr.next();
				photoList=warehouse.getPhotos();
				photos.put(warehouse.getTrackId(),photoList);
			}
			request.setAttribute("photoList", photos);
			mav.addObject("wareHouseList", itemList);
			}catch (Exception e) {
				statusMsg="Error Occured while loading Warehouselist";
			}
			//Setting select Option
			mav.addObject("statusMsg", statusMsg);
			mav.addObject("selectStatus", "All");
			return mav;
			
		}else{
			mav=new ModelAndView("notAuthorized");
			mav.addObject("statusMsg", "Problem Occured . Please try Again");
			return mav;
		}
    }
	
	@RequestMapping(value="/editwarehouse",method = {RequestMethod.POST,RequestMethod.GET})
	public ModelAndView showEditWarehouse(@RequestParam("id") String strId,
			@RequestParam(required = false, defaultValue = "All", value="sort") String sorting,
			HttpServletRequest request, HttpServletResponse response,ModelMap model)
	{
		//Get warehouse Id from request
		HttpSession session=request.getSession(true);
		String userRole=(String) session.getAttribute("userRole");
		String username=(String) session.getAttribute("username");
		ModelAndView mav;
		String statusMsg=null;
		VendorWareHouseMaster item=null;
		UserMaster u=null;
		statusMsg=(String)session.getAttribute("strStatus");
		session.removeAttribute("strStatus");

		//Role wise Restricting the user to access the url 1
		int role=(Integer) request.getSession().getAttribute("userRole1");
		if(role!=7 && role!=8 && role!=9) {
		if( ((userRole.equalsIgnoreCase("Admin"))) || ((userRole.equalsIgnoreCase("Audit"))) || ((userRole.equalsIgnoreCase("Business Team"))) || ((userRole.equalsIgnoreCase("Agency"))))
		{
			mav=new ModelAndView("editUpdateWareHouse");
			try{
			 item=wareHouseService.getWarehouse(strId);
			}catch (Exception e) {
				statusMsg="Error Occured while loading Warehouselist";
			}
			if(item==null)
			{
				mav=new ModelAndView("editUpdateWareHouse");
				statusMsg="No Warehouse Found.";
				mav.addObject("noWarehouse", "No Warehouse Found.");
				return mav;
			}else{
				List<WareHouseHistory> itemList=new ArrayList<WareHouseHistory>();
				try{
				 u=wareHouseService.getNameById(Integer.parseInt(item.getUserId()));
				}catch (Exception e) {
					statusMsg="Error Occured while loading User";
				}
				String owner=u.getUsername();
			mav.addObject("warehouse", item);
			try{
			 itemList=wareHouseService.getWareHouseHistory(item.getName(),sorting);
			}catch (Exception e) {
				statusMsg="Error Occured while loading Warehosue History";
			}
			mav.addObject("itemList", itemList);
			mav.addObject("strWareHouseName", item.getName());
			//Setting select Option
			mav.addObject("selectStatus", sorting);
			try{
			List<Photo> photoList=wareHouseService.getPhotoDetails(strId);
			mav.addObject("photoList", photoList);
			}catch (Exception e) {
				statusMsg="Error Occured while loading Warehosue Imagess";
			}
			mav.addObject("strStatus", statusMsg);
			mav.addObject("id", strId);
			mav.addObject("owner", owner);
			return mav;
			}
			
		}else if(userRole.equalsIgnoreCase("Vendor"))
		{
			//Validate Username/ Owner
			try{
			 item=wareHouseService.getWarehouse(strId);
			}catch (Exception e) {
				statusMsg="Error Occured while loading Warehosue";
			}
			if(item!=null)
			{
			try{
				 u=wareHouseService.getNameById(Integer.parseInt(item.getUserId()));
			}catch (Exception e) {
				statusMsg="Error Occured while loading User";
			}
			
			}else {
				try {
				request.getRequestDispatcher("/views/login.jsp").forward(request, response);
				}catch (Exception e) {
				}
			}
				String owner=u.getUsername();
				if(owner.equalsIgnoreCase(username)){
					mav=new ModelAndView("editUpdateWareHouse");
					mav.addObject("warehouse", item);
					try{
					mav.addObject("itemList", wareHouseService.getWareHouseHistory(item.getName(),sorting));
					}catch (Exception e) {
						statusMsg="Error Occured while Warehouse History";
					}
					mav.addObject("strStatus", statusMsg);
					mav.addObject("strWareHouseName", item.getName());
					mav.addObject("selectStatus", sorting);
					mav.addObject("id", strId);
					mav.addObject("owner", owner);
					return mav;
				}else{
					mav=new ModelAndView("notAuthorized");
					mav.addObject("strStatus", "Problem Occured. Please try Again.");
					return mav;
				}
			}else{
				mav=new ModelAndView("editUpdateWareHouse");
				statusMsg="No Warehouse Found";
				mav.addObject("strStatus", statusMsg);
				mav.addObject("noWarehouse", "No Warehouse Found.");
				return mav;
			}
		}else{
			mav=new ModelAndView("notAuthorized");
			return mav;
		}
		
		
    }
	
	@RequestMapping(value="/showPendingWareHouses")
	public ModelAndView showPendingWareHouses(HttpServletRequest request, HttpServletResponse response,ModelMap model){
		HttpSession session=request.getSession(true);
		ModelAndView mav;
		//get User Roles
		String userRole=(String) session.getAttribute("userRole");
		String username=(String) session.getAttribute("username");
		String statusMsg=null;
		
		if( ((userRole.equalsIgnoreCase("Admin"))) || ((userRole.equalsIgnoreCase("Audit"))) || ((userRole.equalsIgnoreCase("Business Team"))) || ((userRole.equalsIgnoreCase("Agency"))))
		{
			mav=new ModelAndView("adminWarehouse");
			try{
			//getPendingWareHouses
			List<VendorWareHouseMaster> itemList=wareHouseService.getPendingWarehouse();
			mav.addObject("wareHouseList", itemList);
			}catch (Exception e) {
				statusMsg="Error Occured while loading Pending Warehouse List";
			}
			//Setting select Option
			mav.addObject("selectStatus", "Submitted");
			mav.addObject("statusMsg",statusMsg);
			return mav;
			
		}
		else if((userRole.equalsIgnoreCase("Vendor")) ){
			mav=new ModelAndView("adminWarehouse");
			//Get Vendor Specific Warehouse List
			try{
			List<VendorWareHouseMaster> itemList=wareHouseService.listMyPendingWareHouse(username);
			mav.addObject("wareHouseList", itemList);
			}catch (Exception e) {
				statusMsg="Error Occured while loading Pending Warehouse List";
			}
			//Setting select Option
			mav.addObject("selectStatus", "Submitted");
			mav.addObject("statusMsg",statusMsg);
			return mav;
		}
		else{
			mav=new ModelAndView("notAuthorized");
			mav.addObject("statusMsg", "Problem Occured. Please try Again.");
			return mav;
			
		}
	
	}
	
	@RequestMapping(value="/showRejectedWareHouses")
	public ModelAndView showRejectedWareHouses(HttpServletRequest request, HttpServletResponse response,ModelMap model){
		HttpSession session=request.getSession(true);
		ModelAndView mav;
		//get User Roles
		String userRole=(String) session.getAttribute("userRole");
		String username=(String) session.getAttribute("username");
		String statusMsg=null;
		
		if( ((userRole.equalsIgnoreCase("Admin"))) || ((userRole.equalsIgnoreCase("Audit"))) || ((userRole.equalsIgnoreCase("Business Team"))) || ((userRole.equalsIgnoreCase("Agency"))))
		{
			mav=new ModelAndView("adminWarehouse");
			try{
			//getAllWareHouses
			List<VendorWareHouseMaster> itemList=wareHouseService.getRejectedWarehouse();
			mav.addObject("wareHouseList", itemList);
			}catch (Exception e) {
				statusMsg="Error Occured while loading Rejected Warehouse List";
			}
			//Setting select Option
			mav.addObject("selectStatus", "Rejected");
			mav.addObject("statusMsg",statusMsg);
			return mav;
		}
		else if((userRole.equalsIgnoreCase("Vendor")) ){
			mav=new ModelAndView("adminWarehouse");
			try{
			//Get Vendor Specific Warehouse List
			List<VendorWareHouseMaster> itemList=wareHouseService.listMyRejectedWareHouse(username);
			mav.addObject("wareHouseList", itemList);
			}catch (Exception e) {
				statusMsg="Error Occured while loading Rejected Warehouse List";
			}
			//Setting select Option
			mav.addObject("selectStatus", "Rejected");
			mav.addObject("statusMsg",statusMsg);
			return mav;
		}
		else{
			mav=new ModelAndView("notAuthorized");
			return mav;
		}
	}
	
	@RequestMapping(value="/showApprovedWareHouses")
	public ModelAndView showApprovedWareHouses(HttpServletRequest request, HttpServletResponse response,ModelMap model){
		HttpSession session=request.getSession(true);
		ModelAndView mav;
		//get User Roles
		String userRole=(String) session.getAttribute("userRole");
		String username=(String) session.getAttribute("username");
		String statusMsg=null;
		if( ((userRole.equalsIgnoreCase("Admin"))) || ((userRole.equalsIgnoreCase("Audit"))) || ((userRole.equalsIgnoreCase("Business Team"))) || ((userRole.equalsIgnoreCase("Agency"))))
		{
			mav=new ModelAndView("adminWarehouse");
			try{
			//getAllWareHouses
			List<VendorWareHouseMaster> itemList=wareHouseService.getAprovedWarehouse();
			mav.addObject("wareHouseList", itemList);
			}catch (Exception e) {
				statusMsg="Error Occured while loading Approved Warehouse List";
			}
			//Setting select Option
			mav.addObject("selectStatus", "Approved");
			mav.addObject("statusMsg",statusMsg);
			return mav;
			
		}
		else if((userRole.equalsIgnoreCase("Vendor")) ){
			mav=new ModelAndView("adminWarehouse");
			try{
			//Get Vendor Specific Warehouse List
			List<VendorWareHouseMaster> itemList=wareHouseService.listMyApprovedWareHouse(username);
			mav.addObject("wareHouseList", itemList);
		}catch (Exception e) {
			statusMsg="Error Occured while loading Approved Warehouse List";
		}
		mav.addObject("statusMsg",statusMsg);
			//Setting select Option
			mav.addObject("selectStatus", "Approved");
			return mav;
			
		}
		else{
			mav=new ModelAndView("notAuthorized");
			return mav;
		}
	}
	
	@RequestMapping(value="/massApprove")
	public String massApprove(@RequestParam("wareHouse_submit") String strSelected,HttpServletRequest request, HttpServletResponse response,ModelMap model){
		//Update selected WareHouse Status as Approved
		String statusMsg=null;
		HttpSession session=request.getSession(true);
		try{
			String username=(String) session.getAttribute("username");
		String message=wareHouseService.massApprove(strSelected);
		if(message.equalsIgnoreCase("Success")){
			//Update same to WareHouse_History Table
			//int result=wareHouseService.updateWareHouseHistory(strSelected, username);
			try{
			wareHouseService.updateWareHouseHistory(strSelected, username);
			}catch (Exception e) {
				statusMsg="Error Occured while updating the Warehouse history";
			}
			statusMsg="The Selected Combination is Approved successfully";
		}
		
		}catch (Exception e) {
				statusMsg="Error Occured while approving the  Warehouse List";
			}
		session.setAttribute("status",statusMsg);
		return "redirect:/warehouse";
				
	}
	
	@RequestMapping(value="/massReject")
	public String massReject(@RequestParam("wareHouse_submit") String strSelected,HttpServletRequest request, HttpServletResponse response,ModelMap model){
		String statusMsg=null;
		HttpSession session=request.getSession(true);
		try{
		String message=wareHouseService.massReject(strSelected);
		String username=(String) session.getAttribute("username");
		if(message.equalsIgnoreCase("Success")){
			//Update same to WareHouse_History Table
			//int result=wareHouseService.updateWareHouseHistory(strSelected, username);
			try{
			wareHouseService.updateWareHouseHistory(strSelected, username);
			}catch (Exception e) {
				statusMsg="Error Occured while updating the Warehouse history";
			}
			statusMsg="The Selected Combination is Rejected successfully";
		}
		}catch (Exception e) {
			statusMsg="Error Occured while rejecting the Warehouse List";
		}
		session.setAttribute("status",statusMsg);
		return "redirect:/warehouse";
				
	}
	
	@RequestMapping(value="/approveWarehouse")
	public String approveWarehouse(@RequestParam("wareHouseId") String strId,@RequestParam("comment") String strComments,HttpServletRequest request, HttpServletResponse response,ModelMap model){
		String statusMsg=null;
		try{
			HttpSession session=request.getSession(true);
			String username=(String) session.getAttribute("username");
		//Update selected WareHouse Status as Approved
		String message=wareHouseService.approveWareHouse(strId,strComments);
		if(message.equalsIgnoreCase("Success")){
			//Update same to WareHouse_History Table
			//int result=wareHouseService.updateWareHouseHistory(strId, username);
			try{
			wareHouseService.updateWareHouseHistory(strId, username);
		}catch (Exception e) {
			statusMsg="Error Occured while updating the Warehouse history";
		}
			message="Warehouse Approved Successfully";
		}
		session.setAttribute("strStatus", message);
		}catch (Exception e) {
			statusMsg="Error Occured while Approving the Warehouse";
		}
		request.setAttribute("strStatus",statusMsg);
		return "redirect:/editwarehouse?id="+strId;
				
	}
	
	@RequestMapping(value="/rejectWarehouse")
	public String rejectWarehouse(@RequestParam("wareHouseId") String strId,@RequestParam("comment") String strComments,HttpServletRequest request, HttpServletResponse response,ModelMap model){
		String message=null;
		try{
			HttpSession session=request.getSession(true);
			String username=(String) session.getAttribute("username");
		//Update selected WareHouse Status as Approved
		 message=wareHouseService.rejectWareHouse(strId,strComments);
		if(message.equalsIgnoreCase("Success")){
			//Update same to WareHouse_History Table
			//int result=wareHouseService.updateWareHouseHistory(strId, username);
			try{
			wareHouseService.updateWareHouseHistory(strId, username);
		}catch (Exception e) {
			message="Error Occured while updating the Warehouse history";
		}
			message="Warehouse Rejected Successfully";
		}
		}catch (Exception e) {
			message="Error Occured while Rejecting the Warehouse";
		}
		HttpSession session=request.getSession(true);
		session.setAttribute("strStatus", message);
		return "redirect:/editwarehouse?id="+strId;
				
	}
	
	@RequestMapping(value="/updateVen",method = RequestMethod.POST)
	public String updateVendor(@RequestParam("wareHouseId") String strId,@RequestParam("contact") String strContact,
			@RequestParam("email") String strEmail,@RequestParam("comment") String strComment,
			HttpServletRequest request, HttpServletResponse response,ModelMap model){
		String strStatus=null;
		//Create Object of VendorWareHouseMaster
		VendorWareHouseMaster obj=new VendorWareHouseMaster();
		obj.setId(strId);
		obj.setVendorContact(strContact);;
		obj.setVendorEmail(strEmail);
		obj.setComment(strComment);
		try{
		//Update this object in DB
		 strStatus=wareHouseService.updateVendor(obj);
		}catch (Exception e) {
			strStatus="Error Occured while approving the Warehouse";
		}
		request.setAttribute("statusMsg",strStatus);
		return "redirect:/editwarehouse?id="+strId;
				
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/downloadWarehouse", method = {RequestMethod.POST,RequestMethod.GET})
	public void downloadWarehouse(HttpServletRequest request,HttpServletResponse response, Model model) {
		String strStatus=null;
		//Role wise Restricting the user to access the url 1
				int role=(Integer) request.getSession().getAttribute("userRole1");
				if(role!=7 && role!=8 && role!=9) {
		try{
		String strFile ="WareHouseList.csv";
		HttpSession session=request.getSession(true);
		List<VendorWareHouseMaster> listVendorWareHouse=(List<VendorWareHouseMaster>)session.getAttribute("download");
		 Map<Integer, String> stateMap=new HashMap<Integer, String>();
		 stateMap=( Map<Integer, String>)session.getAttribute("stateMap");
		 Map<Integer, String> cityMap=new HashMap<Integer, String>();
		 cityMap=( Map<Integer, String>)session.getAttribute("cityMap");
		downloadCSV(request,response, listVendorWareHouse,strFile,stateMap,cityMap);
		}catch (Exception e) {
			strStatus="Error Occured while downloading the Warehouse List";
		}
				}else {
					try {
					request.getRequestDispatcher("/views/login.jsp").forward(request, response);
					}catch (Exception e) {
					}
				}
		request.setAttribute("statusMsg",strStatus);
	}
	
	
	//File Download Method
	public void downloadCSV(HttpServletRequest req,HttpServletResponse response, List<VendorWareHouseMaster> itemList,String strFile,Map<Integer, String> stateMap,Map<Integer, String> cityMap) {
		ServletOutputStream outStream = null;
		try {
			 Map<Integer, String> vendorMap=new HashMap<Integer, String>();
			 vendorMap=( Map<Integer, String>)req.getSession().getAttribute("vendorMap");
			ArrayList<String> rows = new ArrayList<String>();
			rows.add("WareHouse Name,Status,Vendor Name,Contact,Vendor Email, "
					+ " Address 01, Address 02, State, City, PinCode, WareHouse Area,Comments");
			rows.add("\n");

			Iterator<VendorWareHouseMaster> iter = itemList.iterator();
			while (iter.hasNext()) {
				VendorWareHouseMaster item = (VendorWareHouseMaster) iter.next();
				rows.add(item.getName()+","+item.getStatus()+","+vendorMap.get(item.getVendorId())+","+item.getVendorContact()+
						","+item.getVendorEmail()+","+item.getAddr1()+
						","+item.getAddr2()+","+stateMap.get(item.getStateId())+","+cityMap.get(item.getCityId())+","+
						item.getPin()+","+item.getArea()+","+item.getComment());

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
	
	@RequestMapping(value = "/wareHouseHistory", method = RequestMethod.POST)
	public ModelAndView wareHouseHistory(@RequestParam("warehouseName") String strWareHouseName,@RequestParam("wareHouseId") String strId,HttpServletRequest request,HttpServletResponse response) {
		ModelAndView mav=new ModelAndView("wareHouseHistory");
		mav.addObject("itemList", wareHouseService.getWareHouseHistory(strWareHouseName,"All"));
		mav.addObject("strId", strId);
		mav.addObject("strWareHouseName", strWareHouseName);
		return mav;
	}
	
	@RequestMapping(value = "/showWareHouseHistory", method = {RequestMethod.POST,RequestMethod.GET})
	public ModelAndView showWareHouseHistory(@RequestParam("id") String strWareHouseName,@RequestParam("backButton") String backButton,HttpServletRequest request,HttpServletResponse response) {
		ModelAndView mav=new ModelAndView("wareHouseHistory");
		mav.addObject("itemList", wareHouseService.getWareHouseHistory(strWareHouseName,"All"));
		mav.addObject("backButton", backButton);
		mav.addObject("strWareHouseName", strWareHouseName);
		return mav;
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/downloadHistory", method = {RequestMethod.POST,RequestMethod.GET})
	public void downloadHistory(HttpServletRequest request,HttpServletResponse response, Model model) {
		String strStatus=null;
		//Role wise Restricting the user to access the url 1
				int role=(Integer) request.getSession().getAttribute("userRole1");
				if(role!=7 && role!=8 && role!=9) {
		try{
		String strFile ="WareHouseHistory.csv";
		HttpSession session=request.getSession(true);
		List<WareHouseHistory> list=(List<WareHouseHistory>)session.getAttribute("downloadHistory");
		downloadWareHouseHistory(response,request, list,strFile);
		}catch (Exception e) {
			strStatus="Error Occured while downloading the Warehouse List";
		}

				}else {
					try {
					request.getRequestDispatcher("/views/login.jsp").forward(request, response);
					}catch (Exception e) {
					}
				}
		request.setAttribute("statusMsg",strStatus);
	}
	
	
	//File Download Method
	public void downloadWareHouseHistory(HttpServletResponse response,HttpServletRequest req, List<WareHouseHistory> itemList,String strFile) {
		ServletOutputStream outStream = null;
		Map<Integer, String> stateMap=new HashMap<Integer, String>();
		Map<Integer, String> cityMap=new HashMap<Integer, String>();
		Map<Integer, String> vendorMap=new HashMap<Integer,String>();
		try {
			HttpSession session=req.getSession();
			stateMap=(Map<Integer, String>)session.getAttribute("stateMap");
			cityMap=(Map<Integer, String>)session.getAttribute("cityMap");
			vendorMap=(Map<Integer, String>)session.getAttribute("vendorMap");
			ArrayList<String> rows = new ArrayList<String>();
			rows.add("WareHouse Name,Status,Vendor Name,Contact,Vendor Email,  "
					+ " Address 01, Address 02, State, City, PinCode, WareHouse Area,Comments");
			rows.add("\n");

			Iterator<WareHouseHistory> iter = itemList.iterator();
			while (iter.hasNext()) {
				WareHouseHistory item = (WareHouseHistory) iter.next();
				rows.add(item.getWarehouseName()+","+item.getWarehouseStatus()+","+vendorMap.get(item.getVendorId())+","+item.getVendorContact()+
						","+item.getVendorEmail()+","+item.getWareHouseAddress01()+
						","+item.getWareHouseAddress02()+","+stateMap.get(item.getStateId())+","+cityMap.get(item.getCityId())+","+
						item.getPinCode()+","+item.getWareHouseAreainSqft()+","+item.getComment());

				rows.add("\n");
				//strFile=item.getWarehouseName();
			}
			outStream = response.getOutputStream();
			/*strFile.replaceAll(" ", "_");
			strFile=strFile+"_History.csv";*/
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

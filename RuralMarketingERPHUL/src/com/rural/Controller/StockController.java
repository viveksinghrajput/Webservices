
package com.rural.Controller;


import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.Part;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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

import com.rural.Model.CreateInvoices;
import com.rural.Constant.*;
import com.rural.exceptions.ERPException;
import com.rural.utility.CommonUtility;
import com.rural.Model.ItemDescriptionMaster;
import com.rural.Model.Stock;
import com.rural.Model.StockUsers;
import com.rural.Service.StockService;

@Controller
public class StockController {

	@Autowired
	StockService stockService;

	static Logger logger =Logger.getLogger(StockController.class);

	@SuppressWarnings("unused")
	@RequestMapping(value="/stock")
	public String Stock(HttpServletRequest request, HttpServletResponse response,Map<String, Object> model){
		logger.info("StockController :: Stock");
		List<Stock> itemList = null ;
		String statusMsg = null;
		String userRole=null;
		Integer userId=null;	
		Map<Integer,String> monthMap=null;
		List<Integer> yearList=null;
		HttpSession session=request.getSession(true);
		try {
			userId=(Integer) session.getAttribute("userId");
			 userRole=(String) session.getAttribute("userRole");
		
		List<String> listCity = new ArrayList<String>();
		request.getSession().setAttribute("stateList", stockService.getState());
		request.getSession().setAttribute("stateList", stockService.getState());
		Map<String, String> mapItems = new LinkedHashMap<String, String>();
		List<ItemDescriptionMaster> itemDescList=stockService.getitemDesc();
		monthMap=CommonUtility.getMonthMap();
		yearList=CommonUtility.getYearList();
		Iterator<ItemDescriptionMaster> itr=itemDescList.iterator();
		ItemDescriptionMaster items=null;
		Map<Integer, String> map=new HashMap<Integer,String>();
		while(itr.hasNext()) {
			items=new ItemDescriptionMaster();
			items=itr.next();
			mapItems.put(String.valueOf(items.getItemId()), items.getItemDescription());
			map.put(items.getItemId(), items.getUnitspercase()+":"+items.getPriceperunit());
		}
		request.setAttribute("map", map);
		session.setAttribute("mapItems", mapItems);
		Stock stock = new Stock();
		stock.setInvoices(new ArrayList<>());
		List<String> listUsers=new ArrayList<String>();
		if(userRole.equals("Admin") || userRole.equals("Business Team")){
			listUsers=stockService.getUsers(userRole);
		}
		if(!userRole.equals("Admin")){
			//List<StockUsers> skuList=stockService.getSKUDetails(userId,userRole);
			itemList = stockService.getallStocklist(userId,userRole);
			request.getSession().setAttribute("itemList",itemList);
			//session.setAttribute("skuList", skuList);
		}
		model.put("stock", stock);
		session.setAttribute("listUsers", listUsers);
		session.removeAttribute("monthMap");
		session.removeAttribute("yearList");
		//keeping in the session 
		session.setAttribute("monthMap", monthMap);
		session.setAttribute("yearList", yearList);
		}catch (ERPException erp) {
			request.setAttribute("statusMsg","Problem Occured. Please try Again");
			logger.error("Problem Occured. Please try Again"+erp.getMessage());
			erp.printStackTrace();
		}
		catch (Exception e) {
			request.setAttribute("statusMsg","Problem Occured. Please try Again");
			logger.error("Problem Occured. Please try Again"+e.getMessage());
			e.printStackTrace();
		}
		return ("stock12");
	}	

	@RequestMapping(value = "/getCitiesForStock", method = RequestMethod.POST)
	public @ResponseBody List<String> getCity(@RequestParam("state") String strstate, HttpServletRequest req,HttpServletResponse response,
			Model model) {
		List<String> listCity = null;
		//Role wise Restricting the user to access the url
		int role=(Integer) req.getSession().getAttribute("userRole1");
		if(role==2 || role==6 || role==4) {
		try {
		logger.info("StockController :: getCity");
		listCity = new ArrayList<String>();
		listCity = stockService.getCityofState(strstate);
		}
		catch (ERPException erp) {
			req.setAttribute("message","Problem Occured. Please try Again");
			logger.error("Problem Occured. Please try Again"+erp.getMessage());
		}
		catch (Exception e) {
			req.setAttribute("message","Problem Occured. Please try Again");
			logger.error("Problem Occured. Please try Again"+e.getMessage());
			e.printStackTrace();
		}
		}
		else {
			try {
			req.getRequestDispatcher("/views/login.jsp").forward(req, response);
			}catch (Exception e) {
			}
		}
		return listCity;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/downloadStock")
	public void downloadStock(HttpServletRequest request,HttpServletResponse response, Model model) {
		//Role wise Restricting the user to access the url
		int role=(Integer) request.getSession().getAttribute("userRole1");
		if(role==2 || role==6 || role==4) {
		try {
		String strFile ="StockList.csv";
		HttpSession session=request.getSession(true);
		List<Stock> listStock=(List<Stock>)session.getAttribute("itemList");
		downloadCSV(request,response, listStock,strFile);
		}
		catch (ERPException erp) {
			request.setAttribute("message","Problem Occured. Please try Again");
			logger.error("Problem Occured. Please try Again"+erp.getMessage());
		}
		catch (Exception e) {
			request.setAttribute("message","Problem Occured. Please try Again");
			logger.error("Problem Occured. Please try Again"+e.getMessage());
			e.printStackTrace();
		}
		}
		else {
			try {
			request.getRequestDispatcher("/views/login.jsp").forward(request, response);
			}catch (Exception e) {
			}
		}
	}

	//File Download Method
	public void downloadCSV(HttpServletRequest request, HttpServletResponse response, List<Stock> itemList,String strFile) {
		ServletOutputStream outStream = null;
		logger.info("StockController :: downloadCSV");
		try {
			ArrayList<String> rows = new ArrayList<String>();
			rows.add("Bill Number,RS Code,RS Name,State, City, Bill date, Total Amount");
			rows.add("\n");
			Iterator<Stock> iter = itemList.iterator();
			while (iter.hasNext()) {
				Stock item = (Stock) iter.next();
				rows.add(item.getBill_number()+","+item.getRs_code()+","+item.getRs_name()+
						","+item.getState()+","+item.getCity()+","+item.getBill_date()+","+item.getTotal_amount());
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
		}catch (IOException ie) {
			request.setAttribute("message","Problem Occured. Please try Again");
			logger.error("Problem Occured. Please try Again"+ie.getMessage());
		}
		catch (ERPException erp) {
			request.setAttribute("message","Problem Occured. Please try Again");
			logger.error("Problem Occured. Please try Again"+erp.getMessage());
		}
		catch (Exception e) {
			request.setAttribute("message","Problem Occured. Please try Again");
			logger.error("Problem Occured. Please try Again"+e.getMessage());
			e.printStackTrace();
		} 
		 finally {
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

	@RequestMapping(value= "/addstock")
	public String createInvoices(HttpServletRequest req,HttpServletResponse response,
			Map<String, Object> model) {
		logger.info("StockController :: createInvoices");
		Integer userId=null;
		String userRole=null;
		HttpSession session=req.getSession(true);
		//Role wise Restricting the user to access the url
				int role=(Integer) req.getSession().getAttribute("userRole1");
				if(role==2 || role==6 || role==4) {
		try {
			userId=(Integer) session.getAttribute("userId");
			 userRole=(String) session.getAttribute("userRole");
		String res=req.getSession().getAttribute("statusMsg")==null?"":(String)req.getSession().getAttribute("statusMsg");
		req.setAttribute("statusMsg1",res);
		req.getSession().setAttribute("stateList", stockService.getState());
		List<ItemDescriptionMaster> itemList=stockService.getitemDesc();
		Iterator<ItemDescriptionMaster> itr=itemList.iterator();
		ItemDescriptionMaster items=null;
		Map<Integer, String> map=new HashMap<Integer,String>();
		while(itr.hasNext()) {
			items=new ItemDescriptionMaster();
			items=itr.next();
			map.put(items.getItemId(), items.getUnitspercase()+":"+items.getPriceperunit());
		}
		
		List<Stock> itemListStock = null ;
		itemListStock = stockService.getallStocklist(userId,userRole);
		session.setAttribute("itemList",itemListStock );
		req.setAttribute("map", map);
		Stock stock = new Stock();
		stock.setInvoices(new ArrayList<>());
		model.put("stock", stock);
		}
		catch (ERPException erp) {
			req.setAttribute("message","Problem Occured. Please try Again");
			logger.error("Problem Occured. Please try Again"+erp.getMessage());
		}
		catch (Exception e) {
			req.setAttribute("message","Problem Occured. Please try Again");
			logger.error("Problem Occured. Please try Again"+e.getMessage());
			e.printStackTrace();
		} 
				}
		else {
			try {
			req.getRequestDispatcher("/views/login.jsp").forward(req, response);
			}catch (Exception e) {
			}
		}
		return "stock12";
	}
	
	@RequestMapping(value= "/checkStock", method = RequestMethod.POST)
	public @ResponseBody int  checkstock(@RequestParam("bill_number") String bill_number,HttpServletRequest req,
			Map<String, Object> model,HttpServletResponse response) {
		logger.info("StockController :: checkstock");
		Stock stock = new Stock();
		//Role wise Restricting the user to access the url
				int role=(Integer) req.getSession().getAttribute("userRole1");
				if(role==2 || role==6 || role==4) {
		if(bill_number != null)
			stock = stockService.getStockByBillNumber(bill_number);		
				}else {
			try {
			req.getRequestDispatcher("/views/login.jsp").forward(req, response);
			}catch (Exception e) {
			}
		}
		if(stock != null)
		{
			return 1;
		}
		else{
			return 0;
		}
		
	}
	
	@RequestMapping(value= "/createInvoice", method = RequestMethod.POST)
	public String createInvoices(@ModelAttribute("stock") Stock stock,
			HttpServletRequest request, ModelAndView mav, Model model, @RequestParam("bill") String bill_number,  BindingResult validationResult,
			@RequestParam(required = false, defaultValue = "RS", value="NonRS") String strNonRS,HttpServletResponse response) {
		logger.info("StockController :: createInvoice");
		//Role wise Restricting the user to access the url
				int role=(Integer) request.getSession().getAttribute("userRole1");
				if(role==2 || role==6 || role==4) {
		HttpSession session=request.getSession();
		String statusMsg = null;
		Part partDispatch1 = null;
		Part partDispatch2=null;
		try {
		session.removeAttribute("statusMsg");
		Integer userId=(Integer) session.getAttribute("userId");
		String userRole=(String) session.getAttribute("userRole");
		try {
			 userId=(Integer)session.getAttribute("userId");
			stock.setVenId(userId);
			partDispatch1 = request.getPart("invoice_image");
			partDispatch2=request.getPart("mailbox_image");
		} catch (IOException | ServletException e) {
			e.printStackTrace();
		}
		String path=IConstants.STOCK_WINDOW;
		String DESTINATION_DIR_PATH = path+bill_number;
		
		stockService.copyPartToFile(bill_number,partDispatch1,DESTINATION_DIR_PATH,partDispatch2);
		
		 stock.setInvoice_path(DESTINATION_DIR_PATH+"/"+extractFileName(partDispatch1));
		 stock.setMail_path(DESTINATION_DIR_PATH+"/"+extractFileName(partDispatch2));
	
		 String temp=stock.getTotal_amount().replace(",", "");
		 stock.setTotal_amount(temp);
		 String temp1=stock.getBill_number();
		 stock.setBill_number(temp1);
		 stock.setBill_number(bill_number);
		 stock.setNon_rs(strNonRS);
		String stockdata = stockService.addInvoice(stock);
		if(stockdata.equalsIgnoreCase("fail")){
		statusMsg="Unable to add Invoice";
		session.setAttribute("statusMsg", statusMsg);
		}
		else {
			statusMsg="Invoice added successfully";
			session.setAttribute("statusMsg", statusMsg);
			List<Stock> itemList = null ;
			itemList = stockService.getallStocklist(userId,userRole);
			request.getSession().setAttribute("itemList",itemList );
			return "redirect:/addstock";
		}
		statusMsg="Invoice added successfully";
		session.setAttribute("statusMsg", statusMsg);
		}catch (ERPException erp) {
			request.setAttribute("message","Problem Occured. Please try Again");
			logger.error("Problem Occured. Please try Again"+erp.getMessage());
		}
		catch (Exception e) {
			request.setAttribute("message","Problem Occured. Please try Again");
			logger.error("Problem Occured. Please try Again"+e.getMessage());
			e.printStackTrace();
		} 
		
				}else {
			try {
			request.getRequestDispatcher("/views/login.jsp").forward(request, response);
			}catch (Exception e) {
			}
		}
		return "redirect:/addstock";
	}
	
	@SuppressWarnings("unused")
	private int CopyUploadedFiles(int bill_number, Part partDispatch1, String dESTINATION_DIR_PATH,
			HttpServletRequest request) {
		
		File file = new File(dESTINATION_DIR_PATH);
		 int len=0;
		    boolean folderCreate = false;
		    if (!file.exists()) {
		    	folderCreate = file.mkdirs();
		    }
		 String fileName1=extractFileName(partDispatch1);
		 try {
			partDispatch1.write(dESTINATION_DIR_PATH + File.separator + fileName1);
		 len=file.listFiles().length;
		 } catch (IOException e) {
				e.printStackTrace();
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
	
	@RequestMapping(value = "/viewStock")
	public ModelAndView addinVoices(@ModelAttribute("command")Stock stock, BindingResult result, HttpServletRequest request,HttpServletResponse response) {
		//Role wise Restricting the user to access the url
				int role=(Integer) request.getSession().getAttribute("userRole1");
				Map<String, Object> model = null;
				if(role==2 || role==6 || role==4) {
		logger.info("StockController :: addinVoices");
		try {
		model = new HashMap<String, Object>();
		model.put("stock",  (stockService.addInvoice(stock)));
		}catch (ERPException erp) {
			request.setAttribute("message","Problem Occured. Please try Again");
			logger.error("Problem Occured. Please try Again"+erp.getMessage());
		}
		catch (Exception e) {
			request.setAttribute("message","Problem Occured. Please try Again");
			logger.error("Problem Occured. Please try Again"+e.getMessage());
			e.printStackTrace();
		} 
				}else {
					try {
					request.getRequestDispatcher("/views/login.jsp").forward(request, response);
					}catch (Exception e) {
					}
				}
		return new ModelAndView("stockService", model);
	}
	
	@RequestMapping(value = "/viewInvoiceDetails", method = RequestMethod.POST)
	public ModelAndView getStock(@RequestParam("billNum") String billNumber, 
			HttpServletRequest req, Model model,HttpServletResponse response) {
		logger.info("StockController :: getStock");
		ModelAndView mav = null;
		//Role wise Restricting the user to access the url
				int role=(Integer) req.getSession().getAttribute("userRole1");
				if(role==2 || role==6 || role==4) {
		try {
		
		mav = new ModelAndView("showStockList");
		Stock stock = new Stock();
		stock = stockService.getStockByBillNumber(billNumber);
	String strLine = stockService.getOutletAndGSTNos(billNumber);
		
		//for spliting the variable after colon.....
	if(!strLine.equals("null") | strLine!=null){
		String namepass[] = strLine.split(":"); 
		String hul_outlet_code = namepass[0]; 
		String gstn_no = namepass[1];
		mav.addObject("hul_outlet_code", hul_outlet_code);
		mav.addObject("gstn_no", gstn_no);
	}
		HttpSession session=req.getSession();
		Map<String, String> mapItems = new LinkedHashMap<String, String>();
		mapItems=(Map<String, String>)session.getAttribute("mapItems");
		session.setAttribute("mapItems", mapItems);
		List<CreateInvoices> items=stock.getInvoices();
			mav.addObject("stock", stock);
			mav.addObject("itemsList", items);
	
		}catch (ERPException erp) {
			req.setAttribute("message","Problem Occured. Please try Again");
			logger.error("Problem Occured. Please try Again"+erp.getMessage());
		}
		catch (Exception e) {
			req.setAttribute("message","Problem Occured. Please try Again");
			logger.error("Problem Occured. Please try Again"+e.getMessage());
			e.printStackTrace();
		} 
				}else {
					try {
					req.getRequestDispatcher("/views/login.jsp").forward(req, response);
					}catch (Exception e) {
					}
				}
		return mav;
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/downloadItemsBybill_number", method=RequestMethod.POST)
	public void downloadItemsBybill_number(HttpServletRequest request,HttpServletResponse response, Model model) {
		logger.info("StockController :: downloadItemsBybill_number");
		String billNumber=request.getParameter("billNo");
		//Role wise Restricting the user to access the url
				int role=(Integer) request.getSession().getAttribute("userRole1");
				if(role==2 || role==6 || role==4) {
		try {
		String strFile ="Invoiceof"+billNumber+".csv";
		try {
		Stock stock = new Stock();
		stock = stockService.getStockByBillNumber(billNumber);
		List<CreateInvoices> items = new ArrayList<CreateInvoices>();
		items = stockService.getInvoiceItemsByInvoice(billNumber);
		Map<String,String> itemDescMap=(Map<String,String>)request.getSession().getAttribute("mapItems");
		if(stock != null && items != null)
		downloadCSVItems(response,request, stock,items,strFile,itemDescMap);
		}catch(Exception e) {
		}
		}catch(ERPException erp) {
			logger.error("Problem Occured. Please try Again"+erp.getMessage());
		}
		catch (Exception e) {
			logger.error("Problem Occured. Please try Again"+e.getMessage());
		} 
				}else {
					try {
					request.getRequestDispatcher("/views/login.jsp").forward(request, response);
					}catch (Exception e) {
					}
				}
	}

	//File Download Method
	public void downloadCSVItems(HttpServletResponse response, HttpServletRequest request, Stock stock,List<CreateInvoices> itemList,String strFile,Map<String,String> itemDescMap) {
		ServletOutputStream outStream = null;
		logger.info("StockController :: downloadCSVItems");
		try {
			ArrayList<String> rows = new ArrayList<String>();
			rows.add("Bill Number,RS Name,RS Code,State,City,Bill Date,Total Amount");
			rows.add("\n");
			rows.add(stock.getBill_number()+","+stock.getRs_name()+","+stock.getRs_code()+","+stock.getState()+","+stock.getCity()+","+stock.getBill_date()+","+stock.getTotal_amount());
			rows.add("\n");
			rows.add("Item Description, Case, Units, Total Units, MRP,Net Amount");
			rows.add("\n");
			Iterator<CreateInvoices> iter = itemList.iterator();
			while (iter.hasNext()) {
				CreateInvoices item=(CreateInvoices) iter.next();
				rows.add(itemDescMap.get(item.getItemId()) +"," +item.getCases()+","+item.getUnits()+","+item.getTotalunits()+","+ item.getPriceperunits()+","+item.getNet_amount());
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
		} catch(ERPException erp) {
			request.setAttribute("message","Problem Occured. Please try Again");
			logger.error("Problem Occured. Please try Again"+erp.getMessage());
		}
		catch (Exception e) {
			request.setAttribute("message","Problem Occured. Please try Again");
			logger.error("Problem Occured. Please try Again"+e.getMessage());
			e.printStackTrace();
		}  finally {
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
	
		@RequestMapping(value = "/approveinvoice", method = RequestMethod.POST)
		public @ResponseBody String approveInvoice(@RequestParam("invoice_id") String strinvoiceId,@RequestParam("comment") String strComments,
				@RequestParam("status") String status,HttpServletRequest request, HttpServletResponse response,ModelMap model) {
			//Role wise Restricting the user to access the url
			int role=(Integer) request.getSession().getAttribute("userRole1");
			String strStatus=null;
			if(role==2 || role==6 || role==4) {
			logger.info("StockController :: approveInvoice");
			try {
			String message = stockService.approveInvoice(strinvoiceId,status, strComments);
			if(message.equalsIgnoreCase("Success")){
				strStatus="Invoice Approved successfully";
			}
			else{
				strStatus="Problem Occured in Approving the Invoice";
			}
			}catch(ERPException erp) {
				request.setAttribute("message","Problem Occured. Please try Again");
			}
			catch (Exception e) {
				request.setAttribute("message","Problem Occured. Please try Again");
				e.printStackTrace();
			} 
			}else {
				try {
				request.getRequestDispatcher("/views/login.jsp").forward(request, response);
				}catch (Exception e) {
				}
			}
			return strStatus;			
		}
		
		@RequestMapping(value = "/rejectinvoice", method = RequestMethod.POST)
		public @ResponseBody String rejectInvoice(@RequestParam("invoice_id") String strinvoiceId,@RequestParam("comment") String strComments,@RequestParam("status") String status,HttpServletRequest request, HttpServletResponse response,ModelMap model) {
			//Role wise Restricting the user to access the url
			int role=(Integer) request.getSession().getAttribute("userRole1");
			String strStatus=null;
			if(role==2 || role==6 || role==4) {
			logger.info("StockController :: rejectInvoice");
			try {
			String message = stockService.rejectInvoice(strinvoiceId,status, strComments);
			if(message.equalsIgnoreCase("Success")){
			strStatus="Invoice Rejected successfully";
		}
		else{
			strStatus="Problem Occured in Rejecting the Invoice";
		}
			}catch(ERPException erp) {
				request.setAttribute("message","Problem Occured. Please try Again");
			}
			catch (Exception e) {
				request.setAttribute("message","Problem Occured. Please try Again");
				e.printStackTrace();
			} 
			}else {
				try {
				request.getRequestDispatcher("/views/login.jsp").forward(request, response);
				}catch (Exception e) {
				}
			}
			return strStatus;
			}
		
		@RequestMapping(value = "/getUserSKUDetails", method = RequestMethod.POST)
		public  @ResponseBody List<String> getUserSKUDetails(@RequestParam("user") Integer userId,@RequestParam("date") String strDate,
ModelMap model,HttpServletRequest request, HttpServletResponse response) {
			//Role wise Restricting the user to access the url
			int role=(Integer) request.getSession().getAttribute("userRole1");
			 List<String> list=new ArrayList<String>();
			if(role==2 || role==6 || role==4) {
			Integer intYear=0;
			Integer intMonth=0;
			logger.info("StockController :: getUserSKUDetails strDate bhagya >>>>"+strDate);
			String strCalendar[]=new String[2];
			strCalendar=strDate.split(" ");
			intMonth=CommonUtility.getMonthId(strCalendar[0].substring(0, 3));
			intYear=Integer.parseInt(strCalendar[1]);
			String userRole=null;
			model.put("stock", new Stock());
			 HttpSession session=request.getSession();
			try {
				session.removeAttribute("skuList");
				 List<StockUsers> skuList=stockService.getSKUDetails(userId,userRole,intMonth,intYear);
				 Iterator<StockUsers> sku = skuList.iterator();
				 while(sku.hasNext()){
					 StockUsers sb = sku.next();
					 String str=sb.getItem_description()+":"+sb.getNoofUnits();
					list.add(str);
				 }
				 session.setAttribute("skuList", skuList);
			}catch(ERPException erp) {
				request.setAttribute("message","Problem Occured. Please try Again");
				logger.error("Problem Occured. Please try Again"+erp.getMessage());
			}
			catch (Exception e) {
				request.setAttribute("message","Problem Occured. Please try Again");
				logger.error("Problem Occured. Please try Again"+e.getMessage());
				e.printStackTrace();
			} 
			}else {
				try {
				request.getRequestDispatcher("/views/login.jsp").forward(request, response);
				}catch (Exception e) {
				}
			}
			return list;	
		}
		
		
		@RequestMapping(value = "/getUserSKUDetailsForVendor", method = RequestMethod.POST)
		public  @ResponseBody List<String> getUserSKUDetailsForVendor(@RequestParam("date") String strDate,
				ModelMap model,HttpServletRequest request, HttpServletResponse response) {
			//Role wise Restricting the user to access the url
			int role=(Integer) request.getSession().getAttribute("userRole1");
			 List<String> list=new ArrayList<String>();
			if(role==2 || role==6 || role==4) {
			String userRole=null;
			model.put("stock", new Stock());
			 Integer intYear=0;
			Integer intMonth=0;
			HttpSession session=request.getSession();
			try {
				logger.info("StockController :: getUserSKUDetails strDate bhagya >>>>"+strDate);
				session.removeAttribute("skuList");
				String strCalendar[]=new String[2];
				strCalendar=strDate.split(" ");
				intMonth=CommonUtility.getMonthId(strCalendar[0].substring(0, 3));
				intYear=Integer.parseInt(strCalendar[1]);
				 List<StockUsers> skuList=stockService.getSKUDetails(0,userRole,intMonth,intYear);
				 Iterator<StockUsers> sku = skuList.iterator();
				 while(sku.hasNext()){
					 StockUsers sb = sku.next();
					 String str=sb.getItem_description()+":"+sb.getNoofUnits();
					list.add(str);
				 }
				 session.setAttribute("skuList", skuList);
			}catch(ERPException erp) {
				request.setAttribute("message","Problem Occured. Please try Again");
				logger.error("Problem Occured. Please try Again"+erp.getMessage());
			}
			catch (Exception e) {
				request.setAttribute("message","Problem Occured. Please try Again");
				logger.error("Problem Occured. Please try Again"+e.getMessage());
				e.printStackTrace();
			} 
			}else {
				try {
				request.getRequestDispatcher("/views/login.jsp").forward(request, response);
				}catch (Exception e) {
				}
			}
			return list;	
		}
		
		
		@RequestMapping(value = "/download1", method = RequestMethod.GET)
		public void download(HttpServletRequest request, HttpServletResponse response,ModelMap model){
			//Role wise Restricting the user to access the url
			int role=(Integer) request.getSession().getAttribute("userRole1");
			if(role==2 || role==6 || role==4) {
			model.addAttribute("stock",new Stock());
			String strPath=request.getParameter("path");
		File file = new File(strPath);
		response.setContentType("text/csv");
		response.setHeader("Content-Disposition", "attachment;filename=" + file.getName());
		BufferedInputStream inStrem=null;
		BufferedOutputStream outStream=null;
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
		
		@SuppressWarnings("unchecked")
		@RequestMapping(value = "/downloadSKUList", method = RequestMethod.POST)
		public void downloadSKUList(HttpServletRequest request,HttpServletResponse response) {
			//Role wise Restricting the user to access the url
			int role=(Integer) request.getSession().getAttribute("userRole1");
			if(role==2 || role==6 || role==4) {
			logger.info("StockController :: downloadItemsBybill_number");
			try {
			String strFile ="SKUList.csv";
			HttpSession session=request.getSession();
			try {
				List<StockUsers> skuList=(List<StockUsers>)session.getAttribute("skuList");
			if(skuList != null && skuList != null)
			downloadSkuList(response,request,strFile,skuList);
			}catch(Exception e) {
			}
			}catch(ERPException erp) {
				request.setAttribute("message","Problem Occured. Please try Again");
				logger.error("Problem Occured. Please try Again"+erp.getMessage());
			}
			catch (Exception e) {
				request.setAttribute("message","Problem Occured. Please try Again");
				logger.error("Problem Occured. Please try Again"+e.getMessage());
				e.printStackTrace();
			} 
			}else {
				try {
				request.getRequestDispatcher("/views/login.jsp").forward(request, response);
				}catch (Exception e) {
				}
			}
		}

		//File Download Method
		public void downloadSkuList(HttpServletResponse response, HttpServletRequest request,String strFile,List<StockUsers> skuList) {
			ServletOutputStream outStream = null;
			logger.info("StockController :: downloadCSVItems");
			try {
				ArrayList<String> rows = new ArrayList<String>();
				rows.add("Vendor,Brand,Items");
				rows.add("\n");
				Iterator<StockUsers> iter = skuList.iterator();
				while (iter.hasNext()) {
					StockUsers sku=(StockUsers) iter.next();
					rows.add(sku.getItem_description() +"," +sku.getNoofUnits());
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
			} catch(ERPException erp) {
				request.setAttribute("message","Problem Occured. Please try Again");
				logger.error("Problem Occured. Please try Again"+erp.getMessage());
			}
			catch (Exception e) {
				request.setAttribute("message","Problem Occured. Please try Again");
				logger.error("Problem Occured. Please try Again"+e.getMessage());
				e.printStackTrace();
			}  finally {
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

/*
*StockServiceImpl.java
*Created By		:SaiTej Deep
*Created Date	:Apr 4, 2018
*/
package com.rural.Service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Part;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.rural.DAO.StockDAO;
import com.rural.Model.CreateInvoices;
import com.rural.Model.ItemDescriptionMaster;
import com.rural.Model.Stock;
import com.rural.Model.StockUsers;


@Component
@Repository
@Transactional
@EnableTransactionManagement
public class StockServiceImpl implements StockService {

	@Autowired
	StockDAO stockDAO;

	@Override
	public List<Stock> getallStocklist(Integer userId,String userRole) {
		return stockDAO.getAllStocks(userId,userRole);
	}

	@Override
	public List<String> getState() {
		// TODO Auto-generated method stub
		return stockDAO.getAllStates();
	}

	@Override
	public List<String> getCityofState(String state) {
		// TODO Auto-generated method stub
		return stockDAO.getAllCities(state);
	}

	@Override
	@Transactional
	public String addInvoice(Stock stock) {
		if(stock != null)
			stock.setStatus(12);
		boolean value = stockDAO.addStock(stock);

		if(value)
			return "added successfully!";
		else
			return "fail";
	}


	@Override
	public String saveInvoice(Stock stock) {
		return this.stockDAO.saveInvoice(stock);
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

	@Override
	public void copyPartToFile(String bill_number, Part partDispatch1, String DESTINATION_DIR_PATH,Part partDispatch2) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		File file = new File(DESTINATION_DIR_PATH);
		boolean folderCreate = false;
		if (!file.exists()) {
			folderCreate = file.mkdirs();
		}
		if (folderCreate){
			System.out.println("Directory successfully created");
		}
		else{
			System.out.println("Directoy directory");
		}
		String fileName1=extractFileName(partDispatch1);
		String fileName2=extractFileName(partDispatch2);
		try {
			partDispatch1.write(DESTINATION_DIR_PATH + File.separator + fileName1);
			partDispatch2.write(DESTINATION_DIR_PATH+ File.separator+ fileName2);


		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public CreateInvoices getInvoiceItemsByBillNumber(String billNumber) {
		return this.stockDAO.getInvoiceItemsByBillNumber(billNumber);
	}

	@Override
	public Stock getStockByBillNumber(String billNumber) {
		return this.stockDAO.getStockByBillNumber(billNumber);
	}
	@Override
	public List<CreateInvoices> getInvoiceItemsByInvoice(String billNumber) {
		// TODO Auto-generated method stub
		return  this.stockDAO.getInvoiceItemsByInvoice(billNumber);
	}

	@Override
	public List<ItemDescriptionMaster> getitemDesc() {
		return stockDAO.getAllitemDesc();
	}
	@Override
	public List<String> getUsers(String userRole) {
		// TODO Auto-generated method stub
		return stockDAO.getUsers(userRole);
	}

	@Override
	public List<StockUsers> getSKUDetails(Integer username,	String userRole,Integer intMonth,Integer intYear) {
		
		return stockDAO.getSKUDetails(username,userRole,intMonth,intYear);
	}

	@Override
	public String approveInvoice(String strinvoiceId,String status, String strComments) {
		
		
		return stockDAO.approveInvoices(strinvoiceId, status, strComments);	
	}

	@Override
	public String rejectInvoice(String strinvoiceId, String status, String strComments) {
		return stockDAO.rejectInvoices(strinvoiceId, status, strComments);	
	}

	@Override
	public String getOutletAndGSTNos(String billNumber) {
		// TODO Auto-generated method stub
		return stockDAO.getOutletAndGSTNos(billNumber);
	}

	
	/*private String extractFileName(Part part) {
	    String contentDisp = part.getHeader("content-disposition");
	    String[] items = contentDisp.split(";");
	    for (String s : items) {
	        if (s.trim().startsWith("filename")) {
	            return s.substring(s.indexOf("=") + 2, s.length()-1);
	        }
	    }
	    return "";
	}*/




}

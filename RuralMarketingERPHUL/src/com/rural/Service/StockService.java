/*
*StockService.java
*Created By		:SaiTej Deep
*Created Date	:Apr 4, 2018
*/
package com.rural.Service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.Part;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.rural.Model.CreateInvoices;
import com.rural.Model.ItemDescriptionMaster;
import com.rural.Model.Stock;
import com.rural.Model.StockUsers;


@Component
@Service
public interface StockService {

	List<Stock> getallStocklist(Integer userId,String userRole);
	List<String> getState();
	public String addInvoice(Stock stock);
	public String saveInvoice(Stock stock);
	List<String> getCityofState(String state);
	public CreateInvoices getInvoiceItemsByBillNumber(String billNumber);
	public List<CreateInvoices>  getInvoiceItemsByInvoice(String billNumber);
	public Stock getStockByBillNumber(String billNumber);
	List<ItemDescriptionMaster> getitemDesc();
	List<String> getUsers(String userRole);
	List<StockUsers> getSKUDetails(Integer username, String userRole, Integer intMonth, Integer intYear);
	public String approveInvoice(String strinvoiceId,String status, String strComments);
	public String rejectInvoice(String strinvoiceId, String status, String strComments);
void copyPartToFile(String bill_number, Part partDispatch1, String DESTINATION_DIR_PATH, Part partDispatch2);
	String getOutletAndGSTNos(String billNumber);
	

	




}

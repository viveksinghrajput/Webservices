/*
*VendorWarehouseController.java
*Created By		:Bhagya Lakshmi Mula
*Created Date	:Apr 4, 2018
*/
package com.rural.webservice.Controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.rural.Model.UserMaster;
import com.rural.Model.VendorWareHouseMaster;
import com.rural.Service.AdminService;
import com.rural.webservice.DTO.StatusMessages;
import com.rural.webservice.DTO.UserReq;
import com.rural.webservice.DTO.WarehouseResponse;
import com.rural.webservice.Service.VendorWarehouseService;

@RestController
public class VendorWarehouseController {
	@Autowired
	VendorWarehouseService warehouseService;
	
	@Autowired
	AdminService adminService;
	
	static Logger logger =Logger.getLogger(VendorWarehouseController.class);

	@RequestMapping(value="/getWarehouse", method = RequestMethod.POST, consumes = "application/json", produces = "application/json; charset=utf-8")
    public   ResponseEntity<?> getWarehouse(@RequestBody UserReq user) {
	 WarehouseResponse warehouse=new WarehouseResponse();
	 try{
		 warehouse=warehouseService.getWarehouses(user.getUserId());
	 if(warehouse.equals("null")){
		 StatusMessages msg=new StatusMessages();
		 msg.setStatus("failure");
		return new ResponseEntity<>(msg, HttpStatus.NO_CONTENT); 
	 }
	 }catch(Exception ex){
		 StatusMessages msg=new StatusMessages();
		 msg.setStatus("error");
		return new ResponseEntity<>(msg, HttpStatus.BAD_REQUEST);
	    }
	return new ResponseEntity<>(warehouse, HttpStatus.OK);
    }
	
	
	 @RequestMapping(value="/addWarehouse", method = RequestMethod.POST, consumes = "application/json")
		public ResponseEntity<?>  addWarehouse(@RequestBody VendorWareHouseMaster warehouse,HttpServletRequest req) {
		   UserMaster user=warehouseService.getVendorInto(warehouse.getUserId());
		   StatusMessages statusMessages=null;
		   if(user!=null){
			   warehouse.setVendorId(user.getVendorId());
		   warehouse.setVendorContact(user.getContactNum());
		   warehouse.setVendorEmail(user.getEmail());
		   warehouse.setStatus("Submitted");
		    statusMessages=warehouseService.addWarehouse(warehouse);
		   }
		   else{
			   statusMessages=new StatusMessages();
			   statusMessages.setStatus("Invalid User");
			   
		   }
		   if(statusMessages.equals("failure")){
		   return new ResponseEntity<>(statusMessages, HttpStatus.NO_CONTENT);  
		   }
		   else if(statusMessages.equals("error")){
			   return new ResponseEntity<>(statusMessages, HttpStatus.NOT_ACCEPTABLE);  
			 }
		   else{
			   return new ResponseEntity<>(statusMessages, HttpStatus.OK);  
		   }
		}
	 
	 @RequestMapping(value="/updateWarehouse", method = RequestMethod.POST, consumes = "application/json")
		public ResponseEntity<?>  updateWarehouse(@RequestBody VendorWareHouseMaster warehouse,HttpServletRequest req) {
		
		 StatusMessages statusMessages=null;
		 UserMaster user=warehouseService.getVendorInto(warehouse.getUserId());
		 if(user!=null){
		   warehouse.setVendorId(user.getVendorId());
		   warehouse.setVendorContact(user.getContactNum());
		   warehouse.setVendorEmail(user.getEmail());
		    statusMessages=warehouseService.updateWarehouse(warehouse);
		  }
		   else{
			   statusMessages=new StatusMessages();
			   statusMessages.setStatus("Invalid User");
			   
		   }
		//   warehouse.setStatus("Updated");
		   if(statusMessages.equals("failure")){
			   return new ResponseEntity<>(statusMessages, HttpStatus.NO_CONTENT);  
			   }
			   else if(statusMessages.equals("error")){
				   return new ResponseEntity<>(statusMessages, HttpStatus.NOT_ACCEPTABLE);  
				 }
			   else{
				   return new ResponseEntity<>(statusMessages, HttpStatus.OK);  
			   }
		}
	 
}

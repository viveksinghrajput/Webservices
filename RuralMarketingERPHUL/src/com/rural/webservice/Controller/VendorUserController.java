/*
*WebServiceRestController.java
*Created By		:Bhagya Lakshmi Mula
*Created Date	:Jul 4, 2018
*/
package com.rural.webservice.Controller;


import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.rural.Model.BatchTemp;
import com.rural.Model.DiviceMaster;
import com.rural.Model.LoginResponse;
import com.rural.Model.UserMaster;
import com.rural.webservice.DTO.StatusMessages;
import com.rural.webservice.DTO.UserReq;
import com.rural.webservice.Service.VendorUserService;

@RestController
public class VendorUserController {
	
	@Autowired
	VendorUserService userServices;
	static Logger logger =Logger.getLogger(VendorUserController.class);
	
	 @RequestMapping(value="/getStates", method = RequestMethod.POST, consumes = "application/json",produces = "application/json")
	    public   ResponseEntity<?> getStates(@RequestBody UserReq user) {
		 UserMaster u=userServices.validateUser(user.getUserId());
		 if(u==null || u.equals("null")){
			 StatusMessages msg=new StatusMessages();
			 msg.setStatus("failure");
			 return new ResponseEntity<>(msg, HttpStatus.OK);
		 }
		 else{
		 BatchTemp batch=new BatchTemp();
		 try{
		 batch=userServices.getStates();
		 if(batch.equals("null")){
			  return new ResponseEntity<>("Failure", HttpStatus.NO_CONTENT); 
		 }
		 }catch(Exception ex){
		        String errorMessage;
		        errorMessage = ex + " <== error";
		        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
		    }
		return new ResponseEntity<>(batch, HttpStatus.OK);
		 }
	    }
	 
	 @RequestMapping(value="/androidLogin", method = RequestMethod.POST, consumes = "application/json; charset=utf-8" , produces = "application/json; charset=utf-8")
		public  ResponseEntity<?> androidLogin(@RequestBody DiviceMaster diviceMaster) {
			String userName=diviceMaster.getUser();
			String password=diviceMaster.getPass();
			
			int count=0;
				count=userServices.validateUser(userName, password);
			if(count>=1)
			{
				LoginResponse loginResponse=null;
				loginResponse=userServices.getDeviceInfo(diviceMaster);
				if(loginResponse==null || loginResponse.equals("null")){
					StatusMessages msg=new StatusMessages();
					msg=new StatusMessages();
					msg.setStatus("Invalid User");		
					return new ResponseEntity<>(msg, HttpStatus.NO_CONTENT);
				}
				else{
				return new ResponseEntity<>(loginResponse, HttpStatus.OK);
				}
			} 
			else{
				StatusMessages msg=new StatusMessages();
				msg=new StatusMessages();
				msg.setStatus("Invalid User");		
				return new ResponseEntity<>(msg, HttpStatus.NO_CONTENT); 
			}
			
			  
		}
	 
}

/*
*DeliveryAndInventoryServiceImpl.java
*Created By		:Bhagya Lakshmi Mula
*Created Date	:Jul 4, 2018
*/
package com.rural.Service;

import java.io.File;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.Part;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import com.rural.Constant.IConstants;
import com.rural.DAO.DeliveryAndInventoryDAO;
import com.rural.Model.BrandMaster;
import com.rural.Model.DeliveryAndInventory;
import com.rural.Model.DeliveryAndInventoryItems;
import com.rural.Model.DeliveryAndInventoryTemp;

@Component
@Service
public class DeliveryAndInventoryServiceImpl implements DeliveryAndInventoryService {
	@Autowired
	DeliveryAndInventoryDAO deliveryAndInventoryDAO;
	
	@Override
	public Map<Integer,String> getBrands() {
		Map<Integer,String> brandMap=new LinkedHashMap<Integer, String>();
		List<BrandMaster> brandMasterList= deliveryAndInventoryDAO.getBrands();
		for(BrandMaster brandMaster:brandMasterList){
			brandMap.put(brandMaster.getBrand_id(), brandMaster.getBrandName());
		}
		return brandMap;
	}
	
	@Override
	public List<String> getStateList() {
		// TODO Auto-generated method stub
		
		return deliveryAndInventoryDAO.getStateList();
	}

	@Override
	public List<String> getCityList(String strState) {
		// TODO Auto-generated method stub
		
		return deliveryAndInventoryDAO.getCityList(strState);
	}

	@Override
	public List<String> getWarehouseList(String strCity) {
		// TODO Auto-generated method stub
		return deliveryAndInventoryDAO.getWarehouseList(strCity);
	}

	@Override
	public List<DeliveryAndInventory> createDeliveryAndInventory(
			DeliveryAndInventory deliveryAndInventory,int noOfKits,String strRemarks) {
		// TODO Auto-generated method stub
		return deliveryAndInventoryDAO.createDeliveryAndInventory(deliveryAndInventory,noOfKits,strRemarks);
	}

	@Override
	public List<DeliveryAndInventory> getAllDeliveryAndInventaries(String userRole,String userName) {
		// TODO Auto-generated method stub
		return deliveryAndInventoryDAO.getAllDeliveryAndInventaries(userRole,userName);
	}

	@Override
	public DeliveryAndInventory getDeliveryInventoryDetails(
			String strReq_Num) {
		// TODO Auto-generated method stub
		return deliveryAndInventoryDAO.getDeliveryInventoryDetails(strReq_Num);
	}

	@Override
	public List<DeliveryAndInventoryItems> updateStatus(String username,String userRole,String strStatus, String strReqNo,String StrProduser,String reachedLogi,String tempStatus) {
		// TODO Auto-generated method stub
		String statusTemp=null;
		
		/*if((userRole.equals("Production Team") || userRole.equals("Admin")) && strStatus.equals(IConstants.PRODUCTION_ACK))
		{
			statusTemp=IConstants.PRODUCTION_ACK;
		}*/
		
		if((userRole.equals("Production Team") || userRole.equals("Admin")) && strStatus.equals(IConstants.BUSINESS_SUBMITTED))
		{
			statusTemp=IConstants.PRODUCTION_ACK;
		}
		else if((userRole.equals("Logistics") || userRole.equals("Admin")) && strStatus.equals(IConstants.PRODUCTION_SUBMITTED))
		{
			statusTemp=IConstants.LOGISTICS_ACK;
		}
		else if((userRole.equals("Vendor") || userRole.equals("Admin")) && strStatus.equals(IConstants.LOGISTICS_SUBMITTED))
		{
			statusTemp=IConstants.VENDOR_ACK;
		}
		
		return deliveryAndInventoryDAO.updateStatus(username,userRole,strReqNo,statusTemp,StrProduser,reachedLogi,tempStatus);
	}

	@Override
	public List<DeliveryAndInventoryItems> updateInventories(
			DeliveryAndInventoryItems deliveryAndInventory,String userRole,String tempStatus,String destPath,DeliveryAndInventoryTemp[] obj) {
		// TODO Auto-generated method stub
		return deliveryAndInventoryDAO.updateInventories(deliveryAndInventory,userRole,tempStatus,destPath,obj);
	}

	@Override
	public void CopyUploadedFiles(String strReqNo, Part partDispatch1,
			Part partDispatch2,String DESTINATION_DIR_PATH) {
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
		 try {
			partDispatch1.write(DESTINATION_DIR_PATH + File.separator + fileName1);
		 String fileName2=extractFileName(partDispatch2);
		 partDispatch2.write(DESTINATION_DIR_PATH + File.separator + fileName2);
		 } catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	/*public  void copyPartToFile(String reqNo,Part part,String DESTINATION_DIR_PATH) throws IOException{
	    InputStream inputStream = part.getInputStream();
	    String contentType = part.getContentType();
	   // String pathname = "GradePlus/Attachment/tmpFile."+ contentType.substring(contentType.lastIndexOf('/') +1);
	    File copy = new File(DESTINATION_DIR_PATH);
	    copy.renameTo(copy);
	    OutputStream outputStream = new FileOutputStream(copy);
	    try {
	        byte[] buffer = new byte[1024];
	        int length;
	        while ((length = inputStream.read(buffer)) > 0) {
	            outputStream.write(buffer, 0, length);
	        }
	    } finally {
	        inputStream.close();
	        outputStream.close();
	    }
	}
*/
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

			@Override
			public java.sql.Date getEstimatedDeliveryDate(String reqNo) {
				// TODO Auto-generated method stub
			return	deliveryAndInventoryDAO.getEstimatedDeliveryDate(reqNo);
				
			}

			/*@Override
			public String getCollaterals(String username, String strReq_Num) {
				// TODO Auto-generated method stub
				return deliveryAndInventoryDAO.getCollaterals(username,strReq_Num);
			}
*/
			@Override
			public List<DeliveryAndInventoryItems> getDeliveryInventoryItems(String username,String userRole,String strReq_Num) {
				// TODO Auto-generated method stub
				return deliveryAndInventoryDAO.getDeliveryInventoryItems(username,userRole,strReq_Num);
			}

			/*@Override
			public List<DeliveryAndInventoryProductionUser> getUsersForLogistics(
					String strReq_Num) {
				// TODO Auto-generated method stub
				return deliveryAndInventoryDAO.getUsersForLogistics(strReq_Num);
			}*/

	/*@Override
	public List<CollateralMaster> getAllCollaterals() {
		// TODO Auto-generated method stub
		return deliveryAndInventoryDAO.getAllCollaterals();
	}*/

	
}
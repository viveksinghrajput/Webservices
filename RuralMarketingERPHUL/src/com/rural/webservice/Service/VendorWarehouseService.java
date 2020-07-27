/*
*VendorWarehouseService.java
*Created By		:Bhagya Lakshmi Mula
*Created Date	:Apr 4, 2018
*/
package com.rural.webservice.Service;

import com.rural.Model.UserMaster;
import com.rural.Model.VendorWareHouseMaster;
import com.rural.webservice.DTO.StatusMessages;
import com.rural.webservice.DTO.WarehouseResponse;

public interface VendorWarehouseService {

	WarehouseResponse getWarehouses(int strUser);

	StatusMessages addWarehouse(VendorWareHouseMaster warehouse);
	StatusMessages updateWarehouse(VendorWareHouseMaster warehouse);

	UserMaster getVendorInto(String userId);

}

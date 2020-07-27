/*
*WarehouseDAO.java
*Created By		:Bhagya Lakshmi Mula
*Created Date	:Apr 4, 2018
*/
package com.rural.webservice.DAO;

import org.springframework.stereotype.Component;

import com.rural.Model.UserMaster;
import com.rural.Model.VendorWareHouseMaster;
import com.rural.webservice.DTO.StatusMessages;
import com.rural.webservice.DTO.WarehouseResponse;

@Component
public interface WarehouseDAO {

	WarehouseResponse getWarehouses(int userId);

	StatusMessages addWarehouse(VendorWareHouseMaster warehouse);
	StatusMessages updateWarehouse(VendorWareHouseMaster warehouse);

	UserMaster getVendorInto(String userId);

}

/*
*WarehouseResponse.java
*Created By		:Bhagya Lakshmi Mula
*Created Date	:Apr 4, 2018
*/
package com.rural.webservice.DTO;

import java.util.List;

public class WarehouseResponse {
		private String status;
	    private List<WarehouseTemp> warehouses;
		public String getStatus() {
			return status;
		}
		public void setStatus(String status) {
			this.status = status;
		}
		public List<WarehouseTemp> getWarehouses() {
			return warehouses;
		}
		public void setWarehouses(List<WarehouseTemp> warehouses) {
			this.warehouses = warehouses;
		}
	    

}

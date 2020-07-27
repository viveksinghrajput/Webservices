/*
*WarehouseDAOImpl.java
*Created By		:Bhagya Lakshmi Mula
*Created Date	:Apr 4, 2018
*/
package com.rural.webservice.DAO;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.annotation.Resource;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import com.rural.Model.Photo;
import com.rural.Model.UserMaster;
import com.rural.Model.VendorWareHouseMaster;
import com.rural.Model.WareHouseHistory;
import com.rural.exceptions.ERPException;
import com.rural.webservice.DTO.StatusMessages;
import com.rural.webservice.DTO.WarehouseResponse;
import com.rural.webservice.DTO.WarehouseTemp;

@Component
@Repository
public class WarehouseDAOImpl implements WarehouseDAO{
	@Resource(name = "sessionFactory")
	protected SessionFactory sessionFactory;
	
	static Logger logger =Logger.getLogger(WarehouseDAOImpl.class);

	@Override
	public WarehouseResponse getWarehouses(int userId) {
		logger.debug("=====START WarehouseDAOImpl getWarehouses======");
		WarehouseResponse response=new WarehouseResponse();
		List<VendorWareHouseMaster> listWarehouse=null;
		WarehouseTemp warehouse=null;
		List<WarehouseTemp> warehouseList=null;
		Session session = null;
		try {
			session = sessionFactory.openSession();
			listWarehouse = new ArrayList<VendorWareHouseMaster>();
			warehouseList=new ArrayList<WarehouseTemp>();
			Query query = session.createSQLQuery("CALL GET_WAREHOUSELIST(:user)").addEntity(VendorWareHouseMaster.class)
					.setParameter("user", userId);
			listWarehouse = query.list();
			for(VendorWareHouseMaster tempWarehouse:listWarehouse){
				warehouse=new WarehouseTemp();
				warehouse.setTrackId(tempWarehouse.getTrackId());
				warehouse.setId(tempWarehouse.getId());
				warehouse.setName(tempWarehouse.getName());
				warehouse.setStatus(tempWarehouse.getStatus());
				warehouse.setAddr2(tempWarehouse.getAddr1());
				warehouse.setAddr1(tempWarehouse.getAddr2());
				warehouse.setStateId(tempWarehouse.getStateId());
				warehouse.setCityId(tempWarehouse.getCityId());
				warehouse.setPin(tempWarehouse.getPin());
				warehouse.setRemarks(tempWarehouse.getRemarks());
				warehouse.setArea(tempWarehouse.getArea());
				warehouse.setUserId(userId);
				
				 java.util.Date newDate=tempWarehouse.getLastEditedDate();
		    	// Create an instance of SimpleDateFormat used for formatting 
		    	// the string representation of date (month/day/year)
		    	DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
		    	// Get the date today using Calendar object.
		    	java.util.Date today = Calendar.getInstance().getTime();   
		    	// Using DateFormat format method we can create a string 
		    	// representation of a date with the defined format.
		    /*	String reportDate = df.format(today);
		    	System.out.println("reportDate:::: "+reportDate);*/
		    	String strDate=df.format(newDate);
		    	warehouse.setTime(strDate);
				warehouseList.add(warehouse);
			}
			response.setStatus("success");
			response.setWarehouses(warehouseList);
			logger.debug("======END WarehouseDAOImpl getWarehouses======");
		} catch (HibernateException he) {
			logger.error("Exception occured while loading Warehouses :"+ he.getMessage());
			logger.error("Exception occured while loading Warehouses :"+ he.getMessage());
		} catch (ERPException erp) {
			logger.error("Exception occured while loading Warehouses :"+ erp.getMessage());
			logger.error("Exception occured while loading Warehouses :"+ erp.getMessage());
		} catch (Exception e) {
			logger.error("Exception occured while loading Warehouses :"+ e.getMessage());
			logger.error("Exception occured while loading Warehouses :"+ e.getMessage());
		} finally {
			session.close();
		}
		return response;
	}
	
	@Override
	public UserMaster getVendorInto(String userId) {
		UserMaster userMaster=null;
		logger.debug("=====START WarehouseDAOImpl getVendorInto======");
		Session session = null;
		try {
			session = sessionFactory.openSession();
			Query query = session.createSQLQuery("CALL GET_VENDOR_INFO(:id)").addEntity(UserMaster.class).setParameter("id", userId);
			List<UserMaster> list = query.list();
			Iterator<UserMaster> itr = list.iterator();
			while (itr.hasNext()) {
				userMaster = new UserMaster();
				userMaster = itr.next();
			}
			logger.debug("=====START WarehouseDAOImpl getVendorInto======");
		} catch (HibernateException he) {
			logger.error("Exception occured while loading user inforamtion:"+ he.getMessage());
			throw new ERPException("Exception occured while loading user inforamtion:"+ he.getMessage());
		} catch (ERPException erp) {
			logger.error("Exception occured while loading user inforamtion:"+ erp.getMessage());
			throw new ERPException("Exception occured while loading user inforamtion:"+ erp.getMessage());
		} catch (Exception e) {
			logger.error("Exception occured while loading user inforamtion:"+ e.getMessage());
			throw new ERPException("Exception occured while loading user inforamtion:"+ e.getMessage());
		} finally {
			session.close();
		}
		return userMaster;
	}


	@Override
	public StatusMessages addWarehouse(VendorWareHouseMaster warehouse) {
		logger.debug("=====START WarehouseDAOImpl addWarehouse======");
		Session session = null;
		 Transaction transaction = null;
		 StatusMessages statusMessages=new StatusMessages();
		try {
			session = sessionFactory.openSession();
			
	      transaction = session.beginTransaction();
	    List<Photo> photoList=warehouse.getPhotos();
	    WareHouseHistory history=new WareHouseHistory();
	    history.setTrackId(warehouse.getTrackId());
	    history.setVendorId(warehouse.getVendorId());
    	history.setVendorContact(warehouse.getVendorContact());
    	history.setVendorEmail(warehouse.getVendorEmail());
    	history.setWarehouseName(warehouse.getName());
    	history.setWarehouseStatus(warehouse.getStatus());
    	history.setWareHouseAddress01(warehouse.getAddr1());
    	history.setWareHouseAddress02(warehouse.getAddr2());
    	history.setWareHouseAreainSqft(warehouse.getArea());
    	history.setStateId(warehouse.getStateId());
    	history.setCityId(warehouse.getCityId());
    	history.setPinCode(warehouse.getPin());
	   /* for(Photo photo:photoList)
	    {
	    	*/
	    	history.setWareHouseOuterImage(photoList.get(0).getName());
	    	history.setLatOuter(photoList.get(0).getLat());
	    	history.setLonOuter(photoList.get(0).getLon());
	    	
	    	history.setWareHouseInnerImage01(photoList.get(1).getName());
	    	history.setLatInner1(photoList.get(1).getLat());
	    	history.setLonInner1(photoList.get(1).getLon());
	    	
	    	history.setWareHouseInnerImage02(photoList.get(2).getName());
	    	history.setLatInner2(photoList.get(2).getLat());
	    	history.setLonInner2(photoList.get(2).getLon());
	    /*}
	    */
	    	history.setCreatedDate(warehouse.getTime());
	    	history.setComment(warehouse.getComment());
	   
	     warehouse.setStatus("Submitted");
	     session.save(warehouse);
	     session.save(history);
	     statusMessages.setStatus("success");
	     transaction.commit();
	     logger.debug("=====END WarehouseDAOImpl addWarehouse======");
		} catch (HibernateException he) {
			if (transaction != null) {
		        transaction.rollback();
		      }
			 statusMessages.setStatus("failure");
			 he.printStackTrace();
			logger.error("Exception occured while adding warehouse :"+ he.getMessage());
			throw new ERPException("Exception occured while adding warehouse :"+ he.getMessage());
		} catch (ERPException erp) {
			if (transaction != null) {
		        transaction.rollback();
		      }
			logger.error("Exception occured while adding warehouse :"+ erp.getMessage());
			throw new ERPException("Exception occured while adding warehouse :"+ erp.getMessage());
		} catch (Exception e) {
			if (transaction != null) {
		        transaction.rollback();
		      }
		      e.printStackTrace();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return statusMessages;
	}

	@Override
	public StatusMessages updateWarehouse(VendorWareHouseMaster warehouse) {
		 logger.debug("=====START WarehouseDAOImpl updateWarehouse======");
		Session session = null;
		 Transaction transaction = null;
		 StatusMessages statusMessages=new StatusMessages();
		try {
			session = sessionFactory.openSession();
	      transaction = session.beginTransaction();
	       
	     List<Photo> photoList=warehouse.getPhotos();
	     warehouse.setStatus("Updated");
	     warehouse.setLastEditedDate(new Date());
	     
		    WareHouseHistory history=new WareHouseHistory();
		    history.setTrackId(warehouse.getTrackId());
		    history.setVendorId(warehouse.getVendorId());
	    	history.setVendorContact(warehouse.getVendorContact());
	    	history.setVendorEmail(warehouse.getVendorEmail());
	    	history.setWarehouseName(warehouse.getName());
	    	history.setWarehouseStatus(warehouse.getStatus());
	    	history.setWareHouseAddress01(warehouse.getAddr1());
	    	history.setWareHouseAddress02(warehouse.getAddr2());
	    	history.setWareHouseAreainSqft(warehouse.getArea());
	    	history.setStateId(warehouse.getStateId());
	    	history.setCityId(warehouse.getCityId());
	    	history.setPinCode(warehouse.getPin());
		   /* for(Photo photo:photoList)
		    {
		    	*/
		    	history.setWareHouseOuterImage(photoList.get(0).getName());
		    	history.setLatOuter(photoList.get(0).getLat());
		    	history.setLonOuter(photoList.get(0).getLon());
		    	
		    	history.setWareHouseInnerImage01(photoList.get(1).getName());
		    	history.setLatInner1(photoList.get(1).getLat());
		    	history.setLonInner1(photoList.get(1).getLon());
		    	
		    	history.setWareHouseInnerImage02(photoList.get(2).getName());
		    	history.setLatInner2(photoList.get(2).getLat());
		    	history.setLonInner2(photoList.get(2).getLon());
		    /*}
		    */
		    	history.setCreatedDate(warehouse.getTime());
		    	history.setComment(warehouse.getComment());
		    	
		    	
	     session.update(warehouse);
	     
	     for(Photo photo:photoList){
    	     session.update(photo);
	     }
	     
	     session.save(history);
	     transaction.commit();
	     statusMessages.setStatus("success");
	     logger.debug("=====END WarehouseDAOImpl updateWarehouse======");
		} catch (HibernateException he) {
			if (transaction != null) {
		        transaction.rollback();
		      }
			 statusMessages.setStatus("failure");
			 he.printStackTrace();
			logger.error("Exception occured while updating the warehouse :"+ he.getMessage());
			throw new ERPException("Exception occured while updating the warehouse :"+ he.getMessage());
		} catch (ERPException erp) {
			if (transaction != null) {
		        transaction.rollback();
		      }
			logger.error("Exception occured while updating the warehouse :"+ erp.getMessage());
			throw new ERPException("Exception occured while updating the warehouse :"+ erp.getMessage());
		} catch (Exception e) {
			if (transaction != null) {
		        transaction.rollback();
		      }
			logger.error("Exception occured while updating the warehouse :"+ e.getMessage());
			throw new ERPException("Exception occured while updating the warehouse :"+ e.getMessage());
		} finally {
			session.close();
		}
		return statusMessages;
	}

}

/*
*DeliveryAndInventoryDAOImpl.java
*Created By		:Bhagya Lakshmi Mula
*Created Date	:Jul 4, 2018
*/
package com.rural.DAO;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.rural.Constant.IConstants;
import com.rural.Model.BrandMaster;
import com.rural.Model.DeliveryAndInventory;
import com.rural.Model.DeliveryAndInventoryItems;
import com.rural.Model.DeliveryAndInventoryTemp;
import com.rural.exceptions.ERPException;
import com.rural.utility.ConnectionManager;

@Component
@Repository
public class DeliveryAndInventoryDAOImpl implements DeliveryAndInventoryDAO {
	
	@Resource(name = "sessionFactory")
	protected SessionFactory sessionFactory;
	
	static Logger logger =Logger.getLogger(DeliveryAndInventoryDAOImpl.class);
	
	@Override
	public List<BrandMaster> getBrands() {
		logger.debug("======START DeliveryAndInventoryDAOImpl getBrands======");
		List<BrandMaster> listBrands = null;
		Session session = null;
		try {
			session = sessionFactory.openSession();
			listBrands = new ArrayList<BrandMaster>();
			Query query = session.createSQLQuery("CALL GET_BRANDSFORDELIVERYINVENTORY()").addEntity(BrandMaster.class);
			listBrands = query.list();
		} catch (HibernateException he) {
			logger.error("Exception occured while loading brands :"+ he.getMessage());
			throw new ERPException("Exception occured while loading brands :"+ he.getMessage());
		} catch (ERPException erp) {
			logger.error("Exception occured while loading brands :"+ erp.getMessage());
			throw new ERPException("Error while loading brands");
		} catch (Exception e) {
			logger.error("Exception occured while loading brands :"+ e.getMessage());
			throw new ERPException("Error while loading brands");
		} finally {
			session.close();
		}
		logger.debug("=====END DeliveryAndInventoryDAOImpl getBrands======");
		return listBrands;
	}

	
	@Override
	public List<String> getStateList() {
		logger.debug("=====START DeliveryAndInventoryDAOImpl getStateList======");
		List<String> listStates = null;
		Session session = null;
		try {
			session = sessionFactory.openSession();
			listStates = new ArrayList<String>();
			Query query = session.createSQLQuery("CALL Get_States(:module)")
					.setParameter("module", "DeliveryAndInventory");
			listStates = query.list();
			logger.debug("=====END DeliveryAndInventoryDAOImpl getStateList======");
			} catch (HibernateException he) {
			logger.error("Exception occured while loading states :"+ he.getMessage());
			throw new ERPException("Exception occured while loading states :"+ he.getMessage());
		} catch (ERPException erp) {
			logger.error("Exception occured while loading states :"+ erp.getMessage());
			throw new ERPException("Error while loading states");
		} catch (Exception e) {
			logger.error("Exception occured while loading states :"+ e.getMessage());
			throw new ERPException("Error while loading states");
		} finally {
			session.close();
		}
		return listStates;
	}
	
	@Override
	public List<String> getCityList(String strState) {
		logger.debug("=====START DeliveryAndInventoryDAOImpl getCityList======");
		List<String> listVendors = null;
		Session session = null;
		try {
			session = sessionFactory.openSession();
			listVendors = new ArrayList<String>();
			Query query = session.createSQLQuery("CALL GET_CITYLIST(:state,:module)")
					.setParameter("state", strState)
					.setParameter("module", "DeliveryAndInventroy");
			listVendors = query.list();
			logger.debug("=====END DeliveryAndInventoryDAOImpl getCityList======");
		} catch (HibernateException he) {
			logger.error("Exception occured while loading cities :"+ he.getMessage());
			throw new ERPException("Exception occured while loading cities :"+ he.getMessage());
		} catch (ERPException erp) {
			logger.error("Exception occured while loading cities :"+ erp.getMessage());
			throw new ERPException("Error while loading cities");
		} catch (Exception e) {
			logger.error("Exception occured while loading cities :"+ e.getMessage());
			throw new ERPException("Error while loading cities");
		} finally {
			session.close();
		}
		return listVendors;
	}

	@Override
	public List<String> getWarehouseList(String strCity) {
		logger.debug("=====START DeliveryAndInventoryDAOImpl getWarehouseList======");
		List<String> warehouseList = null;
		Session session = null;
		try {
			session = sessionFactory.openSession();
			warehouseList = new ArrayList<String>();
			Query query = session.createSQLQuery("CALL GET_WAREHOUSELISTONCITY(:city)")
					.setParameter("city", strCity);
			warehouseList = query.list();
			logger.debug("=====END DeliveryAndInventoryDAOImpl getWarehouseList======");
		} catch (HibernateException he) {
			logger.error("Exception occured while loading Warehouses :"+ he.getMessage());
			throw new ERPException("Exception occured while loading Warehouses :" + he.getMessage());
		} catch (ERPException erp) {
			logger.error("Exception occured while loading Warehouses :"+ erp.getMessage());
			throw new ERPException("Error while loading Warehouses");
		} catch (Exception e) {
			logger.error("Exception occured while loading Warehouses :"+ e.getMessage());
			throw new ERPException("Error while loading Warehouses");
		} finally {
			session.close();
		}
		return warehouseList;
	
	}


	@Override
	public List<DeliveryAndInventory> createDeliveryAndInventory(
			DeliveryAndInventory deliveryAndInventory,int noOfKits,String strRemarks) {
		List<DeliveryAndInventory> deliveryAndInventorList=null;
		logger.debug("=====START DeliveryAndInventoryDAOImpl createDeliveryAndInventory======");
		Session session = null;
		try {
			session = sessionFactory.openSession();
			deliveryAndInventorList = new ArrayList<DeliveryAndInventory>();
			Query query = session
					.createSQLQuery(
							"CALL CREATE_DELIVERYANDIVENTORY(:brand,:campaign,:state,:city,:warehouse,:no_of_kits,:remarks)")
					.addEntity(DeliveryAndInventory.class)
					.setParameter("brand", deliveryAndInventory.getBrand())
					.setParameter("campaign", deliveryAndInventory.getCampaign())
					.setParameter("state", deliveryAndInventory.getState())
					.setParameter("city", deliveryAndInventory.getCity())
					.setParameter("warehouse", deliveryAndInventory.getWarehouse())
					.setParameter("no_of_kits", noOfKits)
					.setParameter("remarks", strRemarks);
			List<DeliveryAndInventory> list = query.list();
			Iterator<DeliveryAndInventory> itr = list.iterator();
			while (itr.hasNext()) {

				DeliveryAndInventory delivery = new DeliveryAndInventory();
				delivery = itr.next();
				deliveryAndInventorList.add(delivery);

			}
			logger.debug("=====ENd DeliveryAndInventoryDAOImpl createDeliveryAndInventory======");
		} catch (HibernateException he) {
			logger.error("Exception occured while creating Delivery And Inventory :"+ he.getMessage());
			throw new ERPException("Exception occured while creating Delivery And Inventory :"+ he.getMessage());
		} catch (ERPException erp) {
			logger.error("Exception occured while creating Delivery And Inventory :"+ erp.getMessage());
			throw new ERPException("Error while creating Delivery And Inventory");
		} catch (Exception e) {
			logger.error("Exception occured while creating Delivery And Inventory :"+ e.getMessage());
			throw new ERPException("Error while creating Delivery And Inventory");
		} finally {
			session.close();
		}
		return deliveryAndInventorList;
	}


	@Override
	public List<DeliveryAndInventory> getAllDeliveryAndInventaries(String userRole,String userName) {
		List<DeliveryAndInventory> deliveryAndInventorList=null;
		logger.debug("=====START DeliveryAndInventoryDAOImpl getAllDeliveryAndInventaries====== ");
		Session session = null;
		String strStatus=null;
		try {
			session = sessionFactory.openSession();
			deliveryAndInventorList = new ArrayList<DeliveryAndInventory>();
			Query query = session
					.createSQLQuery(
							"CALL GET_ALL_DELIVERYANDINVENTORIES(:role,:user)")
					.addEntity(DeliveryAndInventory.class)
		.setParameter("role",userRole)
		.setParameter("user",userName);
		List<DeliveryAndInventory> list = query.list();
		if(list!=null){
			Iterator<DeliveryAndInventory> itr = list.iterator();
			while (itr.hasNext()) {
				DeliveryAndInventory deliveryAndInventory = new DeliveryAndInventory();
				deliveryAndInventory = itr.next();
				deliveryAndInventorList.add(deliveryAndInventory);

			}
		}
			logger.debug("=====END DeliveryAndInventoryDAOImpl getAllDeliveryAndInventaries====== ");
		} catch (HibernateException he) {
			logger.error("Exception occured while loading delivery and inventories:"+ he.getMessage());
			throw new ERPException("Exception occured while creating Delivery And Inventory :"+ he.getMessage());
		} catch (ERPException erp) {
			logger.error("Exception occured while loading delivery and inventories:"+ erp.getMessage());
			throw new ERPException("Error while creating Delivery And Inventory");
		} catch (Exception e) {
			logger.error("Exception occured while loading delivery and inventories:"+ e.getMessage());
			throw new ERPException("Error while creating Delivery And Inventory");
		} finally {
			session.close();
		}
		return deliveryAndInventorList;
	}


	@Override
	public DeliveryAndInventory getDeliveryInventoryDetails(
			String strReq_Num) {
		DeliveryAndInventory deliveryAndInventory=null;
		logger.debug("=====START DeliveryAndInventoryDAOImpl getDeliveryInventoryDetails======");
		Session session = null;
		try {
			session = sessionFactory.openSession();
			deliveryAndInventory = new DeliveryAndInventory();
			Query query = session
					.createSQLQuery(
							"CALL GET_DELIVERYANDINVENTORIES(:req_num)")
					.addEntity(DeliveryAndInventory.class)
					.setParameter("req_num", strReq_Num);
			List<DeliveryAndInventory> list = query.list();
			Iterator<DeliveryAndInventory> itr = list.iterator();
			while (itr.hasNext()) {
				deliveryAndInventory = itr.next();
			}
			logger.debug("=====END DeliveryAndInventoryDAOImpl getDeliveryInventoryDetails======");
		} catch (HibernateException he) {
			logger.error("Exception occured while loading delivery and inventories header:"+ he.getMessage());
			throw new ERPException( "Exception occured while creating Delivery And Inventory header:" + he.getMessage());
		} catch (ERPException erp) {
			logger.error("Exception occured while loading delivery and inventories header:"+ erp.getMessage());
			throw new ERPException("Error while creating Delivery And Inventory");
		} catch (Exception e) {
			logger.error("Exception occured while loading delivery and inventories header:"+ e.getMessage());
			throw new ERPException("Error while creating Delivery And Inventory");
		} finally {
			session.close();
		}
		return deliveryAndInventory;
	}


	@Override
	public List<DeliveryAndInventoryItems> updateStatus(String username,String userRole,String strReqNo, String strStatus,String StrProduser,String reachedLogi,String tempStatus) {
		DeliveryAndInventoryItems deliveryAndInventoryItems=null;
		List<DeliveryAndInventoryItems> items=new ArrayList<DeliveryAndInventoryItems>();
		logger.debug("======START DeliveryAndInventoryDAOImpl updateStatus======");
		Session session = null;	
		try {
			session = sessionFactory.openSession();
			deliveryAndInventoryItems = new DeliveryAndInventoryItems();
			Query query = session
					.createSQLQuery(
							"CALL UPDATESTATUSINVENTORIES(:user,:role,:req_num,:status,:produser,:reachedLogi,:tempStatus)")
					.addEntity(DeliveryAndInventoryItems.class)
					.setParameter("user", username)
					.setParameter("role", userRole)
					.setParameter("req_num", strReqNo)
					.setParameter("status", strStatus)
					.setParameter("produser", StrProduser)
					.setParameter("reachedLogi", reachedLogi)
					.setParameter("tempStatus", tempStatus);
			List<DeliveryAndInventoryItems> list = query.list();
			Iterator<DeliveryAndInventoryItems> itr = list.iterator();
			while (itr.hasNext()) {

				deliveryAndInventoryItems = itr.next();
				items.add(deliveryAndInventoryItems);

			}
			logger.debug("======END DeliveryAndInventoryDAOImpl updateStatus======");
		} catch (HibernateException he) {
			logger.error("Exception occured while updating the status:"+ he.getMessage());
			throw new ERPException("Exception occured while updating the status :"+ he.getMessage());
		} catch (ERPException erp) {
			logger.error("Exception occured while updating the status :"+ erp.getMessage());
			throw new ERPException("Error while updating the status");
		} catch (Exception e) {
			logger.error("Exception occured while updating the status :"+ e.getMessage());
			throw new ERPException("Error while updating the status");
		} finally {
			session.close();
		}
		return items;
	}


	@Override
	public List<DeliveryAndInventoryItems> updateInventories(
			DeliveryAndInventoryItems deliveryInventory,String userRole,String tempStatus,String destPath,DeliveryAndInventoryTemp[] obj) {
		List<DeliveryAndInventoryItems> items=new ArrayList<DeliveryAndInventoryItems>();
		DeliveryAndInventoryItems deliveryAndInventory=null;
		logger.debug("======START DeliveryAndInventoryDAOImpl updateInventories======");
		Session session = null;
		Query query =null;
		List<DeliveryAndInventoryItems> list=new ArrayList<DeliveryAndInventoryItems>();
		try {
			
			session = sessionFactory.openSession();
			if(obj==null || obj.length<=1){
			if((deliveryInventory.getStatus().equals(IConstants.PRODUCTION_ACK) || deliveryInventory.getStatus().equals(IConstants.BUSINESS_SUBMITTED) ) && !userRole.equals("Logistics")){
				 query = session
						.createSQLQuery(
								"CALL UPDATEINVENTORIES(:reqNo,:param1,:param2,:status,:remarks,:path,:user,:reachedlogi,:team,:role,:tempstatus)")
						.addEntity(DeliveryAndInventoryItems.class)
						.setParameter("reqNo", deliveryInventory.getReq_Id())
				        .setParameter("param1", deliveryInventory.getItem_Produced())
						.setParameter("param2", deliveryInventory.getItem_Dispatch_Prod())
						.setParameter("status", deliveryInventory.getStatus())
						.setParameter("remarks", deliveryInventory.getProd_Remarks_produced())
						.setParameter("path","")
						.setParameter("user",deliveryInventory.getProd_users())
						.setParameter("reachedlogi",deliveryInventory.getReachedLogi())
						.setParameter("team","Production")
						.setParameter("role",userRole)
						.setParameter("tempstatus",tempStatus);
			}
			if((deliveryInventory.getStatus().equals(IConstants.PRODUCTION_COMPLETED)) && !userRole.equals("Logistics")) {
				
				 query = session
						 .createSQLQuery(
									"CALL UPDATEINVENTORIES(:reqNo,:param1,:param2,:status,:remarks,:path,:user,:reachedlogi,:team,:role,:tempstatus)")
						.addEntity(DeliveryAndInventoryItems.class)
						.setParameter("reqNo", deliveryInventory.getReq_Id())
						.setParameter("param1", "")
						.setParameter("param2", deliveryInventory.getItem_Dispatch_Prod())
						.setParameter("status", deliveryInventory.getStatus())
						.setParameter("remarks", deliveryInventory.getProd_Remarks_dispatched())
						.setParameter("path","")
						.setParameter("user",deliveryInventory.getProd_users())
						.setParameter("reachedlogi",deliveryInventory.getReachedLogi())
						.setParameter("team","Production")
						.setParameter("role",userRole)
						.setParameter("tempstatus",tempStatus);

				
			}
			if(obj!=null && obj.length==1){
			if(deliveryInventory.getStatus().equals(IConstants.LOGISTICS_ACK) || deliveryInventory.getStatus().equals(IConstants.PRODUCTION_SUBMITTED) || tempStatus.equals(IConstants.LOGISTICS_ACK)){
				 query = session
						 .createSQLQuery(
									"CALL UPDATEINVENTORIES(:reqNo,:param1,:param2,:status,:remarks,:path,:user,:reachedlogi,:team,:role,:tempstatus)")
						.addEntity(DeliveryAndInventoryItems.class)
						.setParameter("reqNo", deliveryInventory.getReq_Id())
						.setParameter("param1", obj[0].getNo_of_kits_received()==null?"":obj[0].getNo_of_kits_received())
						.setParameter("param2", obj[0].getNo_of_kits_dispatched_logi()==null?"":obj[0].getNo_of_kits_dispatched_logi())
						.setParameter("status", deliveryInventory.getStatus())
						.setParameter("remarks",obj[0].getLogi_remarks())
						.setParameter("path",destPath)
.setParameter("user",obj[0].getUser())
.setParameter("reachedlogi",deliveryInventory.getReachedLogi())
.setParameter("team","Logistics")
.setParameter("role",userRole)
.setParameter("tempstatus",tempStatus);
				
			}
			}
			if(deliveryInventory.getStatus().equals(IConstants.LOGISTICS_COMPLETED)){
				
				 query = session
						 .createSQLQuery(
									"CALL UPDATEINVENTORIES(:reqNo,:param1,:param2,:status,:remarks,:path,:user,:reachedlogi,:team,:role,:tempstatus)")
						.addEntity(DeliveryAndInventoryItems.class)
						.setParameter("reqNo", deliveryInventory.getReq_Id())
						.setParameter("param1", "")
						.setParameter("param2", deliveryInventory.getItem_Dispatch_logi())
						.setParameter("status", deliveryInventory.getStatus())
						.setParameter("remarks", deliveryInventory.getLogist_Remarks_dispatched())
						.setParameter("path",destPath)
.setParameter("user",deliveryInventory.getProd_users())
.setParameter("reachedlogi",deliveryInventory.getReachedLogi())
.setParameter("team","Logistics")
.setParameter("role",userRole)
.setParameter("tempstatus",tempStatus);
				
			}
			if(deliveryInventory.getStatus().equals(IConstants.VENDOR_ACK)){
				
				 query = session
						 .createSQLQuery(
									"CALL UPDATEINVENTORIES(:reqNo,:param1,:param2,:status,:remarks,:path,:user,:reachedlogi,:team,:role,:tempstatus)")
						.addEntity(DeliveryAndInventoryItems.class)
						.setParameter("reqNo", deliveryInventory.getReq_Id())
						.setParameter("param1", "")
						.setParameter("param2", deliveryInventory.getItem_Reveived_Vend())
						.setParameter("status", deliveryInventory.getStatus())
						.setParameter("remarks", deliveryInventory.getVendor_Remarks())
						.setParameter("path", destPath)
.setParameter("user",deliveryInventory.getProd_users())
.setParameter("reachedlogi",deliveryInventory.getReachedLogi())
.setParameter("team","Logistics")
.setParameter("role",userRole)
.setParameter("tempstatus",tempStatus);

			}
			deliveryAndInventory = new DeliveryAndInventoryItems();
		
			list = query.list();
			}
			else{
					for(int i=0;i<obj.length;i++){
						 query = session
								 .createSQLQuery(
											"CALL UPDATEINVENTORIESFORLOGI(:reqNo,:param1,:param2,:status,:remarks,:path,:user,:reachedlogi,:team,:role,:tempstatus)")
								.addEntity(DeliveryAndInventoryItems.class)
								.setParameter("reqNo", deliveryInventory.getReq_Id())
								.setParameter("param1", obj[i].getNo_of_kits_received()==null?"":obj[i].getNo_of_kits_received())
								.setParameter("param2", obj[i].getNo_of_kits_dispatched_logi()==null?"":obj[i].getNo_of_kits_dispatched_logi())
								.setParameter("status", deliveryInventory.getStatus())
								.setParameter("remarks", obj[i].getLogi_remarks()==null?"":obj[i].getLogi_remarks())
								.setParameter("path",destPath)
		.setParameter("user",obj[i].getUser())
		.setParameter("reachedlogi",deliveryInventory.getReachedLogi())
		.setParameter("team","Logistics")
		.setParameter("role",userRole)
		.setParameter("tempstatus",tempStatus);
						 deliveryAndInventory = new DeliveryAndInventoryItems();
							
							 list = query.list();
							
					}
				}
			Iterator<DeliveryAndInventoryItems> itr = list.iterator();
			while (itr.hasNext()) {
				deliveryAndInventory = itr.next();
				items.add(deliveryAndInventory);

			}
			logger.debug("======END DeliveryAndInventoryDAOImpl updateInventories======");
		} catch (HibernateException he) {
			logger.error("Exception occured while updating Delivery And Inventory :" 	+ he.getMessage());
			throw new ERPException( "Exception occured while updating Delivery And Inventory :"+ he.getMessage());
		} catch (ERPException erp) {
			logger.error("Exception occured while updating Delivery And Inventory :" 	+ erp.getMessage());
			throw new ERPException("Error while updating Delivery And Inventory");
		} catch (Exception e) {
			logger.error("Exception occured while updating Delivery And Inventory :" 	+ e.getMessage());
			throw new ERPException("Error while updating Delivery And Inventory");
		} finally {
			session.close();
		}
		return items;
	}


	@Override
	public java.sql.Date getEstimatedDeliveryDate(String reqNo) {
		logger.debug("======START DeliveryAndInventoryDAOImpl getEstimatedDeliveryDate======");
		Connection con=null;
		CallableStatement cStmt=null;
		java.sql.Date estimatedDate=null;
		try {
			con=ConnectionManager.getConnection();
			 cStmt =con.prepareCall("{call GETESTIMATEDDATE(?,?)}");
			 cStmt.setString(1, reqNo);
			 cStmt.registerOutParameter(2, java.sql.Types.DATE);

			// execute getDBUSERByUserId store procedure
			 cStmt.executeUpdate();
			  estimatedDate = cStmt.getDate(2);
				logger.debug("======END DeliveryAndInventoryDAOImpl getEstimatedDeliveryDate======");
		} catch (HibernateException he) {
			logger.error("Exception occured while getting getEstimatedDeliveryDate :" + he.getMessage());
			throw new ERPException( "Exception occured while getting getEstimatedDeliveryDate :" + he.getMessage());
		} catch (ERPException erp) {
			logger.error("Exception occured while getting getEstimatedDeliveryDate :" + erp.getMessage());
			throw new ERPException( "Exception occured while getting getEstimatedDeliveryDate :" + erp.getMessage());
		} catch (Exception e) {
			logger.error("Exception occured while getting getEstimatedDeliveryDate :" + e.getMessage());
			throw new ERPException( "Exception occured while getting getEstimatedDeliveryDate :" + e.getMessage());
		} finally {
			try {
				cStmt.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		return estimatedDate;
	
	}


	@Override
	public List<DeliveryAndInventoryItems> getDeliveryInventoryItems(String username,String userRole,String strReq_Num) {
		DeliveryAndInventoryItems deliveryAndInventoryItems=null;
		List<DeliveryAndInventoryItems> items=new LinkedList<DeliveryAndInventoryItems>();
		logger.debug("======START DeliveryAndInventoryDAOImpl getDeliveryInventoryItems======");
		Session session = null;
		try {
			session = sessionFactory.openSession();
			deliveryAndInventoryItems = new DeliveryAndInventoryItems();
			Query query = session
					.createSQLQuery(
							"CALL GET_DELIVERYANDINVENTORYITEMS(:user,:role,:req_num)")
					.addEntity(DeliveryAndInventoryItems.class)
					.setParameter("user", username)
					.setParameter("role", userRole)
					.setParameter("req_num", strReq_Num);
			List<DeliveryAndInventoryItems> list  = query.list();
			Iterator<DeliveryAndInventoryItems> itr = list.iterator();
			while (itr.hasNext()) {
				deliveryAndInventoryItems =(DeliveryAndInventoryItems) itr.next();
				items.add(deliveryAndInventoryItems);

			}
		logger.debug("======END DeliveryAndInventoryDAOImpl getDeliveryInventoryItems======");
		} catch (HibernateException he) {
			logger.error("Exception occured while getting Delivery And Inventory Items :"+ he.getMessage());
			throw new ERPException("Exception occured while getting Delivery And Inventory Items :"+ he.getMessage());
		} catch (ERPException erp) {
			logger.error("Exception occured while getting Delivery And Inventory Items :"+ erp.getMessage());
			throw new ERPException("Error while getting Delivery And Inventory Items :");
		} catch (Exception e) {
			logger.error("Exception occured while getting Delivery And Inventory Items :"+ e.getMessage());
			throw new ERPException("Error while getting Delivery And Inventory Items :");
		} finally {
			session.close();
		}
		return items;
	}

}

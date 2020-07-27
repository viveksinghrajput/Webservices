/*
*VendorWareHouseDAOImpl.java
*Created By		:Kamal Thapa
*Created Date	:Mar 9, 2018
*/
package com.rural.DAO;

import java.sql.CallableStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.annotation.Resource;
import javax.transaction.Transactional;
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
import com.rural.utility.ConnectionManager;

@Component
@Repository
@Transactional
public class VendorWareHouseDAOImpl implements VendorWareHouseDAO{

	
	@Resource(name="sessionFactory")
    protected SessionFactory sessionFactory;
	static Logger logger =Logger.getLogger(VendorWareHouseDAOImpl.class);
	
	@SuppressWarnings("unchecked")
	@Override
	public List<VendorWareHouseMaster> allWareHouse() {
		logger.debug("======START VendorWareHouseDAOImpl allWareHouse======");
		List<VendorWareHouseMaster> output=new ArrayList<VendorWareHouseMaster>();
		Session session=null;
		try{
		 session = sessionFactory.openSession();
		 output=session.createQuery("From VendorWareHouseMaster").list();
		 Iterator<VendorWareHouseMaster> itr=output.iterator();
		 VendorWareHouseMaster warehouse=null;
			while(itr.hasNext())
			{
				warehouse=(VendorWareHouseMaster)itr.next();
				logger.debug("warehouse.getPhotos().size(); ::::daooooooooooooo:::::::: "+warehouse.getPhotos().size());
			}
		 logger.info("=====END VendorWareHouseDAOImpl allWareHouse======");
	} catch (HibernateException he) {
		logger.error("Exception occured while loading all the warehouses :"+ he.getMessage());
		throw new ERPException("Exception occured while loading all the warehouses :"+ he.getMessage());
	} catch (ERPException erp) {
		logger.error("Exception occured while loading all the warehouses :"+ erp.getMessage());
		throw new ERPException("Error while loading all the warehouses");
	} catch (Exception e) {
		logger.error("Exception occured while loading all the warehouses :"+ e.getMessage());
		throw new ERPException("Error while loading all the warehouses");
	} 	
	finally {
	      try {
	        session.close();
	      } catch (Exception e) {
	        e.printStackTrace();
	      }
	    }
		return output;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<VendorWareHouseMaster> myWareHouse(String username) {
		Session session=null;
		logger.info("=====START VendorWareHouseDAOImpl myWareHouse======");
		List<VendorWareHouseMaster> output=new ArrayList<VendorWareHouseMaster>();
		try{
		 session = sessionFactory.openSession();
		 output=session.createQuery("From VendorWareHouseMaster where userId=:username").setParameter("username", username).list();
		Iterator<VendorWareHouseMaster> itr=output.iterator();
		 VendorWareHouseMaster warehouse=null;
			while(itr.hasNext())
			{
				warehouse=(VendorWareHouseMaster)itr.next();
				logger.debug("warehouse.getPhotos().size(); ::::daooooooooooooo:::::::: "+warehouse.getPhotos().size());
			}
		logger.info("=====END VendorWareHouseDAOImpl allWareHouse======");
		} catch (HibernateException he) {
			logger.error("Exception occured while loading role specific warehouses :"+ he.getMessage());
			throw new ERPException("Exception occured while loading role specific  warehouses :"+ he.getMessage());
		} catch (ERPException erp) {
			logger.error("Exception occured while loading role specific warehouses :"+ erp.getMessage());
			throw new ERPException("Error while loading role specific warehouses");
		} catch (Exception e) {
			logger.error("Exception occured while loading role specific warehouses :"+ e.getMessage());
			throw new ERPException("Error while loading role specific warehouses");
		} 	
		finally {
		      try {
		        session.close();
		      } catch (Exception e) {
		        e.printStackTrace();
		      }
		    }
		
		return output;
	
	}
	
	
	@Override
	public UserMaster getNameById(int userId) {
		 logger.info("=====START VendorWareHouseDAOImpl getNameById======");
		UserMaster userMaster=new UserMaster();
		Session session = sessionFactory.openSession();
		try{
		Query query = sessionFactory.getCurrentSession().createQuery("from UserMaster where id = :userId");
		  query.setParameter("userId", userId);
		  userMaster= (UserMaster) query.list().get(0);
		  logger.info("=====END VendorWareHouseDAOImpl getNameById======");
			} catch (HibernateException he) {
				logger.error("Exception occured while loading the user :"+ he.getMessage());
				throw new ERPException("Exception occured while loading the user :"+ he.getMessage());
			} catch (ERPException erp) {
				logger.error("Exception occured while loading the user :"+ erp.getMessage());
				throw new ERPException("Error while loading the user");
			} catch (Exception e) {
				logger.error("Exception occured while loading the user :"+e.getMessage());
				throw new ERPException("Error while loading the user");
			} 	
			finally {
			      try {
			        session.close();
			      } catch (Exception e) {
			        e.printStackTrace();
			      }
			    }
		return userMaster;
	}

	@Override
	public int updateWarehouse(WareHouseHistory item) {
		
		return 0;
	}

	@Override
	public VendorWareHouseMaster warehouse(String id) {
		logger.info("=====START VendorWareHouseDAOImpl warehouse======");
		VendorWareHouseMaster result=new VendorWareHouseMaster();
		Session session = sessionFactory.openSession();
		try{
		 session = sessionFactory.openSession();
		 result=(VendorWareHouseMaster)session.createQuery("From VendorWareHouseMaster v where v.id=:id").setParameter("id", id).uniqueResult();
		//VendorWareHouseMaster output=result;
		 logger.info("=====END VendorWareHouseDAOImpl warehouse======");
		} catch (HibernateException he) {
			logger.error("Exception occured while loading the warehouse with its Id :"+ he.getMessage());
			throw new ERPException("Exception occured while loading the warehouse with its Id :"+ he.getMessage());
		} catch (ERPException erp) {
			logger.error("Exception occured while loading the warehouse with its Id :"+ erp.getMessage());
			throw new ERPException("Error while loading the warehouse with its Id");
		} catch (Exception e) {
			logger.error("Exception occured while loading the warehouse with its Id :"+e.getMessage());
			throw new ERPException("Error while loading the warehouse with its Id");
		} 	
		finally {
		      try {
		        session.close();
		      } catch (Exception e) {
		        e.printStackTrace();
		      }
		    }
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<VendorWareHouseMaster> viewWareHouses(String strStatus) {
		logger.info("=====START VendorWareHouseDAOImpl viewWareHouses======");
		List<VendorWareHouseMaster> output=new ArrayList<VendorWareHouseMaster>();
		Session session = sessionFactory.openSession();
		try{
		 session = sessionFactory.openSession();
		 output=session.createQuery("From VendorWareHouseMaster as v where v.status=:status").setParameter("status", strStatus).list();
		 logger.info("=====END VendorWareHouseDAOImpl viewWareHouses======");
	} catch (HibernateException he) {
		logger.error("Exception occured while loading the warehouse with its status :"+ he.getMessage());
		throw new ERPException("Exception occured while loading the warehouse with its status :"+ he.getMessage());
	} catch (ERPException erp) {
		logger.error("Exception occured while loading the warehouse with its status :"+ erp.getMessage());
		throw new ERPException("Error while loading the warehouse with its status");
	} catch (Exception e) {
		logger.error("Exception occured while loading the warehouse with its status :"+e.getMessage());
		throw new ERPException("Error while loading the warehouse with its status");
	} 	
	finally {
	      try {
	        session.close();
	      } catch (Exception e) {
	        e.printStackTrace();
	      }
	    }
		return output;
	}

	@Override
	public int approveWareHouse(String id, String strComments) {
		logger.info("=====START VendorWareHouseDAOImpl approveWareHouse======");
		int result=0;
		Session session = sessionFactory.openSession();
		try{
		 session = sessionFactory.openSession();
		Query query=session.createQuery("UPDATE VendorWareHouseMaster as v SET v.status='Approved', v.comment=:comment where v.id=:id");
		query.setParameter("comment", strComments);
		query.setParameter("id", id);
		
		 result=query.executeUpdate();
		 logger.info("=====END VendorWareHouseDAOImpl approveWareHouse======");
			} catch (HibernateException he) {
				logger.error("Exception occured while approving the warehouse :"+ he.getMessage());
				throw new ERPException("Exception occured while approving the warehouse :"+ he.getMessage());
			} catch (ERPException erp) {
				logger.error("Exception occured while approving the warehouse :"+ erp.getMessage());
				throw new ERPException("Error while approving the warehouse");
			} catch (Exception e) {
				logger.error("Exception occured while  approving the warehouse :"+e.getMessage());
				throw new ERPException("Error while  approving the warehouse");
			} 	
		return result;
	}

	@Override
	public int rejectWareHouse(String id, String strComments) {
		
		logger.info("=====START VendorWareHouseDAOImpl rejectWareHouse======");
		int result=0;
		Session session = sessionFactory.openSession();
		try{
		 session = sessionFactory.openSession();
		Query query=session.createQuery("UPDATE VendorWareHouseMaster as v SET v.status='Rejected', "
				+ "v.comment=:comment where v.id=:id");
		query.setParameter("comment", strComments);
		query.setParameter("id", id);
		 result=query.executeUpdate();
		logger.info("=====END VendorWareHouseDAOImpl rejectWareHouse======");
	} catch (HibernateException he) {
		logger.error("Exception occured while rejecting the warehouse :"+ he.getMessage());
		throw new ERPException("Exception occured while rejecting the warehouse :"+ he.getMessage());
	} catch (ERPException erp) {
		logger.error("Exception occured while rejecting the warehouse :"+ erp.getMessage());
		throw new ERPException("Error while rejecting the warehouse");
	} catch (Exception e) {
		logger.error("Exception occured while  rejecting the warehouse :"+e.getMessage());
		throw new ERPException("Error while  rejecting the warehouse");
	} 	
		return result;
	}

	@Override
	public String editWareHouse(VendorWareHouseMaster obj) {
		logger.info("=====START VendorWareHouseDAOImpl editWareHouse======");
		CallableStatement cStmt=null;
		int updCnt=0;
		String stausMasg=null;
		try {
			/*Query query = session
					.createSQLQuery(
							"CALL GETESTIMATEDDATE(:reqNo,:date)")
					.addEntity(SLAMaster.class)
					.setParameter("reqNo", reqNo)*/
			 cStmt =ConnectionManager.getConnection().prepareCall("{call UPDATEVENDOR(?,?,?,?,?)}");
			 cStmt.setString(1, obj.getId());
			 cStmt.setString(2, obj.getVendorContact());
			 cStmt.setString(3, obj.getVendorEmail());
			 cStmt.setString(4, obj.getComment());
			 cStmt.registerOutParameter(5, java.sql.Types.INTEGER);

			// execute getDBUSERByUserId store procedure
			 cStmt.executeUpdate();
			 updCnt = cStmt.getInt(5);
			 if(updCnt>0)
			 {
				 stausMasg="Success";
			 }
			 logger.info("=====START VendorWareHouseDAOImpl editWareHouse======");
		}catch (SQLException sqe) {
			logger.error("Exception occured while  editing the warehouse :"+sqe.getMessage());
			throw new ERPException("Error while  editing the warehouse");
		} 
			catch (ERPException erp) {
				logger.error("Exception occured while  editing the warehouse :"+erp.getMessage());
				throw new ERPException("Error while  editing the warehouse");
		} catch (Exception e) {
			logger.error("Exception occured while  editing the warehouse :"+e.getMessage());
			throw new ERPException("Error while  editing the warehouse");
		} 
		finally {
			try {
				cStmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
				 
	}
		return stausMasg;
	
	}

	@Override
	public int persistWareHouseHistory(WareHouseHistory obj) {
		logger.info("=====START VendorWareHouseDAOImpl persistWareHouseHistory======");
		Session session = null;
		try 
		{
			session = sessionFactory.openSession();
			Transaction tx = session.beginTransaction();
			session.save(obj);
		    tx.commit();
		    logger.info("=====START VendorWareHouseDAOImpl persistWareHouseHistory======");
		    return 1;
		} catch (HibernateException he) {
			logger.error("Exception occured while saving the warehouse :"+ he.getMessage());
			throw new ERPException("Exception occured while saving the warehouse :"+ he.getMessage());
		} catch (ERPException erp) {
			logger.error("Exception occured while saving the warehouse :"+ erp.getMessage());
			throw new ERPException("Error while saving the warehouse");
		} catch (Exception e) {
			logger.error("Exception occured while saving the warehouse :"+ e.getMessage());
			throw new ERPException("Error while saving the warehouse");
		} 	
		finally {
		      try {
		        session.close();
		      } catch (Exception e) {
		        e.printStackTrace();
		      }
		    }
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<WareHouseHistory> getHistory(String wareHouseName, String sorting) {
		logger.info("=====START VendorWareHouseDAOImpl getHistory======");
		Session session =null;
		List<WareHouseHistory> result=null;
		Query query=null;
		try 
		{
		 session = sessionFactory.openSession();
		if(sorting.equalsIgnoreCase("All")){
			query=session.createQuery("From WareHouseHistory w where w.warehouseName=:name");
			query.setParameter("name", wareHouseName);
			result=query.list();	
		}else{
			query=session.createQuery("From WareHouseHistory w where w.warehouseName=:name and warehouseStatus=:status");
			query.setParameter("name", wareHouseName);
			query.setParameter("status", sorting);
			result=query.list();
		}
		logger.info("=====END VendorWareHouseDAOImpl getHistory======");
		} catch (HibernateException he) {
			logger.error("Exception occured while loading the history of the warehouse :"+ he.getMessage());
			throw new ERPException("Exception occured while loading the history of the warehouse :"+ he.getMessage());
		} catch (ERPException erp) {
			logger.error("Exception occured while loading the history of the warehouse :"+ erp.getMessage());
			throw new ERPException("Error while loading the history of the warehouse");
		} catch (Exception e) {
			logger.error("Exception occured while loading the history of the warehouse :"+ e.getMessage());
			throw new ERPException("Error while loading the history of the warehouse");
		} 	
		finally {
		      try {
		        session.close();
		      } catch (Exception e) {
		        e.printStackTrace();
		      }
		    }
		return result;
	}

	@Override
	public List<WareHouseHistory> getVendorHistory(String vendorName) {
		
		return null;
	}

	@Override
	public List<Photo> getPhotoDetails(String strId) {
		logger.debug("======START VendorWareHouseDAOImpl getPhotoDetails======");
		List<Photo> photoList = null;
		Session session = null;
		try {
			session = sessionFactory.openSession();
			photoList = new ArrayList<Photo>();
			Query query = session.createSQLQuery("CALL GETPHOTOS(:id)").addEntity(Photo.class)
					.setParameter("id", strId);
			photoList = query.list();
			 logger.debug("=====END VendorWareHouseDAOImpl allWareHouse======");
		} catch (HibernateException he) {
			logger.error("Exception occured while loading the photo List:"+ he.getMessage());
			throw new ERPException("Exception occured while loading the photo List :"+ he.getMessage());
		} catch (ERPException erp) {
			logger.error("Exception occured while loading the photo List :"+ erp.getMessage());
			throw new ERPException("Error while loading the photo List");
		} catch (Exception e) {
			logger.error("Exception occured while loading the photo List :"+ e.getMessage());
			throw new ERPException("Error while loading the photo List");
		} 	
		finally {
		      try {
		        session.close();
		      } catch (Exception e) {
		        e.printStackTrace();
		      }
		    }
		return photoList;
	}

}

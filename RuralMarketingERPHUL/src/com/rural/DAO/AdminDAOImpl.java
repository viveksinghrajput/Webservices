/*
*AdminDAOImpl.java
*Created By		:Kamal Thapa
*Created Date	:Apr 4, 2018
*/
package com.rural.DAO;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import com.rural.Model.AgencyMaster;
import com.rural.Model.BrandMaster;
import com.rural.Model.CityMaster;
import com.rural.Model.ConversionMaster;
import com.rural.Model.RoleMaster;
import com.rural.Model.Roles;
import com.rural.Model.SaturationMaster;
import com.rural.Model.StateMaster;
import com.rural.Model.UserMaster;
import com.rural.Model.VendorMaster;
import com.rural.exceptions.ERPException;
import com.rural.utility.ConnectionManager;

@Component
@Repository
@Transactional
@SuppressWarnings("unchecked")
public  class AdminDAOImpl implements AdminDAO {

	@Resource(name = "sessionFactory")
	protected SessionFactory sessionFactory;

	static Logger logger =Logger.getLogger(AdminDAOImpl.class);
	
	@Override
	public List<AgencyMaster> listAgencyMaster() {
		logger.debug("=====START AdminDAOImpl listAgencyMaster======");
		Session session = null;
		List<AgencyMaster> output=new ArrayList<AgencyMaster>();
		try {
			session = sessionFactory.openSession();
		Criteria criteria=session.createCriteria(AgencyMaster.class);
		output=criteria.list();
		} catch (HibernateException he) {
			logger.error("Exception occured while getting agencies:"+ he.getMessage());
			throw new ERPException("Exception occured while getting agencies:"+ he.getMessage());
		} catch (ERPException erp) {
			logger.error("Exception occured while getting agencies:"+ erp.getMessage());
			throw new ERPException("Error while getting agencies");
		} catch (Exception e) {
		} finally {
			session.close();
		}
		logger.debug("=====END AdminDAOImpl listAgencyMaster======");
		return output;
	}
	
	
	//show the list of city of state
	@Override
	public List<CityMaster> listCityOfStates(Integer intStateId) {
		Session session = null;// ssionFactory.openSession();
		logger.debug("======START AdminDAOImpl listCityOfStates======");
		List<CityMaster> cityList = null;
		try {
			session = sessionFactory.openSession();
			cityList = new ArrayList<CityMaster>();
			Query query = session
					.createSQLQuery("CALL GET_CITY_LIST(:state)")
					.addEntity(CityMaster.class)
					.setParameter("state", intStateId);
			List<CityMaster> list = query.list();
			Iterator<CityMaster> itr = list.iterator();
			while (itr.hasNext()) {
				CityMaster cityMaster = new CityMaster();
				cityMaster = itr.next();
				cityList.add(cityMaster);
			}
		} catch (HibernateException he) {
			logger.error("Exception occured while loading the  cities :"+ he.getMessage());
			throw new ERPException("Exception occured while loading the cities :"+ he.getMessage());
		} catch (ERPException erp) {
			logger.error("Exception occured while loading the  cities :"+ erp.getMessage());
			throw new ERPException("Exception occured while loading the  cities");
		} catch (Exception e) {
			logger.error("Exception occured while loading the  cities :"+ e.getMessage());
			throw new ERPException("Exception occured while loading the  cities");
		} finally {
			session.close();
		}
		logger.debug("=====END AdminDAOImpl listCityOfStates======");
		return cityList;}
	//show the list of vendor of agency
	
	@Override
	public List<String> listVendorofAgency(String strAgency) {
		
		logger.debug("=====START AdminDAOImpl listVendorofAgency======");
		List<String> list=new ArrayList<String>();
		Session session = null;
		try {
		session = sessionFactory.openSession();
		list = session.createQuery(" select distinct c.vendorName From VendorMaster c where c.agencyName=:agency ")
				.setParameter("agency", strAgency).list();
		} catch (HibernateException he) {
			logger.error("Exception occured while getting vendors:"
					+ he.getMessage());
			throw new ERPException(
					"Exception occured while getting vendors:"
							+ he.getMessage());
		} catch (ERPException erp) {
			logger.error("Exception occured while getting vendors:"
					+ erp.getMessage());
			throw new ERPException("Error while getting vendors");
		} catch (Exception e) {
		} finally {
			session.close();
		}
		logger.debug("=====END AdminDAOImpl listVendorofAgency======");
		return list;
	}
	//here we are saving the new agency 
	@Override
	public int saveNewAgency(AgencyMaster newAgency) {
		logger.debug("=====START AdminDAOImpl saveNewAgency======");
		Session session = null;
		try {
		session = sessionFactory.openSession();	
			Transaction tx = session.beginTransaction();
			session.save(newAgency);
		    tx.commit();
		} catch (HibernateException he) {
			logger.error("Exception occured while inserting agency:"
					+ he.getMessage());
			throw new ERPException(
					"Exception occured while inserting agency:"
							+ he.getMessage());
		} catch (ERPException erp) {
			logger.error("Exception occured while inserting agency:"
					+ erp.getMessage());
			throw new ERPException("Error while inserting agency");
		} catch (Exception e) {
		} finally {
			session.close();
		}
		logger.debug("=====END AdminDAOImpl saveNewAgency======");
		 return 1;
	}
	//here we are fetching the details from the back-end.
	@Override
	public AgencyMaster getAgency(int strId) {
		logger.debug("=====START AdminDAOImpl getAgency======");
		Session session = null;
		AgencyMaster output=null;//(AgencyMaster) session.get(AgencyMaster.class, strId);
		try {
		session = sessionFactory.openSession();	
		List<AgencyMaster> outputlist=session.createQuery("from AgencyMaster as a where a.Id=:id")
				.setParameter("id", strId).list();
		for (AgencyMaster agencyMaster : outputlist) {
			output=new AgencyMaster();
			output=agencyMaster;
			
		}
		} catch (HibernateException he) {
			logger.error("Exception occured while loading agency:"
					+ he.getMessage());
			throw new ERPException(
					"Exception occured while loading agency:"
							+ he.getMessage());
		} catch (ERPException erp) {
			logger.error("Exception occured while loading agency:"
					+ erp.getMessage());
			throw new ERPException("Error while loading agency");
		} catch (Exception e) {
		} finally {
			session.close();
		}
		logger.debug("=====END AdminDAOImpl getAgency======");
		return output;
	}

	/*
	@Override
	public ConversionMaster getConv(int strId) {
		
		Session session = sessionFactory.openSession();
		ConversionMaster output=null;
		List<ConversionMaster> outputlist=session.createQuery("From ConversionMaster as c where c.conversion_id=:id").setParameter("id",strId).list();
		for(ConversionMaster temp : outputlist){
			output=temp;
		}
		session.close();
		return output;
	}*/
	//for checking purpose of Agency is available or not
	@Override
	public AgencyMaster checkAgencyName(String newAgencyName) {
		logger.debug("=====START AdminDAOImpl checkAgencyName======");
		Session session = null;
		AgencyMaster agencymaster=new AgencyMaster();
		try {
		session = sessionFactory.openSession();	
		agencymaster=(AgencyMaster) session.createQuery("from AgencyMaster as a where a.agencyname=:name")
				.setParameter("name", newAgencyName)
				.uniqueResult();
		} catch (HibernateException he) {
			logger.error("Exception occured while loading agency:"
					+ he.getMessage());
			throw new ERPException(
					"Exception occured while loading agency:"
							+ he.getMessage());
		} catch (ERPException erp) {
			logger.error("Exception occured while loading agency:"
					+ erp.getMessage());
			throw new ERPException("Error while loading agency");
		} catch (Exception e) {
		} finally {
			session.close();
		}
		logger.debug("=====END AdminDAOImpl checkAgencyName======");
		return agencymaster;
	}
	//for updating the new Agency
	@Override
	public int updateAgencyValue(int strId, String agencyname, String city, String state) {
		logger.debug("=====START AdminDAOImpl updateAgencyValue======");
		Session session = null;
		int result=0;
		try {
		session = sessionFactory.openSession();	
		Query query=session.createQuery("UPDATE AgencyMaster as a SET a.statename=:newstate,a.cityname=:newcity where a.id=:newId");
		query.setParameter("newstate", state);
		query.setParameter("newcity", city);
		query.setParameter("newId", strId);
		result=query.executeUpdate();
		} catch (HibernateException he) {
			logger.error("Exception occured while updating agency:"
					+ he.getMessage());
			throw new ERPException(
					"Exception occured while updating agency:"
							+ he.getMessage());
		} catch (ERPException erp) {
			logger.error("Exception occured while updating agency:"
					+ erp.getMessage());
			throw new ERPException("Error while updating agency");
		} catch (Exception e) {
		} finally {
			session.close();
		}
		logger.debug("=====END AdminDAOImpl updateAgencyValue======");
		return result;
	}
	public int updateVendorValue(int strId, String vendorname, String agencyname, String state, String city) {
		logger.debug("=====START AdminDAOImpl updateVendorValue======");
		Session session = null;
		int result=0;
		try {
		session = sessionFactory.openSession();	
		Query query=session.createQuery("update VendorMaster as v set v.agencyName=:newagency,v.state=:newstate,v.city=:newcity where v.id=:newid");
		query.setParameter("newid", strId);
		query.setParameter("newagency", agencyname);
		query.setParameter("newstate",state);
		query.setParameter("newcity", city);
		result=query.executeUpdate();
		} catch (HibernateException he) {
			logger.error("Exception occured while updating vendor:"
					+ he.getMessage());
			throw new ERPException(
					"Exception occured while updating vendor:"
							+ he.getMessage());
		} catch (ERPException erp) {
			logger.error("Exception occured while updating vendor:"
					+ erp.getMessage());
			throw new ERPException("Error while updating vendor");
		} catch (Exception e) {
		} finally {
			session.close();
		}
		logger.debug("=====END AdminDAOImpl updateVendorValue======");
		return result;
	}
	@Override
	public int LockVendorValue(int strId) {
		logger.debug("=====START AdminDAOImpl LockVendorValue======");
		Session session = null;
		int result=0;
		try {
			session = sessionFactory.openSession();	
			Query  query=session.createQuery("update  VendorMaster as v set isActive=0 where v.id=:newId");
			query.setParameter("newId", strId);
			result = query.executeUpdate();
		} catch (HibernateException he) {
			logger.error("Exception occured while locking vendor:"
					+ he.getMessage());
			throw new ERPException(
					"Exception occured while locking vendor:"
							+ he.getMessage());
		} catch (ERPException erp) {
			logger.error("Exception occured while locking vendor:"
					+ erp.getMessage());
			throw new ERPException("Error while locking vendor");
		} catch (Exception e) {
		} finally {
			session.close();
		}
		logger.debug("=====END AdminDAOImpl LockVendorValue======");
		return result;
	}
	@Override
	public int UnLockVendorValue(int strId) {
		logger.debug("=====START AdminDAOImpl UnLockVendorValue======");
		Session session = null;
		int result=0;
		try {
			session = sessionFactory.openSession();	
			Query  query=session.createQuery("update  VendorMaster as v set isActive=1 where v.id=:newId");
			query.setParameter("newId", strId);
			result = query.executeUpdate();
		} catch (HibernateException he) {
			logger.error("Exception occured Unlocking the vendor :"
					+ he.getMessage());
			throw new ERPException("Exception occured while Unlocking the vendor :"
					+ he.getMessage());
		} catch (ERPException erp) {
			logger.error("Exception occured Unlocking the vendor :"
					+ erp.getMessage());
			throw new ERPException("Error while Unlocking the vendor");
		} catch (Exception e) {
		} finally {
			session.close();
		}
		logger.debug("=====END AdminDAOImpl UnLockVendorValue======");
		return result;
	}
	@Override
	public int LockAgencyValue(int strId) {
		logger.debug("=====START AdminDAOImpl LockAgencyValue======");
		Session session = null;
		int result=0;
		try {
			session = sessionFactory.openSession();	
			Query query=session.createQuery("update  AgencyMaster as a set isActive=0 where a.id=:newId");
			query.setParameter("newId", strId);
			result = query.executeUpdate();
		} catch (HibernateException he) {
			logger.error("Exception occured Locking the Agency :"
					+ he.getMessage());
			throw new ERPException("Exception occured while Locking the Agency :"
					+ he.getMessage());
		} catch (ERPException erp) {
			logger.error("Exception occured Locking the Agency :"
					+ erp.getMessage());
			throw new ERPException("Error while Locking the Agency");
		} catch (Exception e) {
		} finally {
			session.close();
		}
		logger.debug("=====END AdminDAOImpl LockAgencyValue======");
		return result;
	}
	@Override
	public int UnLockAgencyValue(int strId) {
		logger.debug("=====START AdminDAOImpl UnLockAgencyValue======");
		Session session = null;
		int result=0;
		try {
			session = sessionFactory.openSession();	
			Query query=session.createQuery("update  AgencyMaster as a set isActive=1 where a.id=:newId");
			query.setParameter("newId", strId);
			result = query.executeUpdate();
		} catch (HibernateException he) {
			logger.error("Exception occured UnLocking the Agency :"
					+ he.getMessage());
			throw new ERPException("Exception occured while UnLocking the Agency :"
					+ he.getMessage());
		} catch (ERPException erp) {
			logger.error("Exception occured UnLocking the Agency :"
					+ erp.getMessage());
			throw new ERPException("Error while UnLocking the Agency");
		} catch (Exception e) {
		} finally {
			session.close();
		}
		logger.debug("=====END AdminDAOImpl UnLockAgencyValue======");
		return result;
	}
	@Override
	public int LockUserValue(int strId) {
		logger.debug("=====START AdminDAOImpl LockUserValue======");
		Session session = null;
		int result=0;
		try {
			session = sessionFactory.openSession();	
			Query query=session.createQuery("update UserMaster as u set isActive=0 where u.id=:newId");
			query.setParameter("newId", strId);
			result = query.executeUpdate();
		} catch (HibernateException he) {
			logger.error("Exception occured Locking the User :"
					+ he.getMessage());
			throw new ERPException("Exception occured while Locking the User :"
					+ he.getMessage());
		} catch (ERPException erp) {
			logger.error("Exception occured Locking the User :"
					+ erp.getMessage());
			throw new ERPException("Error while Locking the User");
		} catch (Exception e) {
		} finally {
			session.close();
		}
		logger.debug("=====END AdminDAOImpl LockUserValue======");
		return result;
	}
	@Override
	public int UnLockUserValue(int strId) {
		logger.debug("=====START AdminDAOImpl UnLockUserValue======");
		Session session = null;
		int result=0;
		try {
			session = sessionFactory.openSession();	
			Query query=session.createQuery("update UserMaster as u set isActive=1 where u.id=:newId ");
			query.setParameter("newId", strId);
			result = query.executeUpdate();
		} catch (HibernateException he) {
			logger.error("Exception occured UnLocking the User :"
					+ he.getMessage());
			throw new ERPException("Exception occured while UnLocking the User :"
					+ he.getMessage());
		} catch (ERPException erp) {
			logger.error("Exception occured UnLocking the User :"
					+ erp.getMessage());
			throw new ERPException("Error while UnLocking the User");
		} catch (Exception e) {
		} finally {
			session.close();
		}
		logger.debug("=====END AdminDAOImpl UnLockUserValue======");
		return result;
	}
	@Override
	public int updateUserValue(int strId, String strpassword, String strEmailId, String strContact) {
		logger.debug("=====START AdminDAOImpl updateUserValue======");
		Session session = null;
		int result=0;
		try {
			session = sessionFactory.openSession();	
			Query query=session.createQuery("update UserMaster as u set u.email=:NewEmail,u.passwordHash=:strpassword,u.contactNum=:NewContact where u.id=:NewId");
			query.setParameter("NewId",strId);
			query.setParameter("NewPassword", strpassword);
			query.setParameter("NewEmail", strEmailId);
			query.setParameter("NewContact", strContact);
			
			result = query.executeUpdate();
		} catch (HibernateException he) {
			logger.error("Exception occured Updating the User :"
					+ he.getMessage());
			throw new ERPException("Exception occured while Updating the User :"
					+ he.getMessage());
		} catch (ERPException erp) {
			logger.error("Exception occured Updating the User :"
					+ erp.getMessage());
			throw new ERPException("Error while Updating the User");
		} catch (Exception e) {
			logger.error("Exception occured Updating the User :"
					+ e.getMessage());
			} finally {
			session.close();
		}
		logger.debug("=====END AdminDAOImpl updateUserValue======");
		return result;
	}
	
	@Override
	public Map<String, String> stateMap(){
		Map<String,String> states=new LinkedHashMap<String,String>();
		logger.debug("=====START AdminDAOImpl stateMap======");
		Session session = null;
		String statename=null;
		try {
			session = sessionFactory.openSession();
			List list = session.createSQLQuery("CALL Get_States(:module)")
					.setParameter("module", "Admin").list();
			Iterator itr=list.iterator();
			while(itr.hasNext()) {
				statename=(String) itr.next();
				states.put(statename,statename);
			}
		} catch (HibernateException he) {
			logger.error("Exception occured loading states :"
					+ he.getMessage());
			throw new ERPException("Exception occured while loading states :"
					+ he.getMessage());
		} catch (ERPException erp) {
			logger.error("Exception occured Updating loading states :"
					+ erp.getMessage());
			throw new ERPException("Error while loading states:");
		} catch (Exception e) {
			logger.error("Exception occured while loading states :"
					+ e.getMessage());
			} finally {
			session.close();
		}
		logger.debug("=====END AdminDAOImpl stateMap======");
		return states;
	}
	@Override
	public List<ConversionMaster> getConvMaster() {
		List<ConversionMaster> output = null;
		logger.debug("=====START AdminDAOImpl getConvMaster======");
		Session session = null;
		try {
			session = sessionFactory.openSession();
			output = session.createQuery("From ConversionMaster").list();
		} catch (HibernateException he) {
			logger.error("Exception occured loading conversions :"+ he.getMessage());
			throw new ERPException("Exception occured while loading conversions :"+ he.getMessage());
		} catch (ERPException erp) {
			logger.error("Exception occured Updating loading conversions :"
					+ erp.getMessage());
			throw new ERPException("Error while loading conversions:");
		} catch (Exception e) {
			logger.error("Exception occured while loading conversions :"
					+ e.getMessage());
			} finally {
			session.close();
		}
		logger.debug("=====END AdminDAOImpl getConvMaster======");
		return output;
	}

	
	@Override
	public List<SaturationMaster> getSatMaster() {
		logger.debug("=====START AdminDAOImpl getSatMaster======");
		Session session = null;
		List<SaturationMaster> output=new ArrayList<SaturationMaster>();
		try {
			session = sessionFactory.openSession();
			output = session.createQuery("From SaturationMaster").list();
		} catch (HibernateException he) {
			logger.error("Exception occured loading conversions :"
					+ he.getMessage());
			throw new ERPException("Exception occured while loading saturation :"
					+ he.getMessage());
		} catch (ERPException erp) {
			logger.error("Exception occured Updating loading saturation :"
					+ erp.getMessage());
			throw new ERPException("Error while loading saturation:");
		} catch (Exception e) {
			logger.error("Exception occured while loading saturation :"
					+ e.getMessage());
			} finally {
			session.close();
		}
		logger.debug("=====END AdminDAOImpl getSatMaster======");
		return output;
		
	}
	@Override
	public ConversionMaster getConv(int strId) {
		logger.debug("=====START AdminDAOImpl getConv======");
		Session session = null;
		ConversionMaster output=null;
		try {
			session = sessionFactory.openSession();
			List<ConversionMaster> outputlist=session.createQuery("From ConversionMaster as c where c.conversion_id=:id")
					.setParameter("id",strId)
					.list();
			for(ConversionMaster temp : outputlist){
				output=temp;
			}
		}catch (HibernateException he) {
			logger.error("Exception occured loading the of ConversionMaster :"
					+ he.getMessage());
			throw new ERPException("Exception occured while loading  of ConversionMaster :"
					+ he.getMessage());
		} catch (ERPException erp) {
			logger.error("Exception occured loading the of ConversionMaster :"
					+ erp.getMessage());
			throw new ERPException("Error while loading  of ConversionMaster");
		} catch (Exception e) {
			logger.error("Exception occured loading the of ConversionMaster :"
					+ e.getMessage());
		} finally {
			session.close();
		}
		logger.debug("=====END AdminDAOImpl getConv======");
		return output;
	}

	
	@Override
	public SaturationMaster getSat(int strId) {
		logger.debug("=====START AdminDAOImpl getSat======");
		Session session = null;
		SaturationMaster output=null;
		try {
			session = sessionFactory.openSession();
			List<SaturationMaster> outputList=session.createQuery("From SaturationMaster as s where s.saturation_id=:id").setParameter("id", strId).list();
			for(SaturationMaster temp:outputList){
				output=temp;
			}
		} catch (HibernateException he) {
			logger.error("Exception occured loading saturation value based on its id :"+ he.getMessage());
			throw new ERPException("Exception occured while loading  saturation value based on its id:"+ he.getMessage());
		} catch (ERPException erp) {
			logger.error("Exception occured loading saturation value based on its id :"+ erp.getMessage());
			throw new ERPException("Error while loading  saturation value based on its id");
		} catch (Exception e) {
			logger.error("Exception occured loading saturation value based on its id :"+ e.getMessage());
		} finally {
			session.close();
		}
		logger.debug("=====END AdminDAOImpl getSat======");
		return output;
	}

	@Override
	public int updateConvValue(int strId, String strConv, String avgSales,String ipAddress) {
		Session session = sessionFactory.openSession();
		//Query query=session.createQuery("UPDATE ConversionMaster as c SET c.conpercent=:newConv, c.avgsales=:newSales where c.conversion_id=:id");
		int result=0;
		logger.debug("====== START AdminDAOImpl updateConvValue======");
		try {
			Query query=session.createQuery("UPDATE ConversionMaster as c SET c.lastUpdated=now(),c.conpercent=:newConv,c.ipAddress=:ipAddress where c.conversion_id=:id");
			query.setParameter("id", strId);
			query.setParameter("newConv",strConv);
			query.setParameter("ipAddress", ipAddress);
			//query.setParameter("newSales",avgSales);
			
			result = query.executeUpdate();
			logger.debug("====== END AdminDAOImpl updateConvValue======");
		} catch (HibernateException he) {
			logger.error("Exception occured updating the ConversionMaster :"+ he.getMessage());
			throw new ERPException("Exception occured while updating  of ConversionMaster :"	+ he.getMessage());
		} catch (ERPException erp) {
			logger.error("Exception occured updating the ConversionMaster :"+ erp.getMessage());
			throw new ERPException("Exception occured while updating  of ConversionMaster :"	+ erp.getMessage());
		} catch (Exception e) {
			logger.error("Exception occured updating the ConversionMaster :"+ e.getMessage());
			throw new ERPException("Exception occured while updating  of ConversionMaster :"	+ e.getMessage());
		} finally {
			session.close();
		}
		return result;
	}

	@Override
	public int updateSatValue(int strId, String strSat,String ipAddress) {
		Session session = sessionFactory.openSession();
		logger.debug("====== START AdminDAOImpl updateSatValue======");
		int result=0;
		try {
			Query query=session.createQuery("UPDATE SaturationMaster as s SET s.lastUpdated=now(),s.satpercent=:newSat,s.ipAddress=:ipAddress where s.saturation_id=:id");
			query.setParameter("id", strId);
			query.setParameter("newSat", strSat);
			query.setParameter("ipAddress", ipAddress);
			result = query.executeUpdate();
			logger.debug("====== END AdminDAOImpl updateSatValue======");
		} catch (HibernateException he) {
			logger.error("Exception occured updating the SaturationMaster :"+ he.getMessage());
			throw new ERPException("Exception occured while updating  of SaturationMaster :"+ he.getMessage());
		} catch (ERPException erp) {
			logger.error("Exception occured updating the SaturationMaster :"+ erp.getMessage());
			throw new ERPException("Exception occured while updating  of SaturationMaster :"+ erp.getMessage());
		} catch (Exception e) {
			logger.error("Exception occured updating the SaturationMaster :"+ e.getMessage());
			throw new ERPException("Exception occured while updating  of SaturationMaster :"+ e.getMessage());
		} finally {
			session.close();
		}
		return result;
	}
	@Override
	public List<VendorMaster> listVendorMaster() {
		logger.debug("=====START AdminDAOImpl listVendorMaster======");
		List<VendorMaster> output = null;
		Session session = null;
		try {
			session = sessionFactory.openSession();
			Criteria criteria = session.createCriteria(VendorMaster.class);
			output = criteria.list();
		} catch (HibernateException he) {
			logger.error("Exception occured while loading the listVendorMaster :"+ he.getMessage());
			throw new ERPException("Exception occured while loading the listVendorMaster :"	+ he.getMessage());
		} catch (ERPException erp) {
			logger.error("Exception occured while loading the listVendorMaster :"+ erp.getMessage());
			throw new ERPException("Error while loading the listVendorMaster");
		} catch (Exception e) {
			logger.error("Exception occured while loading the listVendorMaster :"+ e.getMessage());
		} finally {
			session.close();
		}		
		logger.debug("=====END AdminDAOImpl listVendorMaster======");
		return output;
	}

	@Override
	public int saveNewVendor(VendorMaster newVendor) {
		logger.debug("=====START AdminDAOImpl saveNewVendor======");
		Session session = null;
		try {
			session = sessionFactory.openSession();
			Transaction tx = session.beginTransaction();
			session.save(newVendor);
		    tx.commit();
		} catch (HibernateException he) {
			logger.error("Exception occured while loading the listVendorMaster :"
					+ he.getMessage());
			throw new ERPException("Exception occured while loading the listVendorMaster :"
					+ he.getMessage());
		} catch (ERPException erp) {
			logger.error("Exception occured while saving vendor :"
					+ erp.getMessage());
			throw new ERPException("Error while saving vendor :");
		} catch (Exception e) {
			logger.error("Exception occured while saving vendor :"
					+ e.getMessage());
		} finally {
			session.close();
		}		
		logger.debug("=====END AdminDAOImpl saveNewVendor======");
		 return 1;
	}
	@Override
	public VendorMaster getVendor(int strId) {
		VendorMaster output = null;
		logger.debug("=====START AdminDAOImpl getVendor======");
		Session session = null;
		try {
			session = sessionFactory.openSession();
			output = (VendorMaster)session.get(VendorMaster.class,strId);
		} catch (HibernateException he) {
			logger.error("Exception occured while loading the vendor :"
					+ he.getMessage());
			throw new ERPException("Exception occured while loading the vendor :"
					+ he.getMessage());
		} catch (ERPException erp) {
			logger.error("Exception occured while loading vendor :"
					+ erp.getMessage());
			throw new ERPException("Error while loading vendor :");
		} catch (Exception e) {
			logger.error("Exception occured while loading vendor :"
					+ e.getMessage());
		} finally {
			session.close();
		}		
		logger.debug("=====END AdminDAOImpl getVendor======");	
		return output;
	}



	@Override
	public List<UserMaster> listUserMaster() {
		List<UserMaster> output=null;
		logger.debug("=====START AdminDAOImpl listUserMaster======");
		Session session = null;
		try {
			session = sessionFactory.openSession();
			Criteria criteria = session.createCriteria(UserMaster.class);
			output = criteria.list();
		} catch (HibernateException he) {
			logger.error("Exception occured while loading the users :"
					+ he.getMessage());
			throw new ERPException("Exception occured while loading the users :"
					+ he.getMessage());
		} catch (ERPException erp) {
			logger.error("Exception occured while loading users :"
					+ erp.getMessage());
			throw new ERPException("Error while loading users :");
		} catch (Exception e) {
			logger.error("Exception occured while loading users :"
					+ e.getMessage());
		} finally {
			session.close();
		}		
		logger.debug("=====END AdminDAOImpl listUserMaster======");		
		return output;
	}
	
	@Override
	public List<RoleMaster> listRoles() {
		List<RoleMaster> output=null;
		logger.debug("=====START AdminDAOImpl listRoles======");
		Session session = null;
		try {
			session = sessionFactory.openSession();
			Criteria criteria = session.createCriteria(RoleMaster.class);
			output = criteria.list();
		} catch (HibernateException he) {
			logger.error("Exception occured while loading the roles :"
					+ he.getMessage());
			throw new ERPException("Exception occured while loading the roles :"
					+ he.getMessage());
		} catch (ERPException erp) {
			logger.error("Exception occured while loading roles :"
					+ erp.getMessage());
			throw new ERPException("Error while loading roles :");
		} catch (Exception e) {
			logger.error("Exception occured while loading roles :"
					+ e.getMessage());
		} finally {
			session.close();
		}		
		logger.debug("=====END AdminDAOImpl listRoles======");				
		return output;
	}


	@Override
	public UserMaster checkUniqueUserName(String newUserName) {
		UserMaster user=null;
		logger.debug("=====START AdminDAOImpl checkUniqueUserName======");
		Session session = null;
		try {
			session = sessionFactory.openSession();
			user=(UserMaster) session.createQuery("from UserMaster as a where a.username=:name ")
					.setParameter("name", newUserName)
					.uniqueResult();
		} catch (HibernateException he) {
			logger.error("Exception occured while checking the user :"
					+ he.getMessage());
			throw new ERPException("Exception occured while checking the user :"
					+ he.getMessage());
		} catch (ERPException erp) {
			logger.error("Exception occured while checking the user :"
					+ erp.getMessage());
			throw new ERPException("Error while checking the user :");
		} catch (Exception e) {
			logger.error("Exception occured while checking the user :"
					+ e.getMessage());
		} finally {
			session.close();
		}		
		logger.debug("=====END AdminDAOImpl checkUniqueUserName======");		
		return user;
	}
	@Override
	public int saveNewUser(UserMaster newUser) {
		logger.debug("======START AdminDAOImpl saveNewUser======");
		Session session = null;
		CallableStatement cStmt;
		int count=0;
		try {
			session = sessionFactory.openSession();
			 cStmt =ConnectionManager.getConnection().prepareCall("{call INSERTUSER(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
			 cStmt.setString(1, newUser.getFirstName());
			 cStmt.setString(2, newUser.getLastName());
			 cStmt.setString(3, newUser.getUsername());
			 cStmt.setInt(4, newUser.getRoleId());
			 cStmt.setInt(5, newUser.getBrandId());
			 cStmt.setInt(6, newUser.getVendorId());
			 cStmt.setInt(7, newUser.getAgencyId());
			 cStmt.setInt(8, newUser.getState());
			 cStmt.setInt(9, newUser.getCity());
			 cStmt.setString(10, newUser.getEmail());
			 cStmt.setString(11, newUser.getContactNum());
			 cStmt.setString(12, newUser.getMobileAccess());
			 cStmt.setString(13, newUser.getIsActive());
			 cStmt.setInt(14, newUser.getCounterFlag());
			 cStmt.registerOutParameter(15, java.sql.Types.INTEGER);

			 cStmt.executeUpdate();
			  count = cStmt.getInt(15);
			  logger.debug("======START AdminDAOImpl saveNewUser======");
		} catch (HibernateException he) {
			logger.error("Exception occured while creating the user :"+ he.getMessage());
			throw new ERPException("Exception occured while creating the user :"+ he.getMessage());
		} catch (ERPException erp) {
			logger.error("Exception occured while creating the user :"+ erp.getMessage());
			throw new ERPException("Exception occured while creating the user :"+ erp.getMessage());
		} catch (Exception e) {
		} finally {
			session.close();
		}
		return count;
		}

	@Override
	public UserMaster getUser(int strId) {
	logger.debug("======START AdminDAOImpl getUser======");
	Session session = null;
	UserMaster user=null;
	try {
	session = sessionFactory.openSession();
	user = new UserMaster();
	Query query = session.createSQLQuery("CALL GET_USER(:id)").addEntity(UserMaster.class).setParameter("id", strId);
	List<UserMaster> list = query.list();
	if ((list != null) && (list.size() > 0)) 
	{
	Iterator<UserMaster> itr=list.iterator();
	while(itr.hasNext()) {
	user=(UserMaster) itr.next();
	}
	}
	} catch (HibernateException he) {
		logger.error("Exception occured while loading the user :"+ he.getMessage());
		throw new ERPException("Exception occured while loading the user :"+ he.getMessage());
	} catch (ERPException erp) {
		logger.error("Exception occured while loading the user :"+ erp.getMessage());
		throw new ERPException("Exception occured while loading the user :"+ erp.getMessage());
	} catch (Exception e) {
	} finally {
		session.close();
	}
	return user;
	}
	@Override
	public UserMaster getUserroleId(int strId) {
		UserMaster output=null;
		Session session = null;
		logger.debug("=====START AdminDAOImpl getUserroleId======");
		try {
			session = sessionFactory.openSession();
			output = (UserMaster) session.get(UserMaster.class, strId);
		} catch (HibernateException he) {
			logger.error("Exception occured while loading the user :"
					+ he.getMessage());
			throw new ERPException("Exception occured while loading the user :"
					+ he.getMessage());
		} catch (ERPException erp) {
			logger.error("Exception occured while loading the user :"
					+ erp.getMessage());
			throw new ERPException("Error while loading the user :");
		} catch (Exception e) {
			logger.error("Exception occured while loading the user :"
					+ e.getMessage());
		} finally {
			session.close();
		}		
		logger.debug("=====END AdminDAOImpl getUserroleId======");	
		return output;
	}

	@Override
	public int updateUser(UserMaster user) {
			logger.debug("=====START AdminDAOImpl UPDATEUSER======");
			Connection con=null;
			CallableStatement cStmt=null;
			int updateCnt=0;
			try {
				con=ConnectionManager.getConnection();
				 cStmt =con.prepareCall("{call UPDATEUSER(?,?,?,?,?,?,?)}");
				 cStmt.setInt(1, user.getId());
				 cStmt.setInt(2, user.getRoleId());
				 cStmt.setString(3, user.getPassword());
				 cStmt.setString(4, user.getEmail());
				 cStmt.setString(5, user.getContactNum());
				 if(user.getRoleId()==4) {
					 cStmt.setString(6, user.getMobileAccess());
				 }
				 else if(user.getRoleId()==5) {
					 cStmt.setInt(6, user.getBrandId());
				 }
				 else {
					 cStmt.setString(6,"");
				 }
				 cStmt.registerOutParameter(7, java.sql.Types.INTEGER);

				// execute getDBUSERByUserId store procedure
				 cStmt.executeUpdate();
				 updateCnt = cStmt.getInt(7);
			
			} catch (HibernateException he) {
				logger.error("Exception occured while updating the user :"+ he.getMessage());
				throw new ERPException("Exception occured while updating the user:"+ he.getMessage());
			} catch (ERPException erp) {
				logger.error("Exception occured while updating the user :"+ erp.getMessage());
				throw new ERPException("Exception occured while updating the user:"+ erp.getMessage());
			} catch (Exception e) {
				logger.error("Exception occured while updating the user :"+ e.getMessage());
				throw new ERPException("Exception occured while updating the user:"+ e.getMessage());
			} finally {
				try {
					cStmt.close();
					con.close();
				} catch (SQLException e) {
				}
				
			}
			logger.debug("=========End AdminDAOImpl UPDATEUSER===================");
		return updateCnt;
	}


	@Override
	public String getVendorAgency(String userVendor) {
		Session session = null;
		logger.debug("=====START AdminDAOImpl getVendorAgency======");
		String agencyName = null;
		try {
			session = sessionFactory.openSession();
			agencyName = (String)session.createQuery("Select v.agencyName from VendorMaster as v where v.vendorName=:name")
					.setParameter("name", userVendor).uniqueResult();
		} catch (HibernateException he) {
			logger.error("Exception occured while loading the vendor :"
					+ he.getMessage());
			throw new ERPException("Exception occured while loading the vendor :"
					+ he.getMessage());
		} catch (ERPException erp) {
			logger.error("Exception occured while loading the vendor :"
					+ erp.getMessage());
			throw new ERPException("Error while loading the vendor");
		} catch (Exception e) {
			logger.error("Exception occured while loading the vendor"
					+ e.getMessage());
		} finally {
			session.close();
		}		
		logger.debug("=====END AdminDAOImpl getVendorAgency======");	
		return agencyName;
	}
	@Override
	public List<String> liststatus(){
		List<String> output=null;
		Session session = null;
		logger.debug("=====START AdminDAOImpl liststatus======");
		try {
			session = sessionFactory.openSession();
			output = session.createQuery("select a.status from AgencyMaster as a").list();
		} catch (HibernateException he) {
			logger.error("Exception occured while loading the cities :"
					+ he.getMessage());
			throw new ERPException("Exception occured while loading the status :"
					+ he.getMessage());
		} catch (ERPException erp) {
			logger.error("Exception occured while loading the status :"
					+ erp.getMessage());
			throw new ERPException("Error while loading the status");
		} catch (Exception e) {
			logger.error("Exception occured while loading the status"
					+ e.getMessage());
		} finally {
			session.close();
		}		
		logger.debug("=====END AdminDAOImpl liststatus======");	
		return output;
		
	}
	@Override
	public List<Roles> listRoleId() {
		List<Roles> output=null;
		Session session = null;
		logger.debug("=====START AdminDAOImpl listRoleId======");
		try {
			session = sessionFactory.openSession();
			Criteria criteria = session.createCriteria(Roles.class);
			output = criteria.list();
		} catch (HibernateException he) {
			logger.error("Exception occured while loading the roles :"
					+ he.getMessage());
			throw new ERPException("Exception occured while loading the roles :"
					+ he.getMessage());
		} catch (ERPException erp) {
			logger.error("Exception occured while loading the roles :"
					+ erp.getMessage());
			throw new ERPException("Error while loading the roles");
		} catch (Exception e) {
			logger.error("Exception occured while loading the roles"
					+ e.getMessage());
		} finally {
			session.close();
		}		
		logger.debug("=====END AdminDAOImpl listRoleId======");	
		return output;
	}
	@Override
	public String checkUniqueRoleId(int newRoleId) {
		logger.debug("=====START AdminDAOImpl checkUniqueRoleId======");	
		Session session=sessionFactory.openSession();
		UserMaster user=null;
		try {
			user=new UserMaster();
			user=(UserMaster) session.createQuery("from UserMaster as u where u.roleId=1").uniqueResult();
			logger.debug("=====END AdminDAOImpl checkUniqueRoleId======");	
		} catch (HibernateException he) {
			logger.error("Exception occured while checking the role :"+ he.getMessage());
			throw new ERPException("Exception occured while checking the role :"+ he.getMessage());
		} catch (ERPException erp) {
			logger.error("Exception occured while checking the role :"+ erp.getMessage());
			throw new ERPException("Error while checking the role");
		} catch (Exception e) {
			logger.error("Exception occured while checking the role"+ e.getMessage());
		} finally {
			session.close();
		}	
		if(user==null)
		{
			return "Does Not Exists";
		}
		return "You cannot create more than one user for Admin. Please select another Role";
	}
	
	@Override
	public List<VendorMaster> getVendorNameActives() {
		Session session = null;
		logger.debug("=====START AdminDAOImpl getVendorNameActives======");
		List<VendorMaster> out=null;
		try {
			session = sessionFactory.openSession();
			Criteria criteria=session.createCriteria(VendorMaster.class);
			criteria.add(Restrictions.eq("isActive", 1));
			out = criteria.list();
		} catch (HibernateException he) {
			logger.error("Exception occured while loading the agencies :"
					+ he.getMessage());
			throw new ERPException("Exception occured while loading the vendors :"
					+ he.getMessage());
		} catch (ERPException erp) {
			logger.error("Exception occured while loading the vendors :"
					+ erp.getMessage());
			throw new ERPException("Error while loading the vendors");
		} catch (Exception e) {
			logger.error("Exception occured while checking the vendors"
					+ e.getMessage());
		} finally {
			session.close();
		}		
		logger.debug("=====END AdminDAOImpl getVendorNameActives======");
		return out;
	}
	
	
	@Override
	public UserMaster UserName(String username) {
		logger.debug("=====START AdminDAOImpl UserName======");
		Session session = null;
		UserMaster userMaster= new UserMaster();
		try {
			session = sessionFactory.openSession();
		userMaster=(UserMaster) session.createQuery("from UserMaster as a where a.username=:name")
				.setParameter("name", username)
				.uniqueResult();
		logger.debug("=====END AdminDAOImpl UserName======");
		} catch (HibernateException he) {
			logger.error("Exception occured while loading the user:"+ he.getMessage());
			throw new ERPException("Exception occured while loading the user:"+ he.getMessage());
		} catch (ERPException erp) {
			logger.error("Exception occured while loading the user:"+ erp.getMessage());
			throw new ERPException("Error while loading the user");
		} catch (Exception e) {
		} finally {
			session.close();
		}
		return userMaster;
	}
/*	@Override
	public int getStatusId(String strStatus, String userRole, String strModule) {
		Connection con=null;
		CallableStatement cStmt=null;
		int statusId=0;
		logger.debug("=====START AdminDAOImpl getStatusId======");
		try {
			con=ConnectionManager.getConnection();
			 cStmt =con.prepareCall("{call GET_STATUSID(?,?,?,?)}");
			 cStmt.setString(1, strStatus);
			 cStmt.setString(2, userRole);
			 cStmt.setString(3, strModule);
			 cStmt.registerOutParameter(4, java.sql.Types.INTEGER);

			// execute getDBUSERByUserId store procedure
			 cStmt.executeUpdate();
			 statusId = cStmt.getInt(4);
		
		} catch (HibernateException he) {
			logger.error("Exception occured while loading the status:"
					+ he.getMessage());
			throw new ERPException(
					"Exception occured while loading the status:"
							+ he.getMessage());
		} catch (ERPException erp) {
			logger.error("Exception occured while loading the status:"
					+ erp.getMessage());
			throw new ERPException("Error while loading the status:");
		} catch (Exception e) {
		} 
	 finally {
			try {
				cStmt.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		logger.debug("=====END AdminDAOImpl getStatusId======");
		return statusId;
	
	}
	*/


	@Override
	public int getStatusId(String strStatus, String userRole, String strModule) {
		logger.debug("=====START AdminDAOImpl getStatusId======");
		Connection con=null;
		CallableStatement cStmt=null;
		int statusId=0;
		try {
			con=ConnectionManager.getConnection();
			 cStmt =con.prepareCall("{call GET_STATUSID(?,?,?,?)}");
			 cStmt.setString(1, strStatus);
			 cStmt.setString(2, userRole);
			 cStmt.setString(3, strModule);
			 cStmt.registerOutParameter(4, java.sql.Types.INTEGER);

			// execute getDBUSERByUserId store procedure
			 cStmt.executeUpdate();
			 statusId = cStmt.getInt(4);
			 logger.debug("=====START AdminDAOImpl getStatusId======");
		} catch (HibernateException he) {
			logger.error("Exception occured while loading the status :"+ he.getMessage());
			throw new ERPException("Exception occured while loading the status :"+ he.getMessage());
		} catch (ERPException erp) {
			logger.error("Exception occured while loading the status :"+ erp.getMessage());
			throw new ERPException("Exception occured while loading the status :"+ erp.getMessage());
		} catch (Exception e) {
			logger.error("Exception occured while loading the status :"+ e.getMessage());
			throw new ERPException("Exception occured while loading the status :"+ e.getMessage());
		} finally {
			try {
				cStmt.close();
				con.close();
			} catch (SQLException e) {
			}
			
		}
		return statusId;
	
	}


	@Override
	public VendorMaster checkVendorName(String vendorName) {
		logger.debug("=====START AdminDAOImpl checkVendorName======");
		Session session = null;
		VendorMaster vendorMaster= new VendorMaster();
		try {
		session = sessionFactory.openSession();
		vendorMaster=(VendorMaster) session.createQuery("from VendorMaster as a where a.vendorName=:name")
				.setParameter("name", vendorName)
				.uniqueResult();
		logger.debug("=====END AdminDAOImpl checkVendorName======");
	} catch (HibernateException he) {
		logger.error("Exception occured while checking the vendor:"+ he.getMessage());
		throw new ERPException("Exception occured while checking the vendor:"+ he.getMessage());
	} catch (ERPException erp) {
		logger.error("Exception occured while checking the vendor:"+ erp.getMessage());
		throw new ERPException("Error while checking the vendor:");
	} catch (Exception e) {
	} finally {
		session.close();
	}
		return vendorMaster;
	}

	@Override
	public List<AgencyMaster> getAgenciesList() {
		Session session = null;// ssionFactory.openSession();
		logger.debug("=====START AdminDAOImpl getAgenciesList======");
		List<AgencyMaster> agencyList = null;
		try {
			session = sessionFactory.openSession();
			agencyList = new ArrayList<AgencyMaster>();
			Query query = session
					.createSQLQuery("CALL Get_Agencies()")
					.addEntity(AgencyMaster.class);
			List<AgencyMaster> list = query.list();
			Iterator<AgencyMaster> itr = list.iterator();
			while (itr.hasNext()) {
				AgencyMaster agencyMaster = new AgencyMaster();
				agencyMaster = itr.next();
				agencyList.add(agencyMaster);
			}
			logger.debug("=====END AdminDAOImpl getAgenciesList======");
		} catch (HibernateException he) {
			logger.error("Exception occured while loading the agencies :"+ he.getMessage());
			throw new ERPException("Exception occured while loading the agencies :"+ he.getMessage());
		} catch (ERPException erp) {
			logger.error("Exception occured while loading the agencies :"+ erp.getMessage());
			throw new ERPException("Exception occured while loading the agencies :"+ erp.getMessage());
		} catch (Exception e) {
			logger.error("Exception occured while loading the agencies :"+ e.getMessage());
			throw new ERPException("Exception occured while loading the agencies :"+ e.getMessage());
		} finally {
			session.close();
		}
		return agencyList;
	}


	//shows the list of state
		@Override
		public List<StateMaster> getStateList() {
			Session session = null;// ssionFactory.openSession();
			logger.debug("======START AdminDAOImpl getStateList======");
			List<StateMaster> StateList = null;
			try {
				session = sessionFactory.openSession();
				StateList = new ArrayList<StateMaster>();
				Query query = session
						.createSQLQuery("CALL GET_STATE_LIST()")
						.addEntity(StateMaster.class);
				List<StateMaster> list = query.list();
				Iterator<StateMaster> itr = list.iterator();
				while (itr.hasNext()) {
					StateMaster stateMaster = new StateMaster();
					stateMaster = itr.next();
					StateList.add(stateMaster);
				}
				logger.debug("======END AdminDAOImpl getStateList======");
			} catch (HibernateException he) {
				logger.error("Exception occured while loading states :"+ he.getMessage());
				throw new ERPException("Exception occured while loading states :"+ he.getMessage());
			} catch (ERPException erp) {
				logger.error("Exception occured while loading states :"+ erp.getMessage());
			throw new ERPException("Exception occured while loading states :"+ erp.getMessage());
			} catch (Exception e) {
				logger.error("Exception occured while loading states :"+ e.getMessage());
				throw new ERPException("Exception occured while loading states :"+ e.getMessage());
			} finally {
				session.close();
			}
			return StateList;
		}



		@Override
		public List<VendorMaster> getVendorsList(int intAgencyId) {
			Session session = null;// ssionFactory.openSession();
			logger.debug("======START AdminDAOImpl getVendorsList======");
			List<VendorMaster> vendorList = null;
			try {
				session = sessionFactory.openSession();
				vendorList = new ArrayList<VendorMaster>();
				Query query = session
						.createSQLQuery("CALL GET_VendorList(:agency)")
						.addEntity(VendorMaster.class)
						.setParameter("agency", intAgencyId);
				List<VendorMaster> list = query.list();
				Iterator<VendorMaster> itr = list.iterator();
				while (itr.hasNext()) {
					VendorMaster vendorMaster = new VendorMaster();
					vendorMaster = itr.next();
					vendorList.add(vendorMaster);
				}
				logger.debug("======END AdminDAOImpl getVendorsList======");
			} catch (HibernateException he) {
				logger.error("Exception occured while loading vendors :"+ he.getMessage());
				throw new ERPException("Exception occured while loading vendors :"+ he.getMessage());
			} catch (ERPException erp) {
				logger.error("Exception occured while loading vendors :"+ erp.getMessage());
				throw new ERPException("Exception occured while loading vendors :"+ erp.getMessage());
			} catch (Exception e) {
				logger.error("Exception occured while loading vendors :"+ e.getMessage());
				throw new ERPException("Exception occured while loading vendors :"+ e.getMessage());
			} finally {
				session.close();
			}
			return vendorList;
		}



		@Override
		public Map<Integer, String> brandMap() {
			logger.debug("======START AdminDAOImpl brandMap======");
			Map<Integer,String> brands=new LinkedHashMap<Integer,String>();
			//ConversionMaster convMap=null;
			BrandMaster brandMap=null; 
			Session session=null;
			try {
			 session = sessionFactory.openSession();
			Query query = session.createSQLQuery("CALL GET_BrandBy_Id").addEntity(BrandMaster.class);
			List<BrandMaster> list = query.list();
			Iterator<BrandMaster> itr=list.iterator();
			while(itr.hasNext()) {
				brandMap=(BrandMaster)itr.next();
				brands.put(brandMap.getBrand_id(),brandMap.getBrandName());
		
			}
			logger.debug("======END AdminDAOImpl brandMap======");
			} catch (HibernateException he) {
				logger.error("Exception occured while loading brands :"+ he.getMessage());
				throw new ERPException("Exception occured while loading brands :"+ he.getMessage());
			} catch (ERPException erp) {
				logger.error("Exception occured while loading brands :"+ erp.getMessage());
				throw new ERPException("Exception occured while loading brands :"+ erp.getMessage());
			} catch (Exception e) {
				logger.error("Exception occured while loading brands :"+ e.getMessage());
				throw new ERPException("Exception occured while loading brands :"+ e.getMessage());
			} finally {
				session.close();
			}
			return brands;
		}
	
	
}

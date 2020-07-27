/*
*PrePlanningDAO.java
*Created By		:Kamal Thappa
*Created Date	:Apr 4, 2018
*/
package com.rural.DAO;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
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
import com.rural.Model.CensusMaster;
import com.rural.Model.ConversionMaster;
import com.rural.Model.PrePlanMaster;
import com.rural.Model.SaturationMaster;
import com.rural.exceptions.ERPException;

@Component
@Repository
@Transactional
public class PrePlanningDAOImpl implements PrePlanningDAO{

	@Resource(name="sessionFactory")
    protected SessionFactory sessionFactory;
	
	static Logger logger =Logger.getLogger(PrePlanningDAOImpl.class);
	
	@SuppressWarnings("unchecked")
	@Override
	public Map<String, String> brandMap() {
		logger.debug("======START PrePlanningDAOImpl brandMap======");
		Map<String,String> brands=new LinkedHashMap<String,String>();
		ConversionMaster convMap=null;
		Session session=null;
		try {
		 session = sessionFactory.openSession();
		Query query = session.createSQLQuery("CALL GET_Brand_Conversion").addEntity(ConversionMaster.class);
		List<ConversionMaster> list = query.list();
		Iterator<ConversionMaster> itr=list.iterator();
		while(itr.hasNext()) {
			convMap=(ConversionMaster)itr.next();
			brands.put(convMap.getBrandname(),convMap.getBrandname());
	
		}
		} catch (HibernateException he) {
			logger.error("Exception occured while loading brands :" + he.getMessage());
			throw new ERPException("Exception occured while loading brands :" + he.getMessage());
		} catch (ERPException erp) {
			logger.error("Exception occured while loading brands :" + erp.getMessage());
			erp.printStackTrace();
			throw new ERPException("Error while loading brands");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		logger.debug("======END PrePlanningDAOImpl brandMap======");
		return brands;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Map<String, String> stateMap() {
		logger.debug("======START PrePlanningDAOImpl stateMap======");
		Map<String,String> states=new LinkedHashMap<String,String>();
		String statename=null;
		Session session=null;
		try{
		 session = sessionFactory.openSession();
		List list = session.createSQLQuery("CALL Get_States(:module)")
				.setParameter("module", "Preplanning").list();
		Iterator itr=list.iterator();
		while(itr.hasNext()) {
			statename=(String) itr.next();
			states.put(statename,statename);
		}
		} catch (HibernateException he) {
			logger.error("Exception occured while loading states :"+ he.getMessage());
			throw new ERPException("Exception occured while loading states :"+ he.getMessage());
		} catch (ERPException erp) {
			logger.error("Exception occured while loading states :"+ erp.getMessage());
			erp.printStackTrace();
			throw new ERPException("Error while loading states");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		logger.debug("======END PrePlanningDAOImpl stateMap======");
		return states;
	}

	

	@SuppressWarnings("unchecked")
	@Override
	public List<String> getcityList(String state) {
		logger.debug("======START PrePlanningDAOImpl getcityList======");
		Session session = null;
		List<String> list=new ArrayList<String>();
		try{
			session=sessionFactory.openSession();
			Query query = session.createSQLQuery("CALL GET_CITYLIST(:state,:module)")
					.setParameter("state", state)
					.setParameter("module", "Preplanning");
		list = query.list();
		logger.debug("======END PrePlanningDAOImpl getcityList======");
		} catch (HibernateException he) {
			logger.error("Exception occured while loading cities :"+ he.getMessage());
			throw new ERPException("Exception occured while loading cities :"+ he.getMessage());
		} catch (ERPException erp) {
			logger.error("Exception occured while loading cities :"+ erp.getMessage());
			erp.printStackTrace();
			throw new ERPException("Error while loading cities");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return list;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<String> getcityList(String strBrand,String strState) {
		logger.debug("======START PrePlanningDAOImpl getcityList======");
		Session session = null;
		List<String> list = null;
		try{
			session = sessionFactory.openSession();
			list=new ArrayList<String>();
		String QueryGetBrand="select distinct c.city From SaturationMaster c where c.state=:state and c.brand=:brand";
		Query query = session.createQuery(QueryGetBrand);
		query.setParameter("brand",strBrand);
		query.setParameter("state",strState);
		 list = query.list();
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
		logger.debug("======END PrePlanningDAOImpl getcityList======");
		return list;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<PrePlanMaster> showAllPrePlanData() {
		logger.debug("======START PrePlanningDAOImpl showAllPrePlanData======");
		List<PrePlanMaster> getAllPrePlanData = new ArrayList<PrePlanMaster>();
		Session session=null;
		try{
		session = sessionFactory.openSession();
		String QueryGetBrand="From PrePlanMaster";
		Query query = session.createQuery(QueryGetBrand);
		
		List<PrePlanMaster> list = query.list();
		Iterator<PrePlanMaster> itr=list.iterator();
		while(itr.hasNext()) {
			
			PrePlanMaster prePlanData=new PrePlanMaster();
			prePlanData=itr.next();
			getAllPrePlanData.add(prePlanData);
		}
		} catch (HibernateException he) {
			logger.error("Exception occured while loading showAllPrePlanData :"+ he.getMessage());
			throw new ERPException("Exception occured while loading showAllPrePlanData :"+ he.getMessage());
		} catch (ERPException erp) {
			logger.error("Exception occured while loading showAllPrePlanData :"+ erp.getMessage());
			throw new ERPException("Error while loading showAllPrePlanData");
		} catch (Exception e) {
			logger.error("Exception occured while loading showAllPrePlanData :"+ e.getMessage());
			throw new ERPException("Error while loading showAllPrePlanData");
		} finally {
			session.close();
		}
		logger.debug("======END PrePlanningDAOImpl showAllPrePlanData======");
		return getAllPrePlanData;

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String> getBrandConvAndSat(String strBrand) {
		logger.debug("======START PrePlanningDAOImpl getBrandConvAndSat======");
		List<String> dataList=new ArrayList<String>();
		Session session=null;
		try{
			session=sessionFactory.openSession();
		String queryGetConvAndSat="From ConversionMaster as b Where b.brandname=:brand";
		Query query = session.createQuery(queryGetConvAndSat);
		query.setParameter("brand",strBrand);
		List<ConversionMaster> list = query.list();
		Iterator<ConversionMaster> itr=list.iterator();
		while(itr.hasNext()) {
			ConversionMaster conversionMaster = new ConversionMaster();
			conversionMaster=itr.next();
			dataList.add(conversionMaster.getConpercent());
			dataList.add(conversionMaster.getAvgsales());
		}
		} catch (HibernateException he) {
			logger.error("Exception occured while loading getBrandConvAndSat :"+ he.getMessage());
			throw new ERPException("Exception occured while loading getBrandConvAndSat :"+ he.getMessage());
		} catch (ERPException erp) {
			logger.error("Exception occured while loading getBrandConvAndSat :"+ erp.getMessage());
			throw new ERPException("Error while loading getBrandConvAndSat");
		} catch (Exception e) {
			logger.error("Exception occured while loading getBrandConvAndSat :"+ e.getMessage());
			throw new ERPException("Error while loading getBrandConvAndSat");
		} finally {
			session.close();
		}
		logger.debug("======END PrePlanningDAOImpl getBrandConvAndSat======");
		return dataList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public String getCitySaturation(String strCity) {
		logger.debug("======START PrePlanningDAOImpl getCitySaturation======");	
		String dataList="";
		Session session=null;
		try{
		 session=sessionFactory.openSession();
		String queryGetSaturation="From SaturationMaster as s Where s.city=:city";
		Query query = session.createQuery(queryGetSaturation);
		query.setParameter("city",strCity);
		List<SaturationMaster> list = query.list();
		Iterator<SaturationMaster> itr=list.iterator();
		while(itr.hasNext()) {
			SaturationMaster satMaster = new SaturationMaster();
			satMaster=itr.next();
			dataList=satMaster.getSatpercent();

		}
		} catch (HibernateException he) {
			logger.error("Exception occured while loading getCitySaturation :"+ he.getMessage());
			throw new ERPException("Exception occured while loading getCitySaturation :"+ he.getMessage());
		} catch (ERPException erp) {
			logger.error("Exception occured while loading getCitySaturation :"+ erp.getMessage());
			throw new ERPException("Error while loading getCitySaturation");
		} catch (Exception e) {
			logger.error("Exception occured while loading getCitySaturation :"+ e.getMessage());
			throw new ERPException("Error while loading getCitySaturation");
		} finally {
			session.close();
		}
		logger.debug("======END PrePlanningDAOImpl getCitySaturation======");	
		return dataList;
	}
	@Override
	public String getBrandCitySaturation(String strBrand, String strCity) {
		logger.debug("======START PrePlanningDAOImpl getBrandCitySaturation======");	
		String dataList="";
		Session session=null;
		try{
			session=sessionFactory.openSession();
		String queryGetSaturation="From SaturationMaster as s Where s.city=:city and s.brand=:brand";
		Query query = session.createQuery(queryGetSaturation);
		query.setParameter("city",strCity);
		query.setParameter("brand",strBrand);
		List<SaturationMaster> list = query.list();
		Iterator<SaturationMaster> itr=list.iterator();
		while(itr.hasNext()) {
			SaturationMaster satMaster = new SaturationMaster();
			satMaster=itr.next();
			dataList=satMaster.getSatpercent();

		}
		} catch (HibernateException he) {
			logger.error("Exception occured while loading getBrandCitySaturation :"+ he.getMessage());
			throw new ERPException("Exception occured while loading getBrandCitySaturation :"+ he.getMessage());
		} catch (ERPException erp) {
			logger.error("Exception occured while loading getBrandCitySaturation :"+ erp.getMessage());
			throw new ERPException("Error while loading getBrandCitySaturation");
		} catch (Exception e) {
			logger.error("Exception occured while loading getBrandCitySaturation :"+ e.getMessage());
			throw new ERPException("Error while loading getBrandCitySaturation");
		} finally {
			session.close();
		}
		logger.debug("======END PrePlanningDAOImpl getBrandCitySaturation======");	
		return dataList;
	}
	@SuppressWarnings("unchecked")
	@Override
	public String getSaturation(String strCity,String strBrand, String strState) {
		logger.debug("======START PrePlanningDAOImpl getSaturation======");
		String dataList="";
		Session session=null;
		try{
		 session=sessionFactory.openSession();
		String queryGetSaturation="From SaturationMaster as s Where s.city=:city and s.state=:state and s.brand=:brand";
		Query query = session.createQuery(queryGetSaturation);
		query.setParameter("city",strCity);
		query.setParameter("state",strState);
		query.setParameter("brand",strBrand);
		List<SaturationMaster> list = query.list();
		Iterator<SaturationMaster> itr=list.iterator();
		while(itr.hasNext()) {
			SaturationMaster satMaster = new SaturationMaster();
			satMaster=itr.next();
			dataList=satMaster.getSatpercent();

		}
		} catch (HibernateException he) {
			logger.error("Exception occured while loading getSaturation :"+ he.getMessage());
			throw new ERPException("Exception occured while loading getSaturation :"+ he.getMessage());
		} catch (ERPException erp) {
			logger.error("Exception occured while loading getSaturation :"+ erp.getMessage());
			throw new ERPException("Error while loading getSaturation");
		} catch (Exception e) {
			logger.error("Exception occured while loading getSaturation :"+ e.getMessage());
			throw new ERPException("Error while loading getSaturation");
		} finally {
			session.close();
		}
		logger.debug("=====END PrePlanningDAOImpl getSaturation======");
		return dataList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CensusMaster> getCensusData(String strCity) {
		logger.debug("=====START PrePlanningDAOImpl getCensusData======");
		List<CensusMaster> dataList=new ArrayList<CensusMaster>();
		Session session=null;
		try{
			session=sessionFactory.openSession();
		String queryCensusData="From CensusMaster as c Where c.city=:city";
		Query query = session.createQuery(queryCensusData);
		query.setParameter("city",strCity);

		List<CensusMaster> list = query.list();
		Iterator<CensusMaster> itr=list.iterator();
		while(itr.hasNext()) {
			CensusMaster census = new CensusMaster();
			census=itr.next();
			dataList.add(census);

		}
		} catch (HibernateException he) {
			logger.error("Exception occured while loading getCensusData :"+ he.getMessage());
			throw new ERPException("Exception occured while loading getCensusData :"+ he.getMessage());
		} catch (ERPException erp) {
			logger.error("Exception occured while loading getCensusData :"+ erp.getMessage());
			throw new ERPException("Error while loading getCensusData");
		} catch (Exception e) {
			logger.error("Exception occured while loading getCensusData :"+ e.getMessage());
			throw new ERPException("Error while loading getCensusData");
		} finally {
			session.close();
		}
		logger.debug("======END PrePlanningDAOImpl getCensusData======");
		return dataList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ConversionMaster> getConversionData(String strBrand) {
		logger.debug("======START PrePlanningDAOImpl getCensusData======");
		List<ConversionMaster> ConversionMasterList=new ArrayList<ConversionMaster>();
		Session session=null;
		try{
			session=sessionFactory.openSession();
		String queryConversionMaster="From ConversionMaster as c Where c.brandname=:brand";
		Query query = session.createQuery(queryConversionMaster);
		query.setParameter("brand",strBrand);
		
		List<ConversionMaster> list = query.list();
		Iterator<ConversionMaster> itr=list.iterator();
		while(itr.hasNext()) {
			ConversionMaster conversion = new ConversionMaster();
			conversion=itr.next();
			ConversionMasterList.add(conversion);

		}
		} catch (HibernateException he) {
			logger.error("Exception occured while loading getCensusData :"+ he.getMessage());
			throw new ERPException("Exception occured while loading getCensusData :"+ he.getMessage());
		} catch (ERPException erp) {
			logger.error("Exception occured while loading getCensusData :"+ erp.getMessage());
			throw new ERPException("Error while loading getCensusData");
		} catch (Exception e) {
			logger.error("Exception occured while loading getCensusData :"+ e.getMessage());
			throw new ERPException("Error while loading getCensusData");
		} finally {
			session.close();
		}
		logger.debug("======END PrePlanningDAOImpl getCensusData======");
		return ConversionMasterList;
	}

	@Override
	public int persistPrePlanMaster(PrePlanMaster preplanMaster) {
		logger.debug("======START PrePlanningDAOImpl persistPrePlanMaster======");
		Session session = sessionFactory.openSession();
		try 
		{
			Transaction tx = session.beginTransaction();
			session.save(preplanMaster);
		    tx.commit();
		} catch (HibernateException he) {
			logger.error("Exception occured while loading persistPrePlanMaster :"+ he.getMessage());
			throw new ERPException("Exception occured while loading persistPrePlanMaster :"+ he.getMessage());
		} catch (ERPException erp) {
			logger.error("Exception occured while loading persistPrePlanMaster :"+ erp.getMessage());
			throw new ERPException("Error while loading persistPrePlanMaster");
		} catch (Exception e) {
			logger.error("Exception occured while loading persistPrePlanMaster :"+ e.getMessage());
			throw new ERPException("Error while loading persistPrePlanMaster");
		} finally {
			session.close();
		}
		logger.debug("======END PrePlanningDAOImpl persistPrePlanMaster======");
		 return 1;
	}

	@Override
	public List<String> getStateList(String strBrand) {
		logger.debug("======START PrePlanningDAOImpl getStateList======");
		Session session=null;
		List<String> list=null;
		try{
			session = sessionFactory.openSession();
			list=new ArrayList<String>();
		String QueryGetBrand="select distinct s.state From SaturationMaster s where s.brand=:brand";
		Query query = session.createQuery(QueryGetBrand);
		query.setParameter("brand",strBrand);
		 list = query.list();
		 logger.debug("======END PrePlanningDAOImpl getStateList======");
		} catch (HibernateException he) {
			logger.error("Exception occured while loading getStateList :"+ he.getMessage());
			throw new ERPException("Exception occured while loading getStateList :"+ he.getMessage());
		} catch (ERPException erp) {
			logger.error("Exception occured while loading getStateList :"+ erp.getMessage());
			throw new ERPException("Error while loading getStateList");
		} catch (Exception e) {
			logger.error("Exception occured while loading getStateList :"+ e.getMessage());
			throw new ERPException("Error while loading getStateList");
		} finally {
			session.close();
		}
		return list;
	}

	

}

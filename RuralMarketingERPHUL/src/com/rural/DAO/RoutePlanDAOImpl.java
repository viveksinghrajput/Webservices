/*
*RoutePlanDAOImpl.java
*Created By		:Bhagya Lakshmi Mula
*Created Date	:Jul 4, 2018
*/
package com.rural.DAO;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.type.StandardBasicTypes;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.rural.Constant.IConstants;
import com.rural.Model.PrePlanMaster;
import com.rural.Model.RoutePlanMaster;
import com.rural.exceptions.ERPException;

@Component
@Repository
public class RoutePlanDAOImpl implements RoutePlanDAO {
	@Resource(name = "sessionFactory")
	protected SessionFactory sessionFactory;

	static Logger logger =Logger.getLogger(RoutePlanDAOImpl.class);
	
	@Override
	public List<String> getCities(String strBrand) {
		logger.debug("=====START RoutePlanDAOImpl getCities======");
		List<String> listCities = null;
		Session session = null;
		try {
			session = sessionFactory.openSession();
			listCities = new ArrayList<String>();
			Query query = session.createSQLQuery("CALL GET_CITIES(:brand)")
					.setParameter("brand", strBrand);
			listCities = ((List<String>)query.list());
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
		logger.debug("=====END RoutePlanDAOImpl getCities======");
		return listCities;
	}

	@Override
	public List<String> getBrands() {
		logger.debug("=====START RoutePlanDAOImpl getBrands======");
		Session session = null;
		List<String> listBrand = new ArrayList<String>();
		try {
			session = sessionFactory.openSession();
			Query query = session.createSQLQuery("CALL GET_BRANDS()");
			listBrand = query.list();
		} catch (HibernateException he) {
			logger.error("Exception occured while loading brand :"+ he.getMessage());
			throw new ERPException("Exception occured while loading brand :"+ he.getMessage());
		} catch (ERPException erp) {
			logger.error("Exception occured while loading brand :"+ erp.getMessage());
			throw new ERPException("Error while loading brands");
		} catch (Exception e) {
			logger.error("Exception occured while loading brand :"+ e.getMessage());
			throw new ERPException("Error while loading brands");
		} finally {
			session.close();
		}
		logger.debug("=====END RoutePlanDAOImpl getBrands======");
		return listBrand;

	}

	@Override
	public List<PrePlanMaster> getPrePlanList() {
		Session session = null;// ssionFactory.openSession();
		logger.debug("=====START RoutePlanDAOImpl getPrePlanList======");
		List<PrePlanMaster> prePlanList = null;
		try {
			session = sessionFactory.openSession();
			prePlanList = new ArrayList<PrePlanMaster>();
			Query query = session
					.createSQLQuery("CALL GET_PREPLAN_LIST()")
					.addEntity(PrePlanMaster.class);
			List<PrePlanMaster> list = query.list();
			Iterator<PrePlanMaster> itr = list.iterator();
			while (itr.hasNext()) {
				PrePlanMaster prePlanMaster = new PrePlanMaster();
				prePlanMaster = itr.next();
				prePlanList.add(prePlanMaster);
			}
		} catch (HibernateException he) {
			logger.error("Exception occured while loading preplans :"+ he.getMessage());
			throw new ERPException("Exception occured while loading preplans :" + he.getMessage());
		} catch (ERPException erp) {
			logger.error("Exception occured while loading preplans :"+ erp.getMessage());
			throw new ERPException("Error while loading preplans");
		} catch (Exception e) {
			logger.error("Exception occured while loading preplans :"+ e.getMessage());
			throw new ERPException("Error while loading preplans");
		} finally {
			session.close();
		}
		logger.debug("=====END RoutePlanDAOImpl getPrePlanList======");
		return prePlanList;
	}
	@Override
	public List<RoutePlanMaster> getRouteplanlist(String strBrand,
			String strCity, String strLsm) {
		logger.debug("=====START RoutePlanDAOImpl getRouteplanlist======");
		Session session = null;
		List<RoutePlanMaster> routePlanList = null;
		try {
			session = sessionFactory.openSession();
			logger.debug(" strBrand :: "+strBrand+" strCity ::: "+strCity+" strLsm "+strLsm);
			routePlanList = new ArrayList<RoutePlanMaster>();
			Query query = session
					.createSQLQuery("CALL ROUTE_PLAN_PROC(:brand,:city,:lsm)")
					.addEntity(RoutePlanMaster.class)
					.setParameter("brand", strBrand)
					.setParameter("city", strCity).setParameter("lsm", strLsm);
			List<RoutePlanMaster> list = query.list();
			Iterator<RoutePlanMaster> itr = list.iterator();
			while (itr.hasNext()) {

				RoutePlanMaster routePlanMaster = new RoutePlanMaster();
				routePlanMaster = itr.next();
				routePlanList.add(routePlanMaster);
			}
		} catch (HibernateException he) {
			logger.error("Exception occured while loading routeplans :"+ he.getMessage());
			throw new ERPException( "Exception occured while loading routeplans :" + he.getMessage());
		} catch (ERPException erp) {
			logger.error("Exception occured while loading routeplans :"+ erp.getMessage());
			throw new ERPException("Error while loading routeplanlist");
		} catch (Exception e) {
			logger.error("Exception occured while loading routeplans :"+ e.getMessage());
			throw new ERPException("Error while loading routeplanlist");
		} finally {
			session.close();
		}
		logger.debug("=====END RoutePlanDAOImpl getRouteplanlist======");
		return routePlanList;
	}

	@Override
	public List<RoutePlanMaster> insertRoutePlan(String strBrand,
			String strCity, String strLsm, String strSessionId, String strUser) {
		logger.debug("=====START RoutePlanDAOImpl insertRoutePlan======");
		Session session = null;
		List<RoutePlanMaster> routePlanList = null;
		try {
			session = sessionFactory.openSession();
			Transaction tx = session.beginTransaction();
			routePlanList = new ArrayList<RoutePlanMaster>();
			String strRouteplanName = strSessionId + strBrand + strCity;
			Query query = session
					.createSQLQuery(
							"CALL CREATE_ROUTE_PLAN(:session,:user,:brand,:city,:lsm,:routeplanName)")
					.addEntity(RoutePlanMaster.class)
					.setParameter("session", strSessionId)
					.setParameter("user", strUser)
					.setParameter("brand", strBrand)
					.setParameter("city", strCity).setParameter("lsm", strLsm)
					.setParameter("routeplanName", strRouteplanName);
			List<RoutePlanMaster> list = query.list();
			tx.commit();
			Iterator<RoutePlanMaster> itr = list.iterator();
			while (itr.hasNext()) {

				RoutePlanMaster routePlanMaster = new RoutePlanMaster();
				routePlanMaster = itr.next();
				routePlanList.add(routePlanMaster);

			}
		} catch (HibernateException he) {
			logger.error("Exception occured while creating Route plan :"+ he.getMessage());
			throw new ERPException("Exception occured while creating Route plan :"+ he.getMessage());
		} catch (ERPException erp) {
			logger.error("Exception occured while creating Route plan :"+ erp.getMessage());
			throw new ERPException("Error while creating RoutePlan");
		} catch (Exception e) {
			logger.error("Exception occured while loading routeplans :"+ e.getMessage());
			throw new ERPException("Error while loading routeplanlist");
		} finally {
			session.close();
		}
		logger.debug("=====END RoutePlanDAOImpl insertRoutePlan======");
		return routePlanList;
	}

	@Override
	public PrePlanMaster getPlanDetails(int prePlanID) {
		logger.debug("=====START RoutePlanDAOImpl getPlanDetails======");
		Session session = null;
		PrePlanMaster prePlanMaster = null;
		try {
			session = sessionFactory.openSession();
			Query query = session
					.createSQLQuery("CALL CALL_PREPLAN(:prePlanId)")
					.addScalar("brand", StandardBasicTypes.STRING)
					.addScalar("citytown", StandardBasicTypes.STRING)
					.setParameter("prePlanId", prePlanID);

			Iterator itr = query.list().iterator();
			while (itr.hasNext()) {
				prePlanMaster = new PrePlanMaster();
				Object[] obj = (Object[]) itr.next();

				prePlanMaster.setBrand((String) obj[0]);
				prePlanMaster.setCitytown((String) obj[1]);
			}
		} catch (HibernateException he) {
			logger.error("Exception occured in getPlanDetails:" + he.getMessage());
			throw new ERPException("Exception occured in getPlanDetails :" + he.getMessage());
		} catch (ERPException erp) {
			logger.error("Exception occured in getPlanDetails:" + erp.getMessage());
			throw new ERPException("Exception occured in getPlanDetails :" +erp.getMessage());
		} catch (Exception e) {
			logger.error("Exception occured in getPlanDetails:" + e.getMessage());
			throw new ERPException("Exception occured in getPlanDetails :" + e.getMessage());
		} finally {
			session.close();
		}
		logger.debug("=====END RoutePlanDAOImpl getPlanDetails======");
		return prePlanMaster;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<RoutePlanMaster> getTargetWards(String strBrand,
			String strCity, double targetAmount, String strconsiderationset, String[] strQuadrant ) {
		logger.debug("=====START RoutePlanDAOImpl getTargetWards======");
		Session session = null;
		List<RoutePlanMaster> routePlanList = null;
		Query query=null;
		Double temp=0.0;
		String strQuadrantTemp=null;
		Double targetAmounttemp=0.0;
		List<List<RoutePlanMaster>> masters=new ArrayList<List<RoutePlanMaster>>();
		try {
			session = sessionFactory.openSession();
			logger.debug(" strBrand :: "+strBrand+" strCity ::: "+strCity+" targetAmount "+targetAmount+"strconsiderationset : "+strconsiderationset+" strquadrant :: "+strQuadrant);
			routePlanList = new ArrayList<RoutePlanMaster>();
			List<RoutePlanMaster> list4th =null;
			List<RoutePlanMaster> list3rd =null;
			List<RoutePlanMaster> list1st =null;
			List<RoutePlanMaster> list2nd =null;
			List<RoutePlanMaster> listQuadrant =null;
			/* query = session
					.createSQLQuery("CALL GET_TARGET_WARDS(:brand,:city,:amount, :quadrant)")
					.addEntity(RoutePlanMaster.class)
					.setParameter("brand", strBrand)
					.setParameter("city", strCity)
					.setParameter("amount", targetAmount)
					.setParameter("quadrant", strQuadrant);*/
					if(strconsiderationset.equalsIgnoreCase("Yes")) {
				
						strQuadrantTemp= IConstants.Quadrant4;
							 query = session
									.createSQLQuery("CALL GET_TARGET_WARDS(:brand,:city,:amount, :quadrant)")
									.addEntity(RoutePlanMaster.class)
									.setParameter("brand", strBrand)
									.setParameter("city", strCity)
									.setParameter("amount", targetAmount)
									.setParameter("quadrant", strQuadrantTemp);
							 list4th= query.list();
							 Iterator<RoutePlanMaster> itr4th = list4th.iterator();
							 
								while (itr4th.hasNext()) {

									RoutePlanMaster routePlanMaster = new RoutePlanMaster();
									routePlanMaster = itr4th.next();
									temp=temp+Long.parseLong(routePlanMaster.getBalance_TG_HH());
									routePlanList.add(routePlanMaster);
									masters.add(routePlanList);
								}
								if(targetAmount>temp) {
									targetAmounttemp= targetAmount-temp;
									strQuadrantTemp= IConstants.Quadrant3;
									 query = session
											.createSQLQuery("CALL GET_TARGET_WARDS(:brand,:city,:amount, :quadrant)")
											.addEntity(RoutePlanMaster.class)
											.setParameter("brand", strBrand)
											.setParameter("city", strCity)
											.setParameter("amount", targetAmounttemp)
											.setParameter("quadrant", strQuadrantTemp);
									 list3rd= query.list();
									 Iterator<RoutePlanMaster> it3rd = list3rd.iterator();
									 
										while (it3rd.hasNext()) {

											RoutePlanMaster routePlanMaster = new RoutePlanMaster();
											routePlanMaster = it3rd.next();
											temp=temp+Long.parseLong(routePlanMaster.getBalance_TG_HH());
											routePlanList.add(routePlanMaster);
											masters.add(routePlanList);
										}
								}
								if(targetAmount>temp) {
									targetAmounttemp= targetAmount-temp;
									strQuadrantTemp= IConstants.Quadrant1;
									 query = session
											.createSQLQuery("CALL GET_TARGET_WARDS(:brand,:city,:amount, :quadrant)")
											.addEntity(RoutePlanMaster.class)
											.setParameter("brand", strBrand)
											.setParameter("city", strCity)
											.setParameter("amount", targetAmounttemp)
											.setParameter("quadrant", strQuadrantTemp);
									 list1st= query.list();
									 Iterator<RoutePlanMaster> itr1st = list1st.iterator();
										while (itr1st.hasNext()) {

											RoutePlanMaster routePlanMaster = new RoutePlanMaster();
											routePlanMaster = itr1st.next();
											temp=temp+Long.parseLong(routePlanMaster.getBalance_TG_HH());
											routePlanList.add(routePlanMaster);
											masters.add(routePlanList);
										}
								}
								if(targetAmount>temp) {
									targetAmounttemp= targetAmount-temp;
									strQuadrantTemp= IConstants.Quadrant2;
									 query = session
											.createSQLQuery("CALL GET_TARGET_WARDS(:brand,:city,:amount, :quadrant)")
											.addEntity(RoutePlanMaster.class)
											.setParameter("brand", strBrand)
											.setParameter("city", strCity)
											.setParameter("amount", targetAmounttemp)
											.setParameter("quadrant", strQuadrantTemp);
									 list2nd= query.list();
									 Iterator<RoutePlanMaster> itr2nd = list2nd.iterator();
									 
										while (itr2nd.hasNext()) {

											RoutePlanMaster routePlanMaster = new RoutePlanMaster();
											routePlanMaster = itr2nd.next();
											temp=temp+Long.parseLong(routePlanMaster.getBalance_TG_HH());
											routePlanList.add(routePlanMaster);
											masters.add(routePlanList);
										}
								}
							}
							else {
								targetAmounttemp= targetAmount;
								for(int i=0;i<strQuadrant.length;i++) {
								  query = session
											.createSQLQuery("CALL GET_TARGET_WARDS(:brand,:city,:amount, :quadrant)")
											.addEntity(RoutePlanMaster.class)
											.setParameter("brand", strBrand)
											.setParameter("city", strCity)
											.setParameter("amount", targetAmounttemp)
											.setParameter("quadrant", strQuadrant[i]);
								  listQuadrant= query.list();
								  Iterator<RoutePlanMaster> itr = listQuadrant.iterator();
									while (itr.hasNext()) {

										RoutePlanMaster routePlanMaster = new RoutePlanMaster();
										routePlanMaster = itr.next();
										temp=temp+Long.parseLong(routePlanMaster.getBalance_TG_HH());
										routePlanList.add(routePlanMaster);
									}
									
									targetAmounttemp= targetAmount-temp;
									if(targetAmount<temp) {
										break;
									}
								
								}
							}
					
		} catch (HibernateException he) {
			logger.error("Exception occured while loading target wards :"+ he.getMessage());
			throw new ERPException( "Exception occured while loading target wards :" + he.getMessage());
		} catch (ERPException erp) {
			logger.error("Exception occured while loading target wards :"+ erp.getMessage());
			throw new ERPException("Error while loading Target Wards");
		} catch (Exception e) {
			logger.error("Exception occured while loading target wards :"+ e.getMessage());
			throw new ERPException("Error while loading Target Wards");
		} finally {
			session.close();
		}
		logger.debug("=====END RoutePlanDAOImpl getTargetWards======");
		return routePlanList;
	}

}

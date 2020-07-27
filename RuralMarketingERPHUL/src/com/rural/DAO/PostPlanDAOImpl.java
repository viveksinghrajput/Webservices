/*
*PostPlanDAOImpl.java
*Created By		:Bhagya Lakshmi Mula
*Created Date	:Jul 4, 2018
*/
package com.rural.DAO;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import javax.annotation.Resource;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import com.rural.Constant.IConstants;
import com.rural.Model.SalesMaster;
import com.rural.exceptions.ERPException;
import com.rural.utility.ConnectionManager;

@Component
@Repository
public class PostPlanDAOImpl implements PostPlanDAO {
	@Resource(name = "sessionFactory")
	protected SessionFactory sessionFactory;

	static Logger logger =Logger.getLogger(PostPlanDAOImpl.class);
	
	@Override
	public List<String> getCities() {
		logger.debug("======START PostPlanDAOImpl getCities======");
		List<String> listCities = new ArrayList<String>();
		Session session = null;
		try {
			session = sessionFactory.openSession();
			Query query = session
					.createSQLQuery("CALL GET_CITIES_POSTPLANNING()");
			listCities = query.list();
			logger.debug("======END PostPlanDAOImpl getCities======");
		} catch (HibernateException he) {
			logger.error("Exception occured while loading cities :" + he.getMessage());
			throw new ERPException("Exception occured while loading cities :" + he.getMessage());
		} catch (ERPException erp) {
			logger.error("Exception occured while loading cities :" + erp.getMessage());
			throw new ERPException("Exception occured while loading cities :" + erp.getMessage());
		} catch (Exception e) {
			logger.error("Exception occured while loading cities :" + e.getMessage());
			throw new ERPException("Exception occured while loading cities :" + e.getMessage());
		} finally {
			session.close();
		}
		return listCities;
	}

	@Override
	public List<String> getCalendar(String strFrequency) {
		logger.debug("======START PostPlanDAOImpl getCalendar ======");
		List<String> listCalendar = new ArrayList<String>();
		Session session = null;
		try {
			session = sessionFactory.openSession();
			Query query = session.createSQLQuery(
					"CALL GET_CALENDAR(:frequency)").setParameter("frequency",
					strFrequency);
			listCalendar = query.list();
			logger.debug("======END PostPlanDAOImpl getCalendar ======");
		} catch (HibernateException he) {
			logger.error("Exception occured while loading Calendar :" + he.getMessage());
			throw new ERPException("Exception occured while loading Calendar :" + he.getMessage());
		} catch (ERPException erp) {
			logger.error("Exception occured while loading Calendar :" + erp.getMessage());
			throw new ERPException("Exception occured while loading Calendar");
		} catch (Exception e) {
			logger.error("Exception occured while loading Calendar :" + e.getMessage());
			throw new ERPException("Exception occured while loading Calendar");
		} finally {
			session.close();
		}
		return listCalendar;
	}

	@Override
	public List<String> getSelectedCalendar(String strFrom, String strTo,
			String strFrequency) {
		logger.debug("======START PostPlanDAOImpl getSelectedCalendar ======");
		List<String> listCalendar = new ArrayList<String>();
		Session session = null;
		try {
			session = sessionFactory.openSession();
			Query query = session
					.createSQLQuery(
							"CALL GET_SELECTEDCALENDAR(:from,:to,:frequency)")
					.setParameter("from", strFrom).setParameter("to", strTo)
					.setParameter("frequency", strFrequency);
			listCalendar = query.list();
			logger.debug("======END PostPlanDAOImpl getSelectedCalendar ======");
		} catch (HibernateException he) {
			logger.error("Exception occured while loading Calendar :" + he.getMessage());
			throw new ERPException("Exception occured while loading Calendar :" + he.getMessage());
		} catch (ERPException erp) {
			logger.error("Exception occured while loading Calendar :" + erp.getMessage());
		} catch (Exception e) {
			logger.error("Exception occured while loading Calendar :" + e.getMessage());
		} finally {
			session.close();
		}
		return listCalendar;
	}

	public List<String> getPeriod(String strFrequency, String strFromQtr,
			String strToQtr) {
		logger.debug("======START PostPlanDAOImpl getPeriod ======");
		Session session = null;
		List<String> listQuarters = null;
		try {
			session = sessionFactory.openSession();
			listQuarters = new ArrayList<String>();
			Query query = session
					.createSQLQuery("CALL GET_PERIOD(:frequency,:from,:to)")
					.setParameter("frequency", strFrequency)
					.setParameter("from", strFromQtr)
					.setParameter("to", strToQtr);
			listQuarters = query.list();
			logger.debug("======END PostPlanDAOImpl getPeriod ======");
		} catch (HibernateException he) {
			logger.error("Exception occured while loading Period :" + he.getMessage());
			throw new ERPException("Exception occured while loading Period :" + he.getMessage());
		} catch (ERPException erp) {
			logger.error("Exception occured while loading Period :" + erp.getMessage());
			throw new ERPException("Exception occured while loading Period");
		} catch (Exception e) {
			logger.error("Exception occured while loading Period :" + e.getMessage());
		} finally {
			session.close();
		}
		return listQuarters;
	}

	@Override
	public List<SalesMaster> genereateReport(String strCity,
			String strFrequency, String strFromQtr, String strToQtr) {
		logger.debug("======START PostPlanDAOImpl genereateReport=====");
		Connection conn = null;
		ResultSet rs = null;
		CallableStatement stmt = null;
		SalesMaster salesMaster = null;
		Map<String, Double> salesMap = null;
		Map<String, Double> totSalesMap = null;
		String firstYear = null;
		String secondYear = null;
		DecimalFormat decimalFormat = new DecimalFormat(".##");
		if (strFrequency.equals(IConstants.MONTH)
				|| strFrequency.equals(IConstants.QUARTER)) {
			firstYear = strFromQtr.substring(3);
			secondYear = strToQtr.substring(3);
		} else {
			firstYear = strFromQtr;
			secondYear = strToQtr;
		}
		List<SalesMaster> salesMasterList = new ArrayList<SalesMaster>();
		Map<String,Double> h=new LinkedHashMap<String, Double>();
		String query = "{ CALL GENERATE_POST_PLAN_REPORT(?,?,?,?) }";
		try {
			conn = ConnectionManager.getConnection();
			stmt = conn.prepareCall(query);
			stmt.setString(1, strCity);
			stmt.setString(2, strFromQtr);
			stmt.setString(3, strToQtr);
			stmt.setString(4, strFrequency);
			rs = stmt.executeQuery();
			ResultSetMetaData metadata = rs.getMetaData();
			while (rs.next()) {
				// Retrieve by column name
				salesMaster = new SalesMaster();
				salesMap = new LinkedHashMap<String, Double>();
				totSalesMap = new LinkedHashMap<String, Double>();
				salesMaster.setCity(rs.getString("city"));
				salesMaster.setTotal_HH(rs.getString("tot_hh"));
				salesMaster.setNoHH(rs.getString("noofHH"));
				salesMaster.setPenetration(rs.getInt("Penetration"));
				salesMaster.setDone(rs.getString("done"));
				salesMaster.setWard(rs.getString("ward"));
				salesMaster.setSlab_saturation(rs.getInt("saturation"));
				salesMaster.setTotal_sales(rs.getString("tot_sales"));
				/*// calculate Sales
				String tempSalesFromBD = rs.getString("sales");
				StringTokenizer tokenizerSales = new StringTokenizer(
						tempSalesFromBD, ",");
				while (tokenizerSales.hasMoreTokens()) {
					String tempSales = tokenizerSales.nextToken();
					salesMap.put(tempSales.substring(0, 7),
							Double.parseDouble(tempSales.substring(8)));
				}
				salesMaster.setSalesMap(salesMap);
				// Ended calculate sales
*/
				// Calculate Total sales
				String tempTotalSalesFromBD = rs.getString("tot_sales");
				StringTokenizer tokenizerTotalSales = new StringTokenizer(
						tempTotalSalesFromBD, ",");
				Double d1 = 0.00;
				Double d2 = 0.00;
				String strTotalSales = null;
				while (tokenizerTotalSales.hasMoreTokens()) {
					strTotalSales = tokenizerTotalSales.nextToken();
					if (strTotalSales.substring(0, 4).equals(firstYear)) {
						d1 = d1
								+ Double.parseDouble(strTotalSales.substring(5));

					} else {
						d2 = d2
								+ Double.parseDouble(strTotalSales.substring(5));

					}
				}
				if (firstYear.equals(secondYear)) {
					totSalesMap.put(firstYear,
							Double.parseDouble(decimalFormat.format(d1)));
				} else {
					totSalesMap.put(firstYear,
							Double.parseDouble(decimalFormat.format(d1)));
					totSalesMap.put(secondYear,
							Double.parseDouble(decimalFormat.format(d2)));
				}
				salesMaster.setSalesMap(salesMap);
				salesMaster.setTotSalesMap(totSalesMap);
				
				// Ended Total sales

				salesMasterList.add(salesMaster);
			}
			logger.debug("=====END PostPlanDAOImpl genereateReport=====");
		} catch (SQLException he) {
			logger.error("Exception occured while in Genereate Report :" + he.getMessage());
			throw new ERPException( "Exception occured while in Genereate Report :" + he.getMessage());
		} catch (ERPException erp) {
			logger.error("Exception occured while in Genereate Report :" + erp.getMessage());
		} catch (Exception e) {
			logger.error("Exception occured while in Genereate Report :" + e.getMessage());
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (stmt != null)
					stmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return salesMasterList;
	}

	@Override
	public List<String> getMOC(String tempQrt, String strFrequency) {
		logger.debug("=====START PostPlanDAOImpl getMOC=====");
		List<String> listCalendar = new ArrayList<String>();
		Session session = null;
		try {
			session = sessionFactory.openSession();
			Query query = session
					.createSQLQuery(
							"CALL GET_MOC_SELECTEDCALENDAR(:from,:frequency)")
					.setParameter("from", tempQrt)
					.setParameter("frequency", strFrequency);
			listCalendar = query.list();
			logger.debug("=====END PostPlanDAOImpl getMOC=====");
		} catch (HibernateException he) {
			logger.error("Exception occured while loadin MOC :" + he.getMessage());
			throw new ERPException("Exception occured while loading MOC :" 	+ he.getMessage());
		} catch (ERPException erp) {
			logger.error("Exception occured while loadin MOC :" + erp.getMessage());
			throw new ERPException("Exception occured while loading getMOC");
		} catch (Exception e) {
			logger.error("Exception occured while loadin MOC :" + e.getMessage());
		} finally {
			session.close();
		}
		return listCalendar;
	}

	
}

/*
*StockDAOImpl.java
*Created By		:SaiTej Deep
*Created Date	:Jul 4, 2018
*/
package com.rural.DAO;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.transaction.Transactional;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import com.rural.Model.CreateInvoices;
import com.rural.Model.ItemDescriptionMaster;
import com.rural.Model.Stock;
import com.rural.Model.StockUsers;
import com.rural.exceptions.ERPException;
import com.rural.utility.ConnectionManager;

@Component
@Repository
@Transactional
@EnableTransactionManagement
public class StockDAOImpl implements StockDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	static Logger logger =Logger.getLogger(StockDAOImpl.class);
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Stock> getAllStocks(Integer userId,String userRole) {
		logger.debug("=====START StockDAOImpl getAllStates======");
		List<Stock> stockList = null ;
		Session session=null;
		try {
			session = sessionFactory.openSession();
			Query query = session
					.createSQLQuery(
							"CALL GET_STOCKLIST(:user,:role)")
					.addEntity(Stock.class)
		.setParameter("user",userId)
		.setParameter("role",userRole);
		stockList= query.list();
		}catch(HibernateException he) {
			logger.error("Getting error while loading stock list "+ he.getMessage());
			throw new ERPException("Exception occured while loading stock list:"+ he.getMessage());
		}catch(Exception e) {
			logger.error("Getting error while loading stock list "
					+ e.getMessage());
		}
		finally{
			session.close();
		}
		return stockList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String> getAllStates() {
		logger.debug("======START StockDAOImpl getAllStates======");
		List<String> listStates = null;
		Session session = null;
		try {
			session = sessionFactory.openSession();
			listStates = new ArrayList<String>();
			Query query = session.createSQLQuery("CALL Get_States(:module)")
					.setParameter("module", "Stock");
			listStates = query.list();
			logger.debug("======END StockDAOImpl getAllStates======");
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

	@SuppressWarnings("unchecked")
	@Override
	public List<String> getAllCities(String state) {
		logger.debug("=====START StockDAOImpl getAllCities======");
		Session session = null;
		List<String> list = null;
		try {
		session= sessionFactory.openSession();
		session = sessionFactory.openSession();
		list = new ArrayList<String>();
		Query query = session.createSQLQuery("CALL GET_CITYLIST(:state,:module)")
				.setParameter("state", state)
				.setParameter("module", "Stock");
		list = query.list();
		logger.debug("=====END StockDAOImpl getAllCities======");
		}
		catch (ERPException erp) {
			logger.error("Exception occured while loading cities:"+ erp.getMessage());
			throw new ERPException("Error while loading cities");
		} catch (Exception e) {
			logger.error("Exception occured while loading cities:"+ e.getMessage());
			throw new ERPException("Error while loading cities");
		} finally {
			session.close();
		}
		return list;
	}

	@Override
	public boolean addStock(Stock stock) {
		logger.debug("=====START StockDAOImpl addStock======");
		Session session = null;
		boolean flag=false;
		try {
			session = sessionFactory.openSession();
			Transaction transaction = session.beginTransaction();
			session.save(stock);
			for(CreateInvoices invoice : stock.getInvoices()) {
				if( invoice.getItemId() != null  ) {
					invoice.setStock(stock);
					if(invoice.getCases().equals("") ) {
						invoice.setCases("0");
					}
					if(invoice.getUnits().equals("")) {
						invoice.setUnits("0");
					}
					invoice.setNet_amount(invoice.getNet_amount().replace(",", ""));
					session.save(invoice);
				}
			}
			transaction.commit();
			flag=true;
		}
		catch (ERPException erp) {
			logger.error("Exception occured while adding invoice:"+ erp.getMessage());
			throw new ERPException("Error while adding invoice");
		} catch (Exception e) {
			logger.error("Exception occured while adding invoice:"+ e.getMessage());
			throw new ERPException("Error while adding invoice");
		} finally {
			session.close();
		}
		logger.debug("=====END StockDAOImpl addStock======");
		return flag;
		}


	@Override
	@Transactional
	public boolean addInvoice(CreateInvoices invoice) {
		logger.debug("=====START StockDAOImpl addStock======");
		Session session = null;
		boolean flag=false;
		try {
			session= sessionFactory.openSession();
			session.save(invoice);
			flag=true;
		}catch (ERPException erp) {
			logger.error("Exception occured while adding invoice:"+ erp.getMessage());
			throw new ERPException("Error while adding invoice");
		} catch (Exception e) {
			logger.error("Exception occured while adding invoice:"+ e.getMessage());
			throw new ERPException("Error while adding invoice");
		} finally {
			session.close();
		}
		logger.debug("=====END StockDAOImpl addInvoice======");
			return flag;
		}
	
	@Override
	public String saveInvoice(Stock stock) {
		logger.debug("=====START StockDAOImpl saveInvoice======");
		Session session = null;
		try {
			session= sessionFactory.openSession();
			session.saveOrUpdate(stock);
			return "success"+stock.getBill_number();
		}catch (ERPException erp) {
			logger.error("Exception occured while saving invoice:"+ erp.getMessage());
			throw new ERPException("Error while saving invoice");
		} catch (Exception e) {
			logger.error("Exception occured while saving invoice:"+ e.getMessage());
		} finally {
			session.close();
		}
		logger.debug("=====END StockDAOImpl saveInvoice======");
			return "failure";
		}
	


	@Override
	public CreateInvoices getInvoiceItemsByBillNumber(String billNumber) {
		logger.debug("=====START StockDAOImpl getInvoiceItemsByBillNumber======");
		Session session = null;
		CreateInvoices createInvoices = null;
		try {
		session= sessionFactory.openSession();
		createInvoices = new CreateInvoices();
		createInvoices=(CreateInvoices)session.createQuery("From CreateInvoices cs where cs.bill_number=:billNumber").setParameter("billNumber", billNumber).uniqueResult();
		}catch (ERPException erp) {
			logger.error("Exception occured while getting invoice item by billnumber:"+ erp.getMessage());
			throw new ERPException("Error while getting invoice item by billnumber");
		} catch (Exception e) {
			logger.error("Exception occured while getting invoice item by billnumber:"+ e.getMessage());
			throw new ERPException("Error while getting invoice item by billnumber");
		} finally {
			session.close();
		}
		logger.debug("=====END StockDAOImpl getInvoiceItemsByBillNumber======");
		return createInvoices;
	}


	@Override
	public Stock getStockByBillNumber(String billNumber) {
		logger.debug("=====START StockDAOImpl getStockByBillNumber======");
		Session session = null;
		Stock stock = new Stock();
		List<CreateInvoices> items = new ArrayList<CreateInvoices>();
		try {
			session= sessionFactory.openSession();
			stock=(Stock)session.createQuery("From Stock s where s.bill_number=:billNumber").setParameter("billNumber", billNumber).uniqueResult();
			if(stock != null && stock.getInvoices().size() >0)
				items = stock.getInvoices();
		}catch (ERPException erp) {
			logger.error("Exception occured getting stock by billnumber:"+ erp.getMessage());
			throw new ERPException("Error while getting stock by billnumber");
		} catch (Exception e) {
			logger.error("Exception occured getting stock by billnumber:"+ e.getMessage());
			throw new ERPException("Error while getting stock by billnumber");
		} finally {
			session.close();
		}
		logger.debug("=====END StockDAOImpl getStockByBillNumber======");
			return stock;
		}
	


	@SuppressWarnings("unchecked")
	 @Override
	 public List<CreateInvoices> getInvoiceItemsByInvoice(String billNumber) {
	 logger.debug("=====START StockDAOImpl getInvoiceItemsByInvoice======");
	 Session session = null;
	 List<CreateInvoices> itemList = null;
	 try{
	 session = sessionFactory.openSession();
	 itemList=new ArrayList<CreateInvoices>();
	 Query query = session.createSQLQuery("CALL Get_Invoice_download(:billNumber)")
	 .addEntity(CreateInvoices.class)
	 .setParameter("billNumber", billNumber);
	 itemList = query.list();

	 logger.info("=====END UserDAOImpl validateUser======");
	 }catch (ERPException erp) {
	 logger.error("Exception occured getting invoice items by Invoice:"+ erp.getMessage());
	 throw new ERPException("Error while getting invoice items by Invoice:");
	 } catch (Exception e) {
	 logger.error("Exception occured getting invoice items by Invoice:"+ e.getMessage());
	 throw new ERPException("Error while getting invoice items by Invoice:");
	 } finally {
	 session.close();
	 }
	 logger.debug("=====END StockDAOImpl getInvoiceItemsByInvoice======");
	 return itemList;

	 }
	@SuppressWarnings("unchecked")
	@Override
	public List<ItemDescriptionMaster> getAllitemDesc() {
		logger.debug("=====START StockDAOImpl getAllitemDesc======");
		Session session = null;
		List<ItemDescriptionMaster> list = new ArrayList<ItemDescriptionMaster>();
		try {
			session= sessionFactory.openSession();
			list = session.createQuery("from ItemDescriptionMaster").list();
		}
		catch (ERPException erp) {
			logger.error("Exception occured getting items desc:"+ erp.getMessage());
			throw new ERPException("Error while getting items desc:");
		} catch (Exception e) {
			logger.error("Exception occured getting items desc:"+ e.getMessage());
			throw new ERPException("Error while getting items desc:");
		} finally {
			session.close();
		}
		logger.debug("=====END StockDAOImpl getAllitemDesc======");
			return list;
		}
	

	@Override
	public List<String> getUsers(String userRole) {
		logger.debug("=====START StockDAOImpl getUsers======");
		Session session = null;
		List<String> listUsers = null;
		try {
			session= sessionFactory.openSession();
			listUsers = new ArrayList<String>();
			Query query = session.createSQLQuery("CALL GETUSERSFORADMIN(:userRole)")
					.setParameter("userRole", userRole);
			listUsers = query.list();
		}catch (ERPException erp) {
			logger.error("Exception occured getting users:"+ erp.getMessage());
			throw new ERPException("Error while getting users:");
		} catch (Exception e) {
			logger.error("Exception occured getting users:"+ e.getMessage());
			throw new ERPException("Error while getting users:");
		} finally {
			session.close();
		}
		logger.debug("=====END StockDAOImpl getUsers======");
		return listUsers;
	}

	@Override
	public List<StockUsers> getSKUDetails(Integer username,
			String userRole,Integer intMonth,Integer intYear) {
		logger.debug("=====START StockDAOImpl getSKUDetails======");
		Session session = null;
		Connection conn = null;
		ResultSet rs = null;
		CallableStatement stmt = null;
		List<StockUsers> items=null;
			String query = "{ CALL  GETSKULIST(?,?,?,?) }";
			try {
				session= sessionFactory.openSession();
				items=new ArrayList<StockUsers>();
				conn = ConnectionManager.getConnection();
				stmt = conn.prepareCall(query);
				stmt.setInt(1, username);
				stmt.setString(2, userRole);
				stmt.setInt(3, intMonth);
				stmt.setInt(4, intYear);
				rs = stmt.executeQuery();
				ResultSetMetaData metadata = rs.getMetaData();
				List<String> listUsers=new ArrayList<String>();
				Map<String,List<StockUsers>> mapSkuUsers=new HashMap<String,List<StockUsers>>();
				while (rs.next()) {
					
					// Retrieve by column name
					StockUsers item = new StockUsers();
					item.setUserName(rs.getString("user"));
					item.setItem_description(rs.getString("item_desc"));
					item.setNoofUnits(rs.getInt("noofUnits"));
					items.add(item);
		}
			} 
			
			catch (SQLException he) {
				try {
					throw new SQLException(
							"Exception occured while in getting SKU list:"
									+ he.getMessage());
				} catch (SQLException e) {
					logger.error("Exception occured getting SKU list:"+ e.getMessage());
					throw new ERPException("Error while getting SKU list:");
				}
			} catch (ERPException erp) {
				logger.error("Exception occured getting SKU list:"+ erp.getMessage());
				throw new ERPException("Error while getting SKU list:");
			} catch (Exception e) {
				logger.error("Exception occured getting SKU list:"+ e.getMessage());
				throw new ERPException("Error while getting SKU list:");
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
					session.close();
				}
			}
		logger.debug("=====END DeliveryAndInventoryDAOImpl getSKUDetails======");
		return items;
}

	@SuppressWarnings("unused")
	@Override
	public String approveInvoices(String strinvoiceId, String status, String strComments) {
		logger.debug("=====START StockDAOImpl approveInvoices======");
		Stock stock = new Stock();
		Session session = null;
		try {
		session= sessionFactory.openSession();
		Query query=session.createQuery("UPDATE Stock as s SET s.status='13', s.comments=:strComments where s.invoice_id=:strinvoiceId");
		query.setParameter("strComments", strComments);
		query.setParameter("strinvoiceId", strinvoiceId);
		int result=query.executeUpdate();
		}catch (ERPException erp) {
			logger.error("Exception occured approving stock:"+ erp.getMessage());
			throw new ERPException("Error while approving stock:");
		} catch (Exception e) {
			logger.error("Exception occured approving stock:"+ e.getMessage());
			throw new ERPException("Error while approving stock:");
		} finally {
			session.close();
		}
		logger.debug("=====END StockDAOImpl approveInvoices======");
		return "success";
		}
			
	@SuppressWarnings("unused")
	@Override
	public String rejectInvoices(String strinvoiceId, String status, String strComments) {
		logger.debug("=====START StockDAOImpl rejectInvoices======");
		Stock stock = new Stock();
		Session session = null;
		try {
		session= sessionFactory.openSession();
		Query query=session.createQuery("UPDATE Stock as s SET s.status='14', s.comments=:strComments where s.invoice_id=:strinvoiceId");
		query.setParameter("strComments", strComments);
		query.setParameter("strinvoiceId", strinvoiceId);
		int result=query.executeUpdate();
		
		}catch (ERPException erp) {
			logger.error("Exception occured rejecting invoice:"+ erp.getMessage());
			throw new ERPException("Error while rejecting invoice:");
		} catch (Exception e) {
			logger.error("Exception occured rejecting invoice:"+ e.getMessage());
			throw new ERPException("Error while rejecting invoice:");
		} finally {
			session.close();
		}
		logger.debug("=====END StockDAOImpl rejectInvoices======");
		return "Success";
		}
@Override
	public String getOutletAndGSTNos(String billNumber) {
		logger.debug("=====START StockDAOImpl getOutletAndGSTNos======");
		Connection con=null;
		CallableStatement cStmt=null;
		String result=null;
		try {
			con=ConnectionManager.getConnection();
			 cStmt =con.prepareCall("{call GET_HUL_OUTLET_AND_GSTN(?,?)}");
			 cStmt.setString(1, billNumber);
			 cStmt.registerOutParameter(2, java.sql.Types.VARCHAR);

			// execute getDBUSERByUserId store procedure
			cStmt.executeUpdate();
			 result = cStmt.getString(2);
			 logger.debug("=====END StockDAOImpl getOutletAndGSTNos======");
		} catch (HibernateException he) {
			logger.error("Exception occured while creating getOutletAndGSTNos :"+ he.getMessage());
			throw new ERPException("Exception occured while creating getOutletAndGSTNos :"+ he.getMessage());
		} catch (ERPException erp) {
			logger.error("Exception occured while creating getOutletAndGSTNos :"+ erp.getMessage());
			throw new ERPException("Exception occured while creating getOutletAndGSTNos :"+ erp.getMessage());
		} catch (Exception e) {
			logger.error("Exception occured while creating getOutletAndGSTNos :"+ e.getMessage());
			throw new ERPException("Exception occured while creating getOutletAndGSTNos :"+ e.getMessage());
		} finally {
			try {
				cStmt.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		return result;
	}
			
	}



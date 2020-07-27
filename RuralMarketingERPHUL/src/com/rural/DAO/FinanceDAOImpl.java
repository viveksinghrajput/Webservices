	/*
	*FinanceDAOImpl.java
	*Created By		:Bhagya Lakshmi Mula
	*Created Date	:Jul 4, 2018
	*/
	package com.rural.DAO;
	
	import java.sql.CallableStatement;
	import java.sql.Connection;
	import java.sql.PreparedStatement;
	import java.sql.ResultSet;
	import java.sql.SQLException;
	import java.util.ArrayList;
	import java.util.HashMap;
	import java.util.Iterator;
	import java.util.List;
	import java.util.Map;
	import javax.annotation.Resource;
	import org.apache.log4j.Logger;
	import org.hibernate.HibernateException;
	import org.hibernate.Query;
	import org.hibernate.Session;
	import org.hibernate.SessionFactory;
	import org.springframework.stereotype.Component;
	import org.springframework.stereotype.Repository;
	import com.rural.Model.AgencyMaster;
	import com.rural.Model.DOMaster;
	import com.rural.Model.DOMasterTemp;
	import com.rural.Model.FinanceDOBalance;
	import com.rural.Model.FinanceDOMapping;
	import com.rural.Model.FinanceHistory;
	import com.rural.Model.FinanceMaster;
	import com.rural.Model.StatusMaster;
	import com.rural.exceptions.ERPException;
	import com.rural.utility.ConnectionManager;
	
	@Component
	@Repository
	public class FinanceDAOImpl implements FinanceDAO {
		
		@Resource(name = "sessionFactory")
		protected SessionFactory sessionFactory;
		
		static Logger logger =Logger.getLogger(FinanceDAOImpl.class);
	
		@Override
		public List<AgencyMaster> getAgencyList() {
			Session session = null;// ssionFactory.openSession();
			logger.debug("======START FinanceDAOImpl getAgencyList======");
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
				logger.debug("======END FinanceDAOImpl getAgencyList======");
			} catch (HibernateException he) {
				logger.error("Exception occured while loading agencies :"+ he.getMessage());
				throw new ERPException("Exception occured while saving request :"+ he.getMessage());
			} catch (ERPException erp) {
				logger.error("Exception occured while loading agencies :"+ erp.getMessage());
				throw new ERPException("Exception occured while saving request :"+ erp.getMessage());
			} catch (Exception e) {
				logger.error("Exception occured while loading agencies :"+ e.getMessage());
				throw new ERPException("Exception occured while saving request :"+ e.getMessage());
			} finally {
				session.close();
			}
			return agencyList;
		}
	
		@Override
		public int createFinRequest(FinanceMaster financeMaster,String userRole,String path,String fileName) {
			logger.debug("======START FinanceDAOImpl createFinRequest======");
			Connection con=null;
			CallableStatement cStmt=null;
			int historyId=0;
			try {
				con=ConnectionManager.getConnection();
				con.setAutoCommit(false);  
				 cStmt =con.prepareCall("{call saveFinrequest(?,?,?,?,?,?,?,?,?)}");
				 cStmt.setString(1, financeMaster.getCompletionRptDesc());
				 cStmt.setInt(2, financeMaster.getAgencyId());
				 cStmt.setString(3, financeMaster.getMonth());
				 cStmt.setString(4, financeMaster.getYear());
				 cStmt.setString(5, financeMaster.getHistory().get(0).getRemarks());
				 cStmt.setString(6, userRole);
				 cStmt.setString(7, path);
				 cStmt.setString(8, fileName);
				 cStmt.registerOutParameter(9, java.sql.Types.INTEGER);
	
				// execute getDBUSERByUserId store procedure
				 cStmt.executeUpdate();
				  historyId = cStmt.getInt(9);
				  con.commit();  
				  logger.debug("======END FinanceDAOImpl createFinRequest======");
		}catch (SQLException sql) {
			logger.error("Exception occured while creating the request agencies :"+ sql.getMessage());
			throw new ERPException("Exception occured while creating the request agencies :");
		}  catch (ERPException erp) {
			logger.error("Exception occured while creating the request agencies :"+ erp.getMessage());
			throw new ERPException("Exception occured while creating the request agencies :");
		} catch (Exception e) {
			logger.error("Exception occured while creating the request agencies :"+ e.getMessage());
			throw new ERPException("Exception occured while creating the request agencies :");
		} finally {
			try {
				cStmt.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
	return historyId;
		}
	
		@Override
		public List<FinanceMaster> getMyAssignedRequests(String username,String userRole) {
			Session session = null;
			 logger.debug("======START FinanceDAOImpl getMyAssignedRequests======");
			List<FinanceMaster> financeList = null;
			try {
				session = sessionFactory.openSession();
				financeList = new ArrayList<FinanceMaster>();
				Query query = session
						.createSQLQuery("CALL Get_MyAssignedRequests(:username,:userrole)")
						.addEntity(FinanceMaster.class)
						.setParameter("username", username)
						.setParameter("userrole", userRole);
				List<FinanceMaster> list = query.list();
				Iterator<FinanceMaster> itr = list.iterator();
				while (itr.hasNext()) {
					FinanceMaster financeMaster = new FinanceMaster();
					  	financeMaster = itr.next();
					financeList.add(financeMaster);
				}
				 logger.debug("======END FinanceDAOImpl getMyAssignedRequests======");
			} catch (HibernateException he) {
				logger.error("Exception occured while loading getMyAssignedRequests :"+ he.getMessage());
				throw new ERPException("Exception occured while loading getMyAssignedRequests :"+ he.getMessage());
			} catch (ERPException erp) {
				logger.error("Exception occured while loading getMyAssignedRequests :"+ erp.getMessage());
				throw new ERPException("Exception occured while loading getMyAssignedRequests :"+ erp.getMessage());
			} catch (Exception e) {
				logger.error("Exception occured while loading getMyAssignedRequests :"+ e.getMessage());
				throw new ERPException("Exception occured while loading getMyAssignedRequests :"+ e.getMessage());
			} finally {
				session.close();
			}
			return financeList;
		}
	
		@Override
		public List<FinanceMaster> getAllRequests(String username, String userRole) {
			 logger.debug("======START FinanceDAOImpl getAllRequests======");
			Session session = null;
			List<FinanceMaster> financeList = null;
			try {
				session = sessionFactory.openSession();
				financeList = new ArrayList<FinanceMaster>();
				Query query = session
						.createSQLQuery("CALL Get_AllFinanceRequests(:username,:userrole)")
						.addEntity(FinanceMaster.class)
						.setParameter("username", username)
						.setParameter("userrole", userRole);
				List<FinanceMaster> list = query.list();
				Iterator<FinanceMaster> itr = list.iterator();
				while (itr.hasNext()) {
					FinanceMaster financeMaster = new FinanceMaster();
					financeMaster = itr.next();
					financeList.add(financeMaster);
				}
				 logger.debug("======END FinanceDAOImpl getAllRequests======");
			} catch (HibernateException he) {
				logger.error("Exception occured while loading getAllRequests :"+ he.getMessage());
				throw new ERPException("Exception occured while loading getAllRequests :"+ he.getMessage());
			} catch (ERPException erp) {
				logger.error("Exception occured while loading getAllRequests :"+ erp.getMessage());
				throw new ERPException("Exception occured while loading getAllRequests :"+ erp.getMessage());
			} catch (Exception e) {
				logger.error("Exception occured while loading getAllRequests :"+ e.getMessage());
				throw new ERPException("Exception occured while loading getAllRequests :"+ e.getMessage());
			} finally {
				session.close();
			}
			return financeList;
		}
	
		@Override
		public FinanceMaster getRequestDetailsFinance(
				FinanceMaster finance) {
			logger.debug("======START FinanceDAOImpl getRequestDetailsFinance======");
			FinanceMaster financeMaster=null;
			List<FinanceHistory> history = null;
			Session session=null;
			
			try {
				 session= sessionFactory.openSession();
				 financeMaster=new FinanceMaster();
				 history=new ArrayList<FinanceHistory>();
				 financeMaster = (FinanceMaster) session.load(FinanceMaster.class, new Integer(finance.getFinId()));
				if(financeMaster != null && financeMaster.getHistory().size() >0){
					history = financeMaster.getHistory();
				}
				logger.debug("======END FinanceDAOImpl getRequestDetailsFinance======");
			} catch (HibernateException he) {
				logger.error("Exception occured while loading the Request Header :"+ he.getMessage());
				throw new ERPException("Exception occured while loading the Request Header:"+ he.getMessage());
			} catch (ERPException erp) {
				logger.error("Exception occured while loading the Request Header :"+ erp.getMessage());
				throw new ERPException("Exception occured while loading the Request Header:"+ erp.getMessage());
			} catch (Exception e) {
				logger.error("Exception occured while loading the Request Header :"+ e.getMessage());
				throw new ERPException("Exception occured while loading the Request Header:"+ e.getMessage());
			} finally {
				session.close();
			}
			return financeMaster;
		}
	

		@Override
		public int updateFinRequest(int intFinId,int statusId, String strRemarks,
				String userRole, String strPath, String strFileName) {
			logger.debug("======START FinanceDAOImpl updateFinRequest======");
			Connection con=null;
			CallableStatement cStmt=null;
			int historyId=0;
			try {
				if(strFileName.equals("")){
					strPath="";
				}
				con=ConnectionManager.getConnection();
				con.setAutoCommit(false);  
				 cStmt =con.prepareCall("{call updateFinrequest(?,?,?,?,?,?,?)}");
				 cStmt.setInt(1, intFinId);
				 cStmt.setInt(2, statusId);
				 cStmt.setString(3, strRemarks);
				 cStmt.setString(4, userRole);
				 cStmt.setString(5, strPath);
				 cStmt.setString(6, strFileName);
				 cStmt.registerOutParameter(7, java.sql.Types.INTEGER);
				// execute getDBUSERByUserId store procedure
				 cStmt.executeUpdate();
				  historyId = cStmt.getInt(7);
				  con.commit();
				  logger.debug("======END FinanceDAOImpl updateFinRequest======");				
		}catch (HibernateException he) {
			logger.error("Exception occured while updating the Request :"+ he.getMessage());
			throw new ERPException("Exception occured while updating the Request:"+ he.getMessage());
		} 
			catch (ERPException erp) {
				logger.error("Exception occured while updating the Request :"+ erp.getMessage());
				throw new ERPException("Exception occured while updating the Request:"+ erp.getMessage());
		} catch (Exception e) {
			logger.error("Exception occured while updating the Request :"+ e.getMessage());
			throw new ERPException("Exception occured while updating the Request:"+ e.getMessage());
		} finally {
			try {
				cStmt.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
	return historyId;
		}
	
	
		public Map<String, String> getStatusMap(int intFinId,String userRole, String strMoudule) {
			logger.debug("======START FinanceDAOImpl getStatusMap======");
			Session session = null;
			Map<String, String> statusMap=new HashMap<String, String>();
	
			try {
				session = sessionFactory.openSession();
				Query query = session
						.createSQLQuery(
								"CALL GET_STATUSLIST(:finId,:role,:module)")
						.addEntity(StatusMaster.class)
						.setParameter("finId", intFinId)
						.setParameter("role", userRole)
						.setParameter("module", strMoudule);
				List<StatusMaster> list = query.list();
				Iterator<StatusMaster> itr = list.iterator();
				while (itr.hasNext()) {
	
					StatusMaster status = new StatusMaster();
					status = itr.next();
					statusMap.put(Integer.toString(status.getStatusId()), status.getStatusDesc());
				}
				logger.debug("======END FinanceDAOImpl getStatusMap======");
			} catch (HibernateException he) {
				logger.error("Exception occured while loading Status Map :"+ he.getMessage());
				throw new ERPException("Exception occured while loading Status Map :"+ he.getMessage());
			} catch (ERPException erp) {
				logger.error("Exception occured while loading Status Map :"+ erp.getMessage());
				throw new ERPException("Exception occured while loading Status Map :"+ erp.getMessage());
			} catch (Exception e) {
				logger.error("Exception occured while loading Status Map :"+ e.getMessage());
				throw new ERPException("Exception occured while loading Status Map :"+ e.getMessage());
			} finally {
				session.close();
			}
			return statusMap;
		}
	
		@Override
		public List<DOMaster> getDOList(String strAgencyName) {
			logger.debug("======START FinanceDAOImpl getDOList======");
			List<DOMaster> doList = null;
			Session session = null;
			try {
				session = sessionFactory.openSession();
				doList = new ArrayList<DOMaster>();
				Query query = session.createSQLQuery("CALL GET_DOLIST(:agency)");
				query.setParameter("agency", strAgencyName);
				doList = query.list();
				logger.debug("======END FinanceDAOImpl getDOList======");
			} catch (HibernateException he) {
				logger.error("Exception occured while loading DO List :"+ he.getMessage());
				throw new ERPException("Exception occured while loading DO List :"+ he.getMessage());
			} catch (ERPException erp) {
				logger.error("Exception occured while loading DO List :"+ erp.getMessage());
				throw new ERPException("Exception occured while loading DO List :"+ erp.getMessage());
			} catch (Exception e) {
				logger.error("Exception occured while loading DO List :"+ e.getMessage());
				throw new ERPException("Exception occured while loading DO List :"+ e.getMessage());
			} finally {
				session.close();
			}
			return doList;
		}
	
		@Override
		public int updateFinRequest(int finId, int statusId, String strRemarks,
				String userRole, String strPath, String strFileName,String totalAmount,
				List<FinanceDOMapping> finDOMapping) {
			logger.debug("======START FinanceDAOImpl updateFinRequest======");
			Connection con=null;
			CallableStatement cStmt=null;
			final int batchSize = 40;
			int count = 0;
			PreparedStatement ps=null;
			int historyId=0;
			try {
				con=ConnectionManager.getConnection();
				con.setAutoCommit(false);
				 cStmt =con.prepareCall("{call updateFinrequestForDO(?,?,?,?,?,?,?,?)}");
				 cStmt.setInt(1, finId);
				 cStmt.setInt(2, statusId);
				 cStmt.setString(3, strRemarks);
				 cStmt.setString(4, userRole);
				 cStmt.setString(5, strPath);
				 cStmt.setString(6, strFileName);
				 cStmt.setString(7, totalAmount.replace(",", ""));
				 cStmt.registerOutParameter(8, java.sql.Types.INTEGER);
	
				// execute getDBUSERByUserId store procedure
				 cStmt.executeUpdate();
				  historyId = cStmt.getInt(8);
				  try {
				 String sql = "insert into finance_do_mapping (fin_id,do_number,amount,valid_from,valid_to) values (?,?,?,?,?)";
					 ps = con.prepareStatement(sql);
					 for (FinanceDOMapping doTemp: finDOMapping) {
						 ps.setInt(1, doTemp.getFinId());
						 ps.setString(2, doTemp.getDoNumber());
						 ps.setString(3, doTemp.getAmount());
						 ps.setString(4, doTemp.getValidFrom());
						 ps.setString(5, doTemp.getValidTo());
						 ps.addBatch();
					if(++count % batchSize == 0) {
						ps.executeBatch();
					}
					ps.executeBatch(); // insert remaining records
					 }
					 logger.debug("======END FinanceDAOImpl updateFinRequest======");
				  }catch (SQLException sqe) {
					  logger.error("Error while inserting into Finance DO Mapping :"+sqe.getMessage());
						throw new ERPException("Error while inserting into Finance+h DO Mapping");
					} 
						catch (ERPException erp) {
							 logger.error("Error while inserting into Finance DO Mapping :"+erp.getMessage());
								throw new ERPException("Error while inserting into Finance+h DO Mapping");
					} catch (Exception e) {
						 logger.error("Error while inserting into Finance DO Mapping :"+e.getMessage());
							throw new ERPException("Error while inserting into Finance+h DO Mapping");
					} 
				  con.commit();  
			}catch (SQLException sqe) {
				logger.error("Error while updating the Finance request :"+sqe.getMessage());
				throw new ERPException("Error while updating the Finance request");
			} 
				catch (ERPException erp) {
					logger.error("Error while updating the Finance request :"+erp.getMessage());
					throw new ERPException("Error while updating the Finance request");
			} catch (Exception e) {
				logger.error("Error while updating the Finance request :"+e.getMessage());
				throw new ERPException("Error while updating the Finance request");
			} 
			finally {
				try {
					ps.close();
					cStmt.close();
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
					 
		}
	return historyId;
		}
	
		@Override
		public List<DOMasterTemp> insertIntoDoTemp(List<DOMasterTemp> doList) {
			logger.debug("======START FinanceDAOImpl insertIntoDoTemp======");
			final int batchSize = 40;
			int count = 0;
			Connection con=null;
			PreparedStatement ps1=null;
			PreparedStatement ps2=null;
			List<DOMasterTemp> doTempList=new ArrayList<DOMasterTemp>();
			try{
				
			      String sql = "DELETE FROM do_master_temp";
			      con =ConnectionManager.getConnection();
			      ps1 = con.prepareStatement(sql);
					 int deleteCnt=ps1.executeUpdate();
					 logger.debug(deleteCnt+"Records are deleted");
				
				 sql = "insert into do_master_temp (do_number, AgencyName, do_value,valid_from,valid_to) values (?,?,?,?,?)";
				 ps2 = con.prepareStatement(sql);
				 for (DOMasterTemp doTemp: doList) {
					 ps2.setString(1, doTemp.getDoNumber());
					 ps2.setString(2, doTemp.getAgencyName());
					 ps2.setString(3, doTemp.getDoValue());
					 ps2.setString(4, doTemp.getValidFrom());
					 ps2.setString(5, doTemp.getValidTo());
					 ps2.addBatch();
				if(++count % batchSize == 0) {
					ps2.executeBatch();
				}
				ps2.executeBatch(); // insert remaining records
				 }
				 
				 doTempList=getErrorList();
				 logger.debug("======END FinanceDAOImpl insertIntoDoTemp======");				 
		}catch (SQLException sqe) {
			logger.error("Exception occured while inserting into do_master_temp table :"+sqe.getMessage());
			throw new ERPException("Exception occured while inserting into do_master_temp table :");
		} 
			catch (ERPException erp) {
				logger.error("Exception occured while inserting into do_master_temp table :"+erp.getMessage());
				throw new ERPException("Exception occured while inserting into do_master_temp table :");
		} catch (Exception e) {
			logger.error("Exception occured while inserting into do_master_temp table :"+e.getMessage());
			throw new ERPException("Exception occured while inserting into do_master_temp table :");
		} finally {
			try {
				ps1.close();
				ps2.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return doTempList;
	}
	
		private List<DOMasterTemp> getErrorList() {
			Session session = null;
			logger.debug("======START FinanceDAOImpl getErrorList======");
			List<DOMasterTemp> doTempList = null;
			try {
				session = sessionFactory.openSession();
				doTempList = new ArrayList<DOMasterTemp>();
				Query query = session
						.createSQLQuery("CALL VALIDATE_DO_DETAILS_TEMP()")
						.addEntity(DOMasterTemp.class);
				List<DOMasterTemp> list = query.list();
				Iterator<DOMasterTemp> itr = list.iterator();
				while (itr.hasNext()) {
					DOMasterTemp doTemp = new DOMasterTemp();
					doTemp = itr.next();
					doTempList.add(doTemp);
				}
				logger.debug("======END FinanceDAOImpl getErrorList======");
			} catch (HibernateException he) {
				logger.error("Exception occured while validating  DOLIST  :"	+ he.getMessage());
				throw new ERPException("Exception occured while validating  DOLIST  :"+ he.getMessage());
			} catch (ERPException erp) {
				logger.error("Exception occured while validating  DOLIST  :"	+ erp.getMessage());
				throw new ERPException("Exception occured while validating  DOLIST  :"+ erp.getMessage());
			} catch (Exception e) {
				logger.error("Exception occured while validating  DOLIST  :"	+ e.getMessage());
				throw new ERPException("Exception occured while validating  DOLIST  :"+ e.getMessage());
			} finally {
				session.close();
			}
			return doTempList;
		}
	
		@Override
		public List<FinanceDOMapping> getDOMapping(int finId) {
			logger.debug("======START FinanceDAOImpl getDOMapping======");
			Session session = null;
			List<FinanceDOMapping> outputlist=new ArrayList<FinanceDOMapping>();
			try {
			session = sessionFactory.openSession();	
			outputlist=session.createQuery("from FinanceDOMapping as f where f.finId=:id")
					.setParameter("id", finId).list();
			logger.debug("======END FinanceDAOImpl getDOMapping======");
			} catch (HibernateException he) {
				logger.error("Exception occured while loading DO Numbers:"+ he.getMessage());
				throw new ERPException("Exception occured while loading DO Numbers:"+ he.getMessage());
			} catch (ERPException erp) {
				logger.error("Exception occured while loading DO Numbers:"+ erp.getMessage());
				throw new ERPException("Exception occured while loading DO Numbers:"+ erp.getMessage());
			} catch (Exception e) {
				logger.error("Exception occured while loading DO Numbers:"+ e.getMessage());
				throw new ERPException("Exception occured while loading DO Numbers:"+ e.getMessage());
			} finally {
				session.close();
			}
			return outputlist;
		}
	
		@Override
		public List<DOMaster> getDOList(Integer userId,
				String userRole) {
			logger.debug("======START FinanceDAOImpl getDOList======");
			Session session = null;
			List<DOMaster> doList = null;
			try {
				session = sessionFactory.openSession();
				doList = new ArrayList<DOMaster>();
				Query query = session
						.createSQLQuery("CALL GET_COMPREPORTLIST(:userId,:role)")
						.addEntity(DOMaster.class)
						.setParameter("userId", userId)
						.setParameter("role", userRole);
				List<DOMaster> list = query.list();
				Iterator<DOMaster> itr = list.iterator();
				while (itr.hasNext()) {
					DOMaster doMaster = new DOMaster();
					doMaster = itr.next();
					  	
					  	doList.add(doMaster);
				}
				logger.debug("======END FinanceDAOImpl getDOList======");
			} catch (HibernateException he) {
				logger.error("Exception occured while loading DOMaster :"+ he.getMessage());
				throw new ERPException("Exception occured while loading DOMaster :"+ he.getMessage());
			} catch (ERPException erp) {
				logger.error("Exception occured while loading DOMaster :"+ erp.getMessage());
				throw new ERPException("Exception occured while loading DOMaster :"+ erp.getMessage());
			} catch (Exception e) {
				logger.error("Exception occured while loading DOMaster :"+ e.getMessage());
				throw new ERPException("Exception occured while loading DOMaster :"+ e.getMessage());
			} finally {
				session.close();
			}
			return doList;
		}
	
		@Override
		public List<FinanceDOBalance> getFinanceDetailsByDO(String doNumber) {
			logger.debug("======START FinanceDAOImpl getFinanceDetailsByDO======");
			List<FinanceDOBalance> financeList = new ArrayList<FinanceDOBalance>();
			Connection conn = null;
			ResultSet rs = null;
			CallableStatement stmt = null;
			try {
				String query = "{ CALL GET_FINANCEREQUESTBYDONUMBER(?) }";
				conn = ConnectionManager.getConnection();
				stmt = conn.prepareCall(query);
				stmt.setString(1, doNumber);
				rs = stmt.executeQuery();
				while (rs.next()) {
					FinanceDOBalance balance=new FinanceDOBalance();
					balance.setReqNum(rs.getString("req_num"));
					balance.setAgencyName(rs.getString("agencyName"));
					balance.setTotalAmount(rs.getInt("total_amount"));
					balance.setCreatedDate(rs.getDate("created_date"));
					financeList.add(balance);
				}
				logger.debug("======END FinanceDAOImpl getFinanceDetailsByDO======");
			} catch (SQLException he) {
				logger.error("Exception occured while loading DO wise Finance Request  :"+ he.getMessage());
				throw new ERPException("Exception occured while loading DO wise Finance Request  :"+ he.getMessage());
			} catch (ERPException erp) {
				logger.error("Exception occured while loading DO wise Finance Request  :"+ erp.getMessage());
				throw new ERPException("Exception occured while loading DO wise Finance Request  :"+ erp.getMessage());
			} catch (Exception e) {
				logger.error("Exception occured while loading DO wise Finance Request  :"+ e.getMessage());
				throw new ERPException("Exception occured while loading DO wise Finance   :"+ e.getMessage());
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
			return financeList;
		
		}
	
		@Override
		public DOMaster getDODetails(String doNumber) {
			DOMaster dOMaster=null;
			logger.info("======START FinanceDAOImpl getDODetails======");
			Session session = null;
			try {
				session = sessionFactory.openSession();
				dOMaster = new DOMaster();
				Query query = session
						.createSQLQuery(
								"CALL GET_DODETAILSBYDONUMBER(:doNumber)")
						.addEntity(DOMaster.class)
						.setParameter("doNumber", doNumber);
				List<DOMaster> list = query.list();
				Iterator<DOMaster> itr = list.iterator();
				while (itr.hasNext()) {
					dOMaster = itr.next();
				}
				logger.info("======END FinanceDAOImpl getDODetails======");
			} catch (HibernateException he) {
				logger.error("Exception occured while loading DO Details :"+ he.getMessage());
				throw new ERPException("Exception occured while loading DO Details :"+ he.getMessage());
			} catch (ERPException erp) {
				logger.error("Exception occured while loading DO Details :"+ erp.getMessage());
				throw new ERPException("Exception occured while loading DO Details :"+ erp.getMessage());
			} catch (Exception e) {
				logger.error("Exception occured while loading DO Details :"+ e.getMessage());
				throw new ERPException("Exception occured while loading DO Details :"+ e.getMessage());
			} finally {
				session.close();
			}
			return dOMaster;
		}
	}

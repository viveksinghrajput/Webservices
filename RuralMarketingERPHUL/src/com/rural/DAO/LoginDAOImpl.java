package com.rural.DAO;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import javax.annotation.Resource;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import  org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.rural.Model.AgencyMaster;
import com.rural.Model.MenuMaster;
import com.rural.Model.MenuTemp;
import com.rural.Model.Roles;
import com.rural.Model.SalesMaster;
import com.rural.Model.SecurityAnswers;
import com.rural.Model.SecurityQuestions;
import com.rural.Model.StateMaster;
import com.rural.Model.StatusMaster;
import com.rural.Model.UserMaster;
import com.rural.Model.VendorMaster;
import com.rural.exceptions.ERPException;
import com.rural.utility.ConnectionManager;

@Component
@Repository
public class LoginDAOImpl implements LoginDAO{

	@Resource(name="sessionFactory")
    protected SessionFactory sessionFactory;
	static Logger logger =Logger.getLogger(LoginDAOImpl.class);
	@Override
	public boolean validateLogin(String userName, String userPassword) {
		UserMaster user=null;
		boolean userFound = false;
		logger.debug("=====START LoginDAOImpl validateLogin======");
		Session session = null;
		try {
			session = sessionFactory.openSession();
			user = new UserMaster();
			user= (UserMaster)session
					.createSQLQuery(
							"CALL VALIDATEUSER(:user,:password)")
					.addEntity(UserMaster.class)
					.setParameter("user", userName)
					.setParameter("password", userPassword).uniqueResult();
			if ((user != null) && (user.getId() != 0)) {
				userFound=true;
			}
			logger.debug("=====END LoginDAOImpl validateLogin======");
		} catch (HibernateException he) {
			logger.error("Exception occured while validating the user:"+ he.getMessage());
			throw new ERPException("Exception occured while validating the user:"+ he.getMessage());
		} catch (ERPException erp) {
			logger.error("Exception occured while validating the user:"+ erp.getMessage());
			throw new ERPException("Error while validating the user");
		} catch (Exception e) {
			logger.error("Exception occured while validating the user:"+ e.getMessage());
			throw new ERPException("Error while validating the user");
		} finally {
			session.close();
		}
	
		return userFound;
	
	}
	@Override
	public List<Roles> getRoles() {
		List<Roles> rolesList=new ArrayList<Roles>();
		logger.debug("=====START LoginDAOImpl getRoles======");
		Session session = null;
		try {
			session = sessionFactory.openSession();
	Query query = session.createQuery("From Roles");
	rolesList= (List<Roles>) query.list();
	logger.debug("=====END LoginDAOImpl getRoles======");
		} catch (HibernateException he) {
			logger.error("Exception occured while loading the roles:"+ he.getMessage());
			throw new ERPException("Exception occured while loading the roles:"+ he.getMessage());
		} catch (ERPException erp) {
			logger.error("Exception occured while loading the roles:"	+ erp.getMessage());
			throw new ERPException("Error while loading the roles");
		} catch (Exception e) {
			logger.error("Exception occured while loading the roles:"	+ e.getMessage());
			throw new ERPException("Error while loading the roles");
		} finally {
			session.close();
		}
	return rolesList;
	 }
	@Override
	public List<StateMaster> getStateMap() {
		logger.debug("======START LoginDAOImpl getStateMap======");
		List<StateMaster> stateMasterList=new ArrayList<StateMaster>();
		try{
		Query query = sessionFactory.openSession().createQuery("From StateMaster");
		stateMasterList= (List<StateMaster>) query.list();
		logger.debug("=====END LoginDAOImpl getStateMap======");
		} catch (HibernateException he) {
			logger.error("Exception occured while loading the states:"+ he.getMessage());
			throw new ERPException("Exception occured while loading the states:"+ he.getMessage());
		} catch (ERPException erp) {
			logger.error("Exception occured while loading the states:"+ erp.getMessage());
			throw new ERPException("Error while loading the states");
		} catch (Exception e) {
			logger.error("Exception occured while loading the states:"+ e.getMessage());
			throw new ERPException("Error while loading the states");
		} 
		return stateMasterList;
	}
	@Override
	public List<AgencyMaster> getAgencyMap() {
		logger.debug("======START LoginDAOImpl getAgencyMap======");
		List<AgencyMaster> aagencyMasterList=new ArrayList<AgencyMaster>();
		Session session = null;
		try{
			session = sessionFactory.openSession();
		Query query = session.createQuery("From AgencyMaster");
		aagencyMasterList= (List<AgencyMaster>) query.list();
		logger.debug("=====END LoginDAOImpl getAgencyMap======");
		} catch (HibernateException he) {
			logger.error("Exception occured while loading the agencies:"	+ he.getMessage());
			throw new ERPException("Exception occured while loading the agencies:"+ he.getMessage());
		} catch (ERPException erp) {
			logger.error("Exception occured while loading the agencies:"+ erp.getMessage());
			throw new ERPException("Error while loading the agencies");
		} catch (Exception e) {
			logger.error("Exception occured while loading the agencies:"+ e.getMessage());
			throw new ERPException("Error while loading the agencies");
		} finally {
			session.close();
		}
		return aagencyMasterList;
	}
	@Override
	public List<VendorMaster> getVendorMap() {
		logger.debug("======START LoginDAOImpl getVendorMap======");
		List<VendorMaster> vendorMasterList=new ArrayList<VendorMaster>();
		Session session = sessionFactory.openSession();
		try{
		Query query =session.createQuery("From VendorMaster");
		vendorMasterList=(List<VendorMaster>) query.list();
		logger.debug("======END LoginDAOImpl getVendorMap======");
		}
		  catch (ERPException erp) {
			  logger.error("Exception occured while loading the vendors: "+erp.getMessage());
				throw new ERPException("Error while loading the vendor");
			} catch (Exception e) {
				  logger.error("Exception occured while loading the vendors: "+e.getMessage());
					throw new ERPException("Error while loading the vendor");
			} finally {
				session.close();
			}
		return vendorMasterList;
	}
	@Override
	public List<StatusMaster> getStatusMap() {
		logger.debug("=====START LoginDAOImpl getStatusMap======");
		List<StatusMaster> statusMasterList=new ArrayList<StatusMaster>();
		Session session = null;
		try{
			session	=sessionFactory.openSession();
		Query query = session.createQuery("From StatusMaster");
		statusMasterList= ((List<StatusMaster>) query.list());
		logger.debug("=====END LoginDAOImpl getStatusMap======");
		} catch (HibernateException he) {
			logger.error("Exception occured while loading the status:"+ he.getMessage());
			throw new ERPException("Exception occured while loading the status:"+ he.getMessage());
		} catch (ERPException erp) {
			logger.error("Exception occured while loading the status:"+ erp.getMessage());
			throw new ERPException("Error while loading the status");
		} catch (Exception e) {
			logger.error("Exception occured while loading the status:"+ e.getMessage());
			throw new ERPException("Error while loading the status");
		} finally {
			session.close();
		}
		return statusMasterList;
	}
	@Override
	public List<UserMaster> getNamesList() {
		logger.debug("=====START LoginDAOImpl getNamesList======");
		List<UserMaster> userMasterList = new ArrayList<UserMaster>();
		Session session = null;
		try {
			session = sessionFactory.openSession();
			Query query = session.createQuery("From UserMaster");
			userMasterList = ((List<UserMaster>) query.list());
			logger.debug("=====END LoginDAOImpl getNamesList======");
		} catch (HibernateException he) {
			logger.error("Exception occured while loading the users:"+ he.getMessage());
			throw new ERPException("Exception occured while loading the users:"+ he.getMessage());
		} catch (ERPException erp) {
			logger.error("Exception occured while loading the users:"+ erp.getMessage());
			throw new ERPException("Error while loading the users");
		} catch (Exception e) {
			logger.error("Exception occured while loading the users:"+ e.getMessage());
			throw new ERPException("Error while loading the users");
		} finally {
			session.close();
		}
		return userMasterList;
	}
			@Override
			public List<SecurityQuestions> listQns(Integer userId) {
			logger.debug("=====START LoginDAOImpl listQns======");
			Connection con = null;
			PreparedStatement ps = null;
			List<SecurityQuestions> list = new ArrayList<>();
			try {
			String sql = "select * from question_master order by rand() limit 2";
			con = ConnectionManager.getConnection();
			ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery(sql);
			while(rs.next()){
				SecurityQuestions questions=new SecurityQuestions();
				int qus_id = rs.getInt("qus_id");
				String qus_name = rs.getString("qus_name");
				questions.setQus_id(qus_id);
				questions.setQus_name(qus_name);
				list.add(questions);
			}
			logger.debug("=====END LoginDAOImpl listQns======");
			}
			catch(SQLException sqe) {
				logger.error("Exception while loading questions"+sqe.getMessage());
				throw new ERPException("Exception while loading questions");
			}catch(ERPException erp) {
				logger.error("Exception while loading questions"+erp.getMessage());
				throw new ERPException("Exception while loading questions");
			}catch (Exception e) {
				logger.error("Exception while loading questions"+e.getMessage());
				throw new ERPException("Exception while loading questions");
			} finally {
		
			}
				return list;
		}
			
			
			@SuppressWarnings("null")
			@Override
			public void saveAnswers(List<SecurityAnswers> ansList, Integer userId, String username, String strpassword) {
				logger.debug("=====START LoginDAOImpl saveAnswers======");
			Connection con = null;
			PreparedStatement ps=null;
			try {
			String sql = "insert into answer_master (qus_id, ans_desc, isActive, updatedon, updatedby, user_id) values (?,?,?,?,?,?)";
			con = ConnectionManager.getConnection();
			ps = con.prepareStatement(sql);
		
			for (SecurityAnswers sTemp: ansList) {
			ps.setInt(1, sTemp.getQus_id());
			ps.setString(2, sTemp.getAns_desc());
			ps.setString(3, "1");
			java.sql.Date date = getCurrentDatetime();
			ps.setDate(4, date);
			ps.setString(5, username);
			ps.setInt(6, userId);
			ps.addBatch();
			}
			ps.executeBatch();
			CallableStatement cStmt=null;
			 cStmt =con.prepareCall("{call UPDATE_USER_DETAILS(?,?)}");
			 cStmt.setInt(1, userId);
			 cStmt.setString(2, strpassword);
			 cStmt.executeUpdate();
			 logger.debug("=====END LoginDAOImpl saveAnswers======");
			}
			catch (SQLException sqe) {
				logger.error("Exception occured while loading the users:"+ sqe.getMessage());
				throw new ERPException("Exception occured while loading the users:"+ sqe.getMessage());
				} catch (ERPException erp) {
				logger.error("Exception occured while loading the users:"+ erp.getMessage());
				throw new ERPException("Error while loading the users");
				} catch (Exception e) {
					logger.error("Exception occured while loading the users:"+ e.getMessage());	
					throw new ERPException("Error while loading the users");
				} finally {
				try {
					ps.close();
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				}
				}
				/*To set Current Date*/
				public java.sql.Date getCurrentDatetime() {
				java.util.Date today = new java.util.Date();
				return new java.sql.Date(today.getTime());
				}
				/*End of Current Date*/
				
				@Override
				public int validateQuestions(Integer userId, List<SecurityAnswers> ansList) {
				logger.debug("=====START LoginDAOImpl validateQuestions======");
				Connection con = null;
				PreparedStatement ps = null;
				ResultSet rs = null;
				int count=0;
				try {
		
				con = ConnectionManager.getConnection();
				ps = con.prepareStatement("select * from answer_master where user_id = ? and qus_id = ? and ans_desc = ?");
				for (SecurityAnswers sTemp: ansList) {
				ps.setInt(1, userId);
				ps.setInt(2, sTemp.getQus_id());
				ps.setString(3, sTemp.getAns_desc());
				rs = ps.executeQuery();
				while (rs.next()) {
				count++;
				}
				}
				logger.debug("=====END LoginDAOImpl validateQuestions======");
				}
				catch(SQLException sqe) {
					logger.error("Exception occured while validating questions"+ sqe.getMessage());
					throw new ERPException("Error while validating questions");
					}
				catch(ERPException erp) {
					logger.error("Exception occured while validating questions"+ erp.getMessage());
					throw new ERPException("Error while validating questions");
					}catch (Exception e) {
					logger.error("Exception occured while validating questions"+ e.getMessage());
					throw new ERPException("Error while validating questions");
					} finally {
					try {
					rs.close();
					ps.close();
					con.close();
					} catch (SQLException e) {
					e.printStackTrace();
					}
					}

					return count;
				}
				@Override
				public int countAns(String username) {
				logger.debug("=====START LoginDAOImpl countAns======");
				CallableStatement cstmt;
				int count = 0;
				try {
				cstmt = ConnectionManager.getConnection().prepareCall("{call CounterLogin(?,?)}");
				cstmt.setString(1, username);
				cstmt.registerOutParameter(2, java.sql.Types.INTEGER);
				cstmt.executeUpdate();
				count = cstmt.getInt(2);
				logger.debug("=====END LoginDAOImpl countAns======");
				}catch (SQLException sqe) {
				logger.error("Exception occured while validating Answers"+ sqe.getMessage());
				throw new ERPException("Error while validating  Answers");
				} catch (ERPException erp) {
					logger.error("Exception occured while validating Answers"+ erp.getMessage());
					throw new ERPException("Error while validating  Answers");
				} catch (Exception e) {
					logger.error("Exception occured while validating Answers"+ e.getMessage());
					throw new ERPException("Error while validating  Answers");
				}
				return count;
				}
				
				
	@Override
	public String getPassword(int userId) {
		logger.debug("=====START LoginDAOImpl getPassword======");
		Connection con=null;
		CallableStatement cStmt=null;
		String password=null;
		try {
			con=ConnectionManager.getConnection();
			 cStmt =con.prepareCall("{call GET_PASSWORD(?,?)}");
			 cStmt.setInt(1, userId);
			 cStmt.registerOutParameter(2, java.sql.Types.VARCHAR);
			// execute getDBUSERByUserId store procedure
			 cStmt.executeUpdate();
			 password = cStmt.getString(2);
			 logger.debug("=====END LoginDAOImpl getPassword======");
		} catch (SQLException sqe) {
			logger.error("Exception occured while getting the pasword :"+ sqe.getMessage());
			throw new ERPException("Exception occured while creating getting the pasword :"+ sqe.getMessage());
		} catch (ERPException erp) {
			logger.error("Exception occured while getting the pasword :"+ erp.getMessage());
			throw new ERPException("Error while getting the pasword");
		} catch (Exception e) {
			logger.error("Exception occured while getting the pasword :"+ e.getMessage());
			throw new ERPException("Error while getting the pasword");
		} finally {
			try {
				cStmt.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		return password;
	}
	@Override
	public List<String> getMenuListbyRole(int roleId) {
		logger.debug("=====START LoginDAOImpl getMenuListbyRole======");
		Connection conn = null;
		ResultSet rs = null;
		CallableStatement stmt = null;
		List<String> listTemp=new ArrayList<String>();
		try {
			conn = ConnectionManager.getConnection();
			String query = "{ CALL GET_MENUS(?) }";
			stmt = conn.prepareCall(query);
			stmt.setInt(1, roleId);
			rs = stmt.executeQuery();
			while (rs.next()) {
				String menu=rs.getString("menu");
				listTemp.add("/"+menu);
	         }
			logger.debug("=====END LoginDAOImpl getMenuListbyRole======");
		} catch (HibernateException he) {
			logger.error("Exception occured while loading the Role and its Menus:"+ he.getMessage());
			throw new ERPException("Exception occured while loading the Role and its Menus:"+ he.getMessage());
		} catch (ERPException erp) {
			logger.error("Exception occured while loading the Role and its Menus:"+ erp.getMessage());
			throw new ERPException("Exception occured while loading the Role and its Menus:");
		} catch (Exception e) {
			logger.error("Exception occured while loading the Role and its Menus:"+ e.getMessage());
			throw new ERPException("Exception occured while loading the Role and its Menus:");
		} finally {
			if (rs != null)
				try {
					rs.close();
			if (stmt != null)
				stmt.close();
			if (conn != null)
				conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
	
		return listTemp;
	
	}
				
}

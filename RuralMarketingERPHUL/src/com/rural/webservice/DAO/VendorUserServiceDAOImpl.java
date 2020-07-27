/*
*UserServiceDAOImpl.java
*Created By		:Kamal Thapa
*Created Date	:Apr 4, 2018
*/
package com.rural.webservice.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.Resource;
import javax.transaction.Transactional;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import com.rural.Model.BatchTemp;
import com.rural.Model.CityMaster;
import com.rural.Model.CityTemp;
import com.rural.Model.DiviceMaster;
import com.rural.Model.LoginResponse;
import com.rural.Model.StateMaster;
import com.rural.Model.StateTemp;
import com.rural.Model.UserMaster;
import com.rural.exceptions.ERPException;
import com.rural.utility.ConnectionManager;

@Component
@Repository
@Transactional
@SuppressWarnings("unchecked")
public  class VendorUserServiceDAOImpl implements VendorUserDAO {

	@Resource(name = "sessionFactory")
	protected SessionFactory sessionFactory;

	static Logger logger =Logger.getLogger(VendorUserServiceDAOImpl.class);
	
	
	@Override
	public BatchTemp getStates() {
		logger.info("=====START UserServiceDAOImpl getStates======");
		List<StateMaster> stateList=null;
		BatchTemp batch=new BatchTemp();
		StateTemp state=null;//new StateTemp();
		CityTemp city=null;//new CityTemp();
		int batchId=0;
		Set<StateTemp> stateSet= new HashSet<StateTemp>();
		Set<CityTemp> citySet=null;// new HashSet<CityTemp>();
		Session session=null;
		try{
		 session = sessionFactory.openSession();
			stateList = session.createQuery("from StateMaster s order by s.states").list();
			
		
		for(StateMaster stateMaster:stateList){
			citySet= new HashSet<CityTemp>();
			state=new StateTemp();
			 batchId=stateMaster.getBatchId();
			state.setId(stateMaster.getStateId());
			state.setName(stateMaster.getStates());
			
			for(CityMaster cityMaster : stateMaster.getCities())
            {
				city=new CityTemp();
				city.setId(cityMaster.getCityId());
				city.setName(cityMaster.getCityName());
				citySet.add(city);
				
            }
			state.setCities(citySet);
			stateSet.add(state);
			batch.setBatch(batchId);
			batch.setStatus("success");
			batch.setStates(stateSet);
		}
		} catch (HibernateException he) {
			logger.error("Exception occured while loading the states :"
					+ he.getMessage());
			throw new ERPException("Exception occured while loading the states :"
					+ he.getMessage());
		} catch (ERPException erp) {
			logger.error("Exception occured while loading the states :"
					+ erp.getMessage());
			throw new ERPException("Error while loading the states");
		} catch (Exception e) {
			logger.error("Exception occured while checking the states"
					+ e.getMessage());
		} 	
		finally {
		      try {
		      
		        session.close();
		      } catch (Exception e) {
		        e.printStackTrace();
		      }
		    }
		logger.info("=====END UserServiceDAOImpl getStates======");
		return batch;
	}

	@Override
	public int validateUser(String userName, String passWord) {
		ResultSet rs = null;
	    Connection conn = null;
	    PreparedStatement pstmt = null;
	    int count=0;
	    logger.info("=====START UserServiceDAOImpl validateUser======");
		try {
	      conn = ConnectionManager.getConnection();
	      String query = "select *  from user_master where username= ? and password=aes_encrypt(?,'test123') and roleId=4 and isActive=1";

	      pstmt = conn.prepareStatement(query); // create a statement
	      pstmt.setString(1, userName); // set input parameter
	      pstmt.setString(2, passWord); // set input parameter
	      rs = pstmt.executeQuery();
	      // extract data from the ResultSet
	      while (rs.next()) {
	       count++;
	      }
		} catch (SQLException se) {
			logger.error("Exception occured while validating the user :"+ se.getMessage());
			throw new ERPException("Exception occured while validating the user :"
					+ se.getMessage());
		} catch (ERPException erp) {
			logger.error("Exception occured while validating the user :"
					+ erp.getMessage());
			throw new ERPException("Error while validating the user");
		} catch (Exception e) {
			logger.error("Exception occured while validating the user"
					+ e.getMessage());
		} 	
		
		 finally {
	      try {
	        rs.close();
	        pstmt.close();
	        conn.close();
	      } catch (SQLException e) {
	        e.printStackTrace();
	      }
	    }
		logger.info("=====END UserServiceDAOImpl validateUser======");
return count;
	}

	@Override
	public LoginResponse getDeviceInfo(DiviceMaster diviceMaster) {
		logger.info("=====END UserServiceDAOImpl getDeviceInfo======");
		ResultSet rs = null;
	    Connection conn = null;
	    PreparedStatement pstmt = null;
	    PreparedStatement pstmt1= null;
	    LoginResponse loginResponse=null;
	    try {
	    	try{

	    		Calendar calendar = Calendar.getInstance();
		      conn = ConnectionManager.getConnection();
		      String query = "update device_master set imei=?,deviceName=?,deviceModel=?,androidVersion=?,"
		      		+ "appVersionCode=?, "
		      		+ "appVersionName=?,deviceTime=now() "
		      		+ "where username=?";

		      pstmt = conn.prepareStatement(query); // create a statement
		      pstmt.setString(1, diviceMaster.getImei()); // set input parameter
		      pstmt.setString(2, diviceMaster.getdName()); // set input parameter
		      pstmt.setString(3, diviceMaster.getdModel()); // set input parameter
		      pstmt.setInt(4, diviceMaster.getAndroidVer()); // set input parameter
		      pstmt.setString(5, diviceMaster.getAppVerCode()); // set input parameter
		      pstmt.setString(6, diviceMaster.getAppVerName()); // set input parameter
		      pstmt.setString(7, diviceMaster.getUser()); // set input parameter
		      int i = pstmt.executeUpdate();
		     
		    } 
	    	catch (SQLException sqe) {
		    	logger.error("Exception occured while updating the user :"+ sqe.getMessage());
				throw new ERPException("Exception occured while updating the user :"+ sqe.getMessage());
		    }catch (Exception e) {
		    	logger.error("Exception occured while updating the user :"+ e.getMessage());
				throw new ERPException("Exception occured while updating the user :"+ e.getMessage());
		    } finally {
		      try {
		        pstmt.close();
		        conn.close();
		      } catch (SQLException e) {
		        e.printStackTrace();
		      }
		    }
		    
		      conn = ConnectionManager.getConnection();
	      String query = "select id,roleId,0,agency_Id,now(),apkVer,apkPath,locationAccuracy,locationTimeout,photoResolution,photoQuality,max(batch_id) from user_master,configuration,state_master where username=? and roleId=4"; 

	      pstmt1 = conn.prepareStatement(query); // create a statement
	      pstmt1.setString(1, diviceMaster.getUser()); // set input parameter
	      rs = pstmt1.executeQuery();
	      // extract data from the ResultSet
	      while (rs.next()) {
	    	  loginResponse=new LoginResponse();
	    	  loginResponse.setStatus("success");
	    	  loginResponse.setUserId(rs.getInt(1));
	    	  loginResponse.setRole(rs.getInt(2));
	    	  loginResponse.setSupId(rs.getInt(3));
	    	  loginResponse.setAgenId(rs.getInt(4));
	    	DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
	    	Date date = new Date();
	    	loginResponse.setTime(df.format(date));
	    	loginResponse.setApkVer(rs.getInt(6));
	    	loginResponse.setApkPath(rs.getString(7));
	    	loginResponse.setLocAcc(rs.getInt(8));
	    	  loginResponse.setLocTime(rs.getInt(9));
	    	  loginResponse.setPhotoRes(rs.getInt(10));
	    	  loginResponse.setPhotoQly(rs.getInt(11));
	    	  loginResponse.setStatesBatch(rs.getInt(12));
	    	 
	    	  }
	    } catch (Exception e) {
	    	logger.error("Exception occured while loading the config details :"
					+ e.getMessage());
			throw new ERPException("Exception occured while loading the config details :"
					+ e.getMessage());
	    } finally {
	      try {
	    	  if(rs!=null){
	        rs.close();
	    	  }
	    	  if(pstmt1!=null){
	        pstmt1.close();
	    	  }
	    	  if(conn!=null){
	        conn.close();
	    	  }
	      } catch (SQLException e) {
	        e.printStackTrace();
	      }
	    }
	    logger.info("=====END UserServiceDAOImpl getDeviceInfo======");
return loginResponse;
	}
	@Override
	public UserMaster validateUser(int userId) {
		UserMaster userMaster=new UserMaster();
		logger.info("=====START UserServiceDAOImpl validateUser======");
		Session session = null;
		try {
			session = sessionFactory.openSession();
		Query query = session.createQuery("from UserMaster where id = :userId");
		userMaster=(UserMaster)query.setParameter("userId", userId).uniqueResult();
		} catch (HibernateException he) {
			logger.error("Exception occured while validating the user:"
					+ he.getMessage());
			throw new ERPException(
					"Exception occured while validating the user:"
							+ he.getMessage());
		} catch (ERPException erp) {
			logger.error("Exception occured while validating the user:"
					+ erp.getMessage());
			throw new ERPException("Error while validating the user");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		logger.info("=====END UserServiceDAOImpl validateUser======");
		return userMaster;
	}
	
	
}

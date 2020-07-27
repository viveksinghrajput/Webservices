package com.rural.DAO;

import java.sql.CallableStatement;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.rural.Model.SecurityQuestions;
import com.rural.Model.UserMaster;
import com.rural.exceptions.ERPException;
import com.rural.utility.ConnectionManager;

@Component
@Repository
public class UserDAOImpl implements UserDAO{

	@Resource(name="sessionFactory")
    protected SessionFactory sessionFactory;

	static Logger logger =Logger.getLogger(UserDAOImpl.class);
	
	@SuppressWarnings("unchecked")
	@Override
	public UserMaster validateUser(String username, String password) {
		logger.debug("======START UserDAOImpl validateUser======");
		UserMaster user=null;
		Session session=null;
		try{
		 session = sessionFactory.openSession();		
		Query query = session
				.createSQLQuery(
						"CALL VALIDATEUSER(:user,:pwd)")
				.addEntity(UserMaster.class)
				.setParameter("user", username)
				.setParameter("pwd", password);
		List<UserMaster> list = query.list();
		if ((list != null) && (list.size() > 0)) 
		{
			Iterator<UserMaster> itr=list.iterator();
			while(itr.hasNext()) {

				 user = new UserMaster();
				user=(UserMaster) itr.next();
				
			}
		}
		 logger.info("=====END UserDAOImpl validateUser======");
		} catch (HibernateException he) {
			logger.error("Exception occured while validating the user :"+ he.getMessage());
			throw new ERPException("Exception occured while validating the user :"+ he.getMessage());
		} catch (ERPException erp) {
			logger.error("Exception occured while validating the user :"+ erp.getMessage());
			throw new ERPException("Exception occured while validating the user :"+ erp.getMessage());
		} catch (Exception e) {
			logger.error("Exception occured while validating the user :"+ e.getMessage());
			throw new ERPException("Exception occured while validating the user :"+ e.getMessage());
		} 	
		finally {
		      try {
		        session.close();
		      } catch (Exception e) {
		        e.printStackTrace();
		      }
		    }
		return user;
	}

	
	@SuppressWarnings("unchecked")
	@Override
	public List<SecurityQuestions> getAllitemDesc() {
		logger.debug("======START UserDAOImpl getAllitemDesc======");
	List<SecurityQuestions> qList = null;
	Session session=null;
	try {
		 session = sessionFactory.openSession();
		qList = session.createQuery("from SecurityQuestions").list();
		session.close();
		if(qList!= null && qList.size() > 0) {
		 logger.info("=====END UserDAOImpl allWareHouse======");
		 return qList;
		} }catch (HibernateException he) {
			logger.error("Exception occured while loading the Questions :"+ he.getMessage());
			throw new ERPException("Exception occured while loading the Questions :"+ he.getMessage());
		} catch (ERPException erp) {
			logger.error("Exception occured while loading the Questions  :"+ erp.getMessage());
			throw new ERPException("Error while loading the Questions ");
		} catch (Exception e) {
			logger.error("Exception occured while loading the Questions  :"+ e.getMessage());
			throw new ERPException("Error while loading the Questions ");
		} 	
		finally {
		      try {
		      } catch (Exception e) {
		        e.printStackTrace();
		      }
		    }
	return qList;
	}

	@Override
	public String resethitcount(String username) {
		 logger.info("=====END UserDAOImpl resethitcount======");
		Session session=null;
		String str="";
		try {
			 session = sessionFactory.openSession();
	Query query = session.createQuery("update UserMaster set counterFlag = 0 where username= :username");
	query.setParameter("username", username);
	int result=query.executeUpdate();
	 logger.info("=====END UserDAOImpl resethitcount======");
		} catch (HibernateException he) {
			logger.error("Exception occured while Resetting the User hit count :"+ he.getMessage());
			throw new ERPException("Exception occured while Resetting the User hit count :"+ he.getMessage());
		} catch (ERPException erp) {
			logger.error("Exception occured while Resetting the User hit count:"+ erp.getMessage());
			throw new ERPException("Error while Resetting the User hit count");
		} catch (Exception e) {
			logger.error("Exception occured while Resetting the User hit count :"+ e.getMessage());
			throw new ERPException("Error while Resetting the User hit count");
		} 	
		finally {
		      try {
		        session.close();
		      } catch (Exception e) {
		        e.printStackTrace();
		      }
		    }
	return str;
	}

	@Override
	public int countLogin(String username) {
		 logger.info("=====START UserDAOImpl countLogin======");
	CallableStatement cstmt=null;
	int count = 0;
	try {
	cstmt = ConnectionManager.getConnection().prepareCall("{call CounterLogin(?,?)}");
	cstmt.setString(1, username);
	cstmt.registerOutParameter(2, java.sql.Types.INTEGER);
	cstmt.executeUpdate();
	count = cstmt.getInt(2);
	logger.info("=====END UserDAOImpl countLogin======");
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
			cstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	return count;


	}

}

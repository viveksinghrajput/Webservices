/*
*MenuMasterDAOImpl.java
*Created By		:Bhagya Lakshmi Mula
*Created Date	:Jul 4, 2018
*/
package com.rural.DAO;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import com.rural.Model.MenuMaster;
import com.rural.Model.UserMaster;
import com.rural.exceptions.ERPException;
import com.rural.utility.CommonUtility;

@Component
@Repository
public class MenuMasterDAOImpl implements MenuMasterDAO {

	@Resource(name="sessionFactory")
	protected SessionFactory sessionFactory;
	
	static Logger logger =Logger.getLogger(MenuMasterDAO.class);

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	protected Session getSession() {
		return sessionFactory.openSession();
	}

	@Override
	public Map<String, Map<String, String>> getDynamicMenu(UserMaster user) {
		logger.info("=====START MenuMasterDAOImpl getDynamicMenu======");
		Session session=null;
		Map<String, Map<String, String>> dynamicMenuMap =null;
		try{
		session = sessionFactory.openSession();
		dynamicMenuMap = new LinkedHashMap<String, Map<String, String>>();
		String strMenu=null;
		//Get Main Menu
		//String strMenu="select distinct m.menuGroup from MenuMaster m,RoleMaster r,UserMaster u where u.username=:username and u.roleId = r.roleId and m.menuGroup=r.roleMenu and  r.roleId IN(select distinct r1.roleMenu from RoleMaster r1 ,User u1  where  u1.roleName = r1.roleName and r1.accessLevel=1)";
		if(user.getAppType().equalsIgnoreCase("h2h")){
		strMenu="select distinct m.menuGroup from MenuMaster m,RoleMaster r,UserMaster u where "
		+ "u.username=:username and m.appType='H2H' and u.roleId = r.roleId and m.menuId=r.roleMenuId and m.appType=u.appType and "
		+ "r.roleId IN(select distinct r1.roleId from Roles r1 ,UserMaster u1  where  u1.roleId = r1.roleId)";
		}
		else{
			strMenu="select distinct m.menuGroup from MenuMaster m,RoleMaster r,UserMaster u where "
					+ "u.username=:username and m.appType='WP' and u.roleId = r.roleId and m.menuId=r.roleMenuId and m.appType=u.appType and "
					+ "r.roleId IN(select distinct r1.roleId from Roles r1 ,UserMaster u1  where  u1.roleId = r1.roleId)";
		}
		 
		Query query = session.createQuery(strMenu);
		query.setParameter("username", user.getUsername());
		List<String> list= query.list();
		//Get Main Menu End
		
		//Get Menu Links
		String menu="";
		MenuMaster menuMaster=null;
		String[] menuArray = CommonUtility.convertListToStringArray(list);
		for(int i = 0; i < menuArray.length; i++) 
		{
			menu=menuArray[i];
			System.out.println("menu received :"+menu);
			String qryMenuLinks=null;
			if(user.getAppType().equalsIgnoreCase("h2h")){
			qryMenuLinks="select * from menu_master where  menuGroup = :menu  and app_type='H2H' order by menuOrder";
			}
			else{
				qryMenuLinks="select * from menu_master where  menuGroup = :menu and app_type='WP' order by menuOrder";
			}
			 SQLQuery qry = session.createSQLQuery(qryMenuLinks);
			 qry.addEntity(MenuMaster.class);
			 qry.setParameter("menu", menu);
			 List data = qry.list();	 
				Map<String,String> subMap=new LinkedHashMap<String, String>();
			 for(Object object : data) {
				 menuMaster = (MenuMaster)object;
					subMap.put(menuMaster.getMenuItem(),menuMaster.getMenuLink());
					dynamicMenuMap.put(menuArray[i], subMap);

	         }
			//Get Menu Links END
		}
	}catch (HibernateException  he) {
		logger.error("Exception occured in loading dynamic menu:" + he.getMessage()); 
		throw new ERPException("Exception occured in loading dynamic menu::"+he.getMessage());
	}catch(ERPException erp)
	{
		logger.error("Exception occured in loading dynamic menu:" + erp.getMessage()); 
		throw new ERPException("Exception occured in loading dynamic menu::"+erp.getMessage());
}catch (Exception e) {
	logger.error("Exception occured in loading dynamic menu:" + e.getMessage()); 
	throw new ERPException("Exception occured in loading dynamic menu::"+e.getMessage());
	}
	finally{
	    session.close();
	}
		logger.info("=====END MenuMasterDAOImpl getDynamicMenu======");
		return dynamicMenuMap;
	}

}

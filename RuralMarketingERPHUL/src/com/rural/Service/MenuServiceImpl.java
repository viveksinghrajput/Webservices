/*
*MenuServiceImpl.java
*Created By		:Bhagya Lakshmi Mula
*Created Date	:Apr 4, 2018
*/
package com.rural.Service;

import java.sql.SQLException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import com.rural.DAO.MenuMasterDAO;
import com.rural.Model.UserMaster;

@Component
@Service
public class MenuServiceImpl implements MenuService{

	@Autowired
	MenuMasterDAO menuMasterDAO;
	
	@Override
	public Map<String,Map<String, String>> getMenu(UserMaster userMaster)  {
		Map<String, Map<String, String>> mapMenu=null;
		//menuMasterDAO =new MenuMasterDAOImpl();
		try {
			mapMenu = menuMasterDAO.getDynamicMenu(userMaster);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return mapMenu;
		
	}

	

}

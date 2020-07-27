/*
*MenuMasterDAO.java
*Created By		:Bhagya Lakshmi Mula
*Created Date	:Jul 4, 2018
*/
package com.rural.DAO;

import java.sql.SQLException;
import java.util.Map;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import com.rural.Model.UserMaster;

@Repository
@Component
public interface MenuMasterDAO {
	public Map<String,Map<String,String>> getDynamicMenu(UserMaster userMaster) throws SQLException;
}

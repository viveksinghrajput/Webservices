/*
*ConnectionManager.java
*Created By		:Bhagya Lakshmi Mula
*Created Date	:Apr 4, 2018
*/
package com.rural.utility;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionManager {
	
	  //Get JDBC Connection
	  public static Connection getConnection() throws Exception {
		  //SET GLOBAL group_concat_max_len = 1000000;
		  // SET group_concat_max_len=1000000;
		    String driver = "com.mysql.jdbc.Driver";
		    String url = "jdbc:mysql://localhost:3306/hul_rural";
		    String username = "root";
		    String password = "root";
		    Class.forName(driver);
		    Connection conn = DriverManager.getConnection(url, username, password);
		    return conn;
		  }

}

/*
*MenuController.java
*Created By		:Bhagya Lakshmi Mula
*Created Date	:Jul 4, 2018
*/
package com.rural.Controller;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import com.rural.Model.UserMaster;
import com.rural.Service.MenuService;
import com.rural.Service.UserService;
import com.rural.Service.UserServiceImpl;


@Controller
public class MenuController {
	
	@Autowired
	private MenuService menuService;
	
	@RequestMapping(value="/ShowMenu")
	public ModelAndView getMenu(@RequestParam("username") String username, @RequestParam("password") String password,HttpServletRequest req) 
			throws Exception{
		//Write Logic to show menu based on Logged in user of its Role.
		HttpSession session=req.getSession();
		ModelAndView mav = null;
		UserService userService=new UserServiceImpl();
	//	menuService=new MenuServiceImpl();
		UserMaster user=new UserMaster();
		user.setUsername(username);
		user.setPassword(password);
	   UserMaster u = userService.validateUser(user.getUsername(),user.getPassword());
	  // String name=userService.getName(username);
	    if (u!=null) {
	    	Map<String,Map<String,String>> mapMenu=new LinkedHashMap<String, Map<String,String>>();// menuService.getMenu(username);
	    	
	    	session.setAttribute("username", username);
		    session.setAttribute("name", username);
		    session.setAttribute("mapMenu", mapMenu);

	    mav = new ModelAndView("MainLinks");
	    mav.addObject("name", username);
	    mav.addObject("mapMenu", username);
	    
	    } else {
	    mav = new ModelAndView("login");
	    mav.addObject("message", "Username or Password is wrong!!");
	    }
	    

	    return mav;		

		}
	@RequestMapping(value="/logout",method = RequestMethod.GET)
	public ModelAndView logOut(){
		return new ModelAndView("login");
		}
	
}

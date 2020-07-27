/*
*LoginController.java
*Created By		:Bhagya Lakshmi Mula
*Created Date	:Jul 4, 2018
*/
package com.rural.Controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rural.Model.AgencyMaster;
import com.rural.Model.Roles;
import com.rural.Model.SecurityAnswers;
import com.rural.Model.SecurityQuestions;
import com.rural.Model.SecurityTemp;
import com.rural.Model.CityMaster;
import com.rural.Model.StateMaster;
import com.rural.Model.StatusMaster;
import com.rural.Model.UserMaster;
import com.rural.Model.VendorMaster;
import com.rural.Service.LoginService;
import com.rural.Service.MenuService;
import com.rural.Service.PrePlanningService;
import com.rural.Service.UserService;
import com.rural.exceptions.ERPException;

@Component
@Controller
public class LoginController {

	@Autowired
	public LoginService loginService;
	@Autowired
	public UserService userService;
	@Autowired
	private MenuService menuService;

	@Autowired
	PrePlanningService prePlanningService;

	static Logger logger = Logger.getLogger(LoginController.class);

	@RequestMapping(value = { "/", "/Login" })
	public ModelAndView login(ModelMap model) {
		// model.addAttribute("user", new User());
		logger.info("login");
		return new ModelAndView("login");
	}
	
	@RequestMapping(value = "/Home")
	public ModelAndView homePage(ModelMap model, HttpServletRequest request) {
		// Disable HomePage as Currently PrePlanning will be default Page
		// return new ModelAndView("homePage");
		// Set per-Planning as homePage
		HttpSession session =(HttpSession) request.getSession();
		String username = (String)session.getAttribute("username");

		UserMaster user=(UserMaster)session.getAttribute("user");
		logger.info("Get Menus");
		Map<String,Map<String,String>> mapMenu= menuService.getMenu(user);
		System.out.println("menu map is........"+mapMenu);
		List<StateMaster> stateMasterList=loginService.getStateMap();
		 StateMaster stateMaster=null;
		 CityMaster cityMaster=null;
		 Map<Integer, String> stateMap=new HashMap<Integer, String>();
		 Map<Integer, String> cityMap=new HashMap<Integer, String>();
		 Iterator<StateMaster> stateitr=stateMasterList.iterator();
		 Set<CityMaster> citySet=new HashSet<CityMaster>();
		 while(stateitr.hasNext())
		 {
			 stateMaster=new StateMaster();
			 stateMaster=stateitr.next();
			 stateMap.put(stateMaster.getStateId(), stateMaster.getStates());
			 citySet=stateMaster.getCities();
			 Iterator<CityMaster> cityItr=citySet.iterator();
			 while(cityItr.hasNext()){
				 cityMaster=new CityMaster();
				 cityMaster=cityItr.next();
				 cityMap.put(cityMaster.getCityId(),cityMaster.getCityName());
			 }
			 
		 }
		  List<Roles> roleList=loginService.getRoles();
			Roles roles=null;
			AgencyMaster agency=null;
			StatusMaster status=null;
			UserMaster users = null;
			Map<Integer, String> rolesMap=new HashMap<Integer, String>();
			Iterator<Roles> rolesItr=roleList.iterator();
			while(rolesItr.hasNext())
			{
			roles=new Roles();
			roles=rolesItr.next();
			rolesMap.put(roles.getRoleId(), roles.getRoleName());
			}
			
			
				/* Display username */
				String firstName = null;
				Integer Id = null;
			List<UserMaster> namesList = loginService.getNames(Id, firstName);
				
			Map<Integer, String> nameList = new HashMap<Integer, String>();
			Iterator<UserMaster> useritr  = namesList.iterator();
			while(useritr.hasNext()) {
				users = new UserMaster();
				users = useritr.next();
				nameList.put(users.getId(), users.getFirstName()+" "+users.getLastName());
				
			}
				List<AgencyMaster> agencyList=loginService.getAgencyMap();
				Map<Integer, String> agencyMap=new HashMap<Integer, String>();
				Iterator<AgencyMaster> agencyItr=agencyList.iterator();
				while(agencyItr.hasNext())
				{
					agency=new AgencyMaster();
					agency=agencyItr.next();
					agencyMap.put(agency.getId(), agency.getAgencyname());
				}
				
				List<VendorMaster> vendorList=loginService.getVendorMap();
				Map<Integer, String> vendorMap=new HashMap<Integer, String>();
				Iterator<VendorMaster> vendorItr=vendorList.iterator();
				while(vendorItr.hasNext())
				{
					VendorMaster vendor=new VendorMaster();
					vendor=vendorItr.next();
					vendorMap.put(vendor.getId(), vendor.getVendorName());
				}
				
				
				List<StatusMaster> statusList=loginService.getStatusMap();
				Map<Integer, String> statusMap=new HashMap<Integer, String>();
				Iterator<StatusMaster> statusItr=statusList.iterator();
				while(statusItr.hasNext())
				{
					status=new StatusMaster();
					status=statusItr.next();
					statusMap.put(status.getStatusId(), status.getStatusDesc());
				}
				 userService.resethitcount(username);
				 List<String> menuList=loginService.getMenuListbyRole(user.getRoleId());

		session.setAttribute("mapMenu", mapMenu);
		session.setAttribute("userRole", rolesMap.get(user.getRoleId()));
		session.setAttribute("stateMap", stateMap);
		session.setAttribute("cityMap", cityMap);
		session.setAttribute("rolesMap", rolesMap);
		session.setAttribute("vendorMap", vendorMap);
		session.setAttribute("agencyMap", agencyMap);
		session.setAttribute("statusMap", statusMap);
		session.setAttribute("userRole1", user.getRoleId());
		session.setAttribute("nameList", nameList);
		session.setAttribute("menuList", menuList);
		
		Integer strRole =(Integer) request.getSession().getAttribute("userRole1");
			
			if(strRole==1 || strRole==2) {
					return new ModelAndView("forward:/prePlan");
			}
			else if(strRole==3 || strRole==6) 
			{
				return new ModelAndView("forward:/warehouse");
			}
			
			else if(strRole==4 || strRole==7 || strRole==8) 
			{
				return new ModelAndView("forward:/deliveryandinventory");
			}
				
			else {
				return new ModelAndView("homePage");
			}
	}
	
	@RequestMapping(value = "/homePage")
	public ModelAndView homePage1(@RequestParam(required =false, defaultValue = "h2h", value="selection") String strSelection, 
			ModelMap model, HttpServletRequest request,HttpServletResponse responce) {
		// Disable HomePage as Currently PrePlanning will be default Page
		// return new ModelAndView("homePage");
		// Set per-Planning as homePage
		HttpSession session =(HttpSession) request.getSession();
		
		String username = (String)session.getAttribute("username");
		
			if(username!=null) {
			if(strSelection.equalsIgnoreCase("h2h")) {
				
				UserMaster user=(UserMaster)session.getAttribute("user");
				logger.info("Get Menus");
				
				user.setAppType("H2H");
				Map<String,Map<String,String>> mapMenu= menuService.getMenu(user);
				System.out.println("menu map is........"+mapMenu);
				List<StateMaster> stateMasterList=loginService.getStateMap();
				 StateMaster stateMaster=null;
				 CityMaster cityMaster=null;
				 Map<Integer, String> stateMap=new HashMap<Integer, String>();
				 Map<Integer, String> cityMap=new HashMap<Integer, String>();
				 Iterator<StateMaster> stateitr=stateMasterList.iterator();
				 Set<CityMaster> citySet=new HashSet<CityMaster>();
				 while(stateitr.hasNext())
				 {
					 stateMaster=new StateMaster();
					 stateMaster=stateitr.next();
					 stateMap.put(stateMaster.getStateId(), stateMaster.getStates());
					 citySet=stateMaster.getCities();
					 Iterator<CityMaster> cityItr=citySet.iterator();
					 while(cityItr.hasNext()){
						 cityMaster=new CityMaster();
						 cityMaster=cityItr.next();
						 cityMap.put(cityMaster.getCityId(),cityMaster.getCityName());
					 }
					 
				 }
				  List<Roles> roleList=loginService.getRoles();
					Roles roles=null;
					AgencyMaster agency=null;
					StatusMaster status=null;
					UserMaster users = null;
					Map<Integer, String> rolesMap=new HashMap<Integer, String>();
					Iterator<Roles> rolesItr=roleList.iterator();
					while(rolesItr.hasNext())
					{
					roles=new Roles();
					roles=rolesItr.next();
					rolesMap.put(roles.getRoleId(), roles.getRoleName());
					}
					
					
						/* Display username */
						String firstName = null;
						Integer Id = null;
					List<UserMaster> namesList = loginService.getNames(Id, firstName);
						
					Map<Integer, String> nameList = new HashMap<Integer, String>();
					Iterator<UserMaster> useritr  = namesList.iterator();
					while(useritr.hasNext()) {
						users = new UserMaster();
						users = useritr.next();
						nameList.put(users.getId(), users.getFirstName()+" "+users.getLastName());
						
					}
						List<AgencyMaster> agencyList=loginService.getAgencyMap();
						Map<Integer, String> agencyMap=new HashMap<Integer, String>();
						Iterator<AgencyMaster> agencyItr=agencyList.iterator();
						while(agencyItr.hasNext())
						{
							agency=new AgencyMaster();
							agency=agencyItr.next();
							agencyMap.put(agency.getId(), agency.getAgencyname());
						}
						
						List<VendorMaster> vendorList=loginService.getVendorMap();
						Map<Integer, String> vendorMap=new HashMap<Integer, String>();
						Iterator<VendorMaster> vendorItr=vendorList.iterator();
						while(vendorItr.hasNext())
						{
							VendorMaster vendor=new VendorMaster();
							vendor=vendorItr.next();
							vendorMap.put(vendor.getId(), vendor.getVendorName());
						}
						
						
						List<StatusMaster> statusList=loginService.getStatusMap();
						Map<Integer, String> statusMap=new HashMap<Integer, String>();
						Iterator<StatusMaster> statusItr=statusList.iterator();
						while(statusItr.hasNext())
						{
							status=new StatusMaster();
							status=statusItr.next();
							statusMap.put(status.getStatusId(), status.getStatusDesc());
						}
						 userService.resethitcount(username);
						 List<String> menuList=loginService.getMenuListbyRole(user.getRoleId());
				session.setAttribute("mapMenu", mapMenu);
				session.setAttribute("userRole", rolesMap.get(user.getRoleId()));
				session.setAttribute("stateMap", stateMap);
				session.setAttribute("cityMap", cityMap);
				session.setAttribute("rolesMap", rolesMap);
				session.setAttribute("vendorMap", vendorMap);
				session.setAttribute("agencyMap", agencyMap);
				session.setAttribute("statusMap", statusMap);
				session.setAttribute("userRole1", user.getRoleId());
				session.setAttribute("nameList", nameList);
				session.setAttribute("menuList", menuList);
				
				//System.out.println("Role............."+rolesMap.get(user.getRoleId()));
				/*Integer strRole =(Integer) request.getSession().getAttribute("userRole1");
				if(strRole==1) {
					return new ModelAndView("wallpainting");
				}*/
				
			}
		
	
			Integer strRole = (Integer) request.getSession().getAttribute("userRole1");
			if(strRole==1 || strRole==2) {
					return new ModelAndView("forward:/prePlan");
			}
			else if(strRole==3 || strRole==6) 
			{
				return new ModelAndView("forward:/warehouse");
			}
			
			else if(strRole==4 || strRole==7 || strRole==8) 
			{
				return new ModelAndView("forward:/deliveryandinventory");
			}
				
			else {
				return new ModelAndView("homePage");
			}
			}else {
				return new ModelAndView("login");
			}

		
	}

	@RequestMapping(value = "/LoginUser", method = RequestMethod.POST)
	public ModelAndView validateLogin(@RequestParam("username") String username,
			@RequestParam("password") String password, HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		logger.info("LoginController :: validateLogin");
		HttpSession session = request.getSession(true);
		session.removeAttribute("strStatus");
		session.setMaxInactiveInterval(60*10);
		
		String ipAddress = captureIPAddress(request);
		session.setAttribute("ipAddress", ipAddress);
		logger.info("username :" + username);
		// get user Details
		logger.info("get user Details......");
		UserMaster user = null;
		try {
			if (username != null && username.length() > 0 && password != null && password.length() > 0) {
				user = userService.validateUser(username, password);
				//session.setAttribute("password", password);
			} 
			else {
				logger.info("No Value Passed in UserName and PassWord Field............");
			}
		} catch (ERPException erp) {
			request.setAttribute("message", "Problem Occured while validating the user. Please try Again");
			logger.error("Problem Occured while validating the user. Please try Again"+erp.getMessage());
			return new ModelAndView("forward:/Login");
		} catch (Exception e) {
			request.setAttribute("message", "Problem Occured while validating the user. Please try Again");
			logger.error("Problem Occured while validating the user. Please try Again"+e.getMessage());
			return new ModelAndView("forward:/Login");
		}
		

		if (user != null) {
			 List<Roles> roleList=loginService.getRoles();
			Roles roles=null;
			try{
				logger.info("User Validated..");
				Map<Integer, String> rolesMap=new HashMap<Integer, String>();
				Iterator<Roles> rolesItr=roleList.iterator();
				while(rolesItr.hasNext())
				{
				roles=new Roles();
				roles=rolesItr.next();
				rolesMap.put(roles.getRoleId(), roles.getRoleName());
				}
			int userId= user.getId();
			String password1=loginService.getpassword(userId);
			String name=user.getFirstName()+" "+user.getLastName();
			session.setAttribute("name", name);
			session.setAttribute("userId", user.getId());
			session.removeAttribute("password1");
			session.setAttribute("password", password1);
			session.setAttribute("username", username);
			session.setAttribute("userId", userId);
			session.setAttribute("userRole1", user.getRoleId());
			session.setAttribute("userRole", rolesMap.get(user.getRoleId()));
			session.removeAttribute("statusMsg");
			request.removeAttribute("statusMsg");
			session.setAttribute("loginBean", user);
			ModelAndView mav=null;
			if(user.getIslogin().equals("0"))
			{
				session.setAttribute("userId", user.getId());
				session.setAttribute("username", user.getUsername());
				session.removeAttribute("password1");
				session.setAttribute("password", password1);
				Map<Integer, String> map = new LinkedHashMap<Integer, String>();
				try{
				List<SecurityQuestions> securityQuestionsList = userService.securityQnsList();
				Iterator<SecurityQuestions> itr = securityQuestionsList.iterator();
				SecurityQuestions securityQuestions = null;
				while (itr.hasNext()) {
					securityQuestions = new SecurityQuestions();
					securityQuestions = itr.next();
					map.put(securityQuestions.getQus_id(), securityQuestions.getQus_name());

				}
				}
				catch(Exception e){
					request.setAttribute("message", "Problem Occured while validating the user. Please try Again");
				}
				mav= new ModelAndView("securityquestion");
				mav.addObject("map", map);
				return mav;
				
			}
			
			if(user.getIslogin().equals("1"))
			{
				session.setAttribute("userId", user.getId());
				session.setAttribute("username", user.getUsername());
				session.setAttribute("role", user.getRoleId());
				List<SecurityQuestions> qnsList=new ArrayList<SecurityQuestions>();
				mav= new ModelAndView("loginsecurityqns");
				try{
				 qnsList = loginService.qList(userId);
				}
				catch(Exception e){
					request.setAttribute("message", "Problem Occured while loading the questions. Please try Again");
				}
				mav.addObject("qnsList", qnsList);
				session.setAttribute("user", user);
				return  mav;
				
			}
			
			
		} catch (ERPException erp) {
			request.setAttribute("message", "Problem Occured. Please try Again");
		} catch (Exception e) {
			request.setAttribute("message", "Problem Occured. Please try Again");
		}
			return new ModelAndView("forward:/Home");
		} else {
			
			ModelAndView mav= new ModelAndView("forward:/Login");
			int count= userService.countLogin(username);
			
			logger.info("User Not Validated..");
			// mav = new ModelAndView("login");

			request.setAttribute("message", "Incorrect Username Or Password");
			if(count>4) {
				mav.addObject("message", "Account blocked. contact Admin");
				
			}
			// return mav;
			// response.sendRedirect("/Login");
			return mav;
		}

		// return new ModelAndView("homePage");
	}

	public String captureIPAddress(HttpServletRequest request) {
		String remoteAddr = "";
		if (request != null) {
			remoteAddr = request.getHeader("X-FORWARDED-FOR");
			if (remoteAddr == null || "".equals(remoteAddr)) {
				remoteAddr = request.getRemoteAddr();
			}
		}
		return remoteAddr;
	}

/*	private Map<String, String> getRequestHeadersInMap(HttpServletRequest request) {

		Map<String, String> result = new HashMap<>();

		Enumeration headerNames = request.getHeaderNames();
		while (headerNames.hasMoreElements()) {
			String key = (String) headerNames.nextElement();
			String value = request.getHeader(key);
			result.put(key, value);
		}

		return result;
	}*/

	@RequestMapping(value = "/LogOut", method = RequestMethod.GET)
	public ModelAndView LogOut(ModelMap model, HttpServletRequest request, SessionStatus status) {
		HttpSession session = (HttpSession) request.getSession();
		try {
			status.setComplete();
			session.removeAttribute("name");
			session.removeAttribute("mapMenu");
			session.removeAttribute("username");
			session.removeAttribute("userRole");
			session.removeAttribute("userId");
			session.removeAttribute("stateMap");
			session.removeAttribute("cityMap");
			session.removeAttribute("rolesMap");
			session.removeAttribute("agencyMap");
			session.removeAttribute("mapMenu");
			session.removeAttribute("statusMap");
			session.removeAttribute("nameList");
			session.removeAttribute("password");
			session.removeAttribute("brandMap");
			session.invalidate();
			request.setAttribute("message", "Logged Out Succesfully");
			logger.info("Logged Out Succesfully..");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("login");
	}
	
	@SuppressWarnings("null")
	@RequestMapping(value = "/submitanswers", method= RequestMethod.POST)
		public ModelAndView saveSecurityAnswers(@RequestParam("password") String strpassword, HttpServletRequest request, ModelAndView model) throws JsonParseException, JsonMappingException, IOException {
		HttpSession session = (HttpSession) request.getSession();
		Integer userId= (Integer)session.getAttribute("userId");
		String username = (String)session.getAttribute("username");
		SecurityTemp[] obj = null;
		String newList = (String) request.getParameter("ans-list-info");
		ObjectMapper mapper = new ObjectMapper();
		List<SecurityAnswers> ansList=new ArrayList<SecurityAnswers>();
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		obj = mapper.readValue(newList, SecurityTemp[].class);
		for(int i = 0; i<obj.length; i++) {
			SecurityAnswers ans=new SecurityAnswers();
			ans.setQus_id(obj[i].getqNo());
			ans.setAns_desc(obj[i].getAns());
			ansList.add(ans);
		}
		try{
		loginService.saveAnswers(ansList, userId, username, strpassword);
		} catch (ERPException erp) {
			request.setAttribute("statusMsg","Problem Occured. Please try Again");
			return new ModelAndView("securityquestion");
		} catch (Exception e) {
			request.setAttribute("statusMsg","Problem Occured. Please try Again");
			return new ModelAndView("securityquestion");
		}
		return new ModelAndView("login");
			
	}
	@RequestMapping(value="/profileSelection", method=RequestMethod.POST)
	public ModelAndView redirectproject(HttpServletRequest request) {
			return new ModelAndView("profileSelection");
	}
	@RequestMapping(value = "/submitquestions", method= RequestMethod.POST)
	public ModelAndView validateQuestions(HttpServletRequest request) throws JsonParseException, JsonMappingException, IOException {
		HttpSession session = (HttpSession) request.getSession();
		Integer userId= (Integer)session.getAttribute("userId");
		String username= (String)session.getAttribute("username");
		Integer role=(Integer)session.getAttribute("role");
		if(username!=null) {
		SecurityTemp[] obj = null;
		String newList = (String) request.getParameter("ans-list-info");
		ObjectMapper mapper = new ObjectMapper();
		List<SecurityAnswers> ansList=new ArrayList<SecurityAnswers>();
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		obj = mapper.readValue(newList, SecurityTemp[].class);
		int result=0;
		for(int i = 0; i<obj.length; i++) {
			SecurityAnswers ans=new SecurityAnswers();
			ans.setQus_id(obj[i].getqNo());
			ans.setAns_desc(obj[i].getAns());
			ansList.add(ans);
		}
		try{
		 result = loginService.validateQuestions(userId, ansList);
		} catch (ERPException erp) {
			request.setAttribute("statusMsg","Problem Occured while validating the Questions. Please try Again");
			return new ModelAndView("loginsecurityqns");
		} catch (Exception e) {
			request.setAttribute("statusMsg","Problem Occured while validating the Questions. Please try Again");
			return new ModelAndView("loginsecurityqns");
		}
		if(result==2)
		{
			if(role==1)
			{
				return new ModelAndView("");
			}
			else {
				UserMaster user=(UserMaster)session.getAttribute("loginBean");
				if(user.getAppType().equalsIgnoreCase("h2h")){
				
				return new ModelAndView("forward:/Home");
				}
				else{
					Map<String,Map<String,String>> mapMenu= menuService.getMenu(user);
					System.out.println("menu map is........"+mapMenu);
					session.setAttribute("mapMenu",mapMenu);
					return new ModelAndView("forward:/wallpainting");
				}
			}
			}
		else {
			ModelAndView	mav= new ModelAndView("loginsecurityqns");
			List<SecurityQuestions> qnsList = loginService.qList(userId);
			mav.addObject("qnsList", qnsList);
			mav.addObject("statusMsg", "Questions and Answers does not match");
			
			int count=loginService.countAns(username);
			if(count>4) {
				mav.addObject("statusMsg", "User is blocked since 5 attempts completed. Please contact Admin");
			}
			return mav;
		}
		}
		else {
			return new ModelAndView("login");
		}
	}
	
	@RequestMapping(value = "/submitquestionsAdmin", method= RequestMethod.GET)
	  public @ResponseBody String submitquestionsAdmin(HttpServletRequest request) throws JsonParseException,
	              JsonMappingException, IOException {
	    HttpSession session = (HttpSession) request.getSession();
	    Integer userId= (Integer)session.getAttribute("userId");
	    String username= (String)session.getAttribute("username");
	    Integer role=(Integer)session.getAttribute("role");
	    SecurityTemp[] obj = null;
	    String newList = (String) request.getParameter("myKeyVals");
	    ObjectMapper mapper = new ObjectMapper();
	    List<SecurityAnswers> ansList=new ArrayList<SecurityAnswers>();
	    mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	    obj = mapper.readValue(newList, SecurityTemp[].class);
	    int result=0;
	    for(int i = 0; i<obj.length; i++) {
	      SecurityAnswers ans=new SecurityAnswers();
	      ans.setQus_id(obj[i].getqNo());
	      ans.setAns_desc(obj[i].getAns());
	      ansList.add(ans);
	    }
	    try{
	     result = loginService.validateQuestions(userId, ansList);
	    } catch (ERPException erp) {
	      request.setAttribute("statusMsg","Problem Occured while validating the Questions. Please try Again");
	      return "Problem Occured while validating the Questions. Please try Again";
	    } catch (Exception e) {
	      request.setAttribute("statusMsg","Problem Occured while validating the Questions. Please try Again");
	      return "Problem Occured while validating the Questions. Please try Again";
	    }
	    if(result==2){
	      return "1";
	    }
	    else{
	      return "0";
	    }
	    
	  }
	
	
	@RequestMapping(value="/wallpainting", method=RequestMethod.POST)
	public ModelAndView wallpaintinghome(HttpServletRequest request) {
		HttpSession session=request.getSession();
		String appType=request.getParameter("appType");
		session.removeAttribute("mapMenu");
		
		UserMaster user=(UserMaster)session.getAttribute("loginBean");
		if(appType!=null){
		if(appType.equalsIgnoreCase("h2h")){
			user.setAppType("H2H");
		}
		else
		{
			user.setAppType("WP");
		}
		}
		Map<String,Map<String,String>> mapMenu= menuService.getMenu(user);
		System.out.println("menu map is........"+mapMenu);
		session.setAttribute("mapMenu",mapMenu);
		session.setAttribute("mapMenu",mapMenu);
			return new ModelAndView("wallpainting");
	}
}

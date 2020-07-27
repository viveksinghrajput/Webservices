package com.rural.Interceptor;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.annotations.common.util.impl.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.rural.Model.SessionBean;
public class RequestHandlerInterceptor extends HandlerInterceptorAdapter {
	
	protected static final org.jboss.logging.Logger logger = LoggerFactory
			.logger(RequestHandlerInterceptor.class);
	public static final ConcurrentMap<String, SessionBean> sessionMap = new ConcurrentHashMap<String, SessionBean>();
	@SuppressWarnings("unchecked")
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		logger.info("Pre handle method - check handling start time");
		String rquestURL = request.getRequestURI();
		String remainURL = (String) rquestURL.substring(rquestURL.lastIndexOf("/") , rquestURL.length());
		 List<String> allowedUriList=new  ArrayList<String>();
		Integer strRole=(Integer) request.getSession().getAttribute("userRole1");
		Integer role =   strRole==null?0:strRole;
		String username=(String)request.getSession().getAttribute("username");
		SessionBean sessionBean=null;
		String sessionId=null;
		HttpSession session = request.getSession();
		System.out.println("username :"+username);
		
		if(remainURL.equalsIgnoreCase("/") ||remainURL.equalsIgnoreCase("/LoginUser")
																 ||remainURL.equalsIgnoreCase("/Home")
																 || remainURL.equalsIgnoreCase("/submitanswers")
																 || remainURL.equalsIgnoreCase("/submitquestions")
																 || remainURL.equalsIgnoreCase("/submitquestionsAdmin")
																 || remainURL.equalsIgnoreCase("/LogOut")
																 ||remainURL.equalsIgnoreCase("/profileSelection")
																 ||remainURL.equalsIgnoreCase("/homePage")
																 ||remainURL.equalsIgnoreCase("/wallpainting")) {
			
			return true;
		}
		
		//taking the map of uri	
		if(strRole!=null){
			if(sessionMap.size()!=0){
				sessionBean=sessionMap.get(username);
				sessionId=sessionBean.getSessionId();
				if(!session.getId().equals(sessionId)) {
					request.getRequestDispatcher("/views/login.jsp").forward(request, response);
					return false;
				}
			}
				
				allowedUriList=( List<String>)request.getSession().getAttribute("menuList");
				if(allowedUriList.contains(remainURL)){
					request.getRequestDispatcher("/views/login.jsp").forward(request, response);
					return false;
				}
				else
				{
					return true;
				}
				
		}
		
		if(request.getSession()==null && role==0 && remainURL.equalsIgnoreCase("/androidLogin")
					|| remainURL.equalsIgnoreCase("/getStates")
					|| remainURL.equalsIgnoreCase("/getWarehouse")
					
					|| remainURL.equalsIgnoreCase("/addWarehouse")
					|| remainURL.equalsIgnoreCase("/updateWarehouse")
					||remainURL.equalsIgnoreCase("/downloadApk")){
			return true;
		}
	
		request.getRequestDispatcher("/views/login.jsp").forward(request, response);
			return false;
	   }
	
	
	
	
	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response,Object handler,ModelAndView modelAndView)throws Exception {
		
		HttpSession session = request.getSession();
		String requestUri = request.getRequestURI();
		String username=null;
		
		  if (session != null && !(requestUri.contains("LoginUser"))) {

			  username = (String) session.getAttribute("username");
			  

}
		  if (username != null) {

              SessionBean sessionBean = new SessionBean();
              sessionBean.setUserId(username);
              sessionBean.setSessionId(session.getId());
              sessionMap.put(username, sessionBean);

             
}


		}
	@Override
	public void afterCompletion(HttpServletRequest request,
            HttpServletResponse response,
            Object handler,
            Exception ex) throws Exception{
	}
}

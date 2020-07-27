
/*
*AdminController.java
*Created By		:Kamal Thappa
*Created Date	:Apr 4, 2018
*/
package com.rural.Controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.rural.Model.AgencyMaster;
import com.rural.Model.CityMaster;
import com.rural.Model.ConversionMaster;
import com.rural.Model.SaturationMaster;
import com.rural.Model.UserMaster;
import com.rural.Model.VendorMaster;
import com.rural.Service.AdminService;
import com.rural.exceptions.ERPException;

@Controller
public class AdminController {
	@Autowired
	AdminService adminService;
	static Logger logger = Logger.getLogger(AdminController.class);

	@RequestMapping(value = "/conversionMas")
	public String showConversionMaster(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		logger.debug("show showConversionMaster");
		HttpSession session = request.getSession();
		session.removeAttribute("ConvStatus");
		String statusMsg = null;
		// Role wise Restricting the user to access the url
		int role = (Integer) request.getSession().getAttribute("userRole1");
		if (role == 1) {
			List<ConversionMaster> list = new ArrayList<ConversionMaster>();
			try {
				list = adminService.getAllConversion();
				// Start -----Sort Conversion List based on Brand Name
				if (!list.isEmpty()) {
					Collections.sort(list, new Comparator<ConversionMaster>() {
						@Override
						public int compare(ConversionMaster c1, ConversionMaster c2) {
							// You should ensure that list doesn't contain null values!
							return c1.getBrandname().compareTo(c2.getBrandname());
						}
					});
					// End -----Sort Conversion List based on Brand Name
				}
			} catch (ERPException erp) {
				statusMsg = "Problem Occured while Loading Conversions";
				request.setAttribute("strStatus", statusMsg);
			} catch (Exception e) {
				statusMsg = "Problem Occured while Loading Conversions";
				request.setAttribute("strStatus", statusMsg);
			}

			session.setAttribute("allConversion", list);
		} else {
			try {
				request.getRequestDispatcher("/views/login.jsp").forward(request, response);
			} catch (Exception e) {
			}
		}
		return ("showConversionMaster");
	}

	@RequestMapping(value = "/saturationMas")
	public String showSaturationMaster(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		logger.debug("show showSaturationMaster");
		HttpSession session = request.getSession(true);
		List<SaturationMaster> list = new ArrayList<SaturationMaster>();
		String statusMsg = null;
		// Role wise Restricting the user to access the url
		int role = (Integer) request.getSession().getAttribute("userRole1");
		if (role == 1) {
			try {
				list = adminService.getAllSaturation();
				// Start -----Sort Saturation List based on Brand Name
				if (!list.isEmpty()) {
					Collections.sort(list, new Comparator<SaturationMaster>() {
						@Override
						public int compare(SaturationMaster c1, SaturationMaster c2) {
							// You should ensure that list doesn't contain null values!
							return c1.getBrand().compareTo(c2.getBrand());
						}
					});
					// End -----Sort Saturation List based on Brand Name
				}
			} catch (ERPException erp) {
				statusMsg = "Problem Occured while Loading Saturations";
				request.setAttribute("strStatus", statusMsg);
			} catch (Exception e) {
				statusMsg = "Problem Occured while Loading Saturations";
				request.setAttribute("strStatus", statusMsg);
			}
			session.setAttribute("allSaturation", list);
		} else {
			try {
				request.getRequestDispatcher("/views/login.jsp").forward(request, response);
			} catch (Exception e) {
			}
		}
		return ("showSaturationMaster");
	}

	@RequestMapping(value = "/getConvRowData", method = RequestMethod.POST)
	public @ResponseBody ConversionMaster getConvRowData(@RequestParam("id") int strId, HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		logger.debug("ConversionMaster ID :" + strId);
		ConversionMaster conMaster = new ConversionMaster();
		// Role wise Restricting the user to access the url
		int role = (Integer) request.getSession().getAttribute("userRole1");
		if (role == 1) {
			try {
				conMaster = adminService.getConvById(strId);
			} catch (ERPException erp) {
				logger.error("Some Problem Occured while getting conversion by id " + erp.getMessage());
			} catch (Exception e) {
				logger.error("Some Problem Occured while getting conversion by id " + e.getMessage());
			}
		} else {
			try {
				request.getRequestDispatcher("/views/login.jsp").forward(request, response);
			} catch (Exception e) {
			}
		}
		return conMaster;
	}

	@RequestMapping(value = "/getSatRowData", method = RequestMethod.POST)
	public @ResponseBody SaturationMaster getSatRowData(@RequestParam("id") int strId, HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		logger.debug("Saturation ID :" + strId);
		SaturationMaster satMaster = new SaturationMaster();
		// Role wise Restricting the user to access the url
		int role = (Integer) request.getSession().getAttribute("userRole1");
		if (role == 1) {
			satMaster = adminService.getSatById(strId);
		} else {
			try {
				request.getRequestDispatcher("/views/login.jsp").forward(request, response);
			} catch (Exception e) {
			}
		}
		return satMaster;
	}

	@RequestMapping(value = "/updateMaster")
	public ModelAndView updateMaster(@RequestParam("id") int strId, @RequestParam("modal") String strModal,
			HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		// Get Saturation Id from request
		logger.debug("ID  		:" + strId);
		logger.debug("new Data 		:" + strModal);
		ModelAndView mav = new ModelAndView("editMaster");
		String statusMsg = null;
		// Role wise Restricting the user to access the url
		int role = (Integer) request.getSession().getAttribute("userRole1");
		if (role == 1) {
			try {
				mav.addObject("model", strModal);
				if (strModal.equalsIgnoreCase("Saturation")) {
					mav.addObject("item", adminService.getSatById(strId));
				} else if (strModal.equalsIgnoreCase("Conversion")) {
					mav.addObject("item", adminService.getConvById(strId));
				}
			} catch (ERPException erp) {
				statusMsg = "Problem Occured while loding the Masters";
				request.setAttribute("strStatus", statusMsg);
			} catch (Exception e) {
				statusMsg = "Problem Occured while loding the Masters";
				request.setAttribute("strStatus", statusMsg);
			}
		} else {
			try {
				request.getRequestDispatcher("/views/login.jsp").forward(request, response);
			} catch (Exception e) {
			}
		}
		return mav;
	}

	@RequestMapping(value = "/updateConv", method = RequestMethod.POST)
	public @ResponseBody ConversionMaster updateConv(@RequestParam("id") int strConvId,
			@RequestParam("conv") String strConversion, HttpServletRequest request, HttpServletResponse response) {
		logger.debug("Conversion Brand ID	 :" + strConvId);
		logger.debug("New Conversion 		:" + strConversion);
		ConversionMaster convObj = null;
		// Role wise Restricting the user to access the url
		int role = (Integer) request.getSession().getAttribute("userRole1");
		if (role == 1) {
			int result = 0;

			HttpSession session = request.getSession(true);
			String ipAddress = (String) session.getAttribute("ipAddress");
			result = adminService.updateConv(strConvId, strConversion, "", ipAddress);
			if (result == 1) {
				// DB Updated Succesfully so send this Conversion Data to Update Table Cell
				convObj = adminService.getConvById(strConvId);
			}
		} else {
			try {
				request.getRequestDispatcher("/views/login.jsp").forward(request, response);
			} catch (Exception e) {
			}
		}
		return convObj;
	}

	@RequestMapping(value = "/updateSat", method = RequestMethod.POST)
	public @ResponseBody SaturationMaster updateSat(@RequestParam("id") int strId,
			@RequestParam("newSat") String strSat, HttpServletRequest request, HttpServletResponse response) {

		SaturationMaster satObj = null;
		// Role wise Restricting the user to access the url
		int role = (Integer) request.getSession().getAttribute("userRole1");
		if (role == 1) {
			int result = 0;
			try {
				HttpSession session = request.getSession(true);
				String ipAddress = (String) session.getAttribute("ipAddress");
				result = adminService.updateSat(strId, strSat, ipAddress);
				if (result == 1) {
					// DB Updated Succesfully so send this updated Saturation Data to Update Table
					// Cell
					satObj = adminService.getSatById(strId);
				}

			} catch (ERPException erp) {
				/*
				 * logger.error("Problem Occured while updating saturation "+erp.getMessage());
				 */ } catch (Exception e) {
				/* logger.error("Problem Occured while updating saturation "+e.getMessage()); */
			}
		} else {
			try {
				request.getRequestDispatcher("/views/login.jsp").forward(request, response);
			} catch (Exception e) {
			}
		}
		return satObj;
	}

	@RequestMapping(value = "/downloadConversion", method = RequestMethod.GET)
	public void downloadConversion(HttpServletRequest request, HttpServletResponse response, Model model) {
		// Role wise Restricting the user to access the url
		int role = (Integer) request.getSession().getAttribute("userRole1");
		if (role == 1) {
			String strFile = "ConversionMaster.csv";
			try {
				List<ConversionMaster> itemList = adminService.getAllConversion();
				if (itemList != null) {
					exportConversion(response, itemList, strFile);
				}

			} catch (ERPException erp) {
				/*
				 * logger.error("Problem Occured while downloading conversions "+erp.getMessage(
				 * ));
				 */
			} catch (Exception e) {
				/*
				 * logger.error("Problem Occured while downloading conversions "+e.getMessage())
				 * ;
				 */
			}
		} else {
			try {
				request.getRequestDispatcher("/views/login.jsp").forward(request, response);
			} catch (Exception e) {
			}
		}

	}

	public void exportConversion(HttpServletResponse response, List<ConversionMaster> itemList, String strFile) {
		ServletOutputStream outStream = null;
		try {
			ArrayList<String> rows = new ArrayList<String>();
			rows.add("Brand,Conversion Percent, Average Sales Per Promotor, Last Updated Date, Last Updated By");
			rows.add("\n");
			Iterator<ConversionMaster> iter = itemList.iterator();
			while (iter.hasNext()) {
				ConversionMaster item = (ConversionMaster) iter.next();
				rows.add(item.getBrandname() + "," + item.getConpercent() + "," + item.getAvgsales() + ","
						+ item.getLastUpdated() + "," + item.getUpdatedBy());

				rows.add("\n");
			}
			outStream = response.getOutputStream();
			response.setContentType("text/csv");
			response.setHeader("Content-disposition", "attachment;filename=" + strFile);
			Iterator<String> itr = rows.iterator();
			while (itr.hasNext()) {
				String outputString = (String) itr.next();
				outStream.print(outputString);
			}

		} catch (IOException ie) {
			/* logger.error("Error while downloading the Conversions "+ie.getMessage()); */
		} catch (Exception e) {
			/* logger.error("Error while downloading the Conversions "+e.getMessage()); */
		} finally {
			try {
				if (outStream != null) {
					outStream.flush();
					outStream.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	@RequestMapping(value = "/downloadSaturation", method = RequestMethod.GET)
	public void downloadSaturation(HttpServletRequest request, HttpServletResponse response, Model model) {
		// Role wise Restricting the user to access the url
		int role = (Integer) request.getSession().getAttribute("userRole1");
		if (role == 1) {

			try {
				String strFile = "SaturationMaster.csv";
				List<SaturationMaster> itemList = adminService.getAllSaturation();
				if (itemList != null) {
					exportSaturation(response, itemList, strFile);
				}

			} catch (ERPException erp) {
				/*
				 * logger.error("Some Problem Occured while downloading Saturaions "+erp.
				 * getMessage());
				 */
			} catch (Exception e) {
				/*
				 * logger.error("Some Problem Occured while downloading Saturaions "+e.
				 * getMessage());
				 */
			}
		} else {
			try {
				request.getRequestDispatcher("/views/login.jsp").forward(request, response);
			} catch (Exception e) {
			}
		}
	}

	public void exportSaturation(HttpServletResponse response, List<SaturationMaster> itemList, String strFile) {
		ServletOutputStream outStream = null;
		try {
			ArrayList<String> rows = new ArrayList<String>();
			rows.add("Brand,State,City,Saturation Percent, Last Updated Date, Updated By");
			rows.add("\n");
			Iterator<SaturationMaster> iter = itemList.iterator();
			while (iter.hasNext()) {
				SaturationMaster item = (SaturationMaster) iter.next();
				rows.add(item.getBrand() + "," + item.getState() + "," + item.getCity() + "," + item.getSatpercent()
						+ "," + item.getLastUpdated() + "," + item.getUpdatedBy());

				rows.add("\n");
			}
			outStream = response.getOutputStream();
			response.setContentType("text/csv");
			response.setHeader("Content-disposition", "attachment;filename=" + strFile);
			Iterator<String> itr = rows.iterator();
			while (itr.hasNext()) {
				String outputString = (String) itr.next();
				outStream.print(outputString);
			}

		} catch (IOException ie) {
			/*
			 * logger.error("Some Problem Occured while downloading Saturaions "+ie.
			 * getMessage());
			 */
		} catch (Exception e) {
			/*
			 * logger.error("Some Problem Occured while downloading Saturaions "+e.
			 * getMessage());
			 */
		} finally {
			try {
				if (outStream != null) {
					outStream.flush();
					outStream.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	@RequestMapping(value = "/agencymaster")
	public ModelAndView showAgencyMaster(HttpServletRequest request, HttpServletResponse response, ModelMap model) {

		ModelAndView mav = new ModelAndView("ShowAgencyMaster");
		String statusMsg = null;
		// Role wise Restricting the user to access the url
		int role = (Integer) request.getSession().getAttribute("userRole1");
		if (role == 1) {
			try {
				model.addAttribute("agency", new AgencyMaster());
				mav.addObject("stateList", adminService.getStateList());
				mav.addObject("agencyList", adminService.getAgencyNames());
			} catch (ERPException erp) {
				statusMsg = "Problem Occured in Agency Master";
				request.setAttribute("strStatus", statusMsg);
				/* logger.error("Problem Occured in Agency Master"+erp.getMessage()); */
			} catch (Exception e) {
				statusMsg = "Problem Occured in Agency Master";
				request.setAttribute("strStatus", statusMsg);
				/* logger.error("Problem Occured in Agency Master"+e.getMessage()); */
			}
		} else {
			try {
				request.getRequestDispatcher("/views/login.jsp").forward(request, response);
			} catch (Exception e) {
			}
		}
		return mav;
	}
	// creating the new agency

	@RequestMapping(value = "/saveNewAgency", method = RequestMethod.POST)
	public ModelAndView saveNewAgency(@ModelAttribute("agency") AgencyMaster agencyMaster, HttpServletRequest request,
			HttpServletResponse response) {
		String strReturn = "";

		agencyMaster.setIsActive(1);
		String statusMsg = null;
		// After saving the details or checking the details data will shown on jsp pages
		ModelAndView mav = new ModelAndView("ShowAgencyMaster");
		// Role wise Restricting the user to access the url
		int role = (Integer) request.getSession().getAttribute("userRole1");
		if (role == 1) {
			try {
				strReturn = adminService.persistAgency(agencyMaster);
				mav.addObject("agencyList", adminService.getAgencyNames());
				mav.addObject("stateList", adminService.getStateList());
				mav.addObject("strStatus", strReturn);

			} catch (ERPException erp) {
				statusMsg = "Problem Occured while saving new Agency";
				request.setAttribute("strStatus", statusMsg);
				/* logger.error("Problem Occured while saving new Agency"+erp.getMessage()); */
			} catch (Exception e) {
				statusMsg = "Problem Occured while saving new Agency";
				request.setAttribute("strStatus", statusMsg);
				/* logger.error("Problem Occured while saving new Agency"+e.getMessage()); */
			}

		} else {
			try {
				request.getRequestDispatcher("/views/login.jsp").forward(request, response);
			} catch (Exception e) {
			}
		}
		return mav;
	}

	@RequestMapping(value = "/checkAgency", method = RequestMethod.POST)
	public @ResponseBody int checkagency(@RequestParam("agencyname") String agencyname, HttpServletRequest req,
			HttpServletResponse response, Map<String, Object> model) {
		AgencyMaster agencyMaster = new AgencyMaster();

		// Role wise Restricting the user to access the url
		int role = (Integer) req.getSession().getAttribute("userRole1");
		if (role == 1) {
			try {
				if (agencyname != null) {
					agencyMaster = adminService.checkAgencyName(agencyname);
				}
			} catch (ERPException erp) {
				/* logger.error("Problem Occured while checking agency "+erp.getMessage()); */
			} catch (Exception e) {
				/* logger.error("Problem Occured while checking agency "+e.getMessage()); */
			}
		} else {
			try {
				req.getRequestDispatcher("/views/login.jsp").forward(req, response);
			} catch (Exception e) {
			}
		}
		if (agencyMaster != null) {
			return 1;
		} else {
			return 0;
		}

	}

	@RequestMapping(value = "/lockVendor", method = RequestMethod.POST)
	public @ResponseBody String lockVendor(@RequestParam("venId") int strId, HttpServletRequest request,
			HttpServletResponse responce, HttpServletResponse response) {
		logger.debug("Vendor Id:" + strId);
		String strStatus = null;
		// Role wise Restricting the user to access the url
		int role = (Integer) request.getSession().getAttribute("userRole1");
		if (role == 1) {
			try {
				int result = 0;
				result = adminService.LockVendor(strId);
				if (result == 1) {
					strStatus = "Vendor Blocked successfully";
				} else {
					strStatus = "Vendor Blocking is not sucessfull";
				}
			} catch (ERPException erp) {
				/* logger.error("Problem Occured while blocking vendor "+erp.getMessage()); */
			} catch (Exception e) {
				/* logger.error("Problem Occured while blocking vendor "+e.getMessage()); */
			}
		} else {
			try {
				request.getRequestDispatcher("/views/login.jsp").forward(request, response);
			} catch (Exception e) {
			}
		}
		return strStatus;

	}

	@RequestMapping(value = "/unlockVendor", method = RequestMethod.POST)
	public @ResponseBody String unlockVendor(@RequestParam("venId") int strId, HttpServletRequest request,
			HttpServletResponse responce) {
		logger.debug("Vendor Id:" + strId);
		String strStatus = null;
		// Role wise Restricting the user to access the url
		int role = (Integer) request.getSession().getAttribute("userRole1");
		if (role == 1) {
			try {
				int result = 0;
				result = adminService.unlockVendor(strId);
				if (result == 1) {
					strStatus = "Vendor UnBlocked  successfully";
				} else {
					strStatus = "Vendor UnBlock is not sucessfull";
				}
			} catch (ERPException erp) {
				/* logger.error("Problem Occured while unblocking vendor "+erp.getMessage()); */
			} catch (Exception e) {
				/* logger.error("Problem Occured while unblocking vendor "+e.getMessage()); */
			}
		} else {
			try {
				request.getRequestDispatcher("/views/login.jsp").forward(request, responce);
			} catch (Exception e) {
			}
		}

		return strStatus;

	}

	@RequestMapping(value = "/checkVendor", method = RequestMethod.POST)
	public @ResponseBody int checkvendor(@RequestParam("vendorName") String vendorName, HttpServletRequest req,
			HttpServletResponse response, Map<String, Object> model) {
		logger.debug("checking Vendor by Vendor name :" + vendorName);
		VendorMaster vendorMaster = new VendorMaster();
		// Role wise Restricting the user to access the url
		int role = (Integer) req.getSession().getAttribute("userRole1");
		if (role == 1) {
			try {
				if (vendorName != null) {
					vendorMaster = adminService.checkVendorName(vendorName);
				}
			} catch (ERPException erp) {
				/* logger.error("Problem Occured while checking vendor "+erp.getMessage()); */
			} catch (Exception e) {
				/* logger.error("Problem Occured while checking vendor "+e.getMessage()); */
			}
		} else {
			try {
				req.getRequestDispatcher("/views/login.jsp").forward(req, response);
			} catch (Exception e) {
			}
		}
		if (vendorMaster != null) {
			logger.debug("vendorName already exists :" + vendorName);
			return 1;
		} else {
			logger.debug("vendorName does not exists :" + vendorName);
			return 0;
		}

	}

	@RequestMapping(value = "/lockAgency", method = RequestMethod.POST)
	public @ResponseBody String lockAgency(@RequestParam("agenId") int strId, HttpServletRequest request,
			HttpServletResponse responce) {
		logger.debug("Agency Id:" + strId);
		String strStatus = null;

		// Role wise Restricting the user to access the url
		int role = (Integer) request.getSession().getAttribute("userRole1");
		if (role == 1) {
			try {
				int result = 0;
				result = adminService.LockAgency(strId);
				if (result == 1) {
					strStatus = "Agency Blocked successfully";
				} else {
					strStatus = "Agency Blocking is not sucessfull";
				}
			} catch (ERPException erp) {
				/* logger.error("Problem Occured while blocking agency "+erp.getMessage()); */
			} catch (Exception e) {
				/* logger.error("Problem Occured while blocking agency "+e.getMessage()); */
			}
		} else {
			try {
				request.getRequestDispatcher("/views/login.jsp").forward(request, responce);
			} catch (Exception e) {
			}
		}

		return strStatus;
	}

	@RequestMapping(value = "/unlockAgency", method = RequestMethod.POST)
	public @ResponseBody String unlockAgency(@RequestParam("agenId") int strId, HttpServletRequest request,
			HttpServletResponse responce) {
		String strStatus = null;
		// Role wise Restricting the user to access the url
		int role = (Integer) request.getSession().getAttribute("userRole1");
		if (role == 1) {
			try {
				int result = 0;
				result = adminService.unlockAgency(strId);
				if (result == 1) {
					strStatus = "Agency UnBlocked  successfully";
				} else {
					strStatus = "Agency UnBlocking is not sucessfull";
				}
			} catch (ERPException erp) {
				/* logger.error("Problem Occured while unblocking agency "+erp.getMessage()); */
			} catch (Exception e) {
				/* logger.error("Problem Occured while unblocking agency "+e.getMessage()); */
			}
		} else {
			try {
				request.getRequestDispatcher("/views/login.jsp").forward(request, responce);
			} catch (Exception e) {
			}
		}

		return strStatus;

	}

	@RequestMapping(value = "/lockUser", method = RequestMethod.POST)
	public @ResponseBody String lockUser(@RequestParam("userId") int strId, HttpServletRequest request,
			HttpServletResponse responce) {
		String strStatus = null;
		// Role wise Restricting the user to access the url
		int role = (Integer) request.getSession().getAttribute("userRole1");
		if (role == 1) {
			try {
				int result = 0;
				result = adminService.LockUser(strId);
				if (result == 1) {
					strStatus = "User Blocked successfully";
				} else {
					strStatus = "USer Blocking is not sucessfull";
				}
			} catch (ERPException erp) {
				/* logger.error("Problem Occured while blocking user "+erp.getMessage()); */
			} catch (Exception e) {
				/* logger.error("Problem Occured while blocking user "+e.getMessage()); */
			}
		} else {
			try {
				request.getRequestDispatcher("/views/login.jsp").forward(request, responce);
			} catch (Exception e) {
			}
		}
		return strStatus;
	}

	@RequestMapping(value = "/unlockUser", method = RequestMethod.POST)
	public @ResponseBody String unlockUser(@RequestParam("userId") int strId, HttpServletRequest request,
			HttpServletResponse responce) {
		String strStatus = null;
		// Role wise Restricting the user to access the url
		int role = (Integer) request.getSession().getAttribute("userRole1");
		if (role == 1) {
			try {
				int result = 0;
				result = adminService.unlockUser(strId);
				if (result == 1) {
					strStatus = "User UnBlocked  successfully";
				} else {
					strStatus = "User UnBLocking is not sucessfull";
				}
			} catch (ERPException erp) {
				/* logger.error("Problem Occured while unblocking user "+erp.getMessage()); */
			} catch (Exception e) {
				/* logger.error("Problem Occured while unblocking user "+e.getMessage()); */
			}

		} else {
			try {
				request.getRequestDispatcher("/views/login.jsp").forward(request, responce);
			} catch (Exception e) {
			}
		}
		return strStatus;

	}

	@RequestMapping(value = "/vendormaster")
	public ModelAndView showVendorMaster(HttpServletRequest request, HttpServletResponse response, ModelMap model) {

		ModelAndView mav = new ModelAndView("showVendorMaster");
		String statusMsg = null;
		// Role wise Restricting the user to access the url
		int role = (Integer) request.getSession().getAttribute("userRole1");
		if (role == 1) {
			try {
				model.addAttribute("vendor", new VendorMaster());
				mav.addObject("vendorList", adminService.getVendorMaster());
			} catch (ERPException erp) {
				statusMsg = "Problem Occured in loading vendor. Please try Again";
				request.setAttribute("strStatus", statusMsg);
			} catch (Exception e) {
				statusMsg = "Problem Occured in loading vendor. Please try Again";
				request.setAttribute("strStatus", statusMsg);
			}
			// mav.addObject("agencyList", adminService.getAgencyNames());
			try {
				mav.addObject("agencyList", adminService.getAgenciesList());
			} catch (ERPException erp) {
				statusMsg = "Problem Occured in loading agencies. Please try Again";
				request.setAttribute("strStatus", statusMsg);
			} catch (Exception e) {
				statusMsg = "Problem Occured in loading agencies. Please try Again";
				request.setAttribute("strStatus", statusMsg);
			}
			try {
				mav.addObject("stateList", adminService.getStateList());
			} catch (ERPException erp) {
				statusMsg = "Problem Occured in loading states. Please try Again";
				request.setAttribute("strStatus", statusMsg);
			} catch (Exception e) {
				statusMsg = "Problem Occured in loading states. Please try Again";
				request.setAttribute("strStatus", statusMsg);
			}
		} else {
			try {
				request.getRequestDispatcher("/views/login.jsp").forward(request, response);
			} catch (Exception e) {
			}
		}
		return mav;
	}

	@RequestMapping(value = "/saveNewVendor", method = RequestMethod.POST)
	public ModelAndView saveNewVendor(@ModelAttribute("vendor") VendorMaster vendorMaster, HttpServletRequest request,
			HttpServletResponse response) {

		logger.info("Inside AdminController  ::  saveNewVendor");
		String strReturn = "";
		vendorMaster.setIsActive(1);

		String statusMsg = null;
		ModelAndView mav = new ModelAndView("showVendorMaster");
		// Role wise Restricting the user to access the url
		int role = (Integer) request.getSession().getAttribute("userRole1");
		if (role == 1) {
			try {
				strReturn = adminService.persistVendor(vendorMaster);
				mav.addObject("vendorList", adminService.getVendorMaster());
				// mav.addObject("agencyList", adminService.getAgencyNames());
				mav.addObject("agencyList", adminService.getAgenciesList());
				mav.addObject("stateList", adminService.getStateList());
				mav.addObject("strStatus", strReturn);
			} catch (ERPException erp) {
				statusMsg = "Problem Occured while saving new vendor. Please try Again";
				request.setAttribute("strStatus", statusMsg);
			} catch (Exception e) {
				statusMsg = "Problem Occured while saving new vendor Please try Again";
				request.setAttribute("strStatus", statusMsg);
			}
		} else {
			try {
				request.getRequestDispatcher("/views/login.jsp").forward(request, response);
			} catch (Exception e) {
			}
		}
		return mav;

	}

	@RequestMapping(value = "/getCityState", method = RequestMethod.POST)
	public @ResponseBody Map<Integer, String> getCityState(@RequestParam("state") Integer intStateId,
			HttpServletRequest request, Model model, HttpServletResponse response) {
		List<CityMaster> listCity = new ArrayList<CityMaster>();
		Map<Integer, String> cityMap = new HashMap<Integer, String>();

		// Role wise Restricting the user to access the url
		int role = (Integer) request.getSession().getAttribute("userRole1");
		if (role == 1) {
			try {
				listCity = adminService.getCityofState(intStateId);
				cityMap = new HashMap<Integer, String>();
				Iterator<CityMaster> cityItr = listCity.iterator();
				CityMaster cityMaster = null;
				while (cityItr.hasNext()) {
					cityMaster = new CityMaster();
					cityMaster = cityItr.next();
					cityMap.put(cityMaster.getCityId(), cityMaster.getCityName());
				}
			} catch (ERPException erp) {
				/*
				 * logger.error("Problem Occured while loading cities. Please try Again "+erp.
				 * getMessage());
				 */
			} catch (Exception e) {
				/*
				 * logger.error("Problem Occured while loading cities. Please try Again "+e.
				 * getMessage());
				 */
			}
		} else {
			try {
				request.getRequestDispatcher("/views/login.jsp").forward(request, response);
			} catch (Exception e) {
			}
		}

		return cityMap;

	}

	@RequestMapping(value = "/getVendorofAgency", method = RequestMethod.POST)
	public @ResponseBody Map<Integer, String> getVendorofAgency(@RequestParam("agency") Integer agencyId,
			HttpServletRequest req, Model model, HttpServletResponse response) {
		Map<Integer, String> vendorMap = new HashMap<Integer, String>();
		List<VendorMaster> listVendor = new ArrayList<VendorMaster>();

		// Role wise Restricting the user to access the url
		int role = (Integer) req.getSession().getAttribute("userRole1");
		if (role == 1) {
			try {
				listVendor = adminService.getVendorList(agencyId);
				Iterator<VendorMaster> vendorItr = listVendor.iterator();
				VendorMaster vendorMaster = null;
				while (vendorItr.hasNext()) {
					vendorMaster = new VendorMaster();
					vendorMaster = vendorItr.next();
					vendorMap.put(vendorMaster.getId(), vendorMaster.getVendorName());
				}
			} catch (ERPException erp) {
				/*
				 * logger.error("Problem Occured while loading vendors. Please try Again "+erp.
				 * getMessage());
				 */
			} catch (Exception e) {
				/*
				 * logger.error("Problem Occured while loading vendors. Please try Again "+e.
				 * getMessage());
				 */
			}
		} else {
			try {
				req.getRequestDispatcher("/views/login.jsp").forward(req, response);
			} catch (Exception e) {
			}
		}
		return vendorMap;

	}

	@RequestMapping(value = "/getAgencyRowData", method = RequestMethod.POST)
	public @ResponseBody AgencyMaster getAgencyRowData(@RequestParam("id") int strId, HttpServletRequest request,
			HttpServletResponse response) {
		AgencyMaster agencyMaster = new AgencyMaster();

		// Role wise Restricting the user to access the url
		int role = (Integer) request.getSession().getAttribute("userRole1");
		if (role == 1) {
			try {
				agencyMaster = adminService.getAgencyById(strId);
			} catch (ERPException erp) {
				/*
				 * logger.error("Problem Occured while loading agency. Please try Again "+erp.
				 * getMessage());
				 */
			} catch (Exception e) {
				/*
				 * logger.error("Problem Occured while loading agency. Please try Again "+e.
				 * getMessage());
				 */
			}

		} else {
			try {
				request.getRequestDispatcher("/views/login.jsp").forward(request, response);
			} catch (Exception e) {
			}
		}
		return agencyMaster;
	}

	@RequestMapping(value = "/downloadAgencyMaster", method = RequestMethod.GET)
	public void downloadAgencyMaster(HttpServletRequest request, HttpServletResponse response) {
		logger.debug("downloadAgencyMaster Started............");
		String strFile = "AgencyMasterReport.csv";

		// Role wise Restricting the user to access the url
		int role = (Integer) request.getSession().getAttribute("userRole1");
		if (role == 1) {
			try {
				List<AgencyMaster> itemList = adminService.getAgencyMaster();

				exportAgencyMaster(response, itemList, strFile, request);

			} catch (ERPException erp) {
				/*
				 * logger.error("Problem Occured while downloading agencies. Please try Again "
				 * +erp.getMessage());
				 */
			} catch (Exception e) {
				/*
				 * logger.error("Problem Occured while downloading agencies. Please try Again "
				 * +e.getMessage());
				 */
			}
		} else {
			try {
				request.getRequestDispatcher("/views/login.jsp").forward(request, response);
			} catch (Exception e) {
			}
		}
	}

	@SuppressWarnings("unchecked")
	public void exportAgencyMaster(HttpServletResponse response, List<AgencyMaster> itemList, String strFile,
			HttpServletRequest req) {
		ServletOutputStream outStream = null;
		Map<Integer, String> stateMap = new HashMap<Integer, String>();
		Map<Integer, String> cityMap = new HashMap<Integer, String>();
		try {
			HttpSession session = req.getSession();
			stateMap = (Map<Integer, String>) session.getAttribute("stateMap");
			cityMap = (Map<Integer, String>) session.getAttribute("cityMap");
			ArrayList<String> rows = new ArrayList<String>();
			rows.add("Agency Name,State,City,IsActive");
			rows.add("\n");
			Iterator<AgencyMaster> iter = itemList.iterator();
			while (iter.hasNext()) {
				AgencyMaster item = (AgencyMaster) iter.next();
				rows.add(item.getAgencyname() + "," + stateMap.get(item.getStateId()) + ","
						+ cityMap.get(item.getCityId()) + "," + (item.getIsActive() == 1 ? "Active" : "DeActive"));
				rows.add("\n");
			}
			outStream = response.getOutputStream();
			response.setContentType("text/csv");
			response.setHeader("Content-disposition", "attachment;filename=" + strFile);
			Iterator<String> itr = rows.iterator();
			while (itr.hasNext()) {
				String outputString = (String) itr.next();
				outStream.print(outputString);
			}

		} catch (IOException ie) {
			ie.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (outStream != null) {
					outStream.flush();
					outStream.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	@RequestMapping(value = "/downloadVendorMaster", method = RequestMethod.GET)
	public void downloadVendorMaster(HttpServletRequest request, HttpServletResponse response, Model model) {
		String strFile = "VendorMasterReport.csv";

		// Role wise Restricting the user to access the url
		int role = (Integer) request.getSession().getAttribute("userRole1");
		if (role == 1) {
			List<VendorMaster> itemList = adminService.getVendorMaster();

			exportVendorMaster(response, itemList, strFile, request);
		} else {
			try {
				request.getRequestDispatcher("/views/login.jsp").forward(request, response);
			} catch (Exception e) {
			}
		}
	}

	public void exportVendorMaster(HttpServletResponse response, List<VendorMaster> itemList, String strFile,
			HttpServletRequest req) {
		ServletOutputStream outStream = null;
		Map<Integer, String> stateMap = new HashMap<Integer, String>();
		Map<Integer, String> cityMap = new HashMap<Integer, String>();
		Map<Integer, String> agencyMap = new HashMap<Integer, String>();
		try {
			HttpSession session = req.getSession();
			stateMap = (Map<Integer, String>) session.getAttribute("stateMap");
			cityMap = (Map<Integer, String>) session.getAttribute("cityMap");
			agencyMap = (Map<Integer, String>) session.getAttribute("agencyMap");
			ArrayList<String> rows = new ArrayList<String>();
			rows.add("Vendor Name,Agency Name,State,City,IsAcitive");
			rows.add("\n");

			Iterator<VendorMaster> iter = itemList.iterator();
			while (iter.hasNext()) {
				VendorMaster item = (VendorMaster) iter.next();
				rows.add(item.getVendorName() + "," + agencyMap.get(item.getAgencyId()) + ","
						+ stateMap.get(item.getState()) + "," + cityMap.get(item.getCity()) + ","
						+ (item.getIsActive() == 1 ? "Active" : "DeActive"));

				rows.add("\n");
			}
			outStream = response.getOutputStream();
			response.setContentType("text/csv");
			response.setHeader("Content-disposition", "attachment;filename=" + strFile);
			Iterator<String> itr = rows.iterator();
			while (itr.hasNext()) {
				String outputString = (String) itr.next();
				outStream.print(outputString);
			}

		} catch (IOException ie) {
			ie.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (outStream != null) {
					outStream.flush();
					outStream.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	@RequestMapping(value = "/getVendorRowData", method = RequestMethod.POST)
	public @ResponseBody VendorMaster getVendorRowData(@RequestParam("id") int strId, HttpServletRequest request,
			HttpServletResponse response) {
		VendorMaster vendorMaster = new VendorMaster();

		// Role wise Restricting the user to access the url
		int role = (Integer) request.getSession().getAttribute("userRole1");
		if (role == 1) {
			try {
				vendorMaster = adminService.getVendorById(strId);
			} catch (ERPException erp) {
				/* logger.error("Problem in loading vendor"+erp.getMessage()); */
			} catch (Exception e) {
				/* logger.error("Problem in loading vendor"+e.getMessage()); */
			}
		} else {
			try {
				request.getRequestDispatcher("/views/login.jsp").forward(request, response);
			} catch (Exception e) {
			}
		}
		return vendorMaster;
	}

	@RequestMapping(value = "/createUserForm")
	public ModelAndView createUserForm(ModelMap map, HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("createUser");
		mav.addObject("roleMap", adminService.getRoleId());

		HttpSession session = request.getSession();
		session.removeAttribute("brandList");
		String statusMsg = null;

		// Role wise Restricting the user to access the url
		int role = (Integer) request.getSession().getAttribute("userRole1");
		if (role == 1) {
			try {
				map.addAttribute("user", new UserMaster());
				mav.addObject("agencyList", adminService.getAgenciesList());
			} catch (ERPException erp) {
				statusMsg = "Problem Occured while getting agency";
				request.setAttribute("strStatus", statusMsg);
			} catch (Exception e) {
				statusMsg = "Problem Occured. Please try Again";
				request.setAttribute("strStatus", statusMsg);
			}
			try {
				mav.addObject("stateList", adminService.getStateList());

			} catch (ERPException erp) {
				statusMsg = "Problem Occured while getting state";
				request.setAttribute("strStatus", statusMsg);
			} catch (Exception e) {
				statusMsg = "Problem Occured while getting state";
				request.setAttribute("strStatus", statusMsg);
			}
			try {
				mav.addObject("brandList", adminService.getBrandList());
			} catch (ERPException erp) {
				statusMsg = "Problem Occured while getting brand";
				request.setAttribute("strStatus", statusMsg);
			} catch (Exception e) {
				statusMsg = "Problem Occured while getting brand";
				request.setAttribute("strStatus", statusMsg);
			}
		} else {
			try {
				request.getRequestDispatcher("/views/login.jsp").forward(request, response);
			} catch (Exception e) {
			}
		}
		return mav;
	}

	@RequestMapping(value = "/usersmaster")
	public ModelAndView showUsersMaster(ModelMap model, HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = null;
		String statusMsg = null;
		// Role wise Restricting the user to access the url
		int role = (Integer) request.getSession().getAttribute("userRole1");
		if (role == 1) {
			try {
				mav = new ModelAndView("showUserMaster");
				model.addAttribute("user", new UserMaster());
				mav.addObject("userList", adminService.getUserMaster());
			} catch (ERPException erp) {
				statusMsg = "Problem Occured while loading the users";
				/* logger.error("Problem Occured while loading the users"+erp.getMessage()); */
				request.setAttribute("strStatus", statusMsg);
			} catch (Exception e) {
				/* logger.error("Problem Occured while loading the users"+e.getMessage()); */
				statusMsg = "Problem Occured while loading the users";
				request.setAttribute("strStatus", statusMsg);
			}
		} else {
			try {
				request.getRequestDispatcher("/views/login.jsp").forward(request, response);
			} catch (Exception e) {
			}
		}
		return mav;

	}

	@RequestMapping(value = "/edituser", method = RequestMethod.POST)
	public ModelAndView edituser(@RequestParam("id") int strId, HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		logger.info("=========== showUsersMaster============");
		ModelAndView mav = new ModelAndView("editUserDetails");
		String statusMsg = null;
		// Role wise Restricting the user to access the url
		int role = (Integer) request.getSession().getAttribute("userRole1");
		if (role == 1) {
			try {
				UserMaster user = adminService.getUserById(strId);
				mav.addObject("userDetails", user);
			} catch (ERPException erp) {
				statusMsg = "Problem Occured while getting the user. Please try Again";
				request.setAttribute("strStatus", statusMsg);
			} catch (Exception e) {
				statusMsg = "Problem Occured while getting the user. Please try Again";
				request.setAttribute("strStatus", statusMsg);
			}
			try {
				mav.addObject("agencyList", adminService.getAgenciesList());
			} catch (ERPException erp) {
				statusMsg = "Problem Occured while getting the Agencies. Please try Again";
				request.setAttribute("strStatus", statusMsg);
			} catch (Exception e) {
				statusMsg = "Problem Occured while getting the Agencies. Please try Again";
				request.setAttribute("strStatus", statusMsg);
			}
			try {
				mav.addObject("vendorList", adminService.getVendorNameActives());
			} catch (ERPException erp) {
				statusMsg = "Problem Occured while getting the vendor. Please try Again";
				request.setAttribute("strStatus", statusMsg);
			} catch (Exception e) {
				statusMsg = "Problem Occured while getting the vendor. Please try Again";
				request.setAttribute("strStatus", statusMsg);
			}
			try {
				mav.addObject("brandList", adminService.getBrandList());

			} catch (ERPException erp) {
				statusMsg = "Problem Occured while getting the brand. Please try Again";
				request.setAttribute("strStatus", statusMsg);
			} catch (Exception e) {
				statusMsg = "Problem Occured while getting the brand. Please try Again";
				request.setAttribute("strStatus", statusMsg);
			}
		} else {
			try {
				request.getRequestDispatcher("/views/login.jsp").forward(request, response);
			} catch (Exception e) {
			}
		}
		return mav;

	}

	@RequestMapping(value = "/UpdateUserDetails", method = RequestMethod.POST)
	public ModelAndView UpdateUserDetails(HttpServletRequest request, ModelMap map, HttpServletResponse response) {

		logger.info("Inside AdminController  ::   UpdateUserDeatils");
		ModelAndView mav = null;
		String statusMsg = null;

		// Role wise Restricting the user to access the url
		int role1 = (Integer) request.getSession().getAttribute("userRole1");
		if (role1 == 1) {
			try {
				int strId = request.getParameter("Id") == null ? 0 : Integer.parseInt(request.getParameter("Id"));
				String strpassword = request.getParameter("password") == null ? "" : request.getParameter("password");
				String strEmailId = request.getParameter("email") == null ? "" : request.getParameter("email");
				String strContact = request.getParameter("contact") == null ? "" : request.getParameter("contact");
				int role = request.getParameter("role") == null ? 0 : Integer.parseInt(request.getParameter("role"));
				String username = request.getParameter("username") == null ? "" : request.getParameter("username");
				String firstname = request.getParameter("firstname") == null ? "" : request.getParameter("firstname");
				String lastname = request.getParameter("lastname") == null ? "" : request.getParameter("lastname");
				String active = request.getParameter("active") == null ? "" : request.getParameter("active");

				UserMaster user = new UserMaster();
				user.setId(strId);
				user.setPassword(strpassword);
				user.setEmail(strEmailId);
				user.setContactNum(strContact);
				user.setRoleId(role);
				user.setUsername(username);
				user.setFirstName(firstname);
				user.setLastName(lastname);
				user.setIsActive(active);
				user.setMobileAccess("No");

				if (role == 5) {
					Integer strBrand = request.getParameter("brand") == null ? 0
							: Integer.parseInt(request.getParameter("brand"));
					user.setBrandId(strBrand);
				} else if (role == 4) {
					String strMobileAcess = request.getParameter("mobileaccess") == null ? "No"
							: request.getParameter("mobileaccess");
					user.setMobileAccess(strMobileAcess);
				}
				mav = new ModelAndView("editUserDetails");
				mav.addObject("agencyList", adminService.getAgenciesList());
				mav.addObject("vendorList", adminService.getVendorNameActives());

				UserMaster userobj = null;
				String strStatus = null;
				int result = 0;
				try {
					result = adminService.updateUser(user);
				} catch (Exception e) {
					strStatus = "Problem in User updation";
				}
				userobj = new UserMaster();
				strStatus = "User Updated Successfully";
				userobj = adminService.getUserById(strId);
				mav.addObject("userDetails", userobj);
				mav.addObject("strStatus", strStatus);
			} catch (ERPException erp) {
				statusMsg = "Problem Occured while updating the user";
				/* logger.error("Problem Occured while updating the user"+erp.getMessage()); */
				request.setAttribute("strStatus", statusMsg);
			} catch (Exception e) {
				/* logger.error("Problem Occured while updating the user"+e.getMessage()); */
				statusMsg = "Problem Occured while updating the user";
				request.setAttribute("strStatus", statusMsg);
			}
		} else {
			try {
				request.getRequestDispatcher("/views/login.jsp").forward(request, response);
			} catch (Exception e) {
			}
		}
		return mav;

	}

	@RequestMapping(value = "/checkUsername", method = RequestMethod.POST)
	public @ResponseBody int checkuser(@RequestParam("username") String username, HttpServletRequest req,
			HttpServletResponse response, Map<String, Object> model) {
		UserMaster userMaster = new UserMaster();

		// Role wise Restricting the user to access the url
		int role = (Integer) req.getSession().getAttribute("userRole1");
		if (role == 1) {
			if (username != null)
				try {
					userMaster = adminService.checkUser(username);
				} catch (ERPException erp) {
					/* logger.error("Problem Occured while checking the user"+erp.getMessage()); */
				} catch (Exception e) {
					/* logger.error("Problem Occured while checking the user"+e.getMessage()); */
				}
		} else {
			try {
				req.getRequestDispatcher("/views/login.jsp").forward(req, response);
			} catch (Exception e) {
			}
		}
		if (userMaster != null) {
			return 1;
		} else {
			return 0;
		}
	}

	@RequestMapping(value = "/saveNewUser", method = RequestMethod.POST)
	public ModelAndView saveNewUser(@ModelAttribute("user") UserMaster userMaster,
			@RequestParam(required = false, defaultValue = "No", value = "mobileaccess") String strMobileAcess,
			@RequestParam(required = false, defaultValue = "0", value = "brand") String brand,
			HttpServletRequest request, ModelMap map, HttpServletResponse response) {
		boolean isInserted = false;
		userMaster.setMobileAccess(strMobileAcess);
		userMaster.setIsActive("1");
		userMaster.setCounterFlag(0);
		userMaster.setBrandId(Integer.parseInt(brand));

		ModelAndView mav = new ModelAndView("createUser");
		String strStatus = null;
		// Role wise Restricting the user to access the url
		int role = (Integer) request.getSession().getAttribute("userRole1");
		if (role == 1) {

			try {
				isInserted = adminService.persistUserRole(userMaster);
				request.getSession().setAttribute("userList", adminService.getUserMaster());
				mav.addObject("roleMap", adminService.getRoleId());
				mav.addObject("stateList", adminService.getStateList());
				mav.addObject("agencyList", adminService.getAgencyMaster());
				if (userMaster.getRoleId() == 1) {
					if (isInserted) {
						strStatus = "New User Created Successfully";
						map.addAttribute("user", new UserMaster());
						mav.addObject("strStatus", strStatus);
					} else {
						strStatus = "We can't Create more than one Admin";
						mav.addObject("strStatus", strStatus);
					}
				} else {
					if (isInserted) {
						strStatus = "New User Created Successfully";
						map.addAttribute("user", new UserMaster());
						mav.addObject("strStatus", strStatus);
					} else {
						strStatus = "Problem in User Creation";
						mav.addObject("strStatus", strStatus);
					}

				}
			} catch (ERPException erp) {
				/* logger.error("Problem Occured while saving the user"+erp.getMessage()); */
			} catch (Exception e) {
				/* logger.error("Problem Occured while saving the user"+e.getMessage()); */
			}
		} else {
			try {
				request.getRequestDispatcher("/views/login.jsp").forward(request, response);
			} catch (Exception e) {
			}
		}
		return mav;

	}

	@RequestMapping(value = "/getUserRowData", method = RequestMethod.POST)
	public @ResponseBody UserMaster getUserRowData(@RequestParam("id") int strId, HttpServletRequest request,
			HttpServletResponse response) {
		UserMaster userMaster = new UserMaster();

		// Role wise Restricting the user to access the url
		int role = (Integer) request.getSession().getAttribute("userRole1");
		if (role == 1) {
			try {
				userMaster = adminService.getUserById(strId);
			} catch (ERPException erp) {
				/* logger.error("Problem Occured while loading the user"+erp.getMessage()); */
			} catch (Exception e) {
				/* logger.error("Problem Occured while loading the user"+e.getMessage()); */
			}
		} else {
			try {
				request.getRequestDispatcher("/views/login.jsp").forward(request, response);
			} catch (Exception e) {
			}
		}

		return userMaster;

	}

	@RequestMapping(value = "/downloadUserMaster", method = RequestMethod.GET)
	public void downloadUserMaster(HttpServletRequest request, HttpServletResponse response, Model model) {
		String strFile = "UserMasterReport.csv";

		// Role wise Restricting the user to access the url
		int role = (Integer) request.getSession().getAttribute("userRole1");
		if (role == 1) {
			try {
				List<UserMaster> userList = adminService.getUserMaster();
				exportUserMaster(response, userList, strFile, request);
			} catch (ERPException erp) {
				/*
				 * logger.error("Problem Occured while downloading the users"+erp.getMessage());
				 */
			} catch (Exception e) {
				/*
				 * logger.error("Problem Occured while downloading the users"+e.getMessage());
				 */
			}
		} else {
			try {
				request.getRequestDispatcher("/views/login.jsp").forward(request, response);
			} catch (Exception e) {
			}
		}

	}

	public void exportUserMaster(HttpServletResponse response, List<UserMaster> itemList, String strFile,
			HttpServletRequest req) {
		ServletOutputStream outStream = null;
		Map<Integer, String> stateMap = new HashMap<Integer, String>();
		Map<Integer, String> cityMap = new HashMap<Integer, String>();
		Map<Integer, String> agencyMap = new HashMap<Integer, String>();
		Map<Integer, String> roleMap = new HashMap<Integer, String>();
		/*
		 * Map<Integer, String> brandMap=new HashMap<Integer, String>();
		 */ Map<Integer, String> vendorMap = new HashMap<Integer, String>();
		try {
			HttpSession session = req.getSession();
			stateMap = (Map<Integer, String>) session.getAttribute("stateMap");
			cityMap = (Map<Integer, String>) session.getAttribute("cityMap");
			agencyMap = (Map<Integer, String>) session.getAttribute("agencyMap");
			roleMap = (Map<Integer, String>) session.getAttribute("rolesMap");
			agencyMap = (Map<Integer, String>) session.getAttribute("agencyMap");
			vendorMap = (Map<Integer, String>) session.getAttribute("vendorMap");
			/*
			 * brandMap = (Map<Integer, String>)session.getAttribute("brandMap");
			 */

			ArrayList<String> rows = new ArrayList<String>();
			rows.add("Username,FirstName,LastName,Email,Contact Number, Role, AgencyName, VendorName,"
					+ " hasMobileAccess, isActive, State, City");
			rows.add("\n");

			Iterator<UserMaster> iter = itemList.iterator();
			while (iter.hasNext()) {
				UserMaster item = (UserMaster) iter.next();
				rows.add(item.getUsername() + "," + item.getFirstName() + "," + item.getLastName() + ","
						+ item.getEmail() + "," + item.getContactNum() + "," + roleMap.get(item.getRoleId()) + ","
						+ agencyMap.get(item.getAgencyId()) + "," + vendorMap.get(item.getVendorId()) + ","
						+ item.getMobileAccess() + ","/*
														 * +item.getIsActive()+","+item.getIsBlocked()+","+item.getState
														 * ()+"," +item.getCity()
														 */);
				if (item.getIsActive().equals("1")) {
					rows.add("Active" + ",");
				} else {
					rows.add("DeActive" + ",");
				}
				rows.add(stateMap.get(item.getState()) + "," + cityMap.get(item.getCity()));
				rows.add("\n");
			}
			outStream = response.getOutputStream();
			response.setContentType("text/csv");
			response.setHeader("Content-disposition", "attachment;filename=" + strFile);
			Iterator<String> itr = rows.iterator();
			while (itr.hasNext()) {
				String outputString = (String) itr.next();
				outStream.print(outputString);
			}

		} catch (IOException ie) {
			/*
			 * logger.error("Problem Occured while downloading the users"+ie.getMessage());
			 */
		} catch (Exception e) {
			/*
			 * logger.error("Problem Occured while downloading the users"+e.getMessage());
			 */
		} finally {
			try {
				if (outStream != null) {
					outStream.flush();
					outStream.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}// End of Class

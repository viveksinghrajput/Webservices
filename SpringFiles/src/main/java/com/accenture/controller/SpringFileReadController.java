package com.accenture.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.accenture.model.User;
import com.accenture.service.SpringFileReadService;

@Controller
public class SpringFileReadController {

	@Autowired
	private SpringFileReadService fileReadService;
	@Autowired
	private ServletContext context;

	@GetMapping(value = "/")
	public String Home(Model model) {

		model.addAttribute("user", new User());
		List<User> users = fileReadService.findAll();
		model.addAttribute("users", users);
		return "views/user";
	}

	/*
	 * @PostMapping(value = "/fileupload") public String
	 * FileUpload(@ModelAttribute("user") User user, RedirectAttributes
	 * redirectAttributes) { boolean isFalg =
	 * fileReadService.saveDataFromFileUpload(user.getFile()); if (isFalg) {
	 * redirectAttributes.addFlashAttribute("successmessage",
	 * "File Upload SuccessFully!"); } else {
	 * redirectAttributes.addFlashAttribute("errormessage",
	 * "File Upload not done ,Please try again !"); } return "redirect:/"; }
	 */

	@PostMapping("/import")
	public String mapReapExcelDatatoDB(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes)
			throws IOException {
		boolean isFalg = fileReadService.saveDataFromFileUpload(file);
		if (isFalg) {
			redirectAttributes.addFlashAttribute("successmessage", "File Upload SuccessFully!");
		} else {
			redirectAttributes.addFlashAttribute("errormessage", "File Upload not done ,Please try again !");
		}
		return "redirect:/";
	}

	@GetMapping("/downloadPdf")
	public void exportPdfData(HttpServletRequest request, HttpServletResponse response) {

		List<User> listuser = fileReadService.findAll();
		boolean isFlag = fileReadService.createPdf(listuser, context, request, response);
		if (isFlag) {
			String fullPath = request.getServletContext().getRealPath("/resources/reports/" + "user" + ".pdf");
			filedownload(fullPath, response, "user.pdf");
		}

	}
	@GetMapping("/downloadExcel")
	public void exportExcelData(HttpServletResponse response,HttpServletRequest request) {
		List<User> listuser=fileReadService.findAll();
		boolean isFlag=fileReadService.createExcel(listuser,context,request,response);
		if(isFlag) {
			String fullPath = context.getRealPath("/resources/reports/" + "user" + ".xls");//request.getServletContext().getRealPath("/resources/reports/" + "user" + ".xls");
			filedownload(fullPath,response,"user.xls");
		}
	}
	
	@GetMapping("/downloadJson")
	public void exportJsonData(HttpServletRequest request,HttpServletResponse response) {
		List<User> listuser=fileReadService.findAll();
		boolean isFlag=fileReadService.createJson(listuser,context);
		if(isFlag) {
			String fullPath = context.getRealPath("/resources/reports/" + "user.json");
			filedownload(fullPath,response,"user.json");
			
		}
		
	}
	@GetMapping("/downloadCsv")
	public void exportCsvData(HttpServletRequest request,HttpServletResponse response) {
		List<User> listuser=fileReadService.findAll();
		boolean isFlag=fileReadService.createCSV(listuser,context);
		if(isFlag) {
			String fullPath = context.getRealPath("/resources/reports/" + "user.csv");
			filedownload(fullPath,response,"user.csv");
			
		}

	}
	

	private void filedownload(String fullPath, HttpServletResponse response, String fileName) {
		File file = new File(fullPath);
		final int BUFFER_SIZE = 4096;
		if (file.exists()) {
			try {
				FileInputStream inputStream = new FileInputStream(file);
				String mineType = context.getMimeType(fullPath);
				response.setContentType(mineType);
				response.setHeader("content-disposition", "attachment;filename=" + fileName);
				OutputStream outputStream = response.getOutputStream();
				byte[] buffer = new byte[BUFFER_SIZE];
				int byteRead = -1;
				while ((byteRead = inputStream.read(buffer)) != -1) {
					outputStream.write(buffer, 0, byteRead);
				}
				inputStream.close();
				outputStream.close();
				file.delete();
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

	}

}

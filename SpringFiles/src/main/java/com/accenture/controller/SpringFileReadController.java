package com.accenture.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.accenture.model.User;
import com.accenture.service.SpringFileReadService;

@Controller
public class SpringFileReadController {

	@Autowired
	private SpringFileReadService fileReadService;

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
	public String mapReapExcelDatatoDB(@RequestParam("file") MultipartFile file,RedirectAttributes redirectAttributes) throws IOException {
		boolean isFalg =fileReadService.saveDataFromFileUpload(file);
		if (isFalg) {
			redirectAttributes.addFlashAttribute("successmessage", "File Upload SuccessFully!");
		} else {
			redirectAttributes.addFlashAttribute("errormessage", "File Upload not done ,Please try again !");
		}
		return "redirect:/";
	}
	
}

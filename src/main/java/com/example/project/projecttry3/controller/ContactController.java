package com.example.project.projecttry3.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.project.projecttry3.model.ContactMe;
import com.example.project.projecttry3.repository.ContactRepository;

@Controller
@RequestMapping("/user")
public class ContactController {
	
	@Autowired
	private ContactRepository cRepo;
	
	@GetMapping("/contactForm")
	public ModelAndView getContactForm() {
		ModelAndView mav = new ModelAndView("contactMe");
		ContactMe cInfo = new ContactMe();
		mav.addObject("cInfo", cInfo);
		return mav;
	}
	
	@PostMapping("/contactForm")
	public String submitForm(@ModelAttribute ContactMe cMe, Principal p, RedirectAttributes redirectAttributes) {
		String email = p.getName();
		cMe.setUser_email(email);
		try {
			cRepo.save(cMe);
			redirectAttributes.addFlashAttribute("errorMsg", "Sent Successfully!");
			return "redirect:/user/contactForm";
        } catch (DataIntegrityViolationException e) {
            redirectAttributes.addFlashAttribute("errorMsg", "Something Wrong!");
            return "redirect:/user/contactForm";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMsg", "Something went wrong!");
            return "redirect:/user/contactForm";
        }
	}
	
}

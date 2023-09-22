package com.example.project.projecttry3.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.project.projecttry3.model.UserDetails;
import com.example.project.projecttry3.service.UserService;


@Controller
public class ExpenseController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping({"/cover", "/", "", "/logout"})
	public String getCover() {
	    try {
	        return "cover";
	    } catch (Exception e) {
	        e.printStackTrace(); 
	        return "error";
	    }
	}
	
	@GetMapping("/signin")
	public String getLogin() {
	    try {
	        return "login";
	    } catch (Exception e) {
	        e.printStackTrace(); 
	        return "error";
	    }
	}
	
	@GetMapping("/register")
	public String getRegister() {
	    try {
	        return "register";
	    } catch (Exception e) {
	        e.printStackTrace();
	        return "error";
	    }
	}
	
	@PostMapping("/createUser")
	public String createUser(@ModelAttribute UserDetails user, Model model, RedirectAttributes redirectAttributes) {
	    try {
	        boolean b = userService.checkEmail(user.getEmail());
	        if (b) {
	            System.out.println("Email already exists!");
	            redirectAttributes.addFlashAttribute("errorMessage", "Email already exists!");
	            return "redirect:/register";
	        }

	        UserDetails userDtls = userService.createUser(user);
	        System.out.println("Registered successfully!");
	        return "login";
	    } catch (DataIntegrityViolationException e) {
	        redirectAttributes.addFlashAttribute("errorMessage", "Username already taken!");
	        return "redirect:/register";
	    } catch (Exception e) {
	        e.printStackTrace(); 
	        redirectAttributes.addFlashAttribute("errorMessage", "Something went wrong!");
	        return "redirect:/register";
	    }
	}
	
}

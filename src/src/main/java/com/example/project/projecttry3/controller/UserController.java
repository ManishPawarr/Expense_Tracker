//3.0.10
package com.example.project.projecttry3.controller;
import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.project.projecttry3.model.UserDetails;
import com.example.project.projecttry3.model.UserSpecific;
import com.example.project.projecttry3.repository.SpecificRepository;
import com.example.project.projecttry3.repository.UserRepository;
import com.example.project.projecttry3.service.UserSpecificService;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private SpecificRepository specRepo;
	
	@Autowired
	private UserSpecificService uService;
	
	@GetMapping("/about")
	public ModelAndView about() {
		ModelAndView mav = new ModelAndView("about");
		return mav;
	}
	
	@ModelAttribute
	private void userDetails(Model m, Principal p) {
	    try {
	        String email = p.getName();
	        UserDetails user = userRepo.findByEmail(email);
	        m.addAttribute("user", user);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}

	@GetMapping({"/", "", "/home"})
	public ModelAndView home(Principal p, Model m) {
	    ModelAndView mav = new ModelAndView("user/home");
	    UserSpecific uSpecific = new UserSpecific();
	    try {
	        String email = p.getName();
	        System.out.println("name : " + email);

	        long id = uSpecific.getUniqueId();
	        System.out.println("user id: " + id);

	        List<UserSpecific> us = specRepo.findByEmail(email);
	        mav.addObject("specific", us);

	        List<Long> list1 = specRepo.spent(email);
	        List<Long> list2 = specRepo.deposit(email);
	        Long profit = uService.calculateProfit(list1, list2);
	        m.addAttribute("profit", profit);

	        Long totalExpense = uService.calculateSpent(list1);
	        Long totalDeposit = uService.calculateDeposit(list2);
	        m.addAttribute("totalExpense", totalExpense);
	        m.addAttribute("totalDeposit", totalDeposit);
	    } catch (Exception e) {
	        e.printStackTrace(); 
	        ModelAndView errorMav = new ModelAndView("error");
	        return errorMav;
	    }
	    return mav;
	}

	
	@PostMapping("/addItems")
	public String addItems(@ModelAttribute UserSpecific uSpecific, Principal principal, Model m) {
	    try {
	        String name = principal.getName();
	        System.out.println("principal name: " + name);
	        UserDetails user = userRepo.findByEmail(name);

	        uSpecific.setUser(user);
	        uSpecific.setEmail(name);

	        String userName = user.getName();
	        uSpecific.setName(userName);

	        uSpecific.setUniqueId(user.getId());

	        user.getUserSpecific().add(uSpecific);

	        userRepo.save(user);
	    } catch (Exception e) {
	        e.printStackTrace();
	        return "redirect:/error";
	    }
	    
	    return "redirect:/user/";
	}
	
	
	@GetMapping("/deleteItems")
	public String deleteItem(@RequestParam Long uId) {
		specRepo.deleteById(uId);
		return "redirect:/user/";
	}

	
	@GetMapping("/editItem")
	public String editItem(@RequestParam Long uId, Model m) {
	    UserSpecific edit = specRepo.findById(uId).orElse(null);
	    if (edit != null) {
	        m.addAttribute("edit", edit);
	        return "user/editForm";
	    } else {
	        return "redirect:/user/";
	    }
	}

	@PostMapping("/updateItem")
	public String updateItem(@ModelAttribute UserSpecific updatedItem) {
	    try {
	    	UserSpecific existingItem = specRepo.findById(updatedItem.getuId()).orElse(null);
	    	if (existingItem != null) {
	 	        existingItem.setDescription(updatedItem.getDescription());
	 	        existingItem.setSpent(updatedItem.getSpent());
	 	        existingItem.setLastUpdate(updatedItem.getLastUpdate());

	 	        specRepo.save(existingItem);
	 	    }
	    } catch (Exception e) {
	    	e.printStackTrace();
	    	return "redirect:/error";
	    }
	    return "redirect:/user/";
	}
	

} 

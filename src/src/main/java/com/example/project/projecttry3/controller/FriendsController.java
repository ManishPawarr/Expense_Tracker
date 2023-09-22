package com.example.project.projecttry3.controller;

import java.security.Principal;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.project.projecttry3.model.FriendsCategory;
import com.example.project.projecttry3.model.UserDetails;
import com.example.project.projecttry3.repository.FriendsRepository;
import com.example.project.projecttry3.repository.UserRepository;
import com.example.project.projecttry3.service.FriendsService;

@RequestMapping("/user/category")
@Controller
public class FriendsController {
	
	@Autowired
	private FriendsRepository fRepo;
	
	@Autowired
	private FriendsService fService;
	
	@Autowired
	private UserRepository userRepo;

	@GetMapping("/friends")
	public ModelAndView getFriends(Principal p) {
		ModelAndView mav = new ModelAndView("friends/friendsCover");
		String email = p.getName();
		List<FriendsCategory> fC = fRepo.findByEmail(email);
		System.out.println("friends Email: "+email);
		mav.addObject("fc", fC);
		return mav;
	}

	@PostMapping("/addNewItem")
	public String addNewItem(@ModelAttribute FriendsCategory fCategory, Principal p, Model m) {
	    try {
	        String email = p.getName();
	        UserDetails user = userRepo.findByEmail(email);
	        fCategory.setUser(user);
	        fCategory.setEmail(email);
	        user.getfCategory().add(fCategory);
	        userRepo.save(user);
	        return "redirect:/user/category/friends";
	    } catch (Exception e) {
	        e.printStackTrace();
	        return "redirect:/error"; 
	    }
	}
	
	@GetMapping("/deleteItem")
	public String deleteItem(@RequestParam Long fId) {
		fRepo.deleteById(fId);
		return "redirect:/user/category/friends";
	}
	
	@GetMapping("/editItem")
	public String editItem(@RequestParam Long fId, Model m) {
		Optional<FriendsCategory> edit = fRepo.findById(fId);
		if(edit != null) {
			m.addAttribute("edit", edit);
			return "friends/editForm";
		} else {
			return "redirect:/user/category/friends";
		}
	}
	
	@PostMapping("/editItems")
	public String editItems(@ModelAttribute FriendsCategory fCategory) {
	    try {
	        FriendsCategory existingItem = fRepo.findById(fCategory.getfId()).orElse(null);
	        if (existingItem != null) {
	            existingItem.setName(fCategory.getName());
	            existingItem.setDescription(fCategory.getDescription());
	            existingItem.setAmount(fCategory.getAmount());
	            existingItem.setLastCreated(fCategory.getLastCreated());
	            fRepo.save(existingItem);
	        }
	        return "redirect:/user/category/friends";
	    } catch (Exception e) {
	        e.printStackTrace();
	        return "redirect:/error";
	    }
	}

	@GetMapping("/totalCalculation")
	public String totalCalculation(Principal p, Model m) {
		String email = p.getName();
		List<String> arr1 = fRepo.arr1(email);
		List<Integer> arr2 = fRepo.arr2(email);
		List<String> names = arr1;
        List<Integer> amounts = arr2;

        Map<String, Integer> personSums = fService.calculateSums(names, amounts);

        System.out.println("ans: "+personSums);
        m.addAttribute("fTotal", personSums);
		return "friends/submitAll";
	}
	
	@PostMapping("/totalCalculation")
	public String totalCal(Principal p, Model m) {
        return "friends/submitAll";
	}

	
	@GetMapping("/deleteAll")
    public String showDeletePage(@RequestParam(value = "email", required = false) String email, Model model) {
        model.addAttribute("email", email);
        return "/user/category/friends";
    }
    
    @PostMapping("/deleteAll")
    public String deleteDataByEmail(Principal p) {
        try {
            String email = p.getName();
            fService.deleteDataByEmail(email);
            return "redirect:/user/category/friends";
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/error";
        }
    }
}

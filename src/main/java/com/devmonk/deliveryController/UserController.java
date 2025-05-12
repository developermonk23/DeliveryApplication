package com.devmonk.deliveryController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.devmonk.deliveryService.DeliveryService;
import com.devmonk.model.User;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class UserController {
	
	@Autowired
	private DeliveryService deliveryService;
	
	// To populate login page
		@GetMapping("/login")
		public String login() {
		    return "login";
		}
	
	@GetMapping("/registration")
	public String getRegistrationPage(@ModelAttribute("user") User user) {
	    return "register";
	}

	// To save user details during registration
	@PostMapping("/registration")
	public String saveUser(@ModelAttribute("user") User user, 
	                       @RequestParam("role") String roleName, 
	                       Model model, 
	                       HttpServletRequest request) throws Exception {
	    
	    String siteURL = request.getRequestURL().toString();
	    deliveryService.saveUserWithRole(user, roleName, siteURL);
	    model.addAttribute("message", "Registered Successfully!");
	    return "register_success";
	}

}

package controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import dao.UserDao;
import entity.User;


@Controller
public class LoginController {

	
	@Autowired
	UserDao userDao;
	
	// This method will handle the login requests
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public void showLoginForm(Model model) {
		// Logic to display the login form
		model.addAttribute("objUser", new User()); 
//		return "login"; // This should match the view name in your JSP files, if not returning a view name then 
		                // it will return the default view which is the same as the request mapping
	}

	// This method will handle the login submission
	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public ModelAndView authenticateUser(@ModelAttribute("objUser")User objUser) {
		// Logic to process the login
//		if (isValidUser(username, password)) {
//			return "redirect:/home"; // Redirect to home page on success
//		} else {
//			return "login"; // Return to login page on failure
//		}

//		if(objUser != null && objUser.getUsername().equals("user1") && objUser.getPassword().equals("user1")) {
//			return new ModelAndView("welcome", "msg", "welcome to home page");
//		}else {
//			return new ModelAndView("failure", "msg", "invalid authentication");
//		}
		
        if (userDao.validateUser(objUser)) {
            return new ModelAndView("welcome", "msg", "Welcome, " + objUser.getUsername());
        } else {
            return new ModelAndView("failure", "msg", "Invalid authentication");
        }
		
	}

	private boolean isValidUser(String username, String password) {
		// Implement your user validation logic here
		return "admin".equals(username) && "password".equals(password);
	}
}
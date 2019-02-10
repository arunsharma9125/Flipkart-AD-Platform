package in.flipkart.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import in.flipkart.entity.User;
import in.flipkart.entity.User.Gender;
import in.flipkart.service.UserService;
import in.flipkart.util.SessionUtil;

@Controller
public class HomeController {
	
	@Autowired
	private UserService userService;
	
	

	@RequestMapping(value="/signup", method=RequestMethod.GET)
	public String signup(ModelMap model){
		model.addAttribute("genderList",Gender.values() );
		return "signup";
	}
	
	@RequestMapping(value="/signin", method=RequestMethod.GET)
	public String signin(ModelMap model){
		return "signin";
	}
	
	@RequestMapping(value="/signup", method=RequestMethod.POST)
	public String signup(
			@RequestParam("name") String name, 
			@RequestParam("email") String email,
			@RequestParam("password") String password,
			@RequestParam("confirmationPassword") String confirmationPassword,
			@RequestParam("gender") Gender gender,
			@RequestParam("age") Integer age){

		User user = new User(email, confirmationPassword, name, age, gender);
		userService.saveOrUpdate(user);
		Authentication authentication = new UsernamePasswordAuthenticationToken(user, user.getPassword(), user.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(authentication);
		return "redirect:/";
	}
	
	@RequestMapping(value="/signin", method=RequestMethod.POST)
	public String signin(
			@RequestParam("email") String email,
			@RequestParam("password") String password){

		User user = userService.getByEmail(email);
		Authentication authentication = new UsernamePasswordAuthenticationToken(user, user.getPassword(), user.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(authentication);
		return "redirect:/";
	}

	@RequestMapping(value={"/", "", "/index"}, method=RequestMethod.GET)
	public String index(ModelMap model){
		String message = "abc";
		User loggedInUser = SessionUtil.getLoggedInUser();
		model.addAttribute("loggedInUser", loggedInUser);
		model.addAttribute("message", message);
		return "index";
	}
}

package in.flipkart.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import in.flipkart.entity.User;
import in.flipkart.service.UserService;

@Controller
@RequestMapping("/admin/user")
public class UserController {

	@Autowired 
	private UserService userService;
	
	@RequestMapping(value="/list", method=RequestMethod.GET)
	public String listUsers(ModelMap model){
		List<User> userList= userService.listUser();
		model.addAttribute("userList", userList);
		return "admin/user/listUser";
	}
}

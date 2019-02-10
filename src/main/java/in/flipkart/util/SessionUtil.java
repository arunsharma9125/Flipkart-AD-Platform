package in.flipkart.util;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import in.flipkart.entity.User;

public class SessionUtil {

	public static boolean isUserAuthenticated(){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		boolean authenticated = auth != null && !(auth instanceof AnonymousAuthenticationToken) && auth.isAuthenticated();
		return authenticated;
	}
	
	public static boolean isUserAnonymous(){
		return !isUserAuthenticated();
	}
	
	public static User getLoggedInUser(){
		User user = null;
		if(isUserAuthenticated()){
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			user = (User) auth.getPrincipal();
		}
		return user;
	}
}

package in.flipkart.util;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import in.flipkart.entity.User;

public class SessionUtil {

	public static boolean isUserAnonymous(){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		boolean authenticated = auth != null && !(auth instanceof AnonymousAuthenticationToken) && auth.isAuthenticated();
		return authenticated;
	}
	
	public static boolean isUserAuthenticated(){
		return !isUserAnonymous();
	}
	
	public static User getLoggedInUser(){
		User user = null;
		if(isUserAuthenticated()){
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			auth.getPrincipal();
		}
		return user;
	}
}

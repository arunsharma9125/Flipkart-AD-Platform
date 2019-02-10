package in.flipkart.config;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import in.flipkart.entity.User;
import in.flipkart.service.UserService;

public class CustomDaoAuthenticationProvider extends DaoAuthenticationProvider {

	private UserService userService;

	public CustomDaoAuthenticationProvider(UserService userService) {
		this.userService = userService;
	}

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String email = ((CustomWebAuthenticationDetails) authentication.getDetails()).getUsername();
		User user = userService.getByEmail(email);
		if (user == null) {
			throw new BadCredentialsException("Bad credentials");
		}
		Authentication defaultAuthentication = new UsernamePasswordAuthenticationToken(user, user.getPassword(),
				user.getAuthorities());
		;
		defaultAuthentication = super.authenticate(authentication);
		return defaultAuthentication;
	}
}

package in.flipkart.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import in.flipkart.dao.UserDao;
import in.flipkart.entity.User;

@Service("flipkartUserDetailsService")
public class flipkartUserDetailsService implements UserDetailsService {

	@Autowired
	private UserDao userDao;

	@Override
	public UserDetails loadUserByUsername(final String username) {

		User user = userDao.getByEmail(username);
		if (user == null) {
			throw new UsernameNotFoundException(username + " not found");
		}
		return user;
	}
}

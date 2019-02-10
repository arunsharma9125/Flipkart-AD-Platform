package in.flipkart.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import in.flipkart.service.UserService;
import in.flipkart.service.flipkartUserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private DataSource dataSource;

	@Autowired
	private CustomWebAuthenticationDetailsSource authenticationDetailsSource;

	@Autowired
	flipkartUserDetailsService flipkartUserDetailsService;

	@Autowired
	UserService userService;

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		CustomDaoAuthenticationProvider authProvider = new CustomDaoAuthenticationProvider(userService);
		authProvider.setPasswordEncoder(passwordEncoder());
		authProvider.setUserDetailsService(flipkartUserDetailsService);
		authProvider.setHideUserNotFoundExceptions(false);
		auth.authenticationProvider(authProvider);
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		PasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		.antMatchers("/signin/**").permitAll()
		.antMatchers("/signup/**").permitAll()
		.antMatchers("/**/flipkart").authenticated()
		.antMatchers("/**/flipkart/**").authenticated()
		.anyRequest().permitAll();
		
		http.csrf().disable();
		http.exceptionHandling();
		http.logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/");
		http.formLogin().loginPage("/signin").defaultSuccessUrl("/flipkart", true).authenticationDetailsSource(authenticationDetailsSource);
	}

	@Bean
	public PersistentTokenRepository persistentTokenRepository() {
		JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
		tokenRepository.setDataSource(dataSource);
		return tokenRepository;
	}
}

package in.flipkart.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@EnableWebMvc
public class WebConfig extends WebMvcConfigurerAdapter {

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
		String path = "/app";
		if ("prod".equals(System.getenv("env"))) {
			path = "/dist";
		}
		registry.addResourceHandler("/css/**").addResourceLocations(path + "/css/");

		registry.addResourceHandler("/fonts/**").addResourceLocations(path + "/fonts/");

		registry.addResourceHandler("/js/**").addResourceLocations(path + "/js/");

		registry.addResourceHandler("/images/**").addResourceLocations(path + "/images/");
		
		registry.addResourceHandler("/robots.txt").addResourceLocations("/robots.txt");
		
		registry.addResourceHandler("/sitemap.xml").addResourceLocations("/sitemap.xml");
		
		registry.addResourceHandler("/BingSiteAuth.xml").addResourceLocations("/BingSiteAuth.xml");
	}
}

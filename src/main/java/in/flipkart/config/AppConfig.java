package in.flipkart.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.session.jdbc.config.annotation.web.http.EnableJdbcHttpSession;

@Configuration
//@EnableJdbcHttpSession
@EnableRedisHttpSession
@ComponentScan(value = {"in.flipkart"})
@EnableAsync
public class AppConfig {

}

package in.flipkart.config;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.AvailableSettings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;
import org.springframework.session.data.redis.config.ConfigureRedisAction;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

@Configuration
@EnableTransactionManagement
@PropertySources({ @PropertySource("classpath:lib-settings.properties"),
@PropertySource("classpath:wiring-local.properties") })
public class EnvConfig {

	@Autowired
	private Environment env;

	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(env.getRequiredProperty("flipkart.datasource.driver"));
		dataSource.setUrl(env.getRequiredProperty("flipkart.datasource.url"));
		dataSource.setUsername(env.getRequiredProperty("flipkart.datasource.username"));
		dataSource.setPassword(env.getRequiredProperty("flipkart.datasource.password"));
		return dataSource;
	}

	@Bean(name = "sessionFactory")
	public SessionFactory getSessionFactory(DataSource dataSource) {
		LocalSessionFactoryBuilder sessionBuilder = new LocalSessionFactoryBuilder(dataSource);
		sessionBuilder.scanPackages("in.flipkart.entity");
		sessionBuilder.addProperties(getHibernateProperties());
		return sessionBuilder.buildSessionFactory();
	}

	@Bean
	public HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {
		HibernateTransactionManager transactionManager = new HibernateTransactionManager(sessionFactory);
		return transactionManager;
	}

	private Properties getHibernateProperties() {
		Properties properties = new Properties();
		properties.setProperty(AvailableSettings.HBM2DDL_AUTO, env.getRequiredProperty("flipkart.hibernate.hbm2ddl.auto"));
		properties.setProperty(AvailableSettings.DIALECT, env.getRequiredProperty("flipkart.hibernate.dialect"));
		properties.setProperty(AvailableSettings.SHOW_SQL, env.getRequiredProperty("flipkart.hibernate.show_sql"));
		properties.setProperty(AvailableSettings.MERGE_ENTITY_COPY_OBSERVER, "allow");
		properties.setProperty("hibernate.cache.use_second_level_cache", "true");
		properties.setProperty("hibernate.cache.use_query_cache", "false");
		properties.setProperty("hibernate.cache.region.factory_class", "org.hibernate.cache.ehcache.EhCacheRegionFactory");
		properties.setProperty("net.sf.ehcache.configurationResourceName", "/ehcache.xml");
		properties.setProperty("hibernate.cache.use_structured_entries", "true");

		return properties;
	}

	@Bean
	public CommonsMultipartResolver multipartResolver() {
		CommonsMultipartResolver resolver = new CommonsMultipartResolver();
		resolver.setDefaultEncoding("utf-8");
		return resolver;
	}

	@Bean
	public FreeMarkerConfigurer freemarkerConfig() {
		FreeMarkerConfigurer freeMarkerConfigurer = new FreeMarkerConfigurer();
		freeMarkerConfigurer.setTemplateLoaderPath("/views/ftl/");
		freeMarkerConfigurer.setDefaultEncoding("utf-8");		
		return freeMarkerConfigurer;
	}

	@Bean
	public FreeMarkerViewResolver freemarkerViewResolver() {
		FreeMarkerViewResolver resolver = new FreeMarkerViewResolver();
		resolver.setCache(true);
		resolver.setSuffix(".ftl");
		resolver.setExposeSpringMacroHelpers(true);
		resolver.setExposeRequestAttributes(true);
		resolver.setExposeSessionAttributes(true);
		resolver.setAllowSessionOverride(true);
		resolver.setAllowRequestOverride(true);
		return resolver;
	}

	@Bean
	public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
		return new PersistenceExceptionTranslationPostProcessor();
	}
	
	@Bean
	public JedisConnectionFactory connectionFactory(){
		JedisConnectionFactory factory = new JedisConnectionFactory();
		factory.setHostName(env.getRequiredProperty("flipkart.redis.host"));
		factory.setPort(Integer.parseInt(env.getRequiredProperty("flipkart.redis.port")));
		return factory;
	}
	
	@Bean
	public static ConfigureRedisAction configureRedisAction(){
		return ConfigureRedisAction.NO_OP;
	}
}

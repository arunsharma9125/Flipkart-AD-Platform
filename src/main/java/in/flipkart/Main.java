package in.flipkart;

import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.util.EnumSet;

import javax.management.remote.JMXServiceURL;
import javax.servlet.DispatcherType;

import org.eclipse.jetty.jmx.ConnectorServer;
import org.eclipse.jetty.jmx.MBeanContainer;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.servlet.FilterHolder;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.util.thread.QueuedThreadPool;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.ResourcePropertySource;
import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;
import org.springframework.session.web.context.AbstractHttpSessionApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.DispatcherServlet;

public class Main {

    private static final int DEFAULT_PORT = 8081;
    private static final int JMX_PORT = 1099;
    private static final String CONTEXT_PATH = "/";
    private static final String CONFIG_LOCATION = "in.flipkart.config";
    private static final String MAPPING_URL = "/*";
    private static final String DEFAULT_PROFILE = "dev";

    public static void main(String[] args) throws Exception {
        startJetty(getPortFromArgs(args));
    }

    private static int getPortFromArgs(String[] args) {
        if (args.length > 0) {
            try {
                return Integer.valueOf(args[0]);
            } catch (NumberFormatException ignore) {
            }
        }
        return DEFAULT_PORT;
    }

    private static void startJetty(int port) throws Exception {
        Server server = new Server(new QueuedThreadPool(100));
        server.setHandler(getServletContextHandler(getContext()));
        
        // Setup JMX
        MBeanContainer mbContainer=new MBeanContainer(ManagementFactory.getPlatformMBeanServer());
        server.addEventListener(mbContainer);
        server.addBean(mbContainer);
        
        String jmx = "service:jmx:rmi://localhost:" + JMX_PORT + "/jndi/rmi://localhost:" + 1099 +"/jmxrmi";

        // Setup ConnectorServer
        JMXServiceURL jmxURL = new JMXServiceURL(jmx);
        ConnectorServer jmxServer = new ConnectorServer(jmxURL, "org.eclipse.jetty.jmx:name=rmiconnectorserver");
        server.addBean(jmxServer);
        
        ServerConnector connector = new ServerConnector(server);
        connector.setPort(port);
        server.addConnector(connector);
        
        server.start();
        server.join();
    }

    private static ServletContextHandler getServletContextHandler(WebApplicationContext context) throws IOException {
        ServletContextHandler contextHandler = new ServletContextHandler(ServletContextHandler.SESSIONS);
        contextHandler.setErrorHandler(null);
        contextHandler.setContextPath(CONTEXT_PATH);
        contextHandler.addServlet(new ServletHolder(new DispatcherServlet(context)), MAPPING_URL);
        contextHandler.addEventListener(new ContextLoaderListener(context));
        contextHandler.setResourceBase(Main.class.getResource("/META-INF/webapp").toExternalForm() );
        contextHandler.addFilter(new FilterHolder(new DelegatingFilterProxy(AbstractHttpSessionApplicationInitializer.DEFAULT_FILTER_NAME)),
        		"/*", EnumSet.allOf(DispatcherType.class));
        contextHandler.addFilter(new FilterHolder(new DelegatingFilterProxy(AbstractSecurityWebApplicationInitializer.DEFAULT_FILTER_NAME)),
        		"/*", EnumSet.allOf(DispatcherType.class));
        contextHandler.getSessionHandler().setMaxInactiveInterval(24 * 60 * 60); // 2 Day
        return contextHandler;
    }

    @SuppressWarnings("rawtypes")
	private static WebApplicationContext getContext() {
		String env = System.getProperty("env", "local");
        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        context.setConfigLocation(CONFIG_LOCATION);
        context.getEnvironment().setDefaultProfiles(DEFAULT_PROFILE);
		try {
	        PropertySource ps1 = new ResourcePropertySource(new ClassPathResource("lib-settings.properties"));
	        PropertySource ps2 = new ResourcePropertySource(new ClassPathResource("wiring-" + env + ".properties"));
	        context.getEnvironment().getPropertySources().addFirst(ps1);
	        context.getEnvironment().getPropertySources().addFirst(ps2);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
        return context;
    }

}
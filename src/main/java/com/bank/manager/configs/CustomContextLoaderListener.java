package com.bank.manager.configs;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;


public class CustomContextLoaderListener extends ContextLoader implements ServletContextListener {
	

		/**
		 * Create a new {@code ContextLoaderListener} that will create a web application
		 * context based on the "contextClass" and "contextConfigLocation" servlet
		 * context-params. See {@link ContextLoader} superclass documentation for details on
		 * default values for each.
		 * <p>This constructor is typically used when declaring {@code ContextLoaderListener}
		 * as a {@code <listener>} within {@code web.xml}, where a no-arg constructor is
		 * required.
		 * <p>The created application context will be registered into the ServletContext under
		 * the attribute name {@link WebApplicationContext#ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE}
		 * and the Spring application context will be closed when the {@link #contextDestroyed}
		 * lifecycle method is invoked on this listener.
		 * @see ContextLoader
		 * @see #ContextLoaderListener(WebApplicationContext)
		 * @see #contextInitialized(ServletContextEvent)
		 * @see #contextDestroyed(ServletContextEvent)
		 */
		public CustomContextLoaderListener() {
		}

		/**
		 * Create a new {@code ContextLoaderListener} with the given application context. This
		 * constructor is useful in Servlet 3.0+ environments where instance-based
		 * registration of listeners is possible through the {@link javax.servlet.ServletContext#addListener}
		 * API.
		 * <p>The context may or may not yet be {@linkplain
		 * org.springframework.context.ConfigurableApplicationContext#refresh() refreshed}. If it
		 * (a) is an implementation of {@link ConfigurableWebApplicationContext} and
		 * (b) has <strong>not</strong> already been refreshed (the recommended approach),
		 * then the following will occur:
		 * <ul>
		 * <li>If the given context has not already been assigned an {@linkplain
		 * org.springframework.context.ConfigurableApplicationContext#setId id}, one will be assigned to it</li>
		 * <li>{@code ServletContext} and {@code ServletConfig} objects will be delegated to
		 * the application context</li>
		 * <li>{@link #customizeContext} will be called</li>
		 * <li>Any {@link org.springframework.context.ApplicationContextInitializer ApplicationContextInitializer}s
		 * specified through the "contextInitializerClasses" init-param will be applied.</li>
		 * <li>{@link org.springframework.context.ConfigurableApplicationContext#refresh refresh()} will be called</li>
		 * </ul>
		 * If the context has already been refreshed or does not implement
		 * {@code ConfigurableWebApplicationContext}, none of the above will occur under the
		 * assumption that the user has performed these actions (or not) per his or her
		 * specific needs.
		 * <p>See {@link org.springframework.web.WebApplicationInitializer} for usage examples.
		 * <p>In any case, the given application context will be registered into the
		 * ServletContext under the attribute name {@link
		 * WebApplicationContext#ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE} and the Spring
		 * application context will be closed when the {@link #contextDestroyed} lifecycle
		 * method is invoked on this listener.
		 * @param context the application context to manage
		 * @see #contextInitialized(ServletContextEvent)
		 * @see #contextDestroyed(ServletContextEvent)
		 */
		public CustomContextLoaderListener(WebApplicationContext context) {
			super(context);
		}


		/**
		 * Initialize the root web application context.
		 */
		@Override
		public void contextInitialized(ServletContextEvent event) {
			initWebApplicationContext(event.getServletContext());
		}


		/**
		 * Close the root web application context.
		 */
		@Override
		public void contextDestroyed(ServletContextEvent event) {
			System.out.println("Destroying Context...");

            try {
            	System.out.println("Calling MySQL AbandonedConnectionCleanupThread shutdown");
                com.mysql.jdbc.AbandonedConnectionCleanupThread.shutdown();

            } catch (InterruptedException e) {
            	System.out.println("Error calling MySQL AbandonedConnectionCleanupThread shutdown {}");
            }

            ClassLoader cl = Thread.currentThread().getContextClassLoader();

            Enumeration<Driver> drivers = DriverManager.getDrivers();
            while (drivers.hasMoreElements()) {
                Driver driver = drivers.nextElement();

                if (driver.getClass().getClassLoader() == cl) {

                    try {
                    	System.out.println("Deregistering JDBC driver {}");
                        DriverManager.deregisterDriver(driver);

                    } catch (SQLException ex) {
                    	System.out.println("Error deregistering JDBC driver {}");
                    }

                } else {
                	System.out.println("Not deregistering JDBC driver {} as it does not belong to this webapp's ClassLoader");
                }
            }
			closeWebApplicationContext(event.getServletContext());
			ContextCleanupListener.cleanupAttributes(event.getServletContext());
		}

	}



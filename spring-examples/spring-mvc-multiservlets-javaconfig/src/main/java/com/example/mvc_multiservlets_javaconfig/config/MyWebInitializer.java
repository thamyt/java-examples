package com.example.mvc_multiservlets_javaconfig.config;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import com.example.mvc_multiservlets_javaconfig.config.root.RootConfig;
import com.example.mvc_multiservlets_javaconfig.config.servlet.WebFreemarkerConfig;
import com.example.mvc_multiservlets_javaconfig.config.servlet.WebJspConfig;
import com.example.mvc_multiservlets_javaconfig.config.servlet.WebThymeleafConfig;

@Configuration
public class MyWebInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		// TODO Auto-generated method stub
		super.onStartup(servletContext);
		
		// root context
	    AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
	    rootContext.register(RootConfig.class); // configuration class for root context
	    rootContext.scan("com.example.mvc_multiservlets_javaconfig.dto"); // scan only some packages
	    servletContext.addListener(new ContextLoaderListener(rootContext));
	    
	    /*
	    // dispatcher servlet (JSP)
	    AnnotationConfigWebApplicationContext webContext = new AnnotationConfigWebApplicationContext();
	    webContext.setParent(rootContext);
	    webContext.register(WebConfig.class); // configuration class for servlet
	    ServletRegistration.Dynamic dispatcher =
	    servletContext.addServlet("SpringDispatcher", new DispatcherServlet(webContext));
	    dispatcher.setLoadOnStartup(1);
	    dispatcher.addMapping("/");
		*/

	    // dispatcher servlet (JSP)
	    AnnotationConfigWebApplicationContext webJspContext = new AnnotationConfigWebApplicationContext();
	    webJspContext.setParent(rootContext);
	    webJspContext.register(WebJspConfig.class); // configuration class for servlet
	    //webJspContext.scan("com.example.mvc_multiservlets_javaconfig.controller.jsp");            // scan some other packages
	    ServletRegistration.Dynamic dispatcherJsp =
	    servletContext.addServlet("SpringDispatcherJsp", new DispatcherServlet(webJspContext));
	    dispatcherJsp.setLoadOnStartup(1);
	    dispatcherJsp.addMapping("/jsp/*");
	    
	    // dispatcher servlet (Thymeleaf)
	    AnnotationConfigWebApplicationContext webThymeleafContext = new AnnotationConfigWebApplicationContext();
	    webThymeleafContext.setParent(rootContext);
	    webThymeleafContext.register(WebThymeleafConfig.class); // configuration class for servlet
	    //webThymeleafContext.scan("com.example.mvc_multiservlets_javaconfig.controller.thymeleaf");            // scan some other packages
	    ServletRegistration.Dynamic dispatcherThymeleaf =
	    servletContext.addServlet("SpringDispatcherThymeleaf", new DispatcherServlet(webThymeleafContext));
	    dispatcherThymeleaf.setLoadOnStartup(1);
	    dispatcherThymeleaf.addMapping("/thymeleaf/*");
	    
	    // dispatcher servlet (Freemarker)
	    AnnotationConfigWebApplicationContext webFreemarkerContext = new AnnotationConfigWebApplicationContext();
	    webFreemarkerContext.setParent(rootContext);
	    webFreemarkerContext.register(WebFreemarkerConfig.class); // configuration class for servlet
	    //webFreemarkerContext.scan("com.example.mvc_multiservlets_javaconfig.controller.thymeleaf");            // scan some other packages
	    ServletRegistration.Dynamic dispatcherFreemarker =
	    servletContext.addServlet("SpringDispatcherFreemarker", new DispatcherServlet(webFreemarkerContext));
	    dispatcherFreemarker.setLoadOnStartup(1);
	    dispatcherFreemarker.addMapping("/freemarker/*");
	}
	
	@Override
	protected Class<?>[] getRootConfigClasses() {
		return null;
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return null;
	}

	@Override
	protected String[] getServletMappings() {
		return null;
	}
}

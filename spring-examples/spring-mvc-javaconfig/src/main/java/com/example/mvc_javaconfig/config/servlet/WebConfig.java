package com.example.mvc_javaconfig.config.servlet;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;

import com.example.mvc_javaconfig.interceptor.ExecuteTimeInterceptor;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"com.example.mvc_javaconfig.controller"})
public class WebConfig implements WebMvcConfigurer {

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		// TODO Auto-generated method stub
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
		WebMvcConfigurer.super.addResourceHandlers(registry);
	}
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// TODO Auto-generated method stub
		registry.addInterceptor(new ExecuteTimeInterceptor());
		WebMvcConfigurer.super.addInterceptors(registry);
	}

	@Bean
	public InternalResourceViewResolver jspViewResolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/views/jsp/");
		//resolver.setSuffix(".jsp");
		resolver.setViewNames("*.jsp");
		resolver.setOrder(2);
		return resolver;
	}
	
	@Bean
    public SpringTemplateEngine templateEngine() {
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(thymeleafTemplateResolver());
        
        /* Enabling the SpringEL compiler with Spring 4.2.4 or newer can speed up
  		   execution in most scenarios, but might be incompatible with specific
  		   cases when expressions in one template are reused across different data
  		   types, so this flag is "false" by default for safer backwards compatibility.
  		*/
  	    templateEngine.setEnableSpringELCompiler(true);
        return templateEngine;
    }

    @Bean
    public SpringResourceTemplateResolver thymeleafTemplateResolver() {
        SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
        templateResolver.setPrefix("/WEB-INF/views/thymeleaf/");
        //templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode("HTML5");
        templateResolver.setCacheable(true);
        return templateResolver;
    }
    
    @Bean
    public ThymeleafViewResolver thymeleafViewResolver() {
        ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
        viewResolver.setTemplateEngine(templateEngine());
        viewResolver.setViewNames(new String[] {"*.html"});
        viewResolver.setOrder(0);
        return viewResolver;
    }
	
	@Bean
    public FreeMarkerViewResolver freemarkerViewResolver() {
		FreeMarkerViewResolver resolver = new FreeMarkerViewResolver();
        resolver.setCache(true);
        resolver.setPrefix("");
        //resolver.setSuffix(".ftl");
        resolver.setViewNames("*.ftl");
        resolver.setOrder(1);
        return resolver;
    }

    @Bean
    public FreeMarkerConfigurer freemarkerConfig() {

    	FreeMarkerConfigurer freeMarkerConfigurer = new FreeMarkerConfigurer();
        freeMarkerConfigurer.setTemplateLoaderPath("/WEB-INF/views/ftl/");
        return freeMarkerConfigurer;
    }
}

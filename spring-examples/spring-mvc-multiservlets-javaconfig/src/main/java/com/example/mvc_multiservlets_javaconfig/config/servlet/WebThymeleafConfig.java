package com.example.mvc_multiservlets_javaconfig.config.servlet;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;

import com.example.mvc_multiservlets_javaconfig.interceptor.ExecuteTimeInterceptor;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"com.example.mvc_multiservlets_javaconfig.controller.thymeleaf"})
public class WebThymeleafConfig implements WebMvcConfigurer {

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new ExecuteTimeInterceptor());	
		WebMvcConfigurer.super.addInterceptors(registry);
	}
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		// TODO Auto-generated method stub
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
		WebMvcConfigurer.super.addResourceHandlers(registry);
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
        //viewResolver.setOrder(0);
        return viewResolver;
    }
}

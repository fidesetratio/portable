package com.app.configuration;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import nz.net.ultraq.thymeleaf.LayoutDialect;

@Configuration
public class MvcConfig implements WebMvcConfigurer {
	
	@Autowired
	private Environment env;
	

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/dashboard").setViewName("dashboard");
           
    }
    
    
    
    
    @Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		// TODO Auto-generated method stub
    	
    	String fileName = "file:///"+env.getProperty("spring.upload")+File.separator;
    	System.out.println(fileName);
    	
    	
    	registry.addResourceHandler("/imgu/**").addResourceLocations(fileName);
    	
		WebMvcConfigurer.super.addResourceHandlers(registry);
	}




	@Bean
    public LayoutDialect layoutDialect() {
        return new LayoutDialect();
    }
    
    

}

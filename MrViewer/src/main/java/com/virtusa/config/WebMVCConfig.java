package com.virtusa.config;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.InjectionPoint;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = { "com.virtusa.controller", "com.virtusa.validator" })
@PropertySource(value = "classpath:log4j.properties")
public class WebMVCConfig implements WebMvcConfigurer {

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		System.out.println("rsources handler method****");
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
	}

	@Bean
	public InternalResourceViewResolver createIRVR() {
		InternalResourceViewResolver i = new InternalResourceViewResolver();
		i.setPrefix("/WEB-INF/pages/");
		i.setSuffix(".jsp");
		i.setViewClass(JstlView.class);
		return i;
	}

	@Bean
	@Scope("prototype")
	public Logger logger(InjectionPoint injectPoint) {
		System.out.println("Loger*******");
		return Logger.getLogger(injectPoint.getMember().getDeclaringClass());
	}

}

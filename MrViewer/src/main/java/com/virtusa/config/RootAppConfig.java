package com.virtusa.config;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.InjectionPoint;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Scope;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

@Configuration
@Import({ ServiceConfig.class, PersistenceConfig.class })
@ComponentScan(basePackages = "/com/virtusa/database/copy/")
public class RootAppConfig {

	@Bean(name = "multipartResolver")
	public CommonsMultipartResolver createCMResolver() {
		CommonsMultipartResolver resolver = new CommonsMultipartResolver();
		resolver.setMaxUploadSizePerFile(999999999);
		return resolver;
	}

	@Bean
	public ResourceBundleMessageSource messageSource() {
		ResourceBundleMessageSource source = new ResourceBundleMessageSource();
		source.setBasename("/com/virtusa/commons/validator");
		source.setCacheSeconds(3600); // Refresh cache once per hour.
		return source;
	}

	@Bean
	@Scope("prototype")
	public Logger logger(InjectionPoint injectPoint) {
		System.out.println("Loger*******");
		return Logger.getLogger(injectPoint.getMember().getDeclaringClass());
	}

}

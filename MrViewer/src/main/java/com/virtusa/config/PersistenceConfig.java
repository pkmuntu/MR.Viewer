package com.virtusa.config;

import java.util.Properties;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ComponentScan(basePackages = "com.virtusa.dao")
@PropertySource(value = "classpath:/com/virtusa/commons/jdbc.properties")
@EnableTransactionManagement
public class PersistenceConfig {

	@Autowired
	private Environment env;

	@Bean(name = "ds")
	public DataSource createDS() {
		try {
			InitialContext ic = new InitialContext();
			DataSource ds = (DataSource) ic.lookup(env.getProperty("ds.jndi"));
			return ds;
		} catch (NamingException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Bean(name = "sf")
	public LocalSessionFactoryBean createSF() {
		LocalSessionFactoryBean sf = new LocalSessionFactoryBean();
		sf.setDataSource(createDS());
		sf.setPackagesToScan("com.virtusa.entity");
		Properties p = new Properties();
		p.setProperty("hibernate.dialect", env.getProperty("hibernate.dialect"));
		p.setProperty("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl.auto"));
		p.setProperty("hibernate.show_sql", env.getProperty("hibernate.show_sql"));
		p.setProperty("hibernate.connection.autocommit", env.getProperty("hibernate.connection.autocommit"));
		p.setProperty("hibernate.format_sql", env.getProperty("hibernate.format_sql"));
		sf.setHibernateProperties(p);
		return sf;
	}

	@Bean(name = "ht")
	public HibernateTemplate createHT() {
		HibernateTemplate ht = new HibernateTemplate();
		ht.setCheckWriteOperations(false);
		ht.setSessionFactory(createSF().getObject());
		return ht;
	}

	@Bean(name = "dsTxMgmr")
	public HibernateTransactionManager createHTM() {
		return new HibernateTransactionManager(createSF().getObject());

	}

}

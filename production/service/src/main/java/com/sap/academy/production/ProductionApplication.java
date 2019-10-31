package com.sap.academy.production;



import com.sap.academy.dbhandler.annotations.EnableDynamicDbConnection;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableDynamicDbConnection
@ComponentScan({"com.sap.academy","com.sap.academy.dbhandler","com.sap.academy.connection"})
@EnableAutoConfiguration(exclude = { DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
@EnableJpaRepositories
public class ProductionApplication {
	public static void main(String[] args) {
		SpringApplication.run(ProductionApplication.class, args);
	}

}

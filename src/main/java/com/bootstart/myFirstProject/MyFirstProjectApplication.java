package com.bootstart.myFirstProject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.MongoTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement // included for transaction
public class MyFirstProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyFirstProjectApplication.class, args);
	}

	//It is used for implementing transaction which makes a whole function act like a container
	// this is used for configuration
	// PlatformTransactionManager implements MongoTransactionManager
	@Bean
	public PlatformTransactionManager anything(MongoDatabaseFactory dbfactory){
		return new MongoTransactionManager(dbfactory);
	}
}


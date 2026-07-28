package com.employee;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;


/*@SpringBootApplication
@EnableJpaAuditing
public class EmployeeCrudApplication {

	public static void main(String[] args) {
		Dotenv dotenv=Dotenv.configure().ignoreIfMissing().load();
		System.setProperty("DB_URL", dotenv.get("DB_URL"));
		System.setProperty("DB_DRIVER", dotenv.get("DB_DRIVER"));
		System.setProperty("DB_USERNAME", dotenv.get("DB_USERNAME"));
		System.setProperty("DB_PASSWORD",dotenv.get("DB_PASSWORD"));
		System.setProperty("JWT_SECRETKEY", dotenv.get("JWT_SECRETKEY"));
		SpringApplication.run(EmployeeCrudApplication.class, args);
	}
}*/

@SpringBootApplication
@EnableJpaAuditing
public class EmployeeCrudApplication {

    public static void main(String[] args) {

        System.out.println("DB_URL = " + System.getenv("DB_URL"));
        System.out.println("DB_USERNAME = " + System.getenv("DB_USERNAME"));
        System.out.println("DB_PASSWORD = " + System.getenv("DB_PASSWORD"));

        SpringApplication.run(EmployeeCrudApplication.class, args);
    }
}
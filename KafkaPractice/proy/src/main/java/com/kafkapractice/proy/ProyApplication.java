package com.kafkapractice.proy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
/**Delimita cobertura de packages */
@EnableJpaRepositories(basePackages = {"com.kafkapractice.proy.Respositories"})
public class ProyApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProyApplication.class, args);
	}

}

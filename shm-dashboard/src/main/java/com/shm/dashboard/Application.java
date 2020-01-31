package com.shm.dashboard;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.rest.RepositoryRestMvcAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = {"com.shm"}, exclude = RepositoryRestMvcAutoConfiguration.class)
@EnableJpaRepositories(basePackages = { "com.shm" })
@EntityScan(basePackages = { "com.shm" })
//@EnableAsync
public class Application {
	public static void main(String[] args) throws InterruptedException {
		SpringApplication.run(Application.class, args);
	}
}
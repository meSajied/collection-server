package org.collections.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = {"org.collections.collections",
		"org.collections.users", "org.collections.admin"})
@EnableJpaRepositories(basePackages = {"org.collections.collections",
		"org.collections.users", "org.collections.admin"})
@ComponentScan(basePackages = {"org.collections.server",
		"org.collections.collections", "org.collections.users",
		"org.collections.admin"})
public class CollectionserverApplication {
	public static void main(String[] args) {
		SpringApplication.run(CollectionserverApplication.class, args);
	}
}

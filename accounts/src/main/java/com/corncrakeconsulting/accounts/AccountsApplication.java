package com.corncrakeconsulting.accounts;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")

@OpenAPIDefinition(
		info = @io.swagger.v3.oas.annotations.info.Info(
				title = "Accounts Microservice",
				version = "1.0",
				description = "Accounts Service API",
				contact = @Contact(
						name = "Corncrake Consulting",
						email = "6yK9o@example.com",
						url = "https://www.corncrakeconsulting.com"),
				license = @License(
						name = "Apache 2.0",
						url = "https://www.apache.org/licenses/LICENSE-2.0"
				)
		)
)
@EntityScan(basePackages = "com.corncrakeconsulting.accounts.entity")
public class AccountsApplication {
	public static void main(String[] args) {
		SpringApplication.run(AccountsApplication.class, args);

	}
}

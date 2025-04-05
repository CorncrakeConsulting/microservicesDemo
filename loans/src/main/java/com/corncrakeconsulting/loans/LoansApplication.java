package com.corncrakeconsulting.loans;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
/*@ComponentScans({ @ComponentScan("com.corncrakeconsulting.loans.controller") })
@EnableJpaRepositories("com.corncrakeconsulting.loans.repository")
@EntityScan("com.corncrakeconsulting.loans.model")*/
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
@OpenAPIDefinition(
		info = @Info(
				title = "Loans microservice REST API Documentation",
				description = "CrakeBank Loans microservice REST API Documentation",
				version = "v1",
				contact = @Contact(
						name = "Madan Reddy",
						email = "tutor@corncrakeconsulting.com",
						url = "https://www.corncrakeconsulting.com"
				),
				license = @License(
						name = "Apache 2.0",
						url = "https://www.corncrakeconsulting.com"
				)
		),
		externalDocs = @ExternalDocumentation(
				description = "CrakeBank Loans microservice REST API Documentation",
				url = "https://www.corncrakeconsulting.com/swagger-ui.html"
		)
)
public class LoansApplication {

	public static void main(String[] args) {
		SpringApplication.run(LoansApplication.class, args);
	}
}

package ecommerce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

// Starting the Api application
@SpringBootApplication
@EnableSwagger2
public class EcommerceOrderApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(EcommerceOrderApiApplication.class, args);
	}

}


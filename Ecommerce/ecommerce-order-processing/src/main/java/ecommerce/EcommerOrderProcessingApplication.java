package ecommerce;

import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.KafkaListener;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@EnableSwagger2
public class EcommerOrderProcessingApplication {

	public static void main(String[] args) {
		SpringApplication.run(EcommerOrderProcessingApplication.class, args);
	}



}


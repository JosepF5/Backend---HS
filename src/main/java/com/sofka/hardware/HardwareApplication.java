package com.sofka.hardware;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "HardwareApplication", version = "1.1", description = "Josep Palomino - Final Project v1.1"))
public class HardwareApplication {

	public static void main(String[] args) {
		SpringApplication.run(HardwareApplication.class, args);
	}

}

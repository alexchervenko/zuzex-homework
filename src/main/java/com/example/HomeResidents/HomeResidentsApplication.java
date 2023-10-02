package com.example.HomeResidents;

import com.example.HomeResidents.config.RsaKeyProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableConfigurationProperties(RsaKeyProperties.class)
@SpringBootApplication
public class HomeResidentsApplication {

	public static void main(String[] args) {
		SpringApplication.run(HomeResidentsApplication.class, args);
	}

}

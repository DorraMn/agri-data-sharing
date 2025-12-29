package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient    // pour enregistrer le service auprès du serveur Eureka
@EnableFeignClients

public class DonneesPartageesApplication {

	public static void main(String[] args) {
		SpringApplication.run(DonneesPartageesApplication.class, args);
	}

}

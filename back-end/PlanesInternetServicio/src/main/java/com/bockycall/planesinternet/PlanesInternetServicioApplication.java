package com.bockycall.planesinternet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class PlanesInternetServicioApplication {

    public static void main(String[] args) {
        SpringApplication.run(PlanesInternetServicioApplication.class, args);
    }

}

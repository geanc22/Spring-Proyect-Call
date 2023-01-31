package com.bockycall.recargas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class RecargasServicioApplication {

    public static void main(String[] args) {
        SpringApplication.run(RecargasServicioApplication.class, args);
    }

}

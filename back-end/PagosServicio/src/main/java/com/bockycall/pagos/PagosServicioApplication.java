package com.bockycall.pagos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class PagosServicioApplication {

    public static void main(String[] args) {
        SpringApplication.run(PagosServicioApplication.class, args);
    }

}

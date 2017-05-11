package com.thecoffeine.virtuoso;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

/**
 * Virtuoso application.
 *
 * @version 1.0
 */
@SpringBootApplication
//@EnableResourceServer//FIXME: mock auth server for tests.
@EnableDiscoveryClient
@EnableEurekaClient
@EnableFeignClients
public class VirtuosoApplication {

    /**
     * Entry point.
     *
     * @param args    App's args.
     */
    public static void main( String [] args ) {
        SpringApplication.run( VirtuosoApplication.class, args );
    }
}
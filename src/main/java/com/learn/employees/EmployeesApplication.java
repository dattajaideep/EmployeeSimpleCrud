package com.learn.employees;

//import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableEurekaClient//Enable as Client for Eureka Server Registry
@EnableDiscoveryClient
@EnableFeignClients
//@EnableEncryptableProperties
public class EmployeesApplication {
    public static void main(String[] args) {
        SpringApplication.run(EmployeesApplication.class, args);
        System.out.println("Service Started");
    }
}

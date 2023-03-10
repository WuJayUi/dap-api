package com.tbb.dap.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@EntityScan(basePackages = "com.tbb.repository.entity")
@EnableJpaRepositories(basePackages = "com.tbb.repository")
@ComponentScan(basePackages = {
        "com.tbb.dap.common.*",
        "com.tbb.dap.test.*",
        "com.tbb.dap.services.*"
})
@SpringBootApplication
public class DapTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(DapTestApplication.class, args);
    }

}

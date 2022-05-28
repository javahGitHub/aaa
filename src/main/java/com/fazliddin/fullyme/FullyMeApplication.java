package com.fazliddin.fullyme;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;

@SpringBootApplication
public class FullyMeApplication {

    public static void main(String[] args) {
        SpringApplication.run(FullyMeApplication.class, args);
    }

}

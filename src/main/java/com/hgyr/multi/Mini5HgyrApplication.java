package com.hgyr.multi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class Mini5HgyrApplication {

    public static void main(String[] args) {
        SpringApplication.run(Mini5HgyrApplication.class, args);}

}

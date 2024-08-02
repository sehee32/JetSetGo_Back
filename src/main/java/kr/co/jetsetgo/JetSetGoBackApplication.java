package kr.co.jetsetgo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = {"kr.co.jetsetgo.dbio"})
@SpringBootApplication
public class JetSetGoBackApplication {

    public static void main(String[] args) {
        SpringApplication.run(JetSetGoBackApplication.class, args);
    }

}

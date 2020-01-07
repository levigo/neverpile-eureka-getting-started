package com.neverpile.eureka.tutorial02.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.neverpile.eureka.util.EnableNeverpileEurekaSpringApplication;

@SpringBootApplication
@EnableNeverpileEurekaSpringApplication
public class NeverpileEureka {

  public static void main(final String[] args) {
    new SpringApplication(NeverpileEureka.class).run(args);
  }
}

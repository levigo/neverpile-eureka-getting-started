package com.neverpile.eureka.tutorial03.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.neverpile.eureka.util.EnableNeverpileEurekaSpringApplication;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@SpringBootApplication
@EnableNeverpileEurekaSpringApplication
public class NeverpileEureka {

  public static void main(final String[] args) {
    new SpringApplication(NeverpileEureka.class).run(args);
  }
}

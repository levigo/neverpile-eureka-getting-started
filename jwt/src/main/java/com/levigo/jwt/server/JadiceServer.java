package com.levigo.jwt.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import com.levigo.jadice.web.server.spring.autoconfig.EnableJWTSpringBootApplication;

@SpringBootApplication
@EnableJWTSpringBootApplication("com.levigo.jwt.Application")
public class JadiceServer extends SpringBootServletInitializer {

  public static void main(String[] args) {
    SpringApplication.run(JadiceServer.class, args);
  }
}
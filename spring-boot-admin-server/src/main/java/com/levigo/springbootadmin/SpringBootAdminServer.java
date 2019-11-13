package com.levigo.springbootadmin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import de.codecentric.boot.admin.server.config.EnableAdminServer;

@EnableAdminServer
@SpringBootApplication
public class SpringBootAdminServer {

  public static void main(final String[] args) {
    SpringApplication.run(SpringBootAdminServer.class, args);
  }
}

package com.alfa.alfabattle.bargaining;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main Spring application
 */
@SpringBootApplication(scanBasePackages = {"com.alfa.alfabattle.bargaining"})
public class Application {

  public static void main(String[] args) {
    SpringApplication.run(com.alfa.alfabattle.bargaining.Application.class, args);
  }
}
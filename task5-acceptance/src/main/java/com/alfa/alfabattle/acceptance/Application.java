package com.alfa.alfabattle.acceptance;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main Spring application
 */
@SpringBootApplication(scanBasePackages = {"com.alfa.alfabattle.acceptance"})
public class Application {

  public static void main(String[] args) {
    SpringApplication.run(com.alfa.alfabattle.acceptance.Application.class, args);
  }
}
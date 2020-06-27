package com.alfa.alfabattle.depression;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main Spring application
 */
@SpringBootApplication(scanBasePackages = {"com.alfa.alfabattle.depression"})
public class Application {

  public static void main(String[] args) {
    SpringApplication.run(com.alfa.alfabattle.depression.Application.class, args);
  }
}
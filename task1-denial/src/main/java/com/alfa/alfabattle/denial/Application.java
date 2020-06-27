package com.alfa.alfabattle.denial;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main Spring application
 */
@SpringBootApplication(scanBasePackages = {"com.alfa.alfabattle.denial"})
public class Application {

  public static void main(String[] args) {
    SpringApplication.run(com.alfa.alfabattle.denial.Application.class, args);
  }
}
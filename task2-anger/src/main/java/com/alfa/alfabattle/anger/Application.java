package com.alfa.alfabattle.anger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main Spring application
 */
@SpringBootApplication(scanBasePackages = {"com.alfa.alfabattle.anger"})
public class Application {

  public static void main(String[] args) {
    SpringApplication.run(com.alfa.alfabattle.anger.Application.class, args);
  }
}
package com.alfa.alfabattle.bargaining.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller to test application availability
 */
@RestController
public class TestController {
  private static final Logger LOG = LoggerFactory.getLogger(TestController.class);

  @Autowired
  public TestController() {
  }

  @GetMapping("/test")
  @ResponseBody
  public ResponseEntity<String> getPages() {
        return ResponseEntity.ok("It works");
  }
}

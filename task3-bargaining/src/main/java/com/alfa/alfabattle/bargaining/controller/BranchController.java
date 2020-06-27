package com.alfa.alfabattle.bargaining.controller;

import com.alfa.alfabattle.bargaining.model.Branch;
import com.alfa.alfabattle.bargaining.repository.BranchRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;

/**
 * Controller to work with branches
 */
@RestController
public class BranchController {
  private static final Logger LOG = LoggerFactory.getLogger(BranchController.class);

  private BranchRepository branchRepository;

  @Autowired
  public BranchController(BranchRepository branchRepository) {
    this.branchRepository = branchRepository;
  }

  @GetMapping(path = "/branches/{id}", produces = "application/json")
  @ResponseBody
  public ResponseEntity<?> getBranchById(@PathVariable Long id) {
    try {
      Optional<Branch> branch = branchRepository.findById(id);
      if (branch.isPresent()) {
        return ResponseEntity.ok(branch.get());
      } else {
        Map<String, String> errors = Collections.singletonMap("status", "branch not found");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errors);
      }
    } catch (Exception ex) {
      int k = 0;
      return ResponseEntity.badRequest().build();
    }
  }
}

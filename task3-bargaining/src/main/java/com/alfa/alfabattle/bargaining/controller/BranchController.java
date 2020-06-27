package com.alfa.alfabattle.bargaining.controller;

import com.alfa.alfabattle.bargaining.model.Branch;
import com.alfa.alfabattle.bargaining.repository.BranchRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

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
      return ResponseEntity.badRequest().build();
    }
  }

  @GetMapping(path = "/branches", produces = "application/json")
  @ResponseBody
  public ResponseEntity<?> getDistance(@RequestParam(name="lat") Double lat, @RequestParam(name="lon") Double lon) {
    try {
      List<Branch> branches = new ArrayList<>();
      Iterable<Branch> branchIterator = branchRepository.findAll();
      branchIterator.forEach(branches::add);

      Optional<BranchWithDistance> min = branches.stream()
              .map(branch -> new BranchWithDistance(branch, Long.valueOf(calcDistance(branch, lat, lon))
                      .intValue()))
              .min(Comparator.comparingInt(BranchWithDistance::getDistance));

      if (min.isPresent()) {
        return ResponseEntity.ok(min.get());
      } else {
        Map<String, String> errors = Collections.singletonMap("status", "no branches to compare");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errors);
      }
    } catch (Exception ex) {
      return ResponseEntity.badRequest().build();
    }
  }

  private long calcDistance(Branch branch, Double lat, Double lon) {
    int EARTH_RADIUS = 6371000; // in meters
    double fi1 = Math.toRadians(branch.getLat()); //широта 1
    double fi2 = Math.toRadians(lat); //широта 2
    double la1 = Math.toRadians(branch.getLon()); //долгота 1
    double la2 = Math.toRadians(lon); //долгота 2

    double x = Math.sin((fi2-fi1)/2);
    x = x*x;
    double y = Math.sin((la2-la1)/2);
    y = y*y;
    y = y * Math.cos(fi1) * Math.cos(fi1);
    return Math.round(
            2 * EARTH_RADIUS * Math.asin(
                    Math.sqrt(x+y))
    );
  }

  private double fromGradToRadian(double grad) {
    return grad * Math.PI / 180;
  }

  public static class BranchWithDistance extends Branch {
    private int distance;

    public BranchWithDistance(Branch branch, int distance) {
      super(branch);
      this.distance = distance;
    }

    public int getDistance() {
      return distance;
    }

    public void setDistance(int distance) {
      this.distance = distance;
    }
  }
}

package com.alfa.alfabattle.bargaining.controller;

import com.alfa.alfabattle.bargaining.model.Branch;
import com.alfa.alfabattle.bargaining.repository.BranchRepository;
import com.alfa.alfabattle.bargaining.services.DistanceCalculator;
import com.alfa.alfabattle.bargaining.services.LoadPredictor;
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

  private LoadPredictor loadPredictor;

  private DistanceCalculator distanceCalculator = new DistanceCalculator();

  @Autowired
  public BranchController(
          LoadPredictor loadPredictor,
          BranchRepository branchRepository) {
    this.loadPredictor = loadPredictor;
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
              .map(branch -> new BranchWithDistance(branch, distanceCalculator.calc(branch, lat, lon)))
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

  @GetMapping(path = "/branches/{id}/predict", produces = "application/json")
  @ResponseBody
  public ResponseEntity<?> getLoad(
          @PathVariable Long id,
            @RequestParam(name="dayOfWeek") int dayOfWeek,
          @RequestParam(name="hourOfDay") int hourOfDay) {
    try {
      Optional<Branch> branch = branchRepository.findById(id);

      if (branch.isPresent()) {
        return ResponseEntity.ok(
                new BranchWithLoad(branch.get(), dayOfWeek, hourOfDay,
                        loadPredictor.predictTime(branch.get(), dayOfWeek, hourOfDay))
        );
      } else {
        Map<String, String> errors = Collections.singletonMap("status", "branch not found");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errors);
      }
    } catch (Exception ex) {
      return ResponseEntity.badRequest().build();
    }
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

  public static class BranchWithLoad extends Branch {
    private int dayOfWeek;
    private int hourOfDay;
    private int predicting;

    public BranchWithLoad(Branch branch, int dayOfWeek, int hourOfDay, int predicting) {
      super(branch);
      this.dayOfWeek = dayOfWeek;
      this.hourOfDay = hourOfDay;
      this.predicting = predicting;
    }

    public int getDayOfWeek() {
      return dayOfWeek;
    }

    public void setDayOfWeek(int dayOfWeek) {
      this.dayOfWeek = dayOfWeek;
    }

    public int getHourOfDay() {
      return hourOfDay;
    }

    public void setHourOfDay(int hourOfDay) {
      this.hourOfDay = hourOfDay;
    }

    public int getPredicting() {
      return predicting;
    }

    public void setPredicting(int predicting) {
      this.predicting = predicting;
    }
  }
}

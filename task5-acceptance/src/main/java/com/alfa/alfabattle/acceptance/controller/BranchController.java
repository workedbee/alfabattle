package com.alfa.alfabattle.acceptance.controller;

import com.alfa.alfabattle.acceptance.model.PositionResponse;
import com.alfa.alfabattle.acceptance.model.ReceiptRequest;
import com.alfa.alfabattle.acceptance.model.ReceiptResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

/**
 * Controller to work with branches
 */
@RestController
public class BranchController {
  private static final Logger LOG = LoggerFactory.getLogger(BranchController.class);

  @Autowired
  public BranchController() {
  }


  @PostMapping(path = "/promo", produces = "application/json")
  @ResponseBody
  public ResponseEntity<?> applyPromo() {
    try {
      return ResponseEntity.ok("{}");
    } catch (Exception ex) {
      return ResponseEntity.badRequest().build();
    }
  }

  @PostMapping(path = "/receipt", produces = "application/json")
  @ResponseBody
  public ResponseEntity<?> applyReceipt(@RequestBody ReceiptRequest receiptRequest) {
    try {
      ReceiptResponse response = new ReceiptResponse();
      response.setDiscount(.0);
      response.setTotal(141.99);

      PositionResponse position = new PositionResponse();
      position.setId("3432166");
      position.setName("ЛУК.Жид.АНТИФ.G11 Green нез.1кг");
      position.setPrice(141.99);
      position.setRegularPrice(141.99);
      response.setPositions(Collections.singletonList(position));

      return ResponseEntity.ok(response);
    } catch (Exception ex) {
      return ResponseEntity.badRequest().build();
    }
  }

}

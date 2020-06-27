package com.alfa.alfabattle.acceptance.model;

import java.util.List;

public class ReceiptResponse {
    private double total;
    private double discount;
    private List<PositionResponse> positions;

    public ReceiptResponse(){}

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public List<PositionResponse> getPositions() {
        return positions;
    }

    public void setPositions(List<PositionResponse> positions) {
        this.positions = positions;
    }
}

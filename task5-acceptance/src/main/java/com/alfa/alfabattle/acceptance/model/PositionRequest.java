package com.alfa.alfabattle.acceptance.model;

public class PositionRequest {
    private String itemId;
    private int quantity;

    public PositionRequest() {}

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}

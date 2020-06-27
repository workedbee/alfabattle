package com.alfa.alfabattle.acceptance.model;

import java.util.List;

public class ReceiptRequest {
    private int shopId;
    private boolean loyaltyCard;
    private List<PositionRequest> positions;

    public ReceiptRequest(){}

    public int getShopId() {
        return shopId;
    }

    public void setShopId(int shopId) {
        this.shopId = shopId;
    }

    public boolean isLoyaltyCard() {
        return loyaltyCard;
    }

    public void setLoyaltyCard(boolean loyaltyCard) {
        this.loyaltyCard = loyaltyCard;
    }

    public List<PositionRequest> getPositions() {
        return positions;
    }

    public void setPositions(List<PositionRequest> positions) {
        this.positions = positions;
    }
}

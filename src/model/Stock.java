package model;

import exceptions.InvalidValueException;

public class Stock {
    private Integer shelfId, shelfLevel, quantity;
    private String productId;

    public Stock(Integer shelfId, Integer shelfLevel, Integer quantity, String productId) throws InvalidValueException {
        setShelfId(shelfId);
        setShelfLevel(shelfLevel);
        setQuantity(quantity);
        setProductId(productId);
    }

    public void setShelfId(Integer shelfId) {
        this.shelfId = shelfId;
    }

    public void setShelfLevel(Integer shelfLevel) {
        this.shelfLevel = shelfLevel;
    }

    public void setQuantity(Integer quantity) throws InvalidValueException {
        if (quantity == null || quantity < 0)
            throw new InvalidValueException("La quantité doit être un nombre positif.", quantity);
        this.quantity = quantity;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public Integer getShelfId() {
        return shelfId;
    }

    public Integer getShelfLevel() {
        return shelfLevel;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public String getProductId() {
        return productId;
    }
}

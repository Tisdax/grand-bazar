package model;

import exceptions.InvalidValueException;

public class ProductStockInfo {
    private String productName;
    private Integer stockQuantity, shelfLevel, shelfId;
    private Boolean isShelfRefregirated;

    public ProductStockInfo(String productName, Integer stockQuantity, Integer shelfLevel, Integer shelfId, Boolean isShelfRefregirated) throws InvalidValueException {
        this.productName = productName;
        setStockQuantity(stockQuantity);
        setShelfLevel(shelfLevel);
        setShelfId(shelfId);
        this.isShelfRefregirated = isShelfRefregirated;
    }

    public void setStockQuantity(Integer stockQuantity) throws InvalidValueException {
        if (stockQuantity == null || stockQuantity <= 0)
            throw new InvalidValueException("La quantité en stock est obligatoire et doit être un nombre positif.", stockQuantity);
        this.stockQuantity = stockQuantity;
    }

    public void setShelfLevel(Integer shelfLevel) throws InvalidValueException {
        if (shelfLevel == null || shelfLevel <= 0)
            throw new InvalidValueException("L'étage de l'étagère est obligatoire et doit être un nombre positif.", shelfLevel);
        this.shelfLevel = shelfLevel;
    }

    public void setShelfId(Integer shelfId) throws InvalidValueException {
        if (shelfId == null || shelfId <= 0)
            throw new InvalidValueException("L'id de l'étagère est obligatoire et doit être un nombre positif.", shelfId);
        this.shelfId = shelfId;
    }

    public String getProductName() {
        return productName;
    }

    public Integer getStockQuantity() {
        return stockQuantity;
    }

    public Integer getShelfLevel() {
        return shelfLevel;
    }

    public Integer getShelfId() {
        return shelfId;
    }

    public Boolean getShelfRefregirated() {
        return isShelfRefregirated;
    }
}

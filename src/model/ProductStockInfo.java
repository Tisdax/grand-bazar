package model;

public class ProductStockInfo {
    private String productName;
    private Integer stockQuantity, shelfLevel, shelfId;
    private Boolean isShelfRefregirated;

    public ProductStockInfo(String productName, Integer stockQuantity, Integer shelfLevel, Integer shelfId, Boolean isShelfRefregirated) {
        this.productName = productName;
        this.stockQuantity = stockQuantity;
        this.shelfLevel = shelfLevel;
        this.shelfId = shelfId;
        this.isShelfRefregirated = isShelfRefregirated;
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

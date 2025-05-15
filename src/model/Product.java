package model;

import java.time.LocalDate;

public class Product {
    private String id, name, categoryName;
    private Double netPrice;
    private Integer vatPercentage, loyaltyPointsNb, minQuantity, promotionMinQuantity, timeBeforeRemoving;
    private Boolean isEdible;
    private LocalDate saleDate;

    public Product(String id, String name, Double netPrice, Integer vatPercentage, Integer loyaltyPointsNb, Integer minQuantity, Integer promotionMinQuantity, Integer timeBeforeRemoving, Boolean isEdible, LocalDate saleDate, String categoryName) {
        this.id = id;
        this.name = name;
        setNetPrice(netPrice);
        this.vatPercentage = vatPercentage;
        this.loyaltyPointsNb = loyaltyPointsNb;
        setMinQuantity(minQuantity);
        this.minQuantity = minQuantity;
        this.promotionMinQuantity = promotionMinQuantity;
        this.timeBeforeRemoving = timeBeforeRemoving;
        this.isEdible = isEdible;
        this.saleDate = saleDate;
        this.categoryName = categoryName;
    }

    public Product(String id, String name, Double netPrice, Integer vatPercentage, Integer loyaltyPointsNb, Boolean isEdible, LocalDate saleDate, String categoryName) {
        this(id, name, netPrice, vatPercentage, loyaltyPointsNb, null, null, null, isEdible, saleDate, categoryName);
    }

    public void setNetPrice(Double netPrice) {
        if (netPrice >= 0)
            this.netPrice = netPrice;
    }

    public void setMinQuantity(Integer minQuantity) {
            this.minQuantity = minQuantity;
    }

    public void setPromotionMinQuantity(Integer promotionMinQuantity) {
        if (promotionMinQuantity > 0)
            this.promotionMinQuantity = promotionMinQuantity;
    }

    public void setTimeBeforeRemoving(Integer timeBeforeRemoving) {
        this.timeBeforeRemoving = timeBeforeRemoving;
    }

    public void setSaleDate(LocalDate saleDate) {
        this.saleDate = saleDate;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public Double getNetPrice() {
        return netPrice;
    }

    public Integer getVatPercentage() {
        return vatPercentage;
    }

    public Integer getLoyaltyPointsNb() {
        return loyaltyPointsNb;
    }

    public Integer getMinQuantity() {
        return minQuantity;
    }

    public Integer getPromotionMinQuantity() {
        return promotionMinQuantity;
    }

    public Integer getTimeBeforeRemoving() {
        return timeBeforeRemoving;
    }

    public Boolean getEdible() {
        return isEdible;
    }

    public LocalDate getSaleDate() {
        return saleDate;
    }
}
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
        this.minQuantity = minQuantity;
        this.promotionMinQuantity = promotionMinQuantity;
        this.timeBeforeRemoving = timeBeforeRemoving;
        this.isEdible = isEdible;
        this.saleDate = saleDate;
        this.categoryName = categoryName;
    }

    public Product(String id, String name, Double netPrice, Integer vatPercentage, Integer loyaltyPointsNb, Boolean isEdible, String categoryName) {
        this(id, name, netPrice, vatPercentage, loyaltyPointsNb, null, null, null, isEdible, null, categoryName);
    }

    public void setNetPrice(Double netPrice) {
        if (netPrice < 0)
            this.netPrice = (double)0;
        else
            this.netPrice = netPrice;
    }
}
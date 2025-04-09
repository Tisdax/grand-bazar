package model;

import java.time.LocalDate;

public class Product {
    private String id, name;
    private Double netPrice;
    private Integer vatPercentage, loyaltyPointsNb, minQuantity, promotionMinQuantity, timeBeforeRemoving;
    private Boolean isEdible;
    private LocalDate saleDate;
    private ProductCategory category;

    public Product(String id, String name, Double netPrice, Integer vatPercentage, Integer loyaltyPointsNb, Integer minQuantity, Integer promotionMinQuantity, Integer timeBeforeRemoving, Boolean isEdible, LocalDate saleDate, ProductCategory category) {
        this.id = id;
        this.name = name;
        this.netPrice = netPrice;
        this.vatPercentage = vatPercentage;
        this.loyaltyPointsNb = loyaltyPointsNb;
        this.minQuantity = minQuantity;
        this.promotionMinQuantity = promotionMinQuantity;
        this.timeBeforeRemoving = timeBeforeRemoving;
        this.isEdible = isEdible;
        this.saleDate = saleDate;
        this.category = category;
    }

    public Product(String id) {
        this(id, null, null, null, null, null, null, null, null, null, null);
    }
}
package model;

import java.time.LocalDate;

public class ProductOrderSummary {
    private String productId, productName;
    private Double productNetPrice;
    private Integer quantity, saleId;
    private LocalDate saleDate;

    public ProductOrderSummary(String productId, String productName, Double productNetPrice, Integer quantity, Integer saleId, LocalDate saleDate) {
        this.productId = productId;
        this.productName = productName;
        this.productNetPrice = productNetPrice;
        this.quantity = quantity;
        this.saleId = saleId;
        this.saleDate = saleDate;
    }

    public String getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public Double getProductNetPrice() {
        return productNetPrice;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public Integer getSaleId() {
        return saleId;
    }

    public LocalDate getSaleDate() {
        return saleDate;
    }
}
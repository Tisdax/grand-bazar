package model;

import exceptions.InvalidValueException;

import java.time.LocalDate;

public class ProductOrderSummary {
    private String productId, productName;
    private Double productNetPrice;
    private Integer quantity, saleId;
    private LocalDate saleDate;

    public ProductOrderSummary(String productId, String productName, Double productNetPrice, Integer quantity, Integer saleId, LocalDate saleDate) throws InvalidValueException {
        this.productId = productId;
        this.productName = productName;
        setProductNetPrice(productNetPrice);
        setQuantity(quantity);
        setSaleId(saleId);
        this.saleDate = saleDate;
    }

    public void setProductNetPrice(Double netPrice) throws InvalidValueException {
        if (netPrice == null || netPrice <= 0)
            throw new InvalidValueException("Le prix est obligatoire et doit être un nombre positif.", netPrice);
        this.productNetPrice = netPrice;
    }

    public void setQuantity(Integer quantity) throws InvalidValueException {
        if (quantity == null || quantity <= 0)
            throw new InvalidValueException("La quantité est obligatoire et doit être un nombre positif.", quantity);
        this.quantity = quantity;
    }

    public void setSaleId(Integer saleId) throws InvalidValueException {
        if (saleId == null || saleId <= 0)
            throw new InvalidValueException("L'id de la vente est obligatoire et doit être un nombre positif.", saleId);
        this.saleId = saleId;
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
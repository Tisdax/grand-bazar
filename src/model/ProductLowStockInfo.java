package model;

import exceptions.InvalidValueException;

public class ProductLowStockInfo {
    private Integer productMinQuantity, stockQuantity;
    private String productId, productName;

    public ProductLowStockInfo(String productId, String productName, Integer stockQuantity, Integer productMinQuantity) throws InvalidValueException {
        setProductId(productId);
        this.productName = productName;
        setStockQuantity(stockQuantity);
        setProductMinQuantity(productMinQuantity);
    }

    public void setProductId(String productId) throws InvalidValueException {
        if (productId == null || productId.isEmpty())
            throw new InvalidValueException("Veuillez entrez un identifiant", productId);

        if (productId.length() > 13)
            throw new InvalidValueException("L'identifiant doit avoir maximum 13 caractères", productId);

        this.productId = productId;
    }

    public void setProductMinQuantity(Integer productMinQuantity) throws InvalidValueException {
        if (productMinQuantity != null && productMinQuantity <= 0)
            throw new InvalidValueException("La quantité minimale doit être décochée ou être un nombre positif.", productMinQuantity);
        this.productMinQuantity = productMinQuantity;
    }

    public void setStockQuantity(Integer stockQuantity) throws InvalidValueException {
        if (stockQuantity == null || stockQuantity < 0)
            throw new InvalidValueException("La quantité doit être un nombre positif.", stockQuantity);
        this.stockQuantity = stockQuantity;
    }

    public Integer getProductMinQuantity() {
        return productMinQuantity;
    }

    public Integer getStockQuantity() {
        return stockQuantity;
    }

    public String getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }
}

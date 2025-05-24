package model;

import exceptions.InvalidValueException;

public class CommandLine {
    private Integer sale, quantity;
    private String product;

    public CommandLine(Integer sale, String product, Integer quantity) throws InvalidValueException {
        setSale(sale);
        this.product = product;
        setQuantity(quantity);
    }

    public void setSale(Integer sale) throws InvalidValueException {
        if (sale == null || sale <= 0)
            throw new InvalidValueException("L'id de la vente liée est obligatoire et doit être un nombre positif.", sale);
        this.sale = sale;
    }

    public void setQuantity(Integer quantity) throws InvalidValueException {
        if (quantity != null && quantity <= 0)
            throw new InvalidValueException("La quantité doit être un nombre positif.", quantity);
        this.quantity = quantity;
    }

    public Integer getSale() {
        return sale;
    }

    public String getProduct() {
        return product;
    }

    public Integer getQuantity() {
        return quantity;
    }
}
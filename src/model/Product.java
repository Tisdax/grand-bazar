package model;

import controller.ApplicationController;
import exceptions.DAOException;
import exceptions.InvalidValueException;

import java.time.LocalDate;

public class Product {
    private String id, name, categoryName;
    private Double netPrice;
    private Integer vatPercentage, loyaltyPointsNb, minQuantity, promotionMinQuantity, timeBeforeRemoving;
    private Boolean isEdible;
    private LocalDate saleDate;

    public Product(String id, String name, Double netPrice, Integer vatPercentage, Integer loyaltyPointsNb, Integer minQuantity, Integer promotionMinQuantity, Integer timeBeforeRemoving, Boolean isEdible, LocalDate saleDate, String categoryName) throws InvalidValueException, DAOException {
        setId(id);
        setName(name);
        setNetPrice(netPrice);
        this.vatPercentage = vatPercentage;
        setLoyaltyPointsNb(loyaltyPointsNb);
        setMinQuantity(minQuantity);
        setPromotionMinQuantity(promotionMinQuantity);
        setTimeBeforeRemoving(timeBeforeRemoving);
        this.isEdible = isEdible;
        setSaleDate(saleDate);
        this.categoryName = categoryName;
    }

    public Product(String id, String name, Double netPrice, Integer vatPercentage, Integer loyaltyPointsNb, Boolean isEdible, LocalDate saleDate, String categoryName) throws InvalidValueException, DAOException {
        this(id, name, netPrice, vatPercentage, loyaltyPointsNb, null, null, null, isEdible, saleDate, categoryName);
    }

    public void setId(String id) throws InvalidValueException, DAOException {
        if (id == null || id.isEmpty()){
            throw new InvalidValueException("Veuillez entrez un identifiant", id);
        } else {
            if (id.length() > 13){
                throw new InvalidValueException("L'identifiant doit avoir maximum 13 caractères", id);
            } else {
                this.id = id;
            }
        }
    }

    public void setName(String name) throws InvalidValueException {
        if (name == null || name.isEmpty()){
            throw new InvalidValueException("Veuillez entrez un nom au produit", name);
        }
        this.name = name;
    }
    public void setNetPrice(Double netPrice) throws InvalidValueException {
        if (netPrice == null || netPrice <= 0)
            throw new InvalidValueException("Le prix est obligatoire et doit être un nombre positif.", netPrice);
        this.netPrice = netPrice;
    }

    public void setLoyaltyPointsNb(Integer loyaltyPointsNb) throws InvalidValueException {
        if (loyaltyPointsNb == null || loyaltyPointsNb < 0)
            throw new InvalidValueException("Le nombre de points de fidélité doit être un nombre positif.", loyaltyPointsNb);
        this.loyaltyPointsNb = loyaltyPointsNb;
    }

    public void setMinQuantity(Integer minQuantity) throws InvalidValueException {
        if (minQuantity != null && minQuantity <= 0)
            throw new InvalidValueException("La quantité minimale doit être décochée ou être un nombre positif.", minQuantity);
        this.minQuantity = minQuantity;
    }

    public void setPromotionMinQuantity(Integer promotionMinQuantity) throws InvalidValueException {
        if (promotionMinQuantity != null && promotionMinQuantity <= 0)
            throw new InvalidValueException("La quantité minimale en promotion doit être décochée ou être un nombre positif.", promotionMinQuantity);
        this.promotionMinQuantity = promotionMinQuantity;
    }

    public void setTimeBeforeRemoving(Integer timeBeforeRemoving) throws InvalidValueException {
        if (timeBeforeRemoving != null && timeBeforeRemoving <= 0)
            throw new InvalidValueException("Le temps avant de retirer des rayons doit être laissé vide ou être un nombre positif.", timeBeforeRemoving);
        this.timeBeforeRemoving = timeBeforeRemoving;
    }

    public void setSaleDate(LocalDate saleDate) throws InvalidValueException {
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

    public String toString(){
        return "(" + id + ")" + " " + name + " " + "ajouté le " + saleDate;
    }
}
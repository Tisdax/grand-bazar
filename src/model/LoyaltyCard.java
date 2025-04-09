package model;

public class LoyaltyCard {
    private Integer number, totalPoints;
    private Boolean isValid;
    private Customer customer;

    public LoyaltyCard(Integer number, Integer totalPoints, Boolean isValid, Customer customer) {
        this.number = number;
        this.totalPoints = totalPoints;
        this.isValid = isValid;
        this.customer = customer;
    }

    public LoyaltyCard(Integer number) {
        this(number, null, null, null);
    }
}
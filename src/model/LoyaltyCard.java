package model;

public class LoyaltyCard {
    private Integer number, totalPoints, customer;
    private Boolean isValid;

    public LoyaltyCard(Integer number, Integer totalPoints, Boolean isValid, Integer customer) {
        setNumber(number);
        this.totalPoints = totalPoints;
        this.isValid = isValid;
        this.customer = customer;
    }

    public void setNumber(Integer number) {
        if (number >= 0)
            this.number = number;
    }
}
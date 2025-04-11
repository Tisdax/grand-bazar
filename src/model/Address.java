package model;

public class Address {
    private String street, zipCode, name;
    private Integer houseNumber, postal_box_number;

    public Address(String street, String zipCode, String name, Integer houseNumber, Integer postal_box_number) {
        this.street = street;
        this.zipCode = zipCode;
        this.name = name;
        this.houseNumber = houseNumber;
        this.postal_box_number = postal_box_number;
    }

    public Address(String street, String zipCode, String name, Integer houseNumber) {
        this(street, zipCode, name, houseNumber, null);
    }
}
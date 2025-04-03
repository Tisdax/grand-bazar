package model;

public class Address {
    private Locality locality;
    private String street;
    private Integer houseNumber, postal_box_number;

    public Address(Locality locality, String street, Integer houseNumber, Integer postal_box_number) {
        this.locality = locality;
        this.street = street;
        this.houseNumber = houseNumber;
        this.postal_box_number = postal_box_number;
    }
}

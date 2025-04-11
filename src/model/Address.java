package model;

public class Address {
    private String street, localityZipCode, localityName;
    private Integer houseNumber, postal_box_number;

    public Address(String street, String localityZipCode, String localityName, Integer houseNumber, Integer postal_box_number) {
        this.street = street;
        this.localityZipCode = localityZipCode;
        this.localityName = localityName;
        this.houseNumber = houseNumber;
        this.postal_box_number = postal_box_number;
    }

    public Address(String street, String localityZipCode, String localityName, Integer houseNumber) {
        this(street, localityZipCode, localityName, houseNumber, null);
    }
}
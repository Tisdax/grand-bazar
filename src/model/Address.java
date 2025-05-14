package model;

public class Address {
    private String street, localityName;
    private Integer localityZipCode, houseNumber, postal_box_number;

    public Address(String street, Integer localityZipCode, String localityName, Integer houseNumber, Integer postal_box_number) {
        this.street = street;
        setLocalityZipCode(localityZipCode);
        this.localityName = localityName;
        setHouseNumber(houseNumber);
        setPostal_box_number(postal_box_number);
    }

    public Address(String street, Integer localityZipCode, String localityName, Integer houseNumber) {
        this(street, localityZipCode, localityName, houseNumber, null);
    }

    public void setLocalityZipCode(Integer localityZipCode) {
        this.localityZipCode = localityZipCode;
    }

    public void setPostal_box_number(Integer postal_box_number) {
        this.postal_box_number = postal_box_number;
    }

    public void setHouseNumber(Integer houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getStreet() {
        return street;
    }

    public Integer getLocalityZipCode() {
        return localityZipCode;
    }

    public String getLocalityName() {
        return localityName;
    }

    public Integer getHouseNumber() {
        return houseNumber;
    }

    public Integer getPostal_box_number() {
        return postal_box_number;
    }
}
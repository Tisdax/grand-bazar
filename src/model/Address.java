package model;

import exceptions.InvalidAddressException;

public class Address {
    private String street, localityName, houseNumber;
    private Integer localityZipCode, postal_box_number;

    public Address(String street, Integer localityZipCode, String localityName, String houseNumber, Integer postal_box_number) /*throws InvalidAddressException*/ {
        this.street = street;
        setLocalityZipCode(localityZipCode);
        this.localityName = localityName;
        this.houseNumber = houseNumber;
        setPostal_box_number(postal_box_number);
    }

    public Address(String street, Integer localityZipCode, String localityName, String houseNumber) /*throws InvalidAddressException*/ {
        this(street, localityZipCode, localityName, houseNumber, null);
    }

    public void setLocalityZipCode(Integer localityZipCode) /*throws InvalidAddressException*/ {
        //if (localityZipCode == null || localityZipCode <= 0)
            //throw new InvalidAddressException("Le code postal est obligatoire et doit être un nombre positif", localityZipCode);
        this.localityZipCode = localityZipCode;
    }

    public void setPostal_box_number(Integer postal_box_number) {
        //if (postal_box_number != null && postal_box_number <= 0)
            //throw new InvalidAddressException("La boite postale doit être laissée vide ou être un nombre positif", postal_box_number);
        this.postal_box_number = postal_box_number;
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

    public String getHouseNumber() {
        return houseNumber;
    }

    public Integer getPostal_box_number() {
        return postal_box_number;
    }
}
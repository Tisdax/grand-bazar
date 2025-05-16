package model;

import exceptions.InvalidValueException;

public class Address {
    private String street, localityName, houseNumber;
    private Integer localityZipCode, postalBoxNumber;

    public Address(String street, Integer localityZipCode, String localityName, String houseNumber, Integer postalBoxNumber) throws InvalidValueException {
        this.street = street;
        setLocalityZipCode(localityZipCode);
        this.localityName = localityName;
        this.houseNumber = houseNumber;
        setPostalBoxNumber(postalBoxNumber);
    }

    public Address(String street, Integer localityZipCode, String localityName, String houseNumber) throws InvalidValueException {
        this(street, localityZipCode, localityName, houseNumber, null);
    }

    public void setLocalityZipCode(Integer localityZipCode) throws InvalidValueException {
        if (localityZipCode == null || localityZipCode <= 0)
            throw new InvalidValueException("Le code postal est obligatoire et doit être un nombre positif.", localityZipCode);
        this.localityZipCode = localityZipCode;
    }

    public void setPostalBoxNumber(Integer postalBoxNumber) throws InvalidValueException {
        if (postalBoxNumber != null && postalBoxNumber <= 0)
            throw new InvalidValueException("La boite postale doit être laissée vide ou être un nombre positif.", postalBoxNumber);
        this.postalBoxNumber = postalBoxNumber;
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

    public Integer getPostalBoxNumber() {
        return postalBoxNumber;
    }
}
package model;

import exceptions.InvalidValueException;

public class CustomerAddressInfo {
    private String lastName, firstName, street, localityName, houseNumber;
    private Integer postalBoxNumber, zipCode;

    public CustomerAddressInfo(String lastName, String firstName, String street, String localityName, String houseNumber, Integer postalBoxNumber, Integer zipCode) throws InvalidValueException {
        this.lastName = lastName;
        this.firstName = firstName;
        this.street = street;
        this.localityName = localityName;
        this.houseNumber = houseNumber;
        setPostalBoxNumber(postalBoxNumber);
        setZipCode(zipCode);
    }

    public CustomerAddressInfo(String lastName, String firstName, String street, String localityName, String houseNumber, Integer zipCode) throws InvalidValueException {
        this(lastName, firstName, street, localityName, houseNumber, null, zipCode);
    }

    public void setZipCode(Integer zipCode) throws InvalidValueException {
        if (zipCode == null || zipCode <= 0)
            throw new InvalidValueException("Le code postal est obligatoire et doit être un nombre positif.", zipCode);
        this.zipCode = zipCode;
    }

    public void setPostalBoxNumber(Integer postalBoxNumber) throws InvalidValueException {
        if (postalBoxNumber != null && postalBoxNumber <= 0)
            throw new InvalidValueException("La boite postale doit être laissée vide ou être un nombre positif.", postalBoxNumber);
        this.postalBoxNumber = postalBoxNumber;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getStreet() {
        return street;
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

    public Integer getZipCode() {
        return zipCode;
    }
}
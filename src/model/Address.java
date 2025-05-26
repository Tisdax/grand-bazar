package model;

import exceptions.InvalidValueException;

public class Address {
    private String street, localityName, houseNumber;
    private Integer localityZipCode, postalBoxNumber;

    public Address(String street, Integer localityZipCode, String localityName, String houseNumber, Integer postalBoxNumber) throws InvalidValueException {
        setStreet(street);
        setLocalityZipCode(localityZipCode);
        this.localityName = localityName;
        setHouseNumber(houseNumber);
        setPostalBoxNumber(postalBoxNumber);
    }

    public Address(String street, Integer localityZipCode, String localityName, String houseNumber) throws InvalidValueException {
        this(street, localityZipCode, localityName, houseNumber, null);
    }

    public void setStreet(String street) throws InvalidValueException {
        if (street == null || street.isEmpty()){
            throw new InvalidValueException("Veuillez entrez une adresse valide.", street);
        }
        this.street = street;
    }

    public void setHouseNumber(String houseNumber) throws InvalidValueException {
        if (houseNumber == null || houseNumber.isEmpty() || houseNumber.length() > 5) {
            throw new InvalidValueException("Le numéro de maison peut faire 5 caractères maximum et ne peut pas être vide.", houseNumber);
        }
        this.houseNumber = houseNumber;
    }

    public void setLocalityZipCode(Integer localityZipCode) throws InvalidValueException {
        if (localityZipCode == null || localityZipCode  <= 0)
            throw new InvalidValueException("Le code postal est obligatoire et doit être un nombre positif.", localityZipCode);
        this.localityZipCode = localityZipCode;
    }

    public void setPostalBoxNumber(Integer postalBoxNumber) throws InvalidValueException {
        if (postalBoxNumber != null && postalBoxNumber <= 0)
            throw new InvalidValueException("La boite postale doit être décochée ou être un nombre positif.", postalBoxNumber);
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

    public String toString() {
        return street + " " + houseNumber + (postalBoxNumber != null ? " boite n°" + postalBoxNumber : "") + ", " + localityZipCode + " " + localityName;
    }
}
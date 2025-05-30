package model;

import exceptions.InvalidValueException;

public class Address {
    private String street, localityName, houseNumber;
    private Integer localityZipCode, postalBoxNumber;

    public Address(String street, Integer localityZipCode, String localityName, String houseNumber, Integer postalBoxNumber) throws InvalidValueException {
        setStreet(street);
        setLocalityZipCode(localityZipCode);
        setLocalityName(localityName);
        setHouseNumber(houseNumber);
        setPostalBoxNumber(postalBoxNumber);
    }

    public Address(String street, Integer localityZipCode, String localityName, String houseNumber) throws InvalidValueException {
        this(street, localityZipCode, localityName, houseNumber, null);
    }

    public void setStreet(String street) throws InvalidValueException {
        if (street == null || street.isEmpty() || street.length() > 50){
            throw new InvalidValueException("Le nom de rue est obligatoire et peut faire maximum 50 caractères de long.", street);
        }
        this.street = street;
    }

    public void setHouseNumber(String houseNumber) throws InvalidValueException {
        if (houseNumber == null || houseNumber.isEmpty() || houseNumber.length() > 5) {
            throw new InvalidValueException("Le numéro de maison est obligatoire et peut faire maximum 5 caractères de long.", houseNumber);
        }
        this.houseNumber = houseNumber;
    }

    public void setLocalityName(String localityName) throws InvalidValueException {
        if (localityName == null || localityName.length() > 30)
            throw new InvalidValueException("Le nom de localité est obligatoire et peut faire maximum 30 caractères de long.", localityName);
        this.localityName = localityName;
    }

    public void setLocalityZipCode(Integer localityZipCode) throws InvalidValueException {
        if (localityZipCode == null || localityZipCode  <= 0)
            throw new InvalidValueException("Le code postal est obligatoire et doit être un nombre positif.", localityZipCode);
        this.localityZipCode = localityZipCode;
    }

    public void setPostalBoxNumber(Integer postalBoxNumber) throws InvalidValueException {
        if (postalBoxNumber != null && (postalBoxNumber <= 0 || postalBoxNumber > 100))
            throw new InvalidValueException("La boite postale doit être décochée ou être un nombre positif, au maximum 100.", postalBoxNumber);
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
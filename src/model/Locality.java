package model;

import exceptions.InvalidValueException;

public class Locality {
    private Integer zipCode;
    private String name;

    public Locality(Integer zipCode, String name) throws InvalidValueException {
        setZipCode(zipCode);
        this.name = name;
    }

    public void setZipCode(Integer zipCode) throws InvalidValueException {
        if (zipCode == null || zipCode <= 0)
            throw new InvalidValueException("Le code postal est obligatoire et doit être un nombre positif.", zipCode);
        this.zipCode = zipCode;
    }

    public Integer getZipCode() {
        return zipCode;
    }

    public String getName() {
        return name;
    }
}
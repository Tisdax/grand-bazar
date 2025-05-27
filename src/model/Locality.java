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

    public void setName(String name) throws InvalidValueException {
        if (name == null || name.length() > 30)
            throw new InvalidValueException("Le nom de localité est obligatoire et peut faire maximum 30 caractères de long..", name);
        this.name = name;
    }

    public Integer getZipCode() {
        return zipCode;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return zipCode + " " + name;
    }
}
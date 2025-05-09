package model;

public class Locality {
    private String zipCode, name;

    public Locality(String zip_code, String name) {
        this.zipCode = zip_code;
        this.name = name;
    }

    public String getZipCode() {
        return zipCode;
    }

    public String getName() {
        return name;
    }
}
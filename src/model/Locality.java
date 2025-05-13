package model;

public class Locality {
    private Integer zipCode;
    private String name;

    public Locality(Integer zipCode, String name) {
        setZipCode(zipCode);
        this.name = name;
    }

    public void setZipCode(Integer zipCode) {
        if (zipCode > 0)
            this.zipCode = zipCode;
    }

    public Integer getZipCode() {
        return zipCode;
    }

    public String getName() {
        return name;
    }
}
package model;

public class CustomerAddressInfo {
    private String lastName, firstName, street, localityName;
    private Integer houseNumber, postalBoxNumber, zipCode;

    public CustomerAddressInfo(String lastName, String firstName, String street, String localityName, Integer houseNumber, Integer postalBoxNumber, Integer zipCode) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.street = street;
        this.localityName = localityName;
        setHouseNumber(houseNumber);
        setPostalBoxNumber(postalBoxNumber);
        setZipCode(zipCode);
    }

    public CustomerAddressInfo(String lastName, String firstName, String street, String localityName, Integer houseNumber, Integer zipCode) {
        this(lastName, firstName, street, localityName, houseNumber, null, zipCode);
    }

    public void setZipCode(Integer zipCode) {
        this.zipCode = zipCode;
    }

    public void setHouseNumber(Integer houseNumber) {
        this.houseNumber = houseNumber;
    }

    public void setPostalBoxNumber(Integer postalBoxNumber) {
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

    public Integer getHouseNumber() {
        return houseNumber;
    }

    public Integer getPostalBoxNumber() {
        return postalBoxNumber;
    }

    public Integer getZipCode() {
        return zipCode;
    }
}
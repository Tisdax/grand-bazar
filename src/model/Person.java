package model;

public class Person {
    private Integer id, houseNumber;
    private String lastName, firstName, addressStreet, localityZipCode, localityName;

    public Person(Integer id, String lastName, String firstName, String addressStreet, String localityZipCode, String localityName, Integer houseNumber) {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.addressStreet = addressStreet;
        this.localityZipCode = localityZipCode;
        this.localityName = localityName;
        this.houseNumber = houseNumber;
    }
}
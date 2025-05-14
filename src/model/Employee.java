package model;

public class Employee {
    private Integer managerId, id, houseNumber;
    private String lastName, firstName, addressStreet, localityZipCode, localityName;

    public Employee(Integer id, String lastName, String firstName, Integer managerId, String addressStreet, String localityZipCode, String localityName, Integer houseNumber) {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.addressStreet = addressStreet;
        this.localityZipCode = localityZipCode;
        this.localityName = localityName;
        this.houseNumber = houseNumber;
        this.managerId = managerId;
    }

    public Employee(Integer id, String lastName, String firstName, String addressStreet, String localityZipCode, String localityName, Integer houseNumber) {
        this(id, lastName, firstName, null, addressStreet, localityZipCode, localityName, houseNumber);
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setManagerId(Integer managerId) {
        this.managerId = managerId;
    }

    public void setHouseNumber(Integer houseNumber) {
        this.houseNumber = houseNumber;
    }

    public void setLocalityZipCode(String localityZipCode) {
        this.localityZipCode = localityZipCode;
    }
}
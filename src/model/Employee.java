package model;

import exceptions.InvalidValueException;

public class Employee {
    private Integer id, managerId, localityZipCode;
    private String lastName, firstName, localityName, addressStreet, houseNumber;

    public Employee(Integer id, String lastName, String firstName, Integer managerId, Integer localityZipCode, String localityName, String addressStreet, String houseNumber) throws InvalidValueException {
        setId(id);
        setLastName(lastName);
        setFirstName(firstName);
        setManagerId(managerId);
        setLocalityZipCode(localityZipCode);
        setLocalityName(localityName);
        setAddressStreet(addressStreet);
        setHouseNumber(houseNumber);
    }

    public Employee(Integer id, String lastName, String firstName, Integer localityZipCode, String localityName, String addressStreet, String houseNumber) throws InvalidValueException {
        this(id, lastName, firstName, null, localityZipCode, localityName, addressStreet, houseNumber);
    }

    public void setId(Integer id) throws InvalidValueException {
        if (id == null || id <= 0)
            throw new InvalidValueException("L'id est obligatoire et doit être un nombre positif.", id);
        this.id = id;
    }

    public void setLastName(String lastName) throws InvalidValueException {
        if (lastName == null || lastName.isEmpty() || lastName.length() > 50){
            throw new InvalidValueException("Le nom de famille est obligatoire et peut faire maximum 50 caractères de long.", lastName);
        }
        this.lastName = lastName;
    }

    public void setFirstName(String firstName) throws InvalidValueException {
        if (firstName == null || firstName.isEmpty() || firstName.length() > 30){
            throw new InvalidValueException("Le prénom est obligatoire et peut faire maximum 30 caractères de long.", firstName);
        }
        this.firstName = firstName;
    }

    public void setManagerId(Integer managerId) throws InvalidValueException {
        if (managerId != null && managerId <= 0)
            throw new InvalidValueException("L'id du manager doit être laissé vide ou doit être un nombre positif.", managerId);
        this.managerId = managerId;
    }

    public void setLocalityZipCode(Integer localityZipCode) throws InvalidValueException {
        if (localityZipCode == null || localityZipCode <= 0)
            throw new InvalidValueException("Le code postal est obligatoire et doit être un nombre positif.", localityZipCode);
        this.localityZipCode = localityZipCode;
    }

    public void setAddressStreet(String addressStreet) throws InvalidValueException {
        if (addressStreet == null || addressStreet.isEmpty() || addressStreet.length() > 50){
            throw new InvalidValueException("Le nom de rue est obligatoire et peut faire maximum 50 caractères de long.", addressStreet);
        }
        this.addressStreet = addressStreet;
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

    public Integer getId() {
        return id;
    }

    public Integer getManagerId() {
        return managerId;
    }

    public Integer getLocalityZipCode() {
        return localityZipCode;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLocalityName() {
        return localityName;
    }

    public String getAddressStreet() {
        return addressStreet;
    }

    public String getHouseNumber() {
        return houseNumber;
    }
}

package model;

import java.time.LocalDate;

public class Customer {
    private String phone, email, vatNumber, typeName, lastName, firstName, addressStreet, localityZipCode, localityName;
    private LocalDate birthdate;
    private Boolean isSubscribedToNewsLetter;
    private Integer id, houseNumber;

    public Customer(Integer id, String lastName, String firstName, String addressStreet, String localityZipCode, String localityName, Integer houseNumber, String phone, String email, Boolean isSubscribedToNewsLetter, String vatNumber, LocalDate birthdate, String typeName) {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.addressStreet = addressStreet;
        this.localityZipCode = localityZipCode;
        this.localityName = localityName;
        this.houseNumber = houseNumber;
        this.phone = phone;
        this.email = email;
        this.isSubscribedToNewsLetter = isSubscribedToNewsLetter;
        this.vatNumber = vatNumber;
        this.birthdate = birthdate;
        this.typeName = typeName;
    }

    public Customer(Integer id, String lastName, String firstName, LocalDate birthdate, Boolean isSubscribedToNewsLetter, String addressStreet, String localityZipCode, String localityName, Integer houseNumber, String typeName) {
        this(id, lastName, firstName, addressStreet, localityZipCode, localityName, houseNumber, null, null, isSubscribedToNewsLetter, null, birthdate, typeName);
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setVatNumber(String vatNumber) {
        this.vatNumber = vatNumber;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public String getVatNumber() {
        return vatNumber;
    }

    public String getTypeName() {
        return typeName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getAddressStreet() {
        return addressStreet;
    }

    public String getLocalityZipCode() {
        return localityZipCode;
    }

    public String getLocalityName() {
        return localityName;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public Boolean getSubscribedToNewsLetter() {
        return isSubscribedToNewsLetter;
    }

    public Integer getId() {
        return id;
    }

    public Integer getHouseNumber() {
        return houseNumber;
    }
}
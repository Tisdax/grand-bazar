package model;

import exceptions.InvalidValueException;

import java.time.LocalDate;

public class Customer {
    private String phone, email, vatNumber, typeName, lastName, firstName, addressStreet, localityName, houseNumber;
    private LocalDate birthdate;
    private Boolean isSubscribedToNewsLetter;
    private Integer id, localityZipCode;

    public Customer(Integer id, String lastName, String firstName, LocalDate birthdate, String phone, String email, Boolean isSubscribedToNewsLetter, String vatNumber,
                    Integer localityZipCode, String localityName, String addressStreet, String houseNumber, String typeName) throws InvalidValueException {
        setId(id);
        setLastName(lastName);
        setFirstName(firstName);
        setBirthdate(birthdate);
        setPhone(phone);
        setEmail(email);
        this.isSubscribedToNewsLetter = isSubscribedToNewsLetter;
        setVatNumber(vatNumber);
        setLocalityZipCode(localityZipCode);
        this.localityName = localityName;
        this.addressStreet = addressStreet;
        setHouseNumber(houseNumber);
        this.typeName = typeName;
    }

    public Customer(Integer id, String lastName, String firstName, LocalDate birthdate, Boolean isSubscribedToNewsLetter, Integer localityZipCode,
                    String localityName, String addressStreet, String houseNumber, String typeName) throws InvalidValueException {
        this(id, lastName, firstName, birthdate, null, null, isSubscribedToNewsLetter, null, localityZipCode, localityName, addressStreet, houseNumber, typeName);
    }

    public void setId(Integer id) throws InvalidValueException {
        if (id == null || id <= 0)
            throw new InvalidValueException("L'id est obligatoire et doit être un nombre positif.", id);
        this.id = id;
    }

    public void setLastName(String lastName) throws InvalidValueException {
        if (lastName == null || lastName.isEmpty() || lastName.length() > 50){
            throw new InvalidValueException("Veuillez entrer un nom d'une longueur de maximum 50 caractères.", lastName);
        }
        this.lastName = lastName;
    }

    public void setFirstName(String firstName) throws InvalidValueException {
        if (firstName == null || firstName.isEmpty() || firstName.length() > 30){
            throw new InvalidValueException("Veuillez entrer un prénom d'une longueur de maximum 30 caractères.", firstName);
        }
        this.firstName = firstName;
    }

    public void setPhone(String phone) throws InvalidValueException {
        if (phone != null && phone.charAt(phone.length() - 1) == '_'){
            throw new InvalidValueException("Veuillez entrez un numéro de téléphone valide.", phone);
        }
        this.phone = phone;
    }

    public void setEmail(String email) throws InvalidValueException {
        if (email != null && (email.isEmpty() || email.length() > 100 || !email.contains("@"))){
            throw new InvalidValueException("Veuillez entrez une adresse email d'une longueur de maximum 100 caractères contenant un @.", email);
        }
        this.email = email;
    }

    public void setVatNumber(String vatNumber) throws InvalidValueException {
        if (vatNumber != null && vatNumber.charAt(vatNumber.length() - 1) == '_'){
            throw new InvalidValueException("Veuillez entrez un numéro de TVA valide", vatNumber);
        }
        this.vatNumber = vatNumber;
    }

    public void setLocalityZipCode(Integer localityZipCode) throws InvalidValueException {
        if (localityZipCode == null || localityZipCode <= 0)
            throw new InvalidValueException("Le code postal est obligatoire et doit être un nombre positif.", localityZipCode);
        this.localityZipCode = localityZipCode;
    }

    public void setBirthdate(LocalDate birthdate) throws InvalidValueException {
        if (birthdate == null || birthdate.isAfter(LocalDate.now()))
            throw new InvalidValueException("Veuillez entrer une date de naissance valide", birthdate);
        this.birthdate = birthdate;
    }

    public void setHouseNumber(String houseNumber) throws InvalidValueException {
        if (houseNumber == null || houseNumber.length() > 5)
            throw new InvalidValueException("Le numéro de maison peut faire 5 caractères maximum et ne peut pas être vide.", birthdate);
        this.houseNumber = houseNumber;
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

    public Integer getLocalityZipCode() {
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

    public String getHouseNumber() {
        return houseNumber;
    }
}
package model;

import java.time.LocalDate;

public class Customer extends Person {
    private String phone, email, vatNumber, typeName;
    private LocalDate birthdate;
    private Boolean isSubscribedToNewsLetter;

    public Customer(Integer id, String lastName, String firstName, String addressStreet, String localityZipCode, String localityName, Integer houseNumber, String phone, String email, Boolean isSubscribedToNewsLetter, String vatNumber, LocalDate birthdate, String typeName) {
        super(id, lastName, firstName, addressStreet, localityZipCode, localityName, houseNumber);
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
}
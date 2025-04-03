package model;

import java.time.LocalDate;

public class Customer extends Person {
    private String phone, email, vatNumber;
    private LocalDate birthdate;
    private Boolean isSubscribedToNewsLetter;
    private Address address;
    private CustomerType customerType;

    public Customer(Integer id, String lastName, String firstName, String phone, String email, String vatNumber, LocalDate birthdate, Boolean isSubscribedToNewsLetter, Address address, CustomerType customerType) {
        super(id, lastName, firstName);
        this.phone = phone;
        this.email = email;
        this.vatNumber = vatNumber;
        this.birthdate = birthdate;
        this.isSubscribedToNewsLetter = isSubscribedToNewsLetter;
        this.address = address;
        this.customerType = customerType;
    }
}
package model;

import java.time.LocalDate;

public class Customer {
    private Integer id;
    private String lastName, firstName, phone, email, vatNumber;
    private LocalDate birthdate;
    private Boolean isSubscribedToNewsLetter;
    private Address address;
    private CustomerType customerType;

    public Customer(Integer id, String lastName, String firstName, String phone, String email, String vatNumber, LocalDate birthdate, Boolean isSubscribedToNewsLetter, Address address, CustomerType customerType) {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.phone = phone;
        this.email = email;
        this.vatNumber = vatNumber;
        this.birthdate = birthdate;
        this.isSubscribedToNewsLetter = isSubscribedToNewsLetter;
        this.address = address;
        this.customerType = customerType;
    }
}

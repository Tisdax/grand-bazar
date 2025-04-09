package model;

import java.time.LocalDate;

public class Sale {
    private Integer number;
    private Customer customer;
    private LocalDate date;
    private Employee employee;

    public Sale(Integer number, Customer customer, LocalDate date, Employee employee) {
        this.number = number;
        this.customer = customer;
        this.date = date;
        this.employee = employee;
    }

    public Sale(Integer number, Customer customer) {
        this(number, customer, null, null);
    }
}
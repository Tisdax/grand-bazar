package model;

import java.time.LocalDate;

public class Sale {
    private Integer id;
    private Customer customer;
    private LocalDate date;
    private Employee employee;

    public Sale(Integer id, Customer customer, LocalDate date, Employee employee) {
        this.id = id;
        this.customer = customer;
        this.date = date;
        this.employee = employee;
    }
    public Sale(Integer id, LocalDate date, Employee employee) {
        this(id, null, date, employee);
    }

    public Sale(Integer id, Customer customer) {
        this(id, customer, null, null);
    }
}
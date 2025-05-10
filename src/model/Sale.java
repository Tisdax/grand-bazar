package model;

import java.time.LocalDate;

public class Sale {
    private Integer id, employee, customer;
    private LocalDate date;

    public Sale(Integer id, Integer customer, LocalDate date, Integer employee) {
        this.id = id;
        this.customer = customer;
        this.date = date;
        this.employee = employee;
    }

    public Sale(Integer id, LocalDate date, Integer employee) {
        this(id, null, date, employee);
    }

    public Integer getId() {
        return id;
    }

    public Integer getEmployee() {
        return employee;
    }

    public Integer getCustomer() {
        return customer;
    }

    public LocalDate getDate() {
        return date;
    }
}
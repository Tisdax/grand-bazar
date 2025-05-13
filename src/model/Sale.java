package model;

import java.time.LocalDate;

public class Sale {
    private Integer id, employee, customer;
    private LocalDate date;

    public Sale(Integer id, Integer customer, LocalDate date, Integer employee) {
        setId(id);
        setCustomer(customer);
        this.date = date;
        setEmployee(employee);
    }

    public Sale(Integer id, LocalDate date, Integer employee) {
        this(id, null, date, employee);
    }

    public void setId(Integer id) {
        if (id >= 0)
            this.id = id;
    }

    public void setEmployee(Integer employee) {
        if (employee >= 0)
            this.employee = employee;
    }

    public void setCustomer(Integer customer) {
        if (customer >= 0)
            this.customer = customer;
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
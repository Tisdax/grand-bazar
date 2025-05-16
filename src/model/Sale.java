package model;

import exceptions.InvalidValueException;

import java.time.LocalDate;

public class Sale {
    private Integer id, employee, customer;
    private LocalDate date;

    public Sale(Integer id, Integer customer, LocalDate date, Integer employee) throws InvalidValueException {
        setId(id);
        setCustomer(customer);
        this.date = date;
        setEmployee(employee);
    }

    public Sale(Integer id, LocalDate date, Integer employee) throws InvalidValueException {
        this(id, null, date, employee);
    }

    public void setId(Integer id) throws InvalidValueException {
        if (id == null || id <= 0)
            throw new InvalidValueException("L'id est obligatoire et doit être un nombre positif.", id);
        this.id = id;
    }

    public void setEmployee(Integer employee) throws InvalidValueException {
        if (employee >= 0)
            throw new InvalidValueException("L'id employé est obligatoire et doit être un nombre positif.", employee);
        this.employee = employee;
    }

    public void setCustomer(Integer customer) throws InvalidValueException {
        if (customer >= 0)
            throw new InvalidValueException("L'id client doit être laissé vide ou être un nombre positif.", customer);
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
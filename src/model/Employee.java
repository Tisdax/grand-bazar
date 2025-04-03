package model;

public class Employee extends Person {
    private Employee manager;
    private Address address;

    public Employee(Integer id, String lastName, String firstName, Employee manager, Address address) {
        super(id, lastName, firstName);
        this.manager = manager;
        this.address = address;
    }
}
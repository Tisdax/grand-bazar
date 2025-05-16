package model;

import javax.management.relation.Role;

public class EmployeeRole {
    private Employee employee;
    private EmployeeFunction employeeFunction;

    public EmployeeRole(Employee employee, EmployeeFunction employeeFunction) {
        this.employee = employee;
        this.employeeFunction = employeeFunction;
    }
}

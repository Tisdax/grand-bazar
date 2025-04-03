package model;

public class Person {
    private Integer id;
    private String lastName, firstName;

    public Person(Integer id, String lastName, String firstName) {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
    }
}
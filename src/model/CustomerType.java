package model;

public class CustomerType {
    private String name, description;

    public CustomerType(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public CustomerType(String name) {
        this(name, null);
    }
}
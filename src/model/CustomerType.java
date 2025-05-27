package model;

import exceptions.InvalidValueException;

public class CustomerType {
    private String name, description;

    public CustomerType(String name, String description) throws InvalidValueException {
        setName(name);
        this.description = description;
    }

    public void setName(String name) throws InvalidValueException {
        if (name == null || name.length() > 20)
            throw new InvalidValueException("Le nom de type de client est obligatoire et peut faire maximum 20 caractères de long.", name);
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
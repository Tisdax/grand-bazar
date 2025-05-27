package model;

import exceptions.InvalidValueException;

public class ProductCategory {
    private String name, description;

    public ProductCategory(String name, String description) throws InvalidValueException {
        setName(name);
        this.description = description;
    }

    public void setName(String name) throws InvalidValueException {
        if (name == null || name.length() > 30)
            throw new InvalidValueException("Le nom de la catégorie produit est obligatoire et peut faire maximum 30 caractères de long.", name);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
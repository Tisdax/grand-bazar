package controller;

import businessLogic.CategoryManager;
import businessLogic.ProductManager;
import exceptions.DBAccesException;
import model.Product;
import model.ProductCategory;

import java.time.*;
import java.util.ArrayList;

import javax.swing.*;
import java.util.Date;

public class ApplicationController {
    private CategoryManager categoryManager;
    private ProductManager productManager;

    public ApplicationController(){
        categoryManager = new CategoryManager();
        productManager = new ProductManager();
    }

    public ArrayList<ProductCategory> getAllCategory() throws DBAccesException {
        return categoryManager.getAllCategory();
    }

    public void addProduct(JTextField idField, JTextField nameField, JTextField netPriceField, JComboBox vatComboBox, JSpinner loyaltyPointsSpinner, JSpinner minQuantSpinner, JSpinner promotionQuantSpinner, JSpinner timeBeforeRemovingSpinner, JCheckBox isEdibleCheckBox, JSpinner saleDateSpinner, JComboBox categoryComboBox) throws DBAccesException {
        String id = idField.getText();
        String name = nameField.getText();
        Double netPrice = Double.valueOf(netPriceField.getText());
        Integer vat = (Integer) vatComboBox.getSelectedItem();
        Integer loyaltyPoints = (Integer) loyaltyPointsSpinner.getValue();
        Integer minQuant = (Integer) minQuantSpinner.getValue();
        Integer promotionMinQuant = (Integer) promotionQuantSpinner.getValue();
        Integer timeBeforeRemoving = (Integer) timeBeforeRemovingSpinner.getValue();
        Boolean isEdible = isEdibleCheckBox.isSelected();
        LocalDate saleDate = ((Date) saleDateSpinner.getValue()).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        String category = ((ProductCategory) categoryComboBox.getSelectedItem()).getName();
        productManager.addProduct(new Product(id , name, netPrice, vat, loyaltyPoints, minQuant, promotionMinQuant, timeBeforeRemoving, isEdible, saleDate, category));
    }

    public int deletProduct(String productId) throws DBAccesException {
        return productManager.deleteProduct(productId);
    }

    public void updateProduct(Product product) throws DBAccesException {
        productManager.updateProduct(product);
    }

    public ArrayList<Product> productList() throws DBAccesException {
        return productManager.productList();
    }
}

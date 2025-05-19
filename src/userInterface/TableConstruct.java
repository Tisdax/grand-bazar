package userInterface;

import DAO.CustomerDBAccess;
import DAO.ProductDBAccess;
import controller.ApplicationController;
import exceptions.DAOException;
import exceptions.InvalidValueException;
import model.Customer;
import model.Product;
import model.ProductCategory;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.Stack;

public class TableConstruct {
    DefaultTableModel tableModel;
    JTable table;
    ApplicationController controller;

    public TableConstruct(String[] columnNames) {
        tableModel = new DefaultTableModel();
        table = new JTable(tableModel);
        controller = new ApplicationController();

        for (String columnName : columnNames) {
            tableModel.addColumn(columnName);
        }
    }
    public void fillProductTable() {
        try {
            ArrayList<Product> products = controller.productList();
            for(Product product : products) {
                tableModel.addRow(new Object[] {
                        product.getId(), product.getName(), product.getNetPrice(), product.getVatPercentage(),
                        product.getLoyaltyPointsNb(), product.getEdible(), product.getMinQuantity(), product.getPromotionMinQuantity(),
                        product.getSaleDate(), product.getTimeBeforeRemoving(), product.getCategoryName()
                });
            }

        } catch (DAOException | InvalidValueException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }
    public void fillCustomerTable() {
        try {
            ArrayList<Customer> customers = controller.customerList();
            for(Customer customer : customers) {
                tableModel.addRow(new Object[] {
                        customer.getId(), customer.getLastName(), customer.getFirstName(), customer.getBirthdate(), customer.getPhone(),
                        customer.getEmail(), customer.getSubscribedToNewsLetter(), customer.getVatNumber(), customer.getLocalityZipCode(),
                        customer.getLocalityName(), customer.getAddressStreet(), customer.getHouseNumber(), customer.getTypeName()
                });
            }
        } catch (DAOException | InvalidValueException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }
    public void fillStockTable() {
        try {
            ArrayList<ProductCategory> categories = controller.getAllCategory();
            for (ProductCategory category : categories) {
                tableModel.addRow(new Object[] {
                     category.getName(), category.getDescription()
                });
            }
        } catch (DAOException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }

    public JTable getTable() {
        return table;
    }
    public void refreshTable() {
        tableModel.setRowCount(0);
        tableModel.fireTableDataChanged();
    }
}

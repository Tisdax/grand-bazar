package userInterface;

import DAO.CustomerDBAccess;
import DAO.ProductDBAccess;
import exceptions.DAOException;
import exceptions.InvalidValueException;
import model.Customer;
import model.Product;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

public class TableConstruct {
    String[] columnNames;
    DefaultTableModel tableModel;
    JTable table;

    public TableConstruct(String[] columnNames) {
        tableModel = new DefaultTableModel();
        table = new JTable(tableModel);

        for (String columnName : columnNames) {
            tableModel.addColumn(columnName);
        }
    }
    public void fillProductTable(ProductDBAccess productDBAccess) {
        try {
            ArrayList<Product> products = productDBAccess.productList();
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
    public void fillCustomerTable(CustomerDBAccess customerDBAccess) {
        try {
            ArrayList<Customer> customers = customerDBAccess.customerList();
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

    public JTable getTable() {
        return table;
    }
}

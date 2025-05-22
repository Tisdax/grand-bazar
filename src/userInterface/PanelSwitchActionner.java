package userInterface;

import controller.ApplicationController;
import exceptions.DAOException;
import model.Address;
import model.Customer;
import model.CustomerDeletionMode;
import model.Product;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.Callable;
import java.util.function.Supplier;

public class PanelSwitchActionner {
    private ApplicationController controller;

    public PanelSwitchActionner() {
        controller = new ApplicationController();
    }


    // Buttons
    public JButton createButton(String buttonLabel, Callable<JPanel> callablePanel, int width, int height) {
        JButton button = new JButton(buttonLabel);
        button.setPreferredSize(new Dimension(width, height));
        button.addActionListener(e -> {
            try {
                JPanel panel = callablePanel.call();
                addPanelToFrameTest(panel);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        });
        return button;
    }
    public JButton createButton(String buttonLabel, Callable<JPanel> callablePanel) {
        return createButton(buttonLabel, callablePanel, 250, 60);
    }


    // Customer buttons :
    public JButton createEditCustomerButton(int width, int height) {
        JButton button = new JButton("Modifier un client");
        button.setPreferredSize(new Dimension(width, height));
        button.addActionListener(e -> {
            editCustomerActionner();
        });
        return button;
    }
    public JButton createEditCustomerButton() {
        return createEditCustomerButton(250, 60);
    }

    public JButton createDeleteCustomerButton(int width, int height) {
        JButton button = new JButton("Supprimer un client");
        button.setPreferredSize(new Dimension(width, height));
        button.addActionListener(e -> {
            deleteCustomerActionner();
        });
        return button;
    }
    public JButton createDeleteCustomerButton() {
        return createDeleteCustomerButton(250, 60);
    }

    // Product buttons :
    public JButton createEditProductButton(int width, int height) {
        JButton button = new JButton("Modifier un produit");
        button.setPreferredSize(new Dimension(width, height));
        button.addActionListener(e -> {
            editProductActionner();
        });
        return button;
    }
    public JButton createEditProductButton() {
        return createEditProductButton(250, 60);
    }


    // MenuItems
    public JMenuItem createMenuItem(String menuItemLabel, Callable<JPanel> callablePanel) {
        JMenuItem menuItem = new JMenuItem(menuItemLabel);
        menuItem.addActionListener(e -> {
            try {
                JPanel panel = callablePanel.call();
                addPanelToFrameTest(panel);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        });
        return menuItem;
    }
    public JMenuItem createEditCustomerMenuItem() {
        JMenuItem menuItem = new JMenuItem("Modifier un client");
        menuItem.addActionListener(e -> {
            editCustomerActionner();
        });
        return menuItem;
    }

    //
    private void addPanelToFrameTest(JPanel panel) {
        MainWindow mainWindow = MainWindow.getInstance();
        mainWindow.getContentPane().removeAll();
        mainWindow.add(panel);
        mainWindow.revalidate();
        mainWindow.repaint();
    }

    private void editCustomerActionner() {
        try {
            CustomerForm customerForm = new CustomerForm();
            int inputID = Integer.parseInt(JOptionPane.showInputDialog("ID du client à modifier :"));
            Customer customer = controller.getCustomer(inputID);
            customerForm.fillCustomerForm(customer);
            addPanelToFrameTest(customerForm);

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }
    private void deleteCustomerActionner() {
        try {
            CustomerDeletionMode deletionMode;
            Object[] options = {"Supprimer uniquement client", "Supprimmer client et ses achats", "Annuler"};
            int inputID = Integer.parseInt(JOptionPane.showInputDialog("ID du client à supprimer :"));
            int choice = JOptionPane.showOptionDialog(null, "Il y a potentiellement des achats liés à ce client",
                    "Attention", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[0]);

            if (choice == 0) {
                deletionMode = CustomerDeletionMode.REMOVE_FROM_SALES;
                controller.deleteCustomer(inputID, deletionMode);
            } else if (choice == 1) {
                deletionMode = CustomerDeletionMode.DELETE_SALES;
                controller.deleteCustomer(inputID, deletionMode);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }
    private void editProductActionner() {
        try {
            ProductForm productForm = new ProductForm();
            String inputID = JOptionPane.showInputDialog("ID du produit à modifier :");
            Product product = controller.getProduct(inputID);
            productForm.fillProductForm(product);
            addPanelToFrameTest(productForm);

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }
}
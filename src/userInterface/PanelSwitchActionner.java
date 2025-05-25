package userInterface;

import controller.ApplicationController;
import exceptions.DAOException;
import model.Customer;
import model.CustomerDeletionMode;
import model.Product;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.Callable;

public class PanelSwitchActionner {
    private static final int BUTTON_WIDTH = 350;
    private static final int BUTTON_HEIGHT = 80;
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
        return createButton(buttonLabel, callablePanel, BUTTON_WIDTH, BUTTON_HEIGHT);
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
        return createEditCustomerButton(BUTTON_WIDTH, BUTTON_HEIGHT);
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
        return createDeleteCustomerButton(BUTTON_WIDTH, BUTTON_HEIGHT);
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
        return createEditProductButton(BUTTON_WIDTH, BUTTON_HEIGHT);
    }

    public JButton createDeleteProductButton(int width, int height) {
        JButton button = new JButton("Supprimer un produit");
        button.setPreferredSize(new Dimension(width, height));
        button.addActionListener(e -> {
            deleteProductActionner();
        });
        return button;
    }
    public JButton createDeleteProductButton() {
        return createDeleteProductButton(BUTTON_WIDTH, BUTTON_HEIGHT);
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

    // Customer MenuItems :
    public JMenuItem createEditCustomerMenuItem() {
        JMenuItem menuItem = new JMenuItem("Modifier un client");
        menuItem.addActionListener(e -> {
            editCustomerActionner();
        });
        return menuItem;
    }
    public JMenuItem createDeleteCustomerMenuItem() {
        JMenuItem menuItem = new JMenuItem("Supprimer un client");
        menuItem.addActionListener(e -> {
            deleteCustomerActionner();
        });
        return menuItem;
    }

    // Product MenuItems :
    public JMenuItem createEditProductMenuItem() {
        JMenuItem menuItem = new JMenuItem("Modifier un produit");
        menuItem.addActionListener(e -> {
            editProductActionner();
        });
        return menuItem;
    }
    public JMenuItem createDeleteProductMenuItem() {
        JMenuItem menuItem = new JMenuItem("Supprimer un produit");
        menuItem.addActionListener(e -> {
            deleteProductActionner();
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

    // Customer methods
    private void editCustomerActionner() {
        CustomerForm customerForm = new CustomerForm();
        String inputID = JOptionPane.showInputDialog("ID du client à modifier :");
        if (inputID == null){
            return;
        }

        int inputIdInt;
        try {
            inputIdInt = Integer.parseInt(inputID);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Veuillez entrer un identifiant ne contenant que des chiffres entiers", "Erreur", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            if (controller.customerExistsById(inputIdInt)) {
                try {
                    Customer customer = controller.findCustomerById(inputIdInt);
                    customerForm.fillCustomerForm(customer);
                    addPanelToFrameTest(customerForm);

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Cet identifiant n'existe pas veuillez essayer avec un identifiant valide", "Erreur",JOptionPane.ERROR_MESSAGE);
            }
        } catch (DAOException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }
    private void deleteCustomerActionner() {
        CustomerDeletionMode deletionMode;
        Object[] options = {"Supprimer uniquement client", "Supprimmer client et ses achats", "Annuler"};
        String inputID = JOptionPane.showInputDialog("ID du client à supprimer :");
        if (inputID == null) {
            return;
        }

        int inputIdInt;
        try {
            inputIdInt = Integer.parseInt(inputID);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Veuillez entrer un identifiant ne contenant que des chiffres entiers", "Erreur", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            if (controller.customerExistsById(inputIdInt)) {
                try {
                    int choice = JOptionPane.showOptionDialog(null, "Il y a potentiellement des achats liés à ce client",
                            "Attention", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[0]);

                    if (choice == 0) {
                        deletionMode = CustomerDeletionMode.REMOVE_FROM_SALES;
                        controller.deleteCustomer(inputIdInt, deletionMode);
                    } else if (choice == 1) {
                        deletionMode = CustomerDeletionMode.DELETE_SALES;
                        controller.deleteCustomer(inputIdInt, deletionMode);
                    }
                } catch(Exception ex){
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Cet identifiant n'existe pas veuillez essayer avec un identifiant valide", "Erreur",JOptionPane.ERROR_MESSAGE);
            }
        } catch (DAOException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }
    // Product methods
    private void editProductActionner() {
        ProductForm productForm = new ProductForm();
        String inputID = JOptionPane.showInputDialog("ID du produit à modifier :");
        if (inputID == null){
            return;
        }

        try {
            if (controller.productExistsById(inputID)) {
                try {
                    Product product = controller.findProductById(inputID);
                    productForm.fillProductForm(product);
                    addPanelToFrameTest(productForm);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Cet identifiant n'existe pas veuillez essayer avec un identifiant valide", "Erreur",JOptionPane.ERROR_MESSAGE);
            }
        } catch (DAOException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }
    private void deleteProductActionner() {
        Object[] options = {"Supprimer produit", "Annuler"};
        String inputID = JOptionPane.showInputDialog("ID du produit à supprimer :");
        if (inputID == null){
            return;
        }

        try {
            if (controller.productExistsById(inputID)){
                int choice = JOptionPane.showOptionDialog(null, "Etes-vous sur de vouloir supprimer ce produit ?", "Attention", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[0]);

                if(choice == 0) {
                    try {
                        controller.deleteProduct(inputID);
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
                    }
                }
            } else {
                JOptionPane.showMessageDialog(null, "Cet identifiant n'existe pas veuillez essayer avec un identifiant valide", "Erreur",JOptionPane.ERROR_MESSAGE);
            }
        } catch (DAOException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }
}
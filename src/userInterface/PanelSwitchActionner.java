package userInterface;

import controller.ApplicationController;
import exceptions.DAOException;
import model.Address;
import model.Customer;

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
    // Surcharge avec dimensions par défaut
    public JButton createButton(String buttonLabel, Callable<JPanel> callablePanel) {
        return createButton(buttonLabel, callablePanel, 250, 60);
    }

    public JButton createEditCustomerButton(int width, int height) {
        JButton button = new JButton("Modifier un client");
        button.setPreferredSize(new Dimension(width, height));
        button.addActionListener(e -> {
            editCustomerActionner();
        });
        return button;
    }
    // Surcharge avec dimensions par défaut
    public JButton createEditCustomerButton() {
        return createEditCustomerButton(250, 60);
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
}
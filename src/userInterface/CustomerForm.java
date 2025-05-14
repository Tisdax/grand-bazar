package userInterface;

import controller.ApplicationController;
import exceptions.DBAccesException;
import model.Customer;
import model.Product;

import javax.swing.*;
import java.awt.*;

public class CustomerForm extends JPanel {
    private JPanel titlePanel, formPanel, buttonPanel;
    private JLabel titleLabel, idLabel, LastNameLabel, firstName, adresseStreetLabel, localityZipCodeLabel, localityNameLabel,
            houseNumberLabel, phoneLabel, emailLabel, isSubscrideLabel, vatNumberLabel, birthdayLabel, typeLabel;
    private JTextField idLabelField, lastNameField, firstNameField, adresseStreetField, localidtyZipCodeField, localityNameField, houseNumberField, emailField, vatNumberField;
    private JSpinner phoneNumberSpinner, birthdaySpinner;
    private JCheckBox isSubscrideCheckbox;
    private JComboBox typeComboBox;
    private JButton addButton, updateButton;
    private ApplicationController controller;

    public CustomerForm() throws DBAccesException {
        controller = new ApplicationController();

        this.setLayout(new BorderLayout());
        titlePanel = new JPanel();
        formPanel = new JPanel(new GridLayout());
        buttonPanel = new JPanel();
        this.add(titlePanel, BorderLayout.NORTH);
        this.add(formPanel, BorderLayout.CENTER);
        this.add(buttonPanel, BorderLayout.SOUTH);

        titleLabel = new JLabel("Formulaire de client");
        Font font = new Font("Arial", Font.BOLD, 20);
        titleLabel.setFont(font);
        titlePanel.add(titleLabel);


        addButton.addActionListener(e -> {
            try {
                controller.addProduct(transformCustomer(idLabelField, lastNameField, firstNameField, adresseStreetField, localidtyZipCodeField, localityNameField, houseNumberField,
                        emailField, vatNumberField, phoneNumberSpinner, birthdaySpinner, isSubscrideCheckbox, typeComboBox));
            } catch (DBAccesException ex) {
                JOptionPane.showMessageDialog(null, ex.getDescription(), "Problème lors de l'ajout du client", JOptionPane.ERROR_MESSAGE);
            }
)
        });

        updateButton.addActionListener(e -> {
            try {
                controller.updateProduct(transformCustomer(idLabelField, lastNameField, firstNameField, adresseStreetField, localidtyZipCodeField, localityNameField, houseNumberField,
                        emailField, vatNumberField, phoneNumberSpinner, birthdaySpinner, isSubscrideCheckbox, typeComboBox));
            } catch (DBAccesException ex) {
                JOptionPane.showMessageDialog(null, ex.getDescription(), "Problème lors de la mise à jour du client", JOptionPane.ERROR_MESSAGE);
            }
        });

    }

    private Product transformCustomer(JTextField idLabelField, JTextField lastNameField, JTextField firstNameField, JTextField adresseStreetField,
                                       JTextField localidtyZipCodeField, JTextField localityNameField, JTextField houseNumberField, JTextField emailField,
                                       JTextField vatNumberField, JSpinner phoneNumberSpinner, JSpinner birthdaySpinner, JCheckBox isSubscrideCheckbox, JComboBox typeComboBox){

        return new Product();
    }

    private void fillCustomerForm(Customer customer){
        updateButton();

    }

    public void updateButton(){
        buttonPanel.removeAll();
        buttonPanel.add(updateButton);
        revalidate();
        repaint();
    }
}
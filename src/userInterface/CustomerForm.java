package userInterface;

import controller.ApplicationController;
import exceptions.DAOException;
import model.Customer;

import javax.swing.*;
import java.awt.*;

public class CustomerForm extends JPanel {
    private JPanel titlePanel, formPanel, buttonPanel;
    private JLabel titleLabel, idLabel, lastNameLabel, firstNameLabel, adresseStreetLabel, loclalityLabel,
            houseNumberLabel, phoneLabel, emailLabel, isSubscrideLabel, vatNumberLabel, birthdayLabel, typeLabel;
    private JTextField idField, lastNameField, firstNameField, adresseStreetField, houseNumberField, emailField, vatNumberField;
    private JSpinner phoneNumberSpinner, birthdaySpinner;
    private JCheckBox isSubscrideCheckbox;
    private JComboBox localityComboBox, typeComboBox;
    private JButton addButton, updateButton;
    private ApplicationController controller;

    public CustomerForm() throws DAOException {
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

        idLabel = new JLabel("Identifiant client");
        idField = new JTextField();
        formPanel.add(idLabel);
        formPanel.add(idField);

        lastNameLabel = new JLabel("Nom");
        lastNameField = new JTextField();
        formPanel.add(lastNameLabel);
        formPanel.add(lastNameField);

        firstNameLabel = new JLabel("Prénom");
        firstNameField = new JTextField();
        formPanel.add(firstNameLabel);
        formPanel.add(firstNameField);



        addButton = new JButton("Ajouter client");
        updateButton = new JButton("Modifier client");
        buttonPanel.add(addButton);

//        addButton.addActionListener(e -> {
//            try {
//              controller.addCustomer(transformCustomer(idLabelField, lastNameField, firstNameField, adresseStreetField, localidtyZipCodeField, localityNameField, houseNumberField,
//                        emailField, vatNumberField, phoneNumberSpinner, birthdaySpinner, isSubscrideCheckbox, typeComboBox));
//            } catch (DBAccesException ex) {
//                JOptionPane.showMessageDialog(null, ex.getDescription(), "Problème lors de l'ajout du client", JOptionPane.ERROR_MESSAGE);
//            }
//)
//        });
//
//        updateButton.addActionListener(e -> {
//            try {

//               controller.updateCustomer(transformCustomer(idLabelField, lastNameField, firstNameField, adresseStreetField, localidtyZipCodeField, localityNameField, houseNumberField,
//                        emailField, vatNumberField, phoneNumberSpinner, birthdaySpinner, isSubscrideCheckbox, typeComboBox));
//            } catch (DBAccesException ex) {
//                JOptionPane.showMessageDialog(null, ex.getDescription(), "Problème lors de la mise à jour du client", JOptionPane.ERROR_MESSAGE);
//            }
//        });

    }

    private void transformAddress(JComboBox localityComboBox, JTextField adresseStreetField, JTextField houseNumberField){

    }

    private void transformCustomer(JTextField idField, JTextField lastNameField, JTextField firstNameField, JTextField adresseStreetField,
                                       JTextField localidtyZipCodeField, JTextField localityNameField, JTextField houseNumberField, JTextField emailField,
                                       JTextField vatNumberField, JSpinner phoneNumberSpinner, JSpinner birthdaySpinner, JCheckBox isSubscrideCheckbox, JComboBox typeComboBox){

    }

    private void fillCustomerForm(Customer customer){
        updateButton();

    }

    private void updateButton(){
        buttonPanel.removeAll();
        buttonPanel.add(updateButton);
        revalidate();
        repaint();
    }
}
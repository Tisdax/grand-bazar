package userInterface;

import controller.ApplicationController;
import exceptions.DAOException;
import exceptions.InvalidValueException;
import model.Customer;

import javax.swing.*;
import java.awt.*;
import java.util.Calendar;

public class CustomerForm extends JPanel {
    private JPanel titlePanel, formPanel, buttonPanel;
    private JLabel titleLabel, idLabel, lastNameLabel, firstNameLabel, addressStreetLabel, localityLabel, houseNumberLabel, phoneLabel, emailLabel,
            isSubscrideLabel, vatNumberLabel, birthdayLabel, typeLabel, phoneNumberCBLabel, emailCBLabel, vatNumberCBLabel;
    private JTextField idField, lastNameField, firstNameField, addressStreetField, houseNumberField, emailField, vatNumberField;
    private JSpinner phoneNumberSpinner, birthdaySpinner;
    private JCheckBox isSubscrideCheckbox, phoneNumberCheckBox, emailCheckBox, vatNumberCheckBox;
    private JComboBox localityComboBox, typeComboBox;
    private JButton addButton, updateButton;
    private ApplicationController controller;

    public CustomerForm() throws DAOException {
        controller = new ApplicationController();

        this.setLayout(new BorderLayout());
        titlePanel = new JPanel();
        formPanel = new JPanel(new GridLayout(6, 2, 10, 10));
        buttonPanel = new JPanel();
        this.add(titlePanel, BorderLayout.NORTH);
        this.add(formPanel, BorderLayout.CENTER);
        this.add(buttonPanel, BorderLayout.SOUTH);

        // TITLE PANEL
        titleLabel = new JLabel("Formulaire de client", SwingConstants.RIGHT);
        Font font = new Font("Arial", Font.BOLD, 20);
        titleLabel.setFont(font);
        titlePanel.add(titleLabel);

        // FORM PANEL
        idLabel = new JLabel("Identifiant client", SwingConstants.RIGHT);
        idField = new JTextField();
        idField.setText(String.valueOf(controller.lastId() + 1));
        idField.setEnabled(false);
        formPanel.add(idLabel);
        formPanel.add(idField);

        lastNameLabel = new JLabel("Nom", SwingConstants.RIGHT);
        lastNameField = new JTextField();
        formPanel.add(lastNameLabel);
        formPanel.add(lastNameField);

        firstNameLabel = new JLabel("Prénom", SwingConstants.RIGHT);
        firstNameField = new JTextField();
        formPanel.add(firstNameLabel);
        formPanel.add(firstNameField);

        addressStreetLabel = new JLabel("Nom de la rue", SwingConstants.RIGHT);
        addressStreetField = new JTextField();
        formPanel.add(addressStreetLabel);
        formPanel.add(addressStreetField);

        // Locality
        // Locality
        // Locality

        houseNumberLabel = new JLabel("Numéro de maison", SwingConstants.RIGHT);
        houseNumberField = new JTextField();
        formPanel.add(houseNumberLabel);
        formPanel.add(houseNumberField);

        phoneLabel = new JLabel("Numéro de téléphone");
        phoneNumberSpinner = new JSpinner(new SpinnerNumberModel(0, null, null, 1));
        phoneNumberSpinner.setEditor(new JSpinner.NumberEditor(phoneNumberSpinner, "0000/00/00/00"));
        formPanel.add(phoneLabel);
        formPanel.add(phoneNumberSpinner);


        // BUTTON PANEL
        addButton = new JButton("Ajouter client");
        updateButton = new JButton("Modifier client");
        buttonPanel.add(addButton);

//        addButton.addActionListener(e -> {
//            try {
//              controller.addCustomer(transformCustomer(idField, lastNameField, firstNameField, addressStreetField, localityComboBox, houseNumberField, emailField, vatNumberField, phoneNumberSpinner, birthdaySpinner, isSubscrideCheckbox, typeComboBox));
//              JOptionPane.showMessageDialog(null, "Client ajouté", "Réussite", JOptionPane.INFORMATION_MESSAGE);
//               emptyForm(idField, lastNameField, firstNameField, addressStreetField, localityComboBox, houseNumberField, emailField, vatNumberField, phoneNumberSpinner, birthdaySpinner, isSubscrideCheckbox, typeComboBox);
//            } catch (InvalidValueException ex) {
//                JOptionPane.showMessageDialog(null, ex.getMessage(), "Problème lors de l'ajout du client", JOptionPane.ERROR_MESSAGE);
//            }
//)
//        });
//
//        updateButton.addActionListener(e -> {
//            try {
//               controller.updateCustomer(transformCustomer(idField, lastNameField, firstNameField, addressStreetField, localityComboBox, houseNumberField, emailField, vatNumberField, phoneNumberSpinner, birthdaySpinner, isSubscrideCheckbox, typeComboBox));
//               JOptionPane.showMessageDialog(null, "Client modifié", "Réussite", JOptionPane.INFORMATION_MESSAGE);
//               emptyForm(idField, lastNameField, firstNameField, addressStreetField, localityComboBox, houseNumberField, emailField, vatNumberField, phoneNumberSpinner, birthdaySpinner, isSubscrideCheckbox, typeComboBox);
//            } catch (InvalidValueException ex) {
//                JOptionPane.showMessageDialog(null, ex.getMessage(), "Problème lors de la mise à jour du client", JOptionPane.ERROR_MESSAGE);
//            }
//        });

    }

    private void transformAddress(JComboBox localityComboBox, JTextField adresseStreetField, JTextField houseNumberField){

    }

//    private Customer transformCustomer(JTextField idField, JTextField lastNameField, JTextField firstNameField, JTextField adresseStreetField,
//                                       JComboBox localityComboBox, JTextField houseNumberField, JTextField emailField,
//                                       JTextField vatNumberField, JSpinner phoneNumberSpinner, JSpinner birthdaySpinner, JCheckBox isSubscrideCheckbox, JComboBox typeComboBox){
//
//    }

    private void fillCustomerForm(Customer customer){
        updateButton();

    }

    private void emptyForm(JTextField idField, JTextField lastNameField, JTextField firstNameField, JTextField adresseStreetField,
                           JComboBox localityComboBox, JTextField houseNumberField, JTextField emailField, JTextField vatNumberField, JSpinner phoneNumberSpinner, JSpinner birthdaySpinner, JCheckBox isSubscrideCheckbox, JComboBox typeComboBox){

    }

    private void updateButton(){
        buttonPanel.removeAll();
        buttonPanel.add(updateButton);
        revalidate();
        repaint();
    }
}
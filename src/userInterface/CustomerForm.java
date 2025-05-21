package userInterface;

import controller.ApplicationController;
import exceptions.DAOException;
import exceptions.InvalidValueException;
import model.Address;
import model.Customer;
import model.Locality;
import model.ProductCategory;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

public class CustomerForm extends JPanel {
    private JPanel titlePanel, formPanel, buttonPanel;
    private JLabel titleLabel, idLabel, lastNameLabel, firstNameLabel, addressStreetLabel, localityLabel, houseNumberLabel, phoneLabel, emailLabel,
            isSubscrideLabel, vatNumberLabel, birthdayLabel, typeLabel, phoneNumberCBLabel, emailCBLabel, postalBoxNumberLabel, postalBoxNumberCBLabel;
    private JTextField idField, lastNameField, firstNameField, addressStreetField, houseNumberField, emailField;
    private JSpinner birthdaySpinner, postalBoxNumberSpinner;
    private JCheckBox isSubscrideCheckbox, phoneNumberCheckBox, emailCheckBox, postalBoxNumberCheckBox;
    private JComboBox localityComboBox, typeComboBox;
    private JButton addButton, updateButton;
    private ApplicationController controller;
    JFormattedTextField phoneNumberField, vatNumberField;

    public CustomerForm() {
        controller = new ApplicationController();

        this.setLayout(new BorderLayout());
        titlePanel = new JPanel();
        formPanel = new JPanel(new GridLayout(16, 2, 5, 10));
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
        idLabel = new JLabel("Identifiant client :", SwingConstants.RIGHT);
        idField = new JTextField();
        try {
            idField.setText(String.valueOf(controller.lastId() + 1));
        } catch (DAOException e) {
            JOptionPane.showMessageDialog(null, "Erreur lors de l'ajout de l'identifiant veuillez réesayer", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
        idField.setEnabled(false);
        formPanel.add(idLabel);
        formPanel.add(idField);

        lastNameLabel = new JLabel("Nom :", SwingConstants.RIGHT);
        lastNameField = new JTextField();
        formPanel.add(lastNameLabel);
        formPanel.add(lastNameField);

        firstNameLabel = new JLabel("Prénom :", SwingConstants.RIGHT);
        firstNameField = new JTextField();
        formPanel.add(firstNameLabel);
        formPanel.add(firstNameField);

        localityLabel = new JLabel("Catégorie du produit :", SwingConstants.RIGHT);
        localityComboBox = new JComboBox<>();
        ArrayList<Locality> localities = null;
        try {
            localities = controller.localitiesList();
        } catch (DAOException e) {
            throw new RuntimeException(e);
        } catch (InvalidValueException e) {
            throw new RuntimeException(e);
        }
        for (Locality locality : localities) {
            localityComboBox.addItem(locality);
        }
        formPanel.add(localityLabel);
        formPanel.add(localityComboBox);

        addressStreetLabel = new JLabel("Nom de la rue :", SwingConstants.RIGHT);
        addressStreetField = new JTextField();
        formPanel.add(addressStreetLabel);
        formPanel.add(addressStreetField);

        houseNumberLabel = new JLabel("Numéro de maison :", SwingConstants.RIGHT);
        houseNumberField = new JTextField();
        formPanel.add(houseNumberLabel);
        formPanel.add(houseNumberField);

        postalBoxNumberCBLabel = new JLabel("Définir numéro de boite postale :", SwingConstants.RIGHT);
        postalBoxNumberCheckBox = new JCheckBox();
        formPanel.add(postalBoxNumberCBLabel);
        formPanel.add(postalBoxNumberCheckBox);

        postalBoxNumberLabel = new JLabel("Numéro de boite postale :", SwingConstants.RIGHT);
        postalBoxNumberSpinner = new JSpinner(new SpinnerNumberModel(0, 0, null, 1));
        postalBoxNumberSpinner.setEnabled(false);
        formPanel.add(postalBoxNumberLabel);
        formPanel.add(postalBoxNumberSpinner);

        phoneNumberCBLabel = new JLabel("Définir numéro de téléphone :", SwingConstants.RIGHT);
        phoneNumberCheckBox = new JCheckBox();
        formPanel.add(phoneNumberCBLabel);
        formPanel.add(phoneNumberCheckBox);

        phoneLabel = new JLabel("Numéro de téléphone :", SwingConstants.RIGHT);
        MaskFormatter phoneFormatter = null;
        try {
            phoneFormatter = new MaskFormatter("####/##/##/##");
        } catch (ParseException e) {
            JOptionPane.showMessageDialog(null, "Erreur lors du formatage du numéro de téléphone veuillez réessayer", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
        phoneFormatter.setPlaceholderCharacter('_');
        phoneNumberField = new JFormattedTextField(phoneFormatter);
        phoneNumberField.setEnabled(false);
        formPanel.add(phoneLabel);
        formPanel.add(phoneNumberField);

        emailCBLabel = new JLabel("Définir email :", SwingConstants.RIGHT);
        emailCheckBox = new JCheckBox();
        formPanel.add(emailCBLabel);
        formPanel.add(emailCheckBox);

        emailLabel = new JLabel("adresse mail :", SwingConstants.RIGHT);
        emailField = new JTextField();
        emailField.setEnabled(false);
        formPanel.add(emailLabel);
        formPanel.add(emailField);

        isSubscrideLabel = new JLabel("S'inscrire à la New Letter :", SwingConstants.RIGHT);
        isSubscrideCheckbox = new JCheckBox();
        formPanel.add(isSubscrideLabel);
        formPanel.add(isSubscrideCheckbox);

        birthdayLabel = new JLabel("Date d'anniversaire :", SwingConstants.RIGHT);
        Date today = new Date();
        birthdaySpinner = new JSpinner(new SpinnerDateModel(today, null, today, Calendar.MONTH));
        birthdaySpinner.setEditor(new JSpinner.DateEditor(birthdaySpinner, "dd/MM/yyyy"));
        formPanel.add(birthdayLabel);
        formPanel.add(birthdaySpinner);

        typeLabel = new JLabel("Type de client :", SwingConstants.RIGHT);
        typeComboBox = new JComboBox<>(new String[]{"particulier", "professionnel"});
        formPanel.add(typeLabel);
        formPanel.add(typeComboBox);

        vatNumberLabel = new JLabel("Numéro de TVA (BE) :", SwingConstants.RIGHT);
        MaskFormatter vatFormatter = null;
        try {
            vatFormatter = new MaskFormatter("##########");
        } catch (ParseException e) {
            JOptionPane.showMessageDialog(null, "Erreur lors du formatage du numéro de TVA veuillez réessayer", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
        vatFormatter.setPlaceholderCharacter('_');
        vatNumberField = new JFormattedTextField(vatFormatter);
        vatNumberField.setEnabled(false);
        formPanel.add(vatNumberLabel);
        formPanel.add(vatNumberField);


        // BUTTON PANEL
        addButton = new JButton("Ajouter client");
        updateButton = new JButton("Modifier client");
        buttonPanel.add(addButton);


        // EVENT LISTENERS
        phoneNumberCheckBox.addItemListener(e -> {
            phoneNumberField.setEnabled(phoneNumberCheckBox.isSelected());
        });

        emailCheckBox.addItemListener(e -> {
            emailField.setEnabled(emailCheckBox.isSelected());
        });

        postalBoxNumberCheckBox.addItemListener(e -> {
            postalBoxNumberSpinner.setEnabled(postalBoxNumberCheckBox.isSelected());
        });

        typeComboBox.addItemListener(e -> {
            if (Objects.equals(typeComboBox.getSelectedItem(), "professionnel"))
                vatNumberField.setEnabled(true);
            else
                vatNumberField.setEnabled(false);
        });

        addButton.addActionListener(e -> {
            try {
                if (!controller.exists(transformAddress(addressStreetField, localityComboBox, houseNumberField, postalBoxNumberSpinner))){
                    controller.addAddress(transformAddress(addressStreetField, localityComboBox, houseNumberField, postalBoxNumberSpinner));
                }
                controller.addCustomer(transformCustomer(idField, lastNameField, firstNameField, addressStreetField, localityComboBox, houseNumberField, emailField, vatNumberField, phoneNumberField, birthdaySpinner, isSubscrideCheckbox, typeComboBox));
                JOptionPane.showMessageDialog(null, "Client ajouté", "Réussite", JOptionPane.INFORMATION_MESSAGE);
                emptyForm(idField, lastNameField, firstNameField, addressStreetField, localityComboBox, houseNumberField, emailField, vatNumberField, phoneNumberField, birthdaySpinner, isSubscrideCheckbox, typeComboBox, postalBoxNumberCheckBox, postalBoxNumberSpinner);
            } catch (DAOException | InvalidValueException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Problème lors de l'ajout du client", JOptionPane.ERROR_MESSAGE);
            }
        });

        updateButton.addActionListener(e -> {
            try {
                if (!controller.exists(transformAddress(addressStreetField, localityComboBox, houseNumberField, postalBoxNumberSpinner))){
                    controller.addAddress(transformAddress(addressStreetField, localityComboBox, houseNumberField, postalBoxNumberSpinner));
                }
                controller.updateCustomer(transformCustomer(idField, lastNameField, firstNameField, addressStreetField, localityComboBox, houseNumberField, emailField, vatNumberField, phoneNumberField, birthdaySpinner, isSubscrideCheckbox, typeComboBox));
                JOptionPane.showMessageDialog(null, "Client modifié", "Réussite", JOptionPane.INFORMATION_MESSAGE);
                emptyForm(idField, lastNameField, firstNameField, addressStreetField, localityComboBox, houseNumberField, emailField, vatNumberField, phoneNumberField, birthdaySpinner, isSubscrideCheckbox, typeComboBox, postalBoxNumberCheckBox, postalBoxNumberSpinner);
            } catch (DAOException | InvalidValueException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Problème lors de la mise à jour du client", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    private Address transformAddress(JTextField addressStreetField, JComboBox localityComboBox, JTextField houseNumberField, JSpinner postalBoxNumberSpinner) throws InvalidValueException {
        String street = addressStreetField.getText();
        String houseNumber = houseNumberField.getText();
        Locality locality = (Locality) localityComboBox.getSelectedItem();
        Integer localityZipCode = locality.getZipCode();
        String localityName = locality.getName();


        Integer postalBoxNumber;
        if (postalBoxNumberCheckBox.isSelected()){
            postalBoxNumber = (Integer) postalBoxNumberSpinner.getValue();
        } else {
            postalBoxNumber = null;
        }

        return new Address(street, localityZipCode, localityName, houseNumber, postalBoxNumber);
    }

    private Customer transformCustomer(JTextField idField, JTextField lastNameField, JTextField firstNameField, JTextField adresseStreetField, JComboBox localityComboBox, JTextField houseNumberField, JTextField emailField, JTextField vatNumberField, JFormattedTextField phoneNumberField, JSpinner birthdaySpinner, JCheckBox isSubscrideCheckbox, JComboBox typeComboBox) throws InvalidValueException {
        Integer id = Integer.valueOf(idField.getText());
        String lastname = lastNameField.getText();
        String firstName = firstNameField.getText();
        String addressStreet = addressStreetField.getText();
        Locality locality = (Locality) localityComboBox.getSelectedItem();
        Integer localityZipCode = locality.getZipCode();
        String localityName = locality.getName();
        String houseNumber = houseNumberField.getText();
        Boolean isSubscribedToNewsLetter = isSubscrideCheckbox.isSelected();
        LocalDate birthday = ((Date) birthdaySpinner.getValue()).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();;
        String typeName = (String) typeComboBox.getSelectedItem();

        String phone;
        if (phoneNumberCheckBox.isSelected())
            phone = phoneNumberField.getText();
        else
            phone = null;

        String email = null;
        if (emailCheckBox.isSelected())
                email = emailField.getText();
        else
            email = null;

        String vatNumber;
        if (typeComboBox.getSelectedItem().equals("professionnel"))
            vatNumber = vatNumberField.getText();
        else
            vatNumber = null;

        return new Customer(id, lastname, firstName, birthday, phone, email, isSubscribedToNewsLetter, vatNumber,
                localityZipCode, localityName, addressStreet, houseNumber, typeName);
    }

    private void fillCustomerForm(Customer customer, Address address){
        updateButton();
        idField.setText(String.valueOf(customer.getId()));
        lastNameField.setText(customer.getLastName());
        firstNameField.setText(customer.getFirstName());
        emailField.setText(customer.getEmail());
        vatNumberField.setValue(customer.getVatNumber());
        phoneNumberField.setValue(customer.getPhone());
        birthdaySpinner.setValue(Date.from(customer.getBirthdate().atStartOfDay(ZoneId.systemDefault()).toInstant()));
        isSubscrideCheckbox.setSelected(customer.getSubscribedToNewsLetter());
        typeComboBox.setSelectedItem(customer.getTypeName());

        addressStreetField.setText(address.getStreet());
        localityComboBox.setSelectedItem(address.getLocalityZipCode() + " " +address.getLocalityName());
        houseNumberField.setText(address.getHouseNumber());
        postalBoxNumberSpinner.setValue(address.getPostalBoxNumber());
    }

    private void emptyForm(JTextField idField, JTextField lastNameField, JTextField firstNameField, JTextField addressStreetField, JComboBox localityComboBox, JTextField houseNumberField, JTextField emailField, JTextField vatNumberField, JFormattedTextField phoneNumberField, JSpinner birthdaySpinner, JCheckBox isSubscrideCheckbox, JComboBox typeComboBox, JCheckBox postalBoxNumberCheckBox ,JSpinner postalBoxNumberSpinner) throws DAOException {
        idField.setText(String.valueOf(controller.lastId() + 1));
        lastNameField.setText("");
        firstNameField.setText("");
        emailField.setText("");
        vatNumberField.setText("");
        phoneNumberField.setValue(null);
        birthdaySpinner.setValue(new Date());
        isSubscrideCheckbox.setSelected(false);
        typeComboBox.setSelectedIndex(0);

        addressStreetField.setText("");
        localityComboBox.setSelectedIndex(0);
        houseNumberField.setText("");
        postalBoxNumberCheckBox.setSelected(false);
        postalBoxNumberSpinner.setValue(0);
    }

    private void updateButton(){
        buttonPanel.removeAll();
        buttonPanel.add(updateButton);
        revalidate();
        repaint();
    }
}
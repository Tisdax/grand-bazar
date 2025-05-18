package userInterface;

import DAO.CustomerDBAccess;
import DAO.ProductDBAccess;
import exceptions.DAOException;
import exceptions.InvalidValueException;
import model.Customer;
import model.Product;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class CustomerManager extends JPanel {
    private CustomerDBAccess customerDBAccess;
    private JLabel titleLabel;
    private JButton addCustomerButton;
    private JButton removeCustomerButton;

    public CustomerManager() {
        DefaultTableModel tableModel = new DefaultTableModel();
        JTable customerTable = new JTable(tableModel);
        tableModel.addColumn("ID");
        tableModel.addColumn("Nom");
        tableModel.addColumn("Prénom");
        tableModel.addColumn("Date de naissance");
        tableModel.addColumn("Num Téléphone");
        tableModel.addColumn("Email");
        tableModel.addColumn("Est abonné newsletter");
        tableModel.addColumn("Numéro TVA");
        tableModel.addColumn("Localité zip code");
        tableModel.addColumn("Nom localité");
        tableModel.addColumn("Rue");
        tableModel.addColumn("Numéro maison");
        tableModel.addColumn("Type");

        customerDBAccess = new CustomerDBAccess();
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

        titleLabel = new JLabel("Gestion des clients");
        titleLabel.setFont(new Font("Dialog", Font.PLAIN, 30));

        addCustomerButton = new JButton("Ajouter un client");
        addCustomerButton.setPreferredSize(new Dimension(250, 60));

        removeCustomerButton = new JButton("Supprimer un client");
        removeCustomerButton.setPreferredSize(new Dimension(250, 60));

        this.add(titleLabel);
        this.add(addCustomerButton);
        this.add(removeCustomerButton);
        this.add(new JScrollPane(customerTable));
    }



}
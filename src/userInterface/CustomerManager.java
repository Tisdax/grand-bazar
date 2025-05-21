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
    private JLabel titleLabel;
    private JButton addCustomerButton;
    private JButton removeCustomerButton;
    private TableConstruct customerTable;

    public CustomerManager() {
        PanelSwitchActionner switchActionner = PanelSwitchActionner.getInstance();
        String[] columnNames = {
                "ID", "Nom", "Prénom", "Date de naissance", "Num Téléphone", "Email",
                "Est abonné newsletter", "Numéro TVA", "Localité zip code",
                "Nom localité", "Rue", "Numéro maison", "Type"
        };
        customerTable = new TableConstruct(columnNames);
        customerTable.fillCustomerTable();

        titleLabel = new JLabel("Gestion des clients");
        titleLabel.setFont(new Font("Dialog", Font.PLAIN, 30));

        addCustomerButton = switchActionner.createButton("Ajouter un client", CustomerForm::new);

        removeCustomerButton = new JButton("Supprimer un client");
        removeCustomerButton.setPreferredSize(new Dimension(250, 60));

        this.add(titleLabel);
        this.add(addCustomerButton);
        this.add(removeCustomerButton);
        this.add(new JScrollPane(customerTable.getTable()));
    }



}
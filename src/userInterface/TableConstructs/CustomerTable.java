package userInterface.TableConstructs;

import exceptions.DAOException;
import exceptions.InvalidValueException;
import model.Customer;

import javax.swing.*;
import java.util.ArrayList;

public class CustomerTable extends TableConstruct {
    public CustomerTable() {
        super(new String[] {
                "ID", "Nom", "Prénom", "Date de naissance", "Num Téléphone", "Email",
                "Est abonné newsletter", "Numéro TVA", "Localité zip code",
                "Nom localité", "Rue", "Numéro maison", "Type"
        });
        fillTable();
    }
    public void fillTable() {
            try {
                ArrayList<Customer> customers = controller.findAllCustomers();
                super.refreshTable();
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
}

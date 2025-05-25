package userInterface.TableConstructs;

import exceptions.DAOException;
import exceptions.InvalidValueException;
import model.CustomerAddressInfo;

import javax.swing.*;
import java.util.ArrayList;

public class LoyaltyCardTable extends TableConstruct{
    public LoyaltyCardTable() {
        super(new String[] {"Nom", "Prénom", "Rue", "Numéro maison", "Numéro boite postale", "Zip code", "Ville",});
    }
    public void fillTable(int minPoints, int maxPoints) {
        try {
            ArrayList<CustomerAddressInfo> customers = controller.findCustomersByLoyaltyPoints(minPoints, maxPoints);
            super.refreshTable();
            for(CustomerAddressInfo customer : customers) {
                tableModel.addRow(new Object[] {customer.getLastName(), customer.getFirstName(), customer.getStreet(),
                    customer.getHouseNumber(), customer.getPostalBoxNumber(), customer.getZipCode(), customer.getLocalityName()});
            }
        } catch (DAOException | InvalidValueException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }
}

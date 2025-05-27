package userInterface;

import userInterface.TableConstructs.CustomerTable;
import userInterface.TableConstructs.TableConstruct;

import javax.swing.*;
import java.awt.*;

public class CustomerManager extends JPanel {
    private JLabel titleLabel;
    private JButton addCustomerButton;
    private JButton removeCustomerButton;
    private JButton editCustomerButton;
    private CustomerTable customerTable;

    public CustomerManager() {
        PanelSwitchActionner switchActionner = new PanelSwitchActionner();
        // Main Layout
        setLayout(new BorderLayout(0, 50));
        setBorder(BorderFactory.createEmptyBorder(0, 100, 50, 100));
        // Buttons Layout
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        // Title Panel
        JPanel titlePanel = new JPanel();

        // Table
        customerTable = new CustomerTable();

        titleLabel = new JLabel("Gestion des clients");
        titleLabel.setFont(new Font("Dialog", Font.PLAIN, 30));
        titlePanel.add(titleLabel);

        // Buttons
        addCustomerButton = switchActionner.createButton("Ajouter un client", CustomerForm::new);
        removeCustomerButton = switchActionner.createDeleteCustomerButton(customerTable);
        editCustomerButton = switchActionner.createEditCustomerButton();
        buttonPanel.add(addCustomerButton);
        buttonPanel.add(removeCustomerButton);
        buttonPanel.add(editCustomerButton);

        this.add(titlePanel, BorderLayout.NORTH);
        this.add(buttonPanel, BorderLayout.CENTER);
        this.add(new JScrollPane(customerTable.getTable()), BorderLayout.SOUTH);
    }
}
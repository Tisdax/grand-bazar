package userInterface;

import userInterface.TableConstructs.CustomerTable;
import userInterface.TableConstructs.EmployeeTable;
import userInterface.TableConstructs.ProductTable;
import userInterface.TableConstructs.TableConstruct;

import javax.swing.*;
import javax.swing.table.TableModel;
import java.awt.*;

public class CartManager extends JPanel {
    private JLabel titleLabel;
    private JButton selectEmployee;
    private JButton selectCustomer;
    private CustomerTable customerTable;
    private EmployeeTable employeeTable;
    private ProductTable productTable;


    public CartManager() {
        // Main Layout
        setLayout(new BorderLayout(0, 50));
        setBorder(BorderFactory.createEmptyBorder(0, 100, 50, 100));
        // Title Panel
        JPanel titlePanel = new JPanel();
        titleLabel = new JLabel("Gestion Achats");
        titleLabel.setFont(new Font("Dialog", Font.PLAIN, 30));
        titlePanel.add(titleLabel);

        // Buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        // Tables
        customerTable = new CustomerTable();
        JScrollPane customerScrollTable = new JScrollPane(customerTable.getTable());
        employeeTable = new EmployeeTable();
        JScrollPane employeeScrollTable = new JScrollPane(employeeTable.getTable());
        productTable = new ProductTable();
        JScrollPane productScrollTable = new JScrollPane(productTable.getTable());

        selectEmployee = new JButton("Sélectionner un employé");
        selectEmployee.setPreferredSize(new Dimension(350, 80));
        buttonPanel.add(selectEmployee);
        selectEmployee.addActionListener(e -> {



            this.remove(employeeScrollTable);
            this.add(customerScrollTable, BorderLayout.SOUTH);
            this.revalidate();
            this.repaint();
            buttonPanel.remove(selectEmployee);
            buttonPanel.add(selectCustomer);
            buttonPanel.revalidate();
            buttonPanel.repaint();
        });

        selectCustomer = new JButton("Sélectionner un client");
        selectCustomer.setPreferredSize(new Dimension(350, 80));
        selectCustomer.addActionListener(e -> {

        });



        this.add(titlePanel, BorderLayout.NORTH);
        this.add(buttonPanel, BorderLayout.CENTER);
        this.add(employeeScrollTable, BorderLayout.SOUTH);
    }
}


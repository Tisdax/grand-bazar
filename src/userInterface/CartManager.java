package userInterface;

import controller.ApplicationController;
import model.Sale;
import userInterface.TableConstructs.CustomerTable;
import userInterface.TableConstructs.EmployeeTable;
import userInterface.TableConstructs.ProductTable;
import userInterface.TableConstructs.TableConstruct;

import javax.swing.*;
import javax.swing.table.TableModel;
import java.awt.*;

public class CartManager extends JPanel {
    private ApplicationController controller;
    private JLabel titleLabel;
    private JButton selectEmployee;
    private JButton selectCustomer;
    private JButton addProduct;
    private CustomerTable customerTable;
    private EmployeeTable employeeTable;
    private ProductTable productTable;
    private Sale sale;


    public CartManager() {
        // Main Layout
        setLayout(new BorderLayout(0, 50));
        setBorder(BorderFactory.createEmptyBorder(0, 100, 50, 100));
        // Title Panel
        JPanel titlePanel = new JPanel();
        titleLabel = new JLabel("Gestion Achats");
        titleLabel.setFont(new Font("Dialog", Font.PLAIN, 30));
        titlePanel.add(titleLabel);

        // Sale
//        try {
//            int saleId = controller.lastSaleId() + 1;
//            sale = new Sale(saleId,);
//        } catch (Exception ex) {
//            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
//        }

        // Tables
        customerTable = new CustomerTable();
        JScrollPane customerScrollTable = new JScrollPane(customerTable.getTable());
        employeeTable = new EmployeeTable();
        JScrollPane employeeScrollTable = new JScrollPane(employeeTable.getTable());
        productTable = new ProductTable();
        JScrollPane productScrollTable = new JScrollPane(productTable.getTable());

        // Button Panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        // Employee Button
        selectEmployee = new JButton("Sélectionner un employé");
        selectEmployee.setPreferredSize(new Dimension(350, 80));
        buttonPanel.add(selectEmployee);
        selectEmployee.addActionListener(e -> {
            String inputID = JOptionPane.showInputDialog("ID de l'employé:");
            if (inputID == null){
                return;
            }

            this.remove(employeeScrollTable);
            this.add(customerScrollTable, BorderLayout.SOUTH);
            this.revalidate();
            this.repaint();
            buttonPanel.remove(selectEmployee);
            buttonPanel.add(selectCustomer);
            buttonPanel.revalidate();
            buttonPanel.repaint();
        });
        // Customer Button
        selectCustomer = new JButton("Sélectionner un client");
        selectCustomer.setPreferredSize(new Dimension(350, 80));
        selectCustomer.addActionListener(e -> {
            String inputID = JOptionPane.showInputDialog("ID du client:");
            if (inputID == null){
                return;
            }

            this.remove(customerScrollTable);
            this.add(productScrollTable, BorderLayout.SOUTH);
            this.revalidate();
            this.repaint();
            buttonPanel.remove(selectCustomer);
            buttonPanel.add(addProduct);
            buttonPanel.revalidate();
            buttonPanel.repaint();
        });
        // Product Button
        addProduct = new JButton("Ajouter un produit");
        addProduct.setPreferredSize(new Dimension(350, 80));
        addProduct.addActionListener(e -> {

        });

        this.add(titlePanel, BorderLayout.NORTH);
        this.add(buttonPanel, BorderLayout.CENTER);
        this.add(employeeScrollTable, BorderLayout.SOUTH);
    }
}


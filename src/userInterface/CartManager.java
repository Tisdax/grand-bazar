package userInterface;

import businessLogic.CommandLineManager;
import businessLogic.SaleManager;
import controller.ApplicationController;
import exceptions.DAOException;
import exceptions.InvalidValueException;
import model.CommandLine;
import model.Product;
import model.Sale;
import userInterface.TableConstructs.*;

import javax.swing.*;
import javax.swing.table.TableModel;
import java.awt.*;
import java.time.LocalDate;

public class CartManager extends JPanel {
    private ApplicationController controller;
    private JLabel titleLabel;
    private JButton selectEmployee;
    private JButton selectCustomer;
    private JButton addProduct;
    private JButton endSale;
    private CustomerTable customerTable;
    private EmployeeTable employeeTable;
    private ProductTable productTable;
    private SaleTable saleTable;
    private Sale sale;
    private int saleId;
    private int customerIdOfSale;
    private int employeeIdOfSale;
    // private LocalDate dateOfSale;
    private CommandLine commandLine;


    public CartManager() {
        controller = new ApplicationController();
        // Main Layout
        setLayout(new BorderLayout(0, 50));
        setBorder(BorderFactory.createEmptyBorder(0, 100, 50, 100));
        // Title Panel
        JPanel titlePanel = new JPanel();
        titleLabel = new JLabel("Gestion Achats");
        titleLabel.setFont(new Font("Dialog", Font.PLAIN, 30));
        titlePanel.add(titleLabel);

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
            int inputIdInt;
            try {
                inputIdInt = Integer.parseInt(inputID);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Veuillez entrer un identifiant ne contenant que des chiffres entiers", "Erreur", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
                if (controller.employeeExistsById(inputIdInt)) {
                    employeeIdOfSale = inputIdInt;
                    this.remove(employeeScrollTable);
                    this.add(customerScrollTable, BorderLayout.SOUTH);
                    this.revalidate();
                    this.repaint();
                    buttonPanel.remove(selectEmployee);
                    buttonPanel.add(selectCustomer);
                    buttonPanel.revalidate();
                    buttonPanel.repaint();
                } else {
                    JOptionPane.showMessageDialog(null, "Cet identifiant n'existe pas veuillez choisir un identifiant de la liste", "Erreur",JOptionPane.ERROR_MESSAGE);
                }
            } catch (DAOException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        });
        // Customer Button
        selectCustomer = new JButton("Sélectionner un client");
        selectCustomer.setPreferredSize(new Dimension(350, 80));
        selectCustomer.addActionListener(e -> {
            String inputID = JOptionPane.showInputDialog("ID du client:");
            if (inputID == null){
                return;
            }
            int inputIdInt;
            try {
                inputIdInt = Integer.parseInt(inputID);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Veuillez entrer un identifiant ne contenant que des chiffres entiers", "Erreur", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
                if (controller.customerExistsById(inputIdInt)) {
                    customerIdOfSale = inputIdInt;
                    this.remove(customerScrollTable);
                    this.add(productScrollTable, BorderLayout.SOUTH);
                    this.revalidate();
                    this.repaint();
                    buttonPanel.remove(selectCustomer);
                    buttonPanel.add(addProduct);
                    buttonPanel.add(endSale);
                    buttonPanel.revalidate();
                    buttonPanel.repaint();

                    // Sale
                    saleId = controller.lastSaleId() + 1;
                    sale = new Sale(saleId, customerIdOfSale, LocalDate.now(), employeeIdOfSale);
                    controller.saveSale(sale);

                } else {
                    JOptionPane.showMessageDialog(null, "Cet identifiant n'existe pas veuillez choisir un identifiant de la liste", "Erreur",JOptionPane.ERROR_MESSAGE);
                }
            } catch (DAOException | InvalidValueException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        });


        // Product Button
        addProduct = new JButton("Ajouter un produit");
        addProduct.setPreferredSize(new Dimension(350, 80));
        addProduct.addActionListener(e -> {
            String inputID = JOptionPane.showInputDialog("ID du produit:");
            if (inputID == null){
                return;
            }

            try {
                if (controller.productExistsById(inputID)) {
                    String inputQuantity = JOptionPane.showInputDialog("Quantité du produit :");
                    int inputQuantityInt;

                    try {
                        inputQuantityInt = Integer.parseInt(inputQuantity);
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "Veuillez entrer un quantité ne contenant que des chiffres entiers", "Erreur", JOptionPane.ERROR_MESSAGE);
                        return;
                    }


                    commandLine = new CommandLine(saleId, inputID, inputQuantityInt);
                    if (controller.CommandLineExistsById(saleId, inputID)) {
                        
                        controller.commandLineUpdate(commandLine);
                    } else {
                        controller.saveCommandLine(commandLine);
                    }


                } else {
                    JOptionPane.showMessageDialog(null, "Cet identifiant n'existe pas veuillez choisir un identifiant dans la liste", "Erreur",JOptionPane.ERROR_MESSAGE);
                }
            } catch (DAOException | InvalidValueException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        });

        saleTable = new SaleTable();
        JScrollPane saleScrollTable = new JScrollPane(saleTable.getTable());

        // endSale button
        endSale = new JButton("Terminer l'achat");
        endSale.setPreferredSize(new Dimension(350, 80));
        endSale.addActionListener(e -> {

            saleTable.fillTable(saleId);
            this.remove(productScrollTable);
            this.add(saleScrollTable, BorderLayout.SOUTH);
            this.revalidate();
            this.repaint();
            buttonPanel.removeAll();
            buttonPanel.revalidate();
            buttonPanel.repaint();
        });

        this.add(titlePanel, BorderLayout.NORTH);
        this.add(buttonPanel, BorderLayout.CENTER);
        this.add(employeeScrollTable, BorderLayout.SOUTH);
    }
}


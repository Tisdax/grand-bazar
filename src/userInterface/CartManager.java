package userInterface;

import businessLogic.InvoiceManager;
import controller.ApplicationController;
import exceptions.DAOException;
import exceptions.InvalidValueException;
import model.CommandLine;
import model.Sale;
import model.Customer;
import userInterface.TableConstructs.*;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class CartManager extends JPanel {
    private ApplicationController controller;
    private PanelSwitchActionner switchActionner;
    private JLabel titleLabel;
    private JLabel customerInformationLabel;
    private JLabel VATPriceLabel;
    private JButton selectEmployee;
    private JButton selectCustomer;
    private JButton addProduct;
    private JButton endSale;
    private JButton newSale;
    private InvoiceManager invoiceManager;
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
        invoiceManager = new InvoiceManager();
        switchActionner = new PanelSwitchActionner();
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

                    int quantityInStock = controller.findQuantityStockByProduct(inputID);
                    if (inputQuantityInt > quantityInStock){
                        JOptionPane.showMessageDialog(null, "Il n'y a que " + quantityInStock + " produit en stock veuillez diminuer la quantité", "Erreur", JOptionPane.ERROR_MESSAGE);
                    } else {


                        if (controller.CommandLineExistsById(saleId, inputID)) {
                            int newQuant = controller.findCommandLineById(saleId, inputID).getQuantity() + inputQuantityInt;
                            commandLine = new CommandLine(saleId, inputID, newQuant);
                            controller.commandLineUpdate(commandLine);
                        } else {
                            commandLine = new CommandLine(saleId, inputID, inputQuantityInt);
                            controller.saveCommandLine(commandLine);
                        }

                        controller.lowerStocks(inputID, inputQuantityInt);
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

            ArrayList<CommandLine> commandLines = new ArrayList<>();
            try {
                commandLines = controller.findBySale(saleId);
                VATPriceLabel = new JLabel("Prix total à payer : " + String.valueOf(Math.floor(invoiceManager.totalPriceVATIncl(commandLines)*100) /100), SwingConstants.RIGHT);
                VATPriceLabel.setFont(new Font("Dialog", Font.PLAIN, 20));
            } catch (DAOException | InvalidValueException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            }

            try {
                Customer customer = controller.findCustomerById(customerIdOfSale);
                customerInformationLabel = new JLabel(customer.getFirstName() + " " + customer.getLastName() + " - " +
                        customer.getHouseNumber() + " " + customer.getAddressStreet() + " " + customer.getLocalityZipCode() + " " + customer.getLocalityName(), SwingConstants.RIGHT);
                customerInformationLabel.setFont(new Font("Dialog", Font.PLAIN, 20));
            } catch (DAOException | InvalidValueException exe) {
                JOptionPane.showMessageDialog(null, exe.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            }

            saleTable.fillTable(saleId);
            this.remove(productScrollTable);
            this.add(saleScrollTable, BorderLayout.SOUTH);
            this.revalidate();
            this.repaint();
            buttonPanel.setLayout(new GridLayout(0,1));
            buttonPanel.removeAll();
            buttonPanel.add(newSale);
            buttonPanel.add(customerInformationLabel);
            buttonPanel.add(VATPriceLabel);
            buttonPanel.revalidate();
            buttonPanel.repaint();
        });

        // newSale button
        newSale = switchActionner.createButton("Nouvel Achat", CartManager::new);

        this.add(titlePanel, BorderLayout.NORTH);
        this.add(buttonPanel, BorderLayout.CENTER);
        this.add(employeeScrollTable, BorderLayout.SOUTH);
    }
}


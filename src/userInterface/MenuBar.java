package userInterface;

import controller.ApplicationController;
import exceptions.DAOException;
import exceptions.InvalidValueException;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.text.ParseException;

public class MenuBar {
    public static JMenuBar CreateJMenuBar(JFrame frame) throws DAOException, ParseException, InvalidValueException {
        PanelSwitchActionner switchActionner = new PanelSwitchActionner(frame);
        // Menu & Menu Items
        JMenuBar menu = new JMenuBar();
        JMenu applicationMenu, productMenu, customerMenu, shoppingCartMenu, stockMenu;
        JMenuItem welcomePage, leave, createProduct, readProduct, updateProduct, deleteProduct, createCustomer, readCustomer,
                updateCustomer, deleteCustomer, newPurchase, stockReport, lowStockProductReport;

        // Panels
        CustomerForm customerForm = new CustomerForm();
        ProductForm productForm = new ProductForm();
        WelcomePanel welcomePanel = new WelcomePanel();
        ProductManager productManager = new ProductManager(frame);
        CustomerManager customerManager = new CustomerManager();
        CartManager cartManager = new CartManager();
        StockInformation stockInformation = new StockInformation();
        LowStockProductPanel LowStockProductPanel = new LowStockProductPanel();

        // Application Menu
        applicationMenu = new JMenu("Application");
        welcomePage = switchActionner.createMenuItem(welcomePanel, "Accueil");
        leave = switchActionner.createMenuItem(welcomePanel, "Quitter");
        menu.add(applicationMenu);
        applicationMenu.add(welcomePage);
        applicationMenu.add(leave);

        // Product Menu
        productMenu = new JMenu("Gestion produit");
        createProduct = switchActionner.createMenuItem(productForm, "Nouveau produit");
        readProduct = switchActionner.createMenuItem(productManager, "Afficher produit");
        updateProduct = switchActionner.createMenuItem(productManager, "Modifier produit");
        deleteProduct = switchActionner.createMenuItem(productManager, "Supprimer produit");
        menu.add(productMenu);
        productMenu.add(createProduct);
        productMenu.addSeparator();
        productMenu.add(readProduct);
        productMenu.addSeparator();
        productMenu.add(updateProduct);
        productMenu.addSeparator();
        productMenu.add(deleteProduct);

        // Customer Menu
        customerMenu = new JMenu("Gestion client");
        createCustomer = switchActionner.createMenuItem(customerForm, "Nouveau client");
        readCustomer = switchActionner.createMenuItem(customerManager, "Afficher client");
        updateCustomer = switchActionner.createMenuItem(customerManager, "Modifier client");
        deleteCustomer = switchActionner.createMenuItem(customerManager, "Supprimer client");
        menu.add(customerMenu);
        customerMenu.add(createCustomer);
        customerMenu.addSeparator();
        customerMenu.add(readCustomer);
        customerMenu.addSeparator();
        customerMenu.add(updateCustomer);
        customerMenu.addSeparator();
        customerMenu.add(deleteCustomer);

        // Cart Menu
        shoppingCartMenu = new JMenu("Panier");
        newPurchase = switchActionner.createMenuItem(cartManager, "Nouvelle commande");
        menu.add(shoppingCartMenu);
        shoppingCartMenu.add(newPurchase);

        // Stock Menu
        stockMenu = new JMenu("Stock");
        stockReport = switchActionner.createMenuItem(stockInformation, "Rapport de stock");
        lowStockProductReport = switchActionner.createMenuItem(LowStockProductPanel, "Produits ayant un stock insuffisant");
        menu.add(stockMenu);
        stockMenu.add(stockReport);
        stockMenu.addSeparator();
        stockMenu.add(lowStockProductReport);

        // exit
        leave.addActionListener(e -> {
            System.exit(0);
        });
        //
        return menu;
    }
}

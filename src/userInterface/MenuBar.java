package userInterface;

import controller.ApplicationController;
import exceptions.DAOException;
import exceptions.InvalidValueException;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.text.ParseException;

public class MenuBar {
    public static JMenuBar CreateJMenuBar() {
        PanelSwitchActionner switchActionner = PanelSwitchActionner.getInstance();
        // Menu & Menu Items
        JMenuBar menu = new JMenuBar();
        JMenu applicationMenu, productMenu, customerMenu, shoppingCartMenu, stockMenu;
        JMenuItem welcomePage, leave, createProduct, readProduct, updateProduct, deleteProduct, createCustomer, readCustomer,
                updateCustomer, deleteCustomer, newPurchase, stockReport, lowStockProductReport;

        // Application Menu
        applicationMenu = new JMenu("Application");
        welcomePage = switchActionner.createMenuItem("Accueil", WelcomePanel::new);
        leave = new JMenuItem("Quitter");
        menu.add(applicationMenu);
        applicationMenu.add(welcomePage);
        applicationMenu.add(leave);

        // Product Menu
        productMenu = new JMenu("Gestion produit");
        createProduct = switchActionner.createMenuItem("Nouveau produit", ProductForm::new);
        readProduct = switchActionner.createMenuItem("Afficher produit", ProductManager::new);
        updateProduct = switchActionner.createMenuItem("Modifier produit", ProductManager::new);
        deleteProduct = switchActionner.createMenuItem("Supprimer produit", ProductManager::new);
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
        createCustomer = switchActionner.createMenuItem("Nouveau client", CustomerForm::new);
        readCustomer = switchActionner.createMenuItem("Afficher client", CustomerManager::new);
        updateCustomer = switchActionner.createMenuItem("Modifier client", CustomerManager::new);
        deleteCustomer = switchActionner.createMenuItem("Supprimer client", CustomerManager::new);
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
        newPurchase = switchActionner.createMenuItem("Nouvelle commande", CartManager::new);
        menu.add(shoppingCartMenu);
        shoppingCartMenu.add(newPurchase);

        // Stock Menu
        stockMenu = new JMenu("Stock");
        stockReport = switchActionner.createMenuItem("Rapport de stock", StockInformation::new);
        lowStockProductReport = switchActionner.createMenuItem("Produits ayant un stock insuffisant", LowStockProductPanel::new);
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

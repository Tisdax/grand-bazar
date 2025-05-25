package userInterface;

import businessLogic.ConnectionManager;
import controller.ApplicationController;
import exceptions.DAOException;
import exceptions.InvalidValueException;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.text.ParseException;

public class MenuBar {
    public static JMenuBar CreateJMenuBar() {
        PanelSwitchActionner switchActionner = new PanelSwitchActionner();
        // Menu & Menu Items
        JMenuBar menu = new JMenuBar();
        JMenu applicationMenu, productMenu, customerMenu, shoppingCartMenu, stockMenu;
        JMenuItem welcomePage, leave, createProduct, readProduct, updateProduct, deleteProduct, createCustomer, readCustomer,
                updateCustomer, deleteCustomer, newPurchase, stockReport, lowStockProductReport, productStockForm, purchaseInfos, loyaltyCardsInfos;

        // Application Menu
        applicationMenu = new JMenu("Application");
        welcomePage = switchActionner.createMenuItem("Accueil", WelcomePanel::new);
        leave = new JMenuItem("Quitter");
        menu.add(applicationMenu);
        applicationMenu.add(welcomePage);
        applicationMenu.add(leave);

        // Product Menu
        productMenu = new JMenu("Gestion des produits");
        createProduct = switchActionner.createMenuItem("Nouveau produit", ProductForm::new);
        readProduct = switchActionner.createMenuItem("Afficher produits", ProductManager::new);
        updateProduct = switchActionner.createEditProductMenuItem();
        deleteProduct = switchActionner.createDeleteProductMenuItem();
        menu.add(productMenu);
        productMenu.add(createProduct);
        productMenu.addSeparator();
        productMenu.add(readProduct);
        productMenu.addSeparator();
        productMenu.add(updateProduct);
        productMenu.addSeparator();
        productMenu.add(deleteProduct);

        // Customer Menu
        customerMenu = new JMenu("Gestion des clients");
        createCustomer = switchActionner.createMenuItem("Nouveau client", CustomerForm::new);
        readCustomer = switchActionner.createMenuItem("Afficher clients", CustomerManager::new);
        updateCustomer = switchActionner.createEditCustomerMenuItem();
        deleteCustomer = switchActionner.createDeleteCustomerMenuItem();
        loyaltyCardsInfos = switchActionner.createMenuItem("Infos clients intervalle pts fidélité", LoyaltyCardPanel::new);
        menu.add(customerMenu);
        customerMenu.add(createCustomer);
        customerMenu.addSeparator();
        customerMenu.add(readCustomer);
        customerMenu.addSeparator();
        customerMenu.add(updateCustomer);
        customerMenu.addSeparator();
        customerMenu.add(deleteCustomer);
        customerMenu.addSeparator();
        customerMenu.add(loyaltyCardsInfos);

        // Cart Menu
        shoppingCartMenu = new JMenu("Achats");
        newPurchase = switchActionner.createMenuItem("Nouvelle commande", CartManager::new);
        purchaseInfos = switchActionner.createMenuItem("Infos Achats entre deux dates", PurchaseManager::new);
        menu.add(shoppingCartMenu);
        shoppingCartMenu.add(newPurchase);
        shoppingCartMenu.addSeparator();
        shoppingCartMenu.add(purchaseInfos);

        // Stock Menu
        stockMenu = new JMenu("Stocks");
        stockReport = switchActionner.createMenuItem("Rapport de stocks selon catégorie produit", StockInformation::new);
        lowStockProductReport = switchActionner.createMenuItem("Produits ayant un stock insuffisant", LowStockProductPanel::new);
        productStockForm = switchActionner.createMenuItem("Ajout/Mise à jour stock produit", ProductStockForm::new);
        menu.add(stockMenu);
        stockMenu.add(stockReport);
        stockMenu.addSeparator();
        stockMenu.add(productStockForm);
        stockMenu.addSeparator();
        stockMenu.add(lowStockProductReport);

        // exit
        leave.addActionListener(e -> {
            try {
                ApplicationController controller = new ApplicationController();
                controller.closeConnection();
                System.exit(0);
            } catch (DAOException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Problème lors de la fermeture de la BD",JOptionPane.ERROR_MESSAGE);
            }
        });
        return menu;
    }
}

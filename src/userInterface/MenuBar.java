package userInterface;

import exceptions.DAOException;

import javax.swing.*;

public class MenuBar {
    public static JMenuBar CreateJMenuBar(JFrame frame) throws DAOException {
        // Menu & Menu Items
        JMenuBar menu = new JMenuBar();
        JMenu applicationMenu, productMenu, customerMenu, shoppingCartMenu, stockMenu;
        JMenuItem welcomePage, leave, createProduct, readProduct, updateProduct, deleteProduct, createCustomer, readCustomer,
                updateCustomer, deleteCustomer, newPurchase, stockReport;

        // Panels
        CustomerForm customerForm = new CustomerForm();
        ProductForm productForm = new ProductForm();
        WelcomePanel welcomePanel = new WelcomePanel();
        ProductManager productManager = new ProductManager();
        CustomerManager customerManager = new CustomerManager();
        CartManager cartManager = new CartManager();
        StockInformation stockInformation = new StockInformation();

        // Application Menu
        applicationMenu = new JMenu("Application");
        welcomePage = new JMenuItem("Accueil");
        leave = new JMenuItem("Quitter");
        menu.add(applicationMenu);
        applicationMenu.add(welcomePage);
        applicationMenu.add(leave);

        // Product Menu
        productMenu = new JMenu("Gestion produit");
        createProduct = new JMenuItem("Nouveau produit");
        readProduct = new JMenuItem("Afficher produit");
        updateProduct = new JMenuItem("Modifier produit");
        deleteProduct = new JMenuItem("Supprimer produit");
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
        createCustomer = new JMenuItem("Nouveau client");
        readCustomer = new JMenuItem("Afficher client");
        updateCustomer = new JMenuItem("Modifier client");
        deleteCustomer = new JMenuItem("Supprimer client");
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
        newPurchase = new JMenuItem("Nouvelle commande");
        menu.add(shoppingCartMenu);
        shoppingCartMenu.add(newPurchase);

        // Stock Menu
        stockMenu = new JMenu("Stock");
        stockReport = new JMenuItem("Rapport de stock");
        menu.add(stockMenu);
        stockMenu.add(stockReport);

        // Link panels to menu
        addPanelToFrame(frame, welcomePage, welcomePanel);
        addPanelToFrame(frame, createProduct, productForm);
        addPanelToFrame(frame, createCustomer, customerForm);
        addPanelToFrame(frame, readProduct, productManager);
        addPanelToFrame(frame, updateProduct, productManager); // same as readProduct
        addPanelToFrame(frame, deleteProduct, productManager); // same as readProduct
        addPanelToFrame(frame, readCustomer, customerManager);
        addPanelToFrame(frame, updateCustomer, customerManager); // same as readCustomer
        addPanelToFrame(frame, deleteCustomer, customerManager); // same as readCustomer
        addPanelToFrame(frame, newPurchase, cartManager);
        addPanelToFrame(frame, stockReport, stockInformation);

        // exit
        leave.addActionListener(e -> {
            System.exit(0);
        });
        //
        return menu;
    }
    public static void addPanelToFrame(JFrame frame, JMenuItem menuItem, JPanel panel) {
        menuItem.addActionListener(e -> {
            frame.getContentPane().removeAll();
            frame.add(panel);
            frame.revalidate();
            frame.repaint();
        });
    }
}
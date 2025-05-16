package userInterface;

import exceptions.DAOException;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class MenuBar {

        public static JMenuBar CreateJMenuBar(MainWindow frame) throws DAOException {
            ProductForm productForm = new ProductForm();
            CustomerForm customerForm = new CustomerForm();
            JMenuBar menu = new JMenuBar();
            JMenu applicationMenu, productMenu, customerMenu, shoppingCartMenu, stockMenu;
            JMenuItem welcomePage, leave, createProduct, readProduct, updateProduct, deleteProduct, createCustomer, readCustomer, updateCustomer, deleteCustomer;

            applicationMenu = new JMenu("Application");
            welcomePage = new JMenuItem("Accueil");
            leave = new JMenuItem("Quitter");

            welcomePage.addActionListener(e -> {
                WelcomePanel welcomePanel = new WelcomePanel();
                frame.getContentPane().removeAll();
                frame.add(welcomePanel);
                frame.revalidate();
                frame.repaint();
            });

            leave.addActionListener(e -> {
                System.exit(0);
            });

            menu.add(applicationMenu);
            applicationMenu.add(welcomePage);
            applicationMenu.add(leave);


            productMenu = new JMenu("Gestion produit");
            createProduct = new JMenuItem("Nouveau produit");
            readProduct = new JMenuItem("Afficher produit");
            updateProduct = new JMenuItem("Modifier produit");
            deleteProduct = new JMenuItem("Supprimer produit");

            createProduct.addActionListener(e -> {
                frame.getContentPane().removeAll();
                frame.add(productForm);
                frame.revalidate();
                frame.repaint();
            });

            menu.add(productMenu);
            productMenu.add(createProduct);
            productMenu.addSeparator();
            productMenu.add(readProduct);
            productMenu.addSeparator();
            productMenu.add(updateProduct);
            productMenu.addSeparator();
            productMenu.add(deleteProduct);


            customerMenu = new JMenu("Gestion client");
            createCustomer = new JMenuItem("Nouveau client");
            readCustomer = new JMenuItem("Afficher client");
            updateCustomer = new JMenuItem("Modifier client");
            deleteCustomer = new JMenuItem("Supprimer client");

            createCustomer.addActionListener(e -> {
                frame.getContentPane().removeAll();
                frame.add(customerForm);
                frame.revalidate();
                frame.repaint();
            });

            menu.add(customerMenu);
            customerMenu.add(createCustomer);
            customerMenu.addSeparator();
            customerMenu.add(readCustomer);
            customerMenu.addSeparator();
            customerMenu.add(updateCustomer);
            customerMenu.addSeparator();
            customerMenu.add(deleteCustomer);


            shoppingCartMenu = new JMenu("Panier");
            menu.add(shoppingCartMenu);

            stockMenu = new JMenu("Stock");
            menu.add(stockMenu);

            return menu;
        }

}


package userInterface;

import DAO.ProductDBAccess;
import controller.ApplicationController;
import exceptions.DAOException;
import exceptions.InvalidValueException;
import model.Product;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;

public class ProductManager extends JPanel {
    private JLabel titleLabel;
    private JButton addProductButton;
    private JButton removeProductButton;
    private JButton editProductButton;
    private TableConstruct tableConstruct;
    private PanelSwitchActionner switchActionner;

    public ProductManager(JFrame mainFrame) throws DAOException {
        switchActionner = new PanelSwitchActionner(mainFrame);
        String[] columnNames = {
                "ID", "Name", "Prix Net", "% TVA", "Pts Fidélité", "Eligible", "Quantité minimum",
                "Quantité minimum promo", "Date mise en vente", "Temps avant retrait", "Catégorie"
        };
        tableConstruct = new TableConstruct(columnNames);
        tableConstruct.fillProductTable();

        titleLabel = new JLabel("Gestion des produits");
        titleLabel.setFont(new Font("Dialog", Font.PLAIN, 30));

        addProductButton = switchActionner.createButton(new ProductForm(), "Ajouter un produit");
//        addProductButton = new JButton("Ajouter un produit");
//        addProductButton.setPreferredSize(new Dimension(250, 60));

        removeProductButton = new JButton("Supprimer un produit");
        removeProductButton.setPreferredSize(new Dimension(250, 60));

        editProductButton = new JButton("Modifier un produit");
        editProductButton.setPreferredSize(new Dimension(250, 60));
        // Faire une méthode pour les boutons

        this.add(titleLabel);
        this.add(addProductButton);
        this.add(removeProductButton);
        this.add(editProductButton);
        this.add(new JScrollPane(tableConstruct.getTable()));
    }
}
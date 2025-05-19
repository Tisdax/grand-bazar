package userInterface;

import DAO.ProductDBAccess;
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
    private ProductDBAccess productDBAccess;
    private JLabel titleLabel;
    private JButton addProductButton;
    private JButton removeProductButton;
    private TableConstruct tableConstruct;

    public ProductManager() {
        String[] columnNames = {
                "ID", "Name", "Prix Net", "% TVA", "Pts Fidélité", "Eligible", "Quantité minimum",
                "Quantité minimum promo", "Date mise en vente", "Temps avant retrait", "Catégorie"
        };

        productDBAccess = new ProductDBAccess();
        tableConstruct = new TableConstruct(columnNames);
        tableConstruct.fillProductTable(productDBAccess);

        titleLabel = new JLabel("Gestion des produits");
        titleLabel.setFont(new Font("Dialog", Font.PLAIN, 30));

        addProductButton = new JButton("Ajouter un produit");
        addProductButton.setPreferredSize(new Dimension(250, 60));

        removeProductButton = new JButton("Supprimer un produit");
        removeProductButton.setPreferredSize(new Dimension(250, 60));

        this.add(titleLabel);
        this.add(addProductButton);
        this.add(removeProductButton);
        this.add(new JScrollPane(tableConstruct.getTable()));
    }
}
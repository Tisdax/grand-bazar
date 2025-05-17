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

    public ProductManager() {
        JTable productTable = new JTable();
        DefaultTableModel tableModel = new DefaultTableModel();
        productTable.setModel(tableModel);
        tableModel.addColumn("ID");
        tableModel.addColumn("Name");
        tableModel.addColumn("Prix Net");
        tableModel.addColumn("% TVA");
        tableModel.addColumn("Pts Fidélité");
        tableModel.addColumn("Eligible");
        tableModel.addColumn("Quantité minimum");
        tableModel.addColumn("Quantité minimum promo");
        tableModel.addColumn("Date mise en vente");
        tableModel.addColumn("Temps avant retrait");
        tableModel.addColumn("Catégorie");

        productDBAccess = new ProductDBAccess();
        try {
            ArrayList<Product>products = productDBAccess.productList();

            for(Product product : products) {
                tableModel.addRow(new Object[] {
                        product.getId(), product.getName(), product.getNetPrice(), product.getVatPercentage(),
                        product.getLoyaltyPointsNb(), product.getEdible(), product.getMinQuantity(), product.getPromotionMinQuantity(),
                        product.getSaleDate(), product.getTimeBeforeRemoving(), product.getCategoryName()
                });
            }

        } catch (DAOException | InvalidValueException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }

        titleLabel = new JLabel("Gestion des produits");
        titleLabel.setFont(new Font("Dialog", Font.PLAIN, 30));

        addProductButton = new JButton("Ajouter un produit");
        addProductButton.setPreferredSize(new Dimension(250, 60));

        removeProductButton = new JButton("Supprimer un produit");
        removeProductButton.setPreferredSize(new Dimension(250, 60));

        this.add(titleLabel);
        this.add(addProductButton);
        this.add(removeProductButton);
        this.add(new JScrollPane(productTable));
    }
}
package userInterface.TableConstructs;

import exceptions.DAOException;
import exceptions.InvalidValueException;
import model.Product;

import javax.swing.*;
import java.util.ArrayList;

public class ProductTable extends TableConstruct {
    public ProductTable() {
        super(new String[] {
                "ID", "Nom", "Prix HTVA", "% TVA", "Nombre points fidélité", "Commestible", "Qtt min",
                "Qtt min en promo", "Date de mise en vente", "Temps avant retrait", "Catégorie"
        });
        fillTable();
    }
    public void fillTable() {
        try {
            ArrayList<Product> products = controller.findAllProducts();
            super.refreshTable();
            for(Product product : products) {
                tableModel.addRow(new Object[] {
                        product.getId(), product.getName(), product.getNetPrice(), product.getVatPercentage(),
                        product.getLoyaltyPointsNb(), product.getEdible() ? "Oui" : "Non", product.getMinQuantity(), product.getPromotionMinQuantity(),
                        product.getSaleDate(), product.getTimeBeforeRemoving(), product.getCategoryName()
                });
            }

        } catch (DAOException | InvalidValueException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }
}

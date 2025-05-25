package userInterface.TableConstructs;

import exceptions.DAOException;
import exceptions.InvalidValueException;
import model.Product;

import javax.swing.*;
import java.util.ArrayList;

public class ProductTable extends TableConstruct {
    public ProductTable() {
        super(new String[] {
                "ID", "Name", "Prix Net", "% TVA", "Pts Fidélité", "Eligible", "Qt min",
                "Qt min en promo", "Date mise en vente", "Temps avant retrait", "Catégorie"
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
                        product.getLoyaltyPointsNb(), product.getEdible(), product.getMinQuantity(), product.getPromotionMinQuantity(),
                        product.getSaleDate(), product.getTimeBeforeRemoving(), product.getCategoryName()
                });
            }

        } catch (DAOException | InvalidValueException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }

}

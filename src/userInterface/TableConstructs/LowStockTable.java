package userInterface.TableConstructs;

import exceptions.DAOException;
import exceptions.InvalidValueException;
import model.ProductLowStockInfo;
import userInterface.LowStockProductPanel;

import javax.swing.*;
import java.util.ArrayList;

public class LowStockTable extends TableConstruct {
    public LowStockTable() {
        super(new String[] {"ID", "Nom", "Qt Stock / Qt min", "%", "Nb manquants"});
        fillTable();
    }
    public void fillTable() {
        try {
            ArrayList<ProductLowStockInfo> products = controller.findProductsByLowStock();
            super.refreshTable();
            for (ProductLowStockInfo product : products) {
                String qtBasedOnTotal, nbMissingProducts;
                double qtPercentage;

                qtBasedOnTotal = product.getStockQuantity() +" / "+ product.getProductMinQuantity();
                qtPercentage = Math.floor(((double)product.getStockQuantity() / product.getProductMinQuantity()) * 100) * 100 / 100;
                nbMissingProducts = (product.getProductMinQuantity() - product.getStockQuantity()) + " produits manquants";

                tableModel.addRow(new Object[]{
                        product.getProductId(), product.getProductName(),
                        qtBasedOnTotal, qtPercentage + "%", nbMissingProducts});
            }

        } catch (DAOException | InvalidValueException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }
}

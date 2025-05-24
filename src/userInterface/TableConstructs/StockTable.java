package userInterface.TableConstructs;

import exceptions.DAOException;
import exceptions.InvalidValueException;
import model.ProductStockInfo;

import javax.swing.*;
import java.util.ArrayList;

public class StockTable extends TableConstruct {
    public StockTable() {
        super(new String[] {
                "Nom ¨produit", "Qt en stock", "Shelf Level", "Shelf ID", "Est réfrigérer"
        });
    }
    public void fillTable(String category) {
        try {
            ArrayList<ProductStockInfo> productStockInfos = controller.findProductsByCategoryId(category);
            for (ProductStockInfo productStockInfo : productStockInfos) {
                tableModel.addRow(new Object[] {
                        productStockInfo.getProductName(), productStockInfo.getStockQuantity(), productStockInfo.getShelfLevel(),
                        productStockInfo.getShelfId(), productStockInfo.getShelfRefregirated()
                });
            }
        } catch (DAOException | InvalidValueException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }
}

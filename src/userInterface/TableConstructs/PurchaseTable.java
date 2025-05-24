package userInterface.TableConstructs;

import exceptions.DAOException;
import exceptions.InvalidValueException;
import model.Product;
import model.ProductOrderSummary;

import javax.swing.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class PurchaseTable extends TableConstruct {
    public PurchaseTable() {
        super(new String[] {"ID Produit", "Nom", "Prix HTVA", "Qt", "ID Vente", "Date de Vente"});
    }
    public void fillPurchaseTable(LocalDate startDate, LocalDate endDate) {
        try {
            ArrayList<ProductOrderSummary> products = controller.findProductsBySaleDate(startDate, endDate);
            for(ProductOrderSummary product : products) {
                tableModel.addRow(new Object[] {
                        product.getProductId(), product.getProductName(), product.getProductNetPrice(),
                        product.getQuantity(), product.getSaleId(), product.getSaleDate()
                });
            }
        } catch (DAOException | InvalidValueException e) {
        JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }
}

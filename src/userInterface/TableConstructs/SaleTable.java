package userInterface.TableConstructs;

import businessLogic.InvoiceManager;
import exceptions.DAOException;
import exceptions.InvalidValueException;
import model.CommandLine;
import model.Product;
import model.ProductStockInfo;

import javax.swing.*;
import java.util.ArrayList;

public class SaleTable extends TableConstruct {
    public SaleTable() {
        super(new String[] {"ID", "Nom produit", "Prix unitaire", "Quantité"});
    }
    public void fillTable(int saleId) {
        try {
            ArrayList<CommandLine> commandLines = controller.findBySale(saleId);
            ArrayList<Double> netPrices;
            InvoiceManager invoiceManager;
            Product product;

            super.refreshTable();
            for (CommandLine commandLine : commandLines) {
                product = controller.findProductById(commandLine.getProduct());
                tableModel.addRow(new Object[] {
                        commandLine.getProduct(), product.getName(), product.getNetPrice(), commandLine.getQuantity()
                });
            }
          //  netPrices = invoiceManager.netPrice(commandLines)

        } catch (DAOException | InvalidValueException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }
}

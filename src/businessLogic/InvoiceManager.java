package businessLogic;

import exceptions.DAOException;
import exceptions.InvalidValueException;
import model.CommandLine;
import model.Product;

import java.util.ArrayList;

public class InvoiceManager {
    private ProductManager productManager;

    public InvoiceManager() {
        this.productManager = new ProductManager();
    }

    public ArrayList<Double> netPrice(ArrayList<CommandLine> commandLines) throws DAOException, InvalidValueException {
        ArrayList<Double> pricesExclVAT = new ArrayList<>();
        Product product;
        if (commandLines.size() == 0){
            return pricesExclVAT = null;
        } else {
            for (CommandLine commandLine : commandLines){
                product = productManager.findById(commandLine.getProduct());
                pricesExclVAT.add(product.getNetPrice() * commandLine.getQuantity());
            }
        }
        return pricesExclVAT;
    }

    public Double totalPriceVATIncl(ArrayList<CommandLine> commandLines) throws DAOException, InvalidValueException {
        Double pricesExclVAT;
        Double totalPriceVatIncluded = 0.00;
        Product product;
        if (commandLines.size() > 0){
            for (CommandLine commandLine : commandLines){
                product = productManager.findById(commandLine.getProduct());
                pricesExclVAT = product.getNetPrice() * commandLine.getQuantity();
                totalPriceVatIncluded += pricesExclVAT * (1 + (product.getVatPercentage() / 100.0));
            }
        }
        return totalPriceVatIncluded;
    }
}
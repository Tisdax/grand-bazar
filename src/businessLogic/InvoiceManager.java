package businessLogic;

import controller.ApplicationController;
import exceptions.DAOException;
import exceptions.InvalidValueException;
import model.CommandLine;
import model.Product;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class InvoiceManager {
    private ArrayList<CommandLine>  commandLines;
    private ApplicationController controller;


    public InvoiceManager(ArrayList<CommandLine> commandLines) {
        this.commandLines = commandLines;
        this.controller = new ApplicationController();
    }

    public ArrayList<Double> netPrice() throws DAOException, InvalidValueException {
        ArrayList<Double> pricesExclVAT = new ArrayList<>();

        if (commandLines.size() == 0){
            return pricesExclVAT = null;
        } else {
            for (CommandLine commandLine : commandLines){
                pricesExclVAT.add(controller.findProductById(commandLine.getProduct()).getNetPrice() * commandLine.getQuantity());
            }
        }
        return pricesExclVAT;
    }

    public Double totalPriceVATIncl() throws DAOException, InvalidValueException {
        Double pricesExclVAT;
        Double totalPriceVatIncluded = 0.00;
        if (commandLines.size() > 0){
            for (CommandLine commandLine : commandLines){
                Product product = controller.findProductById(commandLine.getProduct());
                pricesExclVAT = product.getNetPrice() * commandLine.getQuantity();
                totalPriceVatIncluded += pricesExclVAT * (1 + (product.getVatPercentage() / 100.0));
            }
        }
        return totalPriceVatIncluded;
    }
}

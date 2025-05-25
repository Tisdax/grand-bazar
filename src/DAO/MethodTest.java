package DAO;

import DAOinterfaces.*;
import exceptions.DAOException;
import exceptions.InvalidValueException;
import model.*;

import java.time.LocalDate;
import java.util.ArrayList;

public class MethodTest {
    public static void main(String[] args) {
        CustomerDAO customerDAO = new CustomerDBAccess();
        ProductDAO productDAO = new ProductDBAccess();
        AddressDAO addressDAO = new AddressDBAccess();
        CategoryDAO categoryDAO = new CategoryDBAccess();
        LocalityDAO localityDAO = new LocalityDBAccess();
        SaleDAO saleDAO = new SaleDBAccess();
        DAO dao = new DBAccess();
        CommandLineDAO commandLineDAO = new CommandLineDBAccess();
        StockDAO stockDAO = new StockDBAccess();

        try {
            Sale sale = new Sale(saleDAO.lastId()+1, 1, LocalDate.now(), 1);
            saleDAO.save(sale);

            CommandLine commandLine = new CommandLine(saleDAO.lastId(), "P001", 5);
            //choisir produit dans la liste
            // vérifier qu'il y a pas déjà une ligne de commande avec ce produit et cette vente
            if (!commandLineDAO.existsById(commandLine.getSale(), commandLine.getProduct())) {
                // si non : demander la quantité et ajouter
                commandLineDAO.save(commandLine);
            } else {
                // si oui : afficher une autre fenetre avec un jtextfield préremplit avec la qtt déjà présente pour ce produit et lui permettre de modif
                System.out.println("fenetre avec jspinner préremplit avec la qtt " + commandLineDAO.findById(commandLine.getSale(), commandLine.getProduct()).getQuantity());
                commandLineDAO.update(commandLine);
            }
            CommandLine commandLine2 = new CommandLine(saleDAO.lastId(), "P002", 3);
            if (!commandLineDAO.existsById(commandLine2.getSale(), commandLine2.getProduct())) {
                commandLineDAO.save(commandLine2);
            } else {
                System.out.println("fenetre avec jspinner préremplit avec la qtt " + commandLineDAO.findById(commandLine2.getSale(), commandLine2.getProduct()).getQuantity());
                commandLineDAO.update(commandLine2);
            }
            CommandLine commandLine3 = new CommandLine(saleDAO.lastId(), "P002", 7);
            if (!commandLineDAO.existsById(commandLine3.getSale(), commandLine3.getProduct())) {
                commandLineDAO.save(commandLine3);
            } else {
                System.out.println("fenetre avec jspinner préremplit avec la qtt " + commandLineDAO.findById(commandLine3.getSale(), commandLine3.getProduct()).getQuantity());
                commandLineDAO.update(commandLine3);
            }

            // une fois que l'utilisateur appuye sur le bouton "passer commande" ou un truc du genre
            // afficher toutes les lignes de commande
            ArrayList<CommandLine> commandLines = commandLineDAO.findBySale(saleDAO.lastId());
            for (CommandLine commandLiiiine : commandLines)
                System.out.println(commandLiiiine.getProduct() + " " + commandLiiiine.getQuantity());

            System.out.println(stockDAO.findQuantityByProduct("P020"));

            stockDAO.lowerStocks("P001", 89);

            dao.closeConnection();
        }
        catch (DAOException e) {
            System.out.println(e.getDescription());
        }
        catch (InvalidValueException e) {
            System.out.println(e.getMessage());
        }
    }
}

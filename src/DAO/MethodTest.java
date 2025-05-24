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

        try {
            Sale sale = new Sale(saleDAO.lastId()+1, 1, LocalDate.now(), 1);
            saleDAO.save(sale);
            CommandLine commandLine = new CommandLine(saleDAO.lastId(), "P001", 5);
            CommandLine commandLine2 = new CommandLine(saleDAO.lastId(), "P002", 3);
            commandLineDAO.save(commandLine);
            commandLineDAO.save(commandLine2);
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

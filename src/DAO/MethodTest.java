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
        EmployeeDAO employeeDAO = new EmployeeDBAccess();

        try {
            System.out.println(employeeDAO.existsById(2));
            ArrayList<Employee> employees = employeeDAO.findAll();
            for(Employee employee : employees)
                System.out.println(employee.getId() + " " + employee.getLastName() + " " + employee.getFirstName() + " " + employee.getManagerId());

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

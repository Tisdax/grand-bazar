package DAO;

import DAOinterfaces.SaleDAO;
import exceptions.DBAccesException;
import model.Sale;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class SaleDBAccess implements SaleDAO {
    public int deleteSale(int customerId) throws DBAccesException {
        String sqlInstruction = "delete from sale where customer = ?";
        try {
            int nbUpdatedLines = 0;
            Connection connection = SingletonConnection.getInstance();
            PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);
            CommandLineDBAccess commandLineDBAccess = new CommandLineDBAccess();

            preparedStatement.setInt(1, customerId);

            ArrayList<Sale> sales = getSales(customerId);
            for (Sale sale : sales) {
                nbUpdatedLines += commandLineDBAccess.deleteCommandLine(sale.getId());
            }

            nbUpdatedLines += preparedStatement.executeUpdate();

            return nbUpdatedLines;
        }
        catch (SQLException e) {
            throw new DBAccesException(e.getMessage());
        }
    }

    public ArrayList<Sale> getSales(int customerId) throws DBAccesException {
        String sqlInstruction = "select * from sale where customer = ?";
        try {
            Connection connection = SingletonConnection.getInstance();
            PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);

            preparedStatement.setInt(1, customerId);

            ResultSet data = preparedStatement.executeQuery();
            ArrayList<Sale> sales = new ArrayList<>();
            while (data.next()) {
                sales.add(new Sale(data.getInt("id"), customerId, data.getDate("date").toLocalDate(), data.getInt("employee")));
            }

            return sales;
        }
        catch (SQLException e) {
            throw new DBAccesException(e.getMessage());
        }
    }

    public int removeCustomerFromSales(int customerId) throws DBAccesException {
        String sqlInstruction = "update sale set customer = null where customer = ?";
        try {
            Connection connection = SingletonConnection.getInstance();
            PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);

            preparedStatement.setInt(1, customerId);

            return preparedStatement.executeUpdate();
        }
        catch (SQLException e) {
            throw new DBAccesException(e.getMessage());
        }
    }
}

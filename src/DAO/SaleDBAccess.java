package DAO;

import DAOinterfaces.SaleDAO;
import exceptions.DAOException;
import exceptions.InvalidValueException;
import model.Sale;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SaleDBAccess implements SaleDAO {
    public int lastId() throws DAOException {
        String sqlInstruction = "select MAX(id) from sale";
        try {
            Connection connection = SingletonConnection.getInstance();
            PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);

            ResultSet data = preparedStatement.executeQuery();

            return data.next() ? data.getInt(1) : 0;
        }
        catch (SQLException e) {
            throw new DAOException(e.getMessage(), "Erreur lors de la recheche du dernier identifiant de vente.");
        }
    }

    public void save(Sale sale) throws DAOException {
        String sqlInstruction = "insert into sale(id, date, employee) values (?, ?, ?)";
        try {
            Connection connection = SingletonConnection.getInstance();
            PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);

            preparedStatement.setInt(1, sale.getId());
            preparedStatement.setDate(2, java.sql.Date.valueOf(sale.getDate()));
            preparedStatement.setInt(3, sale.getEmployee());

            preparedStatement.executeUpdate();

            if (sale.getCustomer() != null) {
                sqlInstruction = "update sale set customer = ? where id = ?";

                preparedStatement = connection.prepareStatement(sqlInstruction);
                preparedStatement.setInt(1, sale.getCustomer());
                preparedStatement.setInt(2, sale.getId());

                preparedStatement.executeUpdate();
            }
        }
        catch (SQLException e) {
            throw new DAOException(e.getMessage(), "Erreur lors de l'ajout de la vente " + sale.getId());
        }
    }

    public int delete(int customerId) throws DAOException, InvalidValueException {
        String sqlInstruction = "delete from sale where customer = ?";
        try {
            int nbUpdatedLines = 0;
            Connection connection = SingletonConnection.getInstance();
            PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);
            CommandLineDBAccess commandLineDBAccess = new CommandLineDBAccess();

            preparedStatement.setInt(1, customerId);

            ArrayList<Sale> sales = findByCustomer(customerId);
            for (Sale sale : sales) {
                nbUpdatedLines += commandLineDBAccess.deleteBySale(sale.getId());
            }

            nbUpdatedLines += preparedStatement.executeUpdate();

            return nbUpdatedLines;
        }
        catch (SQLException e) {
            throw new DAOException(e.getMessage(), "Erreur lors de la suppression d'une vente liée au client " + customerId + ".");
        }
    }

    public ArrayList<Sale> findByCustomer(int customerId) throws DAOException, InvalidValueException {
        String sqlInstruction = "select * from sale where customer = ?";
        try {
            Connection connection = SingletonConnection.getInstance();
            PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);

            preparedStatement.setInt(1, customerId);

            ResultSet data = preparedStatement.executeQuery();
            ArrayList<Sale> sales = new ArrayList<>();
            while (data.next()) {
                sales.add(new Sale(data.getInt("id"), data.getInt("customer"), data.getDate("date").toLocalDate(), data.getInt("employee")));
            }

            return sales;
        }
        catch (SQLException e) {
            throw new DAOException(e.getMessage(), "Erreur lors de la récupération des ventes sur base de client " + customerId + ".");
        }
    }

    public int removeCustomer(int customerId) throws DAOException {
        String sqlInstruction = "update sale set customer = null where customer = ?";
        try {
            Connection connection = SingletonConnection.getInstance();
            PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);

            preparedStatement.setInt(1, customerId);

            return preparedStatement.executeUpdate();
        }
        catch (SQLException e) {
            throw new DAOException(e.getMessage(), "Erreur lors de la suppression du client " + customerId + " de ses ventes.");
        }
    }
}

package DAO;

import exceptions.DBAccesException;
import DAOinterfaces.AddressDAO;
import model.Address;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AddressDBAccess implements AddressDAO {
    public void addAddress(Address address) throws DBAccesException {
        String sqlInstruction = "insert into address(locality_zip_code, locality_name, street, house_number) values (?, ?, ?, ?)";
        try {
            Connection connection = SingletonConnection.getInstance();
            PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);

            connection.prepareStatement(sqlInstruction);

            preparedStatement.setString(1, address.getLocalityZipCode());
            preparedStatement.setString(2, address.getLocalityName());
            preparedStatement.setString(3, address.getStreet());
            preparedStatement.setInt(4, address.getHouseNumber());

            preparedStatement.executeUpdate();

            if (address.getPostal_box_number() != null) {
                sqlInstruction = "update address set postal_box_number = ? where locality_zip_code = ? and locality_name = ? and street = ? and house_number = ?";

                preparedStatement.setInt(1, address.getPostal_box_number());
                preparedStatement.setString(2, address.getLocalityZipCode());
                preparedStatement.setString(3, address.getLocalityName());
                preparedStatement.setString(4, address.getStreet());
                preparedStatement.setInt(5, address.getHouseNumber());

                preparedStatement.executeUpdate();
            }

        }
        catch (SQLException e) {
            throw new DBAccesException(e.getMessage(), "Erreur lors de l'ajout d'une adresse");
        }
    }

    public boolean exists(Address address) throws DBAccesException {
        String sqlInstruction = "select * from address where locality_zip_code = ? and locality_name = ? and street = ? and house_number = ?";
        try {
            Connection connection = SingletonConnection.getInstance();
            PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);

            preparedStatement.setString(1, address.getLocalityZipCode());
            preparedStatement.setString(2, address.getLocalityName());
            preparedStatement.setString(3, address.getStreet());
            preparedStatement.setInt(4, address.getHouseNumber());

            ResultSet data = preparedStatement.executeQuery();

            if (!data.next())
                return false;

            data.getString("locality_zip_code");
            return !data.wasNull();
        }
        catch (SQLException e) {
            throw new DBAccesException(e.getMessage(), "Erreur lors de la recherche d'une adresse dans la base de données");
        }
    }
}

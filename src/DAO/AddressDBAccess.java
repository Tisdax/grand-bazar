package DAO;

import exceptions.DAOException;
import DAOinterfaces.AddressDAO;
import exceptions.InvalidValueException;
import model.Address;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AddressDBAccess implements AddressDAO {
    public boolean existsById(Address address) throws DAOException {
        String sqlInstruction = "select * from address where locality_zip_code = ? and locality_name = ? and street = ? and house_number = ?";
        try {
            Connection connection = SingletonConnection.getInstance();
            PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);

            preparedStatement.setInt(1, address.getLocalityZipCode());
            preparedStatement.setString(2, address.getLocalityName());
            preparedStatement.setString(3, address.getStreet());
            preparedStatement.setString(4, address.getHouseNumber());

            ResultSet data = preparedStatement.executeQuery();

            return data.next();
        }
        catch (SQLException e) {
            throw new DAOException(e.getMessage(), "Erreur lors de la recherche de l'adresse " + address + " pour savoir si elle existe.");
        }
    }

    public Address findById(int localityZipCode, String localityName, String street, String houseNumber) throws DAOException, InvalidValueException {
        String sqlInstruction = "select * from address where locality_zip_code = ? and locality_name = ? and street = ? and house_number = ?";
        try {
            Connection connection = SingletonConnection.getInstance();
            PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);

            preparedStatement.setInt(1, localityZipCode);
            preparedStatement.setString(2, localityName);
            preparedStatement.setString(3, street);
            preparedStatement.setString(4, houseNumber);

            ResultSet data = preparedStatement.executeQuery();
            data.next();

            Address address = new Address(street, localityZipCode, localityName, houseNumber);

            int postalBoxNumber = data.getInt("postal_box_number");
            if (!data.wasNull())
                address.setPostalBoxNumber(postalBoxNumber);

            return address;
        }
        catch (SQLException e) {
            throw new DAOException(e.getMessage(), "Erreur lors de la récupération de l'adresse" + street + " " + houseNumber + ", " + localityZipCode + " " + localityName + " dans la base de données");
        }
    }

    public void save(Address address) throws DAOException {
        String sqlInstruction = "insert into address(locality_zip_code, locality_name, street, house_number) values (?, ?, ?, ?)";
        try {
            Connection connection = SingletonConnection.getInstance();
            PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);

            connection.prepareStatement(sqlInstruction);

            preparedStatement.setInt(1, address.getLocalityZipCode());
            preparedStatement.setString(2, address.getLocalityName());
            preparedStatement.setString(3, address.getStreet());
            preparedStatement.setString(4, address.getHouseNumber());

            preparedStatement.executeUpdate();

            if (address.getPostalBoxNumber() != null) {
                sqlInstruction = "update address set postal_box_number = ? where locality_zip_code = ? and locality_name = ? and street = ? and house_number = ?";

                preparedStatement = connection.prepareStatement(sqlInstruction);

                preparedStatement.setInt(1, address.getPostalBoxNumber());
                preparedStatement.setInt(2, address.getLocalityZipCode());
                preparedStatement.setString(3, address.getLocalityName());
                preparedStatement.setString(4, address.getStreet());
                preparedStatement.setString(5, address.getHouseNumber());

                preparedStatement.executeUpdate();
            }

        }
        catch (SQLException e) {
            throw new DAOException(e.getMessage(), "Erreur lors de l'ajout de l'adresse " + address);
        }
    }
}

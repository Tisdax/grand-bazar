package dataAccess;

import exceptions.DBAccesException;
import DAOinterfaces.CustomerDataAccess;
import model.Customer;

import java.sql.*;
import java.util.ArrayList;

public class CustomerDBAccess implements CustomerDataAccess {
    public void addCustomer(Customer customer) throws DBAccesException {
        String sqlInstruction = "insert into customer (id, last_name, first_name, birthdate, is_subscribed_to_newsletter, address_locality_zip_code, address_locality_name, address_street, address_house_number, type) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            Connection connection = SingletonConnection.getInstance();
            PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);

            preparedStatement.setInt(1, customer.getId());
            preparedStatement.setString(2, customer.getLastName());
            preparedStatement.setString(3, customer.getFirstName());
            preparedStatement.setDate(4, java.sql.Date.valueOf(customer.getBirthdate()));
            preparedStatement.setBoolean(5, customer.getSubscribedToNewsLetter());
            preparedStatement.setString(6, customer.getLocalityZipCode());
            preparedStatement.setString(7, customer.getLocalityName());
            preparedStatement.setString(8, customer.getAddressStreet());
            preparedStatement.setInt(9, customer.getHouseNumber());
            preparedStatement.setString(10, customer.getTypeName());

            preparedStatement.executeUpdate();

            if (customer.getPhone() != null) {
                sqlInstruction = "update customer set phone = ? where id = ?";

                preparedStatement = connection.prepareStatement(sqlInstruction);
                preparedStatement.setString(1, customer.getPhone());
                preparedStatement.setInt(2, customer.getId());

                preparedStatement.executeUpdate();
            }

            if (customer.getEmail() != null) {
                sqlInstruction = "update customer set mail = ? where id = ?";

                preparedStatement = connection.prepareStatement(sqlInstruction);
                preparedStatement.setString(1, customer.getEmail());
                preparedStatement.setInt(2, customer.getId());

                preparedStatement.executeUpdate();
            }

            if (customer.getVatNumber() != null) {
                sqlInstruction = "update customer set vat_number = ? where id = ?";

                preparedStatement = connection.prepareStatement(sqlInstruction);
                preparedStatement.setString(1, customer.getVatNumber());
                preparedStatement.setInt(2, customer.getId());

                preparedStatement.executeUpdate();
            }
        }
        catch (SQLException e) {
            throw new DBAccesException(e.getMessage());
        }
    }

    public int deleteCustomer(int customerId) throws  DBAccesException {
        String sqlInstruction = "delete from customer where id = ?";
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

    public void updateCustomer(Customer customer) throws DBAccesException {
        String sqlInstruction = "update customer set last_name = ?, first_name = ?, birthdate = ?, phone = ?, email = ?, is_subscribed_to_newsletter = ?, vat_number = ?, address_locality_zip_code = ?, address_locality_name = ?, address_street = ?, address_house_number = ?, type = ? where id = ?";
        try {
            Connection connection = SingletonConnection.getInstance();
            PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);
            preparedStatement.setInt(13, customer.getId());

            preparedStatement.setString(1, customer.getLastName());
            preparedStatement.setString(2, customer.getFirstName());
            preparedStatement.setDate(3, java.sql.Date.valueOf(customer.getBirthdate()));

            if (customer.getPhone() == null)
                preparedStatement.setNull(4, Types.VARCHAR);
            else
                preparedStatement.setString(4, customer.getPhone());

            if (customer.getEmail() == null)
                preparedStatement.setNull(5, Types.VARCHAR);
            else
                preparedStatement.setString(5, customer.getEmail());

            preparedStatement.setBoolean(6, customer.getSubscribedToNewsLetter());

            if (customer.getVatNumber() == null)
                preparedStatement.setNull(7, Types.VARCHAR);
            else
                preparedStatement.setString(7, customer.getVatNumber());

            preparedStatement.setString(8, customer.getLocalityZipCode());
            preparedStatement.setString(9, customer.getLocalityName());
            preparedStatement.setString(10, customer.getAddressStreet());
            preparedStatement.setInt(11, customer.getHouseNumber());
            preparedStatement.setString(12, customer.getTypeName());

            preparedStatement.executeUpdate();
        }
        catch (SQLException e) {
            throw new DBAccesException(e.getMessage());
        }
    }

    public ArrayList<Customer> customerList() throws  DBAccesException {
        String sqlInstruction = "select * from customer";
        try {
            Connection connection = SingletonConnection.getInstance();
            PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction); // utiliser une variable connection
            ResultSet data = preparedStatement.executeQuery();
            ArrayList<Customer> customers = new ArrayList<>();
            Customer customer;
            String phone, email, vatNumber;

            while (data.next()) {
                customer = new Customer(data.getInt("id"), data.getString("last_name"), data.getString("first_name"), data.getDate("birthdate").toLocalDate(), data.getBoolean("is_subscribed_to_newsletter"), data.getString("address_locality_zip_code"), data.getString("address_locality_name"), data.getString("address_street"), data.getInt("address_house_number"), data.getString("type"));

                phone = data.getString("phone");
                if (!data.wasNull())
                    customer.setPhone(phone);

                email = data.getString("email");
                if (!data.wasNull())
                    customer.setEmail(email);

                vatNumber = data.getString("vat_number");
                if (!data.wasNull())
                    customer.setVatNumber(vatNumber);

                customers.add(customer);
            }
            return customers;
        }
        catch (SQLException e) {
            throw new DBAccesException(e.getMessage());
        }
    }
}

package DAO;

import DAOinterfaces.DAO;
import exceptions.DAOException;
import DAOinterfaces.CustomerDAO;
import exceptions.InvalidValueException;
import model.Customer;
import model.CustomerAddressInfo;
import model.CustomerDeletionMode;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class CustomerDBAccess implements CustomerDAO {
    public boolean exists(int customerId) throws DAOException {
        String sqlInstruction = "select * from customer where id = ?";
        try {
            Connection connection = SingletonConnection.getInstance();
            PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);

            preparedStatement.setInt(1, customerId);

            ResultSet data = preparedStatement.executeQuery();

            return data.next();
        }
        catch (SQLException e) {
            throw new DAOException(e.getMessage(), "Erreur lors de la recherche d'un client");
        }
    }

    public Customer getCustomer(int customerId) throws DAOException, InvalidValueException {
        String sqlInstruction = "select * from customer where id = ?";
        try {
            Connection connection = SingletonConnection.getInstance();
            PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);

            preparedStatement.setInt(1, customerId);

            ResultSet data = preparedStatement.executeQuery();
            data.next();

            Customer customer = new Customer(data.getInt("id"), data.getString("last_name"), data.getString("first_name"),
                    data.getDate("birthdate").toLocalDate(), data.getBoolean("is_subscribed_to_newsletter"),
                    data.getInt("address_locality_zip_code"), data.getString("address_locality_name"),
                    data.getString("address_street"), data.getString("address_house_number"),
                    data.getString("type"));

            String phone = data.getString("phone");
            if (!data.wasNull())
                customer.setPhone(phone);

            String email = data.getString("email");
            if (!data.wasNull())
                customer.setEmail(email);

            String vatNumber = data.getString("vat_number");
            if (!data.wasNull())
                customer.setVatNumber(vatNumber);

            return customer;
        }
        catch (SQLException e) {
            throw new DAOException(e.getMessage(), "Erreur : id innexistant");
        }
    }

    public int lastId() throws DAOException {
        String sqlInstruction = "SELECT MAX(id) FROM customer";
        try {
            Connection connection = SingletonConnection.getInstance();
            PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);

            ResultSet data = preparedStatement.executeQuery();

            if (data.next()) {
                return data.getInt(1);
            } else {
                return 0;
            }
        }
        catch (SQLException e) {
            throw new DAOException(e.getMessage(), "Problème lors de la recheche du dernier identifiant");
        }
    }

    public void addCustomer(Customer customer) throws DAOException {
        String sqlInstruction = "insert into customer (id, last_name, first_name, birthdate, is_subscribed_to_newsletter, address_locality_zip_code, address_locality_name, address_street, address_house_number, type) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            Connection connection = SingletonConnection.getInstance();
            PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);

            preparedStatement.setInt(1, customer.getId());
            preparedStatement.setString(2, customer.getLastName());
            preparedStatement.setString(3, customer.getFirstName());
            preparedStatement.setDate(4, java.sql.Date.valueOf(customer.getBirthdate()));
            preparedStatement.setBoolean(5, customer.getSubscribedToNewsLetter());
            preparedStatement.setInt(6, customer.getLocalityZipCode());
            preparedStatement.setString(7, customer.getLocalityName());
            preparedStatement.setString(8, customer.getAddressStreet());
            preparedStatement.setString(9, customer.getHouseNumber());
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
                sqlInstruction = "update customer set email = ? where id = ?";

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
            throw new DAOException(e.getMessage(), "Erreur lors de l'ajout du client");
        }
    }

    public int deleteCustomer(int customerId, CustomerDeletionMode deleteMode) throws DAOException, InvalidValueException {
        String sqlInstruction = "delete from customer where id = ?";
        try {
            int nbUpdatedLines = 0;
            Connection connection = SingletonConnection.getInstance();
            PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);
            LoyaltyCardDBAccess loyaltyCardDAO = new LoyaltyCardDBAccess();
            SaleDBAccess saleDBAccess = new SaleDBAccess();

            nbUpdatedLines += loyaltyCardDAO.delete(customerId);
            switch(deleteMode) {
                case DELETE_SALES : nbUpdatedLines += saleDBAccess.deleteSale(customerId); break;
                case REMOVE_FROM_SALES : nbUpdatedLines += saleDBAccess.removeCustomerFromSales(customerId); break;
            }

            preparedStatement.setInt(1, customerId);

            nbUpdatedLines +=preparedStatement.executeUpdate();
            return nbUpdatedLines;
        }
        catch (SQLException e) {
            throw new DAOException(e.getMessage(), "Erreur lors de la suppression du client");
        }
    }

    public void updateCustomer(Customer customer) throws DAOException {
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

            preparedStatement.setInt(8, customer.getLocalityZipCode());
            preparedStatement.setString(9, customer.getLocalityName());
            preparedStatement.setString(10, customer.getAddressStreet());
            preparedStatement.setString(11, customer.getHouseNumber());
            preparedStatement.setString(12, customer.getTypeName());

            preparedStatement.executeUpdate();
        }
        catch (SQLException e) {
            throw new DAOException(e.getMessage(), "Erreur lors de la modification du client");
        }
    }

    public ArrayList<Customer> customerList() throws DAOException, InvalidValueException {
        String sqlInstruction = "select * from customer";
        try {
            Connection connection = SingletonConnection.getInstance();
            PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction); // utiliser une variable connection
            ResultSet data = preparedStatement.executeQuery();
            ArrayList<Customer> customers = new ArrayList<>();
            Customer customer;
            String phone, email, vatNumber;

            while (data.next()) {
                customer = new Customer(data.getInt("id"), data.getString("last_name"), data.getString("first_name"),
                        data.getDate("birthdate").toLocalDate(), data.getBoolean("is_subscribed_to_newsletter"),
                        data.getInt("address_locality_zip_code"), data.getString("address_locality_name"),
                        data.getString("address_street"), data.getString("address_house_number"),
                        data.getString("type"));


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
            throw new DAOException(e.getMessage(), "Erreur lors de la lecture des clients dans la base de données");
        }
    }

    public ArrayList<CustomerAddressInfo> CustomerAddressSearch(int nbPointsMin, int nbPointsMax) throws DAOException, InvalidValueException {
        String sqlInstruction = "select c.last_name as 'customer_last_name', c.first_name as 'customer_first_name', a.street, a.house_number, a.postal_box_number, l.zip_code, l.name as 'locality_name' from customer c inner join address a on c.address_locality_zip_code = a.locality_zip_code and c.address_locality_name = a.locality_name and c.address_street = a.street and c.address_house_number = a.house_number inner join locality l on a.locality_zip_code = l.zip_code and a.locality_name = l.name inner join loyalty_card lc on lc.customer = c.id where lc.total_points between ? and ?";
        try {
            Connection connection = SingletonConnection.getInstance();
            PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);

            preparedStatement.setInt(1, nbPointsMin);
            preparedStatement.setInt(2, nbPointsMax);

            ResultSet data = preparedStatement.executeQuery();
            ArrayList<CustomerAddressInfo> customersAddressInfos = new ArrayList<>();
            CustomerAddressInfo customerAddressInfo;
            int postalBoxNumber;

            while (data.next()) {
                customerAddressInfo = new CustomerAddressInfo(data.getString("customer_last_name"), data.getString("customer_first_name"), data.getString("street"), data.getString("locality_name"), data.getString("house_number"), data.getInt("zip_code"));

                postalBoxNumber = data.getInt("postal_box_number");
                if (!data.wasNull())
                    customerAddressInfo.setPostalBoxNumber(postalBoxNumber);

                customersAddressInfos.add(customerAddressInfo);
            }
            return customersAddressInfos;
        }
        catch (SQLException e) {
            throw new DAOException(e.getMessage(), "Erreur lors de la recherche de clients sur base d'un nombre de points de fidélité");
        }
    }
}

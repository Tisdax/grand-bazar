package DAO;

import exceptions.DAOException;
import DAOinterfaces.ProductDAO;
import exceptions.InvalidValueException;
import model.*;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class ProductDBAccess implements ProductDAO {
    public boolean existsById(String productId) throws DAOException {
        String sqlInstruction = "select * from product where id = ?";
        try {
            Connection connection = SingletonConnection.getInstance();
            PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);

            preparedStatement.setString(1, productId);

            ResultSet data = preparedStatement.executeQuery();

            return data.next();
        }
        catch (SQLException e) {
            throw new DAOException(e.getMessage(), "Erreur lors de la recherche du produit " + productId + " pour savoir s'il existe.");
        }
    }

    public Product findById(String productId) throws DAOException, InvalidValueException {
        String sqlInstruction = "select * from product where id = ?";
        try {
            Connection connection = SingletonConnection.getInstance();
            PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);

            preparedStatement.setString(1, productId);

            ResultSet data = preparedStatement.executeQuery();
            data.next();

            Product product = new Product(data.getString("id"), data.getString("name"), data.getDouble("net_price"), data.getInt("vat_percentage"), data.getInt("loyalty_points_nb"), data.getBoolean("is_edible"), data.getDate("sale_date").toLocalDate(), data.getString("category"));

            int minQuantity = data.getInt("min_quantity");
            if (!data.wasNull())
                product.setMinQuantity(minQuantity);

            int promotionMinQuantity = data.getInt("promotion_min_quantity");
            if (!data.wasNull())
                product.setPromotionMinQuantity(promotionMinQuantity);

            int timeBeforeRemoving = data.getInt("time_before_removing");
            if (!data.wasNull())
                product.setTimeBeforeRemoving(timeBeforeRemoving);

            return product;
        }
        catch (SQLException e) {
            throw new DAOException(e.getMessage(), "Erreur lors de la récupération du produit " + productId + " dans la base de données");
        }
    }

    public void save(Product product) throws DAOException {
        String sqlInstruction = "insert into product (id, name, net_price, vat_percentage, loyalty_points_nb, is_edible, sale_date, category) values (?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            Connection connection = SingletonConnection.getInstance();
            PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);

            preparedStatement.setString(1, product.getId());
            preparedStatement.setString(2, product.getName());
            preparedStatement.setDouble(3, product.getNetPrice());
            preparedStatement.setInt(4, product.getVatPercentage());
            preparedStatement.setInt(5, product.getLoyaltyPointsNb());
            preparedStatement.setBoolean(6, product.getEdible());
            preparedStatement.setDate(7, java.sql.Date.valueOf(product.getSaleDate()));
            preparedStatement.setString(8, product.getCategoryName());

            preparedStatement.executeUpdate();

            if (product.getMinQuantity() != null) {
                sqlInstruction = "update product set min_quantity = ? where id = ?";

                preparedStatement = connection.prepareStatement(sqlInstruction);
                preparedStatement.setInt(1, product.getMinQuantity());
                preparedStatement.setString(2, product.getId());

                preparedStatement.executeUpdate();
            }

            if (product.getPromotionMinQuantity() != null) {
                sqlInstruction = "update product set promotion_min_quantity = ? where id = ?";

                preparedStatement = connection.prepareStatement(sqlInstruction);
                preparedStatement.setInt(1, product.getPromotionMinQuantity());
                preparedStatement.setString(2, product.getId());

                preparedStatement.executeUpdate();
            }

            if (product.getTimeBeforeRemoving() != null) {
                sqlInstruction = "update product set time_before_removing = ? where id = ?";

                preparedStatement = connection.prepareStatement(sqlInstruction);
                preparedStatement.setInt(1, product.getTimeBeforeRemoving());
                preparedStatement.setString(2, product.getId());

                preparedStatement.executeUpdate();
            }
        }
        catch (SQLException e) {
            throw new DAOException(e.getMessage(), "Erreur lors de l'ajout du produit " + product.getId() + " " + product.getName() + ".");
        }
    }

    public int delete(String productId) throws DAOException {
        String sqlInstruction = "delete from product where id = ?";
        try {
            int nbUpdatedLines = 0;
            Connection connection = SingletonConnection.getInstance();
            PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);
            CommandLineDBAccess commandLineDBAccess = new CommandLineDBAccess();
            PromotionDBAccess promotionDBAccess = new PromotionDBAccess();
            StockDBAccess stockDBAccess = new StockDBAccess();

            nbUpdatedLines += commandLineDBAccess.deleteByProduct(productId);
            nbUpdatedLines += promotionDBAccess.delete(productId);
            nbUpdatedLines += stockDBAccess.deleteById(productId);

            preparedStatement.setString(1, productId);

            nbUpdatedLines += preparedStatement.executeUpdate();

            return nbUpdatedLines;
        }
        catch (SQLException e) {
            throw new DAOException(e.getMessage(), "Erreur lors de la supprression du produit " + productId + ".");
        }

    }

    public void update(Product product) throws DAOException {
        String sqlInstruction = "update product set name = ?, net_price = ?, vat_percentage = ?, loyalty_points_nb = ?, is_edible = ?, min_quantity = ?, promotion_min_quantity = ?, sale_date = ?, time_before_removing = ?, category = ? where id = ?";
        try {
            Connection connection = SingletonConnection.getInstance();
            PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);
            preparedStatement.setString(11, product.getId());

            preparedStatement.setString(1, product.getName());
            preparedStatement.setDouble(2, product.getNetPrice());
            preparedStatement.setInt(3, product.getVatPercentage());
            preparedStatement.setInt(4, product.getLoyaltyPointsNb());
            preparedStatement.setBoolean(5, product.getEdible());

            if (product.getMinQuantity() == null)
                preparedStatement.setNull(6, Types.INTEGER);
            else
                preparedStatement.setInt(6, product.getMinQuantity());

            if (product.getPromotionMinQuantity() == null)
                preparedStatement.setNull(7, Types.INTEGER);
            else
                preparedStatement.setInt(7, product.getPromotionMinQuantity());

            preparedStatement.setDate(8, java.sql.Date.valueOf(product.getSaleDate()));

            if (product.getTimeBeforeRemoving() == null)
                preparedStatement.setNull(9, Types.INTEGER);
            else
                preparedStatement.setInt(9, product.getTimeBeforeRemoving());

            preparedStatement.setString(10, product.getCategoryName());

            preparedStatement.executeUpdate();
        }
        catch (SQLException e) {
            throw new DAOException(e.getMessage(), "Erreur lors de la suppression du produit " + product.getId() + " " + product.getName() + ".");
        }
    }

    public ArrayList<Product> findAll() throws DAOException, InvalidValueException {
        String sqlInstruction = "select * from product";
        try {
            Connection connection = SingletonConnection.getInstance();
            PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);

            ResultSet data = preparedStatement.executeQuery();

            ArrayList<Product> products = new ArrayList<>();
            Product product;
            int minQuantity, promotionMinQuantity, timeBeforeRemoving;

            while (data.next()) {
                product = new Product(data.getString("id"), data.getString("name"), data.getDouble("net_price"), data.getInt("vat_percentage"), data.getInt("loyalty_points_nb"), data.getBoolean("is_edible"), data.getDate("sale_date").toLocalDate(), data.getString("category"));

                minQuantity = data.getInt("min_quantity");
                if (!data.wasNull())
                    product.setMinQuantity(minQuantity);

                promotionMinQuantity = data.getInt("promotion_min_quantity");
                if (!data.wasNull())
                    product.setPromotionMinQuantity(promotionMinQuantity);

                timeBeforeRemoving = data.getInt("time_before_removing");
                if (!data.wasNull())
                    product.setTimeBeforeRemoving(timeBeforeRemoving);

                products.add(product);
            }

            return products;
        }
        catch (SQLException e) {
            throw new DAOException(e.getMessage(), "Erreur lors de la récupération de la liste des produits.");
        }
    }

    public ArrayList<ProductStockInfo> findByCategoryId(String categoryId) throws DAOException, InvalidValueException {
        String sqlInstruction = "select p.name as 'product_name', st.quantity as 'stock_quantity', st.shelf_level as 'shelf_level', sh.id as 'shelf_id', sh.is_refrigirated as 'is_shelf_refregirated' from product p inner join stock st on p.id = st.product inner join shelf sh on st.shelf = sh.id where p.category = ?";
        try {
            Connection connection = SingletonConnection.getInstance();
            PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);

            preparedStatement.setString(1, categoryId);

            ResultSet data = preparedStatement.executeQuery();

            ArrayList<ProductStockInfo> productsInfos = new ArrayList<>();
            ProductStockInfo productInfos;

            while (data.next()) {
                productInfos = new ProductStockInfo(data.getString("product_name"), data.getInt("stock_quantity"), data.getInt("shelf_level"), data.getInt("shelf_id"), data.getBoolean("is_shelf_refregirated"));
                productsInfos.add(productInfos);
            }

            return productsInfos;
        }
        catch (SQLException e) {
            throw new DAOException(e.getMessage(), "Erreur lors de la recherche des produits sur base de la catégorie " + categoryId + ".");
        }
    }

    public ArrayList<ProductOrderSummary> findBySaleDate(LocalDate startDate, LocalDate endDate) throws DAOException, InvalidValueException {
        String sqlInstruction = "select p.id as 'product_id', p.name as 'product_name', p.net_price as 'product_net_price', c.quantity, s.id as 'sale_id', s.date as 'sale_date' from product p inner join command_line c on c.product = p.id inner join sale s on c.sale = s.id where s.date between ? and ?";
        try {
            Connection connection = SingletonConnection.getInstance();
            PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);

            preparedStatement.setDate(1, java.sql.Date.valueOf(startDate));
            preparedStatement.setDate(2, java.sql.Date.valueOf(endDate));

            ResultSet data = preparedStatement.executeQuery();

            ArrayList<ProductOrderSummary> productsOrderSummaries = new ArrayList<>();
            ProductOrderSummary productOrderSummary;

            while (data.next()) {
                productOrderSummary = new ProductOrderSummary(data.getString("product_id"), data.getString("product_name"), data.getDouble("product_net_price"), data.getInt("quantity"), data.getInt("sale_id"), data.getDate("sale_date").toLocalDate());
                productsOrderSummaries.add(productOrderSummary);
            }

            return productsOrderSummaries;
        }
        catch (SQLException e) {
            throw new DAOException(e.getMessage(), "Erreur lors de récupération de la liste des produits entre les dates " + startDate.getDayOfMonth() + "/" + startDate.getMonthValue() + "/" + startDate.getYear() + " et " + endDate.getDayOfMonth() + "/" + endDate.getMonthValue() + "/" + endDate.getYear() + ".");
        }
    }

    public ArrayList<ProductLowStockInfo> findByLowStock() throws DAOException, InvalidValueException {
        String sqlInstruction = "select p.id as 'product_id', p.name as 'product_name', sum(s.quantity) as 'stock_quantity', p.min_quantity as 'product_min_quantity' from product p inner join stock s on p.id = s.product group by p.id having p.min_quantity is not null";
        try {
            Connection connection = SingletonConnection.getInstance();
            PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);

            ResultSet data = preparedStatement.executeQuery();

            ArrayList<ProductLowStockInfo> productsLowStockInfos = new ArrayList<>();
            ProductLowStockInfo productLowStockInfo;

            while (data.next()) {
                productLowStockInfo = new ProductLowStockInfo(data.getString("product_id"), data.getString("product_name"), data.getInt("stock_quantity"), data.getInt("product_min_quantity"));
                productsLowStockInfos.add(productLowStockInfo);
            }

            return productsLowStockInfos;
        }
        catch (SQLException e) {
            throw new DAOException(e.getMessage(), "Erreur lors de la recherche des produits ayant une quantité en stock trop faible.");
        }

    }
}
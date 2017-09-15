package dao;

import dbtools.ConnectionFactory;
import dbtools.DBSettings;
import domain.Product;

import javax.xml.transform.Result;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDaoImpl implements ProductDao {

    private final Connection connection = ConnectionFactory.getConnection(
            DBSettings.DB_CONNECTION,
            DBSettings.DB_USER,
            DBSettings.DB_PASSWORD);

    private static final String FIND_ALL = "SELECT * FROM warehouse.\"Products\"";
    private static final String FIND_BY_NAME = "SELECT * FROM warehouse.\"Products\" WHERE name=?";
    private static final String INSERT_NEW = "INSERT INTO warehouse.\"Products\" (id,name) VALUES (?,?)";

    private List<Product> getProductsFromResultSet(ResultSet resultSet) {
        List<Product> result = new ArrayList<Product>();
        try {
            while (resultSet.next()) {
                Integer productId = resultSet.getInt("id");
                String name = resultSet.getString("name");
                Integer quantity = resultSet.getInt("quantity");
                Integer unitCost = resultSet.getInt("unitcost");
                Integer totalCost = resultSet.getInt("totalcost");
                Product product = new Product(productId, name);
                product.setCountry(resultSet.getString("country"));
                product.setQuantity(quantity);
                product.setTotalCost(totalCost);
                product.setUnitCost(unitCost);
                result.add(product);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    public List<Product> findAll() {
        Statement statement = null;
        List<Product> result = new ArrayList<Product>();
        try {
            statement = connection.createStatement();
            result = getProductsFromResultSet(statement.executeQuery(FIND_ALL));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        try {
            statement.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return result;
    }

    public Product findById(Integer id) {
        return null;
    }

    public Product findByName(String name) {
        PreparedStatement statement = null;
        Product result = null;
        try {
            statement = connection.prepareStatement(FIND_BY_NAME);
            statement.setString(1, name);
            List<Product> products = getProductsFromResultSet(statement.executeQuery());
            if (products.size() > 0)
                result = products.get(0);
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public void update(Product product) {

    }

    private void assignDataFromProduct(Product product, PreparedStatement statement){
        try {
            statement.setInt(1, product.getId());
            statement.setString(2, product.getName());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insert(Product product) {
        PreparedStatement statement;
        try {
            statement = connection.prepareStatement(INSERT_NEW);
            assignDataFromProduct(product, statement);
            //statement.setInt(1, product.getId());
            //statement.setString(2, product.getName());
            statement.executeUpdate();
            statement.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }


    }
}

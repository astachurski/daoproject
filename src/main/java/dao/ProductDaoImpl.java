package dao;

import dbtools.ConnectionFactory;
import dbtools.DBSettings;
import domain.Product;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ProductDaoImpl implements ProductDao {

    private final Connection connection = ConnectionFactory.getConnection(
            DBSettings.DB_CONNECTION,
            DBSettings.DB_USER,
            DBSettings.DB_PASSWORD);

    private static final String FIND_ALL = "SELECT * FROM warehouse.\"Products\"";

    public List<Product> findAll() {
        Statement statement = null;
        List<Product> result = new ArrayList<Product>();
        try {
            statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(FIND_ALL);
            while (rs.next()) {
                Integer productId = rs.getInt("id");
                String name = rs.getString("name");
                Integer quantity = rs.getInt("quantity");
                Integer unitCost = rs.getInt("unitcost");
                Integer totalCost = rs.getInt("totalcost");
                Product product = new Product(productId, name);
                product.setCountry(rs.getString("country"));
                product.setQuantity(quantity);
                product.setTotalCost(totalCost);
                product.setUnitCost(unitCost);
                result.add(product);
            }
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
        return null;
    }

    public void update(Product product) {

    }

    public void insert(Product product) {

    }
}

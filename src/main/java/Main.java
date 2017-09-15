import dao.ProductDao;
import dao.ProductDaoImpl;
import domain.Product;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("hello");

        ProductDao dao = new ProductDaoImpl();
        List<Product> products = dao.findAll();

        for (Product product : products) {
            System.out.println(product.getId() + " / " + product.getName());
        }

    }
}

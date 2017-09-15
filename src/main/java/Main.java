import dao.ProductDao;
import dao.ProductDaoImpl;
import dao.ProductSearchDao;
import domain.Product;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("hello");

        ProductSearchDao dao = new ProductDaoImpl();
        List<Product> products = dao.findAll();

        for (Product product : products) {
            System.out.println(product.getId() + " / " + product.getName());
        }

        Product product = dao.findByName("kefir");
        if (product != null)
            System.out.println(product.getName());
        else
            System.out.println(" no such product found");

        Product majonez = new Product(3, "majonez" );
        majonez.setCountry("germany");
        majonez.setUnitCost(234);
        ((ProductDao)dao).insert(majonez);
        majonez = dao.findByName("majonez");
        System.out.println("product found: " + majonez.getName());

    }
}

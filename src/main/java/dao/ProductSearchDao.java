package dao;

import domain.Product;

import java.util.List;

public interface ProductSearchDao {
    public List<Product> findAll();
    public Product findByName(String name);
    public Product findById(Integer id);
}

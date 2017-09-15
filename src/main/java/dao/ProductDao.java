package dao;

import domain.Product;

import java.util.List;

public interface ProductDao {
    public List<Product> findAll();
    public Product findByName(String name);
    public Product findById(Integer id);
    public void update(Product product);
    public void insert(Product product);
}

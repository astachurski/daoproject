package dao;

import domain.Product;

import java.util.List;

public interface ProductDao {
    public List<Product> findAll();
    public Product findById(Integer id);
    public Product findByName(String name);
    public void update(Product product);
    public void insert(Product product);
}

package dao;

import domain.Product;

import java.util.List;

public interface ProductDao {
    public void update(Product product);
    public void insert(Product product);
}

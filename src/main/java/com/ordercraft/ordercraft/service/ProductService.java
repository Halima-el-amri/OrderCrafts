// ProductService.java
package com.ordercraft.ordercraft.service;

import com.ordercraft.ordercraft.dao.ProductDao;
import com.ordercraft.ordercraft.model.classes.Product;

import java.util.List;

public class ProductService {
    private ProductDao productDao;

    public ProductService(ProductDao productDao) {
        this.productDao = productDao;
    }

    public List<Product> getAllProducts() {
        try {
            return productDao.getAllProducts();
        } catch (RuntimeException e) {
            e.printStackTrace();
            throw new RuntimeException("Error retrieving products from the database.", e);
        }
    }

    public Product getProductById(int productId) {
        try {
            return productDao.getProductById(productId);
        } catch (RuntimeException e) {
            e.printStackTrace();
            throw new RuntimeException("Error retrieving product from the database.", e);
        }
    }

    public void addProduct(Product product) {
        try {
            productDao.addProduct(product);
        } catch (RuntimeException e) {
            e.printStackTrace();
            throw new RuntimeException("Error adding product to the database.", e);
        }
    }

    public void updateProduct(Product product) {
        try {
            productDao.updateProduct(product);
        } catch (RuntimeException e) {
            e.printStackTrace();
            throw new RuntimeException("Error updating product in the database.", e);
        }
    }

    public void deleteProduct(int productId) {
        try {
            productDao.deleteProduct(productId);
        } catch (RuntimeException e) {
            e.printStackTrace();
            throw new RuntimeException("Error deleting product from the database.", e);
        }
    }
}

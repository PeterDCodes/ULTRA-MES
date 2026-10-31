package org.ultra.Domains.Products;

import java.sql.SQLException;
import java.util.List;

public class ProductHandler {

    public static Product makeProduct(String skuNumber, String productName) {
        Product newProduct = new Product(null, skuNumber, productName);

        ProductRepository productRepository = new ProductRepository();

        try {
            return productRepository.insert(newProduct);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<Product> getProducts() {
        ProductRepository productRepository = new ProductRepository();

        try {
            return productRepository.getProducts();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static Product getProductById(int productId) {
        ProductRepository productRepository = new ProductRepository();

        try {
            return productRepository.getProductById(productId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void removeProduct(int productId) {
        ProductRepository productRepository = new ProductRepository();

        try {
            productRepository.removeProduct(productId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}

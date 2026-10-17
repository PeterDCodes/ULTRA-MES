package org.ultra.domains.Products;

import org.ultra.database.DatabaseManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductRepository {

    public Product insert(Product product) throws SQLException {

        String sql = """
            INSERT INTO products (sku_number, product_name)
            VALUES (?, ?)
            RETURNING *
            """;

        try (
                Connection conn = DatabaseManager.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)
        ) {
            stmt.setString(1, product.getSkuNumber());
            stmt.setString(2, product.getProductName());

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    product.setProductId(rs.getInt("product_id"));
                    return product;
                }
            }
        }

        throw new SQLException("Failed to insert product");
    }

    public List<Product> getProducts() throws SQLException {
        String sql = """
            SELECT product_id, sku_number, product_name
            FROM products
            ORDER BY product_id
            """;
        List<Product> products = new ArrayList<>();

        try (
                Connection conn = DatabaseManager.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()
        ) {
            while (rs.next()) {
                products.add(new Product(
                        rs.getObject("product_id", Integer.class),
                        rs.getString("sku_number"),
                        rs.getString("product_name")
                ));
            }
        }

        return products;
    }

    public Product getProductById(int productId) throws SQLException {
        String sql = """
            SELECT product_id, sku_number, product_name
            FROM products
            WHERE product_id = ?
            """;

        try (
                Connection conn = DatabaseManager.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)
        ) {
            stmt.setInt(1, productId);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Product(
                            rs.getObject("product_id", Integer.class),
                            rs.getString("sku_number"),
                            rs.getString("product_name")
                    );
                }
            }
        }

        return null;
    }

    public void removeProduct(int productId) throws SQLException {
        String sql = """
            DELETE FROM products
            WHERE product_id = ?
            """;

        try (
                Connection conn = DatabaseManager.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)
        ) {
            stmt.setInt(1, productId);
            stmt.executeUpdate();
        }
    }

}

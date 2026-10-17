package org.ultra.domains.Products;

import java.sql.SQLException;

public class ProductUtils {

    public static boolean isValidProduct(Integer productId) throws SQLException {
        return productId != null && new ProductRepository().getProductById(productId) != null;
    }
}

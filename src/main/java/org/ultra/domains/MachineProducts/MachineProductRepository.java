package org.ultra.domains.MachineProducts;

import org.ultra.database.DatabaseManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MachineProductRepository {

    public MachineProduct insert(MachineProduct machineProduct) throws SQLException {

        String sql = """
            INSERT INTO machine_product (type_code, product_code)
            VALUES (?, ?)
            RETURNING *
            """;

        try (
                Connection conn = DatabaseManager.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)
        ) {
            stmt.setInt(1, machineProduct.getTypeCode());
            stmt.setInt(2, machineProduct.getProductCode());

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    machineProduct.setMachineProductCode(rs.getInt("machine_product_code"));
                    return machineProduct;
                }
            }
        }

        throw new SQLException("Failed to insert machineProduct");
    }

    public List<MachineProduct> getMachineProducts() throws SQLException {
        String sql = """
            SELECT machine_product_code, type_code, product_code
            FROM machine_product
            ORDER BY machine_product_code
            """;
        List<MachineProduct> machineProducts = new ArrayList<>();

        try (
                Connection conn = DatabaseManager.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()
        ) {
            while (rs.next()) {
                machineProducts.add(new MachineProduct(
                        rs.getObject("machine_product_code", Integer.class),
                        rs.getInt("type_code"),
                        rs.getInt("product_code")
                ));
            }
        }

        return machineProducts;
    }

    public MachineProduct getMachineProductById(int machineProductCode) throws SQLException {
        String sql = """
            SELECT machine_product_code, type_code, product_code
            FROM machine_product
            WHERE machine_product_code = ?
            """;

        try (
                Connection conn = DatabaseManager.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)
        ) {
            stmt.setInt(1, machineProductCode);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new MachineProduct(
                            rs.getObject("machine_product_code", Integer.class),
                            rs.getInt("type_code"),
                            rs.getInt("product_code")
                    );
                }
            }
        }

        return null;
    }

    public void removeMachineProduct(int machineProductCode) throws SQLException {
        String sql = """
            DELETE FROM machine_product
            WHERE machine_product_code = ?
            """;

        try (
                Connection conn = DatabaseManager.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)
        ) {
            stmt.setInt(1, machineProductCode);
            stmt.executeUpdate();
        }
    }

}

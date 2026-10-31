package org.ultra.Domains.OrderStatusCodes;

import org.ultra.Database.DatabaseManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderStatusCodeRepository {

    public OrderStatusCode insert(OrderStatusCode orderStatusCode) throws SQLException {

        String sql = """
            INSERT INTO order_status_codes (status)
            VALUES (?)
            RETURNING *
            """;

        try (
                Connection conn = DatabaseManager.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)
        ) {
            stmt.setString(1, orderStatusCode.getStatus());

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    orderStatusCode.setStatusId(rs.getInt("status_id"));
                    return orderStatusCode;
                }
            }
        }

        throw new SQLException("Failed to insert orderStatusCode");
    }

    public List<OrderStatusCode> getOrderStatusCodes() throws SQLException {
        String sql = """
            SELECT status_id, status
            FROM order_status_codes
            ORDER BY status_id
            """;
        List<OrderStatusCode> orderStatusCodes = new ArrayList<>();

        try (
                Connection conn = DatabaseManager.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()
        ) {
            while (rs.next()) {
                orderStatusCodes.add(new OrderStatusCode(
                        rs.getObject("status_id", Integer.class),
                        rs.getString("status")
                ));
            }
        }

        return orderStatusCodes;
    }

    public OrderStatusCode getOrderStatusCodeById(int statusId) throws SQLException {
        String sql = """
            SELECT status_id, status
            FROM order_status_codes
            WHERE status_id = ?
            """;

        try (
                Connection conn = DatabaseManager.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)
        ) {
            stmt.setInt(1, statusId);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new OrderStatusCode(
                            rs.getObject("status_id", Integer.class),
                            rs.getString("status")
                    );
                }
            }
        }

        return null;
    }

    public void removeOrderStatusCode(int statusId) throws SQLException {
        String sql = """
            DELETE FROM order_status_codes
            WHERE status_id = ?
            """;

        try (
                Connection conn = DatabaseManager.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)
        ) {
            stmt.setInt(1, statusId);
            stmt.executeUpdate();
        }
    }

}

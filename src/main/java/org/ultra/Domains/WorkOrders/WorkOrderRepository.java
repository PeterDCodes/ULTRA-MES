package org.ultra.Domains.WorkOrders;

import org.ultra.Database.DatabaseManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.time.OffsetDateTime;

public class WorkOrderRepository {

    public WorkOrder insert(WorkOrder workOrder) throws SQLException {

        String sql = """
            INSERT INTO work_orders (product_id, quantity, status_code)
            VALUES (?, ?, ?)
            RETURNING *
            """;

        try (
                Connection conn = DatabaseManager.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)
        ) {
            stmt.setInt(1, workOrder.getProductId());
            stmt.setInt(2, workOrder.getQuantity());
            stmt.setInt(3, workOrder.getStatusCode());

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new WorkOrder(
                            rs.getObject("order_id", Integer.class),
                            rs.getInt("product_id"),
                            rs.getInt("quantity"),
                            rs.getObject("created_at", OffsetDateTime.class),
                            rs.getInt("status_code")
                    );
                }
            }
        }

        throw new SQLException("Failed to insert workOrder");
    }

    public List<WorkOrder> getWorkOrders() throws SQLException {
        String sql = """
            SELECT order_id, product_id, quantity, created_at, status_code
            FROM work_orders
            ORDER BY order_id
            """;
        List<WorkOrder> workOrders = new ArrayList<>();

        try (
                Connection conn = DatabaseManager.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()
        ) {
            while (rs.next()) {
                workOrders.add(new WorkOrder(
                        rs.getObject("order_id", Integer.class),
                        rs.getInt("product_id"),
                        rs.getInt("quantity"),
                        rs.getObject("created_at", OffsetDateTime.class),
                        rs.getInt("status_code")
                ));
            }
        }

        return workOrders;
    }

    public WorkOrder getWorkOrderById(int orderId) throws SQLException {
        String sql = """
            SELECT order_id, product_id, quantity, created_at, status_code
            FROM work_orders
            WHERE order_id = ?
            """;

        try (
                Connection conn = DatabaseManager.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)
        ) {
            stmt.setInt(1, orderId);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new WorkOrder(
                            rs.getObject("order_id", Integer.class),
                            rs.getInt("product_id"),
                            rs.getInt("quantity"),
                            rs.getObject("created_at", OffsetDateTime.class),
                            rs.getInt("status_code")
                    );
                }
            }
        }

        return null;
    }

    public void removeWorkOrder(int orderId) throws SQLException {
        String sql = """
            DELETE FROM work_orders
            WHERE order_id = ?
            """;

        try (
                Connection conn = DatabaseManager.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)
        ) {
            stmt.setInt(1, orderId);
            stmt.executeUpdate();
        }
    }

}

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

    public WorkOrder changeOrderStatus(int orderId, int statusCode) throws SQLException {
        try (Connection conn = DatabaseManager.getConnection()) {
            conn.setAutoCommit(false);

            try {
                WorkOrder order;
                try (PreparedStatement stmt = conn.prepareStatement("""
                        SELECT order_id, product_id, quantity, created_at, status_code
                        FROM work_orders WHERE order_id = ? FOR UPDATE
                        """)) {
                    stmt.setInt(1, orderId);
                    try (ResultSet rs = stmt.executeQuery()) {
                        if (!rs.next()) {
                            throw new SQLException("Work order not found: " + orderId);
                        }
                        order = new WorkOrder(
                                rs.getInt("order_id"),
                                rs.getInt("product_id"),
                                rs.getInt("quantity"),
                                rs.getObject("created_at", OffsetDateTime.class),
                                rs.getInt("status_code")
                        );
                    }
                }

                try (PreparedStatement stmt = conn.prepareStatement("""
                        INSERT INTO work_order_history (work_order, prev_status, new_status, dt_record)
                        VALUES (?, ?, ?, CURRENT_TIMESTAMP)
                        """)) {
                    stmt.setInt(1, orderId);
                    stmt.setInt(2, order.getStatusCode());
                    stmt.setInt(3, statusCode);
                    stmt.executeUpdate();
                }

                try (PreparedStatement stmt = conn.prepareStatement(
                        "UPDATE work_orders SET status_code = ? WHERE order_id = ?")) {
                    stmt.setInt(1, statusCode);
                    stmt.setInt(2, orderId);
                    stmt.executeUpdate();
                }

                conn.commit();
                return new WorkOrder(order.getOrderId(), order.getProductId(), order.getQuantity(),
                        order.getCreatedAt(), statusCode);
            } catch (SQLException | RuntimeException e) {
                try {
                    conn.rollback();
                } catch (SQLException rollbackError) {
                    e.addSuppressed(rollbackError);
                }
                throw e;
            }
        }
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

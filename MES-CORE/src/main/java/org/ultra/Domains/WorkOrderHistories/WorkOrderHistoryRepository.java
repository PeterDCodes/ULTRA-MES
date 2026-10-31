package org.ultra.Domains.WorkOrderHistories;

import org.ultra.Database.DatabaseManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

public class WorkOrderHistoryRepository {

    public WorkOrderHistory insert(WorkOrderHistory history) throws SQLException {
        String sql = """
            INSERT INTO work_order_history (work_order, prev_status, new_status, dt_record)
            VALUES (?, ?, ?, ?)
            RETURNING record_id
            """;

        try (
                Connection conn = DatabaseManager.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)
        ) {
            stmt.setObject(1, history.getWorkOrder(), Types.INTEGER);
            stmt.setObject(2, history.getPrevStatus(), Types.INTEGER);
            stmt.setObject(3, history.getNewStatus(), Types.INTEGER);
            stmt.setObject(4, history.getDtRecord(), Types.TIMESTAMP_WITH_TIMEZONE);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    history.setRecordId(rs.getInt("record_id"));
                    return history;
                }
            }
        }

        throw new SQLException("Failed to insert work order history");
    }

    public List<WorkOrderHistory> getWorkOrderHistories() throws SQLException {
        String sql = """
            SELECT record_id, work_order, prev_status, new_status, dt_record
            FROM work_order_history
            ORDER BY record_id
            """;
        List<WorkOrderHistory> histories = new ArrayList<>();

        try (
                Connection conn = DatabaseManager.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()
        ) {
            while (rs.next()) {
                histories.add(mapHistory(rs));
            }
        }

        return histories;
    }

    public WorkOrderHistory getWorkOrderHistoryById(int recordId) throws SQLException {
        String sql = """
            SELECT record_id, work_order, prev_status, new_status, dt_record
            FROM work_order_history
            WHERE record_id = ?
            """;

        try (
                Connection conn = DatabaseManager.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)
        ) {
            stmt.setInt(1, recordId);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapHistory(rs);
                }
            }
        }

        return null;
    }

    public void removeWorkOrderHistory(int recordId) throws SQLException {
        String sql = "DELETE FROM work_order_history WHERE record_id = ?";

        try (
                Connection conn = DatabaseManager.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)
        ) {
            stmt.setInt(1, recordId);
            stmt.executeUpdate();
        }
    }

    private WorkOrderHistory mapHistory(ResultSet rs) throws SQLException {
        return new WorkOrderHistory(
                rs.getObject("record_id", Integer.class),
                rs.getObject("work_order", Integer.class),
                rs.getObject("prev_status", Integer.class),
                rs.getObject("new_status", Integer.class),
                rs.getObject("dt_record", OffsetDateTime.class)
        );
    }
}

package org.ultra.Domains.MachineAssignments;

import org.ultra.Database.DatabaseManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.time.OffsetDateTime;

public class MachineAssignmentRepository {

    public MachineAssignment insert(MachineAssignment machineAssignment) throws SQLException {

        String sql = """
            INSERT INTO machine_assignment (area_code, machine_code, active)
            VALUES (?, ?, ?)
            RETURNING *
            """;

        try (
                Connection conn = DatabaseManager.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)
        ) {
            stmt.setInt(1, machineAssignment.getAreaCode());
            stmt.setInt(2, machineAssignment.getMachineCode());
            stmt.setBoolean(3, machineAssignment.isActive());

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new MachineAssignment(
                            rs.getObject("assignment_code", Integer.class),
                            rs.getInt("area_code"),
                            rs.getInt("machine_code"),
                            rs.getObject("created_at", OffsetDateTime.class),
                            rs.getBoolean("active")
                    );
                }
            }
        }

        throw new SQLException("Failed to insert machineAssignment");
    }

    public List<MachineAssignment> getMachineAssignments() throws SQLException {
        String sql = """
            SELECT assignment_code, area_code, machine_code, created_at, active
            FROM machine_assignment
            ORDER BY assignment_code
            """;
        List<MachineAssignment> machineAssignments = new ArrayList<>();

        try (
                Connection conn = DatabaseManager.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()
        ) {
            while (rs.next()) {
                machineAssignments.add(new MachineAssignment(
                        rs.getObject("assignment_code", Integer.class),
                        rs.getInt("area_code"),
                        rs.getInt("machine_code"),
                        rs.getObject("created_at", OffsetDateTime.class),
                        rs.getBoolean("active")
                ));
            }
        }

        return machineAssignments;
    }

    public MachineAssignment getMachineAssignmentById(int assignmentCode) throws SQLException {
        String sql = """
            SELECT assignment_code, area_code, machine_code, created_at, active
            FROM machine_assignment
            WHERE assignment_code = ?
            """;

        try (
                Connection conn = DatabaseManager.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)
        ) {
            stmt.setInt(1, assignmentCode);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new MachineAssignment(
                            rs.getObject("assignment_code", Integer.class),
                            rs.getInt("area_code"),
                            rs.getInt("machine_code"),
                            rs.getObject("created_at", OffsetDateTime.class),
                            rs.getBoolean("active")
                    );
                }
            }
        }

        return null;
    }

    public void removeMachineAssignment(int assignmentCode) throws SQLException {
        String sql = """
            DELETE FROM machine_assignment
            WHERE assignment_code = ?
            """;

        try (
                Connection conn = DatabaseManager.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)
        ) {
            stmt.setInt(1, assignmentCode);
            stmt.executeUpdate();
        }
    }

}

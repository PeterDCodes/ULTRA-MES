package org.ultra.Domains.Machines;

import org.ultra.Database.DatabaseManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MachineRepository {

    public Machine insert(Machine machine) throws SQLException {

        String sql = """
            INSERT INTO machines (machine_id, machine_type, active)
            VALUES (?, ?, ?)
            RETURNING *
            """;

        try (
                Connection conn = DatabaseManager.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)
        ) {
            stmt.setObject(1, machine.getMachineId());
            stmt.setInt(2, machine.getMachineType());
            stmt.setBoolean(3, machine.isActive());

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    machine.setMachineCode(rs.getInt("machine_code"));
                    return machine;
                }
            }
        }

        throw new SQLException("Failed to insert machine");
    }

    public List<Machine> getMachines() throws SQLException {
        String sql = """
            SELECT machine_code, machine_id, machine_type, active
            FROM machines
            ORDER BY machine_code
            """;
        List<Machine> machines = new ArrayList<>();

        try (
                Connection conn = DatabaseManager.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()
        ) {
            while (rs.next()) {
                machines.add(new Machine(
                        rs.getObject("machine_code", Integer.class),
                        rs.getObject("machine_id", Integer.class),
                        rs.getInt("machine_type"),
                        rs.getBoolean("active")
                ));
            }
        }

        return machines;
    }

    public Machine getMachineById(int machineCode) throws SQLException {
        String sql = """
            SELECT machine_code, machine_id, machine_type, active
            FROM machines
            WHERE machine_code = ?
            """;

        try (
                Connection conn = DatabaseManager.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)
        ) {
            stmt.setInt(1, machineCode);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Machine(
                            rs.getObject("machine_code", Integer.class),
                            rs.getObject("machine_id", Integer.class),
                            rs.getInt("machine_type"),
                            rs.getBoolean("active")
                    );
                }
            }
        }

        return null;
    }

    public void removeMachine(int machineCode) throws SQLException {
        String sql = """
            DELETE FROM machines
            WHERE machine_code = ?
            """;

        try (
                Connection conn = DatabaseManager.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)
        ) {
            stmt.setInt(1, machineCode);
            stmt.executeUpdate();
        }
    }

}

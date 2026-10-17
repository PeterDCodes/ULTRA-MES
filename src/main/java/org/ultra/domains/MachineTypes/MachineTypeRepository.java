package org.ultra.domains.MachineTypes;

import org.ultra.database.DatabaseManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MachineTypeRepository {

    public MachineType insert(MachineType machineType) throws SQLException {

        String sql = """
            INSERT INTO machine_types (machine_type)
            VALUES (?)
            RETURNING *
            """;

        try (
                Connection conn = DatabaseManager.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)
        ) {
            stmt.setString(1, machineType.getMachineType());

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    machineType.setTypeCode(rs.getInt("type_code"));
                    return machineType;
                }
            }
        }

        throw new SQLException("Failed to insert machineType");
    }

    public List<MachineType> getMachineTypes() throws SQLException {
        String sql = """
            SELECT type_code, machine_type
            FROM machine_types
            ORDER BY type_code
            """;
        List<MachineType> machineTypes = new ArrayList<>();

        try (
                Connection conn = DatabaseManager.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()
        ) {
            while (rs.next()) {
                machineTypes.add(new MachineType(
                        rs.getObject("type_code", Integer.class),
                        rs.getString("machine_type")
                ));
            }
        }

        return machineTypes;
    }

    public MachineType getMachineTypeById(int typeCode) throws SQLException {
        String sql = """
            SELECT type_code, machine_type
            FROM machine_types
            WHERE type_code = ?
            """;

        try (
                Connection conn = DatabaseManager.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)
        ) {
            stmt.setInt(1, typeCode);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new MachineType(
                            rs.getObject("type_code", Integer.class),
                            rs.getString("machine_type")
                    );
                }
            }
        }

        return null;
    }

    public void removeMachineType(int typeCode) throws SQLException {
        String sql = """
            DELETE FROM machine_types
            WHERE type_code = ?
            """;

        try (
                Connection conn = DatabaseManager.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)
        ) {
            stmt.setInt(1, typeCode);
            stmt.executeUpdate();
        }
    }

}

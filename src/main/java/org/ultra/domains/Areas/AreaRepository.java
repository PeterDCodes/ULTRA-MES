package org.ultra.domains.Areas;

import org.ultra.database.DatabaseManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AreaRepository {

    public Area insert(Area area) throws SQLException {

        String sql = """
            INSERT INTO areas (site_code, area_name, active)
            VALUES (?, ?, ?)
            RETURNING *
            """;

        try (
                Connection conn = DatabaseManager.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)
        ) {
            stmt.setInt(1, area.getSiteCode());
            stmt.setString(2, area.getAreaName());
            stmt.setBoolean(3, area.isActive());

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    area.setAreaCode(rs.getInt("area_code"));
                    return area;
                }
            }
        }

        throw new SQLException("Failed to insert area");
    }

    public List<Area> getAreas() throws SQLException {
        String sql = """
            SELECT area_code, site_code, area_name, active
            FROM areas
            ORDER BY area_code
            """;
        List<Area> areas = new ArrayList<>();

        try (
                Connection conn = DatabaseManager.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()
        ) {
            while (rs.next()) {
                areas.add(new Area(
                        rs.getObject("area_code", Integer.class),
                        rs.getInt("site_code"),
                        rs.getString("area_name"),
                        rs.getBoolean("active")
                ));
            }
        }

        return areas;
    }

    public Area getAreaById(int areaCode) throws SQLException {
        String sql = """
            SELECT area_code, site_code, area_name, active
            FROM areas
            WHERE area_code = ?
            """;

        try (
                Connection conn = DatabaseManager.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)
        ) {
            stmt.setInt(1, areaCode);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Area(
                            rs.getObject("area_code", Integer.class),
                            rs.getInt("site_code"),
                            rs.getString("area_name"),
                            rs.getBoolean("active")
                    );
                }
            }
        }

        return null;
    }

    public void removeArea(int areaCode) throws SQLException {
        String sql = """
            DELETE FROM areas
            WHERE area_code = ?
            """;

        try (
                Connection conn = DatabaseManager.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)
        ) {
            stmt.setInt(1, areaCode);
            stmt.executeUpdate();
        }
    }

}

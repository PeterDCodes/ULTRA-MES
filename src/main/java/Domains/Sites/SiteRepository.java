package Domains.Sites;

import Database.DatabaseManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SiteRepository {

    public Site insert(Site site) throws SQLException {

        String sql = """
            INSERT INTO sites (site_name, active)
            VALUES (?, ?)
            RETURNING *
            """;

        try (
                Connection conn = DatabaseManager.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)
        ) {
            stmt.setString(1, site.getSiteName());
            stmt.setBoolean(2, site.isActive());

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    site.setSiteCode(rs.getInt("site_code"));
                    return site;
                }
            }
        }

        throw new SQLException("Failed to insert site");
    }

    public List<Site> getSites() throws SQLException {
        String sql = """
            SELECT site_code, site_name, active
            FROM sites
            ORDER BY site_code
            """;
        List<Site> sites = new ArrayList<>();

        try (
                Connection conn = DatabaseManager.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()
        ) {
            while (rs.next()) {
                sites.add(new Site(
                        rs.getInt("site_code"),
                        rs.getString("site_name"),
                        rs.getBoolean("active")
                ));
            }
        }

        return sites;
    }

}

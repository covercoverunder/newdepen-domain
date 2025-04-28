package edu.gmu.cs321;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FormCounter {

    public static int count(String status) {
        String query = "SELECT COUNT(*) FROM Form WHERE status = ?";
        try (Connection conn = SQLProcessor.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, status);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static List<Integer> getAllFormIDs() {
        List<Integer> ids = new ArrayList<>();
        String query = "SELECT id FROM Form";
        try (Connection conn = SQLProcessor.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                ids.add(rs.getInt("id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ids;
    }
}

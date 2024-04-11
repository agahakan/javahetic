package org.example.javahetic;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcDataReader implements DataReader {

    private final String dbUrl;
    private final String username;
    private final String password;

    public JdbcDataReader(String dbUrl, String username, String password) {
        this.dbUrl = dbUrl;
        this.username = username;
        this.password = password;
    }

    @Override
    public List<String> readData() throws IOException {
        List<String> operations = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(dbUrl, username, password)) {
            String sql = "SELECT l.param1, l.param2, l.operateur FROM ligne l JOIN fichier f ON l.fichier_id = f.id WHERE f.type = 'OP'";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                ResultSet rs = stmt.executeQuery();
                while (rs.next()) {
                    String operation = rs.getInt("param1") + " " + rs.getInt("param2") + " " + rs.getString("operateur").trim();
                    operations.add(operation);
                }
            }
        } catch (SQLException e) {
            throw new IOException("Database access error", e);
        }
        return operations;
    }
}

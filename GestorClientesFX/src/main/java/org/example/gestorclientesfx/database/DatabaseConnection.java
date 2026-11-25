package org.example.gestorclientesfx.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConnection {
    private static final String URL = "jdbc:sqlite:clientes_fx.db";

    public static Connection getConnection() throws SQLException {
        Connection con = DriverManager.getConnection(URL);
        crearTablaSiNoExiste(con);
        return con;
    }

    private static void crearTablaSiNoExiste(Connection con) throws SQLException {
        String sql = """
            CREATE TABLE IF NOT EXISTS cliente (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                nombre TEXT NOT NULL,
                correo TEXT NOT NULL,
                ciudad TEXT NOT NULL
            );
            """;
        try (Statement st = con.createStatement()) {
            st.execute(sql);
        }
    }
}


package com.example.qlcovid.string;

import java.sql.*;

public class DatabaseConnection {
    public static Connection getJDBC() {


        Connection connection = null;
        try {

            String driverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
            Class.forName(driverName);


//            String serverName = "DESKTOP-GN3V8MM";
//            String mydatabase = "HeThongQuanLiCovid";

            //String url = "jdbc:sqlserver://NIERA-ASUS\\SQLEXPRESS; databaseName=HeThongQuanLiCovid; user=sa; password=12345678";
            String url = "jdbc:sqlserver://localhost:1433;databaseName=HeThongQuanLiCovid;user=sa;password=12345678";



//            String username = "sa";
//            String password = "12345678";
            connection = DriverManager.getConnection(url);
            return connection;
        } catch (ClassNotFoundException e) {
            // Could not find the database driver
        } catch (SQLException e) {
            // Could not connect to the database
        }
        return null;

    }

    public static void main(String[] args) throws SQLException {
        PreparedStatement statement = DatabaseConnection.getJDBC().prepareStatement("SELECT * FROM patient_history " +
                "WHERE patient_id=?");
        statement.setString(1, "Khoidb");
        statement.execute();
    }


}


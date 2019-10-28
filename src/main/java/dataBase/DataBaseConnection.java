package dataBase;

import java.sql.*;

public class DataBaseConnection {
    public void database() {
        final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
        final String DB_URL = "jdbc:mysql://assessor-demo.isida.by:3306/assessor_nbrb";

        final String user = "root";
        final String password = "1";

        Connection conn = null;

        Statement stmt = null;

        try {
            Class.forName(JDBC_DRIVER);
            try {
                conn = DriverManager.getConnection(DB_URL, user, password);
                stmt = conn.createStatement();
                String sql = "SELECT r.id, r.name FROM rooms r";
                ResultSet executeQuery = stmt.executeQuery(sql);

                while (executeQuery.next()) {
                    String id = executeQuery.getString("id");
                    String name = executeQuery.getString("name");
                    System.out.println(id);
                    System.out.println(name);
                }
                executeQuery.close();
                stmt.close();
                conn.close();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


    }
}

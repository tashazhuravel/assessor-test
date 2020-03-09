package dataBase;

import org.junit.rules.TestWatcher;
import org.junit.runner.Description;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DataBaseConnection extends TestWatcher {
    private Connection conn = null;
    public Statement stmt = null;

    private String db_url;

    public DataBaseConnection(String db_url) {
        this.db_url = db_url;
    }

    @Override
    protected void starting(Description description) {
        final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
        final String DB_URL = "jdbc:mysql://assessor-demo.isida.by:3306/assessor_nbrb";

        final String user = "root";
        final String password = "1";

        try {
            Class.forName(JDBC_DRIVER);
            try {
                conn = DriverManager.getConnection(db_url, user, password);
                stmt = conn.createStatement();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();

        }
    }

    @Override
    protected void finished(Description description) {
        try {
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}

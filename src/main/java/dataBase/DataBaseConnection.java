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
    final String user;
    final String pass;
    public DataBaseConnection(String db_url, String user, String pass) {
        this.db_url = db_url;
        this.user = user;
        this.pass = pass;
    }

    @Override
    protected void starting(Description description) {
        final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
        try {
            Class.forName(JDBC_DRIVER);
            try {
                conn = DriverManager.getConnection(db_url, user, pass);
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

package dataBase;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AssesorService {

    private Statement statement;

    public AssesorService(Statement statement) {
        this.statement = statement;
    }

    public List<String> getNamesRoom() {
        List<String> roomsName = new ArrayList<>();
        ResultSet resultSet = null;
        try {
            resultSet = statement.executeQuery("SELECT r.name FROM rooms r ORDER BY r.name ASC");
            while (resultSet.next()) {
                roomsName.add(resultSet.getString("name"));
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return roomsName;
    }
}

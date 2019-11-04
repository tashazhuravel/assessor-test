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
        ResultSet resultSet;
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

    public List<String> getFIOParticipantSitting() {
        List<String> participantFio = new ArrayList<>();
        ResultSet resultSet = null;
        try {
            resultSet = statement.executeQuery("SELECT u.user_real_name FROM committee_secretary c2 LEFT JOIN user u ON u.user_id = c2.user_id  WHERE c2.committee_id = '64' " +
                    "UNION " +
                    "SELECT u.user_real_name FROM committee c1 LEFT JOIN user u ON u.user_id = c1.deputy  WHERE c1.id = '64'" +
                    "UNION " +
                    "SELECT u.user_real_name FROM committee c1 LEFT JOIN user u ON u.user_id = c1.chairman  WHERE c1.id = '64' "
            );
            while (resultSet.next()) {
                participantFio.add(resultSet.getString("user_real_name"));
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return participantFio;
    }
}

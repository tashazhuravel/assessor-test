package dataBase;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AssessorService implements AssessorServiceImp {

    private Statement statement;

    public AssessorService(Statement statement) {
        this.statement = statement;
    }

    @Override
    public List<String> getNamesRoom() {
        List<String> roomsName = new ArrayList<>();
        ResultSet resultSet;
        try {
            resultSet = statement.executeQuery("SELECT name FROM rooms ORDER BY name ASC");
            while (resultSet.next()) {
                roomsName.add(resultSet.getString("name"));
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return roomsName;
    }

    @Override
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

    @Override
    public List<String> getFIOSecretaryOfCommittee() {
        List<String> secretaryFio = new ArrayList<>();
        ResultSet resultSet = null;
        try {
            resultSet = statement.executeQuery("SELECT u.user_real_name FROM committee_secretary c2 LEFT JOIN user u ON u.user_id = c2.user_id  WHERE c2.committee_id = '64'");
            while (resultSet.next()) {
                secretaryFio.add(resultSet.getString("user_real_name"));
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return secretaryFio;
    }

    @Override
    public List<String> getFIOAllParticipant(){
        List<String> allParticipantFio = new ArrayList<>();
        ResultSet resultSet = null;
        try{
            resultSet = statement.executeQuery("")
        }
    }
}

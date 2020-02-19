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
                    "UNION" +
                    " SELECT u.user_real_name FROM committee c1 LEFT JOIN user u ON u.user_id = c1.deputy WHERE c1.id = '64' " +
                    "UNION" +
                    " SELECT u.user_real_name FROM committee c1 LEFT JOIN user u ON u.user_id = c1.chairman WHERE c1.id = '64' " +
                    "UNION" +
                    " SELECT u.user_real_name FROM committee_users c3 JOIN user u ON u.user_id = c3.user_id WHERE c3.committee_id = '64'"
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
    public List<String> getFIOAllParticipant() {
        List<String> allParticipantFio = new ArrayList<>();
        ResultSet resultSet = null;
        try {
            resultSet = statement.executeQuery("SELECT s.members FROM sittings s INNER JOIN committee c ON s.num=c.num AND s.committee = c.id WHERE c.id = '64'");
            while (resultSet.next()) {
                allParticipantFio.add(resultSet.getString("members"));
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allParticipantFio;
    }

    @Override
    public List<String> getSubjectQuestion() {
        List<String> subjectQuestion = new ArrayList<>();
        ResultSet resultSet = null;
        try {
            resultSet = statement.executeQuery("SELECT q.subject FROM questions q INNER JOIN sittings s ON q.sitting = s.id AND s.committee = '64' WHERE q.status = '1'");
            while (resultSet.next()) {
                subjectQuestion.add(resultSet.getString("subject"));
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return subjectQuestion;
    }

    @Override
    public List<String> getIDSittingForExtract() {
        List<String> idSitting = new ArrayList<>();
        ResultSet resultSet = null;
        try {
            resultSet = statement.executeQuery("SELECT s.id FROM sittings s INNER JOIN committee c ON  s.num = c.num AND s.committee = c.id  WHERE s.committee = '64' AND is_archive ='0'");
            while (resultSet.next()) {
                idSitting.add(resultSet.getString("id"));
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return idSitting;
    }

}

package DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import Models.Group;

public class GroupDAO {
    private final String DB_URL = "jdbc:sqlite:database.db";

    public boolean createGroup(Group group) {
        String query = "INSERT INTO groups (group_name) VALUES (?)";
        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement preparedStatement = conn.prepareStatement(query)) {

            preparedStatement.setString(1, group.getGroupName());

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public boolean deleteGroup(int groupId) {
        String query = "DELETE FROM groups WHERE group_id = ?";
        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement preparedStatement = conn.prepareStatement(query)) {

            preparedStatement.setInt(1, groupId);

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public List<Group> getAllGroups() {
        List<Group> groups = new ArrayList<>();
        String query = "SELECT * FROM groups";
        try (Connection conn = DriverManager.getConnection(DB_URL);
             Statement statement = conn.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                int groupId = resultSet.getInt("group_id");
                String groupName = resultSet.getString("group_name");

                Group group = new Group(groupId, groupName);
                groups.add(group);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return groups;
    }
}


package repository.security;

import model.Role;
import model.builder.RoleBuilder;
import model.validation.Notification;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static database.Constants.Tables.ROLE;

public class RoleRepositoryMySQL implements  RoleRepository{

    private final Connection connection;

    public RoleRepositoryMySQL(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Role> findAll() {
        List<Role> roles = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            String query = "Select * from " + ROLE;
            ResultSet resultSet = statement.executeQuery(query);
            
            while(resultSet.next()){
                roles.add(getRoleFromResultSet(resultSet));
            }
            
            
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return roles;
    }

    @Override
    public Notification<Role> findRoleByName(String name) {
        Notification<Role> findRoleByNameNotification = new Notification<>();
        try {
            Statement statement = connection.createStatement();
            String query = "Select * from " + ROLE + " where name='" + name + "'";
            ResultSet resultSet = statement.executeQuery(query);
            if (resultSet.next()){
                findRoleByNameNotification.setResult(getRoleFromResultSet(resultSet));
                return findRoleByNameNotification;
            } else {
                findRoleByNameNotification.addError("No role with that name!");
                return findRoleByNameNotification;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            findRoleByNameNotification.addError("Something is wrong with the Database!");
        }
        return findRoleByNameNotification;
    }

    @Override
    public Notification<Role> findRoleById(long role_id) {
        Notification<Role> findRoleByIDNotification = new Notification<>();
        try {
            Statement statement = connection.createStatement();
            String query = "Select * from " + ROLE + " where id= " + role_id;
            ResultSet resultSet = statement.executeQuery(query);
            if (resultSet.next()) {
                findRoleByIDNotification.setResult(getRoleFromResultSet(resultSet));
                return findRoleByIDNotification;
            } else {
                findRoleByIDNotification.addError("No role with that ID!");
                return findRoleByIDNotification;
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            findRoleByIDNotification.addError("Something is wrong with the Database!");
        }
        return findRoleByIDNotification;
    }

    @Override
    public boolean addRole(String role) {
        try {
            PreparedStatement insertStatement = connection
                    .prepareStatement("INSERT IGNORE INTO " + ROLE + " values (null, ?)");
            insertStatement.setString(1, role);
            insertStatement.executeUpdate();
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }

    }

    @Override
    public boolean deleteRole(String role) {
        try {
            Statement statement = connection.createStatement();
            String query = "DELETE FROM " + ROLE + " WHERE name = '" + role + "'";
            statement.executeUpdate(query);
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
    }

    @Override
    public void removeAll() {
        try {
            Statement statement = connection.createStatement();
            String query = "DELETE FROM " + ROLE + " WHERE id >= 0";
            String alterTable = "ALTER TABLE role AUTO_INCREMENT = 1";
            statement.executeUpdate(query);
            statement.execute(alterTable);

        } catch (SQLException throwables) {
            throwables.printStackTrace();

        }
    }

    private Role getRoleFromResultSet(ResultSet resultSet) throws SQLException {

        return new RoleBuilder()
                .setID(resultSet.getLong("id"))
                .setRole(resultSet.getString("name"))
                .build();
    }

}

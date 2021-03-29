package repository.user;

import model.User;
import model.builder.UserBuilder;
import model.validation.Notification;
import repository.security.RoleRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import static database.Constants.Tables.USER;

public class UserRepositoryMySQL implements UserRepository{

    private final Connection connection;
    private final RoleRepository roleRepository;

    public UserRepositoryMySQL(Connection connection, RoleRepository roleRepository) {
        this.connection = connection;
        this.roleRepository = roleRepository;
    }


    @Override
    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            String query = "Select * from " + USER;
            ResultSet resultSet = statement.executeQuery(query);
            while(resultSet.next())
                users.add(getUserFromResultSet(resultSet));

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return users;
    }

    private User getUserFromResultSet(ResultSet resultSet) throws SQLException {
        return new UserBuilder()
                .setId(resultSet.getLong("id"))
                .setUsername(resultSet.getString("username"))
                .setPassword(resultSet.getString("password"))
                .setRole(roleRepository.findRoleById(resultSet.getLong("role_id")).getResult())
                .build();

    }

    @Override
    public Notification<User> findByUsernameAndPassword(String username, String password) {
        Notification<User> findByUsernameAndPasswordNotification = new Notification<>();
        try {
            Statement statement = connection.createStatement();
            String query =  "Select * from `" + USER + "` where `username`=\'" + username + "\' and `password`=\'" + password + "\'";
            ResultSet resultSet = statement.executeQuery(query);
            if(resultSet.next()) {
                User user = new UserBuilder()
                        .setId(resultSet.getLong("id"))
                        .setUsername(resultSet.getString("username"))
                        .setPassword(resultSet.getString("password"))
                        .setRole(roleRepository.findRoleById(resultSet.getLong("role_id")).getResult())
                        .build();
                findByUsernameAndPasswordNotification.setResult(user);
                return findByUsernameAndPasswordNotification;
            } else {
                findByUsernameAndPasswordNotification.addError("Invalid username or password!");
                return findByUsernameAndPasswordNotification;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            findByUsernameAndPasswordNotification.addError("Something is wrong with the Database!");
        }
        return findByUsernameAndPasswordNotification;
    }

    @Override
    public Notification<User> findByUsername(String username) {
        Notification<User> findByUsernameNotification = new Notification<>();
        try {
            Statement statement = connection.createStatement();
            String query = "Select * from " + USER + " where username='" + username +"'";
            ResultSet resultSet = statement.executeQuery(query);
            if(resultSet.next()) {
                User user = new UserBuilder()
                        .setId(resultSet.getLong("id"))
                        .setUsername(resultSet.getString("username"))
                        .setPassword(resultSet.getString("password"))
                        .setRole(roleRepository.findRoleById(resultSet.getLong("role_id")).getResult())
                        .build();
                findByUsernameNotification.setResult(user);
                return findByUsernameNotification;
            } else {
                findByUsernameNotification.addError("Invalid username!");
                return findByUsernameNotification;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            findByUsernameNotification.addError("Something is wrong with the Database!");
        }
        return findByUsernameNotification;
    }

    @Override
    public boolean save(User user) {
        try {
            PreparedStatement insertStatement = connection
                    .prepareStatement("INSERT IGNORE INTO "+ USER +" values (null, ?, ?, ?)");
            insertStatement.setString(1, user.getUsername());
            insertStatement.setString(2, user.getPassword());
            insertStatement.setLong(3, user.getRole().getId());
            insertStatement.executeUpdate();
            return true;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updatePassword(User user, String newPassword) {
        try {
            Statement statement = connection.createStatement();
            String query = "UPDATE "+ USER + " SET password= '" + newPassword + "' where username= '" + user.getUsername() +"'";
            statement.executeUpdate(query);
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteUser(String username) {
        try {
            Statement statement = connection.createStatement();
            String query = "DELETE FROM "+ USER + " WHERE username='" + username +"'";
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
            String query = "DELETE FROM "+ USER + " WHERE id >= 0";
            String alterTable = "ALTER TABLE user AUTO_INCREMENT = 1";
            statement.executeUpdate(query);
            statement.execute(alterTable);

        } catch (SQLException throwables) {
            throwables.printStackTrace();

        }
    }
}

package repository.activity;

import model.Activity;
import model.User;
import model.builder.ActivityBuilder;
import model.validation.Notification;
import repository.user.UserRepository;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static database.Constants.Tables.ACTIVITY;

public class ActivityRepositoryMySQL implements ActivityRepository{

    private final Connection connection;
    private final UserRepository userRepository;

    public ActivityRepositoryMySQL(Connection connection, UserRepository userRepository) {
        this.connection = connection;
        this.userRepository = userRepository;
    }

    @Override
    public List<Activity> findActivityByEmployee(String username) {
        List<Activity> activityList = new ArrayList<>();
        Notification<List<Activity>> notification = new Notification<>();
        Notification<User> user = userRepository.findByUsername(username);
        if (user.hasErrors())
            notification.addError("There is no user with that username!");
        else {
            try {
                Statement statement = connection.createStatement();
                String query = "SELECT * FROM " + ACTIVITY + " WHERE employeeID= " + user.getResult().getId();

                ResultSet resultSet = statement.executeQuery(query);
                while (resultSet.next())
                    activityList.add(getActivityFromResultSet(resultSet));

            } catch (SQLException throwables) {
                throwables.printStackTrace();
                return activityList;
            }
        }
        return activityList;
    }


    @Override
    public List<Activity> generateActivity(String username, LocalDate start, LocalDate end) {
        List<Activity> activityList = new ArrayList<>();
        Notification<List<Activity>> notification = new Notification<>();
        Notification<User> user = userRepository.findByUsername(username);
        if (user.hasErrors())
            notification.addError("There is no user with that username!");
        else {
            try {
                Statement statement = connection.createStatement();
                String query = "SELECT * FROM " + ACTIVITY + " WHERE employeeID= " + user.getResult().getId() + " AND `date` between '"
                        + Date.valueOf(start) + "' AND '" + Date.valueOf(end) + "'";

                ResultSet resultSet = statement.executeQuery(query);
                while (resultSet.next())
                    activityList.add(getActivityFromResultSet(resultSet));

            } catch (SQLException throwables) {
                throwables.printStackTrace();
                return activityList;
            }
        }
        return activityList;
    }

    @Override
    public boolean addActivity(Activity activity) {
        try {
           PreparedStatement statement = connection.
                   prepareStatement("INSERT IGNORE INTO " + ACTIVITY + " VALUES (null, ?, ?, ?)");
           statement.setLong(1, activity.getEmployeeID());
           statement.setDate(2, (Date) activity.getDate());
           statement.setString(3, activity.getActivity());
           statement.executeUpdate();
           return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
    }

    @Override
    public void removeAll(){
        try {
            Statement statement = connection.createStatement();
            String query = "DELETE FROM " + ACTIVITY + " WHERE id >= 0";
            String alterTable = "ALTER TABLE activity AUTO_INCREMENT = 1";
            statement.executeUpdate(query);
            statement.execute(alterTable);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private Activity getActivityFromResultSet(ResultSet resultSet) throws SQLException {
        return new ActivityBuilder()
                .setID(resultSet.getLong("id"))
                .setEmployeeID(resultSet.getLong("employeeID"))
                .setActivity(resultSet.getString("activity"))
                .setDate(resultSet.getDate("date"))
                .build();
    }
}

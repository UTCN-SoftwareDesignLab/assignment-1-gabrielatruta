package repository.activity;

import model.Activity;

import java.time.LocalDate;
import java.util.List;

public interface ActivityRepository {

    List<Activity> findActivityByEmployee(String username);

    List<Activity> generateActivity(String username, LocalDate start, LocalDate end);

    boolean addActivity(Activity activity);

    void removeAll();
}

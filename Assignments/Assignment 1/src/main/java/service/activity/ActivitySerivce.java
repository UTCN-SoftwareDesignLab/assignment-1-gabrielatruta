package service.activity;

import model.Activity;

import java.time.LocalDate;
import java.util.List;

public interface ActivitySerivce {
    List<Activity> generateActivity(String username, LocalDate start, LocalDate end);

    boolean addActivity(Activity activity);
}

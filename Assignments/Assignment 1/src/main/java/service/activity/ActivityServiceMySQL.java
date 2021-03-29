package service.activity;

import model.Activity;
import repository.activity.ActivityRepository;

import java.time.LocalDate;
import java.util.List;

public class ActivityServiceMySQL implements ActivitySerivce{
    private final ActivityRepository activityRepository;

    public ActivityServiceMySQL(ActivityRepository activityRepository) {
        this.activityRepository = activityRepository;
    }


    @Override
    public List<Activity> generateActivity(String username, LocalDate start, LocalDate end) {
        return activityRepository.generateActivity(username, start, end);
    }

    @Override
    public boolean addActivity(Activity activity) {
        return activityRepository.addActivity(activity);
    }
}

package model.builder;

import model.Activity;

import java.util.Date;

public class ActivityBuilder {
    private final Activity activity;

    public ActivityBuilder() {
        activity = new Activity();
    }

    public ActivityBuilder setID(Long ID){
        activity.setID(ID);
        return this;
    }

    public ActivityBuilder setEmployeeID(Long employeeID){
        activity.setEmployeeID(employeeID);
        return this;
    }

    public ActivityBuilder setDate(Date startDate){
        activity.setDate(startDate);
        return this;
    }

    public ActivityBuilder setActivity(String description){
        activity.setActivity(description);
        return this;
    }

    public Activity build(){
        return activity;
    }
}

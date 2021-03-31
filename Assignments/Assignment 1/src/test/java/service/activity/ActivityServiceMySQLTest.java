package service.activity;

import launcher.ComponentFactory;
import model.User;
import model.builder.ActivityBuilder;
import model.builder.UserBuilder;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import repository.activity.ActivityRepository;
import repository.security.RoleRepository;
import repository.user.UserRepository;

import java.sql.Date;
import java.time.LocalDate;

public class ActivityServiceMySQLTest {
    private static RoleRepository roleRepository;
    private static ActivityRepository activityRepository;
    private static ActivityService activityService;
    private static UserRepository userRepository;

    @BeforeClass
    public static void setupClass(){
        ComponentFactory componentFactory = ComponentFactory.instance(true);
        activityRepository = componentFactory.getActivityRepository();
        roleRepository = componentFactory.getRolesRepository();
        activityService = componentFactory.getActivityService();
        userRepository = componentFactory.getUserRepository();
    }

    @Test
    public void generateActivity() {
        User user = new UserBuilder()
                .setId(-1L)
                .setUsername("test@username.com")
                .setPassword("TestPassword1@")
                .setRole(roleRepository.findRoleByName("employee").getResult())
                .build();

        userRepository.save(user);

        activityRepository.addActivity(new ActivityBuilder()
                .setID(-1L)
                .setEmployeeID(userRepository.findByUsername("test@username.com").getResult().getId())
                .setDate(Date.valueOf(LocalDate.now()))
                .setActivity("Deleted an account from a client")
                .build());

        Assert.assertEquals(1, activityService.generateActivity(user.getUsername(), LocalDate.now(), LocalDate.now()).size());
    }

    @Test
    public void addActivity() {
        User user = new UserBuilder()
                .setId(-1L)
                .setUsername("test@username.com")
                .setPassword("TestPassword1@")
                .setRole(roleRepository.findRoleByName("employee").getResult())
                .build();

        userRepository.save(user);

        Assert.assertTrue(activityRepository.addActivity(new ActivityBuilder()
                .setID(-1L)
                .setEmployeeID(userRepository.findByUsername("test@username.com").getResult().getId())
                .setDate(Date.valueOf(LocalDate.now()))
                .setActivity("Deleted an account from a client")
                .build()));
    }
}
package repository.activity;

import launcher.ComponentFactory;
import model.Activity;
import model.builder.ActivityBuilder;
import model.builder.UserBuilder;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import repository.security.RoleRepository;
import repository.user.UserRepository;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

public class ActivityRepositoryMySQLTest {

    private static UserRepository userRepository;
    private static ActivityRepository activityRepository;
    private  static RoleRepository roleRepository;

    @BeforeClass
    public static void setupClass(){
        ComponentFactory componentFactory = ComponentFactory.instance(true);
        userRepository = componentFactory.getUserRepository();
        activityRepository = componentFactory.getActivityRepository();
        roleRepository = componentFactory.getRolesRepository();
    }

    @Before
    public void setup(){
        activityRepository.removeAll();
        roleRepository.removeAll();

        roleRepository.addRole("employee");

        userRepository.save(new UserBuilder()
                            .setId(-1L)
                            .setUsername("gabriela@yahoo.com")
                            .setPassword("Parola11!")
                            .setRole(roleRepository.findRoleByName("employee").getResult())
                            .build());

        activityRepository.addActivity(new ActivityBuilder()
                                        .setID(-1L)
                                        .setEmployeeID(userRepository.findByUsername("gabriela@yahoo.com").getResult().getId())
                                        .setDate(Date.valueOf(LocalDate.now()))
                                        .setActivity("Logged in")
                                        .build());
    }

    @Test
    public void findActivityByEmployee() {
        List<Activity> activityList= activityRepository.findActivityByEmployee("gabriela@yahoo.com");
        Assert.assertEquals(1, activityList.size());
    }

    @Test
    public void generateActivity() {
        activityRepository.addActivity(new ActivityBuilder()
                .setID(-1L)
                .setEmployeeID(userRepository.findByUsername("gabriela@yahoo.com").getResult().getId())
                .setDate(Date.valueOf(LocalDate.now()))
                .setActivity("View client profile")
                .build());

        activityRepository.addActivity(new ActivityBuilder()
                .setID(-1L)
                .setEmployeeID(userRepository.findByUsername("gabriela@yahoo.com").getResult().getId())
                .setDate(Date.valueOf(LocalDate.now()))
                .setActivity("Added an account to a client")
                .build());

        List<Activity> activityList = activityRepository.generateActivity("gabriela@yahoo.com",
                LocalDate.now(), LocalDate.now());
        Assert.assertEquals(3, activityList.size());
    }

    @Test
    public void addActivity() {
        Assert.assertTrue(activityRepository.addActivity(new ActivityBuilder()
                .setID(-1L)
                .setEmployeeID(userRepository.findByUsername("gabriela@yahoo.com").getResult().getId())
                .setDate(Date.valueOf(LocalDate.now()))
                .setActivity("Deleted an account from a client")
                .build()));
    }

}
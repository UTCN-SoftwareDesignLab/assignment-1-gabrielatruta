package service.user;

import launcher.ComponentFactory;
import model.User;
import model.builder.UserBuilder;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import repository.activity.ActivityRepository;
import repository.security.RoleRepository;
import repository.user.UserRepository;
import service.user.UserService;

public class UserServiceMySQLTest {

    private static UserRepository userRepository;
    private static UserService adminService;
    private static ActivityRepository activityRepository;
    private static RoleRepository roleRepository;

    @BeforeClass
    public static void setupClass(){
        ComponentFactory componentFactory = ComponentFactory.instance(true);
        userRepository = componentFactory.getUserRepository();
        adminService = componentFactory.getUserService();
        roleRepository = componentFactory.getRolesRepository();
        activityRepository = componentFactory.getActivityRepository();

    }

    @Before
    public void setup(){
        userRepository.removeAll();
        activityRepository.removeAll();
        roleRepository.addRole("employee");
    }

    @Test
    public void createEmployee() {
        User user = new UserBuilder()
                .setId(-1L)
                .setUsername("test@username.com")
                .setPassword("TestPassword1@")
                .setRole(roleRepository.findRoleByName("employee").getResult())
                .build();
        Assert.assertFalse(adminService.createEmployee(user).hasErrors());
    }

    @Test
    public void view() {

        User user = new UserBuilder()
                .setId(-1L)
                .setUsername("test@username.com")
                .setPassword("TestPassword1@")
                .setRole(roleRepository.findRoleByName("employee").getResult())
                .build();

        adminService.createEmployee(user);

        Assert.assertEquals(1, adminService.view().size());
    }

    @Test
    public void updatePassword() {

        User user = new UserBuilder()
                .setId(-1L)
                .setUsername("test@username.com")
                .setPassword("TestPassword1@")
                .setRole(roleRepository.findRoleByName("employee").getResult())
                .build();
        adminService.createEmployee(user);
        user.setPassword("agaaab6T!");

        Assert.assertTrue(adminService.updateEmployee(user).getResult());
    }

    @Test
    public void removeUser() {
        User user = new UserBuilder()
                .setId(-1L)
                .setUsername("test@username.com")
                .setPassword("TestPassword1@")
                .setRole(roleRepository.findRoleByName("employee").getResult())
                .build();

        adminService.createEmployee(user);

        Assert.assertTrue(adminService.removeUser("test@username.com"));
    }

}
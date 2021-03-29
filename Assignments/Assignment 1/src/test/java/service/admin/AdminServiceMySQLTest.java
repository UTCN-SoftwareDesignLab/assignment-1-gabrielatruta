package service.admin;

import launcher.ComponentFactory;
import model.Role;
import model.User;
import model.builder.RoleBuilder;
import model.builder.UserBuilder;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import repository.client.ClientRepository;
import repository.user.UserRepository;
import service.employee.EmployeeService;

import static service.user.AuthenticationServiceMySQLTest.TEST_PASSWORD;
import static service.user.AuthenticationServiceMySQLTest.TEST_USERNAME;

public class AdminServiceMySQLTest {


    private static UserRepository userRepository;
    private static AdminService adminService;
    private static Role role;

    @BeforeClass
    public static void setupClass(){
        ComponentFactory componentFactory = ComponentFactory.instance(true);
        userRepository = componentFactory.getUserRepository();
        adminService = componentFactory.getAdminService();

    }

    @Before
    public void setup(){
        userRepository.removeAll();
        role = new RoleBuilder()
                .setID(-1L)
                .setRole("employee")
                .build();
    }

    @Test
    public void createEmployee() {


        User user = new UserBuilder()
                .setId(-1L)
                .setUsername(TEST_USERNAME)
                .setPassword(TEST_PASSWORD)
                .setRole(role)
                .build();
        Assert.assertFalse(adminService.createEmployee(user).hasErrors());
    }

    @Test
    public void view() {

        User user = new UserBuilder()
                .setId(-1L)
                .setUsername(TEST_USERNAME)
                .setPassword(TEST_PASSWORD)
                .setRole(role)
                .build();

        adminService.createEmployee(user);

        Assert.assertEquals(1, adminService.view().size());
    }

    @Test
    public void updatePassword() {

        User user = new UserBuilder()
                .setId(-1L)
                .setUsername(TEST_USERNAME)
                .setPassword(TEST_PASSWORD)
                .setRole(role)
                .build();

        adminService.createEmployee(user);

        Assert.assertTrue(adminService.updatePassword(user, TEST_PASSWORD).getResult());
    }

    @Test
    public void removeUser() {
        User user = new UserBuilder()
                .setId(-1L)
                .setUsername(TEST_USERNAME)
                .setPassword(TEST_PASSWORD)
                .setRole(role)
                .build();

        adminService.createEmployee(user);

        Assert.assertTrue(adminService.removeUser(TEST_USERNAME));
    }
}
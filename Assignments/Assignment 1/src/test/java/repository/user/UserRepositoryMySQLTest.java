package repository.user;

import launcher.ComponentFactory;
import model.Role;
import model.User;
import model.builder.UserBuilder;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import repository.security.RoleRepository;

import java.util.List;

import static database.Constants.Roles.EMPLOYEE;

public class UserRepositoryMySQLTest {

    private static UserRepository userRepository;
    private static RoleRepository roleRepository;

    @BeforeClass
    public static void setupClass(){
        ComponentFactory componentFactory = ComponentFactory.instance(true);
        userRepository = componentFactory.getUserRepository();
        roleRepository = componentFactory.getRolesRepository();
    }

    @Before
    public void cleanUp(){
        userRepository.removeAll();

        userRepository.save(new UserBuilder()
                .setId(-1L)
                .setUsername("gabriela.truta1899@gmail.com")
                .setPassword("gabrielaT3!")
                .setRole(roleRepository.findRoleByName(EMPLOYEE).getResult())
                .build());

        userRepository.save(new UserBuilder()
                .setId(-1L)
                .setUsername("diana.dada@yahoo.com")
                .setPassword("dianaA67*")
                .setRole(roleRepository.findRoleByName(EMPLOYEE).getResult())
                .build());

    }


    @Test
    public void save() {
        roleRepository.addRole(EMPLOYEE);
        Role role = roleRepository.findRoleByName(EMPLOYEE).getResult();

        Assert.assertTrue(userRepository.save(new UserBuilder()
                .setId(-1L)
                .setUsername("ioana.pop@rocket.com")
                .setPassword("davidBowie0*0")
                .setRole(role)
                .build()));
    }

    @Test
    public void findAll() {
        List<User> users = userRepository.findAll();
        Assert.assertEquals(2, users.size());
    }

    @Test
    public void findByUsernameAndPassword() {
        Assert.assertFalse(userRepository.findByUsernameAndPassword("gabriela.truta1899@gmail.com", "gabrielaT3!").hasErrors());
    }

    @Test
    public void findByUsername() {
        Assert.assertFalse(userRepository.findByUsername("diana.dada@yahoo.com").hasErrors());
    }

    @Test
    public void updatePassword() {
        userRepository.updatePassword(userRepository.findByUsername("gabriela.truta1899@gmail.com").getResult(), "GabrielaTruta3!");
        Assert.assertTrue(userRepository.findByUsernameAndPassword("gabriela.truta1899@gmail.com", "gabrielaT3!").hasErrors());
    }

    @Test
    public void deleteUser() {
        userRepository.deleteUser("diana.dada@yahoo.com");
        Assert.assertTrue(userRepository.findByUsername("diana.dada@yahoo.com").hasErrors());
    }

    @Test
    public void removeAll() {
        userRepository.removeAll();
        Assert.assertEquals(0, userRepository.findAll().size());
    }

}
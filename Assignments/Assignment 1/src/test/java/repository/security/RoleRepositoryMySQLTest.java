package repository.security;

import database.DBConnectionFactory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.Connection;



public class RoleRepositoryMySQLTest {

    private static RoleRepository roleRepository;

    @BeforeClass
    public static void setupClass(){
        Connection connection = new DBConnectionFactory().getConnectionWrapper(true).getConnection();
        roleRepository = new RoleRepositoryMySQL(connection);
    }

    @Before
    public void setup(){
        roleRepository.removeAll();
        roleRepository.addRole("administrator");
        roleRepository.addRole("employee");
    }

    @Test
    public void findAll() {
        Assert.assertEquals(2, roleRepository.findAll().size());
    }

    @Test
    public void findRoleByName() {
        Assert.assertFalse(roleRepository.findRoleByName("employee").hasErrors());
    }

    @Test
    public void findRoleById() {
        Assert.assertFalse(roleRepository.findRoleById(1L).hasErrors());
    }

    @Test
    public void addRole() {
        Assert.assertTrue(roleRepository.addRole("test_role"));
    }

    @Test
    public void deleteRole() {
        Assert.assertTrue(roleRepository.deleteRole("test_role"));
    }

}
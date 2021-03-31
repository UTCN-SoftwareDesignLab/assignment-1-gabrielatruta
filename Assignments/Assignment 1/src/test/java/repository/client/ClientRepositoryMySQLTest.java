package repository.client;

import database.DBConnectionFactory;
import model.builder.ClientBuilder;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;


import java.sql.Connection;

public class ClientRepositoryMySQLTest {

    private static ClientRepository clientRepository;


    @BeforeClass
    public static void setupClass(){
        Connection connection = new DBConnectionFactory().getConnectionWrapper(true).getConnection();
        clientRepository = new ClientRepositoryMySQL(connection);
    }

    @Before
    public void setup(){
        clientRepository.removeAll();
        clientRepository.addClient(new ClientBuilder()
                .setID(-1L)
                .setName("Denisa")
                .setSurname("Muresan")
                .setIdentityCardNumber("SX478901")
                .setCNP("2990328314072")
                .setAddress("str. Sfanta Vineri")
                .setMail("denisa.muresan@yahoo.ro")
                .setPhone("0756710921")
                .build());

    }


    @Test
    public void findAll() {

        Assert.assertEquals(1, clientRepository.findAll().size());
    }

    @Test
    public void findById() {
        Assert.assertFalse(clientRepository.findById(1L).hasErrors());
    }

    @Test
    public void findByCNP() {
        Assert.assertFalse(clientRepository.findByCNP("2990328314072").hasErrors());
    }

    @Test
    public void findByFullName() {
        Assert.assertFalse(clientRepository.findByFullName("Denisa", "Muresan").hasErrors());
    }

    @Test
    public void addClient() {

        Assert.assertTrue(clientRepository.addClient(new ClientBuilder()
                .setID(-1L)
                .setName("Anton")
                .setSurname("Pop")
                .setIdentityCardNumber("SX867189")
                .setCNP("1970922314111")
                .setAddress("str. Sperantei")
                .setMail("antold@yahoo.com")
                .setPhone("0731361046")
                .build()));
    }


    @Test
    public void updatePhone() {
        Assert.assertTrue(clientRepository.updateClient(clientRepository.findByCNP("2990328314072").getResult()).getResult());
    }

    @Test
    public void removeAll() {
        clientRepository.removeAll();
        Assert.assertEquals(0, clientRepository.findAll().size());
    }
}
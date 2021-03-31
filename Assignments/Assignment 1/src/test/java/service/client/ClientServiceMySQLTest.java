package service.client;

import launcher.ComponentFactory;
import model.Client;
import model.builder.ClientBuilder;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class ClientServiceMySQLTest {

    public static final String TEST_NAME = "Gabriela";
    public static final String TEST_SURNAME = "Truta";
    public static final String TEST_IDENTITY_CARD_NUMBER2 = "SX681710";
    public static final String TEST_CNP = "2990318314028";
    public static final String TEST_MAIL = "olivia.truta99@yahoo.com";
    public static final String TEST_PHONE = "0789106781";
    private static ClientService clientService;

    @BeforeClass
    public static void setupClass(){
        ComponentFactory componentFactory = ComponentFactory.instance(true);
        clientService = componentFactory.getClientService();
    }

    @Before
    public void setup(){
        clientService.createClient(new ClientBuilder()
                .setID(-1L)
                .setName(TEST_NAME)
                .setSurname(TEST_SURNAME)
                .setCNP(TEST_CNP)
                .setIdentityCardNumber(TEST_IDENTITY_CARD_NUMBER2)
                .setPhone(TEST_PHONE)
                .setMail(TEST_MAIL)
                .setAddress("str. Kiss Karoly")
                .build());
    }

    @Test
    public void createClient() {
        Assert.assertTrue(clientService.createClient(new ClientBuilder()
                .setID(-1L)
                .setName(TEST_NAME)
                .setSurname(TEST_SURNAME)
                .setCNP(TEST_CNP)
                .setIdentityCardNumber(TEST_IDENTITY_CARD_NUMBER2)
                .setPhone(TEST_PHONE)
                .setMail(TEST_MAIL)
                .setAddress("str. Kiss Karoly")
                .build()).getResult());
    }

    @Test
    public void updateClient() {
        Client client = clientService.findByCNP(TEST_CNP).getResult();
        client.setAddress("Str. Maciesului");
        Assert.assertTrue(clientService.updateClient(client).getResult());
    }

    @Test
    public void findByCNP() {
        Assert.assertFalse(clientService.findByCNP(TEST_CNP).hasErrors());
    }

    @Test
    public void findByFullName() {
        Assert.assertFalse(clientService.findByFullName(TEST_NAME, TEST_SURNAME).hasErrors());
    }
}
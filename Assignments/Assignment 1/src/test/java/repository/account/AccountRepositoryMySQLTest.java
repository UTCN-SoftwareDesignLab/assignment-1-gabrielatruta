package repository.account;

import database.DBConnectionFactory;
import model.Account;
import model.builder.AccountBuilder;
import model.builder.ClientBuilder;
import org.junit.*;
import repository.client.ClientRepository;
import repository.client.ClientRepositoryMySQL;

import java.sql.Connection;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.*;

public class AccountRepositoryMySQLTest {

    private static AccountRepository accountRepository;
    private static ClientRepository clientRepository;


    @BeforeClass
    public static void setupClass(){
        Connection connection = new DBConnectionFactory().getConnectionWrapper(true).getConnection();
        clientRepository = new ClientRepositoryMySQL(connection);
        accountRepository = new AccountRepositoryMySQL(connection);
    }

    @Before
    public void cleanUp(){
        accountRepository.removeAll();
        clientRepository.removeAll();

        clientRepository.addClient(new ClientBuilder()
                .setID(-1L)
                .setName("Gabriela")
                .setSurname("Truta")
                .setIdentityCardNumber("SX456189")
                .setCNP("2990318314028")
                .setPhone("0784780820")
                .setMail("gabriela.truta1899@gmail.com")
                .setAddress("str. Kiss Karoly, Zalau, Romania")
                .build());


        Account account1 = new AccountBuilder()
                .setID(-1L)
                .setIdentificationNumber("RO68 RZBR 0000 0600 2053 9307")
                .setType("credit")
                .setAmountMoney(1000L)
                .setCreationDate(Date.valueOf(LocalDate.now()))
                .setClientID(clientRepository.findById(1L).getResult().getId())
                .build();

        Account account2 = new AccountBuilder()
                .setID(-1L)
                .setIdentificationNumber("RO68 RZBR 0000 0600 5693 9707")
                .setType("credit")
                .setAmountMoney(100000L)
                .setCreationDate(Date.valueOf(LocalDate.now()))
                .setClientID(clientRepository.findById(1L).getResult().getId())
                .build();

        accountRepository.save(account1);
        accountRepository.save(account2);

    }

    @Test
    public void findAll() {
        List<Account> accountList = accountRepository.findAll();
        assertEquals(accountList.size(), 2);
    }

    @Test
    public void save() {
        Account account3 = new AccountBuilder()
                .setID(-1L)
                .setIdentificationNumber("RO68 RZBR 0000 0678 2053 9307")
                .setType("debit")
                .setAmountMoney(109010L)
                .setCreationDate(Date.valueOf(LocalDate.now()))
                .setClientID(clientRepository.findById(1L).getResult().getId())
                .build();

        Assert.assertTrue(accountRepository.save(account3));

    }

    @Test
    public void deleteAccountByID() {

        Assert.assertTrue(accountRepository.deleteAccountByID(1L));
    }


    @Test
    public void findAccountById() {
        Assert.assertFalse(accountRepository.findAccountById(2L).hasErrors());
    }

    @Test
    public void findAccountByClient() {
        Assert.assertFalse(accountRepository.findAccountByClient(1L).hasErrors());
    }

    @Test
    public void updateAccount() {
        Account account = new AccountBuilder()
                .setID(-1L)
                .setClientID(clientRepository.findByCNP("2990318314028").getResult().getId())
                .setIdentificationNumber("1781 7181 9181")
                .setAmountMoney(1976191L)
                .setType("debit")
                .setCreationDate(Date.valueOf(LocalDate.now()))
                .build();
        accountRepository.save(account);
        Assert.assertTrue(accountRepository.updateAccount(account).getResult());
    }

    @Test
    public void findAccountByICN() {
        Assert.assertFalse(accountRepository.findAccountByICN("RO68 RZBR 0000 0600 2053 9307").hasErrors());
    }

    @Test
    public void trasnferMoney() {
        Account sender = accountRepository.findAccountByICN("RO68 RZBR 0000 0600 2053 9307").getResult();
        Account receiver = accountRepository.findAccountByICN("RO68 RZBR 0000 0600 5693 9707").getResult();
        Assert.assertTrue(accountRepository.transferMoney(sender, receiver, sender.getAmountOfMoney()).getResult());
    }

    @Test
    public void removeAll() {
        accountRepository.removeAll();
        Assert.assertEquals(0, accountRepository.findAll().size());
    }
}
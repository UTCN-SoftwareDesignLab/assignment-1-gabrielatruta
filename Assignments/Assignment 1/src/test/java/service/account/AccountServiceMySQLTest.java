package service.account;

import launcher.ComponentFactory;
import model.Account;
import model.builder.AccountBuilder;
import model.builder.ClientBuilder;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import repository.client.ClientRepository;

import java.sql.Date;
import java.time.LocalDate;

public class AccountServiceMySQLTest {

    public static final String TEST_TYPE = "debit";
    public static final String TEST_TYPE2 = "credit";

    private static AccountService accountService;
    private static ClientRepository clientRepository;

   @BeforeClass
   public static void setupClass(){
       ComponentFactory componentFactory = ComponentFactory.instance(true);
       accountService = componentFactory.getAccountService();
       clientRepository = componentFactory.getClientRepository();
   }

   @Before
   public void setup(){
       //clientRepository.removeAll();
   }

    @Test
    public void findAccountByClient() {
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

        accountService.createAccount(new AccountBuilder()
                .setID(-1L)
                .setIdentificationNumber("1627 18191 1819")
                .setType(TEST_TYPE)
                .setAmountMoney(171819L)
                .setCreationDate(Date.valueOf(LocalDate.now()))
                .setClientID(clientRepository.findByCNP("2990328314072").getResult().getId())
                .build());

        Assert.assertNotNull(accountService.findAccountByClient(clientRepository.findByCNP("2990328314072").getResult().getId()));
    }


    @Test
    public void transferMoney() {
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

        Account account1 = new AccountBuilder()
                .setID(-1L)
                .setIdentificationNumber("5207 1001 4929")
                .setType(TEST_TYPE)
                .setAmountMoney(91829L)
                .setCreationDate(Date.valueOf(LocalDate.now()))
                .setClientID(clientRepository.findByCNP("2990328314072").getResult().getId())
                .build();

        Account account2 = new AccountBuilder()
                .setID(-1L)
                .setIdentificationNumber("8191 7892 5783")
                .setType(TEST_TYPE)
                .setAmountMoney(10L)
                .setCreationDate(Date.valueOf(LocalDate.now()))
                .setClientID(clientRepository.findByCNP("2990328314072").getResult().getId())
                .build();

        accountService.createAccount(account1);
        accountService.createAccount(account2);

        Assert.assertTrue(accountService.transferMoney(account1, account2, account1.getAmountOfMoney()).getResult());

    }

    @Test
    public void removeAccount() {

        Account account = new AccountBuilder()
                .setID(-1L)
                .setIdentificationNumber("5207 1001 4929")
                .setType(TEST_TYPE)
                .setAmountMoney(91829L)
                .setCreationDate(Date.valueOf(LocalDate.now()))
                .setClientID(clientRepository.findByCNP("2990328314072").getResult().getId())
                .build();
        accountService.createAccount(account);
       Assert.assertTrue(accountService.removeAccount(account.getAccountID()));
    }

    @Test
    public void createAccount() {
       Assert.assertTrue(accountService.createAccount(
               new AccountBuilder()
               .setID(-1L)
               .setIdentificationNumber("5207 1001 4929")
               .setType(TEST_TYPE)
               .setAmountMoney(91829L)
               .setCreationDate(Date.valueOf(LocalDate.now()))
               .setClientID(clientRepository.findByCNP("2990328314072").getResult().getId())
               .build()).getResult());
    }

    @Test
    public void updateAccount() {
       Account account = new AccountBuilder()
               .setID(-1L)
               .setIdentificationNumber("000000")
               .setType(TEST_TYPE)
               .setAmountMoney(91829L)
               .setCreationDate(Date.valueOf(LocalDate.now()))
               .setClientID(clientRepository.findByCNP("2990328314072").getResult().getId())
               .build();
       accountService.createAccount(account);
       account.setType(TEST_TYPE2);
       Assert.assertTrue(accountService.updateAccount(account).getResult());
    }


}
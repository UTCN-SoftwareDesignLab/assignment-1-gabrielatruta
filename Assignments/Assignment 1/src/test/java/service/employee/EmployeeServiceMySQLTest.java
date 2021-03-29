package service.employee;

import launcher.ComponentFactory;
import model.Account;
import model.builder.AccountBuilder;
import model.builder.ClientBuilder;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import repository.account.AccountRepository;
import repository.client.ClientRepository;

import java.sql.Date;
import java.time.LocalDate;


public class EmployeeServiceMySQLTest {

    public static final String TEST_NAME = "Gabriela";
    public static final String TEST_NAME2 = "Olivia";
    public static final String TEST_SURNAME = "Truta";
    public static final String TEST_IDENTITY_CARD_NUMBER = "SX671298";
    public static final String TEST_IDENTITY_CARD_NUMBER2 = "SX681710";
    public static final String TEST_CNP = "2990318314028";
    public static final String TEST_CNP2 = "2990318314127";
    public static final String TEST_MAIL = "gabriela.truta99@yahoo.com";
    public static final String TEST_MAIL2 = "olivia.truta99@yahoo.com";
    public static final String TEST_PHONE = "0767192013";
    public static final String TEST_PHONE2 = "0789106781";
    public static final String TEST_TYPE = "credit";

    private static ClientRepository clientRepository;
    private static AccountRepository accountRepository;
    private static EmployeeService employeeService;

    @BeforeClass
    public static void setupClass(){
        ComponentFactory componentFactory = ComponentFactory.instance(true);
        accountRepository = componentFactory.getAccountRepository();
        clientRepository = componentFactory.getClientRepository();
        employeeService = componentFactory.getEmployeeService();
    }

    @Before
    public void setup(){
        accountRepository.removeAll();
        clientRepository.removeAll();

        employeeService.createClient(new ClientBuilder()
                                        .setID(-1L)
                                        .setName(TEST_NAME)
                                        .setSurname(TEST_SURNAME)
                                        .setCNP(TEST_CNP)
                                        .setIdentityCardNumber(TEST_IDENTITY_CARD_NUMBER)
                                        .setPhone(TEST_PHONE)
                                        .setMail(TEST_MAIL)
                                        .setAddress("str. Kiss Karoly")
                                        .build());

        employeeService.createAccount(new AccountBuilder()
                                        .setID(-1L)
                                        .setType(TEST_TYPE)
                                        .setCreationDate(Date.valueOf(LocalDate.now()))
                                        .setAmountMoney(189018L)
                                        .setIdentificationNumber("1792 8919 9102")
                                        .setClientID(employeeService.findByCNP(TEST_CNP).getResult().getId())
                                        .build());

    }


    @Test
    public void findAccountByClient() {
        Assert.assertTrue(employeeService.findAccountByClient(employeeService.findByCNP(TEST_CNP).getResult().getId()).getResult());
    }

    @Test
    public void viewAccount() {
        Assert.assertEquals(1, employeeService.viewAccount().size());
    }

    @Test
    public void removeAccount() {
        Assert.assertTrue(employeeService.removeAccount(accountRepository.findAccountByClient(clientRepository.findByCNP(TEST_CNP).getResult().getId()).getResult().getAccountID()));
    }

    @Test
    public void createAccount() {
        Account account = new AccountBuilder()
                .setID(-1L)
                .setType(TEST_TYPE)
                .setCreationDate(Date.valueOf(LocalDate.now()))
                .setAmountMoney(0L)
                .setIdentificationNumber("1782 9017 1891")
                .setClientID(employeeService.findByCNP(TEST_CNP).getResult().getId())
                .build();
        Assert.assertTrue(employeeService.createAccount(account).getResult());
    }

    @Test
    public void updateType() {
        Assert.assertTrue(employeeService.updateType(employeeService.findByFullName(TEST_NAME, TEST_SURNAME).getResult().getId(), "debit").getResult());
    }

    @Test
    public void updateAmountOfMoney() {
        Assert.assertTrue(employeeService.updateAmountOfMoney(employeeService.findByCNP(TEST_CNP).getResult().getId(), 19728L));
    }

    @Test
    public void createClient() {
        Assert.assertTrue(employeeService.createClient(new ClientBuilder()
                .setID(-1L)
                .setName(TEST_NAME2)
                .setSurname(TEST_SURNAME)
                .setCNP(TEST_CNP2)
                .setIdentityCardNumber(TEST_IDENTITY_CARD_NUMBER2)
                .setPhone(TEST_PHONE2)
                .setMail(TEST_MAIL2)
                .setAddress("str. Kiss Karoly")
                .build()).getResult());
    }

    @Test
    public void view() {
        Assert.assertEquals(1, employeeService.view().size());
    }

    @Test
    public void updateClientSurname() {
        Assert.assertTrue(employeeService.updateClientSurname(employeeService.findByCNP(TEST_CNP).getResult(), "Holhos").getResult());
    }

    @Test
    public void updateClientICN() {
        Assert.assertTrue(employeeService.updateClientICN(employeeService.findByCNP(TEST_CNP).getResult(), TEST_CNP2).getResult());
    }

    @Test
    public void updateAddress() {
        Assert.assertTrue(employeeService.updateAddress(employeeService.findByCNP(TEST_CNP).getResult(), "str. Florilor").getResult());
    }

    @Test
    public void updateMail() {
        Assert.assertTrue(employeeService.updateMail(employeeService.findByCNP(TEST_CNP).getResult(), TEST_MAIL2).getResult());
    }

    @Test
    public void updatePhone() {
        Assert.assertTrue(employeeService.updatePhone(employeeService.findByCNP(TEST_CNP).getResult(), TEST_PHONE2).getResult());
    }

    @Test
    public void findByCNP() {
        Assert.assertFalse(employeeService.findByCNP(TEST_CNP).hasErrors());
    }

    @Test
    public void findByFullName() {
        Assert.assertFalse(employeeService.findByFullName(TEST_NAME, TEST_SURNAME).hasErrors());
    }
}
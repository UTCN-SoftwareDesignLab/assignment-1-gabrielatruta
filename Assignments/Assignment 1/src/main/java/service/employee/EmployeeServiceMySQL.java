package service.employee;

import model.Account;
import model.Client;
import model.validation.AccountValidator;
import model.validation.ClientValidator;
import model.validation.Notification;
import repository.account.AccountRepository;
import repository.client.ClientRepository;

import java.util.List;

public class EmployeeServiceMySQL implements EmployeeService {

    private final AccountRepository accountRepository;
    private final ClientRepository clientRepository;

    public EmployeeServiceMySQL(AccountRepository accountRepository, ClientRepository clientRepository) {
        this.accountRepository = accountRepository;
        this.clientRepository = clientRepository;
    }

    @Override
    public Notification<Boolean> findAccountByClient(Long clientID) {
        Notification<Boolean> notification = new Notification<>();
        if (!accountRepository.findAccountByClient(clientID).hasErrors())
            notification.setResult(Boolean.TRUE);
        else
            notification.setResult(Boolean.FALSE);
        return notification;
    }

    @Override
    public List<Account> viewAccount() {
        return accountRepository.findAll();
    }

    @Override
    public boolean removeAccount(Long id) {
        return accountRepository.deleteAccountByID(id);

    }

    @Override
    public Notification<Boolean> createAccount(Account account) {

        AccountValidator accountValidator = new AccountValidator(account);
        boolean isValid = accountValidator.validate();
        Notification<Boolean> accountNotification = new Notification<>();

        if(!isValid){
            accountValidator.getErrors().forEach(accountNotification::addError);
            accountNotification.setResult(Boolean.FALSE);
        } else
            accountNotification.setResult(accountRepository.save(account));
        return accountNotification;
    }

    @Override
    public Notification<Boolean> updateType(Long id, String type) {
        Account account = accountRepository.findAccountById(id).getResult();
        account.setType(type);
        AccountValidator accountValidator = new AccountValidator(account);
        boolean isValid = accountValidator.validate();
        Notification<Boolean> accountNotification = new Notification<>();

        if(!isValid){
            accountValidator.getErrors().forEach(accountNotification::addError);
            accountNotification.setResult(Boolean.FALSE);
        } else
            accountNotification.setResult(accountRepository.updateType(id, type));
        return accountNotification;
    }

    @Override
    public boolean updateAmountOfMoney(Long id, Long money) {
        return accountRepository.updateAmountOfMoney(id, money);
    }


    @Override
    public Notification<Boolean> createClient(Client client) {

        ClientValidator clientValidator = new ClientValidator(client);
        boolean isClientValid = clientValidator.validate();
        Notification<Boolean> clientNotification = new Notification<>();

        if(!isClientValid){
            clientValidator.getErrors().forEach(clientNotification::addError);
            clientNotification.setResult(Boolean.FALSE);
        } else
            clientNotification.setResult(clientRepository.addClient(client));

        return clientNotification;
    }

    @Override
    public List<Client> view() {
        System.out.println(clientRepository.findAll().toString());
        return clientRepository.findAll();
    }

    @Override
    public Notification<Boolean> updateClientSurname(Client client, String surname) {

        client.setSurname(surname);

        ClientValidator clientValidator = new ClientValidator(client);
        boolean isValid = clientValidator.validate();
        Notification<Boolean> clientNotification = new Notification<>();

        if(!isValid){
            clientValidator.getErrors().forEach(clientNotification::addError);
            clientNotification.setResult(Boolean.FALSE);
        } else
            clientNotification.setResult(clientRepository.updateClientSurname(client, surname));
        return clientNotification;
    }

    @Override
    public Notification<Boolean> updateClientICN(Client client, String ICN) {
        client.setCNP(ICN);

        ClientValidator clientValidator = new ClientValidator(client);
        boolean isValid = clientValidator.validate();
        Notification<Boolean> clientNotification = new Notification<>();

        if(!isValid){
            clientValidator.getErrors().forEach(clientNotification::addError);
            clientNotification.setResult(Boolean.FALSE);
        } else
            clientNotification.setResult(clientRepository.updateClientICN(client, ICN));
        return clientNotification;
    }

    @Override
    public Notification<Boolean> updateAddress(Client client, String address) {
        Notification<Boolean> updateNotification = new Notification<>();
        if (clientRepository.updateAddress(client, address))
            updateNotification.setResult(Boolean.TRUE);
        else
            updateNotification.setResult(Boolean.FALSE);
        return  updateNotification;
    }

    @Override
    public Notification<Boolean> updateMail(Client client, String mail) {
        client.setMail(mail);

        ClientValidator clientValidator = new ClientValidator(client);
        boolean isValid = clientValidator.validate();
        Notification<Boolean> clientNotification = new Notification<>();

        if(!isValid){
            clientValidator.getErrors().forEach(clientNotification::addError);
            clientNotification.setResult(Boolean.FALSE);
        } else
            clientNotification.setResult(clientRepository.updateMail(client, mail));
        return clientNotification;
    }

    @Override
    public Notification<Boolean> updatePhone(Client client, String phone) {
        client.setPhoneNumber(phone);

        ClientValidator clientValidator = new ClientValidator(client);
        boolean isValid = clientValidator.validate();
        Notification<Boolean> clientNotification = new Notification<>();

        if(!isValid){
            clientValidator.getErrors().forEach(clientNotification::addError);
            clientNotification.setResult(Boolean.FALSE);
        } else
            clientNotification.setResult(clientRepository.updatePhone(client, phone));
        return clientNotification;
    }

    @Override
    public Notification<Client> findByCNP(String CNP) {
        return clientRepository.findByCNP(CNP);
    }

    @Override
    public Notification<Client> findByFullName(String name, String surname) {
        return clientRepository.findByFullName(name, surname);
    }
}

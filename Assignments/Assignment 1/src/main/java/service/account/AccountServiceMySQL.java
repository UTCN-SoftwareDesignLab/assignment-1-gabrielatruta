package service.account;

import model.Account;
import model.Client;
import model.builder.AccountBuilder;
import model.builder.ClientBuilder;
import model.dto.AccountDTO;
import model.validation.AccountValidator;
import model.validation.Notification;
import repository.account.AccountRepository;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

public class AccountServiceMySQL implements AccountService {

    private final AccountRepository accountRepository;

    public AccountServiceMySQL(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public Notification<Account> findAccountByClient(Long clientID) {
        return accountRepository.findAccountByClient(clientID);
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
    public Notification<Boolean> createAccount(AccountDTO accountDTO, Long clientID) {
        Account account = new AccountBuilder()
                .setIdentificationNumber(accountDTO.getIdentificationNumber())
                .setType(accountDTO.getType())
                .setAmountMoney(accountDTO.getAmountOfMoney())
                .setCreationDate(Date.valueOf(LocalDate.now()))
                .setClientID(clientID)
                .build();

        Notification<Boolean> notification = new Notification<>();
        notification.setResult(accountRepository.save(account));
        return notification;
    }

    @Override
    public Notification<Boolean> updateAccount(AccountDTO accountDTO, Long clientID) {
        Account account = new AccountBuilder()
                .setIdentificationNumber(accountDTO.getIdentificationNumber())
                .setType(accountDTO.getType())
                .setAmountMoney(accountDTO.getAmountOfMoney())
                .setCreationDate(Date.valueOf(LocalDate.now()))
                .setClientID(clientID)
                .build();

        return accountRepository.updateAccount(account);
    }

    @Override
    public Notification<Boolean> updateAccount(Account account) {

        AccountValidator accountValidator = new AccountValidator(account);
        boolean isValid = accountValidator.validate();
        Notification<Boolean> accountNotification = new Notification<>();

        if(!isValid){
            accountValidator.getErrors().forEach(accountNotification::addError);
            accountNotification.setResult(Boolean.FALSE);
        } else
            accountNotification.setResult(accountRepository.updateAccount(account).getResult());
        return accountNotification;
    }

    @Override
    public Notification<Boolean> transferMoney(Account sender, Account receiver, Long amountOfMoney) {

        return accountRepository.transferMoney(sender, receiver, amountOfMoney);
    }
}

package service.account;

import model.Account;
import model.dto.AccountDTO;
import model.validation.Notification;

import java.util.List;

public interface AccountService {

    Notification<Account> findAccountByClient(Long clientID);

    List<Account> viewAccount();

    boolean removeAccount(Long id);

    Notification<Boolean> createAccount(Account account);

    Notification<Boolean> createAccount(AccountDTO accountDTO, Long clientID);

    Notification<Boolean> updateAccount(AccountDTO accountDTO, Long clientID);

    Notification<Boolean> updateAccount(Account account);

    Notification<Boolean> transferMoney (Account sender, Account receiver, Long amountOfMoney);

}

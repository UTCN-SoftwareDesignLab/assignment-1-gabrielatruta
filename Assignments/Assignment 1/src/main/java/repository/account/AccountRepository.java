package repository.account;

import model.Account;
import model.validation.Notification;

import java.util.List;

public interface AccountRepository {

    Notification<Account> findAccountById(Long accountID);

    Notification<Account> findAccountByClient(Long clientID);

    Notification<Account> findAccountByICN(String ICN);

    List<Account> findAll();

    boolean deleteAccountByID(Long id);

    boolean save(Account account);

   Notification<Boolean> updateAccount(Account account);

   Notification<Boolean> transferMoney(Account sender, Account receiver, Long amountOfMoney);

    void removeAll();

}

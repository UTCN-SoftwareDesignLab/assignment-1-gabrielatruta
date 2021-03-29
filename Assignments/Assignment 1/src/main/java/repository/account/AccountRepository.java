package repository.account;

import model.Account;
import model.validation.Notification;

import java.util.List;

public interface AccountRepository {

    Notification<Account> findAccountById(Long accoundID);

    Notification<Account> findAccountByClient(Long clientID);

    List<Account> findAll();

    boolean deleteAccountByID(Long id);

    boolean save(Account account);

   boolean updateType(Long id, String type);

   boolean updateAmountOfMoney(Long id, Long money);

    void removeAll();

}

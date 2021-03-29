package service.employee;

import model.Account;
import model.Client;
import model.validation.Notification;

import java.util.List;

public interface EmployeeService {

    Notification<Boolean> findAccountByClient(Long clientID);

    List<Account> viewAccount();

    boolean removeAccount(Long id);

    Notification<Boolean> createAccount(Account account);

    Notification<Boolean> updateType(Long id, String type);

    boolean updateAmountOfMoney(Long id, Long money);

    Notification<Boolean> createClient(Client client);

    List<Client> view();

    Notification<Boolean> updateClientSurname (Client client, String surname);

    Notification<Boolean> updateClientICN(Client client, String ICN);

    Notification<Boolean> updateAddress(Client client, String address);

    Notification<Boolean> updateMail(Client client, String mail);

    Notification<Boolean> updatePhone(Client client, String phone);

    Notification<Client> findByCNP(String CNP);

    Notification<Client> findByFullName(String name, String surname);

    boolean transferMoney (Account sender, Account receiver, Long amountOfMoney);

}

package repository.client;

import model.Client;
import model.validation.Notification;

import java.util.List;

public interface ClientRepository {

    List<Client> findAll();

    Notification<Client> findById(Long id);

    Notification<Client>  findByCNP(String CNP);

    Notification<Client>  findByFullName(String name, String surname);

    boolean addClient(Client client);

    boolean updateClientSurname(Client client, String surname);

    boolean updateClientICN(Client client, String ICN);

    boolean updateAddress(Client client, String address);

    boolean updateMail(Client client, String mail);

    boolean updatePhone(Client client, String phone);

    void removeAll();
}

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

    Notification<Boolean> updateClient(Client client);

    void removeAll();
}

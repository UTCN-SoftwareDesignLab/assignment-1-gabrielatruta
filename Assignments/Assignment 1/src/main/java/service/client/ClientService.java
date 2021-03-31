package service.client;

import model.Client;
import model.dto.ClientDTO;
import model.validation.Notification;

import java.util.List;

public interface ClientService {
    Notification<Boolean> createClient(Client client);

    Notification<Boolean> createClient(ClientDTO clientDTO);

    List<Client> view();

    Notification<Boolean> updateClient(Client client);

    Notification<Boolean> updateClient(ClientDTO clientDTO);

    Notification<Client> findByCNP(String CNP);

    Notification<Client> findByFullName(String name, String surname);

}

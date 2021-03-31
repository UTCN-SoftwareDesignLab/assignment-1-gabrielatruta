package service.client;

import model.Client;
import model.builder.ClientBuilder;
import model.dto.ClientDTO;
import model.validation.ClientValidator;
import model.validation.Notification;
import repository.client.ClientRepository;

import java.util.List;

public class ClientServiceMySQL implements ClientService {

    private final ClientRepository clientRepository;

    public ClientServiceMySQL(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
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
    public Notification<Boolean> createClient(ClientDTO clientDTO) {
        Client client = new ClientBuilder()
                .setID(clientDTO.getId())
                .setName(clientDTO.getName())
                .setSurname(clientDTO.getSurname())
                .setIdentityCardNumber(clientDTO.getIdentityCardNumber())
                .setCNP(clientDTO.getCNP())
                .setAddress(clientDTO.getAddress())
                .setMail(clientDTO.getMail())
                .setPhone(clientDTO.getPhoneNumber())
                .build();
        Notification<Boolean> notification = new Notification<>();
        notification.setResult(clientRepository.addClient(client));
        return notification;
    }

    @Override
    public List<Client> view() {
        System.out.println(clientRepository.findAll().toString());
        return clientRepository.findAll();
    }

    @Override
    public Notification<Boolean> updateClient(Client client) {

        ClientValidator clientValidator = new ClientValidator(client);
        boolean isValid = clientValidator.validate();
        Notification<Boolean> clientNotification = new Notification<>();

        if(!isValid){
            clientValidator.getErrors().forEach(clientNotification::addError);
            clientNotification.setResult(Boolean.FALSE);
        } else
            clientNotification.setResult(clientRepository.updateClient(client).getResult());
        return clientNotification;
    }

    @Override
    public Notification<Boolean> updateClient(ClientDTO clientDTO) {
        Client client = new ClientBuilder()
                .setID(clientDTO.getId())
                .setName(clientDTO.getName())
                .setSurname(clientDTO.getSurname())
                .setIdentityCardNumber(clientDTO.getIdentityCardNumber())
                .setCNP(clientDTO.getCNP())
                .setAddress(clientDTO.getAddress())
                .setMail(clientDTO.getMail())
                .setPhone(clientDTO.getPhoneNumber())
                .build();
        ClientValidator clientValidator = new ClientValidator(client);
        Notification<Boolean> notification = new Notification<>();
        if(clientValidator.validate()) {
            notification = clientRepository.updateClient(client);
            return notification;
        }
        else {
            notification.addError(clientValidator.getErrors().get(0));
        }
        return notification;
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

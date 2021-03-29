package model.builder;

import model.Account;
import model.Client;

public class ClientBuilder {
    private final Client client;

    public ClientBuilder() {
        client = new Client();
    }

    public ClientBuilder setID(Long ID){
        client.setId(ID);
        return this;
    }

    public ClientBuilder setName(String name){
        client.setName(name);
        return this;
    }

    public ClientBuilder setSurname(String surname){
        client.setSurname(surname);
        return this;
    }

    public ClientBuilder setIdentityCardNumber(String identityCardNumber){
        client.setIdentityCardNumber(identityCardNumber);
        return this;
    }

    public ClientBuilder setCNP(String CNP){
        client.setCNP(CNP);
        return this;
    }

    public ClientBuilder setAddress(String address){
        client.setAddress(address);
        return this;
    }

    public ClientBuilder setMail(String mail){
        client.setMail(mail);
        return this;
    }

    public ClientBuilder setPhone(String phone){
        client.setPhoneNumber(phone);
        return this;
    }


    public Client build(){
        return client;
    }
}

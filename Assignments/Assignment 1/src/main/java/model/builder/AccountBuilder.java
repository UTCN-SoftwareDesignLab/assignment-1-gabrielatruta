package model.builder;

import model.Account;

import java.util.Date;

public class AccountBuilder {

    private final Account account;

    public AccountBuilder() {
        account = new Account();
    }

    public AccountBuilder setID (Long id){
        account.setAccountID(id);
        return this;
    }

    public AccountBuilder setIdentificationNumber (String identificationNumber){
        account.setIdentificationNumber(identificationNumber);
        return this;
    }

    public AccountBuilder setType (String type){
        account.setType(type);
        return this;
    }

    public AccountBuilder setAmountMoney (Long amountOfMoney){
        account.setAmountOfMoney(amountOfMoney);
        return this;
    }

    public AccountBuilder setCreationDate (Date creationDate){
        account.setCreationDate(creationDate);
        return this;
    }

    public AccountBuilder setClientID (Long clientID){
        account.setClient_id(clientID);
        return this;
    }

    public Account build(){
        return  account;
    }
}

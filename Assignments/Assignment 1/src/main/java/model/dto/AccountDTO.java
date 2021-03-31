package model.dto;

public class AccountDTO {
    private Long accountID;
    private String identificationNumber;
    private String type;
    private Long amountOfMoney;

    public AccountDTO(){

    }

    public AccountDTO(Long accountID, String identificationNumber, String type, Long amountOfMoney) {
        this.accountID = accountID;
        this.identificationNumber = identificationNumber;
        this.type = type;
        this.amountOfMoney = amountOfMoney;
    }

    public AccountDTO(String identificationNumber, String type, Long amountOfMoney) {
        this.identificationNumber = identificationNumber;
        this.type = type;
        this.amountOfMoney = amountOfMoney;
    }

    public AccountDTO(Long accountID, String identificationNumber, Long amountOfMoney) {
        this.accountID = accountID;
        this.identificationNumber = identificationNumber;
        this.amountOfMoney = amountOfMoney;
    }

    public AccountDTO(String identificationNumber, Long amountOfMoney) {
        this.identificationNumber = identificationNumber;
        this.amountOfMoney = amountOfMoney;
    }

    public AccountDTO(Long amountOfMoney) {
        this.amountOfMoney = amountOfMoney;
    }

    public Long getAccountID() {
        return accountID;
    }

    public void setAccountID(Long accountID) {
        this.accountID = accountID;
    }

    public String getIdentificationNumber() {
        return identificationNumber;
    }

    public void setIdentificationNumber(String identificationNumber) {
        this.identificationNumber = identificationNumber;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getAmountOfMoney() {
        return amountOfMoney;
    }

    public void setAmountOfMoney(Long amountOfMoney) {
        this.amountOfMoney = amountOfMoney;
    }



}

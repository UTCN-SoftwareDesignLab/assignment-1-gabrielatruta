package model.dto;

public class ClientDTO {
    private Long id;
    private String name;
    private String surname;
    private String identityCardNumber;
    private String CNP;
    private String address;
    private String mail;
    private String phoneNumber;

    public ClientDTO(){

    }

    public ClientDTO(Long id, String name, String surname, String identityCardNumber, String CNP, String address, String mail, String phoneNumber) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.identityCardNumber = identityCardNumber;
        this.CNP = CNP;
        this.address = address;
        this.mail = mail;
        this.phoneNumber = phoneNumber;
    }

    public ClientDTO(String name, String surname, String identityCardNumber, String CNP, String address, String mail, String phoneNumber) {
        this.name = name;
        this.surname = surname;
        this.identityCardNumber = identityCardNumber;
        this.CNP = CNP;
        this.address = address;
        this.mail = mail;
        this.phoneNumber = phoneNumber;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getIdentityCardNumber() {
        return identityCardNumber;
    }

    public void setIdentityCardNumber(String identityCardNumber) {
        this.identityCardNumber = identityCardNumber;
    }

    public String getCNP() {
        return CNP;
    }

    public void setCNP(String CNP) {
        this.CNP = CNP;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}

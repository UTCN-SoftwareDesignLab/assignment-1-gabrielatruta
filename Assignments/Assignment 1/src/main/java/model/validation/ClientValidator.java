package model.validation;

import model.Client;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class ClientValidator {


    private static final String NAME_VALIDATOR = "^[a-zA-Z]+$";
    private static final String SURNAME_VALIDATOR = "^[a-zA-Z]+$";
    private static final String IDENTITY_CARD_NUMBER_VALIDATOR = "^[a-zA-Z]{2}+[0-9]{6}+$";
    private static final String CNP_VALIDATOR = "^[0-9]{13}+$";
    private static final String MAIL_VALIDATOR = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
    private static final String PHONE_VALIDATOR = "^[0-9]{10}+$";

    public List<String> getErrors() {
        return errors;
    }

    private final List<String> errors;

    private final Client client;

    public ClientValidator(Client client) {
      errors = new ArrayList<>();
        this.client = client;
    }

    public boolean validate() {
        validateName(client.getName());
        validateSurname(client.getSurname());
        validateIdentityCardNumber(client.getIdentityCardNumber());
        validateCNP(client.getCNP());
        validateMail(client.getMail());
        validatePhone(client.getPhoneNumber());
        return errors.isEmpty();
    }

    private void validateName(String name) {
        if (!Pattern.compile(NAME_VALIDATOR).matcher(name).matches()) {
            errors.add("Invalid name! The name can contain only letters");
        }
    }

    private void validateSurname(String surname) {
        if (!Pattern.compile(SURNAME_VALIDATOR).matcher(surname).matches()) {
            errors.add("Invalid surname! The surname can contain only letters");
        }
    }

    private void validateIdentityCardNumber(String identityCardNumber) {
        if (!Pattern.compile(IDENTITY_CARD_NUMBER_VALIDATOR).matcher(identityCardNumber).matches()) {
            errors.add("Invalid identity card number! It should start with 2 letters and be preceded by 4 numbers!");
        }
    }

    private void validateCNP(String cnp) {
        if (!Pattern.compile(CNP_VALIDATOR).matcher(cnp).matches()) {
            errors.add("Invalid cnp! It should contain 13 numbers!");
        }
    }

    private void validateMail(String mail) {
        if (!Pattern.compile(MAIL_VALIDATOR).matcher(mail).matches()) {
            errors.add("Invalid mail!");
        }
    }

    private void validatePhone(String phoneNumber) {
        if (!Pattern.compile(PHONE_VALIDATOR).matcher(phoneNumber).matches()) {
            errors.add("Invalid phone number! It should contain 10 numbers!");
        }
    }

}

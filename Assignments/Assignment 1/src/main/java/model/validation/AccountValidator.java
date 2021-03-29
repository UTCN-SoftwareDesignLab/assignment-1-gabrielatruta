package model.validation;

import model.Account;

import java.util.ArrayList;
import java.util.List;

public class AccountValidator {

    private static final String TYPE_VALIDATOR_DEBIT = "debit";
    private static final String TYPE_VALIDATOR_CREDIT = "credit";

    public List<String> getErrors() {
        return errors;
    }

    private final List<String> errors;
    private final Account account;

    public AccountValidator(Account account) {
        errors = new ArrayList<>();
        this.account = account;
    }

    public boolean validate() {
        validateType();
        return errors.isEmpty();
    }

    private void validateType() {
        if (!account.getType().equals(TYPE_VALIDATOR_CREDIT) && !account.getType().equals(TYPE_VALIDATOR_DEBIT)) {
            errors.add("The only available types are debit and credit!");
        }
    }

}

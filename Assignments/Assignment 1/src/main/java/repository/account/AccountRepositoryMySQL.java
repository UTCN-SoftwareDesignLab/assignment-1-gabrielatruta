package repository.account;

import model.Account;
import model.builder.AccountBuilder;
import model.validation.Notification;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static database.Constants.Tables.ACCOUNT;

public class AccountRepositoryMySQL implements AccountRepository{

    private final Connection connection;

    public AccountRepositoryMySQL(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Notification<Account> findAccountById(Long accountID) {
        Notification<Account> findAccountByIdNotification = new Notification<>();
        try {
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM " + ACCOUNT + " WHERE id=" + accountID;
            ResultSet resultSet = statement.executeQuery(query);
            if(resultSet.next()){
                findAccountByIdNotification.setResult(getAccountFromResultSet(resultSet));
                return findAccountByIdNotification;
            } else {
                findAccountByIdNotification.addError("No account with such ID!");
                return findAccountByIdNotification;
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            findAccountByIdNotification.addError("Something is wrong with the Database!");
        }
        return findAccountByIdNotification;
    }

    @Override
    public Notification<Account> findAccountByClient(Long clientID) {
        Notification<Account> findAccountByClientNotification = new Notification<>();
        try {
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM " + ACCOUNT + " WHERE client_id=" + clientID;
            ResultSet resultSet = statement.executeQuery(query);
            if(resultSet.next()){
                findAccountByClientNotification.setResult(getAccountFromResultSet(resultSet));
                return findAccountByClientNotification;
            } else {
                findAccountByClientNotification.addError("This client has no accounts!");
                return findAccountByClientNotification;
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            findAccountByClientNotification.addError("Something is wrong with the Database!");
        }
        return findAccountByClientNotification;
    }

    @Override
    public Notification<Account> findAccountByICN(String ICN) {
        Notification<Account> notification = new Notification<>();
        try {
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM " + ACCOUNT + " WHERE identificationNumber= '" + ICN + "'";
            ResultSet resultSet = statement.executeQuery(query);
            if(resultSet.next()){
                notification.setResult(getAccountFromResultSet(resultSet));
                return notification;
            } else {
                notification.addError("No account with such IBAN!");
                return notification;
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            notification.addError("Something is wrong with the Database!");
        }
        return notification;
    }

    @Override
    public List<Account> findAll() {
        List<Account> accounts = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM " + ACCOUNT;
            ResultSet resultSet = statement.executeQuery(query);
            while(resultSet.next())
                accounts.add(getAccountFromResultSet(resultSet));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return accounts;
    }

    private Account getAccountFromResultSet(ResultSet resultSet) throws SQLException {
        return new AccountBuilder()
                .setID(resultSet.getLong("id"))
                .setIdentificationNumber(resultSet.getString("identificationNumber"))
                .setType(resultSet.getString("type"))
                .setAmountMoney(resultSet.getLong("amountOfMoney"))
                .setCreationDate(resultSet.getDate("creationDate"))
                .setClientID(resultSet.getLong("client_id"))
                .build();
    }

    @Override
    public boolean deleteAccountByID(Long id) {
        try {
            Statement statement = connection.createStatement();
            String query = "DELETE FROM " + ACCOUNT + " WHERE id=" + id;
            statement.executeUpdate(query);
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean save(Account account) {
        try {
            PreparedStatement insertStatement = connection
                    .prepareStatement("INSERT INTO "+ ACCOUNT +" values (null, ?, ?, ?, ?, ?)");
            insertStatement.setString(1, account.getIdentificationNumber());
            insertStatement.setString(2, account.getType());
            insertStatement.setLong(3, account.getAmountOfMoney());
            insertStatement.setDate(4, (Date) account.getCreationDate());
            insertStatement.setLong(5, account.getClient_id());
            insertStatement.executeUpdate();
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
    }

    @Override
    public Notification<Boolean> updateAccount(Account account) {
        Notification<Boolean> notification = new Notification<>();
        try {
            PreparedStatement insertStatement = connection
                    .prepareStatement("UPDATE `" + ACCOUNT + "` SET  identificationNumber = ?, type= ?, amountOfMoney = ?, creationDate = ?, client_id = ? WHERE id = ?");
            insertStatement.setString(1, account.getIdentificationNumber());
            insertStatement.setString(2, account.getType());
            insertStatement.setLong(3, account.getAmountOfMoney());
            insertStatement.setDate(4, (Date) account.getCreationDate());
            insertStatement.setLong(5, account.getClient_id());
            insertStatement.setLong(6, account.getAccountID());
            insertStatement.executeUpdate();
            notification.setResult(true);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            notification.addError("There is an error in the database!");
            notification.setResult(false);
        }
        return notification;
    }

    @Override
    public Notification<Boolean> transferMoney (Account sender, Account receiver, Long amountOfMoney) {

        Notification<Boolean> notification = new Notification<>();
        try {
            Statement statement = connection.createStatement();

            if (amountOfMoney > sender.getAmountOfMoney()) {
                notification.addError("There are not enough money in the account!");
                notification.setResult(false);
            } else {
                long afterSending = sender.getAmountOfMoney() - amountOfMoney;
                long afterReceiving = receiver.getAmountOfMoney() + amountOfMoney;
                String senderQuery =  "UPDATE `" + ACCOUNT + "` SET amountOfMoney = " + afterSending +" WHERE identificationNumber = '" + sender.getIdentificationNumber() + "'";
                String receiverQuery =  "UPDATE `" + ACCOUNT + "` SET amountOfMoney = " + afterReceiving +" WHERE identificationNumber = '" + receiver.getIdentificationNumber() + "'";
                statement.executeUpdate(senderQuery);
                statement.executeUpdate(receiverQuery);
                notification.setResult(true);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            notification.addError("There is an error in the database!");
            notification.setResult(false);
        }
        return notification;
    }

    @Override
    public void removeAll() {
        try {
            Statement statement = connection.createStatement();
            String query = "DELETE FROM "+ ACCOUNT + " WHERE id >= 0";
            String alterTable = "ALTER TABLE account AUTO_INCREMENT = 1";
            statement.executeUpdate(query);
            statement.execute(alterTable);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}

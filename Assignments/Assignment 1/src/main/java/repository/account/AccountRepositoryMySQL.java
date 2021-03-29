package repository.account;

import model.Account;
import model.builder.AccountBuilder;
import model.validation.Notification;
import repository.client.ClientRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static database.Constants.Tables.ACCOUNT;

public class AccountRepositoryMySQL implements AccountRepository{

    private final Connection connection;
    //private final ClientRepository clientRepository;

    public AccountRepositoryMySQL(Connection connection) {
        this.connection = connection;
        //this.clientRepository = clientRepository;
    }

    @Override
    public Notification<Account> findAccountById(Long accountID) {
        Notification<Account> findAccountByIdNotification = new Notification<>();
        try {
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM " + ACCOUNT + " WHERE id=" + accountID;
            ResultSet resultSet = statement.executeQuery(query);
            if(resultSet.next()){
                Account account = new AccountBuilder()
                        .setID(resultSet.getLong("id"))
                        .setIdentificationNumber(resultSet.getString("identificationNumber"))
                        .setType(resultSet.getString("type"))
                        .setAmountMoney(resultSet.getLong("amountOfMoney"))
                        .setCreationDate(resultSet.getDate("creationDate"))
                        .setClientID(resultSet.getLong("client_id"))
                        .build();
                findAccountByIdNotification.setResult(account);
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
                Account account = new AccountBuilder()
                        .setID(resultSet.getLong("id"))
                        .setIdentificationNumber(resultSet.getString("identificationNumber"))
                        .setType(resultSet.getString("type"))
                        .setAmountMoney(resultSet.getLong("amountOfMoney"))
                        .setCreationDate(resultSet.getDate("creationDate"))
                        .setClientID(resultSet.getLong("client_id"))
                        .build();
                findAccountByClientNotification.setResult(account);
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
    public boolean updateType(Long id, String type) {
        try {
            Statement statement = connection.createStatement();
            String query = "UPDATE "+ ACCOUNT + " SET type='" + type + "' where id=" + id;
            statement.executeUpdate(query);
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updateAmountOfMoney(Long id, Long money) {
        try {
            Statement statement = connection.createStatement();
            String query = "UPDATE "+ ACCOUNT + " SET amountOfMoney=" + money + " where id=" + id;
            statement.executeUpdate(query);
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
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

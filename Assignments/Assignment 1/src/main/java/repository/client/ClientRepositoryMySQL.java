package repository.client;

import model.Client;
import model.builder.ClientBuilder;
import model.validation.Notification;
import repository.account.AccountRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static database.Constants.Tables.CLIENT;

public class ClientRepositoryMySQL implements ClientRepository {

    private final Connection connection;


    public ClientRepositoryMySQL(Connection connection) {
        this.connection = connection;

    }

    @Override
    public List<Client> findAll() {

        List<Client> clients = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            String query = "Select * from client";
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next())
               clients.add(getClientFromResultSet(resultSet));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return clients;
    }

    @Override
    public Notification<Client> findById(Long id) {
        Notification<Client> findByIDNotification = new Notification<>();
        try {
            Statement statement = connection.createStatement();
            String query = "Select * from " + CLIENT + " where id=" + id;
            ResultSet resultSet = statement.executeQuery(query);
            if(resultSet.next()) {
                Client client = new ClientBuilder()
                        .setID(resultSet.getLong("id"))
                        .setName(resultSet.getString("name"))
                        .setSurname(resultSet.getString("surname"))
                        .setIdentityCardNumber(resultSet.getString("identityCardNumber"))
                        .setCNP(resultSet.getString("cnp"))
                        .setAddress(resultSet.getString("address"))
                        .setMail(resultSet.getString("mail"))
                        .setPhone(resultSet.getString("phone"))
                        .build();

                findByIDNotification.setResult(client);
                return findByIDNotification;
            } else {
                findByIDNotification.addError("Invalid Client ID!");
                return findByIDNotification;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            findByIDNotification.addError("Something is wrong with the Database!");
        }
        return findByIDNotification;
    }

    @Override
    public Notification<Client>  findByCNP(String CNP) {
        Notification<Client> findByCNPNotification = new Notification<>();
        try {
            Statement statement = connection.createStatement();
            String query = "Select * from " + CLIENT + " where cnp=" + CNP;
            ResultSet resultSet = statement.executeQuery(query);
            if(resultSet.next()) {
                Client client = new ClientBuilder()
                        .setID(resultSet.getLong("id"))
                        .setName(resultSet.getString("name"))
                        .setSurname(resultSet.getString("surname"))
                        .setIdentityCardNumber(resultSet.getString("identityCardNumber"))
                        .setCNP(resultSet.getString("cnp"))
                        .setAddress(resultSet.getString("address"))
                        .setMail(resultSet.getString("mail"))
                        .setPhone(resultSet.getString("phone"))
                        .build();
                findByCNPNotification.setResult(client);
                return findByCNPNotification;
            } else {
                findByCNPNotification.addError("Invalid CNP!");
                return findByCNPNotification;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            findByCNPNotification.addError("Something is wrong with the Database!");
        }
        return findByCNPNotification;
    }

    @Override
    public Notification<Client>  findByFullName(String name, String surname) {
        Notification<Client> findByFullNameNotification = new Notification<>();
        try {
            Statement statement = connection.createStatement();
            String query = "Select * from " + CLIENT + " where name= '" + name + "' and surname= '" + surname + "'";
            ResultSet resultSet = statement.executeQuery(query);
            if(resultSet.next()) {
                Client client = new ClientBuilder()
                        .setID(resultSet.getLong("id"))
                        .setName(resultSet.getString("name"))
                        .setSurname(resultSet.getString("surname"))
                        .setIdentityCardNumber(resultSet.getString("identityCardNumber"))
                        .setCNP(resultSet.getString("cnp"))
                        .setAddress(resultSet.getString("address"))
                        .setMail(resultSet.getString("mail"))
                        .setPhone(resultSet.getString("phone"))
                        .build();

                findByFullNameNotification.setResult(client);
                return findByFullNameNotification;
            } else {
                findByFullNameNotification.addError("Invalid Name or Surname!");
                return findByFullNameNotification;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            findByFullNameNotification.addError("Something is wrong with the Database!");
        }
        return findByFullNameNotification;
    }

    @Override
    public boolean addClient(Client client) {
        try {
            PreparedStatement insertStatement = connection
                    .prepareStatement("INSERT INTO "+ CLIENT +" values (null, ?, ?, ?, ?, ?, ?, ?)");
            insertStatement.setString(1, client.getName());
            insertStatement.setString(2, client.getSurname());
            insertStatement.setString(3, client.getIdentityCardNumber());
            insertStatement.setString(4, client.getCNP());
            insertStatement.setString(5, client.getAddress());
            insertStatement.setString(6, client.getMail());
            insertStatement.setString(7, client.getPhoneNumber());
            insertStatement.executeUpdate();
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updateClientSurname(Client client, String surname) {
        try {
            Statement statement = connection.createStatement();
            String query = "UPDATE "+ CLIENT + " SET surname= '" + surname + "' WHERE id=" + client.getId();
            statement.executeUpdate(query);
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updateClientICN(Client client, String ICN) {
        try {
            Statement statement = connection.createStatement();
            String query = "UPDATE "+ CLIENT + " SET identityCardNumber= '" + ICN + "' WHERE id=" + client.getId();
            statement.executeUpdate(query);
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updateAddress(Client client, String address) {
        try {
            Statement statement = connection.createStatement();
            String query = "UPDATE "+ CLIENT + " SET address= '" + address + "' WHERE id=" + client.getId();
            statement.executeUpdate(query);
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updateMail(Client client, String mail) {
        try {
            Statement statement = connection.createStatement();
            String query = "UPDATE "+ CLIENT + " SET mail= '" + mail + "' WHERE id=" + client.getId();
            statement.executeUpdate(query);
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updatePhone(Client client, String phone) {
        try {
            Statement statement = connection.createStatement();
            String query = "UPDATE "+ CLIENT + " SET phone=" + phone + " WHERE id=" + client.getId();
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
            String query = "DELETE FROM " + CLIENT + " WHERE id >= 0";
            String alterTable = "ALTER TABLE client AUTO_INCREMENT = 1";
            statement.executeUpdate(query);
            statement.execute(alterTable);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    private Client getClientFromResultSet(ResultSet resultSet) throws SQLException {
        return new ClientBuilder()
                .setID(resultSet.getLong("id"))
                .setName(resultSet.getString("name"))
                .setSurname(resultSet.getString("surname"))
                .setIdentityCardNumber(resultSet.getString("identityCardNumber"))
                .setCNP(resultSet.getString("cnp"))
                .setAddress(resultSet.getString("address"))
                .setMail(resultSet.getString("mail"))
                .setPhone(resultSet.getString("phone"))
                .build();
    }

}

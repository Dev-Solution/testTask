package store;

import models.Account;
import models.Client;
import service.UtilService;

import java.sql.*;
import java.util.List;

/**
 * Created by Sergey on 19.12.2015.
 */
public class JdbcStorage implements Storage {
    private Connection connection;

    public JdbcStorage() {
        Settings settings = Settings.getInstance();
        try {
            Class.forName(settings.value("jdbc.driver_class"));
            connection = DriverManager.getConnection(settings.value("jdbc.url"),settings.value("jdbc.username"),
                                                     settings.value("jdbc.password")
            );
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public double getClientBalance(Client client, List<Account> accounts) {
        double balance = 0;
        String accountsCommaText = UtilService.getCommaTextAccount(accounts);
        String SUM_BALANCE =
                " SELECT SUM(Balance) as SumBalance " +
                " FROM Account " +
                " WHERE NumberAccount IN ("+accountsCommaText+") AND ClientId = ? ";

        try(PreparedStatement statement = connection.prepareStatement(SUM_BALANCE)){
            statement.setInt(1, client.getId());
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                balance = rs.getDouble("SumBalance");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return balance;
    }

    @Override
    public Client getClientWithMaxBalance(List<Account> accounts) {
        Client client = null;
        String accountsCommaText = UtilService.getCommaTextAccount(accounts);
        String CLIENT_WITH_MAX_BALANCE =
                " SELECT Client.* FROM Client " +
                " INNER JOIN (SELECT ClientId, MAX(Balance) " +
                "             FROM (SELECT ClientId,(Account.Balance) " +
                "                   FROM Account " +
                "                   WHERE Account.NumberAccount IN ("+accountsCommaText+") " +
                "                   ) AS AccounList " +
                "             ) AS MaxAccount ON Client.ID = MaxAccount.ClientId ";
        try(Statement statement = connection.createStatement()){
            ResultSet rs = statement.executeQuery(CLIENT_WITH_MAX_BALANCE);
            while (rs.next()) {
                client = new Client(rs.getInt("Id"), rs.getString("FirstName"), rs.getString("MiddleName"),
                        rs.getString("LastName"), rs.getString("Telephone"),
                        rs.getString("Address"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return client;
    }

    @Override
    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}

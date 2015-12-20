package service;

import store.Settings;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by Sergey on 20.12.2015.
 */
public class JdbcService {
    private static Connection connection;
    static {
        Settings settings = Settings.getInstance();
        try {
            Class.forName(settings.value("jdbc.driver_class"));
            connection = DriverManager.getConnection(settings.value("jdbc.url"), settings.value("jdbc.username"),
                                                     settings.value("jdbc.password")
            );
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    private static final String CREATE_TABLES_1 =
            " CREATE TABLE IF NOT EXISTS Client( " +
            " Id INT (15) AUTO_INCREMENT, " +
            " FirstName varchar(30) NOT NULL, " +
            " MiddleName varchar (30) NOT NULL, " +
            " LastName varchar(30) NOT NULL, " +
            " Telephone varchar(20) NOT NULL, " +
            " Address varchar(255) NOT NULL, " +
            " PRIMARY KEY (Id) ); " ;

    private static final String CREATE_TABLES_2 =
            " Create TABLE IF NOT EXISTS Account( " +
            " Id INT (15) AUTO_INCREMENT, " +
            " ClientId INT(15) NOT NULL, " +
            " NumberAccount INT(15)  NOT NULL, " +
            " Balance DOUBLE DEFAULT 0, " +
            " PRIMARY KEY (Id) , " +
            " FOREIGN KEY (ClientId) REFERENCES client(Id), " +
            " UNIQUE (NumberAccount)); " ;

    private static final String CREATE_TABLES_3 =
            " CREATE TABLE IF NOT EXISTS Payment( "+
            " Id INT (15) AUTO_INCREMENT, " +
            " ToAccountId INT(15) NOT NULL, " +
            " FromAccountId INT(15) NOT NULL, " +
            " Sum DOUBLE NOT NULL, " +
            " Date DateTime NOT NULL, " +
            " PRIMARY KEY (Id), " +
            " FOREIGN KEY (ToAccountId) REFERENCES Account(Id), " +
            " FOREIGN KEY (FromAccountId) REFERENCES Account(Id)); ";

    private static final String ADD_CLIENT_1 =
            " INSERT INTO client ( " +
                    " FirstName, " +
                    " MiddleName, " +
                    " LastName, " +
                    " Telephone, " +
                    " Address)" +
                    " value ('Иван','Иванович','Иванов','+380970000007','Харьков'); " ;

    private static final String ADD_CLIENT_2 =
            " INSERT INTO client ( " +
                    " FirstName, " +
                    " MiddleName, " +
                    " LastName, " +
                    " Telephone, " +
                    " Address)" +
                    " value ('Сидор','Сидорович','Сидоров','+380951111115','Киев'); " ;

    private static final String ADD_CLIENT_3 =
            " INSERT INTO client ( " +
                    " FirstName, " +
                    " MiddleName, " +
                    " LastName, " +
                    " Telephone, " +
                    " Address) " +
                    " value ('Петр','Петрович','Петров','+380963333300','Одесса'); " ;

    private static final String ADD_Account_1 =
            " INSERT INTO Account ( " +
                    " ClientId, " +
                    " NumberAccount, " +
                    " Balance) " +
                    " value (1,1001,500) ; ";

    private static final String ADD_Account_2 =
            " INSERT INTO Account ( " +
                    " ClientId, " +
                    " NumberAccount, " +
                    " Balance)" +
                    " value (2,1002,1500); " ;

    private static final String ADD_Account_3 =
            " INSERT INTO Account ( " +
                    " ClientId, " +
                    " NumberAccount, " +
                    " Balance)" +
                    " value (3,1003,1200); " ;

    private static final String ADD_Account_4 =
            " INSERT INTO Account ( " +
                    " ClientId, " +
                    " NumberAccount, " +
                    " Balance)" +
                    " value (1,1004,2000); " ;

    private static final String ADD_Account_5 =
            " INSERT INTO Account ( " +
                    " ClientId, " +
                    " NumberAccount, "+
                    " Balance)" +
                    " value (2,1005,2000); " ;

    private static final String ADD_Account_6 =
            " INSERT INTO Account ( "+
                    " ClientId, " +
                    " NumberAccount, "+
                    " Balance)" +
                    " value (3,1006,500); ";

    private static final String DROP_DB =
            " DROP TABLE IF EXISTS Payment,Account,Client; ";

    public JdbcService() {

    }

    public static void createDB(){
        try(Statement statement = connection.createStatement()) {
            statement.addBatch(CREATE_TABLES_1);
            statement.addBatch(CREATE_TABLES_2);
            statement.addBatch(CREATE_TABLES_3);
            statement.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void addDate(){
        try(Statement statement = connection.createStatement()) {
            statement.addBatch(ADD_CLIENT_1);
            statement.addBatch(ADD_CLIENT_2);
            statement.addBatch(ADD_CLIENT_3);
            statement.addBatch(ADD_Account_1);
            statement.addBatch(ADD_Account_2);
            statement.addBatch(ADD_Account_3);
            statement.addBatch(ADD_Account_4);
            statement.addBatch(ADD_Account_5);
            statement.addBatch(ADD_Account_6);
            statement.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void dropDB(){
        try(Statement statement = connection.createStatement()) {
            statement.addBatch(DROP_DB);
            statement.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}

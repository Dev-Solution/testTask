package store;

import models.Account;
import models.Client;

import java.util.List;

/**
 * Created by Sergey on 19.12.2015.
 */
public class UserStorage implements Storage {
    private static UserStorage INSTANCE = new UserStorage();

    private Storage storage = new JdbcStorage();

    public static UserStorage getInstance() {
        return INSTANCE;
    }

    private UserStorage() {
    }

    @Override
    public double getClientBalance(Client client, List<Account> accounts) {
        return storage.getClientBalance(client,accounts);
    }

    @Override
    public Client getClientWithMaxBalance(List<Account> accounts) {
        return storage.getClientWithMaxBalance(accounts);
    }

    @Override
    public void close() {
        storage.close();
    }

}

package service;

import models.Account;
import models.Client;
import store.Storage;
import store.UserStorage;

import java.util.List;

/**
 * Created by Sergey on 19.12.2015.
 */
public class ClientServiceImpl implements ClientService {

    Storage storage = UserStorage.getInstance();

    @Override
    public double getClientBalance(Client client, List<Account> accounts) {
        double balance = storage.getClientBalance(client,accounts);
        return balance;
    }

    @Override
    public Client getClientWithMaxBalance(List<Account> accounts) {
        Client client = storage.getClientWithMaxBalance(accounts);
        return client;
    }
    public void close() {
        storage.close();
    }
}

package service;

import models.Account;
import models.Client;

import java.util.List;

/**
 * Created by Sergey on 19.12.2015.
 */
public interface ClientService {
    double getClientBalance(Client client, List<Account> accounts);
    Client getClientWithMaxBalance(List<Account> accounts);
}

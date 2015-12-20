package store;


import models.Account;
import models.Client;
import org.junit.Test;
import service.JdbcService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class JdbcStorageTest {
    private static final double DELTA = 1e-15;
    @Test
    public void testGetClientBalanceNull() throws Exception {
        JdbcService.dropDB();
        JdbcService.createDB();
        JdbcService.addDate();
        JdbcStorage storage = new JdbcStorage();
        List<Account> account = new ArrayList<>();
        Account acc1 = new Account(1,11001);
        Account acc2 = new Account(2,11002);
        Account acc3 = new Account(3,11003);
        Account acc4 = new Account(1,11004);
        account.add(acc1);
        account.add(acc2);
        account.add(acc3);
        account.add(acc4);
        Client client = storage.getClientWithMaxBalance(account);
        assertNull(client);
        storage.close();

    }
    @Test
    public void testGetClientBalanceEquals() throws Exception {
        JdbcService.dropDB();
        JdbcService.createDB();
        JdbcService.addDate();
        JdbcStorage storage = new JdbcStorage();
        List<Account> account = new ArrayList<>();
        Account acc1 = new Account(1,1001);
        Account acc2 = new Account(2,1002);
        Account acc3 = new Account(3,1003);
        Account acc4 = new Account(1,1004);
        account.add(acc1);
        account.add(acc2);
        account.add(acc3);
        account.add(acc4);
        Client client1 = new Client(1,"Иван","Иванович","Иванов","+380970000007","Харьков");
        Client client = storage.getClientWithMaxBalance(account);
        assertEquals("Иван",client.getFirstName());
        assertEquals("Иванович",client.getMiddleName());
        assertEquals("Иванов",client.getLastName());
        assertEquals("+380970000007",client.getTelephone());
        assertEquals("Харьков",client.getAddress());
        assertEquals(client1,client);
        storage.close();

    }
    @Test
    public void testGetClientWithMaxBalance() throws Exception {
        JdbcService.dropDB();
        JdbcService.createDB();
        JdbcService.addDate();
        JdbcStorage storage = new JdbcStorage();
        List<Account> account = new ArrayList<>();
        Account acc1 = new Account(1,1001);
        Account acc2 = new Account(2,1002);
        Account acc3 = new Account(3,1003);
        Account acc4 = new Account(1,1004);
        account.add(acc1);
        account.add(acc2);
        account.add(acc3);
        account.add(acc4);
        Client client = new Client(1,"Иван","Иванович","Иванов","+380970000007","Харьков");
        assertEquals(2500, storage.getClientBalance(client, account),DELTA);
        storage.close();

    }
}
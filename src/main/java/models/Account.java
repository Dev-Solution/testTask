package models;

/**
 * Created by Sergey on 19.12.2015.
 */
public class Account {
    private int id;
    private int clientID;
    private int numberAccount;
    private double balance;

    public Account(int clientID, int numberAccount, double balance) {
        this.clientID = clientID;
        this.numberAccount = numberAccount;
        this.balance = balance;
    }

    public Account(int clientID,int numberAccount) {
        this.clientID = clientID;
        this.numberAccount = numberAccount;
        this.balance = 0;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getClientID() {
        return clientID;
    }

    public void setClientID(int clientID) {
        this.clientID = clientID;
    }

    public int getNumberAccount() {
        return numberAccount;
    }

    public void setNumberAccount(int numberAccount) {
        this.numberAccount = numberAccount;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Account account = (Account) o;
        return (numberAccount == account.numberAccount) ;
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + numberAccount;
        return result;
    }

    @Override
    public String toString() {
        return String.valueOf(numberAccount);
    }

}

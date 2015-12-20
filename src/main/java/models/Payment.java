package models;

import java.util.Date;

/**
 * Created by Sergey on 19.12.2015.
 */
public class Payment {
    private int id ;
    private int fromAccountID;
    private int toAccountID;
    private double sum;
    private Date date;

    public Payment(int fromAccountID, int toAccountID, double sum) {
        this.fromAccountID = fromAccountID;
        this.toAccountID = toAccountID;
        this.sum = sum;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFromAccountID() {
        return fromAccountID;
    }

    public void setFromAccountID(int fromAccountID) {
        this.fromAccountID = fromAccountID;
    }

    public int getToAccountID() {
        return toAccountID;
    }

    public void setToAccountID(int toAccountID) {
        this.toAccountID = toAccountID;
    }

    public double getSum() {
        return sum;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Payment payment = (Payment) o;

        if (fromAccountID != payment.fromAccountID) return false;
        if (id != payment.id) return false;
        if (Double.compare(payment.sum, sum) != 0) return false;
        if (toAccountID != payment.toAccountID) return false;
        if (date != null ? !date.equals(payment.date) : payment.date != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id;
        result = 31 * result + fromAccountID;
        result = 31 * result + toAccountID;
        temp = Double.doubleToLongBits(sum);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (date != null ? date.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Payment: " +
                "fromAccountID=" + fromAccountID +
                ", toAccountID=" + toAccountID +
                ", sum=" + sum +
                ", date=" + date ;
    }
}

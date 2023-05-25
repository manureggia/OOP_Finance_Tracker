package DataStructure;

import java.io.Serializable;
import java.lang.String;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

/**
 * The type Transaction.
 */
public class Transaction implements Serializable {

    private Date date;
    private String description;
    private double amount;
    private SimpleDateFormat formatter;

    /**
     * Instantiates a new Transaction.
     *
     * @param date        the date
     * @param description the description
     * @param amount      the amount
     */
    public Transaction(Date date, String description, double amount) {
        this.date = date;
        this.description = description;
        this.amount = amount;
        formatter = new SimpleDateFormat("dd/MM/yyyy");
    }

    public Transaction(String date, String description, double amount) {
        formatter = new SimpleDateFormat("dd/MM/yyyy");
        try {
            this.date = formatter.parse(date);
        }catch (ParseException ex){
            System.err.println("Errore nel parsing");
        }
        this.description = description;
        this.amount = amount;

    }


    /**
     * Gets date.
     *
     * @return the date
     */
    public synchronized Date getDate() {
        return date;
    }

    /**
     * Gets string date.
     *
     * @return the string date
     */
    public synchronized String getStringDate() {
        return formatter.format(date);
    }

    /**
     * Sets date.
     *
     * @param date the date
     */
    public synchronized void setDate(Date date) {
        this.date = date;
    }

    /**
     * Gets description.
     *
     * @return the description
     */
    public synchronized String getDescription() {
        return description;
    }

    /**
     * Sets description.
     *
     * @param description the description
     */
    public synchronized void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets amount.
     *
     * @return the amount
     */
    public synchronized double getAmount() {
        return amount;
    }

    /**
     * Sets amount.
     *
     * @param amount the amount
     */
    public synchronized void setAmount(double amount) {
        this.amount = amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transaction that = (Transaction) o;
        return Double.compare(that.amount, amount) == 0 && Objects.equals(date, that.date) && Objects.equals(description, that.description);
    }



}

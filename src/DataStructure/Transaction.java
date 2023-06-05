package DataStructure;

import java.io.Serializable;
import java.lang.String;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

/**
 * La transazione, tiene conto di Data Ammontare e Descrizione.
 */
public class Transaction implements Serializable {
    /**
     * la data della transazione
     */
    private Date date;
    /**
     * la descrizione della transazioe
     */
    private String description;
    /**
     * l'ammontare della transazione che può essere positivo o negativo
     */
    private double amount;
    /**
     * Formattatore per la data
     */
    private final SimpleDateFormat formatter;

    /**
     * Costruttore per la transazione.
     * si passano i parametri e viene creato una transazione
     *
     * @param date        la data
     * @param description la descrizione
     * @param amount      l'ammontare posivo o negtativo
     */
    public Transaction(Date date, String description, double amount) {
        this.date = date;
        this.description = description;
        this.amount = amount;
        formatter = new SimpleDateFormat("dd/MM/yyyy");
    }
    /**
     * Costruttore per la transazione.
     * si passano i parametri e viene creato una transazione
     *
     * @param date        la data in stringa
     * @param description la descrizione
     * @param amount      l'ammontare posivo o negtativo
     */
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
     * restituice la data.
     *
     * @return date
     */
    public synchronized Date getDate() {
        return date;
    }

    /**
     * restituisce la stringa della data.
     *
     * @return data in formato stringa
     */
    public synchronized String getStringDate() {
        return formatter.format(date);
    }

    /**
     * assegna un valore alla data.
     *
     * @param date la data
     */
    public synchronized void setDate(Date date) {
        this.date = date;
    }

    /**
     * restituisce la descrizione.
     *
     * @return stringa contentente la descrizione
     */
    public synchronized String getDescription() {
        return description;
    }

    /**
     * assegna una descrizione.
     *
     * @param description stringa contentente la descrizione
     */
    public synchronized void setDescription(String description) {
        this.description = description;
    }

    /**
     * restituisce l'ammontare.
     *
     * @return Double dell'ammontare
     */
    public synchronized double getAmount() {
        return amount;
    }

    /**
     * assegna l'ammontare.
     *
     * @param amount double dell'ammontare
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

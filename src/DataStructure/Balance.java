package DataStructure;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * La classe bilancio che gestisce un vettore di tipo {@link DataStructure.Transaction}.
 */
public class Balance implements Serializable {


    /**
     * l'array list di tipo transazione
     */
    private final ArrayList<Transaction> transactionVector;
    /**
     * il totale
     */
    private double total;

    /**
     * Il costruttore del bilancio, dove viene inizializzato l'{@link ArrayList} di {@link DataStructure.Transaction}.
     */
    public Balance() {
        transactionVector = new ArrayList<>();
        total = 0;
    }

    /**
     * Restituisce il vettore di transazione
     * @return vettore di transazioni
     */
    public ArrayList<Transaction> getTransactionVector() {
        return transactionVector;
    }

    /**
     * Aggiunge una transazione.
     *
     * @param e la transazioe da aggiungere
     */
    public synchronized void addTransaction(Transaction e){
        transactionVector.add(e);
        total += e.getAmount();
    }

    /**
     * rimuove la transazione.
     *
     * @param e la transazione da rimuovere
     */
    public synchronized  void removeTransaction(Transaction e){
        transactionVector.remove(e);
        total -= e.getAmount();
    }

    /**
     * calcola il totale .
     *
     * @return il totale
     */
    public synchronized double calcTotal(){
        total = 0;
        for (Transaction t : transactionVector) {
            total += t.getAmount();
        }
        return total;
    }

    /**
     * ritorna il valore della variabile totale.
     *
     * @return il totale precedentemente calcolato
     */
    public double  getTotal(){
        return total;
    }

    /**
     * restituisce la dimensione del vettore di transazioni.
     *
     * @return dimensione del vettore
     */
    public int getSize(){
        return transactionVector.size();
    }

    /**
     * Restituisce una transazione ad un determinato indice.
     *
     * @param index l'indice
     * @return la transazione
     */
    public Transaction getElementAt(int index){
        return transactionVector.get(index);
    }

}

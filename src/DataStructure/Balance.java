package DataStructure;

import java.util.ArrayList;
import java.util.Vector;

/**
 * The type Balance.
 */
public class Balance {
    private ArrayList<Transaction> transactionVector;
    private double total;

    /**
     * Instantiates a new Balance.
     */
    public Balance() {
        transactionVector = new ArrayList<Transaction>();
        total = 0;
    }

    /**
     * Add transaction.
     *
     * @param e the transaction
     */
    public void addTransaction(Transaction e){
        transactionVector.add(e);
        total += e.getAmount();
    }

    /**
     * Remove transaction.
     *
     * @param e the transaction
     */
    public void removeTransaction(Transaction e){
        transactionVector.remove(e);
        total -= e.getAmount();
    }

    /**
     * Calc total double.
     *
     * @return the total
     */
    public double calcTotal(){
        total = 0;
        for (Transaction t : transactionVector) {
            total += t.getAmount();
        }
        return total;
    }

    /**
     * Get total double.
     *
     * @return the total
     */
    public double  getTotal(){
        return total;
    }

    /**
     * Get size int.
     *
     * @return the int
     */
    public int getSize(){
        return transactionVector.size();
    }

    /**
     * Get element at transaction.
     *
     * @param index the index
     * @return the transaction
     */
    public Transaction getElementAt(int index){
        return transactionVector.get(index);
    }

}

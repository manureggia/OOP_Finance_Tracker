package DataStructure;

import java.util.ArrayList;
import java.util.Vector;

public class Balance {
    private ArrayList<Transaction> transactionVector;
    private double total;

    public Balance() {
        transactionVector = new ArrayList<Transaction>();
        total = 0;
    }

    public void addTransaction(Transaction e){
        transactionVector.add(e);
        total += e.getAmount();
    }

    public void removeTransaction(Transaction e){
        transactionVector.remove(e);
        total -= e.getAmount();
    }

    public double calcTotal(){
        total = 0;
        for (Transaction t : transactionVector) {
            total += t.getAmount();
        }
        return total;
    }
    public double  getTotal(){
        return total;
    }

    public int getSize(){
        return transactionVector.size();
    }

    public Transaction getElementAt(int index){
        return transactionVector.get(index);
    }

}

package DataStructure.SaveLoad;

import DataStructure.Balance;
import DataStructure.Transaction;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.Scanner;

/**
 * Salvataggio in file tabulati (separati da TAB), estende {@link DataStructure.SaveLoad.AbstractSaver}.
 */
public class TabulatedSave extends AbstractSaver {
    /**
     * Il costruttore di TabulatedSave, permette il savlataggio in file di tipo testuale
     * ogni voce è serparata da un tab.
     */
    public TabulatedSave() {
    }

    @Override
    public void saveData(Balance b, File f){
        Iterator<Transaction> iterator = b.getTransactionVector().iterator();
        try {
            FileWriter writer = new FileWriter(f);
            while (iterator.hasNext()){
                Transaction t = iterator.next();
                writer.write(t.getStringDate()+"\t"+t.getAmount()+"\t"+t.getDescription()+"\n");
            }
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Balance loadData(File f) throws IOException {
        Balance b = new Balance();
        Scanner reader = new Scanner(f);
        while(reader.hasNextLine()){
            String[] riga = reader.nextLine().split("\t");
            if(riga.length != 3) {
                throw new RuntimeException("File non formattatto correttamente");
            }
            b.addTransaction(new Transaction(riga[0],riga[2],Double.parseDouble(riga[1])));
        }
        reader.close();
        return b;
    }
}

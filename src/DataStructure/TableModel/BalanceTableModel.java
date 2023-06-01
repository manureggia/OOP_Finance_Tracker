package DataStructure.TableModel;

import DataStructure.SaveLoad.AbstractSaver;
import DataStructure.Balance;
import DataStructure.Transaction;

import javax.swing.table.AbstractTableModel;
import java.io.File;
import java.io.IOException;
import java.util.Date;


/**
 * Il Table model del bilancio.
 */
public class BalanceTableModel extends AbstractTableModel {
    /**
     * il Bilancio
     */
    private Balance balance;
    /**
     * Nome delle colonne
     */
    private final String[] columnName = {"Date", "Description", "Amount"};


    /**
     * Costruttode del table model. Prende in input il bilancio per poterlo visualizzare nella tabella
     *
     * @param balance il Bilancio
     */
    public BalanceTableModel(Balance balance) {
        this.balance = balance;
    }

    @Override
    public int getRowCount() {
        return balance.getSize();
    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Transaction tran = balance.getElementAt(rowIndex);
        return switch (columnIndex) {
            case 0 -> tran.getStringDate();
            case 1 -> tran.getDescription();
            case 2 -> tran.getAmount();
            default -> throw new IllegalStateException("Unexpected value: " + columnIndex);
        };
    }

    @Override
    public String getColumnName(int col) {
        return columnName[col];
    }

    /**
     * Inserisce una transazione all'interno del bilancio.
     *
     * @param t la transazione di tipo {@link DataStructure.Transaction}
     */
    public void insertRow(Transaction t){
        balance.addTransaction(t);
        fireTableDataChanged();
    }

    /**
     * Cancella un elemento a un certo indice.
     *
     * @param index l'indice per la cancellazione
     */
    public void deleteElementAt(int index){
        balance.removeTransaction(balance.getElementAt(index));
        fireTableDataChanged();
    }

    /**
     * Restituisce una transazione ad un determinato indice.
     *
     * @param index l'indice
     * @return la transazione {@link DataStructure.Transaction}
     */
    public Transaction getElementAt(int index){
        return balance.getElementAt(index);
    }

    /**
     * Moodifica la transazione con delle nuove voci.
     *
     * @param t           La transazione da modificare
     * @param date        la Data
     * @param description la Descrizione
     * @param ammount     l'ammontare
     */
    public void modifyRow(Transaction t, Date date, String description, Double ammount){
        t.setDate(date);
        t.setAmount(ammount);
        t.setDescription(description);
        fireTableDataChanged();
    }

    /**
     * Salva su un file il bilancio.
     *
     * @param saver la tipologia del salvataggio/esportazione {@link DataStructure.SaveLoad.AbstractSaver}
     * @param file  il file su cui salvare
     * @throws IOException  io exception
     */
    public void saveFile(AbstractSaver saver, File file) throws IOException {
        saver.saveData(balance,file);
    }

    /**
     * Caricamento dei dati.
     *
     * @param saver la tipologia del caricamento/importazione {@link DataStructure.SaveLoad.AbstractSaver}
     * @param file  il file da cui caricare
     * @throws IOException io exception
     */
    public void loadData(AbstractSaver saver, File file) throws IOException {
        balance = saver.loadData(file);
        fireTableDataChanged();
    }

    /**
     * restituisce il totale dopo il calcolo.
     *
     * @return il totale
     */
    public double getCalTotal(){
        return balance.calcTotal();
    }
}

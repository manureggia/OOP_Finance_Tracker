package DataStructure.TableModel;

import DataStructure.SaveLoad.AbstractSaver;
import DataStructure.Balance;
import DataStructure.Transaction;

import javax.swing.table.AbstractTableModel;
import java.io.File;
import java.io.IOException;
import java.util.Date;


public class BalanceTableModel extends AbstractTableModel {

    private Balance balance;
    private final String[] columnName = {"Date", "Description", "Amount"};


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

    public void insertRow(Transaction t){
        balance.addTransaction(t);
        fireTableDataChanged();
    }

    public void deleteElementAt(int index){
        balance.removeTransaction(balance.getElementAt(index));
        fireTableDataChanged();
    }

    public Transaction getElementAt(int index){
        return balance.getElementAt(index);
    }

    public void modifyRow(Transaction t, Date date, String description, Double ammount){
        t.setDate(date);
        t.setAmount(ammount);
        t.setDescription(description);
        fireTableDataChanged();
    }

    public void saveFile(AbstractSaver saver, File file) throws IOException {
        saver.saveData(balance,file);
    }

    public void loadData(AbstractSaver saver, File file) throws IOException {
        balance = saver.loadData(file);
        fireTableDataChanged();
    }

    public double getCalTotal(){
        return balance.calcTotal();
    }
}

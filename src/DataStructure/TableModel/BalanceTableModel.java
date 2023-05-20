package DataStructure.TableModel;

import DataStructure.Balance;
import DataStructure.SaverLoader;
import DataStructure.Transaction;

import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;


public class BalanceTableModel extends AbstractTableModel {

    private Balance balance;
    private String[] columnName = {"Date", "Description", "Amount"};
    private SaverLoader saverLoader;


    public BalanceTableModel(Balance balance) {
        this.balance = balance;
        this.saverLoader = new SaverLoader();
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
        switch (columnIndex){
            case 0:
                return tran.getStringDate();
            case 1:
                return tran.getDescription();
            case 2:
                return tran.getAmount();
            default:
                throw new IllegalStateException("Unexpected value: " + columnIndex);
        }
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

    public void saveFile(File file) throws IOException {
        saverLoader.saveData(balance,file);
    }

    public void loadData(File file) throws IOException {
        balance = saverLoader.loadData(file);
        fireTableDataChanged();
    }

    public double getCalTotal(){
        return balance.calcTotal();
    }
}

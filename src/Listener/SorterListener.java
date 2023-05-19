package Listener;

import DataStructure.TableModel.BalanceTableModel;
import DataStructure.Transaction;

import javax.swing.*;
import javax.swing.event.RowSorterEvent;
import javax.swing.event.RowSorterListener;
import javax.swing.table.TableModel;

public class SorterListener implements RowSorterListener {

    JTable table;
    JTextField totaltxt;

    public SorterListener(JTable table, JTextField totaltxt) {
        this.table = table;
        this.totaltxt = totaltxt;
    }

    @Override
    public void sorterChanged(RowSorterEvent e) {
        BalanceTableModel model = (BalanceTableModel) table.getModel();
        RowSorter<? extends TableModel> sorter = table.getRowSorter();
        float total=0;
        Transaction t;
        for(int i=0; i<sorter.getViewRowCount(); i++){
            t =  model.getElementAt(sorter.convertRowIndexToModel(i));
            total += t.getAmount();
        }
        totaltxt.setText("Total: "+total);
    }
}

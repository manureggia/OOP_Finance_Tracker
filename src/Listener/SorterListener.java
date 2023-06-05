package Listener;

import DataStructure.TableModel.BalanceTableModel;
import DataStructure.Transaction;

import javax.swing.*;
import javax.swing.event.RowSorterEvent;
import javax.swing.event.RowSorterListener;
import javax.swing.table.TableModel;

/**
 * La classe SorterListener che implementa {@link RowSorterListener}.
 */
public class SorterListener implements RowSorterListener {

    private final JTable table;
    private final JTextField totaltxt;

    /**
     * Inizializza un oggetto di tipo SorterListener.
     * Quando la tabella cambia per via di un filtro l'evento viene scatenato e il totale viene ricalcolato solo per gli
     * elementi che sono visualizzabili e scritto nella casella apposita
     *
     * @param table    La tabella
     * @param totaltxt Il field del totale
     */
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

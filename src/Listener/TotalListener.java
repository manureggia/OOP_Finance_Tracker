package Listener;

import DataStructure.TableModel.BalanceTableModel;
import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

/**
 * La Calsse TotalListener che calcola il totale.
 */
public class TotalListener implements TableModelListener {
    private final JTextField textField;
    private final JTable table;

    /**
     * Inizializza l'oggetto TotalListener.
     * quando la tabella scatena un firetabledatachanged scatta l'evento che ricalcola il totale per poi scriverlo
     * nella casella apposita
     *
     * @param textField il field del totale
     * @param table     la tabella
     */
    public TotalListener(JTextField textField, JTable table) {
        this.textField = textField;
        this.table = table;
    }

    @Override
    public void tableChanged(TableModelEvent e) {
        BalanceTableModel model = (BalanceTableModel) table.getModel();
        textField.setText("Total: "+model.getCalTotal());
    }
}

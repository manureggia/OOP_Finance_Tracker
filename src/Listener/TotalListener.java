package Listener;

import DataStructure.TableModel.BalanceTableModel;
import DataStructure.*;
import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

public class TotalListener implements TableModelListener {
    JTextField textField;
    JTable table;

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

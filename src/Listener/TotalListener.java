package Listener;

import DataStructure.TableModel.BalanceTableModel;
import DataStructure.*;
import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

public class TotalListener implements TableModelListener {
    JTextField textField;
    Balance balance;

    public TotalListener(JTextField textField, Balance balance) {
        this.textField = textField;
        this.balance = balance;
    }

    @Override
    public void tableChanged(TableModelEvent e) {
        textField.setText("Total: "+balance.calcTotal());
    }
}

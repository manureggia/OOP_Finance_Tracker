package Panels;

import DataStructure.Balance;
import DataStructure.TableModel.BalanceTableModel;
import DataStructure.Transaction;
import Listener.TotalListener;

import javax.swing.*;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Il Pannello principale, dove tutti gli elementi principali sono visualizzati.
 */
public class MainPanel extends JPanel implements ActionListener {
    private final JTable table;

    /**
     * Inizializzazione del pannello principale.
     * Qui viene visualizzata la tabella e i bottoni che servono per interagire con essa: REMOVE MODIFY ADD
     * Questi bottoni sono gestiti dall'ActionListener che attraverso (se necessari) altri pannelli permette l'inserimento
     * la modifica e la rimozione di elementi dalla tabella. La rimozione è possibile oltretutto per più elementi contemporaneamente
     *
     * @param balance the balance
     * @param table   the table
     */
    public MainPanel(Balance balance, JTable table, JTextField totaltxt) {
        super();
        this.setLayout(new BorderLayout());
        //creation of the table and the table model
        this.table = table;
        BalanceTableModel model = (BalanceTableModel) table.getModel();

        //creation of a scroll pane
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane,BorderLayout.CENTER);

        JPanel buttons = new JPanel();
        JButton deletebutton = new JButton("Delete");
        deletebutton.addActionListener(this);
        buttons.add(deletebutton);
        JButton modifybutton = new JButton("Modify");
        modifybutton.addActionListener(this);
        buttons.add((modifybutton));
        JButton addbutton = new JButton("Insert");
        addbutton.addActionListener(this);
        buttons.add(addbutton);
        totaltxt.setText("Total: "+balance.getTotal());
        totaltxt.setEditable(false);
        model.addTableModelListener(new TotalListener(totaltxt,table));
        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.add(totaltxt, BorderLayout.CENTER);
        bottomPanel.add(buttons, BorderLayout.SOUTH);
        add(bottomPanel,BorderLayout.SOUTH);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        RowSorter<? extends TableModel> sorter = table.getRowSorter();
        BalanceTableModel model = (BalanceTableModel) table.getModel();
        if(e.getActionCommand().equals("Insert")){
            JFrame addFrame = new JFrame("Insert");
            AddPanel addpanel = new AddPanel(table, addFrame);
            addFrame.add(addpanel);
            addFrame.pack();
            addFrame.setVisible(true);
        } else if (e.getActionCommand().equals("Modify")) {
            Transaction t;
            if(sorter == null) {
                t = model.getElementAt(table.getSelectedRow());
            } else {
                t = model.getElementAt(sorter.convertRowIndexToModel(table.getSelectedRow()));
            }
            JFrame modFrame = new JFrame("Modify");
            ModifyPanel modifyPanel = new ModifyPanel(table,t,modFrame);
            modFrame.add(modifyPanel);
            modFrame.pack();
            modFrame.setVisible(true);
        }else {
            int[] selectedRows = table.getSelectedRows();
            if (sorter == null){
                for(int i=selectedRows.length -1; i >= 0; i--){
                    model.deleteElementAt(selectedRows[i]);
                }
            }
            else {
                for(int i=selectedRows.length -1; i >= 0; i--) {
                    model.deleteElementAt(sorter.convertRowIndexToModel(selectedRows[i]));
                }
            }
        }
    }
}

package Panels;

import DataStructure.Balance;
import DataStructure.TableModel.BalanceTableModel;
import DataStructure.Transaction;
import Listener.TotalListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainPanel extends JPanel implements ActionListener {
    private Balance balance;
    private JTable table;

    public MainPanel(Balance balance, JTable table) {
        super();
        this.balance = balance;
        this.setLayout(new BorderLayout());
        //creation of the table and the table model
        this.table = table;
        BalanceTableModel model = (BalanceTableModel) table.getModel();

        //creation of a scroll pane
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane,BorderLayout.NORTH);

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
        JTextField totaltxt = new JTextField("Total: "+balance.getTotal());
        totaltxt.setEditable(false);
        model.addTableModelListener(new TotalListener(totaltxt,this.balance));

        add(totaltxt, BorderLayout.CENTER);

        add(buttons, BorderLayout.SOUTH);
        //AddPanel addpanel = new AddPanel(balance,table);
        //add(addpanel,BorderLayout.SOUTH);


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("Insert")){
            JFrame addFrame = new JFrame("Insert");
            AddPanel addpanel = new AddPanel(table, addFrame);
            addFrame.add(addpanel);
            addFrame.pack();
            addFrame.setVisible(true);
        } else if (e.getActionCommand().equals("Modify")) {
            Transaction t = balance.getElementAt(table.getSelectedRow());
            JFrame modFrame = new JFrame("Modify");
            ModifyPanel modifyPanel = new ModifyPanel(table,t,modFrame);
            modFrame.add(modifyPanel);
            modFrame.pack();
            modFrame.setVisible(true);
        }else {
            BalanceTableModel model = (BalanceTableModel) table.getModel();
            model.deleteElementAt(table.getSelectedRow());
        }
    }
}

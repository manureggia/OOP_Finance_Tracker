package Panels;
import DataStructure.*;
import DataStructure.TableModel.BalanceTableModel;
import DataStructure.TableModel.DataFilter;

import javax.swing.*;
import javax.swing.table.TableRowSorter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.util.Calendar;
import java.util.Date;

/**
 * The type Menu bar.
 */
public class MenuBar extends JMenuBar implements ActionListener {
    /**
     * The Balance.
     */
    Balance balance;
    /**
     * The Table.
     */
    JTable table;

    /**
     * Instantiates a new Menu bar.
     *
     * @param balance the balance
     * @param table   the table
     */
    public MenuBar(Balance balance, JTable table) {
        super();
        this.balance = balance;
        this.table = table;
        JMenu sorter = new JMenu("Filter");
        JMenu file = new JMenu("File");
        //sorting menu

        JCheckBoxMenuItem none = new JCheckBoxMenuItem("None",true);
        JCheckBoxMenuItem week = new JCheckBoxMenuItem("Last Week");
        JCheckBoxMenuItem month = new JCheckBoxMenuItem("Last Month");
        JCheckBoxMenuItem year = new JCheckBoxMenuItem("Last Year");
        ButtonGroup group = new ButtonGroup();
        group.add(none);group.add(week);group.add(month);group.add(year);
        none.addActionListener(this);
        week.addActionListener(this);
        month.addActionListener(this);
        year.addActionListener(this);
        sorter.add(none);sorter.add(week); sorter.add(month); sorter.add(year);
        //file menu


        add(file);
        add(sorter);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        FilterTable filter = new FilterTable(table);
        if(e.getActionCommand().equals("None")){
            table.setRowSorter(null);
        }
        else if (e.getActionCommand().equals("Last Month")){
            filter.filterTable("month");
        }
        else if (e.getActionCommand().equals("Last Week")){
            filter.filterTable("week");
        }
        else if (e.getActionCommand().equals("Last Year")) {
            filter.filterTable("year");
        }
        BalanceTableModel model = (BalanceTableModel) table.getModel();
        model.fireTableDataChanged();

    }
}
